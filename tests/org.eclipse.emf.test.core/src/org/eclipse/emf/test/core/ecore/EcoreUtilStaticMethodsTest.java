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
 * $Id: EcoreUtilStaticMethodsTest.java,v 1.8 2005/03/09 15:37:29 marcelop Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.test.core.TestUtil;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDFactory;

public class EcoreUtilStaticMethodsTest extends TestCase
{
  public EcoreUtilStaticMethodsTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("EcoreUtilStaticMethodsTest");
    testSuite.addTest(new EcoreUtilStaticMethodsTest("testGenerateUUID"));
    testSuite.addTest(new EcoreUtilStaticMethodsTest("testIndexOf"));
    testSuite.addTest(new EcoreUtilStaticMethodsTest("testSetEList"));
    testSuite.addTest(new EcoreUtilStaticMethodsTest("testCopyUnsettableSetEmptyList"));
    testSuite.addTest(new EcoreUtilStaticMethodsTest("testCopy"));
    return testSuite;
  }
  
  public void testCopyUnsettableSetEmptyList()
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    xsdComplexTypeDefinition.getLexicalFinal().clear();
    assertTrue(xsdComplexTypeDefinition.isSetLexicalFinal());
    XSDComplexTypeDefinition xsdComplexTypeDefinitionCopy = (XSDComplexTypeDefinition)EcoreUtil.copy(xsdComplexTypeDefinition);
    assertTrue(xsdComplexTypeDefinitionCopy.isSetLexicalFinal());
  }
  
  public void testIndexOf()
  {
    assertIndexOf(populateList(new ArrayList()));
    assertIndexOf(populateList(new LinkedList()));
    assertIndexOf(populateList(new BasicEList()));
  }
  
  public void testSetEList()
  {
    assertSetEList(populateList(new ArrayList()));
    assertSetEList(populateList(new LinkedList()));
    assertSetEList(populateList(new UniqueEList()));
  }
  
  public void testGenerateUUID()
  {
    final Collection set = new HashSet();
    
    set.add(EcoreUtil.generateUUID());
    set.add(EcoreUtil.generateUUID());
    set.add(EcoreUtil.generateUUID());
    assertEquals(3, set.size());
    
    Runnable runnable = new Runnable()
    {
      public void run()
      {
        set.add(EcoreUtil.generateUUID());

        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
        }
        
        set.add(EcoreUtil.generateUUID());
        set.add(EcoreUtil.generateUUID());        
      }
    };
    
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    Thread thread3 = new Thread(runnable);
    
    try
    {
      thread1.start(); thread1.join();
      thread2.start(); thread2.join();
      thread3.start(); thread3.join();
    }
    catch (InterruptedException e)
    {
    }    
    assertEquals(12, set.size());
  }  
  
  protected List populateList(List list)
  {
    list.add(null);            //0
    list.add(Boolean.FALSE);   //1
    list.add(new Integer(1));  //2
    list.add(new Integer(2));  //3
    list.add(null);            //4
    list.add("String");        //5
    list.add(new Integer(1));  //6
    list.add("String");        //7
    list.add(Boolean.FALSE);   //8

    return list;
  }
  
  protected void assertIndexOf(List list)
  {
    assertEquals(0, EcoreUtil.indexOf(list, null, 0));
    assertEquals(4, EcoreUtil.indexOf(list, null, 1));
    assertEquals(4, EcoreUtil.indexOf(list, null, 4));
    
    assertEquals(1, EcoreUtil.indexOf(list, Boolean.FALSE, 1));
    assertEquals(8, EcoreUtil.indexOf(list, Boolean.FALSE, 2));
    assertEquals(-1, EcoreUtil.indexOf(list, Boolean.FALSE, 9));
    assertEquals(1, EcoreUtil.indexOf(list, Boolean.FALSE, -2));
    
    assertEquals(2, EcoreUtil.indexOf(list, new Integer(1), 0));
    assertEquals(2, EcoreUtil.indexOf(list, new Integer(1), 2));
    assertEquals(6, EcoreUtil.indexOf(list, new Integer(1), 3));
    
    assertEquals(5, EcoreUtil.indexOf(list, "String", 3));
    
    assertEquals(-1, EcoreUtil.indexOf(list, null, 1000));
    assertEquals(-1, EcoreUtil.indexOf(list, "String", 1000));
  }
    
  protected void assertSetEList(List prototypeList)
  {
    EList eList = new BasicEList();
    EcoreUtil.setEList(eList, prototypeList);
    assertTrue("Empty list test", TestUtil.areEqual(prototypeList, eList));
    
    eList = new BasicEList();
    eList.add(0, "String");
    eList.add(Boolean.FALSE);
    EcoreUtil.setEList(eList, prototypeList);
    assertTrue("Smaller list test", TestUtil.areEqual(prototypeList, eList));
    
    eList = (EList)populateList(new BasicEList());
    EcoreUtil.setEList(eList, prototypeList);
    assertTrue("Same list test", TestUtil.areEqual(prototypeList, eList));

    eList.remove(2);
    eList.add(3, this);
    EcoreUtil.setEList(eList, prototypeList);
    assertTrue("Equal size list test", TestUtil.areEqual(prototypeList, eList));

    eList.add(0, "String");
    eList.add(2, Boolean.FALSE);
    eList.add(Boolean.FALSE);
    eList.add(this);
    EcoreUtil.setEList(eList, prototypeList);
    assertTrue("Bigger list test", TestUtil.areEqual(prototypeList, eList));
  }
  
  /*
   * Bugzilla 87456
   */
  public void testCopy() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    
    EClass aClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(aClass);
    aClass.setName("AClass");
    
    EAttribute singleAtt = EcoreFactory.eINSTANCE.createEAttribute();
    aClass.getEStructuralFeatures().add(singleAtt);
    singleAtt.setName("SingleAtt");
    singleAtt.setEType(EcorePackage.eINSTANCE.getEString());

    EAttribute multiAtt = EcoreFactory.eINSTANCE.createEAttribute();
    aClass.getEStructuralFeatures().add(multiAtt);
    multiAtt.setName("MultiAtt");
    multiAtt.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    multiAtt.setEType(EcorePackage.eINSTANCE.getEString());

    EReference singleRef = EcoreFactory.eINSTANCE.createEReference();
    aClass.getEStructuralFeatures().add(singleRef);
    singleRef.setName("SingleRef");
    singleRef.setEType(aClass);
    
    EReference multiRef = EcoreFactory.eINSTANCE.createEReference();
    aClass.getEStructuralFeatures().add(multiRef);
    multiRef.setName("MultiRef");
    multiRef.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    multiRef.setEType(aClass);
    
    EObject o1 = pack.getEFactoryInstance().create(aClass);
    o1.eSet(singleAtt, "o1.singleAtt");
    ((List)o1.eGet(multiAtt)).add("o1.multiAtt.0");
    ((List)o1.eGet(multiAtt)).add("o1.multiAtt.1");
    
    EObject o2 = pack.getEFactoryInstance().create(aClass);
    o2.eSet(singleAtt, "o2.singleAtt");
    ((List)o2.eGet(multiAtt)).add("o2.multiAtt.0");
    ((List)o2.eGet(multiAtt)).add("o2.multiAtt.1");

    EObject o3 = pack.getEFactoryInstance().create(aClass);
    o3.eSet(singleAtt, "o3.singleAtt");
    ((List)o3.eGet(multiAtt)).add("o3.multiAtt.0");
    ((List)o3.eGet(multiAtt)).add("o3.multiAtt.1");

    EObject o4 = pack.getEFactoryInstance().create(aClass);
    o4.eSet(singleAtt, "o4.singleAtt");
    ((List)o4.eGet(multiAtt)).add("o4.multiAtt.0");
    ((List)o4.eGet(multiAtt)).add("o4.multiAtt.1");
    
    o1.eSet(singleRef, o2);
    o2.eSet(singleRef, o3);
    o3.eSet(singleRef, o4);
    o4.eSet(singleRef, o1);
    
    ((List)o1.eGet(multiRef)).add(o3);
    ((List)o1.eGet(multiRef)).add(o4);
    
    EObject cpO1 = EcoreUtil.copy(o1);
    
    assertEquals(o1.eGet(singleAtt), cpO1.eGet(singleAtt));
    assertTrue(TestUtil.areEqual((List)o1.eGet(multiAtt), (List)cpO1.eGet(multiAtt)));
    assertEquals(o1.eGet(singleRef), cpO1.eGet(singleRef));
    assertTrue(TestUtil.areEqual((List)o1.eGet(multiRef), (List)cpO1.eGet(multiRef)));
  }
}
