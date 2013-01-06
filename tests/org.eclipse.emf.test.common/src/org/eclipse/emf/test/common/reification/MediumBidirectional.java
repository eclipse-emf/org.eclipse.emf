/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Medium Bidirectional</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.MediumBidirectional#getContentsList <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.common.reification.ReificationPackage#getMediumBidirectional()
 * @model
 * @generated
 */
public interface MediumBidirectional<T extends MediumBidirectional<T> & Medium> extends EObject
{
  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contents</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @generated
   */
  MediumBidirectional<T>[] getContents();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MediumBidirectional<T> getContents(int index);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  int getContentsLength();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  void setContents(MediumBidirectional<T>[] newContents);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  void setContents(int index, MediumBidirectional<T> element);

  /**
   * Returns the value of the '<em><b>Contents</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.common.reification.MediumBidirectional}&lt;T>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contents</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' reference list.
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getMediumBidirectional_Contents()
   * @model
   * @generated
   */
  EList<MediumBidirectional<T>> getContentsList();

} // MediumBidirectional
