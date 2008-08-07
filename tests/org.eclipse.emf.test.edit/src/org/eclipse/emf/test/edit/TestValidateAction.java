/**
 * <copyright>
 *
 * Copyright (c) 2008 IBM Corporation and others.
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
 * $Id: TestValidateAction.java,v 1.1 2008/08/07 16:15:55 marcelop Exp $
 */
package org.eclipse.emf.test.edit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.test.common.TestUtil;

/**
 * @since 2.5.0
 */
public class TestValidateAction extends TestCase
{
  public TestValidateAction(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite suite = new TestSuite("TestValidateAction");
    suite.addTest(new TestValidateAction("testUpdateSelection"));
    suite.addTest(new TestValidateAction("testValidateMultipleSelections"));
    return suite;
  }
  
  protected static class MyValidateAction extends ValidateAction
  {
    public List<EObject> filterRootEObjects(List<?> objects)
    {
      updateSelection(new StructuredSelection(objects));
      return selectedObjects;
    }

    @Override
    public Diagnostic validate(IProgressMonitor progressMonitor)
    {
      return super.validate(progressMonitor);
    }
  }

  public void testUpdateSelection() throws Exception
  {
    MyValidateAction myValidateAction = new MyValidateAction();

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(1);
      objects.add(this);
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.Literals.ECLASS);
      objects.add(2);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(2));
      expectedRoots.add(objects.get(3));

      assertFalse(myValidateAction.updateSelection(new StructuredSelection(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.Literals.ECLASS);

      assertTrue(TestUtil.areEqual(objects, myValidateAction.filterRootEObjects(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.Literals.ECLASS);
      objects.add(EcorePackage.eINSTANCE);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(2));

      assertTrue(TestUtil.areEqual(expectedRoots, myValidateAction.filterRootEObjects(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.Literals.ECLASS);
      objects.add(EcorePackage.eINSTANCE);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(2));

      assertTrue(TestUtil.areEqual(expectedRoots, myValidateAction.filterRootEObjects(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.eINSTANCE);
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.Literals.ECLASS);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(0));

      assertTrue(TestUtil.areEqual(expectedRoots, myValidateAction.filterRootEObjects(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.eINSTANCE);
      objects.add(EcorePackage.Literals.ECLASS);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(1));

      assertTrue(TestUtil.areEqual(expectedRoots, myValidateAction.filterRootEObjects(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.Literals.EANNOTATION);
      objects.add(EcorePackage.eINSTANCE);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(1));

      assertTrue(TestUtil.areEqual(expectedRoots, myValidateAction.filterRootEObjects(objects)));
    }

    {
      List<Object> objects = new ArrayList<Object>();
      objects.add(EcorePackage.eINSTANCE);
      objects.add(EcorePackage.Literals.EANNOTATION);

      List<Object> expectedRoots = new ArrayList<Object>();
      expectedRoots.add(objects.get(0));

      assertTrue(TestUtil.areEqual(expectedRoots, myValidateAction.filterRootEObjects(objects)));
    }
  }

  public void testValidateMultipleSelections() throws Exception
  {
    // # of problems in ePackage: 3 (name, nsURI, prefix)
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();

    // # of problems in eClass1: 1 (name)
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    ePackage.getEClassifiers().add(eClass1);

    // # of problems in eClass2: 0
    EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
    eClass2.setName("EClass2");
    ePackage.getEClassifiers().add(eClass2);

    // # of problems in eAttribute: 3 (name, no type, no eType reference)
    EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    eClass2.getEStructuralFeatures().add(eAttribute);

    MyValidateAction validateAction = new MyValidateAction();

    validateAction.selectionChanged(createSelectionChangedEvent(ePackage));
    assertEquals(7, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(eClass1));
    assertEquals(1, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(eClass2));
    assertEquals(3, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(eAttribute));
    assertEquals(3, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(ePackage, eClass1, eClass2, eAttribute));
    assertEquals(7, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(eClass1, eClass2, eAttribute));
    assertEquals(4, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(eAttribute, eClass2, eClass1));
    assertEquals(4, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(eAttribute, ePackage));
    assertEquals(7, validateAction.validate(new NullProgressMonitor()).getChildren().size());

    validateAction.selectionChanged(createSelectionChangedEvent(ePackage, eAttribute));
    assertEquals(7, validateAction.validate(new NullProgressMonitor()).getChildren().size());
  }

  protected SelectionChangedEvent createSelectionChangedEvent(final Object... objects)
  {
    ISelectionProvider selectionProvider = new ISelectionProvider()
    {
      public ISelection getSelection()
      {
        return new StructuredSelection(objects);
      }

      public void addSelectionChangedListener(ISelectionChangedListener listener)
      {
        // Empty block
      }

      public void removeSelectionChangedListener(ISelectionChangedListener listener)
      {
        // Empty block
      }

      public void setSelection(ISelection selection)
      {
        // Empty block
      }
    };
    return new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection());
  }
}
