/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


public abstract class RegistryReader
{
  protected static final String TAG_DESCRIPTION = "description";

  protected IExtensionRegistry pluginRegistry;
  String pluginID;
  String extensionPointID;
  String qualifiedExtensionPointID;

  public RegistryReader(IExtensionRegistry pluginRegistry, String pluginID, String extensionPointID)
  {
    super();
    this.pluginRegistry = pluginRegistry;
    this.pluginID = pluginID;
    this.extensionPointID = extensionPointID;
    qualifiedExtensionPointID = pluginID + "." + extensionPointID;
  }

  /**
   * Implement this method to read element attributes. 
   * If this element has child elements, the reader will recursively cycle through them 
   * and will call this method, so don't do it here.
   * If you want to support removing entries, override {@link #readElement(IConfigurationElement, boolean)} instead.
   */
  protected boolean readElement(IConfigurationElement element)
  {
    return false;
  }

  /**
   * Implement this method to read element attributes for the purpose of adding or removing their registrations. 
   * If this element has child elements, the reader will recursively cycle through them 
   * and will call this method, so don't do it here.
   */
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    return add && readElement(element);
  }

  /**
   * Reads from the plugin registry and parses it.
   */
  public void readRegistry()
  {
    final IExtensionPoint point = pluginRegistry.getExtensionPoint(pluginID, extensionPointID);
    if (point != null)
    {
      IConfigurationElement[] elements = point.getConfigurationElements();
      for (int i = 0; i < elements.length; i++)
      {
        internalReadElement(elements[i], true);
      }
  
      pluginRegistry.addRegistryChangeListener
        (new IRegistryChangeListener()
         {
           public void registryChanged(IRegistryChangeEvent event)
           {
             IExtensionDelta[] deltas = event.getExtensionDeltas();
             for (int i = 0; i < deltas.length; ++i) 
             {
               IExtensionDelta delta = deltas[i];
               if (point.equals(delta.getExtensionPoint()))
               {
                 boolean add = delta.getKind() == IExtensionDelta.ADDED;
                 IExtension extension = delta.getExtension();
                 IConfigurationElement[] configurationElement = extension.getConfigurationElements();
                 for (int j = 0; j < configurationElement.length; ++j) 
                 {
                   internalReadElement(configurationElement[j], add);
                 }
               }
             }
           }
         });
      }
  }

  private void internalReadElement(IConfigurationElement element, boolean add)
  {
    boolean recognized = readElement(element, add);
    if (recognized)
    {
      IConfigurationElement[] children = element.getChildren();
      for (int i = 0; i < children.length; ++i)
      {
        internalReadElement(children[i], add);
      }
    }
    else
    {
      logError(element, "Error processing extension: " + element);
    }
  }

  /**
   * Logs the error in the desktop log using the provided
   * text and the information in the configuration element.
   */
  protected void logError(IConfigurationElement element, String text)
  {
    IExtension extension = element.getDeclaringExtension();
    System.err.println("Plugin " + extension.getContributor().getName() + ", extension " + extension.getExtensionPointUniqueIdentifier());
    System.err.println(text);
  }

  /**
   * Logs a very common registry error when a required attribute is missing.
   */
  protected void logMissingAttribute(IConfigurationElement element, String attributeName)
  {
    logError(element, "The required attribute '" + attributeName + "' not defined");
  }

  public static class PluginClassDescriptor 
  {
    protected IConfigurationElement element;
    protected String attributeName;

    public PluginClassDescriptor(IConfigurationElement element, String attributeName)
    {
      this.element = element;
      this.attributeName = attributeName;
    }

    public Object createInstance()
    {
      try
      {
        return element.createExecutableExtension(attributeName);
      }
      catch (CoreException e)
      {
        throw new WrappedException(e);
      }
    }
  }

  static class ResourceFactoryDescriptor extends PluginClassDescriptor implements Resource.Factory.Descriptor
  {
    protected Resource.Factory factoryInstance;

    public ResourceFactoryDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public Resource.Factory createFactory()
    {
      if (factoryInstance == null)
      {
        factoryInstance = (Resource.Factory)createInstance();
      }
      return factoryInstance;
    }
  }

  static class EPackageDescriptor extends PluginClassDescriptor implements EPackage.Descriptor
  {
    static class Dynamic extends EPackageDescriptor
    {
      protected static ResourceSet resourceSet =
        new ResourceSetImpl()
        {
          protected Set<URI> uris = new HashSet<URI>();

          @Override
          protected Resource delegatedGetResource(URI uri, boolean loadOnDemand)
          {
            try
            {
              return uris.add(uri) ? super.delegatedGetResource(uri, loadOnDemand) : null;
            }
            finally
            {
              uris.remove(uri);
            }
          }
        };

      public Dynamic(IConfigurationElement element, String attributeName)
      {
        super(element, attributeName);
      }

      @Override
      public EPackage getEPackage()
      {
        // First try to see if this class has an eInstance 
        //
        try
        {
          String location = element.getAttribute(attributeName);
          if (location != null)
          {
            URI locationURI = URI.createURI(location);
            if (locationURI.isRelative())
            {
              String fragment = locationURI.fragment();
              locationURI = URI.createPlatformPluginURI(element.getDeclaringExtension().getContributor().getName() + "/" + locationURI.trimFragment().toString(), true);
              if (fragment != null)
              {
                locationURI = locationURI.appendFragment(fragment);
              }
            }
            if (!locationURI.hasFragment())
            {
              locationURI = locationURI.appendFragment("/");
            }
            return (EPackage)resourceSet.getEObject(locationURI, true);
          }
          else
          {
            throw new RuntimeException("No location attribute was specified.");
          }
        }
        catch (Exception e)
        {
          throw new WrappedException(e);
        }
      }
    }

    public EPackageDescriptor(IConfigurationElement element, String attributeName)
    {
      super(element, attributeName);
    }

    public EPackage getEPackage()
    {
      // First try to see if this class has an eInstance 
      //
      try
      {
        Class<?> javaClass = CommonPlugin.loadClass(element.getDeclaringExtension().getContributor().getName(), element.getAttribute(attributeName));
        Field field = javaClass.getField("eINSTANCE");
        Object result = field.get(null);
        return (EPackage)result;
      }
      catch (ClassNotFoundException e)
      {
        throw new WrappedException(e);
      }
      catch (IllegalAccessException e)
      {
        throw new WrappedException(e);
      }
      catch (NoSuchFieldException e)
      {
        throw new WrappedException(e);
      }
    }
    
    public EFactory getEFactory()
    {
      return null;
    }
  }
  
  static class EFactoryDescriptor extends PluginClassDescriptor implements EPackage.Descriptor
  {
    protected EPackage.Descriptor overridenDescriptor;
    
    public EFactoryDescriptor(IConfigurationElement element, String attributeName, EPackage.Descriptor overridenDescriptor)
    {
      super(element, attributeName);
      this.overridenDescriptor = overridenDescriptor;
    }

    public EPackage getEPackage()
    {
      return overridenDescriptor.getEPackage();
    }
    
    public EFactory getEFactory()
    {
      // First try to see if this class has an eInstance 
      //
      try
      {
        Class<?> javaClass = CommonPlugin.loadClass(element.getDeclaringExtension().getContributor().getName(), element.getAttribute(attributeName));
        return (EFactory)javaClass.newInstance();
      }
      catch (ClassNotFoundException e)
      {
        throw new WrappedException(e);
      }
      catch (IllegalAccessException e)
      {
        throw new WrappedException(e);
      }
      catch (InstantiationException e)
      {
        throw new WrappedException(e);
      }
    }

    public EPackage.Descriptor getOverridenDescriptor()
    {
      return overridenDescriptor;
    }
  }
}
