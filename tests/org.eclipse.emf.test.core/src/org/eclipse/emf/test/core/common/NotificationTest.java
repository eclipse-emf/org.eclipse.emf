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
 * $Id: NotificationTest.java,v 1.2 2006/07/18 05:40:54 marcelop Exp $
 */
package org.eclipse.emf.test.core.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EcoreFactory;
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
    
    List initialContents = new ArrayList(resource.getContents());
    
    class MyAdapter extends AdapterImpl
    {
      public NotificationImpl mergedNotification;
      
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

    List removedObjects = new ArrayList();
    
    // Remove all, one by one
    for (Iterator i = initialContents.iterator(); i.hasNext();)
    {
      Object item = (Object)i.next();
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
    for (Iterator i = initialContents.iterator(); i.hasNext(); count++)
    {
      Object item = (Object)i.next();
      if ((count % 2) != 0)
      {
        if (resource.getContents().remove(item))
        {
          removedObjects.add(item);
        }
      }
    }
    removeNotificationMergeCheck(removedObjects, initialContents, myAdapter.mergedNotification);    

    // *** Reset
    removedObjects.clear();
    resource.getContents().clear();
    resource.getContents().addAll(initialContents);
    myAdapter.mergedNotification = null;
    
    // Remove even items
    count = 0;
    for (Iterator i = initialContents.iterator(); i.hasNext(); count++)
    {
      Object item = (Object)i.next();
      if ((count % 2) == 0)
      {
        if (resource.getContents().remove(item))
        {
          removedObjects.add(item);
        }
      }
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

  private void removeNotificationMergeCheck(List removedItems, List initialContents, NotificationImpl mergedNotification)
  {
    assertEquals(Notification.REMOVE_MANY, mergedNotification.getEventType());
    
    List oldValue = (List)mergedNotification.getOldValue();
    int[] newValue = (int[])mergedNotification.getNewValue();
    
    assertEquals(removedItems.size(), oldValue.size());
    assertEquals(removedItems.size(), newValue.length);
    
    for (int i=0; i < removedItems.size(); i++)
    {
      assertTrue(removedItems.contains(oldValue.get(i)) );
      assertEquals(oldValue.get(i), initialContents.get(newValue[i]));
    }
  }
}