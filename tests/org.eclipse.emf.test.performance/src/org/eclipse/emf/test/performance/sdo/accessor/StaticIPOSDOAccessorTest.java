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
 * $Id: StaticIPOSDOAccessorTest.java,v 1.65 2007/06/02 19:37:04 emerks Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.test.performance.sdo.StaticIPOModel;

import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;

import org.eclipse.xsd.impl.type.XSDDateType;

import com.example.ipo.Address;
import com.example.ipo.DocumentRoot;
import com.example.ipo.ItemType;
import com.example.ipo.Items;
import com.example.ipo.PurchaseOrderType;
import com.example.ipo.USAddress;
import com.example.ipo.USState;

import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


/**
 * Uses EMF and SDO APIs to access (get/set) instances of static International Purchase Order (IPO) model. 
 */
public class StaticIPOSDOAccessorTest extends DynamicIPOSDOAccessorTest
{
  protected static final int ITERATIONS_1K = 3000000;

  protected static final int ITERATIONS_1_6M = 1280000000;

  protected static final int ITERATIONS_600K = 160000000;

  protected static final int ITERATIONS_100M = 100000000;

  protected static final int ITERATIONS_50M = 50000000;

  // values for get with generated code.
  protected Address addressBillToValue;

  protected Address addressShipToValue;

  protected ItemType itemTypeElementValue;

  protected HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

