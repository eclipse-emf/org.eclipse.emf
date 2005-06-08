/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: EObjectToChangesMapEntryImpl.java,v 1.6.2.1 2005/06/08 18:27:45 nickb Exp $
 */
package org.eclipse.emf.ecore.change.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EObject To Changes Map Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.EObjectToChangesMapEntryImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.EObjectToChangesMapEntryImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EObjectToChangesMapEntryImpl extends EObjectImpl implements BasicEMap.Entry
{
  /**
   * The bit of {@link #eFlags} that is used to represent if key is a proxy.
   */
  protected static final int EPROXY_KEY = ELAST_EOBJECT_FLAG << 1;
  
  /**
   * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedKey()
   * @generated
   * @ordered
   */
  protected EObject key = null;

  /**
   * The cached value of the '{@link #getTypedValue() <em>Value</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedValue()
   * @generated
   * @ordered
   */
  protected EList value = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EObjectToChangesMapEntryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return ChangePackage.eINSTANCE.getEObjectToChangesMapEntry();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getTypedKeyGen()
  {
    if (key != null && key.eIsProxy())
    {
      EObject oldKey = key;
      key = (EObject)eResolveProxy((InternalEObject)key);
      if (key != oldKey)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__KEY, oldKey, key));
      }
    }
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EObject getTypedKey()
  {
    if ((eFlags & EPROXY_KEY) != 0)
    {
      EObject oldKey = key;
      EObject newKey = getTypedKeyGen();
      if (newKey != oldKey)
      {
        eFlags &= ~EPROXY_KEY;
      }
    }
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetTypedKey()
  {
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypedKeyGen(EObject newKey)
  {
    EObject oldKey = key;
    key = newKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__KEY, oldKey, key));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setTypedKey(EObject newKey)
  {
    setTypedKeyGen(newKey);
    if (key != null && key.eIsProxy())
    {
      eFlags |= EPROXY_KEY;
    }
    else
    {
      eFlags &= ~EPROXY_KEY;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getTypedValue()
  {
    if (value == null)
    {
      value = new EObjectContainmentEList(FeatureChange.class, this, ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE);
    }
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE:
          return ((InternalEList)getTypedValue()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__KEY:
        if (resolve) return getTypedKey();
        return basicGetTypedKey();
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE:
        return getTypedValue();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__KEY:
        setTypedKey((EObject)newValue);
        return;
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE:
        getTypedValue().clear();
        getTypedValue().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__KEY:
        setTypedKey((EObject)null);
        return;
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE:
        getTypedValue().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__KEY:
        return key != null;
      case ChangePackage.EOBJECT_TO_CHANGES_MAP_ENTRY__VALUE:
        return value != null && !value.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected int hash = -1;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getHash()
  {
    if (hash == -1)
    {
      Object theKey = getKey();
      hash = (theKey == null ? 0 : theKey.hashCode());
    }
    return hash;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHash(int hash)
  {
    this.hash = hash;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getKey()
  {
    return getTypedKey();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKey(Object key)
  {
    setTypedKey((EObject)key);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getValue()
  {
    return getTypedValue();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object setValue(Object value)
  {
    Object oldValue = getValue();
    getTypedValue().clear();
    getTypedValue().addAll((Collection)value);
    return oldValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getEMap()
  {
    EObject container = eContainer();
    return container == null ? null : (EMap)container.eGet(eContainmentFeature());
  }

} //EObjectToChangesMapEntryImpl
