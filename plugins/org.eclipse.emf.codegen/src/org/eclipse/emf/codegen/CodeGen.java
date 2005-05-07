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
 * $Id: CodeGen.java,v 1.4 2005/05/07 09:49:25 davidms Exp $
 */
package org.eclipse.emf.codegen;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;


/**
 * This class implements some reusable static utility methods.
 * It also implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class CodeGen implements IPlatformRunnable 
{
  /**
   * This is a progress monitor that prints the progress information to a stream.
   */
  public static class StreamProgressMonitor extends NullProgressMonitor
  {
    protected PrintStream printStream;

    public StreamProgressMonitor(PrintStream printStream)
    {
      this.printStream = printStream;
    }

    public void beginTask(String name, int totalWork)
    {
      if (name != null && name.length() != 0)
      {
        printStream.println(">>> " + name);
      }
      super.beginTask(name, totalWork);
    }

    public void setTaskName(String name)
    {
      if (name != null && name.length() != 0)
      {
        printStream.println("<>> " + name);
      }
      super.setTaskName(name);
    }

    public void subTask(String name)
    {
      if (name != null && name.length() != 0)
      {
        printStream.println(">>  " + name);
      }
      super.subTask(name);
    }
  }

  /**
   * This is used to print text via a progress monitor, by delegating to {@link IProgressMonitor#subTask subTask}.
   * It can be safely passed around among short-running methods, instead of SubProgressMonitors, without worrying
   * about beginning, ending, or counting ticks.
   */
  public static class ProgressMonitorPrinter
  {
    protected IProgressMonitor progressMonitor;
    
    public ProgressMonitorPrinter(IProgressMonitor progressMonitor)
    {
      this.progressMonitor = progressMonitor;
    }

    public void print(String message)
    {
      progressMonitor.subTask(message);
    }
  }

  public static IContainer findOrCreateContainer
    (IPath path, boolean forceRefresh, IPath localLocation, IProgressMonitor progressMonitor) throws CoreException
  {
    String projectName = path.segment(0);
    IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
    projectDescription.setLocation(localLocation);
    return findOrCreateContainer(path, forceRefresh, projectDescription, progressMonitor);
  }

  public static IContainer findOrCreateContainer
    (IPath path, boolean forceRefresh, IProjectDescription projectDescription, IProgressMonitor progressMonitor) throws CoreException
  {
    try
    {
      String projectName = path.segment(0);
      progressMonitor.beginTask("", path.segmentCount() + 3);
      progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_ExaminingProject_message", new Object [] { projectName }));
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IProject project = workspace.getRoot().getProject(path.segment(0));

      if (forceRefresh)
      {
        project.refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(progressMonitor, 1));
      }
      else
      {
        progressMonitor.worked(1);
      }

      if (!project.exists())
      {
        project.create(projectDescription, new SubProgressMonitor(progressMonitor, 1));
        project.open(new SubProgressMonitor(progressMonitor, 1));
      }
      else
      {
        project.open(new SubProgressMonitor(progressMonitor, 2));
      }

      IContainer container = project;
      for (int i = 1, length = path.segmentCount(); i < length; ++ i)
      {
        IFolder folder = container.getFolder(new Path(path.segment(i)));
        if (!folder.exists())
        {
          folder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
        }
        else
        {
          progressMonitor.worked(1);
        }

        container = folder;
      }

      return container;
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public static List getClasspathPaths(String pluginID) throws JETException
  {
    List result = new ArrayList();
    try
    {
      Bundle bundle = Platform.getBundle(pluginID);
      String requires = (String)bundle.getHeaders().get(Constants.BUNDLE_CLASSPATH);
      if (requires == null)
      {
        requires = ".";
      }
      ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_CLASSPATH, requires);
      if (elements != null)
      {
        for (int i = 0, count = 0; i < elements.length; ++i)
        {
          ManifestElement element = elements[i];
          String value = element.getValue();
          if (".".equals(value))
          {
            value = "/";
          }
          try
          {
            URL url = bundle.getEntry(value);
            if (url != null)
            {
              URL resolvedURL = Platform.resolve(url);
              String resolvedURLString = resolvedURL.toString();
              if (resolvedURLString.endsWith("!/"))
              {
                resolvedURLString = resolvedURL.getFile();
                resolvedURLString = resolvedURLString.substring(0,resolvedURLString.length() - "!/".length());
              }
              if (resolvedURLString.startsWith("file:"))
              {
                result.add(resolvedURLString.substring("file:".length()));
              }
              else
              {
                result.add(Platform.asLocalURL(url).getFile());
              }
            }
          }
          catch (IOException exception)
          {
            throw new JETException(exception);
          }
          break;
        }
      }
    }
    catch (BundleException exception)
    {
      throw new JETException(exception);
    }
    return result;
  }
  
  public static void addClasspathEntries(Collection classpathEntries, String variableName, String pluginID) throws JETException
  {
    for (ListIterator i = getClasspathPaths(pluginID).listIterator(); i.hasNext(); )
    {
      IPath path = new Path((String)i.next());
      if (variableName == null)
      {
        classpathEntries.add(JavaCore.newLibraryEntry(path, null, null));
      }
      else
      {
        String mangledName = variableName + (i.previousIndex() == 0 ? "" : "_" + i.previousIndex());
        try
        {
          JavaCore.setClasspathVariable(mangledName, path, null);
        }
        catch (JavaModelException exception)
        {
          throw new JETException(exception);
        } 
        classpathEntries.add(JavaCore.newVariableEntry(new Path(mangledName), null, null));
      }
    }
  }

  public static void addClasspathEntries(Collection classpathEntries, String pluginID) throws Exception
  {
    addClasspathEntries(classpathEntries, null, pluginID);
  }
  
  /**
   * This creates an instance.
   */
  public CodeGen() 
  {
  }

  /**
   * This is called with the command line arguments of a headless workbench invocation.
   */
  public Object run(Object object) 
  {
    try
    {
      final String[] arguments = (String[])object;
      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRunnable runnable =
        new IWorkspaceRunnable()
        {
          public void run(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              String templateFile = arguments[0];
              File file = new File(templateFile);
              if (file.exists())
              {
                templateFile = file.getAbsoluteFile().toURL().toString();
              }
              IPath targetPath = new Path(new File(arguments[1]).getAbsolutePath());
              progressMonitor.beginTask("", 5);
              progressMonitor.subTask
                (CodeGenPlugin.getPlugin().getString("_UI_CompilingTemplate_message", new Object [] { templateFile }));

              JControlModel jControlModel = arguments.length > 2 ? new JControlModel(arguments[2]) : null;
              progressMonitor.worked(1);

              progressMonitor.subTask
                (CodeGenPlugin.getPlugin().getString("_UI_ParsingTemplate_message", new Object [] { templateFile }));
              JETCompiler jetCompiler = new JETCompiler(templateFile);
              jetCompiler.parse();
              progressMonitor.worked(1);

              progressMonitor.subTask
                (CodeGenPlugin.getPlugin().getString("_UI_GeneratingJava_message", new Object [] { templateFile }));
              ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
              jetCompiler.generate(outputStream);
              InputStream contents = new ByteArrayInputStream(outputStream.toByteArray());
              progressMonitor.worked(1);

              IPath projectTargetPath = new Path("/Result/" + jetCompiler.getSkeleton().getPackageName().replace('.','/'));

              IContainer container = 
                CodeGen.findOrCreateContainer(projectTargetPath, true, targetPath, new SubProgressMonitor(progressMonitor, 1));
              IFile targetFile = container.getFile(new Path(jetCompiler.getSkeleton().getClassName() + ".java"));

              progressMonitor.subTask
                (CodeGenPlugin.getPlugin().getString("_UI_Updating_message", new Object [] { targetFile.getLocation() }));
              if (targetFile.exists())
              {
                if (jControlModel != null)
                {
                  JMerger jMerger = new JMerger();
                  jMerger.setControlModel(jControlModel);
                  jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(outputStream.toString()));
                  jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForURI(targetPath.toString()));
                  jMerger.merge();
  
                  InputStream mergedContents = new ByteArrayInputStream(jMerger.getTargetCompilationUnitContents().getBytes());
                  targetFile.setContents(mergedContents, true, true, new SubProgressMonitor(progressMonitor, 1));
                }
                else
                {
                  targetFile.setContents(contents, true, true, new SubProgressMonitor(progressMonitor, 1));
                }
              }
              else
              {
                targetFile.create(contents, true, new SubProgressMonitor(progressMonitor, 1));
              }
            }
            catch (java.net.MalformedURLException exception)
            {
              System.err.println(CodeGenPlugin.getPlugin().getString("_UI_BadURL_message", new Object [] { arguments[0] }));
              exception.printStackTrace();
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };
      workspace.run(runnable, new StreamProgressMonitor(System.out));
      return new Integer(0);
    }
    catch (Exception exception)
    {
      System.err.println(CodeGenPlugin.getPlugin().getString("_UI_UsageArguments_message"));
      exception.printStackTrace();
      return new Integer(1);
    }
  }
}
