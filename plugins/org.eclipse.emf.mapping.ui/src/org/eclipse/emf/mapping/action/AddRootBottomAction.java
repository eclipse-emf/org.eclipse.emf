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
 * $Id: AddRootBottomAction.java,v 1.1 2004/03/06 17:31:33 marcelop Exp $
 */
package org.eclipse.emf.mapping.action;


import java.util.Collection;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.ui.action.CommandAction;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This adds new root inputs to the mapping domain's root mapping.
 */
public class AddRootBottomAction extends CommandAction 
{
  /**
   * This method should be overriden with a domain-specific implementation 
   * that returns the inputs to be added, e.g., by querying the user with a dialog.
   */
  protected Collection getBottomsToAdd()
  {
    return null;
  }

  /**
   * This action ignores the selection.
   */
  public void selectionChanged(IAction action, ISelection selection) 
  {
  }

  public void run(IAction action)
  {
    Collection additions = getBottomsToAdd();
    MappingRoot mappingRoot = ((MappingDomain)editingDomain).getMappingRoot();
    command = AddCommand.create
      (editingDomain, 
       mappingRoot, 
       mappingRoot.isTopToBottom() ?
         //mappingRoot.ePackageMapping().getMapping_Outputs() :
         //mappingRoot.ePackageMapping().getMapping_Inputs(),
         MappingPackage.eINSTANCE.getMapping_Outputs() :
         MappingPackage.eINSTANCE.getMapping_Inputs(),
       additions);

    super.run(action);
  }

  public void setActiveEditor(IAction action, IEditorPart editorPart)
  {
    super.setActiveEditor(action, editorPart);
    action.setEnabled(true);
  }
}
