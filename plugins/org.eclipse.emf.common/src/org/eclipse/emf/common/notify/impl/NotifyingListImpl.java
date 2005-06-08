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
 * $Id: NotifyingListImpl.java,v 1.3.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.common.notify.impl;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.common.util.BasicEList;


/**
 * An extensible implementation of a notifying list.
 */
public class NotifyingListImpl extends BasicEList implements NotifyingList
{
  /**
   * Creates an empty instance.
   */
  public NotifyingListImpl()
  {
    super();
  }

  /**
   * Creates an empty instance with the given capacity.
   * @param initialCapacity the initial capacity of the list before it must grow.
   */
  public NotifyingListImpl(int initialCapacity)
  {
    super(initialCapacity);
  }

  /**
   * Creates an instance that is a copy of the collection.
   * @param collection the initial contents of the list.
   */
  public NotifyingListImpl(Collection collection)
  {
    super(collection);
  }

  /**
   * Returns <code>null</code>.
   * @return <code>null</code>.
   */
  public Object getNotifier()
  {
    return null;
  }

  /**
   * Returns <code>null</code>.
   * @return <code>null</code>.
   */
  public Object getFeature()
  {
    return null;
  }

  /**
   * Returns {@link org.eclipse.emf.common.notify.Notification#NO_FEATURE_ID}.
   * @return <code>Notification.NO_FEATURE_ID</code>.
   */
  public int getFeatureID()
  {
    return Notification.NO_FEATURE_ID;
  }

  /**
   * Returns the result of calling {@link #getFeatureID()}.
   * @param expectedClass the class to which the ID is relative.
   * @return <code>getFeatureID()</code>.
   */
  protected int getFeatureID(Class expectedClass)
  {
    return getFeatureID();
  }

  /**
   * Returns whether the list is considered set, i.e., whether it's not empty.
   * A derived implementation may model this state directly.
   * @return whether the list is considered set.
   */
  protected boolean isSet()
  {
    return !isEmpty();
  }

  /**
   * Returns <code>false</code>.
   * @return <code>false</code>.
   */
  protected boolean hasInverse()
  {
    return false;
  }

  /**
   * Returns <code>!{@link #hasInverse()}</code>.
   * @return <code>!hasInverse</code>.
   */
  protected boolean canContainNull()
  {
    return !hasInverse();
  }

  /**
   * Returns <code>false</code>.
   * @return <code>false</code>.
   */
  protected boolean isNotificationRequired()
  {
    return false;
  }

  /**
   * Returns <code>false</code>.
   * @return <code>false</code>.
   */
  protected boolean hasShadow()
  {
    return false;
  }

  /**
   * Does nothing and returns the <code>notifications</code>.
   * Clients can override this to update the inverse of a bidirectional relation.
   * @param object the object that's been added to the list.
   * @param notifications the chain of accumulating notifications.
   * @return the <code>notifications</code>.
   */
  protected NotificationChain shadowAdd(Object object, NotificationChain notifications)
  {
    return notifications;
  }

  /**
   * Does nothing and returns the <code>notifications</code>.
   * Clients can override this to update the inverse of a bidirectional relation.
   * @param object the object that's been remove from the list.
   * @param notifications the chain of accumulating notifications.
   * @return the <code>notifications</code>.
   */
  protected NotificationChain shadowRemove(Object object, NotificationChain notifications)
  {
    return notifications;
  }

  /**
   * Does nothing and returns the <code>notifications</code>.
   * Clients can override this to update the inverse of a bidirectional relation.
   * @param oldObject the object that's been removed from the list.
   * @param newObject the object that's been added to the list.
   * @param notifications the chain of accumulating notifications.
   * @return the <code>notifications</code>.
   */
  protected NotificationChain shadowSet(Object oldObject, Object newObject, NotificationChain notifications)
  {
    return notifications;
  }

  /**
   * Does nothing and returns the <code>notifications</code>.
   * Clients can override this to update the inverse of a bidirectional relation.
   * @param object the object that's been added to the list.
   * @param notifications the chain of accumulating notifications.
   * @return the <code>notifications</code>.
   */
  protected NotificationChain inverseAdd(Object object, NotificationChain notifications)
  {
    return notifications;
  }

