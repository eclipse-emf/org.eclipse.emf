/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.common.types.JvmTypeReference;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XClassifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XClassifier#getInstanceType <em>Instance Type</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XClassifier#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XClassifier#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXClassifier()
 * @model abstract="true"
 * @generated
 */
public interface XClassifier extends XNamedElement
{
  /**
   * Returns the value of the '<em><b>Instance Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Instance Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Instance Type</em>' containment reference.
   * @see #setInstanceType(JvmTypeReference)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXClassifier_InstanceType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getInstanceType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XClassifier#getInstanceType <em>Instance Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Instance Type</em>' containment reference.
   * @see #getInstanceType()
   * @generated
   */
  void setInstanceType(JvmTypeReference value);

  /**
   * Returns the value of the '<em><b>Package</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XPackage#getClassifiers <em>Classifiers</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Package</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Package</em>' container reference.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXClassifier_Package()
   * @see org.eclipse.emf.ecore.xcore.XPackage#getClassifiers
   * @model opposite="classifiers" changeable="false"
   * @generated
   */
  XPackage getPackage();

  /**
   * Returns the value of the '<em><b>Type Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XTypeParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Parameters</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXClassifier_TypeParameters()
   * @model containment="true"
   * @generated
   */
  EList<XTypeParameter> getTypeParameters();

} // XClassifier
