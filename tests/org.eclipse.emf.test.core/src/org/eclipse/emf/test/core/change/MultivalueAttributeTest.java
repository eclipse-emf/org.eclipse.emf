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
 * $Id: MultivalueAttributeTest.java,v 1.1 2004/07/21 14:25:49 marcelop Exp $
 */
package org.eclipse.emf.test.core.change;

import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.test.core.sdo.types.model.types.AThing;
import org.eclipse.emf.test.core.sdo.types.model.types.TypesFactory;

public class MultivalueAttributeTest extends TestCase
{
  private AThing thing;
  
  public MultivalueAttributeTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite();
    ts.addTest(new MultivalueAttributeTest("testMultiValueAttributeChange"));
    return ts;
  }
 
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    thing = TypesFactory.eINSTANCE.createAThing();
  }

  public void testMultiValueAttributeChange()
  {
    thing.getManyInt().add(new Integer(1));
    
    ChangeRecorder changeRecorder = new ChangeRecorder((EObject)thing);
    
    thing.getManyInt().add(new Integer(2));
    thing.getManyInt().add(new Integer(3));
    thing.getManyInt().remove(0);
    
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
        
    assertEquals(2, thing.getManyInt().size());
    assertFalse(thing.getManyInt().contains(new Integer(1)));
    changeDescription.apply();
    assertEquals(1, thing.getManyInt().size());
    assertTrue(thing.getManyInt().contains(new Integer(1)));
  }
}
