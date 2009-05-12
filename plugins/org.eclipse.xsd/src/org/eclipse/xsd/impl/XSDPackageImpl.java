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
 * $Id: XSDPackageImpl.java,v 1.13 2009/05/12 15:54:47 davidms Exp $
 */
package org.eclipse.xsd.impl;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDBoundedFacet;
import org.eclipse.xsd.XSDCardinality;
import org.eclipse.xsd.XSDCardinalityFacet;
import org.eclipse.xsd.XSDComplexFinal;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDContentTypeCategory;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDDisallowedSubstitutions;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDFractionDigitsFacet;
import org.eclipse.xsd.XSDFundamentalFacet;
import org.eclipse.xsd.XSDIdentityConstraintCategory;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDLengthFacet;
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDMinFacet;
import org.eclipse.xsd.XSDMinInclusiveFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDNamespaceConstraintCategory;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDNumericFacet;
import org.eclipse.xsd.XSDOrdered;
import org.eclipse.xsd.XSDOrderedFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDPatternFacet;
import org.eclipse.xsd.XSDProcessContents;
import org.eclipse.xsd.XSDProhibitedSubstitutions;
import org.eclipse.xsd.XSDRedefinableComponent;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDRedefineContent;
import org.eclipse.xsd.XSDRepeatableFacet;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaCompositor;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.XSDScope;
import org.eclipse.xsd.XSDSimpleFinal;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDSubstitutionGroupExclusions;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTotalDigitsFacet;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDVariety;
import org.eclipse.xsd.XSDWhiteSpace;
import org.eclipse.xsd.XSDWhiteSpaceFacet;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.XSDXPathVariety;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XSDPackageImpl 
  extends EPackageImpl 
  implements XSDPackage 
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdAnnotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdAttributeDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdAttributeGroupContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdAttributeGroupDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdAttributeUseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdBoundedFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdCardinalityFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdComplexTypeContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdComplexTypeDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdComponentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdConcreteComponentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdConstrainingFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdDiagnosticEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdElementDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdEnumerationFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdFixedFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdFractionDigitsFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdFundamentalFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdIdentityConstraintDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdImportEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdIncludeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdLengthFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMaxExclusiveFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMaxFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMaxInclusiveFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMaxLengthFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMinExclusiveFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMinFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMinInclusiveFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdMinLengthFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdModelGroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdModelGroupDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdNamedComponentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdNotationDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdNumericFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdOrderedFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdParticleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdParticleContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdPatternFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdRedefinableComponentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdRedefineContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdRedefineEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdRepeatableFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdSchemaEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdSchemaCompositorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdSchemaContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdSchemaDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdScopeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdSimpleTypeDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdTermEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdTotalDigitsFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdTypeDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdWhiteSpaceFacetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdWildcardEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xsdxPathDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdAttributeUseCategoryEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdCardinalityEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdComplexFinalEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdCompositorEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdConstraintEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdContentTypeCategoryEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdDerivationMethodEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdDiagnosticSeverityEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdDisallowedSubstitutionsEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdFormEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdIdentityConstraintCategoryEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdNamespaceConstraintCategoryEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdOrderedEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdProcessContentsEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdProhibitedSubstitutionsEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdSimpleFinalEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdSubstitutionGroupExclusionsEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdVarietyEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdWhiteSpaceEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum xsdxPathVarietyEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType domAttrEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType domDocumentEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType domElementEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType domNodeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType valueEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.xsd.XSDPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private XSDPackageImpl()
  {
    super(eNS_URI, XSDFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDAnnotation()
  {
    return xsdAnnotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAnnotation_ApplicationInformation()
  {
    return (EAttribute)xsdAnnotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAnnotation_UserInformation()
  {
    return (EAttribute)xsdAnnotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAnnotation_Attributes()
  {
    return (EAttribute)xsdAnnotationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDAttributeDeclaration()
  {
    return xsdAttributeDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeDeclaration_AttributeDeclarationReference()
  {
    return (EAttribute)xsdAttributeDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeDeclaration_Annotation()
  {
    return (EReference)xsdAttributeDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeDeclaration_AnonymousTypeDefinition()
  {
    return (EReference)xsdAttributeDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeDeclaration_TypeDefinition()
  {
    return (EReference)xsdAttributeDeclarationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeDeclaration_ResolvedAttributeDeclaration()
  {
    return (EReference)xsdAttributeDeclarationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDAttributeGroupContent()
  {
    return xsdAttributeGroupContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDAttributeGroupDefinition()
  {
    return xsdAttributeGroupDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeGroupDefinition_AttributeGroupDefinitionReference()
  {
    return (EAttribute)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_Annotation()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_Contents()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_AttributeUses()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_AttributeWildcardContent()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_AttributeWildcard()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_ResolvedAttributeGroupDefinition()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeGroupDefinition_SyntheticWildcard()
  {
    return (EReference)xsdAttributeGroupDefinitionEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDAttributeUse()
  {
    return xsdAttributeUseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeUse_Required()
  {
    return (EAttribute)xsdAttributeUseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeUse_Value()
  {
    return (EAttribute)xsdAttributeUseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeUse_Constraint()
  {
    return (EAttribute)xsdAttributeUseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeUse_Use()
  {
    return (EAttribute)xsdAttributeUseEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDAttributeUse_LexicalValue()
  {
    return (EAttribute)xsdAttributeUseEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeUse_AttributeDeclaration()
  {
    return (EReference)xsdAttributeUseEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDAttributeUse_Content()
  {
    return (EReference)xsdAttributeUseEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDBoundedFacet()
  {
    return xsdBoundedFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDBoundedFacet_Value()
  {
    return (EAttribute)xsdBoundedFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDCardinalityFacet()
  {
    return xsdCardinalityFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDCardinalityFacet_Value()
  {
    return (EAttribute)xsdCardinalityFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDComplexTypeContent()
  {
    return xsdComplexTypeContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDComplexTypeDefinition()
  {
    return xsdComplexTypeDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_DerivationMethod()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_Final()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_Abstract()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_ContentTypeCategory()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_ProhibitedSubstitutions()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_LexicalFinal()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_Block()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDComplexTypeDefinition_Mixed()
  {
    return (EAttribute)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_ContentAnnotation()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_BaseTypeDefinition()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_Content()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_ContentType()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_AttributeUses()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_AttributeContents()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_AttributeWildcard()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_AttributeWildcardContent()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_RootTypeDefinition()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_SyntheticParticle()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDComplexTypeDefinition_SyntheticWildcard()
  {
    return (EReference)xsdComplexTypeDefinitionEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDComponent()
  {
    return xsdComponentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDConcreteComponent()
  {
    return xsdConcreteComponentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDConcreteComponent_Element()
  {
    return (EAttribute)xsdConcreteComponentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDConcreteComponent_Container()
  {
    return (EReference)xsdConcreteComponentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDConcreteComponent_RootContainer()
  {
    return (EReference)xsdConcreteComponentEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDConcreteComponent_Schema()
  {
    return (EReference)xsdConcreteComponentEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDConcreteComponent_Diagnostics()
  {
    return (EReference)xsdConcreteComponentEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDConstrainingFacet()
  {
    return xsdConstrainingFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDDiagnostic()
  {
    return xsdDiagnosticEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Severity()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Message()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_LocationURI()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Line()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Column()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Node()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_AnnotationURI()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Key()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDDiagnostic_Substitutions()
  {
    return (EAttribute)xsdDiagnosticEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDDiagnostic_Components()
  {
    return (EReference)xsdDiagnosticEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDDiagnostic_PrimaryComponent()
  {
    return (EReference)xsdDiagnosticEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDElementDeclaration()
  {
    return xsdElementDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_Nillable()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_DisallowedSubstitutions()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_SubstitutionGroupExclusions()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_Abstract()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_LexicalFinal()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_Block()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_ElementDeclarationReference()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDElementDeclaration_Circular()
  {
    return (EAttribute)xsdElementDeclarationEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_Annotation()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_AnonymousTypeDefinition()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_TypeDefinition()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_IdentityConstraintDefinitions()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_ResolvedElementDeclaration()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_SubstitutionGroupAffiliation()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDElementDeclaration_SubstitutionGroup()
  {
    return (EReference)xsdElementDeclarationEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDEnumerationFacet()
  {
    return xsdEnumerationFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDEnumerationFacet_Value()
  {
    return (EAttribute)xsdEnumerationFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDFacet()
  {
    return xsdFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFacet_LexicalValue()
  {
    return (EAttribute)xsdFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFacet_FacetName()
  {
    return (EAttribute)xsdFacetEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFacet_EffectiveValue()
  {
    return (EAttribute)xsdFacetEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDFacet_Annotation()
  {
    return (EReference)xsdFacetEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDFacet_SimpleTypeDefinition()
  {
    return (EReference)xsdFacetEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDFeature()
  {
    return xsdFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFeature_Value()
  {
    return (EAttribute)xsdFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFeature_Constraint()
  {
    return (EAttribute)xsdFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFeature_Form()
  {
    return (EAttribute)xsdFeatureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFeature_LexicalValue()
  {
    return (EAttribute)xsdFeatureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFeature_Global()
  {
    return (EAttribute)xsdFeatureEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFeature_FeatureReference()
  {
    return (EAttribute)xsdFeatureEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDFeature_Scope()
  {
    return (EReference)xsdFeatureEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDFeature_ResolvedFeature()
  {
    return (EReference)xsdFeatureEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDFeature_Type()
  {
    return (EReference)xsdFeatureEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDFixedFacet()
  {
    return xsdFixedFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFixedFacet_Fixed()
  {
    return (EAttribute)xsdFixedFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDFractionDigitsFacet()
  {
    return xsdFractionDigitsFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDFractionDigitsFacet_Value()
  {
    return (EAttribute)xsdFractionDigitsFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDFundamentalFacet()
  {
    return xsdFundamentalFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDIdentityConstraintDefinition()
  {
    return xsdIdentityConstraintDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDIdentityConstraintDefinition_IdentityConstraintCategory()
  {
    return (EAttribute)xsdIdentityConstraintDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDIdentityConstraintDefinition_Annotation()
  {
    return (EReference)xsdIdentityConstraintDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDIdentityConstraintDefinition_ReferencedKey()
  {
    return (EReference)xsdIdentityConstraintDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDIdentityConstraintDefinition_Selector()
  {
    return (EReference)xsdIdentityConstraintDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDIdentityConstraintDefinition_Fields()
  {
    return (EReference)xsdIdentityConstraintDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDImport()
  {
    return xsdImportEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDImport_Namespace()
  {
    return (EAttribute)xsdImportEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDImport_Annotation()
  {
    return (EReference)xsdImportEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDInclude()
  {
    return xsdIncludeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDInclude_Annotation()
  {
    return (EReference)xsdIncludeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDLengthFacet()
  {
    return xsdLengthFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDLengthFacet_Value()
  {
    return (EAttribute)xsdLengthFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMaxExclusiveFacet()
  {
    return xsdMaxExclusiveFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMaxFacet()
  {
    return xsdMaxFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMaxFacet_Value()
  {
    return (EAttribute)xsdMaxFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMaxFacet_Inclusive()
  {
    return (EAttribute)xsdMaxFacetEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMaxFacet_Exclusive()
  {
    return (EAttribute)xsdMaxFacetEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMaxInclusiveFacet()
  {
    return xsdMaxInclusiveFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMaxLengthFacet()
  {
    return xsdMaxLengthFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMaxLengthFacet_Value()
  {
    return (EAttribute)xsdMaxLengthFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMinExclusiveFacet()
  {
    return xsdMinExclusiveFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMinFacet()
  {
    return xsdMinFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMinFacet_Value()
  {
    return (EAttribute)xsdMinFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMinFacet_Inclusive()
  {
    return (EAttribute)xsdMinFacetEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMinFacet_Exclusive()
  {
    return (EAttribute)xsdMinFacetEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMinInclusiveFacet()
  {
    return xsdMinInclusiveFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDMinLengthFacet()
  {
    return xsdMinLengthFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDMinLengthFacet_Value()
  {
    return (EAttribute)xsdMinLengthFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDModelGroup()
  {
    return xsdModelGroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDModelGroup_Compositor()
  {
    return (EAttribute)xsdModelGroupEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDModelGroup_Annotation()
  {
    return (EReference)xsdModelGroupEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDModelGroup_Contents()
  {
    return (EReference)xsdModelGroupEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDModelGroup_Particles()
  {
    return (EReference)xsdModelGroupEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDModelGroupDefinition()
  {
    return xsdModelGroupDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDModelGroupDefinition_ModelGroupDefinitionReference()
  {
    return (EAttribute)xsdModelGroupDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDModelGroupDefinition_Annotation()
  {
    return (EReference)xsdModelGroupDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDModelGroupDefinition_ModelGroup()
  {
    return (EReference)xsdModelGroupDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDModelGroupDefinition_ResolvedModelGroupDefinition()
  {
    return (EReference)xsdModelGroupDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDNamedComponent()
  {
    return xsdNamedComponentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNamedComponent_Name()
  {
    return (EAttribute)xsdNamedComponentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNamedComponent_TargetNamespace()
  {
    return (EAttribute)xsdNamedComponentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNamedComponent_AliasName()
  {
    return (EAttribute)xsdNamedComponentEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNamedComponent_URI()
  {
    return (EAttribute)xsdNamedComponentEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNamedComponent_AliasURI()
  {
    return (EAttribute)xsdNamedComponentEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNamedComponent_QName()
  {
    return (EAttribute)xsdNamedComponentEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDNotationDeclaration()
  {
    return xsdNotationDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNotationDeclaration_SystemIdentifier()
  {
    return (EAttribute)xsdNotationDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNotationDeclaration_PublicIdentifier()
  {
    return (EAttribute)xsdNotationDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDNotationDeclaration_Annotation()
  {
    return (EReference)xsdNotationDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDNumericFacet()
  {
    return xsdNumericFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDNumericFacet_Value()
  {
    return (EAttribute)xsdNumericFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDOrderedFacet()
  {
    return xsdOrderedFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDOrderedFacet_Value()
  {
    return (EAttribute)xsdOrderedFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDParticle()
  {
    return xsdParticleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDParticle_MinOccurs()
  {
    return (EAttribute)xsdParticleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDParticle_MaxOccurs()
  {
    return (EAttribute)xsdParticleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDParticle_Content()
  {
    return (EReference)xsdParticleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDParticle_Term()
  {
    return (EReference)xsdParticleEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDParticleContent()
  {
    return xsdParticleContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDPatternFacet()
  {
    return xsdPatternFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDPatternFacet_Value()
  {
    return (EAttribute)xsdPatternFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDRedefinableComponent()
  {
    return xsdRedefinableComponentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDRedefinableComponent_Circular()
  {
    return (EAttribute)xsdRedefinableComponentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDRedefineContent()
  {
    return xsdRedefineContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDRedefine()
  {
    return xsdRedefineEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDRedefine_Annotations()
  {
    return (EReference)xsdRedefineEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDRedefine_Contents()
  {
    return (EReference)xsdRedefineEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDRepeatableFacet()
  {
    return xsdRepeatableFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDRepeatableFacet_Annotations()
  {
    return (EReference)xsdRepeatableFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDSchema()
  {
    return xsdSchemaEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_Document()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_SchemaLocation()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_TargetNamespace()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_AttributeFormDefault()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_ElementFormDefault()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_FinalDefault()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_BlockDefault()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchema_Version()
  {
    return (EAttribute)xsdSchemaEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_Contents()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_ElementDeclarations()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_AttributeDeclarations()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_AttributeGroupDefinitions()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_TypeDefinitions()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_ModelGroupDefinitions()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_IdentityConstraintDefinitions()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_NotationDeclarations()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_Annotations()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_AllDiagnostics()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_ReferencingDirectives()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_RootVersion()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_OriginalVersion()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_IncorporatedVersions()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchema_SchemaForSchema()
  {
    return (EReference)xsdSchemaEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDSchemaCompositor()
  {
    return xsdSchemaCompositorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchemaCompositor_IncorporatedSchema()
  {
    return (EReference)xsdSchemaCompositorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDSchemaContent()
  {
    return xsdSchemaContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDSchemaDirective()
  {
    return xsdSchemaDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSchemaDirective_SchemaLocation()
  {
    return (EAttribute)xsdSchemaDirectiveEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSchemaDirective_ResolvedSchema()
  {
    return (EReference)xsdSchemaDirectiveEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDScope()
  {
    return xsdScopeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDSimpleTypeDefinition()
  {
    return xsdSimpleTypeDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSimpleTypeDefinition_Variety()
  {
    return (EAttribute)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSimpleTypeDefinition_Final()
  {
    return (EAttribute)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSimpleTypeDefinition_LexicalFinal()
  {
    return (EAttribute)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDSimpleTypeDefinition_ValidFacets()
  {
    return (EAttribute)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_Contents()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_FacetContents()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_Facets()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MemberTypeDefinitions()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_FundamentalFacets()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_BaseTypeDefinition()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_PrimitiveTypeDefinition()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_ItemTypeDefinition()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_RootTypeDefinition()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MinFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MaxFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MaxInclusiveFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MinInclusiveFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MinExclusiveFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MaxExclusiveFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_LengthFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_WhiteSpaceFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EnumerationFacets()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_PatternFacets()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_CardinalityFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(23);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_NumericFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(24);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MaxLengthFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(25);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_MinLengthFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(26);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_TotalDigitsFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(27);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_FractionDigitsFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(28);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_OrderedFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(29);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_BoundedFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(30);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveMaxFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(31);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveWhiteSpaceFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(32);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveMaxLengthFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(33);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveFractionDigitsFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(34);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectivePatternFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(35);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveEnumerationFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(36);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveTotalDigitsFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(37);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveMinLengthFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(38);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveLengthFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(39);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_EffectiveMinFacet()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(40);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDSimpleTypeDefinition_SyntheticFacets()
  {
    return (EReference)xsdSimpleTypeDefinitionEClass.getEStructuralFeatures().get(41);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDTerm()
  {
    return xsdTermEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDTotalDigitsFacet()
  {
    return xsdTotalDigitsFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDTotalDigitsFacet_Value()
  {
    return (EAttribute)xsdTotalDigitsFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDTypeDefinition()
  {
    return xsdTypeDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_Annotation()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_DerivationAnnotation()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_Annotations()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_RootType()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_BaseType()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_SimpleType()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDTypeDefinition_ComplexType()
  {
    return (EReference)xsdTypeDefinitionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDWhiteSpaceFacet()
  {
    return xsdWhiteSpaceFacetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDWhiteSpaceFacet_Value()
  {
    return (EAttribute)xsdWhiteSpaceFacetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDWildcard()
  {
    return xsdWildcardEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDWildcard_NamespaceConstraintCategory()
  {
    return (EAttribute)xsdWildcardEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDWildcard_NamespaceConstraint()
  {
    return (EAttribute)xsdWildcardEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDWildcard_ProcessContents()
  {
    return (EAttribute)xsdWildcardEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDWildcard_LexicalNamespaceConstraint()
  {
    return (EAttribute)xsdWildcardEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDWildcard_Annotation()
  {
    return (EReference)xsdWildcardEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDWildcard_Annotations()
  {
    return (EReference)xsdWildcardEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXSDXPathDefinition()
  {
    return xsdxPathDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDXPathDefinition_Variety()
  {
    return (EAttribute)xsdxPathDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXSDXPathDefinition_Value()
  {
    return (EAttribute)xsdxPathDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXSDXPathDefinition_Annotation()
  {
    return (EReference)xsdxPathDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDAttributeUseCategory()
  {
    return xsdAttributeUseCategoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDCardinality()
  {
    return xsdCardinalityEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDComplexFinal()
  {
    return xsdComplexFinalEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDCompositor()
  {
    return xsdCompositorEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDConstraint()
  {
    return xsdConstraintEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDContentTypeCategory()
  {
    return xsdContentTypeCategoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDDerivationMethod()
  {
    return xsdDerivationMethodEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDDiagnosticSeverity()
  {
    return xsdDiagnosticSeverityEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDDisallowedSubstitutions()
  {
    return xsdDisallowedSubstitutionsEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDForm()
  {
    return xsdFormEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDIdentityConstraintCategory()
  {
    return xsdIdentityConstraintCategoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDNamespaceConstraintCategory()
  {
    return xsdNamespaceConstraintCategoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDOrdered()
  {
    return xsdOrderedEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDProcessContents()
  {
    return xsdProcessContentsEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDProhibitedSubstitutions()
  {
    return xsdProhibitedSubstitutionsEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDSimpleFinal()
  {
    return xsdSimpleFinalEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDSubstitutionGroupExclusions()
  {
    return xsdSubstitutionGroupExclusionsEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDVariety()
  {
    return xsdVarietyEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDWhiteSpace()
  {
    return xsdWhiteSpaceEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getXSDXPathVariety()
  {
    return xsdxPathVarietyEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDOMAttr()
  {
    return domAttrEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDOMDocument()
  {
    return domDocumentEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDOMElement()
  {
    return domElementEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDOMNode()
  {
    return domNodeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getValue()
  {
    return valueEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDFactory getXSDFactory()
  {
    return (XSDFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;
 
  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    xsdAnnotationEClass = createEClass(XSD_ANNOTATION);
    createEAttribute(xsdAnnotationEClass, XSD_ANNOTATION__APPLICATION_INFORMATION);
    createEAttribute(xsdAnnotationEClass, XSD_ANNOTATION__USER_INFORMATION);
    createEAttribute(xsdAnnotationEClass, XSD_ANNOTATION__ATTRIBUTES);

    xsdAttributeDeclarationEClass = createEClass(XSD_ATTRIBUTE_DECLARATION);
    createEAttribute(xsdAttributeDeclarationEClass, XSD_ATTRIBUTE_DECLARATION__ATTRIBUTE_DECLARATION_REFERENCE);
    createEReference(xsdAttributeDeclarationEClass, XSD_ATTRIBUTE_DECLARATION__ANNOTATION);
    createEReference(xsdAttributeDeclarationEClass, XSD_ATTRIBUTE_DECLARATION__ANONYMOUS_TYPE_DEFINITION);
    createEReference(xsdAttributeDeclarationEClass, XSD_ATTRIBUTE_DECLARATION__TYPE_DEFINITION);
    createEReference(xsdAttributeDeclarationEClass, XSD_ATTRIBUTE_DECLARATION__RESOLVED_ATTRIBUTE_DECLARATION);

    xsdAttributeGroupContentEClass = createEClass(XSD_ATTRIBUTE_GROUP_CONTENT);

    xsdAttributeGroupDefinitionEClass = createEClass(XSD_ATTRIBUTE_GROUP_DEFINITION);
    createEAttribute(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_GROUP_DEFINITION_REFERENCE);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__ANNOTATION);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__CONTENTS);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_USES);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__ATTRIBUTE_WILDCARD);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__RESOLVED_ATTRIBUTE_GROUP_DEFINITION);
    createEReference(xsdAttributeGroupDefinitionEClass, XSD_ATTRIBUTE_GROUP_DEFINITION__SYNTHETIC_WILDCARD);

    xsdAttributeUseEClass = createEClass(XSD_ATTRIBUTE_USE);
    createEAttribute(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__REQUIRED);
    createEAttribute(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__VALUE);
    createEAttribute(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__CONSTRAINT);
    createEAttribute(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__USE);
    createEAttribute(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__LEXICAL_VALUE);
    createEReference(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__ATTRIBUTE_DECLARATION);
    createEReference(xsdAttributeUseEClass, XSD_ATTRIBUTE_USE__CONTENT);

    xsdBoundedFacetEClass = createEClass(XSD_BOUNDED_FACET);
    createEAttribute(xsdBoundedFacetEClass, XSD_BOUNDED_FACET__VALUE);

    xsdCardinalityFacetEClass = createEClass(XSD_CARDINALITY_FACET);
    createEAttribute(xsdCardinalityFacetEClass, XSD_CARDINALITY_FACET__VALUE);

    xsdComplexTypeContentEClass = createEClass(XSD_COMPLEX_TYPE_CONTENT);

    xsdComplexTypeDefinitionEClass = createEClass(XSD_COMPLEX_TYPE_DEFINITION);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__DERIVATION_METHOD);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__FINAL);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__ABSTRACT);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE_CATEGORY);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__PROHIBITED_SUBSTITUTIONS);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__LEXICAL_FINAL);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__BLOCK);
    createEAttribute(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__MIXED);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__CONTENT_ANNOTATION);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__BASE_TYPE_DEFINITION);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__CONTENT);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__CONTENT_TYPE);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_USES);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_CONTENTS);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__ATTRIBUTE_WILDCARD_CONTENT);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__ROOT_TYPE_DEFINITION);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE);
    createEReference(xsdComplexTypeDefinitionEClass, XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_WILDCARD);

    xsdComponentEClass = createEClass(XSD_COMPONENT);

    xsdConcreteComponentEClass = createEClass(XSD_CONCRETE_COMPONENT);
    createEAttribute(xsdConcreteComponentEClass, XSD_CONCRETE_COMPONENT__ELEMENT);
    createEReference(xsdConcreteComponentEClass, XSD_CONCRETE_COMPONENT__CONTAINER);
    createEReference(xsdConcreteComponentEClass, XSD_CONCRETE_COMPONENT__ROOT_CONTAINER);
    createEReference(xsdConcreteComponentEClass, XSD_CONCRETE_COMPONENT__SCHEMA);
    createEReference(xsdConcreteComponentEClass, XSD_CONCRETE_COMPONENT__DIAGNOSTICS);

    xsdConstrainingFacetEClass = createEClass(XSD_CONSTRAINING_FACET);

    xsdDiagnosticEClass = createEClass(XSD_DIAGNOSTIC);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__SEVERITY);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__MESSAGE);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__LOCATION_URI);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__LINE);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__COLUMN);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__NODE);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__ANNOTATION_URI);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__KEY);
    createEAttribute(xsdDiagnosticEClass, XSD_DIAGNOSTIC__SUBSTITUTIONS);
    createEReference(xsdDiagnosticEClass, XSD_DIAGNOSTIC__COMPONENTS);
    createEReference(xsdDiagnosticEClass, XSD_DIAGNOSTIC__PRIMARY_COMPONENT);

    xsdElementDeclarationEClass = createEClass(XSD_ELEMENT_DECLARATION);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__NILLABLE);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__DISALLOWED_SUBSTITUTIONS);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_EXCLUSIONS);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__ABSTRACT);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__LEXICAL_FINAL);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__BLOCK);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__ELEMENT_DECLARATION_REFERENCE);
    createEAttribute(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__CIRCULAR);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__ANNOTATION);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__ANONYMOUS_TYPE_DEFINITION);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__TYPE_DEFINITION);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__IDENTITY_CONSTRAINT_DEFINITIONS);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__RESOLVED_ELEMENT_DECLARATION);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP_AFFILIATION);
    createEReference(xsdElementDeclarationEClass, XSD_ELEMENT_DECLARATION__SUBSTITUTION_GROUP);

    xsdEnumerationFacetEClass = createEClass(XSD_ENUMERATION_FACET);
    createEAttribute(xsdEnumerationFacetEClass, XSD_ENUMERATION_FACET__VALUE);

    xsdFacetEClass = createEClass(XSD_FACET);
    createEAttribute(xsdFacetEClass, XSD_FACET__LEXICAL_VALUE);
    createEAttribute(xsdFacetEClass, XSD_FACET__FACET_NAME);
    createEAttribute(xsdFacetEClass, XSD_FACET__EFFECTIVE_VALUE);
    createEReference(xsdFacetEClass, XSD_FACET__ANNOTATION);
    createEReference(xsdFacetEClass, XSD_FACET__SIMPLE_TYPE_DEFINITION);

    xsdFeatureEClass = createEClass(XSD_FEATURE);
    createEAttribute(xsdFeatureEClass, XSD_FEATURE__VALUE);
    createEAttribute(xsdFeatureEClass, XSD_FEATURE__CONSTRAINT);
    createEAttribute(xsdFeatureEClass, XSD_FEATURE__FORM);
    createEAttribute(xsdFeatureEClass, XSD_FEATURE__LEXICAL_VALUE);
    createEAttribute(xsdFeatureEClass, XSD_FEATURE__GLOBAL);
    createEAttribute(xsdFeatureEClass, XSD_FEATURE__FEATURE_REFERENCE);
    createEReference(xsdFeatureEClass, XSD_FEATURE__SCOPE);
    createEReference(xsdFeatureEClass, XSD_FEATURE__RESOLVED_FEATURE);
    createEReference(xsdFeatureEClass, XSD_FEATURE__TYPE);

    xsdFixedFacetEClass = createEClass(XSD_FIXED_FACET);
    createEAttribute(xsdFixedFacetEClass, XSD_FIXED_FACET__FIXED);

    xsdFractionDigitsFacetEClass = createEClass(XSD_FRACTION_DIGITS_FACET);
    createEAttribute(xsdFractionDigitsFacetEClass, XSD_FRACTION_DIGITS_FACET__VALUE);

    xsdFundamentalFacetEClass = createEClass(XSD_FUNDAMENTAL_FACET);

    xsdIdentityConstraintDefinitionEClass = createEClass(XSD_IDENTITY_CONSTRAINT_DEFINITION);
    createEAttribute(xsdIdentityConstraintDefinitionEClass, XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY);
    createEReference(xsdIdentityConstraintDefinitionEClass, XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION);
    createEReference(xsdIdentityConstraintDefinitionEClass, XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY);
    createEReference(xsdIdentityConstraintDefinitionEClass, XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR);
    createEReference(xsdIdentityConstraintDefinitionEClass, XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS);

    xsdImportEClass = createEClass(XSD_IMPORT);
    createEAttribute(xsdImportEClass, XSD_IMPORT__NAMESPACE);
    createEReference(xsdImportEClass, XSD_IMPORT__ANNOTATION);

    xsdIncludeEClass = createEClass(XSD_INCLUDE);
    createEReference(xsdIncludeEClass, XSD_INCLUDE__ANNOTATION);

    xsdLengthFacetEClass = createEClass(XSD_LENGTH_FACET);
    createEAttribute(xsdLengthFacetEClass, XSD_LENGTH_FACET__VALUE);

    xsdMaxExclusiveFacetEClass = createEClass(XSD_MAX_EXCLUSIVE_FACET);

    xsdMaxFacetEClass = createEClass(XSD_MAX_FACET);
    createEAttribute(xsdMaxFacetEClass, XSD_MAX_FACET__VALUE);
    createEAttribute(xsdMaxFacetEClass, XSD_MAX_FACET__INCLUSIVE);
    createEAttribute(xsdMaxFacetEClass, XSD_MAX_FACET__EXCLUSIVE);

    xsdMaxInclusiveFacetEClass = createEClass(XSD_MAX_INCLUSIVE_FACET);

    xsdMaxLengthFacetEClass = createEClass(XSD_MAX_LENGTH_FACET);
    createEAttribute(xsdMaxLengthFacetEClass, XSD_MAX_LENGTH_FACET__VALUE);

    xsdMinExclusiveFacetEClass = createEClass(XSD_MIN_EXCLUSIVE_FACET);

    xsdMinFacetEClass = createEClass(XSD_MIN_FACET);
    createEAttribute(xsdMinFacetEClass, XSD_MIN_FACET__VALUE);
    createEAttribute(xsdMinFacetEClass, XSD_MIN_FACET__INCLUSIVE);
    createEAttribute(xsdMinFacetEClass, XSD_MIN_FACET__EXCLUSIVE);

    xsdMinInclusiveFacetEClass = createEClass(XSD_MIN_INCLUSIVE_FACET);

    xsdMinLengthFacetEClass = createEClass(XSD_MIN_LENGTH_FACET);
    createEAttribute(xsdMinLengthFacetEClass, XSD_MIN_LENGTH_FACET__VALUE);

    xsdModelGroupEClass = createEClass(XSD_MODEL_GROUP);
    createEAttribute(xsdModelGroupEClass, XSD_MODEL_GROUP__COMPOSITOR);
    createEReference(xsdModelGroupEClass, XSD_MODEL_GROUP__ANNOTATION);
    createEReference(xsdModelGroupEClass, XSD_MODEL_GROUP__CONTENTS);
    createEReference(xsdModelGroupEClass, XSD_MODEL_GROUP__PARTICLES);

    xsdModelGroupDefinitionEClass = createEClass(XSD_MODEL_GROUP_DEFINITION);
    createEAttribute(xsdModelGroupDefinitionEClass, XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP_DEFINITION_REFERENCE);
    createEReference(xsdModelGroupDefinitionEClass, XSD_MODEL_GROUP_DEFINITION__ANNOTATION);
    createEReference(xsdModelGroupDefinitionEClass, XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP);
    createEReference(xsdModelGroupDefinitionEClass, XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION);

    xsdNamedComponentEClass = createEClass(XSD_NAMED_COMPONENT);
    createEAttribute(xsdNamedComponentEClass, XSD_NAMED_COMPONENT__NAME);
    createEAttribute(xsdNamedComponentEClass, XSD_NAMED_COMPONENT__TARGET_NAMESPACE);
    createEAttribute(xsdNamedComponentEClass, XSD_NAMED_COMPONENT__ALIAS_NAME);
    createEAttribute(xsdNamedComponentEClass, XSD_NAMED_COMPONENT__URI);
    createEAttribute(xsdNamedComponentEClass, XSD_NAMED_COMPONENT__ALIAS_URI);
    createEAttribute(xsdNamedComponentEClass, XSD_NAMED_COMPONENT__QNAME);

    xsdNotationDeclarationEClass = createEClass(XSD_NOTATION_DECLARATION);
    createEAttribute(xsdNotationDeclarationEClass, XSD_NOTATION_DECLARATION__SYSTEM_IDENTIFIER);
    createEAttribute(xsdNotationDeclarationEClass, XSD_NOTATION_DECLARATION__PUBLIC_IDENTIFIER);
    createEReference(xsdNotationDeclarationEClass, XSD_NOTATION_DECLARATION__ANNOTATION);

    xsdNumericFacetEClass = createEClass(XSD_NUMERIC_FACET);
    createEAttribute(xsdNumericFacetEClass, XSD_NUMERIC_FACET__VALUE);

    xsdOrderedFacetEClass = createEClass(XSD_ORDERED_FACET);
    createEAttribute(xsdOrderedFacetEClass, XSD_ORDERED_FACET__VALUE);

    xsdParticleEClass = createEClass(XSD_PARTICLE);
    createEAttribute(xsdParticleEClass, XSD_PARTICLE__MIN_OCCURS);
    createEAttribute(xsdParticleEClass, XSD_PARTICLE__MAX_OCCURS);
    createEReference(xsdParticleEClass, XSD_PARTICLE__CONTENT);
    createEReference(xsdParticleEClass, XSD_PARTICLE__TERM);

    xsdParticleContentEClass = createEClass(XSD_PARTICLE_CONTENT);

    xsdPatternFacetEClass = createEClass(XSD_PATTERN_FACET);
    createEAttribute(xsdPatternFacetEClass, XSD_PATTERN_FACET__VALUE);

    xsdRedefinableComponentEClass = createEClass(XSD_REDEFINABLE_COMPONENT);
    createEAttribute(xsdRedefinableComponentEClass, XSD_REDEFINABLE_COMPONENT__CIRCULAR);

    xsdRedefineContentEClass = createEClass(XSD_REDEFINE_CONTENT);

    xsdRedefineEClass = createEClass(XSD_REDEFINE);
    createEReference(xsdRedefineEClass, XSD_REDEFINE__ANNOTATIONS);
    createEReference(xsdRedefineEClass, XSD_REDEFINE__CONTENTS);

    xsdRepeatableFacetEClass = createEClass(XSD_REPEATABLE_FACET);
    createEReference(xsdRepeatableFacetEClass, XSD_REPEATABLE_FACET__ANNOTATIONS);

    xsdSchemaEClass = createEClass(XSD_SCHEMA);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__DOCUMENT);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__SCHEMA_LOCATION);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__TARGET_NAMESPACE);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__ELEMENT_FORM_DEFAULT);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__FINAL_DEFAULT);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__BLOCK_DEFAULT);
    createEAttribute(xsdSchemaEClass, XSD_SCHEMA__VERSION);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__CONTENTS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ELEMENT_DECLARATIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ATTRIBUTE_DECLARATIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__TYPE_DEFINITIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__MODEL_GROUP_DEFINITIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__NOTATION_DECLARATIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ANNOTATIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ALL_DIAGNOSTICS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__REFERENCING_DIRECTIVES);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ROOT_VERSION);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__ORIGINAL_VERSION);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__INCORPORATED_VERSIONS);
    createEReference(xsdSchemaEClass, XSD_SCHEMA__SCHEMA_FOR_SCHEMA);

    xsdSchemaCompositorEClass = createEClass(XSD_SCHEMA_COMPOSITOR);
    createEReference(xsdSchemaCompositorEClass, XSD_SCHEMA_COMPOSITOR__INCORPORATED_SCHEMA);

    xsdSchemaContentEClass = createEClass(XSD_SCHEMA_CONTENT);

    xsdSchemaDirectiveEClass = createEClass(XSD_SCHEMA_DIRECTIVE);
    createEAttribute(xsdSchemaDirectiveEClass, XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION);
    createEReference(xsdSchemaDirectiveEClass, XSD_SCHEMA_DIRECTIVE__RESOLVED_SCHEMA);

    xsdScopeEClass = createEClass(XSD_SCOPE);

    xsdSimpleTypeDefinitionEClass = createEClass(XSD_SIMPLE_TYPE_DEFINITION);
    createEAttribute(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__VARIETY);
    createEAttribute(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__FINAL);
    createEAttribute(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__LEXICAL_FINAL);
    createEAttribute(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__VALID_FACETS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__CONTENTS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__FACET_CONTENTS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__FACETS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MEMBER_TYPE_DEFINITIONS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__FUNDAMENTAL_FACETS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__BASE_TYPE_DEFINITION);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__PRIMITIVE_TYPE_DEFINITION);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__ITEM_TYPE_DEFINITION);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__ROOT_TYPE_DEFINITION);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MIN_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MAX_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MAX_INCLUSIVE_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MIN_INCLUSIVE_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MIN_EXCLUSIVE_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MAX_EXCLUSIVE_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__LENGTH_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__WHITE_SPACE_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__ENUMERATION_FACETS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__PATTERN_FACETS);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__CARDINALITY_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__NUMERIC_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MAX_LENGTH_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__MIN_LENGTH_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__TOTAL_DIGITS_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__FRACTION_DIGITS_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__ORDERED_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__BOUNDED_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MAX_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_WHITE_SPACE_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MAX_LENGTH_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_FRACTION_DIGITS_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_PATTERN_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_ENUMERATION_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_TOTAL_DIGITS_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MIN_LENGTH_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_LENGTH_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__EFFECTIVE_MIN_FACET);
    createEReference(xsdSimpleTypeDefinitionEClass, XSD_SIMPLE_TYPE_DEFINITION__SYNTHETIC_FACETS);

    xsdTermEClass = createEClass(XSD_TERM);

    xsdTotalDigitsFacetEClass = createEClass(XSD_TOTAL_DIGITS_FACET);
    createEAttribute(xsdTotalDigitsFacetEClass, XSD_TOTAL_DIGITS_FACET__VALUE);

    xsdTypeDefinitionEClass = createEClass(XSD_TYPE_DEFINITION);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__ANNOTATION);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__ANNOTATIONS);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__ROOT_TYPE);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__BASE_TYPE);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__SIMPLE_TYPE);
    createEReference(xsdTypeDefinitionEClass, XSD_TYPE_DEFINITION__COMPLEX_TYPE);

    xsdWhiteSpaceFacetEClass = createEClass(XSD_WHITE_SPACE_FACET);
    createEAttribute(xsdWhiteSpaceFacetEClass, XSD_WHITE_SPACE_FACET__VALUE);

    xsdWildcardEClass = createEClass(XSD_WILDCARD);
    createEAttribute(xsdWildcardEClass, XSD_WILDCARD__NAMESPACE_CONSTRAINT_CATEGORY);
    createEAttribute(xsdWildcardEClass, XSD_WILDCARD__NAMESPACE_CONSTRAINT);
    createEAttribute(xsdWildcardEClass, XSD_WILDCARD__PROCESS_CONTENTS);
    createEAttribute(xsdWildcardEClass, XSD_WILDCARD__LEXICAL_NAMESPACE_CONSTRAINT);
    createEReference(xsdWildcardEClass, XSD_WILDCARD__ANNOTATION);
    createEReference(xsdWildcardEClass, XSD_WILDCARD__ANNOTATIONS);

    xsdxPathDefinitionEClass = createEClass(XSD_XPATH_DEFINITION);
    createEAttribute(xsdxPathDefinitionEClass, XSD_XPATH_DEFINITION__VARIETY);
    createEAttribute(xsdxPathDefinitionEClass, XSD_XPATH_DEFINITION__VALUE);
    createEReference(xsdxPathDefinitionEClass, XSD_XPATH_DEFINITION__ANNOTATION);

    // Create enums
    xsdAttributeUseCategoryEEnum = createEEnum(XSD_ATTRIBUTE_USE_CATEGORY);
    xsdCardinalityEEnum = createEEnum(XSD_CARDINALITY);
    xsdComplexFinalEEnum = createEEnum(XSD_COMPLEX_FINAL);
    xsdCompositorEEnum = createEEnum(XSD_COMPOSITOR);
    xsdConstraintEEnum = createEEnum(XSD_CONSTRAINT);
    xsdContentTypeCategoryEEnum = createEEnum(XSD_CONTENT_TYPE_CATEGORY);
    xsdDerivationMethodEEnum = createEEnum(XSD_DERIVATION_METHOD);
    xsdDiagnosticSeverityEEnum = createEEnum(XSD_DIAGNOSTIC_SEVERITY);
    xsdDisallowedSubstitutionsEEnum = createEEnum(XSD_DISALLOWED_SUBSTITUTIONS);
    xsdFormEEnum = createEEnum(XSD_FORM);
    xsdIdentityConstraintCategoryEEnum = createEEnum(XSD_IDENTITY_CONSTRAINT_CATEGORY);
    xsdNamespaceConstraintCategoryEEnum = createEEnum(XSD_NAMESPACE_CONSTRAINT_CATEGORY);
    xsdOrderedEEnum = createEEnum(XSD_ORDERED);
    xsdProcessContentsEEnum = createEEnum(XSD_PROCESS_CONTENTS);
    xsdProhibitedSubstitutionsEEnum = createEEnum(XSD_PROHIBITED_SUBSTITUTIONS);
    xsdSimpleFinalEEnum = createEEnum(XSD_SIMPLE_FINAL);
    xsdSubstitutionGroupExclusionsEEnum = createEEnum(XSD_SUBSTITUTION_GROUP_EXCLUSIONS);
    xsdVarietyEEnum = createEEnum(XSD_VARIETY);
    xsdWhiteSpaceEEnum = createEEnum(XSD_WHITE_SPACE);
    xsdxPathVarietyEEnum = createEEnum(XSD_XPATH_VARIETY);

    // Create data types
    domAttrEDataType = createEDataType(DOM_ATTR);
    domDocumentEDataType = createEDataType(DOM_DOCUMENT);
    domElementEDataType = createEDataType(DOM_ELEMENT);
    domNodeEDataType = createEDataType(DOM_NODE);
    valueEDataType = createEDataType(VALUE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    xsdAnnotationEClass.getESuperTypes().add(this.getXSDComponent());
    xsdAnnotationEClass.getESuperTypes().add(this.getXSDRedefineContent());
    xsdAttributeDeclarationEClass.getESuperTypes().add(this.getXSDFeature());
    xsdAttributeDeclarationEClass.getESuperTypes().add(this.getXSDSchemaContent());
    xsdAttributeGroupContentEClass.getESuperTypes().add(this.getXSDConcreteComponent());
    xsdAttributeGroupDefinitionEClass.getESuperTypes().add(this.getXSDRedefinableComponent());
    xsdAttributeGroupDefinitionEClass.getESuperTypes().add(this.getXSDAttributeGroupContent());
    xsdAttributeGroupDefinitionEClass.getESuperTypes().add(this.getXSDRedefineContent());
    xsdAttributeUseEClass.getESuperTypes().add(this.getXSDComponent());
    xsdAttributeUseEClass.getESuperTypes().add(this.getXSDAttributeGroupContent());
    xsdBoundedFacetEClass.getESuperTypes().add(this.getXSDFundamentalFacet());
    xsdCardinalityFacetEClass.getESuperTypes().add(this.getXSDFundamentalFacet());
    xsdComplexTypeContentEClass.getESuperTypes().add(this.getXSDComponent());
    xsdComplexTypeDefinitionEClass.getESuperTypes().add(this.getXSDTypeDefinition());
    xsdComplexTypeDefinitionEClass.getESuperTypes().add(this.getXSDScope());
    xsdComponentEClass.getESuperTypes().add(this.getXSDConcreteComponent());
    xsdConstrainingFacetEClass.getESuperTypes().add(this.getXSDFacet());
    xsdDiagnosticEClass.getESuperTypes().add(this.getXSDConcreteComponent());
    xsdElementDeclarationEClass.getESuperTypes().add(this.getXSDFeature());
    xsdElementDeclarationEClass.getESuperTypes().add(this.getXSDSchemaContent());
    xsdElementDeclarationEClass.getESuperTypes().add(this.getXSDTerm());
    xsdEnumerationFacetEClass.getESuperTypes().add(this.getXSDRepeatableFacet());
    xsdFacetEClass.getESuperTypes().add(this.getXSDComponent());
    xsdFeatureEClass.getESuperTypes().add(this.getXSDNamedComponent());
    xsdFixedFacetEClass.getESuperTypes().add(this.getXSDConstrainingFacet());
    xsdFractionDigitsFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdFundamentalFacetEClass.getESuperTypes().add(this.getXSDFacet());
    xsdIdentityConstraintDefinitionEClass.getESuperTypes().add(this.getXSDNamedComponent());
    xsdImportEClass.getESuperTypes().add(this.getXSDSchemaDirective());
    xsdIncludeEClass.getESuperTypes().add(this.getXSDSchemaCompositor());
    xsdLengthFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdMaxExclusiveFacetEClass.getESuperTypes().add(this.getXSDMaxFacet());
    xsdMaxFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdMaxInclusiveFacetEClass.getESuperTypes().add(this.getXSDMaxFacet());
    xsdMaxLengthFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdMinExclusiveFacetEClass.getESuperTypes().add(this.getXSDMinFacet());
    xsdMinFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdMinInclusiveFacetEClass.getESuperTypes().add(this.getXSDMinFacet());
    xsdMinLengthFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdModelGroupEClass.getESuperTypes().add(this.getXSDTerm());
    xsdModelGroupDefinitionEClass.getESuperTypes().add(this.getXSDRedefinableComponent());
    xsdModelGroupDefinitionEClass.getESuperTypes().add(this.getXSDParticleContent());
    xsdModelGroupDefinitionEClass.getESuperTypes().add(this.getXSDRedefineContent());
    xsdNamedComponentEClass.getESuperTypes().add(this.getXSDComponent());
    xsdNotationDeclarationEClass.getESuperTypes().add(this.getXSDNamedComponent());
    xsdNotationDeclarationEClass.getESuperTypes().add(this.getXSDSchemaContent());
    xsdNumericFacetEClass.getESuperTypes().add(this.getXSDFundamentalFacet());
    xsdOrderedFacetEClass.getESuperTypes().add(this.getXSDFundamentalFacet());
    xsdParticleEClass.getESuperTypes().add(this.getXSDComplexTypeContent());
    xsdParticleContentEClass.getESuperTypes().add(this.getXSDConcreteComponent());
    xsdPatternFacetEClass.getESuperTypes().add(this.getXSDRepeatableFacet());
    xsdRedefinableComponentEClass.getESuperTypes().add(this.getXSDNamedComponent());
    xsdRedefinableComponentEClass.getESuperTypes().add(this.getXSDRedefineContent());
    xsdRedefineContentEClass.getESuperTypes().add(this.getXSDSchemaContent());
    xsdRedefineEClass.getESuperTypes().add(this.getXSDSchemaCompositor());
    xsdRepeatableFacetEClass.getESuperTypes().add(this.getXSDConstrainingFacet());
    xsdSchemaEClass.getESuperTypes().add(this.getXSDScope());
    xsdSchemaCompositorEClass.getESuperTypes().add(this.getXSDSchemaDirective());
    xsdSchemaContentEClass.getESuperTypes().add(this.getXSDConcreteComponent());
    xsdSchemaDirectiveEClass.getESuperTypes().add(this.getXSDSchemaContent());
    xsdScopeEClass.getESuperTypes().add(this.getXSDComponent());
    xsdSimpleTypeDefinitionEClass.getESuperTypes().add(this.getXSDTypeDefinition());
    xsdSimpleTypeDefinitionEClass.getESuperTypes().add(this.getXSDComplexTypeContent());
    xsdTermEClass.getESuperTypes().add(this.getXSDComponent());
    xsdTermEClass.getESuperTypes().add(this.getXSDParticleContent());
    xsdTotalDigitsFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdTypeDefinitionEClass.getESuperTypes().add(this.getXSDRedefinableComponent());
    xsdTypeDefinitionEClass.getESuperTypes().add(this.getXSDRedefineContent());
    xsdWhiteSpaceFacetEClass.getESuperTypes().add(this.getXSDFixedFacet());
    xsdWildcardEClass.getESuperTypes().add(this.getXSDTerm());
    xsdxPathDefinitionEClass.getESuperTypes().add(this.getXSDComponent());

    // Initialize classes and features; add operations and parameters
    initEClass(xsdAnnotationEClass, XSDAnnotation.class, "XSDAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDAnnotation_ApplicationInformation(), this.getDOMElement(), "applicationInformation", null, 0, -1, XSDAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDAnnotation_UserInformation(), this.getDOMElement(), "userInformation", null, 0, -1, XSDAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDAnnotation_Attributes(), this.getDOMAttr(), "attributes", null, 0, -1, XSDAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdAttributeDeclarationEClass, XSDAttributeDeclaration.class, "XSDAttributeDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDAttributeDeclaration_AttributeDeclarationReference(), ecorePackage.getEBoolean(), "attributeDeclarationReference", null, 0, 1, XSDAttributeDeclaration.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeDeclaration_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDAttributeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeDeclaration_AnonymousTypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "anonymousTypeDefinition", null, 0, 1, XSDAttributeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeDeclaration_TypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "typeDefinition", null, 1, 1, XSDAttributeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeDeclaration_ResolvedAttributeDeclaration(), this.getXSDAttributeDeclaration(), null, "resolvedAttributeDeclaration", null, 1, 1, XSDAttributeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdAttributeGroupContentEClass, XSDAttributeGroupContent.class, "XSDAttributeGroupContent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdAttributeGroupDefinitionEClass, XSDAttributeGroupDefinition.class, "XSDAttributeGroupDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDAttributeGroupDefinition_AttributeGroupDefinitionReference(), ecorePackage.getEBoolean(), "attributeGroupDefinitionReference", null, 0, 1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_Contents(), this.getXSDAttributeGroupContent(), null, "contents", null, 0, -1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_AttributeUses(), this.getXSDAttributeUse(), null, "attributeUses", null, 0, -1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_AttributeWildcardContent(), this.getXSDWildcard(), null, "attributeWildcardContent", null, 0, 1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_AttributeWildcard(), this.getXSDWildcard(), null, "attributeWildcard", null, 0, 1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_ResolvedAttributeGroupDefinition(), this.getXSDAttributeGroupDefinition(), null, "resolvedAttributeGroupDefinition", null, 1, 1, XSDAttributeGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeGroupDefinition_SyntheticWildcard(), this.getXSDWildcard(), null, "syntheticWildcard", null, 0, 1, XSDAttributeGroupDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdAttributeUseEClass, XSDAttributeUse.class, "XSDAttributeUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDAttributeUse_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDAttributeUse_Value(), this.getValue(), "value", null, 0, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDAttributeUse_Constraint(), this.getXSDConstraint(), "constraint", null, 0, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDAttributeUse_Use(), this.getXSDAttributeUseCategory(), "use", null, 0, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDAttributeUse_LexicalValue(), ecorePackage.getEString(), "lexicalValue", null, 0, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeUse_AttributeDeclaration(), this.getXSDAttributeDeclaration(), null, "attributeDeclaration", null, 1, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDAttributeUse_Content(), this.getXSDAttributeDeclaration(), null, "content", null, 1, 1, XSDAttributeUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdBoundedFacetEClass, XSDBoundedFacet.class, "XSDBoundedFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDBoundedFacet_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, XSDBoundedFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdCardinalityFacetEClass, XSDCardinalityFacet.class, "XSDCardinalityFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDCardinalityFacet_Value(), this.getXSDCardinality(), "value", null, 0, 1, XSDCardinalityFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdComplexTypeContentEClass, XSDComplexTypeContent.class, "XSDComplexTypeContent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdComplexTypeDefinitionEClass, XSDComplexTypeDefinition.class, "XSDComplexTypeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDComplexTypeDefinition_DerivationMethod(), this.getXSDDerivationMethod(), "derivationMethod", "restriction", 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_Final(), this.getXSDComplexFinal(), "final", null, 0, -1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_ContentTypeCategory(), this.getXSDContentTypeCategory(), "contentTypeCategory", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_ProhibitedSubstitutions(), this.getXSDProhibitedSubstitutions(), "prohibitedSubstitutions", null, 0, -1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_LexicalFinal(), this.getXSDComplexFinal(), "lexicalFinal", null, 0, -1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_Block(), this.getXSDProhibitedSubstitutions(), "block", null, 0, -1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDComplexTypeDefinition_Mixed(), ecorePackage.getEBoolean(), "mixed", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_ContentAnnotation(), this.getXSDAnnotation(), null, "contentAnnotation", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_BaseTypeDefinition(), this.getXSDTypeDefinition(), null, "baseTypeDefinition", null, 1, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_Content(), this.getXSDComplexTypeContent(), null, "content", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_ContentType(), this.getXSDComplexTypeContent(), null, "contentType", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_AttributeUses(), this.getXSDAttributeUse(), null, "attributeUses", null, 0, -1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_AttributeContents(), this.getXSDAttributeGroupContent(), null, "attributeContents", null, 0, -1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_AttributeWildcard(), this.getXSDWildcard(), null, "attributeWildcard", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_AttributeWildcardContent(), this.getXSDWildcard(), null, "attributeWildcardContent", null, 0, 1, XSDComplexTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_RootTypeDefinition(), this.getXSDTypeDefinition(), null, "rootTypeDefinition", null, 1, 1, XSDComplexTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_SyntheticParticle(), this.getXSDParticle(), null, "syntheticParticle", null, 0, 1, XSDComplexTypeDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDComplexTypeDefinition_SyntheticWildcard(), this.getXSDWildcard(), null, "syntheticWildcard", null, 0, 1, XSDComplexTypeDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdComponentEClass, XSDComponent.class, "XSDComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdConcreteComponentEClass, XSDConcreteComponent.class, "XSDConcreteComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDConcreteComponent_Element(), this.getDOMElement(), "element", null, 0, 1, XSDConcreteComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDConcreteComponent_Container(), this.getXSDConcreteComponent(), null, "container", null, 0, 1, XSDConcreteComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDConcreteComponent_RootContainer(), this.getXSDConcreteComponent(), null, "rootContainer", null, 1, 1, XSDConcreteComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDConcreteComponent_Schema(), this.getXSDSchema(), null, "schema", null, 0, 1, XSDConcreteComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDConcreteComponent_Diagnostics(), this.getXSDDiagnostic(), null, "diagnostics", null, 0, -1, XSDConcreteComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdConstrainingFacetEClass, XSDConstrainingFacet.class, "XSDConstrainingFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdDiagnosticEClass, XSDDiagnostic.class, "XSDDiagnostic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDDiagnostic_Severity(), this.getXSDDiagnosticSeverity(), "severity", null, 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_Message(), ecorePackage.getEString(), "message", null, 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_LocationURI(), ecorePackage.getEString(), "locationURI", null, 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_Line(), ecorePackage.getEInt(), "line", "1", 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_Column(), ecorePackage.getEInt(), "column", "1", 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_Node(), this.getDOMNode(), "node", null, 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_AnnotationURI(), ecorePackage.getEString(), "annotationURI", null, 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_Key(), ecorePackage.getEString(), "key", null, 0, 1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDDiagnostic_Substitutions(), ecorePackage.getEString(), "substitutions", null, 0, -1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDDiagnostic_Components(), this.getXSDConcreteComponent(), null, "components", null, 1, -1, XSDDiagnostic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDDiagnostic_PrimaryComponent(), this.getXSDConcreteComponent(), null, "primaryComponent", null, 1, 1, XSDDiagnostic.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdElementDeclarationEClass, XSDElementDeclaration.class, "XSDElementDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDElementDeclaration_Nillable(), ecorePackage.getEBoolean(), "nillable", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_DisallowedSubstitutions(), this.getXSDDisallowedSubstitutions(), "disallowedSubstitutions", null, 0, -1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_SubstitutionGroupExclusions(), this.getXSDSubstitutionGroupExclusions(), "substitutionGroupExclusions", null, 0, -1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_LexicalFinal(), this.getXSDProhibitedSubstitutions(), "lexicalFinal", null, 0, -1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_Block(), this.getXSDDisallowedSubstitutions(), "block", null, 0, -1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_ElementDeclarationReference(), ecorePackage.getEBoolean(), "elementDeclarationReference", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDElementDeclaration_Circular(), ecorePackage.getEBoolean(), "circular", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_AnonymousTypeDefinition(), this.getXSDTypeDefinition(), null, "anonymousTypeDefinition", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_TypeDefinition(), this.getXSDTypeDefinition(), null, "typeDefinition", null, 1, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_IdentityConstraintDefinitions(), this.getXSDIdentityConstraintDefinition(), null, "identityConstraintDefinitions", null, 0, -1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_ResolvedElementDeclaration(), this.getXSDElementDeclaration(), null, "resolvedElementDeclaration", null, 1, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_SubstitutionGroupAffiliation(), this.getXSDElementDeclaration(), null, "substitutionGroupAffiliation", null, 0, 1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDElementDeclaration_SubstitutionGroup(), this.getXSDElementDeclaration(), null, "substitutionGroup", null, 0, -1, XSDElementDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdEnumerationFacetEClass, XSDEnumerationFacet.class, "XSDEnumerationFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDEnumerationFacet_Value(), this.getValue(), "value", null, 0, -1, XSDEnumerationFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdFacetEClass, XSDFacet.class, "XSDFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDFacet_LexicalValue(), ecorePackage.getEString(), "lexicalValue", null, 0, 1, XSDFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFacet_FacetName(), ecorePackage.getEString(), "facetName", null, 0, 1, XSDFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFacet_EffectiveValue(), this.getValue(), "effectiveValue", null, 0, 1, XSDFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDFacet_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDFacet_SimpleTypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "simpleTypeDefinition", null, 0, 1, XSDFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdFeatureEClass, XSDFeature.class, "XSDFeature", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDFeature_Value(), this.getValue(), "value", null, 0, 1, XSDFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFeature_Constraint(), this.getXSDConstraint(), "constraint", null, 0, 1, XSDFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFeature_Form(), this.getXSDForm(), "form", null, 0, 1, XSDFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFeature_LexicalValue(), ecorePackage.getEString(), "lexicalValue", null, 0, 1, XSDFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFeature_Global(), ecorePackage.getEBoolean(), "global", null, 0, 1, XSDFeature.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDFeature_FeatureReference(), ecorePackage.getEBoolean(), "featureReference", null, 0, 1, XSDFeature.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDFeature_Scope(), this.getXSDScope(), null, "scope", null, 0, 1, XSDFeature.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDFeature_ResolvedFeature(), this.getXSDFeature(), null, "resolvedFeature", null, 1, 1, XSDFeature.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDFeature_Type(), this.getXSDTypeDefinition(), null, "type", null, 1, 1, XSDFeature.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdFixedFacetEClass, XSDFixedFacet.class, "XSDFixedFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDFixedFacet_Fixed(), ecorePackage.getEBoolean(), "fixed", null, 0, 1, XSDFixedFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdFractionDigitsFacetEClass, XSDFractionDigitsFacet.class, "XSDFractionDigitsFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDFractionDigitsFacet_Value(), ecorePackage.getEInt(), "value", null, 0, 1, XSDFractionDigitsFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdFundamentalFacetEClass, XSDFundamentalFacet.class, "XSDFundamentalFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdIdentityConstraintDefinitionEClass, XSDIdentityConstraintDefinition.class, "XSDIdentityConstraintDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDIdentityConstraintDefinition_IdentityConstraintCategory(), this.getXSDIdentityConstraintCategory(), "identityConstraintCategory", null, 0, 1, XSDIdentityConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDIdentityConstraintDefinition_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDIdentityConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDIdentityConstraintDefinition_ReferencedKey(), this.getXSDIdentityConstraintDefinition(), null, "referencedKey", null, 0, 1, XSDIdentityConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDIdentityConstraintDefinition_Selector(), this.getXSDXPathDefinition(), null, "selector", null, 1, 1, XSDIdentityConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDIdentityConstraintDefinition_Fields(), this.getXSDXPathDefinition(), null, "fields", null, 1, -1, XSDIdentityConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdImportEClass, XSDImport.class, "XSDImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDImport_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1, XSDImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDImport_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdIncludeEClass, XSDInclude.class, "XSDInclude", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXSDInclude_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDInclude.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdLengthFacetEClass, XSDLengthFacet.class, "XSDLengthFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDLengthFacet_Value(), ecorePackage.getEInt(), "value", null, 0, 1, XSDLengthFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdMaxExclusiveFacetEClass, XSDMaxExclusiveFacet.class, "XSDMaxExclusiveFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdMaxFacetEClass, XSDMaxFacet.class, "XSDMaxFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDMaxFacet_Value(), this.getValue(), "value", null, 0, 1, XSDMaxFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDMaxFacet_Inclusive(), ecorePackage.getEBoolean(), "inclusive", null, 0, 1, XSDMaxFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDMaxFacet_Exclusive(), ecorePackage.getEBoolean(), "exclusive", null, 0, 1, XSDMaxFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdMaxInclusiveFacetEClass, XSDMaxInclusiveFacet.class, "XSDMaxInclusiveFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdMaxLengthFacetEClass, XSDMaxLengthFacet.class, "XSDMaxLengthFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDMaxLengthFacet_Value(), ecorePackage.getEInt(), "value", null, 0, 1, XSDMaxLengthFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdMinExclusiveFacetEClass, XSDMinExclusiveFacet.class, "XSDMinExclusiveFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdMinFacetEClass, XSDMinFacet.class, "XSDMinFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDMinFacet_Value(), this.getValue(), "value", null, 0, 1, XSDMinFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDMinFacet_Inclusive(), ecorePackage.getEBoolean(), "inclusive", null, 0, 1, XSDMinFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDMinFacet_Exclusive(), ecorePackage.getEBoolean(), "exclusive", null, 0, 1, XSDMinFacet.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdMinInclusiveFacetEClass, XSDMinInclusiveFacet.class, "XSDMinInclusiveFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdMinLengthFacetEClass, XSDMinLengthFacet.class, "XSDMinLengthFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDMinLengthFacet_Value(), ecorePackage.getEInt(), "value", null, 0, 1, XSDMinLengthFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdModelGroupEClass, XSDModelGroup.class, "XSDModelGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDModelGroup_Compositor(), this.getXSDCompositor(), "compositor", null, 0, 1, XSDModelGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDModelGroup_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDModelGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDModelGroup_Contents(), this.getXSDParticle(), null, "contents", null, 0, -1, XSDModelGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDModelGroup_Particles(), this.getXSDParticle(), null, "particles", null, 1, -1, XSDModelGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdModelGroupDefinitionEClass, XSDModelGroupDefinition.class, "XSDModelGroupDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDModelGroupDefinition_ModelGroupDefinitionReference(), ecorePackage.getEBoolean(), "modelGroupDefinitionReference", null, 0, 1, XSDModelGroupDefinition.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDModelGroupDefinition_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDModelGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDModelGroupDefinition_ModelGroup(), this.getXSDModelGroup(), null, "modelGroup", null, 1, 1, XSDModelGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDModelGroupDefinition_ResolvedModelGroupDefinition(), this.getXSDModelGroupDefinition(), null, "resolvedModelGroupDefinition", null, 1, 1, XSDModelGroupDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdNamedComponentEClass, XSDNamedComponent.class, "XSDNamedComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDNamedComponent_Name(), ecorePackage.getEString(), "name", null, 0, 1, XSDNamedComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDNamedComponent_TargetNamespace(), ecorePackage.getEString(), "targetNamespace", null, 0, 1, XSDNamedComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDNamedComponent_AliasName(), ecorePackage.getEString(), "aliasName", null, 0, 1, XSDNamedComponent.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDNamedComponent_URI(), ecorePackage.getEString(), "uRI", null, 0, 1, XSDNamedComponent.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDNamedComponent_AliasURI(), ecorePackage.getEString(), "aliasURI", null, 0, 1, XSDNamedComponent.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDNamedComponent_QName(), ecorePackage.getEString(), "qName", null, 0, 1, XSDNamedComponent.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdNotationDeclarationEClass, XSDNotationDeclaration.class, "XSDNotationDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDNotationDeclaration_SystemIdentifier(), ecorePackage.getEString(), "systemIdentifier", null, 0, 1, XSDNotationDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDNotationDeclaration_PublicIdentifier(), ecorePackage.getEString(), "publicIdentifier", null, 0, 1, XSDNotationDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDNotationDeclaration_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDNotationDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdNumericFacetEClass, XSDNumericFacet.class, "XSDNumericFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDNumericFacet_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, XSDNumericFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdOrderedFacetEClass, XSDOrderedFacet.class, "XSDOrderedFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDOrderedFacet_Value(), this.getXSDOrdered(), "value", null, 0, 1, XSDOrderedFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdParticleEClass, XSDParticle.class, "XSDParticle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDParticle_MinOccurs(), ecorePackage.getEInt(), "minOccurs", "1", 0, 1, XSDParticle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDParticle_MaxOccurs(), ecorePackage.getEInt(), "maxOccurs", "1", 0, 1, XSDParticle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDParticle_Content(), this.getXSDParticleContent(), null, "content", null, 1, 1, XSDParticle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDParticle_Term(), this.getXSDTerm(), null, "term", null, 1, 1, XSDParticle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdParticleContentEClass, XSDParticleContent.class, "XSDParticleContent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdPatternFacetEClass, XSDPatternFacet.class, "XSDPatternFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDPatternFacet_Value(), ecorePackage.getEString(), "value", null, 0, -1, XSDPatternFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdRedefinableComponentEClass, XSDRedefinableComponent.class, "XSDRedefinableComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDRedefinableComponent_Circular(), ecorePackage.getEBoolean(), "circular", null, 0, 1, XSDRedefinableComponent.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdRedefineContentEClass, XSDRedefineContent.class, "XSDRedefineContent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdRedefineEClass, XSDRedefine.class, "XSDRedefine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXSDRedefine_Annotations(), this.getXSDAnnotation(), null, "annotations", null, 0, -1, XSDRedefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDRedefine_Contents(), this.getXSDRedefineContent(), null, "contents", null, 0, -1, XSDRedefine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdRepeatableFacetEClass, XSDRepeatableFacet.class, "XSDRepeatableFacet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXSDRepeatableFacet_Annotations(), this.getXSDAnnotation(), null, "annotations", null, 0, -1, XSDRepeatableFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdSchemaEClass, XSDSchema.class, "XSDSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDSchema_Document(), this.getDOMDocument(), "document", null, 0, 1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_SchemaLocation(), ecorePackage.getEString(), "schemaLocation", null, 0, 1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_TargetNamespace(), ecorePackage.getEString(), "targetNamespace", null, 0, 1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_AttributeFormDefault(), this.getXSDForm(), "attributeFormDefault", "unqualified", 0, 1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_ElementFormDefault(), this.getXSDForm(), "elementFormDefault", "unqualified", 0, 1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_FinalDefault(), this.getXSDProhibitedSubstitutions(), "finalDefault", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_BlockDefault(), this.getXSDDisallowedSubstitutions(), "blockDefault", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSchema_Version(), ecorePackage.getEString(), "version", null, 0, 1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_Contents(), this.getXSDSchemaContent(), null, "contents", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_ElementDeclarations(), this.getXSDElementDeclaration(), null, "elementDeclarations", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_AttributeDeclarations(), this.getXSDAttributeDeclaration(), null, "attributeDeclarations", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_AttributeGroupDefinitions(), this.getXSDAttributeGroupDefinition(), null, "attributeGroupDefinitions", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_TypeDefinitions(), this.getXSDTypeDefinition(), null, "typeDefinitions", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_ModelGroupDefinitions(), this.getXSDModelGroupDefinition(), null, "modelGroupDefinitions", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_IdentityConstraintDefinitions(), this.getXSDIdentityConstraintDefinition(), null, "identityConstraintDefinitions", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_NotationDeclarations(), this.getXSDNotationDeclaration(), null, "notationDeclarations", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_Annotations(), this.getXSDAnnotation(), null, "annotations", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_AllDiagnostics(), this.getXSDDiagnostic(), null, "allDiagnostics", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_ReferencingDirectives(), this.getXSDSchemaDirective(), null, "referencingDirectives", null, 0, -1, XSDSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_RootVersion(), this.getXSDSchema(), null, "rootVersion", null, 1, 1, XSDSchema.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_OriginalVersion(), this.getXSDSchema(), null, "originalVersion", null, 0, 1, XSDSchema.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_IncorporatedVersions(), this.getXSDSchema(), null, "incorporatedVersions", null, 0, -1, XSDSchema.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchema_SchemaForSchema(), this.getXSDSchema(), null, "schemaForSchema", null, 1, 1, XSDSchema.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdSchemaCompositorEClass, XSDSchemaCompositor.class, "XSDSchemaCompositor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXSDSchemaCompositor_IncorporatedSchema(), this.getXSDSchema(), null, "incorporatedSchema", null, 0, 1, XSDSchemaCompositor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdSchemaContentEClass, XSDSchemaContent.class, "XSDSchemaContent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdSchemaDirectiveEClass, XSDSchemaDirective.class, "XSDSchemaDirective", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDSchemaDirective_SchemaLocation(), ecorePackage.getEString(), "schemaLocation", null, 0, 1, XSDSchemaDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSchemaDirective_ResolvedSchema(), this.getXSDSchema(), null, "resolvedSchema", null, 0, 1, XSDSchemaDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdScopeEClass, XSDScope.class, "XSDScope", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdSimpleTypeDefinitionEClass, XSDSimpleTypeDefinition.class, "XSDSimpleTypeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDSimpleTypeDefinition_Variety(), this.getXSDVariety(), "variety", null, 0, 1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSimpleTypeDefinition_Final(), this.getXSDSimpleFinal(), "final", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSimpleTypeDefinition_LexicalFinal(), this.getXSDSimpleFinal(), "lexicalFinal", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDSimpleTypeDefinition_ValidFacets(), ecorePackage.getEString(), "validFacets", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_Contents(), this.getXSDSimpleTypeDefinition(), null, "contents", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_FacetContents(), this.getXSDConstrainingFacet(), null, "facetContents", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_Facets(), this.getXSDConstrainingFacet(), null, "facets", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MemberTypeDefinitions(), this.getXSDSimpleTypeDefinition(), null, "memberTypeDefinitions", null, 0, -1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_FundamentalFacets(), this.getXSDFundamentalFacet(), null, "fundamentalFacets", null, 1, -1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_BaseTypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "baseTypeDefinition", null, 1, 1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_PrimitiveTypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "primitiveTypeDefinition", null, 0, 1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_ItemTypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "itemTypeDefinition", null, 0, 1, XSDSimpleTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_RootTypeDefinition(), this.getXSDSimpleTypeDefinition(), null, "rootTypeDefinition", null, 1, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MinFacet(), this.getXSDMinFacet(), null, "minFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MaxFacet(), this.getXSDMaxFacet(), null, "maxFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MaxInclusiveFacet(), this.getXSDMaxInclusiveFacet(), null, "maxInclusiveFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MinInclusiveFacet(), this.getXSDMinInclusiveFacet(), null, "minInclusiveFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MinExclusiveFacet(), this.getXSDMinExclusiveFacet(), null, "minExclusiveFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MaxExclusiveFacet(), this.getXSDMaxExclusiveFacet(), null, "maxExclusiveFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_LengthFacet(), this.getXSDLengthFacet(), null, "lengthFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_WhiteSpaceFacet(), this.getXSDWhiteSpaceFacet(), null, "whiteSpaceFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EnumerationFacets(), this.getXSDEnumerationFacet(), null, "enumerationFacets", null, 0, -1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_PatternFacets(), this.getXSDPatternFacet(), null, "patternFacets", null, 0, -1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_CardinalityFacet(), this.getXSDCardinalityFacet(), null, "cardinalityFacet", null, 1, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_NumericFacet(), this.getXSDNumericFacet(), null, "numericFacet", null, 1, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MaxLengthFacet(), this.getXSDMaxLengthFacet(), null, "maxLengthFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_MinLengthFacet(), this.getXSDMinLengthFacet(), null, "minLengthFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_TotalDigitsFacet(), this.getXSDTotalDigitsFacet(), null, "totalDigitsFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_FractionDigitsFacet(), this.getXSDFractionDigitsFacet(), null, "fractionDigitsFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_OrderedFacet(), this.getXSDOrderedFacet(), null, "orderedFacet", null, 1, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_BoundedFacet(), this.getXSDBoundedFacet(), null, "boundedFacet", null, 1, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveMaxFacet(), this.getXSDMaxFacet(), null, "effectiveMaxFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveWhiteSpaceFacet(), this.getXSDWhiteSpaceFacet(), null, "effectiveWhiteSpaceFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveMaxLengthFacet(), this.getXSDMaxLengthFacet(), null, "effectiveMaxLengthFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveFractionDigitsFacet(), this.getXSDFractionDigitsFacet(), null, "effectiveFractionDigitsFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectivePatternFacet(), this.getXSDPatternFacet(), null, "effectivePatternFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveEnumerationFacet(), this.getXSDEnumerationFacet(), null, "effectiveEnumerationFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveTotalDigitsFacet(), this.getXSDTotalDigitsFacet(), null, "effectiveTotalDigitsFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveMinLengthFacet(), this.getXSDMinLengthFacet(), null, "effectiveMinLengthFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveLengthFacet(), this.getXSDLengthFacet(), null, "effectiveLengthFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_EffectiveMinFacet(), this.getXSDMinFacet(), null, "effectiveMinFacet", null, 0, 1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDSimpleTypeDefinition_SyntheticFacets(), this.getXSDFacet(), null, "syntheticFacets", null, 0, -1, XSDSimpleTypeDefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdTermEClass, XSDTerm.class, "XSDTerm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(xsdTotalDigitsFacetEClass, XSDTotalDigitsFacet.class, "XSDTotalDigitsFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDTotalDigitsFacet_Value(), ecorePackage.getEInt(), "value", null, 0, 1, XSDTotalDigitsFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdTypeDefinitionEClass, XSDTypeDefinition.class, "XSDTypeDefinition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXSDTypeDefinition_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDTypeDefinition_DerivationAnnotation(), this.getXSDAnnotation(), null, "derivationAnnotation", null, 0, 1, XSDTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDTypeDefinition_Annotations(), this.getXSDAnnotation(), null, "annotations", null, 0, -1, XSDTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDTypeDefinition_RootType(), this.getXSDTypeDefinition(), null, "rootType", null, 1, 1, XSDTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDTypeDefinition_BaseType(), this.getXSDTypeDefinition(), null, "baseType", null, 1, 1, XSDTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDTypeDefinition_SimpleType(), this.getXSDSimpleTypeDefinition(), null, "simpleType", null, 0, 1, XSDTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDTypeDefinition_ComplexType(), this.getXSDParticle(), null, "complexType", null, 0, 1, XSDTypeDefinition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdWhiteSpaceFacetEClass, XSDWhiteSpaceFacet.class, "XSDWhiteSpaceFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDWhiteSpaceFacet_Value(), this.getXSDWhiteSpace(), "value", null, 0, 1, XSDWhiteSpaceFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdWildcardEClass, XSDWildcard.class, "XSDWildcard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDWildcard_NamespaceConstraintCategory(), this.getXSDNamespaceConstraintCategory(), "namespaceConstraintCategory", null, 0, 1, XSDWildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDWildcard_NamespaceConstraint(), ecorePackage.getEString(), "namespaceConstraint", null, 0, -1, XSDWildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDWildcard_ProcessContents(), this.getXSDProcessContents(), "processContents", null, 0, 1, XSDWildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDWildcard_LexicalNamespaceConstraint(), ecorePackage.getEString(), "lexicalNamespaceConstraint", null, 0, -1, XSDWildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDWildcard_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDWildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDWildcard_Annotations(), this.getXSDAnnotation(), null, "annotations", null, 0, -1, XSDWildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xsdxPathDefinitionEClass, XSDXPathDefinition.class, "XSDXPathDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getXSDXPathDefinition_Variety(), this.getXSDXPathVariety(), "variety", null, 0, 1, XSDXPathDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXSDXPathDefinition_Value(), ecorePackage.getEString(), "value", null, 0, 1, XSDXPathDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXSDXPathDefinition_Annotation(), this.getXSDAnnotation(), null, "annotation", null, 0, 1, XSDXPathDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(xsdAttributeUseCategoryEEnum, XSDAttributeUseCategory.class, "XSDAttributeUseCategory");
    addEEnumLiteral(xsdAttributeUseCategoryEEnum, XSDAttributeUseCategory.OPTIONAL_LITERAL);
    addEEnumLiteral(xsdAttributeUseCategoryEEnum, XSDAttributeUseCategory.PROHIBITED_LITERAL);
    addEEnumLiteral(xsdAttributeUseCategoryEEnum, XSDAttributeUseCategory.REQUIRED_LITERAL);

    initEEnum(xsdCardinalityEEnum, XSDCardinality.class, "XSDCardinality");
    addEEnumLiteral(xsdCardinalityEEnum, XSDCardinality.FINITE_LITERAL);
    addEEnumLiteral(xsdCardinalityEEnum, XSDCardinality.COUNTABLY_INFINITE_LITERAL);

    initEEnum(xsdComplexFinalEEnum, XSDComplexFinal.class, "XSDComplexFinal");
    addEEnumLiteral(xsdComplexFinalEEnum, XSDComplexFinal.EXTENSION_LITERAL);
    addEEnumLiteral(xsdComplexFinalEEnum, XSDComplexFinal.RESTRICTION_LITERAL);
    addEEnumLiteral(xsdComplexFinalEEnum, XSDComplexFinal.ALL_LITERAL);

    initEEnum(xsdCompositorEEnum, XSDCompositor.class, "XSDCompositor");
    addEEnumLiteral(xsdCompositorEEnum, XSDCompositor.ALL_LITERAL);
    addEEnumLiteral(xsdCompositorEEnum, XSDCompositor.CHOICE_LITERAL);
    addEEnumLiteral(xsdCompositorEEnum, XSDCompositor.SEQUENCE_LITERAL);

    initEEnum(xsdConstraintEEnum, XSDConstraint.class, "XSDConstraint");
    addEEnumLiteral(xsdConstraintEEnum, XSDConstraint.DEFAULT_LITERAL);
    addEEnumLiteral(xsdConstraintEEnum, XSDConstraint.FIXED_LITERAL);

    initEEnum(xsdContentTypeCategoryEEnum, XSDContentTypeCategory.class, "XSDContentTypeCategory");
    addEEnumLiteral(xsdContentTypeCategoryEEnum, XSDContentTypeCategory.EMPTY_LITERAL);
    addEEnumLiteral(xsdContentTypeCategoryEEnum, XSDContentTypeCategory.SIMPLE_LITERAL);
    addEEnumLiteral(xsdContentTypeCategoryEEnum, XSDContentTypeCategory.MIXED_LITERAL);
    addEEnumLiteral(xsdContentTypeCategoryEEnum, XSDContentTypeCategory.ELEMENT_ONLY_LITERAL);

    initEEnum(xsdDerivationMethodEEnum, XSDDerivationMethod.class, "XSDDerivationMethod");
    addEEnumLiteral(xsdDerivationMethodEEnum, XSDDerivationMethod.EXTENSION_LITERAL);
    addEEnumLiteral(xsdDerivationMethodEEnum, XSDDerivationMethod.RESTRICTION_LITERAL);

    initEEnum(xsdDiagnosticSeverityEEnum, XSDDiagnosticSeverity.class, "XSDDiagnosticSeverity");
    addEEnumLiteral(xsdDiagnosticSeverityEEnum, XSDDiagnosticSeverity.FATAL_LITERAL);
    addEEnumLiteral(xsdDiagnosticSeverityEEnum, XSDDiagnosticSeverity.ERROR_LITERAL);
    addEEnumLiteral(xsdDiagnosticSeverityEEnum, XSDDiagnosticSeverity.WARNING_LITERAL);
    addEEnumLiteral(xsdDiagnosticSeverityEEnum, XSDDiagnosticSeverity.INFORMATION_LITERAL);

    initEEnum(xsdDisallowedSubstitutionsEEnum, XSDDisallowedSubstitutions.class, "XSDDisallowedSubstitutions");
    addEEnumLiteral(xsdDisallowedSubstitutionsEEnum, XSDDisallowedSubstitutions.SUBSTITUTION_LITERAL);
    addEEnumLiteral(xsdDisallowedSubstitutionsEEnum, XSDDisallowedSubstitutions.EXTENSION_LITERAL);
    addEEnumLiteral(xsdDisallowedSubstitutionsEEnum, XSDDisallowedSubstitutions.RESTRICTION_LITERAL);
    addEEnumLiteral(xsdDisallowedSubstitutionsEEnum, XSDDisallowedSubstitutions.ALL_LITERAL);

    initEEnum(xsdFormEEnum, XSDForm.class, "XSDForm");
    addEEnumLiteral(xsdFormEEnum, XSDForm.QUALIFIED_LITERAL);
    addEEnumLiteral(xsdFormEEnum, XSDForm.UNQUALIFIED_LITERAL);

    initEEnum(xsdIdentityConstraintCategoryEEnum, XSDIdentityConstraintCategory.class, "XSDIdentityConstraintCategory");
    addEEnumLiteral(xsdIdentityConstraintCategoryEEnum, XSDIdentityConstraintCategory.KEY_LITERAL);
    addEEnumLiteral(xsdIdentityConstraintCategoryEEnum, XSDIdentityConstraintCategory.KEYREF_LITERAL);
    addEEnumLiteral(xsdIdentityConstraintCategoryEEnum, XSDIdentityConstraintCategory.UNIQUE_LITERAL);

    initEEnum(xsdNamespaceConstraintCategoryEEnum, XSDNamespaceConstraintCategory.class, "XSDNamespaceConstraintCategory");
    addEEnumLiteral(xsdNamespaceConstraintCategoryEEnum, XSDNamespaceConstraintCategory.ANY_LITERAL);
    addEEnumLiteral(xsdNamespaceConstraintCategoryEEnum, XSDNamespaceConstraintCategory.NOT_LITERAL);
    addEEnumLiteral(xsdNamespaceConstraintCategoryEEnum, XSDNamespaceConstraintCategory.SET_LITERAL);

    initEEnum(xsdOrderedEEnum, XSDOrdered.class, "XSDOrdered");
    addEEnumLiteral(xsdOrderedEEnum, XSDOrdered.FALSE_LITERAL);
    addEEnumLiteral(xsdOrderedEEnum, XSDOrdered.PARTIAL_LITERAL);
    addEEnumLiteral(xsdOrderedEEnum, XSDOrdered.TOTAL_LITERAL);

    initEEnum(xsdProcessContentsEEnum, XSDProcessContents.class, "XSDProcessContents");
    addEEnumLiteral(xsdProcessContentsEEnum, XSDProcessContents.STRICT_LITERAL);
    addEEnumLiteral(xsdProcessContentsEEnum, XSDProcessContents.LAX_LITERAL);
    addEEnumLiteral(xsdProcessContentsEEnum, XSDProcessContents.SKIP_LITERAL);

    initEEnum(xsdProhibitedSubstitutionsEEnum, XSDProhibitedSubstitutions.class, "XSDProhibitedSubstitutions");
    addEEnumLiteral(xsdProhibitedSubstitutionsEEnum, XSDProhibitedSubstitutions.EXTENSION_LITERAL);
    addEEnumLiteral(xsdProhibitedSubstitutionsEEnum, XSDProhibitedSubstitutions.RESTRICTION_LITERAL);
    addEEnumLiteral(xsdProhibitedSubstitutionsEEnum, XSDProhibitedSubstitutions.ALL_LITERAL);

    initEEnum(xsdSimpleFinalEEnum, XSDSimpleFinal.class, "XSDSimpleFinal");
    addEEnumLiteral(xsdSimpleFinalEEnum, XSDSimpleFinal.LIST_LITERAL);
    addEEnumLiteral(xsdSimpleFinalEEnum, XSDSimpleFinal.RESTRICTION_LITERAL);
    addEEnumLiteral(xsdSimpleFinalEEnum, XSDSimpleFinal.UNION_LITERAL);
    addEEnumLiteral(xsdSimpleFinalEEnum, XSDSimpleFinal.ALL_LITERAL);

    initEEnum(xsdSubstitutionGroupExclusionsEEnum, XSDSubstitutionGroupExclusions.class, "XSDSubstitutionGroupExclusions");
    addEEnumLiteral(xsdSubstitutionGroupExclusionsEEnum, XSDSubstitutionGroupExclusions.EXTENSION_LITERAL);
    addEEnumLiteral(xsdSubstitutionGroupExclusionsEEnum, XSDSubstitutionGroupExclusions.RESTRICTION_LITERAL);

    initEEnum(xsdVarietyEEnum, XSDVariety.class, "XSDVariety");
    addEEnumLiteral(xsdVarietyEEnum, XSDVariety.ATOMIC_LITERAL);
    addEEnumLiteral(xsdVarietyEEnum, XSDVariety.LIST_LITERAL);
    addEEnumLiteral(xsdVarietyEEnum, XSDVariety.UNION_LITERAL);

    initEEnum(xsdWhiteSpaceEEnum, XSDWhiteSpace.class, "XSDWhiteSpace");
    addEEnumLiteral(xsdWhiteSpaceEEnum, XSDWhiteSpace.PRESERVE_LITERAL);
    addEEnumLiteral(xsdWhiteSpaceEEnum, XSDWhiteSpace.REPLACE_LITERAL);
    addEEnumLiteral(xsdWhiteSpaceEEnum, XSDWhiteSpace.COLLAPSE_LITERAL);

    initEEnum(xsdxPathVarietyEEnum, XSDXPathVariety.class, "XSDXPathVariety");
    addEEnumLiteral(xsdxPathVarietyEEnum, XSDXPathVariety.SELECTOR_LITERAL);
    addEEnumLiteral(xsdxPathVarietyEEnum, XSDXPathVariety.FIELD_LITERAL);

    // Initialize data types
    initEDataType(domAttrEDataType, Attr.class, "DOMAttr", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(domDocumentEDataType, Document.class, "DOMDocument", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(domElementEDataType, Element.class, "DOMElement", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(domNodeEDataType, Node.class, "DOMNode", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(valueEDataType, Object.class, "Value", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link XSDPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static XSDPackage init()
  {
    if (isInited) return (XSDPackage)EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI);

    // Obtain or create and register package
    XSDPackageImpl theXSDPackage = (XSDPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XSDPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XSDPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theXSDPackage.createPackageContents();

    // Initialize created meta-data
    theXSDPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theXSDPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(XSDPackage.eNS_URI, theXSDPackage);
    return theXSDPackage;
  }

} //XSDPackageImpl
