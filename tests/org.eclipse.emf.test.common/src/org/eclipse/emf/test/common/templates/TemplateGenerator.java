/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.common.templates;

import java.io.IOException;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;

/**
 * @since 2.3
 */
public class TemplateGenerator
{
  public static String diagnosticTemplate(Diagnostic diagnostic)
  {
    return new DiagnosticTestGen().generate(new Object [] { "BadEcore", diagnostic });
  }
  
  public static Diagnostic diagnoseBadEcore() throws IOException
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI logicalURI = URI.createPlatformPluginURI("platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore", false);
    URI physicalURI = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.core") + "/data/Bad.ecore");
    resourceSet.getURIConverter().getURIMap().put(logicalURI, physicalURI);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource ecoreResource = resourceSet.getResource(logicalURI, true);
    EPackage badPackage = (EPackage)ecoreResource.getContents().get(0);
    
    return Diagnostician.INSTANCE.validate(badPackage);
  }
  
  public static void main(String[] args)
  {
    Diagnostic diagnostic;
    try
    {
      diagnostic = diagnoseBadEcore();
      System.out.println(diagnosticTemplate(diagnostic).replaceAll("\t", "  "));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  } 
}
