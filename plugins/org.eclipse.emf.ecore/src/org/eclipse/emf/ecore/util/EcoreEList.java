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
 * $Id: EcoreEList.java,v 1.9.2.2 2007/07/10 19:44:10 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;


public class EcoreEList extends NotifyingListImpl implements InternalEList.Unsettable, EStructuralFeature.Setting
{
  protected final Class dataClass;
  protected final InternalEObject owner;

  public EcoreEList(Class dataClass, InternalEObject owner)
  {
    super();
    this.dataClass = dataClass;
    this.owner = owner;
  }

  protected Object [] newData(int capacity)
  {
    return (Object [])Array.newInstance(dataClass, capacity);
  }

  protected Object validate(int index, Object object)
  {
    super.validate(index, object);
    if (!hasInstanceClass() && object != null && !isInstance(object))
    {
      throw new ArrayStoreException();
    }
    return object;
  }

  protected boolean isInstance(Object object)
  {
    return dataClass.isInstance(object);
  }

  public Object getNotifier()
  {
    return owner;
  }

  public Object getFeature()
  {
    return getEStructuralFeature();
  }

  public int getFeatureID()
  {
    return owner.eClass().getFeatureID(getEStructuralFeature());
  }

  public EStructuralFeature getEStructuralFeature()
  {
    return owner.eClass().getEStructuralFeature(getFeatureID());
  }

  protected EClassifier getFeatureType()
  {
    return getEStructuralFeature().getEType();
  }

  protected EReference getInverseEReference()
  {
    return ((EReference)getEStructuralFeature()).getEOpposite();
  }

  protected int getInverseFeatureID()
  {
    return getInverseEReference().getFeatureID();
  }

  protected Class getInverseFeatureClass()
  {
    return ((EClass)getEStructuralFeature().getEType()).getInstanceClass();
  }

  protected boolean hasManyInverse()
  {
    return false;
  }

  protected boolean hasNavigableInverse()
  {
    return false;
  }

  protected boolean isEObject()
  {
    return true;
  }

  protected boolean isContainment()
  {
    return false;
  }

  protected boolean hasProxies()
  {
    return false;
  }

  protected boolean hasInstanceClass()
  {
    return true;
  }

  protected Object resolve(int index, Object object)
  {
    return
      isEObject() && hasProxies() ?
        resolve(index, (EObject)object):
        object;
  }
  
  protected EObject resolve(int index, EObject eObject)
  {
    EObject resolved = resolveProxy(eObject);
    if (resolved != eObject)
    {
      Object oldObject = data[index];
      assign(index, validate(index, resolved));
      didSet(index, resolved, oldObject);

      if (isContainment())
      {
        NotificationChain notificationChain = inverseRemove(eObject, null);
        if (((InternalEObject)resolved).eInternalContainer() == null)
        {
          notificationChain = inverseAdd(resolved, notificationChain);
        }
        if (notificationChain != null)
        {
          notificationChain.dispatch();
        }
      }
      
      if (isNotificationRequired())
      {
        owner.eNotify(createNotification(Notification.RESOLVE, eObject, resolved, index, false));
      }

      return resolved;
    }
    else
    {
      return eObject;
    }
  }

  protected Object resolve(Object object)
  {
    return isEObject() ? resolveProxy((EObject)object) : object;
  }

  protected EObject resolveProxy(EObject eObject)
  {
    return eObject.eIsProxy() ? owner.eResolveProxy((InternalEObject)eObject) : eObject;
  }

  public Object[] toArray()
  {
    if (hasProxies())
    {
      for (int i = size - 1; i >= 0; --i)
      {
        get(i);
      }
    }
    return super.toArray();
  }

  public Object[] toArray(Object array[])
  {
    if (hasProxies())
    {
      for (int i = size - 1; i >= 0; --i)
      {
        get(i);
      }
    }
    return super.toArray(array);
  }

  protected NotificationImpl createNotification(int eventType, Object oldObject, Object newObject, int index, boolean wasSet)
  {
    return new ENotificationImpl(owner, eventType, getFeatureID(), oldObject, newObject, index, wasSet);
  }

  protected NotificationImpl createNotification(int eventType, boolean oldValue, boolean newValue)
  {
    return new ENotificationImpl(owner, eventType, getFeatureID(), oldValue, newValue);
  }

