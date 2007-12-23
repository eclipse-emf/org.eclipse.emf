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
 * $Id: AllSuites.java,v 1.41 2007/12/23 18:29:24 emerks Exp $
 */
package org.eclipse.emf.test.core;


import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSuites extends TestSuite
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.core";
  
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.core.common.util.URITest.suite() 
    ,org.eclipse.emf.test.core.ecore.EcoreValidationTest.suite()
    ,org.eclipse.emf.test.core.ecore.EcoreTest.suite()
    ,org.eclipse.emf.test.core.common.NotificationTest.suite()
    ,org.eclipse.emf.test.core.common.util.EqualityTest.suite()
    ,org.eclipse.emf.test.core.common.util.ECollectionsTest.suite()
    ,org.eclipse.emf.test.core.dynamic.SimpleModelTest.suite()
    ,org.eclipse.emf.test.core.change.ChangeAllSuites.suite()
    ,org.eclipse.emf.test.core.ecore.EcoreUtilStaticMethodsTest.suite()
    ,org.eclipse.emf.test.core.ecore.ListTest.suite()
    ,org.eclipse.emf.test.core.ecore.ResourceCacheMechanismTest.suite()
    ,org.eclipse.emf.test.core.ecore.PersistenceTest.suite()
    ,org.eclipse.emf.test.core.ecore.ResourceAttachmentTest.suite()
    ,org.eclipse.emf.test.core.ecore.FeatureMapTest.suite()
    ,org.eclipse.emf.test.core.ecore.ValidationTest.suite()
    ,org.eclipse.emf.test.core.ecore.KeyTest.suite()
    ,org.eclipse.emf.test.core.ecore.URIConverterTest.suite()
    ,org.eclipse.emf.test.core.ecore.GenericTypeBuilderTest.suite()
    ,org.eclipse.emf.test.core.featuremap.FeatureMapTest.suite()
    ,org.eclipse.emf.test.core.ecore.BadEcoreDiagnosticTest.suite()
  };

  public static Test suite()
  {
    return new AllSuites("EMF Core JUnit Test Suite");
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