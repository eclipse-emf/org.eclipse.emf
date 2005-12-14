/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: ModelConverterDescriptorSelectionPage.java,v 1.1 2005/12/14 07:45:42 marcelop Exp $
 */
package org.eclipse.emf.converter.ui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.ui.contribution.ModelConverterDescriptor;
import org.eclipse.emf.converter.ui.contribution.ModelConverterManager;

/**
 * @since 2.2.0
 */
public abstract class ModelConverterDescriptorSelectionPage extends WizardSelectionPage implements ISelectionChangedListener
{
  protected ModelConverterManager modelConverterManager;
  protected ModelConverterDescriptor descriptor;
  
  protected TableViewer descriptorTableViewer;  
  protected ImageDescriptor modeConverterWizardDefaultImageDescriptor;
  protected Map modelConverterWizardNodeMap;
  protected IStructuredSelection selection;
  protected IWorkbench workbench;  
  
  protected boolean firstTime = true;
  protected Set initializedWizards = new HashSet();

  public ModelConverterDescriptorSelectionPage(String pageId, ModelConverterManager modelConverterManager, IWorkbench workbench, IStructuredSelection selection)
  {
    super(pageId);
    this.modelConverterManager = modelConverterManager;
    this.workbench = workbench;
    this.selection = selection;
  }
  
  public void dispose()
  {
    if (descriptorTableViewer != null)
    {
      descriptorTableViewer.removeSelectionChangedListener(this);
      descriptorTableViewer = null;
    }
    
    if (initializedWizards != null)
    {
      initializedWizards.clear();
      initializedWizards = null;
    }
    
    modeConverterWizardDefaultImageDescriptor = null;
    
    descriptor = null;
    modelConverterManager = null;

    clearCache();
    
    super.dispose();
  }
  
  public void clearCache()
  {
    if (modelConverterWizardNodeMap != null)
    {
      for (Iterator i = modelConverterWizardNodeMap.values().iterator(); i.hasNext();)
      {
        IWizardNode wizardNode = (IWizardNode)i.next();
        wizardNode.dispose();
      }

      modelConverterWizardNodeMap.clear();
      modelConverterWizardNodeMap = null;
    }

    selection = null;
    workbench = null;    
  }
  
  public void setModeConverterWizardDefaultImageDescriptor(ImageDescriptor imageDescriptor)
  {
    this.modeConverterWizardDefaultImageDescriptor = imageDescriptor;
  }
  
  public String getLastModelConverterDescriptorId()
  {
    return ConverterPlugin.getPlugin().getPluginPreferences().getString(modelConverterManager.getClass().getName());
  }
  
  public void performFinish()
  {
    if (descriptor != null)
    {
      ConverterPlugin.getPlugin().getPluginPreferences().setValue(modelConverterManager.getClass().getName(), descriptor.getID());
      ConverterPlugin.getPlugin().savePluginPreferences();
    }    
  }

  public void setModelConverterDescriptor(ModelConverterDescriptor descriptor)
  {
    if (getModelConverterDescriptor() != descriptor)
    {
      this.descriptor = descriptor;
      if (descriptorTableViewer != null)
      {
        if (descriptor != null)
        {
          descriptorTableViewer.setSelection(new StructuredSelection(descriptor), true);
        }
        else
        {
          descriptorTableViewer.setSelection(StructuredSelection.EMPTY);
        }
      }
    }
  }

  public ModelConverterDescriptor getModelConverterDescriptor()
  {
    return descriptor;
  }

  public void setVisible(boolean visible)
  {
    super.setVisible(visible);
    if (visible && firstTime)
    {
      firstTime = false;
      Table table = descriptorTableViewer.getTable();
      firstTimeVisible(table);
      table.setFocus();
    }
  }
  
  protected void firstTimeVisible(Table descriptorTable)
  {
    if(descriptorTable.getItemCount() > 0)
    {
      ModelConverterDescriptor descriptor = getModelConverterDescriptor();
      if (descriptor == null)
      {
        descriptor = modelConverterManager.getModelConverterDescriptor(getLastModelConverterDescriptorId());
        if (descriptor == null)
        {
          descriptor = (ModelConverterDescriptor)descriptorTable.getItem(0).getData();
        }
        setModelConverterDescriptor(descriptor);
      }
    }
    else
    {
      setErrorMessage(getNoModelConverterMessage());
    }
  }
  
  protected String getNoModelConverterMessage()
  {
    return GenModelEditPlugin.INSTANCE.getString("_UI_NoModelConverter_error");
  }
  
