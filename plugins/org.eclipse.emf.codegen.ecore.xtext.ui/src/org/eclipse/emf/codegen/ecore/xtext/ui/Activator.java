/**
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.ecore.xtext.ui;


import org.eclipse.emf.codegen.ecore.xtext.GenModelRuntimeModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

  private static final Logger logger = LoggerFactory.getLogger(Activator.class);

  // The plug-in ID
  public static final String PLUGIN_ID = "org.eclipse.emf.codegen.ecore.xtext.ui"; //$NON-NLS-1$

  // The shared instance
  private static Activator plugin;

  private Injector injector;

  /**
   * The constructor
   */
  public Activator()
  {
  }

  public Injector getInjector()
  {
    return injector;
  }

  private void initializeEcoreInjector()
  {
    injector = Guice.createInjector(Modules.override(Modules.override(new GenModelRuntimeModule()).with(new GenModelUIModule(plugin))).with(
      new SharedStateModule()));
  }

  @Override
  public void start(BundleContext context) throws Exception
  {
    super.start(context);
    plugin = this;
    try
    {
      initializeEcoreInjector();
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public void stop(BundleContext context) throws Exception
  {
    plugin = null;
    injector = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static Activator getDefault()
  {
    return plugin;
  }

}
