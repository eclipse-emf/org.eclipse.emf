package org.eclipse.emf.test.ecore.xcore

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
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xcore.XAttribute
import org.eclipse.emf.ecore.xcore.XClassifier
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
import org.eclipse.xtext.xtend2.lib.StringConcatenation

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class ParsingTest extends Object {

	@Inject
	ParseHelper<XPackage> parser
	
	@Inject 
	extension XcoreExtensions exts
	
	@Inject
	extension ValidationTestHelper vth
	
	@Test
	def void parseSimpleFile() {
		val parse = parser.parse("package foo");
		assertEquals("foo", parse.getName());
	}
	
	@Test
	def void testJvmTypes() {
		val pack = parser.parse('''
			package foo 
			class A 
			{ 
				refers A a
			} 
		''')
		EcoreUtil::resolveAll(pack.eResource)
		vth.assertNoErrors(pack);
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
		val text = '''
			package  foo
			class X
			{
				refers X x opposite y   
				refers X y opposite x
				op void foo() {
					val X x = null
				}
			}
		'''.toString
		val pack = parser.parse(text)
		{
			val clazz = pack.classifiers.get(0) as XClass
			val refs = clazz.members.filter(typeof(XReference)).iterator
			var refX = refs.next
			var refY = refs.next
			assertEquals(refY.name, refX.opposite.name)
			assertEquals(refX.name, refY.opposite.name)
		}
		val resource = pack.eResource as XtextResource
		val elements = resource.contents.size
		resource.update(0, text.length, text)
		{
			val clazz = (resource.contents.get(0) as XPackage).classifiers.get(0) as XClass
			val refs = clazz.members.filter(typeof(XReference)).iterator
			var refX = refs.next
			var refY = refs.next
			assertEquals(refY.name, refX.opposite.name)
			assertEquals(refX.name, refY.opposite.name)
			assertEquals(elements, resource.contents.size)
		}
		
		resource.reparse(text)
		{
			val clazz = (resource.contents.get(0) as XPackage).classifiers.get(0) as XClass
			val refs = clazz.members.filter(typeof(XReference)).iterator
			var refX = refs.next
			var refY = refs.next
			assertEquals(refY.name, refX.opposite.name)
			assertEquals(refX.name, refY.opposite.name)
			assertEquals(elements, resource.contents.size)
		}
	}
	
	@Test
	def void operationReturnsVoid() {
		val pack = parser.parse('''
			package foo
			class Bar {
				op void operation() {}
			}
		''');
		val clazz = pack.classifiers.head as XClass
		val operation = clazz.members.head as XOperation
		assertTrue(clazz.eResource.errors.isEmpty)
		assertNull(operation.type)
	}
	
	@Test
	def void referenceMayNotBeVoid() {
		val pack = parser.parse('''
			package foo
			class Bar {
				refers void referenceName
			}
		''');
		assertTrue(pack.eResource.errors.toString, 1 <= pack.eResource.errors.size)
	}

	@Test
	def void stringResolvesToEString() {
		val pack = parser.parse('''
			package foo
			class Bar {
				String value
			}
		''');
		val clazz = pack.classifiers.head as XClass
		val attribute = clazz.members.head as XAttribute
		assertTrue(attribute.type.type instanceof GenClassifier)
		assertEquals("EString", (attribute.type.type as GenClassifier).getName())
	}

	@Test
	def void testEcoreDataTypeAliases() {
		val pack = parser.parse('''
			package foo 
			class A 
			{ 
				java.math.BigDecimal _bigDecimal
				java.math.BigInteger _bigInteger
				boolean _boolean
				Boolean _Boolean
				byte _byte
				Byte _Byte
				char _char
				Character _Character
				java.util.Date _date
				double _double
				Double _Double
				float _float
				Float _Float
				int _int
				Integer _Integer
				Class _class
				Object _object
				long _long
				Long _Long
				short _short
				Short _Short
				String _String
			} 
		''')
		EcoreUtil::resolveAll(pack.eResource)
		vth.assertNoErrors(pack);
	}

}