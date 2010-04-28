/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: DecoratorAdapterFactory.java,v 1.2 2010/04/28 20:38:35 khussey Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.HashMap;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.EMFEditPlugin;


/**
 * This abstract class provides support for creating {@link IItemProviderDecorator}s 
 * for the adapters created by another {@link AdapterFactory}.
 */
public abstract class DecoratorAdapterFactory 
  implements 
    AdapterFactory, 
    ComposeableAdapterFactory, 
    IChangeNotifier,
    IDisposable
{
  protected HashMap<Object, IItemProviderDecorator> itemProviderDecorators = new HashMap<Object, IItemProviderDecorator>();

  /**
   * This keeps the {@link org.eclipse.emf.common.notify.AdapterFactory} being decorated.
   */
  protected AdapterFactory decoratedAdapterFactory;

  /**
   * This is used to implement the {@link ComposeableAdapterFactory} interface.
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This keeps track of all the Viewers.
   */
  protected ChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This creates an instance that decorates the adapters from the given adapter factory.
   */
  public DecoratorAdapterFactory(AdapterFactory decoratedAdapterFactory)
  {
    this.decoratedAdapterFactory = decoratedAdapterFactory;
  }

  /**
   * This just delegates to the {@link #decoratedAdapterFactory}.
   */
  public boolean isFactoryForType(Object type)
  {
    return decoratedAdapterFactory.isFactoryForType(type);
  }

  /**
   * This returns the adapter factory whose adapters are being decorated.
   */
  public AdapterFactory getDecoratedAdapterFactory()
  {
    return decoratedAdapterFactory;
  }

  /**
   * This sets the adapter factory whose adapters will be decorated.
   */
  public void setDecoratedAdapterFactory(AdapterFactory decoratedAdapterFactory)
  {
    this.decoratedAdapterFactory = decoratedAdapterFactory;
  }

  /**
   * This is called when a new decorator is needed by {@link #adapt(Object,Object)}.
   */
  protected abstract IItemProviderDecorator createItemProviderDecorator(Object target, Object Type);

  /**
   * All adapter creation is delegated to this method, which yields decorated item providers.
   * It hooks up the decorators created by {@link #createItemProviderDecorator} 
   * to the adapters returned by {@link #decoratedAdapterFactory}.
   */
  public Object adapt(Object target, Object type)
  {
    Object adapter = decoratedAdapterFactory.adapt(target, type);
    if (adapter instanceof IChangeNotifier)
    {
      IItemProviderDecorator itemProviderDecorator = itemProviderDecorators.get(adapter);
      if (itemProviderDecorator == null)
      {
        itemProviderDecorator = createItemProviderDecorator(target, type);
        itemProviderDecorators.put(adapter, itemProviderDecorator);
        itemProviderDecorator.setDecoratedItemProvider((IChangeNotifier)adapter);
      }

      return itemProviderDecorator;
    }

    return adapter;
  }

  /**
   * This delegates to {@link #adapt(Object,Object)}
   */
  public Adapter adapt(Notifier target, Object type)
  {
    return (Adapter)adapt((Object)target, type);
  }

  /**
   * This interface is not support; an exception will be thrown.
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
    decoratedAdapterFactory.adaptAllNew(target);
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
    changeNotifier.addListener(notifyChangedListener);
  }

  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
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
    for (Object object : itemProviderDecorators.values())
    {
      if (object instanceof IDisposable)
      {
        ((IDisposable)object).dispose();
      }
    }
  }
}
