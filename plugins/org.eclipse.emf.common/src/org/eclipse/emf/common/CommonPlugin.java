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
 * $Id: CommonPlugin.java,v 1.5 2004/07/29 13:32:37 marcelop Exp $
 */
package org.eclipse.emf.common;


import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;


/**
 * The <b>Plugin</b> for the model EMF.Common library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appopriate to the runtime.
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
   * The one instance of this class.
   */
  private static Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private CommonPlugin()
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
   * Use the platform, if available, to convert to a local URI.
   */
  public static URI asLocalURI(URI uri)
  {
    return plugin == null ? uri : Implementation.asLocalURI(uri);
  }

  /**
   * Use the platform, if available, to resolve the URL.
   */
  public static URI resolve(URI uri)
  {
    return plugin == null ? uri : Implementation.resolve(uri);
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

    /**
     * Use the platform to convert to a local URI.
     */
    protected static URI asLocalURI(URI uri)
    {
      try
      {
        String fragment = uri.fragment();
        URL url = Platform.asLocalURL(new URL(uri.trimFragment().toString()));
        return fix(url, fragment);
      }
      catch (IOException exception)
      {
      }
      return uri;
    }

    /**
     * Use the platform to convert to a local URI.
     */
    protected static URI resolve(URI uri)
    {
      try
      {
        String fragment = uri.fragment();
        URL url = Platform.resolve(new URL(uri.trimFragment().toString()));
        return fix(url, fragment);
      }
      catch (IOException exception)
      {
      }
      return uri;
    }

    protected static URI fix(URL url, String fragment) throws IOException
    {
      URI result = 
        "file".equalsIgnoreCase(url.getProtocol()) ?
          URI.createFileURI(url.getFile()) :
          URI.createURI(url.toString());
      if (fragment != null)
      {
        result = result.appendFragment(fragment);
      }
      return result;
    }
  }
}
