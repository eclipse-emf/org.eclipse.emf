/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: EStoreEObjectImpl.java,v 1.8.2.1 2007/07/30 17:39:47 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
import org.eclipse.emf.ecore.util.DelegatingFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * An implementation of '<em><b>EObject</b></em>' that delegates to a {@link org.eclipse.emf.ecore.InternalEObject.EStore store}.
 */
public class EStoreEObjectImpl extends EObjectImpl implements EStructuralFeature.Internal.DynamicValueHolder
{
  /**
   * An internal class for holding less frequently members variables.
   */
  protected static class EStoreEPropertiesHolderImpl implements BasicEObjectImpl.EPropertiesHolder
  {
    protected EClass eClass;
    protected URI eProxyURI;
    protected Resource.Internal eResource;
    protected EList eContents;
    protected EList eCrossReferences;

    public EClass getEClass()
    {
      return eClass;
    }
    
    public void setEClass(EClass eClass)
    {
      this.eClass = eClass;
    }

    public URI getEProxyURI()
    {
      return eProxyURI;
    }

    public void setEProxyURI(URI eProxyURI)
    {
      this.eProxyURI = eProxyURI;
    }

    public Resource.Internal getEResource()
    {
      return eResource;
    }

    public void setEResource(Resource.Internal eResource)
    {
      this.eResource = eResource;
    }

    public EList getEContents()
    {
      return eContents;
    }

    public void setEContents(EList eContents)
    {
      this.eContents = eContents;
    }

    public EList getECrossReferences()
    {
      return eCrossReferences;
    }

    public void setECrossReferences(EList eCrossReferences)
    {
      this.eCrossReferences = eCrossReferences;
    }

    public boolean hasSettings()
    {
      throw new UnsupportedOperationException();
    }

    public void allocateSettings(int maximumDynamicFeatureID)
    {
      throw new UnsupportedOperationException();
    }

    public Object dynamicGet(int dynamicFeatureID)
    {
      throw new UnsupportedOperationException();
    }

    public void dynamicSet(int dynamicFeatureID, Object value)
    {
      throw new UnsupportedOperationException();
    }

