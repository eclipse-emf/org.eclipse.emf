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
 * A representation of the model object '<a href="http://www.w3.org/TR/xmlschema-2/#facets"><em><b>Facet</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDFacet#getLexicalValue <em>Lexical Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFacet#getFacetName <em>Facet Name</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFacet#getEffectiveValue <em>Effective Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFacet#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDFacet#getSimpleTypeDefinition <em>Simple Type Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDFacet()
 * @model abstract="true"
 * @generated
 */
public interface XSDFacet extends XSDComponent
{
  /**
   * Returns the value of the '<em><b>Lexical Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the <code>value</code> attribute of the facet element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lexical Value</em>' attribute.
   * @see #setLexicalValue(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDFacet_LexicalValue()
   * @model
   * @generated
   */
  String getLexicalValue();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFacet#getLexicalValue <em>Lexical Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lexical Value</em>' attribute.
   * @see #getLexicalValue()
   * @generated
   */
  void setLexicalValue(String value);

  /**
   * Returns the value of the '<em><b>Facet Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the name of this type of facet.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Facet Name</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDFacet_FacetName()
   * @model changeable="false" volatile="true"
   * @generated
   */
  String getFacetName();

  /**
   * Returns the value of the '<em><b>Effective Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents a generic version of the <code>value</code> infoset property of this facet.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Value</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDFacet_EffectiveValue()
   * @model dataType="org.eclipse.xsd.Value" changeable="false" volatile="true"
   * @generated
   */
  Object getEffectiveValue();

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the annotation infoset property;
   * each type of facet has an annotation.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDFacet_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDFacet#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Simple Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link #getContainer() containing} simple type definition of the facet.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simple Type Definition</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDFacet_SimpleTypeDefinition()
   * @model resolveProxies="false" changeable="false" volatile="true"
   * @generated
   */
  XSDSimpleTypeDefinition getSimpleTypeDefinition();

}
