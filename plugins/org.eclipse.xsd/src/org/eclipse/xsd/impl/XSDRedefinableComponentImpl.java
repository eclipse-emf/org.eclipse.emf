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
 * $Id: XSDRedefinableComponentImpl.java,v 1.3 2004/08/11 15:08:55 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

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
    return XSDPackage.eINSTANCE.getXSDRedefinableComponent();
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
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XSDPackage.XSD_REDEFINABLE_COMPONENT__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__NAME:
        setName((String)newValue);
        return;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
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
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XSDPackage.XSD_REDEFINABLE_COMPONENT__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
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
    return eDynamicIsSet(eFeature);
  }

} 
