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
 * $Id: RegistryReader.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.ecore.plugin;


import java.lang.reflect.Field;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;


abstract class RegistryReader
{
  protected static final String TAG_DESCRIPTION = "description";

  protected IPluginRegistry pluginRegistry;
  String pluginID;
  String extensionPointID;

  public RegistryReader(IPluginRegistry pluginRegistry, String pluginID, String extensionPointID)
  {
    super();
    this.pluginRegistry = pluginRegistry;
    this.pluginID = pluginID;
    this.extensionPointID = extensionPointID;
  }

  /**
   * Implement this method to read element attributes. 
   * If this element has subelements, the reader will recursively cycle through them 
   * and will call this method, so don't do it here.
   */
  abstract boolean readElement(IConfigurationElement element);

  /**
   * Reads from the plugin registry and parses it.
   */
  public void readRegistry()
  {
    IExtensionPoint point = pluginRegistry.getExtensionPoint(pluginID, extensionPointID);
    if (point != null)
    {
      IConfigurationElement[] elements = point.getConfigurationElements();
      for (int i = 0; i < elements.length; i++)
      {
        internalReadElement(elements[i]);
      }
    }
  }

  private void internalReadElement(IConfigurationElement element)
  {
    boolean recognized = this.readElement(element);
    if (recognized)
    {
      IConfigurationElement[] children = element.getChildren();
      for (int i = 0; i < children.length; ++i)
      {
        internalReadElement(children[i]);
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
    IPluginDescriptor descriptor = extension.getDeclaringPluginDescriptor();
    System.err.println("Plugin " + descriptor.getUniqueIdentifier() + ", extension " + extension.getExtensionPointUniqueIdentifier());
    System.err.println(text);
  }

  /**
   * Logs a very common registry error when a required attribute is missing.
   */
  protected void logMissingAttribute(IConfigurationElement element, String attributeName)
  {
    logError(element, "The required attribute '" + attributeName + "' not defined");
  }

  static class PluginClassDescriptor 
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
        ClassLoader pluginClassLoader = element.getDeclaringExtension().getDeclaringPluginDescriptor().getPluginClassLoader();
        Class javaClass = pluginClassLoader.loadClass(element.getAttribute(attributeName));
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
  }
}
