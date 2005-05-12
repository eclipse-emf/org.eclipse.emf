/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: DynamicIpoLoad.java,v 1.7 2005/05/12 22:30:59 bportier Exp $
 */
package org.eclipse.emf.test.performance.deserialization;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


/**
 * Test deserialization of ipo.xml using dynamic model
 */
public class DynamicIpoLoad extends EMFPerformanceTestCase
{
  final static String BASE_XSD_URI = "file:///" + TestUtil.getPluginDirectory() + "/data/";

  final static int REPETITIONS = 50;

  final static int ITERATIONS = 2000;

  final static int WARMUP = 300;

  static URI XML_INSTANCE_URI;

  XSDEcoreBuilder xsdEcoreBuilder;

  Resource resource;

  HashMap options = new HashMap();

  ResourceSet rs;

  XMLParserPool parserPool = new XMLParserPoolImpl();

  public DynamicIpoLoad(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicIpoLoad");
    ts.addTest(new DynamicIpoLoad("testLoad").setRepetitions(REPETITIONS));
    ts.addTest(new DynamicIpoLoad("testLoadParserCache").setRepetitions(REPETITIONS));
    ts.addTest(new DynamicIpoLoad("testLoadParserAndFeatureMapCache").setRepetitions(REPETITIONS));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    super.setUp();

    HashMap warmupOptions = new HashMap();
    XML_INSTANCE_URI = URI.createURI(BASE_XSD_URI + "ipo.xml");

    rs = new ResourceSetImpl();
    ExtendedMetaData metaData = registerModel(rs);

    options.put(XMLResource.OPTION_EXTENDED_META_DATA, metaData);

    warmupOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, metaData);
    warmupOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
    warmupOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
    // WARMUP
    load(WARMUP, warmupOptions);
  }

  protected ExtendedMetaData registerModel(ResourceSet rs)
  {
    Registry packageRegistry = rs.getPackageRegistry();
    xsdEcoreBuilder = new XSDEcoreBuilder();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMLResourceFactoryImpl());

    Collection packageList = xsdEcoreBuilder.generate(getXSDURI());

    for (Iterator packageIterator = packageList.iterator(); packageIterator.hasNext();)
    {
      EPackage epackage = (EPackage)packageIterator.next();
      String nsURI = epackage.getNsURI();
      packageRegistry.put(nsURI, epackage);
    }

    return new BasicExtendedMetaData(packageRegistry);
  }

  protected URI getXSDURI()
  {
    return URI.createURI(BASE_XSD_URI + "ipo.xsd");
  }

  /**
   * <p>
   * Test details:
   * </p>
   * Tests load of data/ipo.xml. No special options set up.
   * @throws Exception
   */
  public void testLoad() throws Exception
  {
    startMeasuring();
    load(ITERATIONS, options);
    stopMeasuring();
  }

  /**
   * <p>
   * Test details:
   * </p>
   * Tests loading of data/ipo.xml. The XMLResource.OPTION_USE_PARSER_POOL is set.
   * @throws Exception
   */
  public void testLoadParserCache() throws Exception
  {
    options.put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
    startMeasuring();
    load(ITERATIONS, options);
    stopMeasuring();
  }

  /**
   * <p>
   * Test details:
   * </p>
   * Tests loading of data/ipo.xml. 
   * The XMLResource.OPTION_USE_PARSER_POOL is set.
   * The XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP is set.
   * @throws Exception
   */
  public void testLoadParserAndFeatureMapCache() throws Exception
  {
    options.put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);
    options.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
    startMeasuring();
    load(ITERATIONS, options);
    stopMeasuring();
  }

  protected final void load(int iter, HashMap loadOptions) throws Exception
  {
    for (int i = 0; i < iter; i++)
    {
      rs.getResources().clear();
      resource = rs.createResource(XML_INSTANCE_URI);
      resource.load(loadOptions);
    }
  }

}
