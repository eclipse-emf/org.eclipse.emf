/**
 * Copyright (c) 2009 BestSolution and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   Tom Schindl - Initial API and implementation
 */
package org.eclipse.emf.test.databinding.edit;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.IEMFListProperty.ListElementAccess;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.databinding.edit.IEMFEditSetProperty;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.databinding.emfdb.A;
import org.eclipse.emf.test.databinding.emfdb.B;
import org.eclipse.emf.test.databinding.emfdb.EmfdbFactory;
import org.eclipse.emf.test.databinding.emfdb.EmfdbPackage;


public class EMFEditPropertiesTest extends TestCase
{
  private Resource resource;
  private EditingDomain editingDomain;
  private Realm testRealm;
  private boolean flag;
  private ListDiffEntry[] listEntries;
  private ListDiff listDiff;
  private SetDiff diff;
  private BasicCommandStack commandStack;

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();

    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    ResourceSet resourceSet = new ResourceSetImpl();
    commandStack = new BasicCommandStack();

    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new XMIResourceFactoryImpl());
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, resourceSet);
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
    flag = false;
    listEntries = null;
  }

  @Override
  protected void tearDown() throws Exception
  {
    super.tearDown();
  }

  //  public void testValueEditingDomainEStructuralFeature()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testValueEditingDomainFeaturePath()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testValuesEditingDomainEStructuralFeatureArray()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testValuesEditingDomainFeaturePathArray()
  //  {
  //    fail("Not yet implemented");
  //  }

  public void testListEditingDomainEStructuralFeature()
  {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testListEditingDomainEStructuralFeature();
        }
      });
  }

  public void _testListEditingDomainEStructuralFeature()
  {
    A a = (A)resource.getContents().get(0);
    IEMFEditListProperty prop = EMFEditProperties.list(editingDomain, EmfdbPackage.Literals.A__BLIST);
    IObservableList list = prop.observe(a);
    list.addListChangeListener(new IListChangeListener()
      {

        public void handleListChange(ListChangeEvent event)
        {
          flag = true;
          listEntries = event.diff.getDifferences();
        }
      });
    assertEquals(a.getBlist().size(), list.size());
    B b = EmfdbFactory.eINSTANCE.createB();
    list.add(b);
    // a.getBlist().add(b);
    assertEquals(a.getBlist().size(), list.size());
    assertTrue(flag);
    assertNotNull(listEntries);
    assertEquals(1, listEntries.length);
    assertTrue(listEntries[0].isAddition());
    assertSame(b, listEntries[0].getElement());
    assertEquals(a.getBlist().size() - 1, listEntries[0].getPosition());
    assertEquals(list.get(0), a.getBlist().get(0));
  }

  public void testSetProperty()
  {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testSetProperty();
        }
      });
  }

  public void _testSetProperty()
  {
    A a = (A)resource.getContents().get(0);
    IEMFEditSetProperty prop = EMFEditProperties.set(editingDomain, EmfdbPackage.Literals.A__BLIST);
    IObservableSet set = prop.observe(a);
    assertNotNull(set);
    set.addSetChangeListener(new ISetChangeListener()
      {

        public void handleSetChange(SetChangeEvent event)
        {
          diff = event.diff;
        }
      });
    assertNull(diff);
    assertFalse(commandStack.canUndo());
    B b = EmfdbFactory.eINSTANCE.createB();
    // a.getBlist().add(b);
    set.add(b);
    assertNotNull(diff);
    assertEquals(1, diff.getAdditions().size());
    assertSame(b, diff.getAdditions().iterator().next());
    assertTrue(commandStack.canUndo());
    assertFalse(commandStack.canRedo());
    diff = null;
    commandStack.undo();
    assertNotNull(diff);
    assertEquals(1, diff.getRemovals().size());
    assertSame(b, diff.getRemovals().iterator().next());
    assertFalse(commandStack.canUndo());
    assertTrue(commandStack.canRedo());
  }

  //  public void testListEditingDomainFeaturePath()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testMultiListEditingDomainEStructuralFeatureArray()
  //  {
  //    fail("Not yet implemented");
  //  }
  public void testMultiListEditingDomainEStructuralFeatureArray()
  {
    Realm.runWithDefault(testRealm, new Runnable()
      {
        public void run()
        {
          A a = (A)resource.getContents().get(0);
          IEMFEditListProperty prop = EMFEditProperties.multiList(
            editingDomain,
            EmfdbPackage.Literals.A__STRING,
            EmfdbPackage.Literals.A__BLIST);
          IObservableList list = prop.observe(a);
          list.addListChangeListener(new IListChangeListener()
            {

              public void handleListChange(ListChangeEvent event)
              {
                flag = true;
                listEntries = event.diff.getDifferences();
              }
            });
          assertEquals(a.getString(), list.get(0));
          assertEquals(a.getBlist().size() + 1, list.size());
          B b = EmfdbFactory.eINSTANCE.createB();
          a.getBlist().add(b);
          assertEquals(a.getBlist().size() + 1, list.size());
          assertTrue(flag);
          assertNotNull(listEntries);
          assertEquals(1, listEntries.length);
          assertTrue(listEntries[0].isAddition());
          assertSame(b, listEntries[0].getElement());
          assertEquals(a.getBlist().size(), listEntries[0].getPosition());
          assertEquals(list.get(1), a.getBlist().get(0));
          a.setString(null);
          assertEquals(a.getBlist().size(), list.size());
          assertEquals(list.get(0), a.getBlist().get(0));
        }
      });
  }

  public void testResourceProperty()
  {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testResourceProperty();
        }
      });
  }

  private void _testResourceProperty()
  {
    IEMFEditListProperty prop = EMFEditProperties.resource(editingDomain);
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
    assertTrue(listDiff.getDifferences()[1].isAddition()); // Addition
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
    assertNull(commandStack.getUndoCommand());
    list.add(a);
    assertNotNull(listDiff);
    assertEquals(1, listDiff.getDifferences().length);
    assertSame(a, listDiff.getDifferences()[0].getElement());
    assertEquals(1, listDiff.getDifferences()[0].getPosition());
    assertTrue(listDiff.getDifferences()[0].isAddition());
    assertNotNull(commandStack.getUndoCommand());
    assertTrue(commandStack.canUndo());
    assertTrue(commandStack.getUndoCommand() instanceof CompoundCommand);
    CompoundCommand cmd = (CompoundCommand)commandStack.getUndoCommand();
    assertEquals(1, cmd.getCommandList().size());
    assertTrue(cmd.getCommandList().get(0) instanceof AddCommand);
    commandStack.undo();
    assertFalse(commandStack.canUndo());
    commandStack.redo();
    commandStack.flush();

    // Moving
    list.move(1, 0);
    assertEquals(2, listDiff.getDifferences().length);
    assertFalse(listDiff.getDifferences()[0].isAddition()); // Removal
    assertEquals(1, listDiff.getDifferences()[0].getPosition());
    assertTrue(listDiff.getDifferences()[1].isAddition()); // Addition
    assertEquals(0, listDiff.getDifferences()[1].getPosition());
    assertTrue(commandStack.canUndo());
    assertTrue(commandStack.getUndoCommand() instanceof CompoundCommand);
    commandStack.undo();
    assertFalse(commandStack.canUndo());
    commandStack.redo();
    commandStack.flush();

    // Removing
    list.remove(a);
    assertEquals(1, listDiff.getDifferences().length);
    assertFalse(listDiff.getDifferences()[0].isAddition()); // Removal
    assertEquals(0, listDiff.getDifferences()[0].getPosition());
    assertTrue(commandStack.canUndo());
    assertTrue(commandStack.getUndoCommand() instanceof CompoundCommand);
    commandStack.undo();
    assertFalse(commandStack.canUndo());
    commandStack.redo();
    commandStack.flush();

    // Adding many
    list.addAll(Arrays.asList(a, a1));
    assertEquals(2, listDiff.getDifferences().length);
    assertTrue(commandStack.canUndo());
    assertTrue(commandStack.getUndoCommand() instanceof CompoundCommand);
    commandStack.undo();
    assertFalse(commandStack.canUndo());
    commandStack.redo();
    commandStack.flush();

    // Remove many
    list.removeAll(Arrays.asList(a, a1));
    assertEquals(2, listDiff.getDifferences().length);
    assertTrue(commandStack.canUndo());
    assertTrue(commandStack.getUndoCommand() instanceof CompoundCommand);
    commandStack.undo();
    assertFalse(commandStack.canUndo());
    commandStack.redo();
    commandStack.flush();
  }

  public void testListPropertyOnSingleFeature()
  {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testListPropertyOnSingleFeature();
        }
      });
  }

  public void _testListPropertyOnSingleFeature()
  {
    A a = (A)resource.getContents().get(0);
    IEMFEditListProperty prop = EMFEditProperties.list(editingDomain, EmfdbPackage.Literals.A__STRING);
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

  public void testListElementProperty()
  {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testListElementProperty();
        }
      });
  }

  public void _testListElementProperty()
  {
    IEMFEditListProperty prop = EMFEditProperties.list(editingDomain, EmfdbPackage.Literals.A__BLIST);
    IEMFEditValueProperty valueProp = prop.value(new ListElementAccess<Object>()
      {

        @Override
        public int getReadValueIndex(List< Object > list)
        {
          return 0;
        }

        @Override
        public int getWriteValueIndex(List< Object > list)
        {
          return 0;
        }
      });

    IEMFEditValueProperty detailValue = valueProp.value(EmfdbPackage.Literals.B__STRING);

    IObservableValue value = detailValue.observe(resource.getContents().get(0));
    assertEquals("Instance 1", value.getValue());
    A a = (A)resource.getContents().get(0);
    a.getBlist().get(0).setString("Bla Bla");
    assertEquals("Bla Bla", value.getValue());

    B b = EmfdbFactory.eINSTANCE.createB();
    b.setString("New Element");

    a.getBlist().add(0, b);
    assertEquals("New Element", value.getValue());

    b = EmfdbFactory.eINSTANCE.createB();
    b.setString("New Element 2");
    IObservableValue aObservable = valueProp.observe(a);
    try {
		aObservable.setValue(b);
	} finally {
		aObservable.dispose();
	}
    assertEquals("New Element 2", value.getValue());
  }

  //
  //  public void testMultiListEditingDomainFeaturePathEStructuralFeatureArray()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testMultiListEditingDomainFeaturePathArray()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testMultiListEditingDomainIEMFEditListPropertyArray()
  //  {
  //    fail("Not yet implemented");
  //  }
  //
  //  public void testMap()
  //  {
  //    fail("Not yet implemented");
  //  }

}
