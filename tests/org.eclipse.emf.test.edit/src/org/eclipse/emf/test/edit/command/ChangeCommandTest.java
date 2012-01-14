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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;

/**
 * Tests for ChangeCommand.  In each case, the model is built, the command is created, executed, undone, and redone.
 * The state of the model and the executability/undoability/redoability of the command are tested between each step.
 */
public class ChangeCommandTest extends TestCase
{
  public ChangeCommandTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("ChangeCommandTest");
    suite.addTest(new ChangeCommandTest("testEObject"));
    suite.addTest(new ChangeCommandTest("testResource"));
    suite.addTest(new ChangeCommandTest("testResourceSet"));
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

  public void testEObject()
  {
    final E e = refFactory.createE();    
    // State 0
    assertTrue(e.getIds().isEmpty());
    
    ChangeCommand changeCommand = new ChangeCommand(e)
    {
      @Override
      protected void doExecute()
      {
        EList<String> ids = e.getIds();
        ids.add("0");
        ids.add("1");
        ids.add("2");        
      }
    };
    
    editingDomain.getCommandStack().execute(changeCommand);
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());
    
    // State 1
    assertEquals(3, e.getIds().size());
    assertEquals("0", e.getIds().get(0));
    assertEquals("1", e.getIds().get(1));
    assertEquals("2", e.getIds().get(2));
    
    editingDomain.getCommandStack().undo();
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());
    
    // State 0
    assertTrue(e.getIds().isEmpty());

    editingDomain.getCommandStack().redo();
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());

    // State 1
    assertEquals(3, e.getIds().size());
    assertEquals("0", e.getIds().get(0));
    assertEquals("1", e.getIds().get(1));
    assertEquals("2", e.getIds().get(2));
  }
  
  public void testResource()
  {
    final Resource r = new ResourceImpl(URI.createURI("r"));
    final E initialE = refFactory.createE();
    final E finalE = refFactory.createE();
    r.getContents().add(initialE);
    final E e = refFactory.createE();
    
    // State 0
    assertEquals(1, r.getContents().size());
    assertEquals(initialE, r.getContents().get(0));
    assertTrue(e.getIds().isEmpty());
    
    ChangeCommand changeCommand = new ChangeCommand(r)
    {
      @Override
      protected void doExecute()
      {
        r.getContents().set(0, finalE);
        r.getContents().add(e);
        
        EList<String> ids = e.getIds();
        ids.add("0");
        ids.add("1");
        ids.add("2");
      }
    };
    
    editingDomain.getCommandStack().execute(changeCommand);
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());
    
    // State 1
    assertEquals(2, r.getContents().size());
    assertEquals(finalE, r.getContents().get(0));
    assertEquals(e, r.getContents().get(1));
    assertEquals(3, e.getIds().size());
    assertEquals("0", e.getIds().get(0));
    assertEquals("1", e.getIds().get(1));
    assertEquals("2", e.getIds().get(2));
    
    editingDomain.getCommandStack().undo();
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());
    
    // State 0
    assertEquals(1, r.getContents().size());
    assertEquals(initialE, r.getContents().get(0));
    assertTrue(e.getIds().isEmpty());

    editingDomain.getCommandStack().redo();
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());

    // State 1
    assertEquals(2, r.getContents().size());
    assertEquals(finalE, r.getContents().get(0));
    assertEquals(e, r.getContents().get(1));
    assertEquals(3, e.getIds().size());
    assertEquals("0", e.getIds().get(0));
    assertEquals("1", e.getIds().get(1));
    assertEquals("2", e.getIds().get(2));
  }
  
  public void testResourceSet()
  {
    final ResourceSet rs = new ResourceSetImpl();
    final Resource r = new ResourceImpl(URI.createURI("r"));
    final E initialE = refFactory.createE();
    final E finalE = refFactory.createE();
    r.getContents().add(initialE);
    final E e = refFactory.createE();
    
    // State 0
    assertTrue(rs.getResources().isEmpty());
    assertEquals(1, r.getContents().size());
    assertEquals(initialE, r.getContents().get(0));
    assertTrue(e.getIds().isEmpty());
    
    ChangeCommand changeCommand = new ChangeCommand(rs)
    {
      @Override
      protected void doExecute()
      {
        rs.getResources().add(r);
        
        r.getContents().set(0, finalE);
        r.getContents().add(e);
        
        EList<String> ids = e.getIds();
        ids.add("0");
        ids.add("1");
        ids.add("2");        
      }
    };
    
    editingDomain.getCommandStack().execute(changeCommand);
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());
    
    // State 1
    assertEquals(1, rs.getResources().size());
    assertEquals(r, rs.getResources().get(0));
    assertEquals(2, r.getContents().size());
    assertEquals(finalE, r.getContents().get(0));
    assertEquals(e, r.getContents().get(1));
    assertEquals(3, e.getIds().size());
    assertEquals("0", e.getIds().get(0));
    assertEquals("1", e.getIds().get(1));
    assertEquals("2", e.getIds().get(2));
    
    editingDomain.getCommandStack().undo();
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());
    
    // State 0
    assertEquals(1, r.getContents().size());
    assertEquals(initialE, r.getContents().get(0));
    assertTrue(e.getIds().isEmpty());

    editingDomain.getCommandStack().redo();
    assertTrue(changeCommand.canExecute());
    assertTrue(changeCommand.canUndo());

    // State 1
    assertEquals(1, rs.getResources().size());
    assertEquals(r, rs.getResources().get(0));
    assertEquals(2, r.getContents().size());
    assertEquals(finalE, r.getContents().get(0));
    assertEquals(e, r.getContents().get(1));
    assertEquals(3, e.getIds().size());
    assertEquals("0", e.getIds().get(0));
    assertEquals("1", e.getIds().get(1));
    assertEquals("2", e.getIds().get(2));
  }  
}
