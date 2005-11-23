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
 * $Id: XSDSchemaCompositorImpl.java,v 1.9 2005/11/23 18:09:40 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaCompositor;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema Compositor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaCompositorImpl#getIncorporatedSchema <em>Incorporated Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDSchemaCompositorImpl 
  extends XSDSchemaDirectiveImpl 
  implements XSDSchemaCompositor
{
  /**
   * The cached value of the '{@link #getIncorporatedSchema() <em>Incorporated Schema</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIncorporatedSchema()
   * @generated
   * @ordered
   */
  protected XSDSchema incorporatedSchema = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDSchemaCompositorImpl()
  {
    super();
  }

  public void reset()
  {
    super.reset();
    if (incorporatedSchema != resolvedSchema && incorporatedSchema != null && incorporatedSchema.eContainer() != null)
    {
      EcoreUtil.remove(incorporatedSchema);
      incorporatedSchema.reset();
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_SCHEMA_COMPOSITOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSchema getIncorporatedSchema()
  {
    return incorporatedSchema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIncorporatedSchema(XSDSchema newIncorporatedSchema)
  {
    XSDSchema oldIncorporatedSchema = incorporatedSchema;
    incorporatedSchema = newIncorporatedSchema;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA, oldIncorporatedSchema, incorporatedSchema));
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
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__ELEMENT:
        return getElement();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__SCHEMA_LOCATION:
        return getSchemaLocation();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__RESOLVED_SCHEMA:
        return getResolvedSchema();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        return getIncorporatedSchema();
    }
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__SCHEMA_LOCATION:
        setSchemaLocation((String)newValue);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)newValue);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__SCHEMA_LOCATION:
        setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)null);
        return;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)null);
        return;
    }
    eDynamicUnset(featureID);
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
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__SCHEMA_LOCATION:
        return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__RESOLVED_SCHEMA:
        return resolvedSchema != null;
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        return incorporatedSchema != null;
    }
    return eDynamicIsSet(featureID);
  }

}
