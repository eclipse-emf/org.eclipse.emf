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
 * A representation of the model object '<a href="http://www.w3.org/TR/xmlschema-2/#facets"><em><b>Fixed Facet</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDFixedFacet#isFixed <em>Fixed</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDFixedFacet()
 * @model abstract="true"
 * @generated
 */
public interface XSDFixedFacet extends XSDConstrainingFacet {
  /**
   * Returns the value of the '<em><b>Fixed</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * fractionDigitsFacet <a href="http://www.w3.org/TR/xmlschema-2/#fractionDigits-fixed">fixed</a>, 
   * lengthFacet <a href="http://www.w3.org/TR/xmlschema-2/#lengthFacet-fixed">fixed</a>, 
   * maxExclusiveFacet <a href="http://www.w3.org/TR/xmlschema-2/#maxExclusiveFacet-fixed">fixed</a>, 
   * maxInclusiveFacet <a href="http://www.w3.org/TR/xmlschema-2/#maxInclusive-fixed">fixed</a>, 
   * maxLengthFacet <a href="http://www.w3.org/TR/xmlschema-2/#maxLength-fixed">fixed</a>, 
   * minExclusiveFacet <a href="http://www.w3.org/TR/xmlschema-2/#minExclusiveFacet-fixed">fixed</a>, 
   * minInclusiveFacet <a href="http://www.w3.org/TR/xmlschema-2/#minInclusiveFacet-fixed">fixed</a>, 
   * minLengthFacet <a href="http://www.w3.org/TR/xmlschema-2/#minLengthFacet-fixed">fixed</a>, 
   * totalDigitsFacet <a href="http://www.w3.org/TR/xmlschema-2/#totalDigitsFacet-fixed">fixed</a>, 
   * whiteSpaceFacet <a href="http://www.w3.org/TR/xmlschema-2/#whiteSpaceFacet-fixed">fixed</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed</em>' attribute.
   * @see #isSetFixed()
   * @see #unsetFixed()
   * @see #setFixed(boolean)
   * @see org.eclipse.xsd.XSDPackage#getXSDFixedFacet_Fixed()
   * @model unsettable="true"
   * @generated
   */
  boolean isFixed();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFixedFacet#isFixed <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fixed</em>' attribute.
   * @see #isSetFixed()
   * @see #unsetFixed()
   * @see #isFixed()
   * @generated
   */
  void setFixed(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDFixedFacet#isFixed <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetFixed()
   * @see #isFixed()
   * @see #setFixed(boolean)
   * @generated
   */
  void unsetFixed();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDFixedFacet#isFixed <em>Fixed</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Fixed</em>' attribute is set.
   * @see #unsetFixed()
   * @see #isFixed()
   * @see #setFixed(boolean)
   * @generated
   */
  boolean isSetFixed();

} 
