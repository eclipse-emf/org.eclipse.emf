/**
 * <copyright>
 *
 * Copyright (c) 2004-2005 IBM Corporation and others.
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
 * $Id: RemoveVersionTask.java,v 1.2 2005/02/10 22:11:51 marcelop Exp $
 */
package org.eclipse.emf.ant.taskdefs;

import java.io.File;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.ant.util.Util;


/**
 * <p>
 * Removes the version of all the subdirectories of a given directory.  See
 * {@link Util#removeVersion(File)} for further details.
 * This task may be usefull when generating code for which the defined model (a
 * Rose file for example) depends on directory names to find its dependencies.
 * </p>
 * <p>
 * If this task is executed by a Eclipse driver with the <b>org.eclipse.emf.ant</b> 
 * plugin, it is neither necessary to use Ant's task <tt>TaskDef</tt> to declare this 
 * task in a script nor to change the Ant's runtime classpath.
 * </p>
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * &lt;emf.RemoveVersion parentDir=&quot;c:\eclipse\plugins&quot;/&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public class RemoveVersionTask extends EMFTask
{
  private File parentDir;

  public void setParentDir(File dir)
  {
    parentDir = dir;
  }

  protected void checkAttributes() throws BuildException
  {
    assertTrue("The attribute 'parentDir' must indicate a valid directory.", parentDir != null && parentDir.isDirectory());
    assertTrue("You must have read and write access to " + parentDir.getAbsolutePath() + ".", parentDir.canRead() && parentDir.canWrite());
  }

  protected void doExecute() throws Exception
  {
    Util.removeVersion(parentDir);
  }
}