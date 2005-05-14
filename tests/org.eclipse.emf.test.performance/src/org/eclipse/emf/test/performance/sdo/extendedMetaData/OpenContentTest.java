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
 * $Id$
 */
package org.eclipse.emf.test.performance.sdo.extendedMetaData;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.SDOPackage;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;
import org.eclipse.emf.test.performance.TestUtil;
import org.eclipse.emf.test.performance.sdo.SDOPerfUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class OpenContentTest extends EMFPerformanceTestCase
{
  private static final String DATA = TestUtil.getPluginDirectory() + "/data/";

  private static final String DATA_URI = "file:///" + DATA;

  private static final int ITERATIONS_1T = 2000;

  private static String XML_SCHEMA_URI;

  private static ResourceSet resourceSet = null;

  private static final String openNamespace = "http://open.content";

  private static final String demandNamespace = "http://demand.content";

  private static String[] names = null;

  public OpenContentTest(String name)
  {
    super(name);
    XML_SCHEMA_URI = "file:///" + DATA + "openContent.xsd";
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("OpenContentTest");
    //OK ts.addTest(new OpenContentTest("demandFeature").setWarmUp(2).setRepetitions(30));
    //OK ts.addTest(new OpenContentTest("createDemandFeatureDO").setWarmUp(2).setRepetitions(30));
    return ts;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    tagAsSummary("Performance Results for " + getClass().getName(), TIME_DIMENSIONS);

    if (resourceSet == null)
    {
      resourceSet = SDOUtil.createResourceSet();
      SDOPerfUtil.registerModel(resourceSet, XML_SCHEMA_URI);
    }

    if (names == null)
    {
      names = new String [ITERATIONS_1T];
      for (int i = 0; i < ITERATIONS_1T; i++)
      {
        names[i] = "" + i;
      }
    }

  }

  public void demandFeature() throws Exception
  {
    ExtendedMetaData localMetaData = new BasicExtendedMetaData();
    EStructuralFeature feat = null;
    String[] names = OpenContentTest.names;
    String demandNamespace = OpenContentTest.demandNamespace;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_1T; i++)
    {
      if (feat != this)
      {
        feat = localMetaData.demandFeature(demandNamespace, names[i], true);
      }
    }
    stopMeasuring();
  }

  public void createDemandFeatureDO() throws Exception
  {
    EDataGraph dg = SDOFactory.eINSTANCE.createEDataGraph();
    dg.setResourceSet(resourceSet);
    dg.createRootObject(openNamespace, "DocumentRoot");

    DataObject root = dg.getRootObject();
    DataObject openDO = root.createDataObject("GlobalOpenElement");

    ExtendedMetaData localMetaData = new BasicExtendedMetaData();
    EPackage demandPackage = localMetaData.demandPackage(demandNamespace);
    demandPackage.setEFactoryInstance(new SdoInstanceFactory());

    EStructuralFeature feat = null;
    DataObject complexDO = null;
    String[] names = OpenContentTest.names;
    String demandNamespace = OpenContentTest.demandNamespace;

    Property[] properties = new Property [ITERATIONS_1T];
    for (int i = 0; i < ITERATIONS_1T; i++)
    {
      feat = localMetaData.demandFeature(demandNamespace, names[i], true);
      feat.setEType(SDOPackage.eINSTANCE.getEDataObject());
      properties[i] = SDOUtil.adaptProperty(feat);
    }

    startMeasuring();
    for (int i = 0; i < ITERATIONS_1T; i++)
    {
      if (complexDO != this)
      {
        complexDO = openDO.createDataObject(properties[i]);
      }
    }
    stopMeasuring();
  }

  public static class SdoInstanceFactory extends EFactoryImpl implements EFactory
  {
    public EObject basicCreate(EClass eClass)
    {
      EObjectImpl result = (EObjectImpl)SDOFactory.eINSTANCE.createEDataObject();
      result.eSetClass(eClass);
      return result;
    }
  }

}
