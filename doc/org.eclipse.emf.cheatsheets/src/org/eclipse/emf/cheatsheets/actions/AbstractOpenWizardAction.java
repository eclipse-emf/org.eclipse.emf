/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.cheatsheets.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import org.eclipse.emf.cheatsheets.CheatSheetsPlugin;


public abstract class AbstractOpenWizardAction extends Action
{
  @Override
  public void run()
  {
    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    try
    {
      INewWizard wizard = createWizard();
      wizard.init(PlatformUI.getWorkbench(), getSelection());
      WizardDialog dialog = new WizardDialog(shell, wizard);
      dialog.create();
      int res = dialog.open();
      notifyResult(res == Window.OK);
    }
    catch (CoreException e)
    {
      CheatSheetsPlugin.INSTANCE.log(e);
    }
  }

  abstract protected INewWizard createWizard() throws CoreException;

  private IStructuredSelection getSelection()
  {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null)
    {
      ISelection selection = window.getSelectionService().getSelection();
      if (selection instanceof IStructuredSelection)
      {
        return (IStructuredSelection)selection;
      }
    }
    return StructuredSelection.EMPTY;
  }
}
