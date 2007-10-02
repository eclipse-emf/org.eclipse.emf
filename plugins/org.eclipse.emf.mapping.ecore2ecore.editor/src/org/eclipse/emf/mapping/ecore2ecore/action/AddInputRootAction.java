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
 * $Id: AddInputRootAction.java,v 1.8 2007/10/02 17:55:39 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.action;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.presentation.EcoreActionBarContributor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.mapping.action.AddRootTopAction;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditor;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditorPlugin;
import org.eclipse.ui.IWorkbenchPart;


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
    return getRootsToAdd(workbenchPart, editingDomain);
  }

  static Collection<?> getRootsToAdd(final IWorkbenchPart workbenchPart, EditingDomain editingDomain)
  {
    final Collection<Object> objects = new ArrayList<Object>();
    EcoreActionBarContributor.ExtendedLoadResourceAction.ExtendedLoadResourceDialog loadEcoreResourceDialog =
      new EcoreActionBarContributor.ExtendedLoadResourceAction.ExtendedLoadResourceDialog(workbenchPart.getSite().getShell(), editingDomain)
      {
        @Override
        protected boolean processResources()
        {
          if (domain != null)
          {
            for (URI uri : getURIs())
            {
              try
              {
                objects.addAll(((Ecore2EcoreEditor)workbenchPart).getEditingDomain().getResourceSet().getResource(uri, true).getContents());
              }
              catch (RuntimeException exception)
              {
                Ecore2EcoreEditorPlugin.INSTANCE.log(exception);
              }
            }
          }
          return true;
        }
      };
      
    loadEcoreResourceDialog.open();
    return objects;
  }
}