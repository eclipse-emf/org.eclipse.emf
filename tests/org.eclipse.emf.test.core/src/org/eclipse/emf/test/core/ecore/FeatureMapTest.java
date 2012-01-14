/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

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
    testSuite.addTest(new FeatureMapTest("testMoveElements"));
    testSuite.addTest(new FeatureMapTest("testMoveElements_WithNotification"));
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
    @SuppressWarnings("unchecked")
    List<String> preferredList = (List<String>)eObject.eGet(preferredAtt);
    @SuppressWarnings("unchecked")
    List<String> standardList = (List<String>)eObject.eGet(standardAtt);  
    
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
    @SuppressWarnings("unchecked")
    List<EObject> preferredList = (List<EObject>)eObject.eGet(preferredRef);
    @SuppressWarnings("unchecked")
    List<EObject> standardList = (List<EObject>)eObject.eGet(standardRef);  

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
  
  /*
   * Bugzilla 127109
   */
  public void testMoveElements() throws Exception
  {
    moveElementsTest(false);
  }
  
  /*
   * Bugzilla 127109
   */
  public void testMoveElements_WithNotification() throws Exception
  {
    moveElementsTest(true);
  }

  public void moveElementsTest(boolean withNotification) throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("featureMap.test.pack");
    
    EClass house = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(house);
    house.setName("Order");
    
    EClass car = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(car);
    car.setName("Car");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    person.setName("Person");

    EAttribute things = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(things);
    things.setName("things");
    things.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    things.setEType(EcorePackage.Literals.EFEATURE_MAP_ENTRY);
    ExtendedMetaData.INSTANCE.setFeatureKind(things, ExtendedMetaData.GROUP_FEATURE);
    
    EReference houses = EcoreFactory.eINSTANCE.createEReference();
    person.getEStructuralFeatures().add(houses);
    houses.setName("houses");
    houses.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    houses.setContainment(true);
    houses.setEType(house);
    houses.setVolatile(true);
    houses.setTransient(true);
    houses.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(houses, things);    

    EReference cars = EcoreFactory.eINSTANCE.createEReference();
    person.getEStructuralFeatures().add(cars);
    cars.setName("cars");
    cars.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    cars.setContainment(true);
    cars.setEType(car);
    cars.setVolatile(true);
    cars.setTransient(true);
    cars.setDerived(true);
    ExtendedMetaData.INSTANCE.setGroup(cars, things);
    
    EObject john = EcoreUtil.create(person);
    EObject house1 = EcoreUtil.create(house);
    EObject car1 = EcoreUtil.create(car);

    FeatureMap thingsFeatureMap = (FeatureMap)john.eGet(things);
    
    final List<Adapter> referenceNotificationCount = new ArrayList<Adapter>();
    final List<Adapter> attributeNotificationCount = new ArrayList<Adapter>();
    final Map<EObject, EReference> referenceByValueMap = new LinkedHashMap<EObject, EReference>();    
    if (withNotification)
    {
      final FeatureMap theFeatureMap = thingsFeatureMap;
      Adapter adapter = new AdapterImpl()
        {
          @Override
          public void notifyChanged(Notification msg)
          {
            assertEquals(referenceByValueMap.size(), theFeatureMap.size());              

            int valueCount = 0;
            int index=0;
            for (Map.Entry<?, ?> entry  : referenceByValueMap.entrySet())
            {
              assertEquals(entry.getValue(), theFeatureMap.getEStructuralFeature(index));
              assertEquals(entry.getKey(), theFeatureMap.getValue(index));

              ++index;
              if (entry.getValue() == msg.getFeature())
              {
                valueCount++;
              }
            }
                        
            if (msg.getFeature() instanceof EReference)
            {
              referenceNotificationCount.add(this);
              if (msg.getEventType() == Notification.MOVE)
              {
                assertTrue("pos: " + msg.getPosition(), msg.getPosition() >= 0);
                assertTrue("pos: " + msg.getPosition() + " vc:" + valueCount, msg.getPosition() < valueCount);
              }
            }
            else if (msg.getFeature() instanceof EAttribute)
            {
              attributeNotificationCount.add(this);
            }
          }    
        };
      john.eAdapters().add(adapter);      
    }
    
    if (withNotification) referenceByValueMap.put(house1, houses);  
    @SuppressWarnings("unchecked")
    List<EObject> johnHouses = ((List<EObject>)john.eGet(houses));
    johnHouses.add(house1);
    if (withNotification) assertEquals(1, referenceNotificationCount.size());
    if (withNotification) assertEquals(1, attributeNotificationCount.size());
    if (withNotification) referenceByValueMap.put(car1, cars);
    @SuppressWarnings("unchecked")
    List<EObject> johnCars = ((List<EObject>)john.eGet(cars));
    johnCars.add(car1);
    if (withNotification) assertEquals(2, referenceNotificationCount.size());
    if (withNotification) assertEquals(2, attributeNotificationCount.size());
        
    assertEquals(2, thingsFeatureMap.size());
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(house1, thingsFeatureMap.getValue(0));
    assertEquals(car1, thingsFeatureMap.getValue(1));
    
    // Target > Source && Different References
    if (withNotification) referenceByValueMap.clear();
    if (withNotification) referenceByValueMap.put(car1, cars);
    if (withNotification) referenceByValueMap.put(house1, houses);
    thingsFeatureMap.move(1, 0);
    if (withNotification) assertEquals(2, referenceNotificationCount.size());
    if (withNotification) assertEquals(3, attributeNotificationCount.size());
    
    assertEquals(2, thingsFeatureMap.size());
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(car1, thingsFeatureMap.getValue(0));
    assertEquals(house1, thingsFeatureMap.getValue(1));
    
    EObject car2 = EcoreUtil.create(car);
    if (withNotification) referenceByValueMap.clear();
    if (withNotification) referenceByValueMap.put(car1, cars);
    if (withNotification) referenceByValueMap.put(car2, cars);
    if (withNotification) referenceByValueMap.put(house1, houses);
    thingsFeatureMap.add(1, cars, car2);
    if (withNotification) assertEquals(3, referenceNotificationCount.size());
    if (withNotification) assertEquals(4, attributeNotificationCount.size());
    
    assertEquals(3, thingsFeatureMap.size());
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(2));
    assertEquals(car1, thingsFeatureMap.getValue(0));
    assertEquals(car2, thingsFeatureMap.getValue(1));
    assertEquals(house1, thingsFeatureMap.getValue(2));
    
    //  Target < Source && Different References
    if (withNotification) referenceByValueMap.clear();
    if (withNotification) referenceByValueMap.put(house1, houses);
    if (withNotification) referenceByValueMap.put(car1, cars);
    if (withNotification) referenceByValueMap.put(car2, cars);
    thingsFeatureMap.move(0, 2);
    if (withNotification) assertEquals(3, referenceNotificationCount.size());
    if (withNotification) assertEquals(5, attributeNotificationCount.size());
    
    assertEquals(3, thingsFeatureMap.size());
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(2));
    assertEquals(house1, thingsFeatureMap.getValue(0));
    assertEquals(car1, thingsFeatureMap.getValue(1));
    assertEquals(car2, thingsFeatureMap.getValue(2));  

    //  Target < Source && Same Reference
    if (withNotification) referenceByValueMap.clear();
    if (withNotification) referenceByValueMap.put(house1, houses);
    if (withNotification) referenceByValueMap.put(car2, cars);
    if (withNotification) referenceByValueMap.put(car1, cars);
    thingsFeatureMap.move(1, 2);
    if (withNotification) assertEquals(4, referenceNotificationCount.size());
    if (withNotification) assertEquals(6, attributeNotificationCount.size());
    
    assertEquals(3, thingsFeatureMap.size());
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(2));
    assertEquals(house1, thingsFeatureMap.getValue(0));
    assertEquals(car2, thingsFeatureMap.getValue(1));  
    assertEquals(car1, thingsFeatureMap.getValue(2));

    //  Target > Source && Same Reference
    if (withNotification) referenceByValueMap.clear();
    if (withNotification) referenceByValueMap.put(house1, houses);
    if (withNotification) referenceByValueMap.put(car1, cars);
    if (withNotification) referenceByValueMap.put(car2, cars);
    thingsFeatureMap.move(2, 1);
    if (withNotification) assertEquals(5, referenceNotificationCount.size());
    if (withNotification) assertEquals(7, attributeNotificationCount.size());
    
    assertEquals(3, thingsFeatureMap.size());
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(2));
    assertEquals(house1, thingsFeatureMap.getValue(0));
    assertEquals(car1, thingsFeatureMap.getValue(1));
    assertEquals(car2, thingsFeatureMap.getValue(2));  

    //  Target == Source
    thingsFeatureMap.move(2, 2);
    if (withNotification) assertEquals(5, referenceNotificationCount.size());
    if (withNotification) assertEquals(8, attributeNotificationCount.size());
    
    assertEquals(3, thingsFeatureMap.size());
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(2));
    assertEquals(house1, thingsFeatureMap.getValue(0));
    assertEquals(car1, thingsFeatureMap.getValue(1));
    assertEquals(car2, thingsFeatureMap.getValue(2));  

    //  Target < Source && Different References && Source Reference changes
    if (withNotification) referenceByValueMap.clear();
    if (withNotification) referenceByValueMap.put(car2, cars);
    if (withNotification) referenceByValueMap.put(house1, houses);
    if (withNotification) referenceByValueMap.put(car1, cars);
    thingsFeatureMap.move(0, 2);
    if (withNotification) assertEquals(6, referenceNotificationCount.size());
    if (withNotification) assertEquals(9, attributeNotificationCount.size());
    
    assertEquals(3, thingsFeatureMap.size());
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(0));
    assertEquals(houses, thingsFeatureMap.getEStructuralFeature(1));
    assertEquals(cars, thingsFeatureMap.getEStructuralFeature(2));
    assertEquals(car2, thingsFeatureMap.getValue(0));  
    assertEquals(house1, thingsFeatureMap.getValue(1));
    assertEquals(car1, thingsFeatureMap.getValue(2));
    
    if (withNotification)
    {
      assertFalse(referenceByValueMap.isEmpty());
      assertFalse(referenceNotificationCount.isEmpty());
      assertFalse(attributeNotificationCount.isEmpty());
    }
    else
    {
      assertTrue(referenceByValueMap.isEmpty());
      assertTrue(referenceNotificationCount.isEmpty());
      assertTrue(attributeNotificationCount.isEmpty());
    }
  }
}
