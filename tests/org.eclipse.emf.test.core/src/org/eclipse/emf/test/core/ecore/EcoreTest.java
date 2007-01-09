/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: EcoreTest.java,v 1.1 2007/01/09 06:06:54 davidms Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class EcoreTest extends TestCase
{
  public static class NotificationCollector extends AdapterImpl
  {
    private List<Notification> notifications = new ArrayList<Notification>();
    
    public List<Notification> getNotifications()
    {
      return notifications;
    }
    
    @Override
    public void notifyChanged(Notification msg)
    {
      notifications.add(msg);
    }
  }
  
  public EcoreTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("EcoreTest");
    ts.addTest(new EcoreTest("testCreateAnnotationOnInitialization"));
    //ts.addTest(new EcoreTest("testESuperTypeNotificationCount"));
    return ts;
  }

  /*
   * Bug 169926
   * This must be run before any other tests using Ecore, since it will always pass if EcorePackage has been initialized.
   */
  public void testCreateAnnotationOnInitialization()
  {
    EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
    annotation.setSource("XTest");
    annotation.getDetails().put("Test", "true");
    assertEquals("true", annotation.getDetails().get("Test"));
  }

  public void testESuperTypeNotificationCount() throws Exception
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("Class1");
    EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
    eClass2.setName("Class2");
    
    NotificationCollector notificationCollector = new NotificationCollector();    
    eClass1.eAdapters().add(notificationCollector);
    eClass2.eAdapters().add(notificationCollector);
    
    eClass2.getESuperTypes().add(eClass1);
    
    assertEquals(2, notificationCollector.getNotifications().size());
  }
}
