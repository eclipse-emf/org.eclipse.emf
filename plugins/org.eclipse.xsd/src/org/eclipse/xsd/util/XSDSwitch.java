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
 * $Id: XSDSwitch.java,v 1.3 2004/05/16 16:47:00 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.xsd.*;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)} 
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object 
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned, 
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage
 * @generated
 */
public class XSDSwitch {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static XSDPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = XSDPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public Object doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch((EClass)eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case XSDPackage.XSD_ANNOTATION:
      {
        XSDAnnotation xsdAnnotation = (XSDAnnotation)theEObject;
        Object result = caseXSDAnnotation(xsdAnnotation);
        if (result == null) result = caseXSDComponent(xsdAnnotation);
        if (result == null) result = caseXSDRedefineContent(xsdAnnotation);
        if (result == null) result = caseXSDConcreteComponent(xsdAnnotation);
        if (result == null) result = caseXSDSchemaContent(xsdAnnotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION:
      {
        XSDAttributeDeclaration xsdAttributeDeclaration = (XSDAttributeDeclaration)theEObject;
        Object result = caseXSDAttributeDeclaration(xsdAttributeDeclaration);
        if (result == null) result = caseXSDFeature(xsdAttributeDeclaration);
        if (result == null) result = caseXSDSchemaContent(xsdAttributeDeclaration);
        if (result == null) result = caseXSDNamedComponent(xsdAttributeDeclaration);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeDeclaration);
        if (result == null) result = caseXSDComponent(xsdAttributeDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION:
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition = (XSDAttributeGroupDefinition)theEObject;
        Object result = caseXSDAttributeGroupDefinition(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDAttributeGroupContent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDComponent(xsdAttributeGroupDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_USE:
      {
        XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)theEObject;
        Object result = caseXSDAttributeUse(xsdAttributeUse);
        if (result == null) result = caseXSDComponent(xsdAttributeUse);
        if (result == null) result = caseXSDAttributeGroupContent(xsdAttributeUse);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeUse);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_BOUNDED_FACET:
      {
        XSDBoundedFacet xsdBoundedFacet = (XSDBoundedFacet)theEObject;
        Object result = caseXSDBoundedFacet(xsdBoundedFacet);
        if (result == null) result = caseXSDFundamentalFacet(xsdBoundedFacet);
        if (result == null) result = caseXSDFacet(xsdBoundedFacet);
        if (result == null) result = caseXSDComponent(xsdBoundedFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdBoundedFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_CARDINALITY_FACET:
      {
        XSDCardinalityFacet xsdCardinalityFacet = (XSDCardinalityFacet)theEObject;
        Object result = caseXSDCardinalityFacet(xsdCardinalityFacet);
        if (result == null) result = caseXSDFundamentalFacet(xsdCardinalityFacet);
        if (result == null) result = caseXSDFacet(xsdCardinalityFacet);
        if (result == null) result = caseXSDComponent(xsdCardinalityFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdCardinalityFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION:
      {
        XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinition)theEObject;
        Object result = caseXSDComplexTypeDefinition(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDTypeDefinition(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDScope(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDComponent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdComplexTypeDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_DIAGNOSTIC:
      {
        XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)theEObject;
        Object result = caseXSDDiagnostic(xsdDiagnostic);
        if (result == null) result = caseXSDConcreteComponent(xsdDiagnostic);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ELEMENT_DECLARATION:
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)theEObject;
        Object result = caseXSDElementDeclaration(xsdElementDeclaration);
        if (result == null) result = caseXSDFeature(xsdElementDeclaration);
        if (result == null) result = caseXSDSchemaContent(xsdElementDeclaration);
        if (result == null) result = caseXSDTerm(xsdElementDeclaration);
        if (result == null) result = caseXSDNamedComponent(xsdElementDeclaration);
        if (result == null) result = caseXSDConcreteComponent(xsdElementDeclaration);
        if (result == null) result = caseXSDComponent(xsdElementDeclaration);
        if (result == null) result = caseXSDParticleContent(xsdElementDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ENUMERATION_FACET:
      {
        XSDEnumerationFacet xsdEnumerationFacet = (XSDEnumerationFacet)theEObject;
        Object result = caseXSDEnumerationFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDRepeatableFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDComponent(xsdEnumerationFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdEnumerationFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_FRACTION_DIGITS_FACET:
      {
        XSDFractionDigitsFacet xsdFractionDigitsFacet = (XSDFractionDigitsFacet)theEObject;
        Object result = caseXSDFractionDigitsFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDFixedFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDComponent(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdFractionDigitsFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION:
      {
        XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = (XSDIdentityConstraintDefinition)theEObject;
        Object result = caseXSDIdentityConstraintDefinition(xsdIdentityConstraintDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdIdentityConstraintDefinition);
        if (result == null) result = caseXSDComponent(xsdIdentityConstraintDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdIdentityConstraintDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_IMPORT:
      {
        XSDImport xsdImport = (XSDImport)theEObject;
        Object result = caseXSDImport(xsdImport);
        if (result == null) result = caseXSDSchemaDirective(xsdImport);
        if (result == null) result = caseXSDSchemaContent(xsdImport);
        if (result == null) result = caseXSDConcreteComponent(xsdImport);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_INCLUDE:
      {
        XSDInclude xsdInclude = (XSDInclude)theEObject;
        Object result = caseXSDInclude(xsdInclude);
        if (result == null) result = caseXSDSchemaCompositor(xsdInclude);
        if (result == null) result = caseXSDSchemaDirective(xsdInclude);
        if (result == null) result = caseXSDSchemaContent(xsdInclude);
        if (result == null) result = caseXSDConcreteComponent(xsdInclude);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_LENGTH_FACET:
      {
        XSDLengthFacet xsdLengthFacet = (XSDLengthFacet)theEObject;
        Object result = caseXSDLengthFacet(xsdLengthFacet);
        if (result == null) result = caseXSDFixedFacet(xsdLengthFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdLengthFacet);
        if (result == null) result = caseXSDFacet(xsdLengthFacet);
        if (result == null) result = caseXSDComponent(xsdLengthFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdLengthFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MAX_EXCLUSIVE_FACET:
      {
        XSDMaxExclusiveFacet xsdMaxExclusiveFacet = (XSDMaxExclusiveFacet)theEObject;
        Object result = caseXSDMaxExclusiveFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDMaxFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDComponent(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMaxExclusiveFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MAX_INCLUSIVE_FACET:
      {
        XSDMaxInclusiveFacet xsdMaxInclusiveFacet = (XSDMaxInclusiveFacet)theEObject;
        Object result = caseXSDMaxInclusiveFacet(xsdMaxInclusiveFacet);
        if (result == null) result = caseXSDMaxFacet(xsdMaxInclusiveFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMaxInclusiveFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMaxInclusiveFacet);
        if (result == null) result = caseXSDFacet(xsdMaxInclusiveFacet);
        if (result == null) result = caseXSDComponent(xsdMaxInclusiveFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMaxInclusiveFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MAX_LENGTH_FACET:
      {
        XSDMaxLengthFacet xsdMaxLengthFacet = (XSDMaxLengthFacet)theEObject;
        Object result = caseXSDMaxLengthFacet(xsdMaxLengthFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMaxLengthFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMaxLengthFacet);
        if (result == null) result = caseXSDFacet(xsdMaxLengthFacet);
        if (result == null) result = caseXSDComponent(xsdMaxLengthFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMaxLengthFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MIN_EXCLUSIVE_FACET:
      {
        XSDMinExclusiveFacet xsdMinExclusiveFacet = (XSDMinExclusiveFacet)theEObject;
        Object result = caseXSDMinExclusiveFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDMinFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDComponent(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMinExclusiveFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MIN_INCLUSIVE_FACET:
      {
        XSDMinInclusiveFacet xsdMinInclusiveFacet = (XSDMinInclusiveFacet)theEObject;
        Object result = caseXSDMinInclusiveFacet(xsdMinInclusiveFacet);
        if (result == null) result = caseXSDMinFacet(xsdMinInclusiveFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMinInclusiveFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMinInclusiveFacet);
        if (result == null) result = caseXSDFacet(xsdMinInclusiveFacet);
        if (result == null) result = caseXSDComponent(xsdMinInclusiveFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMinInclusiveFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MIN_LENGTH_FACET:
      {
        XSDMinLengthFacet xsdMinLengthFacet = (XSDMinLengthFacet)theEObject;
        Object result = caseXSDMinLengthFacet(xsdMinLengthFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMinLengthFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMinLengthFacet);
        if (result == null) result = caseXSDFacet(xsdMinLengthFacet);
        if (result == null) result = caseXSDComponent(xsdMinLengthFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMinLengthFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MODEL_GROUP:
      {
        XSDModelGroup xsdModelGroup = (XSDModelGroup)theEObject;
        Object result = caseXSDModelGroup(xsdModelGroup);
        if (result == null) result = caseXSDTerm(xsdModelGroup);
        if (result == null) result = caseXSDComponent(xsdModelGroup);
        if (result == null) result = caseXSDParticleContent(xsdModelGroup);
        if (result == null) result = caseXSDConcreteComponent(xsdModelGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION:
      {
        XSDModelGroupDefinition xsdModelGroupDefinition = (XSDModelGroupDefinition)theEObject;
        Object result = caseXSDModelGroupDefinition(xsdModelGroupDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDParticleContent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDComponent(xsdModelGroupDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_NOTATION_DECLARATION:
      {
        XSDNotationDeclaration xsdNotationDeclaration = (XSDNotationDeclaration)theEObject;
        Object result = caseXSDNotationDeclaration(xsdNotationDeclaration);
        if (result == null) result = caseXSDNamedComponent(xsdNotationDeclaration);
        if (result == null) result = caseXSDSchemaContent(xsdNotationDeclaration);
        if (result == null) result = caseXSDComponent(xsdNotationDeclaration);
        if (result == null) result = caseXSDConcreteComponent(xsdNotationDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_NUMERIC_FACET:
      {
        XSDNumericFacet xsdNumericFacet = (XSDNumericFacet)theEObject;
        Object result = caseXSDNumericFacet(xsdNumericFacet);
        if (result == null) result = caseXSDFundamentalFacet(xsdNumericFacet);
        if (result == null) result = caseXSDFacet(xsdNumericFacet);
        if (result == null) result = caseXSDComponent(xsdNumericFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdNumericFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ORDERED_FACET:
      {
        XSDOrderedFacet xsdOrderedFacet = (XSDOrderedFacet)theEObject;
        Object result = caseXSDOrderedFacet(xsdOrderedFacet);
        if (result == null) result = caseXSDFundamentalFacet(xsdOrderedFacet);
        if (result == null) result = caseXSDFacet(xsdOrderedFacet);
        if (result == null) result = caseXSDComponent(xsdOrderedFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdOrderedFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_PARTICLE:
      {
        XSDParticle xsdParticle = (XSDParticle)theEObject;
        Object result = caseXSDParticle(xsdParticle);
        if (result == null) result = caseXSDComplexTypeContent(xsdParticle);
        if (result == null) result = caseXSDComponent(xsdParticle);
        if (result == null) result = caseXSDConcreteComponent(xsdParticle);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_PATTERN_FACET:
      {
        XSDPatternFacet xsdPatternFacet = (XSDPatternFacet)theEObject;
        Object result = caseXSDPatternFacet(xsdPatternFacet);
        if (result == null) result = caseXSDRepeatableFacet(xsdPatternFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdPatternFacet);
        if (result == null) result = caseXSDFacet(xsdPatternFacet);
        if (result == null) result = caseXSDComponent(xsdPatternFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdPatternFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_REDEFINE:
      {
        XSDRedefine xsdRedefine = (XSDRedefine)theEObject;
        Object result = caseXSDRedefine(xsdRedefine);
        if (result == null) result = caseXSDSchemaCompositor(xsdRedefine);
        if (result == null) result = caseXSDSchemaDirective(xsdRedefine);
        if (result == null) result = caseXSDSchemaContent(xsdRedefine);
        if (result == null) result = caseXSDConcreteComponent(xsdRedefine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SCHEMA:
      {
        XSDSchema xsdSchema = (XSDSchema)theEObject;
        Object result = caseXSDSchema(xsdSchema);
        if (result == null) result = caseXSDScope(xsdSchema);
        if (result == null) result = caseXSDComponent(xsdSchema);
        if (result == null) result = caseXSDConcreteComponent(xsdSchema);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION:
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)theEObject;
        Object result = caseXSDSimpleTypeDefinition(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDTypeDefinition(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDComplexTypeContent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDComponent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdSimpleTypeDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_TOTAL_DIGITS_FACET:
      {
        XSDTotalDigitsFacet xsdTotalDigitsFacet = (XSDTotalDigitsFacet)theEObject;
        Object result = caseXSDTotalDigitsFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDFixedFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDComponent(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdTotalDigitsFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_WHITE_SPACE_FACET:
      {
        XSDWhiteSpaceFacet xsdWhiteSpaceFacet = (XSDWhiteSpaceFacet)theEObject;
        Object result = caseXSDWhiteSpaceFacet(xsdWhiteSpaceFacet);
        if (result == null) result = caseXSDFixedFacet(xsdWhiteSpaceFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdWhiteSpaceFacet);
        if (result == null) result = caseXSDFacet(xsdWhiteSpaceFacet);
        if (result == null) result = caseXSDComponent(xsdWhiteSpaceFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdWhiteSpaceFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_WILDCARD:
      {
        XSDWildcard xsdWildcard = (XSDWildcard)theEObject;
        Object result = caseXSDWildcard(xsdWildcard);
        if (result == null) result = caseXSDTerm(xsdWildcard);
        if (result == null) result = caseXSDComponent(xsdWildcard);
        if (result == null) result = caseXSDParticleContent(xsdWildcard);
        if (result == null) result = caseXSDConcreteComponent(xsdWildcard);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_XPATH_DEFINITION:
      {
        XSDXPathDefinition xsdxPathDefinition = (XSDXPathDefinition)theEObject;
        Object result = caseXSDXPathDefinition(xsdxPathDefinition);
        if (result == null) result = caseXSDComponent(xsdxPathDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdxPathDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Annotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDAnnotation(XSDAnnotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Attribute Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Attribute Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDAttributeDeclaration(XSDAttributeDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Attribute Group Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Attribute Group Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDAttributeGroupContent(XSDAttributeGroupContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Attribute Group Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Attribute Group Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Attribute Use</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Attribute Use</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDAttributeUse(XSDAttributeUse object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Bounded Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Bounded Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDBoundedFacet(XSDBoundedFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Cardinality Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Cardinality Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDCardinalityFacet(XSDCardinalityFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Complex Type Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Complex Type Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDComplexTypeContent(XSDComplexTypeContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Complex Type Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Complex Type Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDComplexTypeDefinition(XSDComplexTypeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDComponent(XSDComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Concrete Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Concrete Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDConcreteComponent(XSDConcreteComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Constraining Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Constraining Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDConstrainingFacet(XSDConstrainingFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Diagnostic</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Diagnostic</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDDiagnostic(XSDDiagnostic object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Element Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Element Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDElementDeclaration(XSDElementDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Enumeration Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Enumeration Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDEnumerationFacet(XSDEnumerationFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDFacet(XSDFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDFeature(XSDFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Fixed Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Fixed Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDFixedFacet(XSDFixedFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Fraction Digits Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Fraction Digits Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDFractionDigitsFacet(XSDFractionDigitsFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Fundamental Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Fundamental Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDFundamentalFacet(XSDFundamentalFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Identity Constraint Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Identity Constraint Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDIdentityConstraintDefinition(XSDIdentityConstraintDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Import</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDImport(XSDImport object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Include</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Include</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDInclude(XSDInclude object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Length Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Length Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDLengthFacet(XSDLengthFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Max Exclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Max Exclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMaxExclusiveFacet(XSDMaxExclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Max Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Max Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMaxFacet(XSDMaxFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Max Inclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Max Inclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMaxInclusiveFacet(XSDMaxInclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Max Length Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Max Length Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMaxLengthFacet(XSDMaxLengthFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Min Exclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Min Exclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMinExclusiveFacet(XSDMinExclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Min Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Min Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMinFacet(XSDMinFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Min Inclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Min Inclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMinInclusiveFacet(XSDMinInclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Min Length Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Min Length Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDMinLengthFacet(XSDMinLengthFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Model Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Model Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDModelGroup(XSDModelGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Model Group Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Model Group Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Named Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Named Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDNamedComponent(XSDNamedComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Notation Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Notation Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDNotationDeclaration(XSDNotationDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Numeric Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Numeric Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDNumericFacet(XSDNumericFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Ordered Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Ordered Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDOrderedFacet(XSDOrderedFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Particle</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Particle</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDParticle(XSDParticle object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Particle Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Particle Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDParticleContent(XSDParticleContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Pattern Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Pattern Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDPatternFacet(XSDPatternFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Redefinable Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Redefinable Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDRedefinableComponent(XSDRedefinableComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Redefine Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Redefine Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDRedefineContent(XSDRedefineContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Redefine</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Redefine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDRedefine(XSDRedefine object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Repeatable Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Repeatable Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDRepeatableFacet(XSDRepeatableFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Schema</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Schema</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDSchema(XSDSchema object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Schema Compositor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Schema Compositor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDSchemaCompositor(XSDSchemaCompositor object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Schema Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Schema Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDSchemaContent(XSDSchemaContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Schema Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Schema Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDSchemaDirective(XSDSchemaDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Scope</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Scope</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDScope(XSDScope object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Simple Type Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Simple Type Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Term</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Term</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDTerm(XSDTerm object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Total Digits Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Total Digits Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDTotalDigitsFacet(XSDTotalDigitsFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Type Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Type Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDTypeDefinition(XSDTypeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>White Space Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>White Space Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDWhiteSpaceFacet(XSDWhiteSpaceFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Wildcard</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Wildcard</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDWildcard(XSDWildcard object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>XPath Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>XPath Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseXSDXPathDefinition(XSDXPathDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public Object defaultCase(EObject object)
  {
    return null;
  }

} //XSDSwitch

