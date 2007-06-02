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
 * $Id: DynamicIPOSDOAccessorTest.java,v 1.65 2007/06/02 19:37:04 emerks Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.emf.test.performance.sdo.DynamicIPOModel;
import org.eclipse.emf.test.performance.sdo.IPOModel;

import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import org.eclipse.xsd.impl.type.XSDDateType;

import com.example.ipo.IpoFactory;
import com.example.ipo.USAddress;

import commonj.sdo.DataObject;
import commonj.sdo.Property;


/**
 * Uses EMF and SDO APIs to access (get/set) instances of dynamic International Purchase Order (IPO) model. 
 */
public class DynamicIPOSDOAccessorTest extends EMFPerformanceTestCase
{
  protected static final int REPETITIONS_20 = 20;

  protected static final int REPETITIONS_50 = 50;

  protected static final int REPETITIONS_80 = 80;

  protected static final int REPETITIONS_100 = 100;

  protected static final int REPETITIONS_200 = 200;

  protected static int ITERATIONS_150 = 700000;

  protected static int ITERATIONS_300 = 1200000;

  protected static final int ITERATIONS_5K = 1400000;

  protected static final int ITERATIONS_10K = 2500000;

  protected static final int ITERATIONS_12M = 2000000;

  protected static final int ITERATIONS_100K = 40000000;

  protected static int ITERATIONS_20K = 40000000;

  protected static final int ITERATIONS_200K = 40000000;

  protected static int ITERATIONS_500K = 200000;

  protected static final int ITERATIONS_4M = 8000000;

  protected static final int ITERATIONS_20M = 20000000;

  protected static final int ITERATIONS_1_2M = 32000000;

  protected static final int ITERATIONS_12M1 = 6000000;

  protected static final int ITERATIONS_400K = 80000000;

  protected static final int ITERATIONS_300K = 20000000;

  protected static final int ITERATIONS_80M = 8000000;

  protected static int ITERATIONS_120K = 16000000;

  protected static final int ITERATIONS_8M = 60000000;

  protected static final int ITERATIONS_80K = 15000000;

  protected static int ITERATIONS_2M = 2000000;

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

  protected List<?> itemsValue;

  protected DataObject itemsValue1;

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

  protected XMLGregorianCalendar orderDate0 = (XMLGregorianCalendar)new XSDDateType().getValue("2006-02-10");

  protected XMLGregorianCalendar orderDate1 = (XMLGregorianCalendar)new XSDDateType().getValue("2006-02-11");

  protected String orderComment0 = "Another comment0.";

  protected String orderComment1 = "Another comment1.";

  protected String productName0 = "The new Product0.";

  protected String productName1 = "The new Product1.";

  protected BigInteger quantity0 = new BigInteger("3210");

  protected BigInteger quantity1 = new BigInteger("3211");

  protected BigDecimal usPrice0 = new BigDecimal(4480);

  protected BigDecimal usPrice1 = new BigDecimal(4481);

  protected String itemComment0 = "A comment0 on the item";

  protected String itemComment1 = "A comment1 on the item";

  protected XMLGregorianCalendar shipDate0 = (XMLGregorianCalendar)new XSDDateType().getValue("2006-03-10");

  protected XMLGregorianCalendar shipDate1 = (XMLGregorianCalendar)new XSDDateType().getValue("2006-03-11");

  protected String partNum0 = "part1234560";

  protected String partNum1 = "part1234561";

  protected final Object object = new Object();

  public DynamicIPOSDOAccessorTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectWithEGet").setWarmUp(3).setRepetitions(REPETITIONS_50));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectWithESet").setWarmUp(6).setRepetitions(REPETITIONS_100));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectByProperty").setWarmUp(3).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectByProperty").setWarmUp(6).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectByIndex").setWarmUp(30).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectByIndex").setWarmUp(20).setRepetitions(REPETITIONS_100));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(3).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setBigIntegerByProperty").setWarmUp(5).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(4).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setBigIntegerByIndex").setWarmUp(3).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(10).setRepetitions(REPETITIONS_50));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(3).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(3).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(3).setRepetitions(REPETITIONS_50));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getStringByShortPath").setWarmUp(12).setRepetitions(REPETITIONS_80));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getDataObjectByProperty").setWarmUp(8).setRepetitions(REPETITIONS_100));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setDataObjectByProperty").setWarmUp(15).setRepetitions(REPETITIONS_200));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByProperty").setWarmUp(1).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByProperty").setWarmUp(1).setRepetitions(REPETITIONS_20));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByShortPath").setWarmUp(1).setRepetitions(REPETITIONS_80));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByShortPath").setWarmUp(1).setRepetitions(REPETITIONS_80));

    return testSuite;
  }

  @Override
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
    HashMap<String, Object> loadOptions = new HashMap<String, Object>();
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
      // Ignore
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

  /**
   * <p>
   * Uses the EMF reflective API to get the value of a property of type Object from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: Object</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective with eGet</li>
   * </ul>
   * </p>
   */
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

  /**
   * <p>
   * Uses the EMF reflective API to set the value of a property of type Object from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: Object</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective with eSet</li>
   * </ul>
   * </p>
   */
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
    for (int i = 0; i < ITERATIONS_12M1; i++)
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

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type Object from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: Object</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
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

  /**
   * <p>
   * Uses the SDO reflective API to set the value of a property of type Object from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: Object</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
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
    for (int i = 0; i < ITERATIONS_80M; i++)
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

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type Object from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: Object</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
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

  /**
   * <p>
   * Uses the SDO reflective API to set the value of a property of type Object from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: Object</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
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

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type BigInteger from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
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
        quantityValue = itemElement.getBigInteger(quantityProp);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the value of a property of type BigInteger from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
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
      itemElement.setBigInteger(quantityProp, quantity1);
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type BigInteger from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
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
        quantityValue = itemElement.getBigInteger(1);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the value of a property of type BigInteger from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public void setBigIntegerByIndex()
  {
    DataObject itemElement = this.itemElement;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_8M; i++)
    {
      itemElement.setBigInteger(1, quantity0);
      itemElement.setBigInteger(1, quantity1);
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type BigInteger from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigInteger</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
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

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type BigDecimal from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigDecimal</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
  public void getBigDecimalByProperty()
  {
    DataObject itemElement = this.itemElement;
    BigDecimal usPriceValue = this.usPriceValue;
    BigDecimal usPrice0 = this.usPrice0;
    Property usPriceProp = model.getUsPriceProp();

    startMeasuring();
    for (int i = 0; i < ITERATIONS_20K; i++)
    {
      // to use usPriceValue inside the loop.
      if (usPriceValue != usPrice0)
      {
        usPriceValue = itemElement.getBigDecimal(usPriceProp);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type BigDecimal from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigDecimal</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public void getBigDecimalByIndex()
  {
    DataObject itemElement = this.itemElement;
    BigDecimal usPriceValue = this.usPriceValue;
    BigDecimal usPrice0 = this.usPrice0;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_20K; i++)
    {
      // to use usPriceValue inside the loop.
      if (usPriceValue != usPrice0)
      {
        usPriceValue = itemElement.getBigDecimal(2);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type BigDecimal from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: BigDecimal</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
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
        usPriceValue = po.getBigDecimal("items/item[1]/uSPrice");
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type String from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: String</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by short path</li>
   * </ul>
   * </p>
   */
  public void getStringByShortPath()
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

  /**
   * <p>
   * Uses the SDO reflective API to get the value of a property of type DataObject from a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>type: DataObject</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
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

  /**
   * <p>
   * Uses the SDO reflective API to set the value of a property of type DataObject from a DataObject whose model has been statically or dynamically created.
   * Note: billTo and shipTo are containment references. 
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>type: DataObject</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
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
   * Uses the SDO reflective API to get the values of properties of a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
  public void getByProperty()
  {
    DataObject po = this.po;
    // values
    DataObject shipToValue = this.shipToValue;
    DataObject billToValue = this.billToValue;
    String orderCommentValue = this.orderCommentValue;
    String productNameValue = this.productNameValue;
    String itemCommentValue = this.itemCommentValue;
    String partNumValue = this.partNumValue;
    Object orderDateValue = this.orderDateValue;
    List<?> itemsValue = this.itemsValue;
    Object shipDateValue = this.shipDateValue;
    DataObject itemElementValue = this.itemElementValue;
    BigInteger quantityValue = this.quantityValue;
    BigDecimal usPriceValue = this.usPriceValue;
    Object object = this.object;
    // model
    Property shipToProp = model.getShipToProp();
    Property billToProp = model.getBillToProp();
    Property commentProp = model.getCommentProp();
    Property orderDateProp = model.getOrderDateProp();
    Property itemsProp = model.getItemsProp();
    Property itemProp = model.getItemProp();
    Property productNameProp = model.getProductNameProp();
    Property quantityProp = model.getQuantityProp();
    Property usPriceProp = model.getUsPriceProp();
    Property itemCommentProp = model.getItemCommentProp();
    Property shipDateProp = model.getShipDateProp();
    Property partNumProp = model.getPartNumProp();
    startMeasuring();

    for (int i = 0; i < ITERATIONS_2M; i++)
    {
      if ((shipToValue != this) && (billToValue != this) && (orderCommentValue != object) && (orderDateValue != this)
        && (itemsValue != this))
      {
        shipToValue = po.getDataObject(shipToProp);
        billToValue = po.getDataObject(billToProp);
        orderCommentValue = po.getString(commentProp);
        orderDateValue = po.get(orderDateProp);
        itemsValue = po.getDataObject(itemsProp).getList(itemProp);
        for (int j = 0; j < itemsValue.size(); j++)
        {
          if ((itemElementValue != this) && (productNameValue != object) && (quantityValue != object) && (usPriceValue != object)
            && (itemCommentValue != object) && (shipDateValue != this) && (partNumValue != object))
          {
            itemElementValue = (DataObject)itemsValue.get(j);
            productNameValue = itemElementValue.getString(productNameProp);
            quantityValue = itemElementValue.getBigInteger(quantityProp);
            usPriceValue = itemElementValue.getBigDecimal(usPriceProp);
            itemCommentValue = itemElementValue.getString(itemCommentProp);
            shipDateValue = itemElementValue.get(shipDateProp);
            partNumValue = itemElementValue.getString(partNumProp);
          }
        }
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of properties of a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by property</li>
   * </ul>
   * </p>
   */
  public void setByProperty()
  {
    DataObject po = this.po;
    // new values
    DataObject newShipToAddress0 = this.newShipToAddress0;
    DataObject newShipToAddress1 = this.newShipToAddress1;
    DataObject newBillToAddress0 = this.newBillToAddress0;
    DataObject newBillToAddress1 = this.newBillToAddress1;
    String orderComment0 = this.orderComment0;
    String orderComment1 = this.orderComment1;
    Object orderDate0 = this.orderDate0;
    Object orderDate1 = this.orderDate1;
    List<?> itemsValue = this.itemsValue;
    DataObject itemElementValue = this.itemElementValue;
    String productName0 = this.productName0;
    String productName1 = this.productName1;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    BigDecimal usPrice0 = this.usPrice0;
    BigDecimal usPrice1 = this.usPrice1;
    String itemComment0 = this.itemComment0;
    String itemComment1 = this.itemComment1;
    Object shipDate0 = this.shipDate0;
    Object shipDate1 = this.shipDate1;
    String partNum0 = this.partNum0;
    String partNum1 = this.partNum1;
    // model
    Property shipToProp = model.getShipToProp();
    Property billToProp = model.getBillToProp();
    Property commentProp = model.getCommentProp();
    Property orderDateProp = model.getOrderDateProp();
    Property itemsProp = model.getItemsProp();
    Property itemProp = model.getItemProp();
    Property productNameProp = model.getProductNameProp();
    Property quantityProp = model.getQuantityProp();
    Property usPriceProp = model.getUsPriceProp();
    Property itemCommentProp = model.getItemCommentProp();
    Property shipDateProp = model.getShipDateProp();
    Property partNumProp = model.getPartNumProp();

    int NUM_ITEMS = DynamicIPOSDOAccessorTest.NUM_ITEMS;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_500K; i++)
    {
      po.setDataObject(shipToProp, newShipToAddress0);
      po.setDataObject(shipToProp, newShipToAddress1);

      po.setDataObject(billToProp, newBillToAddress0);
      po.setDataObject(billToProp, newBillToAddress1);

      po.setString(commentProp, orderComment0);
      po.setString(commentProp, orderComment1);

      po.set(orderDateProp, orderDate0);
      po.set(orderDateProp, orderDate1);

      itemsValue = po.getDataObject(itemsProp).getList(itemProp);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);

        itemElementValue.setString(productNameProp, productName0);
        itemElementValue.setString(productNameProp, productName1);

        itemElementValue.setBigInteger(quantityProp, quantity0);
        itemElementValue.setBigInteger(quantityProp, quantity1);

        itemElementValue.setBigDecimal(usPriceProp, usPrice0);
        itemElementValue.setBigDecimal(usPriceProp, usPrice1);

        itemElementValue.setString(itemCommentProp, itemComment0);
        itemElementValue.setString(itemCommentProp, itemComment1);

        itemElementValue.set(shipDateProp, shipDate0);
        itemElementValue.set(shipDateProp, shipDate1);

        itemElementValue.setString(partNumProp, partNum0);
        itemElementValue.setString(partNumProp, partNum1);
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of properties of a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by short path</li>
   * </ul>
   * </p>
   */
  public void getByShortPath()
  {
    DataObject po = this.po;
    // values
    DataObject shipToValue = this.shipToValue;
    DataObject billToValue = this.billToValue;
    String orderCommentValue = this.orderCommentValue;
    String productNameValue = this.productNameValue;
    String itemCommentValue = this.itemCommentValue;
    String partNumValue = this.partNumValue;
    Object orderDateValue = this.orderDateValue;
    DataObject itemsValue1 = this.itemsValue1;
    Object shipDateValue = this.shipDateValue;
    DataObject itemElementValue = this.itemElementValue;
    BigInteger quantityValue = this.quantityValue;
    BigDecimal usPriceValue = this.usPriceValue;
    Object object = this.object;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_300; i++)
    {
      if ((shipToValue != this) && (billToValue != this) && (orderCommentValue != object) && (orderDateValue != this)
        && (itemsValue1 != this) && (itemElementValue != this) && (productNameValue != object) && (quantityValue != object)
        && (usPriceValue != object) && (itemCommentValue != object) && (shipDateValue != this) && (partNumValue != object))
      {
        shipToValue = po.getDataObject("shipTo");
        billToValue = po.getDataObject("billTo");
        orderCommentValue = po.getString("comment");
        orderDateValue = po.get("orderDate");
        itemsValue1 = po.getDataObject("items");
        itemElementValue = itemsValue1.getDataObject("item[1]");
        productNameValue = itemElementValue.getString("productName");
        quantityValue = itemElementValue.getBigInteger("quantity");
        usPriceValue = itemElementValue.getBigDecimal("uSPrice");
        itemCommentValue = itemElementValue.getString("comment");
        shipDateValue = itemElementValue.get("shipDate");
        partNumValue = itemElementValue.getString("partNum");
      }
    }
    stopMeasuring();
  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of properties of a DataObject whose model has been statically or dynamically created.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>model generation: static/dynamic</li>
   * <li>access API: reflective by short path</li>
   * </ul>
   * </p>
   */
  public void setByShortPath()
  {
    DataObject po = this.po;
    DataObject newShipToAddress0 = this.newShipToAddress0;
    DataObject newShipToAddress1 = this.newShipToAddress1;
    DataObject newBillToAddress0 = this.newBillToAddress0;
    DataObject newBillToAddress1 = this.newBillToAddress1;
    String orderComment0 = this.orderComment0;
    String orderComment1 = this.orderComment1;
    Object orderDate0 = this.orderDate0;
    Object orderDate1 = this.orderDate1;
    DataObject itemsValue1 = this.itemsValue1;
    DataObject itemElementValue = this.itemElementValue;
    String productName0 = this.productName0;
    String productName1 = this.productName1;
    BigInteger quantity0 = this.quantity0;
    BigInteger quantity1 = this.quantity1;
    BigDecimal usPrice0 = this.usPrice0;
    BigDecimal usPrice1 = this.usPrice1;
    String itemComment0 = this.itemComment0;
    String itemComment1 = this.itemComment1;
    Object shipDate0 = this.shipDate0;
    Object shipDate1 = this.shipDate1;
    String partNum0 = this.partNum0;
    String partNum1 = this.partNum1;
    startMeasuring();

    for (int i = 0; i < ITERATIONS_150; i++)
    {
      po.setDataObject("shipTo", newShipToAddress0);
      po.setDataObject("shipTo", newShipToAddress1);

      po.setDataObject("billTo", newBillToAddress0);
      po.setDataObject("billTo", newBillToAddress1);

      po.setString("comment", orderComment0);
      po.setString("comment", orderComment1);

      po.set("orderDate", orderDate0);
      po.set("orderDate", orderDate1);

      itemsValue1 = po.getDataObject("items");
      itemElementValue = itemsValue1.getDataObject("item[1]");

      itemElementValue.setString("productName", productName0);
      itemElementValue.setString("productName", productName1);

      itemElementValue.setBigInteger("quantity", quantity0);
      itemElementValue.setBigInteger("quantity", quantity1);

      itemElementValue.setBigDecimal("uSPrice", usPrice0);
      itemElementValue.setBigDecimal("uSPrice", usPrice1);

      itemElementValue.setString("comment", itemComment0);
      itemElementValue.setString("comment", itemComment1);

      itemElementValue.set("shipDate", shipDate0);
      itemElementValue.set("shipDate", shipDate1);

      itemElementValue.setString("partNum", partNum0);
      itemElementValue.setString("partNum", partNum1);
    }
    stopMeasuring();
  }

}
