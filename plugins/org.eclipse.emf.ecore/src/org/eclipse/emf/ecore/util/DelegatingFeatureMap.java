/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: DelegatingFeatureMap.java,v 1.9 2004/05/25 19:48:31 emerks Exp $
 */
package org.eclipse.emf.ecore.util;



import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;



public abstract class DelegatingFeatureMap extends DelegatingEcoreEList implements FeatureMap.Internal
{
  protected final FeatureMapUtil.Validator featureMapValidator;
  protected final EStructuralFeature eStructuralFeature;

  public DelegatingFeatureMap(InternalEObject owner, int featureID)
  {
    super(owner);
    this.eStructuralFeature = (EStructuralFeature)owner.eClass().getEAllStructuralFeatures().get(featureID);
    featureMapValidator = FeatureMapUtil.getValidator(owner.eClass(), getEStructuralFeature());
  }

  public DelegatingFeatureMap(InternalEObject owner, EStructuralFeature eStructuralFeature)
  {
    super(owner);
    this.eStructuralFeature = eStructuralFeature;
    featureMapValidator = FeatureMapUtil.getValidator(owner.eClass(), getEStructuralFeature());
  }

/*
  List theList = new java.util.ArrayList();
  protected List delegateList()
  {
    return theList;
  }
*/

  protected Object validate(int index, Object object)
  {
    Object result = super.validate(index, object);
    EStructuralFeature eStructuralFeature = ((Entry)object).getEStructuralFeature();
    if (!eStructuralFeature.isChangeable() || !featureMapValidator.isValid(eStructuralFeature))
    {
      throw
        new RuntimeException
          ("Invalid entry feature '" + eStructuralFeature.getEContainingClass().getName() + "." + eStructuralFeature.getName() + "'");
    }
    return result;
  }

  protected boolean isEObject()
  {
    return false;
  }

  protected boolean isUnique()
  {
    return false;
  }

  protected boolean canContainNull()
  {
    return false;
  }

  protected EClassifier getFeatureType()
  {
    return org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getEJavaObject();
  }

  public EStructuralFeature getEStructuralFeature()
  {
    return eStructuralFeature;
  }

