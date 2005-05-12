/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * ModelImporterWizard.java,v 1.1 2005/05/12 17:10:24 marcelop Exp
 */
package org.eclipse.emf.importer.ui.contribution.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.IModelImporterWizard;


/**
 * @since 2.1.0
 */
public abstract class ModelImporterWizard extends Wizard implements IModelImporterWizard
{
  public static class PageHelper implements IPageChangedListener, IDisposable
  {
    protected Object oldPage;
    protected IPageChangeProvider pageChangeProvider;

    public void dispose()
    {
      oldPage = null;
      pageChangeProvider = null;
    }

    public void pageChanged(PageChangedEvent event)
    {
      pageChangeProvider = event.getPageChangeProvider();
      pageChanged(event.getSelectedPage());
    }

    protected void pageChanged(Object currentPage)
    {
      if (oldPage != currentPage)
      {
        int cause = -1;
        if (oldPage instanceof ModelImporterPage)
        {
          ModelImporterPage page = (ModelImporterPage)oldPage;
          cause = page.forwardDirection ? ModelImporterPage.CAUSE_NEXT : ModelImporterPage.CAUSE_BACK;
          page.pageDeactivated(cause);
        }

        oldPage = currentPage;
        if (currentPage instanceof ModelImporterPage)
        {
          ModelImporterPage page = (ModelImporterPage)currentPage;
          page.pageActivated(page.neverVisible, cause);
          page.neverVisible = false;
        }
      }
    }

    public boolean firePageAboutToDeactivate(int cause)
    {
      if (pageChangeProvider != null && pageChangeProvider.getSelectedPage() instanceof ModelImporterPage)
      {
        return ((ModelImporterPage)pageChangeProvider.getSelectedPage()).pageAboutToDeactivate(cause);
      }
      else
      {
        return true;
      }
    }
  }

  protected IStructuredSelection selection;
  protected IWorkbench workbench;
  protected PageHelper pageHelper;

  protected ModelImporter modelImporter;
  
  protected IFile originalGenModelFile;
  protected IFile modelFile;
  protected String fileName;
  protected IPath genModelContainerPath;
  protected IPath projectLocation;
  protected IPath projectPath;

  public ModelImporterWizard()
  {
    super();
    setNeedsProgressMonitor(true);
  }

  public void dispose()
  {
    selection = null;
    workbench = null;

    if (modelImporter != null)
    {
      modelImporter.dispose();
      modelImporter = null;
    }

    if (pageHelper != null)
    {
      if (getContainer() instanceof WizardDialog)
      {
        ((WizardDialog)getContainer()).removePageChangedListener(pageHelper);
      }
      pageHelper.dispose();
      pageHelper = null;
    }

    super.dispose();
  }

  protected abstract ModelImporter createModelImporter();

  public ModelImporter getModelImporter()
  {
    if (modelImporter == null)
    {
      modelImporter = createModelImporter();
    }
    return modelImporter;
  }

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
  }

  public IWorkbench getWorkbench()
  {
    return workbench;
  }

  public IStructuredSelection getSelection()
  {
    return selection;
  }

  public void setContainer(IWizardContainer wizardContainer)
  {
    super.setContainer(wizardContainer);
    if (wizardContainer instanceof WizardDialog)
    {
      pageHelper = new PageHelper();
      ((WizardDialog)wizardContainer).addPageChangedListener(pageHelper);
    }
  }

  public void setOriginalGenModelFile(IFile originalGenModelFile)
  {
    this.originalGenModelFile = originalGenModelFile; 
    if (originalGenModelFile != null)
    {
      setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_ReloadWizard_title"));
      getModelImporter().defineOriginalGenModelPath(originalGenModelFile.getFullPath());
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
  }

  public IPath getGenModelProjectPath()
  {
    return projectPath;
  }
  
  public boolean performCancel()
  {
    return pageHelper == null || pageHelper.firePageAboutToDeactivate(ModelImporterPage.CAUSE_CANCEL);
  }

  public boolean performFinish()
  {
    if (pageHelper != null && !pageHelper.firePageAboutToDeactivate(ModelImporterPage.CAUSE_FINISH))
    {
      return false;
    }

    try
    {
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
        {
          protected void execute(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              getModelImporter().prepareGenModelAndEPackages(progressMonitor);
              getModelImporter().saveGenModelAndEPackages(progressMonitor);
            }
            catch (Exception exception)
            {
              ImporterPlugin.INSTANCE.log(exception);
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      try
      {
        getContainer().run(false, false, operation);
      }
      catch (Exception exception)
      {
        ImporterPlugin.INSTANCE.log(exception);
        return false;
      }

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
    catch (Exception exception)
    {
      ImporterPlugin.INSTANCE.log(exception);
      return false;
    }
  }

  protected IFile getFile(IPath path)
  {
    return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
  }

  protected void selectFile(IFile file)
  {
    IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
    final IWorkbenchPart activePart = page.getActivePart();
    if (activePart instanceof ISetSelectionTarget)
    {
      final ISelection targetSelection = new StructuredSelection(file);
      getShell().getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
          }
        });
    }
  }

  protected void openEditor(IFile file) throws PartInitException
  {
    IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
    page.openEditor(new FileEditorInput(file), getWorkbench().getEditorRegistry().getDefaultEditor(file.getFullPath().toString()).getId());
  }
}
