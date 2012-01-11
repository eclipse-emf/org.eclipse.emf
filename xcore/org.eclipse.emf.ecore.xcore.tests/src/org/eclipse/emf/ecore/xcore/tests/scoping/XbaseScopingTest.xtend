package org.eclipse.emf.ecore.xcore.tests.scoping

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
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
	
}