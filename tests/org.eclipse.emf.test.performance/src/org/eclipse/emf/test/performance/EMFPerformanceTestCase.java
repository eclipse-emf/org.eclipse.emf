/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: EMFPerformanceTestCase.java,v 1.2 2005/02/01 04:10:59 marcelop Exp $
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
 * <li>The <tt>iterations</tt> attribute defines how many times a test will
 * be invoked.  Each iteration is measured and the final result is an average of 
 * all iterations.  The default value is 1.</li>
 * <li>The <tt>warmUp</tt> attribute defines how many times a test will be invoked
 * <b>before</b> the measurements take place.</li>
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
  
  private int iterations = 1;
  private int warmUp = 0;
  
  public EMFPerformanceTestCase(String name)
  {
    super(name);
  }
  
  public EMFPerformanceTestCase()
  {
    super();
  }
  
  public EMFPerformanceTestCase setIterations(int repeat)
  {
    this.iterations = repeat;
    return this;
  }
  
  public int getIterations()
  {
    return iterations;
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

  protected void runTest() throws Throwable
  {
    assertTrue("Iterations must be greater than 0", getIterations() > 0); 
    
    for (int i=0, maxi=getWarmUp(); i<maxi; i++)
    {
      super.runTest();
    }
    
    for (int i=0, maxi=getIterations(); i<maxi; i++)
    {
      startMeasuring();
      super.runTest();
      stopMeasuring();
    }
  }
  
  protected void tearDown() throws Exception
  {
    commitMeasurements();
    assertPerformance();    
    super.tearDown();
  }
}
