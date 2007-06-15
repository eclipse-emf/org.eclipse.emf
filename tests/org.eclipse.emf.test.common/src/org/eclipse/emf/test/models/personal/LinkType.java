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
 * $Id: LinkType.java,v 1.4 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.personal;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.LinkType#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.LinkType#getSubordinates <em>Subordinates</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='linkType' kind='empty'"
 * @generated
 */
public interface LinkType
{
  /**
   * Returns the value of the '<em><b>Manager</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Manager</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Manager</em>' attribute.
   * @see #setManager(String)
   * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
   *        extendedMetaData="kind='attribute' name='manager'"
   * @generated
   */
  String getManager();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.LinkType#getManager <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Manager</em>' attribute.
   * @see #getManager()
   * @generated
   */
  void setManager(String value);

  /**
   * Returns the value of the '<em><b>Subordinates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subordinates</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subordinates</em>' attribute.
   * @see #setSubordinates(List)
   * @model dataType="org.eclipse.emf.ecore.xml.type.IDREFS" many="false"
   *        extendedMetaData="kind='attribute' name='subordinates'"
   * @generated
   */
  List<String> getSubordinates();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.LinkType#getSubordinates <em>Subordinates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subordinates</em>' attribute.
   * @see #getSubordinates()
   * @generated
   */
  void setSubordinates(List<String> value);

} // LinkType
