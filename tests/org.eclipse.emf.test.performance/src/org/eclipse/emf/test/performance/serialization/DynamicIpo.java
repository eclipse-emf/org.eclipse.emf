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
 * $Id: DynamicIpo.java,v 1.7 2005/05/13 14:33:23 bportier Exp $
 */
package org.eclipse.emf.test.performance.serialization;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


/**
 * Test serialization of ipo.xml using dynamic model
 */
public class DynamicIpo extends EMFPerformanceTestCase
{
  final static String BASE_XSD_URI = "file:///" + TestUtil.getPluginDirectory() + "/data/";

  // 10 reps needed to get consistent results (tested on static ser w/ caching)
  final static int REPETITIONS = 50;

  final static int ITERATIONS = 2000;

  final static int WARMUP = 3000;

  XSDEcoreBuilder xsdEcoreBuilder;

  Resource resource;

  HashMap options = new HashMap();

  ArrayList cache = new ArrayList();

  ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);

  public DynamicIpo(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicIpo");
    //OK ts.addTest(new DynamicIpo("testSer").setRepetitions(REPETITIONS));
    //OK ts.addTest(new DynamicIpo("testSerCaching").setRepetitions(REPETITIONS));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
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

    Collection packageList = xsdEcoreBuilder.generate(getXSDURI());

    for (Iterator packageIterator = packageList.iterator(); packageIterator.hasNext();)
    {
      EPackage epackage = (EPackage)packageIterator.next();
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
   * Test details:
   * </p>
   * Tests serialization of data/ipo.xml. No special options set up.
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
   * Test details:
   * </p>
   * Tests serialization of data/ipo.xml. The XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE is specified.
   * @throws Exception
   */
  public void testSerCaching() throws Exception
  {
    options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, cache);
    startMeasuring();
    serialize(ITERATIONS, options);
    stopMeasuring();
  }

  protected final void serialize(int iter, HashMap saveOptions) throws Exception
  {
    for (int i = 0; i < iter; i++)
    {
      resource.save(outputstream, saveOptions);
      outputstream.reset();
    }
  }

}
