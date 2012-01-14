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
package org.eclipse.emf.test.models.ref;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>E</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.E#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.E#getIds <em>Ids</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.E#getLabels <em>Labels</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.E#getD <em>D</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getE()
 * @model
 * @generated
 */
public interface E extends EObject
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
   * @see org.eclipse.emf.test.models.ref.RefPackage#getE_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.E#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Ids</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ids</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ids</em>' attribute list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getE_Ids()
   * @model
   * @generated
   */
  EList<String> getIds();

  /**
   * Returns the value of the '<em><b>Labels</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Labels</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Labels</em>' attribute list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getE_Labels()
   * @model unique="false"
   * @generated
   */
  EList<String> getLabels();

  /**
   * Returns the value of the '<em><b>D</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.D}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.D#getE <em>E</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>D</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>D</em>' reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getE_D()
   * @see org.eclipse.emf.test.models.ref.D#getE
   * @model opposite="e"
   * @generated
   */
  EList<D> getD();

} // E
