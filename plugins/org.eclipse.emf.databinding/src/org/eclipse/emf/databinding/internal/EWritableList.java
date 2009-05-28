/**
 * <copyright>
 *
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation in 262160
 * </copyright>
 *
 * $Id: EWritableList.java,v 1.2 2009/05/28 10:02:03 emerks Exp $
 */
package org.eclipse.emf.databinding.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.AbstractObservableList;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * Writable list which can be used to observe an {@link NotifyingList}
 * 
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @param <Type> the type
 * @since 2.5
 */
public class EWritableList<Type> extends AbstractObservableList implements IObservableList
{
  private NotifyingList<Type> wrappedList;
  private Object elementType;
  private boolean stale = false;

  private class Listener extends AdapterImpl
  {
    private Object feature;

    public Listener(Object feature)
    {
      this.feature = feature;
    }

    @Override
    public void notifyChanged(Notification msg)
    {

      if (feature == null && msg.getFeature() == null && msg.getFeatureID(Resource.class) != Resource.RESOURCE__CONTENTS)
      {
        return;
      }

      if (feature == msg.getFeature() && !msg.isTouch())
      {
        final ListDiff diff;
        switch (msg.getEventType())
        {
          case Notification.ADD: {
            diff = Diffs.createListDiff(Diffs.createListDiffEntry(msg.getPosition(), true, msg.getNewValue()));
            // fireListChange(diff);
            break;
          }
          case Notification.ADD_MANY: {
            Collection< ? > newValues = (Collection< ? >)msg.getNewValue();
            ListDiffEntry[] listDiffEntries = new ListDiffEntry [newValues.size()];
            int position = msg.getPosition();
            int index = 0;
            for (Object newValue : newValues)
            {
              listDiffEntries[index++] = Diffs.createListDiffEntry(position++, true, newValue);
            }
            diff = Diffs.createListDiff(listDiffEntries);
            // fireListChange(diff);
            break;
          }
          case Notification.REMOVE: {
            diff = Diffs.createListDiff(Diffs.createListDiffEntry(msg.getPosition(), false, msg.getOldValue()));
            // fireListChange(diff);
            break;
          }
          case Notification.REMOVE_MANY: {
            Collection< ? > oldValues = (Collection< ? >)msg.getOldValue();
            ListDiffEntry[] listDiffEntries = new ListDiffEntry [oldValues.size()];
            int position = msg.getPosition();
            int index = 0;
            for (Object oldValue : oldValues)
            {
              listDiffEntries[index++] = Diffs.createListDiffEntry(position++, false, oldValue);
            }
            diff = Diffs.createListDiff(listDiffEntries);
            // fireListChange(diff);
            break;
          }
          case Notification.MOVE: {
            Object movedValue = msg.getNewValue();
            ListDiffEntry[] listDiffEntries = new ListDiffEntry [2];
            listDiffEntries[0] = Diffs.createListDiffEntry((Integer)msg.getOldValue(), false, movedValue);
            listDiffEntries[1] = Diffs.createListDiffEntry(msg.getPosition(), true, movedValue);
            diff = Diffs.createListDiff(listDiffEntries);
            // fireListChange(diff);
            break;
          }
          case Notification.UNSET: {
            // This just represents going back to the unset state, but
            // that doesn't affect the contents of the list.
            //
            return;
          }
          default: {
            throw new RuntimeException("unhandled case");
          }
        }

        getRealm().exec(new Runnable()
          {
            public void run()
            {
              fireListChange(diff);
            }
          });

        // System.err.println("CHANGE: " +
        // diff.getDifferences()[0].getElement());

        // fireListChange(diff);
        // listener.handlePropertyChange(new SimplePropertyEvent(msg
        // .getNotifier(), EMFListProperty.this, diff));
      }
    }

  }

  private Adapter listener;

  /**
   * New writable list wrapping the {@link NotifyingList}
   * 
   * @param wrappedList
   *            the wrapped list
   */
  public EWritableList(NotifyingList<Type> wrappedList)
  {
    this(Realm.getDefault(), wrappedList);
  }

  /**
   * New writable list wrapping the {@link NotifyingList} and using the
   * {@link Realm}
   * 
   * @param realm
   *            the realm
   * @param wrappedList
   *            the wrapped list
   */
  public EWritableList(Realm realm, NotifyingList<Type> wrappedList)
  {
    this(realm, wrappedList, null);
  }