    public void dynamicUnset(int dynamicFeatureID)
    {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * A list that delegates to a eStore.
   */
  public static class EStoreEList extends DelegatingEcoreEList.Dynamic
  {
    protected InternalEObject.EStore store; 

    public EStoreEList(InternalEObject owner, EStructuralFeature eStructuralFeature, InternalEObject.EStore store)
    {
      super(owner, eStructuralFeature);
      this.store = store;
    }

    protected List delegateList()
    {
      throw new UnsupportedOperationException();
    }

    public EStructuralFeature getEStructuralFeature()
    {
      return eStructuralFeature;
    }

    protected void delegateAdd(int index, Object object)
    {
      store.add(owner, eStructuralFeature, index, object);
    }

    protected void delegateAdd(Object object)
    {
      delegateAdd(delegateSize(), object);
    }

    protected List delegateBasicList()
    {
      int size = delegateSize();
      if (size == 0)
      {
        return ECollections.EMPTY_ELIST;
      }
      else
      {
        Object[] data = store.toArray(owner, eStructuralFeature);
        return new EcoreEList.UnmodifiableEList(owner, eStructuralFeature, data.length, data);
      }
    }

    protected void delegateClear()
    {
      store.clear(owner, eStructuralFeature);
    }

    protected boolean delegateContains(Object object)
    {
      return store.contains(owner, eStructuralFeature, object);
    }

    protected boolean delegateContainsAll(Collection collection)
    {
      for (Iterator i = collection.iterator(); i.hasNext(); )
      {
        if (!delegateContains(i.next()))
        {
          return false;
        }
      }
      return true;
    }

    protected Object delegateGet(int index)
    {
      return store.get(owner, eStructuralFeature, index);
    }

    protected int delegateHashCode()
    {
      return store.hashCode(owner, eStructuralFeature);
    }

    protected int delegateIndexOf(Object object)
    {
      return store.indexOf(owner, eStructuralFeature, object);
    }

    protected boolean delegateIsEmpty()
    {
      return store.isEmpty(owner, eStructuralFeature);
    }

    protected Iterator delegateIterator()
    {
      return iterator();
    }

    protected int delegateLastIndexOf(Object object)
    {
      return store.lastIndexOf(owner, eStructuralFeature, object);
    }

    protected ListIterator delegateListIterator()
    {
      return listIterator();
    }

    protected Object delegateRemove(int index)
    {
      return store.remove(owner, eStructuralFeature, index);
    }

    protected Object delegateSet(int index, Object object)
    {
      return store.set(owner, eStructuralFeature, index, object);
    }

    protected int delegateSize()
    {
      return store.size(owner, eStructuralFeature);
    }

    protected Object[] delegateToArray()
    {
      return store.toArray(owner, eStructuralFeature);
    }

    protected Object[] delegateToArray(Object[] array)
    {
      return store.toArray(owner, eStructuralFeature, array);
    }

    protected boolean delegateEquals(Object object)
    {
      if (object == this)
      {
        return true;
      }

      if (!(object instanceof List))
      {
        return false;
      }

      List list = (List)object;
      if (list.size() != delegateSize())
      {
        return false;
      }


      for (ListIterator i = list.listIterator(); i.hasNext(); )
      {
        Object element= i.next();
        if (element == null ? get(i.previousIndex()) != null : !element.equals(get(i.previousIndex())))
        {
          return false;
        }
      }

      return true;
    }

    protected String delegateToString()
    {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("[");
      for (int i = 0, size = size(); i < size; )
      {
        Object value = delegateGet(i);
        stringBuffer.append(String.valueOf(value));
        if (++i < size)
        {
          stringBuffer.append(", ");
        }
      }
      stringBuffer.append("]");
      return stringBuffer.toString();
    }
  }

  /**
   * A feature map that delegates to a store.
   */
  public static class EStoreFeatureMap extends DelegatingFeatureMap
  {
    protected final InternalEObject.EStore store; 

    public EStoreFeatureMap(InternalEObject owner, EStructuralFeature eStructuralFeature, InternalEObject.EStore store)
    {
      super(owner, eStructuralFeature);
      this.store = store;
    }
    
    protected List delegateList()
    {
      throw new UnsupportedOperationException();
    }

    public EStructuralFeature getEStructuralFeature()
    {
      return eStructuralFeature;
    }

    protected void delegateAdd(int index, Object object)
    {
      store.add(owner, eStructuralFeature, index, object);
    }

    protected void delegateAdd(Object object)
    {
      delegateAdd(delegateSize(), object);
    }

    protected List delegateBasicList()
    {
      int size = delegateSize();
      if (size == 0)
      {
        return ECollections.EMPTY_ELIST;
      }
      else
      {
        Object[] data = store.toArray(owner, eStructuralFeature);
        return new EcoreEList.UnmodifiableEList(owner, eStructuralFeature, data.length, data);
      }
    }

    protected void delegateClear()
    {
      store.clear(owner, eStructuralFeature);
    }

    protected boolean delegateContains(Object object)
    {
      return store.contains(owner, eStructuralFeature, object);
    }

    protected boolean delegateContainsAll(Collection collection)
    {
      for (Iterator i = collection.iterator(); i.hasNext(); )
      {
        if (!delegateContains(i.next()))
        {
          return false;
        }
      }
      return true;
    }

    protected Object delegateGet(int index)
    {
      return store.get(owner, eStructuralFeature, index);
    }

    protected int delegateHashCode()
    {
      return store.hashCode(owner, eStructuralFeature);
    }

    protected int delegateIndexOf(Object object)
    {
      return store.indexOf(owner, eStructuralFeature, object);
    }

    protected boolean delegateIsEmpty()
    {
      return store.isEmpty(owner, eStructuralFeature);
    }

    protected Iterator delegateIterator()
    {
      return iterator();
    }

    protected int delegateLastIndexOf(Object object)
    {
      return store.lastIndexOf(owner, eStructuralFeature, object);
    }

    protected ListIterator delegateListIterator()
    {
      return listIterator();
    }

    protected Object delegateRemove(int index)
    {
      return store.remove(owner, eStructuralFeature, index);
    }

    protected Object delegateSet(int index, Object object)
    {
      return store.set(owner, eStructuralFeature, index, object);
    }

    protected int delegateSize()
    {
      return store.size(owner, eStructuralFeature);
    }

    protected Object[] delegateToArray()
    {
      return store.toArray(owner, eStructuralFeature);
    }

    protected Object[] delegateToArray(Object[] array)
    {
      return store.toArray(owner, eStructuralFeature, array);
    }

    protected String delegateToString()
    {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("[");
      for (int i = 0, size = size(); i < size; )
      {
        Object value = delegateGet(i);
        stringBuffer.append(String.valueOf(value));
        if (++i < size)
        {
          stringBuffer.append(", ");
        }
      }
      stringBuffer.append("]");
      return stringBuffer.toString();
    }
  }

  protected static final Object [] ENO_SETTINGS = new Object [0];
  protected static final InternalEObject EUNINITIALIZED_CONTAINER = new EObjectImpl();

  protected Object [] eSettings;
  protected InternalEObject.EStore eStore;

  /**
   * Creates a store-based EObject.
   */
  public EStoreEObjectImpl()
  {
    super();
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  /**
   * Creates a store-based EObject.
   */
  public EStoreEObjectImpl(InternalEObject.EStore eStore) 
  {
    super();
    eSetStore(eStore);
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  /**
   * Creates a store-based EObject.
   */
  public EStoreEObjectImpl(EClass eClass)
  {
    super();
    eSetClass(eClass);
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  /**
   * Creates a store-based EObject.
   */
  public EStoreEObjectImpl(EClass eClass, InternalEObject.EStore eStore) 
  {
    super();
    eSetClass(eClass);
    eSetStore(eStore);
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  protected boolean eIsCaching()
  {
    return true;
  }

  public Object dynamicGet(int dynamicFeatureID)
  {
    Object result = eSettings[dynamicFeatureID];
    if (result == null)
    {
      EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
      if (!eStructuralFeature.isTransient())
      {
        if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
        {
          eSettings[dynamicFeatureID] = result = createFeatureMap(eStructuralFeature);
        }
        else if (eStructuralFeature.isMany())
        {
          eSettings[dynamicFeatureID] = result = createList(eStructuralFeature);
        }
        else
        {
          result = eStore().get(this, eStructuralFeature, InternalEObject.EStore.NO_INDEX);
          if (eIsCaching())
          {
            eSettings[dynamicFeatureID] = result;
          }
        }
      }
    }
    return result;
  }

  public void dynamicSet(int dynamicFeatureID, Object value)
  {
    EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
    if (eStructuralFeature.isTransient())
    {
      eSettings[dynamicFeatureID] = value;
    }
    else
    {
      eStore().set(this, eStructuralFeature, InternalEObject.EStore.NO_INDEX, value == NIL ? null : value);
      if (eIsCaching())
      {
        eSettings[dynamicFeatureID] = value;
      }
    }
  }

  public void dynamicUnset(int dynamicFeatureID)
  {
    eStore().unset(this, eDynamicFeature(dynamicFeatureID));
    eSettings[dynamicFeatureID] = null;
  }

  protected boolean eDynamicIsSet(int dynamicFeatureID, EStructuralFeature eFeature)
  {
    return
      dynamicFeatureID < 0 ?
        eOpenIsSet(eFeature) :
        eFeature.isTransient() ?
          eSettingDelegate(eFeature).dynamicIsSet(this, eSettings(), dynamicFeatureID) :
          eStore().isSet(this, eFeature);
  }

  protected EList createList(EStructuralFeature eStructuralFeature)
  {
    EClassifier eType = eStructuralFeature.getEType();
    if (eType.getInstanceClassName() == "java.util.Map$Entry")
    {
      return 
        new EcoreEMap
          ((EClass)eType, 
           eType.getInstanceClass(), 
           new EStoreEList(this, eStructuralFeature, eStore()));
    }
    else
    {
      return new EStoreEList(this, eStructuralFeature, eStore());
    }
  }

  protected FeatureMap createFeatureMap(EStructuralFeature eStructuralFeature)
  {
    return new EStoreFeatureMap(this, eStructuralFeature, eStore());
  }

  public InternalEObject eInternalContainer()
  {
    if (eContainer == EUNINITIALIZED_CONTAINER)
    {
      eInitializeContainer();
    }

    return eContainer;
  }

  public int eContainerFeatureID()
  {
    if (eContainer == EUNINITIALIZED_CONTAINER)
    {
      eInitializeContainer();
    }

    return eContainerFeatureID;
  }

  protected void eInitializeContainer()
  {
    eContainer = eStore().getContainer(this);
    if (eContainer != null)
    {
      EStructuralFeature eContainingFeature = eStore().getContainingFeature(this);
      if (eContainingFeature instanceof EReference)
      {
        EReference eContainingReference = (EReference)eContainingFeature;
        EReference eOpposite = eContainingReference.getEOpposite();
        if (eOpposite != null)
        {
          eContainerFeatureID = eClass().getFeatureID(eOpposite);
          return;
        }
      }

      eContainerFeatureID = EOPPOSITE_FEATURE_BASE - eContainer.eClass().getFeatureID(eContainingFeature);
    }
  }

  public InternalEObject.EStore eStore()
  {
    return eStore;
  }

  public void eSetStore(InternalEObject.EStore store)
  {
    this.eStore = store;
  }

  protected int eStaticFeatureCount()
  {
    return 0;
  }

  public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature)
  {
    return eClass().getFeatureID(eStructuralFeature);
  }

  protected BasicEObjectImpl.EPropertiesHolder eProperties()
  {
    if (eProperties == null)
    {
      eProperties = new EStoreEPropertiesHolderImpl();
    }
    return eProperties;
  }

  protected boolean eHasSettings()
  {
    return eSettings != null;
  }

  protected EStructuralFeature.Internal.DynamicValueHolder eSettings()
  {
    if (eSettings == null)
    {
      int size = eClass().getFeatureCount() - eStaticFeatureCount();
      eSettings = size == 0 ? ENO_SETTINGS : new Object [size];
    }

    return this;
  }

/*
  public String toString()
  {
    String result = super.toString();
    int index = result.indexOf("EStoreEObjectImpl");
    return index == -1 ? result : result.substring(0, index) + result.substring(index + 6);
  }
*/


  /**
   *  This class is for testing purposes only and will be removed.
   */
  public static class EStoreImpl implements InternalEObject.EStore
  {
    // protected static final EStructuralFeature CONTAINING_FEATURE = new EReferenceImpl();
    // protected static final EStructuralFeature CONTAINER = new EReferenceImpl();

    protected Map map = new HashMap();

    public static class Entry
    {
      protected EObject eObject;
      protected EStructuralFeature eStructuralFeature;

      public Entry(InternalEObject eObject, EStructuralFeature eStructuralFeature)
      {
        this.eObject = eObject;
        this.eStructuralFeature = eStructuralFeature;
      }

      public boolean equals(Object that)
      {
        if (that instanceof Entry)
        {
          Entry entry = (Entry)that;
          return eObject == entry.eObject && eStructuralFeature == entry.eStructuralFeature;
        }
        else
        {
          return false;
        }
      }

      public int hashCode()
      {
        return eObject.hashCode() ^ eStructuralFeature.hashCode();
      }
    }

    protected EList getList(Entry entry)
    {
      EList result = (EList)map.get(entry);
      if (result == null)
      {
        result = new BasicEList();
        map.put(entry, result);
      }
      return result;
    }

    public Object get(InternalEObject eObject, EStructuralFeature feature, int index)
    {
      Entry entry = new Entry(eObject, feature);
      if (index == NO_INDEX)
      {
        return map.get(entry);
      }
      else
      {
        return getList(entry).get(index);
      }
    }

    public Object set(InternalEObject eObject, EStructuralFeature feature, int index, Object value)
    {
      // if (feature instanceof EReference && ((EReference)feature).isContainment())
      // {
        // Entry containingFeatureEntry = new Entry(value, CONTAINING_FEATURE);
        // map.put(containingFeatureEntry, feature);
        // Entry containerEntry = new Entry(value, CONTAINER);
        // map.put(containerEntry, feature);
      // }

      Entry entry = new Entry(eObject, feature);
      if (index == NO_INDEX)
      {
        return map.put(entry, value);
      }
      else
      {
        List list = (List)map.get(entry);
        return list.set(index, value);
      }
    }

    public void add(InternalEObject eObject, EStructuralFeature feature, int index, Object value)
    {
      // if (feature instanceof EReference && ((EReference)feature).isContainment())
      // {
        // Entry containingFeatureEntry = new Entry(value, CONTAINING_FEATURE);
        // map.put(containingFeatureEntry, feature);
        // Entry containerEntry = new Entry(value, CONTAINER);
        // map.put(containerEntry, feature);
      // }
      Entry entry = new Entry(eObject, feature);
      getList(entry).add(index, value);
    }

    public Object remove(InternalEObject eObject, EStructuralFeature feature, int index)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).remove(index);
    }

    public Object move(InternalEObject eObject, EStructuralFeature feature, int targetIndex, int sourceIndex)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).move(targetIndex, sourceIndex);
    }

    public void clear(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      map.remove(entry);
      //getList(entry).clear();
    }

    public boolean isSet(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      return map.containsKey(entry);
    }

    public void unset(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      map.remove(entry);
    }

    public int size(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).size();
    }

