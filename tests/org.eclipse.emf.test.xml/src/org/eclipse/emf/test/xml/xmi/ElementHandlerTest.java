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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.xml.AllSuites;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ElementHandlerTest
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";

  protected DocumentBuilder builder;

  @Before
  public void setUp() throws Exception
  {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
  }

  @After
  public void tearDown() throws Exception
  {
    builder = null;
  }

  @Test
  public void testSubsitutionGroup() throws Exception
  {
    String inputXML = BASE_XML_URI + "elementHandler.xml";
    String expectedXML = BASE_XML_URI + "elementHandlerOutput.xml";
    URI uri = URI.createFileURI(inputXML);
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(uri);
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
    options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
    resource.load(options);

    ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);
    resource.save(outputstream, options);

    CompareXML.compareFiles(builder, expectedXML, new ByteArrayInputStream(outputstream.toByteArray()));
  }

  @Test
  public void testComplexDeducedRoot() throws Exception
  {
    String inputXML = BASE_XML_URI + "elementHandlerComplexRoot.xml";
    String expectedXML = BASE_XML_URI + "elementHandlerComplexRootOutput.xml";
    URI uri = URI.createFileURI(inputXML);
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(uri);
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
    options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
    resource.load(options);

    ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);
    resource.save(outputstream, options);

    CompareXML.compareFiles(builder, expectedXML, new ByteArrayInputStream(outputstream.toByteArray()));
  }

  @Test
  public void testSimpleDeducedRoot() throws Exception
  {
    String inputXML = BASE_XML_URI + "elementHandlerSimpleRoot.xml";
    String expectedXML = BASE_XML_URI + "elementHandlerSimpleRootOutput.xml";
    URI uri = URI.createFileURI(inputXML);
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(uri);
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
    options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
    resource.load(options);

    ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);
    @SuppressWarnings("unchecked")
    ElementHandlerImpl elementHandler = new ElementHandlerImpl(false, (Collection<EPackage>)(Collection<?>)resourceSet.getPackageRegistry().values());
    options.put(XMLResource.OPTION_ELEMENT_HANDLER, elementHandler);
    resource.save(outputstream, options);

    CompareXML.compareFiles(builder, expectedXML, new ByteArrayInputStream(outputstream.toByteArray()));

    Resource resource2 = resourceSet.createResource(uri);
    resource2.load(new ByteArrayInputStream(outputstream.toByteArray()), options);
    outputstream = new ByteArrayOutputStream(2064);
    resource2.save(outputstream, options);
    CompareXML.compareFiles(builder, expectedXML, new ByteArrayInputStream(outputstream.toByteArray()));
  }
}