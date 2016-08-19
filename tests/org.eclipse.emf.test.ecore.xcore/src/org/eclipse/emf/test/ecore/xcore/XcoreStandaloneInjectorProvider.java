/**
 * Copyright (c) 2016 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore;

import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.XcoreRuntimeModule;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;

public class XcoreStandaloneInjectorProvider extends XcoreInjectorProvider
{
  @Override
  protected XcoreRuntimeModule createRuntimeModule()
  {
    return new XcoreStandaloneSetup.XcoreStandaloneRuntimeModule();
  }
}
