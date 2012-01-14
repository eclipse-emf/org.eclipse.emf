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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.cheatsheets.CheatSheetsPlugin;


/**
 * <p>Action that creates a new Java project in workspace.</p>
 * @since 2.2.0
 */
public class NewJavaProjectAction extends NewProjectAction
{
  protected static final String SOURCE_FOLDER = "src";
  protected static final String OUTPUT_FOLDER = "bin";

  /**
   * Create a new Java project
   * @param projectName Name of the project
   * @param monitor Monitoring the action
   * @return Java project
   */
  @Override
  protected IProject createProject(String projectName, IProgressMonitor monitor) throws CoreException
  {
    monitor.beginTask(CheatSheetsPlugin.INSTANCE.getString("_UI_CreateJavaProject_message", new String []{ projectName }), 5);
    IProject project = super.createProject(projectName, new SubProgressMonitor(monitor, 1));
    if (project != null)
    {
      IProjectDescription description = project.getDescription();
      if (!description.hasNature(JavaCore.NATURE_ID))
      {
        IJavaProject javaProject = JavaCore.create(project);
        if (javaProject != null)
        {
          String[] natures = description.getNatureIds();
          String[] javaNatures = new String [natures.length + 1];
          System.arraycopy(natures, 0, javaNatures, 0, natures.length);
          javaNatures[natures.length] = JavaCore.NATURE_ID;
          description.setNatureIds(javaNatures);
          project.setDescription(description, new SubProgressMonitor(monitor, 1));

          IFolder sourceFolder = project.getFolder(SOURCE_FOLDER);
          if (!sourceFolder.exists())
          {
            sourceFolder.create(true, true, new SubProgressMonitor(monitor, 1));
          }

          javaProject.setOutputLocation(project.getFolder(OUTPUT_FOLDER).getFullPath(), new SubProgressMonitor(monitor, 1));
          IClasspathEntry[] entries = new IClasspathEntry []{
            JavaCore.newSourceEntry(sourceFolder.getFullPath()),
            JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER")) };
          javaProject.setRawClasspath(entries, new SubProgressMonitor(monitor, 1));
        }
      }
    }
    monitor.done();
    return project;
  }
}
