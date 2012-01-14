/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.impl;


import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * An implementation of a package registry that can delegate failed lookup to another registry.
 */
public class EPackageRegistryImpl extends HashMap<String, Object> implements EPackage.Registry
{
  private static final long serialVersionUID = 1L;

  /**
   * Creates the {@link EPackage.Registry#INSTANCE instance} of the global registry.
   * If a {@link System#getSecurityManager() security manager} is active,
   * and <code>"classLoader"</code> {@link RuntimePermission permission} is not granted,
   * a secure delegator instance is created,
   * i.e., a private registry implementation that securely accesses class loaders 
   * and keeps them private, will be used.
   */
  public static EPackage.Registry createGlobalRegistry()
  {
    try
    {
      String className = System.getProperty("org.eclipse.emf.ecore.EPackage.Registry.INSTANCE");
      if (className == null)
      {
        if (EcorePlugin.getDefaultRegistryImplementation() != null)
        {
          return EcorePlugin.getDefaultRegistryImplementation();
        }
        else if (!EMFPlugin.IS_ECLIPSE_RUNNING)
        {
          try
          {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null)
            {
              securityManager.checkPermission(new RuntimePermission("classLoader"));
            }
            return new Delegator();
          }
          catch (Throwable throwable)
          {
            return new SecureDelegator();
          }
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

  /** 
   * The delegate registry.
   */
  protected EPackage.Registry delegateRegistry;

  /**
   * Creates a non-delegating instance.
   */
  public EPackageRegistryImpl()
  {
    super();
  }

  /**
   * Creates a delegating instance.
   */
  public EPackageRegistryImpl(EPackage.Registry delegateRegistry)
  {
    this.delegateRegistry = delegateRegistry;
  }

  /*
   * Javadoc copied from interface.
   */
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
        if (result.getNsURI() == null)
        {
          initialize(result);
        }
        else
        {
          put(nsURI, result);
        }
      }
      return result;
    }
    else
    {
      return delegatedGetEPackage(nsURI);
    }
  }

  /*
   * Javadoc copied from interface.
   */
  public EFactory getEFactory(String nsURI)
  {
    Object ePackage = get(nsURI);
    if (ePackage instanceof EPackage)
    {
      EPackage result = (EPackage)ePackage;
      if (result.getNsURI() == null)
      {
        initialize(result);
      }
      return result.getEFactoryInstance();
    }
    else if (ePackage instanceof EPackage.Descriptor)
    {
      EPackage.Descriptor ePackageDescriptor = (EPackage.Descriptor)ePackage;
      EFactory result = ePackageDescriptor.getEFactory();
      return result;
    }
    else
    {
      return delegatedGetEFactory(nsURI);
    }
  }

  /**
   * Creates a delegating instance.
   */
  protected void initialize(EPackage ePackage)
  {
    // Do nothing.
  }

  /**
   * Returns the package from the delegate registry, if there is one.
   * @return the package from the delegate registry.
   */
  protected EPackage delegatedGetEPackage(String nsURI)
  {
    if (delegateRegistry != null)
    {
      return delegateRegistry.getEPackage(nsURI);
    }

    return null;
  }

  /**
   * Returns the factory from the delegate registry, if there is one.
   * @return the factory from the delegate registry.
   */
  protected EFactory delegatedGetEFactory(String nsURI)
  {
    if (delegateRegistry != null)
    {
      return delegateRegistry.getEFactory(nsURI);
    }

    return null;
  }

  /**
   * Returns whether this map or the delegate map contains this key. Note that
   * if there is a delegate map, the result of this method may
   * <em><b>not</b></em> be the same as <code>keySet().contains(key)</code>.
   * @param key the key whose presence in this map is to be tested.
   * @return whether this map or the delegate map contains this key.
   */
  @Override
  public boolean containsKey(Object key)
  {
    return super.containsKey(key) || delegateRegistry != null && delegateRegistry.containsKey(key);
  }

  /**
   * A map from class loader to its associated registry.
   */
  protected static Map<ClassLoader, EPackage.Registry> classLoaderToRegistryMap = new WeakHashMap<ClassLoader, EPackage.Registry>();

  /**
   * Returns the package registry associated with the given class loader.
   * @param classLoader the class loader.
   * @return the package registry associated with the given class loader.
   */
  public static synchronized EPackage.Registry getRegistry(ClassLoader classLoader)
  {
    EPackage.Registry result = classLoaderToRegistryMap.get(classLoader);
    if (result == null)
    {
      if (classLoader != null)
      {
        result = new EPackageRegistryImpl(getRegistry(classLoader.getParent()));
        classLoaderToRegistryMap.put(classLoader, result);
      }
    }
    return result;
  }

  /**
   * A package registry implementation that delegates to a class loader specific registry.
   */
  public static class Delegator implements EPackage.Registry
  {
    protected EPackage.Registry delegateRegistry(ClassLoader classLoader)
    {
      return getRegistry(classLoader);
    }

