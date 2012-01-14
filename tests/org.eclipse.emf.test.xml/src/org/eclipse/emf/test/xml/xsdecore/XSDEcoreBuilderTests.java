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
 * Test for conversion from XSD to ECore - compare against expected output
 */
public class XSDEcoreBuilderTests extends TestCase
{

  XSDEcoreBuilder xsdEcoreBuilder;

  final static String BASE_XSD_URI = "file:///" + TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xsd/";

  public XSDEcoreBuilderTests(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("XSDEcoreBuilderTests");
    ts.addTest(new XSDEcoreBuilderTests("testMyIPO"));
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

  public void testMyIPO() throws Exception
  {
    // SET SCHEMA URI
    @SuppressWarnings("unchecked")
    Collection<EPackage> packageList = (Collection<EPackage>)(Collection<?>)xsdEcoreBuilder.generate(URI.createURI(BASE_XSD_URI + "myipo.xsd"));

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
    Resource resource = rs.createResource(URI.createURI(BASE_XSD_URI + "myipo.xml"));

    // LOAD INSTANCE DOCUMENT
    resource.load(options);

    // SERIALIZE
    ByteArrayOutputStream out = new ByteArrayOutputStream(2064);
    resource.save(out, options);
    String value = new String(out.toByteArray(), "UTF8");
    
    assertTrue(value.contains("]]&gt;") );
  }

}