/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMIExporter.java,v 1.1 2005/12/14 14:56:08 emerks Exp $
 */
package org.eclipse.xsd.ecore.exporter;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.exporter.ModelExporter;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.ecore.EcoreXMISchemaBuilder;

/**
 * @since 2.2.0
 */
public class XMIExporter extends XSDExporter
{
  public String getID()
  {
    return "org.eclipse.xsd.ecore.exporter.xmi";
  }
  
  protected void doExport(Monitor monitor, ModelExporter.ExportData exportData) throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    EcoreXMISchemaBuilder ecoreXMISchemaBuilder =  new EcoreXMISchemaBuilder();
    
    for (Iterator i = exportData.genPackageToArtifactURI.entrySet().iterator(); i.hasNext();)
    {
      Map.Entry entry = (Map.Entry)i.next();
      GenPackage genPackage = (GenPackage)entry.getKey();
      EPackage ePackage = genPackage.getEcorePackage();
      URI schemaLocationURI = (URI)entry.getValue();
      XSDSchema xsdSchema = (XSDSchema)ecoreXMISchemaBuilder.generate(ePackage).iterator().next();
      for (Iterator j = xsdSchema.getContents().iterator(); j.hasNext(); )
      {
        Object content = j.next();
        if (content instanceof XSDSchemaDirective)
        {
          XSDSchemaDirective xsdSchemaDirective = (XSDSchemaDirective)content;
          String schemaLocation = xsdSchemaDirective.getSchemaLocation();
          EPackage referencedEPackage = genModel.getExtendedMetaData().getPackage(schemaLocation);
          GenPackage referencedGenPackage = genModel.findGenPackage(referencedEPackage);
          URI actualLocation = (URI)exportData.referencedGenPackagesToArtifactURI.get(referencedGenPackage);
          if (actualLocation == null)
          {
            actualLocation = (URI)exportData.genPackageToArtifactURI.get(referencedGenPackage);
          }
          if (actualLocation != null)
          {
            xsdSchemaDirective.setSchemaLocation(actualLocation.toString());
          }
          else if ("XMI.xsd".equals(schemaLocation) && 
                     xsdSchemaDirective instanceof XSDImport && 
                     ExtendedMetaData.XMI_URI.equals(((XSDImport)xsdSchemaDirective).getNamespace()) &&
                     !EcorePackage.eNS_URI.equals(xsdSchema.getTargetNamespace()))
          {
            xsdSchemaDirective.setSchemaLocation("platform:/plugin/org.eclipse.emf.ecore/model/XMI.xsd");
          }
          else if (EcorePackage.eNS_URI.equals(schemaLocation))
          {
            xsdSchemaDirective.setSchemaLocation("platform:/plugin/org.eclipse.emf.ecore/model/EcoreXMI.xsd");
          }
        }
        else if (!(content instanceof XSDAnnotation))
        {
          break;
        }
      }
      
      Resource resource = resourceSet.createResource(schemaLocationURI);
      resource.getContents().add(xsdSchema);
      resource.save(null);
    }
  }
}
