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
 * $Id: DynamicIPOSDOAccessorTest.java,v 1.36 2005/05/02 19:38:32 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.emf.test.performance.sdo.DynamicIPOModel;
import org.eclipse.emf.test.performance.sdo.IPOModel;
import org.eclipse.xsd.impl.type.XSDDateType;

import com.example.sdo.ipo.IpoFactory;
import com.example.sdo.ipo.USAddress;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class DynamicIPOSDOAccessorTest extends EMFPerformanceTestCase
{
  protected static final int REPETITIONS_5 = 5;

  protected static final int REPETITIONS_50 = 50;

  protected static final int REPETITIONS_100 = 100;

  protected static final int REPETITIONS_500 = 500;

  protected static final int ITERATIONS_300 = 240000;

  protected static final int ITERATIONS_2_5K = 2000000;

  protected static final int ITERATIONS_5K = 1400000;

  protected static final int ITERATIONS_10K = 2500000;

  protected static final int ITERATIONS_12M = 200000;

  protected static final int ITERATIONS_50K = 25000000;

  protected static final int ITERATIONS_100K = 40000000;

  protected static final int ITERATIONS_200K = 40000000;

  protected static final int ITERATIONS_4M = 4000000;  

  protected static final int ITERATIONS_20M = 20000000;

  protected static final int ITERATIONS_1_2M = 320000000;

  protected static final int ITERATIONS_500K = 120000000;

  protected static final int ITERATIONS_400K = 80000000;

  protected static final int ITERATIONS_300K = 80000000;

  protected static final int ITERATIONS_120K = 1600000;

  protected static final int ITERATIONS_8M = 8000000;

  protected static final int ITERATIONS_80K = 32000000;

  protected static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  protected static final int NUM_ITEMS = 1;

  protected IpoFactory ipoFactoryInstance = IpoFactory.eINSTANCE;

  // model
  protected static IPOModel model;

  // the purchase order
  protected DataObject po;

  protected DataObject itemElement;

  // instance data (used in the get tests)

  protected DataObject billToValue;

  protected DataObject shipToValue;

  protected String orderCommentValue;

  protected Object orderDateValue;

  protected List itemsValue;

  protected DataObject itemElementValue;

  protected String productNameValue;

  protected BigInteger quantityValue;

  protected Object objectValue;

  protected DataObject dataObjectValue;

  protected BigInteger bigIntegerValue;

  protected BigDecimal usPriceValue;

  protected String itemCommentValue;

  protected Object shipDateValue;

  protected String partNumValue;

  protected String stringValue;

  //instance data (used in AccessorTest' set tests)

  protected DataObject newBillToAddress0;

  protected DataObject newBillToAddress1;

  protected DataObject newShipToAddress0;

  protected DataObject newShipToAddress1;

  protected Object orderDate0 = new XSDDateType().getValue("2006-02-10");

  protected Object orderDate1 = new XSDDateType().getValue("2006-02-11");

  protected String orderComment0 = "Another comment0.";

  protected String orderComment1 = "Another comment1.";

  protected String productName0 = "The new Product0.";

  protected BigInteger quantity0 = new BigInteger("3210");

  protected BigInteger quantity1 = new BigInteger("3211");

  protected BigDecimal usPrice0 = new BigDecimal(4480);

  protected BigDecimal usPrice1 = new BigDecimal(4481);

  protected String itemComment0 = "A comment0 on the item";

  protected Object shipDate0 = new XSDDateType().getValue("2006-03-10");

  protected String partNum0 = "part1234560";

  public DynamicIPOSDOAccessorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectWithEGet").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectWithESet").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    //
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectByProperty").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectByIndex").setWarmUp(500).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectByIndex").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    //
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
          testSuite.addTest(new DynamicIPOSDOAccessorTest("setBigIntegerByProperty").setWarmUp(50).setRepetitions(REPETITIONS_100));
    //OK  testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(4).setRepetitions(REPETITIONS_50));
          testSuite.addTest(new DynamicIPOSDOAccessorTest("setBigIntegerByIndex").setWarmUp(30).setRepetitions(REPETITIONS_100));
    //OK      testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(10).setRepetitions(REPETITIONS_50));

    //OK  testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(3).setRepetitions(REPETITIONS_50));
    //OK  testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(3).setRepetitions(REPETITIONS_50));
    //OK  testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(3).setRepetitions(REPETITIONS_50));

    //  testSuite.addTest(new DynamicIPOSDOAccessorTest("getStringByName").setWarmUp(12).setRepetitions(REPETITIONS_50));
    //
    //  testSuite.addTest(new DynamicIPOSDOAccessorTest("getDataObjectByProperty").setWarmUp(8).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setDataObjectByProperty").setWarmUp(150).setRepetitions(REPETITIONS_500));

    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByIndex").setWarmUp(500).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByIndex").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByPath").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    //    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByPath").setWarmUp(1000).setRepetitions(REPETITIONS_50));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);

    // metadata
    setModel();

    // instance
    initPO();
    assertNotNull(po);

    serialize();

    initNewValues();
  }

  protected void serialize()
  {
    // do nothing.
  }

  protected void setModel()
  {
    // dynamic model
    model = DynamicIPOModel.INSTANCE;
  }

  protected void initPO()
  {
    HashMap loadOptions = new HashMap();
    loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, model.getMetaData());

    try
    {
      String fileName = DATA + "ipoDG1.xml";
      FileInputStream inputStream = new FileInputStream(fileName);
      EDataGraph dataGraph = SDOUtil.loadDataGraph(inputStream, loadOptions);
      inputStream.close();
      DataObject root = (DataObject)dataGraph.getERootObject();
      po = root.getDataObject("purchaseOrder");
      itemElement = (DataObject)po.getDataObject(model.getItemsProp()).getList(model.getItemProp()).get(0);
    }
    catch (IOException e)
    {
    }
  }

  protected void initNewValues()
  {
    // create new addresses used in set methods.
    // because ipo only has uni-directional references, the DO values to set to can be static DOs. (no difference with dynamic DOs since uni-directional).

    USAddress address1 = ipoFactoryInstance.createUSAddress();
    address1.setCity("Toronto");
    address1.setName("Mr. S. D. O. 0");
    // leave state not set.
    //address1.setState(USState.PA_LITERAL);
    address1.setStreet("37 Jenner Way");
    address1.setZip(new BigInteger("66524"));
    newShipToAddress0 = (DataObject)address1;

    address1 = ipoFactoryInstance.createUSAddress();
    address1.setCity("Toronto");
    address1.setName("Mr. S. D. O. 1");
    // leave state not set.
    //address1.setState(USState.PA_LITERAL);
    address1.setStreet("37 Jenner Way");
    address1.setZip(new BigInteger("66524"));
    newShipToAddress1 = (DataObject)address1;

    address1 = ipoFactoryInstance.createUSAddress();
    address1.setCity("New York City");
    address1.setName("Mr. Big Apple 0");
    // leave state not set
    //address1.setState(USState.AL_LITERAL);
    address1.setStreet("222 Manhattan ");
    address1.setZip(new BigInteger("12345"));
    newBillToAddress0 = (DataObject)address1;

    address1 = ipoFactoryInstance.createUSAddress();
    address1.setCity("New York City");
    address1.setName("Mr. Big Apple 1");
    // leave state not set
    //address1.setState(USState.AL_LITERAL);
    address1.setStreet("222 Manhattan ");
    address1.setZip(new BigInteger("12345"));
    newBillToAddress1 = (DataObject)address1;
  }

  public void getObjectWithEGet()
  {
    EObject itemElement = (EObject)this.itemElement;
    Object objectValue = this.objectValue;
    EStructuralFeature quantityFeat = model.getQuantityFeat();
    EStructuralFeature usPriceFeat = model.getUsPriceFeat();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_1_2M; i++)
    {
      // to use objectValue inside the loop.
      if (objectValue != this)
      {
        objectValue = itemElement.eGet(quantityFeat);
        objectValue = itemElement.eGet(usPriceFeat);
      }
    }
    stopMeasuring();
  }

  public void setObjectWithESet()
  {
    EObject itemElement = (EObject)this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    BigDecimal usPrice0 = this.usPrice0;
    BigDecimal usPrice1 = this.usPrice1;
    EStructuralFeature quantityFeat = model.getQuantityFeat();
    EStructuralFeature usPriceFeat = model.getUsPriceFeat();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_500K; i++)
    {
      itemElement.eSet(quantityFeat, quantity0);
      // to alternate the feature to set.
      itemElement.eSet(usPriceFeat, usPrice0);
      // to set to a new value each time.
      itemElement.eSet(quantityFeat, quantity1);
      itemElement.eSet(usPriceFeat, usPrice1);
    }
    stopMeasuring();
  }

  public void getObjectByProperty()
  {
    DataObject itemElement = this.itemElement;
    Object objectValue = this.objectValue;
    Property quantityProp = model.getQuantityProp();
    Property usPriceProp = model.getUsPriceProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_300K; i++)
    {
      // to use objectValue inside the loop.
      if (objectValue != this)
      {
        objectValue = itemElement.get(quantityProp);
        objectValue = itemElement.get(usPriceProp);
      }
    }
    stopMeasuring();
  }

  public void setObjectByProperty()
  {
    DataObject itemElement = this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    BigDecimal usPrice0 = this.usPrice0;
    BigDecimal usPrice1 = this.usPrice1;
    Property quantityProp = model.getQuantityProp();
    Property usPriceProp = model.getUsPriceProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_300K; i++)
    {
      itemElement.set(quantityProp, quantity0);
      // to alternate the feature to set.
      itemElement.set(usPriceProp, usPrice0);
      // to set to a new value each time.
      itemElement.set(quantityProp, quantity1);
      itemElement.set(usPriceProp, usPrice1);
    }
    stopMeasuring();
  }

  public void getObjectByIndex()
  {
    DataObject itemElement = this.itemElement;
    Object objectValue = this.objectValue;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_20M; i++)
    {
      // to use objectValue inside the loop.
      if (objectValue != this)
      {
        objectValue = itemElement.get(1);
        objectValue = itemElement.get(2);
      }
    }
    stopMeasuring();
  }

  public void setObjectByIndex()
  {
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    BigDecimal usPrice0 = this.usPrice0;
    BigDecimal usPrice1 = this.usPrice1;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_4M; i++)
    {
      itemElement.set(1, quantity0);
      // to alternate the feature to set.
      itemElement.set(2, usPrice0);
      // to set to a new value each time.
      itemElement.set(1, quantity1);
      itemElement.set(2, usPrice1);
    }
    stopMeasuring();
  }

  public void getBigIntegerByProperty()
  {
    DataObject itemElement = this.itemElement;
    BigInteger quantityValue = this.quantityValue;
    BigInteger quantity0 = this.quantity0;
    Property quantityProp = model.getQuantityProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_400K; i++)
    {
      // to use quantityValue inside the loop.
      if (quantityValue != quantity0)
      {
        // TODO ideally, we'd want to call getBigInteger for different features.
        quantityValue = itemElement.getBigInteger(quantityProp);
      }
    }
    stopMeasuring();
  }

  public void setBigIntegerByProperty()
  {
    DataObject itemElement = this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    Property quantityProp = model.getQuantityProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_120K; i++)
    {
      itemElement.setBigInteger(quantityProp, quantity0);
      // TODO ideally, we'd want to alternate the feature to set.
      itemElement.setBigInteger(quantityProp, quantity1);
    }
    stopMeasuring();
  }

  public void getBigIntegerByIndex()
  {
    DataObject itemElement = this.itemElement;
    BigInteger quantityValue = this.quantityValue;
    BigInteger quantity0 = this.quantity0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_200K; i++)
    {
      // to use quantityValue inside the loop.
      if (quantityValue != quantity0)
      {
        // TODO ideally, we'd want to call getBigInteger for different features.
        quantityValue = itemElement.getBigInteger(1);
      }
    }
    stopMeasuring();
  }

  public void setBigIntegerByIndex()
  {
    DataObject itemElement = this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_8M; i++)
    {
      itemElement.setBigInteger(1, quantity0);
      // TODO ideally, we'd want to alternate the feature to set.
      itemElement.setBigInteger(1, quantity1);
    }
    stopMeasuring();
  }

  public void getBigIntegerByPath()
  {
    DataObject po = this.po;
    BigInteger bigIntegerValue = this.bigIntegerValue;
    BigInteger quantity0 = this.quantity0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_5K; i++)
    {
      // to use quantityValue inside the loop.
      if (bigIntegerValue != quantity0)
      {
        bigIntegerValue = po.getBigInteger("items/item[1]/quantity");
        bigIntegerValue = po.getBigInteger("billTo/zip");
      }
    }
    stopMeasuring();
  }

  public void getBigDecimalByProperty()
  {
    DataObject itemElement = this.itemElement;
    BigDecimal usPriceValue = this.usPriceValue;
    BigDecimal usPrice0 = this.usPrice0;
    Property usPriceProp = model.getUsPriceProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_200K; i++)
    {
      // to use usPriceValue inside the loop.
      if (usPriceValue != usPrice0)
      {
        // TODO ideally, we'd want to call getBigDecimal for different features.
        usPriceValue = itemElement.getBigDecimal(usPriceProp);
      }
    }
    stopMeasuring();
  }

  public void getBigDecimalByIndex()
  {
    DataObject itemElement = this.itemElement;
    BigDecimal usPriceValue = this.usPriceValue;
    BigDecimal usPrice0 = this.usPrice0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_200K; i++)
    {
      // to use usPriceValue inside the loop.
      if (usPriceValue != usPrice0)
      {
        // TODO ideally, we'd want to call getBigDecimal for different features.
        usPriceValue = itemElement.getBigDecimal(2);
      }
    }
    stopMeasuring();
  }

  public void getBigDecimalByPath()
  {
    DataObject po = this.po;
    BigDecimal usPriceValue = this.usPriceValue;
    BigDecimal usPrice0 = this.usPrice0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_10K; i++)
    {
      // to use usPriceValue inside the loop.
      if (usPriceValue != usPrice0)
      {
        // TODO ideally, we'd want to call getBigDecimal for different features.
        usPriceValue = po.getBigDecimal("items/item[1]/uSPrice");
      }
    }
    stopMeasuring();
  }

  public void getStringByName()
  {
    DataObject itemElement = this.itemElement;
    String stringValue = this.stringValue;
    String partNum0 = this.partNum0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_80K; i++)
    {
      // to use stringValue inside the loop.
      if (stringValue != partNum0)
      {
        stringValue = itemElement.getString("productName");
        stringValue = itemElement.getString("comment");
      }
    }
    stopMeasuring();
  }

  public void getDataObjectByProperty()
  {
    DataObject po = this.po;
    DataObject dataObjectValue = this.dataObjectValue;
    Property shipToProp = model.getShipToProp();
    Property billToProp = model.getBillToProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100K; i++)
    {
      // to use dataObjectValue inside the loop.
      if (dataObjectValue != this)
      {
        dataObjectValue = po.getDataObject(shipToProp);
        dataObjectValue = po.getDataObject(billToProp);
      }
    }
    stopMeasuring();
  }

  public void setDataObjectByProperty()
  {
    DataObject po = this.po;
    DataObject newShipToAddress0 = this.newShipToAddress0;
    DataObject newShipToAddress1 = this.newShipToAddress1;
    DataObject newBillToAddress0 = this.newBillToAddress0;
    DataObject newBillToAddress1 = this.newBillToAddress1;
    Property shipToProp = model.getShipToProp();
    Property billToProp = model.getBillToProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_12M; i++)
    {
      po.setDataObject(shipToProp, newShipToAddress0);
      // to alternate the feature to set.
      po.setDataObject(billToProp, newBillToAddress0);
      // to set to a new value each time.
      po.setDataObject(shipToProp, newShipToAddress1);
      po.setDataObject(billToProp, newBillToAddress1);
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: dynamic</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
  public void getByProperty()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS_10K; i++)
    {
      if (i % 2 == 0)
      { // like set.
        shipToValue = po.getDataObject(model.getShipToProp());
        billToValue = po.getDataObject(model.getBillToProp());
        orderCommentValue = po.getString(model.getCommentProp());
        orderDateValue = po.get(model.getOrderDateProp());
      }
      else
      {
        shipToValue = po.getDataObject(model.getShipToProp());
        billToValue = po.getDataObject(model.getBillToProp());
        orderCommentValue = po.getString(model.getCommentProp());
        orderDateValue = po.get(model.getOrderDateProp());
      }

      itemsValue = po.getDataObject(model.getItemsProp()).getList(model.getItemProp());
      for (int j = 0; j < itemsValue.size(); j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        productNameValue = itemElementValue.getString(model.getProductNameProp());
        quantityValue = itemElementValue.getBigInteger(model.getQuantityProp());
        usPriceValue = itemElementValue.getBigDecimal(model.getUsPriceProp());
        itemCommentValue = itemElementValue.getString(model.getItemCommentProp());
        shipDateValue = itemElementValue.get(model.getShipDateProp());
        partNumValue = itemElementValue.getString(model.getPartNumProp());
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of a DataObject whose model has been dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: dynamic</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
  public void setByProperty()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS_5K; i++)
    {
      if (i % 2 == 0)
      { // to set to a new value each time.
        po.setDataObject(model.getShipToProp(), newShipToAddress0);
        po.setDataObject(model.getBillToProp(), newBillToAddress0);
        po.setString(model.getCommentProp(), orderComment0);
        po.set(model.getOrderDateProp(), orderDate0);
      }
      else
      {
        po.setDataObject(model.getShipToProp(), newShipToAddress1);
        po.setDataObject(model.getBillToProp(), newBillToAddress1);
        po.setString(model.getCommentProp(), orderComment1);
        po.set(model.getOrderDateProp(), orderDate1);
      }

      itemsValue = po.getDataObject(model.getItemsProp()).getList(model.getItemProp());
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        itemElementValue.setString(model.getProductNameProp(), productName0);
        itemElementValue.setBigInteger(model.getQuantityProp(), quantity0);
        itemElementValue.setBigDecimal(model.getUsPriceProp(), usPrice0);
        itemElementValue.setString(model.getItemCommentProp(), itemComment0);
        itemElementValue.set(model.getShipDateProp(), shipDate0);
        itemElementValue.setString(model.getPartNumProp(), partNum0);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public void getByIndex()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS_10K; i++)
    {
      if (i % 2 == 0)
      { // like set.
        shipToValue = po.getDataObject(0);
        billToValue = po.getDataObject(1);
        orderCommentValue = po.getString(2);
        orderDateValue = po.get(4);
      }
      else
      {
        shipToValue = po.getDataObject(0);
        billToValue = po.getDataObject(1);
        orderCommentValue = po.getString(2);
        orderDateValue = po.get(4);
      }

      itemsValue = po.getDataObject(3).getList(0);
      for (int j = 0; j < itemsValue.size(); j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        productNameValue = itemElementValue.getString(0);
        quantityValue = itemElementValue.getBigInteger(1);
        usPriceValue = itemElementValue.getBigDecimal(2);
        itemCommentValue = itemElementValue.getString(3);
        shipDateValue = itemElementValue.get(4);
        partNumValue = itemElementValue.getString(5);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of a DataObject whose model has been dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public void setByIndex()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS_5K; i++)
    {
      if (i % 2 == 0)
      { // to set to a new value each time.
        po.setDataObject(0, newShipToAddress0);
        po.setDataObject(1, newBillToAddress0);
        po.set(2, orderComment0);
        po.set(4, orderDate0);
      }
      else
      {
        po.setDataObject(0, newShipToAddress1);
        po.setDataObject(1, newBillToAddress1);
        po.set(2, orderComment1);
        po.set(4, orderDate1);
      }

      // items
      itemsValue = po.getDataObject(3).getList(0);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        itemElementValue.setString(0, productName0);
        itemElementValue.setBigInteger(1, quantity0);
        itemElementValue.setBigDecimal(2, usPrice0);
        itemElementValue.setString(3, itemComment0);
        itemElementValue.set(4, shipDate0);
        itemElementValue.setString(5, partNum0);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject whose model has been dynamically created.
   * Not comparable to other non-path accesor tests because less iterations.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: dynamic</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public void getByPath()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS_300; i++)
    {
      if (i % 2 == 0)
      { // like set.
        shipToValue = po.getDataObject("shipTo");
        billToValue = po.getDataObject("billTo");
        orderCommentValue = po.getString("comment");
        orderDateValue = po.get("orderDate");
      }
      else
      {
        shipToValue = po.getDataObject("shipTo");
        billToValue = po.getDataObject("billTo");
        orderCommentValue = po.getString("comment");
        orderDateValue = po.get("orderDate");
      }

      productNameValue = po.getString("items/item[1]/productName");
      quantityValue = po.getBigInteger("items/item[1]/quantity");
      usPriceValue = po.getBigDecimal("items/item[1]/uSPrice");
      itemCommentValue = po.getString("items/item[1]/comment");
      shipDateValue = po.get("items/item[1]/shipDate");
      partNumValue = po.getString("items/item[1]/partNum");
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of a DataObject whose model has been dynamically created.
   * Not comparable to other non-path accesor tests because less iterations.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: dynamic</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public void setByPath()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS_300; i++)
    {
      if (i % 2 == 0)
      { // to set to a new value each time.
        po.setDataObject("shipTo", newShipToAddress0);
        po.setDataObject("billTo", newBillToAddress0);
        po.set("comment", orderComment0);
        po.set("orderDate", orderDate0);
      }
      else
      {
        po.setDataObject("shipTo", newShipToAddress1);
        po.setDataObject("billTo", newBillToAddress1);
        po.set("comment", orderComment1);
        po.set("orderDate", orderDate1);
      }

      po.setString("items/item[1]/productName", productName0);
      po.setBigInteger("items/item[1]/quantity", quantity0);
      po.setBigDecimal("items/item[1]/uSPrice", usPrice0);
      po.setString("items/item[1]/comment", orderComment0);
      po.set("items/item[1]/shipDate", shipDate0);
      po.setString("items/item[1]/partNum", partNum0);
    }
    stopMeasuring();
    initPO();
  }

}