    public int indexOf(InternalEObject eObject, EStructuralFeature feature, Object value)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).indexOf(value);
    }

    public int lastIndexOf(InternalEObject eObject, EStructuralFeature feature, Object value)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).lastIndexOf(value);
    }

    public Object[] toArray(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).toArray();
    }

    public Object[] toArray(InternalEObject eObject, EStructuralFeature feature, Object[] array)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).toArray(array);
    }

    public boolean isEmpty(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).isEmpty();
    }

    public boolean contains(InternalEObject eObject, EStructuralFeature feature, Object value)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).contains(value);
    } 

    public int hashCode(InternalEObject eObject, EStructuralFeature feature)
    {
      Entry entry = new Entry(eObject, feature);
      return getList(entry).hashCode();
    }

    public InternalEObject getContainer(InternalEObject eObject)
    {
      return null;

      // Entry entry = new Entry(eObject, CONTAINER);
      // return (InternalEObject)map.get(entry);
    }

    public EStructuralFeature getContainingFeature(InternalEObject eObject)
    {
      // This should never be called.
      //
      throw new UnsupportedOperationException();
      // Entry entry = new Entry(eObject, CONTAINING_FEATURE);
      // return (EStructuralFeature)map.get(entry);
    }

    public EObject create(EClass eClass)
    {
      InternalEObject result = new EStoreEObjectImpl(eClass, this);
      return result;
    }
  }
}
