/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: CreateChildAction.java,v 1.1 2010/03/11 02:30:12 khussey Exp $
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
 * A child creation action is implemented by creating a {@link
 * CreateChildCommand}.
 */
public class CreateChildAction extends StaticSelectionCommandAction
{
  /**
   * This describes the child to be created.
   */
  protected Object descriptor;

  /**
   * This constructs an instance of an action that uses the workbench part's editing domain to create a child
   * specified by <code>descriptor</code> for the single object in the
   * <code>selection</code>.
   * @since 2.1.0
   */
  public CreateChildAction(IWorkbenchPart workbenchPart, ISelection selection, Object descriptor)
  {
    super(workbenchPart);
    this.descriptor = descriptor;
    configureAction(selection);
  }

  /**
   * This constructor is simply retained for binary compatibility. It just
   * calls the {@link #CreateChildAction(IWorkbenchPart, ISelection, Object)
   * new form}.
   */
  public CreateChildAction(IEditorPart editorPart, ISelection selection, Object descriptor)
  {
    this((IWorkbenchPart)editorPart, selection, descriptor);
  }

  /**
   * This constructs an instance of an action that uses the given editing domain to create a child
   * specified by <code>descriptor</code> for the single object in the
   * <code>selection</code>.
   * @since 2.4.0
   */
  public CreateChildAction(EditingDomain editingDomain, ISelection selection, Object descriptor)
  {
    super(editingDomain);
    this.descriptor = descriptor;
    configureAction(selection);
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
      Object owner = collection.iterator().next();
      return CreateChildCommand.create(editingDomain, owner,
                                       descriptor, collection);
    }
    return UnexecutableCommand.INSTANCE;
  }
}
