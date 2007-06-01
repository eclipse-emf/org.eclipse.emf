/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: ChangeRecordTest.java,v 1.5.2.4 2007/06/01 20:30:05 emerks Exp $
 */
package org.eclipse.emf.test.core.change;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.test.core.TestUtil;


public class ChangeRecordTest
extends TestCase
{
  private boolean callSummarize = false;
  
  private ResourceSet resourceSet;
  private EAnnotation eAnnotation;
  private EClass eClass0;
  
  public ChangeRecordTest(String name)
  {
    super(name);
  }
  
  public ChangeRecordTest(String name, boolean callSummarize)
  {
    super(name);
    this.callSummarize = callSummarize;
  }  

  public static Test suite()
  {
    return suite(false);
  }
  
  public static Test suite(boolean callSummarize)
  {
    TestSuite ts = new TestSuite("ChangeReportTest - callSummarize:" + callSummarize);
    ts.addTest(new ChangeRecordTest("testResource", callSummarize));
    ts.addTest(new ChangeRecordTest("testAttribute", callSummarize));
    ts.addTest(new ChangeRecordTest("testReuse", callSummarize));
    ts.addTest(new ChangeRecordTest("testSetElement", callSummarize));
    ts.addTest(new ChangeRecordTest("testRemoveElementAndApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testAddElementAndApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testMoveElementAndApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testApplyAndReverse", callSummarize));
    //ts.addTest(new ChangeRecordTest("testResumeRecording", callSummarize)); // not supported in 2.0.2 (yet)
    //ts.addTest(new ChangeRecordTest("testResumeSerializedRecording", callSummarize)); // // not supported in 2.0.2 (yet)
    return ts;
  }
  
  
  protected void setUp() throws Exception
  {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

    resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(URI.createURI("test.ecore"));

    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("testEPackage");

    resource.getContents().add(ePackage);

    eClass0 = EcoreFactory.eINSTANCE.createEClass();
    eClass0.setName("testEClass0");

    eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    eAnnotation.setSource("testEAnnotation");
    eAnnotation.getContents().add(eClass0);

    ePackage.getEAnnotations().add(eAnnotation);
  }

  public void testSetElement()
  {
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);

    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    if (callSummarize) changeRecorder.summarize();
    eClass1.setName("testEClass1");
    if (callSummarize) changeRecorder.summarize();
    eAnnotation.getContents().set(0, eClass1);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    EMap objectChanges = changeDescription.getObjectChanges();
    assertEquals(1, objectChanges.size());
    assertTrue(objectChanges.containsKey(eAnnotation));
    assertEquals(1, objectChanges.values().size());
    assertTrue(objectChanges.values().iterator().next() instanceof EList);
    
    EList eList = (EList)objectChanges.values().iterator().next();
    assertEquals(1, eList.size());
    assertTrue(eList.iterator().next() instanceof FeatureChange);
    
    FeatureChange featureChange = (FeatureChange)eList.iterator().next();
    assertEquals(EcorePackage.eINSTANCE.getEAnnotation_Contents(), featureChange.getFeature());
    assertEquals(2, featureChange.getListChanges().size());
    
    int checker = 0;
    for (Iterator i = featureChange.getListChanges().iterator(); i.hasNext();)
    {
      ListChange listChange = (ListChange)i.next();
      switch (listChange.getKind().getValue())
      {
        case ChangeKind.ADD:
          assertEquals(0, listChange.getIndex());
        	assertEquals(1, listChange.getValues().size());
        	assertEquals(eClass0, listChange.getValues().get(0));
        	checker += 1;
          break;
        
        case ChangeKind.REMOVE:
        	assertEquals(1, listChange.getIndex());
        	assertEquals(0, listChange.getReferenceValues().size());
          checker += 4;
          break;
      }
    }
    assertEquals(5, checker);
  }
  
  /*
   * bugzilla 68200
   */
  public void testRemoveElementAndApply()
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");
    eAnnotation.getContents().add(eClass1);
    
    List beforeChange = new ArrayList(eAnnotation.getContents());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(eAnnotation);
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, false);
    }
    eAnnotation.getContents().remove(eClass0);
    if (callSummarize)
    {
      ChangeDescription changeDescription = changeRecorder.summarize();

      //Tests if the change description has what we expect
      assertEquals(1, changeDescription.getObjectsToAttach().size());
      assertEquals(0, changeDescription.getObjectsToDetach().size());
      assertEquals(eClass0, changeDescription.getObjectsToAttach().get(0));
      EMap objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      assertTrue(objectChanges.get(eAnnotation) instanceof EList);
      EList featureChanges = (EList)objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = (FeatureChange)featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = (ListChange)featureChange.getListChanges().get(0);
      assertEquals(ChangeKind.ADD_LITERAL, listChange.getKind());
      assertEquals(1, listChange.getReferenceValues().size());
      assertEquals(eClass0, listChange.getReferenceValues().get(0));
      
      applyCheck(changeRecorder.summarize(), beforeChange, true);
    }
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    if(!callSummarize)
    {
      //Tests if the change description has what we expect
      assertEquals(1, changeDescription.getObjectsToAttach().size());
      assertEquals(0, changeDescription.getObjectsToDetach().size());
      assertEquals(eClass0, changeDescription.getObjectsToAttach().get(0));
      EMap objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      assertTrue(objectChanges.get(eAnnotation) instanceof EList);
      EList featureChanges = (EList)objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = (FeatureChange)featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = (ListChange)featureChange.getListChanges().get(0);
      assertEquals(ChangeKind.ADD_LITERAL, listChange.getKind());
      assertEquals(1, listChange.getReferenceValues().size());
      assertEquals(eClass0, listChange.getReferenceValues().get(0));
    }
    
    applyCheck(changeDescription, beforeChange, !callSummarize);
  }  

  public void testAddElementAndApply()
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");    
    
    List beforeChange = new ArrayList(eAnnotation.getContents());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(eAnnotation);
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, false);
    }
    eAnnotation.getContents().add(eClass1);
    if (callSummarize)
    {
      ChangeDescription changeDescription = changeRecorder.summarize();
      
      //Tests if the change description has what we expect
      assertEquals(0, changeDescription.getObjectsToAttach().size());
      assertEquals(1, changeDescription.getObjectsToDetach().size());
      assertEquals(eClass1, changeDescription.getObjectsToDetach().get(0));
      EMap objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      assertTrue(objectChanges.get(eAnnotation) instanceof EList);
      EList featureChanges = (EList)objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = (FeatureChange)featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = (ListChange)featureChange.getListChanges().get(0);
      assertEquals(ChangeKind.REMOVE_LITERAL, listChange.getKind());
      assertEquals(1, listChange.getIndex());
      
      applyCheck(changeRecorder.summarize(), beforeChange, true);
    }
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    if (!callSummarize)
    {
      //Tests if the change description has what we expect
      assertEquals(0, changeDescription.getObjectsToAttach().size());
      assertEquals(1, changeDescription.getObjectsToDetach().size());
      assertEquals(eClass1, changeDescription.getObjectsToDetach().get(0));
      EMap objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      assertTrue(objectChanges.get(eAnnotation) instanceof EList);
      EList featureChanges = (EList)objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = (FeatureChange)featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = (ListChange)featureChange.getListChanges().get(0);
      assertEquals(ChangeKind.REMOVE_LITERAL, listChange.getKind());
      assertEquals(1, listChange.getIndex());
    }

    applyCheck(changeDescription, beforeChange, !callSummarize);
  } 
  
  public void testMoveElementAndApply()
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");  
    
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    
    eAnnotation.getContents().add(eClass1);
    
    List beforeChange = new ArrayList(eAnnotation.getContents());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(eAnnotation);
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, false);
    }
    eAnnotation.getContents().move(4, 1);
    if (callSummarize)
    {
      ChangeDescription changeDescription = changeRecorder.summarize();
        
      //Tests if the change description has what we expect
      assertEquals(0, changeDescription.getObjectsToAttach().size());
      assertEquals(0, changeDescription.getObjectsToDetach().size());
      EMap objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      assertTrue(objectChanges.get(eAnnotation) instanceof EList);
      EList featureChanges = (EList)objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = (FeatureChange)featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = (ListChange)featureChange.getListChanges().get(0);
      assertEquals(ChangeKind.MOVE_LITERAL, listChange.getKind());
      if(listChange.getIndex() == 1)
      {
        assertEquals(4, listChange.getMoveToIndex());
      }
      else
      {
        assertEquals(4, listChange.getIndex());
        assertEquals(1, listChange.getMoveToIndex());
      }

      applyCheck(changeDescription, beforeChange, true);      
    }
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    if (!callSummarize)
    {
      //Tests if the change description has what we expect
      assertEquals(0, changeDescription.getObjectsToAttach().size());
      assertEquals(0, changeDescription.getObjectsToDetach().size());
      EMap objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      assertTrue(objectChanges.get(eAnnotation) instanceof EList);
      EList featureChanges = (EList)objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = (FeatureChange)featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = (ListChange)featureChange.getListChanges().get(0);
      assertEquals(ChangeKind.MOVE_LITERAL, listChange.getKind());
      if(listChange.getIndex() == 1)
      {
        assertEquals(4, listChange.getMoveToIndex());
      }
      else
      {
        assertEquals(4, listChange.getIndex());
        assertEquals(1, listChange.getMoveToIndex());
      }
    }

    applyCheck(changeDescription, beforeChange, !callSummarize);
  }
  
  public void testApply()
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");
    eAnnotation.getContents().add(eClass1);
    
    List beforeChange = new ArrayList(eAnnotation.getContents());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(eAnnotation);
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, false);
    }
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, true);
    }
    eAnnotation.getContents().move(0, 1);
    eAnnotation.getContents().remove(0);
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, true);
    }
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().remove(1);
    eAnnotation.getContents().move(callSummarize?1:3, callSummarize?0:1);
    if (callSummarize)
    {
      applyCheck(changeRecorder.summarize(), beforeChange, true);
    }
    eAnnotation.getContents().remove(callSummarize?1:3);
    
    applyCheck(changeRecorder.endRecording(), beforeChange, true);
  }
  
  public void testApplyAndReverse()
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");
    eAnnotation.getContents().add(eClass1);
    
    List beforeChange = new ArrayList(eAnnotation.getContents());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(eAnnotation);
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().move(0, 1);
    eAnnotation.getContents().remove(0);
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().remove(1);
    eAnnotation.getContents().move(callSummarize?1:3, callSummarize?0:1);
    eAnnotation.getContents().remove(callSummarize?1:3);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();   
    List afterChange = new ArrayList(eAnnotation.getContents());
    
    //current != before && current == after
    assertFalse(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
    assertTrue(TestUtil.areEqual(afterChange, eAnnotation.getContents()));

    changeDescription.applyAndReverse();
    
    //current == before && current != after
    assertTrue(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
    assertFalse(TestUtil.areEqual(afterChange, eAnnotation.getContents()));
    
    changeDescription.applyAndReverse();

    //current != before && current == after
    assertFalse(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
    assertTrue(TestUtil.areEqual(afterChange, eAnnotation.getContents()));
    
    changeDescription.apply();
    
    //current == before && current != after
    assertTrue(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
    assertFalse(TestUtil.areEqual(afterChange, eAnnotation.getContents()));
  }

  /*
   * bugzilla 68310
   */
  public void testReuse()
  {
    ChangeRecorder changeRecorder = new ChangeRecorder();
    changeRecorder.beginRecording(Collections.singleton(resourceSet));
    if (callSummarize) changeRecorder.summarize();
    eClass0.setName("Test0");
    if (callSummarize) changeRecorder.summarize();
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertEquals(1, changeDescription.getObjectChanges().size());
    
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");
    eAnnotation.getContents().add(eClass1);
    
    changeRecorder.beginRecording(Collections.singleton(resourceSet));
    eClass1.setName("test1");
    changeDescription = changeRecorder.endRecording();
    
    assertEquals(1, changeDescription.getObjectChanges().size());
  }
  
  public void testResource()
  {
    Resource resource = (Resource)resourceSet.getResources().get(0);
    assertEquals(1, resource.getContents().size());
    EPackage ePackage = (EPackage)resource.getContents().get(0);
    
    EPackage newPackage = EcoreFactory.eINSTANCE.createEPackage();
    newPackage.setName("name0");

    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    resource.getContents().set(0, newPackage);
    if (callSummarize) changeRecorder.summarize();
    newPackage.setName("name1");
    resource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertEquals(2, resource.getContents().size());
    assertEquals(newPackage, resource.getContents().get(0));
    assertEquals("name1", newPackage.getName());
    assertTrue(eClass0 != resource.getContents().get(1));
    
    assertEquals(1, changeDescription.getResourceChanges().size());
    ResourceChange resourceChange = (ResourceChange)changeDescription.getResourceChanges().get(0);
    assertEquals(3, resourceChange.getListChanges().size());
    
    int hasCorrectKinds = 0;
    for (Iterator i = resourceChange.getListChanges().iterator(); i.hasNext();)
    {
      ListChange listChange = (ListChange)i.next();
      switch(listChange.getKind().getValue())
      {
        case ChangeKind.REMOVE:
          hasCorrectKinds += 1;
          break;
        case ChangeKind.ADD:
          hasCorrectKinds += 5;
          break;
      }
    }
    assertEquals(7, hasCorrectKinds);
    
    assertEquals(1, changeDescription.getObjectChanges().size());
    
    changeDescription.apply();
    assertEquals(1, resource.getContents().size());
    assertEquals(ePackage, resource.getContents().get(0));
    assertEquals("name0", newPackage.getName());
  }
  
  public void testAttribute()
  {
    String previousName = eClass0.getName();
    Object previousSource = eAnnotation.getSource();
    
    Resource resource = (Resource)resourceSet.getResources().get(0);
    URI previousURI = resource.getURI();
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    if (callSummarize) changeRecorder.summarize();
    eClass0.setName("newName");
    eAnnotation.setSource("new Source");
    if (callSummarize) changeRecorder.summarize();
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertEquals(2, changeDescription.getObjectChanges().size());
    
    assertFalse(previousName.equals(eClass0.getName()));
    assertFalse(eAnnotation.getSource().equals(previousSource));
    
    changeDescription.apply();
    
    assertEquals(previousName, eClass0.getName());
    assertEquals(previousSource, eAnnotation.getSource());    
  }
  
  protected void applyCheck(ChangeDescription changeDescription, List beforeChange, boolean initialListAreDifferent)
  {
    if (initialListAreDifferent)
    {
      //Tests if the lists are different
      assertFalse(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
    }
    else
    {
      //Tests if the lists are equal
      assertTrue(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));      
    }
    
    changeDescription.apply(); 
    
    //Tests if the change description was reset
//    assertEquals(0, changeDescription.getObjectChanges().size());
//    assertEquals(0, changeDescription.getObjectsToAttach().size());
    
    //Tests if the list was rolled back
    assertTrue(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
    
    // Another apply shouldn't change anything
    changeDescription.apply(); 
    assertTrue(TestUtil.areEqual(beforeChange, eAnnotation.getContents()));
  }
  
  /*
   * Bugzilla 81013
   */  
  public void testResumeRecording() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.eINSTANCE.getEString());
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.eINSTANCE.getEString());
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    EObject peter = pack.getEFactoryInstance().create(person);
    peter.eSet(name, "Peter");
    ((List)john.eGet(friendsReference)).add(peter);
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(john);
    
    // State 0
    assertEquals(1, resource.getContents().size());
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(peter, ((List)john.eGet(friendsReference)).get(0));
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    ((List)john.eGet(friendsReference)).add(mary);
    ((List)john.eGet(friendsReference)).remove(peter);
    ChangeDescription changeDescription = callSummarize ? changeRecorder.endRecording() : changeRecorder.summarize();

    // State 1
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(mary, ((List)john.eGet(friendsReference)).get(0));
    assertEquals(1, changeDescription.getObjectsToDetach().size());
    assertEquals(mary, changeDescription.getObjectsToDetach().get(0));
    
    changeRecorder = new ChangeRecorder();
    // changeRecorder.beginRecording(changeDescription, Arrays.asList(new Object[]{john}));
    mary.eSet(name, "Mary P");
    john.eSet(id, "789");
    EObject joe = pack.getEFactoryInstance().create(person);
    joe.eSet(name, "Joe");
    ((List)john.eGet(friendsReference)).add(joe);
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 2
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("789", john.eGet(id));
    assertEquals("Mary P", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals(2, ((List)john.eGet(friendsReference)).size());
    assertEquals(mary, ((List)john.eGet(friendsReference)).get(0));
    assertEquals(joe, ((List)john.eGet(friendsReference)).get(1));
    assertEquals(2, changeDescription.getObjectsToDetach().size());
    assertEquals(mary, changeDescription.getObjectsToDetach().get(0));
    assertEquals(joe, changeDescription.getObjectsToDetach().get(1));
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(peter, ((List)john.eGet(friendsReference)).get(0));
    assertEquals(1, changeDescription.getObjectsToDetach().size());
    assertEquals(peter, changeDescription.getObjectsToDetach().get(0));
    
    changeDescription.applyAndReverse();
    
    // State 2
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("789", john.eGet(id));
    assertEquals("Mary P", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals(2, ((List)john.eGet(friendsReference)).size());
    assertEquals(mary, ((List)john.eGet(friendsReference)).get(0));
    assertEquals(joe, ((List)john.eGet(friendsReference)).get(1));
    assertEquals(2, changeDescription.getObjectsToDetach().size());
    assertEquals(mary, changeDescription.getObjectsToDetach().get(0));
    assertEquals(joe, changeDescription.getObjectsToDetach().get(1));
    
    // changeRecorder.beginRecording(changeDescription, Arrays.asList(new Object[]{resource, john, mary, joe}));
    mary.eSet(name, "Mary Po");
    john.eSet(id, "0");
    EObject jane = pack.getEFactoryInstance().create(person);
    jane.eSet(name, "Jane");
    ((List)john.eGet(friendsReference)).add(jane);
    ((List)john.eGet(friendsReference)).remove(mary);
    ((List)john.eGet(friendsReference)).remove(joe);
    resource.getContents().remove(0);
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 3
    assertTrue(resource.getContents().isEmpty());
    assertEquals("John", john.eGet(name));
    assertEquals("0", john.eGet(id));
    assertEquals("Mary Po", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("Jane", jane.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(jane, ((List)john.eGet(friendsReference)).get(0));
    assertEquals(3, changeDescription.getObjectsToDetach().size());
    assertEquals(mary, changeDescription.getObjectsToDetach().get(0));
    assertEquals(joe, changeDescription.getObjectsToDetach().get(1));
    assertEquals(jane, changeDescription.getObjectsToDetach().get(2));
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(peter, ((List)john.eGet(friendsReference)).get(0));

    changeDescription.apply();

    // State 3
    assertTrue(resource.getContents().isEmpty());
    assertEquals("John", john.eGet(name));
    assertEquals("0", john.eGet(id));
    assertEquals("Mary Po", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("Jane", jane.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(jane, ((List)john.eGet(friendsReference)).get(0));

    // changeRecorder.beginRecording(changeDescription, Arrays.asList(new Object[]{john, mary}));
    mary.eSet(name, "Mary Pop");
    john.eSet(id, "1");
    jane.eSet(id, "2");
    ((List)john.eGet(friendsReference)).set(0, joe);
    joe.eSet(id, "123");
    ((List)john.eGet(friendsReference)).add(jane);
    resource.getContents().add(mary);
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 4
    assertEquals(1, resource.getContents().size());
    assertEquals(mary, resource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("1", john.eGet(id));
    assertEquals("Mary Pop", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("123", joe.eGet(id));
    assertEquals("Jane", jane.eGet(name));
    assertEquals("2", jane.eGet(id));
    assertEquals(2, ((List)john.eGet(friendsReference)).size());
    assertEquals(joe, ((List)john.eGet(friendsReference)).get(0));
    assertEquals(jane, ((List)john.eGet(friendsReference)).get(1));
    
    changeDescription.applyAndReverse();   
    
    // State 3 (apply resets the ChangeDescription)
    assertTrue(resource.getContents().isEmpty());
    assertEquals("John", john.eGet(name));
    assertEquals("0", john.eGet(id));
    assertEquals("Mary Po", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("Jane", jane.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(jane, ((List)john.eGet(friendsReference)).get(0));
  }
  
  /*
   * Bugzilla 81013
   */
  public void testResumeSerializedRecording() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.eINSTANCE.getEString());
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.eINSTANCE.getEString());
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");

    URI johnURI = URI.createFileURI(TestUtil.getPluginDirectory() + "/johnTRSR.xmi");
    URI changeDescriptionURI = URI.createFileURI(TestUtil.getPluginDirectory() + "/changeDescriptionTRSR.xmi");    
    
    XMLResource johnResource = new XMLResourceImpl(johnURI);
    johnResource.getContents().add(john);
    
    // State 0
    assertEquals(1, johnResource.getContents().size());
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertTrue(((List)john.eGet(friendsReference)).isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    ((List)john.eGet(friendsReference)).add(mary);
    ChangeDescription changeDescription = callSummarize ? changeRecorder.endRecording() : changeRecorder.summarize();
    
    XMLResource changeDescriptionResource = new XMLResourceImpl(changeDescriptionURI);
    changeDescriptionResource.getContents().add(changeDescription);
    
    // State 1
    assertEquals(1, johnResource.getContents().size());
    assertEquals(john, johnResource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, ((List)john.eGet(friendsReference)).size());
    assertEquals(mary, ((List)john.eGet(friendsReference)).get(0));

    johnResource.save(null);
    changeDescriptionResource.save(null);
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    
    Resource loadedJohnResource = resourceSet.getResource(johnURI, true);
    EObject loadedJohn = (EObject)loadedJohnResource.getContents().get(0);
    EObject loadedMary = (EObject)((List)loadedJohn.eGet(friendsReference)).get(0);
    Resource loadedChangeDescriptionResource = resourceSet.getResource(changeDescriptionURI, true);
    ChangeDescription loadedChangeDescription = (ChangeDescription)loadedChangeDescriptionResource.getContents().get(0);
    
    // State 1
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertEquals("John", loadedJohn.eGet(name));
    assertEquals("456", loadedJohn.eGet(id));
    assertEquals("Mary", loadedMary.eGet(name));
    assertEquals(1, ((List)loadedJohn.eGet(friendsReference)).size());
    assertEquals(loadedMary, ((List)loadedJohn.eGet(friendsReference)).get(0));
    
    loadedChangeDescription.applyAndReverse();
    
    // State 0
    assertEquals(1, loadedJohnResource.getContents().size());
    assertNull(loadedJohn.eGet(name));
    assertEquals("123", loadedJohn.eGet(id));
    assertEquals("Mary", loadedMary.eGet(name));
    assertTrue(((List)loadedJohn.eGet(friendsReference)).isEmpty());
    
    // changeRecorder.beginRecording(loadedChangeDescription, Arrays.asList(new Object[]{loadedJohn, loadedMary}));
    loadedMary.eSet(name, "Mary P");
    loadedJohn.eSet(id, "789");
    EObject joe = pack.getEFactoryInstance().create(person);
    joe.eSet(name, "Joe");
    ((List)loadedJohn.eGet(friendsReference)).add(joe);    
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 2
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertNull(loadedJohn.eGet(name));
    assertEquals("789", loadedJohn.eGet(id));
    assertEquals("Mary P", loadedMary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals(1, ((List)loadedJohn.eGet(friendsReference)).size());
    assertEquals(joe, ((List)loadedJohn.eGet(friendsReference)).get(0));
    
    loadedChangeDescription.applyAndReverse();

    // State 1
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertEquals("John", loadedJohn.eGet(name));
    assertEquals("456", loadedJohn.eGet(id));
    assertEquals("Mary", loadedMary.eGet(name));
    assertEquals(1, ((List)loadedJohn.eGet(friendsReference)).size());
    assertEquals(loadedMary, ((List)loadedJohn.eGet(friendsReference)).get(0));


    EObject aVeryDifferentPerson = pack.getEFactoryInstance().create(person);
    aVeryDifferentPerson.eSet(id, "123");
    assertEquals("123", aVeryDifferentPerson.eGet(id));
    
    // changeRecorder.beginRecording(loadedChangeDescription, Arrays.asList(new Object[]{aVeryDifferentPerson}));
    aVeryDifferentPerson.eSet(id, "1");
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    assertEquals("1", aVeryDifferentPerson.eGet(id));

    loadedChangeDescription.applyAndReverse();
    
    assertEquals("123", aVeryDifferentPerson.eGet(id));
    // State 2
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertNull(loadedJohn.eGet(name));
    assertEquals("789", loadedJohn.eGet(id));
    assertEquals("Mary P", loadedMary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals(1, ((List)loadedJohn.eGet(friendsReference)).size());
    assertEquals(joe, ((List)loadedJohn.eGet(friendsReference)).get(0));
    
    File file = new File(johnURI.toFileString());
    file.delete();
    assertFalse(file.exists());
    file = new File(changeDescriptionURI.toFileString());
    file.delete();
    assertFalse(file.exists());    
  }
}