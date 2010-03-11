/**
 * <copyright> 
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: EclipseUIPlugin.java,v 1.1 2010/03/11 02:30:05 khussey Exp $
 */
package org.eclipse.emf.common.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.MissingResourceException;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.WrappedException;

/**
 * An Eclipse <b>AbstractUIPlugin</b> implementation base to be used within UI plug-ins.
 * @since 2.2.0
 */
public abstract class EclipseUIPlugin extends AbstractUIPlugin implements ResourceLocator, Logger, EMFPlugin.InternalEclipsePlugin
{
  /**
   * The EMF plug-in APIs are all delegated to this helper, so that code can be shared by plug-in
   * implementations with a different platform base class (e.g. Plugin).
   */
  protected EMFPlugin.InternalHelper helper;

  /**
   * Creates an instance.
   */
  public EclipseUIPlugin()
  {
    super();
    helper = new EMFPlugin.InternalHelper(this);
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
