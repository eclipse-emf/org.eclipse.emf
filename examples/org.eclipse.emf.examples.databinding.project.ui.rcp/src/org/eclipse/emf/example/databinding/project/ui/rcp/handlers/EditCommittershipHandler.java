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
 * $Id: EditCommittershipHandler.java,v 1.2 2009/06/01 17:19:26 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;

import org.eclipse.emf.example.databinding.project.ui.rcp.ResourceProvider;
import org.eclipse.emf.example.databinding.project.ui.rcp.dialogs.CommitterShipDialog;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;


/**
 * Handles the editing of committers
 */
public class EditCommittershipHandler extends AbstractHandler
{

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IEvaluationContext ctx = (IEvaluationContext)event.getApplicationContext();
    Shell shell = (Shell)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_SHELL_NAME);
    IModelResource resource = (IModelResource)ctx.getVariable(ResourceProvider.MODEL_RESOURCE_NAME);
    CommitterShip com = (CommitterShip)ctx.getVariable(ResourceProvider.COMMITTER_NAME);

    if (resource.setSavePoint().isOK())
    {
      CommitterShipDialog dialog = new CommitterShipDialog(shell, resource, com, false);
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
      throw new ExecutionException("Could not create a save point");
    }

    return null;
  }

}