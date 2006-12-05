/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: CommonPlugin.java,v 1.12 2006/12/05 20:19:58 emerks Exp $
 */
package org.eclipse.emf.common;


import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
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
   * Use the platform, if available, to convert to a local URI.
   */
  public static URI asLocalURI(URI uri)
  {
    return plugin == null ? uri : Implementation.asLocalURI(uri);
  }

  /**
   * Use the platform, if available, to resolve the URI.
   */
  public static URI resolve(URI uri)
  {
    return plugin == null ? uri : Implementation.resolve(uri);
  }

  /**
   * Use the platform, if available, to load the named class using the right class loader.
   */
  public static Class<?> loadClass(String pluginID, String className) throws ClassNotFoundException
  {
    return plugin == null ? Class.forName(className) : Implementation.loadClass(pluginID, className);
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
        URL url = FileLocator.toFileURL(new URL(uri.trimFragment().toString()));
        return fix(url, fragment);
      }
      catch (IOException exception)
      {
        // Ignore the exception and return the original URI.
      }
      return uri;
    }

    /**
     * Use the platform to convert to a local URI.
     */
    protected static URI resolve(URI uri)
    {
      String fragment = uri.fragment();
      URI uriWithoutFragment = uri.trimFragment();
      String uriWithoutFragmentToString = uriWithoutFragment.toString();
      
      URL url = null;
      try
      {
        url = FileLocator.resolve(new URL(uriWithoutFragmentToString));
      }
      catch (IOException exception1)
      {
        // Platform.resolve() doesn't work if the project is encoded.
        //
        try
        {
          uriWithoutFragmentToString = URI.decode(uriWithoutFragmentToString);
          url = FileLocator.resolve(new URL(uriWithoutFragmentToString));
        }
        catch (IOException exception2)
        {
          // Continue with the unresolved URI.
        }
      }
      if (url != null)
      {
        try
        {
          return fix(url, fragment);
        }
        catch (IOException exception)
        {
          // Return the original URI.
        }
      }
      
      return uri;
    }

    protected static URI fix(URL url, String fragment) throws IOException
    {
      // Only file-scheme URIs will be re-encoded. If a URI was decoded in the workaround
      // above, and Platform.resolve() didn't return a file-scheme URI, then this will return
      // an decoded URI.
      //
      URI result = 
        "file".equalsIgnoreCase(url.getProtocol()) ?
          URI.createFileURI(URI.decode(url.getFile())) :
          URI.createURI(url.toString());
      if (fragment != null)
      {
        result = result.appendFragment(fragment);
      }
      return result;
    }
    
    /**
     * Use the platform to load the named class using the right class loader.
     */
    public static Class<?> loadClass(String pluginID, String className) throws ClassNotFoundException
    {
      return Platform.getBundle(pluginID).loadClass(className);
    }
  }
}
