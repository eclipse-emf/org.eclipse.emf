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
 * $Id: DynamicIPOSDOAccessorTest.java,v 1.1 2005/02/16 23:02:12 bportier Exp $
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.impl.type.XSDDateType;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class DynamicIPOSDOAccessorTest extends EMFPerformanceTestCase
{
  private static final int REPETITIONS = 5;

  private static final int ITERATIONS = 40000;

  private static final int WARMUP = 200;

  private static final int PATH_WARMUP = 100;

  private static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  private static final String DATA_URI = "file:///" + DATA;

  private IPOSDOAccessorTest accessorTest;

  // dynamic model

  private Property shipToProp;

  private Property billToProp;

  private Property commentProp;

  private Property itemsProp;

  private Property itemProp;

  private Property dateProp;

  private Property productNameProp;

  private Property quantityProp;

  private Property usPriceProp;

  private Property itemCommentProp;

  private Property shipDateProp;

  private Property partNumProp;

  private EClass usAddressEClass;

  private EStructuralFeature usAddressNameFeat;

  private EStructuralFeature usAddressStreetFeat;

  private EStructuralFeature usAddressCityFeat;

  private EStructuralFeature usAddressStateFeat;

  private EStructuralFeature usAddressZipFeat;

  private ExtendedMetaData metaData;

  private ResourceSet resourceSet;

  // the dynamic purchase order
  private DataObject po;

  //instance data (used in AccessorTest' set tests)

  private EObject billToAddress;

  private EObject shipToAddress;

  private Object orderDate = new XSDDateType().getValue("2006-02-10");

  private String orderComment = "Another comment.";

  private String productName = "The new Product.";

  private BigInteger quantity = new BigInteger("5000");

  private BigDecimal usPrice = new BigDecimal(4488);

  private String itemComment = "A comment on the item";

  private Object shipDate = new XSDDateType().getValue("2006-03-10");;

  private String partNum = "part123456";

  public DynamicIPOSDOAccessorTest(String name)
  {
    super(name);

  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByProperty").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByIndex").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("getByPath").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByProperty").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
    testSuite.addTest(new DynamicIPOSDOAccessorTest("setByIndex").setWarmUp(WARMUP).setRepetitions(REPETITIONS));
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

    accessorTest = new IPOSDOAccessorTest(
      ITERATIONS,
      shipToProp,
      billToProp,
      commentProp,
      itemsProp,
      itemProp,
      dateProp,
      productNameProp,
      quantityProp,
      usPriceProp,
      itemCommentProp,
      shipDateProp,
      partNumProp);

    accessorTest.initInstanceData(
      (DataObject)billToAddress,
      (DataObject)shipToAddress,
      orderComment,
      orderDate,
      productName,
      quantity,
      usPrice,
      itemComment,
      shipDate,
      partNum);

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
        dateProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(4));
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

  private void initPO()
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

      // create new addresses used in set methods.
      shipToAddress = EcoreUtil.create(usAddressEClass);
      shipToAddress.eSet(usAddressCityFeat, "Toronto");
      shipToAddress.eSet(usAddressNameFeat, "not the GTA one.");
      shipToAddress.eSet(usAddressStreetFeat, "37 Jenner Way");
      shipToAddress.eSet(usAddressZipFeat, new BigInteger("66524"));

      billToAddress = EcoreUtil.create(usAddressEClass);
      billToAddress.eSet(usAddressCityFeat, "New York City");
      billToAddress.eSet(usAddressNameFeat, "Mr. Big Apple");
      billToAddress.eSet(usAddressStreetFeat, "222 Manhattan ");
      shipToAddress.eSet(usAddressZipFeat, new BigInteger("12345"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
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
    accessorTest.getByProperty(po);
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
    accessorTest.getByIndex(po);
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
    accessorTest.getByPath(po);
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
    accessorTest.setByProperty(po);
    stopMeasuring();
    initPO();
  }

  // difference with not calling accesorTest:
  // calling accessorTest: 142ms
  // doing all below: 150ms
  /*public void setByProperty()
   {
   startMeasuring();
   
   for (int i = 0; i < ITERATIONS; i++)
   {
   po.set(shipToProp, shipToAddress);
   po.set(billToProp, billToAddress);
   po.set(commentProp, comment);

   List items = (List)((DataObject)po.get(itemsProp)).get(itemProp);
   for (int j = 0; j < 20; j++)
   {
   items.add(item);
   }

   po.set(dateProp, date);
   }
   
   stopMeasuring();
   initPO();
   }*/

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
    accessorTest.setByIndex(po);
    stopMeasuring();
    initPO();
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
    accessorTest.setByPath(po);
    stopMeasuring();
    initPO();
  }

}
