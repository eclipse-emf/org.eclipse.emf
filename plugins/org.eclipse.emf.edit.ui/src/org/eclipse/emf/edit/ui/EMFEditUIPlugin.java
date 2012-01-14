/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.emf.edit.ui.util.QueryDelegateTextViewer;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


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
   * The one instance of this class.
   */
  private static Implementation plugin;

  public static final String QUERY_DELEGATE_TEXT_VIEWER_FACTORIES_PPID = "queryDelegateTextViewerFactories";

  /**
   * Creates the singleton instance.
   */
  private EMFEditUIPlugin()
  {
    super(new ResourceLocator []{});
  }

  /*
   * Javadoc copied from base class.
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * @return the singleton instance.
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>.
   */
  public static class Implementation extends EclipseUIPlugin
  {
    /**
     * Creates an instance.
     */
    public Implementation()
    {
      super();

      // Remember the static instance.
      //
      plugin = this;
    }

    /**
     * Starts up this plugin by reading some extensions and populating the relevant registries.
     * <p>
     * @throws Exception if there is a show stopping problem.
     */
    @Override
    public void start(BundleContext context) throws Exception
    {
      super.start(context);

      Bundle bundle = Platform.getBundle("org.eclipse.jface.text");
      if (bundle != null && (bundle.getState() & (Bundle.ACTIVE | Bundle.STARTING | Bundle.RESOLVED)) != 0)
      {
        new RegistryReader(
          Platform.getExtensionRegistry(),
          EMFEditUIPlugin.getPlugin().getBundle().getSymbolicName(),
          QUERY_DELEGATE_TEXT_VIEWER_FACTORIES_PPID)
          {
            @Override
            protected boolean readElement(IConfigurationElement element, boolean add)
            {
              if (element.getName().equals("factory"))
              {
                String uri = element.getAttribute("uri");
                if (uri == null)
                {
                  logMissingAttribute(element, "uri");
                }
                else if (element.getAttribute("class") == null)
                {
                  logMissingAttribute(element, "class");
                }

                class FactoryDescriptor extends PluginClassDescriptor implements QueryDelegateTextViewer.Factory.Descriptor
                {
                  protected QueryDelegateTextViewer.Factory factory;

                  public FactoryDescriptor(IConfigurationElement e, String attrName)
                  {
                    super(e, attrName);
                  }

                  public QueryDelegateTextViewer.Factory getFactory()
                  {
                    if (factory == null)
                    {
                      factory = (QueryDelegateTextViewer.Factory)createInstance();
                    }
                    return factory;
                  }

                  public IConfigurationElement getElement()
                  {
                    return element;
                  }
                }

                if (add)
                {
                  Object previous = QueryDelegateTextViewer.Factory.Registry.INSTANCE.put(uri, new FactoryDescriptor(element, "class"));
                  if (previous instanceof FactoryDescriptor)
                  {
                    FactoryDescriptor descriptor = (FactoryDescriptor)previous;
                    EMFEditUIPlugin.INSTANCE.log("Both '" + descriptor.getElement().getContributor().getName() + "' and '"
                      + element.getContributor().getName() + "' register a query delegate text viewer factory for '" + uri + "'");
                  }
                  return true;
                }
                else
                {
                  QueryDelegateTextViewer.Factory.Registry.INSTANCE.remove(uri);
                  return true;
                }
              }
              return false;
            }
          }.readRegistry();
      }
    }
  }
}
