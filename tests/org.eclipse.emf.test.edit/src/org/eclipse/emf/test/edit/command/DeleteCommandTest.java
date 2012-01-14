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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.test.models.ref.A;
import org.eclipse.emf.test.models.ref.B;
import org.eclipse.emf.test.models.ref.C;
import org.eclipse.emf.test.models.ref.C1;
import org.eclipse.emf.test.models.ref.C4;
import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;
import org.eclipse.emf.test.models.tree.Data;
import org.eclipse.emf.test.models.tree.Node;
import org.eclipse.emf.test.models.tree.TreeFactory;
import org.eclipse.emf.test.models.tree.TreePackage;
import org.eclipse.emf.test.models.tree.provider.TreeItemProviderAdapterFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for DeleteCommand.  In each case, the model is built, the command is created, executed, undone, and redone.
 * The state of the model and the executability/undoability/redoability of the command are tested between each step.
 */
public class DeleteCommandTest extends TestCase
{
  public DeleteCommandTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("DeleteCommandTest");
    suite.addTest(new DeleteCommandTest("testDeleteSingleObject"));
    suite.addTest(new DeleteCommandTest("testDeleteObjectFromList"));
    suite.addTest(new DeleteCommandTest("testDeleteObjectFromBidirectionalLists"));
    suite.addTest(new DeleteCommandTest("testDeleteObjectsWithManyReferences"));
    suite.addTest(new DeleteCommandTest("testDeleteObjectWithContents"));    
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
   * The Tree test package.
   */
  protected TreePackage treePackage;

  
  /**
   * The Tree factory.
   */
  protected TreeFactory treeFactory;

  /**
   * An editing domain for for these tests.
   */
  protected EditingDomain editingDomain;

  /**
   * The resource, from the editing domain's resource set, that will contain the objects.  
   */
  protected Resource resource;

  @Override
  protected void setUp() throws Exception
  {
    refPackage = RefPackage.eINSTANCE;
    refFactory = refPackage.getRefFactory();

    treePackage = TreePackage.eINSTANCE;
    treeFactory = treePackage.getTreeFactory();
    
    AdapterFactory adapterFactory = new ComposedAdapterFactory(new AdapterFactory[] { new RefItemProviderAdapterFactory(), new TreeItemProviderAdapterFactory() });
    CommandStack commandStack = new BasicCommandStack();
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack);