  protected FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value)
  {
    return FeatureMapUtil.createEntry(eStructuralFeature, value);
  }

  protected NotificationImpl createNotification
    (int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index, boolean wasSet)
  {
    return new FeatureMapUtil.FeatureENotificationImpl(owner, eventType, feature, oldObject, newObject, index, wasSet);
  }

  protected boolean isMany(EStructuralFeature feature)
  {
    int upperBound = feature.getUpperBound();
    if (upperBound == ETypedElement.UNSPECIFIED_MULTIPLICITY)
    {
      EClass eclass = owner.eClass();
      if (eclass.getEAllStructuralFeatures().contains(feature))
      {
        return false;
      }
      else if (feature.getEContainingClass().getEPackage() == XMLTypePackage.eINSTANCE)
      {
        return true;
      }
      else
      {
        EStructuralFeature affiliation = ExtendedMetaData.INSTANCE.getAffiliation(eclass, feature);
        return
          affiliation == null ||
            affiliation.isMany() && ExtendedMetaData.INSTANCE.getFeatureKind(affiliation) != ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE;
      }
    }
    else
    {
      return upperBound == ETypedElement.UNBOUNDED_MULTIPLICITY || upperBound > 1 || FeatureMapUtil.isFeatureMap(feature);
    }
  }

  protected boolean hasInverse()
  {
    return true;
  }

  protected boolean hasShadow()
  {
    return true;
  }

  protected int entryIndex(EStructuralFeature feature, int index)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    int count = 0;
    int size = delegateSize();
    int result = size;
    for (int i = 0; i < size; ++i)
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        if (index == count)
        {
          return i;
        }
        ++count;
        result = i + 1;
      }
    }

    if (index == count)
    {
      return result;
    }
    else
    {
      throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
    }
  }

  protected boolean isResolveProxies(EStructuralFeature feature)
  {
    return feature instanceof EReference && ((EReference)feature).isResolveProxies();
  }

  public Object resolveProxy(EStructuralFeature feature, int entryIndex, int index, Object object)
  {
    EObject resolved = resolveProxy((EObject)object);
    if (resolved != object)
    {
      Object oldObject = delegateGet(entryIndex);
      Entry entry = createEntry(feature, resolved);
      delegateSet(entryIndex, validate(entryIndex, entry));
      didSet(entryIndex, entry, oldObject);

      if (isNotificationRequired())
      {
        NotificationImpl notifications = 
          createNotification
            (Notification.RESOLVE, 
             entry.getEStructuralFeature(), 
             object,
             resolved,
             index,
             false);

        notifications.add(createNotification(Notification.RESOLVE, oldObject, entry, index, false));
        notifications.dispatch();
      }

      return resolved;
    }

    return object;
  }

  protected EObject resolveProxy(EObject eObject)
  {
    return owner.eResolveProxy((InternalEObject)eObject);
  }

  public int getModCount()
  {
    return modCount;
  }

  public EStructuralFeature getEStructuralFeature(int index)
  {
    return ((Entry)get(index)).getEStructuralFeature();
  }

  public Object getValue(int index)
  {
    return ((Entry)get(index)).getValue();
  }

  public Object setValue(int index, Object value)
  {
    return ((Entry)set(index, createEntry(getEStructuralFeature(index), value))).getValue();
  }

  public NotificationChain shadowAdd(Object object, NotificationChain notifications)
  {
    if (isNotificationRequired())
    {
      Entry entry = (Entry)object;
      EStructuralFeature feature = entry.getEStructuralFeature();
      Object value = entry.getValue();
      // EATM must fix isSet bits.
      NotificationImpl notification = 
        feature.isMany() ?
          createNotification
            (Notification.ADD,
             feature,
             null, 
             value,
             indexOf(feature, value),
             true) :
          createNotification
            (Notification.SET, 
             feature,
             feature.getDefaultValue(), 
             value,
             Notification.NO_INDEX,
             true);
  
      if (notifications != null)
      {
        notifications.add(notification);
      }
      else
      {
        notifications = notification;
      }
    }
    return notifications;
  }

  public NotificationChain inverseAdd(Object object, NotificationChain notifications)
  {
    Entry entry = (Entry)object;
    EStructuralFeature feature = entry.getEStructuralFeature();
    if (feature instanceof EReference)
    {
      EReference eReference = (EReference)feature;
      EReference eOpposite = eReference.getEOpposite();
      if (eOpposite != null)
      {
        InternalEObject internalEObject = (InternalEObject)entry.getValue();
        if (internalEObject != null)
        {
          notifications = 
            internalEObject.eInverseAdd
              (owner,
               internalEObject.eClass().getEAllStructuralFeatures().indexOf(eOpposite),
               null,
               notifications);
        }
      }
      else if (eReference.isContainment())
      {
        InternalEObject internalEObject = (InternalEObject)entry.getValue();
        if (internalEObject != null)
        {
          InternalEObject oldValue = (InternalEObject)internalEObject.eContainer();
          int containmentFeatureID = owner.eClass().getEAllStructuralFeatures().indexOf(eReference);
          notifications =
            internalEObject.eInverseAdd
              (owner,
               InternalEObject.EOPPOSITE_FEATURE_BASE - (containmentFeatureID == -1 ? getFeatureID() : containmentFeatureID),
               null,
               notifications);
        }
      }
    }

    return notifications;
  }

  public NotificationChain shadowRemove(Object object, NotificationChain notifications)
  {
    if (isNotificationRequired())
    {
      Entry entry = (Entry)object;
      EStructuralFeature feature = entry.getEStructuralFeature();
      Object value = entry.getValue();
      NotificationImpl notification = 
        feature.isMany() ?
          createNotification
            (Notification.REMOVE,
             feature,
             value,
             null, 
             indexOf(feature, value),
             true) :
          createNotification
            (feature.isUnsettable() ? Notification.UNSET : Notification.SET, 
             feature,
             value,
             feature.getDefaultValue(), 
             Notification.NO_INDEX,
             true);

      if (notifications != null)
      {
        notifications.add(notification);
      }
      else
      {
        notifications = notification;
      }
    }
    return notifications;
  }

  public NotificationChain inverseRemove(Object object, NotificationChain notifications)
  {
    Entry entry = (Entry)object;
    EStructuralFeature feature = entry.getEStructuralFeature();
    if (feature instanceof EReference)
    {
      EReference eReference = (EReference)feature;
      EReference eOpposite = eReference.getEOpposite();
      if (eOpposite != null)
      {
        InternalEObject internalEObject = (InternalEObject)entry.getValue();
        if (internalEObject != null)
        {
          notifications = 
            internalEObject.eInverseRemove
              (owner,
               internalEObject.eClass().getEAllStructuralFeatures().indexOf(eOpposite),
               null,
               notifications);
        }
      }
      else if (eReference.isContainment())
      {
        InternalEObject internalEObject = (InternalEObject)entry.getValue();
        if (internalEObject != null)
        {
          int containmentFeatureID = owner.eClass().getEAllStructuralFeatures().indexOf(eReference);
          notifications =
            internalEObject.eInverseRemove
              (owner,
               InternalEObject.EOPPOSITE_FEATURE_BASE - (containmentFeatureID == -1 ? getFeatureID() : containmentFeatureID),
               null,
               notifications);
        }
      }
    }
    return notifications;
  }

  public NotificationChain shadowSet(Object oldObject, Object newObject, NotificationChain notifications)
  {
    if (isNotificationRequired())
    {
      Entry entry = (Entry)oldObject;
      EStructuralFeature feature = entry.getEStructuralFeature();
      Object oldValue = entry.getValue();
      Object newValue = ((Entry)newObject).getValue();
      NotificationImpl notification = 
        createNotification
          (Notification.SET,
           feature,
           oldValue,
           newValue,
           feature.isMany() ? indexOf(feature, newValue) : Notification.NO_INDEX,
           true);

      if (notifications != null)
      {
        notifications.add(notification);
      }
      else
      {
        notifications = notification;
      }
    }
    return notifications;
  }

  public NotificationChain inverseTouch(Object object, NotificationChain notifications)
  {
    if (isNotificationRequired())
    {
      Entry entry = (Entry)object;
      EStructuralFeature feature = entry.getEStructuralFeature();
      Object value = entry.getValue();
      NotificationImpl notification = 
        createNotification
          (Notification.SET,
           feature,
           value, 
           value,
           feature.isMany() ? indexOf(feature, value) : Notification.NO_INDEX,
           true);
  
      if (notifications != null)
      {
        notifications.add(notification);
      }
      else
      {
        notifications = notification;
      }
    }

    return notifications;
  }

  public Object move(int targetIndex, int sourceIndex)
  {
    if (isNotificationRequired())
    {
      Entry sourceEntry = (Entry)delegateGet(sourceIndex);
      EStructuralFeature feature = sourceEntry.getEStructuralFeature();
      if (isMany(feature))
      {
        FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
        int featureTargetIndex = -1;
        int featureSourceIndex = -1;
        int count = 0;
        for (int i = 0, size = delegateSize(); i < size; ++i)
        {
          Entry entry = (Entry)delegateGet(i);
          if (i == targetIndex)
          {
            featureTargetIndex = count;
          }
          if (i == sourceIndex)
          {
            featureSourceIndex = count;
          }
          if (validator.isValid(entry.getEStructuralFeature()))
          {
            ++count;
          }
        }

        Object result = doMove(targetIndex, sourceIndex);
        if (featureSourceIndex != featureTargetIndex)
        {
          dispatchNotification
            (new ENotificationImpl
               (owner, 
                Notification.MOVE, 
                feature,
                new Integer(featureSourceIndex), 
                sourceEntry.getValue(),
                featureTargetIndex));
        }
        return result;
      }
      else
      {
        return doMove(targetIndex, sourceIndex);
      }
    }
    else
    {
      return doMove(targetIndex, sourceIndex);
    }
  }

  protected Object doMove(int targetIndex, int sourceIndex)
  {
    return super.move(targetIndex, sourceIndex);
  }

  public Object set(int index, Object object)
  {
    Entry entry = (Entry)object;
    EStructuralFeature entryFeature = entry.getEStructuralFeature();
    if (isMany(entryFeature))
    {
      if (entryFeature.isUnique())
      {
        for (int i = 0, size = delegateSize(); i < size; ++i)
        {
          Entry otherEntry = (Entry)delegateGet(i);
          if (otherEntry.equals(entry) && i != index)
          {
            throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
          }
        }
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), entryFeature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry otherEntry = (Entry)delegateGet(i);
        if (validator.isValid(otherEntry.getEStructuralFeature()) && i != index)
        {
          throw new IllegalArgumentException("The multiplicity constraint is violated");
        }
      }
    }

    return doSet(index, object);
  }

  public Object doSet(int index, Object object)
  {
    return super.set(index, object);
  }

  public boolean add(Object object)
  {
    Entry entry = (Entry)object;
    EStructuralFeature entryFeature = entry.getEStructuralFeature();
    if (isMany(entryFeature))
    {
      if (entryFeature.isUnique() && contains(entryFeature, entry.getValue()))
      {
        return false;
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), entryFeature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry otherEntry = (Entry)delegateGet(i);
        if (validator.isValid(otherEntry.getEStructuralFeature()))
        {
          if (otherEntry.equals(entry))
          {
            return false;
          }
          else
          {
            doSet(i, object);
            return true;
          }
        }
      }
    }

    return doAdd(object);
  }

  protected boolean doAdd(Object object)
  {
    return super.add(object);
  }

  public void add(int index, Object object)
  {
    Entry entry = (Entry)object;
    EStructuralFeature entryFeature = entry.getEStructuralFeature();
    if (isMany(entryFeature))
    {
      if (entryFeature.isUnique())
      {
        for (int i = 0, size = delegateSize(); i < size; ++i)
        {
          Entry otherEntry = (Entry)delegateGet(i);
          if (otherEntry.equals(entry) && i != index)
          {
            throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
          }
        }
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), entryFeature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry otherEntry = (Entry)delegateGet(i);
        if (validator.isValid(otherEntry.getEStructuralFeature()))
        {
          throw new IllegalArgumentException("The multiplicity constraint is violated");
        }
      }
    }

    doAdd(index, object);
  }

  public void doAdd(int index, Object object)
  {
    super.add(index, object);
  }

  public boolean addAll(Collection collection)
  {
    Collection uniqueCollection = new BasicEList(collection.size());
    for (Iterator i = collection.iterator(); i.hasNext(); )
    {
      Entry entry = (Entry)i.next();
      EStructuralFeature entryFeature = entry.getEStructuralFeature();
      if (isMany(entryFeature))
      {
        if (!entryFeature.isUnique() || !contains(entryFeature, entry.getValue()) && !uniqueCollection.contains(entry))
        {
          uniqueCollection.add(entry);
        }
      }
      else
      {
        FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), entryFeature);
        boolean include = true;
        for (int j = 0, size = delegateSize(); j < size; ++j)
        {
          Entry otherEntry = (Entry)delegateGet(j);
          if (validator.isValid(otherEntry.getEStructuralFeature()))
          {
            doSet(j, entry);
            include = false;
            break;
          }
        }
        if (include)
        {
          uniqueCollection.add(entry);
        }
      }
    }

    return doAddAll(uniqueCollection);
  }

  public boolean doAddAll(Collection collection)
  {
    return super.addAll(collection);
  }

  public boolean addAll(int index, Collection collection)
  {
    Collection uniqueCollection = new BasicEList(collection.size());
    for (Iterator i = collection.iterator(); i.hasNext(); )
    {
      Entry entry = (Entry)i.next();
      EStructuralFeature entryFeature = entry.getEStructuralFeature();
      if (isMany(entryFeature))
      {
        if (!entryFeature.isUnique() || !contains(entryFeature, entry.getValue()) && !uniqueCollection.contains(entry))
        {
          uniqueCollection.add(entry);
        }
      }
      else
      {
        FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), entryFeature);
        boolean include = true;
        for (int j = 0, size = delegateSize(); j < size; ++j)
        {
          Entry otherEntry = (Entry)delegateGet(j);
          if (validator.isValid(otherEntry.getEStructuralFeature()))
          {
            doSet(j, entry);
            include = false;
            break;
          }
        }
        if (include)
        {
          uniqueCollection.add(entry);
        }
      }
    }

    return doAddAll(index, uniqueCollection);
  }

  public boolean doAddAll(int index, Collection collection)
  {
    return super.addAll(index, collection);
  }


  public int size(EStructuralFeature feature)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    int result = 0;
    for (int i = 0, size = delegateSize(); i < size; ++i)
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        ++result;
      }
    }
    return result;
  }

  public boolean isEmpty(EStructuralFeature feature)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    for (int i = 0, size = delegateSize(); i < size; ++i)
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        return false;
      }
    }
    return true;
  }

  public boolean contains(EStructuralFeature feature, Object object)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()) && entry.equals(object))
        {
          return true;
        }
      }
    }
    else if (object != null)
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()) && object.equals(entry.getValue()))
        {
          return true;
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()) && entry.getValue() == null)
        {
          return false;
        }
      }
    }

    return false;
  }

  public boolean containsAll(EStructuralFeature feature, Collection collection)
  {
    for (Iterator i = collection.iterator(); i.hasNext(); )
    {
      if (!contains(feature, i.next()))
      {
        return false;
      }
    }

    return true;
  }

  public int indexOf(EStructuralFeature feature, Object object)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    int result = 0;
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.equals(object))
          {
            return result;
          }
          ++result;
        }
      }
    }
    else if (object != null)
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (object.equals(entry.getValue()))
          {
            return result;
          }
          ++result;
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.getValue() == null)
          {
            return result;
          }
          ++result;
        }
      }
    }

    return -1;
  }

  public int lastIndexOf(EStructuralFeature feature, Object object)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    int result = -1;
    int count = 0;
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.equals(object))
          {
            result = count;
          }
          ++count;
        }
      }
    }
    else if (object != null)
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (object.equals(entry.getValue()))
          {
            result = count;
          }
          ++count;
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.getValue() == null)
          {
            result = count;
          }
          ++count;
        }
      }
    }

    return result;
  }

  public Iterator iterator(EStructuralFeature feature)
  {
    return 
      feature instanceof EReference && ((EReference)feature).isResolveProxies() ?
        new ResolvingFeatureEIterator(feature, this) :
        new FeatureEIterator(feature, this);
  }

  public ListIterator listIterator(EStructuralFeature feature)
  {
    return 
      feature instanceof EReference && ((EReference)feature).isResolveProxies() ?
        new ResolvingFeatureEIterator(feature, this) :
        new FeatureEIterator(feature, this);
  }

  public ListIterator listIterator(EStructuralFeature feature, int index)
  {
    ListIterator result =
      feature instanceof EReference && ((EReference)feature).isResolveProxies() ?
        new ResolvingFeatureEIterator(feature, this) :
        new FeatureEIterator(feature, this);
    for (int i = 0; i < index; ++i)
    {
      result.next();
    }
    return result;
  }

