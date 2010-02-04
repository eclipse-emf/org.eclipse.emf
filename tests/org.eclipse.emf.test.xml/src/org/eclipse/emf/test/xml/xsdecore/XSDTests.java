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
 * $Id: XSDTests.java,v 1.9 2010/02/04 20:56:59 emerks Exp $
 */
package org.eclipse.emf.test.xml.xsdecore;


import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.xml.AllSuites;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


/**
 * General tests for XSDBuilder
 */
public class XSDTests extends TestCase
{

  XSDEcoreBuilder xsdEcoreBuilder;

  final static String BASE_XSD_URI = "file:///" + TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xsd/";

  public XSDTests(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("XSDTests");
    ts.addTest(new XSDTests("testEnumLoad"));
    ts.addTest(new XSDTests("testEnumUnion"));
    ts.addTest(new XSDTests("testEnumSave"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {

    xsdEcoreBuilder = new XSDEcoreBuilder();
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMLResourceFactoryImpl());

  }

  /*
   * Bug #83463
   */
  public void testEnumUnion() throws Exception
  {
    testHelper(BASE_XSD_URI + "unionEnum.xsd", BASE_XSD_URI + "unionEnum.xml");
  }

  public void testEnumLoad() throws Exception
  {
    testHelper(BASE_XSD_URI + "enum.xsd", BASE_XSD_URI + "enum.xml");
  }

  public void testEnumSave() throws Exception
  {
    testHelper(BASE_XSD_URI + "enum.xsd", BASE_XSD_URI + "enum-single.xml");
  }

  protected void testHelper(String schema, String xml) throws Exception
  {
    //  SET SCHEMA URI
    @SuppressWarnings("unchecked")
    Collection<EPackage> packageList = (Collection<EPackage>)(Collection<?>)xsdEcoreBuilder.generate(URI.createURI(schema));

    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));

    ResourceSet rs = new ResourceSetImpl();
    Registry packageRegistry = rs.getPackageRegistry();
    for (EPackage epackage : packageList)
    {
      String nsURI = epackage.getNsURI();
      packageRegistry.put(nsURI, epackage);
    }

    ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(packageRegistry);
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

    // SET INSTANCE DOCUMENT URI
    Resource resource = rs.createResource(URI.createURI(xml));

    // LOAD INSTANCE DOCUMENT
    resource.load(options);

    // SERIALIZE
    resource.save(new ByteArrayOutputStream(2064), options);
  }

}