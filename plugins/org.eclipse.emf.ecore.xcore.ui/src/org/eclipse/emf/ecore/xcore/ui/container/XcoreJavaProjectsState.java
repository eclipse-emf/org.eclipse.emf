/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.container;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.ui.containers.JavaProjectsState;
import org.eclipse.xtext.ui.resource.IStorage2UriMapperJdtExtensions;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class XcoreJavaProjectsState extends JavaProjectsState
{
  @Inject
  IStorage2UriMapperJdtExtensions jdtExtensions;

  @Override
  protected String doInitHandle(URI uri)
  {
    String result = getJavaProjectsHelper().initHandle(uri);
    if (result == null && uri.isPlatformResource() && uri.segmentCount() > 1)
    {
      IProject project = getWorkspaceRoot().getProject(uri.segment(1));
      IJavaProject javaProject = JavaCore.create(project);
      result = javaProject.exists() ? javaProject.getHandleIdentifier() : project.getName();
    }
    return result;
  }

  @Override
  protected List<String> doInitVisibleHandles(String handle)
  {
    List<String> result = getJavaProjectsHelper().initVisibleHandles(handle);
    if (!result.isEmpty())
    {
      IJavaElement javaElement = JavaCore.create(handle);
      if (javaElement != null)
      {
        IProject project = javaElement.getJavaProject().getProject();
        result = new UniqueEList<String>(result);
        LOOP:
        for (ListIterator<String> i = result.listIterator(); i.hasNext();)
        {
          String visibleHandle = i.next();
          IJavaElement visibleHandleElement = JavaCore.create(visibleHandle);
          if (visibleHandleElement instanceof IPackageFragmentRoot)
          {
            Map<URI, IStorage> allEntries = jdtExtensions.getAllEntries((IPackageFragmentRoot)visibleHandleElement);
            for (URI uri : allEntries.keySet())
            {
              if ("xcore".equals(uri.fileExtension()) && uri.isPlatformResource())
              {
                String projectName = uri.segment(1);
                if (!result.contains(projectName))
                {
                  i.add(projectName);
                }
                continue LOOP;
              }
            }
          }
        }

        result.addAll(getProjectsHelper().initVisibleHandles(project.getName()));
      }
    }
    return result;
  }
}
