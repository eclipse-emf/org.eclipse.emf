/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.importer;


import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.importer.ImporterPlugin;


public final class XcoreImporterPlugin extends EMFPlugin
{
  /**
   * The singleton instance of the plugin.
   */
  public static final XcoreImporterPlugin INSTANCE = new XcoreImporterPlugin();

  /**
   * The one instance of this class.
   */
  private static Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private XcoreImporterPlugin()
  {
    super(new ResourceLocator [] { ImporterPlugin.INSTANCE });
  }

  /*
   * Javadoc copied from base class.
   */
  @Override
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
  public static class Implementation extends EclipsePlugin
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
