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
 * $Id: XSDEditorPlugin.java,v 1.3 2004/07/29 13:32:41 marcelop Exp $
 */
package org.eclipse.xsd.presentation;


import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;


/**
 * The <b>Plugin</b> for the XSD.Editor library.
 * @see #INSTANCE
 */
public final class XSDEditorPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final XSDEditorPlugin INSTANCE = new XSDEditorPlugin();

  /**
   * The one instance of this class.
   */
  private static Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private XSDEditorPlugin()
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
