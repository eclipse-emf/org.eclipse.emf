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
 * $Id: DynamicBidirectionalTest.java,v 1.1 2005/03/11 22:29:57 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;

import com.example.sdo.library.LibraryFactory;
import com.example.sdo.library.util.DoNothingAdapter;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class DynamicBidirectionalTest extends EMFPerformanceTestCase
{
  protected static final int REPETITIONS_10 = 10;

  protected static final int ITERATIONS_8K = 8000;

  protected static final int ITERATIONS_25K = 25000;

  protected LibraryFactory libFactoryInstance = LibraryFactory.eINSTANCE;

  protected DoNothingAdapter adapter = DoNothingAdapter.INSTANCE;

  DataObject book0;

  DataObject book1;

  DataObject writer0;

  DataObject writer1;

  // model

  Property authorProp;

  EStructuralFeature authorFeat;

  Property bookProp;

  EStructuralFeature bookFeat;

  public DynamicBidirectionalTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {

    TestSuite testSuite = new TestSuite();

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);
    libSetup();
    assertNotNull(book0);
    assertNotNull(book1);
    assertNotNull(writer0);
    assertNotNull(writer1);
  }

  protected void libSetup()
  {
    // TODO
  }

  public void setWithESet()
  {
    EObject book0 = (EObject)this.book0;
    Object writer0 = this.writer0;
    Object writer1 = this.writer1;
    EStructuralFeature authorFeat = this.authorFeat;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_25K; i++)
    {
      book0.eSet(authorFeat, writer1);
      book0.eSet(authorFeat, writer0);
    }
    stopMeasuring();
  }

}
