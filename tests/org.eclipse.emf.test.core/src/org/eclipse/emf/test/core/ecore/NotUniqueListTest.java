/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: NotUniqueListTest.java,v 1.3 2005/06/08 06:17:44 nickb Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.Collections;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;

public class NotUniqueListTest extends TestCase
{
  private int notificationCount;
  
  public NotUniqueListTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ListTest");
    testSuite.addTest(new NotUniqueListTest("testRemoveAll"));
    testSuite.addTest(new NotUniqueListTest("testDynamicModel"));
    return testSuite;
  }
  
  /*
   * bugzilla 76290
   */
  public void testRemoveAll()
  {
    List list = new DelegatingNotifyingListImpl()
    {
      List delegateList = new BasicEList();
      
      public boolean isUnique()
      {
        return false;
      }
      
      public boolean isNotificationRequired()
      {
        return true;
      }
      
      public void dispatchNotification(Notification notification)
      {
      }

      protected List delegateList()
      {
        return delegateList;
      }
    };      

    list.add("a");
    list.add("b");
    list.add("a");
    list.add("c");
    assertEquals(4, list.size());
    assertEquals("a", list.get(0));
    assertEquals("a", list.get(2));
    
    list.removeAll(Collections.singleton("a"));
    assertEquals(2, list.size());
    
    list.removeAll(Collections.EMPTY_LIST);
    assertEquals(2, list.size());
  }
  
  /*
   * bugzilla 76290
   */
  public void testDynamicModel()
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
        
    EClass city = EcoreFactory.eINSTANCE.createEClass();
    city.setName("City");
    pack.getEClassifiers().add(city);
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);
    final EReference trips = EcoreFactory.eINSTANCE.createEReference();
    trips.setName("trips");
    trips.setEType(city);
    trips.setContainment(false);
    trips.setUnique(false);
    trips.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(trips);
    
    final EObject ny = pack.getEFactoryInstance().create(city);
    final EObject paris = pack.getEFactoryInstance().create(city);
    final EObject rio = pack.getEFactoryInstance().create(city);
    
    EObject john = pack.getEFactoryInstance().create(person);
    List johnTrips = (List)john.eGet(trips);
    johnTrips.add(paris);
    johnTrips.add(ny);
    johnTrips.add(rio);
    johnTrips.add(paris);
    johnTrips.add(ny);
    assertEquals(5, johnTrips.size());
    assertEquals(ny, johnTrips.get(1));
    assertEquals(ny, johnTrips.get(4));
    assertEquals(paris, johnTrips.get(0));
    assertEquals(paris, johnTrips.get(3));
    
    Adapter adapter = new AdapterImpl()
    {
      public void notifyChanged(Notification msg)
      {
        switch(notificationCount++)
        {
          case 0:
          {
            assertEquals(Notification.REMOVE_MANY, msg.getEventType());
            assertEquals(trips, msg.getFeature());
            assertTrue(msg.getOldValue() instanceof List);
            assertEquals(1, msg.getPosition());
            break;
          }
          case 1:
          {
            assertEquals(Notification.REMOVE, msg.getEventType());
            assertEquals(trips, msg.getFeature());
            assertEquals(paris, msg.getOldValue());
            assertEquals(0, msg.getPosition());
            break;
          }
        }
      }
    };
    john.eAdapters().add(adapter);
    
    johnTrips.removeAll(Collections.singleton(ny));
    assertEquals(3, johnTrips.size());
    johnTrips.remove(paris);
    assertEquals(2, johnTrips.size());
    assertEquals(paris, johnTrips.get(1));
    
    assertEquals(2, notificationCount);
  }
}
