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
package org.eclipse.xsd.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDRedefinableComponent;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Redefinable Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDRedefinableComponentImpl#isCircular <em>Circular</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDRedefinableComponentImpl 
  extends XSDNamedComponentImpl 
  implements XSDRedefinableComponent
{
  /**
   * The default value of the '{@link #isCircular() <em>Circular</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCircular()
   * @generated
   * @ordered
   */
  protected static final boolean CIRCULAR_EDEFAULT = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDRedefinableComponentImpl()
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
    return XSDPackage.Literals.XSD_REDEFINABLE_COMPONENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getCircular() 
  {
    return isCircular() ? Boolean.TRUE : Boolean.FALSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public boolean isCircular()
  {
    return false;
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
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__CIRCULAR:
        return isCircular();
    }
    return super.eGet(featureID, resolve, coreType);
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
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__CIRCULAR:
        return isCircular() != CIRCULAR_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

} 
