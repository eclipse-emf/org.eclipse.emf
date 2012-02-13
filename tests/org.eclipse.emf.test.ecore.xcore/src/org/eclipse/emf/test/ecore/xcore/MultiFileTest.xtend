/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore

import com.google.inject.Inject
import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.util.StringInputStream
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

import org.eclipse.emf.ecore.xcore.XReference
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class MultiFileTest {
	
	@Inject
	XtextResourceSet resourceSet
	
	@Inject
	ValidationTestHelper validator
	
	@Inject
	extension XcoreMapper mapper
	
	@Test
	def void testReferenceBetweenTwoModels() {
		val resourceA = resourceSet.createResource(URI::createURI('file:/modelA.xcore'))
		val resourceB = resourceSet.createResource(URI::createURI('file:/modelB.xcore'))
		resourceB.load(new StringInputStream('''
			package packB
			
			class TypeB {
			}
		'''.toString), null)
		resourceA.load(new StringInputStream('''
			package packA
			
			class TypeA {
				refers packB.TypeB refToB 
			}
		'''.toString), null)
		
		validator.assertNoErrors(resourceA.contents.head)
		validator.assertNoErrors(resourceB.contents.head)
		val allContents = resourceA.allContents 
		val xclass = allContents.filter(typeof(XClass)).head
		val referencedGenClass = xclass.members.head.type.type
		assertEquals('TypeB', (referencedGenClass as GenClass).name) 
	}
	
	@Test
	def void testBidirectionalReferenceBetweenTwoModels() {
		val resourceA = resourceSet.createResource(URI::createURI('file:/modelA.xcore'))
		val resourceB = resourceSet.createResource(URI::createURI('file:/modelB.xcore'))
		resourceB.load(new StringInputStream('''
			package packB
			
			class TypeB {
				refers packA.TypeA refToA opposite refToB
			}
		'''.toString), null)
		resourceA.load(new StringInputStream('''
			package packA
			
			class TypeA {
				refers packB.TypeB refToB opposite refToA
			}
		'''.toString), null)
		
		validator.assertNoErrors(resourceA.contents.head)
		validator.assertNoErrors(resourceB.contents.head)
		val allContents = resourceA.allContents
		val xclass = allContents.filter(typeof(XClass)).head
		val referencedGenClass = xclass.members.head.type.type
		assertEquals('TypeB', (referencedGenClass as GenClass).name) 
		val ref = xclass.members.head as XReference
		assertEquals(ref , (ref.opposite.XFeature as XReference).opposite.XFeature)
	}
}