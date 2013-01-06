/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.test.common.reification.Bidirectional;
import org.eclipse.emf.test.common.reification.ReificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bidirectional</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.BidirectionalImpl#getIn <em>In</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.BidirectionalImpl#getOut <em>Out</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.BidirectionalImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.BidirectionalImpl#getValuesList <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BidirectionalImpl<T extends Bidirectional<T>> extends EObjectImpl implements Bidirectional<T>
{
  /**
   * The cached value of the '{@link #getIn() <em>In</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn()
   * @generated
   * @ordered
   */
  protected T in;

  /**
   * The cached value of the '{@link #getOut() <em>Out</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut()
   * @generated
   * @ordered
   */
  protected T out;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected T value;

  /**
   * The cached value of the '{@link #getValuesList() <em>Values</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValuesList()
   * @generated
   * @ordered
   */
  protected EList<T> values;

  /**
   * The empty value for the '{@link #getValues() <em>Values</em>}' array accessor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValues()
   * @generated
   * @ordered
   */
  @SuppressWarnings("rawtypes")
  protected static final Bidirectional[] VALUES_EEMPTY_ARRAY = new Bidirectional [0];

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BidirectionalImpl()
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
    return ReificationPackage.Literals.BIDIRECTIONAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public T getIn()
  {
    if (in != null && in.eIsProxy())
    {
      InternalEObject oldIn = (InternalEObject)in;
      in = (T)eResolveProxy(oldIn);
      if (in != oldIn)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReificationPackage.BIDIRECTIONAL__IN, oldIn, in));
      }
    }
    return in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public T basicGetIn()
  {
    return in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIn(T newIn, NotificationChain msgs)
  {
    T oldIn = in;
    in = newIn;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.BIDIRECTIONAL__IN, oldIn, newIn);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIn(T newIn)
  {
    if (newIn != in)
    {
      NotificationChain msgs = null;
      if (in != null)
        msgs = ((InternalEObject)in).eInverseRemove(this, ReificationPackage.BIDIRECTIONAL__OUT, Bidirectional.class, msgs);
      if (newIn != null)
        msgs = ((InternalEObject)newIn).eInverseAdd(this, ReificationPackage.BIDIRECTIONAL__OUT, Bidirectional.class, msgs);
      msgs = basicSetIn(newIn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.BIDIRECTIONAL__IN, newIn, newIn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public T getOut()
  {
    if (out != null && out.eIsProxy())
    {
      InternalEObject oldOut = (InternalEObject)out;
      out = (T)eResolveProxy(oldOut);
      if (out != oldOut)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReificationPackage.BIDIRECTIONAL__OUT, oldOut, out));
      }
    }
    return out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public T basicGetOut()
  {
    return out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOut(T newOut, NotificationChain msgs)
  {
    T oldOut = out;
    out = newOut;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.BIDIRECTIONAL__OUT, oldOut, newOut);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOut(T newOut)
  {
    if (newOut != out)
    {
      NotificationChain msgs = null;
      if (out != null)
        msgs = ((InternalEObject)out).eInverseRemove(this, ReificationPackage.BIDIRECTIONAL__IN, Bidirectional.class, msgs);
      if (newOut != null)
        msgs = ((InternalEObject)newOut).eInverseAdd(this, ReificationPackage.BIDIRECTIONAL__IN, Bidirectional.class, msgs);
      msgs = basicSetOut(newOut, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.BIDIRECTIONAL__OUT, newOut, newOut));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public T getValue()
  {
    if (value != null && value.eIsProxy())
    {
      InternalEObject oldValue = (InternalEObject)value;
      value = (T)eResolveProxy(oldValue);
      if (value != oldValue)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReificationPackage.BIDIRECTIONAL__VALUE, oldValue, value));
      }
    }
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public T basicGetValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(T newValue)
  {
    T oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.BIDIRECTIONAL__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public Bidirectional<T>[] getValues()
  {
    if (values == null || values.isEmpty()) return VALUES_EEMPTY_ARRAY;
    BasicEList<T> list = (BasicEList<T>)values;
    list.shrink();
    return (Bidirectional<T>[])list.data();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public T getValues(int index)
  {
    return getValuesList().get(index);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValuesLength()
  {
    return values == null ? 0 : values.size();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValues(T[] newValues)
  {
    ((BasicEList<T>)getValuesList()).setData(newValues.length, newValues);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValues(int index, T element)
  {
    getValuesList().set(index, element);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<T> getValuesList()
  {
    if (values == null)
    {
      values = new EObjectResolvingEList<T>(Bidirectional.class, this, ReificationPackage.BIDIRECTIONAL__VALUES);
    }
    return values;
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
      case ReificationPackage.BIDIRECTIONAL__IN:
        if (in != null)
          msgs = ((InternalEObject)in).eInverseRemove(this, ReificationPackage.BIDIRECTIONAL__OUT, Bidirectional.class, msgs);
        return basicSetIn((T)otherEnd, msgs);
      case ReificationPackage.BIDIRECTIONAL__OUT:
        if (out != null)
          msgs = ((InternalEObject)out).eInverseRemove(this, ReificationPackage.BIDIRECTIONAL__IN, Bidirectional.class, msgs);
        return basicSetOut((T)otherEnd, msgs);
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
      case ReificationPackage.BIDIRECTIONAL__IN:
        return basicSetIn(null, msgs);
      case ReificationPackage.BIDIRECTIONAL__OUT:
        return basicSetOut(null, msgs);
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
      case ReificationPackage.BIDIRECTIONAL__IN:
        if (resolve) return getIn();
        return basicGetIn();
      case ReificationPackage.BIDIRECTIONAL__OUT:
        if (resolve) return getOut();
        return basicGetOut();
      case ReificationPackage.BIDIRECTIONAL__VALUE:
        if (resolve) return getValue();
        return basicGetValue();
      case ReificationPackage.BIDIRECTIONAL__VALUES:
        return getValuesList();
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
      case ReificationPackage.BIDIRECTIONAL__IN:
        setIn((T)newValue);
        return;
      case ReificationPackage.BIDIRECTIONAL__OUT:
        setOut((T)newValue);
        return;
      case ReificationPackage.BIDIRECTIONAL__VALUE:
        setValue((T)newValue);
        return;
      case ReificationPackage.BIDIRECTIONAL__VALUES:
        getValuesList().clear();
        getValuesList().addAll((Collection<? extends T>)newValue);
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
      case ReificationPackage.BIDIRECTIONAL__IN:
        setIn((T)null);
        return;
      case ReificationPackage.BIDIRECTIONAL__OUT:
        setOut((T)null);
        return;
      case ReificationPackage.BIDIRECTIONAL__VALUE:
        setValue((T)null);
        return;
      case ReificationPackage.BIDIRECTIONAL__VALUES:
        getValuesList().clear();
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
      case ReificationPackage.BIDIRECTIONAL__IN:
        return in != null;
      case ReificationPackage.BIDIRECTIONAL__OUT:
        return out != null;
      case ReificationPackage.BIDIRECTIONAL__VALUE:
        return value != null;
      case ReificationPackage.BIDIRECTIONAL__VALUES:
        return values != null && !values.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //BidirectionalImpl
