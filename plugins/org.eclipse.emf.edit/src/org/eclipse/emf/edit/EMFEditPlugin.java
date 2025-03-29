/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IPropertyEditorFactory;
import org.eclipse.emf.edit.provider.IPropertyEditorFactory.Descriptor;
import org.eclipse.emf.edit.provider.IPropertyEditorFactory.Registry;
import org.osgi.framework.BundleActivator;


/**
 * The <b>Plugin</b> for the model EMF.Edit library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appropriate to the runtime.
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
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }
  
  /**
   * The singleton instance of an {@link org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
   */
  private static ComposedAdapterFactory.Descriptor.Registry.Impl composedAdapterFactoryDescriptorRegistry;

  /**
   * Returns a populated instance of an {@link org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry item provider adapter factory registry}.
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
              else if (key instanceof Package)
              {
                stringTypes.add(((Package)key).getName());
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
      if (INSTANCE.getPluginResourceLocator() instanceof EclipsePlugin)
      {
        RegistryReader registryReader = 
           new RegistryReader(Platform.getExtensionRegistry(), INSTANCE.getSymbolicName(), "itemProviderAdapterFactories")
           {
             @Override
             protected boolean readElement(IConfigurationElement element, boolean add)
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
                   List<Object> key = new ArrayList<Object>();
                   key.add(packageURI);
                   key.add(supportedType);
                   if (add)
                   {
                     composedAdapterFactoryDescriptorRegistry.put(key, new PluginAdapterFactoryDescriptor(element, "class"));
                   }
                   else
                   {
                     composedAdapterFactoryDescriptorRegistry.remove(key);
                   }
                 }
                 
                 return true;
               }
               return false;
             }
           };
        registryReader.readRegistry();
      }
    }
    return composedAdapterFactoryDescriptorRegistry;
  }

  /**
   * The singleton instance of a {@link org.eclipse.emf.edit.provider.IChildCreationExtender.Descriptor.Registry child creation extender registry}.
   */
  private static IChildCreationExtender.Descriptor.Registry.Impl childCreationExtenderDescriptorRegistry;

  /**
   * Returns a populated instance of a {@link org.eclipse.emf.edit.provider.IChildCreationExtender.Descriptor.Registry child creation extender registry}.
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
      if (INSTANCE.getPluginResourceLocator() instanceof EclipsePlugin)
      {
        RegistryReader registryReader = 
           new RegistryReader(Platform.getExtensionRegistry(), INSTANCE.getSymbolicName(), "childCreationExtenders")
           {
             @Override
             protected boolean readElement(IConfigurationElement element, boolean add)
             {
               if (element.getName().equals("extender"))
               {
                 String packageURI = element.getAttribute("uri");
                 String className = element.getAttribute("class");
                 if (packageURI == null)
                 {
                   logMissingAttribute(element, "uri");
                 }
                 else if (className == null)
                 {
                   logMissingAttribute(element, "class");
                 }

                 class PluginChildCreationExtenderDescriptor extends PluginClassDescriptor implements IChildCreationExtender.Descriptor
                 {
                   protected String contributor;

                   public PluginChildCreationExtenderDescriptor(IConfigurationElement element, String attributeName)
                   {
                     super(element, attributeName);
                     contributor = element.getContributor().getName();
                   }

                   public IChildCreationExtender createChildCreationExtender()
                   {
                     return (IChildCreationExtender)createInstance();
                   }

                   public boolean matches(IConfigurationElement element)
                   {
                     return element.getContributor().getName().equals(contributor);
                   }
                 }

                 Collection<IChildCreationExtender.Descriptor> collection = childCreationExtenderDescriptorRegistry.get(packageURI);
                 if (add)
                 {
                   if (collection == null)
                   {
                     childCreationExtenderDescriptorRegistry.put(packageURI, collection = new ArrayList<IChildCreationExtender.Descriptor>());
                   }

                   collection.add(new PluginChildCreationExtenderDescriptor(element, "class"));
                 }
                 else if (collection != null)
                 {
                   for (IChildCreationExtender.Descriptor descriptor : collection)
                   {
                     if (descriptor instanceof PluginChildCreationExtenderDescriptor && ((PluginChildCreationExtenderDescriptor)descriptor).matches(element))
                     {
                       collection.remove(descriptor);
                       break;
                     }
                   }
                 }

                 return true;
               }
               return false;
             }
           };
        registryReader.readRegistry();
      }
    }
    return childCreationExtenderDescriptorRegistry;
  }

  public static final class PropertyEditorFactoryRegistryImpl extends CommonPlugin.SimpleTargetPlatformRegistryImpl<URI, Object> implements Registry
  {
    private static final long serialVersionUID = 1L;

    public Set<URI> getTargetPlatformFactories()
    {
      return getTargetPlatformValues("org.eclipse.emf.edit.propertyEditorFactories", "uri");
    }

    @Override
    protected URI createKey(String attribute)
    {
      return URI.createURI(attribute);
    }

    protected IPropertyEditorFactory basicGetPropertyEditorFactory(Object key)
    {
      Object propertyEditorFactory = super.get(key);
      if (propertyEditorFactory instanceof Descriptor)
      {
        Descriptor propertyEditoFactoryDescriptor = (Descriptor)propertyEditorFactory;
        propertyEditorFactory = propertyEditoFactoryDescriptor.getPropertyEditorFactory();
        put((URI)key, propertyEditorFactory);
        return (IPropertyEditorFactory)propertyEditorFactory;
      }
      else
      {
        return (IPropertyEditorFactory)propertyEditorFactory;
      }
    }

    public IPropertyEditorFactory getPropertyEditorFactory(URI propertyEditorSpecification)
    {
      IPropertyEditorFactory result = null;
      if (propertyEditorSpecification != null)
      {
        URI baseURI = propertyEditorSpecification.trimQuery().trimFragment();
        for (int i = 0, count = baseURI.segmentCount(); i <= count && result == null; ++i)
        {
          URI uri = baseURI.trimSegments(i);
          result = (IPropertyEditorFactory)basicGetPropertyEditorFactory(uri);
        }
      }
      return result;
    }

    public IPropertyEditorFactory getPropertyEditorFactory(Object propertyEditorSpecification)
    {
      if (propertyEditorSpecification instanceof URI)
      {
        return getPropertyEditorFactory((URI)propertyEditorSpecification);
      }
      else if (propertyEditorSpecification instanceof IPropertyEditorFactory)
      {
        return (IPropertyEditorFactory)propertyEditorSpecification;
      }
      else if (propertyEditorSpecification instanceof String)
      {
        return getPropertyEditorFactory(URI.createURI(propertyEditorSpecification.toString()));
      }
      else
      {
        return null;
      }
    }
  }
  
  /**
   * The singleton instance of a {@link org.eclipse.emf.edit.provider.IPropertyEditorFactory.Registry property editor factory registry}.
   */
  private static PropertyEditorFactoryRegistryImpl propertyEditorFactoryRegistry;

  /**
   * Returns a populated instance of a {@link org.eclipse.emf.edit.provider.IPropertyEditorFactory.Registry property editor factory registry}.
   * @return a populated instance of property editor factory registry.
   * @since 2.14
   */
  public static IPropertyEditorFactory.Registry getPropertyEditorFactoryRegistry()
  {
    if (propertyEditorFactoryRegistry == null)
    {
      propertyEditorFactoryRegistry = new PropertyEditorFactoryRegistryImpl();
      if (INSTANCE.getPluginResourceLocator() instanceof EclipsePlugin)
      {
        RegistryReader registryReader = new RegistryReader(Platform.getExtensionRegistry(), INSTANCE.getSymbolicName(), "propertyEditorFactories")
          {
            @Override
            protected boolean readElement(IConfigurationElement element, boolean add)
            {
              if (element.getName().equals("factory"))
              {
                String propertyEditorFactoryValue = element.getAttribute("uri");
                String className = element.getAttribute("class");
                if (propertyEditorFactoryValue == null)
                {
                  logMissingAttribute(element, "uri");
                }
                else if (className == null)
                {
                  logMissingAttribute(element, "class");
                }

                if (propertyEditorFactoryValue != null && className != null)
                {
                  class PluginPropertyEditorFactoryDescriptor extends PluginClassDescriptor implements IPropertyEditorFactory.Descriptor
                  {
                    public PluginPropertyEditorFactoryDescriptor(IConfigurationElement element)
                    {
                      super(element, "class");
                    }

                    public IPropertyEditorFactory getPropertyEditorFactory()
                    {
                      return (IPropertyEditorFactory)createInstance();
                    }

                    public IConfigurationElement getElement()
                    {
                      return element;
                    }
                  }

                  URI propertyEditorFactoryURI = URI.createURI(propertyEditorFactoryValue);
                  Object propertyEditorFactoryDescriptor = propertyEditorFactoryRegistry.get(propertyEditorFactoryURI);
                  if (add)
                  {
                    Object previous = propertyEditorFactoryRegistry.put(propertyEditorFactoryURI, new PluginPropertyEditorFactoryDescriptor(element));
                    if (previous instanceof PluginPropertyEditorFactoryDescriptor)
                    {
                      PluginPropertyEditorFactoryDescriptor descriptor = (PluginPropertyEditorFactoryDescriptor)previous;
                      EMFEditPlugin.INSTANCE.log(
                        "Both '" + descriptor.getElement().getContributor().getName() + "' and '" + element.getContributor().getName()
                          + "' register a property editor factory for '" + propertyEditorFactoryURI + "'");
                    }
                  }
                  else if (propertyEditorFactoryDescriptor != null)
                  {
                    propertyEditorFactoryRegistry.remove(propertyEditorFactoryURI);
                  }

                  return true;
                }
              }
              return false;
            }
          };
        registryReader.readRegistry();
      }
    }
    return propertyEditorFactoryRegistry;
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

    /**
     * @since 2.10
     */
    public static class Activator extends EMFPlugin.OSGiDelegatingBundleActivator
    {
      @Override
      protected BundleActivator createBundle()
      {
        return new Implementation();
      }
    }
  }
}
