/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: AddInputRootAction.java,v 1.7 2007/06/19 17:31:26 marcelop Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.mapping.action.AddRootTopAction;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditor;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditorPlugin;


/**
 *  
 */
public class AddInputRootAction extends AddRootTopAction
{
  /**
   *  
   */
  public AddInputRootAction()
  {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.mapping.action.AddRootTopAction#getTopsToAdd()
   */
  @Override
  protected Collection<?> getTopsToAdd()
  {
    Collection<Object> objects = new ArrayList<Object>();

    ViewerFilter viewerFilter = new ViewerFilter()
    {
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return !(element instanceof IFile) || "ecore".equals(((IFile)element).getFileExtension());  //$NON-NLS-1$
      }
    };
    final IFile[] files = WorkspaceResourceDialog.openFileSelection(
      workbenchPart.getSite().getShell(), 
      null, 
      Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_SelectInputEcoreModels_label"), //$NON-NLS-1$
      true, 
      null, 
      Collections.singletonList(viewerFilter));
        
    if (files.length > 0)
    {
      for (int i = 0; i < files.length; i++)
      {
        objects.addAll(((Ecore2EcoreEditor)workbenchPart).getEditingDomain().getResourceSet().getResource(
          URI.createPlatformResourceURI(files[i].getFullPath().toString(), true),
          true).getContents());
      }
    }

    return objects;
  }
}