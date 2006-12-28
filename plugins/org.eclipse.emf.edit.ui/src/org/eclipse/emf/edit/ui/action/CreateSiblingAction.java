/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: CreateSiblingAction.java,v 1.4 2006/12/28 06:50:05 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.Collection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A sibling creation action is implemented by creating a {@link CreateChildCommand}.
 */
public class CreateSiblingAction extends StaticSelectionCommandAction
{
  /**
   * This describes the sibling to be created.
   */
  protected Object descriptor;

  /**
   * This constructs an instance of an action that creates a sibling
   * specified by <code>descriptor</code>.
   * @since 2.1.0
   */
  public CreateSiblingAction(IWorkbenchPart workbenchPart, ISelection selection,  Object descriptor)
  {
    super(workbenchPart);
    this.descriptor = descriptor;
    configureAction(selection);
  }

  /**
   * This constructor is simply retained for binary compatibility. It just
   * calls the {@link #CreateSiblingAction(IWorkbenchPart, ISelection, Object)
   * new form}.
   */
  public CreateSiblingAction(IEditorPart editorPart, ISelection selection,  Object descriptor)
  {
    this((IWorkbenchPart)editorPart, selection, descriptor);
  }

  /**
   * This creates the command for {@link
   * StaticSelectionCommandAction#createActionCommand}.
   */
  @Override
  protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection)
  {
    if (collection.size() == 1)
    {
      return CreateChildCommand.create(editingDomain, null,
                                       descriptor, collection);
    }
    return UnexecutableCommand.INSTANCE;
  }
}
