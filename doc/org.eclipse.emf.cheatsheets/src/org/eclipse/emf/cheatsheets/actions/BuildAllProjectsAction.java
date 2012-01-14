/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.cheatsheets.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import org.eclipse.emf.cheatsheets.CheatSheetsPlugin;


/**
 * <p>Action that builds all projects on Workspace.</p>
 * @since 2.2.0
 */

public class BuildAllProjectsAction extends Action implements ICheatSheetAction
{
  /**
   * Execution of the action
   * @param params Array of parameters
   * @param manager Cheatsheet Manager
   */
  public void run(String[] params, ICheatSheetManager manager)
  {
    run();
  }

  /**
   * Run the action
   */
  @Override
  public void run()
  {
    WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException
        {
          buildAllProjects(monitor);
        }
      };
    try
    {
      runWithProgress(operation);
      notifyResult(true);
      return;
    }
    catch (Exception e)
    {
      CheatSheetsPlugin.INSTANCE.log(e);
    }
    notifyResult(false);
  }

  /**
   * Run with progress
   * @param runnable
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  protected void runWithProgress(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException
  {
    new ProgressMonitorDialog(Display.getCurrent().getActiveShell()).run(false, false, runnable);
  }

  /**
   * Build all projects in the workspace
   * @param monitor Monitoring the action
   * @throws CoreException
   */
  protected void buildAllProjects(IProgressMonitor monitor) throws CoreException
  {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot root = workspace.getRoot();
    IProject[] projects = root.getProjects();
    monitor.beginTask(CheatSheetsPlugin.INSTANCE.getString("_UI_BuildProject_message"), projects.length);
    for (int i = 0; i < projects.length; i++)
    {
      projects[i].build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new SubProgressMonitor(monitor, 1));
    }
    monitor.done();
  }
}
