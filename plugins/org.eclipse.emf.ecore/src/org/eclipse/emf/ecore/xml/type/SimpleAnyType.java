/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: SimpleAnyType.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xml.type;


import org.eclipse.emf.ecore.EDataType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Any Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getRawValue <em>Raw Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getInstanceType <em>Instance Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xml.type.XMLTypePackage#getSimpleAnyType()
 * @model 
 * @generated
 */
public interface SimpleAnyType extends AnyType
{
  /**
   * Returns the value of the '<em><b>Raw Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Raw Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Raw Value</em>' attribute.
   * @see #setRawValue(String)
   * @see org.eclipse.emf.ecore.xml.type.XMLTypePackage#getSimpleAnyType_RawValue()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   * @generated
   */
  String getRawValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getRawValue <em>Raw Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Raw Value</em>' attribute.
   * @see #getRawValue()
   * @generated
   */
  void setRawValue(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(Object)
   * @see org.eclipse.emf.ecore.xml.type.XMLTypePackage#getSimpleAnyType_Value()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType" transient="true" volatile="true" derived="true"
   * @generated
   */
  Object getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(Object value);

  /**
   * Returns the value of the '<em><b>Instance Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Instance Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Instance Type</em>' reference.
   * @see #setInstanceType(EDataType)
   * @see org.eclipse.emf.ecore.xml.type.XMLTypePackage#getSimpleAnyType_InstanceType()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  EDataType getInstanceType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getInstanceType <em>Instance Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Instance Type</em>' reference.
   * @see #getInstanceType()
   * @generated
   */
  void setInstanceType(EDataType value);

} // SimpleAnyType
