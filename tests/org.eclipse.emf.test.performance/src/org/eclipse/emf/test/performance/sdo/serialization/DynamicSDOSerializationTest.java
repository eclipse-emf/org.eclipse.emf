/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: DynamicSDOSerializationTest.java,v 1.1 2005/02/15 20:21:05 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.serialization;


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
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

import commonj.sdo.DataGraph;


public class DynamicSDOSerializationTest extends EMFPerformanceTestCase
{
  private static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  private static final String DATA_URI = "file:///" + DATA;
  
  final static int REPETITIONS = 10;

  final static int ITERATIONS = 1500;

  final static int WARMUP = 3000;

  private XSDEcoreBuilder xsdEcoreBuilder;

  private HashMap options = new HashMap();
  
  private Resource resource;

  private DataGraph dataGraph;
  
  private ArrayList cache = new ArrayList();

  private ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);

  public DynamicSDOSerializationTest(String name)
  {
    super(name);
    EPackage.Registry.INSTANCE.clear();
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicSDOSerializationTest");
    ts.addTest(new DynamicSDOSerializationTest("serialize").setRepetitions(REPETITIONS));
    ts.addTest(new DynamicSDOSerializationTest("serializeWithCaching").setRepetitions(REPETITIONS));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    super.setUp();   
    ResourceSet rs = SDOUtil.createResourceSet();  
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, registerModel(rs));

    // SET INSTANCE DOCUMENT URI
    //resource = rs.createResource(getXMLInstanceURI());
    
    // LOAD INSTANCE DOCUMENT
    // TODO
    resource.load(options);
     
    // WARMUP
    serialize(WARMUP, options);
  }
  
  protected ExtendedMetaData registerModel(ResourceSet rs)
  {
    Registry packageRegistry = rs.getPackageRegistry();
    xsdEcoreBuilder = new XSDEcoreBuilder();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    //rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    //rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMLResourceFactoryImpl());

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
    return URI.createURI(DATA_URI + "ipoDG.xml");
  }
  
  protected URI getXSDURI()
  {
    return URI.createURI(DATA_URI + "ipo.xsd");
  }

  /**
   * <p>
   * Test details:
   * </p>
   * Tests serialization of data/ipoDG.xml. No special options set up.
   * @throws Exception
   */
  public void serialize() throws Exception
  {
    startMeasuring();
    serialize(ITERATIONS, options);
    stopMeasuring();
  }

  /**
   * <p>
   * Test details:
   * </p>
   * Tests serialization of data/ipoDG.xml. The XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE is specified.
   * @throws Exception
   */
  public void serializeWithCaching() throws Exception
  {
    options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, cache);
    startMeasuring();
    serialize(ITERATIONS, options);
    stopMeasuring();
  }

  protected void serialize(int iter, HashMap saveOptions) throws Exception
  {
    for (int i = 0; i < iter; i++)
    {
      resource.save(outputstream, saveOptions);
      outputstream.close();
      outputstream.reset();
    }
  }

}
