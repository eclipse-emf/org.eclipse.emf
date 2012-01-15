/**
 * Copyright (c) 2010-2012 Hallvard Traetteberg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hallvard Traetteberg - initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.ui;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.PropertySheetPage;


public class EOperationInvocationView extends AbstractSelectionView
{

  @Override
  public void dispose()
  {
    propertySheetPage.dispose();
    propertySheetPage.dispose();
    super.dispose();
  }

  @Override
  protected boolean isValidSelection(Object o)
  {
    return o instanceof EObject;
  }

  private EObject getSelectedEObject()
  {
    return (EObject)this.selection;
  }

  private final static String nullItem = "<No EOperations available>";

  private static class EOperationInvocation extends ChangeCommand
  {

    private EOperation operation = null;

    private EObject argumentsInstance = null;

    private Collection<?> affectedObjects;

    private Object result;

    public EOperationInvocation(EObject operationOwner)
    {
      super(operationOwner);
    }

    @Override
    public void dispose()
    {
      super.dispose();
      operation = null;
      argumentsInstance = null;
      affectedObjects = null;
      result = null;
    }

    public EObject getOperationOwner()
    {
      return (EObject)notifier;
    }

    public void setOperation(EOperation operation, EObject argumentsInstance)
    {
      this.operation = operation;
      this.argumentsInstance = argumentsInstance;
    }

    private EList<Object> argumentList = new BasicEList<Object>();

    @Override
    protected boolean prepare()
    {
      if (!super.prepare() || getOperationOwner() == null || operation == null)
      {
        return false;
      }
      for (EStructuralFeature property : argumentsInstance.eClass().getEStructuralFeatures())
      {
        Object value = argumentsInstance.eGet(property);
        argumentList.add(value);
      }
      return true;
    }

    @Override
    protected void doExecute()
    {
      try
      {
        result = getOperationOwner().eInvoke(operation, argumentList);
        affectedObjects = operation.isMany() ? (Collection<?>)result : Collections.singleton(result);
      }
      catch (Exception e)
      {
        result = Collections.singletonList(e);
        affectedObjects = Collections.singletonList(e);
      }
    }

    @Override
    public void undo()
    {
      affectedObjects = notifier != null ? Collections.singleton(notifier) : notifiers;
      result = Collections.emptyList();
      super.undo();
    }

    //		public boolean didChange() {
    //			ChangeDescription changes = getChangeDescription();
    //			return changes.getObjectChanges().size() == 0 && changes.getResourceChanges().size() == 0;
    //		}

    private void execute(IEditingDomainProvider editingDomainProvider, Shell shell)
    {
      if (!canExecute())
      {
        return;
      }
      if (editingDomainProvider != null)
      {
        editingDomainProvider.getEditingDomain().getCommandStack().execute(this);
      }
      else
      {
        execute();
      }
      if (affectedObjects != null && shell != null)
      {
        MessageDialog dialog = new MessageDialog(
          shell,
          "Invocation result",
          null,
          String.valueOf(affectedObjects),
          (affectedObjects instanceof Exception ? MessageDialog.ERROR : MessageDialog.INFORMATION),
          new String []{ "Close" },
          0);
        dialog.open();
      }
    }

    @Override
    public Collection<?> getResult()
    {
      return result == null ? Collections.emptyList() : Collections.singleton(result);
    }

    @Override
    public Collection<?> getAffectedObjects()
    {
      return affectedObjects;
    }
  }

  private EOperationInvocation currentInvocation;

  private String toString(EOperation operation)
  {
    String item = operation.getName() + "(";
    for (EParameter param : operation.getEParameters())
    {
      if (param != operation.getEParameters().get(0))
      {
        item += ",";
      }
      EClassifier type = param.getEType();
      item += type.getName() + (param.isMany() ? '*' : 0) + " " + param.getName();
    }
    item += ")";
    return item;
  }

  @Override
  protected void updateView()
  {
    EObject selection = getSelectedEObject();
    EClass eClass = null;
    if (selection == null)
    {
      currentInvocation = null;
    }
    else
    {
      eClass = selection.eClass();
      currentInvocation = new EOperationInvocation(selection);
    }
    // don't re-populate and reset selection, when the EClass is the same
    if (eClass != operationsCombo.getData())
    {
      EList<EOperation> operations = (eClass != null ? eClass.getEAllOperations() : new BasicEList<EOperation>());
      String[] items = new String [operations.size()];
      for (int i = 0; i < items.length; i++)
      {
        items[i] = toString(operations.get(i));
      }
      operationsCombo.setItems(items);
      if (items.length > 0)
      {
        operationsCombo.select(0);
      }
      else
      {
        operationsCombo.setText(nullItem);
      }
      operationsCombo.setData(eClass);
    }
    operationSelected(operationsCombo.getSelectionIndex());
  }

  private void operationSelected(int selectionIndex)
  {
    EClass eClass = (EClass)operationsCombo.getData();
    EOperation operation = null;
    if (eClass != null && selectionIndex >= 0)
    {
      EList<EOperation> operations = eClass.getEAllOperations();
      if (selectionIndex < operations.size())
      {
        operation = operations.get(selectionIndex);
      }
    }
    EObject operationArgumentsInstance = null;
    if (currentInvocation != null)
    {
      if (operation != null)
      {
        operationArgumentsInstance = operationArgumentsInstances.get(operation);
        if (operationArgumentsInstance == null)
        {
          operationArgumentsInstance = createEOperationEObject(createEOperationEClass(operation));
          operationArgumentsInstances.put(operation, operationArgumentsInstance);
          initEOperationOwnerContainer(operationArgumentsInstance, currentInvocation.getOperationOwner());
        }
      }
      currentInvocation.setOperation(operation, operationArgumentsInstance);
    }
    updatePropertySheet(operationArgumentsInstance);
  }

  private EPackage operationArgumentsPackage;

  private EPackage getEOperationEPackage()
  {
    if (operationArgumentsPackage == null)
    {
      operationArgumentsPackage = EcoreFactory.eINSTANCE.createEPackage();
      operationArgumentsPackage.setEFactoryInstance(EcoreFactory.eINSTANCE.createEFactory());
    }
    return operationArgumentsPackage;
  }

  private Map<EOperation, EObject> operationArgumentsInstances = new HashMap<EOperation, EObject>();

  private EClass createEOperationEClass(EOperation operation)
  {
    EClass eClass = createEOperationEClass();
    for (EParameter param : operation.getEParameters())
    {
      EClassifier type = param.getEType();
      EStructuralFeature property = (type instanceof EClass
        ? EcoreFactory.eINSTANCE.createEReference() : EcoreFactory.eINSTANCE.createEAttribute());
      property.setName(param.getName());
      property.setEType(type);
      property.setLowerBound(param.getLowerBound());
      property.setUpperBound(param.getUpperBound());
      eClass.getEStructuralFeatures().add(property);
    }
    return eClass;
  }

  private EObject operationOwnerContainer;

  private EReference operationOwnerContainerContentRef, operationOwnerContainerContextRef;

  private EReference createEObjectEReference(EClass owner, String name)
  {
    EReference ref = EcoreFactory.eINSTANCE.createEReference();
    ref.setName(name);
    ref.setEType(EcorePackage.eINSTANCE.getEObject());
    owner.getEStructuralFeatures().add(ref);
    return ref;
  }

  private EClass createEOperationEClass()
  {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    getEOperationEPackage().getEClassifiers().add(eClass);
    return eClass;
  }

  private EObject createEOperationEObject(EClass eClass)
  {
    EObject eObject = getEOperationEPackage().getEFactoryInstance().create(eClass);
    return eObject;
  }

  private EObject getEOperationOwnerContainer()
  {
    if (operationOwnerContainer == null)
    {
      EClass operationOwnerContainerContainerClass = createEOperationEClass();
      if (operationOwnerContainerContentRef == null)
      {
        operationOwnerContainerContentRef = createEObjectEReference(operationOwnerContainerContainerClass, "contentRef");
        operationOwnerContainerContentRef.setContainment(true);
      }
      if (operationOwnerContainerContextRef == null)
      {
        operationOwnerContainerContextRef = createEObjectEReference(operationOwnerContainerContainerClass, "contextRef");
      }
      operationOwnerContainer = createEOperationEObject(operationOwnerContainerContainerClass);
    }
    return operationOwnerContainer;
  }

  private void initEOperationOwnerContainer(EObject operationArgumentsInstance, EObject operationOwner)
  {
    EObject container = getEOperationOwnerContainer();
    container.eSet(operationOwnerContainerContentRef, operationArgumentsInstance);
    container.eSet(operationOwnerContainerContextRef, getSelectedEObject());
  }

  private Shell parentShell;

  private void updatePropertySheet(EObject eObject)
  {
    if (editingDomainProvider != null)
    {
      AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain)editingDomainProvider.getEditingDomain();
      propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(editingDomain.getAdapterFactory()));
    }
    StructuredSelection selection = (eObject != null && editingDomainProvider != null
      ? new StructuredSelection(eObject) : StructuredSelection.EMPTY);
    propertySheetPage.selectionChanged(null, selection);
  }

  private Combo operationsCombo;

  private PropertySheetPage propertySheetPage;

  @Override
  public void createPartControl(Composite parent)
  {
    parentShell = parent.getShell();
    super.createPartControl(parent);
    parent.setLayout(new GridLayout(2, false));

    operationsCombo = new Combo(parent, SWT.READ_ONLY | SWT.DROP_DOWN);
    operationsCombo.setText(nullItem);
    operationsCombo.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
          operationSelected(operationsCombo.getSelectionIndex());
        }
      });
    operationsCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    Button invokeButton = new Button(parent, SWT.PUSH);
    invokeButton.setText("Invoke!");
    invokeButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
    invokeButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent e)
        {
          EOperationInvocation invocation = currentInvocation;
          currentInvocation = null;
          if (invocation != null)
          {
            invocation.execute(editingDomainProvider, parentShell);
          }
          updateView();
        }
      });

    Composite propertySheetParent = new Composite(parent, SWT.NONE);
    propertySheetParent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    propertySheetParent.setLayout(new FillLayout());

    propertySheetPage = new PropertySheetPage();
    //		propertySheetPage.init(getSite());
    propertySheetPage.createControl(propertySheetParent);
  }

  @Override
  public void setFocus()
  {
    operationsCombo.setFocus();
  }
}
