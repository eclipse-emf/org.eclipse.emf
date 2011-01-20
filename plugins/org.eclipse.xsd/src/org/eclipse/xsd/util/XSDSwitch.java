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
 * $Id: XSDSwitch.java,v 1.12 2011/01/20 01:10:55 emerks Exp $
 */
package org.eclipse.xsd.util;


import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.xsd.*;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)} 
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object 
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned, 
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage
 * @generated
 */
public class XSDSwitch<T> extends Switch<T>
{
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
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case XSDPackage.XSD_ANNOTATION:
      {
        XSDAnnotation xsdAnnotation = (XSDAnnotation)theEObject;
        T result = caseXSDAnnotation(xsdAnnotation);
        if (result == null) result = caseXSDComponent(xsdAnnotation);
        if (result == null) result = caseXSDRedefineContent(xsdAnnotation);
        if (result == null) result = caseXSDSchemaContent(xsdAnnotation);
        if (result == null) result = caseXSDConcreteComponent(xsdAnnotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_DECLARATION:
      {
        XSDAttributeDeclaration xsdAttributeDeclaration = (XSDAttributeDeclaration)theEObject;
        T result = caseXSDAttributeDeclaration(xsdAttributeDeclaration);
        if (result == null) result = caseXSDFeature(xsdAttributeDeclaration);
        if (result == null) result = caseXSDSchemaContent(xsdAttributeDeclaration);
        if (result == null) result = caseXSDNamedComponent(xsdAttributeDeclaration);
        if (result == null) result = caseXSDComponent(xsdAttributeDeclaration);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_GROUP_CONTENT:
      {
        XSDAttributeGroupContent xsdAttributeGroupContent = (XSDAttributeGroupContent)theEObject;
        T result = caseXSDAttributeGroupContent(xsdAttributeGroupContent);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeGroupContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_GROUP_DEFINITION:
      {
        XSDAttributeGroupDefinition xsdAttributeGroupDefinition = (XSDAttributeGroupDefinition)theEObject;
        T result = caseXSDAttributeGroupDefinition(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDAttributeGroupContent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDComponent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdAttributeGroupDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeGroupDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ATTRIBUTE_USE:
      {
        XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)theEObject;
        T result = caseXSDAttributeUse(xsdAttributeUse);
        if (result == null) result = caseXSDComponent(xsdAttributeUse);
        if (result == null) result = caseXSDAttributeGroupContent(xsdAttributeUse);
        if (result == null) result = caseXSDConcreteComponent(xsdAttributeUse);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_BOUNDED_FACET:
      {
        XSDBoundedFacet xsdBoundedFacet = (XSDBoundedFacet)theEObject;
        T result = caseXSDBoundedFacet(xsdBoundedFacet);
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
        T result = caseXSDCardinalityFacet(xsdCardinalityFacet);
        if (result == null) result = caseXSDFundamentalFacet(xsdCardinalityFacet);
        if (result == null) result = caseXSDFacet(xsdCardinalityFacet);
        if (result == null) result = caseXSDComponent(xsdCardinalityFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdCardinalityFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_COMPLEX_TYPE_CONTENT:
      {
        XSDComplexTypeContent xsdComplexTypeContent = (XSDComplexTypeContent)theEObject;
        T result = caseXSDComplexTypeContent(xsdComplexTypeContent);
        if (result == null) result = caseXSDComponent(xsdComplexTypeContent);
        if (result == null) result = caseXSDConcreteComponent(xsdComplexTypeContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_COMPLEX_TYPE_DEFINITION:
      {
        XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinition)theEObject;
        T result = caseXSDComplexTypeDefinition(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDTypeDefinition(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDScope(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDComponent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdComplexTypeDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdComplexTypeDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_COMPONENT:
      {
        XSDComponent xsdComponent = (XSDComponent)theEObject;
        T result = caseXSDComponent(xsdComponent);
        if (result == null) result = caseXSDConcreteComponent(xsdComponent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_CONCRETE_COMPONENT:
      {
        XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)theEObject;
        T result = caseXSDConcreteComponent(xsdConcreteComponent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_CONSTRAINING_FACET:
      {
        XSDConstrainingFacet xsdConstrainingFacet = (XSDConstrainingFacet)theEObject;
        T result = caseXSDConstrainingFacet(xsdConstrainingFacet);
        if (result == null) result = caseXSDFacet(xsdConstrainingFacet);
        if (result == null) result = caseXSDComponent(xsdConstrainingFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdConstrainingFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_DIAGNOSTIC:
      {
        XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)theEObject;
        T result = caseXSDDiagnostic(xsdDiagnostic);
        if (result == null) result = caseXSDConcreteComponent(xsdDiagnostic);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ELEMENT_DECLARATION:
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)theEObject;
        T result = caseXSDElementDeclaration(xsdElementDeclaration);
        if (result == null) result = caseXSDFeature(xsdElementDeclaration);
        if (result == null) result = caseXSDSchemaContent(xsdElementDeclaration);
        if (result == null) result = caseXSDTerm(xsdElementDeclaration);
        if (result == null) result = caseXSDNamedComponent(xsdElementDeclaration);
        if (result == null) result = caseXSDParticleContent(xsdElementDeclaration);
        if (result == null) result = caseXSDComponent(xsdElementDeclaration);
        if (result == null) result = caseXSDConcreteComponent(xsdElementDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_ENUMERATION_FACET:
      {
        XSDEnumerationFacet xsdEnumerationFacet = (XSDEnumerationFacet)theEObject;
        T result = caseXSDEnumerationFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDRepeatableFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDFacet(xsdEnumerationFacet);
        if (result == null) result = caseXSDComponent(xsdEnumerationFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdEnumerationFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_FACET:
      {
        XSDFacet xsdFacet = (XSDFacet)theEObject;
        T result = caseXSDFacet(xsdFacet);
        if (result == null) result = caseXSDComponent(xsdFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_FEATURE:
      {
        XSDFeature xsdFeature = (XSDFeature)theEObject;
        T result = caseXSDFeature(xsdFeature);
        if (result == null) result = caseXSDNamedComponent(xsdFeature);
        if (result == null) result = caseXSDComponent(xsdFeature);
        if (result == null) result = caseXSDConcreteComponent(xsdFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_FIXED_FACET:
      {
        XSDFixedFacet xsdFixedFacet = (XSDFixedFacet)theEObject;
        T result = caseXSDFixedFacet(xsdFixedFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdFixedFacet);
        if (result == null) result = caseXSDFacet(xsdFixedFacet);
        if (result == null) result = caseXSDComponent(xsdFixedFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdFixedFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_FRACTION_DIGITS_FACET:
      {
        XSDFractionDigitsFacet xsdFractionDigitsFacet = (XSDFractionDigitsFacet)theEObject;
        T result = caseXSDFractionDigitsFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDFixedFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDFacet(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDComponent(xsdFractionDigitsFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdFractionDigitsFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_FUNDAMENTAL_FACET:
      {
        XSDFundamentalFacet xsdFundamentalFacet = (XSDFundamentalFacet)theEObject;
        T result = caseXSDFundamentalFacet(xsdFundamentalFacet);
        if (result == null) result = caseXSDFacet(xsdFundamentalFacet);
        if (result == null) result = caseXSDComponent(xsdFundamentalFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdFundamentalFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION:
      {
        XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = (XSDIdentityConstraintDefinition)theEObject;
        T result = caseXSDIdentityConstraintDefinition(xsdIdentityConstraintDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdIdentityConstraintDefinition);
        if (result == null) result = caseXSDComponent(xsdIdentityConstraintDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdIdentityConstraintDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_IMPORT:
      {
        XSDImport xsdImport = (XSDImport)theEObject;
        T result = caseXSDImport(xsdImport);
        if (result == null) result = caseXSDSchemaDirective(xsdImport);
        if (result == null) result = caseXSDSchemaContent(xsdImport);
        if (result == null) result = caseXSDConcreteComponent(xsdImport);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_INCLUDE:
      {
        XSDInclude xsdInclude = (XSDInclude)theEObject;
        T result = caseXSDInclude(xsdInclude);
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
        T result = caseXSDLengthFacet(xsdLengthFacet);
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
        T result = caseXSDMaxExclusiveFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDMaxFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDFacet(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDComponent(xsdMaxExclusiveFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMaxExclusiveFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MAX_FACET:
      {
        XSDMaxFacet xsdMaxFacet = (XSDMaxFacet)theEObject;
        T result = caseXSDMaxFacet(xsdMaxFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMaxFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMaxFacet);
        if (result == null) result = caseXSDFacet(xsdMaxFacet);
        if (result == null) result = caseXSDComponent(xsdMaxFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMaxFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MAX_INCLUSIVE_FACET:
      {
        XSDMaxInclusiveFacet xsdMaxInclusiveFacet = (XSDMaxInclusiveFacet)theEObject;
        T result = caseXSDMaxInclusiveFacet(xsdMaxInclusiveFacet);
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
        T result = caseXSDMaxLengthFacet(xsdMaxLengthFacet);
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
        T result = caseXSDMinExclusiveFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDMinFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDFacet(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDComponent(xsdMinExclusiveFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMinExclusiveFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MIN_FACET:
      {
        XSDMinFacet xsdMinFacet = (XSDMinFacet)theEObject;
        T result = caseXSDMinFacet(xsdMinFacet);
        if (result == null) result = caseXSDFixedFacet(xsdMinFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdMinFacet);
        if (result == null) result = caseXSDFacet(xsdMinFacet);
        if (result == null) result = caseXSDComponent(xsdMinFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdMinFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_MIN_INCLUSIVE_FACET:
      {
        XSDMinInclusiveFacet xsdMinInclusiveFacet = (XSDMinInclusiveFacet)theEObject;
        T result = caseXSDMinInclusiveFacet(xsdMinInclusiveFacet);
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
        T result = caseXSDMinLengthFacet(xsdMinLengthFacet);
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
        T result = caseXSDModelGroup(xsdModelGroup);
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
        T result = caseXSDModelGroupDefinition(xsdModelGroupDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDParticleContent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDComponent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdModelGroupDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdModelGroupDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_NAMED_COMPONENT:
      {
        XSDNamedComponent xsdNamedComponent = (XSDNamedComponent)theEObject;
        T result = caseXSDNamedComponent(xsdNamedComponent);
        if (result == null) result = caseXSDComponent(xsdNamedComponent);
        if (result == null) result = caseXSDConcreteComponent(xsdNamedComponent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_NOTATION_DECLARATION:
      {
        XSDNotationDeclaration xsdNotationDeclaration = (XSDNotationDeclaration)theEObject;
        T result = caseXSDNotationDeclaration(xsdNotationDeclaration);
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
        T result = caseXSDNumericFacet(xsdNumericFacet);
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
        T result = caseXSDOrderedFacet(xsdOrderedFacet);
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
        T result = caseXSDParticle(xsdParticle);
        if (result == null) result = caseXSDComplexTypeContent(xsdParticle);
        if (result == null) result = caseXSDComponent(xsdParticle);
        if (result == null) result = caseXSDConcreteComponent(xsdParticle);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_PARTICLE_CONTENT:
      {
        XSDParticleContent xsdParticleContent = (XSDParticleContent)theEObject;
        T result = caseXSDParticleContent(xsdParticleContent);
        if (result == null) result = caseXSDConcreteComponent(xsdParticleContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_PATTERN_FACET:
      {
        XSDPatternFacet xsdPatternFacet = (XSDPatternFacet)theEObject;
        T result = caseXSDPatternFacet(xsdPatternFacet);
        if (result == null) result = caseXSDRepeatableFacet(xsdPatternFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdPatternFacet);
        if (result == null) result = caseXSDFacet(xsdPatternFacet);
        if (result == null) result = caseXSDComponent(xsdPatternFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdPatternFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_REDEFINABLE_COMPONENT:
      {
        XSDRedefinableComponent xsdRedefinableComponent = (XSDRedefinableComponent)theEObject;
        T result = caseXSDRedefinableComponent(xsdRedefinableComponent);
        if (result == null) result = caseXSDNamedComponent(xsdRedefinableComponent);
        if (result == null) result = caseXSDRedefineContent(xsdRedefinableComponent);
        if (result == null) result = caseXSDComponent(xsdRedefinableComponent);
        if (result == null) result = caseXSDSchemaContent(xsdRedefinableComponent);
        if (result == null) result = caseXSDConcreteComponent(xsdRedefinableComponent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_REDEFINE_CONTENT:
      {
        XSDRedefineContent xsdRedefineContent = (XSDRedefineContent)theEObject;
        T result = caseXSDRedefineContent(xsdRedefineContent);
        if (result == null) result = caseXSDSchemaContent(xsdRedefineContent);
        if (result == null) result = caseXSDConcreteComponent(xsdRedefineContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_REDEFINE:
      {
        XSDRedefine xsdRedefine = (XSDRedefine)theEObject;
        T result = caseXSDRedefine(xsdRedefine);
        if (result == null) result = caseXSDSchemaCompositor(xsdRedefine);
        if (result == null) result = caseXSDSchemaDirective(xsdRedefine);
        if (result == null) result = caseXSDSchemaContent(xsdRedefine);
        if (result == null) result = caseXSDConcreteComponent(xsdRedefine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_REPEATABLE_FACET:
      {
        XSDRepeatableFacet xsdRepeatableFacet = (XSDRepeatableFacet)theEObject;
        T result = caseXSDRepeatableFacet(xsdRepeatableFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdRepeatableFacet);
        if (result == null) result = caseXSDFacet(xsdRepeatableFacet);
        if (result == null) result = caseXSDComponent(xsdRepeatableFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdRepeatableFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SCHEMA:
      {
        XSDSchema xsdSchema = (XSDSchema)theEObject;
        T result = caseXSDSchema(xsdSchema);
        if (result == null) result = caseXSDScope(xsdSchema);
        if (result == null) result = caseXSDComponent(xsdSchema);
        if (result == null) result = caseXSDConcreteComponent(xsdSchema);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SCHEMA_COMPOSITOR:
      {
        XSDSchemaCompositor xsdSchemaCompositor = (XSDSchemaCompositor)theEObject;
        T result = caseXSDSchemaCompositor(xsdSchemaCompositor);
        if (result == null) result = caseXSDSchemaDirective(xsdSchemaCompositor);
        if (result == null) result = caseXSDSchemaContent(xsdSchemaCompositor);
        if (result == null) result = caseXSDConcreteComponent(xsdSchemaCompositor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SCHEMA_CONTENT:
      {
        XSDSchemaContent xsdSchemaContent = (XSDSchemaContent)theEObject;
        T result = caseXSDSchemaContent(xsdSchemaContent);
        if (result == null) result = caseXSDConcreteComponent(xsdSchemaContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SCHEMA_DIRECTIVE:
      {
        XSDSchemaDirective xsdSchemaDirective = (XSDSchemaDirective)theEObject;
        T result = caseXSDSchemaDirective(xsdSchemaDirective);
        if (result == null) result = caseXSDSchemaContent(xsdSchemaDirective);
        if (result == null) result = caseXSDConcreteComponent(xsdSchemaDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SCOPE:
      {
        XSDScope xsdScope = (XSDScope)theEObject;
        T result = caseXSDScope(xsdScope);
        if (result == null) result = caseXSDComponent(xsdScope);
        if (result == null) result = caseXSDConcreteComponent(xsdScope);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_SIMPLE_TYPE_DEFINITION:
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)theEObject;
        T result = caseXSDSimpleTypeDefinition(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDTypeDefinition(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDComplexTypeContent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDComponent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdSimpleTypeDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdSimpleTypeDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_TERM:
      {
        XSDTerm xsdTerm = (XSDTerm)theEObject;
        T result = caseXSDTerm(xsdTerm);
        if (result == null) result = caseXSDComponent(xsdTerm);
        if (result == null) result = caseXSDParticleContent(xsdTerm);
        if (result == null) result = caseXSDConcreteComponent(xsdTerm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_TOTAL_DIGITS_FACET:
      {
        XSDTotalDigitsFacet xsdTotalDigitsFacet = (XSDTotalDigitsFacet)theEObject;
        T result = caseXSDTotalDigitsFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDFixedFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDConstrainingFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDFacet(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDComponent(xsdTotalDigitsFacet);
        if (result == null) result = caseXSDConcreteComponent(xsdTotalDigitsFacet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_TYPE_DEFINITION:
      {
        XSDTypeDefinition xsdTypeDefinition = (XSDTypeDefinition)theEObject;
        T result = caseXSDTypeDefinition(xsdTypeDefinition);
        if (result == null) result = caseXSDRedefinableComponent(xsdTypeDefinition);
        if (result == null) result = caseXSDNamedComponent(xsdTypeDefinition);
        if (result == null) result = caseXSDRedefineContent(xsdTypeDefinition);
        if (result == null) result = caseXSDComponent(xsdTypeDefinition);
        if (result == null) result = caseXSDSchemaContent(xsdTypeDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdTypeDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XSDPackage.XSD_WHITE_SPACE_FACET:
      {
        XSDWhiteSpaceFacet xsdWhiteSpaceFacet = (XSDWhiteSpaceFacet)theEObject;
        T result = caseXSDWhiteSpaceFacet(xsdWhiteSpaceFacet);
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
        T result = caseXSDWildcard(xsdWildcard);
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
        T result = caseXSDXPathDefinition(xsdxPathDefinition);
        if (result == null) result = caseXSDComponent(xsdxPathDefinition);
        if (result == null) result = caseXSDConcreteComponent(xsdxPathDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDAnnotation(XSDAnnotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDAttributeDeclaration(XSDAttributeDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute Group Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute Group Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDAttributeGroupContent(XSDAttributeGroupContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute Group Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute Group Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute Use</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute Use</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDAttributeUse(XSDAttributeUse object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bounded Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bounded Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDBoundedFacet(XSDBoundedFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Cardinality Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Cardinality Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDCardinalityFacet(XSDCardinalityFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Complex Type Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complex Type Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDComplexTypeContent(XSDComplexTypeContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Complex Type Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complex Type Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDComplexTypeDefinition(XSDComplexTypeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDComponent(XSDComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Concrete Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Concrete Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDConcreteComponent(XSDConcreteComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraining Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraining Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDConstrainingFacet(XSDConstrainingFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Diagnostic</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Diagnostic</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDDiagnostic(XSDDiagnostic object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDElementDeclaration(XSDElementDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDEnumerationFacet(XSDEnumerationFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDFacet(XSDFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDFeature(XSDFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fixed Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fixed Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDFixedFacet(XSDFixedFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fraction Digits Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fraction Digits Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDFractionDigitsFacet(XSDFractionDigitsFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fundamental Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fundamental Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDFundamentalFacet(XSDFundamentalFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Identity Constraint Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Identity Constraint Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDIdentityConstraintDefinition(XSDIdentityConstraintDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Import</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDImport(XSDImport object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Include</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Include</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDInclude(XSDInclude object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Length Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Length Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDLengthFacet(XSDLengthFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Max Exclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Max Exclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMaxExclusiveFacet(XSDMaxExclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Max Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Max Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMaxFacet(XSDMaxFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Max Inclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Max Inclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMaxInclusiveFacet(XSDMaxInclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Max Length Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Max Length Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMaxLengthFacet(XSDMaxLengthFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Min Exclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Min Exclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMinExclusiveFacet(XSDMinExclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Min Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Min Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMinFacet(XSDMinFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Min Inclusive Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Min Inclusive Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMinInclusiveFacet(XSDMinInclusiveFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Min Length Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Min Length Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDMinLengthFacet(XSDMinLengthFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDModelGroup(XSDModelGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Group Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Group Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDModelGroupDefinition(XSDModelGroupDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDNamedComponent(XSDNamedComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Notation Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Notation Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDNotationDeclaration(XSDNotationDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Numeric Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Numeric Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDNumericFacet(XSDNumericFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ordered Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ordered Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDOrderedFacet(XSDOrderedFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Particle</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Particle</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDParticle(XSDParticle object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Particle Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Particle Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDParticleContent(XSDParticleContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pattern Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pattern Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDPatternFacet(XSDPatternFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Redefinable Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Redefinable Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDRedefinableComponent(XSDRedefinableComponent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Redefine Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Redefine Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDRedefineContent(XSDRedefineContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Redefine</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Redefine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDRedefine(XSDRedefine object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Repeatable Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Repeatable Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDRepeatableFacet(XSDRepeatableFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Schema</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Schema</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDSchema(XSDSchema object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Schema Compositor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Schema Compositor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDSchemaCompositor(XSDSchemaCompositor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Schema Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Schema Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDSchemaContent(XSDSchemaContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Schema Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Schema Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDSchemaDirective(XSDSchemaDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Scope</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scope</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDScope(XSDScope object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Simple Type Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simple Type Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Term</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Term</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDTerm(XSDTerm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Total Digits Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Total Digits Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDTotalDigitsFacet(XSDTotalDigitsFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDTypeDefinition(XSDTypeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>White Space Facet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>White Space Facet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDWhiteSpaceFacet(XSDWhiteSpaceFacet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Wildcard</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Wildcard</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDWildcard(XSDWildcard object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XPath Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XPath Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXSDXPathDefinition(XSDXPathDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //XSDSwitch

