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
 * $Id: TestUtil.java,v 1.6 2007/07/13 18:41:57 nickb Exp $
 */
package org.eclipse.emf.test.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.core.runtime.FileLocator;

public class TestUtil
{
  private static final String PLUGIN_ID = "org.eclipse.emf.test.build";
  private static final String CLASS_FILE = "org/eclipse/emf/test/build/TestUtil.class";

  private static final String COOKIE = "Cookie"; //$NON-NLS-1$
  private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded"; //$NON-NLS-1$
  private static final String CONTENT_TYPE = "Content-type"; //$NON-NLS-1$
  private static final String POST = "POST"; //$NON-NLS-1$
  private static final String EMPTY = ""; //$NON-NLS-1$

  public static String getPluginDirectory()
  {
    try
    {
      return new File(FileLocator.toFileURL(EMFTestBuildPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
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
  
  public static URLConnection getConn(String url, String method, boolean in, boolean out, String cookie) throws Exception
  {
    URL u = null;
    u = new URL(url);

    URLConnection conn = null;
    conn = u.openConnection();
    if (conn instanceof HttpsURLConnection)
    {
      HttpsURLConnection sconn = (HttpsURLConnection)conn;

      sconn.setRequestMethod(method);
  
      if (method.equals(POST))
      {
        sconn.setRequestProperty(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
      }
  
      if (!cookie.equals(EMPTY))
      {
        sconn.setRequestProperty(COOKIE, cookie);
      }
  
      sconn.setDoInput(in);
      sconn.setDoOutput(out);
  
      sconn.connect();
      return sconn;
    }
    else if (conn instanceof HttpURLConnection)
    {
      HttpURLConnection sconn = (HttpURLConnection)conn;
      sconn.setRequestMethod(method);
      
      if (method.equals(POST))
      {
        sconn.setRequestProperty(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
      }
  
      if (!cookie.equals(EMPTY))
      {
        sconn.setRequestProperty(COOKIE, cookie);
      }
  
      sconn.setDoInput(in);
      sconn.setDoOutput(out);
  
      sconn.connect();
      return sconn;
    }
    return conn;
  
  }

  public static String slurpStream(URLConnection conn) throws Exception
  {
    String ret = EMPTY;
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    char[] tmp = new char[8192];
    int r;
    while ((r = in.read(tmp, 0, 8192)) != -1)
    {
      ret += new String(tmp, 0, r);
    }

    in.close();
    return ret;
  }  
}
