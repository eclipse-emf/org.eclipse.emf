/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: ComplexTypeConverterImpl.java,v 1.4 2005/11/08 14:18:51 emerks Exp $
 */
package org.eclipse.emf.mapping.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.mapping.ComplexTypeConverter;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
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
  protected Mapping in2out = null;

  /**
   * The cached value of the '{@link #getOut2in() <em>Out2in</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut2in()
   * @generated
   * @ordered
   */
  protected Mapping out2in = null;

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
  protected EClass eStaticClass()
  {
    return MappingPackage.eINSTANCE.getComplexTypeConverter();
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
      Mapping oldIn2out = in2out;
      in2out = (Mapping)eResolveProxy((InternalEObject)in2out);
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
      Mapping oldOut2in = out2in;
      out2in = (Mapping)eResolveProxy((InternalEObject)out2in);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.COMPLEX_TYPE_CONVERTER__MAPPER:
        return getMapper();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__HELPED_OBJECT:
        if (resolve) return getHelpedObject();
        return basicGetHelpedObject();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED_IN:
        return getNestedIn();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED:
        return getNested();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        if (resolve) return getIn2out();
        return basicGetIn2out();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        if (resolve) return getOut2in();
        return basicGetOut2in();
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__MAPPER:
        setMapper((Mapping)newValue);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__HELPED_OBJECT:
        setHelpedObject((EObject)newValue);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED_IN:
        setNestedIn((MappingHelper)newValue);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        setIn2out((Mapping)newValue);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        setOut2in((Mapping)newValue);
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__MAPPER:
        setMapper((Mapping)null);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__HELPED_OBJECT:
        setHelpedObject((EObject)null);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED_IN:
        setNestedIn((MappingHelper)null);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED:
        getNested().clear();
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        setIn2out((Mapping)null);
        return;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        setOut2in((Mapping)null);
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
      case MappingPackage.COMPLEX_TYPE_CONVERTER__MAPPER:
        return getMapper() != null;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__HELPED_OBJECT:
        return helpedObject != null;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED_IN:
        return getNestedIn() != null;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__NESTED:
        return nested != null && !nested.isEmpty();
      case MappingPackage.COMPLEX_TYPE_CONVERTER__IN2OUT:
        return in2out != null;
      case MappingPackage.COMPLEX_TYPE_CONVERTER__OUT2IN:
        return out2in != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //ComplexTypeConverterImpl
