/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: AllSuites.java,v 1.19 2009/01/18 03:53:09 davidms Exp $
 */
package org.eclipse.emf.test.tools;



import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSuites extends TestSuite
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.tools";
  
  private static Test[] suites = new Test []{ 
     org.eclipse.emf.test.tools.ImportManagerTest.suite()
    ,org.eclipse.emf.test.tools.JETTest.suite()
    ,org.eclipse.emf.test.tools.ToolsTest.suite()
    ,org.eclipse.emf.test.tools.ant.AntTest.suite()
    ,org.eclipse.emf.test.tools.codegen.GenModelDefaultsTest.suite()
    ,org.eclipse.emf.test.tools.codegen.GenModelTest.suite()
    ,org.eclipse.emf.test.tools.converter.ConverterUtilTest.suite()
    ,org.eclipse.emf.test.tools.importer.ImporterUtilTest.suite()
    ,org.eclipse.emf.test.tools.importer.ModelImporterTest.suite()
    ,org.eclipse.emf.test.tools.merger.MergerAllSuites.suite()
    ,org.eclipse.emf.test.tools.merger.facade.FacadeAllSuites.suite()
  };

  public static Test suite()
  {
    return new AllSuites("EMF Tools JUnit Test Suite");
  }

  public AllSuites()
  {
    super();
    populateSuite();
  }

  public AllSuites(Class<?> theClass)
  {
    super(theClass);
    populateSuite();
  }

  public AllSuites(String name)
  {
    super(name);
    populateSuite();
  }

  protected void populateSuite()
  {
    for (int i = 0; i < suites.length; i++)
    {
      addTest(suites[i]);
    }
  }
}