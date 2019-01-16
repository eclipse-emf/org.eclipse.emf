/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.AbstractA#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getAbstractA()
 * @model abstract="true"
 * @generated
 */
public interface AbstractA extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getAbstractA_Id()
   * @model id="true" required="true"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.core.xrefsopposite.AbstractA#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

} // AbstractA
