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
 * $Id: BasicNotifierImpl.java,v 1.2 2004/10/04 21:51:28 davidms Exp $
 */
package org.eclipse.emf.common.notify.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;


/**
 * An extensible notifier implementation.
 */
public class BasicNotifierImpl implements Notifier
{
  /**
   * Creates a blank new instance.
   */
  public BasicNotifierImpl()
  {
  }

  public static class EAdapterList extends BasicEList
  {
    protected Notifier notifier;

    public EAdapterList(Notifier notifier)
    {
      this.notifier = notifier;
    }

    protected boolean safe;

    protected boolean canContainNull()
    {
      return false;
    }

    protected boolean useEquals()
    {
      return false;
    }

    protected Object [] newData(int capacity)
    {
      return new Adapter [capacity];
    }

    protected void didAdd(int index, Object newObject)
    {
      Adapter adapter = (Adapter)newObject;
      adapter.setTarget(notifier);
    }

    protected void didRemove(int index, Object oldObject)
    {
      Adapter adapter = (Adapter)oldObject;
      if (notifier.eDeliver())
      {
        Notification notification = 
          new NotificationImpl(Notification.REMOVING_ADAPTER, oldObject, null, index)
          {
            public Object getNotifier()
            {
              return notifier;
            }
          };
        adapter.notifyChanged(notification);
      }
      if (adapter instanceof Adapter.Internal)
      {
        ((Adapter.Internal)adapter).unsetTarget(notifier);
      }
      else if (adapter.getTarget() == notifier) 
      {
        adapter.setTarget(null);
      }
    }

    public Object [] data()
    {
      safe = true;
      return (Adapter [])data;
    }

    protected void ensureSafety()
    {
      if (safe && data != null)
      {
        Object [] oldData = data;
        data = newData(data.length);
        System.arraycopy(oldData, 0, data, 0, size);
        safe = false;
      }
    }

    public boolean add(Object object)
    {
      ensureSafety();
      return super.add(object);
    }

    public void add(int index, Object object)
    {
      ensureSafety();
      super.add(index, object);
    }

    public boolean addAll(Collection collection)
    {
      ensureSafety();
      return super.addAll(collection);
    }

    public boolean remove(Object object)
    {
      ensureSafety();
      return super.remove(object);
    }

    public Object remove(int index)
    {
      ensureSafety();
      return super.remove(index);
    }

    public boolean removeAll(Collection collection)
    {
      ensureSafety();
      return super.removeAll(collection);
    }

    public void clear()
    {
      ensureSafety();
      super.clear();
    }

    public boolean retainAll(Collection collection)
    {
      ensureSafety();
      return super.retainAll(collection);
    }

    public Object set(int index, Object object)
    {
      ensureSafety();
      return super.set(index, object);
    }

    public void move(int newPosition, Object object)
    {
      ensureSafety();
      super.move(newPosition, object);
    }

    public Object move(int newPosition, int oldPosition)
    {
      ensureSafety();
      return super.move(newPosition, oldPosition);
    }
  }

  /*
   * Javadoc copied from interface.
   */
  public EList eAdapters()
  {
    return ECollections.EMPTY_ELIST;
  }

  /**
   * Returns the adapter list, even if it is <code>null</code>.
   * @return the adapter list, even if it is <code>null</code>.
   */
  protected BasicEList eBasicAdapters()
  {
    return null;
  }

  /*
   * Javadoc copied from interface.
   */
  public boolean eDeliver()
  {
    return false;
  }

  /*
   * Javadoc copied from interface.
   */
  public void eSetDeliver(boolean deliver)
  {
    throw new UnsupportedOperationException();
  }

  /*
   * Javadoc copied from interface.
   */
  public void eNotify(Notification notification)
  {
    if (eDeliver() && eBasicAdapters() != null)
    {
      int size = eBasicAdapters().size();
      if (size > 0)
      {
        Adapter [] adapters = (Adapter [])eBasicAdapters().data();
        for (int i = 0; i < size; ++i)
        {
          adapters[i].notifyChanged(notification);
        }
      }
    }
  }

  /**
   * Returns whether {@link #eNotify eNotify} needs to be called.
   * This may return <code>true</code> even when {@link #eDeliver eDeliver} is <code>false</code>
   * or when {@link #eAdapters eAdapters} is empty.
   * @return whether {@link #eNotify eNotify} needs to be called.
   */
  public boolean eNotificationRequired()
  {
    BasicEList eAdapters = eBasicAdapters();
    return eAdapters != null && eDeliver() && !eAdapters.isEmpty();
  }
}
