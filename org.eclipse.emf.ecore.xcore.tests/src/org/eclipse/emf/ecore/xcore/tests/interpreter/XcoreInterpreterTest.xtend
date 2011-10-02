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
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EEnum
import org.eclipse.xtext.resource.XtextResourceSet

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

	@Test
	def void testFeatureAccessors() {
		val pack = parse.parse('''
			package foo.bar
			
			class Foo {
				String value
				op void storeValue(String newValue) {
					value = newValue
				}
				
				op String fetchValue() {
					return value
				}
			}
		''')
		validator.assertNoErrors(pack)
		val ePackage = pack.eResource.contents.get(2) as EPackage
		val fooClass = ePackage.getEClassifier("Foo") as EClass
		val foo = ePackage.EFactoryInstance.create(fooClass)
		foo.eInvoke(fooClass.EOperations.head, new BasicEList(newArrayList("Bar")));
		assertEquals("Bar", foo.eInvoke(fooClass.EOperations.get(1), null));
	}
	
	@Test
	def void testConversionDelegates() {
		val pack = parse.parse('''
			package foo.bar 
			
			type URI wraps org.eclipse.emf.common.util.URI 
			create { if (it == null) null else org::eclipse::emf::common::util::URI::createURI(it) } 
			convert { it?.toString  }
		''')
		validator.assertNoErrors(pack)
		val ePackage = pack.eResource.contents.get(2) as EPackage
		val uriDataType = ePackage.getEClassifier("URI") as EDataType
		val literal = "http://www.eclipse.org"
		val uri = ePackage.EFactoryInstance.createFromString(uriDataType, literal) as URI;
		assertEquals(literal, ePackage.EFactoryInstance.convertToString(uriDataType, uri));
	}

	@Test
	def void testSettingDelegates() {
		val pack = parse.parse('''
			package foo.bar
			class Foo
			{
				String name
				String alias get { name}
			}
		''')
		validator.assertNoErrors(pack)
		val ePackage = pack.eResource.contents.get(2) as EPackage
		val fooClass = ePackage.getEClassifier("Foo") as EClass
		val foo = ePackage.EFactoryInstance.create(fooClass)
		foo.eSet(fooClass.getEStructuralFeature("name"), "Sven");
		assertEquals("Sven", foo.eGet(fooClass.getEStructuralFeature("alias")));
	}
	@Test
	def void testEnum() {
		val pack = parse.parse('''
			package foo.bar
			enum NodeKind { Singleton Root Intermediate Leaf }
			class Node
			{
				refers Node parent opposite children
				contains Node[0..*] children opposite parent
				op boolean hasChildren() { !children.empty }
				/*
				transient volatile derived readonly NodeKind nodeKind
				get
				{
					if (hasChildren()) {if (parent == null) {NodeKind::ROOT} else {NodeKind::INTERMEDIATE}}
					else {if (parent == null) {NodeKind::SINGLETON} else {NodeKind::LEAF}}
				}
				*/
			}
		''')
		validator.assertNoErrors(pack)
		val ePackage = pack.eResource.contents.get(2) as EPackage
		val nodeKindEnum = ePackage.getEClassifier("NodeKind") as EEnum
		val nodeClass = ePackage.getEClassifier("Node") as EClass
		val node = ePackage.EFactoryInstance.create(nodeClass)
		// assertEquals(nodeKindEnum.getEEnumLiteral("Root"), node.eGet(nodeClass.getEStructuralFeature("nodeKind")));
	}
}