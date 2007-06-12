/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: EMFPlugin.java,v 1.19 2007/06/12 20:56:17 emerks Exp $
 */
package org.eclipse.emf.common;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.jar.Manifest;

import org.osgi.framework.Bundle;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;


/**
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access (e.g., NL strings, images, and so on) is directed to the resource locator methods,
 * which can redirect the service as appropriate to the runtime.
 * During Eclipse invocation, the implementation delegates to a plugin implementation.
 * During stand-alone invocation, no plugin initialization takes place,
 * so the implementation delegates to a resource JAR on the CLASSPATH.
 * The resource jar will typically <b>not</b> be on the CLASSPATH during Eclipse invocation.
 * It will contain things like the icons and the .properties,  
 * which are available in a different way during Eclipse invocation.
 * @see ResourceLocator
 * @see Logger
 */
public abstract class EMFPlugin implements ResourceLocator, Logger
{
  public static final boolean IS_ECLIPSE_RUNNING;
  static
  {
    boolean result = false;
    try
    {
      result = Platform.isRunning();
    }
    catch (Throwable exception)
    {
      // Assume that we aren't running.
    }
    IS_ECLIPSE_RUNNING = result;
  }

  public static final boolean IS_RESOURCES_BUNDLE_AVAILABLE;
  static
  {
    boolean result = false;
    if (IS_ECLIPSE_RUNNING)
    {
      try
      {
        Bundle resourcesBundle = Platform.getBundle("org.eclipse.core.resources");
        result = resourcesBundle != null && (resourcesBundle.getState() & (Bundle.ACTIVE | Bundle.STARTING | Bundle.RESOLVED)) != 0;
      }
      catch (Throwable exception)
      {
        // Assume that it's not available.
      }
    }
    IS_RESOURCES_BUNDLE_AVAILABLE = result;
  }

  protected ResourceLocator [] delegateResourceLocators;
  protected URL baseURL;
  protected ResourceBundle untranslatedResourceBundle;
  protected ResourceBundle resourceBundle;
  protected Map<String, String> strings = new HashMap<String, String>();
  protected Map<String, String> untranslatedStrings = new HashMap<String, String>();
  protected boolean shouldTranslate = true;
  protected Map<String, Object> images = new HashMap<String, Object>();

  public EMFPlugin(ResourceLocator [] delegateResourceLocators)
  {
    this.delegateResourceLocators = delegateResourceLocators;
  }

  /**
   * Returns an Eclipse plugin implementation of a resource locator.
   * @return an Eclipse plugin implementation of a resource locator.
   */
  public abstract ResourceLocator getPluginResourceLocator();

  /**
   * Returns an Eclipse plugin implementation of a logger.
   * @return an Eclipse plugin implementation of a logger.
   */
  public Logger getPluginLogger()
  {
    return (Logger)getPluginResourceLocator();
  }

  public String getSymbolicName()
  {
    ResourceLocator resourceLocator = getPluginResourceLocator();
    if (resourceLocator instanceof InternalEclipsePlugin)
    {
      return ((InternalEclipsePlugin)resourceLocator).getSymbolicName();
    }
    else
    {
      String result = getClass().getName();
      return result.substring(0, result.lastIndexOf('.'));
    }
  }

