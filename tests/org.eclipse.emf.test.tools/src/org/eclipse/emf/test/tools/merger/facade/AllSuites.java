/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: AllSuites.java,v 1.1 2006/12/06 03:54:34 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger.facade;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSuites extends TestSuite
{
  public static Test suite()
  {
    return new AllSuites("AST Facade Test Suite");
  }

  public AllSuites()
  {
    super();
    populateSuite();
  }

  public AllSuites(String name)
  {
    super(name);
    populateSuite();
  }

  protected void populateSuite()
  {
    addTest(new TestSuite(TypesTest.class, "Types Test"));
    addTest(new TestSuite(EnumsTest.class, "Enums Test"));
    addTest(new TestSuite(FieldsTest.class, "Fields Test"));
    addTest(new TestSuite(SplitFieldsTest.class, "Split Fields Test"));
    addTest(FacadeAPITest.suite());
    addTest(FacadeTest_Example1.suite());
  }
}
