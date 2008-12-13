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
 * $Id: ReflectiveItemProviderAdapterFactory.java,v 1.5 2008/12/13 15:56:01 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers reflectively.
 */
public class ReflectiveItemProviderAdapterFactory 
  extends AdapterFactoryImpl 
  implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * The singleton reflective instance.
   */
  protected ReflectiveItemProvider reflectiveItemProviderAdapter;

  /**
   * This constructs an instance.
   */
  public ReflectiveItemProviderAdapterFactory()
  {
    reflectiveItemProviderAdapter = new ReflectiveItemProvider(this);

    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemPropertySource.class);
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(ITableItemLabelProvider.class);
  }

  @Override
  public Adapter createAdapter(Notifier target)
  {
    return reflectiveItemProviderAdapter;
  }

  /**
   * This returns the root adapter factory that contains this factory.
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * @return whether this factory is applicable for the type of the object.
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return type instanceof EObject || supportedTypes.contains(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   */
  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * This adds a listener.
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   */
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
    if (reflectiveItemProviderAdapter != null)
    {
      reflectiveItemProviderAdapter.dispose();
    }
  }
}
