/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.presentation;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * This is a simple wizard for creating a new dynamic model file.
 */
public class DynamicModelWizard extends Wizard implements INewWizard
{
  /**
   * This caches the class instance.
   */
  protected EClass eClass;

  /**
   * This is the file creation page.
   */
  protected DynamicModelWizardNewFileCreationPage newFileCreationPage;

  /**
   * Remember the selection during initialization for populating the default container.
   */
  protected IStructuredSelection selection;

  /**
   * Remember the workbench during initialization.
   */
  protected IWorkbench workbench;

  /**
   * Creates an instance.
   */
  public DynamicModelWizard(EClass eClass)
  {
    this.eClass = eClass;
  }

  /**
   * This just records the information.
   */
  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
    setDefaultPageImageDescriptor
      (ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditPlugin.INSTANCE.getImage("full/wizban/NewModel")));
  }

  /**
   * Create a new model.
   */
  EObject createInitialModel()
  {
    return EcoreUtil.create(eClass);
  }

  /**
   * Do the work after everything is specified.
   */
  @Override
  public boolean performFinish()
  {
    try
    {
      // Remember the file.
      //
      final IFile modelFile = getModelFile();

      // Do the work within an operation.
      //
      WorkspaceModifyOperation operation =
        new WorkspaceModifyOperation()
        {
          @Override
          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              // Create a resource set
              //
              ResourceSet resourceSet = new ResourceSetImpl();
              resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));

              // Get the URI of the model file.
              //
              URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

              // Create a resource for this file.
              //
              Resource resource = resourceSet.createResource(fileURI);

              // Add the initial model object to the contents.
              //
              EObject rootObject = createInitialModel();
              if (rootObject != null)
              {
                resource.getContents().add(rootObject);
              }

              // Save the contents of the resource to the file system.
              //
              Map<Object, Object> options = new HashMap<Object, Object>();
              options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
              options.put(XMLResource.OPTION_LINE_WIDTH, 80);
              options.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);
              resource.save(options);
            }
            catch (Exception exception)
            {
              exception.printStackTrace();
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      getContainer().run(false, false, operation);

      // Select the new file resource in the current view.
      //
      IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
      IWorkbenchPage page = workbenchWindow.getActivePage();
      final IWorkbenchPart activePart = page.getActivePart();
      if (activePart instanceof ISetSelectionTarget)
      {
        final ISelection targetSelection = new StructuredSelection(modelFile);
        getShell().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
             }
           });
      }

      // Open an editor on the new file.
      //
      try
      {
        IEditorDescriptor editorDescriptor = workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString());
        if (editorDescriptor != null)
        {
          page.openEditor(new FileEditorInput(modelFile), editorDescriptor.getId());
        }
      }
      catch (PartInitException exception)
      {
        MessageDialog.openError
          (workbenchWindow.getShell(), EcoreEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
        return false;
      }

      return true;
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
      return false;
    }
  }

  /**
   * This is the one page of the wizard.
   */
  public class DynamicModelWizardNewFileCreationPage extends WizardNewFileCreationPage
  {
    /**
     * Remember the model file.
     */
    protected IFile modelFile;

    /**
     * Pass in the selection.
     */
    public DynamicModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection)
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
        // Make sure the file ends in ".ecore".
        //
        String requiredExt = EcoreEditorPlugin.INSTANCE.getString("_UI_DynamicInstanceFilenameExtension");
        String enteredExt = new Path(getFileName()).getFileExtension();
        if (enteredExt == null || !enteredExt.equals(requiredExt))
        {
          setErrorMessage(EcoreEditorPlugin.INSTANCE.getString("_WARN_FilenameExtension", new Object [] { requiredExt }));
          return false;
        }
        else
        {
          return true;
        }
      }
      else
      {
        return false;
      }
    }

    /**
     * Store the dialog field settings upon completion.
     */
    public boolean performFinish()
    {
      modelFile = getModelFile();
      return true;
    }

    /**
     */
    public IFile getModelFile()
    {
      return
        modelFile == null ?
          ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName())) :
          modelFile;
    }
  }

  /**
   * The framework calls this to create the contents of the wizard.
   */
  @Override
  public void addPages()
  {
    // Create a page, set the title, and the initial model file name.
    //
    newFileCreationPage = new DynamicModelWizardNewFileCreationPage("Whatever", selection);
    newFileCreationPage.setTitle(EcoreEditorPlugin.INSTANCE.getString("_UI_DynamicModelWizard_label"));
    newFileCreationPage.setDescription
      (EcoreEditorPlugin.INSTANCE.getString("_UI_DynamicModelWizard_description", new Object [] { eClass.getName() }));
    newFileCreationPage.setFileName
      (eClass.getName() + "." + EcoreEditorPlugin.INSTANCE.getString("_UI_DynamicInstanceFilenameExtension"));
    addPage(newFileCreationPage);

    // Try and get the resource selection to determine a current directory for the file dialog.
    //
    if (selection != null && !selection.isEmpty())
    {
      // Get the resource...
      //
      Object selectedElement = selection.iterator().next();
      if (selectedElement instanceof IResource)
      {
        // Get the resource parent, if its a file.
        //
        IResource selectedResource = (IResource)selectedElement;
        if (selectedResource.getType() == IResource.FILE)
        {
          selectedResource = selectedResource.getParent();
        }

        // This gives us a directory...
        //
        if (selectedResource instanceof IFolder || selectedResource instanceof IProject)
        {
          // Set this for the container.
          //
          newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

          // Make up a unique new name here.
          //
          String defaultModelBaseFilename = eClass.getName();
          String defaultModelFilenameExtension = EcoreEditorPlugin.INSTANCE.getString("_UI_DynamicInstanceFilenameExtension");
          String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
          for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i)
          {
            modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
          }
          newFileCreationPage.setFileName(modelFilename);
        }
      }
    }
  }

  /**
   * Get the file from the page.
   */
  public IFile getModelFile()
  {
    return newFileCreationPage.getModelFile();
  }
}
