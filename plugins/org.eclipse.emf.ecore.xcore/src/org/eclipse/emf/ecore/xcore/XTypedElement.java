/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XTyped Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XTypedElement#isUnordered <em>Unordered</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XTypedElement#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XTypedElement#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XTypedElement#getMultiplicity <em>Multiplicity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXTypedElement()
 * @model abstract="true"
 * @generated
 */
public interface XTypedElement extends XNamedElement
{
  /**
   * Returns the value of the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unordered</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unordered</em>' attribute.
   * @see #setUnordered(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXTypedElement_Unordered()
   * @model
   * @generated
   */
  boolean isUnordered();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XTypedElement#isUnordered <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unordered</em>' attribute.
   * @see #isUnordered()
   * @generated
   */
  void setUnordered(boolean value);

  /**
   * Returns the value of the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unique</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unique</em>' attribute.
   * @see #setUnique(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXTypedElement_Unique()
   * @model
   * @generated
   */
  boolean isUnique();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XTypedElement#isUnique <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unique</em>' attribute.
   * @see #isUnique()
   * @generated
   */
  void setUnique(boolean value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(XGenericType)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXTypedElement_Type()
   * @model containment="true" unsettable="true" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  XGenericType getType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XTypedElement#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(XGenericType value);

  /**
   * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Multiplicity</em>' attribute.
   * @see #setMultiplicity(int[])
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXTypedElement_Multiplicity()
   * @model dataType="org.eclipse.emf.ecore.xcore.XMultiplicity"
   * @generated
   */
  int[] getMultiplicity();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XTypedElement#getMultiplicity <em>Multiplicity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Multiplicity</em>' attribute.
   * @see #getMultiplicity()
   * @generated
   */
  void setMultiplicity(int[] value);

} // XTypedElement
