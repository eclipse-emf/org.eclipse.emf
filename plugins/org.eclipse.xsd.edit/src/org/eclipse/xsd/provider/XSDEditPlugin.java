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
 * $Id: XSDEditPlugin.java,v 1.2 2004/05/16 16:45:59 emerks Exp $
 */
package org.eclipse.xsd.provider;


import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;


/**
 * The <b>Plugin</b> for the item providers.
 * The XML Schema model needs to be able to run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all access is directed to the static methods,
 * which can redirect the service as appopriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case you will need to set the <code>VABASE</code> or <code>ECLIPSE</code> environment variable
 * to point at the location of the Eclipse installation.
 * @see #getBaseURL
 */
public class XSDEditPlugin extends Plugin
{
  /**
   * Returns the singleton instance.
   * @return the singleton instance.
   */
  public static XSDEditPlugin getPlugin()
  {
    return plugin;
  }

  /**
   * Returns the base location at which to find resources.
   * During a stand-lone invocation, 
   * the value of the <code>VABASE</code> or <code>ECLIPSE</code> environment variable will be used, e.g.,
   *<pre>
   *  {@link System#getProperty(String) System.getProperty}("VABASE") + "/plugins/org.eclipse.xsd/"
   *</pre>
   * @return the base location at which to find resources.
   * @see org.eclipse.core.runtime.IPluginDescriptor#getInstallURL
   */
  public static String getBaseURL()
  {
    if (plugin == null)
    {
      if (System.getProperty("VABASE") != null)
      {
        return System.getProperty("VABASE") + "/plugins/org.eclipse.xsd/";
      }
      else if (System.getProperty("ECLIPSE") != null)
      {
        return System.getProperty("ECLIPSE") + "/plugins/org.eclipse.xsd/";
      }
      else
      {
        return "file:///eclipse/plugins/org.eclipse.xsd/";
      }
    }
    else
    {
      String baseURL =  getPlugin().getBundle().getEntry("/").toString();
      return baseURL;
    }
  }

  /**
   * Returns a URL for an image resource in the icons folder.
   * @param key the key of the image to fetch.
   * @return a URL for an image resource in the icons folder.
   */
  public static Object getImage(String key)
  {
    try
    {
      return new URL(getBaseURL() + "icons/" + key + ".gif");
    }
    catch (MalformedURLException exception)
    {
      System.out.println("Failed to load image for '" + key + "'");
      exception.printStackTrace();
    }

    return null;
  }

  /**
   * Returns a resource string from the plugin.properties file.
   * @param key the key of the property to fetch.
   * @return a resource string from the plugin.properties file.
   * @see #getString(String, Object [])
   */
  public static String getString(String key)
  {
    if (plugin == null)
    {
      try
      {
        if (resourceBundle == null)
        { 
          resourceBundle = new PropertyResourceBundle(new FileInputStream(getBaseURL() + "plugin.properties"));
        }
        return resourceBundle.getString(key);
      }
      catch (Exception exception)
      {
        exception.printStackTrace(System.err);
        return "";
      }
    }
    else 
    {
      return Platform.getResourceBundle(plugin.getBundle()).getString(key);
    }
  }

  /**
   * Returns a resource string with substitutions from the plugin.properties.
   * @param key the key of the property to fetch.
   * @param objects the substitutions.
   * @return a resource string with substitutions from the plugin.properties.
   * @see #getString(String)
   * @see MessageFormat#format(String, Object[])
   */
  public static String getString(String key, Object [] objects)
  {
    return MessageFormat.format(getString(key), objects);
  }

  /**
   * The one instance of this class.
   */
  private static XSDEditPlugin plugin;

  /**
   * The one instance of the resource bundle.
   */
  private static ResourceBundle resourceBundle;

  /**
   * Creates an instance.
   * @param descriptor the description of the plugin.
   */
  public XSDEditPlugin()
  {
    super();

    // Remember the static instance.
    //
    plugin = this;
  }
}
