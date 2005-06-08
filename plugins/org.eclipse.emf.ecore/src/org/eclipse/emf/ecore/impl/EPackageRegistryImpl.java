/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EPackageRegistryImpl.java,v 1.3.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * An implementation of a package registry.
 */
public class EPackageRegistryImpl extends HashMap implements EPackage.Registry
{
  public static EPackage.Registry createGlobalRegistry()
  {
    try
    {
      String className = System.getProperty("org.eclipse.emf.ecore.EPackage.Registry.INSTANCE");
      if (className == null)
      {
        if (EcorePlugin.getPlugin() == null)
        {
          return new Delegator();
        }
        else
        {
          return new EPackageRegistryImpl();
        }
      }
      else
      {
        return (EPackage.Registry)Class.forName(className).newInstance();
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
      return new EPackageRegistryImpl();
    }
  }

  protected EPackage.Registry delegateRegistry;

  public EPackageRegistryImpl()
  {
  }

  public EPackageRegistryImpl(EPackage.Registry delegateRegistry)
  {
    this.delegateRegistry = delegateRegistry;
  }

  public EPackage getEPackage(String nsURI)
  {
    Object ePackage = get(nsURI);
    if (ePackage instanceof EPackage)
    {
      EPackage result = (EPackage)ePackage;
      if (result.getNsURI() == null)
      {
        initialize(result);
      }
      return result;
    }
    else if (ePackage instanceof EPackage.Descriptor)
    {
      EPackage.Descriptor ePackageDescriptor = (EPackage.Descriptor)ePackage;
      EPackage result = ePackageDescriptor.getEPackage();
      if (result != null)
      {
        put(nsURI, result);
        initialize(result);
      }
      return result;
    }
    else
    {
      return delegatedGetEPackage(nsURI);
    }
  }

  protected void initialize(EPackage ePackage)
  {
  }

  protected EPackage delegatedGetEPackage(String nsURI)
  {
    if (delegateRegistry != null)
    {
      return delegateRegistry.getEPackage(nsURI);
    }

    return null;
  }

  public boolean containsKey(Object key)
  {
    return super.containsKey(key) || delegateRegistry != null && delegateRegistry.containsKey(key);
  }

  protected static Map classLoaderToRegistryMap = new WeakHashMap();

  public static synchronized EPackage.Registry getRegistry(ClassLoader classLoader)
  {
    EPackage.Registry result = (EPackage.Registry)classLoaderToRegistryMap.get(classLoader);
    if (result == null)
    {
      if (classLoader == null)
      {
        result = null;  
      }
      else
      {
        result = new EPackageRegistryImpl(getRegistry(classLoader.getParent()));
        classLoaderToRegistryMap.put(classLoader, result);
      }
    }
    return result;
  }

  public static class Delegator implements EPackage.Registry
  {
    protected EPackage.Registry delegateRegistry(ClassLoader classLoader)
    {
      return getRegistry(classLoader);
    }

    protected EPackage.Registry delegateRegistry()
    {
      return delegateRegistry(Thread.currentThread().getContextClassLoader());
    }

    public EPackage getEPackage(String key)
    {
      return delegateRegistry().getEPackage(key);
    }

    public int size()
    {
      return delegateRegistry().size();
    }

    public boolean isEmpty()
    {
      return delegateRegistry().isEmpty();
    }

    public boolean containsKey(Object key)
    {
      return delegateRegistry().containsKey(key);
    }

    public boolean containsValue(Object value)
    {
      return delegateRegistry().containsValue(value);
    }

    public Object get(Object key)
    {
      return delegateRegistry().get(key);
    }

    public Object put(Object key, Object value) 
    {
      Class valueClass = value.getClass();
      if (valueClass == EPackageImpl.class) 
      {
        return delegateRegistry().put(key, value);
      } 
      else 
      {
        String valueClassName = valueClass.getName();

        // Find the uppermost classloader in the hierarchy that can load the class.
        //
        ClassLoader result = Thread.currentThread().getContextClassLoader();
        for (ClassLoader classLoader = result.getParent(); classLoader != null; classLoader = classLoader.getParent())
        {
          try 
          {
            Class loadedClass = classLoader.loadClass(valueClassName);
            if (loadedClass == valueClass) 
            {
              result = classLoader;
            } 
            else 
            {
              // The class address was not equal, so we don't want this classloader,
              // but instead want the last result that was able to load the class.
              //
              break;
            }
          } 
          catch (ClassNotFoundException exception) 
          {
            // We can't find the class, so we don't want this classloader,
            // but instead want the last result that was able to load the class.
            //
            break;
          }
        }

        // Register with the upper most classloader that's able to load the class.
        //
        return delegateRegistry(result).put(key, value);
      }
    }

    public Object remove(Object key)
    {
      return delegateRegistry().remove(key);
    }

    public void putAll(Map map)
    {
      for (Iterator i = map.entrySet().iterator(); i.hasNext(); )
      {
        Map.Entry entry = (Map.Entry)i.next();
        put(entry.getKey(), entry.getValue());
      }
    }

    public void clear()
    {
      delegateRegistry().clear();
    }

    public Set keySet()
    {
      return delegateRegistry().keySet();
    }

    public Collection values()
    {
      return delegateRegistry().values();
    }

    public Set entrySet()
    {
      return delegateRegistry().entrySet();
    }
  }
}
