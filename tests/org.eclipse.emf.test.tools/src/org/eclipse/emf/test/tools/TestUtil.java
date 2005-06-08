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
 * $Id: TestUtil.java,v 1.7 2005/06/08 06:24:42 nickb Exp $
 */
package org.eclipse.emf.test.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.tools";
  private static final String CLASS_FILE = "org/eclipse/emf/test/tools/TestUtil.class";
  
  public static String getPluginDirectory()
  {
    try
    {
       return new File(Platform.asLocalURL(EMFTestToolsPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
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
  
  public static String getPluginDirectory(String pluginID)
  {
    try
    {
      if (Platform.isRunning())
      {
        return new java.io.File(Platform.asLocalURL(Platform.getBundle(pluginID).getEntry("/")).getFile()).toString();
      }
    }
    catch (Throwable t)
    {
    }
    
    File parentDirectory = new File(getPluginDirectory());
    File[] plugins = parentDirectory.listFiles();
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
  
  public static String readFile(File file, boolean useSystemLineSeparator)
  {    
    StringBuffer stringBuffer = new StringBuffer();

    try
    {
      BufferedReader in = new BufferedReader(new FileReader(file));
      String str = null;
      
      try
      {
        String nl = useSystemLineSeparator ? System.getProperties().getProperty("line.separator") : "\n";
        while ((str = in.readLine()) != null)
        {
          stringBuffer.append(str).append(nl);
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
  
  public static void copyFiles(File fromDir, File toDir, boolean overwrite)
  {
    Copy antCopyTask = new Copy();
    antCopyTask.setProject(new Project());
    antCopyTask.setTodir(toDir);
    FileSet fromDirFS = new FileSet();
    fromDirFS.setDir(fromDir);
    antCopyTask.addFileset(fromDirFS);
    antCopyTask.setOverwrite(overwrite);
    antCopyTask.execute();    
  }
  
  public static void runAnt(File script, String arguments) throws CoreException
  {
    AntRunner antRunner = new AntRunner();
    antRunner.setBuildFileLocation(script.getAbsolutePath());
    if (arguments != null) antRunner.setArguments(arguments);
    antRunner.run(new NullProgressMonitor());
  }  
}
