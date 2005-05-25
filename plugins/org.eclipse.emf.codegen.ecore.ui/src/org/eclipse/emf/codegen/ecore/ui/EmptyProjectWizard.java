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
 * EmptyProjectWizard.java,v 1.1 2005/05/06 02:19:59 marcelop Exp
 */
package org.eclipse.emf.codegen.ecore.ui;

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * @since 2.1.0
 */
public class EmptyProjectWizard extends Wizard implements INewWizard
{
  protected IWorkbench workbench;
  protected IPath genModelProjectLocation;
  protected IPath genModelContainerPath;
  protected IProject project;

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(GenModelEditPlugin.INSTANCE.getImage("full/wizban/NewEmptyEMFProject")));
    setWindowTitle(GenModelEditPlugin.INSTANCE.getString("_UI_NewEmptyProject_title"));
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
            genModelProjectLocation = Platform.getLocation().equals(locationPath) ? null : locationPath;
            IPath projectPath = getProjectHandle().getFullPath();
            genModelContainerPath = projectPath.append("src");
            return true;
          }
          else
          {
            return false;
          }
        }
      };

    newProjectCreationPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_EmptyProject_title"));
    newProjectCreationPage.setDescription(GenModelEditPlugin.INSTANCE.getString("_UI_EmptyProject_description"));
    addPage(newProjectCreationPage);
  }

  public boolean performFinish()
  {
    WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
        protected void execute(IProgressMonitor progressMonitor)
        {
          try
          {
            project = Generator.createEMFProject(
              new Path(genModelContainerPath.toString()),
              genModelProjectLocation,
              Collections.EMPTY_LIST,
              progressMonitor,
              Generator.EMF_MODEL_PROJECT_STYLE);
          }
          catch (Exception exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
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
      GenModelEditPlugin.INSTANCE.log(exception);
      return false;
    }

    if (project != null)
    {
      IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
      final IWorkbenchPart activePart = page.getActivePart();
      if (activePart instanceof ISetSelectionTarget)
      {
        final ISelection targetSelection = new StructuredSelection(project);
        getShell().getDisplay().asyncExec(new Runnable()
          {
            public void run()
            {
              ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
            }
          });
      }
    }
    
    return true;
  }
}
