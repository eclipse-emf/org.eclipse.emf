/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.interpreter.IClassLoaderProvider;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtext.common.types.xtext.ui.XtextResourceSetBasedProjectProvider;
import org.osgi.framework.Bundle;

public class XcoreJavaProjectProvider extends XtextResourceSetBasedProjectProvider implements IClassLoaderProvider
{
  public List<IJavaProject> getJavaProjects(Resource resource)
  {
    List<IJavaProject> result = new UniqueEList<IJavaProject>();
    GenModel genModel = (GenModel)EcoreUtil.getObjectByType(resource.getContents(), GenModelPackage.Literals.GEN_MODEL);
    if (genModel != null)
    {
      result.add(getJavaProject(genModel.getModelDirectory()));
      result.add(getJavaProject(genModel.getEditDirectory()));
      result.add(getJavaProject(genModel.getEditorDirectory()));
      result.add(getJavaProject(genModel.getTestsDirectory()));
    }
    else
    {
      result.add(getJavaProject(resource.getResourceSet()));
    }
    return result;
  }

  protected IJavaProject getJavaProject(String directory)
  {
    if (directory != null)
    {
      URI directoryURI = URI.createURI(directory);
      if (directoryURI.segmentCount() > 0)
      {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(directoryURI.segment(0));
        if (project.exists())
        {
          try
          {
            if (project.hasNature(JavaCore.NATURE_ID))
            {
              return JavaCore.create(project);
            }
          }
          catch (CoreException exception)
          {
            // Ignore.
          }
        } 
      }
    }
    return null;
  }

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

  public ClassLoader getClassLoader(ResourceSet resourceSet)
  {
    IJavaProject project = getJavaProject(resourceSet);
    if (project != null)
    {
      IWorkspaceRoot workspaceRoot = project.getProject().getWorkspace().getRoot();
      List<URL> libraryURLs = new UniqueEList<URL>();
      try
      {
        getAllReferencedProjects(libraryURLs, new IProject[] { project.getProject() });
        IClasspathEntry [] classpath = project.getResolvedClasspath(true);
        if (classpath != null)
        {
          for (int i = 0; i < classpath.length; ++i)
          {
            IClasspathEntry classpathEntry =  classpath[i];
            switch (classpathEntry.getEntryKind())
            {
              case IClasspathEntry.CPE_LIBRARY:
              case IClasspathEntry.CPE_CONTAINER:
              {
                libraryURLs.add(new URL(URI.createFileURI(classpathEntry.getPath().toString()).toString()));
                break;
              }
              case IClasspathEntry.CPE_PROJECT:
              {
                IProject referencedProject = workspaceRoot.getProject(classpathEntry.getPath().segment(0));
                IJavaProject referencedJavaProject = JavaCore.create(referencedProject);
                IContainer container = workspaceRoot.getFolder(referencedJavaProject.getOutputLocation());
                libraryURLs.add(new URL(URI.createFileURI(container.getLocation().toString() + "/").toString()));

                getAllReferencedProjects(libraryURLs, referencedProject.getDescription().getReferencedProjects());
                getAllReferencedProjects(libraryURLs, referencedProject.getDescription().getDynamicReferences());
                break;
              }
              case IClasspathEntry.CPE_SOURCE:
              case IClasspathEntry.CPE_VARIABLE:
              default:
              {
                break;
              }
            }
          }
        }

        return new URLClassLoader(libraryURLs.toArray(new URL [libraryURLs.size()]),  getClass().getClassLoader());
      }
      catch (MalformedURLException exception)
      {
        exception.printStackTrace();
      }
      catch (JavaModelException exception)
      {
        exception.printStackTrace();
      }
      catch (CoreException exception)
      {
        exception.printStackTrace();
      }
    }
    for (Resource resource : resourceSet.getResources())
    {
      URI uri = resource.getURI();
      if (uri.isPlatformPlugin())
      {
        final Bundle bundle = Platform.getBundle(uri.segments()[1]);
        return
          new ClassLoader()
          {
            @Override
            public Enumeration<URL> findResources(String name) throws IOException
            {
              return bundle.getResources(name);
            }

            @Override
            public URL findResource(String name)
            {
              return bundle.getResource(name);
            }


            @Override
            public URL getResource(String name)
            {
              return findResource(name);
            }

            @Override
            public Class<?> findClass(String name) throws ClassNotFoundException
            {
              return bundle.loadClass(name);
            }

            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
            {
              Class<?> clazz = findClass(name);
              if (resolve)
              {
                resolveClass(clazz);
              }
              return clazz;
            }
          };
      }
    }
    return null;
  }

  protected void getAllReferencedProjects(Collection<URL> libraryURLs, IProject [] projects) throws CoreException, MalformedURLException
  {
    for (int i = 0; i < projects.length; ++i)
    {
      IProject project = projects[i];
      if (project.exists() && project.isOpen())
      {
        IJavaProject referencedJavaProject = JavaCore.create(project);
        IContainer container = project.getWorkspace().getRoot().getFolder(referencedJavaProject.getOutputLocation());

        libraryURLs.add(new URL(URI.createFileURI(container.getLocation().toString() + "/").toString()));
        getAllReferencedProjects(libraryURLs, project.getDescription().getReferencedProjects());
        getAllReferencedProjects(libraryURLs, project.getDescription().getDynamicReferences());
      }
    }
  }
}
