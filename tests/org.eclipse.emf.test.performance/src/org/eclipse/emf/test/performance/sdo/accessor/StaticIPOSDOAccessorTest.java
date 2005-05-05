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
 * $Id: StaticIPOSDOAccessorTest.java,v 1.39 2005/05/05 13:56:15 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.test.performance.sdo.StaticIPOModel;
import org.eclipse.xsd.impl.type.XSDDateType;

import com.example.sdo.ipo.Address;
import com.example.sdo.ipo.DocumentRoot;
import com.example.sdo.ipo.ItemType;
import com.example.sdo.ipo.Items;
import com.example.sdo.ipo.PurchaseOrderType;
import com.example.sdo.ipo.USAddress;
import com.example.sdo.ipo.USState;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class StaticIPOSDOAccessorTest extends DynamicIPOSDOAccessorTest
{

  protected static final int ITERATIONS_40K = 32000000;

  protected static final int ITERATIONS_400K = 320000000;

  protected static final int ITERATIONS_800K = 640000000;

  protected static final int ITERATIONS_1_6M =  1280000000; // should be 12.8 billion but that's too big for an int, so do 10 reps of this

  protected static final int ITERATIONS_600K = 160000000;

  protected static final int ITERATIONS_1_2M = 320000000;

  protected static final int ITERATIONS_500K = 12000000;

  protected static final int ITERATIONS_80M = 80000000;

  // values for get with generated code.
  protected Address addressBillToValue;

  protected Address addressShipToValue;

  protected ItemType itemTypeElementValue;

  protected HashMap hashMap = new HashMap();

  public StaticIPOSDOAccessorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite();

//    testSuite.addTest(new StaticIPOSDOAccessorTest("getFromMap").setWarmUp(500).setRepetitions(REPETITIONS_50));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setInMap").setWarmUp(2000).setRepetitions(REPETITIONS_50));
//
//OK   testSuite.addTest(new StaticIPOSDOAccessorTest("getObjectWithEGet").setWarmUp(3).setRepetitions(REPETITIONS_50));
//OK   testSuite.addTest(new StaticIPOSDOAccessorTest("setObjectWithESet").setWarmUp(3).setRepetitions(REPETITIONS_50));
//OK   testSuite.addTest(new StaticIPOSDOAccessorTest("getObjectByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
//OK   testSuite.addTest(new StaticIPOSDOAccessorTest("setObjectByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
//OK   testSuite.addTest(new StaticIPOSDOAccessorTest("getObjectByIndex").setWarmUp(30).setRepetitions(REPETITIONS_100));
//OK   testSuite.addTest(new StaticIPOSDOAccessorTest("setObjectByIndex").setWarmUp(40).setRepetitions(REPETITIONS_100));

//    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByGenerated").setWarmUp(3).setRepetitions(REPETITIONS_50));
//OK  testSuite.addTest(new StaticIPOSDOAccessorTest("setBigIntegerByGenerated").setWarmUp(4).setRepetitions(REPETITIONS_50));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
      testSuite.addTest(new StaticIPOSDOAccessorTest("setBigIntegerByProperty").setWarmUp(5).setRepetitions(REPETITIONS_100));
      testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(4).setRepetitions(REPETITIONS_50));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setBigIntegerByIndex").setWarmUp(2000).setRepetitions(REPETITIONS_5));
      testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(10).setRepetitions(REPETITIONS_50));

      testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
      testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(3).setRepetitions(REPETITIONS_50));
      testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(3).setRepetitions(REPETITIONS_50));
