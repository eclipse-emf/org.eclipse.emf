/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: DateTest.java,v 1.7 2007/06/02 19:37:15 emerks Exp $
 */
package org.eclipse.emf.test.sdo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.ipo.DocumentRoot;
import org.eclipse.emf.test.models.ipo.PurchaseOrderType;
import org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl;
import org.eclipse.emf.test.models.ipo.util.IPOResourceFactoryImpl;

import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;


/*
 * Bugzilla 81752
 * Tests modified IPO where orderDate is of type dateTime, and item shipDate is of time date.
 */
public class DateTest extends TestCase
{
  PurchaseOrderType po;

  Resource resource;

  Map<String, Object> options;

  // constants for the set date APIs
  protected final static int generated = 0, eSet = 1, set = 2, setDate = 3;

  // the Date value
  protected static Date then = new Date();
  protected static XMLCalendar thenXML = new XMLCalendar(then, XMLCalendar.DATE);

  public DateTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("DateTest");
    testSuite.addTest(new DateTest("generatedSetAndSerialize"));
    testSuite.addTest(new DateTest("eSetAndSerialize"));
    testSuite.addTest(new DateTest("setAndSerialize"));
    testSuite.addTest(new DateTest("setDateAndSerialize"));
    return testSuite;
  }

  @Override
  protected void setUp() throws Exception
  {
    options = new HashMap<String, Object>();
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, registerModel());

    String DATA = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/";
    // String XML_SCHEMA_URI = "file:///" + DATA + "ipo.xsd";
    String XML_INSTANCE = DATA + "ipoDG.xml";

    FileInputStream inputStream = new FileInputStream(XML_INSTANCE);
    DataGraph dataGraph = SDOUtil.loadDataGraph(inputStream, options);
    inputStream.close();
    assertNotNull(dataGraph);
    po = ((DocumentRoot)dataGraph.getRootObject()).getPurchaseOrder();
    resource = ((EDataGraph)dataGraph).getDataGraphResource();

  }

  protected ExtendedMetaData registerModel()
  {
    IPOPackageImpl.eINSTANCE.getName();
    ResourceSet resourceSet = SDOUtil.createResourceSet();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IPOResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

  public void generatedSetAndSerialize() throws Exception
  {
    setDate(DateTest.generated);
    testDate();
  }

  public void eSetAndSerialize() throws Exception
  {
    setDate(DateTest.eSet);
    testDate();
  }

  public void setAndSerialize() throws Exception
  {
    setDate(DateTest.set);
    testDate();
  }

  public void setDateAndSerialize() throws Exception
  {
    // 1. set date to a Date.
    setDate(DateTest.setDate);
    testDate();
  }

  protected void setDate(int option)
  {
    // set the shipDate date value on the item using DataObject::setDate()
    DataObject itemDO = (DataObject)this.po.getItems().getItem().get(0);
    itemDO.setDate("shipDate", then);

    // to set the value of the order date, use a different API:
    switch (option)
    {
      case DateTest.generated:
      {
        // using generated code:
        po.setOrderDate(thenXML);
        break;
      }
      case DateTest.eSet:
      {
        // use EObject::eSet to set the dateTime value
        EObject poEObject = (EObject)this.po;
        EClass poEClass = poEObject.eClass();
        EAttribute orderDateAttr = poEClass.getEAllAttributes().get(1);
        poEObject.eSet(orderDateAttr, thenXML);
        break;
      }
      case DateTest.set:
      {
        // using DataObject::set():
        DataObject poDO = (DataObject)this.po;
        poDO.set("orderDate", thenXML);
        break;
      }
      case DateTest.setDate:
      {
        DataObject po = (DataObject)this.po;
        po.setDate("orderDate", then);
        break;
      }
    }

  }

  protected void testDate() throws Exception
  {
    DataObject po = (DataObject)this.po;

    // 2. DG is serialized.
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(2064);
    resource.save(outputStream, options);

    // 3. DG is deserialized
    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    DataGraph dataGraph = SDOUtil.loadDataGraph(inputStream, options);
    inputStream.close();
    assertNotNull(dataGraph);

    po = (DataObject)((DocumentRoot)dataGraph.getRootObject()).getPurchaseOrder();
    // get the orderDate (dateTime) Object value
    Object orderDateObjectValue = po.get("orderDate");
    assertNotNull(orderDateObjectValue);

    // get the orderDate (dateTime) value using the generated API:
    PurchaseOrderType purchaseOrder = (PurchaseOrderType)po;
    // this returns the XMLCalendar value :(
    Object generatedOrderDateValue = purchaseOrder.getOrderDate();
    assertTrue(generatedOrderDateValue != null);

    EObject poEObject = (EObject)po;
    EClass poEClass = poEObject.eClass();
    EAttribute orderDateAttr = poEClass.getEAllAttributes().get(1);
    // this returns the XMLCalendar value
    Object eSetOrderDateValue = poEObject.eGet(orderDateAttr);
    assertTrue(eSetOrderDateValue != null);

    // get the orderDate (dateTime) value using DataObject::getDate() 
    Object orderDateValue = po.getDate("orderDate");

    // the Date value should be a java.util.Date.
    assertTrue(orderDateValue instanceof Date);
    // and should have the same toString() as then??
    assertEquals("" + orderDateValue, "" + then);

    // get the shipDate (date) Objet value
    DataObject itemDO1 = (DataObject)this.po.getItems().getItem().get(0);
    Object shipDateObjectValue = itemDO1.get("shipDate");
    assertNotNull(shipDateObjectValue);

    // get the shipDate (date) Date value
    Object shipDateValue = itemDO1.getDate("shipDate");
    assertTrue(shipDateValue instanceof Date);
  }
}
