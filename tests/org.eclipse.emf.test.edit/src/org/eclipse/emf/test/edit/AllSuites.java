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
package org.eclipse.emf.test.edit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
     org.eclipse.emf.test.edit.command.AddCommandTest.class,
     org.eclipse.emf.test.edit.command.DeleteCommandTest.class,
     org.eclipse.emf.test.edit.command.RemoveCommandTest.class,
     org.eclipse.emf.test.edit.command.SetCommandTest.class,
     org.eclipse.emf.test.edit.command.SetCommandListTest.class,
     org.eclipse.emf.test.edit.command.UnsetCommandTest.class,
     org.eclipse.emf.test.edit.command.ChangeCommandTest.class,
     org.eclipse.emf.test.edit.provider.ComposedAdapterFactoryTest.class,
     org.eclipse.emf.test.edit.provider.TypeSearchTest.class,
     org.eclipse.emf.test.edit.TestValidateAction.class
  })
public class AllSuites
{
  // Empty.
}