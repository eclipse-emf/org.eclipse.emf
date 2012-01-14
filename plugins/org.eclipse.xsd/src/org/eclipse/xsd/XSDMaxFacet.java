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
 * A representation of the model object '<em><b>Max Facet</b></em>'.
 * It represents aspects common to 
 * '{@link org.eclipse.xsd.XSDMaxExclusiveFacet <em>Max Exclusive Facet</em>}' and
 * '{@link org.eclipse.xsd.XSDMaxInclusiveFacet <em>Max Inclusive Facet</em>}'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDMaxFacet#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDMaxFacet#isInclusive <em>Inclusive</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDMaxFacet#isExclusive <em>Exclusive</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDMaxFacet()
 * @model abstract="true"
 * @generated
 */
public interface XSDMaxFacet extends XSDFixedFacet {
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * maxExclusive <a href="http://www.w3.org/TR/xmlschema-2/#maxExclusive-value">value</a> or 
   * maxInclusive <a href="http://www.w3.org/TR/xmlschema-2/#maxInclusive-value">value</a>
   * infoset property.
   * It is computed from the '{@link #getLexicalValue() <em>Lexical Value</em>}' attribute and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(Object)
   * @see org.eclipse.xsd.XSDPackage#getXSDMaxFacet_Value()
   * @model dataType="org.eclipse.xsd.Value"
   * @generated
   */
  Object getValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDMaxFacet#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(Object value);

  /**
   * Returns the value of the '<em><b>Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * The value is <code>true</code> if this is a {@link org.eclipse.xsd.XSDMaxInclusiveFacet}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inclusive</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDMaxFacet_Inclusive()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isInclusive();

  /**
   * Returns the value of the '<em><b>Exclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * The value is <code>true</code> if this is a {@link org.eclipse.xsd.XSDMaxExclusiveFacet}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exclusive</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDMaxFacet_Exclusive()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isExclusive();

}
