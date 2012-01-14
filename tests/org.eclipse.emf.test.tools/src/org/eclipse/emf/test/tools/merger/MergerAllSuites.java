/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.merger;



import junit.framework.Test;
import junit.framework.TestSuite;


public class MergerAllSuites extends TestSuite
{
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.tools.merger.ASTTest.suite()
    ,org.eclipse.emf.test.tools.merger.DeprecatedJMergerTest.suite()
    ,org.eclipse.emf.test.tools.merger.JMergerJDOMTest.suite()
    ,org.eclipse.emf.test.tools.merger.JMergerASTTest.suite()
    ,org.eclipse.emf.test.tools.merger.JMergerTestSuite.suite()
  };

  public static Test suite()
  {
    return new MergerAllSuites("EMF Merge Tests");
  }

  public MergerAllSuites()
  {
    super();
    populateSuite();
  }

  public MergerAllSuites(Class<?> theClass)
  {
    super(theClass);
    populateSuite();
  }

  public MergerAllSuites(String name)
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
