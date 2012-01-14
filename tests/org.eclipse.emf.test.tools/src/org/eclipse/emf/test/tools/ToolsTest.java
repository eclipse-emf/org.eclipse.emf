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

import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


public class ToolsTest extends TestCase
{
  /**
   * @param name
   */
  public ToolsTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("Tools Test");
    ts.addTest(new ToolsTest("testGetEcoreGenModel"));
    return ts;
  }
  
  @Override
  protected void setUp() throws Exception
  {
    assertTrue(EMFPlugin.IS_ECLIPSE_RUNNING);
  }

  public void testGetEcoreGenModel() throws Exception
  {
    String nsURI = EcorePackage.eNS_URI;

    Map<String, URI> map = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
    assertNotNull(map);

    URI genModelURI = map.get(nsURI);
    assertNotNull(genModelURI);

    ResourceSet resourceSet = new ResourceSetImpl();
    Resource genModelResource = resourceSet.getResource(genModelURI, true);
    assertNotNull(genModelResource);
    assertFalse(genModelResource.getContents().isEmpty());

    GenModel genModel = (GenModel)genModelResource.getContents().get(0);
    assertEquals("org.eclipse.emf.ecore", genModel.getModelPluginID());
  }
}
