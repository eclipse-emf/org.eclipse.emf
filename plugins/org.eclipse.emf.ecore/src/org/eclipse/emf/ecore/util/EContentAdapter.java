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
 * $Id: EContentAdapter.java,v 1.1.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * An adapter that maintains itself as an adapter for all contained objects 
 * as they come and go.
 * It can be installed for an {@link EObject}, a {@link Resource}, or a {@link ResourceSet}.
 */
public class EContentAdapter extends AdapterImpl
{
  /**
   * Handles a notification by calling {@link #selfAdapt selfAdapter}.
   */
  public void notifyChanged(Notification notification)
  {
    selfAdapt(notification);

    super.notifyChanged(notification);
  }

  /**
   * Handles a notification by calling {@link #handleContainment handleContainment}
   * for any containment-based notification.
   */
  protected void selfAdapt(Notification notification)
  {
    Object notifier = notification.getNotifier();
    if (notification.getEventType() == Notification.REMOVING_ADAPTER)
    {
      unsetTarget(notifier);
    }
    else if (notifier instanceof ResourceSet)
    {
      if (notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES)
      {
        handleContainment(notification);
      }
    }
    else if (notifier instanceof Resource)
    {
      if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS)
      {
        handleContainment(notification);
      }
    }
    else if (notifier instanceof EObject)
    {
      Object feature = notification.getFeature();
      if (feature instanceof EReference && ((EReference)feature).isContainment())
      {
        handleContainment(notification);
      }
    }
  }

  /**
   * Handles a containment change by adding and removing the adapter as appropriate.
   */
  protected void handleContainment(Notification notification)
  {
    switch (notification.getEventType())
    {
      case Notification.SET:
      case Notification.UNSET:
      {
        Notifier oldValue = (Notifier)notification.getOldValue();
        if (oldValue != null)
        {
          oldValue.eAdapters().remove(this);
        }
        Notifier newValue = (Notifier)notification.getNewValue();
        if (newValue != null)
        {
          newValue.eAdapters().add(this);
        }
        break;
      }
      case Notification.ADD:
      {
        Notifier newValue = (Notifier)notification.getNewValue();
        if (newValue != null)
        {
          newValue.eAdapters().add(this);
        }
        break;
      }
      case Notification.ADD_MANY:
      {
        Collection newValues = (Collection)notification.getNewValue();
        for (Iterator i = newValues.iterator(); i.hasNext(); )
        {
          Notifier newValue = (Notifier)i.next();
          newValue.eAdapters().add(this);
        }
        break;
      }
      case Notification.REMOVE:
      {
        Notifier oldValue = (Notifier)notification.getOldValue();
        if (oldValue != null)
        {
          oldValue.eAdapters().remove(this);
        }
        break;
      }
      case Notification.REMOVE_MANY:
      {
        Collection oldValues = (Collection)notification.getOldValue();
        for (Iterator i = oldValues.iterator(); i.hasNext(); )
        {
          Notifier oldContentValue = (Notifier)i.next();
          oldContentValue.eAdapters().remove(this);
        }
        break;
      }
    }
  }

  /**
   * Handles installation of the adapter
   * by adding the adapter to each of the directly contained objects.
   */
  public void setTarget(Notifier target)
  {
    super.setTarget(target);

    Collection contents = 
      target instanceof EObject ?
        ((EObject)target).eContents() :
        target instanceof ResourceSet ?
          ((ResourceSet)target).getResources() :
          target instanceof Resource ?
            ((Resource)target).getContents() :
            null;
    if (contents != null)
    {
      for (Iterator i = contents.iterator(); i.hasNext(); )
      {
        Notifier notifier = (Notifier)i.next();
        notifier.eAdapters().add(this);
      }
    }
  }

  /**
   * Handles installation of the adapter
   * by adding the adapter to each of the directly contained objects.
   */
  protected void unsetTarget(Object target)
  {
    super.setTarget(null);

    Collection contents = 
      target instanceof EObject ?
        ((EObject)target).eContents() :
        target instanceof ResourceSet ?
          ((ResourceSet)target).getResources() :
          target instanceof Resource ?
            ((Resource)target).getContents() :
            null;
    if (contents != null)
    {
      for (Iterator i = contents.iterator(); i.hasNext(); )
      {
        Notifier notifier = (Notifier)i.next();
        notifier.eAdapters().remove(this);
      }
    }
  }
}
