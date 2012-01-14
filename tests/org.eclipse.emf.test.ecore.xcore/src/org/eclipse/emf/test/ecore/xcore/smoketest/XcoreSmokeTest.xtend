/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.smoketest

import org.eclipse.xtext.junit4.smoketest.AbstractSmokeTest
import org.eclipse.xtext.linking.lazy.LazyLinkingResource
import com.google.inject.Inject
import org.eclipse.xtext.junit.util.ParseHelper
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit.validation.ValidationTestHelper
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(XcoreInjectorProvider))
class XcoreSmokeTest extends AbstractSmokeTest {
	
	@Inject extension ParseHelper<EObject> parser
	@Inject extension ValidationTestHelper validationTestHelper 
	
	/**
	 * The models don't neccessarily need to be proper Xcore models.
	 */
	override Iterable<String> getSmokeTestModels() {
		newArrayList('''
			package foo 
			import org.eclipse.emf.ecore.EObject
			import org.eclipse.emf.ecore.EEList
			class Stuff {
				String x
				contains OtherStuff otherStuff opposite parent keys x
			}
			class OtherStuff {
				container Stuff parent opposite otherStuff
				int x
				transient EEList<Integer> ints
				refers Stuff stuff 
				refers EObject data
				op int bar() { x + 1 }
			}
			type ListOfStringArray wraps java.util.List<String[]>
		'''.toString)
	}
	
	override void processModel(String model) {
		model.parse.validate
	}
	
	override void processModelWithoutResourceSet(String model) {
		
	}
	override LazyLinkingResource createResource(String string) {
		
	}
}