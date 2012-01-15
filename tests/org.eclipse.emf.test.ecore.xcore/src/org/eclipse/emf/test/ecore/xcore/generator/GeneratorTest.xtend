/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import org.eclipse.emf.ecore.xcore.generator.XcoreGenerator
import org.eclipse.xtext.generator.InMemoryFileSystemAccess
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import org.eclipse.xtext.generator.IFileSystemAccess

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class GeneratorTest {
	
	@Inject
	ParseHelper<XPackage> parser
	
	@Inject
	XcoreGenerator xcoreGenerator
	
	@Test
	def void testGenerator() {
		val xPackage = parser.parse('''
			package test
			class X {}
		''')
		val inmemFsa = new InMemoryFileSystemAccess()
		xcoreGenerator.doGenerate(xPackage.eResource, inmemFsa)
		assertEquals(inmemFsa.files.keySet.toString, 8, inmemFsa.files.size)
		
		assertNotNull(inmemFsa.files.get(IFileSystemAccess::DEFAULT_OUTPUT+'test/util/TestSwitch.java'))
	}
}