/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: GenModelModelWizard.java,v 1.2 2004/03/18 20:08:54 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * This is a simple wizard for creating a new model file.
 * @generated NOT
 */
public class GenModelModelWizard extends EMFProjectWizard 
{
  /**
   * This is the one and only page.
   */
  protected GenModelModelWizardNewFileCreationPage newFileCreationPage;

  public GenModelModelWizard()
  {
    whichStyle = NEW_FILE;
    setWindowTitle(GenModelEditPlugin.INSTANCE.getString("_UI_New_title"));
  }

  /**
   * Initializes by recording the arguments.
   */
  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
    setNeedsProgressMonitor(true);
    setDefaultPageImageDescriptor
      (ExtendedImageRegistry.INSTANCE.getImageDescriptor(GenModelEditPlugin.INSTANCE.getImage("full/wizban/NewGenModel")));
  }

  /**
   * This is the one page of the wizard.
   */
  public class GenModelModelWizardNewFileCreationPage extends WizardNewFileCreationPage
  {
    /**
     * Remember the model file.
     */
    protected IFile modelFile;

    /**
     * Pass in the selection.
     */
    public GenModelModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection)
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
        // Make sure the file ends in ".genmodel".
        //
        String extension = new Path(getFileName()).getFileExtension();
        if (extension == null || !extension.equals("genmodel"))
        {
          setErrorMessage(GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameMustEndWithGenModel_message"));
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
      modelFile = getGenModelFile();
      return true;

    }
    public IFile getGenModelFile()
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
  public void addPages()
  {
    // Create a page, set the title, and the initial model file name.
    //
    newFileCreationPage = new GenModelModelWizardNewFileCreationPage("Whatever", selection);
    newFileCreationPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_EMFModels_title"));
    newFileCreationPage.setDescription(GenModelEditPlugin.INSTANCE.getString("_UI_EMFModels_description"));
    newFileCreationPage.setFileName("My.genmodel");

    addPage(newFileCreationPage);

    //  Try and get the resource selection to determine a current directory for the file dialog.
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
          String currentDirectory = selectedResource.getLocation().toOSString();
          newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

          // Make up a unique new name here.
          //
          String defaultModelBaseFilename = "My";
          String defaultModelFilenameExtension = "genmodel";
          String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
          for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i)
          {
            modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
          }
          newFileCreationPage.setFileName(modelFilename);
        }
      }
    }

    super.addPages();
  }

  /**
   *  Get the file from the page.
   */
  public IFile getGenModelFile()
  {
    return newFileCreationPage.getGenModelFile();
  }
}
