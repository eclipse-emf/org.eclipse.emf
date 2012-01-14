/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd;




/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#composition-schemaImport"><em><b>Import</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDImport#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDImport#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDImport()
 * @model
 * @generated
 */
public interface XSDImport extends XSDSchemaDirective
{
  /**
   * Returns the value of the '<em><b>Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-import">namespace</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Namespace</em>' attribute.
   * @see #setNamespace(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDImport_Namespace()
   * @model
   * @generated
   */
  String getNamespace();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDImport#getNamespace <em>Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Namespace</em>' attribute.
   * @see #getNamespace()
   * @generated
   */
  void setNamespace(String value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the annotation content of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-import">import</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDImport_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDImport#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

}
