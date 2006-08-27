/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: XSDExporter.java,v 1.6 2006/08/27 16:52:44 emerks Exp $
 */
package org.eclipse.xsd.ecore.exporter;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.exporter.ModelExporter;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.ecore.EcoreSchemaBuilder;
import org.eclipse.xsd.ecore.MapBuilder;

/**
 * @since 2.2.0
 */
public class XSDExporter extends ModelExporter
{
  protected MapBuilder mapBuilder;
  
  public String getID()
  {
    return "org.eclipse.xsd.ecore.exporter";
  }
  
  public void dispose()
  {
    mapBuilder = null;
    super.dispose();
  }
  
  protected String getDefaultArtifactLocation(EPackage ePackage)
  {
    return getDefaultArtifactFileName(ePackage) + ".xsd";
  }
  
  protected String doCheckEPackageArtifactLocation(String location, String packageName)
  {
    if (!location.endsWith(".xsd"))
    {
      return XSDExporterPlugin.INSTANCE.getString("_UI_InvalidArtifactFileNameExtension_message");
    }
    return super.doCheckEPackageArtifactLocation(location, packageName);
  }
  
  protected XSDSchema getSchema(EPackage ePackage)
  {
    if (mapBuilder == null)
    {
      mapBuilder = new EcoreSchemaBuilder(genModel.getExtendedMetaData())
      {
        protected String getQualifiedPackageName(EPackage ePackage) 
        {
          return genModel.findGenPackage(ePackage).getQualifiedPackageName();
        }
      };
    }
    return ((EcoreSchemaBuilder)mapBuilder).getSchema(ePackage);
  }

  protected Diagnostic doExport(Monitor monitor, ModelExporter.ExportData exportData) throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();    
    for (Iterator i = exportData.genPackageToArtifactURI.entrySet().iterator(); i.hasNext();)
    {
      Map.Entry entry = (Map.Entry)i.next();
      GenPackage genPackage = (GenPackage)entry.getKey();
      EPackage ePackage = genPackage.getEcorePackage();
      URI schemaLocationURI = (URI)entry.getValue();
      XSDSchema xsdSchema = getSchema(ePackage);
      for (Iterator j = xsdSchema.getContents().iterator(); j.hasNext(); )
      {
        Object content = j.next();
        if (content instanceof XSDImport)
        {
          XSDImport xsdImport = (XSDImport)content;
          EPackage referencedEPackage = genModel.getExtendedMetaData().getPackage(xsdImport.getNamespace());
          GenPackage referencedGenPackage = genModel.findGenPackage(referencedEPackage);
          URI artifactURI = getReferencedGenPackageArtifactURI(exportData, referencedGenPackage);
          URI importLocationURI = URI.createURI(computeSchemaLocation(xsdImport, artifactURI));
          if (!schemaLocationURI.isRelative() && schemaLocationURI.isHierarchical() && !importLocationURI.isRelative())
          {
            importLocationURI = importLocationURI.deresolve(schemaLocationURI, true, true, false);
          }
          xsdImport.setSchemaLocation(importLocationURI.toString());
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
    
    return Diagnostic.OK_INSTANCE;
  }
  
  protected URI getReferencedGenPackageArtifactURI(ModelExporter.ExportData exportData, GenPackage genPackage)
  {
    URI artifactURI = (URI)exportData.referencedGenPackagesToArtifactURI.get(genPackage);
    if (artifactURI == null)
    {
      artifactURI = (URI)exportData.genPackageToArtifactURI.get(genPackage);
      if (artifactURI == null)
      {
        for (Iterator i = exportData.referencedGenPackagesToArtifactURI.entrySet().iterator(); i.hasNext();)
        {
          Map.Entry entry = (Map.Entry)i.next();
          GenPackage referencedGenPackage = (GenPackage)entry.getKey();
          if (genPackage.getNSURI().equals(referencedGenPackage.getNSURI()) 
              && genPackage.getEcorePackage().getName().equals(referencedGenPackage.getEcorePackage().getName()))
          {
            artifactURI = (URI)entry.getValue();
          }
        }
      }
    }
    return artifactURI;
  }
  
  protected String computeSchemaLocation(XSDSchemaDirective xsdSchemaDirective, URI artifactURI)
  {
    if (artifactURI != null)
    {
      return artifactURI.toString();
    }
    else if (EcorePackage.eNS_URI.equals(xsdSchemaDirective.getSchemaLocation()))
    {
      return "platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xsd";
    }    
    else
    {
      return xsdSchemaDirective.getSchemaLocation();
    }
  }
}
