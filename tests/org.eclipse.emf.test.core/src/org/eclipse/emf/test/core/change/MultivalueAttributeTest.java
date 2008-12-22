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
 * $Id: MultivalueAttributeTest.java,v 1.13 2008/12/22 14:26:10 emerks Exp $
 */
package org.eclipse.emf.test.core.change;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.test.common.TestUtil;

public class MultivalueAttributeTest extends TestCase
{
  private EObject thing;
  private EAttribute manyInt;
  private EAttribute manyString;
  
  public MultivalueAttributeTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("MultivalueAttributeTest");
    ts.addTest(new MultivalueAttributeTest("testMultiValueAttributeChange"));
    ts.addTest(new MultivalueAttributeTest("testApplyAndReverse"));
    ts.addTest(new MultivalueAttributeTest("testSerialization"));
    return ts;
  }
   
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("ThingPack");
    ePackage.setNsURI("http://thing.com");
    EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);

    EClass thingEClass = EcoreFactory.eINSTANCE.createEClass();
    thingEClass.setName("Thing");
    ePackage.getEClassifiers().add(thingEClass);
    
    manyInt = EcoreFactory.eINSTANCE.createEAttribute();
    manyInt.setName("manyInt");
    manyInt.setEType(EcorePackage.Literals.EINT);
    manyInt.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    thingEClass.getEStructuralFeatures().add(manyInt);

    manyString = EcoreFactory.eINSTANCE.createEAttribute();
    manyString.setName("manyString");
    manyString.setEType(EcorePackage.Literals.ESTRING);
    manyString.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    thingEClass.getEStructuralFeatures().add(manyString);
    
    thing = ePackage.getEFactoryInstance().create(thingEClass);
  }
  
  private List<Integer> getManyInt()
  {
    return getManyInt(thing);
  }

  @SuppressWarnings("unchecked")
  private List<Integer> getManyInt(EObject eObject)
  {
    return (List<Integer>)eObject.eGet(manyInt);
  }
  
  private List<String> getManyString()
  {
    return getManyString(thing);
  }

  @SuppressWarnings("unchecked")
  private List<String> getManyString(EObject eObject)
  {
    return (List<String>)eObject.eGet(manyString);
  }  
   
  public void testMultiValueAttributeChange() throws Exception
  {
    getManyInt().add(1);
    
    ChangeRecorder changeRecorder = new ChangeRecorder(thing);
    
    getManyInt().add(2);
    getManyInt().add(3);
    getManyInt().remove(0);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();
        
    assertEquals(1, changeDescription.getObjectChanges().size());
    EMap<EObject, EList<FeatureChange>> objectChanges = changeDescription.getObjectChanges();
    assertEquals(thing, objectChanges.keySet().iterator().next());
    EList<FeatureChange> changes = objectChanges.values().iterator().next();
    assertEquals(1, changes.size());
    FeatureChange featureChange = changes.get(0);
    assertEquals(3, featureChange.getListChanges().size());
    //
    int count = 0;
    for (ListChange listChange : featureChange.getListChanges())
    {
      switch(listChange.getKind().getValue())
      {
        case ChangeKind.ADD:
        {
          assertEquals(0, listChange.getIndex());
          assertEquals(1, listChange.getValues().size());
          assertEquals(1, listChange.getValues().get(0));
          break;
        }
        case ChangeKind.REMOVE:
        {
          count++;
          break;
        }
      }
    }
    assertEquals(2, count);
        
    assertEquals(2, getManyInt().size());
    assertFalse(getManyInt().contains(1));
    changeDescription.apply();
    assertEquals(1, getManyInt().size());
    assertTrue(getManyInt().contains(1));
  }
  
  /*
   * Bugzilla 76825
   */
  public void testApplyAndReverse() throws Exception
  {
    List<Integer> beforeChange = new ArrayList<Integer>(getManyInt());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(thing);
    getManyInt().add(2);
    getManyInt().add(3);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    List<Integer> afterChange = new ArrayList<Integer>(getManyInt());
    
    //current != before && current == after
    assertFalse(TestUtil.areEqual(beforeChange, getManyInt()));
    assertTrue(TestUtil.areEqual(afterChange, getManyInt()));
    
    changeDescription.applyAndReverse();    
    //current == before && current != after
    assertTrue(TestUtil.areEqual(beforeChange, getManyInt()));
    assertFalse(TestUtil.areEqual(afterChange, getManyInt()));    

    changeDescription.apply();    
    //current != before && current == after
    assertFalse(TestUtil.areEqual(beforeChange, getManyInt()));
    assertTrue(TestUtil.areEqual(afterChange, getManyInt()));
  }  

  /*
   * Bugzilla 78840
   */
  public void testSerialization() throws Exception
  {
    getManyInt().add(1);
    getManyString().add("a");
    List<Integer> intBeforeChange = new ArrayList<Integer>(getManyInt());
    List<String> stringBeforeChange = new ArrayList<String>(getManyString());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(thing);
    getManyInt().add(2);
    getManyInt().add(3);
    getManyString().add("b");
    getManyString().add("c");
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    List<Integer> intAfterChange = new ArrayList<Integer>(getManyInt());
    List<String> stringAfterChange = new ArrayList<String>(getManyString());
    
    //current != before && current == after
    assertFalse(TestUtil.areEqual(intBeforeChange, getManyInt()));
    assertTrue(TestUtil.areEqual(intAfterChange, getManyInt()));
    assertFalse(TestUtil.areEqual(stringBeforeChange, getManyString()));
    assertTrue(TestUtil.areEqual(stringAfterChange, getManyString()));

    Resource thingResource = new XMIResourceImpl(URI.createFileURI("thing.xmi"));
    thingResource.getContents().add(thing);
    
    Resource chandeDescriptionResource = new XMIResourceImpl(URI.createFileURI("chandeDescription.xmi"));
    chandeDescriptionResource.getContents().add(changeDescription);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    chandeDescriptionResource.save(baos, null);    
    Resource loadedChangeDescriptionResource = new XMIResourceImpl(URI.createFileURI("chandeDescription.xmi"));
    loadedChangeDescriptionResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    assertEquals(1, loadedChangeDescriptionResource.getContents().size());
    ChangeDescription loadedChangeDescription = (ChangeDescription)loadedChangeDescriptionResource.getContents().get(0);

    baos = new ByteArrayOutputStream();
    thingResource.save(baos, null);    
    Resource loadedThingResource = new XMIResourceImpl(URI.createFileURI("thing.xmi"));
    loadedThingResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    assertEquals(1, loadedThingResource.getContents().size());
    EObject loadedThing = loadedThingResource.getContents().get(0);
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(loadedChangeDescriptionResource);
    resourceSet.getResources().add(loadedThingResource);
    
    //loadedThing: current != before && current == after
    assertFalse(TestUtil.areEqual(intBeforeChange, getManyInt(loadedThing)));
    assertTrue(TestUtil.areEqual(intAfterChange, getManyInt(loadedThing)));
    assertFalse(TestUtil.areEqual(stringBeforeChange, getManyString(loadedThing)));
    assertTrue(TestUtil.areEqual(stringAfterChange, getManyString(loadedThing)));
    
    loadedChangeDescription.apply();
    
    //loadedThing: current == before && current != after
    assertTrue(TestUtil.areEqual(intBeforeChange, getManyInt(loadedThing)));
    assertFalse(TestUtil.areEqual(intAfterChange, getManyInt(loadedThing)));    
    assertTrue(TestUtil.areEqual(stringBeforeChange, getManyString(loadedThing)));
    assertFalse(TestUtil.areEqual(stringAfterChange, getManyString(loadedThing)));
    
    
    //thing: current != before && current == after <same as after the changes>
    assertFalse(TestUtil.areEqual(intBeforeChange, getManyInt()));
    assertTrue(TestUtil.areEqual(intAfterChange, getManyInt()));
    assertFalse(TestUtil.areEqual(stringBeforeChange, getManyString()));
    assertTrue(TestUtil.areEqual(stringAfterChange, getManyString()));

    changeDescription.apply();

    //thing: current == before && current != after
    assertTrue(TestUtil.areEqual(intBeforeChange, getManyInt()));
    assertFalse(TestUtil.areEqual(intAfterChange, getManyInt()));
    assertTrue(TestUtil.areEqual(stringBeforeChange, getManyString()));
    assertFalse(TestUtil.areEqual(stringAfterChange, getManyString()));
  }  
}
