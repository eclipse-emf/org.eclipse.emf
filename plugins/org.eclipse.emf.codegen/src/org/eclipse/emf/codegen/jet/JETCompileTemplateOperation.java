/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JETCompileTemplateOperation.java,v 1.11 2007/06/12 20:56:05 emerks Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;


public class JETCompileTemplateOperation implements IWorkspaceRunnable 
{
  protected static final String JET_EXTENSION = "jet";

  protected IProject project;
  protected Collection<?> containers;
  protected List<Object> files = new ArrayList<Object>();
  protected boolean inBuild;

  /**
   * Creates an instance given the collection of resources.
   */
  public JETCompileTemplateOperation(IProject project, Collection<?> containers) throws CoreException
  {
    this.project = project;
    this.containers = containers;
    for (Object container : containers)
    {
      if (container instanceof IContainer)
      {
        consider((IContainer)container);
      }
      else 
      {
        consider(container.toString());
      }
    }
  }

  /**
   * Creates an instance given the collection of resources.
   */
  public JETCompileTemplateOperation(IProject project, Collection<?> containers, Collection<?> resources) throws CoreException
  {
    super();

    this.project = project;
    this.containers = containers;
    for (Object object : resources)
    {
      if (object instanceof IFile)
      {
        IFile file = (IFile)object;
        for (IContainer container = file.getParent(); container != null; container = container.getParent())
        {
          if (containers.contains(container))
          {
            consider(file);
            break;
          }
        }
      }
      else if (object instanceof IContainer) 
      {
        for (IContainer container = (IContainer)object; container != null; container = container.getParent())
        {
          if (containers.contains(container))
          {
            consider(container);
            break;
          }
        }
      }
    }
  }
  
  /**
   * Returns true if there are files to compile.
   */
  public boolean shouldCompile()
  {
    return !files.isEmpty();
  }

  /**
   * Adds the URI.
   */
  protected void consider(String uri) 
  {
    URI baseURI = URI.createURI(uri);
    URI localURI = CommonPlugin.asLocalURI(baseURI);
    if (localURI.isFile() && !localURI.isRelative())
    {
      File file = new File(localURI.toFileString());
      if (file.isDirectory() && !uri.endsWith("/"))
      {
        baseURI = URI.createURI(uri + "/");
      }
      consider(baseURI, localURI, new File(localURI.toFileString()));
    }
  }

  protected void consider(URI baseURI, URI localURI, File file) 
  {
    if (file.isDirectory())
    {
      File [] files = file.listFiles();
      for (int i = 0; i < files.length; ++i)
      {
        consider(baseURI, localURI, files[i]);
      }
    }
    else if (file.isFile() && file.getName().endsWith(JET_EXTENSION) && file.getName().indexOf('.') != -1)
    {
      files.add(URI.createFileURI(file.getAbsolutePath()).deresolve(localURI).resolve(baseURI));
    }
  }

  /**
   * Adds the file to {@link #files} the file ends with the {@link #JET_EXTENSION} extension.
   */
  protected void consider(IFile file) 
  {
    if (file.getFileExtension() != null && file.getFileExtension().endsWith(JET_EXTENSION)) 
    {
      files.add(file);
    }
  }

  /**
   * Considers all the files of a container and all its subcontainer.
   */
  protected void consider(IContainer container) throws CoreException 
  {
    IResource[] children = container.members();
    if (children != null) 
    {
      for (int i = 0; i < children.length; ++i) 
      {
        IResource resource = children[i];
        if (resource instanceof IFile) 
        {
          consider((IFile)resource);
        }
        else if (resource instanceof IContainer) 
        {
          consider((IContainer)resource);
        }
      }
    }
  }

