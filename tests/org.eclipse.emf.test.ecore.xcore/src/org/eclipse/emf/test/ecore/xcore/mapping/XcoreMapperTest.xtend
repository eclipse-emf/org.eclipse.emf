/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.mapping

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper
import static org.junit.Assert.*
import org.junit.Test
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XStructuralFeature
import org.eclipse.emf.ecore.xcore.XOperation

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class XcoreMapperTest {

	@Inject
	ParseHelper<XPackage> parser

	@Inject
	extension XcoreMapper mapper

	@Test
	def testMapping() {
		val pack = parser.parse('''
			package foo.bar
			
			type String wraps java.lang.String
			
			class X {
				attr String name
				refers Y reference
			}
			
			class Y<T> extends X {
				op String toString(X x) {
					return null
				}
			}
		''')
		assertNotNull(pack.mapping.EPackage)
		assertEquals(pack.mapping.getEPackage,pack.mapping.genPackage.ecorePackage)
		assertEquals(pack,pack.mapping.genPackage.toXcoreMapping.xcoreElement)
		assertEquals(pack,pack.mapping.EPackage.toXcoreMapping.xcoreElement)

		for (clazz : pack.classifiers.filter(typeof(XClass))) {
			assertNotNull(clazz.mapping.EClass)
			assertEquals(clazz.mapping.EClass,clazz.mapping.genClass.ecoreClass)
			assertEquals(clazz,clazz.mapping.genClass.toXcoreMapping.xcoreElement)
			assertEquals(clazz,clazz.mapping.EClass.toXcoreMapping.xcoreElement)

			for (member : clazz.members) {
				switch member {
					XStructuralFeature : {
						assertNotNull(member.mapping.EStructuralFeature)
						assertEquals(member.mapping.EStructuralFeature, member.mapping.genFeature.ecoreFeature)
						assertEquals(member,member.mapping.EStructuralFeature.toXcoreMapping.xcoreElement)
						assertEquals(member,member.mapping.genFeature.toXcoreMapping.xcoreElement)
						//TODO jvm stuff
					}
					XOperation : {
						assertNotNull(member.mapping.EOperation)
						assertEquals(member.mapping.EOperation, member.mapping.genOperation.ecoreOperation)
						assertEquals(member,member.mapping.EOperation.toXcoreMapping.xcoreElement)
						assertEquals(member,member.mapping.genOperation.toXcoreMapping.xcoreElement)
						//TODO jvm stuff
						for (parameter : member.parameters) {
							assertNotNull(parameter.mapping.EParameter)
							assertEquals(parameter.mapping.EParameter, parameter.mapping.genParameter.ecoreParameter)
							assertEquals(parameter,parameter.mapping.EParameter.toXcoreMapping.xcoreElement)
							assertEquals(parameter,parameter.mapping.genParameter.toXcoreMapping.xcoreElement)
							//TODO jvm stuff
						}
					}
				}
			}
			for (typeParameter : clazz.typeParameters) {
				assertNotNull(typeParameter.mapping.ETypeParameter)
				assertEquals(typeParameter.mapping.ETypeParameter, typeParameter.mapping.genTypeParameter.ecoreTypeParameter)
				assertEquals(typeParameter,typeParameter.mapping.ETypeParameter.toXcoreMapping.xcoreElement)
				assertEquals(typeParameter,typeParameter.mapping.genTypeParameter.toXcoreMapping.xcoreElement)
				//TODO jvm stuff
			}
		}
	}
}