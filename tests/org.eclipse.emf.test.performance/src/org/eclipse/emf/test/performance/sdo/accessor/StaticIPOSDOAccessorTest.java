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
 * $Id: StaticIPOSDOAccessorTest.java,v 1.1 2005/02/16 23:02:12 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.xsd.impl.type.XSDDateType;

import com.example.sdo.ipo.Address;
import com.example.sdo.ipo.DocumentRoot;
import com.example.sdo.ipo.IpoFactory;
import com.example.sdo.ipo.ItemType;
import com.example.sdo.ipo.Items;
import com.example.sdo.ipo.PurchaseOrderType;
import com.example.sdo.ipo.USAddress;
import com.example.sdo.ipo.USState;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class StaticIPOSDOAccessorTest extends EMFPerformanceTestCase
{
  private static final int REPETITIONS = 5;

  private static final int ITERATIONS = 40000;

  private static final int WARMUP = 200;

  private static final int TYPED_WARMUP = 3000;

  private static final int PATH_WARMUP = 100;

  private static final int NUM_ITEMS = 1;

  private static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  private static final String DATA_URI = "file:///" + DATA;

  private IPOSDOAccessorTest accessorTest;

  // static model

  private Property shipToProp;

  private Property billToProp;

  private Property commentProp;

  private Property itemsProp;

  private Property itemProp;

  private Property orderDateProp;

  private Property productNameProp;

  private Property quantityProp;

  private Property usPriceProp;

  private Property itemCommentProp;

  private Property shipDateProp;

  private Property partNumProp;

  private IpoFactory ipoFactoryInstance = IpoFactory.eINSTANCE;

  // the static purchase order
  private PurchaseOrderType po;

  private Object orderDate = new XSDDateType().getValue("2005-02-10");

  private Object shipDate = new XSDDateType().getValue("2005-02-20");

  private USAddress shipToAddress;

  private USAddress billToAddress;

  private String orderComment = "My first purchase order.";

  // instance data (used in the get tests)

  private Address billToValue;

  private Address shipToValue;

  private String orderCommentValue;

  private Object orderDateValue;

  private List itemsValue;

  private ItemType itemElementValue;

  private String productNameValue;

  private BigInteger quantityValue;

  private BigDecimal usPriceValue;

  private String itemCommentValue;

  private Object shipDateValue;

  private String partNumValue;

  // instance data (used in set tests)

  private ItemType itemElement;

  private List item;

  private Object newOrderDate = new XSDDateType().getValue("2006-02-10");

  private String newOrderComment = "Another comment.";

  private USAddress newShipToAddress;

  private USAddress newBillToAddress;

  private String productName = "The new Product.";

  private BigInteger quantity = new BigInteger("5000");

  private BigDecimal usPrice = new BigDecimal(4488);

  private String itemComment = "A comment on the item";

  private Object newShipDate = new XSDDateType().getValue("2006-03-10");;

  private String partNum = "part123456";

  public StaticIPOSDOAccessorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

    testSuite.addTest(new StaticIPOSDOAccessorTest("getStronglyTyped").setWarmUp(TYPED_WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByProperty").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByIndex").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByPath").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setStronglyTyped").setWarmUp(TYPED_WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByProperty").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByIndex").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByPath").setWarmUp(PATH_WARMUP).setRepetitions(REPETITIONS));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();

    tagAsSummary("Performance Results for " + getClass().getPackage().getName(), TIME_DIMENSIONS);

    initPO();

    accessorTest = new IPOSDOAccessorTest(
      ITERATIONS,
      shipToProp,
      billToProp,
      commentProp,
      itemsProp,
      itemProp,
      orderDateProp,
      productNameProp,
      quantityProp,
      usPriceProp,
      itemCommentProp,
      shipDateProp,
      partNumProp);

    accessorTest.initInstanceData(
      (DataObject)newShipToAddress,
      (DataObject)newBillToAddress,
      newOrderComment,
      newOrderDate,
      productName,
      quantity,
      usPrice,
      itemComment,
      newShipDate,
      partNum);

    // serialize DG so that it can be deserialized by DynamicAccessorTest
    serializeDataGraph();

    assertNotNull(po);
  }

  private void initPO()
  {
    SDOFactory sdoFactoryInstance = SDOFactory.eINSTANCE;
    EDataGraph dataGraph = sdoFactoryInstance.createEDataGraph();

    DocumentRoot root = ipoFactoryInstance.createDocumentRoot();
    po = ipoFactoryInstance.createPurchaseOrderType();

    shipToAddress = ipoFactoryInstance.createUSAddress();
    shipToAddress.setCity("Austin");
    shipToAddress.setName("The Address");
    shipToAddress.setState(USState.AR_LITERAL);
    shipToAddress.setStreet("24 Lakeshore Dr.");
    shipToAddress.setZip(new BigInteger("78741"));
    po.setShipTo(shipToAddress);

    newShipToAddress = ipoFactoryInstance.createUSAddress();
    newShipToAddress.setCity("Toronto");
    newShipToAddress.setName("not the GTA one.");
    // leave state not set.
    //newShipToAddress.setState(USState.PA_LITERAL);
    newShipToAddress.setStreet("37 Jenner Way");
    newShipToAddress.setZip(new BigInteger("66524"));

    // leave comment not set.
    //po.setComment(orderComment);

    po.setOrderDate(orderDate);

    billToAddress = ipoFactoryInstance.createUSAddress();
    billToAddress.setCity("Paris");
    billToAddress.setName("One of many");
    billToAddress.setState(USState.AK_LITERAL);
    billToAddress.setStreet("411 Duplex Av.");
    billToAddress.setZip(new BigInteger("14665"));
    po.setBillTo(billToAddress);

    newBillToAddress = ipoFactoryInstance.createUSAddress();
    newBillToAddress.setCity("New York City");
    newBillToAddress.setName("Mr. Big Apple");
    // leave state not set
    //newBillToAddress.setState(USState.AL_LITERAL);
    newBillToAddress.setStreet("222 Manhattan ");
    newBillToAddress.setZip(new BigInteger("12345"));

    Items items = ipoFactoryInstance.createItems();
    po.setItems(items);

    for (int i = 0; i < NUM_ITEMS; i++)
    {
      itemElement = ipoFactoryInstance.createItemType();
      itemElement.setComment("comment " + i);
      itemElement.setPartNum("part num " + i + 10);
      itemElement.setProductName("The " + i + " name");
      // leave quantity unset.
      //item.setQuantity(new BigInteger("133"));
      itemElement.setShipDate(shipDate);
      itemElement.setUSPrice(new BigDecimal(100 + i));
      items.getItem().add(itemElement);
    }

    List properties = ((DataObject)po).getType().getProperties();
    shipToProp = (Property)properties.get(0);
    billToProp = (Property)properties.get(1);
    commentProp = (Property)properties.get(2);
    itemsProp = (Property)properties.get(3);
    itemProp = itemsProp.getType().getProperty("item");
    orderDateProp = (Property)properties.get(4);
    List itemProperties = itemProp.getType().getProperties();
    productNameProp = (Property)itemProperties.get(0);
    quantityProp = (Property)itemProperties.get(1);
    usPriceProp = (Property)itemProperties.get(2);
    itemCommentProp = (Property)itemProperties.get(3);
    shipDateProp = (Property)itemProperties.get(4);
    partNumProp = (Property)itemProperties.get(5);

    root.setPurchaseOrder(po);
    dataGraph.setERootObject((EObject)root);
    dataGraph.setEChangeSummary(sdoFactoryInstance.createEChangeSummary());
  }

  private void serializeDataGraph()
  {
    DataGraph dataGraph = ((DataObject)po).getDataGraph();
    try
    {
      String fileName = DATA + "ipoDG1.xml";
      Resource resource = ((EDataGraph)dataGraph).getDataGraphResource();
      resource.save(new FileOutputStream(fileName), Collections.EMPTY_MAP);
    }
    catch (IOException e)
    {
    }
  }

  /**
   * <p>
   * Uses the generated types to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: static</li>
   * <li>access API: strongly-typed</li>
   * </ul>
   * </p>
   */
  public final void getStronglyTyped()
  {
    startMeasuring();

    for (int i = 0; i < ITERATIONS; i++)
    {
      billToValue = po.getBillTo();
      orderCommentValue = po.getComment();
      orderDateValue = po.getOrderDate();
      shipToValue = po.getShipTo();

      itemsValue = po.getItems().getItem();
      for (int j = 0; j < itemsValue.size(); j++)
      {
        itemElementValue = (ItemType)itemsValue.get(j);
        productNameValue = itemElementValue.getProductName();
        quantityValue = itemElementValue.getQuantity();
        usPriceValue = itemElementValue.getUSPrice();
        itemCommentValue = itemElementValue.getComment();
        shipDateValue = itemElementValue.getShipDate();
        partNumValue = itemElementValue.getPartNum();
      }
    }

    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: static</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
  public void getByProperty()
  {
    DataObject poDataObject = (DataObject)po;
    startMeasuring();
    accessorTest.getByProperty(poDataObject);
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: static</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public void getByIndex()
  {
    DataObject poDataObject = (DataObject)po;
    startMeasuring();
    accessorTest.getByIndex(poDataObject);
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: static</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public void getByPath()
  {
    DataObject poDataObject = (DataObject)po;
    startMeasuring();
    accessorTest.getByPath(poDataObject);
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the generated types to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: static</li>
   * <li>access API: strongly-typed</li>
   * </ul>
   * </p>
   */
  public final void setStronglyTyped()
  {
    startMeasuring();

    for (int i = 0; i < ITERATIONS; i++)
    {

      po.setBillTo(shipToAddress);
      po.setComment(newOrderComment);
      po.setOrderDate(newOrderDate);
      po.setShipTo(billToAddress);

      item = po.getItems().getItem();
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElement = (ItemType)item.get(j);
        itemElement.setProductName(productName);
        itemElement.setQuantity(quantity);
        itemElement.setUSPrice(usPrice);
        itemElement.setComment(itemComment);
        itemElement.setShipDate(newShipDate);
        itemElement.setPartNum(partNum);
      }
    }

    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: static</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
  public void setByProperty()
  {
    DataObject poDataObject = (DataObject)po;
    startMeasuring();
    accessorTest.setByProperty(poDataObject);
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: static</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public void setByIndex()
  {
    DataObject poDataObject = (DataObject)po;
    startMeasuring();
    accessorTest.setByIndex(poDataObject);
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: static</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public void setByPath()
  {
    DataObject poDataObject = (DataObject)po;
    startMeasuring();
    accessorTest.setByPath(poDataObject);
    stopMeasuring();
  }

}