    ResourceSet resourceSet = editingDomain.getResourceSet();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new ResourceFactoryImpl());
    resource = resourceSet.createResource(URI.createURI(""));
  }

  public void testDeleteSingleObject()
  {
    C1 c1 = refFactory.createC1();
    resource.getContents().add(c1);
    A a = refFactory.createA();
    B b = refFactory.createB();

    c1.setA(a);
    c1.getB().add(b);
    a.setB(b);

    Command delete = DeleteCommand.create(editingDomain, a);

    assertEquals(1, c1.getB().size());
    assertSame(b, c1.getB().get(0));
    assertSame(a, c1.getA());
    assertSame(a, b.getA());
    assertSame(b, a.getB());
    assertTrue(delete.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(delete);

    assertEquals(1, c1.getB().size());
    assertSame(b, c1.getB().get(0));
    assertNull(c1.getA());
    assertNull(b.getA());
    assertNull(a.getB());
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(1, c1.getB().size());
    assertSame(b, c1.getB().get(0));
    assertSame(a, c1.getA());
    assertSame(a, b.getA());
    assertSame(b, a.getB());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(1, c1.getB().size());
    assertSame(b, c1.getB().get(0));
    assertNull(c1.getA());
    assertNull(b.getA());
    assertNull(a.getB());
    assertTrue(stack.canUndo());
  }

  public void testDeleteObjectFromList()
  {
    C1 c1 = refFactory.createC1();
    resource.getContents().add(c1);
    A a = refFactory.createA();
    B b0 = refFactory.createB();
    B b1 = refFactory.createB();
    B b2 = refFactory.createB();

    c1.setA(a);
    c1.getB().add(b0);
    c1.getB().add(b1);
    c1.getB().add(b2);
    a.setB(b1);

    Command delete = DeleteCommand.create(editingDomain, b1);

    assertEquals(3, c1.getB().size());
    assertSame(b1, c1.getB().get(1));
    assertSame(a, c1.getA());
    assertSame(a, b1.getA());
    assertSame(b1, a.getB());
    assertTrue(delete.canExecute());

    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(delete);

    assertEquals(2, c1.getB().size());
    assertSame(b2, c1.getB().get(1));
    assertSame(a, c1.getA());
    assertNull(b1.getA());
    assertNull(a.getB());
    assertTrue(stack.canUndo());

    stack.undo();

    assertEquals(3, c1.getB().size());
    assertSame(b1, c1.getB().get(1));
    assertSame(a, c1.getA());
    assertSame(a, b1.getA());
    assertSame(b1, a.getB());
    assertTrue(stack.canRedo());

    stack.redo();

    assertEquals(2, c1.getB().size());
    assertSame(b2, c1.getB().get(1));
    assertSame(a, c1.getA());
    assertNull(b1.getA());
    assertNull(a.getB());
    assertTrue(stack.canUndo()); 
  }

  public void testDeleteObjectFromBidirectionalLists()
  {
    C4 c4 = refFactory.createC4();
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    E e0 = refFactory.createE();
    E e1 = refFactory.createE();

    resource.getContents().add(c4);
    resource.getContents().add(e0);
    resource.getContents().add(e1);

    c4.getD().add(d0);
    c4.getD().add(d1);
    e0.getD().add(d0);
    e0.getD().add(d1);
    e1.getD().add(d0);
    e1.getD().add(d1);

    Command delete = DeleteCommand.create(editingDomain, d0);

    assertEquals(2, c4.getD().size());
    assertSame(d0, c4.getD().get(0));
    assertSame(d1, c4.getD().get(1));

    assertEquals(2, d0.getE().size());
    assertSame(e0, d0.getE().get(0));
    assertSame(e1, d0.getE().get(1));

    assertEquals(2, d1.getE().size());
    assertSame(e0, d1.getE().get(0));
    assertSame(e1, d1.getE().get(1));

    assertEquals(2, e0.getD().size());
    assertSame(d0, e0.getD().get(0));
    assertSame(d1, e0.getD().get(1));

    assertEquals(2, e1.getD().size());
    assertSame(d0, e1.getD().get(0));
    assertSame(d1, e1.getD().get(1));

    assertTrue(delete.canExecute());
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(delete);

    assertEquals(1, c4.getD().size());
    assertSame(d1, c4.getD().get(0));

    assertEquals(0, d0.getE().size());

    assertEquals(2, d1.getE().size());
    assertSame(e0, d1.getE().get(0));
    assertSame(e1, d1.getE().get(1));

    assertEquals(1, e0.getD().size());
    assertSame(d1, e0.getD().get(0));

    assertEquals(1, e1.getD().size());
    assertSame(d1, e1.getD().get(0));

    assertTrue(stack.canUndo());
    stack.undo();

    assertEquals(2, c4.getD().size());
    assertSame(d0, c4.getD().get(0));
    assertSame(d1, c4.getD().get(1));

    // RemoveCommand has a known bug: it doesn't take any action to make a reverse list in order after an undo.
    //
    assertEquals(2, d0.getE().size());
    assertSame(e0, d0.getE().get(1)); //0
    assertSame(e1, d0.getE().get(0)); //1

    assertEquals(2, d1.getE().size());
    assertSame(e0, d1.getE().get(0));
    assertSame(e1, d1.getE().get(1));

    assertEquals(2, e0.getD().size());
    assertSame(d0, e0.getD().get(0));
    assertSame(d1, e0.getD().get(1));

    assertEquals(2, e1.getD().size());
    assertSame(d0, e1.getD().get(0));
    assertSame(d1, e1.getD().get(1));

    assertTrue(stack.canRedo());
    stack.redo();

    assertEquals(1, c4.getD().size());
    assertSame(d1, c4.getD().get(0));

    assertEquals(0, d0.getE().size());

    assertEquals(2, d1.getE().size());
    assertSame(e0, d1.getE().get(0));
    assertSame(e1, d1.getE().get(1));

    assertEquals(1, e0.getD().size());
    assertSame(d1, e0.getD().get(0));

    assertEquals(1, e1.getD().size());
    assertSame(d1, e1.getD().get(0));

    assertTrue(stack.canUndo()); 
  }

  public void testDeleteObjectsWithManyReferences()
  {
    // This test is pretty complicated. Here are all the references:
    //   c4.c: [c]
    //   c4.d: [d0], d1
    //   c.d: d0, [d1]
    //   d0.c: c
    //   d0.e: [e0], [e2], e3
    //   d1.c: [c]
    //   d1.e: e1, e2, e3
    //   b.d: [d0], d1
    //   e0.d: [d0]
    //   e1.d: d1
    //   e2.d: [d0], d1
    //   e3.d: d0, d1
    //
    // The deletion of c and d0 (they are removed from c4) cause all of the removes indicated by square brackets.
    // References involving e3 are not affected as e3 is not in the editing domain.
    // Note that the bidirectional reference between c and d0 is retained.

    
    C4 c4 = refFactory.createC4();
    B b = refFactory.createB();
    C c = refFactory.createC();
    D d0 = refFactory.createD();
    D d1 = refFactory.createD();
    E e0 = refFactory.createE();
    E e1 = refFactory.createE();
    E e2 = refFactory.createE();
    E e3 = refFactory.createE();

    resource.getContents().add(c4);
    resource.getContents().add(b);
    resource.getContents().add(e0);
    resource.getContents().add(e1);
    resource.getContents().add(e2);

    c4.setC(c);
    c4.getD().add(d0);
    c4.getD().add(d1);

    d0.setC(c);
    d1.setC(c);

    b.getD().add(d0);
    b.getD().add(d1);

    e0.getD().add(d0);
    e1.getD().add(d1);
    e2.getD().add(d0);
    e2.getD().add(d1);
    e3.getD().add(d0);
    e3.getD().add(d1);

    Collection<EObject> collection = new ArrayList<EObject>();
    collection.add(c);
    collection.add(d0);
    Command delete = DeleteCommand.create(editingDomain, collection);

    assertSame(c, c4.getC());
    assertEquals(2, c4.getD().size());
    assertSame(d0, c4.getD().get(0));
    assertSame(d1, c4.getD().get(1));

    assertEquals(2, c.getD().size());
    assertSame(d0, c.getD().get(0));
    assertSame(d1, c.getD().get(1));

    assertSame(c, d0.getC());
    assertEquals(3, d0.getE().size());
    assertSame(e0, d0.getE().get(0));
    assertSame(e2, d0.getE().get(1));
    assertSame(e3, d0.getE().get(2));

    assertSame(c, d1.getC());
    assertEquals(3, d1.getE().size());
    assertSame(e1, d1.getE().get(0));
    assertSame(e2, d1.getE().get(1));
    assertSame(e3, d1.getE().get(2));

    assertEquals(2, b.getD().size());
    assertSame(d0, b.getD().get(0));
    assertSame(d1, b.getD().get(1));

    assertEquals(1, e0.getD().size());
    assertSame(d0, e0.getD().get(0));
    
    assertEquals(1, e1.getD().size());
    assertSame(d1, e1.getD().get(0));
    
    assertEquals(2, e2.getD().size());
    assertSame(d0, e2.getD().get(0));
    assertSame(d1, e2.getD().get(1));
    
    assertEquals(2, e3.getD().size());
    assertSame(d0, e3.getD().get(0));
    assertSame(d1, e3.getD().get(1));

    assertTrue(delete.canExecute());
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(delete);

    assertNull(c4.getC());
    assertEquals(1, c4.getD().size());
    assertSame(d1, c4.getD().get(0));

    assertEquals(1, c.getD().size());
    assertSame(d0, c.getD().get(0));

    assertSame(c, d0.getC());
    assertEquals(1, d0.getE().size());
    assertSame(e3, d0.getE().get(0));

    assertNull(d1.getC());
    assertEquals(3, d1.getE().size());
    assertSame(e1, d1.getE().get(0));
    assertSame(e2, d1.getE().get(1));
    assertSame(e3, d1.getE().get(2));

    assertEquals(1, b.getD().size());
    assertSame(d1, b.getD().get(0));

    assertEquals(0, e0.getD().size());
    
    assertEquals(1, e1.getD().size());
    assertSame(d1, e1.getD().get(0));
    
    assertEquals(1, e2.getD().size());
    assertSame(d1, e2.getD().get(0));
    
    assertEquals(2, e3.getD().size());
    assertSame(d0, e3.getD().get(0));
    assertSame(d1, e3.getD().get(1));

    assertTrue(stack.canUndo());
    stack.undo();

    assertSame(c, c4.getC());
    assertEquals(2, c4.getD().size());
    assertSame(d0, c4.getD().get(0));
    assertSame(d1, c4.getD().get(1));

    assertEquals(2, c.getD().size());
    assertSame(d0, c.getD().get(0));
    assertSame(d1, c.getD().get(1));

    assertSame(c, d0.getC());

    // RemoveCommand has a known bug: it doesn't take any action to make a reverse list in order after an undo.
    //
    assertEquals(3, d0.getE().size());
    assertSame(e0, d0.getE().get(2)); //0
    assertSame(e2, d0.getE().get(1));
    assertSame(e3, d0.getE().get(0)); //2

    assertSame(c, d1.getC());
    assertEquals(3, d1.getE().size());
    assertSame(e1, d1.getE().get(0));
    assertSame(e2, d1.getE().get(1));
    assertSame(e3, d1.getE().get(2));

    assertEquals(2, b.getD().size());
    assertSame(d0, b.getD().get(0));
    assertSame(d1, b.getD().get(1));

    assertEquals(1, e0.getD().size());
    assertSame(d0, e0.getD().get(0));
    
    assertEquals(1, e1.getD().size());
    assertSame(d1, e1.getD().get(0));
    
    assertEquals(2, e2.getD().size());
    assertSame(d0, e2.getD().get(0));
    assertSame(d1, e2.getD().get(1));
    
    assertEquals(2, e3.getD().size());
    assertSame(d0, e3.getD().get(0));
    assertSame(d1, e3.getD().get(1));

    assertTrue(stack.canRedo());
    stack.redo();

    assertNull(c4.getC());
    assertEquals(1, c4.getD().size());
    assertSame(d1, c4.getD().get(0));

    assertEquals(1, c.getD().size());
    assertSame(d0, c.getD().get(0));

    assertSame(c, d0.getC());
    assertEquals(1, d0.getE().size());
    assertSame(e3, d0.getE().get(0));

    assertNull(d1.getC());
    assertEquals(3, d1.getE().size());
    assertSame(e1, d1.getE().get(0));
    assertSame(e2, d1.getE().get(1));
    assertSame(e3, d1.getE().get(2));

    assertEquals(1, b.getD().size());
    assertSame(d1, b.getD().get(0));

    assertEquals(0, e0.getD().size());
    
    assertEquals(1, e1.getD().size());
    assertSame(d1, e1.getD().get(0));
    
    assertEquals(1, e2.getD().size());
    assertSame(d1, e2.getD().get(0));
    
    assertEquals(2, e3.getD().size());
    assertSame(d0, e3.getD().get(0));
    assertSame(d1, e3.getD().get(1));

    assertTrue(stack.canUndo());
  }

  public void testDeleteObjectWithContents()
  {
    // n.children: [c0], c1
    // c0.children: gc0, gc1
    // c0.data: [c0d]
    // c1.children: gc2, gc3
    // c1.data: c1d
    // gc0.relatedNodes: gc1, gc2
    // gc0.data: [gc0d]
    // gc2.relatedNodes: [gc1], gc3
    // gc3.data: gc3d
    // c0d.node: [c0]
    // c1d.node: c1
    // gc0d.node: [gc0]
    // gc3d.node: gc3

    Node n = treeFactory.createNode();    // node
    Node c0 = treeFactory.createNode();   // children
    Node c1 = treeFactory.createNode();
    Node gc0 = treeFactory.createNode();  // grandchildren
    Node gc1 = treeFactory.createNode();
    Node gc2 = treeFactory.createNode();
    Node gc3 = treeFactory.createNode();

    Data c0d = treeFactory.createData();  // children data
    Data c1d = treeFactory.createData();
    Data gc0d = treeFactory.createData(); // grandchildren data
    Data gc3d = treeFactory.createData();

    resource.getContents().add(n);
    resource.getContents().add(c0d);
    resource.getContents().add(c1d);
    resource.getContents().add(gc0d);
    resource.getContents().add(gc3d);

    n.getChildren().add(c0);
    n.getChildren().add(c1);

    c0.getChildren().add(gc0);
    c0.getChildren().add(gc1);
    c1.getChildren().add(gc2);
    c1.getChildren().add(gc3);

    gc0.getRelatedNodes().add(gc1);
    gc0.getRelatedNodes().add(gc2);
    gc2.getRelatedNodes().add(gc1);
    gc2.getRelatedNodes().add(gc3);

    c0.setData(c0d);
    c1.setData(c1d);
    gc0.setData(gc0d);
    gc3.setData(gc3d);

    Command delete = DeleteCommand.create(editingDomain, c0);

    assertEquals(2, n.getChildren().size());
    assertSame(c0, n.getChildren().get(0));
    assertSame(c1, n.getChildren().get(1));

    assertEquals(2, c0.getChildren().size());
    assertSame(gc0, c0.getChildren().get(0));
    assertSame(gc1, c0.getChildren().get(1));
    assertSame(c0d, c0.getData());

    assertEquals(2, c1.getChildren().size());
    assertSame(gc2, c1.getChildren().get(0));
    assertSame(gc3, c1.getChildren().get(1));
    assertSame(c1d, c1.getData());

    assertEquals(2, gc0.getRelatedNodes().size());
    assertSame(gc1, gc0.getRelatedNodes().get(0));
    assertSame(gc2, gc0.getRelatedNodes().get(1));
    assertSame(gc0d, gc0.getData());

    assertEquals(2, gc2.getRelatedNodes().size());
    assertSame(gc1, gc2.getRelatedNodes().get(0));
    assertSame(gc3, gc2.getRelatedNodes().get(1));

    assertSame(gc3d, gc3.getData());

    assertSame(c0, c0d.getNode());
    assertSame(c1, c1d.getNode());
    assertSame(gc0, gc0d.getNode());
    assertSame(gc3, gc3d.getNode());

    assertTrue(delete.canExecute());
    CommandStack stack = editingDomain.getCommandStack();
    stack.execute(delete);
    
    assertEquals(1, n.getChildren().size());
    assertSame(c1, n.getChildren().get(0));

    assertEquals(2, c0.getChildren().size());
    assertSame(gc0, c0.getChildren().get(0));
    assertSame(gc1, c0.getChildren().get(1));
    assertNull(c0.getData());

    assertEquals(2, c1.getChildren().size());
    assertSame(gc2, c1.getChildren().get(0));
    assertSame(gc3, c1.getChildren().get(1));
    assertSame(c1d, c1.getData());

    assertEquals(2, gc0.getRelatedNodes().size());
    assertSame(gc1, gc0.getRelatedNodes().get(0));
    assertSame(gc2, gc0.getRelatedNodes().get(1));
    assertNull(gc0.getData());

    assertEquals(1, gc2.getRelatedNodes().size());
    assertSame(gc3, gc2.getRelatedNodes().get(0));

    assertSame(gc3d, gc3.getData());

    assertNull(c0d.getNode());
    assertSame(c1, c1d.getNode());
    assertNull(gc0d.getNode());
    assertSame(gc3, gc3d.getNode());

    assertTrue(stack.canUndo());
    stack.undo();

    assertEquals(2, n.getChildren().size());
    assertSame(c0, n.getChildren().get(0));
    assertSame(c1, n.getChildren().get(1));

    assertEquals(2, c0.getChildren().size());
    assertSame(gc0, c0.getChildren().get(0));
    assertSame(gc1, c0.getChildren().get(1));
    assertSame(c0d, c0.getData());

    assertEquals(2, c1.getChildren().size());
    assertSame(gc2, c1.getChildren().get(0));
    assertSame(gc3, c1.getChildren().get(1));
    assertSame(c1d, c1.getData());

    assertEquals(2, gc0.getRelatedNodes().size());
    assertSame(gc1, gc0.getRelatedNodes().get(0));
    assertSame(gc2, gc0.getRelatedNodes().get(1));
    assertSame(gc0d, gc0.getData());

    assertEquals(2, gc2.getRelatedNodes().size());
    assertSame(gc1, gc2.getRelatedNodes().get(0));
    assertSame(gc3, gc2.getRelatedNodes().get(1));

    assertSame(gc3d, gc3.getData());

    assertSame(c0, c0d.getNode());
    assertSame(c1, c1d.getNode());
    assertSame(gc0, gc0d.getNode());
    assertSame(gc3, gc3d.getNode());

    assertTrue(stack.canRedo());
    stack.redo();

    assertEquals(1, n.getChildren().size());
    assertSame(c1, n.getChildren().get(0));

    assertEquals(2, c0.getChildren().size());
    assertSame(gc0, c0.getChildren().get(0));
    assertSame(gc1, c0.getChildren().get(1));
    assertNull(c0.getData());

    assertEquals(2, c1.getChildren().size());
    assertSame(gc2, c1.getChildren().get(0));
    assertSame(gc3, c1.getChildren().get(1));
    assertSame(c1d, c1.getData());

    assertEquals(2, gc0.getRelatedNodes().size());
    assertSame(gc1, gc0.getRelatedNodes().get(0));
    assertSame(gc2, gc0.getRelatedNodes().get(1));
    assertNull(gc0.getData());

    assertEquals(1, gc2.getRelatedNodes().size());
    assertSame(gc3, gc2.getRelatedNodes().get(0));

    assertSame(gc3d, gc3.getData());

    assertNull(c0d.getNode());
    assertSame(c1, c1d.getNode());
    assertNull(gc0d.getNode());
    assertSame(gc3, gc3d.getNode());

    assertTrue(stack.canUndo());
  }
}
