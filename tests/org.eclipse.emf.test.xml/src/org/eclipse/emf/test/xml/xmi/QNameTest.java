/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: QNameTest.java,v 1.7 2007/12/01 19:16:55 emerks Exp $
 */
package org.eclipse.emf.test.xml.xmi;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.qname.DocumentRoot;
import org.eclipse.emf.test.models.qname.QNameFactory;
import org.eclipse.emf.test.models.qname.QNamePackage;
import org.eclipse.emf.test.xml.AllSuites;

/**
 * XMI tests: loading qname.xml 
 */
public class QNameTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";

  final static String BASE_XMI_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xmi/";

  DocumentBuilder builder;

  String inputXML;

  String expectedXML;

  HashMap<String, Object> options;

  public QNameTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("QNameTest");
    ts.addTestSuite(QNameTest.class);
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    QNamePackage.eINSTANCE.getName();
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    inputXML = BASE_XML_URI + "qname.xml";
    expectedXML = BASE_XMI_URI + "qnameOutput.xml";
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    options = new HashMap<String, Object>();
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    builder = null;
    options = null;
  }

  public void testQname() throws Exception
  {
    URI uri = URI.createFileURI(inputXML);
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(uri);
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
    resource.load(options);

    ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);
    resource.save(outputstream, options);

    CompareXML.compareFiles(builder, expectedXML, new ByteArrayInputStream(outputstream.toByteArray()));
  }

  public void testNullPrefixQname() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
    Resource resource = resourceSet.createResource(URI.createURI("dummy.xml"));
    DocumentRoot documentRoot = QNameFactory.eINSTANCE.createDocumentRoot();
    documentRoot.getXMLNSPrefixMap().put("", "namespace");
    documentRoot.setAnyE(XMLTypeFactory.eINSTANCE.createQName("namespace", "qname", ""));
    resource.getContents().add(documentRoot);
    ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);
    resource.save(outputstream, options);
    ByteArrayInputStream in = new ByteArrayInputStream(outputstream.toByteArray());
    Resource resource2 = resourceSet.createResource(URI.createURI("dummy2.xml"));
    resource2.load(in, options);
    DocumentRoot documentRoot2 = (DocumentRoot)resource2.getContents().get(0);
    QName qname = documentRoot2.getAnyE();
    assertEquals(qname.getPrefix(), "");
  }

}