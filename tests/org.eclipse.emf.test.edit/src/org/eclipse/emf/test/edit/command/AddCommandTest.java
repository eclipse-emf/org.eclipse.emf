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

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for AddCommand.  In each case, the model is built, the command is created, executed, undone, and redone.
 * The state of the model and the executability/undoability/redoability of the command are tested between each step.
 */
public class AddCommandTest extends TestCase
{
  public AddCommandTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("AddCommandTest");
    suite.addTest(new AddCommandTest("testAddDuplicatesToUnique"));
    suite.addTest(new AddCommandTest("testAddSameDuplicate"));
    suite.addTest(new AddCommandTest("testAddEqualDuplicate"));
    return suite;
  }

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

  @Override
  protected void setUp() throws Exception
  {
    refPackage = RefPackage.eINSTANCE;
    refFactory = refPackage.getRefFactory();
    
    AdapterFactory adapterFactory = new RefItemProviderAdapterFactory();
    CommandStack commandStack = new BasicCommandStack();
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
  }

  public void testAddDuplicatesToUnique()
  {
    E e = refFactory.createE();

    String s0 = "0";
    String s1 = "1";
    String s2 = "2";
    String s3 = new String(s0);

    EList<String> ids = e.getIds();
    ids.add(s0);
    ids.add(s1);
    ids.add(s2);

    Command add = AddCommand.create(editingDomain, e, refPackage.getE_Ids(), s0);
    assertFalse(add.canExecute());

    add = AddCommand.create(editingDomain, e, refPackage.getE_Ids(), s3);
    assertFalse(add.canExecute());
  }
  
  public void testAddSameDuplicate()
  {
    E e = refFactory.createE();
    e.eAdapters().add(new AdapterImpl());
    
    String s0 = "0";
    String s1 = "1";
    String s2 = "2";

    EList<String> labels = e.getLabels();
    labels.add(s0);
    labels.add(s1);
    labels.add(s2);

    Command add = AddCommand.create(editingDomain, e, refPackage.getE_Labels(), s0);

    assertEquals(3, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertTrue(add.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(add);

    assertEquals(4, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s0, labels.get(3));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(3, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(4, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s0, labels.get(3));
    assertTrue(stack.canUndo());
  }

  public void testAddEqualDuplicate()
  {
    E e = refFactory.createE();
    e.eAdapters().add(new AdapterImpl());
    
    String s0 = "0";
    String s1 = "1";
    String s2 = "2";
    String s3 = new String(s0);

    EList<String> labels = e.getLabels();
    labels.add(s0);
    labels.add(s1);
    labels.add(s2);

    Command add = AddCommand.create(editingDomain, e, refPackage.getE_Labels(), s3);

    assertEquals(3, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertTrue(add.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(add);

    assertEquals(4, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s3, labels.get(3));
    assertNotSame(s0, labels.get(3));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(3, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(4, labels.size());
    assertSame(s0, labels.get(0));
    assertSame(s1, labels.get(1));
    assertSame(s2, labels.get(2));
    assertSame(s3, labels.get(3));
    assertNotSame(s0, labels.get(3));
    assertTrue(stack.canUndo());
  }
}
