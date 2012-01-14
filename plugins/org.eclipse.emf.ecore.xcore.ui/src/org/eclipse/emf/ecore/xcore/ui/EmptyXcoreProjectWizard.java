/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.ecore.ui.EmptyProjectWizard;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.ui.XtextProjectHelper;

public class EmptyXcoreProjectWizard extends EmptyProjectWizard
{
  @Override
  public void modifyWorkspace(IProgressMonitor progressMonitor) throws CoreException, UnsupportedEncodingException, IOException
  {
    super.modifyWorkspace(progressMonitor);
    IProjectDescription projectDescription = project.getDescription();
    String[] natureIds = projectDescription.getNatureIds();
    String[] newNatureIds = new String [natureIds.length + 1];
    System.arraycopy(natureIds, 0, newNatureIds, 0, natureIds.length);
    newNatureIds[natureIds.length] = XtextProjectHelper.NATURE_ID;
    projectDescription.setNatureIds(newNatureIds);
    project.setDescription(projectDescription, progressMonitor);
    
    IJavaProject javaProject = JavaCore.create(project);
        IClasspathEntry[] classpath = javaProject.getRawClasspath();
    IClasspathEntry[] newClasspath = new IClasspathEntry[classpath.length + 1];
    for (int i = 0, index = 0, length = newClasspath.length; index < length; ++i, ++index)
    {
      newClasspath[index] = classpath[i];
      if (classpath[i].getEntryKind() == IClasspathEntry.CPE_SOURCE)
      {
        IPath path = classpath[i].getPath();
        IPath srcGenPath = path.removeLastSegments(1).append(path.lastSegment() + "-gen");
        IClasspathEntry srcGen = JavaCore.newSourceEntry(srcGenPath);
        CodeGenUtil.EclipseUtil.findOrCreateContainer(srcGenPath, true, genModelProjectLocation, progressMonitor);
        newClasspath[++index] = srcGen;
      }
    }
    javaProject.setRawClasspath(newClasspath, progressMonitor);
  }

  @Override
  protected String[] getRequiredBundles()
  {
    return new String[] { "org.eclipse.emf.ecore", "org.eclipse.xtext.xbase.lib" };
  }

}
