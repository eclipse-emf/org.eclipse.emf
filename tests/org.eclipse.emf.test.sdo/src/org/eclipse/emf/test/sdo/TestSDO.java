/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: TestSDO.java,v 1.9 2008/05/04 10:58:55 emerks Exp $
 */
package org.eclipse.emf.test.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.sdo.simple.SimpleFactory;

import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.EDataObject;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;

import commonj.sdo.DataObject;

public class TestSDO extends TestCase
{
  private static final boolean SYSOUT = false;
  
  private ResourceSet resourceSet;
  private EPackage ePackage;
  private EFactory eFactory;
  private EDataGraph eDataGraph;

  public TestSDO(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("TestSDO");
    ts.addTest(new TestSDO("testStaticModel"));
    ts.addTest(new TestSDO("testDynamicModel"));
    return ts;
  }
  
  @Override
  protected void setUp() throws Exception
  {
    EcorePackage.eINSTANCE.eClass();

    eDataGraph = SDOFactory.eINSTANCE.createEDataGraph();
    resourceSet = eDataGraph.getResourceSet();
    Resource model = resourceSet.getResource(URI.createFileURI(TestUtil.getTestCommonDirectory() + "/models/sdo.Simple/simple.ecore"), true);
    if (SYSOUT) model.save(System.out, null);

    ePackage = (EPackage)model.getContents().get(0);
    ePackage.setEFactoryInstance
      (new EFactoryImpl()
       {
         @Override
        public EObject basicCreate(EClass eClass) 
         {
           return new DynamicEDataObjectImpl(eClass);
         }
       });
    eFactory = ePackage.getEFactoryInstance();
  }
  
  public void testStaticModel() throws Exception
  {
    modelTest(resourceSet, (DataObject)SimpleFactory.INSTANCE.createQuote());
  }

  public void testDynamicModel() throws Exception
  {
    modelTest(resourceSet, (DataObject)eFactory.create((EClass)ePackage.getEClassifier("Quote")));
  }
  
  @Override
  protected void tearDown() throws Exception
  {
    EDataObject root = (EDataObject)eFactory.create((EClass)ePackage.getEClassifier("Quote")); 
    EDataObject child = (EDataObject)eFactory.create((EClass)ePackage.getEClassifier("Quote"));
    child.createDataObject("quotes");
    
    @SuppressWarnings("unchecked") List<EDataObject> dataObjects = root.getList("quotes");
    dataObjects.add(child);
    eDataGraph.setERootObject(root);
    if (SYSOUT) eDataGraph.getDataGraphResource().save(System.out, null);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    objectOutputStream.writeObject(eDataGraph);
    objectOutputStream.close();
    InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
    EDataGraph otherEDataGraph = (EDataGraph)objectInputStream.readObject();
    if (SYSOUT) otherEDataGraph.getDataGraphResource().save(System.out, null);
  }

  protected void modelTest(ResourceSet resourceSet, DataObject quote) throws Exception
  {
    quote.setString("symbol", "fbnt");
    quote.setString("companyName", "FlyByNightTechnology");
    quote.setBigDecimal("price", new BigDecimal("1000.0"));
    quote.setBigDecimal("open1", new BigDecimal("1000.0"));
    quote.setBigDecimal("high", new BigDecimal("1000.0"));
    quote.setBigDecimal("low", new BigDecimal("1000.0"));
    quote.setDouble("volume", 1000);
    quote.setDouble("change1", 1000);
    
    Resource quoteResource = resourceSet.createResource(URI.createURI("quote.xml"));
    quoteResource.getContents().add((EObject)quote);
    
    Map<String, Object> options = new HashMap<String, Object>();
    options.put(XMLResource.OPTION_LINE_WIDTH, new Integer(10));
    if (SYSOUT) quoteResource.save(System.out, options);

    assertEquals("fbnt", quote.getString("symbol"));
  }
}
