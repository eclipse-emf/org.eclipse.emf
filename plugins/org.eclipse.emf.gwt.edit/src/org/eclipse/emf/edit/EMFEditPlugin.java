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
 * $Id: EMFEditPlugin.java,v 1.1 2010/04/28 14:48:45 emerks Exp $
 */
package org.eclipse.emf.edit;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;


/**
 * The <b>Plugin</b> for the model EMF.Edit library.
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
public final class EMFEditPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final EMFEditPlugin INSTANCE = new EMFEditPlugin();


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
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return null;
  }
  
  /**
   * The singleton instance of an {@link ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
   */
  private static ComposedAdapterFactory.Descriptor.Registry.Impl composedAdapterFactoryDescriptorRegistry;

  /**
   * Returns a populated instance of an {@link ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
   * @return a populated instance of an item provider adapter factory registry.
   */
  public static ComposedAdapterFactory.Descriptor.Registry getComposedAdapterFactoryDescriptorRegistry()
  {
    if (composedAdapterFactoryDescriptorRegistry == null)
    {
      composedAdapterFactoryDescriptorRegistry = 
        new ComposedAdapterFactory.Descriptor.Registry.Impl(null)
        {
          private static final long serialVersionUID = 1L;
  
          @Override
          public ComposedAdapterFactory.Descriptor delegatedGetDescriptor(Collection<?> types)
          {
            List<Object> stringTypes = new ArrayList<Object>(types.size());
            for (Object key : types)
            {
              if (key instanceof EPackage)
              {
                stringTypes.add(((EPackage)key).getNsURI());
              }
              else if (key instanceof Class<?>)
              {
                stringTypes.add(((Class<?>)key).getName());
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
    }
    return composedAdapterFactoryDescriptorRegistry;
  }

  /**
   * The singleton instance of a {@link IChildCreationExtender.Descriptor.Registry child creation extender registry}.
   */
  private static IChildCreationExtender.Descriptor.Registry.Impl childCreationExtenderDescriptorRegistry;

  /**
   * Returns a populated instance of a {@link IChildCreationExtender.Descriptor.Registry child creation extender registry}.
   * @return a populated instance of child creation extender registry.
   */
  public static IChildCreationExtender.Descriptor.Registry getChildCreationExtenderDescriptorRegistry()
  {
    if (childCreationExtenderDescriptorRegistry == null)
    {
      childCreationExtenderDescriptorRegistry = 
        new IChildCreationExtender.Descriptor.Registry.Impl(null)
        {
          private static final long serialVersionUID = 1L;
  
          @Override
          public Collection<IChildCreationExtender.Descriptor> delegatedGetDescriptors(String namespace)
          {
            Collection<IChildCreationExtender.Descriptor> descriptors = get(namespace);
            return descriptors != null ? descriptors : super.delegatedGetDescriptors(namespace);
          }
        };
    }
    return childCreationExtenderDescriptorRegistry;
  }
}
