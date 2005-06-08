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
 * $Id: ComposedAdapterFactory.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.EMFEditPlugin;


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
   * This keeps track of all the {@link org.eclipse.emf.common.notify.AdapterFactory} delegates.
   */
  protected List adapterFactories = new ArrayList();

  /**
   * This is used to implement the {@link ComposeableAdapterFactory} interface.
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link IChangeNotifier}
   */
  protected ChangeNotifier changeNotifier = new ChangeNotifier();

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

  public ComposedAdapterFactory(Collection adapterFactories)
  {
    for (Iterator factories = adapterFactories.iterator(); factories.hasNext(); )
    {
      addAdapterFactory((AdapterFactory)factories.next());
    }
  }

  public boolean isFactoryForType(Object type)
  {
    for (Iterator factories = adapterFactories.iterator(); factories.hasNext(); )
    {
      AdapterFactory adapterFactory = (AdapterFactory)factories.next();
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

  public AdapterFactory getFactoryForTypes(Collection types)
  {
    FactoryLoop : for (Iterator factories = adapterFactories.iterator(); factories.hasNext(); )
    {
      AdapterFactory factory = (AdapterFactory)factories.next();
      if (factory instanceof ComposedAdapterFactory)
      {
        factory = ((ComposedAdapterFactory)factory).getFactoryForTypes(types);
        if (factory != null)
        {
          return factory;
        }
      }
      else 
      {
        for (Iterator i = types.iterator(); i.hasNext(); )
        {
          if (!factory.isFactoryForType(i.next()))
          {
            continue FactoryLoop;
          }
        }
        return factory;
      }
    }

    return null;
  }

  public Object adapt(Object target, Object type)
  {
    Object adapter = target;
    if (target instanceof Notifier)
    {
      adapter = adapt((Notifier)target, type);
    }

    if (!(type instanceof Class) || (((Class)type).isInstance(adapter)))
    {
      return adapter;
    }

    return null;
  }

  public Adapter adapt(Notifier target, Object type)
  {
    Adapter result = null;

    if (target instanceof EObject)
    {
      EObject eObject = (EObject)target;
      EClass eClass = eObject.eClass();
      if (eClass != null)
      {
        EPackage ePackage = eClass.getEPackage();
        Collection types = new ArrayList();
        types.add(ePackage);
        if (type != null)
        {
          types.add(type);
        }
        AdapterFactory delegateAdapterFactory = getFactoryForTypes(types);
        if (delegateAdapterFactory != null)
        {
          result = delegateAdapterFactory.adapt(target, type);
        }

        if (result == null)
        {
          Collection failedPackageSet = new HashSet();
          failedPackageSet.add(ePackage);
          for (Iterator supertypes = eClass.getEAllSuperTypes().iterator(); supertypes.hasNext(); )
          {
            EClass eSuperType = (EClass)supertypes.next();
            EPackage eSupertypePackage = eSuperType.getEPackage();
            if (failedPackageSet.add(eSupertypePackage))
            {
              Collection superTypes = new ArrayList();
              superTypes.add(ePackage);
              if (type != null)
              {
                superTypes.add(type);
              }
              delegateAdapterFactory = getFactoryForTypes(superTypes);
              if (delegateAdapterFactory != null)
              {
                result = delegateAdapterFactory.adapt(target, type);
                if (result != null)
                {
                  break;
                }
              }
            }
          }
        }
      }
    }
    else
    {
      result = adapt(target, type, new HashSet(), target.getClass());
    }

    return result;    
  }

  protected Adapter adapt(Notifier target, Object type, Collection failedPackages, Class javaClass)
  {
    Adapter result = null;


    Package javaPackage = javaClass.getPackage();
    if (failedPackages.add(javaPackage))
    {
      Collection types = new ArrayList();
      types.add(javaPackage);
      if (type != null)
      {
        types.add(type);
      }
      AdapterFactory delegateAdapterFactory = getFactoryForTypes(types);
      if (delegateAdapterFactory != null)
      {
        result = delegateAdapterFactory.adapt(target, type);
      }
    }

    if (result == null)
    {
      Class superclass = javaClass.getSuperclass();
      if (superclass != null)
      {
        result = adapt(target, type, failedPackages, javaClass.getSuperclass());
      }
      if (result == null)
      {
        Class [] interfaces = javaClass.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i)
        {
          result = adapt(target, type, failedPackages, interfaces[i]);
          if (result != null)
          {
            break;
          }
        }
      }
    }

    return result;
  }

  /**
   * This method isn't implemented and will throw and exception.
   */
  public Adapter adaptNew(Notifier target, Object type)
  {
    throw 
      new RuntimeException
        (EMFEditPlugin.INSTANCE.getString
          ("_EXC_Method_not_implemented", new Object [] { this.getClass() + "adaptNew(Notifier target, Object type)" }));
  }

  public void adaptAllNew(Notifier target)
  {
    for (Iterator factories = adapterFactories.iterator(); factories.hasNext(); )
    {
      AdapterFactory adapterFactory = (AdapterFactory)factories.next();
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
    for (Iterator factories = adapterFactories.iterator(); factories.hasNext(); )
    {
      Object factory = factories.next();
      if (factory instanceof IDisposable)
      {
        ((IDisposable)factory).dispose();
      }
    }
  }
}
