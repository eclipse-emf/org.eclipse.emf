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
 * $Id: UndoAction.java,v 1.1 2010/03/11 02:30:12 khussey Exp $
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * An undo action is implemented by using the {@link org.eclipse.emf.common.command.CommandStack}.
 */
public class UndoAction extends Action
{
  protected EditingDomain domain;

  public UndoAction(EditingDomain domain)
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item", new Object [] { "" }));
    this.domain = domain;
    update();
  }

  public UndoAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item", new Object [] { "" }));
  }

  /**
   * This returns the action's domain.
   */
  public EditingDomain getEditingDomain()
  {
    return domain;
  }

  /**
   * This sets the action's domain.
   */
  public void setEditingDomain(EditingDomain domain)
  {
    this.domain = domain;
  }

  @Override
  public void run()
  {
    domain.getCommandStack().undo();
  }

  public void update()
  {
    setEnabled(domain.getCommandStack().canUndo());

    Command undoCommand = domain.getCommandStack().getUndoCommand();
    if (undoCommand != null && undoCommand.getLabel() != null)
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item", new Object [] { undoCommand.getLabel() }));
    }
    else 
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item", new Object [] { "" }));
    }

    if (undoCommand != null && undoCommand.getDescription() != null)
    {
      setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item_description", new Object [] { undoCommand.getDescription() }));
    }
    else
    {
      setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item_simple_description"));
    }
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
   */
  @Deprecated
  public void setActiveEditor(IEditorPart editorPart)
  {
    setActiveWorkbenchPart(editorPart);
  }

  /**
   * @since 2.1.0
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
    }
  }
}
