/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: EclipseApplication.java,v 1.1 2006/12/28 08:11:37 marcelop Exp $
 */
package org.eclipse.emf.common.util;

import java.util.Map;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * <p>Superclass for Eclipse Applications.  It implements both {@link IPlatformRunnable}
 * and {@link IApplication}.  The latter's default implementation delegates the execution
 * to the run method defined by the former.</p>
 * <p>Subclasses may choose which method to override depending on how it is
 * registered in the plugin.xml file.</p>
 */
@SuppressWarnings("deprecation")
public class EclipseApplication implements IApplication, IPlatformRunnable
{
  public static Object delegateToPlatformRunnable(IPlatformRunnable platformRunnable, IApplicationContext context) throws Exception
  {
    Object ret = platformRunnable.run(getArguments(context.getArguments()));
    if (ret instanceof Integer)
    {
      if (ret.equals(IPlatformRunnable.EXIT_OK)) return IApplication.EXIT_OK;
      if (ret.equals(IPlatformRunnable.EXIT_RELAUNCH)) return IApplication.EXIT_RELAUNCH;
      if (ret.equals(IPlatformRunnable.EXIT_RESTART)) return IApplication.EXIT_RESTART;
    }
    return ret;    
  }
  
  public static String[] getArguments(Map<?,?> contextArguments)
  {
    String[] args = (String[])contextArguments.get(IApplicationContext.APPLICATION_ARGS);
    return args != null ? args : new String[0];
  }
  
  public Object start(IApplicationContext context) throws Exception
  {
    return delegateToPlatformRunnable(this, context);
  }

  public void stop()
  {
    // Subclasses may override
  }

  public Object run(Object args) throws Exception
  {
    return IPlatformRunnable.EXIT_OK;
  }
}
