/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: DynamicIPOSDOSaveTest.java,v 1.7.2.3 2005/06/08 18:27:47 nickb Exp $
 */
package org.eclipse.emf.test.performance.sdo.serialization;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.emf.test.performance.sdo.SDOPerfUtil;

import commonj.sdo.DataGraph;


public class DynamicIPOSDOSaveTest extends EMFPerformanceTestCase
{
  private static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  private static final String DATA_URI = "file:///" + DATA;

  private static String XML_SCHEMA_URI;

  private static String XML_INSTANCE;

  final static int REPETITIONS = 50;

  final static int ITERATIONS = 10000;

  final static int WARMUP = 3000;

  private HashMap options;

  protected ResourceSet resourceSet;

  private Resource resource;

  private ArrayList cache;

  private ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);

  public DynamicIPOSDOSaveTest(String name)
  {
    super(name);
    XML_SCHEMA_URI = "file:///" + DATA + "ipo.xsd";
    XML_INSTANCE = DATA + "ipoDG.xml";
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicSDOSerializationTest");
    ts.addTest(new DynamicIPOSDOSaveTest("saveWithNoOptions").setRepetitions(REPETITIONS));
    // not supported yet ts.addTest(new DynamicIPOSDOSaveTest("saveWithCaching").setRepetitions(REPETITIONS));
    return ts;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);

    resourceSet = SDOUtil.createResourceSet();
    options = new HashMap();
    cache = new ArrayList();
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, registerModel());

    FileInputStream inputStream = new FileInputStream(XML_INSTANCE);
    DataGraph dataGraph = SDOUtil.loadDataGraph(inputStream, options);
    inputStream.close();
    resource = ((EDataGraph)dataGraph).getDataGraphResource();

    save(WARMUP);
  }

  protected ExtendedMetaData registerModel()
  {
    return SDOPerfUtil.registerModel(resourceSet, XML_SCHEMA_URI);
  }

  /**
   * <p>
   * Serializes SDO DataGraph.
   * Test details:
   * <ul>
   * <li>options: none</li>
   * </ul>
   * </p>
   * @throws Exception 
   */
  public void saveWithNoOptions() throws Exception
  {
    startMeasuring();
    save(ITERATIONS);
    stopMeasuring();
  }

  /**
   * <p>
   * Serializes SDO DataGraph.
   * Test details:
   * <ul>
   * <li>options: XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE</li>
   * </ul>
   * </p>
   * @throws Exception 
   */
  public void saveWithCaching() throws Exception
  {
    options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, cache);
    startMeasuring();
    save(ITERATIONS);
    stopMeasuring();
  }

  /**
   * <p>
   * Serializes SDO DataGraph.
   * </p>
   * @throws Exception 
   */
  protected final void save(int iterations) throws Exception
  {
    for (int i = 0; i < iterations; i++)
    {
      resource.save(outputstream, options);
      outputstream.reset();
    }
  }

}
