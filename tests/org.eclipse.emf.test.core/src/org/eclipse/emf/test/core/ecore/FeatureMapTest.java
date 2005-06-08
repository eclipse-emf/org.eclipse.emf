/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: FeatureMapTest.java,v 1.3 2005/06/08 06:17:44 nickb Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FeatureMapTest extends TestCase
{
  public FeatureMapTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ListTest");
    testSuite.addTest(new FeatureMapTest("testAttributesAsFeatures"));
    testSuite.addTest(new FeatureMapTest("testReferencesAsFeatures"));
    return testSuite;
  }

  public void testAttributesAsFeatures() throws Exception
  {
    EcorePackage ecorePackage = EcorePackage.eINSTANCE;
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    
    EPackage myPack = ecoreFactory.createEPackage();
    myPack.setName("mypack");
    
    EClass employeeClass = ecoreFactory.createEClass();
    myPack.getEClassifiers().add(employeeClass);
    employeeClass.setName("Employee");
    
    //   Sequence
    EAttribute ordersAttr = ecoreFactory.createEAttribute();
    employeeClass.getEStructuralFeatures().add(ordersAttr);
    ordersAttr.setName("orders");
    // set the multiplicity
    ordersAttr.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    // use the EFeatureMapEntry type
    ordersAttr.setEType(ecorePackage.getEFeatureMapEntry());
    // use the default ExtendedMetadata instance to set the feature to the desired kind
    ExtendedMetaData.INSTANCE.setFeatureKind(ordersAttr, ExtendedMetaData.GROUP_FEATURE);

    // the first derived reference
    EAttribute preferredAtt = ecoreFactory.createEAttribute();
    employeeClass.getEStructuralFeatures().add(preferredAtt);
    preferredAtt.setName("preferredOrders");
    preferredAtt.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    preferredAtt.setEType(ecorePackage.getEString());
    preferredAtt.setVolatile(true);
    preferredAtt.setTransient(true);
    preferredAtt.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(preferredAtt, ordersAttr);

    //  the second derived reference
    EAttribute standardAtt = ecoreFactory.createEAttribute();
    employeeClass.getEStructuralFeatures().add(standardAtt);
    standardAtt.setName("standardOrders");
    standardAtt.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    standardAtt.setEType(ecorePackage.getEString());
    standardAtt.setVolatile(true);
    standardAtt.setTransient(true);
    standardAtt.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(standardAtt, ordersAttr);
    
    assertTrue(FeatureMapUtil.isFeatureMap(ordersAttr));
    assertFalse(FeatureMapUtil.isFeatureMap(preferredAtt));
    assertFalse(FeatureMapUtil.isFeatureMap(standardAtt));
       
    EObject eObject = myPack.getEFactoryInstance().create(employeeClass);
    List preferredList = (List)eObject.eGet(preferredAtt);
    List standardList = (List)eObject.eGet(standardAtt);  
    
    preferredList.add("String1");
    preferredList.add("String2");
    standardList.add("String3");
    standardList.add("String4");

    assertEquals("String1", preferredList.get(0));
    assertEquals("String2", preferredList.get(1));
    assertEquals("String3", standardList.get(0));
    assertEquals("String4", standardList.get(1));
    
    FeatureMap ordersFeatureMap = (FeatureMap)eObject.eGet(ordersAttr);
    assertEquals(preferredList, ordersFeatureMap.get(preferredAtt, false));
    assertEquals(standardList, ordersFeatureMap.get(standardAtt, false));
    
    ordersFeatureMap.add(preferredAtt, "String5");
    ordersFeatureMap.add(standardAtt, "String6");
    assertEquals("String5", preferredList.get(2));
    assertEquals("String6", standardList.get(2));
  } 

  public void testReferencesAsFeatures() throws Exception
  {
    EcorePackage ecorePackage = EcorePackage.eINSTANCE;
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    
    EPackage myPack = ecoreFactory.createEPackage();
    myPack.setName("mypack");
    
    EClass employeeClass = ecoreFactory.createEClass();
    myPack.getEClassifiers().add(employeeClass);
    employeeClass.setName("Employee");

    EClass orderClass = ecoreFactory.createEClass();
    myPack.getEClassifiers().add(orderClass);
    orderClass.setName("Order");
    
    //   Sequence
    EAttribute ordersAttr = ecoreFactory.createEAttribute();
    employeeClass.getEStructuralFeatures().add(ordersAttr);
    ordersAttr.setName("orders");
    ordersAttr.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    ordersAttr.setEType(ecorePackage.getEFeatureMapEntry());
    ExtendedMetaData.INSTANCE.setFeatureKind(ordersAttr, ExtendedMetaData.GROUP_FEATURE);

    // the first derived reference
    EReference preferredRef = ecoreFactory.createEReference();
    employeeClass.getEStructuralFeatures().add(preferredRef);
    preferredRef.setName("preferredOrders");
    preferredRef.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    preferredRef.setContainment(true);
    preferredRef.setEType(orderClass);
    preferredRef.setVolatile(true);
    preferredRef.setTransient(true);
    preferredRef.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(preferredRef, ordersAttr);

    //  the second derived reference
    EReference standardRef = ecoreFactory.createEReference();
    employeeClass.getEStructuralFeatures().add(standardRef);
    standardRef.setName("standardOrders");
    standardRef.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    standardRef.setEType(orderClass);
    standardRef.setContainment(true);
    standardRef.setVolatile(true);
    standardRef.setTransient(true);
    standardRef.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(standardRef, ordersAttr);
    
    EObject eObject = myPack.getEFactoryInstance().create(employeeClass);
    List preferredList = (List)eObject.eGet(preferredRef);
    List standardList = (List)eObject.eGet(standardRef);  

    EObject order1 = myPack.getEFactoryInstance().create(orderClass);
    EObject order2 = myPack.getEFactoryInstance().create(orderClass);
    EObject order3 = myPack.getEFactoryInstance().create(orderClass);
    EObject order4 = myPack.getEFactoryInstance().create(orderClass);
    
    preferredList.add(order1);
    preferredList.add(order2);
    standardList.add(order3);
    standardList.add(order4);

    assertEquals(order1, preferredList.get(0));
    assertEquals(order2, preferredList.get(1));
    assertEquals(order3, standardList.get(0));
    assertEquals(order4, standardList.get(1));
    
    FeatureMap ordersFeatureMap = (FeatureMap)eObject.eGet(ordersAttr);
    assertEquals(preferredList, ordersFeatureMap.get(preferredRef, false));
    assertEquals(standardList, ordersFeatureMap.get(standardRef, false));
 
    EObject order5 = myPack.getEFactoryInstance().create(orderClass);
    EObject order6 = myPack.getEFactoryInstance().create(orderClass);
    
    ordersFeatureMap.add(preferredRef, order5);
    ordersFeatureMap.add(standardRef, order6);
    assertEquals(order5, preferredList.get(2));
    assertEquals(order6, standardList.get(2));
    
    assertEquals(3, preferredList.size());
    assertEquals(3, standardList.size());
    ordersFeatureMap.add(preferredRef, order3);
    assertEquals(4, preferredList.size());
    assertEquals(order3, preferredList.get(3));
    assertEquals(2, standardList.size());
    assertFalse(standardList.contains(order3));
  } 
}
