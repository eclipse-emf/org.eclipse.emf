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
 * $Id: TestUtil.java,v 1.2 2005/02/15 20:19:27 bportier Exp $
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
  
  public static String getPluginDirectory()
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
}
