/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: StandAloneSuites.java,v 1.2.2.2 2005/06/08 18:27:45 nickb Exp $
 */
package org.eclipse.emf.test.build;
import junit.framework.Test;
import junit.framework.TestSuite;

public class StandAloneSuites extends TestSuite
{
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.core.AllSuites.suite() 
    ,org.eclipse.emf.test.edit.AllSuites.suite()
    ,org.eclipse.emf.test.sdo.AllSuites.suite()
    ,org.eclipse.emf.test.xml.AllSuites.suite()
  };

  public static Test suite()
  {
    return new StandAloneSuites("EMF StandAlone JUnit Test Suite");
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