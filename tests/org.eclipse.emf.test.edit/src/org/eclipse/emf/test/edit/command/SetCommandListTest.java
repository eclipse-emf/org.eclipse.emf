/**
 * <copyright>
 *
 * Copyright (c) 2008 Tonbeller AG, IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Tonbeller - initial API and implementation
 *
 * </copyright>
 *
 * $Id: SetCommandListTest.java,v 1.1 2008/12/02 15:13:42 davidms Exp $
 */
package org.eclipse.emf.test.edit.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * More Tests for SetCommand.
 * This exercises the cases in {@link SetCommand#create(EditingDomain, Object, Object, Object, int) SetCommand.create}
 * in which a whole new list value is set. Changes are performed on an instance of the Ecore metamodel, and its state
 * are tested after execute, undo and redo.
 */
public class SetCommandListTest extends TestCase
{
  public SetCommandListTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("SetCommandListTest");
    suite.addTest(new SetCommandListTest("testInsert"));
    suite.addTest(new SetCommandListTest("testAddAndRemove"));
    return suite;
  }

  /**
   * A stack on which the commands are executed.
   */
  protected CommandStack commandStack;

  /**
   * An editing domain for the tests. 
   */
  protected EditingDomain domain;

  @Override
  public void setUp()
  {
    AdapterFactory adapterFactory = new ReflectiveItemProviderAdapterFactory();
    commandStack = new BasicCommandStack();
    domain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);
  }

  protected EAttribute createAttribute(String name)
  {
    // Make toString() more concise.
    //
    EAttribute attribute =
      new EAttributeImpl()
      {
        @Override
        public String toString()
        {
          return name;
        }
      };
    attribute.setName(name);
    return attribute;
  }

  public void testInsert()
  {
    EClass eclass = EcoreFactory.eINSTANCE.createEClass();
    EAttribute a1 = createAttribute("a1");
    EAttribute a2 = createAttribute("a2");
    List<EAttribute> before = new ArrayList<EAttribute>();
    before.add(a1);
    before.add(a2);
    eclass.getEStructuralFeatures().addAll(before);

    List<EAttribute> after = new ArrayList<EAttribute>();
    after.add(a1);
    after.add(createAttribute("b1"));
    after.add(createAttribute("b2"));
    after.add(createAttribute("b3"));
    after.add(a2);

    Command command = SetCommand.create(domain, eclass, EcorePackage.eINSTANCE.getEClass_EStructuralFeatures(), after);

    commandStack.execute(command);
    assertEquals(after.toString(), eclass.getEAttributes().toString());

    commandStack.undo();
    assertEquals(before.toString(), eclass.getEAttributes().toString());

    commandStack.redo();
    assertEquals(after.toString(), eclass.getEAttributes().toString());
  }

  public void testAddAndRemove()
  {
    EClass eclass = EcoreFactory.eINSTANCE.createEClass();
    EAttribute a1 = createAttribute("a1");
    EAttribute a2 = createAttribute("a2");
    EAttribute a3 = createAttribute("a3");
    List<EAttribute> before = new ArrayList<EAttribute>();
    before.add(a1);
    before.add(a2);
    before.add(a3);
    eclass.getEStructuralFeatures().addAll(before);

    List<EAttribute> after = new ArrayList<EAttribute>();
    after.add(a1);
    after.add(createAttribute("b1"));
    after.add(createAttribute("b2"));

    Command command = SetCommand.create(domain, eclass, EcorePackage.eINSTANCE.getEClass_EStructuralFeatures(), after);

    commandStack.execute(command);
    assertEquals(after.toString(), eclass.getEAttributes().toString());

    commandStack.undo();
    assertEquals(before.toString(), eclass.getEAttributes().toString());

    commandStack.redo();
    assertEquals(after.toString(), eclass.getEAttributes().toString());
  }
}
