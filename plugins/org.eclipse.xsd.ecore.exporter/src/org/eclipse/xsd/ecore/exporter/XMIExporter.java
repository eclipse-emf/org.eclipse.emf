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
 * $Id: XMIExporter.java,v 1.3 2005/12/21 01:08:45 marcelop Exp $
 */
package org.eclipse.xsd.ecore.exporter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.ecore.EcoreSchemaBuilder;
import org.eclipse.xsd.ecore.EcoreXMISchemaBuilder;

/**
 * @since 2.2.0
 */
public class XMIExporter extends XSDExporter
{
  protected EcoreSchemaBuilder ecoreSchemaBuilder;
  
  public String getID()
  {
    return "org.eclipse.xsd.ecore.exporter.xmi";
  }
  
  protected String getDefaultArtifactLocation(EPackage ePackage)
  {
    return getDefaultArtifactFileName(ePackage) + "XMI.xsd";
  }
  
  protected XSDSchema getSchema(EPackage ePackage)
  {
    if (mapBuilder == null)
    {
      mapBuilder = new EcoreXMISchemaBuilder();
    }
    return (XSDSchema)((EcoreXMISchemaBuilder)mapBuilder).generate(ePackage).iterator().next();
  }
  
  protected String computeSchemaLocation(XSDSchemaDirective xsdSchemaDirective, URI artifactURI)
  {
    if (artifactURI != null)
    {
      return artifactURI.toString();
    }
    else if ("XMI.xsd".equals(xsdSchemaDirective.getSchemaLocation()) && 
               xsdSchemaDirective instanceof XSDImport && 
               ExtendedMetaData.XMI_URI.equals(((XSDImport)xsdSchemaDirective).getNamespace()) &&
               !EcorePackage.eNS_URI.equals(xsdSchemaDirective.getSchema().getTargetNamespace()))
    {
      return "platform:/plugin/org.eclipse.emf.ecore/model/XMI.xsd";
    }
    else if (EcorePackage.eNS_URI.equals(xsdSchemaDirective.getSchemaLocation()))
    {
      return "platform:/plugin/org.eclipse.emf.ecore/model/EcoreXMI.xsd";
    }
    else
    {
      return xsdSchemaDirective.getSchemaLocation();
    }
  }  
}
