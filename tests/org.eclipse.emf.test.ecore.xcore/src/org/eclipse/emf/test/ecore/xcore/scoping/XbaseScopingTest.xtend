/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore.scoping

import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.InjectWith
import com.google.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.eclipse.emf.test.ecore.xcore.XcoreStandaloneInjectorProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreStandaloneInjectorProvider))
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
	
	@Test
	def testImports() {
		val pack = parser.parse('''
			package foo.bar
			
			import java.math.BigDecimal
			
			class X {
				op BigDecimal foo(BigDecimal y) {
					val BigDecimal x = 42bd
					return x + y
				}
			}
		''')
		validator.assertNoErrors(pack)
	}
	
}