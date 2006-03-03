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
 * $Id: ECrossReferenceAdapter.java,v 1.8 2006/03/03 21:19:12 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
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
public class ECrossReferenceAdapter implements Adapter.Internal
{
  /**
   * Returns the first {@link ECrossReferenceAdapter} in the notifier's {@link Notifier#eAdapters() adapter list}, 
   * or <code>null</code>, if there isn't one.
   * @param notifier the object to search.
   * @return the first ECrossReferenceAdapter in the notifier's adapter list.
   */
  public static ECrossReferenceAdapter getCrossReferenceAdapter(Notifier notifier) 
  {
    List adapters = notifier.eAdapters();
    for (int i = 0, size = adapters.size(); i < size; ++i)
    {
      Object adapter = adapters.get(i);
      if (adapter instanceof ECrossReferenceAdapter)
      {
        return (ECrossReferenceAdapter)adapter;
      }
    }
    return null;
  }
  
  protected Set unloadedResources = new HashSet();
  
  protected class InverseCrossReferencer extends EcoreUtil.CrossReferencer
  {
    protected InverseCrossReferencer()
    {
      super((Collection)null);
    }
    
    protected EContentsEList.FeatureIterator getCrossReferences(EObject eObject)
    {
      return
        new ECrossReferenceEList.FeatureIteratorImpl(eObject)
        {
          protected boolean isIncluded(EStructuralFeature eStructuralFeature)
          {
            return FeatureMapUtil.isFeatureMap(eStructuralFeature) || ECrossReferenceAdapter.this.isIncluded((EReference)eStructuralFeature);
          }

          protected boolean resolve()
          {
            return InverseCrossReferencer.this.resolve();
          }
        };
    }

    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return isIncluded(eReference);
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
    protected boolean resolve()
    {
      return ECrossReferenceAdapter.this.resolve();
    }
  }
  
  protected InverseCrossReferencer inverseCrossReferencer;
  
  public ECrossReferenceAdapter()
  {
    inverseCrossReferencer = createInverseCrossReferencer();
  }
  
  public Collection getNonNavigableInverseReferences(EObject eObject)
  {
    Collection result = (Collection)inverseCrossReferencer.get(eObject);
    if (result == null)
    {
      result = Collections.EMPTY_LIST;
    }
    return result;
  }
  
  public Collection getInverseReferences(EObject eObject)
  {
    Collection result = new ArrayList();
    
    EObject eContainer = eObject.eContainer();
    if (eContainer != null)
    {
      result.add(((InternalEObject)eContainer).eSetting(eObject.eContainmentFeature()));
    }
    
    Collection nonNavigableInverseReferences = (Collection)inverseCrossReferencer.get(eObject);
    if (nonNavigableInverseReferences != null)
    {
      result.addAll(nonNavigableInverseReferences);
    }
    
    for (Iterator i = eObject.eClass().getEAllReferences().iterator(); i.hasNext(); )
    {
      EReference eReference = (EReference)i.next();
      EReference eOpposite = eReference.getEOpposite();
      if (eOpposite != null && !eReference.isContainer() && !eReference.isContainment() && eObject.eIsSet(eReference))
      {
        if (eReference.isMany())
        {
          Object collection = eObject.eGet(eReference);
          for (Iterator j = resolve() ? ((Collection)collection).iterator() : ((InternalEList)collection).basicIterator(); j.hasNext(); )
          {
            InternalEObject referencingEObject = (InternalEObject)j.next();
            result.add(referencingEObject.eSetting(eOpposite));
          }
        }
        else
        {
          result.add(((InternalEObject)eObject.eGet(eReference, resolve())).eSetting(eOpposite));
        }
      }
    }
    
    return result;
  }
  
  protected boolean isIncluded(EReference eReference)
  {
    return eReference.getEOpposite() == null && !eReference.isDerived();
  }
  
  protected InverseCrossReferencer createInverseCrossReferencer()
  {
    return new InverseCrossReferencer();
  }
  
  /**
   * Handles a notification by calling {@link #selfAdapt selfAdapter}.
   */
  public void notifyChanged(Notification notification)
  {
    selfAdapt(notification);
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
        else if (isIncluded(reference))
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
              // We get this notification during the first addition of an addAll on an unloaded resource 
              // that isn't actually in the process of loading.
              // At that point, not all the children have been added.
              // The notification for the change to the contents will come later.
              // So we can ignore the child here and count on the fact that we'll process all the children shortly.
              //
              if (child != null)
              {
                addAdapter(child);
              }
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
      case Notification.RESOLVE:
      {
        Notifier oldValue = (Notifier)notification.getOldValue();
        removeAdapter(oldValue);
        Notifier newValue = (Notifier)notification.getNewValue();
        addAdapter(newValue);
        break;
      }
      case Notification.SET:
      case Notification.UNSET:
      {
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
    }
  }
  
  /**
   * Handles a cross reference change by adding and removing the adapter as appropriate.
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
    if (target instanceof EObject)
    {
      EObject eObject = (EObject)target;
      inverseCrossReferencer.add(eObject);
      for (Iterator i = resolve() ? eObject.eContents().iterator() : ((InternalEList)eObject.eContents()).basicIterator(); i.hasNext(); )
      {
        Notifier notifier = (Notifier)i.next();
        addAdapter(notifier);
      }
    }
    else if (target instanceof Resource)
    {
      Resource resource = (Resource)target;
      if (!resource.isLoaded())
      {
        unloadedResources.add(resource);
      }
      List contents = resource.getContents();
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
  public void unsetTarget(Notifier target)
  {
    if (target instanceof EObject)
    {
      EObject eObject = (EObject)target;
      inverseCrossReferencer.remove(eObject);
      for (EContentsEList.FeatureIterator i = inverseCrossReferencer.getCrossReferences(eObject); i.hasNext(); )
      {
        EObject crossReferencedEObject = (EObject)i.next();
        inverseCrossReferencer.remove(eObject, (EReference)i.feature(), crossReferencedEObject);     
      }

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
    List eAdapters = notifier.eAdapters();
    if (!eAdapters.contains(this))
    {
      eAdapters.add(this);
    }
  }
  
  protected void removeAdapter(Notifier notifier)
  {
    notifier.eAdapters().remove(this); 
  }
  
  public void dump()
  {
    EcoreUtil.CrossReferencer.print(System.out, inverseCrossReferencer);
  }

  public Notifier getTarget()
  {
    return null;
  }

  public boolean isAdapterForType(Object type)
  {
    return false;
  }
  
  protected boolean resolve()
  {
    return true;
  }
}
