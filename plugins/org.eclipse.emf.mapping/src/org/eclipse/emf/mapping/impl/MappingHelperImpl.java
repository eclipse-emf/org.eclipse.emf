/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: MappingHelperImpl.java,v 1.3 2004/03/21 17:56:41 emerks Exp $
 */
package org.eclipse.emf.mapping.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Helper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingHelperImpl#getMapper <em>Mapper</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingHelperImpl#getHelpedObject <em>Helped Object</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingHelperImpl#getNestedIn <em>Nested In</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.MappingHelperImpl#getNested <em>Nested</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MappingHelperImpl extends EObjectImpl implements MappingHelper
{
  /**
   * The cached value of the '{@link #getHelpedObject() <em>Helped Object</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHelpedObject()
   * @generated
   * @ordered
   */
  protected EObject helpedObject = null;

  /**
   * The cached value of the '{@link #getNested() <em>Nested</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNested()
   * @generated
   * @ordered
   */
  protected EList nested = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MappingHelperImpl()
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
    return MappingPackage.eINSTANCE.getMappingHelper();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping getMapper()
  {
    if (eContainerFeatureID != MappingPackage.MAPPING_HELPER__MAPPER) return null;
    return (Mapping)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMapper(Mapping newMapper)
  {
    if (newMapper != eContainer || (eContainerFeatureID != MappingPackage.MAPPING_HELPER__MAPPER && newMapper != null))
    {
      if (EcoreUtil.isAncestor(this, newMapper))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newMapper != null)
        msgs = ((InternalEObject)newMapper).eInverseAdd(this, MappingPackage.MAPPING__HELPER, Mapping.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newMapper, MappingPackage.MAPPING_HELPER__MAPPER, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MAPPING_HELPER__MAPPER, newMapper, newMapper));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getHelpedObject()
  {
    if (helpedObject != null && helpedObject.eIsProxy())
    {
      EObject oldHelpedObject = helpedObject;
      helpedObject = (EObject)eResolveProxy((InternalEObject)helpedObject);
      if (helpedObject != oldHelpedObject)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.MAPPING_HELPER__HELPED_OBJECT, oldHelpedObject, helpedObject));
      }
    }
    return helpedObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetHelpedObject()
  {
    return helpedObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHelpedObject(EObject newHelpedObject)
  {
    EObject oldHelpedObject = helpedObject;
    helpedObject = newHelpedObject;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MAPPING_HELPER__HELPED_OBJECT, oldHelpedObject, helpedObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingHelper getNestedIn()
  {
    if (eContainerFeatureID != MappingPackage.MAPPING_HELPER__NESTED_IN) return null;
    return (MappingHelper)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNestedIn(MappingHelper newNestedIn)
  {
    if (newNestedIn != eContainer || (eContainerFeatureID != MappingPackage.MAPPING_HELPER__NESTED_IN && newNestedIn != null))
    {
      if (EcoreUtil.isAncestor(this, newNestedIn))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newNestedIn != null)
        msgs = ((InternalEObject)newNestedIn).eInverseAdd(this, MappingPackage.MAPPING_HELPER__NESTED, MappingHelper.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newNestedIn, MappingPackage.MAPPING_HELPER__NESTED_IN, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MAPPING_HELPER__NESTED_IN, newNestedIn, newNestedIn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getNested()
  {
    if (nested == null)
    {
      nested = new EObjectContainmentWithInverseEList(MappingHelper.class, this, MappingPackage.MAPPING_HELPER__NESTED, MappingPackage.MAPPING_HELPER__NESTED_IN);
    }
    return nested;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case MappingPackage.MAPPING_HELPER__MAPPER:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.MAPPING_HELPER__MAPPER, msgs);
        case MappingPackage.MAPPING_HELPER__NESTED_IN:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.MAPPING_HELPER__NESTED_IN, msgs);
        case MappingPackage.MAPPING_HELPER__NESTED:
          return ((InternalEList)getNested()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
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
        case MappingPackage.MAPPING_HELPER__MAPPER:
          return eBasicSetContainer(null, MappingPackage.MAPPING_HELPER__MAPPER, msgs);
        case MappingPackage.MAPPING_HELPER__NESTED_IN:
          return eBasicSetContainer(null, MappingPackage.MAPPING_HELPER__NESTED_IN, msgs);
        case MappingPackage.MAPPING_HELPER__NESTED:
          return ((InternalEList)getNested()).basicRemove(otherEnd, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case MappingPackage.MAPPING_HELPER__MAPPER:
          return ((InternalEObject)eContainer).eInverseRemove(this, MappingPackage.MAPPING__HELPER, Mapping.class, msgs);
        case MappingPackage.MAPPING_HELPER__NESTED_IN:
          return ((InternalEObject)eContainer).eInverseRemove(this, MappingPackage.MAPPING_HELPER__NESTED, MappingHelper.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case MappingPackage.MAPPING_HELPER__MAPPER:
        return getMapper();
      case MappingPackage.MAPPING_HELPER__HELPED_OBJECT:
        if (resolve) return getHelpedObject();
        return basicGetHelpedObject();
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        return getNestedIn();
      case MappingPackage.MAPPING_HELPER__NESTED:
        return getNested();
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
      case MappingPackage.MAPPING_HELPER__MAPPER:
        setMapper((Mapping)newValue);
        return;
      case MappingPackage.MAPPING_HELPER__HELPED_OBJECT:
        setHelpedObject((EObject)newValue);
        return;
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        setNestedIn((MappingHelper)newValue);
        return;
      case MappingPackage.MAPPING_HELPER__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
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
      case MappingPackage.MAPPING_HELPER__MAPPER:
        setMapper((Mapping)null);
        return;
      case MappingPackage.MAPPING_HELPER__HELPED_OBJECT:
        setHelpedObject((EObject)null);
        return;
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        setNestedIn((MappingHelper)null);
        return;
      case MappingPackage.MAPPING_HELPER__NESTED:
        getNested().clear();
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
      case MappingPackage.MAPPING_HELPER__MAPPER:
        return getMapper() != null;
      case MappingPackage.MAPPING_HELPER__HELPED_OBJECT:
        return helpedObject != null;
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        return getNestedIn() != null;
      case MappingPackage.MAPPING_HELPER__NESTED:
        return nested != null && !nested.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //MappingHelperImpl


