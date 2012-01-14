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


import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.customer.CustomerPackage;
import org.eclipse.emf.test.models.movie.db.DBPackage;
import org.eclipse.emf.test.models.order.OrderPackage;
import org.eclipse.emf.test.xml.AllSuites;
import org.w3c.dom.Document;

/**
 * Test for XMI package: loading data/order.xml 
 */
public class OrderTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";

  String inputXML;

  HashMap<String, Object> options;

  public OrderTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("OrderTest");
    ts.addTestSuite(OrderTest.class);
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    OrderPackage.eINSTANCE.getName();
    CustomerPackage.eINSTANCE.getName();
    DBPackage.eINSTANCE.getName();
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    inputXML = BASE_XML_URI + "order.xml";
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

  public void testQname() throws Exception
  {
    URI uri = URI.createFileURI(inputXML);
    ResourceSet resourceSet = new ResourceSetImpl();
    XMLResource resource = (XMLResource)resourceSet.createResource(uri);
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
    options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
    options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
    resource.load(options);
    StringWriter stringWriter = new StringWriter();
    OutputStream out = new URIConverter.WriteableOutputStream(stringWriter, null);
    resource.save(out, null);
    String result1 = stringWriter.toString();
    stringWriter.getBuffer().setLength(0);
    resource.unload();
    
    InputStream input = resourceSet.getURIConverter().createInputStream(uri);
    try
    {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setNamespaceAware(true);
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document =  documentBuilder.parse(input);
      resource = (XMLResource)resourceSet.createResource(uri);
      options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
      options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
      resource.load(document, options);
      resource.save(System.err, null);
      resource.save(out, null);
      resource.unload();
      String result2 = stringWriter.toString();
      stringWriter.getBuffer().setLength(0);
      resource.unload();
      options.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, Boolean.TRUE);
      resource.load(document.getDocumentElement(), options);
      resource.save(out, null);
      String result3 = stringWriter.toString();
      stringWriter.getBuffer().setLength(0);
      resource.unload();
      
      assertEquals(result1, result2);
      assertEquals(result1, result3);
    }
    finally
    {
      input.close();
    }
  }
}