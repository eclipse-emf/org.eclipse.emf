/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.parser;

import java.io.File;
import java.util.StringTokenizer;


/**
 */
public class Util
{
  public final static String QUOTE = "\"";
  public final static int PATH_NOT_VALID = 0;
  public final static int DIRECTORY_NOT_FOUND = 1;
  public final static int PATH_OK = 2;
  public final static int NOT_A_DIRECTORY = 3;

  public static int isValidPath(String pathname)
  {
    File testDirectory;
    try
    {
      testDirectory = new File(pathname);
    }
    catch (NullPointerException e)
    {
      return PATH_NOT_VALID;
    }
    boolean exists = false;
    try
    {
      exists = testDirectory.exists();
    }
    catch (SecurityException e)
    {
      return PATH_NOT_VALID;
    }
    boolean isDirectory = false;
    try
    {
      isDirectory = testDirectory.isDirectory();
    }
    catch (SecurityException e)
    {
      return PATH_NOT_VALID;
    }
    if (!isDirectory)
    {
      if (exists)
      {
        return NOT_A_DIRECTORY;
      }
      else
      {
        return DIRECTORY_NOT_FOUND;
      }
    }
    return PATH_OK;
  }

  static public boolean createDirectory(String dirName)
  {
    try
    {
      File newDir = new File(dirName);
      if ((newDir.mkdirs()))
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    catch (NullPointerException e)
    {
      return false;
    }
    catch (SecurityException e)
    {
      return false;
    }
  }

  static public String trimQuotes(String str)
  {
    int f_ind = str.indexOf(QUOTE);
    int l_ind = str.lastIndexOf(QUOTE);
    if (f_ind == -1)
    {
      return str;
    }
    else if (f_ind == l_ind)
    {
      return str.substring(f_ind + 1);
    }
    else
    {
      return str.substring(f_ind + 1, l_ind);
    }
  }

  static public String getType(String str)
  {
    return getWord(str, 1);
  }

  static public String getName(String str)
  {
    int ind_end = str.lastIndexOf(QUOTE);
    if (ind_end == -1)
    {
      return null;
    }
    String temp = str.substring(0, ind_end);
    int ind_start = temp.lastIndexOf(QUOTE);
    if (ind_start == -1)
    {
      return null;
    }
    String name = str.substring(ind_start + 1, ind_end);
    if (name.indexOf("$UNNAMED") != -1)
    {
      name = "";
    }
    return name;
  }

  static public String getWord(String str, int n)
  {
    StringTokenizer st = new StringTokenizer(str);
    int i = 0;
    while (st.hasMoreTokens())
    {
      i++;
      String tok = st.nextToken();
      if (i == n)
      {
        return tok;
      }
    }
    return null;
  }

  static public String updateFileName(String fileName, String dilimiter)
  {
    String name = "";
    while (true)
    {
      int ind = fileName.indexOf(dilimiter);
      if (ind == -1)
      {
        name += fileName;
        break;
      }
      name += (fileName.substring(0, ind) + File.separator);
      fileName = fileName.substring(ind + dilimiter.length());
    }
    return name;
  }
}
