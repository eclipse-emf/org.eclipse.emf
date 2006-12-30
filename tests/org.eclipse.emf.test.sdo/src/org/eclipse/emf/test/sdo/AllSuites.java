/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: AllSuites.java,v 1.9 2006/12/30 03:44:08 marcelop Exp $
 */
package org.eclipse.emf.test.sdo;


import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSuites extends TestSuite
{
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.sdo.SpecialCasesTest.suite() //must be the first to be invoked
    ,org.eclipse.emf.test.sdo.DataGraphTest.suite()
    ,org.eclipse.emf.test.sdo.ChangeSummaryTest.suite()
    ,org.eclipse.emf.test.sdo.DynamicSequenceTest.suite()
    ,org.eclipse.emf.test.sdo.types.SDOUtilGetTest.suite()
    ,org.eclipse.emf.test.sdo.types.SDOUtilSetTest.suite()
    ,org.eclipse.emf.test.sdo.types.SDOUtilProtectedGetTest.suite()
    ,org.eclipse.emf.test.sdo.TestSDO.suite()
    ,org.eclipse.emf.test.sdo.DateTest.suite()
  };

  public static Test suite()
  {
    return new AllSuites("EMF SDO JUnit Test Suite");
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