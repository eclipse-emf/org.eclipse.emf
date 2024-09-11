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
package org.eclipse.emf.common.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.emf.common.CommonPlugin;


/**
 * An abstract resource locator implementation
 * comprising a {@link #getPrimaryResourceLocator() primary locator}
 * and a series {@link #getDelegateResourceLocators() delegate locators}.
 */
public abstract class DelegatingResourceLocator implements ResourceLocator
{
  /**
   * The cached base URL.
   */
  protected URL baseURL;

  /**
   * The resource bundle containing untranslated strings.
   */
  protected ResourceBundle untranslatedResourceBundle;

  /**
   * The resource bundle containing translated strings.
   */
  protected ResourceBundle resourceBundle;

  /**
   * A cache of the translated strings.
   */
  protected Map<String, String> strings = new HashMap<String, String>();

  /**
   * A cache of the untranslated strings.
   */
  protected Map<String, String> untranslatedStrings = new HashMap<String, String>();

  /**
   * A cache of the image descriptions.
   */
  protected Map<String, Object> images = new HashMap<String, Object>();

  /**
   * Whether to translate strings by default.
   */
  protected boolean shouldTranslate = true;

  /**
   * The default localization properties file.
   * @since 2.14
   */
  protected String bundleLocalization = "plugin.properties";

  /**
   * Creates an instance.
   */
  public DelegatingResourceLocator()
  {
    super();
  }

  /**
   * Returns the primary resource locator.
   * @return the primary resource locator.
   */
  protected abstract ResourceLocator getPrimaryResourceLocator();

  /**
   * Returns the delegate resource locators.
   * @return the delegate resource locators.
   */
  protected abstract ResourceLocator [] getDelegateResourceLocators();

  private static final URI DOT = URI.createURI(".");

  private static final String PROPERTIES = ".properties";

  /*
   * Javadoc copied from interface.
   */
  public URL getBaseURL()
  {
    if (baseURL == null)
    {
      if (getPrimaryResourceLocator() == null)
      {
        try
        {
          // Determine the base URL by looking for the plugin.properties file in the standard way.
          //
          Class<? extends DelegatingResourceLocator> theClass = getClass();
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
                String bundleLocalization = getBundleLocalization(CommonUtil.newURL(uri.appendSegment("META-INF").appendSegment("MANIFEST.MF").toString()));
                if (bundleLocalization != null)
                {
                  this.bundleLocalization = bundleLocalization;
                }
                baseURL = CommonUtil.newURL(uri.toString());
              }
              catch (IOException exception)
              {
                try
                {
                  // If we can open  an input stream, then the plugin.properties is there, and we have a good base URL.
                  //
                  InputStream inputStream =  CommonUtil.newURL(uri.appendSegment("plugin.properties").toString()).openStream();
                  inputStream.close();
                  baseURL = CommonUtil.newURL(uri.toString());
                }
                catch (IOException nestedException)
                {
                  // If the plugin.properties isn't within the root of the archive,
                  // create a new URI for the folder location of the archive,
                  // so we can look in the folder that contains it.
                  //
                  uri = URI.createURI(uri.authority()).trimSegments(1);
                }
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

              try
              {
                String bundleLocalization = getBundleLocalization(CommonUtil.newURL(uri.appendSegment("META-INF").appendSegment("MANIFEST.MF").toString()));
                if (bundleLocalization != null)
                {
                  this.bundleLocalization = bundleLocalization;
                }
                String baseURI = uri.toString();
                baseURL = CommonUtil.newURL(baseURI.endsWith("/") ? baseURI : baseURI + "/");
              }
              catch (IOException exception)
              {
                uri = uri.appendSegment("plugin.properties");
                try
                {
                  // If we can open  an input stream, then the plugin.properties is in the folder, and we have a good base URL.
                  //
                  InputStream inputStream = CommonUtil.newURL(uri.toString()).openStream();
                  inputStream.close();
                  baseURL = CommonUtil.newURL(DOT.resolve(uri).toString());
                }
                catch (IOException nextedException)
                {
                  // We're going to fail.
                }
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
            baseURL = CommonUtil.newURL(DOT.resolve(URI.createURI(pluginPropertiesURL.toString())).toString());
          }
        }
        catch (IOException exception)
        {
          throw new WrappedException(exception);
        }
      }
      else
      {
        baseURL = getPrimaryResourceLocator().getBaseURL();
      }
    }

    return baseURL;
  }

  /**
   * @since 2.14
   */
  protected String getBundleLocalization(URL manifestURL) throws IOException
  {
    InputStream inputStream =  null;
    try
    {
      inputStream =  manifestURL.openStream();
      Manifest manifest = new Manifest(inputStream);
      Attributes mainAttributes = manifest.getMainAttributes();
      String value = mainAttributes.getValue("Bundle-Localization");
      if (value != null)
      {
        return value + ".properties";
      }
    }
    finally
    {
      if (inputStream != null)
      {
        inputStream.close();
      }
    }
    
    return null;
  }

