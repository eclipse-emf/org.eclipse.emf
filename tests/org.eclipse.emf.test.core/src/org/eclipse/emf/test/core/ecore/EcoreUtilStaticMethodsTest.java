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
 * $Id: EcoreUtilStaticMethodsTest.java,v 1.9 2005/03/18 21:57:37 marcelop Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
