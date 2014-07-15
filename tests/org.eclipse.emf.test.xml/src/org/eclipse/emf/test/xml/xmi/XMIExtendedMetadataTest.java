/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.xml.xmi;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.xml.AllSuites;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test for XMI package:
 * EAttribute annotated with ExtendedMetaData (kind->simple) does not load correctly at runtime (105746)
 * XMLHandler.handleFeature Fails to Load Certain EReferences When Using ExtendedMetadata (101877)
 */
public class XMIExtendedMetadataTest
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xmi/";

  final static String BASE_ECORE_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/ecore/";

  protected ResourceSet resourceSet;

  protected HashMap<String, Object> options;

  @Before
  public void setUp() throws Exception
  {
    resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    options = new HashMap<String, Object>();
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

    Resource ecore = resourceSet.createResource(URI.createFileURI(BASE_ECORE_URI + "profile.ecore"));
    ecore.load(options);
    EPackage p = ((EPackage)ecore.getContents().get(0));
    resourceSet.getPackageRegistry().put(p.getNsURI(), p);

  }

  @After
  public void tearDown() throws Exception
  {
    options = null;
    resourceSet = null;
  }

  @Test
  public void testTopDescription() throws Exception
  {

    Resource resource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "profile.xmi"));
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
    resource.load(options);
    EObject description = resource.getContents().get(0);
    EStructuralFeature value = description.eClass().getEStructuralFeature("value");
    assertNotNull("Description value must not be null", description.eGet(value));
  }

  @Test
  public void testNestedDescription() throws Exception
  {

    Resource resource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "profileNested.xmi"));
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
    resource.load(options);
    EObject classification = resource.getContents().get(0);
    EStructuralFeature descriptorGroupFeature = classification.eClass().getEStructuralFeature("descriptorGroup");

    EObject descriptorGroup = (EObject)((EList<?>)classification.eGet(descriptorGroupFeature)).get(0);

    EStructuralFeature descriptionFeature = descriptorGroup.eClass().getEStructuralFeature("description");
    EObject description = (EObject)descriptorGroup.eGet(descriptionFeature);
    EStructuralFeature value = description.eClass().getEStructuralFeature("value");
    assertNotNull("Description value must not be null", description.eGet(value));
  }

  /**
   * 101877
   * @throws Exception
   */
  @Test
  public void testProxy() throws Exception
  {
    Resource ecore = resourceSet.createResource(URI.createFileURI(BASE_ECORE_URI + "proxy.ecore"));
    ecore.load(options);
    EPackage p = ((EPackage)ecore.getContents().get(0));
    resourceSet.getPackageRegistry().put(p.getNsURI(), p);

    Resource resource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "proxy.xmi"));
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
    resource.load(options);

    EObject proxyRoot = resource.getContents().get(0);

    EStructuralFeature contextFeature = proxyRoot.eClass().getEStructuralFeature("context");
    EObject context = (EObject)proxyRoot.eGet(contextFeature, false);
    assertEquals(
      "Not maching expected value of 'http://some-proxy'",
      URI.createURI("http://some-proxy"),
      ((InternalEObject)context).eProxyURI());

  }

}
