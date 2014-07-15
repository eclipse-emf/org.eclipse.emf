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


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
    TypesTest.class,
    EnumsTest.class,
    FieldsTest.class,
    SplitFieldsTest.class,
    MethodsTest.class,
    CommentOutTest.class,
    FacadeAPITest.class,
    FacadeTest_Example1.class
  })
public class FacadeAllSuites
{
  // Empty.
}