  /**
   */
  public void run(IProgressMonitor progressMonitor) throws CoreException 
  {
    try 
    {
      progressMonitor.beginTask("", 3 * files.size());
      progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETCompilingTemplates_message"));

      IWorkspaceRoot workspaceRoot =  ResourcesPlugin.getWorkspace().getRoot();
      Collection<IProject> jetProjects = new HashSet<IProject>();

      HashSet<String> visitedRelativePaths = new HashSet<String>();
      for (Object file : files)
      {
        String fileName = file instanceof IFile ? ((IFile)file).getName() : file.toString();

        progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETCompile_message", new Object [] { fileName }));

        JETNature nature = JETNature.getRuntime(project);
        IContainer directory = nature.getJavaSourceContainer();

        if(!directory.exists())
        {
          project.getFolder(directory.getProjectRelativePath()).create(true, true, new SubProgressMonitor(progressMonitor, 1));
        }

        IPath filePath = file instanceof IFile ? ((IFile)file).getFullPath() : new Path(file.toString());
        List<Object> templateContainers = nature.getTemplateContainers();
        List<Object> templateSourceContainers = nature.getTemplateSourceContainers();

        String [] containerLocations = new String [templateContainers.size()];
        for (ListIterator<Object> j = templateContainers.listIterator(); j.hasNext(); )
        {
          Object container = j.next();
          if (container instanceof IContainer)
          {
            containerLocations[j.previousIndex()] = URI.createPlatformResourceURI(((IContainer)container).getFullPath().toString(), true).toString();
          }
          else
          {
            containerLocations[j.previousIndex()] = container.toString();
          }
        }

        for (Object container : templateSourceContainers)
        {
          IPath containerPath = container instanceof IContainer ? ((IContainer)container).getFullPath() : new Path(container.toString());
          if (containerPath.isPrefixOf(filePath))
          {
            String relativePath = filePath.removeFirstSegments(containerPath.segmentCount()).setDevice(null).toString();
            if (visitedRelativePaths.add(relativePath))
            {
              JETCompiler compiler =  new JETCompiler(containerLocations, relativePath);
              compiler.parse();
  
              StringWriter stringWriter = new StringWriter();
              compiler.generate(stringWriter);
  
              JETSkeleton skeleton = compiler.getSkeleton();
              if (skeleton.getClassName().equals("")) 
              {
                skeleton.setClassName(fileName.substring(0, fileName.indexOf('.')));
              }
              if (skeleton.getPackageName() != null) 
              {
                directory = getPackageContainer(directory, skeleton.getPackageName(), new SubProgressMonitor(progressMonitor, 1));
              }
              else
              {
                progressMonitor.worked(1);
              }
  
              IFile outputFile = 
                workspaceRoot.getFile(directory.getFullPath().append(skeleton.getClassName() + ".java"));
  
              progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETUpdate_message", new Object [] { fileName }));
  
              String encoding = outputFile.getCharset();
              String result = stringWriter.getBuffer().toString();
              byte [] bytes;
              try
              {
                bytes = encoding == null ? result.getBytes() : result.getBytes(encoding);
              }
              catch (UnsupportedEncodingException exception)
              {
                bytes = result.getBytes();
              }
              
              if (!outputFile.exists()) 
              {
                outputFile.create(new ByteArrayInputStream(bytes), true, progressMonitor);
              } 
              else 
              {
                boolean changed = true;
                try
                {
                  InputStream inputStream = outputFile.getContents();
                  byte [] oldBytes =  new byte[inputStream.available()];
                  inputStream.read(oldBytes);
                  inputStream.close();
                  changed = !Arrays.equals(oldBytes, bytes);
                }
                catch (IOException exception) 
                {
                  // Ignore
                }
  
                if (changed)
                {
                  if (outputFile.isReadOnly()) 
                  {
                    // This call should get the files checked out from version control if the project is a 'team' project.
                    //
                    IStatus status = ResourcesPlugin.getWorkspace().validateEdit(new IFile [] { outputFile }, null);
                    if (!status.isOK()) 
                    {
                      throw new CoreException(status);
                    }
                  }
                  outputFile.setContents(new ByteArrayInputStream(bytes), true, true, progressMonitor);
                }
              }
  
              jetProjects.add(outputFile.getProject());
  
              progressMonitor.worked(1);
  
              break;
            }
          }
        }
      }

      // Kick of a Java build if not already in a build.
      //
      if (!isInBuild()) 
      {
        for (IProject project : jetProjects)
        {
          progressMonitor.subTask
            (CodeGenPlugin.getPlugin().getString("_UI_JETJavaCompileProject_message", new Object [] { project.getFullPath() }));
          project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new SubProgressMonitor(progressMonitor, 1));
        }
      }
    }
    catch (JETException exception)
    {
      throw DiagnosticException.toCoreException(exception);
    }
    finally
    {
      progressMonitor.done();
    }
  }

  protected IContainer getPackageContainer(IContainer root, String packagename, IProgressMonitor monitor) throws CoreException 
  {
    for (StringTokenizer stringTokenizer = new StringTokenizer(packagename, "."); stringTokenizer.hasMoreTokens(); ) 
    {
      IFolder newContainer = root.getFolder(new Path(stringTokenizer.nextToken()));
      if (!newContainer.exists()) 
      {
        newContainer.create(true, true, monitor);
      }
      root = newContainer;
    }

    return root;
  }

  public boolean isInBuild() 
  {
    return inBuild;
  }

  public void setInBuild(boolean build) 
  {
    inBuild = build;
  }
}
