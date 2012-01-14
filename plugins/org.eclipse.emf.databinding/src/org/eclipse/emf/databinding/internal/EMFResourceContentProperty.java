/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.databinding.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.SimplePropertyEvent;
import org.eclipse.core.databinding.property.list.SimpleListProperty;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * @since 2.6
 */
public class EMFResourceContentProperty extends SimpleListProperty
{
  @Override
  protected List<?> doGetList(Object source)
  {
    Resource resource = (Resource)source;
    return resource.getContents();
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetList(Object source, List list, ListDiff diff)
  {
    List< ? > currentList = doGetList(source);
    diff.applyTo(currentList);
  }

  public Object getElementType()
  {
    return null;
  }

  @Override
  public INativePropertyListener adaptListener(ISimplePropertyListener listener)
  {
    return new Listener(listener);
  }

  private class Listener extends AdapterImpl implements INativePropertyListener
  {
    private ISimplePropertyListener listener;

    private Listener(ISimplePropertyListener listener)
    {
      this.listener = listener;
    }

    @Override
    public void notifyChanged(Notification msg)
    {

      if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS && !msg.isTouch())
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
            break;
          }
          case Notification.REMOVE: {
            diff = Diffs.createListDiff(Diffs.createListDiffEntry(msg.getPosition(), false, msg.getOldValue()));
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
            break;
          }
          case Notification.MOVE: {
            Object movedValue = msg.getNewValue();
            ListDiffEntry[] listDiffEntries = new ListDiffEntry [2];
            listDiffEntries[0] = Diffs.createListDiffEntry((Integer)msg.getOldValue(), false, movedValue);
            listDiffEntries[1] = Diffs.createListDiffEntry(msg.getPosition(), true, movedValue);
            diff = Diffs.createListDiff(listDiffEntries);
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

        listener.handleEvent((new SimplePropertyEvent(SimplePropertyEvent.CHANGE, msg.getNotifier(), EMFResourceContentProperty.this, diff)));
      }
    }

    public void addTo(Object source)
    {
      if( source == null ) {
        return;
      }

      Resource resource = (Resource)source;
      NotifyingList<EObject> wrappedList = (NotifyingList<EObject>)resource.getContents();

      if (wrappedList.getNotifier() instanceof Notifier)
      {
        Notifier notifier = (Notifier)wrappedList.getNotifier();
        notifier.eAdapters().add(this);
      }
      else
      {
        throw new IllegalArgumentException("Wrapped list must have a notifier attached!");
      }
    }

    public void removeFrom(Object source)
    {
      if( source == null ) {
        return;
      }

      Resource resource = (Resource)source;
      NotifyingList<EObject> wrappedList = (NotifyingList<EObject>)resource.getContents();

      if (wrappedList.getNotifier() instanceof Notifier)
      {
        Notifier notifier = (Notifier)wrappedList.getNotifier();
        notifier.eAdapters().remove(this);
      }
      else
      {
        throw new IllegalArgumentException("Wrapped list must have a notifier attached!");
      }
    }

  }
}
