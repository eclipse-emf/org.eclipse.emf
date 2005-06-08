/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: TestXMLSave.java,v 1.1.2.2 2005/06/08 18:27:46 nickb Exp $
 */
package org.eclipse.emf.test.xml.perf;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.xml.TestUtil;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


/**
 * @author elitani
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestXMLSave extends TestCase
{
  XSDEcoreBuilder xsdEcoreBuilder;

  static ByteArrayOutputStream outputstream = new ByteArrayOutputStream();

  static int WARMUP = 3000;

  static int ITER = 10000;

  final static String BASE_XSD_URI = "file:///" + TestUtil.getPluginDirectory() + "/data/xsd/";

  public TestXMLSave(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("TestXMLSave");
    ts.addTest(new TestXMLSave("testCaching"));
    ts.addTest(new TestXMLSave("testNoCaching"));
    return ts;
  }

  protected void setUp() throws Exception
  {
    xsdEcoreBuilder = new XSDEcoreBuilder();
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMLResourceFactoryImpl());
  }

  public void testCaching() throws Exception
  {
    HashMap options = new HashMap();
    options.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList());
    testPerformance(options);
  }
  
  public void testNoCaching() throws Exception
  {
    HashMap options = new HashMap();
    testPerformance(options);
  }
  
  protected void testPerformance(Map options) throws Exception
  {
    // SET SCHEMA URI
    Collection packageList = xsdEcoreBuilder.generate(URI.createURI(BASE_XSD_URI + "myipo.xsd"));

    ResourceSet rs = new ResourceSetImpl();
    Registry packageRegistry = rs.getPackageRegistry();
    for (Iterator packageIterator = packageList.iterator(); packageIterator.hasNext();)
    {
      EPackage epackage = (EPackage)packageIterator.next();
      String nsURI = epackage.getNsURI();
      packageRegistry.put(nsURI, epackage);
    }

    ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(packageRegistry);
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

    //SET INSTANCE DOCUMENT URI
    Resource resource = rs.createResource(URI.createURI(BASE_XSD_URI + "myipo.xml"));

    // LOAD INSTANCE DOCUMENT
    resource.load(options);

    // freeze the dynamic package package 
    EcoreUtil.freeze(((EObjectImpl)resource.getContents().get(0)).eClass().getEPackage());

    for (int ii = 0; ii < WARMUP; ii++)
    {
      resource.save(outputstream, options);
      outputstream.reset();

    }

    for (int l = 0; l < 5; l++)
    {
      long start = 0, finish, iter = 0;
      start = System.currentTimeMillis();
      //iter = 1000;
      for (int ii = 0; ii < ITER; ii++)
      {
        resource.save(outputstream, options);
        outputstream.reset();
      }
      finish = System.currentTimeMillis();
      System.out.println("xmlSer Runtime:   " + (finish - start) + " ms");
    }
  }

}
