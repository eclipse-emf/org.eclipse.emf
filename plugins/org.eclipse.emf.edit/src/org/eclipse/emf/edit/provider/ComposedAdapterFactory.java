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
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;


/**
 * This provides support for composing several factories for different models 
 * into a single factory serving the union of the model objects.
 */
public class ComposedAdapterFactory 
  implements 
    AdapterFactory, 
    ComposeableAdapterFactory, 
    IChangeNotifier,
    IDisposable
{
  /**
   * A descriptor can create an adapter factory.
   * They are used as the values in a {@link Descriptor.Registry registry}.
   */
  public interface Descriptor
  {
    /**
     * Creates an adapter factory.
     * @return a new adapter factory.
     */
    AdapterFactory createAdapterFactory();   
    
    /**
     * A registry is an index that takes a collection of keys,
     * typically a pair consisting of an EPackage or java.lang.Package, and a java.lang.Class,
     * and maps it to a {@link Descriptor descriptor}.
     */
    interface Registry
    {
      /**
       * The global registry typically populated by plugin registration.
       */
      Registry INSTANCE = org.eclipse.emf.edit.EMFEditPlugin.getComposedAdapterFactoryDescriptorRegistry();
      
      /**
       * Returns descriptor that can create a factory for the types.
       * @param types collections of keys, typically a pair consisting of an EPackage or java.lang.Package, and a java.lang.Class.
       * @return a descriptor that can create a factory for the types.
       */
      Descriptor getDescriptor(Collection<?> types);
      
      /**
       *  A simple registry implementation that supports delegation.
       */
      class Impl extends HashMap<Collection<?>, Object> implements Registry
      {
        private static final long serialVersionUID = 1L;

        /**
         * The delegate registry should lookup fail locally.
         */
        protected Registry delegateRegistry;
        
        /**
         * Creates an instance.
         * @param delegateRegistry <code>null</code> or a registration that should act as the delegate.
         */
        public Impl(Registry delegateRegistry)
        {
          this.delegateRegistry = delegateRegistry;
        }
        
        public Descriptor getDescriptor(Collection<?> types)
        {
          Descriptor descriptor = (Descriptor)get(types);
          return descriptor == null ?  delegatedGetDescriptor(types) : descriptor;
        }
        
        /**
         * This is called when local lookup fails.
         */
        protected Descriptor delegatedGetDescriptor(Collection<?> types)
        {
          if (delegateRegistry != null)
          {
            return delegateRegistry.getDescriptor(types);
          }
          
          return null;
        }
      }
    }
  }
  
  /**
   * This keeps track of all the {@link org.eclipse.emf.common.notify.AdapterFactory} delegates.
   */
  protected List<AdapterFactory> adapterFactories = new ArrayList<AdapterFactory>();

  /**
   * This is used to implement the {@link ComposeableAdapterFactory} interface.
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link IChangeNotifier}
   */
  protected ChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This is used to demand create adapter factories from a registry. 
   */
  protected Descriptor.Registry adapterFactoryDescriptorRegistry;

  public ComposedAdapterFactory()
  {
    super();
  }
  
  /**
   */
  public ComposedAdapterFactory(Descriptor.Registry adapterFactoryDescriptorRegistry)
  {
    this.adapterFactoryDescriptorRegistry = adapterFactoryDescriptorRegistry;
  }
  
  /**
   */
  public ComposedAdapterFactory(AdapterFactory adapterFactory)
  {
    addAdapterFactory(adapterFactory);
  }

  public ComposedAdapterFactory(AdapterFactory [] adapterFactories)
  {
    for (int i = 0; i < adapterFactories.length; ++i)
    {
      addAdapterFactory(adapterFactories[i]);
    }
  }

  public ComposedAdapterFactory(Collection<? extends AdapterFactory> adapterFactories)
  {
    for (AdapterFactory adapterFactory : adapterFactories)
    {
      addAdapterFactory(adapterFactory);
    }
  }

  public boolean isFactoryForType(Object type)
  {
    for (AdapterFactory adapterFactory : adapterFactories)
    {
      if (adapterFactory.isFactoryForType(type))
      {
        return true;
      }
    }

    return false;
  }

  public AdapterFactory getFactoryForType(Object type)
  {
    return getFactoryForTypes(Collections.singleton(type));
  }

  private static final Object ANY_OBJECT = new Object();
  private static final Object ANY_EOBJECT = EcoreFactory.eINSTANCE.createEObject();

  public AdapterFactory getFactoryForTypes(Collection<?> types)
  {
    AdapterFactory result = null;

    FactoryLoop: 
    for (AdapterFactory factory : adapterFactories)
    {
      if (factory instanceof ComposedAdapterFactory)
      {
        AdapterFactory candidate = ((ComposedAdapterFactory)factory).getFactoryForTypes(types);
        if (candidate != null)
        {
          if (!candidate.isFactoryForType(ANY_EOBJECT) && !candidate.isFactoryForType(ANY_OBJECT))
          {
            return candidate;
          }
          else if (result == null)
          {
            result = candidate;
          }
        }
      }
      else 
      {
        for (Object type : types)
        {
          if (!factory.isFactoryForType(type))
          {
            continue FactoryLoop;
          }
        }
        if (!factory.isFactoryForType(ANY_EOBJECT) && !factory.isFactoryForType(ANY_OBJECT))
        {
          return factory;
        }
        else if (result == null)
        {
          result = factory;
        }
      }
    }

    if (adapterFactoryDescriptorRegistry != null)
    {
      Descriptor descriptor = adapterFactoryDescriptorRegistry.getDescriptor(types);
      if (descriptor != null)
      {
        result = descriptor.createAdapterFactory();
        addAdapterFactory(result);
      }
    }
    
    return result == null ? delegatedGetFactoryForTypes(types) : result;
  }
  
  protected AdapterFactory delegatedGetFactoryForTypes(Collection<?> types)
  {
    return  null;
  }

  public Object adapt(Object target, Object type)
  {
    Object adapter = target;
    if (target instanceof Notifier)
    {
      adapter = adapt((Notifier)target, type);
    }

    if (!(type instanceof Class<?>) || ((Class<?>)type).isInstance(adapter))
    {
      return adapter;
    }

    return null;
  }

  public Adapter adapt(Notifier target, Object type)
  {
    return adapt(target, type, false);
  }

  protected Adapter adapt(Notifier target, Object type, boolean isNew)
  {
    Adapter result = null;

    if (target instanceof EObject)
    {
      EObject eObject = (EObject)target;
      EClass eClass = eObject.eClass();
      if (eClass != null)
      {
        EPackage ePackage = eClass.getEPackage();
        Collection<Object> types = new ArrayList<Object>();
        types.add(ePackage);
        if (type != null)
        {
          types.add(type);
        }
        AdapterFactory delegateAdapterFactory = getFactoryForTypes(types);
        if (delegateAdapterFactory != null)
        {
          result = isNew ? delegateAdapterFactory.adaptNew(target, type) : delegateAdapterFactory.adapt(target, type);
        }

        if (result == null)
        {
          Collection<EPackage> failedPackageSet = new HashSet<EPackage>();
          failedPackageSet.add(ePackage);
          List<EClass> allSuperTypes = new UniqueEList.FastCompare<EClass>(eClass.getESuperTypes());
          for (int i = 0; i < allSuperTypes.size(); ++i)
          {
            EClass eSuperType = allSuperTypes.get(i);
            EPackage eSupertypePackage = eSuperType.getEPackage();
            if (failedPackageSet.add(eSupertypePackage))
            {
              Collection<Object> superTypes = new ArrayList<Object>();
              superTypes.add(eSupertypePackage);
              if (type != null)
              {
                superTypes.add(type);
              }
              delegateAdapterFactory = getFactoryForTypes(superTypes);
              if (delegateAdapterFactory != null)
              {
                result = isNew ? delegateAdapterFactory.adaptNew(target, type) : delegateAdapterFactory.adapt(target, type);
                if (result != null)
                {
                  break;
                }
              }
            }
            allSuperTypes.addAll(eSuperType.getESuperTypes());
          }
        }
      }
    }
    else
    {
      result = 
        isNew ? 
          adapt(target, type, new HashSet<Object>(), target.getClass(), true): 
          adapt(target, type, new HashSet<Object>(), target.getClass());
    }

    return result;    
  }

  protected Adapter adapt(Notifier target, Object type, Collection<Object> failedPackages, Class<?> javaClass)
  {
    return adapt(target, type, failedPackages, javaClass, false);
  }
  
  protected Adapter adapt(Notifier target, Object type, Collection<Object> failedPackages, Class<?> javaClass, boolean isNew)
  {
    Adapter result = null;

    Package javaPackage = javaClass.getPackage();
    if (failedPackages.add(javaPackage))
    {
      Collection<Object> types = new ArrayList<Object>();
      types.add(javaPackage);
      if (type != null)
      {
        types.add(type);
      }
      AdapterFactory delegateAdapterFactory = getFactoryForTypes(types);
      if (delegateAdapterFactory != null)
      {
        result = isNew ? delegateAdapterFactory.adaptNew(target, type) : delegateAdapterFactory.adapt(target, type);
      }
    }

    if (result == null)
    {
      Class<?> superclass = javaClass.getSuperclass();
      if (superclass != null)
      {
        result = adapt(target, type, failedPackages, javaClass.getSuperclass(), isNew);
      }
      if (result == null)
      {
        Class<?> [] interfaces = javaClass.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i)
        {
          result = adapt(target, type, failedPackages, interfaces[i], isNew);
          if (result != null)
          {
            break;
          }
        }
      }
    }

    return result;
  }

  public Adapter adaptNew(Notifier target, Object type)
  {
    return adapt(target, type, true);
  }

  public void adaptAllNew(Notifier target)
  {
    for (AdapterFactory adapterFactory : adapterFactories)
    {
      if (adapterFactory.isFactoryForType(target))
      {
        adapterFactory.adaptAllNew(target);
      }
    }
  }

  public void insertAdapterFactory(AdapterFactory adapterFactory)
  {
    if (!adapterFactories.contains(adapterFactory))
    {
      adapterFactories.add(0, adapterFactory);
      if (adapterFactory instanceof ComposeableAdapterFactory)
      {
        ((ComposeableAdapterFactory)adapterFactory).setParentAdapterFactory(this);
      }
    }
  }

  public void addAdapterFactory(AdapterFactory adapterFactory)
  {
    if (!adapterFactories.contains(adapterFactory))
    {
      adapterFactories.add(adapterFactory);
      if (adapterFactory instanceof ComposeableAdapterFactory)
      {
        ((ComposeableAdapterFactory)adapterFactory).setParentAdapterFactory(this);
      }
    }
  }

  public void removeAdapterFactory(AdapterFactory adapterFactory)
  {
    if (adapterFactories.contains(adapterFactory))
    {
      adapterFactories.remove(adapterFactory);
      if (adapterFactory instanceof ComposeableAdapterFactory)
      {
        ((ComposeableAdapterFactory)adapterFactory).setParentAdapterFactory(null);
      }
    }
  }

  /**
   * This returns the root adapter factory that delegates to this factory.
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the direct parent adapter factory into which this factory is composed.
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.add(notifyChangedListener);
  }

  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.remove(notifyChangedListener);
  }

  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  public void dispose()
  {
    for (Object factory : adapterFactories)
    {
      if (factory instanceof IDisposable)
      {
        ((IDisposable)factory).dispose();
      }
    }
  }
}
