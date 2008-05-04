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
 * $Id: ChangeRecordTest.java,v 1.19 2008/05/04 10:59:07 emerks Exp $
 */
package org.eclipse.emf.test.core.change;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;


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
    String name = "ChangeReportTest";
    if (callSummarize)
    {
      name = name + " - callSummarize:" + callSummarize;
    }
    
    TestSuite ts = new TestSuite(name);
    ts.addTest(new ChangeRecordTest("testResource", callSummarize));
    ts.addTest(new ChangeRecordTest("testAttribute", callSummarize));
    ts.addTest(new ChangeRecordTest("testReuse", callSummarize));
    ts.addTest(new ChangeRecordTest("testSetElement", callSummarize));
    ts.addTest(new ChangeRecordTest("testRemoveElementAndApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testAddElementAndApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testMoveElementAndApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testApply", callSummarize));
    ts.addTest(new ChangeRecordTest("testApplyAndReverse", callSummarize));
    ts.addTest(new ChangeRecordTest("testResumeRecording", callSummarize));
    ts.addTest(new ChangeRecordTest("testResumeSerializedRecording", callSummarize));
    if (!callSummarize)
    {
      ts.addTest(new ChangeRecordTest("testResourceSetChanges1"));
      ts.addTest(new ChangeRecordTest("testResourceSetChanges2"));
      ts.addTest(new ChangeRecordTest("testResourceSetChanges3"));
      ts.addTest(new ChangeRecordTest("testUnsettableList"));
      ts.addTest(new ChangeRecordTest("testMultipleLoads"));
      ts.addTest(new ChangeRecordTest("testESuperTypes"));
      ts.addTest(new ChangeRecordTest("testEGenericSuperTypes1"));
      ts.addTest(new ChangeRecordTest("testEType"));
      ts.addTest(new ChangeRecordTest("testEException"));
    }
    return ts;
  }
  
  
  @Override
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
    
    EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges();
    assertEquals(1, objectChanges.size());
    assertTrue(objectChanges.containsKey(eAnnotation));
    assertEquals(1, objectChanges.values().size());
    
    EList<FeatureChange> eList = objectChanges.values().iterator().next();
    assertEquals(1, eList.size());
    
    FeatureChange featureChange = eList.iterator().next();
    assertEquals(EcorePackage.Literals.EANNOTATION__CONTENTS, featureChange.getFeature());
    assertEquals(2, featureChange.getListChanges().size());
    
    int checker = 0;
    for (ListChange listChange : featureChange.getListChanges())
    {
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
    
    List<EObject> beforeChange = new ArrayList<EObject>(eAnnotation.getContents());
    
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
      EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      EList<FeatureChange> featureChanges = objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = featureChange.getListChanges().get(0);
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
      EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      EList<FeatureChange> featureChanges = objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = featureChange.getListChanges().get(0);
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
    
    List<EObject> beforeChange = new ArrayList<EObject>(eAnnotation.getContents());
    
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
      EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      EList<FeatureChange> featureChanges = objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = featureChange.getListChanges().get(0);
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
      EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      EList<FeatureChange> featureChanges = objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = featureChange.getListChanges().get(0);
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
    
    List<EObject> beforeChange = new ArrayList<EObject>(eAnnotation.getContents());
    
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
      EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      EList<FeatureChange> featureChanges = objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = featureChange.getListChanges().get(0);
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
      EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges(); 
      assertEquals(1, objectChanges.size());
      EList<FeatureChange> featureChanges = objectChanges.get(eAnnotation);
      assertEquals(1, featureChanges.size());
      FeatureChange featureChange = featureChanges.get(0);
      assertEquals(1, featureChange.getListChanges().size());
      ListChange listChange = featureChange.getListChanges().get(0);
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
    
    List<EObject> beforeChange = new ArrayList<EObject>(eAnnotation.getContents());
    
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
    
    List<EObject> beforeChange = new ArrayList<EObject>(eAnnotation.getContents());
    
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
    List<EObject> afterChange = new ArrayList<EObject>(eAnnotation.getContents());
    
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
    Resource resource = resourceSet.getResources().get(0);
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
    ResourceChange resourceChange = changeDescription.getResourceChanges().get(0);
    assertEquals(3, resourceChange.getListChanges().size());
    
    int hasCorrectKinds = 0;
    for (ListChange listChange : resourceChange.getListChanges())
    {
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
  
  protected void applyCheck(ChangeDescription changeDescription, List<EObject> beforeChange, boolean initialListAreDifferent)
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
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.Literals.ESTRING);
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
    @SuppressWarnings("unchecked")
    List<EObject> friendsOfJohn = ((List<EObject>)john.eGet(friendsReference));
    friendsOfJohn.add(peter);
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(john);
    
    // State 0
    assertEquals(1, resource.getContents().size());
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(peter, friendsOfJohn.get(0));
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    friendsOfJohn.add(mary);
    friendsOfJohn.remove(peter);
    ChangeDescription changeDescription = callSummarize ? changeRecorder.endRecording() : changeRecorder.summarize();

    // State 1
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
    assertEquals(1, changeDescription.getObjectsToDetach().size());
    assertEquals(mary, changeDescription.getObjectsToDetach().get(0));
    
    changeRecorder = new ChangeRecorder();
    changeRecorder.beginRecording(changeDescription, Arrays.asList(new Object[]{john}));
    mary.eSet(name, "Mary P");
    john.eSet(id, "789");
    EObject joe = pack.getEFactoryInstance().create(person);
    joe.eSet(name, "Joe");
    friendsOfJohn.add(joe);
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 2
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("789", john.eGet(id));
    assertEquals("Mary P", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals(2, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
    assertEquals(joe, friendsOfJohn.get(1));
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
    assertEquals(1, friendsOfJohn.size());
    assertEquals(peter, friendsOfJohn.get(0));
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
    assertEquals(2, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
    assertEquals(joe, friendsOfJohn.get(1));
    assertEquals(2, changeDescription.getObjectsToDetach().size());
    assertEquals(mary, changeDescription.getObjectsToDetach().get(0));
    assertEquals(joe, changeDescription.getObjectsToDetach().get(1));
    
    changeRecorder.beginRecording(changeDescription, Arrays.asList(new Object[]{resource, john, mary, joe}));
    mary.eSet(name, "Mary Po");
    john.eSet(id, "0");
    EObject jane = pack.getEFactoryInstance().create(person);
    jane.eSet(name, "Jane");
    friendsOfJohn.add(jane);
    // Mary was added when recording, so now she will be removed from the ChangeDescription completely
    friendsOfJohn.remove(mary);
    friendsOfJohn.remove(joe);
    resource.getContents().remove(0);
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 3
    assertTrue(resource.getContents().isEmpty());
    assertEquals("John", john.eGet(name));
    assertEquals("0", john.eGet(id));
    assertEquals("Mary Po", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("Jane", jane.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(jane, friendsOfJohn.get(0));
    assertEquals(1, changeDescription.getObjectsToDetach().size());
    assertEquals(jane, changeDescription.getObjectsToDetach().get(0));
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertEquals(1, resource.getContents().size());
    assertEquals(john, resource.getContents().get(0));
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(peter, friendsOfJohn.get(0));
    assertEquals("Mary Po", mary.eGet(name));

    changeDescription.apply();

    // State 3
    assertTrue(resource.getContents().isEmpty());
    assertEquals("John", john.eGet(name));
    assertEquals("0", john.eGet(id));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("Jane", jane.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(jane, friendsOfJohn.get(0));
    assertEquals("Mary Po", mary.eGet(name));

    changeRecorder.beginRecording(changeDescription, Arrays.asList(new Object[]{john, mary}));
    mary.eSet(name, "Mary Pop");
    john.eSet(id, "1");
    jane.eSet(id, "2");
    friendsOfJohn.set(0, joe);
    joe.eSet(id, "123");
    friendsOfJohn.add(jane);
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
    assertEquals(2, friendsOfJohn.size());
    assertEquals(joe, friendsOfJohn.get(0));
    assertEquals(jane, friendsOfJohn.get(1));
    
    changeDescription.applyAndReverse();   
    
    // State 3 (apply resets the ChangeDescription)
    assertTrue(resource.getContents().isEmpty());
    assertEquals("John", john.eGet(name));
    assertEquals("0", john.eGet(id));
    assertEquals("Mary Po", mary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals("Jane", jane.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(jane, friendsOfJohn.get(0));
  }
  
  /*
   * Bugzilla 81013
   * Bugzilla 122989
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
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(6);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    @SuppressWarnings("unchecked")
    List<EObject> friendsOfJohn = ((List<EObject>)john.eGet(friendsReference));
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");

    URI johnURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/johnTRSR.xmi");
    URI changeDescriptionURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/changeDescriptionTRSR.xmi");    
    
    XMLResource johnResource = new XMLResourceImpl(johnURI);
    johnResource.getContents().add(john);
    
    // State 0
    assertEquals(1, johnResource.getContents().size());
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertTrue(friendsOfJohn.isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    friendsOfJohn.add(mary);
    ChangeDescription changeDescription = callSummarize ? changeRecorder.endRecording() : changeRecorder.summarize();
    
    XMLResource changeDescriptionResource = new XMLResourceImpl(changeDescriptionURI);
    changeDescriptionResource.getContents().add(changeDescription);
    
    // State 1
    assertEquals(1, johnResource.getContents().size());
    assertEquals(john, johnResource.getContents().get(0));
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));

    johnResource.save(null);
    changeDescriptionResource.save(null);
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    
    Resource loadedJohnResource = resourceSet.getResource(johnURI, true);
    EObject loadedJohn = loadedJohnResource.getContents().get(0);
    @SuppressWarnings("unchecked")
    List<EObject> friendsOfLoadedJohn = ((List<EObject>)loadedJohn.eGet(friendsReference));
    EObject loadedMary = friendsOfLoadedJohn.get(0);
    Resource loadedChangeDescriptionResource = resourceSet.getResource(changeDescriptionURI, true);
    ChangeDescription loadedChangeDescription = (ChangeDescription)loadedChangeDescriptionResource.getContents().get(0);
    
    // State 1
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertEquals("John", loadedJohn.eGet(name));
    assertEquals("456", loadedJohn.eGet(id));
    assertEquals("Mary", loadedMary.eGet(name));
    assertEquals(1, friendsOfLoadedJohn.size());
    assertEquals(loadedMary, friendsOfLoadedJohn.get(0));
    
    loadedChangeDescription.applyAndReverse();
    
    // State 0
    assertEquals(1, loadedJohnResource.getContents().size());
    assertNull(loadedJohn.eGet(name));
    assertEquals("123", loadedJohn.eGet(id));
    assertEquals("Mary", loadedMary.eGet(name));
    assertTrue(friendsOfLoadedJohn.isEmpty());
    
    changeRecorder.beginRecording(loadedChangeDescription, Arrays.asList(new Object[]{loadedJohn, loadedMary}));
    loadedMary.eSet(name, "Mary P");
    loadedJohn.eSet(id, "789");
    EObject joe = pack.getEFactoryInstance().create(person);
    joe.eSet(name, "Joe");
    friendsOfLoadedJohn.add(joe);    
    if (callSummarize) changeRecorder.summarize(); else changeRecorder.endRecording();
    
    // State 2
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertNull(loadedJohn.eGet(name));
    assertEquals("789", loadedJohn.eGet(id));
    assertEquals("Mary P", loadedMary.eGet(name));
    assertEquals("Joe", joe.eGet(name));
    assertEquals(1, friendsOfLoadedJohn.size());
    assertEquals(joe, friendsOfLoadedJohn.get(0));
    
    loadedChangeDescription.applyAndReverse();

    // State 1
    assertEquals(1, loadedJohnResource.getContents().size());
    assertEquals(loadedJohn, loadedJohnResource.getContents().get(0));
    assertEquals("John", loadedJohn.eGet(name));
    assertEquals("456", loadedJohn.eGet(id));
    assertEquals("Mary", loadedMary.eGet(name));
    assertEquals(1, friendsOfLoadedJohn.size());
    assertEquals(loadedMary, friendsOfLoadedJohn.get(0));


    EObject aVeryDifferentPerson = pack.getEFactoryInstance().create(person);
    aVeryDifferentPerson.eSet(id, "123");
    assertEquals("123", aVeryDifferentPerson.eGet(id));
    
    changeRecorder.beginRecording(loadedChangeDescription, Arrays.asList(new Object[]{aVeryDifferentPerson}));
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
    assertEquals(1, friendsOfLoadedJohn.size());
    assertEquals(joe, friendsOfLoadedJohn.get(0));
    
    File file = new File(johnURI.toFileString());
    file.delete();
    assertFalse(file.exists());
    file = new File(changeDescriptionURI.toFileString());
    file.delete();
    assertFalse(file.exists());    
  }
  
  /*
   * Bugzilla 136358
   */
  public void testResourceSetChanges1() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();

    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");

    Resource resource1 = new ResourceImpl(URI.createURI("resource1"));
    EClass aClass = EcoreFactory.eINSTANCE.createEClass();
    resource1.getContents().add(aClass);
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    resourceSet.getResources().add(resource1);
    resource1.getContents().add(pack);
    pack.setNsPrefix("prefix");
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    changeRecorder.endRecording();
    
    assertEquals(1, resourceSet.eAdapters().size());
    assertEquals(0, resourceSet.eAdapters().indexOf(changeRecorder));
    assertEquals(1, resource1.eAdapters().size());
    assertEquals(0, resource1.eAdapters().indexOf(changeRecorder));
    assertEquals(1, pack.eAdapters().size());
    assertEquals(0, pack.eAdapters().indexOf(changeRecorder));
    assertEquals(2, person.eAdapters().size());
    assertEquals(1, person.eAdapters().indexOf(changeRecorder));
  }
  
  /*
   * Bugzilla 136358
   */
  public void testResourceSetChanges2() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(new ResourceImpl(URI.createURI("aResource")));

    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");

    Resource resource1 = new ResourceImpl(URI.createURI("resource1"));
    EClass aClass = EcoreFactory.eINSTANCE.createEClass();
    resource1.getContents().add(aClass);
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    resourceSet.getResources().set(0, resource1);
    resource1.getContents().add(pack);
    pack.setNsPrefix("prefix");
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    changeRecorder.endRecording();
    
    assertEquals(1, resourceSet.eAdapters().size());
    assertEquals(0, resourceSet.eAdapters().indexOf(changeRecorder));
    assertEquals(1, resource1.eAdapters().size());
    assertEquals(0, resource1.eAdapters().indexOf(changeRecorder));
    assertEquals(1, pack.eAdapters().size());
    assertEquals(0, pack.eAdapters().indexOf(changeRecorder));
    assertEquals(2, person.eAdapters().size());
    assertEquals(1, person.eAdapters().indexOf(changeRecorder));
  }
  
  /*
   * Bugzilla 136358
   */
  public void testResourceSetChanges3() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();

    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");

    Resource resource1 = new ResourceImpl(URI.createURI("resource1"));
    EClass aClass = EcoreFactory.eINSTANCE.createEClass();
    resource1.getContents().add(aClass);
    
    Resource resource2 = new ResourceImpl(URI.createURI("resource1"));
    EClass bClass = EcoreFactory.eINSTANCE.createEClass();
    resource2.getContents().add(bClass);
    
    List<Resource> list = new ArrayList<Resource>();
    list.add(resource1);
    list.add(resource2);
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    resourceSet.getResources().addAll(list);
    resource1.getContents().add(pack);
    pack.setNsPrefix("prefix");
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    changeRecorder.endRecording();
    
    assertEquals(1, resourceSet.eAdapters().size());
    assertEquals(0, resourceSet.eAdapters().indexOf(changeRecorder));
    assertEquals(1, resource1.eAdapters().size());
    assertEquals(0, resource1.eAdapters().indexOf(changeRecorder));
    assertEquals(1, resource2.eAdapters().size());
    assertEquals(0, resource2.eAdapters().indexOf(changeRecorder));
    assertEquals(1, pack.eAdapters().size());
    assertEquals(0, pack.eAdapters().indexOf(changeRecorder));
    assertEquals(2, person.eAdapters().size());
    assertEquals(1, person.eAdapters().indexOf(changeRecorder));
  }
  
  /*
   * Bugzilla 136653
   */
  public void testUnsettableList() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    person.setName("Person");

    EReference friends = EcoreFactory.eINSTANCE.createEReference();
    person.getEStructuralFeatures().add(friends);
    friends.setName("friends");
    friends.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    friends.setUnsettable(true);
    friends.setContainment(true);
    friends.setEType(person);
    
    EObject john = EcoreUtil.create(person);
    @SuppressWarnings("unchecked")
    List<EObject> johnsFriends = (List<EObject>)john.eGet(friends);
    assertTrue(johnsFriends.isEmpty());
    assertFalse(john.eIsSet(friends));
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eUnset(friends);
    changeRecorder.endRecording();
    
    assertTrue(johnsFriends.isEmpty());
    assertFalse(john.eIsSet(friends));

    EObject mary = EcoreUtil.create(person);
    johnsFriends.add(mary);
    assertEquals(1, johnsFriends.size());
    assertEquals(mary, johnsFriends.get(0));
    assertTrue(john.eIsSet(friends));
    
    changeRecorder = new ChangeRecorder(john);
    john.eUnset(friends);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    assertTrue(johnsFriends.isEmpty());
    assertFalse(john.eIsSet(friends));

    changeDescription.apply();
    
    assertEquals(1, johnsFriends.size());
    assertEquals(mary, johnsFriends.get(0));
    assertTrue(john.eIsSet(friends));    
  }
  
  public void testMultipleLoads() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");

    Resource resource1 = new XMLResourceImpl(URI.createFileURI("resource1.xml"));
    EClass aClass = EcoreFactory.eINSTANCE.createEClass();
    resource1.getContents().add(aClass);
    
    ByteArrayOutputStream baosResource1 = new ByteArrayOutputStream();
    resource1.save(baosResource1, null);
    
    Resource resource2 = new XMLResourceImpl(URI.createFileURI("resource2.xml"));
    EClass bClass = EcoreFactory.eINSTANCE.createEClass();
    resource2.getContents().add(bClass);

    ByteArrayOutputStream baosResource2 = new ByteArrayOutputStream();
    resource2.save(baosResource2, null);
    
    Resource resource3 = new XMLResourceImpl(URI.createFileURI("resource3.xml"));
    EClass cClass = EcoreFactory.eINSTANCE.createEClass();
    resource3.getContents().add(cClass);

    ByteArrayOutputStream baosResource3 = new ByteArrayOutputStream();
    resource3.save(baosResource3, null);

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    
    resource1 = resourceSet.createResource(resource1.getURI());        
    resource1.load(new ByteArrayInputStream(baosResource1.toByteArray()), null);
    aClass = (EClass)resource1.getContents().get(0);
    
    class MyChangeRecorder extends ChangeRecorder
    {
      public MyChangeRecorder(ResourceSet resourceSet)
      {
        super(resourceSet);
      }
      
      public List<Notifier> getOriginalTargetObjects()
      {
        return originalTargetObjects;
      }
    }
    
    MyChangeRecorder changeRecorder = new MyChangeRecorder(resourceSet);
    resource2 = resourceSet.createResource(resource2.getURI());
    resource2.load(new ByteArrayInputStream(baosResource2.toByteArray()), null);
    bClass = (EClass)resource2.getContents().get(0);
    changeRecorder.endRecording();
    
    assertTrue(resource1.eAdapters().contains(changeRecorder));
    assertTrue(aClass.eAdapters().contains(changeRecorder));
    assertTrue(changeRecorder.getOriginalTargetObjects().contains(resource1));
    assertTrue(changeRecorder.getOriginalTargetObjects().contains(aClass));
    
    assertTrue(resource2.eAdapters().contains(changeRecorder));
    assertTrue(bClass.eAdapters().contains(changeRecorder));
    assertTrue(changeRecorder.getOriginalTargetObjects().contains(resource2));
    assertTrue(changeRecorder.getOriginalTargetObjects().contains(bClass));

    resource3 = resourceSet.createResource(resource3.getURI());
    resource3.load(new ByteArrayInputStream(baosResource3.toByteArray()), null);
    cClass = (EClass)resource2.getContents().get(0);

    assertTrue(resource3.eAdapters().contains(changeRecorder));
    assertTrue(cClass.eAdapters().contains(changeRecorder));
    assertTrue(changeRecorder.getOriginalTargetObjects().contains(resource3));
    assertTrue(changeRecorder.getOriginalTargetObjects().contains(cClass));
  }
  
  /*
   * Bugzilla 168891
   */
  public void testESuperTypes() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource res1 = resourceSet.createResource(URI.createURI("null://res1.ecore")); //$NON-NLS-1$
    Resource res2 = resourceSet.createResource(URI.createURI("null://res2.ecore")); //$NON-NLS-1$
    
    EPackage pkg1 = EcoreFactory.eINSTANCE.createEPackage();
    pkg1.setName("package1"); //$NON-NLS-1$
    EClass class1 = EcoreFactory.eINSTANCE.createEClass();
    class1.setName("Class1"); //$NON-NLS-1$
    EClass class2 = EcoreFactory.eINSTANCE.createEClass();
    class2.setName("Class2"); //$NON-NLS-1$
    
    res1.getContents().add(pkg1);
    pkg1.getEClassifiers().add(class1);
    pkg1.getEClassifiers().add(class2);
    res2.getContents().add(class2);
    
    // State 0
    assertTrue(class2.getESuperTypes().isEmpty());
    assertTrue(class2.getEGenericSuperTypes().isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    class2.getESuperTypes().add(class1);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // State 1
    assertEquals(1, class2.getESuperTypes().size());
    assertEquals(class1, class2.getESuperTypes().get(0));
    assertEquals(1, class2.getEGenericSuperTypes().size());
    assertEquals(class1, class2.getEGenericSuperTypes().get(0).getEClassifier());
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertTrue(class2.getESuperTypes().isEmpty());
    assertTrue(class2.getEGenericSuperTypes().isEmpty());
    
    changeDescription.applyAndReverse();
    
    // State 1
    assertEquals(1, class2.getESuperTypes().size());
    assertEquals(class1, class2.getESuperTypes().get(0));
    assertEquals(1, class2.getEGenericSuperTypes().size());
    assertEquals(class1, class2.getEGenericSuperTypes().get(0).getEClassifier());
    
    changeDescription.apply();

    // State 0
    assertTrue(class2.getESuperTypes().isEmpty());
    assertTrue(class2.getEGenericSuperTypes().isEmpty());
  }
  
  public void testEGenericSuperTypes1() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource res1 = resourceSet.createResource(URI.createURI("null://res1.ecore")); //$NON-NLS-1$
    Resource res2 = resourceSet.createResource(URI.createURI("null://res2.ecore")); //$NON-NLS-1$
    
    EPackage pkg1 = EcoreFactory.eINSTANCE.createEPackage();
    pkg1.setName("package1"); //$NON-NLS-1$
    EClass class1 = EcoreFactory.eINSTANCE.createEClass();
    class1.setName("Class1"); //$NON-NLS-1$
    ETypeParameter typeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
    typeParameter.setName("E");
    class1.getETypeParameters().add(typeParameter);
    //
    EClass class2 = EcoreFactory.eINSTANCE.createEClass();
    class2.setName("Class2"); //$NON-NLS-1$
    
    res1.getContents().add(pkg1);
    pkg1.getEClassifiers().add(class1);
    pkg1.getEClassifiers().add(class2);
    res2.getContents().add(class2);
    
    EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
    eGenericType.setEClassifier(class1);
    EGenericType typeArgument = EcoreFactory.eINSTANCE.createEGenericType();
    typeArgument.setEClassifier(EcorePackage.Literals.EBOOLEAN);
    eGenericType.getETypeArguments().add(typeArgument);
    
    // State 0
    assertTrue(class2.getESuperTypes().isEmpty());
    assertTrue(class2.getEGenericSuperTypes().isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    class2.getEGenericSuperTypes().add(eGenericType);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // State 1
    assertEquals(1, class2.getESuperTypes().size());
    assertEquals(class1, class2.getESuperTypes().get(0));
    assertEquals(1, class2.getEGenericSuperTypes().size());
    assertEquals(class1, class2.getEGenericSuperTypes().get(0).getEClassifier());
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertTrue(class2.getESuperTypes().isEmpty());
    assertTrue(class2.getEGenericSuperTypes().isEmpty());
    
    changeDescription.applyAndReverse();
    
    // State 1
    assertEquals(1, class2.getESuperTypes().size());
    assertEquals(class1, class2.getESuperTypes().get(0));
    assertEquals(1, class2.getEGenericSuperTypes().size());
    assertEquals(class1, class2.getEGenericSuperTypes().get(0).getEClassifier());
    
    changeDescription.apply();

    // State 0
    assertTrue(class2.getESuperTypes().isEmpty());
    assertTrue(class2.getEGenericSuperTypes().isEmpty());
  }
  
  public void testEType() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource res1 = resourceSet.createResource(URI.createURI("null://res1.ecore")); //$NON-NLS-1$
    
    EPackage pkg1 = EcoreFactory.eINSTANCE.createEPackage();
    pkg1.setName("package1"); //$NON-NLS-1$
    EClass class1 = EcoreFactory.eINSTANCE.createEClass();
    class1.setName("Class1"); //$NON-NLS-1$
    EAttribute att1 = EcoreFactory.eINSTANCE.createEAttribute();
    att1.setName("att1");    
    
    res1.getContents().add(pkg1);
    pkg1.getEClassifiers().add(class1);
    class1.getEStructuralFeatures().add(att1);
    
    // State 0
    assertNull(att1.getEType());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    att1.setEType(EcorePackage.Literals.ESTRING);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // State 1
    assertEquals(EcorePackage.Literals.ESTRING, att1.getEType());
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertNull(att1.getEType());
    
    changeDescription.applyAndReverse();
    
    // State 1
    assertEquals(EcorePackage.Literals.ESTRING, att1.getEType());
    
    changeDescription.apply();

    // State 0
    assertNull(att1.getEType());
  }  

  public void testEException() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource res1 = resourceSet.createResource(URI.createURI("null://res1.ecore")); //$NON-NLS-1$
    
    EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
    eOperation.setName("operation");
    EClass aException = EcoreFactory.eINSTANCE.createEClass();
    aException.setName("AException");
    
    res1.getContents().add(eOperation);
    res1.getContents().add(aException);
    
    // State 0
    assertTrue(eOperation.getEExceptions().isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    eOperation.getEExceptions().add(aException);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // State 1
    assertEquals(1, eOperation.getEExceptions().size());
    assertEquals(aException, eOperation.getEExceptions().get(0));
    
    changeDescription.applyAndReverse();
    
    // State 0
    assertTrue(eOperation.getEExceptions().isEmpty());
    
    changeDescription.applyAndReverse();
    
    // State 1
    assertEquals(1, eOperation.getEExceptions().size());
    assertEquals(aException, eOperation.getEExceptions().get(0));
    
    changeDescription.apply();

    // State 0
    assertTrue(eOperation.getEExceptions().isEmpty());
  }  
}