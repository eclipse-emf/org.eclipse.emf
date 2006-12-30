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
 * $Id: DynamicSequenceTest.java,v 1.5 2006/12/30 03:44:08 marcelop Exp $
 */
package org.eclipse.emf.test.sdo;


import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import commonj.sdo.DataObject;
import commonj.sdo.Sequence;


public class DynamicSequenceTest extends TestCase
{

  public DynamicSequenceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicSequenceTest");
    ts.addTest(new DynamicSequenceTest("testDynamicSequence"));
    return ts;
  }

  /*
   * @see TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    if (epoPackage == null)
    {
      epoPackage = getEPOPackage();
    }
    super.setUp();
  }

  /*
   * @see TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    super.tearDown();
  }

  /**
   * An example of using the SDO Sequence API.
   */
  public void testDynamicSequence()
  {
    // shows the dynamic SDO API. The dynamic EMF API could be used too.
    DataObject supplierDO = (DataObject)createSupplier();
    // orders is an SDO Sequence
    Sequence orders = supplierDO.getSequence("orders");
    assertTrue(orders.size() == 1);
    assertTrue(supplierName.equals(supplierDO.getString("name")));
    assertTrue(orders.getValue(0) != null);
    assertTrue(preferredRef.getName().equals(orders.getProperty(0).getName()));
    assertTrue(supplierDO.getList("preferredOrders").get(0) != null);
    // throws IndexOutOfBoundsException because there is no standard order:
    // System.out.println("Standard Order #1: " +
    // supplierDO.getList("standardOrders").get(0));

    // adding a standard order
    // (createOrder(String) is a convenience method that returns an
    // E/DataObject whose eClass is poClass)
    orders.add("standardOrders", createOrder("a new comment"));
    assertTrue(orders.size() == 2);
    // this time no IndexOutOfBoundsException
    assertTrue(supplierDO.getList("standardOrders").get(0) != null);
  }

  static EClass supplierClass;

  static EAttribute nameAttr;

  static EAttribute ordersAttr;

  static EClass poClass;

  static EAttribute commentAttr;

  static EReference preferredRef;

  static EReference standardRef;

  static EPackage epoPackage;

  static String supplierName = "Mr. Supplier";

  public static EObject createOrder(String comment)
  {
    EFactory epoFactory = epoPackage.getEFactoryInstance();
    EObject order = epoFactory.create(poClass);
    order.eSet(commentAttr, comment);
    return order;
  }

  public static EObject createSupplier()
  {
    EFactory epoFactory = epoPackage.getEFactoryInstance();
    EObject supplier = epoFactory.create(supplierClass);
    supplier.eSet(nameAttr, supplierName);
    EObject order1 = epoFactory.create(poClass);
    order1.eSet(commentAttr, "A very interesting comment...");

    @SuppressWarnings("unchecked") List<EObject> preferredSupplier = (List<EObject>)supplier.eGet(preferredRef);
    preferredSupplier.add(order1);

    return supplier;
  }

  /**
   * An example of modeling EMF FeatureMaps, using the EMF dynamic and ExtendedMetadata APIs.
   */
  public static EPackage getEPOPackage()
  {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EcorePackage ecorePackage = EcorePackage.eINSTANCE;

    EPackage epoPackage = ecoreFactory.createEPackage();
    epoPackage.setName("epo");
    epoPackage.setNsPrefix("epo");
    epoPackage.setNsURI("http://www.example.com/ExtendedPO");

    EDataType dateDataType = ecoreFactory.createEDataType();
    epoPackage.getEClassifiers().add(dateDataType);
    dateDataType.setName("Date");
    dateDataType.setInstanceClass(Date.class);

    poClass = ecoreFactory.createEClass();
    epoPackage.getEClassifiers().add(poClass);
    poClass.setName("PurchaseOrder");

    commentAttr = ecoreFactory.createEAttribute();
    poClass.getEStructuralFeatures().add(commentAttr);
    commentAttr.setName("comment");
    commentAttr.setEType(ecorePackage.getEString());

    EAttribute orderDateAttr = ecoreFactory.createEAttribute();
    poClass.getEStructuralFeatures().add(orderDateAttr);
    orderDateAttr.setName("orderDate");
    orderDateAttr.setEType(dateDataType);

    supplierClass = ecoreFactory.createEClass();
    epoPackage.getEClassifiers().add(supplierClass);
    supplierClass.setName("Supplier");

    nameAttr = ecoreFactory.createEAttribute();
    supplierClass.getEStructuralFeatures().add(nameAttr);
    nameAttr.setName("name");
    nameAttr.setEType(ecorePackage.getEString());

    ordersAttr = ecoreFactory.createEAttribute();
    supplierClass.getEStructuralFeatures().add(ordersAttr);
    ordersAttr.setName("orders");
    // set the multiplicity
    ordersAttr.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    // use the EFeatureMapEntry type
    ordersAttr.setEType(ecorePackage.getEFeatureMapEntry());
    // use the default ExtendedMetadata instance to set the feature to the
    // desired kind
    ExtendedMetaData.INSTANCE.setFeatureKind(ordersAttr, ExtendedMetaData.GROUP_FEATURE);

    // the first derived reference
    preferredRef = ecoreFactory.createEReference();
    supplierClass.getEStructuralFeatures().add(preferredRef);
    preferredRef.setName("preferredOrders");
    preferredRef.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    // set the type to the po eClass.
    preferredRef.setEType(poClass);
    // make the reference volatile, transient, and derived
    preferredRef.setVolatile(true);
    preferredRef.setTransient(true);
    preferredRef.setDerived(true);
    // make the reference delegate to the ordersAttr group feature.
    ExtendedMetaData.INSTANCE.setGroup(preferredRef, ordersAttr);

    // the second derived reference
    standardRef = ecoreFactory.createEReference();
    supplierClass.getEStructuralFeatures().add(standardRef);
    standardRef.setName("standardOrders");
    standardRef.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    standardRef.setEType(poClass);
    standardRef.setVolatile(true);
    standardRef.setTransient(true);
    standardRef.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(standardRef, ordersAttr);

    epoPackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
    return epoPackage;
  }

}
