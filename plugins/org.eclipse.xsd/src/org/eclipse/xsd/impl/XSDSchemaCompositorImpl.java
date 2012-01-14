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
  protected XSDSchema incorporatedSchema;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDSchemaCompositorImpl()
  {
    super();
  }

  @Override
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
  @Override
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        return getIncorporatedSchema();
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
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)newValue);
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
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)null);
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
      case XSDPackage.XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA:
        return incorporatedSchema != null;
    }
    return super.eIsSet(featureID);
  }

}
