/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: TestUtil.java,v 1.4 2005/02/21 20:07:30 marcelop Exp $
 */
package org.eclipse.emf.test.performance;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.Platform;

/**
 * @author marcelop
 */
public class TestUtil
{
  private static class Foo{};
  private static int runningUnderEclipseFlag = -1;
  
  public final static String getPluginDirectory()
  {
    try
    {
      if (EMFTestPerformancePlugin.getPlugin() != null)
      {
        return new java.io.File(Platform.asLocalURL(EMFTestPerformancePlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
      }
    }
    catch (Throwable t)
    {
    }
    
    URL url = new Foo().getClass().getResource(".");
    String path = url.getPath();
    path = path.substring(0, path.indexOf("org.eclipse.emf.test.performance/") + "org.eclipse.emf.test.performance/".length());
    return new File(path).getAbsolutePath();
  }
  
  
  public final static boolean isRunningUnderEclipse()
  {
    if (runningUnderEclipseFlag == -1)
    {
      runningUnderEclipseFlag = 0; 
      try
      {
        if (Platform.isRunning()) runningUnderEclipseFlag = 1;
      }
      catch (Throwable t)
      {
      }
    }
    return (runningUnderEclipseFlag == 1);
  }
}
