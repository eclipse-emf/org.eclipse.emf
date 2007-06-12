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
 * $Id: Item.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.key;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.key.Item#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.key.Item#getSignature <em>Signature</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.key.Item#getRelatedItems <em>Related Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.key.KeyPackage#getItem()
 * @model
 * @generated
 */
public interface Item extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.test.models.key.KeyPackage#getItem_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.key.Item#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Signature</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Signature</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Signature</em>' attribute list.
   * @see org.eclipse.emf.test.models.key.KeyPackage#getItem_Signature()
   * @model unique="false"
   * @generated
   */
  EList<String> getSignature();

  /**
   * Returns the value of the '<em><b>Related Items</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.key.Item}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Related Items</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Related Items</em>' reference list.
   * @see org.eclipse.emf.test.models.key.KeyPackage#getItem_RelatedItems()
   * @model
   * @generated
   */
  EList<Item> getRelatedItems();

} // Item
