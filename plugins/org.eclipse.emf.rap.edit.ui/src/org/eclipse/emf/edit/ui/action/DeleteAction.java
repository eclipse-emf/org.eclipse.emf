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
package org.eclipse.emf.edit.ui.action;


import java.util.Collection;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * A delete action removes objects from their parent containers, optionally cleaning up other references to the objects.
 * It is implemented by creating a {@link RemoveCommand} or {@link DeleteCommand}.
 */
public class DeleteAction extends CommandActionHandler
{
  private static final long serialVersionUID = 1L;

  /**
   * Whether the action should clean up all references to deleted objects.
   * @since 2.2
   */
  protected boolean removeAllReferences;

  /**
   * @since 2.2
   */
  public DeleteAction(EditingDomain domain, boolean removeAllReferences)
  {
    super(domain, EMFEditUIPlugin.INSTANCE.getString("_UI_Delete_menu_item"));
    this.removeAllReferences = removeAllReferences;
  }

  public DeleteAction(EditingDomain domain)
  {
    this(domain, false);
  }

  /**
   * @since 2.2
   */
  public DeleteAction(boolean removeAllReferences)
  {
    this(null, removeAllReferences);
  }

  public DeleteAction()
  {
    this(null);
  }
  
  @Override
  public Command createCommand(Collection<?> selection)
  {
    return removeAllReferences ? DeleteCommand.create(domain, selection) : RemoveCommand.create(domain, selection);
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
