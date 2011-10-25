/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * IModelImporterWizard.java,v 1.8 2009/04/18 11:40:04 emerks Exp
 */
package org.eclipse.emf.importer.ui.contribution;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkingSet;


/**
 * @since 2.1.0
 */
public interface IModelImporterWizard extends INewWizard
{
  void setOriginalGenModelFile(IFile originalGenModel);
  IFile getOriginalGenModelFile();

  void setModelFile(IFile modelFile);
  IFile getModelFile();

  void setGenModelProjectLocation(IPath projectLocation);
  IPath getGenModelProjectLocation();

  void setGenModelProjectPath(IPath projectPath);
  IPath getGenModelProjectPath();

  void setGenModelContainerPath(IPath path);
  IPath getGenModelContainerPath();

  void setGenModelFileName(String fileName);
  String getGenModelFileName();
  
  List<String> getFileExtensions();

  /**
   * @since 3.3
   */
  void setWorkingSets(IWorkingSet[] workingSets);

  /**
   * @since 3.3
   */
  IWorkingSet[] getWorkingSets();
}