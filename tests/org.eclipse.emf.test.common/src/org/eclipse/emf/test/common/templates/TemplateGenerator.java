/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: TemplateGenerator.java,v 1.1 2007/01/26 13:59:40 marcelop Exp $
 */
package org.eclipse.emf.test.common.templates;

import java.io.IOException;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
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
    return new DiagnosticTestGen().generate(diagnostic);
  }
  
  public static Diagnostic diagnoseBadEcore() throws IOException
  {
    URI uri = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.core") + "/data/Bad.ecore");
    Resource ecoreResource = new EcoreResourceFactoryImpl().createResource(uri);
    ecoreResource.load(null);
    EPackage badPackage = (EPackage)ecoreResource.getContents().get(0);
    
    return Diagnostician.INSTANCE.validate(badPackage);
  }
  
  public static void main(String[] args)
  {
    Diagnostic diagnostic;
    try
    {
      diagnostic = diagnoseBadEcore();
      System.out.println(diagnosticTemplate(diagnostic));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  } 
}
