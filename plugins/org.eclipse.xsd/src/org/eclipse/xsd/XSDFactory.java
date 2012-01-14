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


import org.eclipse.emf.ecore.EFactory;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage
 * @generated
 */
public interface XSDFactory extends EFactory{
  /**
   * The singleton instance of the factory.
   * @generated
   */
  XSDFactory eINSTANCE = org.eclipse.xsd.impl.XSDFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Schema</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Schema</em>'.
   * @generated
   */
  XSDSchema createXSDSchema();

  /**
   * Returns a new object of class '<em>Simple Type Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simple Type Definition</em>'.
   * @generated
   */
  XSDSimpleTypeDefinition createXSDSimpleTypeDefinition();

  /**
   * Returns a new object of class '<em>Max Inclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Max Inclusive Facet</em>'.
   * @generated
   */
  XSDMaxInclusiveFacet createXSDMaxInclusiveFacet();

  /**
   * Returns a new object of class '<em>Min Inclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Min Inclusive Facet</em>'.
   * @generated
   */
  XSDMinInclusiveFacet createXSDMinInclusiveFacet();

  /**
   * Returns a new object of class '<em>Min Exclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Min Exclusive Facet</em>'.
   * @generated
   */
  XSDMinExclusiveFacet createXSDMinExclusiveFacet();

  /**
   * Returns a new object of class '<em>Max Exclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Max Exclusive Facet</em>'.
   * @generated
   */
  XSDMaxExclusiveFacet createXSDMaxExclusiveFacet();

  /**
   * Returns a new object of class '<em>Length Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Length Facet</em>'.
   * @generated
   */
  XSDLengthFacet createXSDLengthFacet();

  /**
   * Returns a new object of class '<em>White Space Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>White Space Facet</em>'.
   * @generated
   */
  XSDWhiteSpaceFacet createXSDWhiteSpaceFacet();

  /**
   * Returns a new object of class '<em>Enumeration Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration Facet</em>'.
   * @generated
   */
  XSDEnumerationFacet createXSDEnumerationFacet();

  /**
   * Returns a new object of class '<em>Pattern Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Pattern Facet</em>'.
   * @generated
   */
  XSDPatternFacet createXSDPatternFacet();

  /**
   * Returns a new object of class '<em>Cardinality Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Cardinality Facet</em>'.
   * @generated
   */
  XSDCardinalityFacet createXSDCardinalityFacet();

  /**
   * Returns a new object of class '<em>Numeric Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Numeric Facet</em>'.
   * @generated
   */
  XSDNumericFacet createXSDNumericFacet();

  /**
   * Returns a new object of class '<em>Max Length Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Max Length Facet</em>'.
   * @generated
   */
  XSDMaxLengthFacet createXSDMaxLengthFacet();

  /**
   * Returns a new object of class '<em>Min Length Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Min Length Facet</em>'.
   * @generated
   */
  XSDMinLengthFacet createXSDMinLengthFacet();

  /**
   * Returns a new object of class '<em>Total Digits Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Total Digits Facet</em>'.
   * @generated
   */
  XSDTotalDigitsFacet createXSDTotalDigitsFacet();

  /**
   * Returns a new object of class '<em>Fraction Digits Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Fraction Digits Facet</em>'.
   * @generated
   */
  XSDFractionDigitsFacet createXSDFractionDigitsFacet();

  /**
   * Returns a new object of class '<em>Ordered Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ordered Facet</em>'.
   * @generated
   */
  XSDOrderedFacet createXSDOrderedFacet();

  /**
   * Returns a new object of class '<em>Bounded Facet</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bounded Facet</em>'.
   * @generated
   */
  XSDBoundedFacet createXSDBoundedFacet();

  /**
   * Returns a new object of class '<em>Particle</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Particle</em>'.
   * @generated
   */
  XSDParticle createXSDParticle();

  /**
   * Returns a new object of class '<em>Element Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element Declaration</em>'.
   * @generated
   */
  XSDElementDeclaration createXSDElementDeclaration();

  /**
   * Returns a new object of class '<em>Identity Constraint Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Identity Constraint Definition</em>'.
   * @generated
   */
  XSDIdentityConstraintDefinition createXSDIdentityConstraintDefinition();

  /**
   * Returns a new object of class '<em>XPath Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XPath Definition</em>'.
   * @generated
   */
  XSDXPathDefinition createXSDXPathDefinition();

  /**
   * Returns a new object of class '<em>Attribute Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attribute Declaration</em>'.
   * @generated
   */
  XSDAttributeDeclaration createXSDAttributeDeclaration();

  /**
   * Returns a new object of class '<em>Attribute Group Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attribute Group Definition</em>'.
   * @generated
   */
  XSDAttributeGroupDefinition createXSDAttributeGroupDefinition();

  /**
   * Returns a new object of class '<em>Attribute Use</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attribute Use</em>'.
   * @generated
   */
  XSDAttributeUse createXSDAttributeUse();

  /**
   * Returns a new object of class '<em>Wildcard</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Wildcard</em>'.
   * @generated
   */
  XSDWildcard createXSDWildcard();

  /**
   * Returns a new object of class '<em>Model Group Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Group Definition</em>'.
   * @generated
   */
  XSDModelGroupDefinition createXSDModelGroupDefinition();

  /**
   * Returns a new object of class '<em>Model Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Group</em>'.
   * @generated
   */
  XSDModelGroup createXSDModelGroup();

  /**
   * Returns a new object of class '<em>Notation Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Notation Declaration</em>'.
   * @generated
   */
  XSDNotationDeclaration createXSDNotationDeclaration();

  /**
   * Returns a new object of class '<em>Diagnostic</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Diagnostic</em>'.
   * @generated
   */
  XSDDiagnostic createXSDDiagnostic();

  /**
   * Returns a new object of class '<em>Annotation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Annotation</em>'.
   * @generated
   */
  XSDAnnotation createXSDAnnotation();

  /**
   * Returns a new object of class '<em>Complex Type Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Complex Type Definition</em>'.
   * @generated
   */
  XSDComplexTypeDefinition createXSDComplexTypeDefinition();

  /**
   * Returns a new object of class '<em>Redefine</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Redefine</em>'.
   * @generated
   */
  XSDRedefine createXSDRedefine();

  /**
   * Returns a new object of class '<em>Include</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Include</em>'.
   * @generated
   */
  XSDInclude createXSDInclude();

  /**
   * Returns a new object of class '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import</em>'.
   * @generated
   */
  XSDImport createXSDImport();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  XSDPackage getXSDPackage();

} 
