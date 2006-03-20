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
 * $Id: HTMLExporter.java,v 1.2 2006/03/20 16:07:29 marcelop Exp $
 */
package org.eclipse.emf.exporter.html;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.emf.exporter.ModelExporter;

/**
 * <p>
 * This example shows how the Export API and JET can be used to generate a
 * HTML documentation for Ecore packages.
 * </p>
 * <p>As any example, this code was <b>NOT</b> extensively tested and is not
 * necessary to cover all the possible nuances of Ecore models.  It doesn't 
 * supported nested packages, for example.
 * </p>   
 * 
 * @since 2.2.0
 */
public class HTMLExporter extends ModelExporter
{
  private ModelExporter.ExportData exportData;
  private Map ePackageToGenPackage;
  
  private GenPackage currentGenPackage;
  private URI currentArtifactURI;
  
  public String getID()
  {
    return "org.eclipse.emf.exporter.html";
  }
  
  protected String getDefaultArtifactLocation(EPackage ePackage)
  {
    return getDefaultArtifactFileName(ePackage) + ".html";
  }
  
  protected String doCheckEPackageArtifactLocation(String location, String packageName)
  {
    if (!location.endsWith(".html"))
    {
      return HTMLExporterPlugin.INSTANCE.getString("_UI_InvalidArtifactFileNameExtension_message");
    }
    return super.doCheckEPackageArtifactLocation(location, packageName);
  }
  
  protected void doExport(Monitor monitor, ModelExporter.ExportData exportData) throws Exception
  {
    this.exportData = exportData;
    ePackageToGenPackage = new HashMap();
    for (Iterator i = exportData.genPackageToArtifactURI.keySet().iterator(); i.hasNext();)
    {
      GenPackage genPackage = (GenPackage)i.next();
      ePackageToGenPackage.put(genPackage.getEcorePackage(), genPackage);
    }
    for (Iterator i = exportData.referencedGenPackagesToArtifactURI.keySet().iterator(); i.hasNext();)
    {
      GenPackage genPackage = (GenPackage)i.next();
      ePackageToGenPackage.put(genPackage.getEcorePackage(), genPackage);
    }
    
    for (Iterator i = exportData.genPackageToArtifactURI.entrySet().iterator(); i.hasNext();)
    {
      Map.Entry entry = (Map.Entry)i.next();      
      currentGenPackage = (GenPackage)entry.getKey();
      currentArtifactURI = (URI)entry.getValue();
      
      String content = new PackageHTML().generate(this);
      save(content);
    }
  }
  
  public GenPackage getCurrentGenPackage()
  {
    return currentGenPackage;
  }
  
  public String computeClassifierLabel(EClassifier eClassifier)
  {
    return computeClassifierLabel(eClassifier, "");
  }
  
  public String computeClassifierLabel(EClassifier eClassifier, String defaultValue)
  {
    if (eClassifier == null)
    {
      return defaultValue;
    }
    else
    {
      String name = eClassifier.getName();
      EPackage eClassifierEPackage = eClassifier.getEPackage();
  
      if (getCurrentGenPackage().getEcorePackage() == eClassifierEPackage
          || getCurrentGenPackage().getEcorePackage().getNsURI().equals(eClassifierEPackage.getNsURI()))
      {
        return new StringBuffer()
          .append("<a href=\"#").append(name).append("\">")
          .append(name)
          .append("</a>")
          .toString(); 
      }
      
      GenPackage eClassifierGenPackage = (GenPackage)ePackageToGenPackage.get(eClassifierEPackage);
      if (eClassifierGenPackage != null)
      {
        URI artifactURI = (URI)exportData.genPackageToArtifactURI.get(eClassifierGenPackage);
        if (artifactURI == null)
        {
          artifactURI = (URI)exportData.referencedGenPackagesToArtifactURI.get(eClassifierGenPackage);
        }
        
        if (artifactURI != null)
        {
          artifactURI = artifactURI.deresolve(currentArtifactURI);
          return new StringBuffer()
          .append("<a href=\"").append(artifactURI.toString()).append("#").append(name).append("\">")
          .append(name)
          .append("</a>")
          .toString();
        }      
      }
      
      return name;
    }
  }
  
  protected void save(String content) throws IOException
  {
    URIConverter uriConverter = new URIConverterImpl();
    OutputStream outputStream = uriConverter.createOutputStream(currentArtifactURI);
    outputStream.write(content.getBytes("UTF-8"));
    outputStream.close();
  }
}