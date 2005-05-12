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
 * $Id: EMFProjectWizard.java,v 1.2 2005/05/12 14:11:24 marcelop Exp $
 */
package org.eclipse.emf.importer.ui;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ui.wizard.IModelImporterWizard;
import org.eclipse.emf.importer.ui.wizard.ModelImporterDescriptor;
import org.eclipse.emf.importer.ui.wizard.ModelImporterUtil;


/**
 * @since 2.1.0
 */
public class EMFProjectWizard extends EMFModelWizard
{
  protected IPath projectLocation;
  protected IPath projectPath;

  public EMFProjectWizard()
  {
    super();
    setWindowTitle(ImporterPlugin.INSTANCE.getString("_UI_EMFProjectWizard_title"));
  }
  
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return ExtendedImageRegistry.INSTANCE.getImageDescriptor(ImporterPlugin.INSTANCE.getImage("full/wizban/NewEMFProject"));
  }

  public void addPages()
  {
    WizardNewProjectCreationPage newProjectCreationPage = new WizardNewProjectCreationPage("NewProjectCreationPage")
      {
        protected boolean validatePage()
        {
          if (super.validatePage())
          {
            IPath locationPath = getLocationPath();
            projectLocation = Platform.getLocation().equals(locationPath) ? null : locationPath;
            projectPath = getProjectHandle().getFullPath();
            return true;
          }
          else
          {
            return false;
          }
        }
      };
    newProjectCreationPage.setTitle(ImporterPlugin.INSTANCE.getString("_UI_EMFProjectWizard_name"));
    newProjectCreationPage.setDescription(ImporterPlugin.INSTANCE.getString("_UI_EMFProjectWizard_description"));
    addPage(newProjectCreationPage);

    SelectionPage selectionPage = new SelectionPage("ModelImporterDescriptorSelectionPage");
    selectionPage.setTitle(ImporterPlugin.INSTANCE.getString("_UI_SelectModelImporters_title"));
    addPage(selectionPage);
  }

  protected List getModelImporterDescriptors()
  {
    return ModelImporterUtil.filterModelImporterDescriptors(ModelImporterDescriptor.TYPE_PROJECT);
  }
  
  protected void adjustModelImportWizard(ModelImporterDescriptor modelImporterDescriptor)
  {
    super.adjustModelImportWizard(modelImporterDescriptor);
    
    IModelImporterWizard modelImporterWizard = modelImporterDescriptor.getWizard();
    if (isValidNewValue(projectLocation, modelImporterWizard.getGenModelProjectLocation()))
    {
      modelImporterWizard.setGenModelProjectLocation(projectLocation);
    }
    if (isValidNewValue(projectPath, modelImporterWizard.getGenModelProjectPath()))
    {
      modelImporterWizard.setGenModelProjectPath(projectPath);
    }
  }
}
