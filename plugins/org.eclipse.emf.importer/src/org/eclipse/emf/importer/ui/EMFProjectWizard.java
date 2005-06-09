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
 * $Id: EMFProjectWizard.java,v 1.6 2005/06/09 21:39:37 marcelop Exp $
 */
package org.eclipse.emf.importer.ui;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.IModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.ModelImporterDescriptor;
import org.eclipse.emf.importer.ui.contribution.ModelImporterUtil;


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
    newProjectCreationPage.setDescription(ImporterPlugin.INSTANCE.getString("_UI_CreateEMFProject_label"));
    addPage(newProjectCreationPage);

    SelectionPage selectionPage = new SelectionPage("ModelImporterDescriptorSelectionPage");
    selectionPage.setTitle(ImporterPlugin.INSTANCE.getString("_UI_SelectModelImporters_title"));
    addPage(selectionPage);
  }

  protected List getModelImporterDescriptors()
  {
    return ModelImporterUtil.filterModelImporterDescriptors(ModelImporterDescriptor.TYPE_PROJECT);
  }
  
  protected void adjustModelImporterWizard(IModelImporterWizard modelImporterWizard, ModelImporterDescriptor modelImporterDescriptor)
  {
    super.adjustModelImporterWizard(modelImporterWizard, modelImporterDescriptor);

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
