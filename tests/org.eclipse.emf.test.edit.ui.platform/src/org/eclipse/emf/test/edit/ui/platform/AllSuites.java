/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.edit.ui.platform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
     org.eclipse.emf.test.edit.ui.platform.NebulaDatePropertyEditorFactoryValidationTest.class,
     org.eclipse.emf.test.edit.ui.platform.TestValidateAction.class,
  })
public class AllSuites
{
  // Empty.
}