    protected EPackage.Registry delegateRegistry()
    {
      return delegateRegistry(getContextClassLoader());
    }

    protected ClassLoader getContextClassLoader()
    {
      return Thread.currentThread().getContextClassLoader();
    }

    protected ClassLoader getParent(ClassLoader classLoader)
    {
      return classLoader == null ? null : classLoader.getParent();
    }

    public EPackage getEPackage(String key)
    {
      return delegateRegistry().getEPackage(key);
    }

    public EFactory getEFactory(String key)
    {
      return delegateRegistry().getEFactory(key);
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

    public Object put(String key, Object value) 
    {
      // TODO Binary incompatibility; an old override must override putAll.
      Class<?> valueClass = value.getClass();
      if (valueClass == EPackageImpl.class) 
      {
        return delegateRegistry().put(key, value);
      } 
      else 
      {
        String valueClassName = valueClass.getName();

        // Find the uppermost class loader in the hierarchy that can load the class.
        //
        ClassLoader result = getContextClassLoader();
        for (ClassLoader classLoader = getParent(result); classLoader != null; classLoader = getParent(classLoader))
        {
          try 
          {
            Class<?> loadedClass = classLoader.loadClass(valueClassName);
            if (loadedClass == valueClass) 
            {
              result = classLoader;
            } 
            else 
            {
              // The class address was not equal, so we don't want this class loader,
              // but instead want the last result that was able to load the class.
              //
              break;
            }
          } 
          catch (ClassNotFoundException exception) 
          {
            // We can't find the class, so we don't want this class loader,
            // but instead want the last result that was able to load the class.
            //
            break;
          }
        }

        // Register with the upper most class loader that's able to load the class.
        //
        return delegateRegistry(result).put(key, value);
      }
    }

    public Object remove(Object key)
    {
      return delegateRegistry().remove(key);
    }

    public void putAll(Map<? extends String, ? extends Object> map)
    {
      for (Map.Entry<? extends String, ? extends Object> entry : map.entrySet())
      {
        put(entry.getKey(), entry.getValue());
      }
    }

    public void clear()
    {
      delegateRegistry().clear();
    }

    public Set<String> keySet()
    {
      return delegateRegistry().keySet();
    }

    public Collection<Object> values()
    {
      return delegateRegistry().values();
    }

    public Set<Entry<String, Object>> entrySet()
    {
      return delegateRegistry().entrySet();
    }
  }

  private static class ParentClassLoaderGetter implements PrivilegedAction<ClassLoader>
  {
    private ClassLoader classLoader;
    
    public ClassLoader run()
    {
      if (classLoader != null)
      {
        classLoader = classLoader.getParent();
      }
      return null;
    }
 
    public ClassLoader getParent(ClassLoader classLoader)
    {
      this.classLoader = classLoader;
      AccessController.doPrivileged(this);
      return this.classLoader;
    }
  }

  private static final ParentClassLoaderGetter PARENT_CLASS_LOADER_GETTER = new ParentClassLoaderGetter();

  private static final PrivilegedAction<ClassLoader> CONTEXT_CLASS_LOADER_ACTION = 
    new PrivilegedAction<ClassLoader>()
    {
      public ClassLoader run()
      {
        return Thread.currentThread().getContextClassLoader();
      }
    };

  private static ClassLoader getContextClassLoaderSecurely()
  {
    return AccessController.doPrivileged(CONTEXT_CLASS_LOADER_ACTION);
  }

  private static final Map<ClassLoader, EPackage.Registry> secureClassLoaderToRegistryMap = new WeakHashMap<ClassLoader, EPackage.Registry>();

  private static synchronized EPackage.Registry getRegistrySecurely(ClassLoader classLoader)
  {
    EPackage.Registry result = secureClassLoaderToRegistryMap.get(classLoader);
    if (result == null)
    {
      if (classLoader != null)
      {
        result = new EPackageRegistryImpl(getRegistrySecurely(PARENT_CLASS_LOADER_GETTER.getParent(classLoader)));
        secureClassLoaderToRegistryMap.put(classLoader, result);
      }
    }
    return result;
  }

  private static final class SecureDelegator extends Delegator
  {
    private final ParentClassLoaderGetter parentClassLoaderGetter = new ParentClassLoaderGetter();
    
    @Override
    protected EPackage.Registry delegateRegistry(ClassLoader classLoader)
    {
      return getRegistrySecurely(classLoader);
    }

    @Override
    protected ClassLoader getContextClassLoader()
    {
      return getContextClassLoaderSecurely();
    }

    @Override
    protected ClassLoader getParent(ClassLoader classLoader)
    {
      return parentClassLoaderGetter.getParent(classLoader);
    }
  }
}
