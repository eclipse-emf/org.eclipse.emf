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
 * '<a href="http://www.w3.org/TR/xmlschema-2/#dc-pattern"><em><b>Pattern Facet</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDPatternFacet#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDPatternFacet()
 * @model
 * @generated
 */
public interface XSDPatternFacet extends XSDRepeatableFacet
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#pattern-value">value</a>
   * infoset property.
   * It is computed from the '{@link #getLexicalValue() <em>Lexical Value</em>}' attribute and should typically not be set directly.
   * Each item in the list is a String representing a pattern.
   * The overall effect of the list of patterns is the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#src-multiple-patterns">logical intersection</a>.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute list.
   * @see org.eclipse.xsd.XSDPackage#getXSDPatternFacet_Value()
   * @model
   * @generated
   */
  EList<String> getValue();

}
