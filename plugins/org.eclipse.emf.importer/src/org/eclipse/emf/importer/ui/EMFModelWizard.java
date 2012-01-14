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
package org.eclipse.emf.importer.ui;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ui.ModelConverterDescriptorSelectionPage;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.IModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.ModelImporterDescriptor;
import org.eclipse.emf.importer.ui.contribution.ModelImporterManager;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;


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
    @Override
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

    @Override
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
          if (getFileName() == null)
          {
            setFileName(getDefaultGenModelFileName());
          }
        }
      }
    }

    @Override
    public void setFileName(String value)
    {
      super.setFileName(value);
      genModelFileName = value;
    }
  }

  public class SelectionPage extends ModelConverterDescriptorSelectionPage
  {
    public SelectionPage(String pageId, IWorkbench workbench, IStructuredSelection selection)
    {
      super(pageId, ModelImporterManager.INSTANCE, workbench, selection);
    }
    
    @Override
    protected Object[] getTableInput()
    {
      return getModelImporterDescriptors().toArray();
    }
    
    @Override
    protected String getSelectModelConverterLabel()
    {
      return ImporterPlugin.INSTANCE.getString("_UI_SelectModelImporters_label");
    }
    
    public void setModelImporterDescriptor(ModelImporterDescriptor modelImporterDescriptor)
    {
      setModelConverterDescriptor(modelImporterDescriptor);
    }

    public ModelImporterDescriptor getModelImporterDescriptor()
    {
      return (ModelImporterDescriptor)getModelConverterDescriptor();
    }
    
    @Override
    protected String getNoModelConverterMessage()
    {
      return ImporterPlugin.INSTANCE.getString("_UI_NoModelImporters_error");
    }    
    
    @Override
    protected void adjustModelConverterWizard(IWizard modelConverterWizard)
    {
      adjustModelImporterWizard((ModelImporterWizard)modelConverterWizard, getModelImporterDescriptor());
    }
  }

  protected IStructuredSelection selection;
  protected IWorkbench workbench;
  protected IPath genModelContainerPath;
  protected String genModelFileName;
  protected IFile reloadFile;

  protected IFile modelFile;
  protected ModelConverterDescriptorSelectionPage selectionPage;
  
  protected IPath defaultPath;
  protected String defaultDescriptorID;  

  public EMFModelWizard()
  {
    super();
    setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_EMFWizardModel_title"));
  }

  public EMFModelWizard(IFile reloadFile)
  {
    super();
    setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_ReloadWizard_title"));
    this.reloadFile = reloadFile;
  }

  @Override
  public void dispose()
  {
    selection = null;
    workbench = null;
    genModelContainerPath = null;
    reloadFile = null;
    selectionPage = null;
    
    super.dispose();
  }
  
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return ExtendedImageRegistry.INSTANCE.getImageDescriptor(ImporterPlugin.INSTANCE.getImage("full/wizban/NewGenModel"));
  }

  @Override
  public void addPages()
  {
    if (reloadFile == null)
    {
      NewGenModelFileCreationPage page = new NewGenModelFileCreationPage("NewModelFileCreationPageID");
      page.setTitle(ImporterPlugin.INSTANCE.getString("_UI_EMFModelWizard_name"));
      page.setDescription(ImporterPlugin.INSTANCE.getString("_UI_CreateGeneratorModel_label"));
      addPage(page);
      
      if (defaultPath != null)
      {
        page.setContainerFullPath(defaultPath.removeLastSegments(1));
        page.setFileName(defaultPath.lastSegment());
        page.setPageComplete(page.isPageComplete());    
      }      
    }
    else
    {
      setForcePreviousAndNextButtons(true);
    }
    
    addSelectionPage();
  }
  
  protected void addSelectionPage()
  {
    selectionPage = new SelectionPage("ModelImporterDescriptorSelectionPage", workbench, selection);
    selectionPage.setTitle(ImporterPlugin.INSTANCE.getString("_UI_SelectModelImporters_title"));
    selectionPage.setModeConverterWizardDefaultImageDescriptor(getDefaultImageDescriptor());
    selectionPage.setModelConverterDescriptor(computeSuggestedDescriptor());     
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
  
  protected String getDefaultGenModelFileName()
  {
    return defaultPath == null ?
      "My.genmodel" : 
      defaultPath.removeFirstSegments(defaultPath.segmentCount()-1).toString();
  }

  protected List<ModelImporterDescriptor> getModelImporterDescriptors()
  {
    return ModelImporterManager.INSTANCE.filterModelImporterDescriptors(ModelImporterDescriptor.TYPE_FILE);
  }
  
  protected ModelImporterDescriptor computeSuggestedDescriptor()
  {
    ModelImporterDescriptor descriptor = null;
    
    if (defaultDescriptorID != null)
    {
      descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor(defaultDescriptorID);
      if (descriptor != null)
      {
        return descriptor;
      }
    }
    
    if (reloadFile != null)
    {
      URI reloadURI = URI.createPlatformResourceURI(reloadFile.getFullPath().toString(), true);
      ResourceSet resourceSet = ConverterUtil.createResourceSet();
      Resource reloadResource = null;
      try
      {
        reloadResource = resourceSet.getResource(reloadURI, true);
      }
      catch (RuntimeException e)
      {
        reloadResource = resourceSet.getResource(reloadURI, false);
      }

      if (reloadResource != null && !reloadResource.getContents().isEmpty())
      {
        Object content = reloadResource.getContents().get(0);
        if (content instanceof GenModel)
        {
          GenModel genModel = (GenModel)content;
          if (genModel.getImporterID() != null)
          {
            descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor(genModel.getImporterID());
          }
          else if (!genModel.getForeignModel().isEmpty())
          {
            String foreignModel = genModel.getForeignModel().get(0);
            if (foreignModel.endsWith(".mdl"))
            {
              descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor("org.eclipse.emf.importer.rose");
            }
            else if (foreignModel.endsWith(".xsd") || foreignModel.endsWith(".wsdl"))
            {
              descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor("org.eclipse.xsd.ecore.importer");
            }
            else if (foreignModel.endsWith("@model"))
            {
              descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor("org.eclipse.emf.importer.java");
            }
            else if (foreignModel.endsWith(".ecore") || foreignModel.endsWith(".emof"))
            {
              descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor("org.eclipse.emf.importer.ecore");
            }              
          }
        }
      }
    }

    if (descriptor == null)
    { 
      if (selection != null && !selection.isEmpty())
      {
        Object element = selection.getFirstElement();
        if (element instanceof IFile)
        {
          String fileExtension = ((IFile)element).getFileExtension();
          descriptor = ModelImporterManager.INSTANCE.getModelImporterDescriptor(selectionPage.getLastModelConverterDescriptorId());
          if (descriptor == null || !descriptor.getExtensions().contains(fileExtension))
          {
            List<ModelImporterDescriptor> descriptors = ModelImporterManager.INSTANCE.filterModelImporterDescriptors(fileExtension);
            if (!descriptors.isEmpty())
            {
              descriptor = descriptors.get(0);
            }
          }
          if (descriptor != null)
          {
            modelFile = ((IFile)element);
          }
        }
      }
    }

    return descriptor;
  }
  
  protected boolean isValidNewValue(Object newValue, Object oldValue)
  {
    return newValue == null ? oldValue != null : !newValue.equals(oldValue);
  }  

  protected void adjustModelImporterWizard(IModelImporterWizard modelImporterWizard, ModelImporterDescriptor modelImporterDescriptor)
  {
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

  @Override
  public boolean canFinish()
  {
    return false;
  }

  @Override
  public boolean performFinish()
  {
    selectionPage.performFinish();
    return true;
  }
  
  /**
   * Sets the model importer descriptor that will be selected when this wizard
   * is presented to the user.  This method has to be invoke before the wizard
   * pages are added.
   * @param id
   */
  public void setDefaultModelImporterDescriptorID(String id)
  {
    defaultDescriptorID = id;
  }
  
  public String getDefaultModelImporterDescriptorID()
  {
    return defaultDescriptorID;
  }
  
  /**
   * Sets the path of the genmodel that will be used when this wizard
   * is presented to the user.  This method has to be invoke before the wizard
   * pages are added.
   * @param path The <b>absolute</b> path of the genmodel.
   */
  public void setDefaultPath(IPath path)
  {
    defaultPath = path.makeAbsolute();
  }
  
  public IPath getDefaultPath()
  {
    return defaultPath;
  }
}
