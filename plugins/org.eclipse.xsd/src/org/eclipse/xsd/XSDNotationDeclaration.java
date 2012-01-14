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
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cNotation_Declarations"><em><b>Notation Declaration</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDNotationDeclaration#getSystemIdentifier <em>System Identifier</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNotationDeclaration#getPublicIdentifier <em>Public Identifier</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNotationDeclaration#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDNotationDeclaration()
 * @model
 * @generated
 */
public interface XSDNotationDeclaration extends XSDNamedComponent, XSDSchemaContent
{
  /**
   * Returns the value of the '<em><b>System Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#system_identifier">system identifier</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>System Identifier</em>' attribute.
   * @see #setSystemIdentifier(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDNotationDeclaration_SystemIdentifier()
   * @model
   * @generated
   */
  String getSystemIdentifier();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDNotationDeclaration#getSystemIdentifier <em>System Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>System Identifier</em>' attribute.
   * @see #getSystemIdentifier()
   * @generated
   */
  void setSystemIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Public Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#public_identifier">public identifier</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Public Identifier</em>' attribute.
   * @see #setPublicIdentifier(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDNotationDeclaration_PublicIdentifier()
   * @model
   * @generated
   */
  String getPublicIdentifier();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDNotationDeclaration#getPublicIdentifier <em>Public Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Public Identifier</em>' attribute.
   * @see #getPublicIdentifier()
   * @generated
   */
  void setPublicIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#n-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDNotationDeclaration_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDNotationDeclaration#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

} 