  abstract protected String getSelectModelConverterLabel();
  abstract protected Object[] getTableInput();

  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    {
      GridLayout layout = new GridLayout();
      layout.numColumns = 1;
      layout.verticalSpacing = 12;
      composite.setLayout(layout);

      GridData data = new GridData();
      data.verticalAlignment = GridData.FILL;
      data.grabExcessVerticalSpace = true;
      data.horizontalAlignment = GridData.FILL;
      composite.setLayoutData(data);
    }

    Label label = new Label(composite, SWT.NONE);
    label.setText(getSelectModelConverterLabel());
    {
      GridData data = new GridData();
      data.verticalAlignment = SWT.FILL;
      data.horizontalAlignment = SWT.FILL;
      label.setLayoutData(data);
    }

    Table descriptorTable = new Table(composite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
    {
      GridData data = new GridData();
      data.widthHint = Display.getCurrent().getBounds().width / 5;
      data.heightHint = Display.getCurrent().getBounds().height / 3;
      data.verticalAlignment = SWT.FILL;
      data.horizontalAlignment = SWT.FILL;
      data.grabExcessHorizontalSpace = true;
      data.grabExcessVerticalSpace = true;
      descriptorTable.setLayoutData(data);
    }

    descriptorTableViewer = new TableViewer(descriptorTable);
    descriptorTableViewer.setContentProvider(new ArrayContentProvider());
    descriptorTableViewer.setLabelProvider(new ModelConverterManager.ModelConverterDescriptorLabelProvider());
    descriptorTableViewer.setSorter(new ViewerSorter());

    descriptorTableViewer.addDoubleClickListener(new IDoubleClickListener()
      {
        public void doubleClick(DoubleClickEvent event)
        {
          if (canFlipToNextPage())
          {
            getContainer().showPage(getNextPage());
          }
        }
      });

    descriptorTableViewer.setInput(getTableInput());
    descriptorTableViewer.addSelectionChangedListener(this);
    if (getModelConverterDescriptor() != null)
    {
      descriptorTableViewer.setSelection(new StructuredSelection(getModelConverterDescriptor()), true);
    }
    setControl(composite);
  }

  public void selectionChanged(SelectionChangedEvent event)
  {
    ISelection selection = event.getSelection();
    if (!selection.isEmpty() && selection instanceof IStructuredSelection)
    {
      Object selectedObject = ((IStructuredSelection)selection).getFirstElement();
      if (selectedObject instanceof ModelConverterDescriptor)
      {
        descriptor = (ModelConverterDescriptor)selectedObject;
        if (modelConverterWizardNodeMap == null)
        {
          modelConverterWizardNodeMap = modelConverterManager.createModelConverterDescriptorWizardNodeMap();
        }
        setMessage(descriptor.getDescription(), IMessageProvider.NONE);
        setSelectedNode((IWizardNode)modelConverterWizardNodeMap.get(descriptor));
        return;
      }
    }

    setPageComplete(false);
  }

  public boolean isPageComplete()
  {
    return descriptor != null;
  }

  public IWizardPage getNextPage()
  {
    IWizard modelConverterWizard = (IWizard)getSelectedNode().getWizard();
    if (initializedWizards.add(modelConverterWizard))
    {
      if (modelConverterWizard instanceof Wizard)
      {
        Wizard wizard = (Wizard)modelConverterWizard;
        if (modeConverterWizardDefaultImageDescriptor != null)
        {
          wizard.setDefaultPageImageDescriptor(modeConverterWizardDefaultImageDescriptor);
        }
        if (wizard.getWindowTitle() == null)
        {
          wizard.setWindowTitle(getWizard().getWindowTitle());
        }
      }
      
      if (modelConverterWizard instanceof IWorkbenchWizard)
      {
        ((IWorkbenchWizard)modelConverterWizard).init(workbench, selection);
      }
    }

    adjustModelConverterWizard(modelConverterWizard);
    IWizardPage wizardPage = super.getNextPage();
    
    IWizardNode wizardNode = getSelectedNode();
    if (wizardNode instanceof ModelConverterManager.ModelConverterDescriptorWizardNode)
    {
      ((ModelConverterManager.ModelConverterDescriptorWizardNode)wizardNode).setContentCreated(true);
    }
    return wizardPage;
  }
  
  protected void adjustModelConverterWizard(IWizard modelConverterWizard)
  {   
  }
}