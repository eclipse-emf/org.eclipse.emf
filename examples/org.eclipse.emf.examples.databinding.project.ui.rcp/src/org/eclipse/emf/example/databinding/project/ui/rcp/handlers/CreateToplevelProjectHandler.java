/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.example.databinding.project.ui.rcp.ResourceProvider;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectFactory;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Create new toplevel project
 */
public class CreateToplevelProjectHandler extends AbstractHandler
{
  /**
   * The command id
   */
  public static final String ID = "org.eclipse.emf.examples.databinding.project.ui.rcp.createtopproject";

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IEvaluationContext ctx = (IEvaluationContext)event.getApplicationContext();
    IModelResource resource = (IModelResource)ctx.getVariable(ResourceProvider.MODEL_RESOURCE_NAME);

    Project project = ProjectFactory.eINSTANCE.createProject();
    Command cmd = AddCommand.create(
      resource.getEditingDomain(),
      resource.getFoundation(),
      ProjectPackage.Literals.FOUNDATION__PROJECTS,
      project);

    if (cmd.canExecute())
    {
      resource.executeCmd(cmd);
      return project;
    }

    throw new ExecutionException("Could not add top level project");
  }

}
