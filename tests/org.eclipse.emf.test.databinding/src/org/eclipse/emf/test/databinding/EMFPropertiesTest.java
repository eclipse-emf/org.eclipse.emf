/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: EMFPropertiesTest.java,v 1.4 2010/05/04 12:59:06 tschindl Exp $
 */
package org.eclipse.emf.test.databinding;

import java.util.Arrays;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.set.SetDiff;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFSetProperty;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.databinding.emfdb.A;
import org.eclipse.emf.test.databinding.emfdb.B;
import org.eclipse.emf.test.databinding.emfdb.EmfdbFactory;
import org.eclipse.emf.test.databinding.emfdb.EmfdbPackage;

import junit.framework.TestCase;

public class EMFPropertiesTest extends TestCase
{
  private Resource resource;
  private Realm testRealm;
  private SetDiff setDiff;
  private ListDiff listDiff;

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new XMIResourceFactoryImpl());

    URI uri = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.databinding") + "/model/A.xmi");
    resource = resourceSet.getResource(uri, true);
    testRealm = new Realm()
      {

        @Override
        public boolean isCurrent()
        {
          return true;
        }
      };
  }

  public void testResourceProperty() {
    Realm.runWithDefault(testRealm, new Runnable()
    {

      public void run()
      {
        _testResourceProperty();
      }
    });
  }

  private void _testResourceProperty() {
    IEMFListProperty prop = EMFProperties.resource();
    IObservableList list = prop.observe(resource);
    list.addListChangeListener(new IListChangeListener()
      {

        public void handleListChange(ListChangeEvent event)
        {
          listDiff = event.diff;
        }
      });
    assertNull(listDiff);

    // Adding
    A a = EmfdbFactory.eINSTANCE.createA();
    A a1 = EmfdbFactory.eINSTANCE.createA();

    resource.getContents().add(a);
    assertNotNull(listDiff);
    assertEquals(1, listDiff.getDifferences().length);
    assertSame(a, listDiff.getDifferences()[0].getElement());
    assertEquals(1, listDiff.getDifferences()[0].getPosition());
    assertTrue(listDiff.getDifferences()[0].isAddition());

    // Moving
    resource.getContents().move(0, a);
    assertEquals(2, listDiff.getDifferences().length);
    assertFalse(listDiff.getDifferences()[0].isAddition()); // Removal
    assertEquals(1, listDiff.getDifferences()[0].getPosition());
    assertTrue(listDiff.getDifferences()[1].isAddition());  // Addition
    assertEquals(0, listDiff.getDifferences()[1].getPosition());

    // Removing
    resource.getContents().remove(a);
    assertEquals(1, listDiff.getDifferences().length);
    assertFalse(listDiff.getDifferences()[0].isAddition()); // Removal
    assertEquals(0, listDiff.getDifferences()[0].getPosition());

    // Adding many
    resource.getContents().addAll(Arrays.asList(a, a1));
    assertEquals(2, listDiff.getDifferences().length);

    // Remove many
    resource.getContents().removeAll(Arrays.asList(a, a1));
    assertEquals(2, listDiff.getDifferences().length);

    // =============================================================

    // Changed through IObservableList
    // Add
    list.add(a);
    assertNotNull(listDiff);
    assertEquals(1, listDiff.getDifferences().length);
    assertSame(a, listDiff.getDifferences()[0].getElement());
    assertEquals(1, listDiff.getDifferences()[0].getPosition());
    assertTrue(listDiff.getDifferences()[0].isAddition());

    // Moving
    list.move(1, 0);
    assertEquals(2, listDiff.getDifferences().length);
    assertFalse(listDiff.getDifferences()[0].isAddition()); // Removal
    assertEquals(1, listDiff.getDifferences()[0].getPosition());
    assertTrue(listDiff.getDifferences()[1].isAddition());  // Addition
    assertEquals(0, listDiff.getDifferences()[1].getPosition());

    // Removing
    list.remove(a);
    assertEquals(1, listDiff.getDifferences().length);
    assertFalse(listDiff.getDifferences()[0].isAddition()); // Removal
    assertEquals(0, listDiff.getDifferences()[0].getPosition());

    // Adding many
    list.addAll(Arrays.asList(a, a1));
    assertEquals(2, listDiff.getDifferences().length);

    // Remove many
    list.removeAll(Arrays.asList(a, a1));
    assertEquals(2, listDiff.getDifferences().length);
  }

  public void testSetProperty() {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testSetProperty();
        }
      });
  }

  public void _testSetProperty() {
    A a = (A)resource.getContents().get(0);
    IEMFSetProperty prop = EMFProperties.set(EmfdbPackage.Literals.A__BLIST);
    IObservableSet set = prop.observe(a);
    assertNotNull(set);
    set.addSetChangeListener(new ISetChangeListener()
      {

        public void handleSetChange(SetChangeEvent event)
        {
          setDiff = event.diff;
        }
      });
    assertNull(setDiff);
    B b = EmfdbFactory.eINSTANCE.createB();
    a.getBlist().add(b);
    assertNotNull(setDiff);
    assertEquals(1, setDiff.getAdditions().size());
    assertSame(b, setDiff.getAdditions().iterator().next());
  }

  public void testListPropertyOnSingleFeature() {
    Realm.runWithDefault(testRealm, new Runnable()
    {

      public void run()
      {
        _testListPropertyOnSingleFeature();
      }
    });
  }

  public void _testListPropertyOnSingleFeature() {
    A a = (A)resource.getContents().get(0);
    IEMFListProperty prop = EMFProperties.list(EmfdbPackage.Literals.A__STRING);
    IObservableList l = prop.observe(a);
    assertEquals(1, l.size());
    assertEquals("Instance 1", l.get(0));
    a.setString("Bla");
    assertEquals("Bla", l.get(0));
    l.addListChangeListener(new IListChangeListener()
      {

        public void handleListChange(ListChangeEvent event)
        {
          System.err.println("Done");
          assertEquals(2, event.diff.getDifferences().length);

          assertEquals(0, event.diff.getDifferences()[0].getPosition());
          assertFalse(event.diff.getDifferences()[0].isAddition());
          assertEquals("Bla", event.diff.getDifferences()[0].getElement());

          assertEquals(0, event.diff.getDifferences()[1].getPosition());
          assertTrue(event.diff.getDifferences()[1].isAddition());
          assertEquals("Instance 1", event.diff.getDifferences()[1].getElement());
        }
      });
    a.setString("Instance 1");
  }
}
