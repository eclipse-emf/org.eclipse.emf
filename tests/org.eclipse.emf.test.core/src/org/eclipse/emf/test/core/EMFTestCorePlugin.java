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
 * $Id: EMFTestCorePlugin.java,v 1.5 2004/03/25 06:42:49 marcelop Exp $
 */
package org.eclipse.emf.test.core;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;

public class EMFTestCorePlugin 
extends Plugin
{
    private static EMFTestCorePlugin instance;
    
    /**
     * @param descriptor
     */
    public EMFTestCorePlugin(IPluginDescriptor descriptor)
    {
        super(descriptor);
        instance = this;
    }

    public static EMFTestCorePlugin getPlugin()
    {
        return instance;
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#startup()
     */
    public void startup() throws CoreException
    {
        super.startup();
    }
    
    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#shutdown()
     */
    public void shutdown() throws CoreException
    {
        super.shutdown();
    }
    
    public String getPluginDirectory()
    {
      try
      {
        return new java.io.File(Platform.asLocalURL(getDescriptor().getInstallURL()).getFile()).toString();
      }
      catch (IOException e)
      {
      }
      return "";
    }
}
