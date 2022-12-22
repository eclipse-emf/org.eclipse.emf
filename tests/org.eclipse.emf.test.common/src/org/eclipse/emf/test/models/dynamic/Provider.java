/**
 * Copyright (c) 2022 Eclipse Contributor and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.models.dynamic;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.dynamic.Provider#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.dynamic.Provider#getAdmin <em>Admin</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.models.dynamic.DynamicPackage#getProvider()
 * @model
 * @generated
 */
public interface Provider extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.emf.test.models.dynamic.DynamicPackage#getProvider_Id()
   * @model id="true" required="true"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dynamic.Provider#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Admin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Admin</em>' containment reference.
   * @see #setAdmin(Admin)
   * @see org.eclipse.emf.test.models.dynamic.DynamicPackage#getProvider_Admin()
   * @model containment="true"
   * @generated
   */
  Admin getAdmin();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dynamic.Provider#getAdmin <em>Admin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Admin</em>' containment reference.
   * @see #getAdmin()
   * @generated
   */
  void setAdmin(Admin value);

} // Provider
