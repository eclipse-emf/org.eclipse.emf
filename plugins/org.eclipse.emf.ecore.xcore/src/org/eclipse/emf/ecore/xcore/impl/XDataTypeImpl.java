/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XcorePackage;

import org.eclipse.xtext.xbase.XBlockExpression;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XData Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl#isSerializable <em>Serializable</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl#getCreateBody <em>Create Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl#getConvertBody <em>Convert Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XDataTypeImpl extends XClassifierImpl implements XDataType
{
  /**
   * The default value of the '{@link #isSerializable() <em>Serializable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSerializable()
   * @generated
   * @ordered
   */
  protected static final boolean SERIALIZABLE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isSerializable() <em>Serializable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSerializable()
   * @generated
   * @ordered
   */
  protected boolean serializable = SERIALIZABLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getCreateBody() <em>Create Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreateBody()
   * @generated
   * @ordered
   */
  protected XBlockExpression createBody;

  /**
   * The cached value of the '{@link #getConvertBody() <em>Convert Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConvertBody()
   * @generated
   * @ordered
   */
  protected XBlockExpression convertBody;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XDataTypeImpl()
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
    return XcorePackage.Literals.XDATA_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSerializable()
  {
    return serializable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSerializable(boolean newSerializable)
  {
    boolean oldSerializable = serializable;
    serializable = newSerializable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XDATA_TYPE__SERIALIZABLE, oldSerializable, serializable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getCreateBody()
  {
    return createBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCreateBody(XBlockExpression newCreateBody, NotificationChain msgs)
  {
    XBlockExpression oldCreateBody = createBody;
    createBody = newCreateBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XDATA_TYPE__CREATE_BODY, oldCreateBody, newCreateBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreateBody(XBlockExpression newCreateBody)
  {
    if (newCreateBody != createBody)
    {
      NotificationChain msgs = null;
      if (createBody != null)
        msgs = ((InternalEObject)createBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XDATA_TYPE__CREATE_BODY, null, msgs);
      if (newCreateBody != null)
        msgs = ((InternalEObject)newCreateBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XDATA_TYPE__CREATE_BODY, null, msgs);
      msgs = basicSetCreateBody(newCreateBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XDATA_TYPE__CREATE_BODY, newCreateBody, newCreateBody));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getConvertBody()
  {
    return convertBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConvertBody(XBlockExpression newConvertBody, NotificationChain msgs)
  {
    XBlockExpression oldConvertBody = convertBody;
    convertBody = newConvertBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XDATA_TYPE__CONVERT_BODY, oldConvertBody, newConvertBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConvertBody(XBlockExpression newConvertBody)
  {
    if (newConvertBody != convertBody)
    {
      NotificationChain msgs = null;
      if (convertBody != null)
        msgs = ((InternalEObject)convertBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XDATA_TYPE__CONVERT_BODY, null, msgs);
      if (newConvertBody != null)
        msgs = ((InternalEObject)newConvertBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XDATA_TYPE__CONVERT_BODY, null, msgs);
      msgs = basicSetConvertBody(newConvertBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XDATA_TYPE__CONVERT_BODY, newConvertBody, newConvertBody));
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
      case XcorePackage.XDATA_TYPE__CREATE_BODY:
        return basicSetCreateBody(null, msgs);
      case XcorePackage.XDATA_TYPE__CONVERT_BODY:
        return basicSetConvertBody(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case XcorePackage.XDATA_TYPE__SERIALIZABLE:
        return isSerializable();
      case XcorePackage.XDATA_TYPE__CREATE_BODY:
        return getCreateBody();
      case XcorePackage.XDATA_TYPE__CONVERT_BODY:
        return getConvertBody();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XcorePackage.XDATA_TYPE__SERIALIZABLE:
        setSerializable((Boolean)newValue);
        return;
      case XcorePackage.XDATA_TYPE__CREATE_BODY:
        setCreateBody((XBlockExpression)newValue);
        return;
      case XcorePackage.XDATA_TYPE__CONVERT_BODY:
        setConvertBody((XBlockExpression)newValue);
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
      case XcorePackage.XDATA_TYPE__SERIALIZABLE:
        setSerializable(SERIALIZABLE_EDEFAULT);
        return;
      case XcorePackage.XDATA_TYPE__CREATE_BODY:
        setCreateBody((XBlockExpression)null);
        return;
      case XcorePackage.XDATA_TYPE__CONVERT_BODY:
        setConvertBody((XBlockExpression)null);
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
      case XcorePackage.XDATA_TYPE__SERIALIZABLE:
        return serializable != SERIALIZABLE_EDEFAULT;
      case XcorePackage.XDATA_TYPE__CREATE_BODY:
        return createBody != null;
      case XcorePackage.XDATA_TYPE__CONVERT_BODY:
        return convertBody != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (serializable: ");
    result.append(serializable);
    result.append(')');
    return result.toString();
  }

} //XDataTypeImpl
