/**
 * <copyright>
 *
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 * </copyright>
 *
 * $Id: EMFPropertyListener.java,v 1.6 2010/05/04 12:59:27 tschindl Exp $
 */
package org.eclipse.emf.databinding.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.SimplePropertyEvent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.5
 */
public abstract class EMFPropertyListener extends AdapterImpl implements INativePropertyListener
{

  public void addTo(Object source)
  {
    if (source != null)
      ((EObject)source).eAdapters().add(this);
  }

  public void removeFrom(Object source)
  {
    if (source != null)
      ((EObject)source).eAdapters().remove(this);
  }

  @Override
  public abstract void notifyChanged(Notification msg);

  /**
   * @return the listener
   */
  protected abstract ISimplePropertyListener getListener();

  /**
   * @return the feature
   */
  protected abstract EStructuralFeature getFeature();

  /**
   * @return the owner property
   */
  protected abstract IProperty getOwner();

  /**
   *
   */
  public abstract static class EMFListPropertyListener extends EMFPropertyListener
  {
    @Override
    public void notifyChanged(Notification msg)
    {
      if (getFeature() == msg.getFeature() && !msg.isTouch())
      {
        final ListDiff diff;
        switch (msg.getEventType())
        {
          case Notification.ADD: {
            diff = Diffs.createListDiff(Diffs.createListDiffEntry(msg.getPosition(), true, msg.getNewValue()));
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
            int[] positions = (int[])msg.getNewValue();
            if (positions == null)
            {
              int index = 0;
              for (Object oldValue : oldValues)
              {
                listDiffEntries[index] = Diffs.createListDiffEntry(0, false, oldValue);
                ++index;
              }
            }
            else
            {
              int index = 0;
              for (Object oldValue : oldValues)
              {
                listDiffEntries[index] = Diffs.createListDiffEntry(positions[index] - index, false, oldValue);
                ++index;
              }
            }
            diff = Diffs.createListDiff(listDiffEntries);
            break;
          }
          case Notification.SET:
          case Notification.RESOLVE: {
            ListDiffEntry[] listDiffEntries = new ListDiffEntry [2];
            int pos = msg.getPosition();
            // Looks like a single valued feature
            if (pos == -1)
            {
              pos = 0;
            }
            listDiffEntries[0] = Diffs.createListDiffEntry(pos, false, msg.getOldValue());
            listDiffEntries[1] = Diffs.createListDiffEntry(pos, true, msg.getNewValue());
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
        getListener().handleEvent((new SimplePropertyEvent(SimplePropertyEvent.CHANGE, msg.getNotifier(), getOwner(), diff)));
      }
    }
  }

  /**
   *
   */
  public abstract static class EMFSetPropertyListener extends EMFPropertyListener
  {
    @Override
    public void notifyChanged(Notification msg)
    {
      if (getFeature() == msg.getFeature() && !msg.isTouch())
      {
        final SetDiff diff;
        switch (msg.getEventType())
        {
          case Notification.ADD: {
            diff = Diffs.createSetDiff(Collections.singleton(msg.getNewValue()), Collections.emptySet());
            break;
          }
          case Notification.ADD_MANY: {
            Collection< ? > newValues = (Collection< ? >)msg.getNewValue();
            diff = Diffs.createSetDiff(new HashSet<Object>(newValues), Collections.emptySet());
            break;
          }
          case Notification.REMOVE: {
            diff = Diffs.createSetDiff(Collections.emptySet(), Collections.singleton(msg.getOldValue()));
            break;
          }
          case Notification.REMOVE_MANY: {
            Collection< ? > oldValues = (Collection< ? >)msg.getOldValue();
            diff = Diffs.createSetDiff(Collections.emptySet(), new HashSet<Object>(oldValues));
            break;
          }
          case Notification.SET:
          case Notification.RESOLVE: {
            diff = Diffs.createSetDiff(Collections.singleton(msg.getNewValue()), Collections.singleton(msg.getOldValue()));
            break;
          }
          case Notification.MOVE:
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
        getListener().handleEvent((new SimplePropertyEvent(SimplePropertyEvent.CHANGE, msg.getNotifier(), getOwner(), diff)));
      }
    }
  }

  /**
   *
   */
  public abstract static class EMFMapPropertyListener extends EMFPropertyListener
  {
    @Override
    public void notifyChanged(Notification msg)
    {
      if (getFeature() == msg.getFeature() && !msg.isTouch())
      {
        getListener().handleEvent(
          new SimplePropertyEvent(SimplePropertyEvent.CHANGE, msg.getNotifier(), getOwner(), Diffs.computeMapDiff(
            (Map< ? , ? >)msg.getOldValue(),
            (Map< ? , ? >)msg.getNewValue())));
      }
    }
  }

  /**
   *
   */
  public abstract static class EMFValuePropertyListener extends EMFPropertyListener
  {
    @Override
    public void notifyChanged(Notification msg)
    {
      if (getFeature() == msg.getFeature() && !msg.isTouch())
      {
        getListener().handleEvent(
          new SimplePropertyEvent(SimplePropertyEvent.CHANGE, msg.getNotifier(), getOwner(), Diffs.createValueDiff(
            msg.getOldValue(),
            msg.getNewValue())));
      }
    }
  }
}
