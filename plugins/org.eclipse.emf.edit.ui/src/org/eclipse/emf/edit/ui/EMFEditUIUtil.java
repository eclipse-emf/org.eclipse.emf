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
 * $Id: EMFEditUIUtil.java,v 1.1 2005/12/08 05:12:16 marcelop Exp $
 */

package org.eclipse.emf.edit.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @since 2.2.0
 */
public class EMFEditUIUtil
{
  /**
   * Opens the default editor for the resource that contains the specified
   * EObject.  This method only works if the resource's URI is a 
   * platform resource URI.
   */
  public static boolean openEditor(EObject eObject) throws PartInitException
  {
    if (eObject != null)
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        String path = resource.getURI().toString().substring("platform:/resource/".length());
        IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
        if (workspaceResource instanceof IFile)
        {
          IWorkbench workbench = PlatformUI.getWorkbench();
          IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
          IEditorPart editorPart = page.openEditor(new FileEditorInput((IFile)workspaceResource), workbench.getEditorRegistry().getDefaultEditor(workspaceResource.getFullPath().toString()).getId());
          return editorPart != null;
        }
      }
    }
    return false;
  }    
}
