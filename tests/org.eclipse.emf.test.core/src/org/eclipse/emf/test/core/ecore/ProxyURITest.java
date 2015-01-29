/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.test.core.ecore;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;

/**
 * Class containing tests about proxy URI after unload of a resource.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ProxyURITest extends TestCase {

	/**
	 * Test that the URI of an {@link EClass} contained in a sub-package of the
	 * root of a {@link Resource} is the same before and after the unload of the
	 * {@link Resource}
	 */
	public void testURIAfterUnload() {
		checkClassURI(loadResources(false));
	}

	/**
	 * Same test as {@link #testURIAfterUnload()} but the {@link Resource}
	 * containing the {@link EClass} is a fragment of another {@link Resource}.
	 */
	public void testURIAfterUnloadWithFragment() {
		checkClassURI(loadResources(true));
	}

	/**
	 * Check that the {@link URI} if the {@link EClass} (contained in the first
	 * package of the root of the resource) has the same URI before and after
	 * unload of the resource.
	 * 
	 * @param res
	 *            {@link Resource} containing the {@link EClass}
	 */
	private void checkClassURI(Resource res) {
		//Get the class
		EClass myClass = (EClass) ((EPackage) res.getContents().get(0)).getESubpackages().get(0).getEClassifiers().get(0);
		//Get the URI before unload
		URI expectedURI = EcoreUtil.getURI(myClass);
		//Unload the resource
		res.unload();
		//Check if the URI has changed
		assertEquals("URI should be the same before after unload of the resource.", expectedURI, EcoreUtil.getURI(myClass));
	}

	/**
	 * Load the resources needed for this test.
	 * 
	 * @param loadMain
	 *            false to only load the "fragmented" part, true to load main
	 *            and "fragmented" resources.
	 * @return The "fragmented" resource
	 */
	private Resource loadResources(boolean loadMain) {
		ResourceSet rset = new ResourceSetImpl();
		Resource controlledResource = rset.getResource(URI
				.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID)
						+ "/data/RootSubPackage1.ecore"), true);
		if (loadMain) {
			rset.getResource(URI.createFileURI(TestUtil
					.getPluginDirectory(AllSuites.PLUGIN_ID)
					+ "/data/Root.ecore"), true);
		}
		EcoreUtil.resolveAll(rset);
		return controlledResource;
	}
}