  private static final URI DOT = URI.createURI(".");
  /*
   * Javadoc copied from interface.
   */
  public URL getBaseURL()
  {
    if (baseURL == null)
    {
      if (getPluginResourceLocator() == null)
      {
        try
        {
          // Determine the base URL by looking for the plugin.properties file in the standard way.
          //
          Class<? extends EMFPlugin> theClass = getClass();
          URL pluginPropertiesURL = theClass.getResource("plugin.properties");
          if (pluginPropertiesURL == null)
          {
            // If that fails, determine the URL for the class itself.
            // The URL will be of one of the following forms,
            // so there are a few good places to consider looking for the plugin.properties.
            //
            // For a plugin.xml with runtime="common.jar":
            // jar:file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/common.jar!/org/eclipse/common/CommonPlugin.class
            //
            // For a plugin.xml with runtime="runtime/common.jar":
            // jar:file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/runtime/common.jar!/org/eclipse/common/CommonPlugin.class
            // 
            // For a plugin.xml with runtime="." where the plugin is jarred:
            // jar:file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common.jar!/org/eclipse/common/CommonPlugin.class
            //
            // For a plugin.xml with runtime="." where the plugin is not jarred.
            // file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/org/eclipse/emf/common/CommonPlugin.class
            //
            // Running in PDE with bin on classpath:
            // file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/bin/org/eclipse/emf/common/CommonPlugin.class
            //
            String className = theClass.getName();
            int index = className.lastIndexOf(".");
            URL classURL = theClass.getResource((index == -1 ? className : className.substring(index + 1)) + ".class");
            URI uri = URI.createURI(classURL.toString());
            
            // Trim off the segments corresponding to the package nesting.
            //
            int count = 1;
            for (int i = 0; (i = className.indexOf('.', i)) != -1; ++i)
            {
              ++count;
            }
            uri = uri.trimSegments(count);
            
            // For an archive URI, check for the plugin.properties in the archive.
            //
            if (URI.isArchiveScheme(uri.scheme()))
            {
              try
              {
                // If we can open  an input stream, then the plugin.properties is there, and we have a good base URL.
                //
                InputStream inputStream =  new URL(uri.appendSegment("plugin.properties").toString()).openStream();
                inputStream.close();
                baseURL = new URL(uri.toString());
              }
              catch (IOException exception)
              {
                // If the plugin.properties isn't within the root of the archive, 
                // create a new URI for the folder location of the archive, 
                // so we can look in the folder that contains it.
                //
                uri = URI.createURI(uri.authority()).trimSegments(1);
              }
            }
            
            // If we didn't find the plugin.properties in the usual place nor in the archive...
            //
            if (baseURL == null)
            {
              // Trim off the "bin" or "runtime" segment.
              //
              String lastSegment = uri.lastSegment();
              if ("bin".equals(lastSegment) || "runtime".equals(lastSegment))
              {
                uri = uri.trimSegments(1);
              }
              uri = uri.appendSegment("plugin.properties");
              try
              {
                // If we can open  an input stream, then the plugin.properties is in the folder, and we have a good base URL.
                //
                InputStream inputStream = new URL(uri.toString()).openStream();
                inputStream.close();
                baseURL = new URL(DOT.resolve(uri).toString());
              }
              catch (IOException exception)
              {
                // Continue with the established base URL.
              }
            }
            
            // If we still don't have a good base URL, complain about it.
            //
            if (baseURL == null)
            {
              String resourceName = 
                index == -1 ? 
                  "plugin.properties" : 
                  className.substring(0, index + 1).replace('.','/') + "plugin.properties";
              throw new MissingResourceException("Missing properties: " + resourceName, theClass.getName(), "plugin.properties");
            }
          }
          else
          {
            baseURL = new URL(DOT.resolve(URI.createURI(pluginPropertiesURL.toString())).toString());
          }
        }
        catch (IOException exception)
        {
          throw new WrappedException(exception);
        }
      }
      else
      {
        baseURL = getPluginResourceLocator().getBaseURL();
      }
    }

    return baseURL;
  } 

  /*
   * Javadoc copied from interface.
   */
  public Object getImage(String key)
  {
    Object result = images.get(key);
    if (result == null)
    {
      if (getPluginResourceLocator() == null)
      {
        try
        {
          result = doGetImage(key);
        }
        catch (MalformedURLException exception)
        {
          throw new WrappedException(exception);
        }
        catch (IOException exception)
        {
          result = delegatedGetImage(key);
        }
      }
      else
      {
        try
        {
          result = getPluginResourceLocator().getImage(key);
        }
        catch (MissingResourceException exception)
        {
          result = delegatedGetImage(key);
        }
      }

      images.put(key, result);
    }

    return result;
  }

  /**
   * Does the work of fetching the image associated with the key.
   * It ensures that the image exists.
   * @param key the key of the image to fetch.
   * @exception IOException if an image doesn't exist.
   * @return the description of the image associated with the key.
   */
  protected Object doGetImage(String key) throws IOException
  {
    URL url = new URL(getBaseURL() + "icons/" + key + ".gif");
    InputStream inputStream = url.openStream(); 
    inputStream.close();
    return url;
  }

