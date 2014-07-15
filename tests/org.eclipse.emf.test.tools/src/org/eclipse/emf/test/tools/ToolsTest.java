/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;


public class ToolsTest
{
  @Before
  public void setUp() throws Exception
  {
    assertTrue(EMFPlugin.IS_ECLIPSE_RUNNING);
  }

  @Test
  public void testGetEcoreGenModel() throws Exception
  {
    String nsURI = EcorePackage.eNS_URI;

    Map<String, URI> map = EcorePlugin.getEPackageNsURIToGenModelLocationMap(true);
    assertNotNull(map);

    URI genModelURI = map.get(nsURI);
    assertNotNull(genModelURI);

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
    Resource genModelResource = resourceSet.getResource(genModelURI, true);
    assertNotNull(genModelResource);
    assertFalse(genModelResource.getContents().isEmpty());

    GenModel genModel = (GenModel)genModelResource.getContents().get(0);
    assertEquals("org.eclipse.emf.ecore", genModel.getModelPluginID());
  }
}
