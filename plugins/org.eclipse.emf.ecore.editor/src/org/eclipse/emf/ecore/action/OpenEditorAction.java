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
package org.eclipse.emf.ecore.action;


import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.part.FileEditorInput;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Generate Java beans from the XML Schema
 */
public class OpenEditorAction extends ActionDelegate
       implements IActionDelegate
{
  protected IFile file;

  public OpenEditorAction()
  {
    super();
  }

  @Override
  public void run(IAction action)
  {
    IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    IWorkbenchPage page = workbenchWindow.getActivePage();

    // Open an editor on the new file.
    //
    try
    {
      page.openEditor
        (new FileEditorInput(file), 
          action.getId().indexOf("XML") == -1 ? 
            "org.eclipse.emf.ecore.presentation.ReflectiveEditorID" :
            "org.eclipse.emf.ecore.presentation.XMLReflectiveEditorID");
    }
    catch (PartInitException exception)
    {
      MessageDialog.openError(workbenchWindow.getShell(), "Open Editor", exception.getMessage());
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) 
  {
    if (selection instanceof IStructuredSelection)
    {
      Object object = ((IStructuredSelection)selection).getFirstElement();
      if (object instanceof IFile)
      {
        file = (IFile)object;

        action.setEnabled
          (action.getId().indexOf("XML") != -1 ||
             Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey
               (file.getFullPath().getFileExtension()));
        return;
      }
    }
    file = null;
    action.setEnabled(false);
  }
}
