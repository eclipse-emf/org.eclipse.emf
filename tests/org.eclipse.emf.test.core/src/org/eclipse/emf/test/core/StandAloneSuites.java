/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: StandAloneSuites.java,v 1.3 2004/10/14 17:41:10 marcelop Exp $
 */
package org.eclipse.emf.test.core;


import junit.framework.Test;
import junit.framework.TestSuite;


public class StandAloneSuites extends TestSuite
{
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.core.common.util.URITest.suite() 
    ,org.eclipse.emf.test.core.dynamic.SimpleModelTest.suite()
    ,org.eclipse.emf.test.core.sdo.DataGraphTest.suite()
    ,org.eclipse.emf.test.core.sdo.types.SDOUtilGetTest.suite()
    ,org.eclipse.emf.test.core.sdo.types.SDOUtilSetTest.suite()
    ,org.eclipse.emf.test.core.sdo.types.SDOUtilProtectedGetTest.suite()
    ,org.eclipse.emf.test.core.change.ChangeReportTest.suite(false)
    ,org.eclipse.emf.test.core.change.ChangeReportTest.suite(true)
    ,org.eclipse.emf.test.core.change.MultivalueAttributeTest.suite()
    ,org.eclipse.emf.test.core.change.SpecialCasesTest.suite()
    ,org.eclipse.emf.test.core.ecore.EcoreUtilStaticMethodsTest.suite()
    ,org.eclipse.emf.test.core.ecore.NotUniqueListTest.suite()
    ,org.eclipse.emf.test.core.xmi.NamespaceTest.suite()
    ,org.eclipse.emf.test.core.xmi.OrderTest.suite()
    ,org.eclipse.emf.test.core.xmi.QNameTest.suite()
    ,org.eclipse.emf.test.core.xsdecore.XSD2EcoreTest.suite()
    ,org.eclipse.emf.test.core.xsdecore.XSDValidateTest.suite()
    ,com.example.simple.TestSDO.suite()
    ,org.eclipse.emf.test.core.featuremap.FeatureMapTest.suite()
    ,org.eclipse.emf.test.core.edit.command.SetCommandTest.suite()
  };

  public static Test suite()
  {
    return new StandAloneSuites("EMF Core StandAlone JUnit Test Suite");
  }

  public StandAloneSuites()
  {
    super();
    populateSuite();
  }

  public StandAloneSuites(Class theClass)
  {
    super(theClass);
    populateSuite();
  }

  public StandAloneSuites(String name)
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