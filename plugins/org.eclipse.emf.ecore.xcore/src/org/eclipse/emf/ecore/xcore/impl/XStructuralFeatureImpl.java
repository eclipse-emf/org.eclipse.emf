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

import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcorePackage;

import org.eclipse.xtext.xbase.XBlockExpression;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XStructural Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#isReadonly <em>Readonly</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#isUnsettable <em>Unsettable</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#isDerived <em>Derived</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#getGetBody <em>Get Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#getSetBody <em>Set Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#getIsSetBody <em>Is Set Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl#getUnsetBody <em>Unset Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XStructuralFeatureImpl extends XMemberImpl implements XStructuralFeature
{
  /**
   * The default value of the '{@link #isReadonly() <em>Readonly</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReadonly()
   * @generated
   * @ordered
   */
  protected static final boolean READONLY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isReadonly() <em>Readonly</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReadonly()
   * @generated
   * @ordered
   */
  protected boolean readonly = READONLY_EDEFAULT;

  /**
   * The default value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVolatile()
   * @generated
   * @ordered
   */
  protected static final boolean VOLATILE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVolatile()
   * @generated
   * @ordered
   */
  protected boolean volatile_ = VOLATILE_EDEFAULT;

  /**
   * The default value of the '{@link #isTransient() <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTransient()
   * @generated
   * @ordered
   */
  protected static final boolean TRANSIENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTransient() <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTransient()
   * @generated
   * @ordered
   */
  protected boolean transient_ = TRANSIENT_EDEFAULT;

  /**
   * The default value of the '{@link #isUnsettable() <em>Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnsettable()
   * @generated
   * @ordered
   */
  protected static final boolean UNSETTABLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isUnsettable() <em>Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnsettable()
   * @generated
   * @ordered
   */
  protected boolean unsettable = UNSETTABLE_EDEFAULT;

  /**
   * The default value of the '{@link #isDerived() <em>Derived</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDerived()
   * @generated
   * @ordered
   */
  protected static final boolean DERIVED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDerived() <em>Derived</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDerived()
   * @generated
   * @ordered
   */
  protected boolean derived = DERIVED_EDEFAULT;

  /**
   * The cached value of the '{@link #getGetBody() <em>Get Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGetBody()
   * @generated
   * @ordered
   */
  protected XBlockExpression getBody;

  /**
   * The cached value of the '{@link #getSetBody() <em>Set Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetBody()
   * @generated
   * @ordered
   */
  protected XBlockExpression setBody;

  /**
   * The cached value of the '{@link #getIsSetBody() <em>Is Set Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIsSetBody()
   * @generated
   * @ordered
   */
  protected XBlockExpression isSetBody;

  /**
   * The cached value of the '{@link #getUnsetBody() <em>Unset Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnsetBody()
   * @generated
   * @ordered
   */
  protected XBlockExpression unsetBody;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XStructuralFeatureImpl()
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
    return XcorePackage.Literals.XSTRUCTURAL_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isReadonly()
  {
    return readonly;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReadonly(boolean newReadonly)
  {
    boolean oldReadonly = readonly;
    readonly = newReadonly;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__READONLY, oldReadonly, readonly));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isVolatile()
  {
    return volatile_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVolatile(boolean newVolatile)
  {
    boolean oldVolatile = volatile_;
    volatile_ = newVolatile;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE, oldVolatile, volatile_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTransient()
  {
    return transient_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransient(boolean newTransient)
  {
    boolean oldTransient = transient_;
    transient_ = newTransient;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT, oldTransient, transient_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnsettable()
  {
    return unsettable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnsettable(boolean newUnsettable)
  {
    boolean oldUnsettable = unsettable;
    unsettable = newUnsettable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE, oldUnsettable, unsettable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDerived()
  {
    return derived;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDerived(boolean newDerived)
  {
    boolean oldDerived = derived;
    derived = newDerived;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__DERIVED, oldDerived, derived));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getGetBody()
  {
    return getBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGetBody(XBlockExpression newGetBody, NotificationChain msgs)
  {
    XBlockExpression oldGetBody = getBody;
    getBody = newGetBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY, oldGetBody, newGetBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGetBody(XBlockExpression newGetBody)
  {
    if (newGetBody != getBody)
    {
      NotificationChain msgs = null;
      if (getBody != null)
        msgs = ((InternalEObject)getBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY, null, msgs);
      if (newGetBody != null)
        msgs = ((InternalEObject)newGetBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY, null, msgs);
      msgs = basicSetGetBody(newGetBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY, newGetBody, newGetBody));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getSetBody()
  {
    return setBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSetBody(XBlockExpression newSetBody, NotificationChain msgs)
  {
    XBlockExpression oldSetBody = setBody;
    setBody = newSetBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY, oldSetBody, newSetBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSetBody(XBlockExpression newSetBody)
  {
    if (newSetBody != setBody)
    {
      NotificationChain msgs = null;
      if (setBody != null)
        msgs = ((InternalEObject)setBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY, null, msgs);
      if (newSetBody != null)
        msgs = ((InternalEObject)newSetBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY, null, msgs);
      msgs = basicSetSetBody(newSetBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY, newSetBody, newSetBody));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getIsSetBody()
  {
    return isSetBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIsSetBody(XBlockExpression newIsSetBody, NotificationChain msgs)
  {
    XBlockExpression oldIsSetBody = isSetBody;
    isSetBody = newIsSetBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY, oldIsSetBody, newIsSetBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIsSetBody(XBlockExpression newIsSetBody)
  {
    if (newIsSetBody != isSetBody)
    {
      NotificationChain msgs = null;
      if (isSetBody != null)
        msgs = ((InternalEObject)isSetBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY, null, msgs);
      if (newIsSetBody != null)
        msgs = ((InternalEObject)newIsSetBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY, null, msgs);
      msgs = basicSetIsSetBody(newIsSetBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY, newIsSetBody, newIsSetBody));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getUnsetBody()
  {
    return unsetBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnsetBody(XBlockExpression newUnsetBody, NotificationChain msgs)
  {
    XBlockExpression oldUnsetBody = unsetBody;
    unsetBody = newUnsetBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY, oldUnsetBody, newUnsetBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnsetBody(XBlockExpression newUnsetBody)
  {
    if (newUnsetBody != unsetBody)
    {
      NotificationChain msgs = null;
      if (unsetBody != null)
        msgs = ((InternalEObject)unsetBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY, null, msgs);
      if (newUnsetBody != null)
        msgs = ((InternalEObject)newUnsetBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY, null, msgs);
      msgs = basicSetUnsetBody(newUnsetBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY, newUnsetBody, newUnsetBody));
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
      case XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY:
        return basicSetGetBody(null, msgs);
      case XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY:
        return basicSetSetBody(null, msgs);
      case XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY:
        return basicSetIsSetBody(null, msgs);
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY:
        return basicSetUnsetBody(null, msgs);
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
      case XcorePackage.XSTRUCTURAL_FEATURE__READONLY:
        return isReadonly();
      case XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE:
        return isVolatile();
      case XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT:
        return isTransient();
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE:
        return isUnsettable();
      case XcorePackage.XSTRUCTURAL_FEATURE__DERIVED:
        return isDerived();
      case XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY:
        return getGetBody();
      case XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY:
        return getSetBody();
      case XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY:
        return getIsSetBody();
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY:
        return getUnsetBody();
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
      case XcorePackage.XSTRUCTURAL_FEATURE__READONLY:
        setReadonly((Boolean)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE:
        setVolatile((Boolean)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT:
        setTransient((Boolean)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE:
        setUnsettable((Boolean)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__DERIVED:
        setDerived((Boolean)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY:
        setGetBody((XBlockExpression)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY:
        setSetBody((XBlockExpression)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY:
        setIsSetBody((XBlockExpression)newValue);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY:
        setUnsetBody((XBlockExpression)newValue);
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
      case XcorePackage.XSTRUCTURAL_FEATURE__READONLY:
        setReadonly(READONLY_EDEFAULT);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE:
        setVolatile(VOLATILE_EDEFAULT);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT:
        setTransient(TRANSIENT_EDEFAULT);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE:
        setUnsettable(UNSETTABLE_EDEFAULT);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__DERIVED:
        setDerived(DERIVED_EDEFAULT);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY:
        setGetBody((XBlockExpression)null);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY:
        setSetBody((XBlockExpression)null);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY:
        setIsSetBody((XBlockExpression)null);
        return;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY:
        setUnsetBody((XBlockExpression)null);
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
      case XcorePackage.XSTRUCTURAL_FEATURE__READONLY:
        return readonly != READONLY_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE:
        return volatile_ != VOLATILE_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT:
        return transient_ != TRANSIENT_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE:
        return unsettable != UNSETTABLE_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__DERIVED:
        return derived != DERIVED_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__GET_BODY:
        return getBody != null;
      case XcorePackage.XSTRUCTURAL_FEATURE__SET_BODY:
        return setBody != null;
      case XcorePackage.XSTRUCTURAL_FEATURE__IS_SET_BODY:
        return isSetBody != null;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSET_BODY:
        return unsetBody != null;
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
    result.append(" (readonly: ");
    result.append(readonly);
    result.append(", volatile: ");
    result.append(volatile_);
    result.append(", transient: ");
    result.append(transient_);
    result.append(", unsettable: ");
    result.append(unsettable);
    result.append(", derived: ");
    result.append(derived);
    result.append(')');
    return result.toString();
  }

} //XStructuralFeatureImpl