  /*
   * Javadoc copied from interface.
   */
  public Object getImage(String key)
  {
    Object result = images.get(key);
    if (result == null)
    {
      ResourceLocator pluginResourceLocator = getPrimaryResourceLocator();
      if (pluginResourceLocator == null)
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
          result = pluginResourceLocator.getImage(key);
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
    URL url = CommonUtil.newURL(getBaseURL() + "icons/" + key + extensionFor(key));
    InputStream inputStream = url.openStream();
    inputStream.close();
    return url;
  }

  /**
   * Computes the file extension to be used with the key to specify an image resource.
   * @param key the key for the imagine.
   * @return the file extension to be used with the key to specify an image resource.
   */
  protected static String extensionFor(String key)
  {    String result = ".gif";
    int index = key.lastIndexOf('.');
    if (index != -1)
    {
      String extension = key.substring(index + 1);
      if ("png".equalsIgnoreCase(extension) || 
            "gif".equalsIgnoreCase(extension) ||
            "bmp".equalsIgnoreCase(extension) ||
            "ico".equalsIgnoreCase(extension) ||
            "jpg".equalsIgnoreCase(extension) ||
            "jpeg".equalsIgnoreCase(extension) ||
            "tif".equalsIgnoreCase(extension) ||
            "tiff".equalsIgnoreCase(extension))
      {
        result = "";
      }
    }
    return result;
  }

  /**
   * Does the work of fetching the image associated with the key,
   * when the image resource is not available locally.
   * @param key the key of the image to fetch.
   * @exception MissingResourceException if the image resource doesn't exist anywhere.
   * @see #getDelegateResourceLocators()
   */
  protected Object delegatedGetImage(String key) throws MissingResourceException
  {
    ResourceLocator[] delegateResourceLocators = getDelegateResourceLocators();
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
        ResourceLocator pluginResourceLocator = getPrimaryResourceLocator();
        if (pluginResourceLocator == null)
        {
          result = doGetString(key, translate);
        }
        else
        {
          result = pluginResourceLocator.getString(key, translate);
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
   * Does the work of fetching the string associated with the key.
   * It ensures that the string exists.
   * @param key the key of the string to fetch.
   * @exception MissingResourceException if a string doesn't exist.
   * @return the string associated with the key.
   */
  protected String doGetString(String key, boolean translate) throws MissingResourceException
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
      URL baseURL = getBaseURL();
      if (translate)
      {
        try
        {
          bundle = resourceBundle = ResourceBundle.getBundle(packageName + ".plugin");
        }
        catch (MissingResourceException exception)
        {
          Locale locale = Locale.getDefault();
          List<String> bundleLocalizations = new ArrayList<String>(5);
          bundleLocalizations.add(bundleLocalization);
          String translatedBundleLocalization = bundleLocalization.replace(PROPERTIES, "");
          String language = locale.getLanguage();
          if (!"".equals(language))
          {
            translatedBundleLocalization += '_' + language;
            bundleLocalizations.add(translatedBundleLocalization + PROPERTIES);
            String script = "";
            try
            {
              script = locale.getScript();
            }
            catch (NoSuchMethodError error)
            {
              // Ignore that an old runtime (Java 5) doesn't have the getScript method.
            }
            if (!"".equals(script))
            {
              translatedBundleLocalization += '_' + script;
              bundleLocalizations.add(translatedBundleLocalization + PROPERTIES);
            }
            String country = locale.getCountry();
            if (!"".equals(country))
            {
              translatedBundleLocalization += '_' + country;
              bundleLocalizations.add(translatedBundleLocalization + PROPERTIES);
              String variant = locale.getVariant();
              if (!"".equals(variant))
              {
                translatedBundleLocalization += '_' + variant;
                bundleLocalizations.add(translatedBundleLocalization + PROPERTIES);
              }
            }
          }
          for (int i = bundleLocalizations.size() - 1; i >= 0; i--)
          {
            // If the bundle can't be found the normal way, try to find it as the base URL.
            // If that also doesn't work, rethrow the original exception.
            //
            try
            {
              InputStream inputStream = CommonUtil.newURL(baseURL.toString() + bundleLocalizations.get(i)).openStream();
              bundle = resourceBundle = new PropertyResourceBundle(inputStream);
              inputStream.close();
              break;
            }
            catch (IOException ioException)
            {
              // We'll rethrow the original exception, not this one.
            }
          }
          if (bundle == null)
          {
            throw exception;
          }
        }
      }
      else
      {
        String resourceName = baseURL.toString() + bundleLocalization;
        try
        {
          InputStream inputStream = CommonUtil.newURL(resourceName).openStream();
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

  /**
   * Does the work of fetching the string associated with the key,
   * when the string resource is not available locally.
   * @param key the key of the string to fetch.
   * @exception MissingResourceException if the string resource doesn't exist anywhere.
   * @see #getDelegateResourceLocators()
   */
  protected String delegatedGetString(String key, boolean translate)
  {
    ResourceLocator[] delegateResourceLocators = getDelegateResourceLocators();
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
}
