/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: QNameTest.java,v 1.2.2.1 2005/01/14 22:56:19 nickb Exp $
 */
package org.eclipse.emf.test.xml.xmi;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

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
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.models.qname.QnamePackage;
import org.eclipse.emf.test.xml.TestUtil;

/**
 * XMI tests: loading qname.xml 
 */
public class QNameTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory() + "/data/xml/";

  final static String BASE_XMI_URI = TestUtil.getPluginDirectory() + "/data/xmi/";

  DocumentBuilder builder;

  String inputXML;

  String expectedXML;

  HashMap options;

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
  protected void setUp() throws Exception
  {
    QnamePackage aPackage = QnamePackage.eINSTANCE;
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    inputXML = BASE_XML_URI + "qname.xml";
    expectedXML = BASE_XMI_URI + "qnameOutput.xml";
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    options = new HashMap();
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
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

}