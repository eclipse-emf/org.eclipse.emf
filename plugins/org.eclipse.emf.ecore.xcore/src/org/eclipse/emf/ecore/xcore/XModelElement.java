/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XModel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XModelElement#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXModelElement()
 * @model abstract="true"
 * @generated
 */
public interface XModelElement extends EObject
{
  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XAnnotation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XAnnotation#getModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXModelElement_Annotations()
   * @see org.eclipse.emf.ecore.xcore.XAnnotation#getModelElement
   * @model opposite="modelElement" containment="true"
   * @generated
   */
  EList<XAnnotation> getAnnotations();

  XAnnotation getAnnotation(String sourceURI);

} // XModelElement
