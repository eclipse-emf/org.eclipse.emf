/**
 * Copyright (c) 2005-2026 Remko Popma and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.examples.jet.article2.ui;


import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.examples.jet.article2.TypesafeEnumPlugin;


/**
 * Convenience class for getting strings from a resource bundle.
 * 
 * @author Remko Popma
 */
public class WizardMessages
{
  private static final ResourceBundle RESOURCE_BUNDLE = Platform.getResourceBundle(TypesafeEnumPlugin.getDefault().getBundle());

  private WizardMessages()
  {
    super();
  }

  public static String getString(String key)
  {
    try
    {
      return RESOURCE_BUNDLE.getString(key);
    }
    catch (MissingResourceException e)
    {
      return '!' + key + '!';
    }
  }

  /**
   * Gets a string from the resource bundle and formats it with the argument
   * 
   * @param key
   *          the string used to get the bundle value, must not be null
   */
  public static String getFormattedString(String key, Object arg)
  {
    return MessageFormat.format(getString(key), new Object []{ arg });
  }

  /**
   * Gets a string from the resource bundle and formats it with arguments
   */
  public static String getFormattedString(String key, Object[] args)
  {
    return MessageFormat.format(getString(key), args);
  }

}