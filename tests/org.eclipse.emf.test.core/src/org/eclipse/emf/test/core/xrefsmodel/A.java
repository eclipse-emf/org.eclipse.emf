/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 *
 */
package org.eclipse.emf.test.core.xrefsmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.A#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.A#getOthers <em>Others</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.A#getAllOthers <em>All Others</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.A#getNonOthers <em>Non Others</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage#getA()
 * @model
 * @generated
 */
public interface A extends EObject
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
   * @see org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage#getA_Name()
   * @model required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.core.xrefsmodel.A#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Others</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsmodel.A}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Others</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Others</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage#getA_Others()
   * @model
   * @generated
   */
  EList<A> getOthers();

  /**
   * Returns the value of the '<em><b>All Others</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsmodel.A}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>All Others</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Others</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage#getA_AllOthers()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList<A> getAllOthers();

  /**
   * Returns the value of the '<em><b>Non Others</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsmodel.A}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Non Others</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non Others</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage#getA_NonOthers()
   * @model
   * @generated
   */
  EList<A> getNonOthers();

} // A
