/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDFactoryImpl.java,v 1.3 2004/05/16 16:47:39 emerks Exp $
 */
package org.eclipse.xsd.impl;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.xsd.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XSDFactoryImpl extends EFactoryImpl implements XSDFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case XSDPackage.XSD_ANNOTATION: return createXSDAnnotation();
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION: return createXSDAttributeDeclaration();
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION: return createXSDAttributeGroupDefinition();
      case XSDPackage.XSD_ATTRIBUTE_USE: return createXSDAttributeUse();
      case XSDPackage.XSD_BOUNDED_FACET: return createXSDBoundedFacet();
      case XSDPackage.XSD_CARDINALITY_FACET: return createXSDCardinalityFacet();
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION: return createXSDComplexTypeDefinition();
      case XSDPackage.XSD_DIAGNOSTIC: return createXSDDiagnostic();
      case XSDPackage.XSD_ELEMENT_DECLARATION: return createXSDElementDeclaration();
      case XSDPackage.XSD_ENUMERATION_FACET: return createXSDEnumerationFacet();
      case XSDPackage.XSD_FRACTION_DIGITS_FACET: return createXSDFractionDigitsFacet();
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION: return createXSDIdentityConstraintDefinition();
      case XSDPackage.XSD_IMPORT: return createXSDImport();
      case XSDPackage.XSD_INCLUDE: return createXSDInclude();
      case XSDPackage.XSD_LENGTH_FACET: return createXSDLengthFacet();
      case XSDPackage.XSD_MAX_EXCLUSIVE_FACET: return createXSDMaxExclusiveFacet();
      case XSDPackage.XSD_MAX_INCLUSIVE_FACET: return createXSDMaxInclusiveFacet();
      case XSDPackage.XSD_MAX_LENGTH_FACET: return createXSDMaxLengthFacet();
      case XSDPackage.XSD_MIN_EXCLUSIVE_FACET: return createXSDMinExclusiveFacet();
      case XSDPackage.XSD_MIN_INCLUSIVE_FACET: return createXSDMinInclusiveFacet();
      case XSDPackage.XSD_MIN_LENGTH_FACET: return createXSDMinLengthFacet();
      case XSDPackage.XSD_MODEL_GROUP: return createXSDModelGroup();
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION: return createXSDModelGroupDefinition();
      case XSDPackage.XSD_NOTATION_DECLARATION: return createXSDNotationDeclaration();
      case XSDPackage.XSD_NUMERIC_FACET: return createXSDNumericFacet();
      case XSDPackage.XSD_ORDERED_FACET: return createXSDOrderedFacet();
      case XSDPackage.XSD_PARTICLE: return createXSDParticle();
      case XSDPackage.XSD_PATTERN_FACET: return createXSDPatternFacet();
      case XSDPackage.XSD_REDEFINE: return createXSDRedefine();
      case XSDPackage.XSD_SCHEMA: return createXSDSchema();
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION: return createXSDSimpleTypeDefinition();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET: return createXSDTotalDigitsFacet();
      case XSDPackage.XSD_WHITE_SPACE_FACET: return createXSDWhiteSpaceFacet();
      case XSDPackage.XSD_WILDCARD: return createXSDWildcard();
      case XSDPackage.XSD_XPATH_DEFINITION: return createXSDXPathDefinition();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XSDPackage.XSD_ATTRIBUTE_USE_CATEGORY:
      {
        XSDAttributeUseCategory result = XSDAttributeUseCategory.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_CARDINALITY:
      {
        XSDCardinality result = XSDCardinality.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_COMPLEX_FINAL:
      {
        XSDComplexFinal result = XSDComplexFinal.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_COMPOSITOR:
      {
        XSDCompositor result = XSDCompositor.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_CONSTRAINT:
      {
        XSDConstraint result = XSDConstraint.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_CONTENT_TYPE_CATEGORY:
      {
        XSDContentTypeCategory result = XSDContentTypeCategory.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_DERIVATION_METHOD:
      {
        XSDDerivationMethod result = XSDDerivationMethod.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_DIAGNOSTIC_SEVERITY:
      {
        XSDDiagnosticSeverity result = XSDDiagnosticSeverity.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_DISALLOWED_SUBSTITUTIONS:
      {
        XSDDisallowedSubstitutions result = XSDDisallowedSubstitutions.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_FORM:
      {
        XSDForm result = XSDForm.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_CATEGORY:
      {
        XSDIdentityConstraintCategory result = XSDIdentityConstraintCategory.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_NAMESPACE_CONSTRAINT_CATEGORY:
      {
        XSDNamespaceConstraintCategory result = XSDNamespaceConstraintCategory.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_ORDERED:
      {
        XSDOrdered result = XSDOrdered.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_PROCESS_CONTENTS:
      {
        XSDProcessContents result = XSDProcessContents.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_PROHIBITED_SUBSTITUTIONS:
      {
        XSDProhibitedSubstitutions result = XSDProhibitedSubstitutions.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_SIMPLE_FINAL:
      {
        XSDSimpleFinal result = XSDSimpleFinal.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_SUBSTITUTION_GROUP_EXCLUSIONS:
      {
        XSDSubstitutionGroupExclusions result = XSDSubstitutionGroupExclusions.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_VARIETY:
      {
        XSDVariety result = XSDVariety.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_WHITE_SPACE:
      {
        XSDWhiteSpace result = XSDWhiteSpace.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.XSD_XPATH_VARIETY:
      {
        XSDXPathVariety result = XSDXPathVariety.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XSDPackage.DOM_ATTR:
        return createDOMAttrFromString(eDataType, initialValue);
      case XSDPackage.DOM_DOCUMENT:
        return createDOMDocumentFromString(eDataType, initialValue);
      case XSDPackage.DOM_ELEMENT:
        return createDOMElementFromString(eDataType, initialValue);
      case XSDPackage.DOM_NODE:
        return createDOMNodeFromString(eDataType, initialValue);
      case XSDPackage.VALUE:
        return createValueFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XSDPackage.XSD_ATTRIBUTE_USE_CATEGORY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_CARDINALITY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_COMPLEX_FINAL:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_COMPOSITOR:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_CONSTRAINT:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_CONTENT_TYPE_CATEGORY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_DERIVATION_METHOD:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_DIAGNOSTIC_SEVERITY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_DISALLOWED_SUBSTITUTIONS:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_FORM:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_CATEGORY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_NAMESPACE_CONSTRAINT_CATEGORY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_ORDERED:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_PROCESS_CONTENTS:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_PROHIBITED_SUBSTITUTIONS:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_SIMPLE_FINAL:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_SUBSTITUTION_GROUP_EXCLUSIONS:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_VARIETY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_WHITE_SPACE:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.XSD_XPATH_VARIETY:
        return instanceValue == null ? null : instanceValue.toString();
      case XSDPackage.DOM_ATTR:
        return convertDOMAttrToString(eDataType, instanceValue);
      case XSDPackage.DOM_DOCUMENT:
        return convertDOMDocumentToString(eDataType, instanceValue);
      case XSDPackage.DOM_ELEMENT:
        return convertDOMElementToString(eDataType, instanceValue);
      case XSDPackage.DOM_NODE:
        return convertDOMNodeToString(eDataType, instanceValue);
      case XSDPackage.VALUE:
        return convertValueToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation createXSDAnnotation()
  {
    XSDAnnotationImpl xsdAnnotation = new XSDAnnotationImpl();
    return xsdAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeDeclaration createXSDAttributeDeclaration()
  {
    XSDAttributeDeclarationImpl xsdAttributeDeclaration = new XSDAttributeDeclarationImpl();
    return xsdAttributeDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeGroupDefinition createXSDAttributeGroupDefinition()
  {
    XSDAttributeGroupDefinitionImpl xsdAttributeGroupDefinition = new XSDAttributeGroupDefinitionImpl();
    return xsdAttributeGroupDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeUse createXSDAttributeUse()
  {
    XSDAttributeUseImpl xsdAttributeUse = new XSDAttributeUseImpl();
    return xsdAttributeUse;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDBoundedFacet createXSDBoundedFacet()
  {
    XSDBoundedFacetImpl xsdBoundedFacet = new XSDBoundedFacetImpl();
    return xsdBoundedFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDCardinalityFacet createXSDCardinalityFacet()
  {
    XSDCardinalityFacetImpl xsdCardinalityFacet = new XSDCardinalityFacetImpl();
    return xsdCardinalityFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDComplexTypeDefinition createXSDComplexTypeDefinition()
  {
    XSDComplexTypeDefinitionImpl xsdComplexTypeDefinition = new XSDComplexTypeDefinitionImpl();
    return xsdComplexTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDDiagnostic createXSDDiagnostic()
  {
    XSDDiagnosticImpl xsdDiagnostic = new XSDDiagnosticImpl();
    return xsdDiagnostic;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDElementDeclaration createXSDElementDeclaration()
  {
    XSDElementDeclarationImpl xsdElementDeclaration = new XSDElementDeclarationImpl();
    return xsdElementDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDEnumerationFacet createXSDEnumerationFacet()
  {
    XSDEnumerationFacetImpl xsdEnumerationFacet = new XSDEnumerationFacetImpl();
    return xsdEnumerationFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDFractionDigitsFacet createXSDFractionDigitsFacet()
  {
    XSDFractionDigitsFacetImpl xsdFractionDigitsFacet = new XSDFractionDigitsFacetImpl();
    return xsdFractionDigitsFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDIdentityConstraintDefinition createXSDIdentityConstraintDefinition()
  {
    XSDIdentityConstraintDefinitionImpl xsdIdentityConstraintDefinition = new XSDIdentityConstraintDefinitionImpl();
    return xsdIdentityConstraintDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDImport createXSDImport()
  {
    XSDImportImpl xsdImport = new XSDImportImpl();
    return xsdImport;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDInclude createXSDInclude()
  {
    XSDIncludeImpl xsdInclude = new XSDIncludeImpl();
    return xsdInclude;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDLengthFacet createXSDLengthFacet()
  {
    XSDLengthFacetImpl xsdLengthFacet = new XSDLengthFacetImpl();
    return xsdLengthFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDMaxExclusiveFacet createXSDMaxExclusiveFacet()
  {
    XSDMaxExclusiveFacetImpl xsdMaxExclusiveFacet = new XSDMaxExclusiveFacetImpl();
    return xsdMaxExclusiveFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDMaxInclusiveFacet createXSDMaxInclusiveFacet()
  {
    XSDMaxInclusiveFacetImpl xsdMaxInclusiveFacet = new XSDMaxInclusiveFacetImpl();
    return xsdMaxInclusiveFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDMaxLengthFacet createXSDMaxLengthFacet()
  {
    XSDMaxLengthFacetImpl xsdMaxLengthFacet = new XSDMaxLengthFacetImpl();
    return xsdMaxLengthFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDMinExclusiveFacet createXSDMinExclusiveFacet()
  {
    XSDMinExclusiveFacetImpl xsdMinExclusiveFacet = new XSDMinExclusiveFacetImpl();
    return xsdMinExclusiveFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDMinInclusiveFacet createXSDMinInclusiveFacet()
  {
    XSDMinInclusiveFacetImpl xsdMinInclusiveFacet = new XSDMinInclusiveFacetImpl();
    return xsdMinInclusiveFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDMinLengthFacet createXSDMinLengthFacet()
  {
    XSDMinLengthFacetImpl xsdMinLengthFacet = new XSDMinLengthFacetImpl();
    return xsdMinLengthFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDModelGroup createXSDModelGroup()
  {
    XSDModelGroupImpl xsdModelGroup = new XSDModelGroupImpl();
    return xsdModelGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDModelGroupDefinition createXSDModelGroupDefinition()
  {
    XSDModelGroupDefinitionImpl xsdModelGroupDefinition = new XSDModelGroupDefinitionImpl();
    return xsdModelGroupDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDNotationDeclaration createXSDNotationDeclaration()
  {
    XSDNotationDeclarationImpl xsdNotationDeclaration = new XSDNotationDeclarationImpl();
    return xsdNotationDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDNumericFacet createXSDNumericFacet()
  {
    XSDNumericFacetImpl xsdNumericFacet = new XSDNumericFacetImpl();
    return xsdNumericFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDOrderedFacet createXSDOrderedFacet()
  {
    XSDOrderedFacetImpl xsdOrderedFacet = new XSDOrderedFacetImpl();
    return xsdOrderedFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDParticle createXSDParticle()
  {
    XSDParticleImpl xsdParticle = new XSDParticleImpl();
    return xsdParticle;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDPatternFacet createXSDPatternFacet()
  {
    XSDPatternFacetImpl xsdPatternFacet = new XSDPatternFacetImpl();
    return xsdPatternFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDRedefine createXSDRedefine()
  {
    XSDRedefineImpl xsdRedefine = new XSDRedefineImpl();
    return xsdRedefine;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSchema createXSDSchema()
  {
    XSDSchemaImpl xsdSchema = new XSDSchemaImpl();
    return xsdSchema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSimpleTypeDefinition createXSDSimpleTypeDefinition()
  {
    XSDSimpleTypeDefinitionImpl xsdSimpleTypeDefinition = new XSDSimpleTypeDefinitionImpl();
    return xsdSimpleTypeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDTotalDigitsFacet createXSDTotalDigitsFacet()
  {
    XSDTotalDigitsFacetImpl xsdTotalDigitsFacet = new XSDTotalDigitsFacetImpl();
    return xsdTotalDigitsFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDWhiteSpaceFacet createXSDWhiteSpaceFacet()
  {
    XSDWhiteSpaceFacetImpl xsdWhiteSpaceFacet = new XSDWhiteSpaceFacetImpl();
    return xsdWhiteSpaceFacet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDWildcard createXSDWildcard()
  {
    XSDWildcardImpl xsdWildcard = new XSDWildcardImpl();
    return xsdWildcard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDXPathDefinition createXSDXPathDefinition()
  {
    XSDXPathDefinitionImpl xsdxPathDefinition = new XSDXPathDefinitionImpl();
    return xsdxPathDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Attr createDOMAttrFromString(EDataType eDataType, String initialValue) 
  {
    return (Attr)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public String convertDOMAttrToString(EDataType eDataType, Object instanceValue) 
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Document createDOMDocumentFromString(EDataType eDataType, String initialValue) 
  {
    return (Document)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public String convertDOMDocumentToString(EDataType eDataType, Object instanceValue) 
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Element createDOMElementFromString(EDataType eDataType, String initialValue) 
  {
    return (Element)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public String convertDOMElementToString(EDataType eDataType, Object instanceValue) 
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Node createDOMNodeFromString(EDataType eDataType, String initialValue) 
  {
    return (Node)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public String convertDOMNodeToString(EDataType eDataType, Object instanceValue) 
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Object createValueFromString(EDataType eDataType, String initialValue) 
  {
    return (Object)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public String convertValueToString(EDataType eDataType, Object instanceValue) 
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDPackage getXSDPackage()
  {
    return (XSDPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static XSDPackage getPackage()
  {
    return XSDPackage.eINSTANCE;
  }

} //XSDFactoryImpl
