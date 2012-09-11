/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XPackage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getImportDirectives <em>Import Directives</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getAnnotationDirectives <em>Annotation Directives</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getClassifiers <em>Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage()
 * @model
 * @generated
 */
public interface XPackage extends XNamedElement
{
  /**
   * Returns the value of the '<em><b>Import Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XImportDirective}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XImportDirective#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Import Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Import Directives</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_ImportDirectives()
   * @see org.eclipse.emf.ecore.xcore.XImportDirective#getPackage
   * @model opposite="package" containment="true"
   * @generated
   */
  EList<XImportDirective> getImportDirectives();

  /**
   * Returns the value of the '<em><b>Annotation Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XAnnotationDirective}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XAnnotationDirective#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation Directives</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_AnnotationDirectives()
   * @see org.eclipse.emf.ecore.xcore.XAnnotationDirective#getPackage
   * @model opposite="package" containment="true"
   * @generated
   */
  EList<XAnnotationDirective> getAnnotationDirectives();

  /**
   * Returns the value of the '<em><b>Classifiers</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XClassifier}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XClassifier#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Classifiers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classifiers</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_Classifiers()
   * @see org.eclipse.emf.ecore.xcore.XClassifier#getPackage
   * @model opposite="package" containment="true"
   * @generated
   */
  EList<XClassifier> getClassifiers();

} // XPackage
