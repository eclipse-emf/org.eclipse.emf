/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * ContributoresourceSet:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: StaticIPOSDOSaveTest.java,v 1.6 2005/06/22 19:59:55 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.serialization;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import com.example.sdo.ipo.IpoPackage;
import com.example.sdo.ipo.util.IpoResourceFactoryImpl;


/**
 * Saves/serializes SDO instances of static International Purchase Order (IPO) model.
 */
public class StaticIPOSDOSaveTest extends DynamicIPOSDOSaveTest
{

  public StaticIPOSDOSaveTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("StaticIPOSDOSaveTest");
    ts.addTest(new StaticIPOSDOSaveTest("saveWithNoOptions").setRepetitions(REPETITIONS));
    ts.addTest(new StaticIPOSDOSaveTest("saveWithCaching").setRepetitions(REPETITIONS));
    return ts;
  }

  protected ExtendedMetaData registerModel()
  {
    IpoPackage.eINSTANCE.getName();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

}
