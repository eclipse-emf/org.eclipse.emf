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
package org.eclipse.emf.codegen.jet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.CodeGenPlugin;


public class JETAddNatureOperation implements IWorkspaceRunnable 
{
  protected List<IProject> projects;

  /**
   * Creates an instance from the given collection of projects.
   */
  public JETAddNatureOperation(Collection<?> objects) 
  {
    super();
    projects = new ArrayList<IProject>();
    for (Object object : objects)
    {
      if (object instanceof IProject)
      {
        projects.add((IProject)object);
      }
    }
  }

  /**
   * Perform this operation.
   */
  public void run(IProgressMonitor monitor) throws CoreException 
  {
    if (!projects.isEmpty())
    {
      monitor.beginTask("", projects.size());
      monitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_AddJETNature_message"));

      for (IProject project : projects)
      {
        monitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_AddJETNatureTo_message", new Object [] { project.getName() }));
        IProjectDescription description = project.getDescription();
        String[] natures = description.getNatureIds();
        String[] newNatures = new String[natures.length + 1];
        System.arraycopy(natures, 0, newNatures, 1, natures.length);
        newNatures[0] = IJETNature.NATURE_ID;
        description.setNatureIds(newNatures);
        project.setDescription(description, new SubProgressMonitor(monitor, 1));
      }

      monitor.done();
    }
  }
}
