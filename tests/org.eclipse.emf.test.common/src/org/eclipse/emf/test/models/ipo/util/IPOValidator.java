/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IPOValidator.java,v 1.4 2008/05/09 20:10:32 emerks Exp $
 */
package org.eclipse.emf.test.models.ipo.util;

import java.math.BigInteger;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

import org.eclipse.emf.test.models.ipo.*;

import org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl
 * @generated
 */
public class IPOValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final IPOValidator INSTANCE = new IPOValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.test.models.ipo";

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

  /**
   * The cached base package validator.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XMLTypeValidator xmlTypeValidator;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IPOValidator()
  {
    super();
    xmlTypeValidator = XMLTypeValidator.INSTANCE;
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
    return IPOPackageImpl.eINSTANCE;
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
      case IPOPackageImpl.ADDRESS:
        return validateAddress((Address)value, diagnostics, context);
      case IPOPackageImpl.DOCUMENT_ROOT:
        return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
      case IPOPackageImpl.ITEMS:
        return validateItems((Items)value, diagnostics, context);
      case IPOPackageImpl.ITEM_TYPE:
        return validateItemType((ItemType)value, diagnostics, context);
      case IPOPackageImpl.PURCHASE_ORDER_TYPE:
        return validatePurchaseOrderType((PurchaseOrderType)value, diagnostics, context);
      case IPOPackageImpl.UK_ADDRESS:
        return validateUKAddress((UKAddress)value, diagnostics, context);
      case IPOPackageImpl.US_ADDRESS:
        return validateUSAddress((USAddress)value, diagnostics, context);
      case IPOPackageImpl.US_STATE:
        return validateUSState((USState)value, diagnostics, context);
      case IPOPackageImpl.POSTCODE:
        return validatePostcode((String)value, diagnostics, context);
      case IPOPackageImpl.QUANTITY_TYPE:
        return validateQuantityType((BigInteger)value, diagnostics, context);
      case IPOPackageImpl.SKU:
        return validateSKU((String)value, diagnostics, context);
      case IPOPackageImpl.UK_POSTCODE:
        return validateUKPostcode((String)value, diagnostics, context);
      case IPOPackageImpl.US_STATE_OBJECT:
        return validateUSStateObject((USState)value, diagnostics, context);
      default:
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateAddress(Address address, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)address, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)documentRoot, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateItems(Items items, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)items, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateItemType(ItemType itemType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)itemType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePurchaseOrderType(PurchaseOrderType purchaseOrderType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)purchaseOrderType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUKAddress(UKAddress ukAddress, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)ukAddress, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUSAddress(USAddress usAddress, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint((EObject)usAddress, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUSState(USState usState, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePostcode(String postcode, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validatePostcode_MinLength(postcode, diagnostics, context);
    if (result || diagnostics != null) result &= validatePostcode_MaxLength(postcode, diagnostics, context);
    return result;
  }

  /**
   * Validates the MinLength constraint of '<em>Postcode</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePostcode_MinLength(String postcode, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    int length = postcode.length();
    boolean result = length >= 7;
    if (!result && diagnostics != null)
      reportMinLengthViolation(IPOPackageImpl.Literals.POSTCODE, postcode, length, 7, diagnostics, context);
    return result;
  }

  /**
   * Validates the MaxLength constraint of '<em>Postcode</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePostcode_MaxLength(String postcode, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    int length = postcode.length();
    boolean result = length <= 7;
    if (!result && diagnostics != null)
      reportMaxLengthViolation(IPOPackageImpl.Literals.POSTCODE, postcode, length, 7, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateQuantityType(BigInteger quantityType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = xmlTypeValidator.validatePositiveInteger_Min(quantityType, diagnostics, context);
    if (result || diagnostics != null) result &= validateQuantityType_Max(quantityType, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateQuantityType_Max
   */
  public static final BigInteger QUANTITY_TYPE__MAX__VALUE = new BigInteger("100");

  /**
   * Validates the Max constraint of '<em>Quantity Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateQuantityType_Max(BigInteger quantityType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = quantityType.compareTo(QUANTITY_TYPE__MAX__VALUE) < 0;
    if (!result && diagnostics != null)
      reportMaxViolation(IPOPackageImpl.Literals.QUANTITY_TYPE, quantityType, QUANTITY_TYPE__MAX__VALUE, false, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateSKU(String sku, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validateSKU_Pattern(sku, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateSKU_Pattern
   */
  public static final  PatternMatcher [][] SKU__PATTERN__VALUES =
    new PatternMatcher [][]
    {
      new PatternMatcher []
      {
        XMLTypeUtil.createPatternMatcher("\\d{3}-[A-Z]{2}")
      }
    };

  /**
   * Validates the Pattern constraint of '<em>SKU</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateSKU_Pattern(String sku, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validatePattern(IPOPackageImpl.Literals.SKU, sku, SKU__PATTERN__VALUES, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUKPostcode(String ukPostcode, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validatePostcode_MinLength(ukPostcode, diagnostics, context);
    if (result || diagnostics != null) result &= validatePostcode_MaxLength(ukPostcode, diagnostics, context);
    if (result || diagnostics != null) result &= validateUKPostcode_Pattern(ukPostcode, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateUKPostcode_Pattern
   */
  public static final  PatternMatcher [][] UK_POSTCODE__PATTERN__VALUES =
    new PatternMatcher [][]
    {
      new PatternMatcher []
      {
        XMLTypeUtil.createPatternMatcher("[A-Z]{2}\\d\\s\\d[A-Z]{2}")
      }
    };

  /**
   * Validates the Pattern constraint of '<em>UK Postcode</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUKPostcode_Pattern(String ukPostcode, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validatePattern(IPOPackageImpl.Literals.UK_POSTCODE, ukPostcode, UK_POSTCODE__PATTERN__VALUES, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUSStateObject(USState usStateObject, DiagnosticChain diagnostics, Map<Object, Object> context)
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
    return super.getResourceLocator();
  }

} //IPOValidator