  public StaticIPOSDOAccessorTest(String name)
  {
    super(name);
    ITERATIONS_150 = 1000000;
    ITERATIONS_300 = 2400000;
    ITERATIONS_20K = 80000000;
    ITERATIONS_120K = 32000000;
    ITERATIONS_2M = 4000000;
    ITERATIONS_500K = 500000;
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite();

    testSuite.addTest(new StaticIPOSDOAccessorTest("getFromMap").setWarmUp(3).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setInMap").setWarmUp(3).setRepetitions(REPETITIONS_80));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getObjectWithEGet").setWarmUp(3).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setObjectWithESet").setWarmUp(3).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getObjectByProperty").setWarmUp(3).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setObjectByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getObjectByIndex").setWarmUp(30).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setObjectByIndex").setWarmUp(40).setRepetitions(REPETITIONS_100));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByGenerated").setWarmUp(3).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setBigIntegerByGenerated").setWarmUp(4).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(5).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setBigIntegerByProperty").setWarmUp(5).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(4).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setBigIntegerByIndex").setWarmUp(3).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(10).setRepetitions(REPETITIONS_50));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(3).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(3).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(3).setRepetitions(REPETITIONS_50));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getStringByShortPath").setWarmUp(5).setRepetitions(REPETITIONS_100));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getDataObjectByProperty").setWarmUp(5).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setDataObjectByProperty").setWarmUp(8).setRepetitions(REPETITIONS_20));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getByGenerated").setWarmUp(1).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByGenerated").setWarmUp(1).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByProperty").setWarmUp(1).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByProperty").setWarmUp(1).setRepetitions(REPETITIONS_20));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByShortPath").setWarmUp(1).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByShortPath").setWarmUp(1).setRepetitions(REPETITIONS_80));

    return testSuite;
  }

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
  }

  @Override
  protected void serialize()
  {
    // serialize DG so that it can be deserialized by DynamicAccessorTest
    //serializeDataGraph();
  }

  @Override
  protected void setModel()
  {
    // static model
    model = StaticIPOModel.INSTANCE;
  }

  @Override
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
    dataGraph.setERootObject(root);
    dataGraph.setEChangeSummary(sdoFactoryInstance.createEChangeSummary());
  }

  protected void serializeDataGraph()
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
      // Ignore
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
   * Tests HashMap (not EMF) access time. To be used as a reference.
   */
  public void getFromMap()
  {
    HashMap<Object, Object> hashMap = this.hashMap;
    Object objectValue = this.objectValue;
    Property quantityProp = model.getQuantityProp();
    Property usPriceProp = model.getUsPriceProp();

    initMap();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100M; i++)
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
   * Tests HashMap (not EMF) access time. To be used as a reference.
   */
  public void setInMap()
  {
    HashMap<Object, Object> hashMap = this.hashMap;
    Property itemCommentProp = model.getItemCommentProp();
    Property partNumProp = model.getPartNumProp();

    initMap();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_50M; i++)
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

  /**
   * <p>
   * Uses the generated types to get the value of a Property of type BigInteger on a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static</li>
   * <li>access API: strongly-typed</li>
   * </ul>
   * </p>
   */
  public void getBigIntegerByGenerated()
  {
    ItemType itemElement = (ItemType)this.itemElement;
    BigInteger quantityValue = this.quantityValue;
    BigInteger quantity0 = this.quantity0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_1_6M; i++)
    {
      // to use quantityValue inside the loop.
      if (quantityValue != quantity0)
      {
        quantityValue = itemElement.getQuantity();
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the generated types to set the value of a Property of type BigInteger on a DataObject whose model has been statically generated.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static</li>
   * <li>access API: strongly-typed</li>
   * </ul>
   * </p>
   */
  public void setBigIntegerByGenerated()
  {
    ItemType itemElement = (ItemType)this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_600K; i++)
    {
      itemElement.setQuantity(quantity0);
      itemElement.setQuantity(quantity1);
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the generated types to get the values of properties of a DataObject whose model has been statically generated.
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
    Address addressValue = this.addressBillToValue;
    String stringValue = this.orderCommentValue;
    Object objectValue = this.orderDateValue;
    List<?> itemsValue = this.itemsValue;
    ItemType itemTypeElementValue = this.itemTypeElementValue;
    BigInteger bigIntegerValue = this.quantityValue;
    BigDecimal bigDecimalValue = this.usPriceValue;
    Object object = this.object;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_50M; i++)
    {
      if ((addressValue != this) && (stringValue != object) && (objectValue != this) && (addressValue != this))
      {
        addressValue = po.getBillTo();
        stringValue = po.getComment();
        objectValue = po.getOrderDate();
        itemsValue = po.getItems().getItem();
        addressValue = po.getShipTo();
        for (int j = 0; j < itemsValue.size(); j++)
        {
          if ((stringValue != object) && (bigIntegerValue != object) && (bigDecimalValue != object) && (objectValue != this))
          {
            itemTypeElementValue = (ItemType)itemsValue.get(j);
            stringValue = itemTypeElementValue.getProductName();
            bigIntegerValue = itemTypeElementValue.getQuantity();
            bigDecimalValue = itemTypeElementValue.getUSPrice();
            stringValue = itemTypeElementValue.getComment();
            objectValue = itemTypeElementValue.getShipDate();
            stringValue = itemTypeElementValue.getPartNum();
          }
        }
      }
    }

    stopMeasuring();
  }

  /**
   * <p>
   * Uses the generated types to get the values of properties of a DataObject whose model has been statically generated.
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
    String orderComment0 = this.orderComment0;
    String orderComment1 = this.orderComment1;
    XMLGregorianCalendar orderDate0 = this.orderDate0;
    XMLGregorianCalendar orderDate1 = this.orderDate1;
    String productName0 = this.productName0;
    String productName1 = this.productName1;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    BigDecimal usPrice0 = this.usPrice0;
    BigDecimal usPrice1 = this.usPrice1;
    String itemComment0 = this.itemComment0;
    String itemComment1 = this.itemComment1;
    XMLGregorianCalendar shipDate0 = this.shipDate0;
    XMLGregorianCalendar shipDate1 = this.shipDate1;
    String partNum0 = this.partNum0;
    String partNum1 = this.partNum1;
    int NUM_ITEMS = DynamicIPOSDOAccessorTest.NUM_ITEMS;
    List<?> itemsValue = this.itemsValue;
    ItemType itemTypeElementValue = this.itemTypeElementValue;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_1K; i++)
    {
      po.setBillTo(newShipToAddress0);
      po.setBillTo(newShipToAddress1);

      po.setComment(orderComment0);
      po.setComment(orderComment1);

      po.setOrderDate(orderDate0);
      po.setOrderDate(orderDate1);

      po.setShipTo(newBillToAddress0);
      po.setShipTo(newBillToAddress1);

      itemsValue = po.getItems().getItem();
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemTypeElementValue = (ItemType)itemsValue.get(j);

        itemTypeElementValue.setProductName(productName0);
        itemTypeElementValue.setProductName(productName1);

        itemTypeElementValue.setQuantity(quantity0);
        itemTypeElementValue.setQuantity(quantity1);

        itemTypeElementValue.setUSPrice(usPrice0);
        itemTypeElementValue.setUSPrice(usPrice1);

        itemTypeElementValue.setComment(itemComment0);
        itemTypeElementValue.setComment(itemComment1);

        itemTypeElementValue.setShipDate(shipDate0);
        itemTypeElementValue.setShipDate(shipDate1);

        itemTypeElementValue.setPartNum(partNum0);
        itemTypeElementValue.setPartNum(partNum1);
      }
    }

    stopMeasuring();
  }

}
