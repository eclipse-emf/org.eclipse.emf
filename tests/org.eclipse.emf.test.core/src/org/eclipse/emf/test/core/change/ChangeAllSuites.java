/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.change;


import junit.framework.Test;
import junit.framework.TestSuite;


public class ChangeAllSuites extends TestSuite
{
  private static Test[] suites = new Test []
  { 
    ListDifferenceAnalyzerTest.suite(),
    ChangeDescriptionReverseTest.suite(),
    ChangeRecordTest.suite(false)
    ,ChangeRecordTest.suite(true)
    ,ChangeDescriptionTest.suite()
    ,MultivalueAttributeTest.suite()
    ,SpecialCasesTest.suite()
    ,ChangeDescriptionBuilderTest.suite()
  };

  public static Test suite()
  {
    return new ChangeAllSuites("EMF ChangeModel JUnit Test Suite");
  }

  public ChangeAllSuites()
  {
    super();
    populateSuite();
  }

  public ChangeAllSuites(Class<?> theClass)
  {
    super(theClass);
    populateSuite();
  }

  public ChangeAllSuites(String name)
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