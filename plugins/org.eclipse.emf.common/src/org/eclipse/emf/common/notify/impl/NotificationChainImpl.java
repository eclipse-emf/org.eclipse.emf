/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.notify.impl;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;


/**
 * A list that acts as a notification chain.
 */
public class NotificationChainImpl extends BasicEList<Notification> implements NotificationChain
{
  private static final long serialVersionUID = 1L;

  /**
   * A map from notifier to either a single {@link Notification} or an an array of Notifications.
   * @see #add(Notification)
   * @see #addToIndex(Notification, boolean)
   */
  private Map<Object, Object> notifierIndex;

  /**
   * Creates an empty instance.
   */
  public NotificationChainImpl()
  {
    super();
  }

  /**
   * Creates an empty instance with a given capacity.
   * @param initialCapacity the initial capacity of the list before it must grow.
   */
  public NotificationChainImpl(int initialCapacity)
  {
    super(initialCapacity);
  }

  /**
   * Returns new data storage of type {@link Notification}[].
   * @return new data storage.
   */
  @Override
  protected Object[] newData(int capacity)
  {
    return new Notification [capacity];
  }

  /**
   * Adds or merges a new notification.
   * @param newNotification a notification.
   * @return <code>true</code> when the notification is added and <code>false</code> when it is merged.
   */
  @Override
  public boolean add(Notification newNotification)
  {
    if (newNotification == null)
    {
      return false;
    }
    else
    {
      // If there are many notifications, merging takes O(n^2}, so build an index at this threshold.
      //
      Notification[] notifications = (Notification[])data;
      if (size >= 1000)
      {
        // If we haven't build the index yet, build it now, of course without merge checking.
        //
        if (notifierIndex == null)
        {
          notifierIndex = new HashMap<Object, Object>(size + 1);
          for (int i = 0; i < size; ++i)
          {
            addToIndex(notifications[i], false);
          }
        }

        // Use the index to add or merge the notification.
        //
        if (!addToIndex(newNotification, true))
        {
          // If it was merged, we don't need to do the add at the end of this method.
          //
          return false;
        }
      }
      else
      {
        for (int i = 0; i < size; ++i)
        {
          Notification notification = notifications[i];
          if (notification.merge(newNotification))
          {
            return false;
          }
        }
      }

      return super.add(newNotification);
    }
  }

  /**
   * Adds or merges the notification using the {@link #notifierIndex notifier index}.
   * @return whether the notification was added ({@code true}) or merged ({@code false}).
   */
  private boolean addToIndex(Notification newNotification, boolean merge)
  {
    // Put the notifier into the index, checking the previous value in the index.
    //
    Object notifier = newNotification.getNotifier();
    Object otherNotification = notifierIndex.put(notifier, newNotification);
    if (otherNotification != null)
    {
      // If the previous value is a notification...
      //
      if (otherNotification instanceof Notification)
      {
        // If we're merging, and the new notification is merged with the old one.
        //
        Notification notification = (Notification)otherNotification;
        if (merge && notification.merge(newNotification))
        {
          // Put the old one back.
          //
          notifierIndex.put(notifier, otherNotification);
          return false;
        }

        // Create an array with both notifications.
        //
        notifierIndex.put(notifier, new Notification []{ notification, newNotification });
      }
      else
      {
        // There is already an array.
        // If we're merging, check each for merging with notification in the array.
        Notification[] otherNotifications = (Notification[])otherNotification;
        if (merge)
        {
          for (int i = 0; i < otherNotifications.length; ++i)
          {
            Notification notification = otherNotifications[i];
            if (notification.merge(newNotification))
            {
              // Put the old ones back.
              //
              notifierIndex.put(notifier, otherNotifications);
              return false;
            }
          }
        }

        // Create a new array with all notifications.
        //
        Notification[] newNotifications = new Notification [otherNotifications.length + 1];
        System.arraycopy(otherNotifications, 0, newNotifications, 0, otherNotifications.length);
        newNotifications[otherNotifications.length] = newNotification;
        notifierIndex.put(notifier, newNotifications);
      }
    }

    return true;
  }

  public void dispatch()
  {
    Notification[] notifications = (Notification[])data;
    for (int i = 0; i < size; ++i)
    {
      Notification notification = notifications[i];
      dispatch(notification);
    }
  }

  /**
   * Dispatches the notification to its notifier.
   */
  protected void dispatch(Notification notification)
  {
    Object notifier = notification.getNotifier();
    if (notifier != null && notification.getEventType() != -1)
    {
      ((Notifier)notifier).eNotify(notification);
    }
  }
}
