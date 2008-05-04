/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: MappingHelper.java,v 1.7 2008/05/04 17:03:16 emerks Exp $
 */
package org.eclipse.emf.mapping;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Helper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.MappingHelper#getMapper <em>Mapper</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.MappingHelper#getHelpedObject <em>Helped Object</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.MappingHelper#getNestedIn <em>Nested In</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.MappingHelper#getNested <em>Nested</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.mapping.MappingPackage#getMappingHelper()
 * @model
 * @generated
 */
public interface MappingHelper extends EObject
{
  /**
   * Returns the value of the '<em><b>Mapper</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.mapping.Mapping#getHelper <em>Helper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapper</em>' container reference.
   * @see #setMapper(Mapping)
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingHelper_Mapper()
   * @see org.eclipse.emf.mapping.Mapping#getHelper
   * @model opposite="helper"
   * @generated
   */
  Mapping getMapper();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.MappingHelper#getMapper <em>Mapper</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapper</em>' container reference.
   * @see #getMapper()
   * @generated
   */
  void setMapper(Mapping value);

  /**
   * Returns the value of the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Helped Object</em>' reference.
   * @see #setHelpedObject(EObject)
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingHelper_HelpedObject()
   * @model
   * @generated
   */
  EObject getHelpedObject();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.MappingHelper#getHelpedObject <em>Helped Object</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Helped Object</em>' reference.
   * @see #getHelpedObject()
   * @generated
   */
  void setHelpedObject(EObject value);

  /**
   * Returns the value of the '<em><b>Nested In</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.mapping.MappingHelper#getNested <em>Nested</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nested In</em>' container reference.
   * @see #setNestedIn(MappingHelper)
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingHelper_NestedIn()
   * @see org.eclipse.emf.mapping.MappingHelper#getNested
   * @model opposite="nested"
   * @generated
   */
  MappingHelper getNestedIn();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.MappingHelper#getNestedIn <em>Nested In</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nested In</em>' container reference.
   * @see #getNestedIn()
   * @generated
   */
  void setNestedIn(MappingHelper value);

  /**
   * Returns the value of the '<em><b>Nested</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.mapping.MappingHelper}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.mapping.MappingHelper#getNestedIn <em>Nested In</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nested</em>' containment reference list.
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingHelper_Nested()
   * @see org.eclipse.emf.mapping.MappingHelper#getNestedIn
   * @model opposite="nestedIn" containment="true"
   * @generated
   */
  EList<MappingHelper> getNested();

} // MappingHelper
