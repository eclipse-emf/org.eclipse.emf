/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.edit.command;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.test.models.ref.A;
import org.eclipse.emf.test.models.ref.B;
import org.eclipse.emf.test.models.ref.C;
import org.eclipse.emf.test.models.ref.C2;
import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;
import org.eclipse.emf.test.models.ref.unsettable.AU;
import org.eclipse.emf.test.models.ref.unsettable.BU;
import org.eclipse.emf.test.models.ref.unsettable.C2U;
import org.eclipse.emf.test.models.ref.unsettable.CU;
import org.eclipse.emf.test.models.ref.unsettable.DU;
import org.eclipse.emf.test.models.ref.unsettable.EU;
import org.eclipse.emf.test.models.ref.unsettable.URefFactory;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;
import org.eclipse.emf.test.models.ref.unsettable.provider.URefItemProviderAdapterFactory;

/**
 * Tests for unsetting use of SetCommand.  In each case, the model is built, the command is created, executed, undone,
 * and redone. The state of the model and the executability/undoability/redoability of the command are tested between
 * each step.
 */
public class UnsetCommandTest extends TestCase
{
  public UnsetCommandTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("UnsetCommandTest");
    suite.addTest(new UnsetCommandTest("testAttribute1"));
    suite.addTest(new UnsetCommandTest("testNullAttribute1"));
    suite.addTest(new UnsetCommandTest("testAttributeMany"));
    suite.addTest(new UnsetCommandTest("testEmptyAttributeMany"));
    suite.addTest(new UnsetCommandTest("testUnsettableAttribute1"));
    suite.addTest(new UnsetCommandTest("testUnsetUnsettableAttribute1"));
    suite.addTest(new UnsetCommandTest("testNullUnsettableAttribute1"));
    suite.addTest(new UnsetCommandTest("testUnsettableAttributeMany"));
    suite.addTest(new UnsetCommandTest("testUnsetUnsettableAttributeMany"));
    suite.addTest(new UnsetCommandTest("testEmptyUnsettableAttributeMany"));
    suite.addTest(new UnsetCommandTest("testContainment1"));
    suite.addTest(new UnsetCommandTest("testNullContainment1"));
    suite.addTest(new UnsetCommandTest("testContainmentMany"));
    suite.addTest(new UnsetCommandTest("testEmptyContainmentMany"));
    suite.addTest(new UnsetCommandTest("testUnsettableContainment1"));
    suite.addTest(new UnsetCommandTest("testUnsetUnsettableContainment1"));
    suite.addTest(new UnsetCommandTest("testNullUnsettableContainment1"));
    suite.addTest(new UnsetCommandTest("testUnsettableContainmentMany"));
    suite.addTest(new UnsetCommandTest("testEmptyUnsettableContainmentMany"));
    suite.addTest(new UnsetCommandTest("testUnsetUnsettableContainmentMany"));
    suite.addTest(new UnsetCommandTest("testReference1ToMany"));
    suite.addTest(new UnsetCommandTest("testNullReference1ToMany"));
    suite.addTest(new UnsetCommandTest("testUnsettableReference1ToMany"));
    suite.addTest(new UnsetCommandTest("testUnsetUnsettableReference1ToMany"));
    suite.addTest(new UnsetCommandTest("testNullUnsettableReference1ToMany"));
    suite.addTest(new UnsetCommandTest("testInUnsettableMany"));
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
   * Another version of the Ref test package, where all the features are unsettable.
   */
  protected URefPackage uRefPackage;

  /**
   * The URefFactory.
   */
  protected URefFactory uRefFactory;

  /**
   * An editing domain for for these tests.
   */
  protected EditingDomain editingDomain;

  @Override
  protected void setUp() throws Exception
  {
    refPackage = RefPackage.eINSTANCE;
    refFactory = refPackage.getRefFactory();

    uRefPackage = URefPackage.eINSTANCE;
    uRefFactory = uRefPackage.getURefFactory();

    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(new AdapterFactory[]
      { new RefItemProviderAdapterFactory(), new URefItemProviderAdapterFactory()});
    CommandStack commandStack = new BasicCommandStack();
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
  }

  public void testAttribute1()
  {
    E e = refFactory.createE();
    String s = "Edward";
    e.setName(s);

    EStructuralFeature feature = refPackage.getE_Name();
    Command set = SetCommand.create(editingDomain, e, feature, SetCommand.UNSET_VALUE);
    assertTrue(e.eIsSet(feature));
    assertEquals(s, e.getName());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFalse(e.eIsSet(feature));
    assertEquals(null, e.getName());
    assertTrue(stack.canUndo());

    stack.undo();

    assertTrue(e.eIsSet(feature));
    assertEquals(s, e.getName());
    assertTrue(stack.canRedo());

    stack.redo();

    assertFalse(e.eIsSet(feature));
    assertEquals(null, e.getName());
  }
  
