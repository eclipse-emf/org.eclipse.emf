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
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cAttributeUse"><em><b>Attribute Use</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#getUse <em>Use</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#getLexicalValue <em>Lexical Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#getAttributeDeclaration <em>Attribute Declaration</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeUse#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse()
 * @model
 * @generated
 */
public interface XSDAttributeUse extends XSDComponent, XSDAttributeGroupContent
{
  /**
   * Returns the value of the '<em><b>Required</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#required">required</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Required</em>' attribute.
   * @see #setRequired(boolean)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_Required()
   * @model
   * @generated
   */
  boolean isRequired();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#isRequired <em>Required</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Required</em>' attribute.
   * @see #isRequired()
   * @generated
   */
  void setRequired(boolean value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the value of the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#au-value_constraint">value constraint</a>
   * infoset property.
   * It is computed from the {@link #getLexicalValue() lexical value} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(Object)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_Value()
   * @model dataType="org.eclipse.xsd.Value"
   * @generated
   */
  Object getValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(Object value);

  /**
   * Returns the value of the '<em><b>Constraint</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDConstraint}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the constraint of the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#au-value_constraint">value constraint</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraint</em>' attribute.
   * @see org.eclipse.xsd.XSDConstraint
   * @see #isSetConstraint()
   * @see #unsetConstraint()
   * @see #setConstraint(XSDConstraint)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_Constraint()
   * @model unsettable="true"
   * @generated
   */
  XSDConstraint getConstraint();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getConstraint <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constraint</em>' attribute.
   * @see org.eclipse.xsd.XSDConstraint
   * @see #isSetConstraint()
   * @see #unsetConstraint()
   * @see #getConstraint()
   * @generated
   */
  void setConstraint(XSDConstraint value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getConstraint <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetConstraint()
   * @see #getConstraint()
   * @see #setConstraint(XSDConstraint)
   * @generated
   */
  void unsetConstraint();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getConstraint <em>Constraint</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Constraint</em>' attribute is set.
   * @see #unsetConstraint()
   * @see #getConstraint()
   * @see #setConstraint(XSDConstraint)
   * @generated
   */
  boolean isSetConstraint();

  /**
   * Returns the value of the '<em><b>Use</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDAttributeUseCategory}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">use</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Use</em>' attribute.
   * @see org.eclipse.xsd.XSDAttributeUseCategory
   * @see #isSetUse()
   * @see #unsetUse()
   * @see #setUse(XSDAttributeUseCategory)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_Use()
   * @model unsettable="true"
   * @generated
   */
  XSDAttributeUseCategory getUse();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getUse <em>Use</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Use</em>' attribute.
   * @see org.eclipse.xsd.XSDAttributeUseCategory
   * @see #isSetUse()
   * @see #unsetUse()
   * @see #getUse()
   * @generated
   */
  void setUse(XSDAttributeUseCategory value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getUse <em>Use</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetUse()
   * @see #getUse()
   * @see #setUse(XSDAttributeUseCategory)
   * @generated
   */
  void unsetUse();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getUse <em>Use</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Use</em>' attribute is set.
   * @see #unsetUse()
   * @see #getUse()
   * @see #setUse(XSDAttributeUseCategory)
   * @generated
   */
  boolean isSetUse();

  /**
   * Returns the value of the '<em><b>Lexical Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">default</a> or 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">fixed</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lexical Value</em>' attribute.
   * @see #setLexicalValue(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_LexicalValue()
   * @model
   * @generated
   */
  String getLexicalValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getLexicalValue <em>Lexical Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lexical Value</em>' attribute.
   * @see #getLexicalValue()
   * @generated
   */
  void setLexicalValue(String value);

  /**
   * Returns the value of the '<em><b>Attribute Declaration</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#attribute">attribute</a>
   * infoset property.
   * It is computed from the {@link #getContent() content} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Declaration</em>' reference.
   * @see #setAttributeDeclaration(XSDAttributeDeclaration)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_AttributeDeclaration()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDAttributeDeclaration getAttributeDeclaration();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getAttributeDeclaration <em>Attribute Declaration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute Declaration</em>' reference.
   * @see #getAttributeDeclaration()
   * @generated
   */
  void setAttributeDeclaration(XSDAttributeDeclaration value);

  /**
   * Returns the value of the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the underlying concrete 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">attribute</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference.
   * @see #setContent(XSDAttributeDeclaration)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUse_Content()
   * @model containment="true" required="true"
   * @generated
   */
  XSDAttributeDeclaration getContent();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeUse#getContent <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content</em>' containment reference.
   * @see #getContent()
   * @generated
   */
  void setContent(XSDAttributeDeclaration value);

}
