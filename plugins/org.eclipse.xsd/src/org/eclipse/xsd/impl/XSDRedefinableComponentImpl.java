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
 * $Id: XSDRedefinableComponentImpl.java,v 1.8 2005/11/23 18:09:40 emerks Exp $
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ELEMENT:
        return getElement();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__NAME:
        return getName();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__TARGET_NAMESPACE:
        return getTargetNamespace();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ALIAS_NAME:
        return getAliasName();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__URI:
        return getURI();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ALIAS_URI:
        return getAliasURI();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__QNAME:
        return getQName();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__CIRCULAR:
        return isCircular() ? Boolean.TRUE : Boolean.FALSE;
    }
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ALIAS_NAME:
        return ALIAS_NAME_EDEFAULT == null ? getAliasName() != null : !ALIAS_NAME_EDEFAULT.equals(getAliasName());
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__URI:
        return URI_EDEFAULT == null ? getURI() != null : !URI_EDEFAULT.equals(getURI());
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ALIAS_URI:
        return ALIAS_URI_EDEFAULT == null ? getAliasURI() != null : !ALIAS_URI_EDEFAULT.equals(getAliasURI());
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__QNAME:
        return QNAME_EDEFAULT == null ? getQName() != null : !QNAME_EDEFAULT.equals(getQName());
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__CIRCULAR:
        return isCircular() != CIRCULAR_EDEFAULT;
    }
    return eDynamicIsSet(featureID);
  }

} 
