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
 * $Id: CreditInfo.java,v 1.4 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.customer;

import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Credit Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.CreditInfo#getHolder <em>Holder</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CreditInfo#getCcNumber <em>Cc Number</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CreditInfo#getExpireDate <em>Expire Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo()
 * @model extendedMetaData="name='creditInfo' kind='elementOnly'"
 * @generated
 */
public interface CreditInfo extends EObject
{
  /**
   * Returns the value of the '<em><b>Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Holder</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Holder</em>' attribute.
   * @see #setHolder(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo_Holder()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='holder'"
   * @generated
   */
  String getHolder();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CreditInfo#getHolder <em>Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Holder</em>' attribute.
   * @see #getHolder()
   * @generated
   */
  void setHolder(String value);

  /**
   * Returns the value of the '<em><b>Cc Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cc Number</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cc Number</em>' attribute.
   * @see #setCcNumber(BigInteger)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo_CcNumber()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
   *        extendedMetaData="kind='element' name='cc_number'"
   * @generated
   */
  BigInteger getCcNumber();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CreditInfo#getCcNumber <em>Cc Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cc Number</em>' attribute.
   * @see #getCcNumber()
   * @generated
   */
  void setCcNumber(BigInteger value);

  /**
   * Returns the value of the '<em><b>Expire Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expire Date</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expire Date</em>' attribute.
   * @see #setExpireDate(XMLGregorianCalendar)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo_ExpireDate()
   * @model dataType="org.eclipse.emf.ecore.xml.type.GYearMonth" required="true"
   *        extendedMetaData="kind='element' name='expireDate'"
   * @generated
   */
  XMLGregorianCalendar getExpireDate();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CreditInfo#getExpireDate <em>Expire Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expire Date</em>' attribute.
   * @see #getExpireDate()
   * @generated
   */
  void setExpireDate(XMLGregorianCalendar value);

} // CreditInfo
