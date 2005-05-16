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
 * $Id: EMFEditPlugin.java,v 1.4 2005/05/16 18:13:45 emerks Exp $
 */
package org.eclipse.emf.edit;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;


/**
 * The <b>Plugin</b> for the model EMF.Edit library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appopriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case, emf.edit.resources.jar must be on the CLASSPATH.
 * @see #INSTANCE
 */
public final class EMFEditPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final EMFEditPlugin INSTANCE = new EMFEditPlugin();

  /**
   * The one instance of this class.
   */
  private static Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private EMFEditPlugin()
  {
    super(new ResourceLocator[] {});
  }

  /*
   * Javadoc copied from base class.
   */
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }
  
  /**
   * Returns a populated instance of an {@link ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
   * @return a populated instance of an item provider adapter factory registry.
   */
  public static ComposedAdapterFactory.Descriptor.Registry getComposedAdapterFactoryDescriptorRegistry()
  {
    final ComposedAdapterFactory.Descriptor.Registry.Impl result =  
      new ComposedAdapterFactory.Descriptor.Registry.Impl(null)
      {
        public ComposedAdapterFactory.Descriptor delegatedGetDescriptor(Collection types)
        {
          List stringTypes = new ArrayList(types.size());
          for (Iterator i = types.iterator(); i.hasNext(); )
          {
            Object key = i.next();
            if (key instanceof EPackage)
            {
              stringTypes.add(((EPackage)key).getNsURI());
            }
            else if (key instanceof Package)
            {
              stringTypes.add(((Package)key).getName());
            }
            else if (key instanceof Class)
            {
              stringTypes.add(((Class)key).getName());
            }
            else
            {
              return null;
            }
          }
          ComposedAdapterFactory.Descriptor descriptor = (ComposedAdapterFactory.Descriptor)get(stringTypes);
          if (descriptor != null)
          {
            put(types, descriptor);
            return descriptor;
          }
          
          return super.delegatedGetDescriptor(types);
        }
      };
    if (INSTANCE.getPluginResourceLocator() instanceof EclipsePlugin)
    {
      RegistryReader registryReader = 
         new RegistryReader(Platform.getExtensionRegistry(), INSTANCE.getSymbolicName(), "itemProviderAdapterFactories")
         {
           protected boolean readElement(IConfigurationElement element)
           {
             if (element.getName().equals("factory"))
             {
               String packageURI = element.getAttribute("uri");
               String className = element.getAttribute("class");
               String supportedTypes = element.getAttribute("supportedTypes");
               if (packageURI == null)
               {
                 logMissingAttribute(element, "uri");
               }
               else if (className == null)
               {
                 logMissingAttribute(element, "class");
               }
               else if (supportedTypes == null)
               {
                 logMissingAttribute(element, "supportedTypes");
               }
               
               class PluginAdapterFactoryDescriptor extends PluginClassDescriptor implements ComposedAdapterFactory.Descriptor
               {
                 public PluginAdapterFactoryDescriptor(IConfigurationElement element, String attributeName)
                 {
                   super(element, attributeName);
                 }
                 
                 public AdapterFactory createAdapterFactory()
                 {
                   return (AdapterFactory)createInstance();
                 }
               }
               
               for (StringTokenizer stringTokenizer = new StringTokenizer(supportedTypes); stringTokenizer.hasMoreTokens(); )
               {
                 String supportedType = stringTokenizer.nextToken();
                 List key = new ArrayList();
                 key.add(packageURI);
                 key.add(supportedType);
                 result.put(key, new PluginAdapterFactoryDescriptor(element, "class"));
               }
               
               return true;
             }
             return false;
           }
         };
      registryReader.readRegistry();
    }
    return result;
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
  public static class Implementation extends EclipsePlugin 
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
  }
}
