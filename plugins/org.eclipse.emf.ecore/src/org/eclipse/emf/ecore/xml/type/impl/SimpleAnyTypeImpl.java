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
 * $Id: SimpleAnyTypeImpl.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xml.type.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Any Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.SimpleAnyTypeImpl#getRawValue <em>Raw Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.SimpleAnyTypeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.SimpleAnyTypeImpl#getInstanceType <em>Instance Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimpleAnyTypeImpl extends AnyTypeImpl implements SimpleAnyType
{
  /**
   * The cached value of the '{@link #getInstanceType() <em>Instance Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstanceType()
   * @generated
   * @ordered
   */
  protected EDataType instanceType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SimpleAnyTypeImpl()
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
    return XMLTypePackage.eINSTANCE.getSimpleAnyType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getRawValue()
  {
    StringBuffer value = new StringBuffer();
    for (Iterator i = getMixed().iterator(); i.hasNext(); )
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)i.next();
      if (entry.getEStructuralFeature() == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text())
      {
        value.append(entry.getValue());
      }
    }
    return value.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setRawValue(String newRawValue)
  {
    getMixed().clear();
    if (newRawValue != null)
    {
      getMixed().add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), newRawValue);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object getValue()
  {
    return EcoreUtil.createFromString(instanceType, getRawValue());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setValue(Object newValue)
  {
    setRawValue(EcoreUtil.convertToString(instanceType, newValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getInstanceType()
  {
    return instanceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstanceType(EDataType newInstanceType)
  {
    EDataType oldInstanceType = instanceType;
    instanceType = newInstanceType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XMLTypePackage.SIMPLE_ANY_TYPE__INSTANCE_TYPE, oldInstanceType, instanceType));
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
        case XMLTypePackage.SIMPLE_ANY_TYPE__MIXED:
          return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
        case XMLTypePackage.SIMPLE_ANY_TYPE__ANY:
          return ((InternalEList)getAny()).basicRemove(otherEnd, msgs);
        case XMLTypePackage.SIMPLE_ANY_TYPE__ANY_ATTRIBUTE:
          return ((InternalEList)getAnyAttribute()).basicRemove(otherEnd, msgs);
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
      case XMLTypePackage.SIMPLE_ANY_TYPE__MIXED:
        return getMixed();
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY:
        return getAny();
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY_ATTRIBUTE:
        return getAnyAttribute();
      case XMLTypePackage.SIMPLE_ANY_TYPE__RAW_VALUE:
        return getRawValue();
      case XMLTypePackage.SIMPLE_ANY_TYPE__VALUE:
        return getValue();
      case XMLTypePackage.SIMPLE_ANY_TYPE__INSTANCE_TYPE:
        return getInstanceType();
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
      case XMLTypePackage.SIMPLE_ANY_TYPE__MIXED:
        getMixed().clear();
        getMixed().addAll((Collection)newValue);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY:
        getAny().clear();
        getAny().addAll((Collection)newValue);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY_ATTRIBUTE:
        getAnyAttribute().clear();
        getAnyAttribute().addAll((Collection)newValue);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__RAW_VALUE:
        setRawValue((String)newValue);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__VALUE:
        setValue((Object)newValue);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__INSTANCE_TYPE:
        setInstanceType((EDataType)newValue);
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
      case XMLTypePackage.SIMPLE_ANY_TYPE__MIXED:
        getMixed().clear();
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY:
        getAny().clear();
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY_ATTRIBUTE:
        getAnyAttribute().clear();
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__RAW_VALUE:
        setRawValue((String)null);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__VALUE:
        setValue((Object)null);
        return;
      case XMLTypePackage.SIMPLE_ANY_TYPE__INSTANCE_TYPE:
        setInstanceType((EDataType)null);
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
      case XMLTypePackage.SIMPLE_ANY_TYPE__MIXED:
        return mixed != null && !mixed.isEmpty();
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY:
        return !getAny().isEmpty();
      case XMLTypePackage.SIMPLE_ANY_TYPE__ANY_ATTRIBUTE:
        return anyAttribute != null && !anyAttribute.isEmpty();
      case XMLTypePackage.SIMPLE_ANY_TYPE__RAW_VALUE:
        return getRawValue() != null;
      case XMLTypePackage.SIMPLE_ANY_TYPE__VALUE:
        return getValue() != null;
      case XMLTypePackage.SIMPLE_ANY_TYPE__INSTANCE_TYPE:
        return instanceType != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //SimpleAnyTypeImpl
