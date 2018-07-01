/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.ui.builder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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

  @Override
  protected boolean hasContentsChanged(IFile file, InputStream newContent)
  {
    BufferedInputStream oldContent = null;
    try
    {
      oldContent = new BufferedInputStream(file.getContents());
      byte[] newBytes = new byte[4048];
      byte[] oldBytes = new byte[4048];
      while (true)
      {
        int newBytesRead = newContent.read(newBytes);
        int oldBytesRead = oldContent.read(oldBytes);
        
        if (newBytesRead != oldBytesRead)
        {
          return true;
        }
        else
        {
          for (int i = 0; i < newBytesRead; ++i)
          {
            if (newBytes[i] != oldBytes[i])
            {
              return true;
            }
          }

          if (newBytesRead < 4048)
          {
            return false;
          }
        }
      }
    }
    catch (CoreException e)
    {
      return true;
    }
    catch (IOException e)
    {
      return true;
    }
    finally
    {
      if (oldContent != null)
      {
        try
        {
          oldContent.close();
        }
        catch (IOException e)
        {
          // ignore
        }
      }
    }
  }
}
