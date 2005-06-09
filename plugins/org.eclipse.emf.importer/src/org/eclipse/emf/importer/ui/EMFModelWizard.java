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
 * $Id: EMFModelWizard.java,v 1.8 2005/06/09 14:50:58 davidms Exp $
 */
package org.eclipse.emf.importer.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
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
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.IModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.ModelImporterDescriptor;
import org.eclipse.emf.importer.ui.contribution.ModelImporterUtil;
import org.eclipse.emf.importer.util.ImporterUtil;


/**
 * @since 2.1.0
 */
public class EMFModelWizard extends Wizard implements INewWizard
{
  public class NewGenModelFileCreationPage extends WizardNewFileCreationPage
  {
    protected boolean firstTime = true;

    /**
     * Pass in the selection.
     */
    public NewGenModelFileCreationPage(String pageId)
    {
      super(pageId, selection);
    }

    /**
     * The framework calls this to see if the file is correct.
     */
    protected boolean validatePage()
    {
      if (super.validatePage())
      {
        String extension = new Path(getFileName()).getFileExtension();
        if (extension == null || !extension.equals("genmodel"))
        {
          setErrorMessage(ImporterPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameMustEndWithGenModel_message"));
          return false;
        }
        else
        {
          genModelContainerPath = getContainerFullPath();
          genModelFileName = getFileName();
          return true;
        }
      }
      else
      {
        return false;
      }
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible && firstTime)
      {
        firstTime = false;
        if (modelFile != null)
        {
          String fileName = modelFile.getFullPath().removeFileExtension().lastSegment();
          setFileName(fileName + ".genmodel");
        }
        else
        {
          setFileName(getDefaultGenModelFileName());
        }
      }
    }

    public void setFileName(String value)
    {
      super.setFileName(value);
      genModelFileName = value;
    }
  }

  public class SelectionPage extends WizardSelectionPage implements ISelectionChangedListener
  {
    protected ModelImporterDescriptor modelImporterDescriptor;
    protected TableViewer modelImpoterDescriptorTableViewer;

    protected boolean firstTime = true;
    protected Set initializedWizards = new HashSet();

    public SelectionPage(String pageId)
    {
      super(pageId);
      ModelImporterUtil.clearWizardNodesCache();
    }

    public void dispose()
    {
      if (modelImpoterDescriptorTableViewer != null)
      {
        modelImpoterDescriptorTableViewer.removeSelectionChangedListener(this);
        modelImpoterDescriptorTableViewer = null;
      }
      modelImporterDescriptor = null;

      if (initializedWizards != null)
      {
        initializedWizards.clear();
        initializedWizards = null;
      }
      
      ModelImporterUtil.clearWizardNodesCache();

      super.dispose();
    }

    public void setModelImporterDescriptor(ModelImporterDescriptor modelImporterDescriptor)
    {
      this.modelImporterDescriptor = modelImporterDescriptor;
      if (modelImpoterDescriptorTableViewer != null)
      {
        if (modelImporterDescriptor != null)
        {
          modelImpoterDescriptorTableViewer.setSelection(new StructuredSelection(modelImporterDescriptor), true);
        }
        else
        {
          modelImpoterDescriptorTableViewer.setSelection(StructuredSelection.EMPTY);
        }
      }
    }

    public ModelImporterDescriptor getModelImporterDescriptor()
    {
      return modelImporterDescriptor;
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible && firstTime)
      {
        firstTime = false;
        Table table = modelImpoterDescriptorTableViewer.getTable();

        ModelImporterDescriptor descriptor = suggestedDescriptor;
        if (descriptor != null)
        {
          setModelImporterDescriptor(descriptor);
        }
        else if (table.getItemCount() > 0)
        {
          setModelImporterDescriptor((ModelImporterDescriptor)table.getItem(0).getData());
        }
        table.setFocus();
      }
    }

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
      label.setText(ImporterPlugin.INSTANCE.getString("_UI_SelectModelImporters_label"));
      {
        GridData data = new GridData();
        data.verticalAlignment = SWT.FILL;
        data.horizontalAlignment = SWT.FILL;
        label.setLayoutData(data);
      }

