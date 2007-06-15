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
 * $Id: UKAddress.java,v 1.3 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.ipo;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UK Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.UKAddress#getPostcode <em>Postcode</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.UKAddress#getExportCode <em>Export Code</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='UKAddress' kind='elementOnly'"
 * @generated
 */
public interface UKAddress extends Address
{
  /**
   * Returns the value of the '<em><b>Postcode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Postcode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Postcode</em>' attribute.
   * @see #setPostcode(String)
   * @model dataType="org.eclipse.emf.test.models.ipo.UKPostcode" required="true"
   *        extendedMetaData="kind='element' name='postcode'"
   * @generated
   */
  String getPostcode();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ipo.UKAddress#getPostcode <em>Postcode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Postcode</em>' attribute.
   * @see #getPostcode()
   * @generated
   */
  void setPostcode(String value);

  /**
   * Returns the value of the '<em><b>Export Code</b></em>' attribute.
   * The default value is <code>"1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Export Code</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Export Code</em>' attribute.
   * @see #isSetExportCode()
   * @see #unsetExportCode()
   * @see #setExportCode(BigInteger)
   * @model default="1" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.PositiveInteger"
   *        extendedMetaData="kind='attribute' name='exportCode'"
   * @generated
   */
  BigInteger getExportCode();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ipo.UKAddress#getExportCode <em>Export Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Export Code</em>' attribute.
   * @see #isSetExportCode()
   * @see #unsetExportCode()
   * @see #getExportCode()
   * @generated
   */
  void setExportCode(BigInteger value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ipo.UKAddress#getExportCode <em>Export Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetExportCode()
   * @see #getExportCode()
   * @see #setExportCode(BigInteger)
   * @generated
   */
  void unsetExportCode();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ipo.UKAddress#getExportCode <em>Export Code</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Export Code</em>' attribute is set.
   * @see #unsetExportCode()
   * @see #getExportCode()
   * @see #setExportCode(BigInteger)
   * @generated
   */
  boolean isSetExportCode();

} // UKAddress
