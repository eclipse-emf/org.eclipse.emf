/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: AntUtil.java,v 1.2 2007/06/04 18:49:09 emerks Exp $
 */
package org.eclipse.emf.test.tools;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class AntUtil
{
  public static void copyFile(File fromFile, File toFile, boolean overwrite)
  {
    Copy antCopyTask = new Copy();
    antCopyTask.setProject(new Project());
    antCopyTask.setTofile(toFile);
    FileSet fromDirFS = new FileSet();
    fromDirFS.setFile(fromFile);
    fromDirFS.setProject(antCopyTask.getProject());
    antCopyTask.addFileset(fromDirFS);
    antCopyTask.setOverwrite(overwrite);
    antCopyTask.execute();    
  }
  
  public static void copyFiles(File fromDir, File toDir, boolean overwrite)
  {
    Copy antCopyTask = new Copy();
    antCopyTask.setProject(new Project());
    antCopyTask.setTodir(toDir);
    FileSet fromDirFS = new FileSet();
    fromDirFS.setDir(fromDir);
    fromDirFS.setProject(antCopyTask.getProject());
    antCopyTask.addFileset(fromDirFS);
    antCopyTask.setOverwrite(overwrite);
    antCopyTask.execute();    
  }
  
  public static void runAnt(File script, String arguments) throws CoreException
  {
    AntRunner antRunner = new AntRunner();
    antRunner.setBuildFileLocation(script.getAbsolutePath());
    if (arguments != null) antRunner.setArguments(arguments);
    antRunner.run(new NullProgressMonitor());
  }  
}
