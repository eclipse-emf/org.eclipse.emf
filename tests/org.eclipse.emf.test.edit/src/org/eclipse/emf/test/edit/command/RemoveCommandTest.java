/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.edit.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for RemoveCommand.  In each case, the model is built, the command is created, executed, undone, and redone.
 * The state of the model and the executability/undoability/redoability of the command are tested between each step.
 */
public class RemoveCommandTest
{
  /**
   * The Ref test package.
   */
  protected RefPackage refPackage;

  /**
   * The Ref factory.
   */
  protected RefFactory refFactory;

  /**
   * An editing domain for for these tests.
   */
  protected EditingDomain editingDomain;

  @Before
  public void setUp() throws Exception
  {
    refPackage = RefPackage.eINSTANCE;
    refFactory = refPackage.getRefFactory();

    AdapterFactory adapterFactory = new RefItemProviderAdapterFactory();
    CommandStack commandStack = new BasicCommandStack();
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
  }

  @Test
  public void testRemoveSameValue()
  {
    E e = refFactory.createE();

    String s0 = "0";
    String s1 = "1";
    String s2 = "2";
    String s3 = new String(s0);

    EList<String> labels = e.getLabels();
    labels.add(s0);
    labels.add(s1);
    labels.add(s2);
    labels.add(s3);
    labels.add(s0);

    Command remove = RemoveCommand.create(editingDomain, e, refPackage.getE_Labels(), s3);

    assertEquals(5, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s3, labels.get(3));
    assertSame(s0, labels.get(4));
    assertNotSame(s0, labels.get(3));
    assertTrue(remove.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(remove);

    assertEquals(4, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s0, labels.get(3));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(5, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s3, labels.get(3));
    assertSame(s0, labels.get(4));
    assertNotSame(s0, labels.get(3));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(4, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s0, labels.get(3));
    assertTrue(stack.canUndo());
  }

  @Test
  public void testRemoveSameValues()
  {
    E e = refFactory.createE();

    String s0 = "0";
    String s1 = "1";
    String s2 = "2";
    String s3 = new String(s1);

    EList<String> labels = e.getLabels();
    labels.add(s0);
    labels.add(s1);
    labels.add(s1);
    labels.add(s2);
    labels.add(s3);

    Collection<String> collection = new ArrayList<String>();
    collection.add(s0);
    collection.add(s1);
    collection.add(s3);

    Command remove = RemoveCommand.create(editingDomain, e, refPackage.getE_Labels(), collection);

    assertEquals(5, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s1, labels.get(2));
    assertSame(s2, labels.get(3));
    assertSame(s3, labels.get(4));
    assertNotSame(s1, labels.get(4));
    assertTrue(remove.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(remove);

    assertEquals(2, labels.size());
    assertSame(s1, labels.get(0));
    assertSame(s2, labels.get(1));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(5, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s1, labels.get(2));
    assertSame(s2, labels.get(3));
    assertSame(s3, labels.get(4));
    assertNotSame(s1, labels.get(4));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(2, labels.size());
    assertSame(s1, labels.get(0));
    assertSame(s2, labels.get(1));
    assertTrue(stack.canUndo());
 }

  @Test
  public void testRemoveEqualValues()
  {
    E e = refFactory.createE();

    String s0 = "0";
    String s1 = "1";
    String s2 = "2";
    String s3 = new String(s1);

    EList<String> labels = e.getLabels();
    labels.add(s0);
    labels.add(s1);
    labels.add(s3);
    labels.add(s2);
    labels.add(s1);

    Collection<String> collection = new ArrayList<String>();
    collection.add(s1);
    collection.add(s1);
    collection.add(s1);

    Command remove = RemoveCommand.create(editingDomain, e, refPackage.getE_Labels(), collection);

    assertEquals(5, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s3, labels.get(2));
    assertSame(s2, labels.get(3));
    assertSame(s1, labels.get(4));
    assertNotSame(s1, labels.get(2));
    assertTrue(remove.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(remove);

    assertEquals(2, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s2, labels.get(1));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(5, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s3, labels.get(2));
    assertSame(s2, labels.get(3));
    assertSame(s1, labels.get(4));
    assertNotSame(s1, labels.get(2));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(2, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s2, labels.get(1));
    assertTrue(stack.canUndo());
 }
}