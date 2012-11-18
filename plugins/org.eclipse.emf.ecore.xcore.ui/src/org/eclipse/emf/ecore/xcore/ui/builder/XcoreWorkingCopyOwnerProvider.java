/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.builder;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.common.types.access.jdt.WorkingCopyOwnerProvider;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.Pair;

import com.google.inject.Inject;

public class XcoreWorkingCopyOwnerProvider extends WorkingCopyOwnerProvider
{
  @Inject
  private IStorage2UriMapper storage2UriMapper;

  @Override
  protected boolean isOnClassPath(IJavaProject javaProject, IStorage storage)
  {
    // Return true if the storage is associated with this project, regardless of whether it's on the physical classpath.
    // That's because Xcore resources are not on the classpath until they're actually deployed in a jarred bundle.
    //
    IProject project = javaProject.getProject();
    Iterable<Pair<IStorage, IProject>> storages = storage2UriMapper.getStorages(storage2UriMapper.getUri(storage));
    for (Pair<IStorage, IProject> pair : storages)
    {
      if (pair.getFirst() == storage && pair.getSecond() == project)
      {
        return true;
      }
    }

    return false;
  }
}
