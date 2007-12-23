/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: JavaImporter.java,v 1.6 2007/12/23 19:34:40 emerks Exp $
 */
package org.eclipse.emf.importer.java;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.java.builder.JavaEcoreBuilder;


/**
 * @since 2.1.0
 */
public class JavaImporter extends ModelImporter
{
  @Override
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
        if (project.hasNature(JavaCore.NATURE_ID))
        {
          return true;
        }
        else if (originalGenModel != null)
        {
          String modelDirectory = originalGenModel.getModelDirectory();
          if (modelDirectory != null)
          {
            IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(modelDirectory));
            if (folder.exists() && folder.getProject().hasNature(JavaCore.NATURE_ID))
            {
              return true;
            }
          }
        }
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
  
  @Override
  public EPackage getReferredEPackage(GenPackage genPackage)
  {
    return genPackage.getEcorePackage();
  }

  @Override
  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    monitor.beginTask("", 2);
    monitor.subTask(JavaImporterPlugin.INSTANCE.getString("_UI_CreatingPackages_message"));

    JavaEcoreBuilder javaEcoreBuilder = new JavaEcoreBuilder(getGenModelFile(), getOriginalGenModel());
    javaEcoreBuilder.computeEPackages(monitor, this);
    getReferencedGenPackages().clear();
    getReferencedGenPackages().addAll(javaEcoreBuilder.getUsedGenPackages());
    return javaEcoreBuilder.getDiagnostic();
  }
  
  @Override
  protected void adjustGenModel(Monitor monitor)
  {
    super.adjustGenModel(monitor);
    getGenModel().getForeignModel().add("@model");
  }  
}