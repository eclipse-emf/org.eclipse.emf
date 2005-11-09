/**
 * <copyright> 
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: ECrossReferenceAdapter.java,v 1.1 2005/11/09 19:37:37 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * An adapter that maintains itself as an adapter for all contained objects.
 * It can be installed for an {@link EObject}, a {@link Resource}, or a {@link ResourceSet}.
 * @since 2.2
 */
public class ECrossReferenceAdapter extends AdapterImpl
{
  protected Set unloadedResources = new HashSet();
  
  protected static class InverseCrossReferencer extends EcoreUtil.CrossReferencer
  {
    protected InverseCrossReferencer()
    {
      super((Collection)null);
    }
    
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return eReference.getEOpposite() == null;
    }
    
    protected Collection newCollection()
    {
      return 
        new ArrayList()
        {
          public boolean add(Object object)
          {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting)object;
            EObject eObject = setting.getEObject();
            EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
            for (Iterator i = iterator(); i.hasNext(); )
            {
              EStructuralFeature.Setting containedSetting = (EStructuralFeature.Setting)i.next();
              if (containedSetting.getEObject() == eObject && containedSetting.getEStructuralFeature() == eStructuralFeature)
              {
                return false;
              }
            }
            return super.add(object);
          }
        };
    }
    
    public void add(EObject eObject)
    {
      handleCrossReference(eObject);
    }
    
    public void add(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      getCollection(crossReferencedEObject).add(((InternalEObject)eObject).eSetting(eReference));
    }
    
    public void remove(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      Collection collection = (Collection)get(crossReferencedEObject);
      if (collection != null)
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          EStructuralFeature.Setting setting = (EStructuralFeature.Setting)i.next();
          if (setting.getEObject() == eObject && setting.getEStructuralFeature() == eReference)
          {
            if (collection.size() == 1)
            {
              remove(crossReferencedEObject);  
            }
            else
            {
              i.remove();
            }
            break;
          }
        }
      }
    }
  }
  
  protected InverseCrossReferencer inverseCrossReferencer = new InverseCrossReferencer();
  
  protected void adapt(Notifier notifier)
  {
    List eAdapters = notifier.eAdapters();
    if (!eAdapters.contains(this))
    {
      eAdapters.add(this);
    }
  }
  
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
    if (notifier instanceof EObject)
    {
      Object feature = notification.getFeature();
      if (feature instanceof EReference)
      {
        EReference reference = (EReference)feature;
        if (reference.isContainment())
        {
          handleContainment(notification);
        }
        else if (reference.getEOpposite() == null)
        {
          handleCrossReference(reference, notification);
        }
      }
    }
    else if (notifier instanceof Resource)
    {
      switch (notification.getFeatureID(Resource.class))
      { 
        case Resource.RESOURCE__CONTENTS:
        {
          if (!unloadedResources.contains(notifier))
          {
            handleContainment(notification);
          }
          break;
        }
        case Resource.RESOURCE__IS_LOADED:
        {
          if (notification.getNewBooleanValue())
          {
            unloadedResources.remove(notifier);
            for (Iterator i = ((Resource)notifier).getContents().iterator(); i.hasNext(); )
            {
              Notifier child = (Notifier)i.next();
              adapt(child);
            }
          }
          else
          {
            unloadedResources.add(notifier);
          }
          break;
        }
      }
    }
    else if (notifier instanceof ResourceSet)
    {
      if (notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES)
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
        Notifier newValue = (Notifier)notification.getNewValue();
        if (newValue != null)
        {
          adapt(newValue);
        }
        break;
      }
      case Notification.ADD:
      {
        Notifier newValue = (Notifier)notification.getNewValue();
        if (newValue != null)
        {
          adapt(newValue);
        }
        break;
      }
      case Notification.ADD_MANY:
      {
        Collection newValues = (Collection)notification.getNewValue();
        for (Iterator i = newValues.iterator(); i.hasNext(); )
        {
          Notifier newValue = (Notifier)i.next();
          adapt(newValue);
        }
        break;
      }
    }
  }
  
  /**
   * Handles a containment change by adding and removing the adapter as appropriate.
   */
  protected void handleCrossReference(EReference reference, Notification notification)
  {
    switch (notification.getEventType())
    {
      case Notification.RESOLVE:
      case Notification.SET:
      case Notification.UNSET:
      {
        EObject oldValue = (EObject)notification.getOldValue();
        if (oldValue != null)
        {
          inverseCrossReferencer.remove((EObject)notification.getNotifier(), (EReference)notification.getFeature(), oldValue);
        }
        EObject newValue = (EObject)notification.getNewValue();
        if (newValue != null)
        {
          inverseCrossReferencer.add((EObject)notification.getNotifier(), (EReference)notification.getFeature(), newValue);
        }
        break;
      }
      case Notification.ADD:
      {
        EObject newValue = (EObject)notification.getNewValue();
        if (newValue != null)
        {
          inverseCrossReferencer.add((EObject)notification.getNotifier(), (EReference)notification.getFeature(), newValue);
        }
        break;
      }
      case Notification.ADD_MANY:
      {
        Collection newValues = (Collection)notification.getNewValue();
        for (Iterator i = newValues.iterator(); i.hasNext(); )
        {
          EObject newValue = (EObject)i.next();
          inverseCrossReferencer.add((EObject)notification.getNotifier(), (EReference)notification.getFeature(), newValue);
        }
        break;
      }
      case Notification.REMOVE:
      {
        EObject oldValue = (EObject)notification.getOldValue();
        if (oldValue != null)
        {
          inverseCrossReferencer.remove((EObject)notification.getNotifier(), (EReference)notification.getFeature(), oldValue);
        }
        break;
      }
      case Notification.REMOVE_MANY:
      {
        Collection oldValues = (Collection)notification.getOldValue();
        for (Iterator i = oldValues.iterator(); i.hasNext(); )
        {
          EObject oldValue = (EObject)i.next();
          inverseCrossReferencer.remove((EObject)notification.getNotifier(), (EReference)notification.getFeature(), oldValue);
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
      inverseCrossReferencer.add(eObject);
      handleChildren(eObject.eContents());
    }
    else if (target instanceof Resource)
    {
      Resource resource = (Resource)target;
      if (!resource.isLoaded())
      {
        unloadedResources.add(resource);
      }
      handleChildren(resource.getContents());
    }
    else if (target instanceof ResourceSet)
    {
      handleChildren(((ResourceSet)target).getResources());
    }
  }
  
  protected void handleChildren(Collection contents)
  {
    for (Iterator i = contents.iterator(); i.hasNext(); )
    {
      Notifier notifier = (Notifier)i.next();
      adapt(notifier);
    }
  } 

  /**
   * Handles installation of the adapter
   * by adding the adapter to each of the directly contained objects.
   */
  public void unsetTarget(Notifier target)
  {
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
  
  public void dump()
  {
    EcoreUtil.CrossReferencer.print(System.out, inverseCrossReferencer);
  }
}
