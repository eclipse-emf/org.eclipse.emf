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
 * $Id: DynamicIPOSDOAccessorTest.java,v 1.11 2005/03/08 20:41:51 bportier Exp $
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
import org.eclipse.emf.ecore.EObject;
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
  protected static final int REPETITIONS_5 = 5;

  protected static final int REPETITIONS_10 = 10;

  protected static final int ITERATIONS_300 = 300;

  protected static final int ITERATIONS_2_5K = 2500;

  protected static final int ITERATIONS_5K = 5000;

  protected static final int ITERATIONS_10K = 10000;

  protected static final int ITERATIONS_50K = 50000;

  protected static final int ITERATIONS_100K = 100000;

  protected static final int ITERATIONS_200K = 200000;

  protected static int ITERATIONS_SETDO = 2500;

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

  protected EStructuralFeature quantityFeat;

  protected EStructuralFeature usPriceFeat;

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

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectWithEGet").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectWithESet").setWarmUp(1000).setRepetitions(REPETITIONS_5));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectByProperty").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getObjectByIndex").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setObjectByIndex").setWarmUp(1000).setRepetitions(REPETITIONS_5));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByProperty").setWarmUp(3000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setBigIntegerByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByIndex").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setBigIntegerByIndex").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigIntegerByPath").setWarmUp(2000).setRepetitions(REPETITIONS_10));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByProperty").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByIndex").setWarmUp(2000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getBigDecimalByPath").setWarmUp(2000).setRepetitions(REPETITIONS_10));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getDataObjectByProperty").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setDataObjectByProperty").setWarmUp(3000).setRepetitions(REPETITIONS_5));

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByProperty").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByIndex").setWarmUp(500).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByIndex").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByPath").setWarmUp(1000).setRepetitions(REPETITIONS_5));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByPath").setWarmUp(1000).setRepetitions(REPETITIONS_10));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);
    poSetup();
    assertNotNull(po);
  }

  protected void poSetup()
  {
    // dynamic model
    resourceSet = SDOUtil.createResourceSet();
    metaData = registerModel(resourceSet);

    // load the po DG from XML.
    initPO();

    initNewValues();

    // No need for EPackage Registry clean-up since working on resourceSet.
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
        quantityFeat = (EStructuralFeature)features.get(1);
        quantityProp = SDOUtil.adaptProperty(quantityFeat);
        usPriceFeat = (EStructuralFeature)features.get(2);
        usPriceProp = SDOUtil.adaptProperty(usPriceFeat);
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
      itemElement = (DataObject)po.getDataObject(itemsProp).getList(itemProp).get(0);
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
    EStructuralFeature quantityFeat = this.quantityFeat;
    EStructuralFeature usPriceFeat = this.usPriceFeat;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100K; i++)
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
    EStructuralFeature quantityFeat = this.quantityFeat;
    EStructuralFeature usPriceFeat = this.usPriceFeat;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_50K; i++)
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
    Property quantityProp = this.quantityProp;
    Property usPriceProp = this.usPriceProp;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100K; i++)
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
    Property quantityProp = this.quantityProp;
    Property usPriceProp = this.usPriceProp;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_50K; i++)
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
    for (int i = 0; i < ITERATIONS_100K; i++)
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
    for (int i = 0; i < ITERATIONS_50K; i++)
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
    Property quantityProp = this.quantityProp;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_200K; i++)
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
    Property quantityProp = this.quantityProp;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100K; i++)
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
    for (int i = 0; i < ITERATIONS_100K; i++)
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
    for (int i = 0; i < ITERATIONS_2_5K; i++)
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
    Property usPriceProp = this.usPriceProp;

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
    for (int i = 0; i < ITERATIONS_5K; i++)
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

  public void getDataObjectByProperty()
  {
    DataObject po = this.po;
    DataObject dataObjectValue = this.dataObjectValue;
    Property shipToProp = this.shipToProp;
    Property billToProp = this.billToProp;

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
    Property shipToProp = this.shipToProp;
    Property billToProp = this.billToProp;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_SETDO; i++)
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
        shipToValue = po.getDataObject(shipToProp);
        billToValue = po.getDataObject(billToProp);
        orderCommentValue = po.getString(commentProp);
        orderDateValue = po.get(orderDateProp);
      }
      else
      {
        shipToValue = po.getDataObject(shipToProp);
        billToValue = po.getDataObject(billToProp);
        orderCommentValue = po.getString(commentProp);
        orderDateValue = po.get(orderDateProp);
      }

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
        po.setDataObject(shipToProp, newShipToAddress0);
        po.setDataObject(billToProp, newBillToAddress0);
        po.setString(commentProp, orderComment0);
        po.set(orderDateProp, orderDate0);
      }
      else
      {
        po.setDataObject(shipToProp, newShipToAddress1);
        po.setDataObject(billToProp, newBillToAddress1);
        po.setString(commentProp, orderComment1);
        po.set(orderDateProp, orderDate1);
      }

      itemsValue = po.getDataObject(itemsProp).getList(itemProp);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElementValue = (DataObject)itemsValue.get(j);
        itemElementValue.setString(productNameProp, productName0);
        itemElementValue.setBigInteger(quantityProp, quantity0);
        itemElementValue.setBigDecimal(usPriceProp, usPrice0);
        itemElementValue.setString(itemCommentProp, itemComment0);
        itemElementValue.set(shipDateProp, shipDate0);
        itemElementValue.setString(partNumProp, partNum0);
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
