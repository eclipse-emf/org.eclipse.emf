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


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.mapping.ComplexTypeConverter;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complex Type Converter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.impl.ComplexTypeConverterImpl#getIn2out <em>In2out</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.ComplexTypeConverterImpl#getOut2in <em>Out2in</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComplexTypeConverterImpl extends TypeConverterImpl implements ComplexTypeConverter
{
  /**
   * The cached value of the '{@link #getIn2out() <em>In2out</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn2out()
   * @generated
   * @ordered
   */
  protected Mapping in2out;

  /**
   * The cached value of the '{@link #getOut2in() <em>Out2in</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut2in()
   * @generated
   * @ordered
   */
  protected Mapping out2in;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComplexTypeConverterImpl()
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
    return MappingPackage.Literals.COMPLEX_TYPE_CONVERTER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping getIn2out()
  {
    if (in2out != null && in2out.eIsProxy())
    {
      InternalEObject oldIn2out = (InternalEObject)in2out;
      in2out = (Mapping)eResolveProxy(oldIn2out);
      if (in2out != oldIn2out)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT, oldIn2out, in2out));
      }
    }
    return in2out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping basicGetIn2out()
  {
    return in2out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIn2out(Mapping newIn2out)
  {
    Mapping oldIn2out = in2out;
    in2out = newIn2out;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT, oldIn2out, in2out));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping getOut2in()
  {
    if (out2in != null && out2in.eIsProxy())
    {
      InternalEObject oldOut2in = (InternalEObject)out2in;
      out2in = (Mapping)eResolveProxy(oldOut2in);
      if (out2in != oldOut2in)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN, oldOut2in, out2in));
      }
    }
    return out2in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping basicGetOut2in()
  {
    return out2in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOut2in(Mapping newOut2in)
  {
    Mapping oldOut2in = out2in;
    out2in = newOut2in;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN, oldOut2in, out2in));
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        if (resolve) return getIn2out();
        return basicGetIn2out();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        if (resolve) return getOut2in();
        return basicGetOut2in();
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        setIn2out((Mapping)newValue);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        setOut2in((Mapping)newValue);
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        setIn2out((Mapping)null);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        setOut2in((Mapping)null);
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        return in2out != null;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        return out2in != null;
    }
    return super.eIsSet(featureID);
  }

} //ComplexTypeConverterImpl