/*
  public List subList(EStructuralFeature feature, int from, int to)
  {
    return null;
  }
*/

  public EList list(EStructuralFeature feature)
  {
    return
      FeatureMapUtil.isFeatureMap(feature) ?
        new FeatureMapUtil.FeatureFeatureMap(feature, this) :
        new FeatureMapUtil.FeatureEList(feature, this);
  }

  public EStructuralFeature.Setting setting(EStructuralFeature feature)
  {
    return 
      isMany(feature) ?
        (EStructuralFeature.Setting)list(feature) :
        (EStructuralFeature.Setting)new FeatureMapUtil.FeatureValue(feature, this);
  }

  public List basicList(EStructuralFeature feature)
  {
    return new FeatureMapUtil.FeatureEList.Basic(feature, this);
  }

  public Iterator basicIterator(EStructuralFeature feature)
  {
    return new FeatureEIterator(feature, this);
  }

  public ListIterator basicListIterator(EStructuralFeature feature)
  {
    return new FeatureEIterator(feature, this);
  }

  public ListIterator basicListIterator(EStructuralFeature feature, int index)
  {
    ListIterator result = new FeatureEIterator(feature, this);
    for (int i = 0; i < index; ++i)
    {
      result.next();
    }
    return result;
  }

  public Object[] toArray(EStructuralFeature feature)
  {
    List result = new BasicEList();
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          result.add(entry);
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          result.add(entry.getValue());
        }
      }
    }
    return result.toArray();
  }

  public Object[] toArray(EStructuralFeature feature, Object [] array)
  {
    List result = new BasicEList();
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          result.add(entry);
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          result.add(entry.getValue());
        }
      }
    }
    return result.toArray(array);
  }


  public void set(EStructuralFeature feature, Object object)
  {
    if (isMany(feature))
    {
      List list = list(feature);
      list.clear();
      list.addAll((Collection)object);
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          doSet(i, FeatureMapUtil.isFeatureMap(feature) ? (Entry)object : createEntry(feature, object));
          return;
        }
      }
  
      doAdd(FeatureMapUtil.isFeatureMap(feature) ? (Entry)object : createEntry(feature, object));
    }
  }

  public void add(int index, EStructuralFeature feature, Object object)
  {
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    if (isMany(feature))
    {
      if (feature.isUnique() && contains(feature, object))
      {
        throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (isFeatureMap ? entry.equals(object) : object == null ? entry.getValue() == null : object.equals(entry.getValue()))
          {
            throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
          }
        }
      }
    }

    doAdd(index, isFeatureMap ? (Entry)object : createEntry(feature, object));
  }

  public boolean add(EStructuralFeature feature, Object object)
  {
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    if (isMany(feature))
    {
      if (feature.isUnique() && contains(feature, object))
      {
        return false;
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (isFeatureMap ? entry.equals(object) : object == null ? entry.getValue() == null : object.equals(entry.getValue()))
          {
            return false;
          }
          else
          {
            doSet(i, isFeatureMap ? (Entry)object : createEntry(feature, object));
            return true;
          }
        }
      }
    }

    return doAdd(isFeatureMap ? (Entry)object : createEntry(feature, object));
  }

  public void add(EStructuralFeature feature, int index, Object object)
  {
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    if (isMany(feature))
    {
      if (feature.isUnique() && contains(feature, object))
      {
        throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          throw new IllegalArgumentException("The multiplicity constraint is violated");
        }
      }
    }

    doAdd(entryIndex(feature, index), isFeatureMap ? (Entry)object : createEntry(feature, object));
  }

  public boolean addAll(int index, EStructuralFeature feature, Collection collection)
  {
    if (collection.size() == 0)
    {
      return false;
    }
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    Collection entryCollection = isFeatureMap ? collection : new BasicEList(collection.size());
    if (isMany(feature))
    {
      if (feature.isUnique())
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          Object object = i.next();
          if (!contains(feature, object))
          {
            Entry entry = createEntry(feature, object);
            if (!entryCollection.contains(entry))
            {
              entryCollection.add(entry);
            }
          }
        }
      }
      else if (!isFeatureMap)
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          Entry entry = createEntry(feature, i.next());
          entryCollection.add(entry);
        }
      }
    }
    else
    {
      if (collection.size() > 1)
      {
        throw new IllegalArgumentException("The multiplicity constraint is violated");
      }

      if (isFeatureMap)
      {
        if (contains(feature, collection.iterator().next()))
        {
          return false;
        }
      }
      else
      {
        FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
        for (int i = 0, size = delegateSize(); i < size; ++i)
        {
          Entry entry = (Entry)delegateGet(i);
          if (validator.isValid(entry.getEStructuralFeature()))
          {
            if (collection.contains(entry.getValue()))
            {
              return false;
            }
            else
            {
              throw new IllegalArgumentException("The multiplicity constraint is violated");
            }
          }
        }
        Entry entry = createEntry(feature, collection.iterator().next());
        entryCollection.add(entry);
      }
    }

    return doAddAll(index, entryCollection);
  }

  public boolean addAll(EStructuralFeature feature, Collection collection)
  {
    if (collection.size() == 0)
    {
      return false;
    }
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    Collection entryCollection = isFeatureMap ? collection : new BasicEList(collection.size());
    if (isMany(feature))
    {
      if (feature.isUnique())
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          Object object = i.next();
          if (!contains(feature, object))
          {
            Entry entry = createEntry(feature, object);
            if (!entryCollection.contains(entry))
            {
              entryCollection.add(entry);
            }
          }
        }
      }
      else if (!isFeatureMap)
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          Entry entry = createEntry(feature, i.next());
          entryCollection.add(entry);
        }
      }
    }
    else
    {
      if (collection.size() > 1)
      {
        throw new IllegalArgumentException("The multiplicity constraint is violated");
      }
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (collection.contains(isFeatureMap ? entry : entry.getValue()))
          {
            return false;
          }
          else
          {
            for (Iterator j = collection.iterator(); j.hasNext(); )
            {
              doSet(i, isFeatureMap ? j.next() : createEntry(feature, j.next()));
            }
            return true;
          }
        }
      }
      if (!isFeatureMap)
      {
        Entry entry = createEntry(feature, collection.iterator().next());
        entryCollection.add(entry);
      }
    }

    return doAddAll(entryCollection);
  }

  public boolean addAll(EStructuralFeature feature, int index, Collection collection)
  {
    if (collection.size() == 0)
    {
      return false;
    }
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    Collection entryCollection = isFeatureMap ? collection : new BasicEList(collection.size());
    if (isMany(feature))
    {
      if (feature.isUnique())
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          Object object = i.next();
          if (contains(feature, object))
          {
            throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
          }
          Entry entry = createEntry(feature, object);
          entryCollection.add(entry);
        }
      }
      else if (!isFeatureMap)
      {
        for (Iterator i = collection.iterator(); i.hasNext(); )
        {
          Entry entry = createEntry(feature, i.next());
          entryCollection.add(entry);
        }
      }
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          throw new IllegalArgumentException("The multiplicity constraint is violated");
        }
      }

      if (collection.size() > 1)
      {
        throw new IllegalArgumentException("The multiplicity constraint is violated");
      }

      if (!isFeatureMap)
      {
        Entry entry = createEntry(feature, collection.iterator().next());
        entryCollection.add(entry);
      }
    }

    return doAddAll(entryIndex(feature, index), entryCollection);
  }

  public void addUnique(EStructuralFeature feature, Object object)
  {
    addUnique(createEntry(feature, object));
  }

  public void addUnique(EStructuralFeature feature, int index, Object object)
  {
    addUnique(entryIndex(feature, index), createEntry(feature, object));
  }

  public NotificationChain basicAdd(EStructuralFeature feature, Object object, NotificationChain notifications)
  {
    Entry entry = FeatureMapUtil.isFeatureMap(feature) ? (Entry)object : createEntry(feature, object);

    notifications = basicAdd(entry, notifications);
    if (isNotificationRequired())
    {
      boolean oldIsSet = !isEmpty(feature);
      NotificationImpl notification = 
        feature.isMany() ?
          createNotification
            (Notification.ADD,
             feature,
             null, 
             object,
             indexOf(feature, object),
             oldIsSet) :
          createNotification
            (Notification.SET, 
             feature,
             feature.getDefaultValue(), 
             object,
             Notification.NO_INDEX,
             oldIsSet);

      if (notifications != null)
      {
        notifications.add(notification);
      }
      else
      {
        notifications = notification;
      }
    }
    return notifications;
  }

  public boolean remove(EStructuralFeature feature, Object object)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.equals(object))
          {
            remove(i);
            return true;
          }
        }
      }
    }
    else if (object != null)
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (object.equals(entry.getValue()))
          {
            remove(i);
            return true;
          }
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.getValue() == null)
          {
            remove(i);
            return true;
          }
        }
      }
    }

    return false;
  }

  public Object remove(EStructuralFeature feature, int index)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    int count = 0;
    for (int i = 0, size = delegateSize(); i < size; ++i)
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        if (count == index)
        {
          remove(i);
          return FeatureMapUtil.isFeatureMap(feature) ? entry : entry.getValue();
        }
        ++count;
      }
    }

    throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
  }

  public boolean removeAll(EStructuralFeature feature, Collection collection)
  {
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      return removeAll(collection);
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      List entryCollection = new BasicEList(collection.size());
      for (int i = delegateSize(); --i >= 0; )
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (collection.contains(entry.getValue()))
          {
            entryCollection.add(entry);
          }
        }
      }

      return removeAll(entryCollection);
    }
  }

  public NotificationChain basicRemove(EStructuralFeature feature, Object object, NotificationChain notifications)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    int count = 0;
    Entry match = null;
    if (FeatureMapUtil.isFeatureMap(feature))
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.equals(object))
          {
            match = entry;
            break;
          }
          ++count;
        }
      }
    }
    if (object != null)
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (object.equals(entry.getValue()))
          {
            match = entry;
            break;
          }
          ++count;
        }
      }
    }
    else
    {
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (entry.getValue() == null)
          {
            match = entry;
            break;
          }
          ++count;
        }
      }
    }

    if (match != null)
    {
      if (isNotificationRequired())
      {
        NotificationImpl notification = 
          feature.isMany() ?
            createNotification
              (Notification.REMOVE,
               feature,
               object,
               null, 
               count,
               true) :
            createNotification
              (feature.isUnsettable() ? Notification.UNSET : Notification.SET, 
               feature,
               object,
               feature.getDefaultValue(), 
               Notification.NO_INDEX,
               true);
  
        if (notifications != null)
        {
          notifications.add(notification);
        }
        else
        {
          notifications = notification;
        }
      }
      notifications = basicRemove(match, notifications);
    }

    return notifications;
  }

  public boolean retainAll(EStructuralFeature feature, Collection collection)
  {
    boolean isFeatureMap = FeatureMapUtil.isFeatureMap(feature);
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    List entryCollection = new BasicEList(collection.size());
    for (int i = delegateSize(); --i >= 0; )
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        if (!collection.contains(isFeatureMap ? entry : entry.getValue()))
        {
          entryCollection.add(entry);
        }
      }
    }

    return removeAll(entryCollection);
  }

  public void clear(EStructuralFeature feature)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    List entryCollection = new BasicEList();
    for (int i = delegateSize(); --i >= 0; )
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        entryCollection.add(entry);
      }
    }

    if (!removeAll(entryCollection))
    {
      dispatchNotification
        (feature.isMany() ?
           createNotification
             (Notification.REMOVE_MANY,
              feature,
              Collections.EMPTY_LIST,
              null, 
              Notification.NO_INDEX,
              false) :
           createNotification
             (feature.isUnsettable() ? Notification.UNSET : Notification.SET, 
              feature,
              null,
              null, 
              Notification.NO_INDEX,
              false));
    }
  }

  public void move(EStructuralFeature feature, int index, Object object)
  {
    move(feature, index, indexOf(feature, object));
  }

  public Object move(EStructuralFeature feature, int targetIndex, int sourceIndex)
  {
    if (isMany(feature))
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      Object result = null;
      int entryTargetIndex = -1;
      int entrySourceIndex = -1;
      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (count == targetIndex)
          {
            entryTargetIndex = i;
          }
          if (count == sourceIndex)
          {
            entrySourceIndex = i;
            result = entry.getValue();
          }
          ++count;
        }
      }
      if (entryTargetIndex == -1)
      {
        throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + count);
      }
      if (entrySourceIndex == -1)
      {
        throw new IndexOutOfBoundsException("sourceIndex=" + targetIndex + ", size=" + count);
      }

      doMove(entryTargetIndex, entrySourceIndex);

      if (isNotificationRequired())
      {
        dispatchNotification
          (createNotification
             (Notification.MOVE, 
              feature,
              new Integer(sourceIndex), 
              result,
              targetIndex,
              true));
      }

      return result;
    }
    else
    {
      throw new IllegalArgumentException("The feature must be many-valued to support move");
    }
  }

  public Object get(EStructuralFeature feature, boolean resolve)
  {
    if (isMany(feature))
    {
      return list(feature);
    }
    else
    {
      FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (FeatureMapUtil.isFeatureMap(feature))
          {
            return entry;
          }
          else
          {
            Object value = entry.getValue();
            if (value != null && resolve && isResolveProxies(feature))
            {
              value = resolveProxy(feature, i, count, value);
            }
            return value;
          }
        }
        ++count;
      }

      return null;
    }
  }

  public Object get(EStructuralFeature feature, int index, boolean resolve)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (isMany(feature))
    {
      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (count == index)
          {
            if (FeatureMapUtil.isFeatureMap(feature))
            {
              return entry;
            }
            else
            {
              Object value = entry.getValue();
              if (value != null && resolve && isResolveProxies(feature))
              {
                value = resolveProxy(feature, i, count, entry.getValue());
              }
              return value;
            }
          }
          ++count;
        }
      }
      throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
    }
    else
    {
      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (FeatureMapUtil.isFeatureMap(feature))
          {
            return entry;
          }
          else
          {
            Object value = entry.getValue();
            if (value != null && resolve && isResolveProxies(feature))
            {
              value = resolveProxy(feature, i, count, value);
            }
            return value;
          }
        }
        ++count;
      }

      return null;
    }
  }

  public Object set(EStructuralFeature feature, int index, Object object)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (isMany(feature))
    {
      if (feature.isUnique())
      {
        int currentIndex = indexOf(feature, object);
        if (currentIndex >=0 && currentIndex != index)
        {
          throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
        }
      }

      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (count == index)
          {
            return doSet(i, FeatureMapUtil.isFeatureMap(feature) ? object : createEntry(feature, object));
          }
          ++count;
        }
      }
      throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
    }
    else
    {
      // Index should be -1.

      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          return FeatureMapUtil.isFeatureMap(feature) ? entry : entry.getValue();
        }
      }

      return null;
    }
  }

  public Object setUnique(EStructuralFeature feature, int index, Object object)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    if (isMany(feature))
    {
      int count = 0;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          if (count == index)
          {
            return setUnique(i, FeatureMapUtil.isFeatureMap(feature) ? object : createEntry(feature, object));
          }
          ++count;
        }
      }
      throw new IndexOutOfBoundsException("index=" + index + ", size=" + count);
    }
    else
    {
      // Index should be -1.

      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          return setUnique(i, FeatureMapUtil.isFeatureMap(feature) ? object : createEntry(feature, object));
        }
      }

      return feature.getDefaultValue();
    }
  }

  public boolean isSet(EStructuralFeature feature)
  {
    return !isEmpty(feature);
  }

  public void unset(EStructuralFeature feature)
  {
    FeatureMapUtil.Validator validator = FeatureMapUtil.getValidator(owner.eClass(), feature);
    List removals = null;
    for (int i = 0, size = delegateSize(); i < size; ++i)
    {
      Entry entry = (Entry)delegateGet(i);
      if (validator.isValid(entry.getEStructuralFeature()))
      {
        if (removals == null)
        {
          removals = new BasicEList();
        }
        removals.add(entry);
      }
    }

    if (removals != null)
    {
      removeAll(removals);
    }
  }

  public NotificationChain basicRemove(Object object, NotificationChain notifications)
  {
    // This may be called directly on an EObject for the case of a containment.
    //
    if (object instanceof FeatureMap.Entry)
    {
      return super.basicRemove(object, notifications);
    }
    else
    {
      Entry match = null;
      EStructuralFeature feature = null;
      for (int i = 0, size = delegateSize(); i < size; ++i)
      {
        Entry entry = (Entry)delegateGet(i); 
        if (object.equals(entry.getValue()))
        {
          feature = entry.getEStructuralFeature();
          if (feature instanceof EReference && ((EReference)feature).isContainment())
          {
            match = entry;
            break;
          }
        }
      }

      if (match != null)
      {
        if (isNotificationRequired())
        {
          NotificationImpl notification =
            feature.isMany() ?
              createNotification
                (Notification.REMOVE,
                 feature,
                 object,
                 null,
                 indexOf(feature, object),
                 true) :
              createNotification
                (feature.isUnsettable() ? Notification.UNSET : Notification.SET,
                 feature,
                 object,
                 feature.getDefaultValue(),
                 Notification.NO_INDEX,
                 true);

          if (notifications != null)
          {
            notifications.add(notification);
          }
          else
          {
            notifications = notification;
          }
        }
        notifications = basicRemove(match, notifications);
      }

      return notifications;
    }
  }

  /**
   * -------------------------------------------
   */
  public static class FeatureEIterator extends FeatureMapUtil.BasicFeatureEIterator
  {
    public FeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap)
    {
      super(eStructuralFeature, featureMap);
    }

    protected boolean scanNext()
    {
      int size = featureMap.size();
      while (entryCursor < size)
      {
        Entry entry = (Entry)featureMap.get(entryCursor);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          preparedResult = extractValue(entry);
          prepared = 2;
          return true;
        }
        ++entryCursor;
      }

      prepared = 1;
      lastCursor = -1;
      return false;
    }

    protected boolean scanPrevious()
    {
      while (--entryCursor >= 0)
      {
        Entry entry = (Entry)featureMap.get(entryCursor);
        if (validator.isValid(entry.getEStructuralFeature()))
        {
          preparedResult = extractValue(entry);
          prepared = -2;
          return true;
        }
      }

      prepared = -1;
      lastCursor = -1;
      return false;
    }
  }

  /**
   * -------------------------------------------
   */
  public static class ResolvingFeatureEIterator extends FeatureEIterator
  {
    public ResolvingFeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap)
    {
      super(eStructuralFeature, featureMap);
    }

    protected boolean resolve()
    {
      return true;
    }
  }

  /**
   * Temporary for testing purposes only.
   */
  public static class FeatureMapEObjectImpl extends org.eclipse.emf.ecore.impl.EObjectImpl
  {
    protected DelegatingFeatureMap featureMap = 
      new DelegatingFeatureMap(this, -1)
      {
        protected List theList = new java.util.ArrayList();
        protected List delegateList()
        {
          return theList;
        }
      };


    public FeatureMapEObjectImpl()
    {
      super();
    }

    public Object eDynamicGet(EStructuralFeature eFeature, boolean resolve)
    {
      if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
      {
        return eSettingDelegate(eFeature).dynamicGet(this, null, -1, true);
      }
      else
      {
        return featureMap.setting(eFeature).get(resolve);
      }
    }

    public void eDynamicSet(EStructuralFeature eFeature, Object newValue)
    {
      if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
      {
        eSettingDelegate(eFeature).dynamicSet(this, null, -1, newValue);
      }
      else
      {
        if (!eFeature.isUnsettable())
        {
          Object defaultValue = eFeature.getDefaultValue();
          if (defaultValue == null ? newValue == null : defaultValue.equals(newValue))
          {
            featureMap.setting(eFeature).unset();
            return;
          }
        }
        featureMap.setting(eFeature).set(newValue);
      }

/*
      if (eFeature instanceof EReference)
      {
        EReference eReference = (EReference)eFeature;
        EReference eOpposite = ((EReference)eFeature).getEOpposite();
        if (eOpposite != null)
        {
          if (!eReference.isContainment() && !eReference.isMany() && !eOpposite.isMany() && !eOpposite.isContainment())
          {
            if (eDynamicGet(eFeature, false) != newValue)
            {
              //Thread.dumpStack();
            }
            else if (newValue instanceof EObject && ((EObject)newValue).eGet(eOpposite) != this)
            {
              Thread.dumpStack();
            }
          }
        }
      }
*/
    }

    public void eDynamicUnset(EStructuralFeature eFeature)
    {
      if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
      {
        eSettingDelegate(eFeature).dynamicUnset(this, null, -1);
      }
      else
      {
        featureMap.setting(eFeature).unset();
      }
    }

    public boolean eDynamicIsSet(EStructuralFeature eFeature)
    {
      if (eFeature instanceof EReference && ((EReference)eFeature).isContainer())
      {
        return eSettingDelegate(eFeature).dynamicIsSet(this, null, -1);
      }
      else
      {
        return featureMap.setting(eFeature).isSet();
      }
    }

    public NotificationChain eDynamicInverseAdd(InternalEObject otherEnd, int featureID, Class inverseClass, NotificationChain notifications)
    {
      EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEAllStructuralFeatures().get(featureID);
      if (feature.isMany())
      {
        return featureMap.basicAdd(feature, otherEnd, notifications);
      }
      else if (feature instanceof EReference && ((EReference)feature).isContainer())
      {
        return eSettingDelegate(feature).dynamicInverseAdd(this, null, -1, otherEnd, notifications);
      }
      else
      {
        InternalEObject oldValue = (InternalEObject)eDynamicGet(feature, false);
        if (oldValue != null)
        {
          notifications = oldValue.eInverseRemove
            (this, oldValue.eClass().getEAllStructuralFeatures().indexOf(((EReference)feature).getEOpposite()), null, notifications);
          notifications = featureMap.basicRemove(feature, oldValue, notifications);
        }

        return featureMap.basicAdd(feature, otherEnd, notifications);
      }
    }

    public NotificationChain eDynamicInverseRemove(InternalEObject otherEnd, int featureID, Class inverseClass, NotificationChain notifications)
    {
      EStructuralFeature.Internal feature = (EStructuralFeature.Internal)eClass().getEAllStructuralFeatures().get(featureID);
      if (feature instanceof EReference && ((EReference)feature).isContainer())
      {
        return eSettingDelegate(feature).dynamicInverseRemove(this, null, -1, otherEnd, notifications);
      }
      else
      {
        return featureMap.basicRemove(feature, otherEnd, notifications);
      }
    }

    public FeatureMap featureMap()
    {
      return featureMap;
    }

    public void eNotify(Notification notification)
    {
      if (notification.getFeatureID(null) != -1)
      {
        super.eNotify(notification);
      }
    }

    public String toString()
    {
      String result = super.toString();
      result = "org.eclipse.emf.ecore.impl.EObjectImpl" + result.substring(result.indexOf("@"));
      return result;
    }
  }
}
