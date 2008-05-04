/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: ChangeDescriptionTest.java,v 1.18 2008/05/04 10:59:07 emerks Exp $
 */
package org.eclipse.emf.test.core.change;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.library.Book;
import org.eclipse.emf.test.models.library.Library;
import org.eclipse.emf.test.models.library.LibraryFactory;
import org.eclipse.emf.test.models.library.Writer;
import org.eclipse.emf.test.models.tree.Node;
import org.eclipse.emf.test.models.tree.TreeFactory;


public class ChangeDescriptionTest extends TestCase
{
  public ChangeDescriptionTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ChangeDescription Test");
    ts.addTest(new ChangeDescriptionTest("testRemoveSuperType"));
    ts.addTest(new ChangeDescriptionTest("testRemoveGenericException"));
    ts.addTest(new ChangeDescriptionTest("testMultipleApplyAndReverse"));
    ts.addTest(new ChangeDescriptionTest("testUnchangeableFeature"));
    ts.addTest(new ChangeDescriptionTest("testApplyAndReverse2"));
    ts.addTest(new ChangeDescriptionTest("testApplyAndReverse3"));
    ts.addTest(new ChangeDescriptionTest("testXMLResourceID"));
    ts.addTest(new ChangeDescriptionTest("testObjectsToDetach1"));
    ts.addTest(new ChangeDescriptionTest("testObjectsToDetach2"));
    ts.addTest(new ChangeDescriptionTest("testAddRemoveObject"));
    ts.addTest(new ChangeDescriptionTest("testSwitchResources"));
    ts.addTest(new ChangeDescriptionTest("testSwitchResources2"));
    ts.addTest(new ChangeDescriptionTest("testSwitchContainers"));
    return ts;
  }

  /*
   * Bugzilla 181288
   */
  public void testRemoveSuperType() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    final EClass superEClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(superEClass);
    final EClass subEClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(subEClass);
    subEClass.getESuperTypes().add(superEClass);
    class Helper
    {
      public void state0()
      {
        assertEquals(1, subEClass.getESuperTypes().size());
        assertEquals(superEClass, subEClass.getESuperTypes().get(0));
        assertEquals(1, subEClass.getEGenericSuperTypes().size());
        assertEquals(superEClass, subEClass.getEGenericSuperTypes().get(0).getEClassifier());
        assertTrue(superEClass.isSuperTypeOf(subEClass));
      }

      public void state1()
      {
        assertTrue(subEClass.getESuperTypes().isEmpty());
        assertTrue(subEClass.getEGenericSuperTypes().isEmpty());
        assertFalse(superEClass.isSuperTypeOf(subEClass));
      }
    }
    Helper helper = new Helper();
    helper.state0();
    
    ChangeRecorder changeRecorder = new ChangeRecorder(pack);
    subEClass.getEGenericSuperTypes().clear();
    ChangeDescription changeDescription = changeRecorder.endRecording();
    helper.state1();
    
    changeDescription.applyAndReverse();
    helper.state0();
    changeDescription.applyAndReverse();
    helper.state1();
    changeDescription.apply();
    helper.state0();
  }

  /*
   * Bugzilla 181288
   */
  public void testRemoveGenericException() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(eClass);
    final EOperation operation = EcoreFactory.eINSTANCE.createEOperation();
    eClass.getEOperations().add(operation);
    final EClass eException = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(eException);
    operation.getEExceptions().add(eException);
    class Helper
    {
      public void state0()
      {
        assertEquals(1, operation.getEExceptions().size());
        assertEquals(eException, operation.getEExceptions().get(0));
        assertEquals(1, operation.getEGenericExceptions().size());
        assertEquals(eException, operation.getEGenericExceptions().get(0).getEClassifier());
      }

      public void state1()
      {
        assertTrue(operation.getEExceptions().isEmpty());
        assertTrue(operation.getEGenericExceptions().isEmpty());
      }
    }
    Helper helper = new Helper();
    helper.state0();
    
    ChangeRecorder changeRecorder = new ChangeRecorder(pack);
    operation.getEGenericExceptions().clear();
    ChangeDescription changeDescription = changeRecorder.endRecording();
    helper.state1();
    
    changeDescription.applyAndReverse();
    helper.state0();
    changeDescription.applyAndReverse();
    helper.state1();
    changeDescription.apply();
    helper.state0();
  }  

  /*
   * Bugzilla 120869
   */
  public void testAddRemoveObject() throws Exception
  {
    // add and remove
    {
      EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
      new ResourceImpl(URI.createURI("foo")).getContents().add(pack);
      EClass class1 = EcoreFactory.eINSTANCE.createEClass();
      class1.setName("Class1");
      pack.getEClassifiers().add(class1);
      EClass class2 = EcoreFactory.eINSTANCE.createEClass();
      class2.setName("Class2");

      ChangeRecorder changeRecorder = new ChangeRecorder(pack);
      pack.setName(pack.getName());
      pack.getEClassifiers().add(class2);
      class2.setName("Class2-1");
      pack.getEClassifiers().remove(class2);
      ChangeDescription changeDescription = changeRecorder.endRecording();

      assertEquals(0, changeDescription.getObjectChanges().size());
      // assertTrue(changeDescription.getObjectChanges().containsKey(pack));
      assertTrue(changeDescription.getObjectsToAttach().isEmpty());
      assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    }

    // remove and add
    {
      EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
      new ResourceImpl(URI.createURI("foo")).getContents().add(pack);
      EClass class1 = EcoreFactory.eINSTANCE.createEClass();
      class1.setName("Class1");
      pack.getEClassifiers().add(class1);
      EClass class2 = EcoreFactory.eINSTANCE.createEClass();
      class2.setName("Class2");

      ChangeRecorder changeRecorder = new ChangeRecorder(pack);
      pack.getEClassifiers().remove(class1);
      class1.setName("Class1-2");
      pack.getEClassifiers().add(class1);
      ChangeDescription changeDescription = changeRecorder.endRecording();

      // Test 2
      assertEquals(1, changeDescription.getObjectChanges().size());
      // assertTrue(changeDescription.getObjectChanges().containsKey(pack));
      assertTrue(changeDescription.getObjectChanges().containsKey(class1));
      assertTrue(changeDescription.getObjectsToAttach().isEmpty());
      assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    }

    // remove and add + remove and add
    {
      EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
      new ResourceImpl(URI.createURI("foo")).getContents().add(pack);
      EClass class1 = EcoreFactory.eINSTANCE.createEClass();
      class1.setName("Class1");
      pack.getEClassifiers().add(class1);
      EClass class2 = EcoreFactory.eINSTANCE.createEClass();
      class2.setName("Class2");

      ChangeRecorder changeRecorder = new ChangeRecorder(pack);
      pack.getEClassifiers().add(class2);
      pack.getEClassifiers().remove(class1);
      class1.setName("Class1-2");
      pack.getEClassifiers().remove(class2);
      class2.setName("Class2-1");
      pack.getEClassifiers().add(class1);
      ChangeDescription changeDescription = changeRecorder.endRecording();

      assertEquals(1, changeDescription.getObjectChanges().size());
      // assertTrue(changeDescription.getObjectChanges().containsKey(pack));
      assertTrue(changeDescription.getObjectChanges().containsKey(class1));
      assertTrue(changeDescription.getObjectsToAttach().isEmpty());
      assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    }

    // add pause remove
    {
      EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
      new ResourceImpl(URI.createURI("foo")).getContents().add(pack);
      EClass class1 = EcoreFactory.eINSTANCE.createEClass();
      class1.setName("Class1");
      pack.getEClassifiers().add(class1);
      EClass class2 = EcoreFactory.eINSTANCE.createEClass();
      class2.setName("Class2");

      ChangeRecorder changeRecorder = new ChangeRecorder(pack);
      pack.getEClassifiers().add(class2);
      ChangeDescription changeDescription = changeRecorder.endRecording();
      changeRecorder = new ChangeRecorder();
      changeRecorder.beginRecording(changeDescription, Collections.singleton(pack));
      class2.setName("Class2-1");
      pack.getEClassifiers().remove(class2);
      changeDescription = changeRecorder.endRecording();

      assertEquals(0, changeDescription.getObjectChanges().size());
      // assertTrue(changeDescription.getObjectChanges().containsKey(pack));
      assertTrue(changeDescription.getObjectsToAttach().isEmpty());
      assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    }

    // remove pause add
    {
      EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
      new ResourceImpl(URI.createURI("foo")).getContents().add(pack);
      EClass class1 = EcoreFactory.eINSTANCE.createEClass();
      class1.setName("Class1");
      pack.getEClassifiers().add(class1);
      EClass class2 = EcoreFactory.eINSTANCE.createEClass();
      class2.setName("Class2");

      ChangeRecorder changeRecorder = new ChangeRecorder(pack);
      pack.getEClassifiers().remove(class1);
      class1.setName("Class1-2");
      ChangeDescription changeDescription = changeRecorder.endRecording();
      changeRecorder = new ChangeRecorder();
      changeRecorder.beginRecording(changeDescription, Collections.singleton(pack));
      pack.getEClassifiers().add(class1);
      changeDescription = changeRecorder.endRecording();

      assertEquals(1, changeDescription.getObjectChanges().size());
      // assertTrue(changeDescription.getObjectChanges().containsKey(pack));
      assertTrue(changeDescription.getObjectChanges().containsKey(class1));
      assertTrue(changeDescription.getObjectsToAttach().isEmpty());
      assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    }

    // remove pause add + remove pause add
    {
      EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
      new ResourceImpl(URI.createURI("foo")).getContents().add(pack);
      EClass class1 = EcoreFactory.eINSTANCE.createEClass();
      class1.setName("Class1");
      pack.getEClassifiers().add(class1);
      EClass class2 = EcoreFactory.eINSTANCE.createEClass();
      class2.setName("Class2");

      ChangeRecorder changeRecorder = new ChangeRecorder(pack);
      pack.getEClassifiers().add(class2);
      pack.getEClassifiers().remove(class1);
      class1.setName("Class1-2");
      ChangeDescription changeDescription = changeRecorder.endRecording();
      changeRecorder = new ChangeRecorder();
      changeRecorder.beginRecording(changeDescription, Collections.singleton(pack));
      pack.getEClassifiers().remove(class2);
      class2.setName("Class2-1");
      pack.getEClassifiers().add(class1);
      changeDescription = changeRecorder.endRecording();

      assertEquals(1, changeDescription.getObjectChanges().size());
      //assertTrue(changeDescription.getObjectChanges().containsKey(pack));
      assertTrue(changeDescription.getObjectChanges().containsKey(class1));
      assertTrue(changeDescription.getObjectsToAttach().isEmpty());
      assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    }
  }

  /*
   * Bugzilla 83872
   * Bugzilla 120869
   */
  public void testObjectsToDetach1() throws Exception
  {
    Resource resource = new XMIResourceImpl(URI.createFileURI("pack"));
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    resource.getContents().add(pack);
    EClass class1 = EcoreFactory.eINSTANCE.createEClass();
    class1.setName("Class1");
    pack.getEClassifiers().add(class1);
    EClass class2 = EcoreFactory.eINSTANCE.createEClass();
    class2.setName("Class2");
    pack.getEClassifiers().add(class2);
    EClass class3 = EcoreFactory.eINSTANCE.createEClass();
    class3.setName("Class3");
    EClass class4 = EcoreFactory.eINSTANCE.createEClass();
    class4.setName("Class4");
    EClass class6 = EcoreFactory.eINSTANCE.createEClass();
    class6.setName("Class6");

    ChangeRecorder changeRecorder = new ChangeRecorder(resource);
    pack.getEClassifiers().add(class3);
    pack.getEClassifiers().set(1, class4);
    pack.getEClassifiers().remove(class1);
    pack.getEClassifiers().add(class1);

    //Add + change + remove the same object
    pack.getEClassifiers().add(class6);
    class6.setName("TheClass6");
    pack.getEClassifiers().remove(class6);

    ChangeDescription changeDescription = changeRecorder.endRecording();
    resource.getContents().add(changeDescription);

    assertEquals(2, changeDescription.getObjectsToDetach().size());
    assertTrue(changeDescription.getObjectsToDetach().contains(class3));
    assertTrue(changeDescription.getObjectsToDetach().contains(class4));
    assertEquals(1, changeDescription.getObjectsToAttach().size());
    assertTrue(changeDescription.getObjectsToAttach().contains(class2));

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    resource.save(baos, null);

    Resource loadedResource = new XMIResourceImpl();
    loadedResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    assertEquals(2, loadedResource.getContents().size());
    assertTrue(loadedResource.getContents().get(0) instanceof EPackage);
    EPackage loadedPack = (EPackage)loadedResource.getContents().get(0);
    assertTrue(loadedResource.getContents().get(1) instanceof ChangeDescription);
    ChangeDescription loadedChangeDescription = (ChangeDescription)loadedResource.getContents().get(1);

    assertEquals(2, loadedChangeDescription.getObjectsToDetach().size());
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedPack.getEClassifier(class3.getName())));
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedPack.getEClassifier(class4.getName())));
    assertEquals(1, loadedChangeDescription.getObjectsToAttach().size());
    assertTrue(loadedChangeDescription.getObjectsToAttach().get(0) instanceof EClass);
    assertEquals(class2.getName(), ((EClass)loadedChangeDescription.getObjectsToAttach().get(0)).getName());

    EClass class5 = EcoreFactory.eINSTANCE.createEClass();
    class5.setName("Class5");

    changeRecorder = new ChangeRecorder();
    changeRecorder.beginRecording(loadedChangeDescription, Collections.singleton(loadedResource));
    loadedPack.getEClassifiers().set(1, class5);
    changeRecorder.endRecording();

    assertEquals(2, loadedChangeDescription.getObjectsToDetach().size());
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedPack.getEClassifier(class4.getName())));
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedPack.getEClassifier(class5.getName())));
    assertEquals(1, loadedChangeDescription.getObjectsToAttach().size());
    Set<String> names = new HashSet<String>();
    names.add(((EClass)loadedChangeDescription.getObjectsToAttach().get(0)).getName());
    assertTrue(names.contains(class2.getName()));
  }

  /*
   * Bugzilla 83872
   */
  public void testObjectsToDetach2() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setNsURI("testObjectsToDetach2");
    EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
    EClass anEClass = EcoreFactory.eINSTANCE.createEClass();
    anEClass.setName("AnEClass");
    pack.getEClassifiers().add(anEClass);
    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    anEClass.getEStructuralFeatures().add(name);
    EReference ref1 = EcoreFactory.eINSTANCE.createEReference();
    ref1.setName("Ref1");
    ref1.setEType(anEClass);
    ref1.setContainment(true);
    anEClass.getEStructuralFeatures().add(ref1);
    EReference ref2 = EcoreFactory.eINSTANCE.createEReference();
    ref2.setName("Ref2");
    ref2.setEType(anEClass);
    ref2.setContainment(true);
    anEClass.getEStructuralFeatures().add(ref2);
    EReference ref3 = EcoreFactory.eINSTANCE.createEReference();
    ref3.setName("Ref3");
    ref3.setEType(anEClass);
    ref3.setContainment(true);
    anEClass.getEStructuralFeatures().add(ref3);
    EReference ref4 = EcoreFactory.eINSTANCE.createEReference();
    ref4.setName("Ref4");
    ref4.setEType(anEClass);
    ref4.setContainment(true);
    anEClass.getEStructuralFeatures().add(ref4);

    EObject root1 = pack.getEFactoryInstance().create(anEClass);
    root1.eSet(name, "root1");
    EObject obj1 = pack.getEFactoryInstance().create(anEClass);
    obj1.eSet(name, "obj1");
    EObject obj2 = pack.getEFactoryInstance().create(anEClass);
    obj2.eSet(name, "obj2");
    EObject obj3 = pack.getEFactoryInstance().create(anEClass);
    obj3.eSet(name, "obj3");
    EObject obj4 = pack.getEFactoryInstance().create(anEClass);
    obj4.eSet(name, "obj4");
    EObject root2 = pack.getEFactoryInstance().create(anEClass);
    root2.eSet(name, "root2");

    root1.eSet(ref1, obj1);
    root1.eSet(ref2, obj2);

    // State 0
    assertEquals(obj1, root1.eGet(ref1));
    assertEquals(root1, obj1.eContainer());
    assertEquals(obj2, root1.eGet(ref2));
    assertEquals(root1, obj2.eContainer());
    assertNull(root1.eGet(ref3));
    assertNull(root1.eGet(ref4));
    assertNull(obj3.eContainer());
    assertNull(obj4.eContainer());
    assertNull(root2.eResource());

    Resource resource = new XMIResourceImpl();
    resource.getContents().add(root1);
    ChangeRecorder changeRecorder = new ChangeRecorder(resource);

    root1.eSet(ref1, obj3); //obj3 replaces obj1 in ref1
    root1.eSet(ref2, obj1); //obj1 replaces obj2 in ref2
    root1.eSet(ref3, obj4); //obj4 is set in ref3

    resource.getContents().add(root2); //root2 is added as a root object

    ChangeDescription changeDescription = changeRecorder.endRecording();
    resource.getContents().add(changeDescription);

    // State 1
    assertEquals(obj3, root1.eGet(ref1));
    assertEquals(root1, obj3.eContainer());
    assertEquals(obj1, root1.eGet(ref2));
    assertEquals(root1, obj1.eContainer());
    assertEquals(obj4, root1.eGet(ref3));
    assertEquals(root1, obj4.eContainer());
    assertNull(root1.eGet(ref4));
    assertEquals(changeDescription, obj2.eContainer());
    assertEquals(resource, root2.eResource());

    // getObjectsToDetach() & getObjectsToAttach() check
    assertEquals(3, changeDescription.getObjectsToDetach().size());
    assertTrue(changeDescription.getObjectsToDetach().contains(obj3));
    assertTrue(changeDescription.getObjectsToDetach().contains(obj4));
    assertTrue(changeDescription.getObjectsToDetach().contains(root2));
    assertEquals(1, changeDescription.getObjectsToAttach().size());
    assertTrue(changeDescription.getObjectsToAttach().contains(obj2));

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    resource.save(baos, null);

    Resource loadedResource = new XMIResourceImpl();

    loadedResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    assertEquals(3, loadedResource.getContents().size());
    EObject loadedRoot1 = loadedResource.getContents().get(0);
    assertEquals(root1.eGet(name), loadedRoot1.eGet(name));
    EObject loadedRoot2 = loadedResource.getContents().get(1);
    assertEquals(root2.eGet(name), loadedRoot2.eGet(name));
    assertTrue(loadedResource.getContents().get(2) instanceof ChangeDescription);
    ChangeDescription loadedChangeDescription = (ChangeDescription)loadedResource.getContents().get(2);

    // getObjectsToDetach() & getObjectsToAttach() check
    assertEquals(3, loadedChangeDescription.getObjectsToDetach().size());
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedRoot1.eGet(ref1)));
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedRoot1.eGet(ref3)));
    assertTrue(loadedChangeDescription.getObjectsToDetach().contains(loadedRoot2));
    assertEquals(1, loadedChangeDescription.getObjectsToAttach().size());

    // State 1
    assertEquals(obj3.eGet(name), ((EObject)loadedRoot1.eGet(ref1)).eGet(name));
    assertEquals(obj1.eGet(name), ((EObject)loadedRoot1.eGet(ref2)).eGet(name));
    assertEquals(obj4.eGet(name), ((EObject)loadedRoot1.eGet(ref3)).eGet(name));
    assertNull(loadedRoot1.eGet(ref4));
    assertEquals(loadedResource, loadedRoot2.eResource());

    loadedChangeDescription.applyAndReverse();

    // State 0
    assertEquals(obj1.eGet(name), ((EObject)loadedRoot1.eGet(ref1)).eGet(name));
    assertEquals(obj2.eGet(name), ((EObject)loadedRoot1.eGet(ref2)).eGet(name));
    assertNull(loadedRoot1.eGet(ref3));
    assertNull(loadedRoot1.eGet(ref4));
    //assertEquals(loadedChangeDescription, loadedRoot2.eContainer());    

    loadedChangeDescription.apply();

    // State 1
    assertEquals(obj3.eGet(name), ((EObject)loadedRoot1.eGet(ref1)).eGet(name));
    assertEquals(obj1.eGet(name), ((EObject)loadedRoot1.eGet(ref2)).eGet(name));
    assertEquals(obj4.eGet(name), ((EObject)loadedRoot1.eGet(ref3)).eGet(name));
    assertNull(loadedRoot1.eGet(ref4));
    assertEquals(loadedResource, loadedRoot2.eResource());
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

    List<EObject> beforeChange = new ArrayList<EObject>(eAnnotation.getContents());

    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().move(0, 1);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    resource = new XMIResourceImpl();
    resource.getContents().add(changeDescription);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    String[] xmi = new String [2];

    List<EObject> afterChange = new ArrayList<EObject>(eAnnotation.getContents());

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

      assertEquals("i=" + i, i % 2 == 0, ePackage.getEAnnotations().contains(ePackageEAnnotation));
      assertEquals("i=" + i, i % 2 == 0, eClass.getEAnnotations().contains(eClassEAnnotation));

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

  /*
   * Bugzilla 172036
   */
  public void testSwitchResources() throws Exception
  {
    final Resource resource1 = new ResourceImpl(URI.createFileURI("/home/res1"));
    final Resource resource2 = new ResourceImpl(URI.createFileURI("/home/res2"));
    final Library library = LibraryFactory.eINSTANCE.createLibrary();
    final Writer writer = LibraryFactory.eINSTANCE.createWriter();

    resource1.getContents().add(library);
    resource2.getContents().add(writer);

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(resource1);
    resourceSet.getResources().add(resource2);

    class Helper
    {
      public void assertState0()
      {
        assertEquals(1, resource1.getContents().size());
        assertEquals(1, resource2.getContents().size());
        assertEquals(resource1, library.eResource());
        assertEquals(resource2, writer.eResource());
      }

      public void assertState1()
      {
        assertEquals(1, resource1.getContents().size());
        assertEquals(1, resource2.getContents().size());
        assertEquals(resource1, writer.eResource());
        assertEquals(resource2, library.eResource());
      }
    }

    Helper helper = new Helper();
    helper.assertState0();

    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    resource1.getContents().add(writer);
    resource2.getContents().add(library);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    helper.assertState1();
    changeDescription.applyAndReverse();
    helper.assertState0();
    changeDescription.applyAndReverse();
    helper.assertState1();
    changeDescription.apply();
    helper.assertState0();
  }

  /*
   * Bugzilla 172036
   */
  public void testSwitchResources2() throws Exception
  {
    final Resource resource1 = new ResourceImpl(URI.createFileURI("/home/res1"));
    final Resource resource2 = new ResourceImpl(URI.createFileURI("/home/res2"));
    final Resource resource3 = new ResourceImpl(URI.createFileURI("/home/res2"));
    final Resource resource4 = new ResourceImpl(URI.createFileURI("/home/res2"));

    final Library library = LibraryFactory.eINSTANCE.createLibrary();
    final Writer writer = LibraryFactory.eINSTANCE.createWriter();
    final Book book1 = LibraryFactory.eINSTANCE.createBook();
    final Book book2 = LibraryFactory.eINSTANCE.createBook();
    final Book book3 = LibraryFactory.eINSTANCE.createBook();

    resource1.getContents().add(library);
    resource2.getContents().add(writer);
    resource2.getContents().add(book2);
    resource4.getContents().add(book3);

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(resource1);
    resourceSet.getResources().add(resource2);
    resourceSet.getResources().add(resource3);
    resourceSet.getResources().add(resource4);

    class Helper
    {
      public void assertState0()
      {
        assertEquals(1, resource1.getContents().size());
        assertEquals(2, resource2.getContents().size());
        assertEquals(0, resource3.getContents().size());
        assertEquals(1, resource4.getContents().size());

        assertEquals(resource1, library.eResource());
        assertEquals(resource2, writer.eResource());
        assertEquals(resource2, book2.eResource());
        assertEquals(resource4, book3.eResource());
        assertNull(book1.eResource());

        assertEquals(0, library.getBooks().size());
      }

      public void assertState1()
      {
        assertEquals(1, resource1.getContents().size());
        assertEquals(1, resource2.getContents().size());
        assertEquals(1, resource3.getContents().size());
        assertEquals(0, resource4.getContents().size());

        assertEquals(resource1, writer.eResource());
        assertEquals(resource2, library.eResource());
        assertEquals(resource2, book1.eResource());
        assertEquals(resource3, book2.eResource());

        assertEquals(2, library.getBooks().size());
        assertEquals(book3, library.getBooks().get(0));
        assertEquals(book1, library.getBooks().get(1));
      }
    }

    Helper helper = new Helper();
    helper.assertState0();

    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    resource3.getContents().add(book1);
    resource3.getContents().add(book2);
    library.getBooks().add(book3);
    resource2.getContents().add(library);
    resource1.getContents().add(writer);
    library.getBooks().add(book1);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    helper.assertState1();
    changeDescription.applyAndReverse();
    helper.assertState0();
    changeDescription.applyAndReverse();
    helper.assertState1();
    changeDescription.applyAndReverse();
    helper.assertState0();
    changeDescription.apply();
    helper.assertState1();
  }

  /*
   * Bugzilla 172037
   */
  public void testSwitchContainers() throws Exception
  {
    final Node node1 = TreeFactory.eINSTANCE.createNode(); node1.setName("node1");
    final Node node2 = TreeFactory.eINSTANCE.createNode(); node2.setName("node2");
    final Node node3 = TreeFactory.eINSTANCE.createNode(); node3.setName("node3");
    final Node node4 = TreeFactory.eINSTANCE.createNode(); node4.setName("node4");

    final Node child1 = TreeFactory.eINSTANCE.createNode(); child1.setName("child1");
    final Node child2 = TreeFactory.eINSTANCE.createNode(); child2.setName("child2");
    final Node child3 = TreeFactory.eINSTANCE.createNode(); child3.setName("child3");
    final Node child4 = TreeFactory.eINSTANCE.createNode(); child4.setName("child4");
    final Node child5 = TreeFactory.eINSTANCE.createNode(); child5.setName("child5");

    node1.getChildren().add(child1);
    node2.getChildren().add(child2);
    node2.getChildren().add(child4);
    node4.getChildren().add(child5);

    Resource resource1 = new ResourceImpl(URI.createFileURI("/home/res1"));
    resource1.getContents().add(node1);
    resource1.getContents().add(node2);
    resource1.getContents().add(node3);
    resource1.getContents().add(node4);
    Resource resource2 = new ResourceImpl(URI.createFileURI("/home/res2"));
    resource2.getContents().add(child3);

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(resource1);
    resourceSet.getResources().add(resource2);

    class Helper
    {
      public void assertState0()
      {
        assertEquals(1, node1.getChildren().size());
        assertEquals(2, node2.getChildren().size());
        assertEquals(0, node3.getChildren().size());
        assertEquals(1, node4.getChildren().size());

        assertEquals(node1, child1.eContainer());
        assertEquals(node2, child2.eContainer());
        assertEquals(node2, child4.eContainer());
        assertEquals(node4, child5.eContainer());
        assertNull(child3.eContainer());

        assertEquals(0, child1.getChildren().size());
      }

      public void assertState1()
      {
        assertEquals(1, node1.getChildren().size());
        assertEquals(1, node2.getChildren().size());
        assertEquals(1, node3.getChildren().size());
        assertEquals(0, node4.getChildren().size());

        assertEquals(node1, child2.eContainer());
        assertEquals(node2, child1.eContainer());
        assertEquals(node2, child3.eContainer().eContainer());
        assertEquals(node3, child4.eContainer());

        assertEquals(2, child1.getChildren().size());
        assertEquals(child5, child1.getChildren().get(0));
        assertEquals(child3, child1.getChildren().get(1));
      }
    }

    Helper helper = new Helper();
    helper.assertState0();

    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    node3.getChildren().add(child3);
    node3.getChildren().add(child4);
    child1.getChildren().add(child5);
    node2.getChildren().add(child1);
    node1.getChildren().add(child2);
    child1.getChildren().add(child3);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    helper.assertState1();
    changeDescription.applyAndReverse();
    helper.assertState0();
    changeDescription.applyAndReverse();
    helper.assertState1();
    changeDescription.applyAndReverse();
    helper.assertState0();
    changeDescription.apply();
    helper.assertState1();
  }
}