/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.common.types.JvmTypeReference;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XClassifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl#getInstanceType <em>Instance Type</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XClassifierImpl extends XNamedElementImpl implements XClassifier
{
  /**
   * The cached value of the '{@link #getInstanceType() <em>Instance Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstanceType()
   * @generated
   * @ordered
   */
  protected JvmTypeReference instanceType;

  /**
   * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeParameters()
   * @generated
   * @ordered
   */
  protected EList<XTypeParameter> typeParameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XClassifierImpl()
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
    return XcorePackage.Literals.XCLASSIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getInstanceType()
  {
    return instanceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInstanceType(JvmTypeReference newInstanceType, NotificationChain msgs)
  {
    JvmTypeReference oldInstanceType = instanceType;
    instanceType = newInstanceType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(
        this,
        Notification.SET,
        XcorePackage.XCLASSIFIER__INSTANCE_TYPE,
        oldInstanceType,
        newInstanceType);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstanceType(JvmTypeReference newInstanceType)
  {
    if (newInstanceType != instanceType)
    {
      NotificationChain msgs = null;
      if (instanceType != null)
        msgs = ((InternalEObject)instanceType).eInverseRemove(
          this,
          EOPPOSITE_FEATURE_BASE - XcorePackage.XCLASSIFIER__INSTANCE_TYPE,
          null,
          msgs);
      if (newInstanceType != null)
        msgs = ((InternalEObject)newInstanceType).eInverseAdd(
          this,
          EOPPOSITE_FEATURE_BASE - XcorePackage.XCLASSIFIER__INSTANCE_TYPE,
          null,
          msgs);
      msgs = basicSetInstanceType(newInstanceType, msgs);
      if (msgs != null)
        msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XCLASSIFIER__INSTANCE_TYPE, newInstanceType, newInstanceType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XPackage getPackage()
  {
    if (eContainerFeatureID() != XcorePackage.XCLASSIFIER__PACKAGE)
      return null;
    return (XPackage)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XTypeParameter> getTypeParameters()
  {
    if (typeParameters == null)
    {
      typeParameters = new EObjectContainmentEList<XTypeParameter>(XTypeParameter.class, this, XcorePackage.XCLASSIFIER__TYPE_PARAMETERS);
    }
    return typeParameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XCLASSIFIER__PACKAGE:
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      return eBasicSetContainer(otherEnd, XcorePackage.XCLASSIFIER__PACKAGE, msgs);
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE:
      return basicSetInstanceType(null, msgs);
      case XcorePackage.XCLASSIFIER__PACKAGE:
      return eBasicSetContainer(null, XcorePackage.XCLASSIFIER__PACKAGE, msgs);
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
      return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
      case XcorePackage.XCLASSIFIER__PACKAGE:
      return eInternalContainer().eInverseRemove(this, XcorePackage.XPACKAGE__CLASSIFIERS, XPackage.class, msgs);
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE:
      return getInstanceType();
      case XcorePackage.XCLASSIFIER__PACKAGE:
      return getPackage();
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
      return getTypeParameters();
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE:
      setInstanceType((JvmTypeReference)newValue);
      return;
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
      getTypeParameters().clear();
      getTypeParameters().addAll((Collection<? extends XTypeParameter>)newValue);
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE:
      setInstanceType((JvmTypeReference)null);
      return;
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
      getTypeParameters().clear();
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE:
      return instanceType != null;
      case XcorePackage.XCLASSIFIER__PACKAGE:
      return getPackage() != null;
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
      return typeParameters != null && !typeParameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //XClassifierImpl
