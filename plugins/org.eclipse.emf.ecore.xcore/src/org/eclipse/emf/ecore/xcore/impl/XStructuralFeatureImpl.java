/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
 * </p>
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
   * The flag representing the value of the '{@link #isReadonly() <em>Readonly</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReadonly()
   * @generated
   * @ordered
   */
  protected static final int READONLY_EFLAG = 1 << 3;

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
   * The flag representing the value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVolatile()
   * @generated
   * @ordered
   */
  protected static final int VOLATILE_EFLAG = 1 << 4;

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
   * The flag representing the value of the '{@link #isTransient() <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTransient()
   * @generated
   * @ordered
   */
  protected static final int TRANSIENT_EFLAG = 1 << 5;

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
   * The flag representing the value of the '{@link #isUnsettable() <em>Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnsettable()
   * @generated
   * @ordered
   */
  protected static final int UNSETTABLE_EFLAG = 1 << 6;

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
   * The flag representing the value of the '{@link #isDerived() <em>Derived</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDerived()
   * @generated
   * @ordered
   */
  protected static final int DERIVED_EFLAG = 1 << 7;

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
    return (eFlags & READONLY_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReadonly(boolean newReadonly)
  {
    boolean oldReadonly = (eFlags & READONLY_EFLAG) != 0;
    if (newReadonly) eFlags |= READONLY_EFLAG; else eFlags &= ~READONLY_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__READONLY, oldReadonly, newReadonly));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isVolatile()
  {
    return (eFlags & VOLATILE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVolatile(boolean newVolatile)
  {
    boolean oldVolatile = (eFlags & VOLATILE_EFLAG) != 0;
    if (newVolatile) eFlags |= VOLATILE_EFLAG; else eFlags &= ~VOLATILE_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE, oldVolatile, newVolatile));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTransient()
  {
    return (eFlags & TRANSIENT_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransient(boolean newTransient)
  {
    boolean oldTransient = (eFlags & TRANSIENT_EFLAG) != 0;
    if (newTransient) eFlags |= TRANSIENT_EFLAG; else eFlags &= ~TRANSIENT_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT, oldTransient, newTransient));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnsettable()
  {
    return (eFlags & UNSETTABLE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnsettable(boolean newUnsettable)
  {
    boolean oldUnsettable = (eFlags & UNSETTABLE_EFLAG) != 0;
    if (newUnsettable) eFlags |= UNSETTABLE_EFLAG; else eFlags &= ~UNSETTABLE_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE, oldUnsettable, newUnsettable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDerived()
  {
    return (eFlags & DERIVED_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDerived(boolean newDerived)
  {
    boolean oldDerived = (eFlags & DERIVED_EFLAG) != 0;
    if (newDerived) eFlags |= DERIVED_EFLAG; else eFlags &= ~DERIVED_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XSTRUCTURAL_FEATURE__DERIVED, oldDerived, newDerived));
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
        return ((eFlags & READONLY_EFLAG) != 0) != READONLY_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__VOLATILE:
        return ((eFlags & VOLATILE_EFLAG) != 0) != VOLATILE_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__TRANSIENT:
        return ((eFlags & TRANSIENT_EFLAG) != 0) != TRANSIENT_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__UNSETTABLE:
        return ((eFlags & UNSETTABLE_EFLAG) != 0) != UNSETTABLE_EDEFAULT;
      case XcorePackage.XSTRUCTURAL_FEATURE__DERIVED:
        return ((eFlags & DERIVED_EFLAG) != 0) != DERIVED_EDEFAULT;
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (readonly: ");
    result.append((eFlags & READONLY_EFLAG) != 0);
    result.append(", volatile: ");
    result.append((eFlags & VOLATILE_EFLAG) != 0);
    result.append(", transient: ");
    result.append((eFlags & TRANSIENT_EFLAG) != 0);
    result.append(", unsettable: ");
    result.append((eFlags & UNSETTABLE_EFLAG) != 0);
    result.append(", derived: ");
    result.append((eFlags & DERIVED_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

} //XStructuralFeatureImpl
