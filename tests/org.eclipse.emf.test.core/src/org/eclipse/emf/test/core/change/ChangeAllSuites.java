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


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
     ListDifferenceAnalyzerTest.class,
     ChangeDescriptionReverseTest.class,
     ChangeRecordTest.class,
     ChangeDescriptionTest.class,
     MultivalueAttributeTest.class,
     SpecialCasesTest.class,
     ChangeDescriptionBuilderTest.class
   })
public class ChangeAllSuites
{
  // Empty.
}