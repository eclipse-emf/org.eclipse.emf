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
 * $Id: FunctionNamePairImpl.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.mapping.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.mapping.FunctionNamePair;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Name Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.impl.FunctionNamePairImpl#getIn2out <em>In2out</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.FunctionNamePairImpl#getOut2in <em>Out2in</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */ 
public class FunctionNamePairImpl extends TypeConverterImpl implements FunctionNamePair
{
  /**
   * The default value of the '{@link #getIn2out() <em>In2out</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn2out()
   * @generated
   * @ordered
   */
  protected static final String IN2OUT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIn2out() <em>In2out</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn2out()
   * @generated
   * @ordered
   */
  protected String in2out = IN2OUT_EDEFAULT;

  /**
   * The default value of the '{@link #getOut2in() <em>Out2in</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut2in()
   * @generated
   * @ordered
   */
  protected static final String OUT2IN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOut2in() <em>Out2in</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut2in()
   * @generated
   * @ordered
   */
  protected String out2in = OUT2IN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FunctionNamePairImpl()
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
    return MappingPackage.eINSTANCE.getFunctionNamePair();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIn2out()
  {
    return in2out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIn2out(String newIn2out)
  {
    String oldIn2out = in2out;
    in2out = newIn2out;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.FUNCTION_NAME_PAIR__IN2OUT, oldIn2out, in2out));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOut2in()
  {
    return out2in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOut2in(String newOut2in)
  {
    String oldOut2in = out2in;
    out2in = newOut2in;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.FUNCTION_NAME_PAIR__OUT2IN, oldOut2in, out2in));
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
        case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.FUNCTION_NAME_PAIR__MAPPER, msgs);
        case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN, msgs);
        case MappingPackage.FUNCTION_NAME_PAIR__NESTED:
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
        case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
          return eBasicSetContainer(null, MappingPackage.FUNCTION_NAME_PAIR__MAPPER, msgs);
        case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
          return eBasicSetContainer(null, MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN, msgs);
        case MappingPackage.FUNCTION_NAME_PAIR__NESTED:
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
        case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
          return ((InternalEObject)eContainer).eInverseRemove(this, MappingPackage.MAPPING__HELPER, Mapping.class, msgs);
        case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
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
      case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
        return getMapper();
      case MappingPackage.FUNCTION_NAME_PAIR__HELPED_OBJECT:
        if (resolve) return getHelpedObject();
        return basicGetHelpedObject();
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
        return getNestedIn();
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED:
        return getNested();
      case MappingPackage.FUNCTION_NAME_PAIR__IN2OUT:
        return getIn2out();
      case MappingPackage.FUNCTION_NAME_PAIR__OUT2IN:
        return getOut2in();
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
      case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
        setMapper((Mapping)newValue);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__HELPED_OBJECT:
        setHelpedObject((EObject)newValue);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
        setNestedIn((MappingHelper)newValue);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__IN2OUT:
        setIn2out((String)newValue);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__OUT2IN:
        setOut2in((String)newValue);
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
      case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
        setMapper((Mapping)null);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__HELPED_OBJECT:
        setHelpedObject((EObject)null);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
        setNestedIn((MappingHelper)null);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED:
        getNested().clear();
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__IN2OUT:
        setIn2out(IN2OUT_EDEFAULT);
        return;
      case MappingPackage.FUNCTION_NAME_PAIR__OUT2IN:
        setOut2in(OUT2IN_EDEFAULT);
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
      case MappingPackage.FUNCTION_NAME_PAIR__MAPPER:
        return getMapper() != null;
      case MappingPackage.FUNCTION_NAME_PAIR__HELPED_OBJECT:
        return helpedObject != null;
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED_IN:
        return getNestedIn() != null;
      case MappingPackage.FUNCTION_NAME_PAIR__NESTED:
        return nested != null && !nested.isEmpty();
      case MappingPackage.FUNCTION_NAME_PAIR__IN2OUT:
        return IN2OUT_EDEFAULT == null ? in2out != null : !IN2OUT_EDEFAULT.equals(in2out);
      case MappingPackage.FUNCTION_NAME_PAIR__OUT2IN:
        return OUT2IN_EDEFAULT == null ? out2in != null : !OUT2IN_EDEFAULT.equals(out2in);
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (in2out: ");
    result.append(in2out);
    result.append(", out2in: ");
    result.append(out2in);
    result.append(')');
    return result.toString();
  }

} //FunctionNamePairImpl
