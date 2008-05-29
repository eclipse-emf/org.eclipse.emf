/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDSimpleTypeDefinition.java,v 1.11 2008/05/29 14:56:36 marcelop Exp $
 */
package org.eclipse.xsd;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-2/#dc-defn"><em><b>Simple Type Definition</b></em></a>'.
 * For the methods named getEffective<b>Xyz</b>Facet, 
 * <em>effective</em> means that the value of the property 
 * is computed based on the direct facets of this type, or, 
 * if the facet is not present, 
 * is computed recursively from the {@link #getBaseTypeDefinition base type}.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getVariety <em>Variety</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal <em>Lexical Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getValidFacets <em>Valid Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getFacetContents <em>Facet Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getFacets <em>Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMemberTypeDefinitions <em>Member Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getFundamentalFacets <em>Fundamental Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getBaseTypeDefinition <em>Base Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getPrimitiveTypeDefinition <em>Primitive Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getItemTypeDefinition <em>Item Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getRootTypeDefinition <em>Root Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMinFacet <em>Min Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMaxFacet <em>Max Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMaxInclusiveFacet <em>Max Inclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMinInclusiveFacet <em>Min Inclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMinExclusiveFacet <em>Min Exclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMaxExclusiveFacet <em>Max Exclusive Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getLengthFacet <em>Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getWhiteSpaceFacet <em>White Space Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEnumerationFacets <em>Enumeration Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getPatternFacets <em>Pattern Facets</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getCardinalityFacet <em>Cardinality Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getNumericFacet <em>Numeric Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMaxLengthFacet <em>Max Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getMinLengthFacet <em>Min Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getTotalDigitsFacet <em>Total Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getFractionDigitsFacet <em>Fraction Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getOrderedFacet <em>Ordered Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getBoundedFacet <em>Bounded Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveMaxFacet <em>Effective Max Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveWhiteSpaceFacet <em>Effective White Space Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveMaxLengthFacet <em>Effective Max Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveFractionDigitsFacet <em>Effective Fraction Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectivePatternFacet <em>Effective Pattern Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveEnumerationFacet <em>Effective Enumeration Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveTotalDigitsFacet <em>Effective Total Digits Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveMinLengthFacet <em>Effective Min Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveLengthFacet <em>Effective Length Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveMinFacet <em>Effective Min Facet</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getSyntheticFacets <em>Synthetic Facets</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition()
 * @model
 * @generated
 */
public interface XSDSimpleTypeDefinition extends XSDTypeDefinition, XSDComplexTypeContent
{
  /**
   * Returns the value of the '<em><b>Variety</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDVariety}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-variety">variety</a>
   * infoset property.
   * It is computed based on the presence or absence of 
   * an {@link #getItemTypeDefinition item type} 
   * or of {@link #getMemberTypeDefinitions member types},
   * and should not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variety</em>' attribute.
   * @see org.eclipse.xsd.XSDVariety
   * @see #isSetVariety()
   * @see #unsetVariety()
   * @see #setVariety(XSDVariety)
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_Variety()
   * @model unsettable="true"
   * @generated
   */
  XSDVariety getVariety();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getVariety <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variety</em>' attribute.
   * @see org.eclipse.xsd.XSDVariety
   * @see #isSetVariety()
   * @see #unsetVariety()
   * @see #getVariety()
   * @generated
   */
  void setVariety(XSDVariety value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getVariety <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetVariety()
   * @see #getVariety()
   * @see #setVariety(XSDVariety)
   * @generated
   */
  void unsetVariety();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getVariety <em>Variety</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Variety</em>' attribute is set.
   * @see #unsetVariety()
   * @see #getVariety()
   * @see #setVariety(XSDVariety)
   * @generated
   */
  boolean isSetVariety();

  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSimpleFinal}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDSimpleFinal}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-final">final</a>
   * infoset property.
   * It is computed from the {@link #getLexicalFinal lexical final} and should not typically be modified directly.
   * </p>
   * @see #getStringFinal()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute list.
   * @see org.eclipse.xsd.XSDSimpleFinal
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_Final()
   * @model
   * @generated
   */
  EList<XSDSimpleFinal> getFinal();

  /**
   * Returns the String value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getFinal <em>Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Final</em>' attribute list.
   * @see #getFinal()
   */
  String getStringFinal();

  /**
   * Returns the value of the '<em><b>Lexical Final</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSimpleFinal}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDSimpleFinal}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute list represents the value of the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#element-simpleType">final</a> attribute.
   * </p>
   * @see #getStringLexicalFinal()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Lexical Final</em>' attribute list.
   * @see org.eclipse.xsd.XSDSimpleFinal
   * @see #isSetLexicalFinal()
   * @see #unsetLexicalFinal()
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_LexicalFinal()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDSimpleFinal> getLexicalFinal();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetLexicalFinal()
   * @see #getLexicalFinal()
   * @generated
   */
  void unsetLexicalFinal();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Lexical Final</em>' attribute list is set.
   * @see #unsetLexicalFinal()
   * @see #getLexicalFinal()
   * @generated
   */
  boolean isSetLexicalFinal();

  /**
   * Returns the String value of the
   * '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Lexical Final</em>' attribute list.
   * @see #getLexicalFinal()
   * @see #setStringLexicalFinal(java.lang.String)
   */
  String getStringLexicalFinal();

  /**
   * Sets the String value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param lexicalFinal the new value of the '<em>Lexical Final</em>' attribute.
   * @see #getLexicalFinal()
   * @see #getStringLexicalFinal()
   */
  void setStringLexicalFinal(String lexicalFinal);

  /**
   * Returns the value of the '<em><b>Valid Facets</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * This computed attribute list represents the
   * {@link org.eclipse.xsd.XSDFacet#getFacetName facet name} 
   * of each type of facet that is valid for this simple type definition.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Valid Facets</em>' attribute list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_ValidFacets()
   * @model
   * @generated
   */
  EList<String> getValidFacets();

  /**
   * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSimpleTypeDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference list represents the anonymous simple type definition content of a
   * <a href="http://www.w3.org/TR/xmlschema-2/#element-restriction">restriction</a>,
   * <a href="http://www.w3.org/TR/xmlschema-2/#element-list">list</a>, or
   * <a href="http://www.w3.org/TR/xmlschema-2/#element-union">union</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_Contents()
   * @model containment="true"
   * @generated
   */
  EList<XSDSimpleTypeDefinition> getContents();

  /**
   * Returns the value of the '<em><b>Facet Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDConstrainingFacet}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference list represents the facet contents of a
   * <a href="http://www.w3.org/TR/xmlschema-2/#element-restriction">restriction</a>.
   * There are convenience methods named get<b>Xyz</b>Facet that provide direct access to the individual facets.
   * There are, however, <b>no</b> methods named setXyzFacet;
   * one should add facets to this list or remove facets from this list in order to effect change.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Facet Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_FacetContents()
   * @model containment="true"
   * @generated
   */
  EList<XSDConstrainingFacet> getFacetContents();

  /**
   * Returns the value of the '<em><b>Facets</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDConstrainingFacet}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-facets">facets</a>
   * infoset property.
   * It is computed from the {@link #getFacetContents() facet contents} and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Facets</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_Facets()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDConstrainingFacet> getFacets();

  /**
   * Returns the value of the '<em><b>Member Type Definitions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSimpleTypeDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-memberTypes">member type definitions</a>
   * infoset property.
   * When constructing a union type, 
   * each <b>anonymous</b> member type should be added to both this list and to the contents list:
   *<pre>
   *  xsdSimpleTypeDefinition.getMemberTypeDefinitions().add(anonymousMemberType);
   *  xsdSimpleTypeDefinition.{@link #getContents}().add(anonymousMemberType);
   * </li>
   * The {@link #getVariety() variety} is determined automatically by the presence of member type definitions.
   *</pre>
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Member Type Definitions</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MemberTypeDefinitions()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDSimpleTypeDefinition> getMemberTypeDefinitions();

  /**
   * Returns the value of the '<em><b>Fundamental Facets</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDFundamentalFacet}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-fund-facets">fundamental facets</a>
   * infoset property.
   * It is computed and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fundamental Facets</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_FundamentalFacets()
   * @model containment="true" required="true" transient="true"
   * @generated
   */
  EList<XSDFundamentalFacet> getFundamentalFacets();

  /**
   * Returns the value of the '<em><b>Base Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-basetype">base type definition</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Type Definition</em>' reference.
   * @see #setBaseTypeDefinition(XSDSimpleTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_BaseTypeDefinition()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDSimpleTypeDefinition getBaseTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getBaseTypeDefinition <em>Base Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base Type Definition</em>' reference.
   * @see #getBaseTypeDefinition()
   * @generated
   */
  void setBaseTypeDefinition(XSDSimpleTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Primitive Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-primitive">primitive type definition</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primitive Type Definition</em>' reference.
   * @see #setPrimitiveTypeDefinition(XSDSimpleTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_PrimitiveTypeDefinition()
   * @model resolveProxies="false"
   * @generated
   */
  XSDSimpleTypeDefinition getPrimitiveTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getPrimitiveTypeDefinition <em>Primitive Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Primitive Type Definition</em>' reference.
   * @see #getPrimitiveTypeDefinition()
   * @generated
   */
  void setPrimitiveTypeDefinition(XSDSimpleTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Item Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-itemType">item type definition</a>
   * infoset property.
   * When constructing a list type, 
   * an <b>anonymous</b> item type should be both set using this method and added to the contents list:
   *<pre>
   *  xsdSimpleTypeDefinition.setItemTypeDefinition().add(itemType);
   *  xsdSimpleTypeDefinition.{@link #getContents}().add(itemType);
   *</pre>
   * The {@link #getVariety() variety} is determined automatically by the presence of an item type definition.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item Type Definition</em>' reference.
   * @see #setItemTypeDefinition(XSDSimpleTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_ItemTypeDefinition()
   * @model resolveProxies="false"
   * @generated
   */
  XSDSimpleTypeDefinition getItemTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getItemTypeDefinition <em>Item Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Item Type Definition</em>' reference.
   * @see #getItemTypeDefinition()
   * @generated
   */
  void setItemTypeDefinition(XSDSimpleTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Root Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This walks the {@link #getBaseTypeDefinition() base type definitions}
   * until it hits that one that has the ur-type definition as it's base type definition.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Type Definition</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_RootTypeDefinition()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDSimpleTypeDefinition getRootTypeDefinition();

  /**
   * Returns the value of the '<em><b>Min Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMinFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MinFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMinFacet getMinFacet();

  /**
   * Returns the value of the '<em><b>Max Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMaxFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MaxFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMaxFacet getMaxFacet();

  /**
   * Returns the value of the '<em><b>Max Inclusive Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMaxInclusiveFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Inclusive Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MaxInclusiveFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMaxInclusiveFacet getMaxInclusiveFacet();

  /**
   * Returns the value of the '<em><b>Min Inclusive Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMinInclusiveFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Inclusive Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MinInclusiveFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMinInclusiveFacet getMinInclusiveFacet();

  /**
   * Returns the value of the '<em><b>Min Exclusive Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMinExclusiveFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Exclusive Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MinExclusiveFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMinExclusiveFacet getMinExclusiveFacet();

  /**
   * Returns the value of the '<em><b>Max Exclusive Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMaxExclusiveFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Exclusive Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MaxExclusiveFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMaxExclusiveFacet getMaxExclusiveFacet();

  /**
   * Returns the value of the '<em><b>Length Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDLengthFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Length Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_LengthFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDLengthFacet getLengthFacet();

  /**
   * Returns the value of the '<em><b>White Space Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDWhiteSpaceFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>White Space Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_WhiteSpaceFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDWhiteSpaceFacet getWhiteSpaceFacet();

  /**
   * Returns the value of the '<em><b>Enumeration Facets</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDEnumerationFacet}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDEnumerationFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Enumeration Facets</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EnumerationFacets()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  EList<XSDEnumerationFacet> getEnumerationFacets();

  /**
   * Returns the value of the '<em><b>Pattern Facets</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDPatternFacet}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDPatternFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern Facets</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_PatternFacets()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  EList<XSDPatternFacet> getPatternFacets();

  /**
   * Returns the value of the '<em><b>Cardinality Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDCardinalityFacet} of the {@link #getFundamentalFacets fundamental facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cardinality Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_CardinalityFacet()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDCardinalityFacet getCardinalityFacet();

  /**
   * Returns the value of the '<em><b>Numeric Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDNumericFacet} of the {@link #getFundamentalFacets fundamental facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Numeric Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_NumericFacet()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDNumericFacet getNumericFacet();

  /**
   * Returns the value of the '<em><b>Max Length Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMaxLengthFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Length Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MaxLengthFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMaxLengthFacet getMaxLengthFacet();

  /**
   * Returns the value of the '<em><b>Min Length Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMinLengthFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Length Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_MinLengthFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMinLengthFacet getMinLengthFacet();

  /**
   * Returns the value of the '<em><b>Total Digits Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDTotalDigitsFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Total Digits Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_TotalDigitsFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDTotalDigitsFacet getTotalDigitsFacet();

  /**
   * Returns the value of the '<em><b>Fraction Digits Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDFractionDigitsFacet} of the {@link #getFacetContents facet contents}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fraction Digits Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_FractionDigitsFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDFractionDigitsFacet getFractionDigitsFacet();

  /**
   * Returns the value of the '<em><b>Ordered Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDOrderedFacet} of the {@link #getFundamentalFacets fundamental facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ordered Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_OrderedFacet()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDOrderedFacet getOrderedFacet();

  /**
   * Returns the value of the '<em><b>Bounded Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDBoundedFacet} of the {@link #getFundamentalFacets fundamental facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounded Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_BoundedFacet()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDBoundedFacet getBoundedFacet();

  /**
   * Returns the value of the '<em><b>Effective Max Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMaxFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Max Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveMaxFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMaxFacet getEffectiveMaxFacet();

  /**
   * Returns the value of the '<em><b>Effective White Space Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDWhiteSpaceFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective White Space Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveWhiteSpaceFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDWhiteSpaceFacet getEffectiveWhiteSpaceFacet();

  /**
   * Returns the value of the '<em><b>Effective Max Length Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMaxLengthFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Max Length Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveMaxLengthFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMaxLengthFacet getEffectiveMaxLengthFacet();

  /**
   * Returns the value of the '<em><b>Effective Fraction Digits Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDFractionDigitsFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Fraction Digits Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveFractionDigitsFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDFractionDigitsFacet getEffectiveFractionDigitsFacet();

  /**
   * Returns the value of the '<em><b>Effective Pattern Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDPatternFacet} of the {@link #getFacets facets}.
   * To iterate through each pattern that applies for this type, 
   * use the list returned by getEffectivePatternFacet().{@link XSDPatternFacet#getValue getValue}().
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Pattern Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectivePatternFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDPatternFacet getEffectivePatternFacet();

  /**
   * Returns the value of the '<em><b>Effective Enumeration Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDEnumerationFacet} of the {@link #getFacets facets}.
   * To iterate through each enumerator which is a legal value of this type,
   * use the list returned by getEffectiveEnumerationFacet().{@link XSDEnumerationFacet#getValue getValue}().
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Enumeration Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveEnumerationFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDEnumerationFacet getEffectiveEnumerationFacet();

  /**
   * Returns the value of the '<em><b>Effective Total Digits Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDTotalDigitsFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Total Digits Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveTotalDigitsFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDTotalDigitsFacet getEffectiveTotalDigitsFacet();

  /**
   * Returns the value of the '<em><b>Effective Min Length Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMinLengthFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Min Length Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveMinLengthFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMinLengthFacet getEffectiveMinLengthFacet();

  /**
   * Returns the value of the '<em><b>Effective Length Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDLengthFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Length Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveLengthFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDLengthFacet getEffectiveLengthFacet();

  /**
   * Returns the value of the '<em><b>Effective Min Facet</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the {@link org.eclipse.xsd.XSDMinLengthFacet} of the {@link #getFacets facets}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effective Min Facet</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_EffectiveMinFacet()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDMinFacet getEffectiveMinFacet();

  /**
   * Returns the value of the '<em><b>Synthetic Facets</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDFacet}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-2/#defn-facets">facets</a>
   * infoset property, if the rules require a synthesized facet.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synthetic Facets</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSimpleTypeDefinition_SyntheticFacets()
   * @model containment="true" transient="true"
   * @generated
   */
  EList<XSDFacet> getSyntheticFacets();

  /**
   * Returns an assessment of the node.
   * @return an assessment of the node.
   * @see #assess(Element, Node)
   */
  Assessment assess(Node node);

  /**
   * Returns an assessment of the node.
   * @return an assessment of the node.
   * @see #assess(Element, String)
   * @since 2.4
   */
  Assessment assess(Element context, Node node);

  /**
   * Returns an assessment of the literal.
   * @return an assessment of the literal.
   * @see #assess(Element, String)
   */
  Assessment assess(String literal);

  /**
   * Returns an assessment of the literal.
   * @return an assessment of the literal.
   * @see #assess(Element, Node)
   * @since 2.4
   */
  Assessment assess(Element context, String literal);

  /**
   * Information gathered during the assessment a literal 
   * with respect to a '{@link org.eclipse.xsd.XSDSimpleTypeDefinition <em>Simple Type Definition</em>}'.
   * <p>
   * You may begin assessment with a {@link #getNode Node}, which handles three cases:
   * if the node is an attribute, it's value is used to determine the literal;
   * if the node is a text node, it's data is used to determine the literal;
   * if the node is an element, it's (one and only) child text node is used to determine the literal as in the preceeding case.
   * You may also begin assessment with just a {@link #getLiteral literal}; 
   * in this case, the returned diagnostics will contain substitution variables.
   * </p>
   */
  public interface Assessment 
  {
    /**
     * Returns the type definition against which the literal is assessed.
     * @return the type definition against which the literal is assessed.
     */
    XSDSimpleTypeDefinition getTypeDefinition();

    /**
     * Returns the element used as the context for this assessment; this is significant only for interpretting the prefix of QNames.
     * @return the element used as the context for this assessment.
     * @since 2.4
     */
    Element getContext();

    /**
     * Returns the node whose literal value is assessed.
     * @return the node whose literal value is assessed.
     */
    Node getNode();

    /**
     * Returns the literal that is assessed.
     * @return the literal that is assessed.
     */
    String getLiteral();

    /**
     * Returns the normalized value of the literal that is assessed.
     * @return the normalized value of the literal that is assessed.
     */
    String getNormalizedLiteral();

    /**
     * Return the <b>value</b> of the normalized literal in the Java representation of the value space.
     * @return the <b>value</b> of the normalized literal.
     */
    Object getValue();

    /**
     * Returns the canonical literal representation of the assessed value.
     * @return the canonical literal representation of the assessed value.
     */
    String getCanonicalLiteral();

    /**
     * Returns the diagnostics collected for this particular assessment and for any nested assessments.
     * @return the diagnostics collected for this particular assessment and for any nested assessments.
     */
    Collection<XSDDiagnostic> getDiagnostics();

    /**
     * Returns any nested assessments that were performed.
     * In the case of a union, there will be an assessment for the member type definition that was chosen to assess the literal.
     * In the case of a list, there will be an assessment for every item in the list.
     * @return any nested assessments that were performed.
     */
    Collection<Assessment> getAssessments();

    /**
     * Returns the diagnostics that are collected for just this particular assessment.
     * @return the diagnostics that are collected for just this particular assessment.
     */
    Collection<XSDDiagnostic> getLocalDiagnostics();

    /**
     * Called with a noun and a proper noun 
     * to perform substitution on the diagnostic messages, 
     * e.g., format("employee", "John Doe").
     * If an element or attribute was originally specified, 
     * the word "element" or "attribute" and the URI of the element or attribute 
     * will already have been substituted.
     * @param noun a noun.
     * @param name a proper noun.
     */
    void format(String noun, String name);
  }

  /**
   * Returns whether the literal is valid with respect to this simple type definition.
   * This involves {@link #assess(java.lang.String) assess}ing that the literal has no diagnostics.
   * @param literal a literal.
   * @return whether the literal is valid.
   * @see #isValidLiteral(Element, String)
   */
  boolean isValidLiteral(String literal);

  /**
   * Returns whether the literal is valid with respect to this simple type definition.
   * This involves {@link #assess(java.lang.String) assess}ing that the literal has no diagnostics.
   * @param context the context in which to resolve prefixed of QNames.
   * @param literal a literal.
   * @return whether the literal is valid.
   * @since 2.4
   */
  boolean isValidLiteral(Element context, String literal);

  /**
   * Returns the <b>value</b> of the literal in the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#value-space">value space</a> of this simple type definition.
   * @param literal a literal.
   * @return the <b>value</b> of the literal.
   * @see #getValue(Element, String)
   */
  Object getValue(String literal);

  /**
   * Returns the <b>value</b> of the literal in the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#value-space">value space</a> of this simple type definition.
   * The type of value expected depends the primitive type:
   * <p>
   * <table border=true cols=2 width='90%'>
   * <tr>
   * <th width=33% valign=top align=left><b>Built-in Primitive Datatype</b></th>
   * <th width=33% valign=top align=left><b>Corresponding&nbsp;Java&nbsp;Type</b></th>
   * </tr>
   *
   * <tr>
   * <td><em>anyListType</em></td>
   * <td>java.util.List</td>
   * </tr>
   *
   * <tr>
   * <td>anyURI</td>
   * <td>java.lang.String</td>
   * </tr>
   *
   * <tr>
   * <td>base64Binary</td>
   * <td>org.eclipse.xsd.util.XSDUtil.ByteSequence</td>
   * </tr>
   *
   * <td>boolean</td>
   * <td>java.lang.Boolean</td>
   * </tr>
   *
   * <td>dateTime</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>date</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>decimal</td>
   * <td>java.math.BigDecimal</td>
   * </tr>
   *
   * <td>double</td>
   * <td>java.lang.Double</td>
   * </tr>
   *
   * <td>duration</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>float</td>
   * <td>java.lang.Float</td>
   * </tr>
   *
   * <td>gDay</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>gMonth</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>gMonthDay</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>gYear</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>gYearMonth</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * <td>hexBinary</td>
   * <td>org.eclipse.xsd.util.XSDUtil.ByteSequence</td>
   * </tr>
   *
   * <td>NOTATION</td>
   * <td>java.lang.String</td>
   * </tr>
   *
   * <td>QName</td>
   * <td>java.lang.String</td>
   * </tr>
   *
   * <td>string</td>
   * <td>java.lang.String</td>
   * </tr>
   *
   * <td>time</td>
   * <td>int&nbsp;[]</td>
   * </tr>
   *
   * </table>
   * </p>
   * <p>
   * All time-based values are represented as in Xerces.
   * </p>
   * @param context the context in which to resolve prefixes of QNames.
   * @param literal a literal.
   * @return the <b>value</b> of the literal.
   * @since 2.4
   */
  Object getValue(Element context, String literal);

  /**
   * Returns the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#dt-whiteSpace">normalized value</a> of the literal 
   * with respect to this simple type definition.
   * @param literal a literal.
   * @return the <b>normalized value</b> of the literal.
   */
  String getNormalizedLiteral(String literal);

  /**
   * Returns the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#dt-canonical-representation">canonical literal</a> of the literal 
   * with respect to this simple type definition.
   * @param literal a literal.
   * @return the <b>canonical literal</b> of the literal.
   */
  String getCanonicalLiteral(String literal);

  /**
   * Returns <code>-1</code>, <code>0</code>, or <code>1</code>, 
   * depending on the {@link #getOrderedFacet order} of the {@link #getValue value} of each of the given literals.
   * @param literal1 a literal.
   * @param literal2 another literal.
   * @return <code>-1</code>, <code>0</code>, or <code>1</code>, depending on the order of the value of each of the given literals.
   * @see #compareLiterals(Element, String, Element, String)
   */
  int compareLiterals(String literal1, String literal2);

  /**
   * Returns <code>-1</code>, <code>0</code>, or <code>1</code>, 
   * depending on the {@link #getOrderedFacet order} of the {@link #getValue value} of each of the given literals.
   * @param context1 the context in which to interpret prefixes of QNames in literal1.
   * @param literal1 a literal.
   * @param context2 the context in which to interpret prefixes of QNames in literal2.
   * @param literal2 another literal.
   * @return <code>-1</code>, <code>0</code>, or <code>1</code>, depending on the order of the value of each of the given literals.
   * @since 2.4
   */
  int compareLiterals(Element context1, String literal1, Element context2, String literal2);

  /**
   * Returns <code>-1</code>, <code>0</code>, or <code>1</code>, 
   * depending on the {@link #getOrderedFacet order} of the {@link #getValue value}s.
   * @param value1 a value.
   * @param value2 another value.
   * @return <code>-1</code>, <code>0</code>, or <code>1</code>, depending on the order of the values.
   */
  int compareValues(Object value1, Object value2);

  /**
   * Returns whether the {@link #getValue value} of each of the given literals are equal.
   * @param literal1 a literal.
   * @param literal2 another literal.
   * @return whether the {@link #getValue value} of each of the given literals are equal.
   * @see #equalLiterals(Element, String, Element, String)
   */
  boolean equalLiterals(String literal1, String literal2);

  /**
   * Returns whether the {@link #getValue value} of each of the given literals are equal.
   * @param context1 the context in which to interpret prefixes of QNames in literal1.
   * @param literal1 a literal.
   * @param context2 the context in which to interpret prefixes of QNames in literal2.
   * @param literal2 another literal.
   * @return whether the {@link #getValue value} of each of the given literals are equal.
   * @since 2.4
   */
  boolean equalLiterals(Element context1, String literal1, Element context2, String literal2);

  /**
   * Returns whether the {@link #getValue value}s are equal.
   * @param value1 a literal.
   * @param value2 another literal.
   * @return whether the {@link #getValue value}s are equal.
   */
  boolean equalValues(Object value1, Object value2);
}
