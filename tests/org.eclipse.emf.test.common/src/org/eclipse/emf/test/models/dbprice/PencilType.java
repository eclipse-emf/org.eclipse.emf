/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.dbprice;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pencil Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.dbprice.PencilType#getPrice <em>Price</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.dbprice.DBPricePackage#getPencilType()
 * @model extendedMetaData="name='pencilType' kind='elementOnly'"
 * @generated
 */
public interface PencilType extends EObject
{
  /**
   * Returns the value of the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Price</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Price</em>' attribute.
   * @see #isSetPrice()
   * @see #unsetPrice()
   * @see #setPrice(int)
   * @see org.eclipse.emf.test.models.dbprice.DBPricePackage#getPencilType_Price()
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
   *        extendedMetaData="kind='element' name='price'"
   * @generated
   */
  int getPrice();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dbprice.PencilType#getPrice <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Price</em>' attribute.
   * @see #isSetPrice()
   * @see #unsetPrice()
   * @see #getPrice()
   * @generated
   */
  void setPrice(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.dbprice.PencilType#getPrice <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetPrice()
   * @see #getPrice()
   * @see #setPrice(int)
   * @generated
   */
  void unsetPrice();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.dbprice.PencilType#getPrice <em>Price</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Price</em>' attribute is set.
   * @see #unsetPrice()
   * @see #getPrice()
   * @see #setPrice(int)
   * @generated
   */
  boolean isSetPrice();

} // PencilType