  /**
   * Does the work of fetching the image associated with the key,
   * when the image resource is not available locally.
   * @param key the key of the image to fetch.
   * @exception MissingResourceException if the image resource doesn't exist anywhere.
   * @see #delegateResourceLocators
   */
  protected Object delegatedGetImage(String key) throws MissingResourceException
  {
    for (int i = 0; i < delegateResourceLocators.length; ++i)
    {
      try
      {
        return delegateResourceLocators[i].getImage(key);
      }
      catch (MissingResourceException exception)
      {
        // Ignore the exception since we will throw one when all else fails.
      }
    }

    throw 
      new MissingResourceException
        (CommonPlugin.INSTANCE.getString("_UI_ImageResourceNotFound_exception", new Object [] { key }),
         getClass().getName(), 
         key);
  }

  /**
   * Indicates whether strings should be translated by default.
   * 
   * @return <code>true</code> if strings should be translated by default; <code>false</code> otherwise.
   */
  public boolean shouldTranslate()
  {
    return shouldTranslate;
  }

  /**
   * Sets whether strings should be translated by default.
   * 
   * @param shouldTranslate whether strings should be translated by default.
   */
  public void setShouldTranslate(boolean shouldTranslate)
  {
    this.shouldTranslate = shouldTranslate;
  }

  /*
   * Javadoc copied from interface.
   */
  public String getString(String key)
  {
    return getString(key, shouldTranslate());
  }

  /*
   * Javadoc copied from interface.
   */
  public String getString(String key, boolean translate)
  {
    Map<String, String> stringMap = translate ? strings : untranslatedStrings;
    String result = stringMap.get(key);
    if (result == null)
    {
      try
      {
        if (getPluginResourceLocator() == null)
        {
          ResourceBundle bundle = translate ? resourceBundle : untranslatedResourceBundle;
          if (bundle == null)
          {
            String packageName = getClass().getName();
            int index = packageName.lastIndexOf(".");
            if (index != -1)
            {
              packageName = packageName.substring(0, index);
            }
            if (translate)
            {
              try
              {
                bundle = resourceBundle = ResourceBundle.getBundle(packageName + ".plugin");
              }
              catch (MissingResourceException exception)
              {
                // If the bundle can't be found the normal way, try to find it as the base URL.
                // If that also doesn't work, rethrow the original exception.
                //
                try
                {
                  InputStream inputStream =  new URL(getBaseURL().toString() + "plugin.properties").openStream();
                  bundle = untranslatedResourceBundle = resourceBundle = new PropertyResourceBundle(inputStream);
                  inputStream.close();
                }
                catch (IOException ioException)
                {
                  // We'll rethrow the original exception, not this one.
                }
                if (bundle == null)
                {
                  throw exception; 
                }
              }
            }
            else
            {
              String resourceName = getBaseURL().toString() + "plugin.properties";
              try
              {
                InputStream inputStream =  new URL(resourceName).openStream();
                bundle = untranslatedResourceBundle = new PropertyResourceBundle(inputStream);
                inputStream.close();
              }
              catch (IOException ioException)
              {
                throw new MissingResourceException("Missing properties: " + resourceName, getClass().getName(), "plugin.properties");
              }
            }
          }
          result = bundle.getString(key);
        }
        else
        {
          result = getPluginResourceLocator().getString(key, translate);
        }
      }
      catch (MissingResourceException exception)
      {
        result = delegatedGetString(key, translate);
      }
  
      stringMap.put(key, result);
    }

    return result;
  }

  /**
   * Does the work of fetching the string associated with the key,
   * when the string resource is not available locally.
   * @param key the key of the string to fetch.
   * @exception MissingResourceException if the string resource doesn't exist anywhere.
   * @see #delegateResourceLocators
   */
  protected String delegatedGetString(String key, boolean translate)
  {
    for (int i = 0; i < delegateResourceLocators.length; ++i)
    {
      try
      {
        return delegateResourceLocators[i].getString(key, translate);
      }
      catch (MissingResourceException exception)
      {
        // Ignore this since we will throw an exception when all else fails.
      }
    }

    throw 
      new MissingResourceException
        (MessageFormat.format("The string resource ''{0}'' could not be located", new Object [] { key }),
         getClass().getName(), 
         key);
  }

  /*
   * Javadoc copied from interface.
   */
  public String getString(String key, Object [] substitutions)
  {
    return getString(key, substitutions, shouldTranslate());
  }
  
  /*
   * Javadoc copied from interface.
   */
  public String getString(String key, Object [] substitutions, boolean translate)
  {
    return MessageFormat.format(getString(key, translate), substitutions);
  }

