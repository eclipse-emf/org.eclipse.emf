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


import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import org.eclipse.emf.codegen.jet.JETCompileTemplateOperation;
import org.eclipse.emf.codegen.jet.JETNature;
import org.eclipse.emf.codegen.presentation.CodeGenUIPlugin;


/**
 * Compile the JET template.
 */
public class CompileTemplateAction implements IActionDelegate 
{
  protected ISelection selection;

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
             for (Iterator<?> i = getSelectedObjects().iterator(); i.hasNext(); )
             {
               IFile file = (IFile)i.next();
               JETNature jetNature = JETNature.getRuntime(file.getProject());
               if (jetNature != null)
               {
                 JETCompileTemplateOperation compileTemplate = 
                   new JETCompileTemplateOperation(file.getProject(), jetNature.getTemplateContainers(), Collections.singleton(file));
                 compileTemplate.run(monitor);
               }
             }
           }
           catch (Exception e)
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
    this.selection = selection;
    setActionState(action);
  }

  protected void setActionState(IAction action) 
  {
    action.setEnabled(true);
  }

  protected List<?> getSelectedObjects()
  {
    return
      selection instanceof IStructuredSelection ?
        ((IStructuredSelection)selection).toList() :
        Collections.EMPTY_LIST;
  }

  protected boolean isSupportedAction(Object object) 
  {
    return object instanceof IFile && ((IFile)object).isAccessible();
  }
}
