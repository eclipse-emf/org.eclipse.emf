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
package org.eclipse.emf.codegen.action;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import org.eclipse.emf.codegen.jet.JETAddNatureOperation;
import org.eclipse.emf.codegen.presentation.CodeGenUIPlugin;


public class AddJETNatureAction implements IActionDelegate 
{
  protected List<IProject> projects;
  
  public AddJETNatureAction() 
  {
    super();
    projects = new ArrayList<IProject>();
  }

  public void run(IAction action) 
  {
    if (action.isEnabled())
    {
      IRunnableWithProgress op = 
        new IRunnableWithProgress()
        {
          public void run(IProgressMonitor monitor) 
          {
            try 
            {
              JETAddNatureOperation addNature = new JETAddNatureOperation(projects);
              addNature.run(monitor);
            } 
            catch (CoreException e) 
            {
              CodeGenUIPlugin.write(e);
            }
          }
        };

      try 
      {
        ProgressMonitorDialog dialog = 
          new ProgressMonitorDialog(CodeGenUIPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell());
        dialog.run(false, true, op);
      } 
      catch (Exception e) 
      {
        CodeGenUIPlugin.write(e);
      }
    }
  }

  public void selectionChanged(IAction action, ISelection selection) 
  {
    projects.clear();
    if (selection instanceof IStructuredSelection)
    {
      for (Object object : ((IStructuredSelection)selection).toList())
      {
        if (object instanceof IProject)
        {
          projects.add((IProject)object);
        }
        else if (object instanceof IJavaProject)
        {
          projects.add(((IJavaProject)object).getProject());
        }
      }
    }
  }
}
