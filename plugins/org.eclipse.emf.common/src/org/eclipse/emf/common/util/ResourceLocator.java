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
 * $Id: ResourceLocator.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.util;


import java.net.URL;


/**
 * A locator of Java resources.
 */
public interface ResourceLocator
{
  /** 
   * Returns the URL from which all resources are based.
   * @return the URL from which all resources are based.
   */
  URL getBaseURL();

  /**
   * Returns the description that can be used to create the image resource associated with the key.
   * The description will typically be in the form of a URL to the image data.
   * Creation of an actual image depends on the GUI environment;
   * within Eclipse, org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry can be used.
   * @param key the key of the image resource.
   * @return the description on the image resource.
   */
  Object getImage(String key);

  /**
   * Returns the string resource associated with the key.
   * @param key the key of the string resource.
   * @return the string resource associated with the key.
   */
  String getString(String key);

  /**
   * Returns a string resource associated with the key, and peforms substitutions.
   * @param key the key of the string.
   * @param substitutions the message substitutions.
   * @return a string resource associated with the key.
   * @see #getString(String)
   * @see java.text.MessageFormat#format(String, Object[])
   */
  String getString(String key, Object [] substitutions);
}
