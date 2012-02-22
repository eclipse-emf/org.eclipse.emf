/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.common.types.xtext.ui.XtextResourceSetBasedProjectProvider;

public class XcoreJavaProjectProvider extends XtextResourceSetBasedProjectProvider
{
  @Override
  public IJavaProject getJavaProject(ResourceSet resourceSet)
  {
    IJavaProject result = super.getJavaProject(resourceSet);
    if (result == null)
    {
      for (Resource resource : resourceSet.getResources())
      {
        URI uri = resource.getURI();
        if (uri.isPlatformResource())
        {
          IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(uri.segment(1));
          if (project.exists())
          {
            try
            {
              if (project.hasNature(JavaCore.NATURE_ID))
              {
                result = JavaCore.create(project);
                break;
              }
            }
            catch (CoreException exception)
            {
              // Ignore.
            }
          }
        }
      }
    }
    return result;
  }
}
