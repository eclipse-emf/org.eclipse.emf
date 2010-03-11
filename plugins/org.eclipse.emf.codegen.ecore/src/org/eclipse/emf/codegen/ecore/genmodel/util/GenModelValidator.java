/**
 * <copyright> 
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: GenModelValidator.java,v 1.5 2010/03/11 02:31:38 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.util;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.genmodel.*;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
//import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * @since 2.4
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
 * @generated
 */
public class GenModelValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final GenModelValidator INSTANCE = new GenModelValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.codegen.ecore.genmodel";

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;
  
  /**
   * @see #validateGenEnum_NoEcoreDataType(GenEnum, DiagnosticChain, Map)
   */
  public static final int NO_ECORE_DATA_TYPE = GENERATED_DIAGNOSTIC_CODE_COUNT + 1;
  
  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected static final int DIAGNOSTIC_CODE_COUNT = NO_ECORE_DATA_TYPE;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModelValidator()
  {
    super();
  }

  /**
   * Returns the package of this validator switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EPackage getEPackage()
  {
    return GenModelPackage.eINSTANCE;
  }

  /**
   * Calls <code>validateXXX</code> for the corresponding classifier of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    switch (classifierID)
    {
      case GenModelPackage.GEN_MODEL:
        return validateGenModel((GenModel)value, diagnostics, context);
      case GenModelPackage.GEN_PACKAGE:
        return validateGenPackage((GenPackage)value, diagnostics, context);
      case GenModelPackage.GEN_CLASS:
        return validateGenClass((GenClass)value, diagnostics, context);
      case GenModelPackage.GEN_FEATURE:
        return validateGenFeature((GenFeature)value, diagnostics, context);
      case GenModelPackage.GEN_BASE:
        return validateGenBase((GenBase)value, diagnostics, context);
      case GenModelPackage.GEN_ENUM:
        return validateGenEnum((GenEnum)value, diagnostics, context);
      case GenModelPackage.GEN_ENUM_LITERAL:
        return validateGenEnumLiteral((GenEnumLiteral)value, diagnostics, context);
      case GenModelPackage.GEN_CLASSIFIER:
        return validateGenClassifier((GenClassifier)value, diagnostics, context);
      case GenModelPackage.GEN_DATA_TYPE:
        return validateGenDataType((GenDataType)value, diagnostics, context);
      case GenModelPackage.GEN_OPERATION:
        return validateGenOperation((GenOperation)value, diagnostics, context);
      case GenModelPackage.GEN_PARAMETER:
        return validateGenParameter((GenParameter)value, diagnostics, context);
      case GenModelPackage.GEN_TYPED_ELEMENT:
        return validateGenTypedElement((GenTypedElement)value, diagnostics, context);
      case GenModelPackage.GEN_ANNOTATION:
        return validateGenAnnotation((GenAnnotation)value, diagnostics, context);
      case GenModelPackage.GEN_TYPE_PARAMETER:
        return validateGenTypeParameter((GenTypeParameter)value, diagnostics, context);
      case GenModelPackage.GEN_PROVIDER_KIND:
        return validateGenProviderKind((GenProviderKind)value, diagnostics, context);
      case GenModelPackage.GEN_PROPERTY_KIND:
        return validateGenPropertyKind((GenPropertyKind)value, diagnostics, context);
      case GenModelPackage.GEN_RESOURCE_KIND:
        return validateGenResourceKind((GenResourceKind)value, diagnostics, context);
      case GenModelPackage.GEN_DELEGATION_KIND:
        return validateGenDelegationKind((GenDelegationKind)value, diagnostics, context);
      case GenModelPackage.GEN_JDK_LEVEL:
        return validateGenJDKLevel((GenJDKLevel)value, diagnostics, context);
      case GenModelPackage.GEN_RUNTIME_VERSION:
        return validateGenRuntimeVersion((GenRuntimeVersion)value, diagnostics, context);
      default:
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenModel(GenModel genModel, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genModel, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenPackage(GenPackage genPackage, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genPackage, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenClass(GenClass genClass, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genClass, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenFeature(GenFeature genFeature, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genFeature, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenBase(GenBase genBase, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genBase, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenEnum(GenEnum genEnum, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (!validate_NoCircularContainment(genEnum, diagnostics, context)) return false;
    boolean result = validate_EveryMultiplicityConforms(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryDataValueConforms(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryProxyResolves(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_UniqueID(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryKeyUnique(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validateGenEnum_NoEcoreDataType(genEnum, diagnostics, context);
    return result;
  }

  /**
   * Validates the NoEcoreDataType constraint of '<em>Gen Enum</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean validateGenEnum_NoEcoreDataType(GenEnum genEnum, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (genEnum.eIsSet(GenModelPackage.Literals.GEN_DATA_TYPE__ECORE_DATA_TYPE))
    {
      if (diagnostics != null)
      {
        diagnostics.add
          (createDiagnostic
            (Diagnostic.ERROR,
             DIAGNOSTIC_SOURCE,
             NO_ECORE_DATA_TYPE,
             "_UI_GenEnumNoEcoreDataType_diagnostic",
             new String[] { getObjectLabel(genEnum.getEcoreDataType(), context) },
             new Object[] { genEnum, genEnum.getEcoreDataType() },
             context));
      }
      return false;
    }
    return true;
  }
  
  @Override
  protected boolean validate_MultiplicityConforms(EObject eObject, EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return
      eStructuralFeature == GenModelPackage.Literals.GEN_DATA_TYPE__ECORE_DATA_TYPE && eObject instanceof GenEnum ||
      super.validate_MultiplicityConforms(eObject, eStructuralFeature, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenEnumLiteral(GenEnumLiteral genEnumLiteral, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genEnumLiteral, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenClassifier(GenClassifier genClassifier, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genClassifier, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenDataType(GenDataType genDataType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genDataType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenOperation(GenOperation genOperation, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genOperation, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenParameter(GenParameter genParameter, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genParameter, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenTypedElement(GenTypedElement genTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genTypedElement, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenAnnotation(GenAnnotation genAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genAnnotation, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenTypeParameter(GenTypeParameter genTypeParameter, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genTypeParameter, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenProviderKind(GenProviderKind genProviderKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenPropertyKind(GenPropertyKind genPropertyKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenResourceKind(GenResourceKind genResourceKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenDelegationKind(GenDelegationKind genDelegationKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenJDKLevel(GenJDKLevel genJDKLevel, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenRuntimeVersion(GenRuntimeVersion genRuntimeVersion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return CodeGenEcorePlugin.INSTANCE;
  }

} //GenModelValidator
