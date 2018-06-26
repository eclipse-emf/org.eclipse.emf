/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
      IProject project = getWorkspaceRoot().getProject(URI.decode(uri.segment(1)));
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
        // Because this is for an Xcore project, where the *.xcore resources are not on the classpath,
        // add the handle for the overall Java project to the resource set.
        // This ensures that inferred JVM types in the Xcore models have precedence when resolving JVM type references.
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=502276
        //
        result = new UniqueEList<String>(result);
        if (!result.contains(handle))
        {
          result.add(0, handle);
        }

        LOOP:
        for (ListIterator<String> i = result.listIterator(); i.hasNext();)
        {
          String visibleHandle = i.next();
          IJavaElement visibleHandleElement = JavaCore.create(visibleHandle);
          if (visibleHandleElement instanceof IPackageFragmentRoot)
          {
            // If there are any Xcore resource URIs related to this fragment root,
            // include the handle for that URI in the result, right after this fragment's handle.
            // This ensures that inferred JVM types from Xcore models in the target platform are used.
            // See org.eclipse.xtext.common.types.xtext.ui.JdtIndexedJvmTypeAccess.findAccessibleType(String, ResourceSet, Iterator<IEObjectDescription>)
            // Unless there is such an entry on the list of visible containers, the resource description for this URI will not be used.
            // https://bugs.eclipse.org/bugs/show_bug.cgi?id=500822
            //
            IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot)visibleHandleElement;
            Map<URI, IStorage> allEntries = jdtExtensions.getAllEntries(packageFragmentRoot);
            for (URI uri : allEntries.keySet())
            {
              if ("xcore".equals(uri.fileExtension()) && uri.isPlatformResource())
              {
                String uriHandle = initHandle(uri);
                if (!result.contains(uriHandle))
                {
                  i.add(uriHandle);
                }
                continue LOOP;
              }
            }

            IJavaProject javaProject = packageFragmentRoot.getJavaProject();
            if (javaProject != null)
            {
              String projectHandle = javaProject.getHandleIdentifier();
              if (!result.contains(projectHandle))
              {
                i.add(projectHandle);
              }
            }
          }
        }

        IProject project = javaElement.getJavaProject().getProject();
        result.addAll(getProjectsHelper().initVisibleHandles(project.getName()));
      }
    }
    return result;
  }
}
