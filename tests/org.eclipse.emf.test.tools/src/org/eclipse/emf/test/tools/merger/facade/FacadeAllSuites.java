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
package org.eclipse.emf.test.tools.merger.facade;

import junit.framework.Test;
import junit.framework.TestSuite;


public class FacadeAllSuites extends TestSuite
{
  public static Test suite()
  {
    return new FacadeAllSuites("AST Facade Test Suite");
  }

  public FacadeAllSuites()
  {
    super();
    populateSuite();
  }

  public FacadeAllSuites(String name)
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
    addTest(new TestSuite(MethodsTest.class, "Methods Test"));
    addTest(new TestSuite(CommentOutTest.class, "Comment Out Feature Test"));    
    addTest(FacadeAPITest.suite());
    addTest(FacadeTest_Example1.suite());
  }
}
