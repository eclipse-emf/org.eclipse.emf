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
 * $Id: DataGraphTest.java,v 1.1 2004/07/21 14:25:49 marcelop Exp $
 */
package org.eclipse.emf.test.core.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class DataGraphTest extends TestCase
{
  private EDataGraph eDataGraph;
  private Map xmlOptions;
  private String expectedXML;
  
  /**
   * @param name
   */
  public DataGraphTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite();
    ts.addTest(new DataGraphTest("testSave"));
    ts.addTest(new DataGraphTest("testLoad"));
    return ts;
  }
  
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    xmlOptions = new HashMap();
    xmlOptions.put(XMLResource.OPTION_DECLARE_XML, Boolean.FALSE);
    xmlOptions.put(XMLResource.OPTION_LINE_WIDTH, new Integer(Integer.MAX_VALUE));
    xmlOptions.put(XMLResource.OPTION_FORMATTED, Boolean.FALSE);
    
    expectedXML = 
    "<sdo:datagraph xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" xmlns:sdo=\"commonj.sdo\"><models><ecore:EPackage name=\"sdotest\"><eClassifiers xsi:type=\"ecore:EClass\" name=\"Class1\"/><eClassifiers xsi:type=\"ecore:EClass\" name=\"Class2\"/></ecore:EPackage></models><ecore:EPackage name=\"sdotest\"><eClassifiers xsi:type=\"ecore:EClass\" name=\"Class1\"/><eClassifiers xsi:type=\"ecore:EClass\" name=\"Class2\"/></ecore:EPackage></sdo:datagraph>" + System.getProperties().getProperty("line.separator");

    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("sdotest");
    
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Class1");
    ePackage.getEClassifiers().add(eClass);
    eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Class2");
    ePackage.getEClassifiers().add(eClass);
    
    eDataGraph = SDOFactory.eINSTANCE.createEDataGraph();
    eDataGraph.setERootObject(ePackage);
  }
  
  public void testSave() throws Exception
  {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    eDataGraph.getDataGraphResource().save(outputStream, xmlOptions);
    assertEquals(expectedXML, new String(outputStream.toByteArray()));
  } 
  
  public void testLoad() throws Exception
  {
    EDataGraph loadedDataGraph = SDOUtil.loadDataGraph(new ByteArrayInputStream(expectedXML.getBytes()), xmlOptions);
    
    assertNotNull(loadedDataGraph.getERootObject());
    assertTrue(loadedDataGraph.getERootObject() instanceof EPackage);
    
    EPackage expectedPackage = (EPackage)eDataGraph.getERootObject();
    EPackage loadedPackage = (EPackage)loadedDataGraph.getERootObject();
    assertEquals(expectedPackage.getName(), loadedPackage.getName());
    assertEquals(expectedPackage.getEClassifiers().size(), loadedPackage.getEClassifiers().size());
    
    for (int i=0; i<expectedPackage.getEClassifiers().size(); i++)
    {
      EClass expectedClass = (EClass)expectedPackage.getEClassifiers().get(i);
      EClass loadedClass = (EClass)loadedPackage.getEClassifiers().get(i);
      assertEquals(expectedClass.getName(), loadedClass.getName());
    }
  }  
}