  public void testNullAttribute1()
  {
    E e = refFactory.createE();

    EStructuralFeature feature = refPackage.getE_Name();
    Command set = SetCommand.create(editingDomain, e, feature, SetCommand.UNSET_VALUE);
    assertFalse(e.eIsSet(feature));
    assertNull(e.getName());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFalse(e.eIsSet(feature));
    assertNull(e.getName());
    assertTrue(stack.canUndo());

    stack.undo();

    assertFalse(e.eIsSet(feature));
    assertNull(e.getName());
    assertTrue(stack.canRedo());

    stack.redo();

    assertFalse(e.eIsSet(feature));
    assertNull(e.getName());
  }

  public void testAttributeMany()
  {
    E e = refFactory.createE();
    String[] ids =  { "x1", "aa", "cmd", "lh" };
    e.getIds().addAll(Arrays.asList(ids));
  
    EStructuralFeature feature = refPackage.getE_Ids();
    Command set = SetCommand.create(editingDomain, e, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(e, feature, ids);
    assertTrue(set.canExecute());
  
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);
  
    assertFeatureUnset(e, feature);
    assertTrue(stack.canUndo());
  
    stack.undo();
  
    assertFeatureSet(e, feature, ids);
    assertTrue(stack.canRedo());
  
    stack.redo();
  
    assertFeatureUnset(e, feature);
  }

  public void testEmptyAttributeMany()
  {
    E e = refFactory.createE();

    EStructuralFeature feature = refPackage.getE_Ids();
    Command set = SetCommand.create(editingDomain, e, feature, SetCommand.UNSET_VALUE);
    assertFalse(e.eIsSet(feature));
    assertTrue(e.getIds().isEmpty());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFalse(e.eIsSet(feature));
    assertTrue(e.getIds().isEmpty());
    assertTrue(stack.canUndo());

    stack.undo();

    assertFalse(e.eIsSet(feature));
    assertTrue(e.getIds().isEmpty());
    assertTrue(stack.canRedo());

    stack.redo();

    assertFalse(e.eIsSet(feature));
    assertTrue(e.getIds().isEmpty());
  }

  public void testUnsettableAttribute1()
  {
    EU eu = uRefFactory.createEU();
    String s = "Edward";
    eu.setName(s);

    Command set = SetCommand.create(editingDomain, eu, uRefPackage.getEU_Name(), SetCommand.UNSET_VALUE);
    assertTrue(eu.isSetName());
    assertEquals(s, eu.getName());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFalse(eu.isSetName());
    assertEquals(null, eu.getName());
    assertTrue(stack.canUndo());

    stack.undo();

    assertTrue(eu.isSetName());
    assertEquals(s, eu.getName());
    assertTrue(stack.canRedo());

    stack.redo();

    assertFalse(eu.isSetName());
    assertEquals(null, eu.getName());
  }

  public void testUnsetUnsettableAttribute1()
  {
    EU eu = uRefFactory.createEU();

    Command set = SetCommand.create(editingDomain, eu, uRefPackage.getEU_Name(), SetCommand.UNSET_VALUE);
    assertFalse(eu.isSetName());
    assertNull(eu.getName());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFalse(eu.isSetName());
    assertNull(eu.getName());
    assertTrue(stack.canUndo());

    stack.undo();

    assertFalse(eu.isSetName());
    assertNull(eu.getName());
    assertTrue(stack.canRedo());

    stack.redo();

    assertFalse(eu.isSetName());
    assertNull(eu.getName());
  }

  public void testNullUnsettableAttribute1()
  {
    EU eu = uRefFactory.createEU();
    eu.setName(null);

    Command set = SetCommand.create(editingDomain, eu, uRefPackage.getEU_Name(), SetCommand.UNSET_VALUE);
    assertTrue(eu.isSetName());
    assertNull(eu.getName());
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFalse(eu.isSetName());
    assertNull(eu.getName());
    assertTrue(stack.canUndo());

    stack.undo();

    assertTrue(eu.isSetName());
    assertNull(eu.getName());
    assertTrue(stack.canRedo());

    stack.redo();

    assertFalse(eu.isSetName());
    assertNull(eu.getName());
  }

