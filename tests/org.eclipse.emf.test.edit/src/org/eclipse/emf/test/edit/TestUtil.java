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
 * $Id: TestUtil.java,v 1.1 2005/01/05 20:42:49 marcelop Exp $
 */
package org.eclipse.emf.test.edit;

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
      if (EMFTestEditPlugin.getPlugin() != null)
      {
        return new java.io.File(Platform.asLocalURL(EMFTestEditPlugin.getPlugin().getBundle().getEntry("/")).getFile()).toString();
      }
    }
    catch (Throwable t)
    {
    }
    
    URL url = new Foo().getClass().getResource(".");
    String path = url.getPath();
    path = path.substring(0, path.indexOf("org.eclipse.emf.test.edit/") + "org.eclipse.emf.test.edit/".length());
    return new File(path).getAbsolutePath();
  }
}
