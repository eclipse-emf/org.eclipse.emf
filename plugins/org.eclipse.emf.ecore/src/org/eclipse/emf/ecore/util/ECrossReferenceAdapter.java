/**
 * <copyright> 
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: ECrossReferenceAdapter.java,v 1.17.2.3 2007/06/05 10:46:30 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
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
    protected Map proxyMap;
    
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
        new BasicEList()
        {
          protected Object[] newData(int capacity)
          {
            return new EStructuralFeature.Setting [capacity];
          }

          public boolean add(Object object)
          {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting)object;
            EObject eObject = setting.getEObject();
            EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
            EStructuralFeature.Setting [] settingData =  (EStructuralFeature.Setting[])data;
            for (int i = 0; i < size; ++i)
            {
              EStructuralFeature.Setting containedSetting = settingData[i];
              if (containedSetting.getEObject() == eObject && containedSetting.getEStructuralFeature() == eStructuralFeature)
              {
                return false;
              }
            }
            addUnique(object);
            return true;
          }
        };
    }
    
    public void add(EObject eObject)
    {
      handleCrossReference(eObject);
      if (!resolve())
      {
        addProxy(eObject, eObject);
      }
    }
    
    protected void add(InternalEObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      super.add(eObject, eReference, crossReferencedEObject);
      if (!resolve())
      {
        addProxy(crossReferencedEObject, eObject);
      }
    }
    
    public void add(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      add((InternalEObject)eObject, eReference, crossReferencedEObject);
    }
    
    protected void addProxy(EObject proxy, EObject context)
    {
      if (proxy.eIsProxy())
      {
        if (proxyMap == null)
        {
          proxyMap = new HashMap();
        }
        URI uri = normalizeURI(((InternalEObject)proxy).eProxyURI(), context);
        List proxies = (List)proxyMap.get(uri);
        if (proxies == null)
        {
          proxyMap.put(uri, proxies = new BasicEList.FastCompare());
        }
        proxies.add(proxy);
      }
    }

    public Object remove(EObject eObject)
    {
      if (!resolve())
      {
        removeProxy(eObject, eObject);
      }
      return super.remove(eObject);
    }

    public void remove(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      if (!resolve())
      {
        removeProxy(crossReferencedEObject, eObject);
      }
      BasicEList collection = (BasicEList)get(crossReferencedEObject);
      if (collection != null)
      {
        EStructuralFeature.Setting [] settingData =  (EStructuralFeature.Setting[])collection.data();
        for (int i = 0, size = collection.size(); i < size; ++i)
        {
          EStructuralFeature.Setting setting = settingData[i];
          if (setting.getEObject() == eObject && setting.getEStructuralFeature() == eReference)
          {
            if (collection.size() == 1)
            {
              super.remove(crossReferencedEObject);  
            }
            else
            {
              collection.remove(i);
            }
            break;
          }
        }
      }      
    }

    protected void removeProxy(EObject proxy, EObject context)
    {
      if (proxyMap != null && proxy.eIsProxy())
      {
        URI uri = normalizeURI(((InternalEObject)proxy).eProxyURI(), context);
        List proxies = (List)proxyMap.get(uri);
        if (proxies != null)
        {
          proxies.remove(proxy);
          if (proxies.isEmpty())
          {
            proxyMap.remove(uri);
          }
        }
      }
    }
    
    protected List removeProxies(URI uri)
    {
      return proxyMap != null ? (List)proxyMap.remove(uri) : null;
    }
    
    protected URI normalizeURI(URI uri, EObject objectContext)
    {
      // This should be the same as the logic in ResourceImpl.getEObject(String).
      //
      String fragment = uri.fragment();
      if (fragment != null)
      {
        int length = fragment.length();
        if (length > 0 && fragment.charAt(0) != '/' && fragment.charAt(length - 1) == '?')
        {
          int index = fragment.lastIndexOf('?', length - 2);
          if (index > 0)
          {
            uri = uri.trimFragment().appendFragment(fragment.substring(0, index));
          }
        }
      }
      Resource resourceContext = objectContext.eResource();
      if (resourceContext != null)
      {
        ResourceSet resourceSetContext = resourceContext.getResourceSet();
        if (resourceSetContext != null)
        {
          return resourceSetContext.getURIConverter().normalize(uri);
        }
      }
      return uri;
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
    return getNonNavigableInverseReferences(eObject, !resolve());
  }

  public Collection getNonNavigableInverseReferences(EObject eObject, boolean resolve)
  {
    if (resolve)
    {
      resolveAll(eObject);
    }

    Collection result = (Collection)inverseCrossReferencer.get(eObject);
    if (result == null)
    {
      result = Collections.EMPTY_LIST;
    }
    return result;
  }
  
  public Collection getInverseReferences(EObject eObject)
  {
    return getInverseReferences(eObject, !resolve());
  }

  public Collection getInverseReferences(EObject eObject, boolean resolve)
  {
    Collection result = new ArrayList();
    
    if (resolve)
    {
      resolveAll(eObject);
    }
    
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
      if (eOpposite != null && !eReference.isContainer() && eObject.eIsSet(eReference))
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
  
  protected void resolveAll(EObject eObject)
  {
    if (!eObject.eIsProxy())
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        URI uri = resource.getURI();
        if (uri != null)
        {
          ResourceSet resourceSet = resource.getResourceSet();
          if (resourceSet != null)
          {
            uri = resourceSet.getURIConverter().normalize(uri);
          }
          uri = uri.appendFragment(resource.getURIFragment(eObject));
        }
        else
        {
          URI.createHierarchicalURI(null, null, resource.getURIFragment(eObject));
        }
        List proxies = inverseCrossReferencer.removeProxies(uri);
        if (proxies != null)
        {
          for (int i = 0, size = proxies.size(); i < size; ++i)
          {
            EObject proxy = (EObject)proxies.get(i);
            for (Iterator inverseReferences = getInverseReferences(proxy, false).iterator(); inverseReferences.hasNext();)
            {
              EStructuralFeature.Setting setting = (EStructuralFeature.Setting)inverseReferences.next();
              Object value = setting.get(true);
              if (setting.getEStructuralFeature().isMany())
              {
                InternalEList list = (InternalEList)value;
                List basicList = list.basicList();
                int index =  basicList.indexOf(proxy);
                if (index != -1)
                {
                  list.get(index);
                }
              }
            }
          }
        }
      }
    }
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
              addAdapter(child);
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
      case Notification.UNSET:
      {
        Object newValue = (Object)notification.getNewValue();
        if (newValue != null && newValue != Boolean.TRUE && newValue != Boolean.FALSE)
        {
          addAdapter((Notifier)newValue);
        }
        break;
      }
      case Notification.SET:
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
        EObject notifier = (EObject)notification.getNotifier();
        EReference feature = (EReference)notification.getFeature();
        EObject oldValue = (EObject)notification.getOldValue();
        if (oldValue != null)
        {
          inverseCrossReferencer.remove(notifier, feature, oldValue);
        }
        EObject newValue = (EObject)notification.getNewValue();
        if (newValue != null)
        {
          inverseCrossReferencer.add(notifier, feature, newValue);
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
        EObject notifier = (EObject)notification.getNotifier();
        EReference feature = (EReference)notification.getFeature();
        Collection newValues = (Collection)notification.getNewValue();
        for (Iterator i = newValues.iterator(); i.hasNext(); )
        {
          EObject newValue = (EObject)i.next();
          inverseCrossReferencer.add(notifier, feature, newValue);
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
        EObject notifier = (EObject)notification.getNotifier();
        EReference feature = (EReference)notification.getFeature();
        Collection oldValues = (Collection)notification.getOldValue();
        for (Iterator i = oldValues.iterator(); i.hasNext(); )
        {
          EObject oldValue = (EObject)i.next();
          inverseCrossReferencer.remove(notifier, feature, oldValue);
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
      setTarget((EObject)target);
    }
    else if (target instanceof Resource)
    {
      setTarget((Resource)target);
    }
    else if (target instanceof ResourceSet)
    {
      setTarget((ResourceSet)target);
    }
  }

  /**
   * Handles installation of the adapter on an EObject
   * by adding the adapter to each of the directly contained objects.
   */
  protected void setTarget(EObject target)
  {
    inverseCrossReferencer.add(target);
    for (Iterator i = resolve() ? target.eContents().iterator() : ((InternalEList)target.eContents()).basicIterator(); i.hasNext(); )
    {
      Notifier notifier = (Notifier)i.next();
      addAdapter(notifier);
    }
  }

  /**
   * Handles installation of the adapter on a Resource
   * by adding the adapter to each of the directly contained objects.
   */
  protected void setTarget(Resource target)
  {
    if (!target.isLoaded())
    {
      unloadedResources.add(target);
    }
    List contents = target.getContents();
    for (int i = 0, size = contents.size(); i < size; ++i)
    {
      Notifier notifier = (Notifier)contents.get(i);
      addAdapter(notifier);
    }
  }

  /**
   * Handles installation of the adapter on a ResourceSet
   * by adding the adapter to each of the directly contained objects.
   */
  protected void setTarget(ResourceSet target)
  {
    List resources =  target.getResources();
    for (int i = 0; i < resources.size(); ++i)
    {
      Notifier notifier = (Notifier)resources.get(i);
      addAdapter(notifier);
    }
  }

  /**
   * Handles deinstallation of the adapter
   * by removing the adapter to each of the directly contained objects.
   */
  public void unsetTarget(Notifier target)
  {
    if (target instanceof EObject)
    {
      unsetTarget((EObject)target);
    }
    else if (target instanceof Resource)
    {
      unsetTarget((Resource)target);
    }
    else if (target instanceof ResourceSet)
    {
      unsetTarget((ResourceSet)target);
    }
  }

  /**
   * Handles deinstallation of the adapter from an EObject
   * by removing the adapter to each of the directly contained objects.
   */
  protected void unsetTarget(EObject target)
  {
    inverseCrossReferencer.remove(target);
    for (EContentsEList.FeatureIterator i = inverseCrossReferencer.getCrossReferences(target); i.hasNext(); )
    {
      EObject crossReferencedEObject = (EObject)i.next();
      inverseCrossReferencer.remove(target, (EReference)i.feature(), crossReferencedEObject);     
    }

    for (Iterator i = resolve() ? target.eContents().iterator() : ((InternalEList)target.eContents()).basicIterator(); i.hasNext(); )
    {
      // Don't remove the adapter if the object is in a different resource 
      // and that resource (and hence all its contents) are being cross referenced.
      //
      InternalEObject internalEObject = (InternalEObject)i.next();
      Resource eDirectResource = internalEObject.eDirectResource();
      if (eDirectResource == null || !eDirectResource.eAdapters().contains(this))
      {
        removeAdapter(internalEObject);
      }
    }
  }

  /**
   * Handles deinstallation of the adapter from a Resource
   * by removing the adapter to each of the directly contained objects.
   */
  protected void unsetTarget(Resource target)
  {
    List contents = target.getContents();
    for (int i = 0, size = contents.size(); i < size; ++i)
    {
      Notifier notifier = (Notifier)contents.get(i);
      removeAdapter(notifier);
    }
  }

  /**
   * Handles deinstallation of the adapter from a ResourceSet
   * by removing the adapter to each of the directly contained objects.
   */
  protected void unsetTarget(ResourceSet target)
  {
    List resources =  target.getResources();
    for (int i = 0; i < resources.size(); ++i)
    {
      Notifier notifier = (Notifier)resources.get(i);
      removeAdapter(notifier);
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
