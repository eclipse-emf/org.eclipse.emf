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
 * $Id: XSDPlugin.java,v 1.3 2004/07/29 13:33:09 marcelop Exp $
 */
package org.eclipse.xsd;


import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;


/**
 * The <b>Plugin</b> for the model.
 * The XML Schema model needs to be able to run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all access is directed to the static methods,
 * which can redirect the service as appopriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case you will need the resources jar on the class path.
 * @see #getBaseURL
 */
public final class XSDPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final XSDPlugin INSTANCE = new XSDPlugin();

  /**
   * The one instance of this class.
   */
  static private Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private XSDPlugin()
  {
    super(new ResourceLocator[] {});
  }

  /*
   * Javadoc copied from base class.
   */
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * @return the singleton instance.
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>.
   */
  public static class Implementation extends EMFPlugin.EclipsePlugin
  {
    /**
     * Creates an instance.
     */
    public Implementation()
    {
      super();

      // Remember the static instance.
      //
      plugin = this;
    }
  }
}
