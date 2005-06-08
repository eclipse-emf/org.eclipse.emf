/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: CodeGen.java,v 1.7 2005/06/08 06:15:57 nickb Exp $
 */
package org.eclipse.emf.codegen;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.emf.codegen.util.CodeGenUtil;


/**
 * This class implements some reusable static utility methods.
 * It also implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class CodeGen implements IPlatformRunnable 
{
  /**
   * This is a progress monitor that prints the progress information to a stream.
   * @deprecated As of EMF 2.1.0, moved to {@link CodeGenUtil.StreamProgressMonitor CodeGenUtil}.
   */
  public static class StreamProgressMonitor extends CodeGenUtil.StreamProgressMonitor
  {
    public StreamProgressMonitor(PrintStream printStream)
    {
      super(printStream);
    }
  }

  /**
   * @deprecated As of EMF 2.1.0, moved to {@link CodeGenUtil#findOrCreateContainer(IPath, boolean, IPath, IProgressMonitor) CodeGenUtil}.
   */
  public static IContainer findOrCreateContainer
    (IPath path, boolean forceRefresh, IPath localLocation, IProgressMonitor progressMonitor) throws CoreException
  {
    return CodeGenUtil.findOrCreateContainer(path, forceRefresh, localLocation, progressMonitor);
  }

  /**
   * @deprecated As of EMF 2.1.0, moved to {@link CodeGenUtil#findOrCreateContainer(IPath, boolean, IProjectDescription, IProgressMonitor) CodeGenUtil}.
   */
  public static IContainer findOrCreateContainer
    (IPath path, boolean forceRefresh, IProjectDescription projectDescription, IProgressMonitor progressMonitor) throws CoreException
  {
    return CodeGenUtil.findOrCreateContainer(path, forceRefresh, projectDescription, progressMonitor);
  }

  /**
   * @deprecated As of EMF 2.1.0, moved to {@link CodeGenUtil#getClasspathPaths CodeGenUtil}.
   */
  public static List getClasspathPaths(String pluginID) throws JETException
  {
    return CodeGenUtil.getClasspathPaths(pluginID);
  }

  /**
   * @deprecated As of EMF 2.1.0, moved to {@link CodeGenUtil#addClasspathEntries(Collection, String, String) CodeGenUtil}.
   */
  public static void addClasspathEntries(Collection classpathEntries, String variableName, String pluginID) throws JETException
  {
    CodeGenUtil.addClasspathEntries(classpathEntries, variableName, pluginID);
  }

  /**
   * @deprecated As of EMF 2.1.0, moved to {@link CodeGenUtil#addClasspathEntries(Collection, String) CodeGenUtil}.
   */
  public static void addClasspathEntries(Collection classpathEntries, String pluginID) throws Exception
  {
    CodeGenUtil.addClasspathEntries(classpathEntries, pluginID);
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
                CodeGenUtil.findOrCreateContainer(projectTargetPath, true, targetPath, new SubProgressMonitor(progressMonitor, 1));
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
      workspace.run(runnable, new CodeGenUtil.StreamProgressMonitor(System.out));
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