      Table modelImpoterDescriptorTable = new Table(composite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
      {
        GridData data = new GridData();
        data.widthHint = Display.getCurrent().getBounds().width / 5;
        data.heightHint = Display.getCurrent().getBounds().height / 3;
        data.verticalAlignment = SWT.FILL;
        data.horizontalAlignment = SWT.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        modelImpoterDescriptorTable.setLayoutData(data);
      }

      modelImpoterDescriptorTableViewer = new TableViewer(modelImpoterDescriptorTable);
      modelImpoterDescriptorTableViewer.setContentProvider(new ArrayContentProvider());
      modelImpoterDescriptorTableViewer.setLabelProvider(new ModelImporterUtil.ModelImporterDescriptorLabelProvider());
      modelImpoterDescriptorTableViewer.setSorter(new ViewerSorter());

      modelImpoterDescriptorTableViewer.addDoubleClickListener(new IDoubleClickListener()
        {
          public void doubleClick(DoubleClickEvent event)
          {
            if (canFlipToNextPage())
            {
              getContainer().showPage(getNextPage());
            }
          }
        });

      modelImpoterDescriptorTableViewer.setInput(getModelImporterDescriptors().toArray());
      if (modelImporterDescriptor != null)
      {
        modelImpoterDescriptorTableViewer.setSelection(new StructuredSelection(modelImporterDescriptor), true);
      }
      modelImpoterDescriptorTableViewer.addSelectionChangedListener(this);
      setControl(composite);
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
      ISelection selection = event.getSelection();
      if (!selection.isEmpty() && selection instanceof IStructuredSelection)
      {
        Object selectedObject = ((IStructuredSelection)selection).getFirstElement();
        if (selectedObject instanceof ModelImporterDescriptor)
        {
          modelImporterDescriptor = (ModelImporterDescriptor)selectedObject;
          setMessage(modelImporterDescriptor.getDescription(), IMessageProvider.NONE);
          setSelectedNode(ModelImporterUtil.getWizardNode(modelImporterDescriptor));
          return;
        }
      }

      setPageComplete(false);
    }

    public boolean isPageComplete()
    {
      return modelImporterDescriptor != null;
    }

