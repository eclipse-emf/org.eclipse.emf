/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMFTestToolsPlugin.java,v 1.2 2004/11/25 19:41:51 marcelop Exp $
 */
package org.eclipse.emf.test.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class EMFTestToolsPlugin 
extends Plugin
{
    private static EMFTestToolsPlugin instance;
    private static class Foo{};
    
    public EMFTestToolsPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestToolsPlugin getPlugin()
    {
        return instance;
    }

    public static String getPluginDirectory()
    {
      if (getPlugin() != null)
      {
        try
        {
            return new java.io.File(Platform.asLocalURL(getPlugin().getBundle().getEntry("/")).getFile()).toString();
        }
        catch (IOException e)
        {
        }
      }
      URL url = new Foo().getClass().getResource(".");
      String path = url.getPath();
      path = path.substring(0, path.indexOf("org.eclipse.emf.test.tools/") + "org.eclipse.emf.test.tools/".length());
      return new File(path).getAbsolutePath();
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
