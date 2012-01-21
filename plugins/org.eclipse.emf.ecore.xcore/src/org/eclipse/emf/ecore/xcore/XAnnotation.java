/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.common.util.EMap;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XAnnotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XAnnotation#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XAnnotation#getDetails <em>Details</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XAnnotation#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXAnnotation()
 * @model
 * @generated
 */
public interface XAnnotation extends XModelElement
{
  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(XAnnotationDirective)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXAnnotation_Source()
   * @model
   * @generated
   */
  XAnnotationDirective getSource();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XAnnotation#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
  void setSource(XAnnotationDirective value);

  /**
   * Returns the value of the '<em><b>Details</b></em>' map.
   * The key is of type {@link java.lang.String},
   * and the value is of type {@link java.lang.String},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Details</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Details</em>' map.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXAnnotation_Details()
   * @model mapType="org.eclipse.emf.ecore.xcore.XStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
   * @generated
   */
  EMap<String, String> getDetails();

  /**
   * Returns the value of the '<em><b>Model Element</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XModelElement#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Element</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Element</em>' container reference.
   * @see #setModelElement(XModelElement)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXAnnotation_ModelElement()
   * @see org.eclipse.emf.ecore.xcore.XModelElement#getAnnotations
   * @model opposite="annotations" resolveProxies="false"
   * @generated
   */
  XModelElement getModelElement();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XAnnotation#getModelElement <em>Model Element</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Element</em>' container reference.
   * @see #getModelElement()
   * @generated
   */
  void setModelElement(XModelElement value);

} // XAnnotation
