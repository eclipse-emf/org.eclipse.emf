/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: StaticIpoLoad.java,v 1.4.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package org.eclipse.emf.test.performance.deserialization;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import com.example.ipo.IpoPackage;
import com.example.ipo.util.IpoResourceFactoryImpl;


/**
 * Test serialization of ipo.xml using static model.
 */
public class StaticIpoLoad extends DynamicIpoLoad
{

  public StaticIpoLoad(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("StaticIpoLoad");
    ts.addTest(new StaticIpoLoad("testLoad").setRepetitions(REPETITIONS));
    ts.addTest(new StaticIpoLoad("testLoadParserCache").setRepetitions(REPETITIONS));
    ts.addTest(new StaticIpoLoad("testLoadParserAndFeatureMapCache").setRepetitions(REPETITIONS));
    return ts;
  }

  protected ExtendedMetaData registerModel(ResourceSet rs)
  {
    IpoPackage p = IpoPackage.eINSTANCE;
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());
    return new BasicExtendedMetaData(rs.getPackageRegistry());
  }

}
