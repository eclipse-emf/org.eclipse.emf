/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EEnum.java,v 1.4 2005/06/08 06:20:10 nickb Exp $
 */
package org.eclipse.emf.ecore;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EEnum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.EEnum#getELiterals <em>ELiterals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.EcorePackage#getEEnum()
 * @model
 * @generated
 */
public interface EEnum extends EDataType
{
  /**
   * Returns the value of the '<em><b>ELiterals</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EEnumLiteral}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.EEnumLiteral#getEEnum <em>EEnum</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the enumerators of the enumeration.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ELiterals</em>' containment reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEEnum_ELiterals()
   * @see org.eclipse.emf.ecore.EEnumLiteral#getEEnum
   * @model type="org.eclipse.emf.ecore.EEnumLiteral" opposite="eEnum" containment="true"
   * @generated
   */
  EList getELiterals();

  /**
   * <!-- begin-user-doc -->
   * Returns the enum literal with the given name, or <code>null</code>.
   * @param name name in question.
   * @return the enum literal with the given name, or <code>null</code>.
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  EEnumLiteral getEEnumLiteral(String name);

  /**
   * <!-- begin-user-doc -->
   * Returns the enum literal with the given value, or <code>null</code>.
   * The values may not be unique; it returns the first.
   * @param value value in question.
   * @return the enum literal with the given value, or <code>null</code>.
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  EEnumLiteral getEEnumLiteral(int value);

}
