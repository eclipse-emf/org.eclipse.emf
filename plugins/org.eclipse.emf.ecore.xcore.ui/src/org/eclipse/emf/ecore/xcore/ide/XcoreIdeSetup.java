/**
 * Copyright (c) 2020 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.ecore.xcore.XcoreRuntimeModule;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class XcoreIdeSetup extends XcoreStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new XcoreRuntimeModule(), new XcoreIdeModule()));
	}
	
}