  /**
   * Does nothing and returns the <code>notifications</code>.
   * Clients can override this to update the inverse of a bidirectional relation.
   * @param object the object that's been remove from the list.
   * @param notifications the chain of accumulating notifications.
   * @return the <code>notifications</code>.
   */
  protected NotificationChain inverseRemove(Object object, NotificationChain notifications)
  {
    return notifications;
  }

  /*
   * @deprecated
   */
  protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index)
  {
    throw new UnsupportedOperationException("Please change your code to call new five argument version of this method");
  }

  /**
   * Creates a notification.
   * @param eventType the type of change that has occurred.
   * @param oldObject the value of the notifier's feature before the change occurred.
   * @param newObject the value of the notifier's feature after the change occurred.
   * @param index the position at which the change occurred.
   * @return a new notification.
   */
  protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet)
  {
    return 
      new NotificationImpl(eventType, oldObject, newObject, index, wasSet)
      {
        public Object getNotifier()
        {
          return NotifyingListImpl.this.getNotifier();
        }

        public Object getFeature()
        {
          return NotifyingListImpl.this.getFeature();
        }

        public int getFeatureID(Class expectedClass)
        {
          return NotifyingListImpl.this.getFeatureID(expectedClass);
        }
      };
  }

  /**
   * Dispatches a notification to the notifier of the list.
   * @param notification the notification to dispatch.
   */
  protected void dispatchNotification(Notification notification)
  {
    ((Notifier)getNotifier()).eNotify(notification);
  }

  /**
   * Adds the object at the end of the list;
   * it does no uniqueness checking.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseAdd inverseAdd} as {@link #hasInverse required}.
   * @param object the object to be added.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseAdd
   */
  public void addUnique(Object object)
  {
    if (isNotificationRequired())
    {
      int index = size;
      boolean oldIsSet = isSet();
      super.addUnique(index, object);
      NotificationImpl notification = createNotification(Notification.ADD, null, object, index, oldIsSet);
      if (hasInverse())
      {
        NotificationChain notifications = inverseAdd(object, null);
        if (hasShadow())
        {
          notifications = shadowAdd(object, notifications);
        }

        if (notifications == null)
        {
          dispatchNotification(notification);
        }
        else
        {
          notifications.add(notification);
          notifications.dispatch();
        }
      }
      else
      {
        dispatchNotification(notification);
      }
    }
    else
    {
      super.addUnique(object);
      if (hasInverse())
      {
        NotificationChain notifications = inverseAdd(object, null);
        if (notifications != null) notifications.dispatch();
      }
    }
  }

  /**
   * Adds the object at the given index in the list;
   * it does no ranging checking or uniqueness checking.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseAdd inverseAdd} as {@link #hasInverse required}.
   * @param object the object to be added.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseAdd
   */
  public void addUnique(int index, Object object)
  {
    if (isNotificationRequired())
    {
      boolean oldIsSet = isSet();
      super.addUnique(index, object);
      NotificationImpl notification = createNotification(Notification.ADD, null, object, index, oldIsSet);
      if (hasInverse())
      {
        NotificationChain notifications = inverseAdd(object, null);
        if (hasShadow())
        {
          notifications = shadowAdd(object, notifications);
        }
        if (notifications == null)
        {
          dispatchNotification(notification);
        }
        else
        {
          notifications.add(notification);
          notifications.dispatch();
        }
      }
      else
      {
        dispatchNotification(notification);
      }
    }
    else
    {
      super.addUnique(index, object);
      if (hasInverse())
      {
        NotificationChain notifications = inverseAdd(object, null);
        if (notifications != null) notifications.dispatch();
      }
    }
  }

  /**
   * Adds each object of the collection to the end of the list;
   * it does no uniqueness checking.
   * This implementation delegates to {@link #addAllUnique(int, Collection) addAllUnique(int, Collection)}.
   * @param collection the collection of objects to be added.
   * @see #inverseAdd
   */
  public boolean addAllUnique(Collection collection)
  {
    return addAllUnique(size, collection);
  }

  /**
   * Adds each object of the collection at each successive index in the list
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseAdd inverseAdd} as {@link #hasInverse required}.
   * @param index the index at which to add.
   * @param collection the collection of objects to be added.
   * @return whether any objects were added.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseAdd
   */
  public boolean addAllUnique(int index, Collection collection)
  {
    int collectionSize = collection.size();
    if (collectionSize == 0)
    {
      return false;
    }
    else
    {
      if (isNotificationRequired())
      {
        boolean oldIsSet = isSet();
        super.addAllUnique(index, collection);
        NotificationImpl notification =
          collectionSize == 1 ?
            createNotification(Notification.ADD, null, collection.iterator().next(), index, oldIsSet) :
            createNotification(Notification.ADD_MANY, null, collection, index, oldIsSet);
        if (hasInverse())
        {
          NotificationChain notifications = null;
          int lastIndex = index + collectionSize;
          for (int i = index; i < lastIndex; ++i)
          {            
            Object value = data[i];
            notifications = inverseAdd(value, notifications);
            notifications = shadowAdd(value, notifications);
          }
          if (notifications == null)
          {
            dispatchNotification(notification);
          }
          else
          {
            notifications.add(notification);
            notifications.dispatch();
          }
        }
        else
        {
          dispatchNotification(notification);
        }
      }
      else
      {
        super.addAllUnique(index, collection);
        if (hasInverse())
        {
          NotificationChain notifications = null;
          int lastIndex = index + collectionSize;
          for (int i = index; i < lastIndex; ++i)
          {            
            notifications = inverseAdd(data[i], notifications);
          }
          if (notifications != null) notifications.dispatch();
        }
      }

      return true;
    }
  }

  /**
   * Adds the object at the end of the list and returns the potentially updated notification chain;
   * it does no {@link #inverseAdd inverse} updating.
   * This implementation generates notifications as {@link #isNotificationRequired required}.
   * @param object the object to be added.
   * @return the notification chain.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseAdd
   */
  public NotificationChain basicAdd(Object object, NotificationChain notifications)
  {
    if (isNotificationRequired())
    {
      int index = size;
      boolean oldIsSet = isSet();
      super.addUnique(index, object);
      NotificationImpl notification = createNotification(Notification.ADD, null, object, index, oldIsSet);
      if (notifications == null)
      {
        notifications = notification;
      }
      else
      {
        notifications.add(createNotification(Notification.ADD, null, object, index, oldIsSet));
      }
    }
    else
    {
      super.addUnique(size, object);
    }
    return notifications;
  }

  /**
   * Removes the object at the index from the list and returns it.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseRemove inverseRemove} as {@link #hasInverse required}.
   * @param index the position of the object to remove.
   * @return the removed object.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseRemove
   */
  public Object remove(int index)
  {
    if (isNotificationRequired())
    {
      NotificationChain notifications = null;
      boolean oldIsSet = isSet();
      if (hasShadow())
      {
        notifications = shadowRemove(basicGet(index), null);
      }
      NotificationImpl notification = createNotification(Notification.REMOVE, super.remove(index), null, index, oldIsSet);
      Object oldObject = notification.getOldValue();
      if (hasInverse() && oldObject != null)
      {
        notifications = inverseRemove(oldObject, notifications);
        if (notifications == null)
        {
          dispatchNotification(notification);
        }
        else
        {
          notifications.add(notification);
          notifications.dispatch();
        }
      }
      else
      {
        if (notifications == null)
        {
          dispatchNotification(notification);
        }
        else
        {
          notifications.add(notification);
          notifications.dispatch();
        }
      }
      return oldObject;
    }
    else
    {
      Object oldObject = super.remove(index);
      if (hasInverse() && oldObject != null)
      {
        NotificationChain notifications = inverseRemove(oldObject, null);
        if (notifications != null) notifications.dispatch();
      }
      return oldObject;
    }
  }

  /**
   * Removes each object of the collection from the list and returns whether any object was actually contained by the list.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseRemove inverseRemove} as {@link #hasInverse required}.
   * @param collection the collection of objects to be removed.
   * @return whether any object was actually contained by the list.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseRemove
   */
  public boolean removeAll(Collection collection)
  {
    boolean oldIsSet = isSet();
    NotificationChain notifications = null;

    boolean result = false;
    int [] positions = null;
    if (isNotificationRequired())
    {
      int listSize = collection.size();
      if (listSize > 0)
      {
        // Copy to a list and allocate positions.
        //
        BasicEList list = new BasicEList(collection);
        Object [] objects = list.data();
        positions = new int [listSize];

        // Count up the objects that will be removed.
        // The objects are exchanged to produce this list's order
        //
        int count = 0;
        for (int i = 0; i < size; ++i)
        {
          Object object = data[i];
          for (int j = listSize; --j >= 0; )
          {
            if (equalObjects(object, objects[j]))
            {
              if (count != j)
              {
                Object x = objects[count];
                objects[count] = objects[j];
                objects[j] = x;
              }
              positions[count++] = i;
              break;
            }
          }
        }

        // If any objects are matched.
        //
        if (count > 0)
        {
          result = true;

          if (hasShadow())
          {
            // Remove from by position in reverse order.
            //
            for (int i = 0; i < count; ++i)
            {
              notifications = shadowRemove(objects[i], notifications);
            }
          }

          // Remove from by position in reverse order.
          //
          for (int i = count; --i >= 0;)
          {
            super.remove(positions[i]);
          }

          // Compact the results to remove unmatched objects
          //
          if (count != listSize)
          {
            for (int i = listSize; --i >= count; )
            {
              list.remove(i);
            }
            int [] oldPositions = positions;
            positions = new int [count];
            System.arraycopy(oldPositions, 0, positions, 0, count);
          }

          collection = list;
        }
      }
    }
    else
    {
      collection = getDuplicates(collection);

      for (int i = size; --i >= 0; )
      {
        if (collection.contains(data[i]))
        {
          super.remove(i);
          result = true;
        }
      }
    }

    if (result)
    {
      if (isNotificationRequired())
      {
        int collectionSize = collection.size();
        NotificationImpl notification =
          (collectionSize == 1 ?
            createNotification(Notification.REMOVE, collection.iterator().next(), null, positions[0], oldIsSet) :
            createNotification(Notification.REMOVE_MANY, collection, positions, positions[0], oldIsSet));

        if (hasInverse())
        {
          for (Iterator i = collection.iterator(); i.hasNext(); )
          {
            notifications = inverseRemove(i.next(), notifications);
          }
          if (notifications == null)
          {
            dispatchNotification(notification);
          }
          else
          {
            notifications.add(notification);
            notifications.dispatch();
          }
        }
        else
        {
          if (notifications == null)
          {
            dispatchNotification(notification);
          }
          else
          {
            notifications.add(notification);
            notifications.dispatch();
          }
        }
      }
      else if (hasInverse())
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          notifications = inverseRemove(i.next(), notifications);
        }
        if (notifications != null) notifications.dispatch();
      }
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Removes the object from the list and returns the potentially updated notification chain;
   * it does no {@link #inverseRemove inverse} updating.
   * This implementation generates notifications as {@link #isNotificationRequired required}.
   * @param object the object to be removed.
   * @return the notification chain.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseRemove
   */
  public NotificationChain basicRemove(Object object, NotificationChain notifications)
  {
    int index = indexOf(object);
    if (index != -1)
    {
      if (isNotificationRequired())
      {
        boolean oldIsSet = isSet();
        Object oldObject = super.remove(index);
        NotificationImpl notification = createNotification(Notification.REMOVE, oldObject, null, index, oldIsSet);
        if (notifications == null) 
        {
          notifications = notification;
        }
        else
        {
          notifications.add(notification);
        }
      }
      else
      {
        super.remove(index);
      }
    }
    return notifications;
  }

  /**
   * Clears the list of all objects.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseRemove inverseRemove} as {@link #hasInverse required}.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseRemove
   */
  public void clear()
  {
    if (isNotificationRequired())
    {
      boolean oldIsSet = isSet();
      if (size > 0)
      {
        List collection = new UnmodifiableEList(size, data);
        int collectionSize = size;

        NotificationChain notifications = null;
        if (hasShadow())
        {
          for (int i = 0; i < size; ++i)
          {
            notifications = shadowRemove(data[i], notifications);
          }
        }

        super.clear();
        Notification notification =
          (collectionSize == 1 ?
            createNotification(Notification.REMOVE, collection.get(0), null, Notification.NO_INDEX, oldIsSet) :
            createNotification(Notification.REMOVE_MANY, collection, null, Notification.NO_INDEX, oldIsSet));

        if (hasInverse())
        {
          for (Iterator i = collection.iterator(); i.hasNext(); )
          {
            notifications = inverseRemove(i.next(), notifications);
          }
          if (notifications == null)
          {
            dispatchNotification(notification);
          }
          else
          {
            notifications.add(notification);
            notifications.dispatch();
          }
        }
        else
        {
          if (notifications == null)
          {
            dispatchNotification(notification);
          }
          else
          {
            notifications.add(notification);
            notifications.dispatch();
          }
        }
      }
      else
      {
        super.clear();
        dispatchNotification(createNotification(Notification.REMOVE_MANY, Collections.EMPTY_LIST, null, Notification.NO_INDEX, oldIsSet));
      }
    }
    else if (hasInverse())
    {
      if (size > 0)
      {
        Object [] oldData = data;
        int oldSize = size;
        super.clear();
        NotificationChain notifications = null;
        for (int i = 0; i < oldSize; ++i)
        {
          notifications = inverseRemove(oldData[i], notifications);
        }
        if (notifications != null) notifications.dispatch();
      }
      else 
      {
        super.clear();
      }
    }
    else
    {
      super.clear();
    }
  }

  /**
   * Sets the object at the index
   * and returns the old object at the index;
   * it does no ranging checking or uniqueness checking.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required} 
   * and delegates to {@link #inverseAdd inverseAdd} and {@link #inverseRemove inverseRemove} as {@link #hasInverse required}.
   * @param index the position in question.
   * @param object the object to set.
   * @return the old object at the index.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseAdd
   * @see #inverseRemove
   */
  public Object setUnique(int index, Object object)
  {
    if (isNotificationRequired())
    {
      NotificationChain notifications = null;
      boolean oldIsSet = isSet();
      Notification notification = createNotification(Notification.SET, super.setUnique(index, object), object, index, oldIsSet);
      Object oldObject = notification.getOldValue();
      if (hasInverse() && !equalObjects(oldObject, object))
      {
        if (oldObject != null)
        {
          notifications = inverseRemove(oldObject, notifications);
        }

        notifications = inverseAdd(object, notifications);

        if (hasShadow())
        {
          notifications = shadowSet(oldObject, object, notifications);
        }

        if (notifications == null)
        {
          dispatchNotification(notification);
        }
        else
        {
          notifications.add(notification);
          notifications.dispatch();
        }
      }
      else
      {
        if (hasShadow())
        {
          notifications = shadowSet(oldObject, object, notifications);
        }

        if (notifications == null)
        {
          dispatchNotification(notification);
        }
        else
        {
          notifications.add(notification);
          notifications.dispatch();
        }
      }

      return oldObject;
    }
    else
    {
      Object oldObject = super.setUnique(index, object);
      if (hasInverse() && !equalObjects(oldObject, object))
      {
        NotificationChain notifications = null;
        if (oldObject != null)
        {
          notifications = inverseRemove(oldObject, null);
        }
        notifications = inverseAdd(object, notifications);
        if (notifications != null) notifications.dispatch();
      }
      return oldObject;
    }
  }

  /**
   * Sets the object at the index
   * and returns the potentially updated notification chain;
   * it does no {@link #hasInverse inverse} updating.
   * This implementation generates notifications as {@link #isNotificationRequired required}.
   * @param index the position in question.
   * @param object the object to set.
   * @return the notification chain.
   * @see #isNotificationRequired
   * @see #hasInverse
   * @see #inverseAdd
   * @see #inverseRemove
   */
  public NotificationChain basicSet(int index, Object object, NotificationChain notifications)
  {
    if (isNotificationRequired())
    {
      boolean oldIsSet = isSet();
      NotificationImpl notification = createNotification(Notification.SET, super.setUnique(index, object), object, index, oldIsSet);
      if (notifications == null) 
      {
        notifications = notification;
      }
      else
      {
        notifications.add(notification);
      }
    }
    else
    {
      super.setUnique(index, object);
    }
    return notifications;
  }

  /**
   * Moves the object at the source index of the list to the target index of the list
   * and returns the moved object.
   * In addition to the normal effects, 
   * this override implementation generates notifications as {@link #isNotificationRequired required}.
   * @param targetIndex the new position for the object in the list.
   * @param sourceIndex the old position of the object in the list.
   * @return the moved object.
   * @exception IndexOutOfBoundsException if either index isn't within the size range.
   * @see #isNotificationRequired
   */
  public Object move(int targetIndex, int sourceIndex)
  {
    if (isNotificationRequired())
    {
      boolean oldIsSet = isSet();
      Object object = super.move(targetIndex, sourceIndex);
      dispatchNotification
        (createNotification
           (Notification.MOVE, 
            new Integer(sourceIndex),
            object, 
            targetIndex,
            oldIsSet));
      return object;
    }
    else
    {
      return super.move(targetIndex, sourceIndex);
    }
  }
}
