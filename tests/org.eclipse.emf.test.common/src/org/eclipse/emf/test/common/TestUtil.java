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
 * $Id: TestUtil.java,v 1.6 2009/03/23 03:53:33 davidms Exp $
 */
package org.eclipse.emf.test.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.osgi.framework.Bundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.common";
  private static final String CLASS_FILE = "org/eclipse/emf/test/common/TestUtil.class";
  
  public static String getTestCommonDirectory()
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      try
      {
         return new File(FileLocator.toFileURL(EMFTestCommonPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
      }
      catch (Throwable t)
      {
        // Ignore
      }
    }

    URL url = ClassLoader.getSystemResource(CLASS_FILE);
    if (url != null)
    {
      URI uri = URI.createURI(url.toString());
      if (uri.isArchive())
      {
        // Returns the inner URI
        String authority = uri.authority();
        
        // Removing the !
        authority = authority.substring(0, authority.length()-1);
        
        uri = URI.createURI(authority);
      }
      
      if (uri.isFile())
      {
        File parentDir = new File(uri.toFileString()).getParentFile();
        while (parentDir != null && parentDir.isDirectory())
        {
          String name = parentDir.getName();
          if (name.equals(PLUGIN_ID) || name.startsWith(PLUGIN_ID + "_"))
          {
            return parentDir.getAbsolutePath(); 
          }
          parentDir = parentDir.getParentFile();
        }
      }
    }
    throw new RuntimeException("Unable to compute the " + PLUGIN_ID + " directory");
  }
  
  public static String getPluginDirectory(String pluginID)
  {
    try
    {
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        Bundle bundle = Platform.getBundle(pluginID);

        // A source bundle won't be installed by the platform at startup, so try installing it manually.
        //
        if (bundle == null && pluginID.endsWith(".source"))
        {
          String binaryPluginID = pluginID.substring(0, pluginID.length() - 7);
          Bundle binaryBundle = Platform.getBundle(binaryPluginID);
          String bundleLocation = binaryBundle.getLocation();
          int i = bundleLocation.lastIndexOf(binaryPluginID);
          if (i != -1)
          {
            bundleLocation = bundleLocation.substring(0, i) + pluginID + bundleLocation.substring(i + binaryPluginID.length());
          }
          bundle = binaryBundle.getBundleContext().installBundle(bundleLocation);
        }

        if (bundle != null)
        {
          File file = new File(FileLocator.toFileURL(bundle.getEntry("/")).getFile());
          if (file.isDirectory())
          {
            return file.getAbsolutePath();
          }
        }
      }
    }
    catch (Throwable t)
    {
      // Ignore
    }
    
    File pluginsDirectory = new File(getTestCommonDirectory()).getParentFile();
    File[] plugins = pluginsDirectory.listFiles();
    for (int i = 0, maxi = plugins.length; i < maxi; i++)
    {
      if (plugins[i].isDirectory())
      {
        String name = plugins[i].getName();
        if (name.equals(pluginID) || name.startsWith(pluginID + "_"))
        {
          return plugins[i].getAbsolutePath();
        }
      }
    }
    return null;
  }  
  
  public static boolean areEqual(List<?> list1, List<?> list2)
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
        in.close();
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
