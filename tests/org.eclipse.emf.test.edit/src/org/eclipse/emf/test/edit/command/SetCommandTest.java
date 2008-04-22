/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: SetCommandTest.java,v 1.6 2008/04/22 13:35:57 emerks Exp $
 */
package org.eclipse.emf.test.edit.command;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.test.models.ref.A;
import org.eclipse.emf.test.models.ref.B;
import org.eclipse.emf.test.models.ref.C;
import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for SetCommand.
 * This exercises the cases in {@link SetCommand#create(EditingDomain, Object, Object, Object, int) SetCommand.create} for which different
 * command may be returned.  In each case, the model is built, the command is created, executed, undone, and redone.
 * The state of the model and the executability/undoability/redoability of the command are tested between each step.
 */
public class SetCommandTest extends TestCase
{
  public SetCommandTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("SetCommandTest");
    suite.addTest(new SetCommandTest("testDuplicate"));
    suite.addTest(new SetCommandTest("testDuplicateInUniqueAttribute"));
    suite.addTest(new SetCommandTest("testDuplicateInAttribute"));
    suite.addTest(new SetCommandTest("testManyToManySimple"));
    suite.addTest(new SetCommandTest("testManyToMany"));
    suite.addTest(new SetCommandTest("testManyToManyNonUndoable"));
    suite.addTest(new SetCommandTest("testOneToManyStayNull"));
    suite.addTest(new SetCommandTest("testOneToManyNull"));
    suite.addTest(new SetCommandTest("testOneToManySimple"));
    suite.addTest(new SetCommandTest("testOneToMany"));
    suite.addTest(new SetCommandTest("testManyToOneSimple"));
    suite.addTest(new SetCommandTest("testManyToOne"));
    suite.addTest(new SetCommandTest("testOneToOneSimple"));
    suite.addTest(new SetCommandTest("testOneToOne"));
    suite.addTest(new SetCommandTest("testManyStayEmpty"));
    return suite;
  }

  /**
   * The Ref test package, which includes all the various combinations of bidirectionality, multiplicity, and
   * containment in references.   
   */
  protected RefPackage refPackage;

  /**
   * The Ref factory.
   */
  protected RefFactory refFactory;

  /**
   * And editing domain for for these tests.
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

  public void testDuplicate()
  {
    C c = refFactory.createC();

    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    D d2 = refFactory.createD();

    EList<D> d = c.getD();
    d.add(d0);
    d.add(d1);
    d.add(d2);

    Command set = SetCommand.create(editingDomain, c, refPackage.getC_D(), d2, 0);
    assertFalse(set.canExecute());
  }

  public void testDuplicateInUniqueAttribute()
  {
    E e = refFactory.createE();

    String s0 = "0";
    String s1 = "1";
    String s2 = "2";

    EList<String> ids = e.getIds();
    ids.add(s0);
    ids.add(s1);
    ids.add(s2);

    Command set = SetCommand.create(editingDomain, e, refPackage.getE_Ids(), s2, 0);
    assertFalse(set.canExecute());
  }

  public void testDuplicateInAttribute()
  {
    E e = refFactory.createE();

    String s0 = "0";
    String s1 = "1";
    String s2 = "2";

    EList<String> labels = e.getLabels();
    labels.add(s0);
    labels.add(s1);
    labels.add(s2);

    Command set = SetCommand.create(editingDomain, e, refPackage.getE_Labels(), s2, 0);

    assertEquals(s0, labels.get(0));
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(s2, labels.get(0));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(s0, labels.get(0));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(s2, labels.get(0));
    assertTrue(stack.canUndo());
  }
  
  public void testManyToManySimple()
  {
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    E e0 = refFactory.createE();
    E e1 = refFactory.createE();
    E e2 = refFactory.createE();
    E e3 = refFactory.createE();

    d0.getE().add(e0);
    d1.getE().add(e1);
    d0.getE().add(e1);
    d0.getE().add(e2);

    Command set = SetCommand.create(editingDomain, d0, refPackage.getD_E(), e3, 1);

    assertEquals(e1, d0.getE().get(1));
    assertEquals(d0, e1.getD().get(1));
    assertTrue(e3.getD().isEmpty());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(e3, d0.getE().get(1));
    assertEquals(d0, e3.getD().get(0));
    assertEquals(1, e1.getD().size());
    assertEquals(d1, e1.getD().get(0));
    assertEquals(e1, d1.getE().get(0));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(e1, d0.getE().get(1));
    assertEquals(d0, e1.getD().get(1));
    assertTrue(e3.getD().isEmpty());
    assertTrue(set.canExecute());

    stack.redo();

    assertEquals(e3, d0.getE().get(1));
    assertEquals(d0, e3.getD().get(0));
    assertEquals(1, e1.getD().size());
    assertEquals(d1, e1.getD().get(0));
    assertEquals(e1, d1.getE().get(0));
  }

  public void testManyToMany()
  {
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    E e0 = refFactory.createE();
    E e1 = refFactory.createE();
    E e2 = refFactory.createE();
    E e3 = refFactory.createE();

    d0.getE().add(e0);
    d0.getE().add(e1);
    d0.getE().add(e2);
    d1.getE().add(e2);

    Command set = SetCommand.create(editingDomain, d0, refPackage.getD_E(), e3, 2);

    assertEquals(e2, d0.getE().get(2));
    assertEquals(d0, e2.getD().get(0));
    assertTrue(e3.getD().isEmpty());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(e3, d0.getE().get(2));
    assertEquals(d0, e3.getD().get(0));
    assertEquals(d1, e2.getD().get(0));
    assertEquals(e2, d1.getE().get(0));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(e2, d0.getE().get(2));
    assertEquals(d0, e2.getD().get(0));
    assertTrue(e3.getD().isEmpty());
    assertTrue(set.canExecute());

    stack.redo();

    assertEquals(e3, d0.getE().get(2));
    assertEquals(d0, e3.getD().get(0));
    assertEquals(d1, e2.getD().get(0));
    assertEquals(e2, d1.getE().get(0));
  }

  public void testManyToManyNonUndoable()
  {
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    E e0 = refFactory.createE();
    E e1 = refFactory.createE();
    E e2 = refFactory.createE();
    E e3 = refFactory.createE();

    d0.getE().add(e0);
    d0.getE().add(e1);
    d0.getE().add(e2);
    d1.getE().add(e1);

    Command set = SetCommand.create(editingDomain, d0, refPackage.getD_E(), e3, 1);

    assertEquals(e1, d0.getE().get(1));
    assertEquals(d0, e1.getD().get(0));
    assertTrue(e3.getD().isEmpty());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(e3, d0.getE().get(1));
    assertEquals(d0, e3.getD().get(0));
    assertEquals(1, e1.getD().size());
    assertEquals(d1, e1.getD().get(0));
    assertEquals(e1, d1.getE().get(0));
    
    assertTrue(stack.canUndo());
    stack.undo();
    assertEquals(e1, d0.getE().get(1));
    assertEquals(d0, e1.getD().get(0));
    assertTrue(e3.getD().isEmpty());
  }

  public void testOneToManyStayNull()
  {
    D d0 = refFactory.createD();
    Command set = SetCommand.create(editingDomain, d0, refPackage.getD_C(), null);

    assertNull(d0.getC());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertNull(d0.getC());
    assertTrue(stack.canUndo());

    stack.undo();

    assertNull(d0.getC());
    assertTrue(stack.canRedo());

    stack.redo();

    assertNull(d0.getC());
  }

  public void testOneToManyNull()
  {
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    D d2 = refFactory.createD();
    C c0 = refFactory.createC();

    d0.setC(c0);
    d1.setC(c0);
    d2.setC(c0);

    Command set = SetCommand.create(editingDomain, d1, refPackage.getD_C(), null);

    assertEquals(c0, d1.getC());
    assertEquals(d1, c0.getD().get(1));
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(null, d1.getC());
    assertEquals(d2, c0.getD().get(1));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(c0, d1.getC());
    assertEquals(d1, c0.getD().get(1));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(null, d1.getC());
    assertEquals(d2, c0.getD().get(1));
  }

  public void testOneToManySimple()
  {
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    C c0 = refFactory.createC();

    d0.setC(c0);

    Command set = SetCommand.create(editingDomain, d1, refPackage.getD_C(), c0);

    assertNull(d1.getC());
    assertEquals(1, c0.getD().size());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(c0, d1.getC());
    assertEquals(d1, c0.getD().get(1));
    assertTrue(stack.canUndo());

    stack.undo();

    assertNull(d1.getC());
    assertEquals(1, c0.getD().size());
    assertTrue(set.canExecute());

    stack.redo();
    
    assertEquals(c0, d1.getC());
    assertEquals(d1, c0.getD().get(1));
  }

  public void testOneToMany()
  {
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    C c0 = refFactory.createC();
    C c1 = refFactory.createC();

    d0.setC(c0);
    d1.setC(c0);

    Command set = SetCommand.create(editingDomain, d0, refPackage.getD_C(), c1);

    assertEquals(c0, d0.getC());
    assertEquals(d0, c0.getD().get(0));
    assertTrue(c1.getD().isEmpty());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(c1, d0.getC());
    assertEquals(d0, c1.getD().get(0));
    assertEquals(d1, c0.getD().get(0));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(c0, d0.getC());
    assertEquals(d0, c0.getD().get(0));
    assertTrue(c1.getD().isEmpty());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(c1, d0.getC());
    assertEquals(d0, c1.getD().get(0));
    assertEquals(d1, c0.getD().get(0));
  }

  public void testManyToOneSimple()
  {
    C c0 = refFactory.createC();
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    D d2 = refFactory.createD();
    D d3 = refFactory.createD();

    c0.getD().add(d0);
    c0.getD().add(d1);
    c0.getD().add(d2);

    Command set = SetCommand.create(editingDomain, c0, refPackage.getC_D(), d3, 1);

    assertEquals(d1, c0.getD().get(1));
    assertEquals(c0, d1.getC());
    assertNull(d3.getC());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(d3, c0.getD().get(1));
    assertEquals(c0, d3.getC());
    assertNull(d1.getC());
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(d1, c0.getD().get(1));
    assertEquals(c0, d1.getC());
    assertNull(d3.getC());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(d3, c0.getD().get(1));
    assertEquals(c0, d3.getC());
    assertNull(d1.getC());
  }

  public void testManyToOne()
  {
    C c0 = refFactory.createC();
    C c1 = refFactory.createC();
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    D d2 = refFactory.createD();
    D d3 = refFactory.createD();
    D d4 = refFactory.createD();

    c0.getD().add(d0);
    c0.getD().add(d1);
    c0.getD().add(d2);

    c1.getD().add(d3);
    c1.getD().add(d4);

    Command set = SetCommand.create(editingDomain, c0, refPackage.getC_D(), d3, 1);

    assertEquals(d1, c0.getD().get(1));
    assertEquals(c0, d1.getC());
    assertEquals(d3, c1.getD().get(0));
    assertEquals(c1, d3.getC());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(d3, c0.getD().get(1));
    assertEquals(c0, d3.getC());
    assertNull(d1.getC());
    assertEquals(d4, c1.getD().get(0));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(d1, c0.getD().get(1));
    assertEquals(c0, d1.getC());
    assertEquals(d3, c1.getD().get(0));
    assertEquals(c1, d3.getC());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(d3, c0.getD().get(1));
    assertEquals(c0, d3.getC());
    assertNull(d1.getC());
    assertEquals(d4, c1.getD().get(0));
  }
  
  public void testOneToOneSimple()
  {
    A a0 = refFactory.createA();
    B b0 = refFactory.createB();
    B b1 = refFactory.createB();

    a0.setB(b0);

    Command set = SetCommand.create(editingDomain, a0, refPackage.getA_B(), b1);

    assertEquals(b0, a0.getB());
    assertEquals(a0, b0.getA());
    assertNull(b1.getA());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(b1, a0.getB());
    assertEquals(a0, b1.getA());
    assertNull(b0.getA());
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(b0, a0.getB());
    assertEquals(a0, b0.getA());
    assertNull(b1.getA());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(b1, a0.getB());
    assertEquals(a0, b1.getA());
    assertNull(b0.getA());
  }

  public void testOneToOne()
  {
    A a0 = refFactory.createA();
    A a1 = refFactory.createA();
    B b0 = refFactory.createB();
    B b1 = refFactory.createB();

    a0.setB(b0);
    a1.setB(b1);

    Command set = SetCommand.create(editingDomain, a0, refPackage.getA_B(), b1);

    assertEquals(b0, a0.getB());
    assertEquals(a0, b0.getA());
    assertEquals(b1, a1.getB());
    assertEquals(a1, b1.getA());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertEquals(b1, a0.getB());
    assertEquals(a0, b1.getA());
    assertNull(a1.getB());
    assertNull(b0.getA());
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(b0, a0.getB());
    assertEquals(a0, b0.getA());
    assertEquals(b1, a1.getB());
    assertEquals(a1, b1.getA());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(b1, a0.getB());
    assertEquals(a0, b1.getA());
    assertNull(a1.getB());
    assertNull(b0.getA());
  }

  public void testManyStayEmpty()
  {
    B b = refFactory.createB();

    Command set = SetCommand.create(editingDomain, b, refPackage.getB_D(), new BasicEList<Object>());

    assertTrue(b.getD().isEmpty());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertTrue(b.getD().isEmpty());
    assertTrue(stack.canUndo());

    stack.undo();

    assertTrue(b.getD().isEmpty());
    assertTrue(stack.canRedo());

    stack.redo();

    assertTrue(b.getD().isEmpty());
  }

  /**
   * Shows that undo on remove doesn't work properly in the many-to-many case.  Should not be run as part of the suite.
   */
  public void testBiDiRemove()
  {
    D d1 = refFactory.createD();
    D d2 = refFactory.createD();
    E e1 = refFactory.createE();
    E e2 = refFactory.createE();

    d1.getE().add(e1);
    d1.getE().add(e2);
    d2.getE().add(e1);

    Command remove = RemoveCommand.create(editingDomain, d1, refPackage.getD_E(), e1);
    
    assertEquals(e1, d1.getE().get(0));
    assertEquals(d1, e1.getD().get(0));
    assertTrue(remove.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(remove);
    
    assertEquals(e2, d1.getE().get(0));
    assertEquals(d2, e1.getD().get(0));
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(e1, d1.getE().get(0));
    assertEquals(d1, e1.getD().get(0));
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(e2, d1.getE().get(0));
    assertEquals(d2, e1.getD().get(0));
    assertTrue(stack.canUndo());
  }
}
