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
 * A representation of the model object '<em><b>XAnnotation Directive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XAnnotationDirective#getSourceURI <em>Source URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXAnnotationDirective()
 * @model
 * @generated
 */
public interface XAnnotationDirective extends XNamedElement
{
  /**
   * Returns the value of the '<em><b>Source URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source URI</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source URI</em>' attribute.
   * @see #setSourceURI(String)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXAnnotationDirective_SourceURI()
   * @model
   * @generated
   */
  String getSourceURI();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XAnnotationDirective#getSourceURI <em>Source URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source URI</em>' attribute.
   * @see #getSourceURI()
   * @generated
   */
  void setSourceURI(String value);

} // XAnnotationDirective
