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
 * $Id: PersonnelType.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.personal.mixed;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Personnel Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonnelType#getPerson <em>Person</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='personnel_._type' kind='elementOnly'"
 * @generated
 */
public interface PersonnelType
{
  /**
   * Returns the value of the '<em><b>Person</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.personal.mixed.PersonType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Person</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Person</em>' containment reference list.
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='person' namespace='##targetNamespace'"
   * @generated
   */
  List<PersonType> getPerson();

} // PersonnelType
