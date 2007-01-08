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
 * $Id: TestUtil.java,v 1.1 2007/01/08 00:22:46 marcelop Exp $
 */
package org.eclipse.emf.test.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.examples";
  private static final String CLASS_FILE = "org/eclipse/emf/test/examples/TestUtil.class";
  
  public static String getPluginDirectory()
  {
    try
    {
       return new File(FileLocator.toFileURL(EMFTestExamplesPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
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
  
  public static String readFile(File file, boolean useSystemLineSeparator)
  {    
    StringBuffer stringBuffer = new StringBuffer();
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(file));
      try
      {
        int size = 0;
        char[] buff = new char[512];
        while ((size = in.read(buff)) >= 0)
        {
          stringBuffer.append(buff, 0, size);
        }        
      }
      finally
      {
        if (in != null)
        {
          in.close();
        }
      }      
    }
    catch(IOException exception)
    {
      throw new RuntimeException(exception);
    }
    
    int length = stringBuffer.length();
    if(length > 0)
    {
      String nl = useSystemLineSeparator ? System.getProperties().getProperty("line.separator") : "\n";
      return stringBuffer.toString().replaceAll("\\r\\n", "\n").replaceAll("[\\n|\\r]", nl);
    }
    return stringBuffer.toString();
  }
  
  public static void delete(File file)
  {
    if (file.isDirectory())
    {
      File[] children = file.listFiles();
      for (int i = 0, maxi = children.length; i < maxi; i++)
      {
        delete(children[i]);
      }
    }
    
    if (file.exists())
    {
      file.delete();
    }
  }  
}
