/**
 * Copyright (c) 2004-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ant;

import org.eclipse.core.runtime.Plugin;


/**
 * @since 2.1.0
 */
public class EMFAntPlugin extends Plugin
{
  //The shared instance.
  private static EMFAntPlugin plugin;

  public EMFAntPlugin()
  {
    super();
    plugin = this;
  }

  /**
   * Returns the shared instance.
   */
  public static EMFAntPlugin getDefault()
  {
    return plugin;
  }
}