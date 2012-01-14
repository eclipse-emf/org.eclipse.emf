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
 * '<a href="http://www.w3.org/TR/xmlschema-2/#dc-ordered"><em><b>Ordered Facet</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDOrderedFacet#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDOrderedFacet()
 * @model
 * @generated
 */
public interface XSDOrderedFacet extends XSDFundamentalFacet
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDOrdered}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#ordered-value">value</a>
   * infoset property.
   * It is computed and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see org.eclipse.xsd.XSDOrdered
   * @see #setValue(XSDOrdered)
   * @see org.eclipse.xsd.XSDPackage#getXSDOrderedFacet_Value()
   * @model
   * @generated
   */
  XSDOrdered getValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDOrderedFacet#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see org.eclipse.xsd.XSDOrdered
   * @see #getValue()
   * @generated
   */
  void setValue(XSDOrdered value);

} 
