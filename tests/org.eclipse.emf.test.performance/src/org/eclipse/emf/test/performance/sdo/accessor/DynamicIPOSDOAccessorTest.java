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
 * $Id: DynamicIPOSDOAccessorTest.java,v 1.3 2005/02/18 22:35:07 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.impl.type.XSDDateType;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

import com.example.sdo.ipo.IpoFactory;
import com.example.sdo.ipo.USAddress;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class DynamicIPOSDOAccessorTest extends EMFPerformanceTestCase
{
  protected static final int REPETITIONS = 5;

  protected static final int MICRO_REPETITIONS = 10;

  protected static final int ITERATIONS = 40000;

  protected static final int MICRO_ITERATIONS = 100000;

  protected static final int WARMUP = 200;

  protected static final int MICRO_WARMUP = 3000;

  protected static final int PATH_WARMUP = 100;

  protected static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  protected static final String DATA_URI = "file:///" + DATA;

  protected static final int NUM_ITEMS = 1;

  protected IpoFactory ipoFactoryInstance = IpoFactory.eINSTANCE;

  // model

  protected Property shipToProp;

  protected Property billToProp;

  protected Property commentProp;

  protected Property itemsProp;

  protected Property itemProp;

  protected Property orderDateProp;

  protected Property productNameProp;

  protected Property quantityProp;

  protected Property usPriceProp;

  protected Property itemCommentProp;

  protected Property shipDateProp;

  protected Property partNumProp;

  protected EClass usAddressEClass;

  protected EStructuralFeature usAddressNameFeat;

  protected EStructuralFeature usAddressStreetFeat;

  protected EStructuralFeature usAddressCityFeat;

  protected EStructuralFeature usAddressStateFeat;

  protected EStructuralFeature usAddressZipFeat;

  protected ExtendedMetaData metaData;

  protected ResourceSet resourceSet;

  // the purchase order
  protected DataObject po;

  // instance data (used in the get tests)

  protected DataObject billToValue;

  protected DataObject shipToValue;

  protected String orderCommentValue;

  protected Object orderDateValue;

  protected List itemsValue;

  protected DataObject itemElementValue;

  protected String productNameValue;

  protected BigInteger quantityValue;

  protected BigDecimal usPriceValue;

  protected String itemCommentValue;

  protected Object shipDateValue;

  protected String partNumValue;

  //instance data (used in AccessorTest' set tests)

  protected DataObject newBillToAddress;

  protected DataObject newShipToAddress;

  protected Object orderDate = new XSDDateType().getValue("2006-02-10");

  protected String orderComment = "Another comment.";

  protected String productName = "The new Product.";

  protected BigInteger quantity = new BigInteger("5000");

  protected BigDecimal usPrice = new BigDecimal(4488);

  protected String itemComment = "A comment on the item";

  protected Object shipDate = new XSDDateType().getValue("2006-03-10");;

  protected String partNum = "part123456";

  public DynamicIPOSDOAccessorTest(String name)
  {
    super(name);

  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

    // the warmup number is the optimal one for consistency of results and best peformance.
    // result: 22
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(3000).setRepetitions(REPETITIONS));
    // result: 30
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(1000).setRepetitions(REPETITIONS));
    // result: 28
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(1000).setRepetitions(REPETITIONS));
    // result: 30
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(2000).setRepetitions(REPETITIONS));
    
    
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByProperty").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByIndex").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByProperty").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByIndex").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    
    
    // TODO find optimal warmup number.
    // result: 518
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(PATH_WARMUP).setRepetitions(REPETITIONS));
    // TODO find optimal warmup number.
    // result:
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(PATH_WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByPath").setWarmUp(PATH_WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByPath").setWarmUp(PATH_WARMUP).setRepetitions(REPETITIONS));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();

    tagAsSummary("Performance Results for " + getClass().getPackage().getName(), TIME_DIMENSIONS);

    // dynamic model
    resourceSet = SDOUtil.createResourceSet();
    metaData = registerModel(resourceSet);

    // load the po DG from XML.
    initPO();

    initNewValues();

    // No need for EPackage Registry clean-up since working on resourceSet.

    assertNotNull(po);
  }

  protected ExtendedMetaData registerModel(ResourceSet resourceSet)
  {
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());

    XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
    List packageList = (List)xsdEcoreBuilder.generate(URI.createURI(DATA_URI + "ipo.xsd"));
    Registry packageRegistry = resourceSet.getPackageRegistry();

    EPackage epackage = (EPackage)packageList.get(0);
    epackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
    String nsURI = epackage.getNsURI();
    packageRegistry.put(nsURI, epackage);
    assertTrue("com.example.ipo".equals(epackage.getName()));

    List classifiers = epackage.getEClassifiers();
    for (Iterator i = classifiers.iterator(); i.hasNext();)
    {
      EClassifier eClassifier = (EClassifier)i.next();
      if ("PurchaseOrderType".equals(eClassifier.getName()))
      {

        EClass eClass = (EClass)eClassifier;
        List features = eClass.getEAllStructuralFeatures();
        shipToProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(0));
        billToProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(1));
        commentProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(2));
        itemsProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(3));
        itemProp = itemsProp.getType().getProperty("item");
        orderDateProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(4));
      }
      if ("ItemType".equals(eClassifier.getName()))
      {
        EClass eClass = (EClass)eClassifier;
        List features = eClass.getEAllStructuralFeatures();
        productNameProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(0));
        quantityProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(1));
        usPriceProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(2));
        itemCommentProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(3));
        shipDateProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(4));
        partNumProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(5));
      }
      if ("USAddress".equals(eClassifier.getName()))
      {
        usAddressEClass = (EClass)eClassifier;
        List features = usAddressEClass.getEAllStructuralFeatures();
        usAddressNameFeat = (EStructuralFeature)features.get(0);
        usAddressStreetFeat = (EStructuralFeature)features.get(1);
        usAddressCityFeat = (EStructuralFeature)features.get(2);
        usAddressStateFeat = (EStructuralFeature)features.get(3);
        usAddressZipFeat = (EStructuralFeature)features.get(4);
      }

    }

    return new BasicExtendedMetaData(packageRegistry);
  }

  protected void initPO()
  {
    HashMap loadOptions = new HashMap();
    loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, metaData);

    try
    {
      String fileName = DATA + "ipoDG1.xml";
      FileInputStream inputStream = new FileInputStream(fileName);
      EDataGraph dataGraph = SDOUtil.loadDataGraph(inputStream, loadOptions);
      inputStream.close();
      DataObject root = (DataObject)dataGraph.getERootObject();
      po = root.getDataObject("purchaseOrder");
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
    address1.setName("Mr. S. D. O.");
    // leave state not set.
    //address1.setState(USState.PA_LITERAL);
    address1.setStreet("37 Jenner Way");
    address1.setZip(new BigInteger("66524"));
    newShipToAddress = (DataObject)address1;

    address1 = ipoFactoryInstance.createUSAddress();
    address1.setCity("New York City");
    address1.setName("Mr. Big Apple");
    // leave state not set
    //address1.setState(USState.AL_LITERAL);
    address1.setStreet("222 Manhattan ");
    address1.setZip(new BigInteger("12345"));
    newBillToAddress = (DataObject)address1;
  }

  public void getBigIntegerByProperty()
  {
    DataObject itemElement = (DataObject)po.getDataObject(itemsProp).getList(itemProp).get(0);
    startMeasuring();
    for (int i = 0; i < MICRO_ITERATIONS; i++)
    {
      quantityValue = itemElement.getBigInteger(quantityProp);
    }
    stopMeasuring();
  }

  public void getBigIntegerByIndex()
  {
    DataObject itemElement = (DataObject)po.getDataObject(itemsProp).getList(itemProp).get(0);
    startMeasuring();
    for (int i = 0; i < MICRO_ITERATIONS; i++)
    {
      quantityValue = itemElement.getBigInteger(1);
    }
    stopMeasuring();
  }

  // TODO is it fair to compare this to the previous two? No
  public void getBigIntegerByPath()
  {
    startMeasuring();
    for (int i = 0; i < MICRO_ITERATIONS; i++)
    {
      quantityValue = po.getBigInteger("items/item[1]/quantity");
    }
    stopMeasuring();
  }

  public void getBigDecimalByProperty()
  {
    DataObject itemElement = (DataObject)po.getDataObject(itemsProp).getList(itemProp).get(0);
    startMeasuring();
    for (int i = 0; i < MICRO_ITERATIONS; i++)
    {
      usPriceValue = itemElement.getBigDecimal(usPriceProp);
    }
    stopMeasuring();
  }

  public void getBigDecimalByIndex()
  {
    DataObject itemElement = (DataObject)po.getDataObject(itemsProp).getList(itemProp).get(0);
    startMeasuring();
    for (int i = 0; i < MICRO_ITERATIONS; i++)
    {
      usPriceValue = itemElement.getBigDecimal(2);
    }
    stopMeasuring();
  }

  public void getBigDecimalByPath()
  {
    startMeasuring();
    for (int i = 0; i < MICRO_ITERATIONS; i++)
    {
      quantityValue = po.getBigInteger("items/item[1]/uSPrice");
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
    for (int i = 0; i < ITERATIONS; i++)
    {
      shipToValue = po.getDataObject(shipToProp);
      billToValue = po.getDataObject(billToProp);
      orderCommentValue = po.getString(commentProp);

      itemsValue = po.getDataObject(itemsProp).getList(itemProp);
      for (int j = 0; j < itemsValue.size(); j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        productNameValue = itemElementValue.getString(productNameProp);
        quantityValue = itemElementValue.getBigInteger(quantityProp);
        usPriceValue = itemElementValue.getBigDecimal(usPriceProp);
        itemCommentValue = itemElementValue.getString(itemCommentProp);
        shipDateValue = itemElementValue.get(shipDateProp);
        partNumValue = itemElementValue.getString(partNumProp);
      }

      orderDateValue = po.get(orderDateProp);
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
    for (int i = 0; i < ITERATIONS; i++)
    {
      shipToValue = po.getDataObject(0);
      billToValue = po.getDataObject(1);
      orderCommentValue = po.getString(2);

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

      orderDateValue = po.get(4);
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
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public void getByPath()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS; i++)
    {
      shipToValue = po.getDataObject("shipTo");
      billToValue = po.getDataObject("billTo");
      orderCommentValue = po.getString("comment");

      productNameValue = po.getString("items/item[1]/productName");
      quantityValue = po.getBigInteger("items/item[1]/quantity");
      usPriceValue = po.getBigDecimal("items/item[1]/uSPrice");
      itemCommentValue = po.getString("items/item[1]/comment");
      shipDateValue = po.get("items/item[1]/shipDate");
      partNumValue = po.getString("items/item[1]/partNum");

      orderDateValue = po.get("orderDate");
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
    for (int i = 0; i < ITERATIONS; i++)
    {
      po.setDataObject(shipToProp, newShipToAddress);
      po.setDataObject(billToProp, newBillToAddress);
      po.setString(commentProp, orderComment);

      itemsValue = po.getDataObject(itemsProp).getList(itemProp);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        itemElementValue.setString(productNameProp, productName);
        itemElementValue.setBigInteger(quantityProp, quantity);
        itemElementValue.setBigDecimal(usPriceProp, usPrice);
        itemElementValue.setString(itemCommentProp, itemComment);
        itemElementValue.set(shipDateProp, shipDate);
        itemElementValue.setString(partNumProp, partNum);
      }
      po.set(orderDateProp, orderDate);
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
    for (int i = 0; i < ITERATIONS; i++)
    {
      // shipTo
      po.set(0, newShipToAddress);
      // billTo
      po.set(1, newBillToAddress);
      // comment
      po.set(2, orderComment);

      // items
      itemsValue = po.getDataObject(3).getList(0);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        itemElementValue.setString(0, productName);
        itemElementValue.setBigInteger(1, quantity);
        itemElementValue.setBigDecimal(2, usPrice);
        itemElementValue.setString(3, itemComment);
        itemElementValue.set(4, shipDate);
        itemElementValue.setString(5, partNum);
      }

      // date
      po.set(4, orderDate);
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
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public void setByPath()
  {
    startMeasuring();
    for (int i = 0; i < ITERATIONS; i++)
    {
      po.set("shipTo", newShipToAddress);
      po.set("billTo", newBillToAddress);
      po.set("comment", orderComment);

      po.setString("items/item[1]/productName", productName);
      po.setBigInteger("items/item[1]/quantity", quantity);
      po.setBigDecimal("items/item[1]/uSPrice", usPrice);
      po.setString("items/item[1]/comment", orderComment);
      po.set("items/item[1]/shipDate", shipDate);
      po.setString("items/item[1]/partNum", partNum);

      po.set("orderDate", orderDate);
    }
    stopMeasuring();
    initPO();
  }

}