  /*
   * Javadoc copied from interface.
   */
  public void log(Object logEntry)
  {
    Logger logger = getPluginLogger();
    if (logger == null)
    {
      if (logEntry instanceof Throwable)
      {
        ((Throwable)logEntry).printStackTrace(System.err);
      }
      else
      {
        System.err.println(logEntry);
      }
    }
    else
    {
      logger.log(logEntry);
    }
  }

  /**
   * The actual implementation of an Eclipse <b>Plugin</b>.
   */
  public static abstract class EclipsePlugin extends Plugin implements ResourceLocator, Logger, InternalEclipsePlugin
  {
    /**
     * The EMF plug-in APIs are all delegated to this helper, so that code can be shared by plug-in
     * implementations with a different platform base class (e.g. AbstractUIPlugin).
     */
    protected InternalHelper helper;
    
    /**
     * Creates an instance.
     */
    public EclipsePlugin()
    {
      super();
      helper = new InternalHelper(this);
    }

    /**
     * Creates an instance.
     * @param descriptor the description of the plugin.
     * @deprecated
     */
    @Deprecated
    public EclipsePlugin(org.eclipse.core.runtime.IPluginDescriptor descriptor)
    {
      super(descriptor);
      helper = new InternalHelper(this);
    }

    /**
     * Return the plugin ID.
     */
    public String getSymbolicName()
    {
      return helper.getSymbolicName();
    }

    /*
     * Javadoc copied from interface.
     */
    public URL getBaseURL()
    {
      return helper.getBaseURL();
    }

    /*
     * Javadoc copied from interface.
     */
    public Object getImage(String key)
    {
      try
      {
        return doGetImage(key);
      }
      catch (MalformedURLException exception)
      {
        throw new WrappedException(exception);
      }
      catch (IOException exception)
      {
        throw 
          new MissingResourceException
            (CommonPlugin.INSTANCE.getString("_UI_StringResourceNotFound_exception", new Object [] { key }),
             getClass().getName(), 
             key);
      }
    }

    /**
     * Does the work of fetching the image associated with the key.
     * It ensures that the image exists.
     * @param key the key of the image to fetch.
     * @exception IOException if an image doesn't exist.
     * @return the description of the image associated with the key.
     */
    protected Object doGetImage(String key) throws IOException
    {
      return helper.getImage(key);
    }

    public String getString(String key)
    {
      return helper.getString(key, true);
    }
    
    public String getString(String key, boolean translate)
    {
      return helper.getString(key, translate);
    }

    public String getString(String key, Object [] substitutions)
    {
      return helper.getString(key, substitutions, true);
    }

    public String getString(String key, Object [] substitutions, boolean translate)
    {
      return helper.getString(key, substitutions, translate);
    }

    public void log(Object logEntry)
    {
      helper.log(logEntry);
    }
  }

  /**
   * This just provides a common interface for the Eclipse plugins supported by EMF.
   * It is not considered API and should not be used by clients.
   */
  public static interface InternalEclipsePlugin
  {
    String getSymbolicName();
  }
  
  /**
   * This just provides a common delegate for non-UI and UI plug-in classes.
   * It is not considered API and should not be used by clients.
   */
  public static class InternalHelper
  {
    protected Plugin plugin;
    protected ResourceBundle resourceBundle;
    protected ResourceBundle untranslatedResourceBundle;

    public InternalHelper(Plugin plugin)
    {
      this.plugin = plugin;
    }

    protected Bundle getBundle()
    {
      return plugin.getBundle();
    }

    protected ILog getLog()
    {
      return plugin.getLog();
    }
    
    /**
     * Return the plugin ID.
     */
    public String getSymbolicName()
    {
      return getBundle().getSymbolicName();
    }

    public URL getBaseURL()
    {
      return getBundle().getEntry("/");
    }

    /**
     * Fetches the image associated with the given key. It ensures that the image exists.
     * @param key the key of the image to fetch.
     * @exception IOException if an image doesn't exist.
     * @return the description of the image associated with the key.
     */
    public Object getImage(String key) throws IOException
    {
      URL url = new URL(getBaseURL() + "icons/" + key + ".gif");
      InputStream inputStream = url.openStream(); 
      inputStream.close();
      return url;
    }

