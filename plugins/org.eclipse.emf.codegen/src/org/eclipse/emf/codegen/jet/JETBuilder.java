/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: JETBuilder.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;


public class JETBuilder extends IncrementalProjectBuilder 
{
  /**
   * Creates a new instances.
   */
  public JETBuilder() 
  {
    super();
  }

  protected IProject[] build(int kind, Map arguments, IProgressMonitor monitor) throws CoreException 
  {
    if (!getProject().exists())
    {
      return new IProject[0];
    }
    else
    {
      try 
      {
        if (kind == FULL_BUILD) 
        {
          fullBuild(monitor);
        } 
        else 
        {
          IResourceDelta delta = getDelta(getProject());
          if (delta == null) 
          {
            fullBuild(monitor);
          } 
          else 
          {
            incrementalBuild(delta, monitor);
          }
        }
      } 
      catch (OperationCanceledException e) 
      {
        // Do nothing for now, and avoid propagating the exception.  
        // How should builders handle cancel?
      } 

      return null;
    }
  }

  /**
   * Does a full build.
   */
  protected void fullBuild(IProgressMonitor monitor) throws CoreException
  {
    JETNature nature = JETNature.getRuntime(getProject());
    if (nature != null) 
    {
      JETCompileTemplateOperation compileTemplateOperation = 
        new JETCompileTemplateOperation(getProject(), nature.getTemplateContainers());
      compileTemplateOperation.setInBuild(true);
      if (compileTemplateOperation.shouldCompile()) 
      {
        compileTemplateOperation.run(monitor);
      }
    }
  }

  /**
   * Does an incremental build.
   */
  protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException
  {
    JETNature nature = JETNature.getRuntime(getProject());
    if (nature != null) 
    {
      if (delta.getKind() == delta.ADDED || 
            delta.getKind() == delta.CHANGED || 
            delta.getKind() == delta.CONTENT || 
            delta.getKind() == delta.OPEN) 
      {
        JETCompileTemplateOperation compileTemplateOperation = 
          new JETCompileTemplateOperation(getProject(), nature.getTemplateContainers()); 
        compileTemplateOperation.setInBuild(true);
        if (compileTemplateOperation.shouldCompile()) 
        {
          compileTemplateOperation.run(monitor);
        }
      }
    }
  }
}