  /**
   * New writable list wrapping the {@link NotifyingList}
   * 
   * @param realm
   *            the realm
   * @param wrappedList
   *            the wrapped list
   * @param elementType
   *            the element type
   */
  public EWritableList(Realm realm, NotifyingList<Type> wrappedList, Class<Type> elementType)
  {
    super(realm);
    this.wrappedList = wrappedList;
    this.elementType = elementType;
  }

  @Override
  protected void firstListenerAdded()
  {
    if (wrappedList.getNotifier() instanceof Notifier)
    {
      Notifier notifier = (Notifier)wrappedList.getNotifier();
      listener = new Listener(wrappedList.getFeature());
      notifier.eAdapters().add(listener);
    }
    else
    {
      throw new IllegalArgumentException("Wrapped list must have a notifier attached!");
    }
  }

  @Override
  protected void lastListenerRemoved()
  {
    if (wrappedList.getNotifier() instanceof Notifier)
    {
      Notifier notifier = (Notifier)wrappedList.getNotifier();
      listener = new Listener(wrappedList.getFeature());
      notifier.eAdapters().remove(listener);
    }
    else
    {
      throw new IllegalArgumentException("Wrapped list must have a notifier attached!");
    }
  }

  @Override
  public synchronized void dispose()
  {
    super.dispose();
  }

  private void getterCalled()
  {
    ObservableTracker.getterCalled(this);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean add(Object o)
  {
    checkRealm();
    return wrappedList.add((Type)o);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean addAll(Collection c)
  {
    checkRealm();
    return wrappedList.addAll(c);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean addAll(int index, Collection c)
  {
    checkRealm();
    return wrappedList.addAll(index, c);
  }

  @Override
  public boolean contains(Object o)
  {
    getterCalled();
    return wrappedList.contains(o);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean containsAll(Collection c)
  {
    getterCalled();
    return wrappedList.containsAll(c);
  }

  @Override
  public Object get(int index)
  {
    getterCalled();
    return wrappedList.get(index);
  }

  public Object getElementType()
  {
    checkRealm();
    return elementType;
  }

  @Override
  public int indexOf(Object o)
  {
    getterCalled();
    return wrappedList.indexOf(o);
  }

  @Override
  public boolean isEmpty()
  {
    getterCalled();
    return wrappedList.isEmpty();
  }

  @Override
  public Iterator<Type> iterator()
  {
    getterCalled();
    return wrappedList.iterator();
  }

  @Override
  public int lastIndexOf(Object o)
  {
    getterCalled();
    return wrappedList.lastIndexOf(o);
  }

  @Override
  public ListIterator<Type> listIterator()
  {
    getterCalled();
    return wrappedList.listIterator();
  }

  @Override
  public ListIterator<Type> listIterator(int index)
  {
    getterCalled();
    return wrappedList.listIterator(index);
  }

  @Override
  public Object move(int oldIndex, int newIndex)
  {
    checkRealm();
    return wrappedList.move(oldIndex, newIndex);
  }

  @Override
  public boolean remove(Object o)
  {
    checkRealm();
    return wrappedList.remove(o);
  }

  @Override
  public Object remove(int index)
  {
    checkRealm();
    return wrappedList.remove(index);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean removeAll(Collection c)
  {
    checkRealm();
    return wrappedList.removeAll(c);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean retainAll(Collection c)
  {
    checkRealm();
    return wrappedList.retainAll(c);
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object set(int index, Object element)
  {
    checkRealm();
    return wrappedList.set(index, (Type)element);
  }

  @Override
  public int doGetSize()
  {
    getterCalled();
    return wrappedList.size();
  }

  @Override
  public List<Type> subList(int fromIndex, int toIndex)
  {
    getterCalled();
    return wrappedList.subList(fromIndex, toIndex);
  }

  @Override
  public Object[] toArray()
  {
    getterCalled();
    return wrappedList.toArray();
  }

  @Override
  public Object[] toArray(Object[] a)
  {
    getterCalled();
    return wrappedList.toArray();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void add(int index, Object element)
  {
    checkRealm();
    wrappedList.add(index, (Type)element);
  }

  @Override
  public void clear()
  {
    checkRealm();
    wrappedList.clear();
  }

  @Override
  public boolean isStale()
  {
    getterCalled();
    return stale;
  }

  // public void setStale(boolean stale) {
  // checkRealm();
  //
  // boolean wasStale = this.stale;
  // this.stale = stale;
  // if (!wasStale && stale) {
  // fireStale();
  // }
  // }
}