  /*
   * Javadoc copied from base class.
   */
  protected void dispatchNotification(Notification notification)
  {
    owner.eNotify(notification);
  }

  public List basicList()
  {
    return super.basicList();
  }

  protected boolean isNotificationRequired()
  {
    return owner.eNotificationRequired();
  }

  public NotificationChain inverseAdd(Object object, NotificationChain notifications)
  {
    InternalEObject internalEObject = (InternalEObject) object;
    if (hasNavigableInverse())
    {
      if (!hasInstanceClass())
      {
        return 
          internalEObject.eInverseAdd
            (owner, 
             internalEObject.eClass().getFeatureID(getInverseEReference()),
             null,
             notifications);
      }
      else
      {
        return 
          internalEObject.eInverseAdd
            (owner, 
             getInverseFeatureID(),
             getInverseFeatureClass(),
             notifications);
      }
    }
    else
    {
      return 
        internalEObject.eInverseAdd
          (owner, 
           InternalEObject.EOPPOSITE_FEATURE_BASE - getFeatureID(),
           null,
           notifications);
    }
  }

  public NotificationChain inverseRemove(Object object, NotificationChain notifications)
  {
    InternalEObject internalEObject = (InternalEObject) object;
    if (hasNavigableInverse())
    {
      if (!hasInstanceClass())
      {
        return 
          internalEObject.eInverseRemove
            (owner, 
             internalEObject.eClass().getFeatureID(getInverseEReference()),
             null,
             notifications);
      }
      else
      {
        return 
          internalEObject.eInverseRemove
            (owner, 
             getInverseFeatureID(),
             getInverseFeatureClass(),
             notifications);
      }
    }
    else
    {
      return 
        internalEObject.eInverseRemove
          (owner, 
           InternalEObject.EOPPOSITE_FEATURE_BASE - getFeatureID(),
           null,
           notifications);
    }
  }

  /**
   * Resolve to compare objects but do not modify list
   */
  public boolean contains(Object object)
  {
    if (isEObject())
    {
      if (size > 4)
      {
        if (!isInstance(object))
        {
          return false;
        }
        else if (isContainment())
        {
          InternalEObject eObject = (InternalEObject)object;
          boolean result = 
            eObject.eInternalContainer() == owner && 
              (hasNavigableInverse() ? 
                 eObject.eBaseStructuralFeatureID(eObject.eContainerFeatureID(), dataClass) == getInverseFeatureID() :
                 InternalEObject.EOPPOSITE_FEATURE_BASE - eObject.eContainerFeatureID() == getFeatureID());
          if (hasProxies() && !result)
          {
            for (int i = 0; i < size; ++i)
            {
              EObject containedEObject = resolveProxy((EObject)data[i]);
              if (containedEObject == object)
              {
                return true;
              }
            }
          }
          return result;
        }
        // We can also optimize single valued reverse. 
        //
        else if (hasNavigableInverse() && !hasManyInverse())
        {
          return ((EObject)object).eGet(getInverseEReference()) == owner;
        }
      }

      boolean result = super.contains(object);
      if (hasProxies() && !result)
      {
        for (int i = 0; i < size; ++i)
        {
          EObject eObject = resolveProxy((EObject)data[i]);
          if (eObject == object)
          {
            return true;
          }
        }
      }
      return result;
    }
    else
    {
      return super.contains(object);
    }
  }

  public int indexOf(Object object)
  {
    int index = super.indexOf(object);
    if (index >= 0)
      return index;

    // EATM This might be better written as a single loop for the EObject case?
    //
    if (isEObject())
    {
      for (int i = 0; i < size; ++i)
      {
        EObject eObject = resolveProxy((EObject)data[i]);
        if (eObject == object)
        {
          return i;
        }
      }
    }

    return -1;
  }

  public int lastIndexOf(Object object)
  {
    int result = super.lastIndexOf(object);
    if (isEObject () && result == -1)
    {
      for (int i = size - 1; i >= 0; --i)
      {
        EObject eObject = resolveProxy((EObject)data[i]);
        if (eObject == object)
        {
          return i;
        }
      }
    }

    return result;
  }

