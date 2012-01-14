/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
  protected EObject helpedObject;

  /**
   * The cached value of the '{@link #getNested() <em>Nested</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNested()
   * @generated
   * @ordered
   */
  protected EList<MappingHelper> nested;

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
  @Override
  protected EClass eStaticClass()
  {
    return MappingPackage.Literals.MAPPING_HELPER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping getMapper()
  {
    if (eContainerFeatureID() != MappingPackage.MAPPING_HELPER__MAPPER) return null;
    return (Mapping)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMapper(Mapping newMapper, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newMapper, MappingPackage.MAPPING_HELPER__MAPPER, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMapper(Mapping newMapper)
  {
    if (newMapper != eInternalContainer() || (eContainerFeatureID() != MappingPackage.MAPPING_HELPER__MAPPER && newMapper != null))
    {
      if (EcoreUtil.isAncestor(this, newMapper))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newMapper != null)
        msgs = ((InternalEObject)newMapper).eInverseAdd(this, MappingPackage.MAPPING__HELPER, Mapping.class, msgs);
      msgs = basicSetMapper(newMapper, msgs);
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
      InternalEObject oldHelpedObject = (InternalEObject)helpedObject;
      helpedObject = eResolveProxy(oldHelpedObject);
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
    if (eContainerFeatureID() != MappingPackage.MAPPING_HELPER__NESTED_IN) return null;
    return (MappingHelper)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNestedIn(MappingHelper newNestedIn, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newNestedIn, MappingPackage.MAPPING_HELPER__NESTED_IN, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNestedIn(MappingHelper newNestedIn)
  {
    if (newNestedIn != eInternalContainer() || (eContainerFeatureID() != MappingPackage.MAPPING_HELPER__NESTED_IN && newNestedIn != null))
    {
      if (EcoreUtil.isAncestor(this, newNestedIn))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newNestedIn != null)
        msgs = ((InternalEObject)newNestedIn).eInverseAdd(this, MappingPackage.MAPPING_HELPER__NESTED, MappingHelper.class, msgs);
      msgs = basicSetNestedIn(newNestedIn, msgs);
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
  public EList<MappingHelper> getNested()
  {
    if (nested == null)
    {
      nested = new EObjectContainmentWithInverseEList<MappingHelper>(MappingHelper.class, this, MappingPackage.MAPPING_HELPER__NESTED, MappingPackage.MAPPING_HELPER__NESTED_IN);
    }
    return nested;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MappingPackage.MAPPING_HELPER__MAPPER:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetMapper((Mapping)otherEnd, msgs);
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetNestedIn((MappingHelper)otherEnd, msgs);
      case MappingPackage.MAPPING_HELPER__NESTED:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getNested()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MappingPackage.MAPPING_HELPER__MAPPER:
        return basicSetMapper(null, msgs);
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        return basicSetNestedIn(null, msgs);
      case MappingPackage.MAPPING_HELPER__NESTED:
        return ((InternalEList<?>)getNested()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case MappingPackage.MAPPING_HELPER__MAPPER:
        return eInternalContainer().eInverseRemove(this, MappingPackage.MAPPING__HELPER, Mapping.class, msgs);
      case MappingPackage.MAPPING_HELPER__NESTED_IN:
        return eInternalContainer().eInverseRemove(this, MappingPackage.MAPPING_HELPER__NESTED, MappingHelper.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
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
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
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
        getNested().addAll((Collection<? extends MappingHelper>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
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
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
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
    return super.eIsSet(featureID);
  }

} //MappingHelperImpl


