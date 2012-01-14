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
 * '<a href="http://www.w3.org/TR/xmlschema-2/#dc-whiteSpace"><em><b>White Space Facet</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDWhiteSpaceFacet#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDWhiteSpaceFacet()
 * @model
 * @generated
 */
public interface XSDWhiteSpaceFacet extends XSDFixedFacet
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDWhiteSpace}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#whiteSpace-value">value</a>
   * infoset property.
   * It is computed from the '{@link #getLexicalValue() <em>Lexical Value</em>}' attribute and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see org.eclipse.xsd.XSDWhiteSpace
   * @see #setValue(XSDWhiteSpace)
   * @see org.eclipse.xsd.XSDPackage#getXSDWhiteSpaceFacet_Value()
   * @model
   * @generated
   */
  XSDWhiteSpace getValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDWhiteSpaceFacet#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see org.eclipse.xsd.XSDWhiteSpace
   * @see #getValue()
   * @generated
   */
  void setValue(XSDWhiteSpace value);

  /**
   * This does any 
   */
  String getNormalizedLiteral(String literal);
} 
