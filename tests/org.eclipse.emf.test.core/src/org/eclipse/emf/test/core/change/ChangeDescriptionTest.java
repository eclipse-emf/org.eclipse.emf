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
 * $Id: ChangeDescriptionTest.java,v 1.6.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.core.change;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.test.core.TestUtil;


public class ChangeDescriptionTest extends TestCase
{
  public ChangeDescriptionTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ChangeDescription Test");
    ts.addTest(new ChangeDescriptionTest("testMultipleApplyAndReverse"));
    ts.addTest(new ChangeDescriptionTest("testUnchangeableFeature"));
    ts.addTest(new ChangeDescriptionTest("testApplyAndReverse2"));
    ts.addTest(new ChangeDescriptionTest("testApplyAndReverse3"));
    ts.addTest(new ChangeDescriptionTest("testXMLResourceID"));
    return ts;
  }

  /*
   * Bugzilla 76971
   */
  public void testMultipleApplyAndReverse() throws Exception
  {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(URI.createURI("test.ecore"));

    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    ePackage.getEAnnotations().add(eAnnotation);
    resource.getContents().add(ePackage);

    List beforeChange = new ArrayList(eAnnotation.getContents());

    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().move(0, 1);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    resource = new XMIResourceImpl();
    resource.getContents().add(changeDescription);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    String[] xmi = new String [2];

    List afterChange = new ArrayList(eAnnotation.getContents());

    for (int i = 1; i <= 20; i++)
    {
      baos.reset();
      resource.save(baos, null);
      switch (i)
      {
        case 1:
          xmi[1] = new String(baos.toByteArray());
          break;
        case 2:
          xmi[0] = new String(baos.toByteArray());
          break;
        default:
          String newXMI = new String(baos.toByteArray());
          assertEquals("Comparing iteration: " + i, xmi[i % 2], newXMI);
      }

      assertEquals(i % 2 == 0, TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
      assertEquals(i % 2 != 0, TestUtil.areEqual(afterChange, eAnnotation.getContents()));

      changeDescription.applyAndReverse();
    }
  }

  public void testUnchangeableFeature()
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();

    EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
    ePackage.getEClassifiers().add(eDataType);

    ChangeRecorder changeRecorder = new ChangeRecorder(ePackage);
    ePackage.getEClassifiers().remove(eDataType);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertTrue(ePackage.getEClassifiers().isEmpty());
    
    //The opposite reference of ePackage.EClassifiers is
    //unchangeable, so it should not be manipulated when reverting the
    //changes
    changeDescription.applyAndReverse();
    
    assertEquals(1, ePackage.getEClassifiers().size());
    assertEquals(eDataType, ePackage.getEClassifiers().get(0));
  }

  public void testApplyAndReverse2() throws Exception
  {    
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    EAnnotation ePackageEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    ePackageEAnnotation.setSource("ePackageEAnnotation");
    ePackage.getEAnnotations().add(ePackageEAnnotation);
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(ePackage);
    resource.setURI(URI.createURI("foo"));

    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    EAnnotation eClassEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    eClassEAnnotation.setSource("eClassEAnnotation");
    eClass.getEAnnotations().add(eClassEAnnotation);
    ePackage.getEClassifiers().add(eClass);

    ChangeRecorder changeRecorder = new ChangeRecorder(ePackage);

    ePackage.getEAnnotations().remove(ePackageEAnnotation);
    eClass.getEAnnotations().remove(eClassEAnnotation);

    ChangeDescription changeDescription = changeRecorder.endRecording();

    Resource changeDescriptionResource = new XMIResourceImpl();
    changeDescriptionResource.getContents().add(changeDescription);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    String[] xmi = new String [2];
    
    for (int i = 1; i <= 20; i++)
    {
      baos.reset();
      changeDescriptionResource.save(baos, null);
      switch (i)
      {
        case 1:
          xmi[1] = new String(baos.toByteArray());
          break;
        case 2:
          xmi[0] = new String(baos.toByteArray());
          break;
        default:
          String newXMI = new String(baos.toByteArray());
          assertEquals("Comparing iteration: " + i, xmi[i % 2], newXMI);
      }
      
      assertEquals("i="+i, i % 2 == 0, ePackage.getEAnnotations().contains(ePackageEAnnotation));
      assertEquals("i="+i, i % 2 == 0, eClass.getEAnnotations().contains(eClassEAnnotation));

      changeDescription.applyAndReverse();
    }
  }

  public void testApplyAndReverse3() throws Exception
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    ePackage.getEAnnotations().add(eAnnotation);
    
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    ePackage.getEClassifiers().add(eClass);
    
    ChangeRecorder changeRecorder = new ChangeRecorder(ePackage);
    eClass.getEAnnotations().add(eAnnotation);
    ChangeDescription changeDescription = changeRecorder.endRecording();
 
    //State1
    assertTrue(ePackage.getEAnnotations().isEmpty());
    assertEquals(1, eClass.getEAnnotations().size());
    assertEquals(eAnnotation, eClass.getEAnnotations().get(0));
    
    changeDescription.applyAndReverse();
    
    //State2
    assertTrue(eClass.getEAnnotations().isEmpty());
    assertEquals(1, ePackage.getEAnnotations().size());
    assertEquals(eAnnotation, ePackage.getEAnnotations().get(0));

    changeDescription.applyAndReverse();
    
    //State1
    assertTrue(ePackage.getEAnnotations().isEmpty());
    assertEquals(1, eClass.getEAnnotations().size());
    assertEquals(eAnnotation, eClass.getEAnnotations().get(0));
  }
  
  public void testXMLResourceID()
  {
    //Instantiating an object
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    EClass cls = EcoreFactory.eINSTANCE.createEClass();
    cls.setName("cls");
    pack.getEClassifiers().add(cls);
    EObject eObject = pack.getEFactoryInstance().create(cls);
    
    //Instantiating a resource
    XMLResource xmlResource = new XMLResourceImpl();
    
    //Adding the object to the resource
    xmlResource.getContents().add(eObject);
    assertEquals(xmlResource, eObject.eResource());
    assertNull(xmlResource.getID(eObject));

    //Setting an external ID
    xmlResource.setID(eObject, "CLASS:cls");
    
    //State1: resource has 1 object and "CLASS:cls" is the object's id
    assertEquals(1, xmlResource.getContents().size());
    assertEquals(eObject, xmlResource.getContents().get(0));
    assertEquals(eObject, xmlResource.getEObject("CLASS:cls"));
    assertEquals("CLASS:cls", xmlResource.getID(eObject));
    
    //Removing the object from the resource
    ChangeRecorder changeRecorder = new ChangeRecorder(xmlResource);
    xmlResource.getContents().remove(eObject);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    //State2: resource has no objects and nothing is identified by the ID     
    assertTrue(xmlResource.getContents().isEmpty());
    assertNull(xmlResource.getID(eObject));
    assertNull(xmlResource.getEObject("CLASS:cls"));
    assertTrue(((XMLResourceImpl)xmlResource).getEObjectToIDMap().isEmpty());
    assertTrue(((XMLResourceImpl)xmlResource).getIDToEObjectMap().isEmpty());
    
    changeDescription.applyAndReverse();
    
    //State 3: resouce has 1 object and no ID is set
    assertEquals(1, xmlResource.getContents().size());
    assertEquals(eObject, xmlResource.getContents().get(0));
    //
    assertNull(xmlResource.getID(eObject));
    assertNull(xmlResource.getEObject("CLASS:cls"));
  }  
}