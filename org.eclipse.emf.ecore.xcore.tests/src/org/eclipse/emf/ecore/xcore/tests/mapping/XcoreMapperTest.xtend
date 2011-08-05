package org.eclipse.emf.ecore.xcore.tests.mapping

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
			
			class Y extends X {
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
			assertNotNull(clazz.mapping.eclass)
			assertEquals(clazz.mapping.eclass,clazz.mapping.genClass.ecoreClass)
			assertEquals(clazz,clazz.mapping.genClass.toXcoreMapping.xcoreElement)
			assertEquals(clazz,clazz.mapping.eclass.toXcoreMapping.xcoreElement)
			
			for (member : clazz.members) {
				switch member {
					XStructuralFeature :{
						assertNotNull(member.mapping.EStructuralFeature)
						assertEquals(member.mapping.EStructuralFeature, member.mapping.genFeature.ecoreFeature)
						assertEquals(member,member.mapping.EStructuralFeature.toXcoreMapping.xcoreElement)
						assertEquals(member,member.mapping.genFeature.toXcoreMapping.xcoreElement)
						//TODO jvm stuff
					}
					XOperation :{
						assertNotNull(member.mapping.EOperation)
						assertEquals(member.mapping.EOperation, member.mapping.genOperation.ecoreOperation)
						assertEquals(member,member.mapping.EOperation.toXcoreMapping.xcoreElement)
						assertEquals(member,member.mapping.genOperation.toXcoreMapping.xcoreElement)
						//TODO jvm stuff						
					}
				} 
			}
		}
	}
}