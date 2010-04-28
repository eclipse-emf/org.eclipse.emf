/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: CommonPlugin.java,v 1.2 2010/04/28 20:37:44 khussey Exp $
 */
package org.eclipse.emf.common;


import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;


/**
 * The <b>Plugin</b> for the model EMF.Common library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appropriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case, common.resources.jar must be on the CLASSPATH.
 * @see #INSTANCE
 */
public final class CommonPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final CommonPlugin INSTANCE = new CommonPlugin();

  /**
   * Creates the singleton instance.
   */
  private CommonPlugin()
  {
    super(new ResourceLocator[] {});
  }

  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return null;
  }

  /**
   * Use the platform, if available, to convert to a local URI.
   */
  public static URI asLocalURI(URI uri)
  {
    return uri;
  }

  /**
   * Use the platform, if available, to resolve the URI.
   */
  public static URI resolve(URI uri)
  {
    return uri;
  }
}
