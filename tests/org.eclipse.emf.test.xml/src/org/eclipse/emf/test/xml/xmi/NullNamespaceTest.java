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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.xml.AllSuites;

/**
 * Test for XMI package: loading data/order.xml 
 */
public class NullNamespaceTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";

  HashMap<String, Object> options;

  public NullNamespaceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("NullNamespaceTest");
    ts.addTestSuite(NullNamespaceTest.class);
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
    options = new HashMap<String, Object>();
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    options = null;
  }

  public void testManyNullPrefixes() throws Exception
  {
    URI uri = URI.createFileURI(BASE_XML_URI + "ManyNullPrefix.xml");
    ResourceSet resourceSet = new ResourceSetImpl();
    XMLResource resource1 = (XMLResource)resourceSet.getResource(uri, true);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    resource1.save(out, null);
    XMLResource resource2 = (XMLResource)resourceSet.createResource(URI.createURI("dummy.xml"));
    resource2.load(new ByteArrayInputStream(out.toByteArray()), null);
    EObject eObject = resource2.getContents().get(0);
    EObject root = eObject.eContents().get(0);
    @SuppressWarnings("unchecked")
    EMap<String, String> eMap = (EMap<String, String>)eObject.eGet(ExtendedMetaData.INSTANCE.getXMLNSPrefixMapFeature(eObject.eClass()));
    for (int i = 1; i < 34; ++i)
    {
      assertEquals("http://prefix" + i, eMap.get("_" + i));
      assertEquals("http://prefix" + i, ExtendedMetaData.INSTANCE.getNamespace(root.eContents().get(i - 1).eContainmentFeature()));
    }
  }

  public void testNoNullNamespace() throws Exception
  {
    URI uri = URI.createFileURI(BASE_XML_URI + "NoNullNamespace.xml");
    ResourceSet resourceSet = new ResourceSetImpl();
    XMLResource resource1 = (XMLResource)resourceSet.getResource(uri, true);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    resource1.save(out, null);
    XMLResource resource2 = (XMLResource)resourceSet.createResource(URI.createURI("dummy.xml"));
    resource2.load(new ByteArrayInputStream(out.toByteArray()), null);
    EObject eObject = resource2.getContents().get(0);
    @SuppressWarnings("unchecked")
    EMap<String, String> eMap = (EMap<String, String>)eObject.eGet(ExtendedMetaData.INSTANCE.getXMLNSPrefixMapFeature(eObject.eClass()));
    assertEquals("http://prefix2", eMap.get(""));
  }

  public void testNullNamespaceType() throws Exception
  {
    URI uri = URI.createFileURI(BASE_XML_URI + "NullNamespaceType.xml");
    ResourceSet resourceSet = new ResourceSetImpl();
    XMLResource resource1 = (XMLResource)resourceSet.getResource(uri, true);
    assertEquals
      (null,
       ExtendedMetaData.INSTANCE.getNamespace(resource1.getContents().get(0).eContents().get(0).eContents().get(0).eClass()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    resource1.save(out, null);
    XMLResource resource2 = (XMLResource)resourceSet.createResource(URI.createURI("dummy.xml"));
    resource2.load(new ByteArrayInputStream(out.toByteArray()), null);
    assertEquals
      (ExtendedMetaData.INSTANCE.getNamespace(resource1.getContents().get(0).eContents().get(0).eContents().get(0).eClass()),
       ExtendedMetaData.INSTANCE.getNamespace(resource2.getContents().get(0).eContents().get(0).eContents().get(0).eClass()));
  }

  public void testNullNamespaceElement() throws Exception
  {
    URI uri = URI.createFileURI(BASE_XML_URI + "NullNamespaceElement.xml");
    ResourceSet resourceSet = new ResourceSetImpl();
    XMLResource resource1 = (XMLResource)resourceSet.getResource(uri, true);
    assertEquals
      (null,
       ExtendedMetaData.INSTANCE.getNamespace(resource1.getContents().get(0).eContents().get(0).eContents().get(0).eContainmentFeature()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    resource1.save(out, null);
    XMLResource resource2 = (XMLResource)resourceSet.createResource(URI.createURI("dummy.xml"));
    resource2.load(new ByteArrayInputStream(out.toByteArray()), null);
    assertEquals
      (ExtendedMetaData.INSTANCE.getNamespace(resource1.getContents().get(0).eContents().get(0).eContents().get(0).eContainmentFeature()),
       ExtendedMetaData.INSTANCE.getNamespace(resource2.getContents().get(0).eContents().get(0).eContents().get(0).eContainmentFeature()));
  }
}