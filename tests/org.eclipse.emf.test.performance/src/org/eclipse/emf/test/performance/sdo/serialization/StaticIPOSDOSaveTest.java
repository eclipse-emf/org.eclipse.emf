/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: StaticIPOSDOSaveTest.java,v 1.7 2006/12/30 03:43:52 marcelop Exp $
 */
package org.eclipse.emf.test.performance.sdo.serialization;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import com.example.ipo.IpoPackage;
import com.example.ipo.util.IpoResourceFactoryImpl;


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

  @Override
  protected ExtendedMetaData registerModel()
  {
    IpoPackage.eINSTANCE.getName();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

}
