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
 * $Id: EMFExportWizard.java,v 1.2 2005/12/14 21:59:06 marcelop Exp $
 */
package org.eclipse.emf.exporter.ui;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import org.eclipse.emf.converter.ui.ModelConverterDescriptorSelectionPage;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.exporter.ExporterPlugin;
import org.eclipse.emf.exporter.ui.contribution.ModelExporterDescriptor;
import org.eclipse.emf.exporter.ui.contribution.ModelExporterManager;


/**
 * @since 2.2.0
 */
public class EMFExportWizard extends Wizard implements IWorkbenchWizard
{
  public class SelectionPage extends ModelConverterDescriptorSelectionPage
  {
    public SelectionPage(String pageId, IWorkbench workbench, IStructuredSelection selection)
    {
      super(pageId, ModelExporterManager.INSTANCE, workbench, selection);
    }
    
    protected Object[] getTableInput()
    {
      return getModelExporterDescriptors().toArray();
    }
    
    protected String getSelectModelConverterLabel()
    {
      return ExporterPlugin.INSTANCE.getString("_UI_SelectModelExporters_label");
    }
    
    public void setModelExporterDescriptor(ModelExporterDescriptor modelExporterDescriptor)
    {
      setModelConverterDescriptor(modelExporterDescriptor);
    }

    public ModelExporterDescriptor getModelExporterDescriptor()
    {
      return (ModelExporterDescriptor)getModelConverterDescriptor();
    }
    
    protected void adjustModelConverterWizard(IWizard modelConverterWizard)
    {
      adjustModelExporterWizard((IWorkbenchWizard)modelConverterWizard, getModelExporterDescriptor());
    }
    
    protected String getNoModelConverterMessage()
    {
      return ExporterPlugin.INSTANCE.getString("_UI_NoModelExporters_error");
    }    
  }
  
  protected IStructuredSelection selection;
  protected IWorkbench workbench;
  protected IFile modelFile;
  protected ModelConverterDescriptorSelectionPage selectionPage;

  public EMFExportWizard()
  {
    super();
    setForcePreviousAndNextButtons(true);
    setWindowTitle(ExporterPlugin.INSTANCE.getString("_UI_ExportModelWizard_title"));
  }

  public EMFExportWizard(IFile modelFile)
  {
    this();
    this.modelFile = modelFile;
  }

  public void dispose()
  {
    selection = null;
    workbench = null;
    
    if (selectionPage != null)
    {
      selectionPage.clearCache();
      selectionPage = null;
    }

    super.dispose();
  }
  
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return ExtendedImageRegistry.INSTANCE.getImageDescriptor(ExporterPlugin.INSTANCE.getImage("ExportModel"));
  }

  public void addPages()
  {
    selectionPage = new SelectionPage("ModelExporterDescriptorSelectionPage", workbench, selection);
    selectionPage.setTitle(ExporterPlugin.INSTANCE.getString("_UI_SelectModelExporters_title"));
    selectionPage.setModeConverterWizardDefaultImageDescriptor(getDefaultImageDescriptor());
    addPage(selectionPage);
  }

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
    init();
  }
  
  protected void init()
  {
    setDefaultPageImageDescriptor(getDefaultImageDescriptor());
  }
  
  protected List getModelExporterDescriptors()
  {
    return ModelExporterManager.INSTANCE.getModelConverterDescriptors();
  }
  
  protected void adjustModelExporterWizard(IWorkbenchWizard workbenchWizard, ModelExporterDescriptor modelExporterDescriptor)
  {
  }

  public boolean canFinish()
  {
    return false;
  }

  public boolean performFinish()
  {
    selectionPage.performFinish();
    return true;
  }
}
