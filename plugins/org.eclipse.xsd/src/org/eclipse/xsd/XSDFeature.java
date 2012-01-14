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
 * A representation of the model object '<em><b>Feature</b></em>'.
 * It is used to represent aspects common to
 * '{@link org.eclipse.xsd.XSDElementDeclaration <em>Element Declarations</em>}' and
 * '{@link org.eclipse.xsd.XSDAttributeDeclaration <em>Attribute Declaration</em>}'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getForm <em>Form</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getLexicalValue <em>Lexical Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#isGlobal <em>Global</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#isFeatureReference <em>Feature Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getScope <em>Scope</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getResolvedFeature <em>Resolved Feature</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFeature#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDFeature()
 * @model abstract="true"
 * @generated
 */
public interface XSDFeature extends XSDNamedComponent
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the value of the
   * attribute <a href="http://www.w3.org/TR/xmlschema-1/#a-value_constraint">value constraint</a> or
   * element <a href="http://www.w3.org/TR/xmlschema-1/#e-value_constraint">value constraint</a>
   * infoset property.
   * It is computed from the {@link #getLexicalValue() lexical value} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(Object)
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_Value()
   * @model dataType="org.eclipse.xsd.Value"
   * @generated
   */
  Object getValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFeature#getValue <em>Value</em>}' attribute.
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
   * attribute <a href="http://www.w3.org/TR/xmlschema-1/#a-value_constraint">value constraint</a> or
   * element <a href="http://www.w3.org/TR/xmlschema-1/#e-value_constraint">value constraint</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraint</em>' attribute.
   * @see org.eclipse.xsd.XSDConstraint
   * @see #isSetConstraint()
   * @see #unsetConstraint()
   * @see #setConstraint(XSDConstraint)
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_Constraint()
   * @model unsettable="true"
   * @generated
   */
  XSDConstraint getConstraint();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFeature#getConstraint <em>Constraint</em>}' attribute.
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
   * Unsets the value of the '{@link org.eclipse.xsd.XSDFeature#getConstraint <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetConstraint()
   * @see #getConstraint()
   * @see #setConstraint(XSDConstraint)
   * @generated
   */
  void unsetConstraint();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDFeature#getConstraint <em>Constraint</em>}' attribute is set.
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
   * Returns the value of the '<em><b>Form</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDForm}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * attribute <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">form</a> attribute or the
   * element <a href="http://www.w3.org/TR/xmlschema-1/#element-element">form</a> attribute.
   * It, along with the 
   * {@link org.eclipse.xsd.XSDSchema#getAttributeFormDefault() attribute form default} and
   * {@link org.eclipse.xsd.XSDSchema#getElementFormDefault() element form default} of the schema,
   * affects the {@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace() target namespace} of locally scoped features.
   * </p>
   * @see org.eclipse.xsd.XSDSchema#getAttributeFormDefault()
   * @see org.eclipse.xsd.XSDSchema#getElementFormDefault()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Form</em>' attribute.
   * @see org.eclipse.xsd.XSDForm
   * @see #isSetForm()
   * @see #unsetForm()
   * @see #setForm(XSDForm)
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_Form()
   * @model unsettable="true"
   * @generated
   */
  XSDForm getForm();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFeature#getForm <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Form</em>' attribute.
   * @see org.eclipse.xsd.XSDForm
   * @see #isSetForm()
   * @see #unsetForm()
   * @see #getForm()
   * @generated
   */
  void setForm(XSDForm value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDFeature#getForm <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetForm()
   * @see #getForm()
   * @see #setForm(XSDForm)
   * @generated
   */
  void unsetForm();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDFeature#getForm <em>Form</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Form</em>' attribute is set.
   * @see #unsetForm()
   * @see #getForm()
   * @see #setForm(XSDForm)
   * @generated
   */
  boolean isSetForm();

  /**
   * Returns the value of the '<em><b>Lexical Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * attribute <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">fixed</a> or
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">default</a> attribute 
   * or the
   * element <a href="http://www.w3.org/TR/xmlschema-1/#element-element">fixed</a> or
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-element">default</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lexical Value</em>' attribute.
   * @see #setLexicalValue(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_LexicalValue()
   * @model
   * @generated
   */
  String getLexicalValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFeature#getLexicalValue <em>Lexical Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lexical Value</em>' attribute.
   * @see #getLexicalValue()
   * @generated
   */
  void setLexicalValue(String value);

  /**
   * Returns the value of the '<em><b>Global</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates whether the feature is globally {@link #getScope() scoped}.  
   * Its value is false if the feature is declared within 
   * a {@link org.eclipse.xsd.XSDComplexTypeDefinition complex type definition}, 
   * an {@link org.eclipse.xsd.XSDAttributeGroupDefinition attribute group definition},
   * or a {@link org.eclipse.xsd.XSDModelGroupDefinition model group definition}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Global</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_Global()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isGlobal();

  /**
   * Returns the value of the '<em><b>Feature Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This is the same result as either 
   * the '{@link org.eclipse.xsd.XSDElementDeclaration#isElementDeclarationReference() <em>Element Reference</em>}' attribute or
   * the '{@link org.eclipse.xsd.XSDAttributeDeclaration#isAttributeDeclarationReference() <em>Attribute Reference</em>}' attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Reference</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_FeatureReference()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isFeatureReference();

  /**
   * Returns the value of the '<em><b>Scope</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * attribute <a href="http://www.w3.org/TR/xmlschema-1/#a-scope">scope</a> or
   * element <a href="http://www.w3.org/TR/xmlschema-1/#e-scope">scope</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scope</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_Scope()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDScope getScope();

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This is the same result as either 
   * the element '{@link org.eclipse.xsd.XSDElementDeclaration#getTypeDefinition() <em>Type Definition</em>}' reference or
   * the attribute '{@link org.eclipse.xsd.XSDAttributeDeclaration#getTypeDefinition() <em>Type Definition</em>}' reference.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_Type()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDTypeDefinition getType();

  /**
   * Returns the value of the '<em><b>Resolved Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This is the same result as either 
   * the '{@link org.eclipse.xsd.XSDElementDeclaration#getResolvedElementDeclaration() <em>Resolved Element Declaration</em>}' reference or
   * the '{@link org.eclipse.xsd.XSDAttributeDeclaration#getResolvedAttributeDeclaration() <em>Resolved Attribute Declaration</em>}' reference.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resolved Feature</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDFeature_ResolvedFeature()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDFeature getResolvedFeature();

} 
