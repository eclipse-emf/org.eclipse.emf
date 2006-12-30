/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: TestUtil.java,v 1.5 2006/12/30 03:43:25 marcelop Exp $
 */
package org.eclipse.emf.test.edit;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.edit";
  private static final String CLASS_FILE = "org/eclipse/emf/test/edit/TestUtil.class";
  
  public static String getPluginDirectory()
  {
    try
    {
       return new File(FileLocator.toFileURL(EMFTestEditPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
    }
    catch (Throwable t)
    {
      // Ignore
    }

    URL url = ClassLoader.getSystemResource(CLASS_FILE);
    if (url != null)
    {
      String path = url.getPath();
      path = path.substring(0, path.indexOf(PLUGIN_ID));
      if (path.startsWith("file:"))
      {
        path = path.substring("file:".length());
      }
      File parentDir = new File(path);
      if (parentDir.isDirectory())
      {
        File[] files = parentDir.listFiles();
        for (int i = 0, maxi = files.length; i < maxi; i++)
        {
          if (files[i].isDirectory() && files[i].getName().startsWith(PLUGIN_ID))
          {
            return files[i].getAbsolutePath();
          }
        }
      }
    }

    return null;
  }
}
