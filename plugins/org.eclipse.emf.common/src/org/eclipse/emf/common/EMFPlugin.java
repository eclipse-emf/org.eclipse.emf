/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.jar.Manifest;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.emf.common.util.DelegatingResourceLocator;
import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;


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
 * @see DelegatingResourceLocator
 * @see ResourceLocator
 * @see Logger
 */
public abstract class EMFPlugin extends DelegatingResourceLocator implements ResourceLocator, Logger
{
  /**
   * @since 2.27
   */
  public static final boolean IS_OSGI_RUNNING;
  static
  {
    boolean result = false;
    try
    {
      result = FrameworkUtil.getBundle(EMFPlugin.class) != null;
    }
    catch (Throwable exception)
    {
      // Assume that we aren't running.
    }
    IS_OSGI_RUNNING = result;
  }

  public static final boolean IS_ECLIPSE_RUNNING;
  static
  {
    boolean result = false;
    try
    {
      if (Platform.isRunning())
      {
        // With this we make sure, that we are really in a proper Eclipse Environment with everything we need.
        // Some supplements pretend that the Platform is running, but don't provide a ExtensionRegistry.
        FrameworkUtil.getBundle(EMFPlugin.class).loadClass("org.eclipse.core.runtime.IRegistryChangeListener");
        result = true;
      }
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

  public EMFPlugin(ResourceLocator [] delegateResourceLocators)
  {
    this.delegateResourceLocators = delegateResourceLocators;
  }

  /**
   * Returns an Eclipse plugin implementation of a resource locator.
   * @return an Eclipse plugin implementation of a resource locator.
   */
  public abstract ResourceLocator getPluginResourceLocator();
  
  @Override
  final protected ResourceLocator getPrimaryResourceLocator()
  {
    return getPluginResourceLocator();
  }
  
  @Override
  protected ResourceLocator[] getDelegateResourceLocators()
  {
    return delegateResourceLocators;
  }

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
   * This is just a bundle activator wrapper for delegating to another bundle activator.
   * It provides a {@link #createBundle() create} method for creating the delegate.
   * Any exception thrown during creation of the delegate is ignored,
   * in which case this activator does nothing for {@link #start(BundleContext) start} and {@link #stop(BundleContext) stop}.
   * The idea is to provide a bundle activator that can delegate to an Equinox-dependent bundle activator,
   * but behaves gracefully in a non-Equinox OSGi implementation.
   * 
   * @since 2.10
   */
  public static abstract class OSGiDelegatingBundleActivator implements BundleActivator
  {
    private final BundleActivator bundle;

    public OSGiDelegatingBundleActivator()
    {
      bundle = createBundleHelper();
    }

    private BundleActivator createBundleHelper()
    {
      try
      {
        return createBundle();
      }
      catch (Throwable throwable)
      {
        return null;
      }
    }

    protected abstract BundleActivator createBundle();

    public final void start(BundleContext context) throws Exception
    {
      if (bundle != null)
      {
        bundle.start(context);
      }
    }

    public final void stop(BundleContext context) throws Exception
    {
      if (bundle != null)
      {
        bundle.stop(context);
      }
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
      URL url = CommonUtil.newURL(getBaseURL() + "icons/" + key + extensionFor(key));
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
          String bundleLocalization = (String)getBundle().getHeaders().get(Constants.BUNDLE_LOCALIZATION);
          String propertiesPath = bundleLocalization != null ? bundleLocalization + ".properties" : "plugin.properties";
          String resourceName = getBaseURL().toString() + propertiesPath;
          try
          {
            InputStream inputStream =  CommonUtil.newURL(resourceName).openStream();
            bundle = untranslatedResourceBundle = new PropertyResourceBundle(inputStream);
            inputStream.close();
          }
          catch (IOException ioException)
          {
            throw new MissingResourceException("Missing properties: " + resourceName, getClass().getName(), propertiesPath);
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
            Throwable cause = throwable.getCause();
            if (cause != null)
            {
              message = cause.getLocalizedMessage();
            }
            if (message == null)
            {
              message = "";
            }
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
          InputStream inputStream =  CommonUtil.newURL(manifestURI).openStream();
          inputStream.close();
          manifestURL = CommonUtil.newURL(manifestURI);
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
        manifestURL = CommonUtil.newURL(uri.toString());
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
