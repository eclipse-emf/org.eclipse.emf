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
 * $Id: TestUtil.java,v 1.4 2006/02/21 06:26:31 marcelop Exp $
 */
package org.eclipse.emf.test.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.xml";
  private static final String CLASS_FILE = "org/eclipse/emf/test/xml/TestUtil.class";
  
  public static String getPluginDirectory()
  {
    try
    {
       return new File(FileLocator.toFileURL(EMFTestXMLPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
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
  
  public static String readFile(File file)
  {    
    StringBuffer stringBuffer = new StringBuffer();

    try
    {
      BufferedReader in = new BufferedReader(new FileReader(file));
      String str = null;
      
      try
      {
        while ((str = in.readLine()) != null)
        {
          stringBuffer.append(str).append("\n");
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
      stringBuffer.deleteCharAt(length - 1);
    }
    return stringBuffer.toString();
  }  
}
