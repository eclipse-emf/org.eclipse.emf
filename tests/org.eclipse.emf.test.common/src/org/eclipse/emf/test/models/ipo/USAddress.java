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
 * $Id: USAddress.java,v 1.3 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.ipo;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>US Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.USAddress#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.USAddress#getZip <em>Zip</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='USAddress' kind='elementOnly'"
 * @generated
 */
public interface USAddress extends Address
{
  /**
   * Returns the value of the '<em><b>State</b></em>' attribute.
   * The default value is <code>"AK"</code>.
   * The literals are from the enumeration {@link org.eclipse.emf.test.models.ipo.USState}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State</em>' attribute.
   * @see org.eclipse.emf.test.models.ipo.USState
   * @see #isSetState()
   * @see #unsetState()
   * @see #setState(USState)
   * @model default="AK" unsettable="true" required="true"
   *        extendedMetaData="kind='element' name='state'"
   * @generated
   */
  USState getState();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ipo.USAddress#getState <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>State</em>' attribute.
   * @see org.eclipse.emf.test.models.ipo.USState
   * @see #isSetState()
   * @see #unsetState()
   * @see #getState()
   * @generated
   */
  void setState(USState value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ipo.USAddress#getState <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetState()
   * @see #getState()
   * @see #setState(USState)
   * @generated
   */
  void unsetState();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ipo.USAddress#getState <em>State</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>State</em>' attribute is set.
   * @see #unsetState()
   * @see #getState()
   * @see #setState(USState)
   * @generated
   */
  boolean isSetState();

  /**
   * Returns the value of the '<em><b>Zip</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Zip</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Zip</em>' attribute.
   * @see #setZip(BigInteger)
   * @model dataType="org.eclipse.emf.ecore.xml.type.PositiveInteger" required="true"
   *        extendedMetaData="kind='element' name='zip'"
   * @generated
   */
  BigInteger getZip();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ipo.USAddress#getZip <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Zip</em>' attribute.
   * @see #getZip()
   * @generated
   */
  void setZip(BigInteger value);

} // USAddress
