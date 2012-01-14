/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.test.common.TestUtil;

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
    testSuite.addTest(new EcoreUtilStaticMethodsTest("testGetConstraints"));
    return testSuite;
  }
  
  public void testCopyUnsettableSetEmptyList()
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    xsdComplexTypeDefinition.getLexicalFinal().clear();
    assertTrue(xsdComplexTypeDefinition.isSetLexicalFinal());
    XSDComplexTypeDefinition xsdComplexTypeDefinitionCopy = EcoreUtil.copy(xsdComplexTypeDefinition);
    assertTrue(xsdComplexTypeDefinitionCopy.isSetLexicalFinal());
  }
    
  public void testGenerateUUID()
  {
    final Collection<Object> set = new HashSet<Object>();
    
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
          // Ignore
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
      // Ignore
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
    singleAtt.setEType(EcorePackage.Literals.ESTRING);

    EAttribute multiAtt = EcoreFactory.eINSTANCE.createEAttribute();
    aClass.getEStructuralFeatures().add(multiAtt);
    multiAtt.setName("MultiAtt");
    multiAtt.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    multiAtt.setEType(EcorePackage.Literals.ESTRING);

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
    @SuppressWarnings("unchecked")
    List<String> o1List = ((List<String>)o1.eGet(multiAtt));
    o1List.add("o1.multiAtt.0");
    o1List.add("o1.multiAtt.1");
    
    EObject o2 = pack.getEFactoryInstance().create(aClass);
    o2.eSet(singleAtt, "o2.singleAtt");
    @SuppressWarnings("unchecked")
    List<String> o2List = ((List<String>)o2.eGet(multiAtt));
    o2List.add("o2.multiAtt.0");
    o2List.add("o2.multiAtt.1");

    EObject o3 = pack.getEFactoryInstance().create(aClass);
    o3.eSet(singleAtt, "o3.singleAtt");
    @SuppressWarnings("unchecked")
    List<String> o3List = ((List<String>)o3.eGet(multiAtt));
    o3List.add("o3.multiAtt.0");
    o3List.add("o3.multiAtt.1");

    EObject o4 = pack.getEFactoryInstance().create(aClass);
    o4.eSet(singleAtt, "o4.singleAtt");
    @SuppressWarnings("unchecked")
    List<String> o4List = ((List<String>)o4.eGet(multiAtt));
    o4List.add("o4.multiAtt.0");
    o4List.add("o4.multiAtt.1");
    
    o1.eSet(singleRef, o2);
    o2.eSet(singleRef, o3);
    o3.eSet(singleRef, o4);
    o4.eSet(singleRef, o1);
    
    @SuppressWarnings("unchecked")
    List<EObject> o1ReferenceList = ((List<EObject>)o1.eGet(multiRef));
    o1ReferenceList.add(o3);
    o1ReferenceList.add(o4);
    
    EObject cpO1 = EcoreUtil.copy(o1);
    
    assertEquals(o1.eGet(singleAtt), cpO1.eGet(singleAtt));
    assertTrue(TestUtil.areEqual((List<?>)o1.eGet(multiAtt), (List<?>)cpO1.eGet(multiAtt)));
    assertEquals(o1.eGet(singleRef), cpO1.eGet(singleRef));
    assertTrue(TestUtil.areEqual((List<?>)o1.eGet(multiRef), (List<?>)cpO1.eGet(multiRef)));
  }
  
  /*
   * Bugzilla 89978 
   */
  public void testGetConstraints()
  {
    EAnnotation constraintAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    constraintAnnotation.setSource(EcorePackage.eNS_URI);
    constraintAnnotation.getDetails().put("constraints", "c1 c2");
    constraintAnnotation.getDetails().put("foo", "bar");
    
    EClass aClass = EcoreFactory.eINSTANCE.createEClass();
    aClass.getEAnnotations().add(constraintAnnotation);
    
    List<String> constraints = EcoreUtil.getConstraints(aClass);
    assertEquals(2, constraints.size());
    assertTrue(constraints.contains("c1"));
    assertTrue(constraints.contains("c2"));
    
    EAnnotation ecoreAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    ecoreAnnotation.setSource(EcorePackage.eNS_URI);
    ecoreAnnotation.getDetails().put("foo", "bar");
    
    aClass.getEAnnotations().add(ecoreAnnotation);
    
    constraints = EcoreUtil.getConstraints(aClass);
    assertEquals(2, constraints.size());
    assertTrue(constraints.contains("c1"));
    assertTrue(constraints.contains("c2"));
    
    aClass.getEAnnotations().clear();
    aClass.getEAnnotations().add(ecoreAnnotation);

    constraints = EcoreUtil.getConstraints(aClass);
    assertTrue(constraints.isEmpty());
  }
}
