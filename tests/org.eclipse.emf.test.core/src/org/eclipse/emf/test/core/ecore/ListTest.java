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
 * $Id: ListTest.java,v 1.4 2008/12/13 15:58:01 emerks Exp $
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class ListTest extends TestCase
{
  private int notificationCount;
  
  public ListTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ListTest");
    testSuite.addTest(new ListTest("testRemoveAllNotUnique"));
    testSuite.addTest(new ListTest("testRemoveAllProxy"));
    testSuite.addTest(new ListTest("testDynamicModel"));
    return testSuite;
  }
  
  /*
   * bugzilla 76290
   */
  public void testRemoveAllNotUnique()
  {
    List<String> list = new DelegatingNotifyingListImpl<String>()
    {
      private static final long serialVersionUID = 1L;

      List<String> delegateList = new BasicEList<String>();
      
      @Override
      public boolean isUnique()
      {
        return false;
      }
      
      @Override
      public boolean isNotificationRequired()
      {
        return true;
      }
      
      @Override
      public void dispatchNotification(Notification notification)
      {
        // Ignore
      }

      @Override
      protected List<String> delegateList()
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
  
  public void testRemoveAllProxy()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    
    Resource resourceA= resourceSet.createResource(URI.createURI("http:///A.ecore"));
    EPackage ePackageA = EcoreFactory.eINSTANCE.createEPackage();
    EClass eClassA = EcoreFactory.eINSTANCE.createEClass();
    eClassA.setName("A");
    ePackageA.getEClassifiers().add(eClassA);
    resourceA.getContents().add(ePackageA);
    
    Resource resourceB = resourceSet.createResource(URI.createURI("http:///B.ecore"));
    EPackage ePackageB = EcoreFactory.eINSTANCE.createEPackage();
    EClass eClassB = EcoreFactory.eINSTANCE.createEClass();
    eClassB.setName("B");
    ePackageB.getEClassifiers().add(eClassB);
    resourceB.getContents().add(ePackageB);
    
    EClass eClassProxyA = EcoreFactory.eINSTANCE.createEClass();
    ((InternalEObject)eClassProxyA).eSetProxyURI(EcoreUtil.getURI(eClassA));
    
    eClassB.getESuperTypes().add(eClassProxyA);
    eClassB.getESuperTypes().remove(eClassA);
    assertEquals(true, eClassB.getESuperTypes().isEmpty());

    eClassB.getESuperTypes().add(eClassProxyA);
    eClassB.getESuperTypes().removeAll(Collections.singleton(eClassA));
    assertEquals(true, eClassB.getESuperTypes().isEmpty());
    
    EClass eClassC = EcoreFactory.eINSTANCE.createEClass(); 
    ePackageB.getEClassifiers().add(eClassC);
    eClassC.getESuperTypes().add(eClassProxyA);
    eClassC.getESuperTypes().removeAll(Collections.singleton(eClassA));
    assertEquals(true, eClassC.getESuperTypes().isEmpty());
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
    @SuppressWarnings("unchecked")
    List<EObject> johnTrips = (List<EObject>)john.eGet(trips);
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
      @Override
      public void notifyChanged(Notification msg)
      {
        switch(notificationCount++)
        {
          case 0:
          {
            assertEquals(Notification.REMOVE_MANY, msg.getEventType());
            assertEquals(trips, msg.getFeature());
            assertTrue(msg.getOldValue() instanceof List<?>);
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