    public IWizardPage getNextPage()
    {
      IModelImporterWizard modelImporterWizard = getModelImporterDescriptor().getWizard();
      if (!initializedWizards.contains(modelImporterWizard))
      {
        initializedWizards.add(modelImporterWizard);
        if (modelImporterWizard instanceof Wizard)
        {
          Wizard wizard = (Wizard)modelImporterWizard;
          wizard.setDefaultPageImageDescriptor(getDefaultImageDescriptor());
          if (wizard.getWindowTitle() == null)
          {
            wizard.setWindowTitle(getWindowTitle());
          }
        }        
        modelImporterWizard.init(workbench, selection);
      }

      adjustModelImportWizard(getModelImporterDescriptor());
      IWizardPage wizardPage = super.getNextPage();
      
      IWizardNode wizardNode = getSelectedNode();
      if (wizardNode instanceof ModelImporterUtil.ModelImporterDescriptorWizardNode)
      {
        ((ModelImporterUtil.ModelImporterDescriptorWizardNode)wizardNode).setContentCreated(true);
      }
      return wizardPage;
    }    
  }

  protected IStructuredSelection selection;
  protected IWorkbench workbench;
  protected IPath genModelContainerPath;
  protected String genModelFileName;
  protected IFile reloadFile;

  protected IFile modelFile;
  protected ModelImporterDescriptor suggestedDescriptor;

  protected boolean canFinish = false;

  public EMFModelWizard()
  {
    super();
    setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_EMFWizardModel_title"));
  }

  public EMFModelWizard(IFile reloadFile)
  {
    this();
    setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_ReloadWizard_title"));
    this.reloadFile = reloadFile;
  }

  public void dispose()
  {
    selection = null;
    workbench = null;
    genModelContainerPath = null;
    reloadFile = null;

    super.dispose();
  }
  
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return ExtendedImageRegistry.INSTANCE.getImageDescriptor(ImporterPlugin.INSTANCE.getImage("full/wizban/NewGenModel"));
  }

  public void addPages()
  {
    if (reloadFile == null)
    {
      NewGenModelFileCreationPage page = new NewGenModelFileCreationPage("NewModelFileCreationPageID");
      page.setTitle(ImporterPlugin.INSTANCE.getString("_UI_EMFModelWizard_name"));
      page.setDescription(ImporterPlugin.INSTANCE.getString("_UI_CreateGeneratorModel_label"));
      addPage(page);
    }
    else
    {
      setForcePreviousAndNextButtons(true);
    }

    SelectionPage selectionPage = new SelectionPage("ModelImporterDescriptorSelectionPage");
    selectionPage.setTitle(ImporterPlugin.INSTANCE.getString("_UI_SelectModelImporters_title"));
    addPage(selectionPage);
  }

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
    computeSuggestedDescriptor();
    init();
  }
  
  protected void init()
  {
    setDefaultPageImageDescriptor(getDefaultImageDescriptor());
  }
  
  protected String getDefaultGenModelFileName()
  {
    return "My.genmodel";
  }

  protected List getModelImporterDescriptors()
  {
    return ModelImporterUtil.filterModelImporterDescriptors(ModelImporterDescriptor.TYPE_FILE);
  }
  
  protected void computeSuggestedDescriptor()
  {
    if (suggestedDescriptor == null)
    {
      if (reloadFile != null)
      {
        URI reloadURI = URI.createPlatformResourceURI(reloadFile.getFullPath().toString());
        Resource reloadResource = null;
        try
        {
          reloadResource = ImporterUtil.createResourceSet().getResource(reloadURI, true);
        }
        catch (Exception e)
        {
        }

        if (reloadResource != null && !reloadResource.getContents().isEmpty())
        {
          Object content = reloadResource.getContents().get(0);
          if (content instanceof GenModel)
          {
            GenModel genModel = (GenModel)content;
            if (genModel.getImporterID() != null)
            {
              suggestedDescriptor = ModelImporterUtil.getModelImporterDescriptor(genModel.getImporterID());
            }
            else if (!genModel.getForeignModel().isEmpty())
            {
              String foreignModel = (String)genModel.getForeignModel().get(0);
              if (foreignModel.endsWith(".mdl"))
              {
                suggestedDescriptor = ModelImporterUtil.getModelImporterDescriptor("org.eclipse.emf.importer.rose");
              }
              else if (foreignModel.endsWith(".xsd") || foreignModel.endsWith(".wsdl"))
              {
                suggestedDescriptor = ModelImporterUtil.getModelImporterDescriptor("org.eclipse.xsd.ecore.importer");
              }
              else if (foreignModel.endsWith("@model"))
              {
                suggestedDescriptor = ModelImporterUtil.getModelImporterDescriptor("org.eclipse.emf.importer.java");
              }
              else if (foreignModel.endsWith(".ecore") || foreignModel.endsWith(".emof"))
              {
                suggestedDescriptor = ModelImporterUtil.getModelImporterDescriptor("org.eclipse.emf.importer.ecore");
              }              
            }
          }
        }
      }

      if (suggestedDescriptor == null)
      {
        if (selection != null && !selection.isEmpty())
        {
          Object element = selection.getFirstElement();
          if (element instanceof IFile)
          {
            List descriptors = ModelImporterUtil.filterModelImporterDescriptors(((IFile)element).getFileExtension());
            if (!descriptors.isEmpty())
            {
              modelFile = ((IFile)element);
              suggestedDescriptor = (ModelImporterDescriptor)descriptors.get(0);
            }
          }
        }
      }

    }
  }
  
  protected boolean isValidNewValue(Object newValue, Object oldValue)
  {
    return newValue == null ? oldValue != null : !newValue.equals(oldValue);
  }  

  protected void adjustModelImportWizard(ModelImporterDescriptor modelImporterDescriptor)
  {
    IModelImporterWizard modelImporterWizard = modelImporterDescriptor.getWizard();
    if (isValidNewValue(reloadFile, modelImporterWizard.getOriginalGenModelFile()))
    {
      modelImporterWizard.setOriginalGenModelFile(reloadFile);
    }
    if (isValidNewValue(genModelContainerPath, modelImporterWizard.getGenModelContainerPath()))
    {
      modelImporterWizard.setGenModelContainerPath(genModelContainerPath);
    }
    if (isValidNewValue(genModelFileName, modelImporterWizard.getGenModelFileName()))
    {
      modelImporterWizard.setGenModelFileName(genModelFileName);
    }
    if (isValidNewValue(modelFile, modelImporterWizard.getModelFile()))
    {
      if (modelFile == null || modelImporterDescriptor.getExtensions().contains(modelFile.getFileExtension()))
      {
        modelImporterWizard.setModelFile(modelFile);
      }
    }
    modelImporterWizard.getFileExtensions().clear();
    modelImporterWizard.getFileExtensions().addAll(modelImporterDescriptor.getExtensions());
  }

  public boolean canFinish()
  {
    return false;
  }

  public boolean performFinish()
  {
    return true;
  }
}
