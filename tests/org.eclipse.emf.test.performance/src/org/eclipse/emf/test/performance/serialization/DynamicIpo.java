/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: DynamicIpo.java,v 1.13 2006/12/30 03:43:52 marcelop Exp $
 */
package org.eclipse.emf.test.performance.serialization;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


/**
 * Saves/serializes EMF instances of dynamic International Purchase Order (IPO) model.
 */
public class DynamicIpo extends EMFPerformanceTestCase
{
  final static String BASE_XSD_URI = "file:///" + TestUtil.getPluginDirectory() + "/data/";

  final static int REPETITIONS = 50;

  final static int ITERATIONS = 2000;

  final static int WARMUP = 3000;

  XSDEcoreBuilder xsdEcoreBuilder;

  Resource resource;

  HashMap<Object, Object> options = new HashMap<Object, Object>();

  ArrayList<Object> cache = new ArrayList<Object>();

  ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);

  public DynamicIpo(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicIpo");
    ts.addTest(new DynamicIpo("testSer").setRepetitions(REPETITIONS));
    ts.addTest(new DynamicIpo("testSerCaching").setRepetitions(REPETITIONS));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
    ResourceSet rs = new ResourceSetImpl();
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, registerModel(rs));

    // SET INSTANCE DOCUMENT URI
    resource = rs.createResource(getXMLInstanceURI());

    // LOAD INSTANCE DOCUMENT
    resource.load(options);

    // WARMUP
    serialize(WARMUP, options);
  }

  protected ExtendedMetaData registerModel(ResourceSet rs)
  {
    Registry packageRegistry = rs.getPackageRegistry();
    xsdEcoreBuilder = new XSDEcoreBuilder();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMLResourceFactoryImpl());

    @SuppressWarnings("unchecked")
    Collection<EPackage> packageList = (Collection)xsdEcoreBuilder.generate(getXSDURI());
    for (EPackage epackage : packageList)
    {
      String nsURI = epackage.getNsURI();
      packageRegistry.put(nsURI, epackage);
    }

    return new BasicExtendedMetaData(packageRegistry);
  }

  protected URI getXMLInstanceURI()
  {
    return URI.createURI(BASE_XSD_URI + "ipo.xml");
  }

  protected URI getXSDURI()
  {
    return URI.createURI(BASE_XSD_URI + "ipo.xsd");
  }

  /**
   * <p>
   * Saves/serializes a static or dynamic instance of the EMF International Purchase Order (IPO) model.
   * Options: none
   * </p>
   * @throws Exception
   */
  public void testSer() throws Exception
  {
    startMeasuring();
    serialize(ITERATIONS, options);
    stopMeasuring();
  }

  /**
   * <p>
   * Saves/serializes a static or dynamic instance of the EMF International Purchase Order (IPO) model.
   * Options:
   * <ul>
   * <li>XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void testSerCaching() throws Exception
  {
    options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, cache);
    startMeasuring();
    serialize(ITERATIONS, options);
    stopMeasuring();
  }

  protected final void serialize(int iter, HashMap<Object, Object> saveOptions) throws Exception
  {
    for (int i = 0; i < iter; i++)
    {
      resource.save(outputstream, saveOptions);
      outputstream.reset();
    }
  }

}
