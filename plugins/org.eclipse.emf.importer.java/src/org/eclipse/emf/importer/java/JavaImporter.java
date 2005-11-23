/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: JavaImporter.java,v 1.3 2005/11/23 19:07:03 emerks Exp $
 */
package org.eclipse.emf.importer.java;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.java.builder.JavaEcoreBuilder;


/**
 * @since 2.1.0
 */
public class JavaImporter extends ModelImporter
{
  public String getID()
  {
    return "org.eclipse.emf.importer.java";
  }

  public boolean canImport()
  {
    IFile genModelFile = getGenModelFile();
    if (genModelFile != null)
    {
      IProject project = genModelFile.getProject();
      try
      {
        return project.hasNature(JavaCore.NATURE_ID);
      }
      catch (CoreException e)
      {
        JavaImporterPlugin.INSTANCE.log(e);
      }
    }
    return false;
  }

  protected IFile getGenModelFile()
  {
    IPath path = getGenModelPath();
    if (path != null)
    {
      return getWorkspaceRoot().getFile(path);
    }
    return null;
  }

  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    monitor.beginTask("", 2);
    monitor.subTask(JavaImporterPlugin.INSTANCE.getString("_UI_CreatingPackages_message"));

    JavaEcoreBuilder javaEcoreBuilder = new JavaEcoreBuilder(getGenModelFile(), getOriginalGenModel());
    javaEcoreBuilder.computeEPackages(monitor, this);
    return javaEcoreBuilder.getDiagnostic();
  }
  
  protected void adjustGenModel(Monitor monitor)
  {
    super.adjustGenModel(monitor);
    getGenModel().getForeignModel().add("@model");
  }  
}