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
 * $Id: ChangeReportTest.java,v 1.11 2004/10/25 20:53:26 marcelop Exp $
 */
package org.eclipse.emf.test.core.change;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.test.core.EMFTestCorePlugin;


public class ChangeReportTest
extends TestCase
{
  private boolean callSummarize = false;
  
  private ResourceSet resourceSet;
  private EAnnotation eAnnotation;
  private EClass eClass0;
  
  public ChangeReportTest(String name)
  {
    super(name);
  }
  
  public ChangeReportTest(String name, boolean callSummarize)
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
    ts.addTest(new ChangeReportTest("testResource", callSummarize));
    ts.addTest(new ChangeReportTest("testAttribute", callSummarize));
    ts.addTest(new ChangeReportTest("testReuse", callSummarize));
    ts.addTest(new ChangeReportTest("testSetElement", callSummarize));
    ts.addTest(new ChangeReportTest("testRemoveElementAndApply", callSummarize));
    ts.addTest(new ChangeReportTest("testAddElementAndApply", callSummarize));
    ts.addTest(new ChangeReportTest("testMoveElementAndApply", callSummarize));
    ts.addTest(new ChangeReportTest("testApply", callSummarize));
    ts.addTest(new ChangeReportTest("testApplyAndReverse", callSummarize));
    ts.addTest(new ChangeReportTest("testMultipleApplyAndReverse", callSummarize));
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
    assertFalse(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));
    assertTrue(EMFTestCorePlugin.areEqual(afterChange, eAnnotation.getContents()));

    changeDescription.applyAndReverse();
    
    //current == before && current != after
    assertTrue(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));
    assertFalse(EMFTestCorePlugin.areEqual(afterChange, eAnnotation.getContents()));
    
    changeDescription.applyAndReverse();

    //current != before && current == after
    assertFalse(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));
    assertTrue(EMFTestCorePlugin.areEqual(afterChange, eAnnotation.getContents()));
    
    changeDescription.apply();
    
    //current == before && current != after
    assertTrue(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));
    assertFalse(EMFTestCorePlugin.areEqual(afterChange, eAnnotation.getContents()));
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
      assertFalse(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));
    }
    else
    {
      //Tests if the lists are equal
      assertTrue(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));      
    }
    
    changeDescription.apply(); 
    
    //Tests if the change description was reset
    assertEquals(0, changeDescription.getObjectChanges().size());
    assertEquals(0, changeDescription.getObjectsToAttach().size());
    
    //Tests if the list was rolled back
    assertTrue(EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));        
  }
  
  /*
   * Bugzilla 76971
   */
  public void testMultipleApplyAndReverse() throws Exception
  {
    eAnnotation.getContents().clear();
    
    List beforeChange = new ArrayList(eAnnotation.getContents());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().add(EcoreFactory.eINSTANCE.createEClass());
    eAnnotation.getContents().move(0, 1);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    Resource resource = new XMIResourceImpl();
    resource.getContents().add(changeDescription);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    String[] xmi = new String[2];
    
    List afterChange = new ArrayList(eAnnotation.getContents());
    
    for(int i=1; i<=20; i++)
    {
      baos.reset();
      resource.save(baos, null);
      switch(i)
      {
        case 1:
          xmi[1] = new String(baos.toByteArray());
          break;
        case 2:
          xmi[0] = new String(baos.toByteArray());
          break;
        default:
          String newXMI = new String(baos.toByteArray());
          assertEquals("Comparing iteration: " + i, xmi[i%2], newXMI);
          xmi[i%2] = newXMI;
      }
      
      assertEquals(i%2 == 0, EMFTestCorePlugin.areEqual(beforeChange, eAnnotation.getContents()));
      assertEquals(i%2 != 0, EMFTestCorePlugin.areEqual(afterChange, eAnnotation.getContents()));

      changeDescription.applyAndReverse();
    }
  }
}