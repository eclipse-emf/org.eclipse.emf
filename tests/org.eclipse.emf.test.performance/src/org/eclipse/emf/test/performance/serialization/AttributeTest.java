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
 * $Id: AttributeTest.java,v 1.4 2005/02/15 20:19:27 bportier Exp $
 */
package org.eclipse.emf.test.performance.serialization;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;

public class AttributeTest extends EMFPerformanceTestCase
{
  private static final int ITERATION = 1000;
  private static final int WARM_UP = 20;
  
  private static int numberOfAttributes = 200;
  
  private String attributeValue;
  private Map saveOptions;
  private Map loadOptions;
  
  private EObject oneAttribute;
  private EObject halfAttributes;
  private EObject allAttributes;
  
  private ByteArrayOutputStream baos = new ByteArrayOutputStream();

  
  public AttributeTest(String name, String attributeValue, Map saveOptions, Map loadOptions)
  {
    super(name);
    
    this.attributeValue = attributeValue;
    this.saveOptions = saveOptions;
    this.loadOptions = loadOptions;
  }

  public static Test suite()
  {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i=0; i< 500; i++)
    {
      stringBuffer.append("a");
    }
    String length500 = stringBuffer.toString();

    TestSuite testSuite = new TestSuite();
    testSuite.addTest(new AttributeTest("test1", "a", Collections.EMPTY_MAP, Collections.EMPTY_MAP).setWarmUp(WARM_UP).setRepetitions(ITERATION));
    testSuite.addTest(new AttributeTest("test2", "a", Collections.EMPTY_MAP, Collections.EMPTY_MAP).setWarmUp(WARM_UP).setRepetitions(ITERATION));
    testSuite.addTest(new AttributeTest("test3", "a", Collections.EMPTY_MAP, Collections.EMPTY_MAP).setWarmUp(WARM_UP).setRepetitions(ITERATION));
    testSuite.addTest(new AttributeTest("test4", length500, Collections.EMPTY_MAP, Collections.EMPTY_MAP).setWarmUp(WARM_UP).setRepetitions(ITERATION));
    testSuite.addTest(new AttributeTest("test5", length500, Collections.EMPTY_MAP, Collections.EMPTY_MAP).setWarmUp(WARM_UP).setRepetitions(ITERATION));
    testSuite.addTest(new AttributeTest("test6", length500, Collections.EMPTY_MAP, Collections.EMPTY_MAP).setWarmUp(WARM_UP).setRepetitions(ITERATION));
    return testSuite;
  }
  
  protected void setUp() throws Exception
  {
    super.setUp();
    
    // Asserting initial conditions
    assertNotNull("attributeValue cannot be null", attributeValue);
    assertNotNull("saveOptions cannot be null", saveOptions);
    assertNotNull("loadOptions cannot be null", loadOptions);
    
    // Tagging these tests
    tagAsSummary("Performance Results for " + getClass().getPackage().getName(), TIME_DIMENSIONS);
    
    // Builing up the model
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setNsURI("http://www.eclipse.org/emf/test/performance");
    pack.setNsPrefix("perf");
    pack.setName("perf");
    EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
    
    EClass aEClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(aEClass);
    aEClass.setName("AEClass");
    
    for (int i=0, maxi=numberOfAttributes; i < maxi; i++)
    {
      EAttribute att = EcoreFactory.eINSTANCE.createEAttribute();
      aEClass.getEStructuralFeatures().add(att);
      att.setName("att" + i);
      att.setEType(EcorePackage.eINSTANCE.getEString());
    }
    
    // Instanciating the model
    oneAttribute = pack.getEFactoryInstance().create(aEClass);
    oneAttribute.eSet((EAttribute)aEClass.getEAttributes().get(0), attributeValue);
    
    halfAttributes = pack.getEFactoryInstance().create(aEClass);
    for (int i=0, maxi=(int)(numberOfAttributes/2); i < maxi; i++)
    {
      halfAttributes.eSet((EAttribute)aEClass.getEAttributes().get(i), attributeValue);
    }

    allAttributes = pack.getEFactoryInstance().create(aEClass);
    for (int i=0, maxi=numberOfAttributes; i < maxi; i++)
    {
      allAttributes.eSet((EAttribute)aEClass.getEAttributes().get(i), attributeValue);
    }
  }
  
  /**
   * <p>
   * Test details:
   * <ul>
   * <li>Uses XMLResourceImpl<li>
   * <li>Instance with one attribute different than the default value</li>
   * <li>The attribute value is a string with length=1</li>
   * <li>The save options is Collections.EMPTY_MAP</li>
   * <li>The load options is Collections.EMPTY_MAP</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void test1() throws Exception
  {
    save(oneAttribute);
  }  
  
  /**
   * <p>
   * Test details:
   * <ul>
   * <li>Instance with half of the total number of attributes different than the 
   * default value</li>
   * <li>The attribute value is a string with length=1</li>
   * <li>The save options is Collections.EMPTY_MAP</li>
   * <li>The load options is Collections.EMPTY_MAP</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void test2() throws Exception
  {
    save(halfAttributes);
  }
  
  /**
   * <p>
   * Test details:
   * <ul>
   * <li>Instance with all attributes different than the default value</li>
   * <li>The attribute value is a string with length=1</li>
   * <li>The save options is Collections.EMPTY_MAP</li>
   * <li>The load options is Collections.EMPTY_MAP</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void test3() throws Exception
  {
    save(allAttributes);
  }    

  /**
   * <p>
   * Test details:
   * <ul>
   * <li>Instance with one attribute different than the default value</li>
   * <li>The attribute value is a string with length=500</li>
   * <li>The save options is Collections.EMPTY_MAP</li>
   * <li>The load options is Collections.EMPTY_MAP</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void test4() throws Exception
  {
    save(oneAttribute);
  }  

  /**
   * <p>
   * Test details:
   * <ul>
   * <li>Instance with half of the total number of attributes different than the 
   * default value</li>
   * <li>The attribute value is a string with length=500</li>
   * <li>The save options is Collections.EMPTY_MAP</li>
   * <li>The load options is Collections.EMPTY_MAP</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void test5() throws Exception
  {
    save(halfAttributes);
  }
  
  /**
   * <p>
   * Test details:
   * <ul>
   * <li>Instance with all attributes different than the default value</li>
   * <li>The attribute value is a string with length=500</li>
   * <li>The save options is Collections.EMPTY_MAP</li>
   * <li>The load options is Collections.EMPTY_MAP</li>
   * </ul>
   * </p>
   * @throws Exception
   */
  public void test6() throws Exception
  {
    save(allAttributes);
  }
  
  private final void save(EObject eObject) throws Exception
  {    
    Resource resource = new XMLResourceImpl();
    resource.getContents().add(eObject);
    
    startMeasuring();
    
    resource.save(baos, saveOptions);
    baos.reset();
    baos.close();
    
    stopMeasuring();
  } 
}
