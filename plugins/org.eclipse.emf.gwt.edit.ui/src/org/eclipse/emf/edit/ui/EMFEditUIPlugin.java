/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: EMFEditUIPlugin.java,v 1.1 2010/08/05 19:45:20 emerks Exp $
 */
package org.eclipse.emf.edit.ui;


import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;


/**
 * The <b>Plugin</b> for the model EMF.Edit.UI library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appropriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case, emf.edit.resources.jar must be on the CLASSPATH.
 * @see #INSTANCE
 */
public final class EMFEditUIPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final EMFEditUIPlugin INSTANCE = new EMFEditUIPlugin();

  /**
   * Creates the singleton instance.
   */
  private EMFEditUIPlugin()
  {
    super(new ResourceLocator[] {});
  }

  /*
   * Javadoc copied from base class.
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return null;
  }
}
