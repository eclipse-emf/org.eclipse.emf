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
 * $Id: GenModelReloadActionDelegate.java,v 1.2 2005/06/08 06:11:55 nickb Exp $
 */
package org.eclipse.emf.importer.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
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
 * Reloads a GenModel file and the Ecore files behind it.
 * This just invokes the {@link EMFWizard} with an existing file.
 * If this action is being contributed to an editor, we'll use its input as the file. Otherwise, we'll
 * try to obtain it from the selection.
 */
public class GenModelReloadActionDelegate extends ActionDelegate implements IEditorActionDelegate
{
  protected boolean fixedFile = false;
  protected IFile file;

  public GenModelReloadActionDelegate()
  {
    super();
  }

  public GenModelReloadActionDelegate(IEditorPart editorPart)
  {
    this();
  }

  public void dispose()
  {
    file = null;
    super.dispose();
  }

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

  public void run(IAction action)
  {
    run(PlatformUI.getWorkbench(), PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), file);
  }

  protected void run(IWorkbench workbench, Shell shell, IFile file)
  {
    EMFModelWizard wizard = new EMFModelWizard(file);
    wizard.init(workbench, new StructuredSelection(file));

    WizardDialog wizardDialog = new WizardDialog(shell, wizard);
    wizardDialog.create();
    wizardDialog.getShell().setSize(540, 580);
    int result = wizardDialog.open();
  }
}