  public void testUnsettableAttributeMany()
  {
    EU eu = uRefFactory.createEU();
    String[] ids =  { "x1", "aa", "cmd", "lh" };
    eu.getIds().addAll(Arrays.asList(ids));

    EStructuralFeature feature = uRefPackage.getEU_Ids();
    Command set = SetCommand.create(editingDomain, eu, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(eu, feature, ids);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(eu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(eu, feature, ids);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(eu, feature);
  }

  public void testUnsetUnsettableAttributeMany()
  {
    EU eu = uRefFactory.createEU();

    EStructuralFeature feature = uRefPackage.getEU_Ids();
    Command set = SetCommand.create(editingDomain, eu, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(eu, feature);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(eu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(eu, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(eu, feature);
  }

  public void testEmptyUnsettableAttributeMany()
  {
    EU eu = uRefFactory.createEU();
    Object[] ids = new Object[0];
    eu.getIds().clear();

    EStructuralFeature feature = uRefPackage.getEU_Ids();
    Command set = SetCommand.create(editingDomain, eu, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(eu, feature, ids);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(eu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(eu, feature, ids);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(eu, feature);
  }

  public void testContainment1()
  {
    C2 c = refFactory.createC2();
    A a = refFactory.createA();
    c.setA(a);

    EStructuralFeature feature = refPackage.getC2_A();
    Command set = SetCommand.create(editingDomain, c, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(c, feature, a);
    assertTrue(set.canExecute());
    
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(c, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(c, feature, a);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(c, feature);
  }

  public void testNullContainment1()
  {
    C2 c = refFactory.createC2();

    EStructuralFeature feature = refPackage.getC2_A();
    Command set = SetCommand.create(editingDomain, c, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(c, feature);
    assertTrue(set.canExecute());
    
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(c, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(c, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(c, feature);
  }

  public void testContainmentMany()
  {
    C2 c = refFactory.createC2();
    B b0 = refFactory.createB();
    B b1 = refFactory.createB();
    B b2 = refFactory.createB();

    c.getB().add(b0);
    c.getB().add(b1);
    c.getB().add(b2);

    Object[] values = { b0, b1, b2 };
    
    EStructuralFeature feature = refPackage.getC2_B();
    Command set = SetCommand.create(editingDomain, c, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(c, feature, values);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(c, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(c, feature, values);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(c, feature);
  }

  public void testEmptyContainmentMany()
  {
    C2 c = refFactory.createC2();

    EStructuralFeature feature = refPackage.getC2_B();
    Command set = SetCommand.create(editingDomain, c, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(c, feature);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(c, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(c, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(c, feature);
  }

  public void testUnsettableContainment1()
  {
    C2U cu = uRefFactory.createC2U();
    AU au = uRefFactory.createAU();
    cu.setAu(au);

    EStructuralFeature feature = uRefPackage.getC2U_Au();
    Command set = SetCommand.create(editingDomain, cu, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(cu, feature, au);
    assertTrue(set.canExecute());
    
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(cu, feature, au);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(cu, feature);
  }

  public void testUnsetUnsettableContainment1()
  {
    C2U cu = uRefFactory.createC2U();

    EStructuralFeature feature = uRefPackage.getC2U_Au();
    Command set = SetCommand.create(editingDomain, cu, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(cu, feature);
    assertTrue(set.canExecute());
    
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(cu, feature);
  }

  public void testNullUnsettableContainment1()
  {
    C2U cu = uRefFactory.createC2U();
    AU value = null;
    cu.setAu(value);

    EStructuralFeature feature = uRefPackage.getC2U_Au();
    Command set = SetCommand.create(editingDomain, cu, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(cu, feature, value);
    assertTrue(set.canExecute());
    
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(cu, feature, value);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(cu, feature);
  }

  public void testUnsettableContainmentMany()
  {
    C2U cu = uRefFactory.createC2U();
    BU bu0 = uRefFactory.createBU();
    BU bu1 = uRefFactory.createBU();
    BU bu2 = uRefFactory.createBU();

    cu.getBu().add(bu0);
    cu.getBu().add(bu1);
    cu.getBu().add(bu2);

    Object[] values = { bu0, bu1, bu2 };
    
    EStructuralFeature feature = uRefPackage.getC2U_Bu();
    Command set = SetCommand.create(editingDomain, cu, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(cu, feature, values);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(cu, feature, values);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(cu, feature);
  }

  public void testEmptyUnsettableContainmentMany()
  {
    C2U cu = uRefFactory.createC2U();
    Object values[] = new Object[0];
    cu.getBu().clear();

    EStructuralFeature feature = uRefPackage.getC2U_Bu();
    Command set = SetCommand.create(editingDomain, cu, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(cu, feature, values);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(cu, feature, values);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(cu, feature);
  }

  public void testUnsetUnsettableContainmentMany()
  {
    C2U cu = uRefFactory.createC2U();
    
    EStructuralFeature feature = uRefPackage.getC2U_Bu();
    Command set = SetCommand.create(editingDomain, cu, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(cu, feature);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(cu, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(cu, feature);
  }

  public void testReference1ToMany()
  {
    C c = refFactory.createC();
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();

    c.getD().add(d0);
    c.getD().add(d1);
    D[] dBefore = new D[] { d0, d1 };
    D[] dAfter = new D[] { d1 };

    EStructuralFeature feature = refPackage.getD_C();
    EStructuralFeature opposite = refPackage.getC_D();
    Command set = SetCommand.create(editingDomain, d0, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(d0, feature, c);
    assertFeatureSet(c, opposite, dBefore);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(d0, feature);
    assertFeatureSet(c, opposite, dAfter);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(d0, feature, c);
    assertFeatureSet(c, opposite, dBefore);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(d0, feature);
    assertFeatureSet(c, opposite, dAfter);
  }

  public void testNullReference1ToMany()
  {
    D d = refFactory.createD();

    EStructuralFeature feature = refPackage.getD_C();
    Command set = SetCommand.create(editingDomain, d, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(d, feature);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(d, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(d, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(d, feature);
  }

  public void testUnsettableReference1ToMany()
  {
    CU cu = uRefFactory.createCU();
    DU du0 = uRefFactory.createDU();
    DU du1 = uRefFactory.createDU();

    cu.getDu().add(du0);
    cu.getDu().add(du1);
    DU[] duBefore = new DU[] { du0, du1 };
    DU[] duAfter = new DU[] { du1 };

    EStructuralFeature feature = uRefPackage.getDU_Cu();
    EStructuralFeature opposite = uRefPackage.getCU_Du();
    Command set = SetCommand.create(editingDomain, du0, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(du0, feature, cu);
    assertFeatureSet(cu, opposite, duBefore);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(du0, feature);
    assertFeatureSet(cu, opposite, duAfter);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(du0, feature, cu);
    assertFeatureSet(cu, opposite, duBefore);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(du0, feature);
    assertFeatureSet(cu, opposite, duAfter);    
  }

  public void testUnsetUnsettableReference1ToMany()
  {
    DU du = uRefFactory.createDU();

    EStructuralFeature feature = uRefPackage.getDU_Cu();
    Command set = SetCommand.create(editingDomain, du, feature, SetCommand.UNSET_VALUE);
    assertFeatureUnset(du, feature);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(du, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureUnset(du, feature);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(du, feature);
  }

  public void testNullUnsettableReference1ToMany()
  {
    DU du = uRefFactory.createDU();
    CU value = null;
    du.setCu(value);

    EStructuralFeature feature = uRefPackage.getDU_Cu();
    Command set = SetCommand.create(editingDomain, du, feature, SetCommand.UNSET_VALUE);
    assertFeatureSet(du, feature, value);
    assertTrue(set.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(set);

    assertFeatureUnset(du, feature);
    assertTrue(stack.canUndo());

    stack.undo();

    assertFeatureSet(du, feature, value);
    assertTrue(stack.canRedo());

    stack.redo();

    assertFeatureUnset(du, feature);
  }

  public void testInUnsettableMany()
  {
    CU cu = uRefFactory.createCU();
    DU du = uRefFactory.createDU();
    cu.getDu().add(du);

    Command set = SetCommand.create(editingDomain, cu, uRefPackage.getCU_Du(), SetCommand.UNSET_VALUE, 0);
    assertFalse(set.canExecute());
  }

  protected void assertFeatureUnset(EObject owner, EStructuralFeature feature)
  {
    assertFalse(owner.eIsSet(feature));
    if (feature.isMany())
    {
      assertTrue(((EList<?>)owner.eGet(feature)).isEmpty());
    }
    else
    {
      assertEquals(feature.getDefaultValue(), owner.eGet(feature));
    }
  }

  protected void assertFeatureSet(EObject owner, EStructuralFeature feature, Object value)
  {
    assertFalse(feature.isMany());
    assertTrue(owner.eIsSet(feature));
    assertEquals(value, owner.eGet(feature));
    assertOppositeSet(owner, feature, value);
  }

  protected void assertFeatureSet(EObject owner, EStructuralFeature feature, Object[] values)
  {
    assertTrue(feature.isMany());
    assertTrue(owner.eIsSet(feature));

    EList<?> actualValues = (EList<?>)owner.eGet(feature);
    assertEquals(values.length, actualValues.size());
    for (int i = 0, len = values.length; i < len; i++)
    {
      Object value = actualValues.get(i);
      assertEquals(values[i], value);
      assertOppositeSet(owner, feature, value);
    }
  }

  protected void assertOppositeSet(EObject owner, EStructuralFeature feature, Object value)
  {
    if (feature instanceof EReference && value != null)
    {
      EReference opposite = ((EReference)feature).getEOpposite();
      if (opposite != null)
      {
        EObject eObject = (EObject)value;
        assertTrue(eObject.eIsSet(opposite));
        Object oppositeValue = eObject.eGet(opposite);
        if (opposite.isMany())
        {
          assertTrue(((List<?>)oppositeValue).contains(owner));
        }
        else
        {
          assertEquals(owner, oppositeValue);
        }
      }
    }
  }


}
