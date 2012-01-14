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
 * A representation of the model object '<em><b>XPath Definition</b></em>'.
 * It represents a 
 * <a href="http://www.w3.org/TR/xmlschema-1/#element-field">field</a> or 
 * <a href="http://www.w3.org/TR/xmlschema-1/#element-selector">selector</a> of an
 * <a href="http://www.w3.org/TR/xmlschema-1/#cIdentity-constraint_Definitions">Identity-constraint Definition</a>.
 * It defines a 
 * <a href="http://www.w3.org/TR/xmlschema-1/#coss-identity-constraint">restricted</a>
 * <a href="http://www.w3.org/TR/xmlschema-1/#bib-xpath">XPath</a>.
 * It is used to represent the types of object returned by 
 * the '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getFields() <em>Fields</em>}' reference list and
 * the '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getSelector() <em>Selector</em>}' reference.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDXPathDefinition#getVariety <em>Variety</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDXPathDefinition#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDXPathDefinition#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDXPathDefinition()
 * @model
 * @generated
 */
public interface XSDXPathDefinition extends XSDComponent
{
  /**
   * Returns the value of the '<em><b>Variety</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDXPathVariety}.
   * <!-- begin-user-doc -->
   * <p>
   * This attribute represents whether this is 
   * a <a href="http://www.w3.org/TR/xmlschema-1/#element-field">field</a> or 
   * a <a href="http://www.w3.org/TR/xmlschema-1/#element-selector">selector</a>.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variety</em>' attribute.
   * @see org.eclipse.xsd.XSDXPathVariety
   * @see #setVariety(XSDXPathVariety)
   * @see org.eclipse.xsd.XSDPackage#getXSDXPathDefinition_Variety()
   * @model
   * @generated
   */
  XSDXPathVariety getVariety();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDXPathDefinition#getVariety <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variety</em>' attribute.
   * @see org.eclipse.xsd.XSDXPathVariety
   * @see #getVariety()
   * @generated
   */
  void setVariety(XSDXPathVariety value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * selector <a href="http://www.w3.org/TR/xmlschema-1/#element-selector">xpath<a> or
   * field <a href="http://www.w3.org/TR/xmlschema-1/#element-selector">xpath<a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDXPathDefinition_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDXPathDefinition#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the annotation contents defined within the body of a
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-field">field</a> or 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-selector">selector</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDXPathDefinition_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDXPathDefinition#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

}
