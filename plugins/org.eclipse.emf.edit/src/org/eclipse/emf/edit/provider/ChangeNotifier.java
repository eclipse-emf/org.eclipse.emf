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
 * $Id: ChangeNotifier.java,v 1.2 2005/06/08 06:17:05 nickb Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;


/**
 * This is a simple and obvious implementation of {@link IChangeNotifier} as an extension of an ArrayList, for flexibility.
 */
public class ChangeNotifier extends ArrayList implements IChangeNotifier
{
  public ChangeNotifier()
  {
  }

  /**
   * This calls {@link org.eclipse.emf.edit.provider.INotifyChangedListener#notifyChanged notifyChanged} for each listener.
   */
  public void fireNotifyChanged(Notification notification)
  {
    for (Iterator listeners = new ArrayList(this).iterator(); listeners.hasNext(); )
    {
      INotifyChangedListener notifyChangedListener = (INotifyChangedListener)listeners.next();
      if (this.contains(notifyChangedListener))
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
