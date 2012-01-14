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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.mapping.FunctionPair;
import org.eclipse.emf.mapping.MappingPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.impl.FunctionPairImpl#getIn2out <em>In2out</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.impl.FunctionPairImpl#getOut2in <em>Out2in</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionPairImpl extends TypeConverterImpl implements FunctionPair
{
  /**
   * The cached value of the '{@link #getIn2out() <em>In2out</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn2out()
   * @generated
   * @ordered
   */
  protected EOperation in2out;

  /**
   * The cached value of the '{@link #getOut2in() <em>Out2in</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut2in()
   * @generated
   * @ordered
   */
  protected EOperation out2in;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FunctionPairImpl()
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
    return MappingPackage.Literals.FUNCTION_PAIR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getIn2out()
  {
    if (in2out != null && in2out.eIsProxy())
    {
      InternalEObject oldIn2out = (InternalEObject)in2out;
      in2out = (EOperation)eResolveProxy(oldIn2out);
      if (in2out != oldIn2out)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.FUNCTION_PAIR__IN2OUT, oldIn2out, in2out));
      }
    }
    return in2out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation basicGetIn2out()
  {
    return in2out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIn2out(EOperation newIn2out)
  {
    EOperation oldIn2out = in2out;
    in2out = newIn2out;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.FUNCTION_PAIR__IN2OUT, oldIn2out, in2out));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getOut2in()
  {
    if (out2in != null && out2in.eIsProxy())
    {
      InternalEObject oldOut2in = (InternalEObject)out2in;
      out2in = (EOperation)eResolveProxy(oldOut2in);
      if (out2in != oldOut2in)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingPackage.FUNCTION_PAIR__OUT2IN, oldOut2in, out2in));
      }
    }
    return out2in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation basicGetOut2in()
  {
    return out2in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOut2in(EOperation newOut2in)
  {
    EOperation oldOut2in = out2in;
    out2in = newOut2in;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.FUNCTION_PAIR__OUT2IN, oldOut2in, out2in));
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
      case MappingPackage.FUNCTION_PAIR__IN2OUT:
        if (resolve) return getIn2out();
        return basicGetIn2out();
      case MappingPackage.FUNCTION_PAIR__OUT2IN:
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
      case MappingPackage.FUNCTION_PAIR__IN2OUT:
        setIn2out((EOperation)newValue);
        return;
      case MappingPackage.FUNCTION_PAIR__OUT2IN:
        setOut2in((EOperation)newValue);
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
      case MappingPackage.FUNCTION_PAIR__IN2OUT:
        setIn2out((EOperation)null);
        return;
      case MappingPackage.FUNCTION_PAIR__OUT2IN:
        setOut2in((EOperation)null);
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
      case MappingPackage.FUNCTION_PAIR__IN2OUT:
        return in2out != null;
      case MappingPackage.FUNCTION_PAIR__OUT2IN:
        return out2in != null;
    }
    return super.eIsSet(featureID);
  }

} //FunctionPairImpl


