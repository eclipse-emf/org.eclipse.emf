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
 * $Id: AllSuites.java,v 1.11 2008/08/07 16:15:55 marcelop Exp $
 */
package org.eclipse.emf.test.edit;


import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSuites extends TestSuite
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.edit";
  
  private static Test[] suites = new Test []{
    org.eclipse.emf.test.edit.command.AddCommandTest.suite()
    ,org.eclipse.emf.test.edit.command.DeleteCommandTest.suite()
    ,org.eclipse.emf.test.edit.command.RemoveCommandTest.suite()
    ,org.eclipse.emf.test.edit.command.SetCommandTest.suite()
    ,org.eclipse.emf.test.edit.command.UnsetCommandTest.suite()
    ,org.eclipse.emf.test.edit.command.ChangeCommandTest.suite()
    ,org.eclipse.emf.test.edit.provider.ComposedAdapterFactoryTest.suite()
    ,org.eclipse.emf.test.edit.provider.TypeSearchTest.suite()
    ,org.eclipse.emf.test.edit.TestValidateAction.suite()
  };

  public static Test suite()
  {
    return new AllSuites("EMF Edit JUnit Test Suite");
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