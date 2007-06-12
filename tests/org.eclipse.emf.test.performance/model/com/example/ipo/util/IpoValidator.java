/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: IpoValidator.java,v 1.4 2007/06/12 15:07:36 emerks Exp $
 */
package com.example.ipo.util;

import com.example.ipo.*;

import java.math.BigInteger;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.example.ipo.IpoPackage
 * @generated
 */
public class IpoValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final IpoValidator INSTANCE = new IpoValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "com.example.ipo";

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
  public IpoValidator()
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
    return IpoPackage.eINSTANCE;
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
      case IpoPackage.ADDRESS:
        return validateAddress((Address)value, diagnostics, context);
      case IpoPackage.DOCUMENT_ROOT:
        return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
      case IpoPackage.ITEMS:
        return validateItems((Items)value, diagnostics, context);
      case IpoPackage.ITEM_TYPE:
        return validateItemType((ItemType)value, diagnostics, context);
      case IpoPackage.PURCHASE_ORDER_TYPE:
        return validatePurchaseOrderType((PurchaseOrderType)value, diagnostics, context);
      case IpoPackage.UK_ADDRESS:
        return validateUKAddress((UKAddress)value, diagnostics, context);
      case IpoPackage.US_ADDRESS:
        return validateUSAddress((USAddress)value, diagnostics, context);
      case IpoPackage.US_STATE:
        return validateUSState((USState)value, diagnostics, context);
      case IpoPackage.POSTCODE:
        return validatePostcode((String)value, diagnostics, context);
      case IpoPackage.QUANTITY_TYPE:
        return validateQuantityType((BigInteger)value, diagnostics, context);
      case IpoPackage.SKU:
        return validateSKU((String)value, diagnostics, context);
      case IpoPackage.UK_POSTCODE:
        return validateUKPostcode((String)value, diagnostics, context);
      case IpoPackage.US_STATE_OBJECT:
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
    return validate_EveryDefaultConstraint(address, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateItems(Items items, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(items, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateItemType(ItemType itemType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(itemType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePurchaseOrderType(PurchaseOrderType purchaseOrderType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(purchaseOrderType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUKAddress(UKAddress ukAddress, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(ukAddress, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUSAddress(USAddress usAddress, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(usAddress, diagnostics, context);
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
      reportMinLengthViolation(IpoPackage.Literals.POSTCODE, postcode, length, 7, diagnostics, context);
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
      reportMaxLengthViolation(IpoPackage.Literals.POSTCODE, postcode, length, 7, diagnostics, context);
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
      reportMaxViolation(IpoPackage.Literals.QUANTITY_TYPE, quantityType, QUANTITY_TYPE__MAX__VALUE, false, diagnostics, context);
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
    return validatePattern(IpoPackage.Literals.SKU, sku, SKU__PATTERN__VALUES, diagnostics, context);
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
    return validatePattern(IpoPackage.Literals.UK_POSTCODE, ukPostcode, UK_POSTCODE__PATTERN__VALUES, diagnostics, context);
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

} //IpoValidator
