/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ant.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Utility classes with generic methods that may be used by more than one task.
 * @since 2.1.0
 */
public class Util
{
  /**
   * Removes the version number of all the subdirectories of a given directory.
   * The expected format of the directories name is <dirName>_<version> where version 
   * has at least from 1 to 4 fields, the first 3 being digits.  Examples: 
   * foo_2, foo_2.1, foo_2.1.0, foo_2.1.0.a,  
   * 
   *  
   * @param parentDir the parent of the directories that will have the version removed
   * @return the number of changed directories
   */
  public static int removeVersion(File parentDir)
  {
    if (parentDir == null || !parentDir.isDirectory())
    {
      return 0;
    }

    int counter = 0;
    File[] dirs = parentDir.listFiles();
    for (int i = 0; i < dirs.length; i++)
    {
      if (dirs[i].isDirectory())
      {
        String name = dirs[i].getName().replaceAll("_(\\d(\\.\\d){0,2}(\\.([\\w-])*)?)$", "");        
        if (!name.equals(dirs[i].getName()) && dirs[i].renameTo(new File(parentDir, name)))
        {
          counter++;
        }
      }
    }
    return counter;
  }

  /**
   * Writes the given content to the specifed file. 
   * @param file The file to be written or overwritten.
   * @param content
   * @throws IOException
   */
  public static void writeFile(File file, String content) throws IOException
  {
    if (!file.getParentFile().isDirectory())
    {
      file.getParentFile().mkdirs();
    }
    
    BufferedWriter out = null;
    try
    {
      out = new BufferedWriter(new FileWriter(file));
      out.write(content);
    }
    finally
    {
      if (out != null)
      {
        out.close();
      }
    }
  }
}