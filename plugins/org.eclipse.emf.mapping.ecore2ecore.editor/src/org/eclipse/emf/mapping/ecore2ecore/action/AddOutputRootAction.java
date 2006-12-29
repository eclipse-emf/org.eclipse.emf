/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: AddOutputRootAction.java,v 1.6 2006/12/29 18:28:58 marcelop Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.action;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.mapping.action.AddRootBottomAction;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditor;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditorPlugin;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;


/**
 *  
 */
public class AddOutputRootAction extends AddRootBottomAction
{
  /**
   *  
   */
  public AddOutputRootAction()
  {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.mapping.action.AddRootBottomAction#getBottomsToAdd()
   */
  @Override
  protected Collection<?> getBottomsToAdd()
  {
    Collection<Object> bottomsToAdd = new ArrayList<Object>();

    ResourceSelectionDialog resourceSelectionDialog = new ResourceSelectionDialog(
      workbenchPart.getSite().getShell(),
      ResourcesPlugin.getWorkspace().getRoot(),
      Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_SelectOutputEcoreModels_label")); //$NON-NLS-1$
    resourceSelectionDialog.open();

    Object[] result = resourceSelectionDialog.getResult();

    if (result != null)
    {

      for (int i = 0; i < result.length; i++)
      {
        IResource resource = (IResource)result[i];

        if (resource.getType() == IResource.FILE && "ecore".equals(resource.getFullPath().getFileExtension())) //$NON-NLS-1$
        {
          bottomsToAdd.addAll(((Ecore2EcoreEditor)workbenchPart).getEditingDomain().getResourceSet().getResource(
            URI.createPlatformResourceURI(resource.getFullPath().toString(), true),
            true).getContents());
        }
      }
    }

    return bottomsToAdd;
  }
}