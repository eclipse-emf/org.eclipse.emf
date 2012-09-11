/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XGeneric Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XGenericType#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XGenericType#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XGenericType#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XGenericType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXGenericType()
 * @model
 * @generated
 */
public interface XGenericType extends EObject
{
  /**
   * Returns the value of the '<em><b>Upper Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Bound</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Bound</em>' containment reference.
   * @see #setUpperBound(XGenericType)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXGenericType_UpperBound()
   * @model containment="true"
   * @generated
   */
  XGenericType getUpperBound();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XGenericType#getUpperBound <em>Upper Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Bound</em>' containment reference.
   * @see #getUpperBound()
   * @generated
   */
  void setUpperBound(XGenericType value);

  /**
   * Returns the value of the '<em><b>Type Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XGenericType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Arguments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Arguments</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXGenericType_TypeArguments()
   * @model containment="true"
   * @generated
   */
  EList<XGenericType> getTypeArguments();

  /**
   * Returns the value of the '<em><b>Lower Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Bound</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Bound</em>' containment reference.
   * @see #setLowerBound(XGenericType)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXGenericType_LowerBound()
   * @model containment="true"
   * @generated
   */
  XGenericType getLowerBound();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XGenericType#getLowerBound <em>Lower Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Bound</em>' containment reference.
   * @see #getLowerBound()
   * @generated
   */
  void setLowerBound(XGenericType value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(GenBase)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXGenericType_Type()
   * @model
   * @generated
   */
  GenBase getType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XGenericType#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(GenBase value);

} // XGenericType
