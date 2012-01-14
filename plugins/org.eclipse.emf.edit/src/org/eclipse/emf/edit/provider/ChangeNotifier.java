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
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;


/**
 * This is a simple and obvious implementation of {@link IChangeNotifier} as an extension of an ArrayList, for flexibility.
 */
public class ChangeNotifier extends ArrayList<INotifyChangedListener> implements IChangeNotifier
{
  private static final long serialVersionUID = 1L;

  public ChangeNotifier()
  {
    super();
  }

  /**
   * This calls {@link org.eclipse.emf.edit.provider.INotifyChangedListener#notifyChanged notifyChanged} for each listener.
   */
  public void fireNotifyChanged(Notification notification)
  {
    int size = size();
    INotifyChangedListener [] listeners = new INotifyChangedListener[size];
    toArray(listeners);
    int expectedModCount = modCount;
    for (int i = 0; i < size; ++i)
    {
      INotifyChangedListener notifyChangedListener = listeners[i];
      if (expectedModCount == modCount || this.contains(notifyChangedListener))
      {
        notifyChangedListener.notifyChanged(notification);
      }
    }
  }

  /**
   * This adds another listener.
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    add(notifyChangedListener);
  }

  /**
   * This removes a listener.
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    remove(notifyChangedListener);
  }
}
