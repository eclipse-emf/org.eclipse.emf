/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: CanadaAddr.java,v 1.3 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.customer;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Canada Addr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.CanadaAddr#getZip <em>Zip</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CanadaAddr#getProvince <em>Province</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCanadaAddr()
 * @model extendedMetaData="name='canadaAddr' kind='elementOnly'"
 * @generated
 */
public interface CanadaAddr extends AddressType
{
  /**
   * Returns the value of the '<em><b>Zip</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Zip</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Zip</em>' attribute.
   * @see #setZip(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCanadaAddr_Zip()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='zip'"
   * @generated
   */
  String getZip();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CanadaAddr#getZip <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Zip</em>' attribute.
   * @see #getZip()
   * @generated
   */
  void setZip(String value);

  /**
   * Returns the value of the '<em><b>Province</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Province</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Province</em>' attribute.
   * @see #setProvince(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCanadaAddr_Province()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='province'"
   * @generated
   */
  String getProvince();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CanadaAddr#getProvince <em>Province</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Province</em>' attribute.
   * @see #getProvince()
   * @generated
   */
  void setProvince(String value);

} // CanadaAddr
