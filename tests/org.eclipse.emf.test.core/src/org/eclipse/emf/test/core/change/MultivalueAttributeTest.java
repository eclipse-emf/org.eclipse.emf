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
 * $Id: MultivalueAttributeTest.java,v 1.4 2004/11/03 23:30:40 marcelop Exp $
 */
package org.eclipse.emf.test.core.change;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
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
import org.eclipse.emf.test.core.EMFTestCorePlugin;

public class MultivalueAttributeTest extends TestCase
{
  private EObject thing;
  private EAttribute manyInt;
  
  public MultivalueAttributeTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("MultivalueAttributeTest");
    ts.addTest(new MultivalueAttributeTest("testMultiValueAttributeChange"));
    ts.addTest(new MultivalueAttributeTest("testApplyAndReverse"));
    return ts;
  }
 
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    EClass thingEClass = EcoreFactory.eINSTANCE.createEClass();
    thingEClass.setName("Thing");
    ePackage.getEClassifiers().add(thingEClass);
    
    manyInt = EcoreFactory.eINSTANCE.createEAttribute();
    manyInt.setName("manyInt");
    manyInt.setEType(EcorePackage.eINSTANCE.getEInt());
    manyInt.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    thingEClass.getEStructuralFeatures().add(manyInt);
    
    thing = ePackage.getEFactoryInstance().create(thingEClass);
  }
  
  private List getManyInt()
  {
    return (List)thing.eGet(manyInt);
  }

  public void testMultiValueAttributeChange()
  {
    getManyInt().add(new Integer(1));
    
    ChangeRecorder changeRecorder = new ChangeRecorder((EObject)thing);
    
    getManyInt().add(new Integer(2));
    getManyInt().add(new Integer(3));
    getManyInt().remove(0);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();
        
    assertEquals(1, changeDescription.getObjectChanges().size());
    EMap objectChanges = changeDescription.getObjectChanges();
    assertEquals(thing, objectChanges.keySet().iterator().next());
    EList changes = (EList)objectChanges.values().iterator().next();
    assertEquals(1, changes.size());
    FeatureChange featureChange = (FeatureChange)changes.get(0);
    assertEquals(3, featureChange.getListChanges().size());
    //
    int count = 0;
    for (Iterator i = featureChange.getListChanges().iterator(); i.hasNext();)
    {
      ListChange listChange = (ListChange)i.next();
      switch(listChange.getKind().getValue())
      {
        case ChangeKind.ADD:
        {
          assertEquals(0, listChange.getIndex());
          assertEquals(1, listChange.getValues().size());
          assertEquals(new Integer(1), listChange.getValues().get(0));
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
    assertFalse(getManyInt().contains(new Integer(1)));
    changeDescription.apply();
    assertEquals(1, getManyInt().size());
    assertTrue(getManyInt().contains(new Integer(1)));
  }
  
  /*
   * Bugzilla 76825
   */
  public void testApplyAndReverse()
  {
    List beforeChange = new ArrayList(getManyInt());
    
    ChangeRecorder changeRecorder = new ChangeRecorder((EObject)thing);
    getManyInt().add(new Integer(2));
    getManyInt().add(new Integer(3));
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    List afterChange = new ArrayList(getManyInt());
    
    //current != before && current == after
    assertFalse(EMFTestCorePlugin.areEqual(beforeChange, getManyInt()));
    assertTrue(EMFTestCorePlugin.areEqual(afterChange, getManyInt()));
    
    changeDescription.applyAndReverse();    
    //current == before && current != after
    assertTrue(EMFTestCorePlugin.areEqual(beforeChange, getManyInt()));
    assertFalse(EMFTestCorePlugin.areEqual(afterChange, getManyInt()));    

    changeDescription.apply();    
    //current != before && current == after
    assertFalse(EMFTestCorePlugin.areEqual(beforeChange, getManyInt()));
    assertTrue(EMFTestCorePlugin.areEqual(afterChange, getManyInt()));
  }  
}
