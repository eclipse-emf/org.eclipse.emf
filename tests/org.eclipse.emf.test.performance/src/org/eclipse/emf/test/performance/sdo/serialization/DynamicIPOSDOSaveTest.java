/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: DynamicIPOSDOSaveTest.java,v 1.12 2006/12/30 03:43:52 marcelop Exp $
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


/**
 * Saves/serializes SDO instances of dynamic International Purchase Order (IPO) model. 
 */
public class DynamicIPOSDOSaveTest extends EMFPerformanceTestCase
{
  private static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  //private static final String DATA_URI = "file:///" + DATA;

  private static String XML_SCHEMA_URI;

  private static String XML_INSTANCE;

  final static int REPETITIONS = 50;

  final static int ITERATIONS = 10000;

  final static int WARMUP = 3000;

  private HashMap<Object, Object> options;

  protected ResourceSet resourceSet;

  private Resource resource;

  private ArrayList<Object> cache;

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
    ts.addTest(new DynamicIPOSDOSaveTest("saveWithCaching").setRepetitions(REPETITIONS));
    return ts;
  }

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);

    resourceSet = SDOUtil.createResourceSet();
    options = new HashMap<Object, Object>();
    cache = new ArrayList<Object>();
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
   * Saves/serializes a static or dynamic instance of the SDO International Purchase Order (IPO) model.
   * Options: none
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
   * Saves/serializes a static or dynamic instance of the SDO International Purchase Order (IPO) model.
   * Options:
   * <ul>
   * <li>XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE</li>
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
