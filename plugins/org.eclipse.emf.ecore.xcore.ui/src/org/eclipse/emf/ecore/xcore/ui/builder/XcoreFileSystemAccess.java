/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.builder;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.xcore.generator.XcoreGenModelGeneratorAdapterFactory;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;

import com.google.common.collect.Sets;

/**
 * This specialize class is needed so that EMF's generator can inform that builder 
 * that it generated a result with contents identical to the current contents 
 *and therefore didn't save it.
 */
public class XcoreFileSystemAccess extends EclipseResourceFileSystemAccess2 implements XcoreGenModelGeneratorAdapterFactory.FileSystemReadAccess
{
  private final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

  protected Set<IProject> projects = Sets.newHashSet();

  public void readFile(String file)
  {
    getCallBack().afterFileUpdate(root.getFile(new Path(file)));
  }
}
