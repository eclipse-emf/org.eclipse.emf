/**
 * <copyright>
 *
 * Copyright (c) 2004-2005 IBM Corporation and others.
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
 * $Id: TestUtil.java,v 1.5 2006/02/21 06:26:27 marcelop Exp $
 */
package org.eclipse.emf.test.core;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.core";
  private static final String CLASS_FILE = "org/eclipse/emf/test/core/TestUtil.class";
  
  public static String getPluginDirectory()
  {
    try
    {
       return new File(FileLocator.toFileURL(EMFTestCorePlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
    }
    catch (Throwable t)
    {
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
  
  public static boolean areEqual(List list1, List list2)
  {
    if (list1 == null) return list2 == null;
    if (list2 == null) return false; 

    int size = list1.size(); 
    if (size != list2.size())
    {
      return false;
    }
    
    for(int i=0; i<size; i++)
    {
      if (list1.get(i) != list2.get(i))
      {
        if (list1.get(i) == null || list2.get(i) == null)
        {
          return false;
        }
        else if (!list1.get(i).equals(list2.get(i)))
        {
          return false; 
        }
      }
    }
    
    return true;  
  } 

  public static boolean isPlatformRunning()
  {
    try
    {
      return org.eclipse.core.runtime.Platform.isRunning(); 
    }
    catch (Throwable t)
    {
    }
    
    return false;
  }
}