//
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getStringByName").setWarmUp(2000).setRepetitions(REPETITIONS_50));
//
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getDataObjectByProperty").setWarmUp(1000).setRepetitions(REPETITIONS_5));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setDataObjectByProperty").setWarmUp(3000).setRepetitions(REPETITIONS_50));
//
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getByGenerated").setWarmUp(3000).setRepetitions(REPETITIONS_5));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setByGenerated").setWarmUp(2000).setRepetitions(REPETITIONS_5));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
//    // TODO tune warmup for setByProperty
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setByProperty").setWarmUp(2000).setRepetitions(REPETITIONS_5));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getByIndex").setWarmUp(500).setRepetitions(REPETITIONS_5));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setByIndex").setWarmUp(1000).setRepetitions(REPETITIONS_5));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("getByPath").setWarmUp(2000).setRepetitions(REPETITIONS_50));
//    testSuite.addTest(new StaticIPOSDOAccessorTest("setByPath").setWarmUp(500).setRepetitions(REPETITIONS_5));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected void serialize()
  {
    // serialize DG so that it can be deserialized by DynamicAccessorTest
    //serializeDataGraph();
  }

  protected void setModel()
  {
    // static model
    model = StaticIPOModel.INSTANCE;
  }

  protected void initPO()
  {
    SDOFactory sdoFactoryInstance = SDOFactory.eINSTANCE;
    EDataGraph dataGraph = sdoFactoryInstance.createEDataGraph();

    DocumentRoot root = ipoFactoryInstance.createDocumentRoot();
    PurchaseOrderType po = ipoFactoryInstance.createPurchaseOrderType();
    this.po = (DataObject)po;

    USAddress shipToAddress = ipoFactoryInstance.createUSAddress();
    shipToAddress.setCity("Austin");
    shipToAddress.setName("The Address");
    shipToAddress.setState(USState.AR_LITERAL);
    shipToAddress.setStreet("24 Lakeshore Dr.");
    shipToAddress.setZip(new BigInteger("78741"));
    po.setShipTo(shipToAddress);

    // leave comment not set.
    //po.setComment(orderComment);

    po.setOrderDate(orderDate0);

    USAddress billToAddress = ipoFactoryInstance.createUSAddress();
    billToAddress.setCity("Paris");
    billToAddress.setName("One of many");
    billToAddress.setState(USState.AK_LITERAL);
    billToAddress.setStreet("411 Duplex Av.");
    billToAddress.setZip(new BigInteger("14665"));
    po.setBillTo(billToAddress);

    Items items = ipoFactoryInstance.createItems();
    po.setItems(items);
    for (int i = 0; i < NUM_ITEMS; i++)
    {
      ItemType itemElement = ipoFactoryInstance.createItemType();
      itemElement.setComment("comment " + i);
      itemElement.setPartNum("part num " + i + 10);
      itemElement.setProductName("The " + i + " name");
      itemElement.setQuantity(new BigInteger("133"));
      itemElement.setShipDate(shipDate0);
      itemElement.setUSPrice(new BigDecimal(100 + i));
      items.getItem().add(itemElement);
    }

    itemElement = (DataObject)po.getItems().getItem().get(0);

    root.setPurchaseOrder(po);
    dataGraph.setERootObject((EObject)root);
    dataGraph.setEChangeSummary(sdoFactoryInstance.createEChangeSummary());
  }

  private void serializeDataGraph()
  {
    DataGraph dataGraph = po.getDataGraph();
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

  private void initMap()
  {
    USAddress shipToAddress = ipoFactoryInstance.createUSAddress();
    shipToAddress.setCity("Austin");
    shipToAddress.setName("The Address");
    shipToAddress.setState(USState.AR_LITERAL);
    shipToAddress.setStreet("24 Lakeshore Dr.");
    shipToAddress.setZip(new BigInteger("78741"));

    USAddress billToAddress = ipoFactoryInstance.createUSAddress();
    billToAddress.setCity("Paris");
    billToAddress.setName("One of many");
    billToAddress.setState(USState.AK_LITERAL);
    billToAddress.setStreet("411 Duplex Av.");
    billToAddress.setZip(new BigInteger("14665"));

    Items items = ipoFactoryInstance.createItems();
    for (int i = 0; i < NUM_ITEMS; i++)
    {
      ItemType itemElement = ipoFactoryInstance.createItemType();
      itemElement.setComment("comment " + i);
      itemElement.setPartNum("part num " + i + 10);
      itemElement.setProductName("The " + i + " name");
      itemElement.setQuantity(new BigInteger("133"));
      itemElement.setShipDate(shipDate0);
      itemElement.setUSPrice(new BigDecimal(100 + i));
      items.getItem().add(itemElement);
    }

    hashMap.put(model.getShipToProp(), shipToAddress);
    hashMap.put(model.getBillToProp(), billToAddress);
    // leave comment not set.
    hashMap.put(model.getItemsProp(), items);
    hashMap.put(model.getOrderDateProp(), new XSDDateType().getValue("2006-02-10"));
    hashMap.put(model.getProductNameProp(), "The 0 name");
    hashMap.put(model.getQuantityProp(), new BigInteger("133"));
    hashMap.put(model.getUsPriceProp(), new BigDecimal(100));
    hashMap.put(model.getItemCommentProp(), "comment 0");
    hashMap.put(model.getShipDateProp(), new XSDDateType().getValue("2006-03-10"));
    hashMap.put(model.getPartNumProp(), "part num 0");
  }

  /**
   * Tests HashMap (not EMF) access time.
   */
  public void getFromMap()
  {
    HashMap hashMap = this.hashMap;
    Object objectValue = this.objectValue;
    Property quantityProp = model.getQuantityProp();
    Property usPriceProp = model.getUsPriceProp();

    initMap();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100K; i++)
    {
      // to use objectValue inside the loop.
      if (objectValue != this)
      { // to get a different value each time.
        objectValue = hashMap.get(quantityProp);
        objectValue = hashMap.get(usPriceProp);
      }
    }
    stopMeasuring();
  }

  /**
   * Tests HashMap (not EMF) access time.
   */
  public void setInMap()
  {
    HashMap hashMap = this.hashMap;
    Property itemCommentProp = model.getItemCommentProp();
    Property partNumProp = model.getPartNumProp();

    initMap();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_50K; i++)
    {
      hashMap.put(itemCommentProp, "comment x");
      // to alternate the feature to set.
      hashMap.put(partNumProp, "part num x");
      // to set to a new value each time.
      hashMap.put(itemCommentProp, "comment y");
      hashMap.put(partNumProp, "part num y");

    }
    stopMeasuring();
  }

  public void getBigIntegerByGenerated()
  {
    ItemType itemElement = (ItemType)this.itemElement;
    BigInteger quantityValue = this.quantityValue;
    BigInteger quantity0 = this.quantity0;

    startMeasuring();
    for (int j = 0; j < 5; j++)
    {
	    for (int i = 0; i < ITERATIONS_1_6M; i++)
	    {
	      // to use quantityValue inside the loop.
	      if (quantityValue != quantity0)
	      {
	        // TODO ideally, we'd want to call different methods (which return BigInteger).
	        quantityValue = itemElement.getQuantity();
	      }
	    }
    }
    stopMeasuring();
  }

  public void setBigIntegerByGenerated()
  {
    ItemType itemElement = (ItemType)this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_600K; i++)
    {
      itemElement.setQuantity(quantity0);
      // TODO ideally, we'd want to alternate the feature to set.
      itemElement.setQuantity(quantity1);
    }
    stopMeasuring();
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
  public void getByGenerated()
  {
    PurchaseOrderType po = (PurchaseOrderType)this.po;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_40K; i++)
    {
      if (i % 2 == 0)
      { // like get
        addressBillToValue = po.getBillTo();
        orderCommentValue = po.getComment();
        orderDateValue = po.getOrderDate();
        addressShipToValue = po.getShipTo();
      }
      else
      {
        addressBillToValue = po.getBillTo();
        orderCommentValue = po.getComment();
        orderDateValue = po.getOrderDate();
        addressShipToValue = po.getShipTo();
      }
      itemsValue = po.getItems().getItem();
      for (int j = 0; j < itemsValue.size(); j++)
      {
        itemTypeElementValue = (ItemType)itemsValue.get(j);
        productNameValue = itemTypeElementValue.getProductName();
        quantityValue = itemTypeElementValue.getQuantity();
        usPriceValue = itemTypeElementValue.getUSPrice();
        itemCommentValue = itemTypeElementValue.getComment();
        shipDateValue = itemTypeElementValue.getShipDate();
        partNumValue = itemTypeElementValue.getPartNum();
      }
    }

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
  public void setByGenerated()
  {
    PurchaseOrderType po = (PurchaseOrderType)this.po;
    USAddress newShipToAddress0 = (USAddress)this.newShipToAddress0;
    USAddress newBillToAddress0 = (USAddress)this.newBillToAddress0;
    USAddress newShipToAddress1 = (USAddress)this.newShipToAddress1;
    USAddress newBillToAddress1 = (USAddress)this.newBillToAddress1;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_10K; i++)
    {
      if (i % 2 == 0)
      { // to set to a new value each time.
        po.setBillTo(newShipToAddress0);
        po.setComment(orderComment0);
        po.setOrderDate(orderDate0);
        po.setShipTo(newBillToAddress0);
      }
      else
      {
        po.setBillTo(newShipToAddress1);
        po.setComment(orderComment1);
        po.setOrderDate(orderDate1);
        po.setShipTo(newBillToAddress1);
      }
      itemsValue = po.getItems().getItem();
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemTypeElementValue = (ItemType)itemsValue.get(j);
        itemTypeElementValue.setProductName(productName0);
        itemTypeElementValue.setQuantity(quantity0);
        itemTypeElementValue.setUSPrice(usPrice0);
        itemTypeElementValue.setComment(itemComment0);
        itemTypeElementValue.setShipDate(shipDate0);
        itemTypeElementValue.setPartNum(partNum0);
      }
    }

    stopMeasuring();
  }

}
