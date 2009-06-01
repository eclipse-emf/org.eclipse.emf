/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Activator.java,v 1.3 2009/06/01 17:19:26 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.emf.examples.databinding.project.core.IModelLoadingService;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements ServiceListener
{

  /**
   * The plug-in ID
   */
  public static final String PLUGIN_ID = "org.eclipse.emf.examples.databinding.project.ui.rcp";

  // The shared instance
  private static Activator plugin;

  private ServiceTracker tracker;

  private IModelLoadingService loadingService;

  private BundleContext context;

  /**
   * The constructor
   */
  public Activator()
  {
  }

  @Override
  public void start(BundleContext context) throws Exception
  {
    super.start(context);
    this.context = context;
    plugin = this;

    tracker = new ServiceTracker(context, IModelLoadingService.class.getName(), null);
    tracker.open();

    loadingService = (IModelLoadingService)tracker.getService();

    context.addServiceListener(this, "(objectclass=" + IModelLoadingService.class.getName() + ")");
  }

  @Override
  public void stop(BundleContext context) throws Exception
  {
    plugin = null;
    tracker.close();
    tracker = null;
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

  /**
   * Returns an image descriptor for the image file at the given
   * plug-in relative path
   *
   * @param path the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path)
  {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }

  /**
   * Load the model resource
   * @param uri the uri
   * @return the resource
   */
  public IModelResource loadResource(String uri)
  {
    if (loadingService != null)
    {
      return loadingService.findAndLoadResource(uri);
    }
    else
    {
      getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Model loading service not available"));
    }

    return null;
  }

  public void serviceChanged(ServiceEvent event)
  {
    ServiceReference sr = event.getServiceReference();
    switch (event.getType())
    {
      case ServiceEvent.REGISTERED: {
        loadingService = (IModelLoadingService)context.getService(sr);
      }
        break;
      case ServiceEvent.UNREGISTERING: {
        loadingService = null;
      }
        break;
    }
  }
}
