/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: PersonEditingHandler.java,v 1.2 2009/06/01 17:19:26 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.ResourceProvider;
import org.eclipse.emf.example.databinding.project.ui.rcp.dialogs.PersonDialog;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectFactory;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Handles the editing/creation of persons
 */
public class PersonEditingHandler extends AbstractHandler
{
  /**
   * Parameter passed to switch between creation/editing
   */
  public static final String ACTION_PARAM = Activator.PLUGIN_ID + ".person.action";
  /**
   * Parameter value to create a new person
   */
  public static final String ACTION_PARAM_VALUE_NEW = "new";
  /**
   * Parameter value to edit a person
   */
  public static final String ACTION_PARAM_VALUE_EDIT = "edit";

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IEvaluationContext ctx = (IEvaluationContext)event.getApplicationContext();
    IModelResource resource = (IModelResource)ctx.getVariable(ResourceProvider.MODEL_RESOURCE_NAME);
    Shell shell = (Shell)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_SHELL_NAME);

    String action = event.getParameter(ACTION_PARAM);

    if (action != null)
    {
      if (action.equals(ACTION_PARAM_VALUE_NEW) || action.equals(ACTION_PARAM_VALUE_EDIT))
      {
        IStatus s = resource.setSavePoint();
        if (s.isOK())
        {
          Person p;

          if (action.equals(ACTION_PARAM_VALUE_NEW))
          {
            p = ProjectFactory.eINSTANCE.createPerson();
            Command cmd = AddCommand.create(
              resource.getEditingDomain(),
              resource.getFoundation(),
              ProjectPackage.Literals.FOUNDATION__PERSONS,
              p);
            if (cmd.canExecute())
            {
              resource.executeCmd(cmd);
            }
            else
            {
              resource.rollback();
              throw new ExecutionException("Could not execute add command");
            }
          }
          else
          {
            p = ((CommitterShip)ctx.getVariable(ResourceProvider.COMMITTER_NAME)).getPerson();
          }

          if (p != null)
          {
            PersonDialog dialog = new PersonDialog(shell, resource.getEditingDomain(), p);
            if (dialog.open() == IDialogConstants.OK_ID)
            {
              resource.commit();
            }
            else
            {
              resource.rollback();
            }
          }
        }
        else
        {
          throw new ExecutionException("Could not set a save point");
        }
      }
    }

    return null;
  }
}