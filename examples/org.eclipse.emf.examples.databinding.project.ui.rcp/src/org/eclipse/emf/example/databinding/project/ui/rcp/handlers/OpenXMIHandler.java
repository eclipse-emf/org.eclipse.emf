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

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import org.eclipse.emf.example.databinding.project.ui.rcp.views.ProjectAdminViewPart;


/**
 * Handles the opening of a XMI-Resource
 */
public class OpenXMIHandler extends AbstractHandler
{

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IEvaluationContext ctx = (IEvaluationContext)event.getApplicationContext();
    Shell shell = (Shell)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_SHELL_NAME);

    FileDialog dialog = new FileDialog(shell, SWT.OPEN);
    String name = dialog.open();

    if (name != null)
    {
      File f = new File(name);
      try
      {
        IWorkbenchWindow w = (IWorkbenchWindow)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
        String path = f.toURL().toString();
        path = path.replaceAll(":", "#_#");

        w.getActivePage().showView(ProjectAdminViewPart.ID, path, IWorkbenchPage.VIEW_ACTIVATE);
      }
      catch (PartInitException e)
      {
        e.printStackTrace();
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
    }

    return null;
  }

}
