package org.eclipse.emf.ecore.xcore.tests.interpreter

import com.google.inject.Inject
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class XcoreInterpreterTest {
	
	@Inject
	ParseHelper<XPackage> parse
	
	@Inject
	ValidationTestHelper validator
	
	@Test
	def void testInterpretation() {
		val pack = parse.parse('''
			package foo.bar
			
			class Foo {
				op String doStuff(String msg) {
					return "Foo says hi to "+msg
				}
			}
		''')
		validator.assertNoErrors(pack)
		val ePackage = pack.eResource.contents.get(2) as EPackage
		val fooClass = ePackage.getEClassifier("Foo") as EClass
		val foo = ePackage.EFactoryInstance.create(fooClass)
		assertEquals("Foo says hi to Bar", foo.eInvoke(fooClass.EOperations.head, new BasicEList(newArrayList("Bar"))))
	}
	
	@Test
	def void testInterpretation_2() {
		val pack = parse.parse('''
			package foo.bar
			
			class Foo {
				op String call1(String msg) {
					return "call1"+call2("call1"+msg)
				}
				
				op String call2(String msg) {
					return "call2"+msg
				}
			}
		''')
		validator.assertNoErrors(pack)
		val ePackage = pack.eResource.contents.get(2) as EPackage
		val fooClass = ePackage.getEClassifier("Foo") as EClass
		val foo = ePackage.EFactoryInstance.create(fooClass)
		assertEquals("call1call2call1Bar", foo.eInvoke(fooClass.EOperations.head, new BasicEList(newArrayList("Bar"))))
	}
}