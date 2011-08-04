package org.eclipse.emf.ecore.xcore.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.InjectWith
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.emf.ecore.xcore.XPackage
import org.junit.Test
import static org.junit.Assert.*
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XcoreExtensions
import org.eclipse.emf.ecore.xcore.XReference
import org.eclipse.xtext.resource.XtextResource

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class ParsingTest {

	@Inject
	ParseHelper<XPackage> parser
	
	@Inject 
	extension XcoreExtensions exts
	

	@Test
	def void parseSimpleFile() {
		val parse = parser.parse("package foo");
		assertEquals("foo", parse.getName());
	}
	
	@Test
	def void testSuperTypeLinking_1() {
		val pack = parser.parse('''
			package foo 
			class A {} 
			class B extends A {}
		''')
		val clazz = pack.classifiers.get(1) as XClass
		assertEquals("A", clazz.superTypes.head.genClass.name)
	}
	
	@Test
	def void testSuperTypeLinking_2() {
		val pack = parser.parse('''
			package foo 
			class A {} 
			class B extends foo.A {}
		''')
		val clazz = pack.classifiers.get(1) as XClass
		assertEquals("A", clazz.superTypes.head.genClass.name)
	}
	
	@Test
	def void testReferenceToAnnotation() {
		val pack = parser.parse('''
			package foo 
			annotation 'foo/bar' as foo
			@foo(holla='bar')
			class A {} 
		''')
		val clazz = pack.classifiers.get(0) as XClass
		assertEquals(pack.annotationDirectives.head, clazz.annotations.head.source)
	}

	@Test
	def void testReferenceToOpposite() {
		val pack = parser.parse('''
			package  foo
			class X
			{
				refers X x opposite y   
				refers X y opposite x
			}
		''')
		{
			val clazz = pack.classifiers.get(0) as XClass
			val refs = clazz.members.filter(typeof(XReference)).iterator
			var refX = refs.next
			var refY = refs.next
			assertEquals(refY.name, refX.opposite.name)
			assertEquals(refX.name, refY.opposite.name)
		}
		val resource = pack.eResource as XtextResource
		resource.update(0, 0, " ")
		{
			val clazz = (resource.contents.get(0) as XPackage).classifiers.get(0) as XClass
			val refs = clazz.members.filter(typeof(XReference)).iterator
			var refX = refs.next
			var refY = refs.next
			assertEquals(refY.name, refX.opposite.name)
			assertEquals(refX.name, refY.opposite.name)
		}
	}

}