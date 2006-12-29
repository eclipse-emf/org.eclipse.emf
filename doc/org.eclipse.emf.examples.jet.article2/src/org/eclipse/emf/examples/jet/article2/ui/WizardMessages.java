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
 * @version $Revision: 1.2 $ ($Date: 2006/12/29 18:36:19 $)
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