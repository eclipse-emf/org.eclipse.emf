/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * ImporterUtil.java,v 1.9 2005/12/14 07:48:49 marcelop Exp
 */
package org.eclipse.emf.importer.util;


/**
 * Utility methods and classes.  This class cannot import UI code because it is used on headless
 * scenarios. 
 * 
 * @since 2.1.0
 */
public class ImporterUtil
{
  public static String validPluginID(String base)
  {
    StringBuffer sb = new StringBuffer(base);
    for (int i = sb.length() - 1; i >= 0; i--)
    {
      char c = sb.charAt(i);
      if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '.')
      {
        //do nothing
      }
      else if (c == ' ')
      {
        sb.deleteCharAt(i);
      }
      else if (c == '-')
      {
        sb.setCharAt(i, '_');
      }
    }
    return sb.toString();
  }
}
