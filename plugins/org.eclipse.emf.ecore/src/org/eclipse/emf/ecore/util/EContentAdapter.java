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
 * $Id: EContentAdapter.java,v 1.5 2006/02/21 11:15:43 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
      case Notification.RESOLVE:
      {
        // We need to be careful that the proxy may be resolved while we are attaching this adapter.
        // We need to avoid attaching the adapter during the resolve 
        // and also attaching it again as we walk the eContents() later.
        // Checking here avoids having to check during addAdapter.
        //
        Notifier oldValue = (Notifier)notification.getOldValue();
        if (oldValue.eAdapters().contains(this))
        {
          removeAdapter(oldValue);
          Notifier newValue = (Notifier)notification.getNewValue();
          addAdapter(newValue);
        }
        break;
      }
      case Notification.SET:
      case Notification.UNSET:
      {
        Notifier oldValue = (Notifier)notification.getOldValue();
        if (oldValue != null)
        {
          removeAdapter(oldValue);
        }
        Notifier newValue = (Notifier)notification.getNewValue();
        if (newValue != null)
        {
          addAdapter(newValue);
        }
        break;
      }
      case Notification.ADD:
      {
        Notifier newValue = (Notifier)notification.getNewValue();
        if (newValue != null)
        {
          addAdapter(newValue);
        }
        break;
      }
      case Notification.ADD_MANY:
      {
        Collection newValues = (Collection)notification.getNewValue();
        for (Iterator i = newValues.iterator(); i.hasNext(); )
        {
          Notifier newValue = (Notifier)i.next();
          addAdapter(newValue);
        }
        break;
      }
      case Notification.REMOVE:
      {
        Notifier oldValue = (Notifier)notification.getOldValue();
        if (oldValue != null)
        {
          removeAdapter(oldValue);
        }
        break;
      }
      case Notification.REMOVE_MANY:
      {
        Collection oldValues = (Collection)notification.getOldValue();
        for (Iterator i = oldValues.iterator(); i.hasNext(); )
        {
          Notifier oldContentValue = (Notifier)i.next();
          removeAdapter(oldContentValue);
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
    
    if (target instanceof EObject)
    {
      EObject eObject = (EObject)target;
      for (Iterator i = resolve() ? eObject.eContents().iterator() : ((InternalEList)eObject.eContents()).basicIterator(); i.hasNext(); )
      {
        Notifier notifier = (Notifier)i.next();
        addAdapter(notifier);
      }
    }
    else if (target instanceof Resource)
    {
      List contents = ((Resource)target).getContents();
      for (int i = 0, size = contents.size(); i < size; ++i)
      {
        Notifier notifier = (Notifier)contents.get(i);
        addAdapter(notifier);
      }
    }
    else if (target instanceof ResourceSet)
    {
      List resources =  ((ResourceSet)target).getResources();
      for (int i = 0; i < resources.size(); ++i)
      {
        Notifier notifier = (Notifier)resources.get(i);
        addAdapter(notifier);
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
    
    if (target instanceof EObject)
    {
      EObject eObject = (EObject)target;
      for (Iterator i = resolve() ? eObject.eContents().iterator() : ((InternalEList)eObject.eContents()).basicIterator(); i.hasNext(); )
      {
        Notifier notifier = (Notifier)i.next();
        removeAdapter(notifier);
      }
    }
    else if (target instanceof Resource)
    {
      List contents = ((Resource)target).getContents();
      for (int i = 0, size = contents.size(); i < size; ++i)
      {
        Notifier notifier = (Notifier)contents.get(i);
        removeAdapter(notifier);
      }
    }
    else if (target instanceof ResourceSet)
    {
      List resources =  ((ResourceSet)target).getResources();
      for (int i = 0; i < resources.size(); ++i)
      {
        Notifier notifier = (Notifier)resources.get(i);
        removeAdapter(notifier);
      }
    }
  }
  
  protected void addAdapter(Notifier notifier)
  {
    notifier.eAdapters().add(this); 
  }
  
  protected void removeAdapter(Notifier notifier)
  {
    notifier.eAdapters().remove(this); 
  }
  
  protected boolean resolve()
  {
    return true;
  }
}
