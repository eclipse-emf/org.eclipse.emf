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
 * $Id: EMFTestBuildPlugin.java,v 1.1 2004/11/03 19:41:32 marcelop Exp $
 */
package org.eclipse.emf.test.build;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class EMFTestBuildPlugin 
extends Plugin
{
    private static EMFTestBuildPlugin instance;
    private static class Foo{};
    
    public EMFTestBuildPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestBuildPlugin getPlugin()
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
      path = path.substring(0, path.indexOf("org.eclipse.emf.test.build/") + "org.eclipse.emf.test.build/".length());
      return new File(path).getAbsolutePath();
    }
}
