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
 * $Id: StaticIPOSDOAccessorTest.java,v 1.5 2005/02/22 16:13:34 marcelop Exp $
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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import com.example.sdo.ipo.Address;
import com.example.sdo.ipo.DocumentRoot;
import com.example.sdo.ipo.IpoPackage;
import com.example.sdo.ipo.ItemType;
import com.example.sdo.ipo.Items;
import com.example.sdo.ipo.PurchaseOrderType;
import com.example.sdo.ipo.USAddress;
import com.example.sdo.ipo.USState;
import com.example.sdo.ipo.util.IpoResourceFactoryImpl;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class StaticIPOSDOAccessorTest extends DynamicIPOSDOAccessorTest
{
  // values for get with generated code.
  protected Address addressBillToValue;

  protected Address addressShipToValue;

  protected ItemType itemTypeElementValue;

  public StaticIPOSDOAccessorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite();

    // the warmup number is the optimal one for consistency of results and best peformance.

    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(2000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(2000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(500).setRepetitions(10));
    
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(2000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(2000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(500).setRepetitions(10));

    testSuite.addTest(new StaticIPOSDOAccessorTest("getByGenerated").setWarmUp(3000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByProperty").setWarmUp(200).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByIndex").setWarmUp(200).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("getByPath").setWarmUp(3000).setRepetitions(10));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByGenerated").setWarmUp(3000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByProperty").setWarmUp(200).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByIndex").setWarmUp(200).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticIPOSDOAccessorTest("setByPath").setWarmUp(3000).setRepetitions(5));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();

    tagAsSummary("Performance Results for " + getClass().getPackage().getName(), TIME_DIMENSIONS);

    initPO();

    // serialize DG so that it can be deserialized by DynamicAccessorTest
    //serializeDataGraph();

    assertNotNull(po);

    initModel();
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

    po.setOrderDate(orderDate);

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
      // leave quantity unset.
      //item.setQuantity(new BigInteger("133"));
      itemElement.setShipDate(shipDate);
      itemElement.setUSPrice(new BigDecimal(100 + i));
      items.getItem().add(itemElement);
    }

    root.setPurchaseOrder(po);
    dataGraph.setERootObject((EObject)root);
    dataGraph.setEChangeSummary(sdoFactoryInstance.createEChangeSummary());
  }

  private void initModel()
  {
    List properties = po.getType().getProperties();
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
  }

  protected ExtendedMetaData registerModel(ResourceSet resourceSet)
  {
    IpoPackage ipoPackageInstance = IpoPackage.eINSTANCE;
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
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
  public final void getByGenerated()
  {
    PurchaseOrderType po = (PurchaseOrderType)this.po;
    startMeasuring();

    for (int i = 0; i < ITERATIONS; i++)
    {
      addressBillToValue = po.getBillTo();
      orderCommentValue = po.getComment();
      orderDateValue = po.getOrderDate();
      addressShipToValue = po.getShipTo();

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
  public final void setByGenerated()
  {
    PurchaseOrderType po = (PurchaseOrderType)this.po;
    USAddress newShipToAddress = (USAddress)this.newShipToAddress;
    USAddress newBillToAddress = (USAddress)this.newBillToAddress;
    startMeasuring();

    for (int i = 0; i < ITERATIONS; i++)
    {
      po.setBillTo(newShipToAddress);
      po.setComment(orderComment);
      po.setOrderDate(orderDate);
      po.setShipTo(newBillToAddress);

      itemsValue = po.getItems().getItem();
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemTypeElementValue = (ItemType)itemsValue.get(j);
        itemTypeElementValue.setProductName(productName);
        itemTypeElementValue.setQuantity(quantity);
        itemTypeElementValue.setUSPrice(usPrice);
        itemTypeElementValue.setComment(itemComment);
        itemTypeElementValue.setShipDate(shipDate);
        itemTypeElementValue.setPartNum(partNum);
      }
    }

    stopMeasuring();
  }

}
