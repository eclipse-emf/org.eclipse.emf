/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: StaticIpoLoad.java,v 1.8 2006/12/30 03:43:52 marcelop Exp $
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
 * Loads/deserializes EMF instances of static International Purchase Order (IPO) model.
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

  @Override
  protected ExtendedMetaData registerModel(ResourceSet rs)
  {
    rs.getPackageRegistry().put(IpoPackage.eNS_URI, IpoPackage.eINSTANCE);
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());
    return new BasicExtendedMetaData(rs.getPackageRegistry());
  }

}
