/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.cheatsheets.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

import org.osgi.framework.Bundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import org.eclipse.emf.cheatsheets.CheatSheetsPlugin;


/**
 * <p>Action that copies a file from plugin to project.</p>
 * @since 2.2.0
 */
public class CopyFileFromPluginAction extends Action implements ICheatSheetAction
{
  protected String projectName; //Name of project
  protected String javaPackageName; //Name of Java package
  protected String sourceFile; //Source file Name - plugin + folder + filename
  protected IWorkspaceRoot root; //Root of workspace

  /**
   * Execution of the action
   * @param params Array of parameters - index 0: projectName, index 1: javaPackageName, index 2: sourceFile
   * @param manager Cheatsheet Manager
   */
  public void run(String[] params, ICheatSheetManager manager)
  {
    projectName = params[0];
    javaPackageName = params[1];
    sourceFile = params[2];
    root = ResourcesPlugin.getWorkspace().getRoot();
    run();
  }

  /**
   * Run the action
   */
  @Override
  public void run()
  {
    WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException
        {
          if (!isNullString(projectName))
          {
            IProject project = getProject(projectName, !isNullString(javaPackageName), monitor);
            copyFileToProject(project, javaPackageName, sourceFile, monitor);
          }
          else
          {
            copyFileToWorkspaceFolder(sourceFile);
          }
        }
      };
    try
    {
      runWithProgress(operation);
      return;
    }
    catch (Exception e)
    {
      CheatSheetsPlugin.INSTANCE.log(e);
    }
    notifyResult(false);
  }

  /**
   * Run with progress
   * @param runnable
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  protected void runWithProgress(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException
  {
    new ProgressMonitorDialog(Display.getCurrent().getActiveShell()).run(false, false, runnable);
  }

  /**
   * Get the project from workspace
   * @param projectName Name of project
   * @param isJavaProject Boolean if the project is a Java project
   * @param monitor Monitoring the action
   * @return Project with projectName
   * @throws CoreException
   */
  protected IProject getProject(String projectName, boolean isJavaProject, IProgressMonitor monitor) throws CoreException
  {
    monitor.beginTask(CheatSheetsPlugin.INSTANCE.getString("_UI_FindProject_message", new String []{ projectName }), 2);
    IProject project = null;
    if (isJavaProject)
    {
      NewJavaProjectAction newJavaProjectAction = new NewJavaProjectAction();
      project = newJavaProjectAction.createProject(projectName, new SubProgressMonitor(monitor, 1));
    }
    else
    {
      NewProjectAction newProjectAction = new NewProjectAction();
      project = newProjectAction.createProject(projectName, new SubProgressMonitor(monitor, 1));
    }
    monitor.done();
    return project;
  }

  /**
   * Copy a file from plugin to project
   * @param project Target Project
   * @param javaPackageName Java package name
   * @param sourceFile Source file name - plugin id + folders + file
   * @param monitor Monitoring the action
   * @return Target file
   * @throws CoreException
   */
  protected IFile copyFileToProject(IProject project, String javaPackageName, String sourceFile, IProgressMonitor monitor) throws CoreException
  {
    monitor.beginTask(CheatSheetsPlugin.INSTANCE.getString("_UI_CopyFileToProject_message", new String []{ projectName }), 2);
    String targetFileName = sourceFile.substring(sourceFile.lastIndexOf('/') + 1);
    IPath targetFolderPath = getFolderPath(project, javaPackageName, monitor);
    IFile targetFile = project.getFile(targetFolderPath.append(targetFileName));
    if (!targetFile.exists())
    {
      String sourcePluginId = sourceFile.substring(0, sourceFile.indexOf('/'));
      String sourceFilePath = sourceFile.substring(sourceFile.indexOf('/'));
      Bundle bundle = Platform.getBundle(sourcePluginId);
      try
      {
        InputStream in = FileLocator.openStream(bundle, new Path(sourceFilePath), false);
        targetFile.create(in, true, new SubProgressMonitor(monitor, 1));
        in.close();
      }
      catch (IOException e)
      {
        CheatSheetsPlugin.INSTANCE.log(e);
      }
    }
    monitor.done();
    notifyResult(targetFile.exists());
    return targetFile;
  }

  protected void copyFileToWorkspaceFolder(String sourceFile) throws CoreException
  {
    String targetFileName = sourceFile.substring(sourceFile.lastIndexOf('/') + 1);
    String sourcePluginId = sourceFile.substring(0, sourceFile.indexOf('/'));
    String sourceFilePath = sourceFile.substring(sourceFile.indexOf('/'));
    Bundle bundle = Platform.getBundle(sourcePluginId);

    File file = new File(root.getLocation().toPortableString() + "/" + targetFileName);
    if (!file.exists())
    {
      try
      {
        InputStream in = FileLocator.openStream(bundle, new Path(sourceFilePath), false);
        FileOutputStream out = new FileOutputStream(file);
        int aChar = 0;
        while ((aChar = in.read()) != -1)
        {
          out.write(aChar);
        }
        in.close();
        out.close();
      }
      catch (IOException e)
      {
        CheatSheetsPlugin.INSTANCE.log(e);
      }
    }
    notifyResult(file.exists());
  }

  /**
   * Get folder path derived from Java package name
   * @param project Target project
   * @param javaPackageName Java package name
   * @param monitor Monitor the action
   * @return Path of the folder
   * @throws CoreException
   */
  protected IPath getFolderPath(IProject project, String javaPackageName, IProgressMonitor monitor) throws CoreException
  {
    IPath folderPath = project.getProjectRelativePath();
    if (!isNullString(javaPackageName))
    {
      folderPath = folderPath.append(NewJavaProjectAction.SOURCE_FOLDER);
      StringTokenizer javaPackageToken = new StringTokenizer(javaPackageName, ".");
      IFolder folder = null;
      while (javaPackageToken.hasMoreElements())
      {
        folderPath = folderPath.append(javaPackageToken.nextToken());
        folder = project.getFolder(folderPath);
        if (!folder.exists())
        {
          folder.create(true, true, new SubProgressMonitor(monitor, 1));
        }
      }
    }
    return folderPath;
  }

  protected boolean isNullString(String value)
  {
    return value == null || value.length() == 0;
  }
}
