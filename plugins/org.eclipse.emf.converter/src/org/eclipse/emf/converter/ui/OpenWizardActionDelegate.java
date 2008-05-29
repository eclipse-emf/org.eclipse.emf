/**
 * <copyright>
 *
 * Copyright (c) 2005-2008 IBM Corporation and others.
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
 * $Id: OpenWizardActionDelegate.java,v 1.3 2008/05/29 02:35:08 marcelop Exp $
 */
package org.eclipse.emf.converter.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;


/**
 * This action just invokes an {@link org.eclipse.jface.wizard.IWizard} with an existing file.
 * If this action is being contributed to an editor, we'll use its input as the file. Otherwise, we'll
 * try to obtain it from the selection.
 * @since 2.2.0
 */
public abstract class OpenWizardActionDelegate extends ActionDelegate implements IEditorActionDelegate
{
  protected boolean fixedFile = false;
  protected IFile file;

  public OpenWizardActionDelegate()
  {
    super();
  }

  public OpenWizardActionDelegate(IEditorPart editorPart)
  {
    this();
  }

  @Override
  public void dispose()
  {
    file = null;
    super.dispose();
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection)
  {
    if (fixedFile) return;

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

  /**
   * This is only called when being used as an editor action delegate.
   */
  public void setActiveEditor(IAction action, IEditorPart targetEditor)
  {
    if (targetEditor != null)
    {
      IEditorInput input = targetEditor.getEditorInput();
      if (input instanceof IFileEditorInput)
      {
        file = ((IFileEditorInput)input).getFile();
        fixedFile = true;
        action.setEnabled(true);
        return;
      }
    }
    file = null;
    action.setEnabled(false);
  }

  @Override
  public void run(IAction action)
  {
    run(PlatformUI.getWorkbench(), PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), file);
  }

  protected void run(IWorkbench workbench, Shell shell, IFile file)
  {
    IWizard wizard = createWizard(workbench, shell, file);

    WizardDialog wizardDialog = new WizardDialog(shell, wizard);
    wizardDialog.create();
    wizardDialog.getShell().setSize(Math.max(540, wizardDialog.getShell().getSize().x), 580);
    wizardDialog.open();
  }
  
  abstract protected IWizard createWizard(IWorkbench workbench, Shell shell, IFile file);
}
