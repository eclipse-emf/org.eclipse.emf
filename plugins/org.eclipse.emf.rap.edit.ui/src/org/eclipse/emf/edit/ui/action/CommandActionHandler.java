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
 * $Id: CommandActionHandler.java,v 1.1 2010/03/11 02:30:12 khussey Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/*
 * This base action class implements an action by creating a command and delegating to it;
 * it's main use is as a base class for action handlers.
 */
public class CommandActionHandler extends BaseSelectionListenerAction
{
  /**
   * This keeps track of the editing domain of the action.
   */
  protected EditingDomain domain;

  /**
   * This keeps track of the command delegate that is created by {@link #createCommand}.
   */
  protected Command command;

  /**
   * This constructs and instance in this editing domain.
   */
  public CommandActionHandler(EditingDomain domain)
  {
    // The label is not used.
    //
    super("");

    this.domain = domain;
  }

  /**
   * This constructs and instance in this editing domain.
   */
  public CommandActionHandler(EditingDomain domain, String label)
  {
    super(label);

    this.domain = domain;
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

  /**
   * This simply execute the command.
   */
  @Override
  public void run()
  {
    domain.getCommandStack().execute(command);
  }

  /**
   * When the selection changes, this will call {@link #createCommand} with the appropriate collection of selected objects.
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection)
  {
    List<?> list = selection.toList();
    Collection<Object> collection = new ArrayList<Object>(list);
    command = createCommand(collection);

    return command.canExecute();
  }

  /**
   * This default implementation simply returns {@link org.eclipse.emf.common.command.UnexecutableCommand#INSTANCE}.
   */
  public Command createCommand(Collection<?> selection)
  {
    return UnexecutableCommand.INSTANCE;
  }
}
