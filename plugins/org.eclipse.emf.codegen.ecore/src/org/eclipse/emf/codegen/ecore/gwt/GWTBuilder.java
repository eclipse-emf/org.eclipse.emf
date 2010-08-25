/**
 * <copyright> 
 *
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GWTBuilder.java,v 1.2 2010/08/25 13:36:45 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.gwt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;

public class GWTBuilder extends IncrementalProjectBuilder
{
  protected static IPath[] CONTAINER_PATHS = new IPath[] { new Path("org.eclipse.pde.core.requiredPlugins"), new Path("com.google.appengine.eclipse.core.GAE_CONTAINER"), new Path("com.google.gwt.eclipse.core.GWT_CONTAINER")};
  @SuppressWarnings("rawtypes")
  @Override
  protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException
  {
    Set<IProject> result = new HashSet<IProject>();
    final IProject project = getProject();
    if (project.exists())
    {
      IWorkspaceRoot root = project.getWorkspace().getRoot();
      IJavaProject javaProject = JavaCore.create(project);
      IClasspathContainer gaeClasspathContainer = JavaCore.getClasspathContainer(new Path("com.google.appengine.eclipse.core.GAE_CONTAINER"), javaProject);
      if (gaeClasspathContainer != null)
      { 
        for (IClasspathEntry classpathEntry : gaeClasspathContainer.getClasspathEntries())
        {
          if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY && 
                classpathEntry.getContentKind() == IPackageFragmentRoot.K_BINARY)
          {
            IPath path = classpathEntry.getPath();
            int segmentCount = path.segmentCount();
            if (segmentCount >= 4)
            {
              if (path.segment(segmentCount - 2).equals("user") || path.segment(segmentCount - 3).equals("user"))
              {
                copy(URI.createFileURI(path.toOSString()), URI.createPlatformResourceURI(project.getName() + "/war/WEB-INF/lib/" + path.lastSegment(), true));
              }
            }
          }
        }
      }

      IClasspathContainer gwtClasspathContainer = JavaCore.getClasspathContainer(new Path("com.google.gwt.eclipse.core.GWT_CONTAINER"), javaProject);
      if (gwtClasspathContainer != null)
      {
        for (IClasspathEntry classpathEntry : gwtClasspathContainer.getClasspathEntries())
        {
          if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY && 
                classpathEntry.getContentKind() == IPackageFragmentRoot.K_BINARY)
          {
            IPath path = classpathEntry.getPath();
            int segmentCount = path.segmentCount();
            if (segmentCount >= 2)
            {
              path = path.removeLastSegments(1).append("gwt-servlet.jar");
              URI fileURI = URI.createFileURI(path.toOSString());
              if (URIConverter.INSTANCE.exists(fileURI, null))
              {
                copy(fileURI, URI.createPlatformResourceURI(project.getName() + "/war/WEB-INF/lib/" + path.lastSegment(), true));
              }
            }
          }
        }
      }
      IClasspathContainer pdeClasspathContainer = JavaCore.getClasspathContainer(new Path("org.eclipse.pde.core.requiredPlugins"), javaProject);
      if (pdeClasspathContainer != null)
      {
        for (IClasspathEntry classpathEntry : pdeClasspathContainer.getClasspathEntries())
        {
          if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT)
          {
            IProject requiredProject = root.getProject(classpathEntry.getPath().segment(0));
            IJavaProject requiredJavaProject = JavaCore.create(requiredProject);
            IPath outputLocation = requiredJavaProject.getOutputLocation();
            final int depth = outputLocation.segmentCount();
            IFolder folder = root.getFolder(outputLocation);
            folder.accept
             (new IResourceVisitor()
              {
                public boolean visit(IResource resource) throws CoreException
                {
                  if (resource.getType() == IResource.FILE)
                  {
                    IPath fullPath = resource.getFullPath();
                    copy
                      (URI.createPlatformResourceURI(fullPath.toString(), true), 
                       URI.createPlatformResourceURI(project.getName() + "/war/WEB-INF/classes/" + fullPath.removeFirstSegments(depth), true));
                  }
                  return true;
                }
              },
              IResource.DEPTH_INFINITE,
              0);
            result.add(requiredProject);
          }
          else if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY && classpathEntry.getContentKind() == IPackageFragmentRoot.K_BINARY)
          {
            IPath path = classpathEntry.getPath();
            copy(URI.createFileURI(path.toOSString()), URI.createPlatformResourceURI(project.getName() + "/war/WEB-INF/lib/" + path.lastSegment(), true));
          }
        }
      }
    }
    return result.toArray(new IProject[result.size()]);
  }

  protected void copy(URI sourceURI, URI targetURI)
  {
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(URIConverter.OPTION_REQUESTED_ATTRIBUTES, Collections.singleton(URIConverter.ATTRIBUTE_TIME_STAMP));
    Long sourceTimeStamp = (Long)URIConverter.INSTANCE.getAttributes(sourceURI, options).get(URIConverter.ATTRIBUTE_TIME_STAMP);
    Long targetTimeStamp = (Long)URIConverter.INSTANCE.getAttributes(targetURI, options).get(URIConverter.ATTRIBUTE_TIME_STAMP);
    if (targetTimeStamp == null || targetTimeStamp.compareTo(sourceTimeStamp) < 0)
    {
      try
      {
        OutputStream out = URIConverter.INSTANCE.createOutputStream(targetURI);
        InputStream in = URIConverter.INSTANCE.createInputStream(sourceURI);
        byte[] bytes = new byte[4048];
        for (int length = in.read(bytes); length > 0; length = in.read(bytes))
        {
          out.write(bytes, 0, length);
        }
        in.close();
        out.close();
      }
      catch (IOException exception)
      {
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
    }
  }
}
