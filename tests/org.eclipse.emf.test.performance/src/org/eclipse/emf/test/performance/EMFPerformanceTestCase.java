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
 * $Id: EMFPerformanceTestCase.java,v 1.5 2005/02/15 20:19:27 bportier Exp $
 */
package org.eclipse.emf.test.performance;

import org.eclipse.test.performance.Dimension;
import org.eclipse.test.performance.PerformanceTestCase;

/**
 * <p>
 * Base class for the EMF performance test cases.  Highlights:
 * <ul>
 * <li>The actions performed by the <tt>setUp</tt> and <tt>tearDown</tt> methods
 * don't affect the measurements.</li>
 * <li>Invoking <tt><b>super.</b>setUp</tt> and <tt><b>super.</b>tearDown</tt> is
 * mandatory if these methods are being overwritten by subclasses</li>
 * <li>The <tt>repetitions</tt> attribute defines how many times a test will be invoked.  
 * If the <tt>startMeasuring</tt> and <tt>stopMeasuring</tt> are invoked in the test, each 
 * iteration is measured and the final result is an average of all repetitions.  The default 
 * number of repetitions is 1.</li>
 * <li>The <tt>warmUp</tt> attribute defines how many times a test will be invoked
 * <b>before</b> the measurements take place.  The <tt>startMeasuring</tt> and 
 * <tt>stopMeasuring</tt> methods won't do anything while the test is being warmed up.</li>
 * </ul>
 * </p>
 * @since 2.1.0
 */
public class EMFPerformanceTestCase extends PerformanceTestCase
{
  public static final Dimension[] TIME_DIMENSIONS = new Dimension[] {
    Dimension.CPU_TIME
    ,Dimension.ELAPSED_PROCESS
    ,Dimension.KERNEL_TIME
  };

  public static final Dimension[] MEMORY_DIMENSIONS = new Dimension[] {
    Dimension.COMITTED
    ,Dimension.USED_JAVA_HEAP
    ,Dimension.WORKING_SET
    ,Dimension.WORKING_SET_PEAK
  };

  public static final Dimension[] ALL_DIMENSIONS = new Dimension[] {
    Dimension.COMITTED
    ,Dimension.CPU_TIME
    ,Dimension.ELAPSED_PROCESS
    ,Dimension.KERNEL_TIME
    ,Dimension.USED_JAVA_HEAP
    ,Dimension.WORKING_SET
    ,Dimension.WORKING_SET_PEAK
  };
  
  private int repetitions = 1;
  private int warmUp = 0;
  private boolean warmingUp = false;
  
  public EMFPerformanceTestCase(String name)
  {
    super(name);
  }
  
  public EMFPerformanceTestCase()
  {
    super();
  }
  
  public EMFPerformanceTestCase setRepetitions(int repeat)
  {
    this.repetitions = repeat;
    return this;
  }
  
  public int getRepetitions()
  {
    return repetitions;
  }
  
  public EMFPerformanceTestCase setWarmUp(int warmUp)
  {
    this.warmUp = warmUp;
    return this;
  }
  
  public int getWarmUp()
  {
    return warmUp;
  }
  
  protected boolean isWarmingUp()
  {
    return warmingUp;
  }
  
  protected void startMeasuring()
  {
    if (!isWarmingUp())
    {
      super.startMeasuring();
    }
  }
  
  protected void stopMeasuring()
  {
    if (!isWarmingUp())
    {
      super.stopMeasuring();
    }
  }
  
  protected void runTest() throws Throwable
  {
    assertTrue("Iterations must be greater than 0", getRepetitions() > 0); 
    
    warmUp();
    for (int i=0, maxi=getRepetitions(); i<maxi; i++)
    {
      super.runTest();
    }
  }
  
  protected void warmUp() throws Throwable
  {
    warmingUp = true;
    try
    {
      for (int i=0, maxi=getWarmUp(); i<maxi; i++)
      {
        super.runTest();
      }
    }
    finally
    {
      warmingUp = false;
    }    
  }
  
  protected void tearDown() throws Exception
  {
    commitMeasurements();
    assertPerformance();    
    super.tearDown();
  }  
}