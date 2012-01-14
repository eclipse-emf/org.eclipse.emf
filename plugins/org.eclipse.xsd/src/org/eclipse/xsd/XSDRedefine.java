/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#modify-schema"><em><b>Redefine</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDRedefine#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDRedefine#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDRedefine()
 * @model
 * @generated
 */
public interface XSDRedefine extends XSDSchemaCompositor
{
  /**
   * Returns the value of the '<em><b>Annotations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAnnotation}.
   * <!-- begin-user-doc -->
   * <p>
   * This reference represents the annotation contents defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-redefine">redefine</a> element.
   * It is computed from the {@link #getContents() contents} and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDRedefine_Annotations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAnnotation> getAnnotations();

  /**
   * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDRedefineContent}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the contents defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-redefine">redefine</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDRedefine_Contents()
   * @model containment="true"
   * @generated
   */
  EList<XSDRedefineContent> getContents();

} 
