/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: StaticIPOSDOLoadTest.java,v 1.3.2.3 2005/06/08 18:27:47 nickb Exp $
 */
package org.eclipse.emf.test.performance.sdo.deserialization;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import com.example.sdo.ipo.IpoPackage;
import com.example.sdo.ipo.util.IpoResourceFactoryImpl;


public class StaticIPOSDOLoadTest extends DynamicIPOSDOLoadTest
{

  public StaticIPOSDOLoadTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("StaticIPOSDOLoadTest");
    ts.addTest(new StaticIPOSDOLoadTest("loadWithNoOptions").setRepetitions(REPETITIONS));
    ts.addTest(new StaticIPOSDOLoadTest("loadWithParserCache").setRepetitions(REPETITIONS));
    // not supported yet ts.addTest(new StaticIPOSDOLoadTest("loadWithParserAndFeatureMapCache").setRepetitions(REPETITIONS));
    return ts;
  }

  protected ExtendedMetaData registerModel()
  {
    IpoPackage ipoPackageInstance = IpoPackage.eINSTANCE;
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

}
