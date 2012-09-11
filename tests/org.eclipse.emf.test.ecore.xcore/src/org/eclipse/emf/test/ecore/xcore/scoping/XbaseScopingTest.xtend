/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.scoping

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class XbaseScopingTest {
	
	@Inject
	ParseHelper<XPackage> parser
	
	@Inject
	ValidationTestHelper validator
	
	@Test
	def testLinkToThis() {
		val pack = parser.parse('''
			package foo.bar
			
			class X {
				op X foo(X x) {
					return this.foo(x)
				}
			}
		''')
		validator.assertNoErrors(pack)
	}
	
}