  public Iterator basicIterator()
  {
    return super.basicIterator();
  }

  public ListIterator basicListIterator()
  {
    return super.basicListIterator();
  }

  public ListIterator basicListIterator(int index)
  {
    return super.basicListIterator(index);
  }

  public EObject getEObject()
  {
    return owner;
  }

  public Object get(boolean resolve)
  {
    return this;
  }

  public void set(Object newValue)
  {
    clear();
    addAll((List)newValue);
  }

  public boolean isSet()
  {
    return !isEmpty();
  }

  public void unset()
  {
    clear();
  }

  public static class UnmodifiableEList 
    extends BasicEList.UnmodifiableEList 
    implements InternalEList.Unsettable, EStructuralFeature.Setting
  {
    public static class FastCompare extends EcoreEList.UnmodifiableEList
    {
      public FastCompare(InternalEObject owner, EStructuralFeature eStructuralFeature, int size, Object [] data)
      {
        super(owner, eStructuralFeature, size, data);
      }
      
      protected boolean useEquals()
      {
        return false;
      }
    }
    
    protected final InternalEObject owner;
    protected final EStructuralFeature eStructuralFeature;

    public UnmodifiableEList(InternalEObject owner, EStructuralFeature eStructuralFeature, int size, Object [] data)
    {
      super(size, data);
      this.owner = owner;
      this.eStructuralFeature = eStructuralFeature;
    }

    public List basicList()
    {
      return super.basicList();
    }

    public Iterator basicIterator()
    {
      return super.basicIterator();
    }

    public ListIterator basicListIterator()
    {
      return super.basicListIterator();
    }

    public ListIterator basicListIterator(int index)
    {
      return super.basicListIterator(index);
    }

    public EObject getEObject()
    {
      return owner;
    }

    public EStructuralFeature getEStructuralFeature()
    {
      return eStructuralFeature;
    }

    public Object get(boolean resolve)
    {
      return this;
    }

    public void set(Object newValue)
    {
      throw new UnsupportedOperationException();
    }

    public boolean isSet()
    {
      return !isEmpty();
    }

    public void unset()
    {
      throw new UnsupportedOperationException();
    }

    public NotificationChain basicRemove(Object object, NotificationChain notifications)
    {
      throw new UnsupportedOperationException();
    }

    public NotificationChain basicAdd(Object object, NotificationChain notifications)
    {
      throw new UnsupportedOperationException();
    }
  }

  public static class Generic extends EcoreEList
  {
    public static final int IS_SET = 0x0001;
    public static final int IS_UNSETTABLE = 0x0002;
    public static final int HAS_INSTANCE_CLASS = 0x0004;
    public static final int HAS_NAVIGABLE_INVERSE = 0x0008;
    public static final int HAS_MANY_INVERSE = 0x0010;
    public static final int IS_CONTAINMENT = 0x0020;
    public static final int IS_CONTAINER = 0x0040;
    public static final int IS_UNIQUE = 0x0080;
    public static final int IS_PRIMITIVE = 0x0100;
    public static final int IS_ENUM = 0x0200;
    public static final int IS_EOBJECT = 0x0400;
    public static final int HAS_PROXIES = 0x0800;

    public static Class wrapperClassFor(Class javaClass)
    {
      if (javaClass == null)
      {
        return Object.class;
      }
      else
      {
        return EcoreUtil.wrapperClassFor(javaClass);
      }
    }

    public static int kind(EStructuralFeature eStructuralFeature)
    {
      int result = 0;

      EClassifier eClassifier = eStructuralFeature.getEType();

      if (eClassifier.getInstanceClass() != null) 
      {
        result |= HAS_INSTANCE_CLASS;
      }

      if (eStructuralFeature.isUnsettable())
      {
        result |= IS_UNSETTABLE;
      }

      if (eStructuralFeature instanceof EReference)
      {
        EReference eReference = (EReference)eStructuralFeature;
        EReference inverseEReference = eReference.getEOpposite();
        if (eReference.isContainment())
        {
          result |= IS_CONTAINMENT;
        }

        if (inverseEReference != null)
        {
          // This forces the feature ids to be assigned.
          //
          inverseEReference.getEContainingClass().getFeatureCount();
          result |= HAS_NAVIGABLE_INVERSE;
          if (inverseEReference.isMany())
          {
            result |= HAS_MANY_INVERSE;
          }
          if (inverseEReference.isContainment())
          {
            result |= IS_CONTAINER;
          }
        }

        if (eReference.isResolveProxies())
        {
          result |= HAS_PROXIES;
        }

        result |= IS_EOBJECT;
      }
      else // if (eStructuralFeature instanceof EAttribute
      {
        if (eClassifier instanceof EEnum)
        {
          result |= IS_ENUM;
        }
        else
        {
          Class instanceClass = eClassifier.getInstanceClass();
          if (instanceClass != null && instanceClass.isPrimitive())
          {
            result |= IS_PRIMITIVE;
          }
        }
      }

      if (eStructuralFeature.isUnique())
      {
        result |= IS_UNIQUE;
      }

      return result;
    }

    protected int kind;

    public Generic(int kind, Class dataClass, InternalEObject owner)
    {
      super(dataClass, owner);
      this.kind = kind;
    }

    protected boolean useEquals()
    {
      // We can use == for EObjects and EnumLiterals.
      //
      return (kind & (IS_EOBJECT | IS_ENUM)) == 0;
    }

    protected boolean canContainNull()
    {
      return (kind & (IS_EOBJECT | IS_PRIMITIVE | IS_ENUM)) == 0;
    }

    protected boolean isUnique()
    {
      return (kind & IS_UNIQUE) != 0;
    }

    protected boolean hasInverse()
    {
      return (kind & (HAS_NAVIGABLE_INVERSE | IS_CONTAINMENT)) != 0;
    }

    protected boolean hasManyInverse()
    {
      return (kind & HAS_MANY_INVERSE) != 0;
    }

    protected boolean hasNavigableInverse()
    {
      return (kind & HAS_NAVIGABLE_INVERSE) != 0; 
    }

    protected boolean isEObject()
    {
      return (kind & IS_EOBJECT) != 0;
    }

    protected boolean isContainment()
    {
      return (kind & IS_CONTAINMENT) != 0;
    }

    protected boolean hasProxies()
    {
      return (kind & HAS_PROXIES) != 0;
    }

    protected boolean hasInstanceClass()
    {
      return (kind & HAS_INSTANCE_CLASS) != 0;
    }

    protected boolean isInstance(Object object)
    {
      return dataClass == null ? getFeatureType().isInstance(object) : super.isInstance(object);
    }

    protected boolean isContainer()
    {
      return (kind & IS_CONTAINER) != 0;
    }

    protected boolean isUnsettable()
    {
      return (kind & IS_UNSETTABLE) != 0;
    }

    public boolean isSet()
    {
      return isUnsettable() ? (kind & IS_SET) != 0 : !isEmpty();
    }

    public void unset()
    {
      super.unset();
      if (isUnsettable())
      {
        if (isNotificationRequired())
        {
          boolean oldIsSet = (kind & IS_SET) != 0;
          kind &= ~IS_SET;
          owner.eNotify(createNotification(Notification.UNSET, oldIsSet, false));
        }
        else
        {
          kind &= ~IS_SET;
        }
      }
    }

    protected void didChange()
    {
      kind |= IS_SET;
    }
  }

  public static class Dynamic extends Generic
  {
    protected EStructuralFeature eStructuralFeature;

    public Dynamic(InternalEObject owner, EStructuralFeature eStructuralFeature)
    {
      super(kind(eStructuralFeature), wrapperClassFor(eStructuralFeature.getEType().getInstanceClass()), owner);
      this.eStructuralFeature = eStructuralFeature;
    }

    public Dynamic(int kind, InternalEObject owner, EStructuralFeature eStructuralFeature)
    {
      super(kind, wrapperClassFor(eStructuralFeature.getEType().getInstanceClass()), owner);
      this.eStructuralFeature = eStructuralFeature;
    }

    public Dynamic(int kind, Class dataClass, InternalEObject owner, EStructuralFeature eStructuralFeature)
    {
      super(kind, dataClass, owner);
      this.eStructuralFeature = eStructuralFeature;
    }

    public EStructuralFeature getEStructuralFeature()
    {
      return eStructuralFeature;
    }
  }
}
