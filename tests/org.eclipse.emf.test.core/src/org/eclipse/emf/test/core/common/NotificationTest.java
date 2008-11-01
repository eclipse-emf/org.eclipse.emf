/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: NotificationTest.java,v 1.4 2008/11/01 10:43:26 emerks Exp $
 */
package org.eclipse.emf.test.core.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

/**
 * @since 2.2.1
 */
public class NotificationTest extends TestCase
{
  public NotificationTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("NotificationTest");
    ts.addTest(new NotificationTest("testMergeRemoveNotifications"));
    ts.addTest(new NotificationTest("testNotify"));
    return ts;
  }
  
  public void testMergeRemoveNotifications() throws Exception
  {
    Resource resource = new ResourceImpl();
    resource.getContents().add(EcoreFactory.eINSTANCE.createEAnnotation());    //0
    resource.getContents().add(EcoreFactory.eINSTANCE.createEAttribute());     //1
    resource.getContents().add(EcoreFactory.eINSTANCE.createEClass());         //2
    resource.getContents().add(EcoreFactory.eINSTANCE.createEDataType());      //3
    resource.getContents().add(EcoreFactory.eINSTANCE.createEEnum());          //4
    resource.getContents().add(EcoreFactory.eINSTANCE.createEEnumLiteral());   //5
    resource.getContents().add(EcoreFactory.eINSTANCE.createEFactory());       //6
    resource.getContents().add(EcoreFactory.eINSTANCE.createEObject());        //7
    resource.getContents().add(EcoreFactory.eINSTANCE.createEOperation());     //8
    resource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());       //9
    resource.getContents().add(EcoreFactory.eINSTANCE.createEParameter());     //10
    
    List<EObject> initialContents = new ArrayList<EObject>(resource.getContents());
    
    class MyAdapter extends AdapterImpl
    {
      public NotificationImpl mergedNotification;
      
      @Override
      public void notifyChanged(Notification msg)
      {
        if (mergedNotification == null)
        {
          mergedNotification = (NotificationImpl)msg;
        }
        else
        {
          mergedNotification.add(msg);
        }
      }
    }
    
    MyAdapter myAdapter = new MyAdapter();
    resource.eAdapters().add(myAdapter);

    List<EObject> removedObjects = new ArrayList<EObject>();
    
    // Remove all, one by one
    for (EObject item : initialContents)
    {
      if (resource.getContents().remove(item))
      {
        removedObjects.add(item);
      }
    }
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);
    
    // *** Reset
    removedObjects.clear();
    resource.getContents().clear();
    resource.getContents().addAll(initialContents);
    myAdapter.mergedNotification = null;
    
    // Remove odd items
    int count = 0;
    for (EObject item : initialContents)
    {
      if ((count % 2) != 0)
      {
        if (resource.getContents().remove(item))
        {
          removedObjects.add(item);
        }
      }
      ++count;
    }
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);    

    // *** Reset
    removedObjects.clear();
    resource.getContents().clear();
    resource.getContents().addAll(initialContents);
    myAdapter.mergedNotification = null;
    
    // Remove even items
    count = 0;
    for (EObject item : initialContents)
    {
      if ((count % 2) == 0)
      {
        if (resource.getContents().remove(item))
        {
          removedObjects.add(item);
        }
      }
      ++count;
    }
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);    

    // *** Reset
    removedObjects.clear();
    resource.getContents().clear();
    resource.getContents().addAll(initialContents);
    myAdapter.mergedNotification = null;
    
    // Remove items in the middle
    removedObjects.add(resource.getContents().remove(6));
    removedObjects.add(resource.getContents().remove(4));
    removedObjects.add(resource.getContents().remove(8));
    removedObjects.add(resource.getContents().remove(3));
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);    

    // *** Reset
    removedObjects.clear();
    resource.getContents().clear();
    resource.getContents().addAll(initialContents);
    myAdapter.mergedNotification = null;
    
    // Remove items from the ends
    removedObjects.add(resource.getContents().remove(resource.getContents().size()-1));
    removedObjects.add(resource.getContents().remove(0));
    removedObjects.add(resource.getContents().remove(0));
    removedObjects.add(resource.getContents().remove(resource.getContents().size()-1));
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);    

    // *** Reset
    removedObjects.clear();
    resource.getContents().clear();
    resource.getContents().addAll(initialContents);
    myAdapter.mergedNotification = null;
    
    // RemoveAll
    removedObjects.add(initialContents.get(0));
    removedObjects.add(initialContents.get(4));
    removedObjects.add(initialContents.get(10));
    removedObjects.add(initialContents.get(1));
    removedObjects.add(initialContents.get(8));
    resource.getContents().removeAll(removedObjects);
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);    
  }

  private void removeNotificationMergeCheck(List<?> removedItems, List<?> initialContents, NotificationImpl mergedNotification)
  {
    assertEquals(Notification.REMOVE_MANY, mergedNotification.getEventType());
    
    List<?> oldValue = (List<?>)mergedNotification.getOldValue();
    int[] newValue = (int[])mergedNotification.getNewValue();
    
    assertEquals(removedItems.size(), oldValue.size());
    assertEquals(removedItems.size(), newValue.length);
    
    for (int i=0; i < removedItems.size(); i++)
    {
      assertTrue(removedItems.contains(oldValue.get(i)) );
      assertEquals(oldValue.get(i), initialContents.get(newValue[i]));
    }
  }

  /**
   * @since 2.5
   */
  public void testNotify()
  {
    EClass c = EcoreFactory.eINSTANCE.createEClass();
    EAttribute a1 = createEAttribute("a1");
    EAttribute a2 = createEAttribute("a2");
    EAttribute a3 = createEAttribute("a3");
    EAttribute a4 = createEAttribute("a4");
    EAttribute a5 = createEAttribute("a5");
    EAttribute a6 = createEAttribute("a6");

    EStructuralFeature eStructuralFeatures = EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES;
    int eStructuralFeaturesInt = EcorePackage.ECLASS__ESTRUCTURAL_FEATURES;
    EStructuralFeature name = EcorePackage.Literals.ENAMED_ELEMENT__NAME;
    int nameInt = EcorePackage.ENAMED_ELEMENT__NAME;
    EStructuralFeature upperBound = EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND;
    int upperBoundInt = EcorePackage.ETYPED_ELEMENT__UPPER_BOUND;
    
    NotificationResult[] expected = new NotificationResult[]
      {
        new NotificationResult(c, Notification.ADD, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, null, a1, false, false, false, 0),
        new NotificationResult(c, Notification.ADD_MANY, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, null, Arrays.asList(a2, a3, a4, a5), true, false, false, 1),
        new NotificationResult(c, Notification.REMOVE, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, a2, null, true, false, false, 1),
        new NotificationResult(c, Notification.REMOVE_MANY, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, Arrays.asList(a3, a4), new int[] { 1, 2 }, true, false, false, 1),
        new NotificationResult(c, Notification.MOVE, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, new Integer(1), a5, true, false, false, 0),
        new NotificationResult(c, Notification.MOVE, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, new Integer(0), a5, true, true, false, 0),
        new NotificationResult(c, Notification.SET, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, a5, a2, true, false, false, 0),
        new NotificationResult(c, Notification.SET, EClass.class, eStructuralFeaturesInt, eStructuralFeatures, a2, a2, true, true, false, 0),
        new NotificationResult(a1, Notification.SET, EAttribute.class, nameInt, name, "a1", "a1", true, true, false, -1),
        new NotificationResult(a1, Notification.SET, EAttribute.class, nameInt, name, "a1", null, true, false, true, -1),
        new NotificationResult(a1, Notification.SET, EAttribute.class, nameInt, name, null, null, false, true, true, -1),
        new NotificationResult(a1, Notification.SET, EAttribute.class, upperBoundInt, upperBound, new Integer(1), new Integer(1), false, true, true, -1),
        new NotificationResult(a1, Notification.SET, EAttribute.class, upperBoundInt, upperBound, new Integer(1), new Integer(-1), false, false, false, -1),
        new NotificationResult(a1, Notification.SET, EAttribute.class, upperBoundInt, upperBound, new Integer(-1), new Integer(-1), true, true, false, -1)
      };
    NotificationTester tester = new NotificationTester(expected);

    c.eAdapters().add(tester);
    c.getEStructuralFeatures().add(a1);
    c.getEStructuralFeatures().addAll(Arrays.asList(a2, a3, a4, a5));
    c.getEStructuralFeatures().addAll(Collections.<EStructuralFeature>emptyList()); // noop
    c.getEStructuralFeatures().remove(1);    
    c.getEStructuralFeatures().remove(a6); // noop
    c.getEStructuralFeatures().removeAll(Arrays.asList(a3, a4));
    c.getEStructuralFeatures().removeAll(Arrays.asList(a6)); // noop
    c.getEStructuralFeatures().move(0, a5);
    c.getEStructuralFeatures().move(0, a5); // touch
    c.getEStructuralFeatures().set(0, a2);
    c.getEStructuralFeatures().set(0, a2); // touch

    a1.eAdapters().add(tester);
    a1.setName("a1"); // touch
    a1.setName(null);
    a1.setName(null); // touch
    a1.setUpperBound(1); // touch
    a1.setUpperBound(-1);
    a1.setUpperBound(-1); // touch
    tester.finish();
  }

  EAttribute createEAttribute(String name)
  {
    EAttribute result = EcoreFactory.eINSTANCE.createEAttribute();
    result.setName(name);
    return result;
  }

  class NotificationResult
  {
    public Notifier notifier;
    public int eventType;
    public Class<?> expectedClass;
    public int featureID;
    public Object feature;
    public Object oldValue;
    public Object newValue;
    public boolean wasSet;
    public boolean touch;
    public boolean reset;
    public int position;

    public NotificationResult(Notifier notifier, int eventType, Class<?> expectedClass, int featureID, Object feature, Object oldValue, Object newValue, boolean wasSet, boolean touch, boolean reset, int position)
    {
      this.notifier = notifier;
      this.eventType = eventType;
      this.expectedClass = expectedClass;
      this.featureID = featureID;
      this.feature = feature;
      this.oldValue = oldValue;
      this.newValue = newValue;
      this.wasSet = wasSet;
      this.touch = touch;
      this.reset = reset;
      this.position = position;
    }

    public void test(Notification notification)
    {
      assertEquals(notifier, notification.getNotifier());
      assertEquals(eventType, notification.getEventType());
      assertEquals(featureID, notification.getFeatureID(expectedClass));
      assertEquals(feature, notification.getFeature());
      assertEqualValues(oldValue, notification.getOldValue());
      assertEqualValues(newValue, notification.getNewValue());
      assertEquals(wasSet, notification.wasSet());
      assertEquals(touch, notification.isTouch());
      assertEquals(reset, notification.isReset());
      assertEquals(position, notification.getPosition());
    }

    void assertEqualValues(Object expected, Object actual)
    {
      if (expected instanceof int[] && actual instanceof int[])
      {
        int[] expectedInts = (int[])expected;
        int[] actualInts = (int[])actual;
        if (!Arrays.equals(expectedInts, actualInts))
        {
          failNotEquals(null, Arrays.toString(expectedInts), Arrays.toString(actualInts));
        }
      }
      else
      {
        assertEquals(expected, actual);
      }
    }
  }

  class NotificationTester extends AdapterImpl
  {
    NotificationResult[] expected;
    int notificationCount = 0;

    public NotificationTester(NotificationResult[] expected)
    {
      this.expected = expected;
    }

    @Override
    public void notifyChanged(Notification msg)
    {
      assertTrue("Unexpected notification received", notificationCount < expected.length);
      expected[notificationCount++].test(msg);
    }

    public void finish()
    {
      assertEquals("Expected notification not received", notificationCount, expected.length);
    }
  }
}
