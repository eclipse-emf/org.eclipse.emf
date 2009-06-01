/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: CreateCommittershipHandler.java,v 1.1 2009/06/01 17:04:02 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.example.databinding.project.ui.rcp.ResourceProvider;
import org.eclipse.emf.example.databinding.project.ui.rcp.dialogs.CommitterShipDialog;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectFactory;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Command which creates a new committer
 */
public class CreateCommittershipHandler extends AbstractHandler
{

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IEvaluationContext ctx = (IEvaluationContext)event.getApplicationContext();
    Shell shell = (Shell)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_SHELL_NAME);
    IModelResource resource = (IModelResource)ctx.getVariable(ResourceProvider.MODEL_RESOURCE_NAME);
    Project parent = (Project)ctx.getVariable(ResourceProvider.PROJECT_NAME);

    CommitterShip com = ProjectFactory.eINSTANCE.createCommitterShip();

    if (resource.setSavePoint().isOK())
    {
      Command cmd = AddCommand.create(resource.getEditingDomain(), parent, ProjectPackage.Literals.PROJECT__COMMITTERS, com);

      if (cmd.canExecute())
      {
        resource.executeCmd(cmd);

        CommitterShipDialog dialog = new CommitterShipDialog(shell, resource, com, true);
        if (dialog.open() == IDialogConstants.OK_ID)
        {
          resource.commit();
        }
        else
        {
          resource.rollback();
        }
      }
      else
      {
        resource.rollback();
        throw new ExecutionException("Could not execute add comitter ship command");
      }
    }
    else
    {
      throw new ExecutionException("Could not create a save point");
    }

    return null;
  }
}