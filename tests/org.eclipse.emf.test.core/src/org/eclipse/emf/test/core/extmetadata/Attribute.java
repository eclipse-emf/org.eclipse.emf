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
package org.eclipse.emf.test.core.extmetadata;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.extmetadata.Attribute#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.core.extmetadata.ExtmetadataPackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends NamedElement {
  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(org.eclipse.emf.test.core.extmetadata.Class)
   * @see org.eclipse.emf.test.core.extmetadata.ExtmetadataPackage#getAttribute_Type()
   * @model required="true"
   * @generated
   */
  org.eclipse.emf.test.core.extmetadata.Class getType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.core.extmetadata.Attribute#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(org.eclipse.emf.test.core.extmetadata.Class value);

} // Attribute
