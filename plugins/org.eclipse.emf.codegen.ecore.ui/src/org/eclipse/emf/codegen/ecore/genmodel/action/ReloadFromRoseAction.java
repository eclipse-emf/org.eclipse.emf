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
 * $Id: ReloadFromRoseAction.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.action;


import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

import org.eclipse.emf.codegen.ecore.genmodel.presentation.EMFProjectWizard;


/**
 * Generate Java beans from the XML Schema
 */
public class ReloadFromRoseAction extends ActionDelegate
       implements IActionDelegate
{
  protected IFile file;

  public ReloadFromRoseAction()
  {
  }

  public void run(IAction action)
  {
    EMFProjectWizard emfProjectWizard = new EMFProjectWizard(file);
    WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), emfProjectWizard);
    wizardDialog.create();
    wizardDialog.getShell().setSize(540, 580);
    int result = wizardDialog.open();
  }

  public void selectionChanged(IAction action, ISelection selection) 
  {
    if (selection instanceof IStructuredSelection)
    {
      Object object = ((IStructuredSelection)selection).getFirstElement();
      if (object instanceof IFile)
      {
        file = (IFile)object;
        action.setEnabled(true);
        return;
      }
    }
    file = null;
    action.setEnabled(false);
  }
}
