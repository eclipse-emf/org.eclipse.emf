/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: RedoAction.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * An redo action is implemented by using the {@link org.eclipse.emf.common.command.CommandStack}.
 */
public class RedoAction extends Action
{
  protected EditingDomain domain;

  public RedoAction(EditingDomain domain)
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object [] { "" }));
    this.domain = domain;
    update();
  }

  public RedoAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object [] { "" }));
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

  public void run()
  {
    domain.getCommandStack().redo();
  }

  public void update()
  {
    setEnabled(domain.getCommandStack().canRedo());

    Command redoCommand = domain.getCommandStack().getRedoCommand();
    if (redoCommand != null && redoCommand.getLabel() != null)
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object [] { redoCommand.getLabel() }));
    }
    else
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object [] { "" }));
    }

    if (redoCommand != null && redoCommand.getDescription() != null)
    {
      setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item_description", new Object [] { redoCommand.getDescription() }));
    }
    else 
    {
      setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item_simple_description"));
    }
  }

  public void setActiveEditor(IEditorPart editorPart)
  {
    if (editorPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)editorPart).getEditingDomain();
    }
  }
}