    public String getString(String key, boolean translate)
    {
      ResourceBundle bundle = translate ? resourceBundle : untranslatedResourceBundle;
      if (bundle == null)
      {
        if (translate)
        {
           bundle = resourceBundle = Platform.getResourceBundle(getBundle());
        }
        else
        {
          String resourceName = getBaseURL().toString() + "plugin.properties";
          try
          {
            InputStream inputStream =  new URL(resourceName).openStream();
            bundle = untranslatedResourceBundle = new PropertyResourceBundle(inputStream);
            inputStream.close();
          }
          catch (IOException ioException)
          {
            throw new MissingResourceException("Missing properties: " + resourceName, getClass().getName(), "plugin.properties");
          }
        }
      }
      return bundle.getString(key);
    }

    public String getString(String key, Object [] substitutions, boolean translate)
    {
      return MessageFormat.format(getString(key, translate), substitutions);
    }

    public void log(Object logEntry)
    {
      IStatus status;
      if (logEntry instanceof IStatus)
      {
        status = (IStatus)logEntry;
        getLog().log(status);
      }
      else 
      {
        if (logEntry == null)
        {
          logEntry = new RuntimeException(getString("_UI_NullLogEntry_exception", true)).fillInStackTrace();
        }

        if (logEntry instanceof Throwable)
        {
          Throwable throwable = (Throwable)logEntry;

          // System.err.println("Logged throwable: --------------------");
          // throwable.printStackTrace();

          String message = throwable.getLocalizedMessage();
          if (message == null)
          {
            message = "";
          }

          getLog().log(new Status(IStatus.WARNING, getBundle().getSymbolicName(), 0, message, throwable));
        }
        else
        {
          // System.err.println("Logged throwable: --------------------");
          // throwable.printStackTrace();

          getLog().log (new Status (IStatus.WARNING, getBundle().getSymbolicName(), 0, logEntry.toString(), null));
        }
      }
    } 
  }

  public static void main(String[] args)
  {
    try
    {
      String [] relativePath = { "META-INF", "MANIFEST.MF" };
      Class<?> theClass =  args.length > 0 ? Class.forName(args[0]) : EMFPlugin.class;

      String className = theClass.getName();
      int index = className.lastIndexOf(".");
      URL classURL = theClass.getResource((index == -1 ? className : className.substring(index + 1)) + ".class");
      URI uri = URI.createURI(classURL.toString());

      // Trim off the segments corresponding to the package nesting.
      //
      int count = 1;
      for (int i = 0; (i = className.indexOf('.', i)) != -1; ++i)
      {
        ++count;
      }
      uri = uri.trimSegments(count);

      URL manifestURL = null;
  
      // For an archive URI, check for the path in the archive.
      //
      if (URI.isArchiveScheme(uri.scheme()))
      {
        try
        {
          // If we can open  an input stream, then the path is there, and we have a good URL.
          //
          String manifestURI = uri.appendSegments(relativePath).toString();
          InputStream inputStream =  new URL(manifestURI).openStream();
          inputStream.close();
          manifestURL = new URL(manifestURI);
        }
        catch (IOException exception)
        {
          // If the path isn't within the root of the archive, 
          // create a new URI for the folder location of the archive, 
          // so we can look in the folder that contains it.
          //
          uri = URI.createURI(uri.authority()).trimSegments(1);
        }
      }
              
      // If we didn't find the path in the usual place nor in the archive...
      //
      if (manifestURL == null)
      {
        // Trim off the "bin" or "runtime" segment.
        //
        String lastSegment = uri.lastSegment();
        if ("bin".equals(lastSegment) || "runtime".equals(lastSegment))
        {
          uri = uri.trimSegments(1);
        }
        uri = uri.appendSegments(relativePath);
        manifestURL = new URL(uri.toString());
      }
              
      Manifest manifest = new Manifest(manifestURL.openStream());
      String symbolicName =  manifest.getMainAttributes().getValue("Bundle-SymbolicName");
      if (symbolicName != null)
      {
        int end = symbolicName.indexOf(";");
        if (end != -1)
        {
          symbolicName = symbolicName.substring(0, end);
        }
        System.out.println("Bundle-SymbolicName=" + symbolicName + " Bundle-Version=" + manifest.getMainAttributes().getValue("Bundle-Version"));
        return;
      }
    }
    catch (Exception exception)
    {
      // Just print an error message.
    }
    
    System.err.println("No Bundle information found");
  }
}
