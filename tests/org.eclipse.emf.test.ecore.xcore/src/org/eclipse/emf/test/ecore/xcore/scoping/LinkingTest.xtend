/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.scoping

import static org.junit.Assert.*

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import org.junit.Test
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.xtext.xbase.XBlockExpression
import org.eclipse.xtext.xbase.XVariableDeclaration
import org.eclipse.xtext.xbase.XBinaryOperation

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class LinkingTest {
	
	@Inject
	ParseHelper<XPackage> parser
	
	@Test
	def void linkVoidReturnType() {
		val pack = parser.parse('''
			package foo
			class Bar {
				op void operation() {
					val int i = 0
				}
			}
		''');
		val declaration = pack.firstVariableDeclaration
		val intType = declaration.type.type
		assertFalse(intType.eIsProxy)
		assertEquals('int', intType.qualifiedName)
	}
	
	@Test
	def void linkQualifiedStringType() {
		val pack = parser.parse('''
			package foo
			class Bar {
				op void operation() {
					val java.lang.String s = null
				}
			}
		''');
		val declaration = pack.firstVariableDeclaration
		val stringType = declaration.type.type
		assertFalse(stringType.eIsProxy)
		assertEquals('java.lang.String', stringType.qualifiedName)
	}
	
	@Test
	def void linkInternalDefinedType() {
		val pack = parser.parse('''
			package foo
			class Bar {
				op void operation() {
					val Bar b = null
				}
			}
		''');
		val declaration = pack.firstVariableDeclaration
		val stringType = declaration.type.type
		assertFalse(stringType.eIsProxy)
		assertEquals('foo.Bar', stringType.qualifiedName)
	}
	
	@Test
	def void linkFeatureCallType() {
		val pack = parser.parse('''
			package foo
			class Bar {
				op void operation() {
					val s = 'a' + 'b'
				}
			}
		''');
		val declaration = pack.firstVariableDeclaration
		val binaryOperation = declaration.right as XBinaryOperation
		val feature = binaryOperation.feature
		assertFalse(feature.eIsProxy)
		assertEquals('operator_plus', feature.simpleName)
	}
	
	def firstVariableDeclaration(XPackage pack) {
		val clazz = pack.classifiers.head as XClass
		val operation = clazz.members.head as XOperation
		val block = operation.body as XBlockExpression
		val declaration = block.expressions.head as XVariableDeclaration
		declaration
	}
}