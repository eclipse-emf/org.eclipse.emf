/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.featuremap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder;
import org.eclipse.emf.test.core.featuremap.supplier.Supplier;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierFactory;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeatureMapTest
{
  private Supplier supplier;

  @Before
  public void setUp()
  {
    SupplierFactory supplierFactory = SupplierPackage.eINSTANCE.getSupplierFactory();

    supplier = supplierFactory.createSupplier();
    supplier.setName("ACME");
  }

  @After
  public void tearDown()
  {
    supplier.getOrders().clear();
    supplier = null;
  }

  @Test
  public void testFeatureMap() throws Exception
  {
    PurchaseOrder po1 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    supplier.getPreferredOrders().add(po1);

    assertEquals(1, supplier.getOrders().size());
    assertEquals(po1, supplier.getOrders().getValue(0));
    assertEquals(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders(), supplier.getOrders().getEStructuralFeature(0));
    //
    assertEquals(1, supplier.getPreferredOrders().size());
    assertEquals(po1, supplier.getPreferredOrders().get(0));
    assertEquals(1, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders()).size());
    assertEquals(po1, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders()).get(0));

    PurchaseOrder po2 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    supplier.getStandardOrders().add(po2);

    assertEquals(2, supplier.getOrders().size());
    assertEquals(po1, supplier.getOrders().getValue(0));
    assertEquals(po2, supplier.getOrders().getValue(1));
    assertEquals(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders(), supplier.getOrders().getEStructuralFeature(0));
    assertEquals(SupplierPackage.eINSTANCE.getSupplier_StandardOrders(), supplier.getOrders().getEStructuralFeature(1));
    //
    assertEquals(1, supplier.getPreferredOrders().size());
    assertEquals(po1, supplier.getPreferredOrders().get(0));
    assertEquals(1, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders()).size());
    assertEquals(po1, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders()).get(0));
    //
    assertEquals(1, supplier.getStandardOrders().size());
    assertEquals(po2, supplier.getStandardOrders().get(0));
    assertEquals(1, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_StandardOrders()).size());
    assertEquals(po2, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_StandardOrders()).get(0));

    supplier.getStandardOrders().add(po1);

    assertEquals(2, supplier.getOrders().size());
    assertEquals(po2, supplier.getOrders().getValue(0));
    assertEquals(po1, supplier.getOrders().getValue(1));
    assertEquals(SupplierPackage.eINSTANCE.getSupplier_StandardOrders(), supplier.getOrders().getEStructuralFeature(0));
    assertEquals(SupplierPackage.eINSTANCE.getSupplier_StandardOrders(), supplier.getOrders().getEStructuralFeature(1));
    //
    assertEquals(0, supplier.getPreferredOrders().size());
    assertEquals(0, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders()).size());
    //
    assertEquals(2, supplier.getStandardOrders().size());
    assertEquals(po2, supplier.getStandardOrders().get(0));
    assertEquals(po1, supplier.getStandardOrders().get(1));
    assertEquals(2, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_StandardOrders()).size());
    assertEquals(po2, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_StandardOrders()).get(0));
    assertEquals(po1, supplier.getOrders().list(SupplierPackage.eINSTANCE.getSupplier_StandardOrders()).get(1));
  }

  /**
   * A test for <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=516113">bug 516113</a>.
   */
  @Test
  public void testRemoveAllNotifications()
  {
    supplier.getPreferredOrders();
    PurchaseOrder po1 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    PurchaseOrder po2 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
    purchaseOrders.add(po1);
    purchaseOrders.add(po2);

    class NotificationTester extends EContentAdapter
    {
      public int supplierAddManyOrdersNotificationCount;
      public int supplierAddManyPreferredOrdersNotificationCount;
      public int supplierRemoveManyOrdersNotificationCount;
      public int supplierRemoveManyPreferredOrdersNotificationCount;
      public int removingAdapterCount;

      @Override
      public void notifyChanged(Notification notification)
      {
        switch (notification.getEventType())
        {
          case Notification.ADD_MANY:
          {
            Object feature = notification.getFeature();
            if (feature == SupplierPackage.Literals.SUPPLIER__ORDERS)
            {
              ++supplierAddManyOrdersNotificationCount;
            }
            else if (feature == SupplierPackage.Literals.SUPPLIER__PREFERRED_ORDERS)
            {
              ++supplierAddManyPreferredOrdersNotificationCount;
            }
            else
            {
              fail("Unexpected notification: " + notification);
            }
            break;
          }
          case Notification.REMOVE_MANY:
          {
            Object feature = notification.getFeature();
            if (feature == SupplierPackage.Literals.SUPPLIER__ORDERS)
            {
              ++supplierRemoveManyOrdersNotificationCount;
            }
            else if (feature == SupplierPackage.Literals.SUPPLIER__PREFERRED_ORDERS)
            {
              ++supplierRemoveManyPreferredOrdersNotificationCount;
            }
            else
            {
              fail("Unexpected notification: " + notification);
            }
            break;
          }
          case Notification.REMOVING_ADAPTER:
          {
            ++removingAdapterCount;
            break;
          }
          default:
          {
            fail("Unexpected notification: " + notification);
            break;
          }
        }

        super.notifyChanged(notification);
      }
    }

    NotificationTester notificationTester = new NotificationTester();

    supplier.eAdapters().add(notificationTester);
    supplier.getPreferredOrders().addAll(purchaseOrders);
    supplier.getPreferredOrders().removeAll(purchaseOrders);

    assertEquals("One ADD_MANY notification expected for the supplier.getOrder()",  1, notificationTester.supplierAddManyOrdersNotificationCount);
    assertEquals("One ADD_MANY notification expected for the supplier.getPreferredOrders()",  1, notificationTester.supplierAddManyPreferredOrdersNotificationCount);
    assertEquals("One REMOVE_MANY notification expected for the supplier.getOrder()",  1, notificationTester.supplierRemoveManyOrdersNotificationCount);
    assertEquals("One REMOVE_MANY notification expected for the supplier.getPreferredOrders()",  1, notificationTester.supplierRemoveManyPreferredOrdersNotificationCount);
    assertEquals("Two REMOVING_ADPATER notifications expected",  2, notificationTester.removingAdapterCount);

    supplier.eAdapters().remove(notificationTester);
  }

  @Test
  public void testSaveAndLoad() throws Exception
  {
    PurchaseOrder po1 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    po1.setId("po1");
    supplier.getPreferredOrders().add(po1);
    PurchaseOrder po2 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    po2.setId("po2");
    supplier.getStandardOrders().add(po2);

    URI supplierURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/supplier.xmi");
    Resource resource = new XMIResourceFactoryImpl().createResource(supplierURI);
    resource.getContents().add(supplier);
    resource.save(Collections.EMPTY_MAP);
    assertTrue(new File(supplierURI.toFileString()).isFile());

    ResourceSet resourceSet = new ResourceSetImpl();
    Resource loadedResource = resourceSet.getResource(supplierURI, true);
    assertEquals(1, loadedResource.getContents().size());
    assertTrue(loadedResource.getContents().get(0) instanceof Supplier);

    Supplier loadedSupplier = (Supplier)loadedResource.getContents().get(0);
    assertEquals(supplier.getName(), loadedSupplier.getName());
    //
    assertEquals(supplier.getPreferredOrders().size(), loadedSupplier.getPreferredOrders().size());
    assertEquals(1, loadedSupplier.getPreferredOrders().size());
    assertEquals(po1.getId(), (loadedSupplier.getPreferredOrders().get(0)).getId());
    assertEquals(po1.getId(), ((PurchaseOrder)loadedSupplier.getOrders().getValue(0)).getId());
    //
    assertEquals(supplier.getStandardOrders().size(), loadedSupplier.getStandardOrders().size());
    assertEquals(1, loadedSupplier.getStandardOrders().size());
    assertEquals(po2.getId(), (loadedSupplier.getStandardOrders().get(0)).getId());
    assertEquals(po2.getId(), ((PurchaseOrder)loadedSupplier.getOrders().getValue(1)).getId());

    new File(supplierURI.toFileString()).delete();
    assertFalse(new File(supplierURI.toFileString()).isFile());
  }
}