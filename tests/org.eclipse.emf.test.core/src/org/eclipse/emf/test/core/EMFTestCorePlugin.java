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
 * $Id: EMFTestCorePlugin.java,v 1.7 2004/07/29 13:32:30 marcelop Exp $
 */
package org.eclipse.emf.test.core;

import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class EMFTestCorePlugin 
extends Plugin
{
    private static EMFTestCorePlugin instance;
    
    public EMFTestCorePlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestCorePlugin getPlugin()
    {
        return instance;
    }

    public String getPluginDirectory()
    {
      try
      {
        return new java.io.File(Platform.asLocalURL(getBundle().getEntry("/")).getFile()).toString();
      }
      catch (IOException e)
      {
      }
      return "";
    }
}
