/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: TestUtil.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;

/**
 * @author marcelop
 */
public class TestUtil
{
  private static class Foo{};
  
  public static String getPluginDirectory()
  {
    try
    {
      if (EMFTestXMLPlugin.getPlugin() != null)
      {
        return new java.io.File(Platform.asLocalURL(EMFTestXMLPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
      }
    }
    catch (Throwable t)
    {
    }
    
    URL url = new Foo().getClass().getResource(".");
    String path = url.getPath();
    path = path.substring(0, path.indexOf("org.eclipse.emf.test.xml/") + "org.eclipse.emf.test.xml/".length());
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
