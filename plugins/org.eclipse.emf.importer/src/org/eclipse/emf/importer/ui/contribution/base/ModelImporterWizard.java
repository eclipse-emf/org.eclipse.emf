/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * ModelImporterWizard.java,v 1.15 2009/04/18 11:40:04 emerks Exp
 */
package org.eclipse.emf.importer.ui.contribution.base;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PartInitException;

import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.converter.ui.contribution.base.ModelConverterWizard;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.IModelImporterWizard;


/**
 * @since 2.1.0
 */
public abstract class ModelImporterWizard extends ModelConverterWizard implements IModelImporterWizard
{
  protected IFile originalGenModelFile;
  protected IFile modelFile;
  protected String fileName;
  protected IPath genModelContainerPath;
  protected IPath projectLocation;
  protected IPath projectPath;
  protected IWorkingSet[] workingSets;

  public IWorkingSet[] getWorkingSets()
  {
    return workingSets;
  }

  public void setWorkingSets(IWorkingSet[] workingSets)
  {
    this.workingSets = workingSets;
  }

  public ModelImporter getModelImporter()
  {
    return (ModelImporter)getModelConverter();
  }

  public List<String> getFileExtensions()
  {
    return getModelImporter().getFileExtensions();
  }

  public void setOriginalGenModelFile(IFile originalGenModelFile)
  {
    this.originalGenModelFile = originalGenModelFile; 
    if (originalGenModelFile != null)
    {
      setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_ReloadWizard_title"));
      try
      {
        getModelImporter().defineOriginalGenModelPath(originalGenModelFile.getFullPath());
      }
      catch (DiagnosticException exception)
      {
        String message = getModelImporter().getOriginalGenModel() != null ?
          ImporterPlugin.INSTANCE.getString("_UI_ProblematicModel_message") :
          ImporterPlugin.INSTANCE.getString("_UI_InvalidModel_message");
          
        Diagnostic diagnostic = exception.getDiagnostic();
        DiagnosticDialog.open
          (getShell(),
           GenModelEditPlugin.INSTANCE.getString("_UI_ModelProblems_title"),
           message,
           diagnostic);
      }
    }
  }
  
  public IFile getOriginalGenModelFile()
  {
    return originalGenModelFile;
  }

  public void setModelFile(IFile modelFile)
  {
    this.modelFile = modelFile;
    getModelImporter().setModelFile(modelFile);
  }
  
  public IFile getModelFile()
  {
    return modelFile;
  }

  public void setGenModelFileName(String fileName)
  {
    this.fileName = fileName;
    getModelImporter().setGenModelFileName(fileName);
  }
  
  public String getGenModelFileName()
  {
    return fileName;
  }  

  public void setGenModelContainerPath(IPath genModelContainerPath)
  {
    this.genModelContainerPath = genModelContainerPath;
    getModelImporter().setGenModelContainerPath(genModelContainerPath);
  }

  public IPath getGenModelContainerPath()
  {
    return genModelContainerPath;
  }

  public void setGenModelProjectLocation(IPath projectLocation)
  {
    this.projectLocation = projectLocation;
    getModelImporter().setGenModelProjectLocation(projectLocation);
  }

  public IPath getGenModelProjectLocation()
  {
    return projectLocation;
  }
  
  public void setGenModelProjectPath(IPath projectPath)
  {
    this.projectPath = projectPath;
    getModelImporter().setGenModelContainerPath(getModelImporter().computeGenModelContainerPath(projectPath));
    getModelImporter().setModelProjectName(projectPath.segment(0));
  }

  public IPath getGenModelProjectPath()
  {
    return projectPath;
  }
  
  @Override
  protected Diagnostic doPerformFinish(Monitor monitor) throws Exception
  {
    getModelImporter().prepareGenModelAndEPackages(monitor);
    getModelImporter().saveGenModelAndEPackages(monitor);

    IWorkingSet[] workingSets = getWorkingSets();
    if (workingSets != null)
    {
      workbench.getWorkingSetManager().addToWorkingSets(ResourcesPlugin.getWorkspace().getRoot().getProject(getModelImporter().getModelProjectName()), workingSets);
    }
    return Diagnostic.OK_INSTANCE;
  }
  
  @Override
  public boolean performFinish()
  {
    if (super.performFinish())
    {
      IFile genModelFile = getFile(getModelImporter().getGenModelPath());
      if (getModelImporter().getOriginalGenModelPath() == null)
      {
        selectFile(genModelFile);
      }

      try
      {
        openEditor(genModelFile);
      }
      catch (PartInitException partInitException)
      {
        MessageDialog.openError(getShell(), ImporterPlugin.INSTANCE.getString("_UI_OpenEditor_title"), partInitException.getMessage());
        return false;
      }
      
      return true;
    }
    else
    {
      return false;
    }
  }
}
