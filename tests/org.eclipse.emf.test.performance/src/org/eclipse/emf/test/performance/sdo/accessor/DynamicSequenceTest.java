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
 * $Id: DynamicSequenceTest.java,v 1.4 2005/03/18 04:04:56 nickb Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.util.List;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;

import com.example.sdo.epo.EPOFactory;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Sequence;


public class DynamicSequenceTest extends EMFPerformanceTestCase
{

  protected static final int REPETITIONS = 10;

  protected EPOFactory epoFactoryInstance = EPOFactory.eINSTANCE;

  // data

  protected DataObject supplier;

  // model

  protected Property ordersProp;

  protected EStructuralFeature ordersFeat;

  protected Property priorityOrdersProp;

  protected Property standardOrdersProp;

  protected EStructuralFeature priorityOrdersFeat;

  protected EStructuralFeature standardOrdersFeat;

  // used in get tests

  protected Sequence sequenceValue;

  protected Object objectValue;

  protected List derivedValue;

  protected static Properties props = new Properties();
  protected static int iterations;

  /**
   * By calculating the value of iterations based on the value in the accompanying iterations.properties file,
   * changing the values of the iterations can be done all in one place instead of throughout this file.
   * Additionally, static fields are no longer required to define ITERATIONS_* constants.
   * @param name
   */
  public DynamicSequenceTest(String name)
  {
    super(name);
	iterations = getIterations(DynamicSequenceTest.class.getName()+"."+name);
  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

	testSuite.addTest(new DynamicSequenceTest("testGetIterationsCount"));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);
    supplierSetup();
    assertNotNull(supplier);
  }

  protected void supplierSetup()
  {
    // dynamic is not tested currently.
  }

  public void getSequenceWithEGet()
  {
    EObject supplier = (EObject)this.supplier;
    Object objectValue = this.objectValue;
    EStructuralFeature ordersFeat = this.ordersFeat;

    startMeasuring();
    for (int i = 0; i < iterations; i++)
    {
      if (objectValue != this)
      { // TODO ideally, we'd want to call different methods (which return Sequence).
        objectValue = supplier.eGet(ordersFeat);
      }
    }
    stopMeasuring();
  }

  public void getDerivedWithEGet()
  {
    EObject supplier = (EObject)this.supplier;
    Object objectValue = this.objectValue;
    EStructuralFeature priorityOrdersFeat = this.priorityOrdersFeat;
    EStructuralFeature standardOrdersFeat = this.standardOrdersFeat;

    startMeasuring();
    for (int i = 0; i < iterations; i++)
    {
      if (objectValue != this)
      {
        objectValue = supplier.eGet(priorityOrdersFeat);
        objectValue = supplier.eGet(standardOrdersFeat);
      }
    }
    stopMeasuring();
  }

  public void getSequence()
  {
    DataObject supplier = this.supplier;
    Sequence sequenceValue = this.sequenceValue;
    Property ordersProp = this.ordersProp;

    startMeasuring();
    for (int i = 0; i < iterations; i++)
    {
      if (sequenceValue != this)
      { // TODO ideally, we'd want to call different methods (which return Sequence).
        sequenceValue = supplier.getSequence(ordersProp);
      }
    }
    stopMeasuring();
  }

  public void getDerived()
  {
    DataObject supplier = this.supplier;
    List derivedValue = this.derivedValue;
    Property priorityOrdersProp = this.priorityOrdersProp;
    Property standardOrdersProp = this.standardOrdersProp;

    startMeasuring();
    for (int i = 0; i < iterations; i++)
    {
      if (derivedValue != this)
      {
        derivedValue = supplier.getList(priorityOrdersProp);
        derivedValue = supplier.getList(standardOrdersProp);
      }
    }
    stopMeasuring();
  }

  public void testGetIterationsCount()
  {
	  System.out.println("DynamicSequenceTest.testGetIterationsCount: "+iterations);
  }

}
