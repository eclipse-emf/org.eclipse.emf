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
 * $Id: StaticSequenceTest.java,v 1.2 2005/03/16 22:39:00 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.EProperty;
import org.eclipse.emf.ecore.sdo.SDOFactory;

import com.example.sdo.epo.PurchaseOrder;
import com.example.sdo.epo.Supplier;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Sequence;


public class StaticSequenceTest extends DynamicSequenceTest
{

  public StaticSequenceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite();
    // TODO tune warmup.

    testSuite.addTest(new StaticSequenceTest("getSequenceByGenerated").setWarmUp(1000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticSequenceTest("getDerivedByGenerated").setWarmUp(1000).setRepetitions(REPETITIONS));

    testSuite.addTest(new StaticSequenceTest("getSequenceWithEGet").setWarmUp(1000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticSequenceTest("getDerivedWithEGet").setWarmUp(1000).setRepetitions(REPETITIONS));

    testSuite.addTest(new StaticSequenceTest("getSequence").setWarmUp(1000).setRepetitions(REPETITIONS));
    testSuite.addTest(new StaticSequenceTest("getDerived").setWarmUp(1000).setRepetitions(REPETITIONS));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected void supplierSetup()
  {
    initSupplier();

    // serialize DG so that it can be deserialized by DynamicAccessorTest
    //serializeDataGraph();

    initModel();
    initNewData();
  }

  protected void initSupplier()
  {
    SDOFactory sdoFactoryInstance = SDOFactory.eINSTANCE;
    EDataGraph dataGraph = sdoFactoryInstance.createEDataGraph();

    Supplier supplier = epoFactoryInstance.createSupplier();
    this.supplier = (DataObject)supplier;
    supplier.setName("The Supplier");

    PurchaseOrder po = epoFactoryInstance.createPurchaseOrder();
    po.setComment("po1");
    supplier.getPriorityOrders().add(po);

    po = epoFactoryInstance.createPurchaseOrder();
    po.setComment("po2");
    supplier.getStandardOrders().add(po);
  }

  private void initModel()
  {
    List properties = supplier.getType().getProperties();
    ordersProp = (Property)properties.get(1);
    ordersFeat = ((EProperty)ordersProp).getEStructuralFeature();
    priorityOrdersProp = (Property)properties.get(2);
    priorityOrdersFeat = ((EProperty)priorityOrdersProp).getEStructuralFeature();
    standardOrdersProp = (Property)properties.get(3);
    standardOrdersFeat = ((EProperty)standardOrdersProp).getEStructuralFeature();
  }

  public void getSequenceByGenerated()
  {
    Supplier supplier = (Supplier)this.supplier;
    Sequence ordersValue = this.sequenceValue;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_1000K; i++)
    {
      if (ordersValue != this)
      {
        // TODO ideally, we'd want to call different methods (which return Sequence).
        ordersValue = supplier.getOrders();
      }
    }
    stopMeasuring();
  }

  public void getDerivedByGenerated()
  {
    Supplier supplier = (Supplier)this.supplier;
    List derivedValue = this.derivedValue;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_100K; i++)
    {
      if (derivedValue != this)
      {
        derivedValue = supplier.getPriorityOrders();
        derivedValue = supplier.getStandardOrders();
      }
    }
    stopMeasuring();
  }

}
