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
 * $Id: AllSuites.java,v 1.20 2004/08/25 21:50:55 marcelop Exp $
 */
package org.eclipse.emf.test.core;


import junit.framework.Test;


public class AllSuites extends StandAloneSuites
{
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.core.jet.JETTest.suite()
    , org.eclipse.emf.test.core.build.BuildTests.suite()
  };

  public static Test suite()
  {
    return new AllSuites("EMF Core JUnit Test Suite");
  }

  public AllSuites()
  {
    super();
  }

  public AllSuites(Class theClass)
  {
    super(theClass);
  }

  public AllSuites(String name)
  {
    super(name);
  }

  protected void populateSuite()
  {
    super.populateSuite();
    for (int i = 0; i < suites.length; i++)
    {
      addTest(suites[i]);
    }
  }
}