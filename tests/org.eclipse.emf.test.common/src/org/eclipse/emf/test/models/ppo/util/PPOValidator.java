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
 * $Id: PPOValidator.java,v 1.3 2007/06/12 15:08:11 emerks Exp $
 */
package org.eclipse.emf.test.models.ppo.util;

import java.util.Date;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.test.models.ppo.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.ppo.PPOPackage
 * @generated
 */
public class PPOValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final PPOValidator INSTANCE = new PPOValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.test.models.ppo";

  /**
   * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Has US State' of 'US Address'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final int US_ADDRESS__HAS_US_STATE = 1;

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 1;

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PPOValidator()
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
    return PPOPackage.eINSTANCE;
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
      case PPOPackage.ITEM:
        return validateItem((Item)value, diagnostics, context);
      case PPOPackage.US_ADDRESS:
        return validateUSAddress((USAddress)value, diagnostics, context);
      case PPOPackage.PURCHASE_ORDER:
        return validatePurchaseOrder((PurchaseOrder)value, diagnostics, context);
      case PPOPackage.SKU:
        return validateSKU((String)value, diagnostics, context);
      case PPOPackage.DATE:
        return validateDate((Date)value, diagnostics, context);
      default: 
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateItem(Item item, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validate_EveryMultiplicityConforms(item, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryDataValueConforms(item, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(item, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryProxyResolves(item, diagnostics, context);
    if (result || diagnostics != null) result &= validate_UniqueID(item, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryKeyUnique(item, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(item, diagnostics, context);
    if (result || diagnostics != null) result &= validateItem_NonNegativeQuantity(item, diagnostics, context);
    if (result || diagnostics != null) result &= validateItem_ValidShipDate(item, diagnostics, context);
    return result;
  }

  /**
   * Validates the NonNegativeQuantity constraint of '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean validateItem_NonNegativeQuantity(Item item, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (item.getQuantity() < 0)
    {
      if (diagnostics != null)
      {
        diagnostics.add
          (new BasicDiagnostic
            (Diagnostic.ERROR,
             DIAGNOSTIC_SOURCE,
             0,
             EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NonNegativeQuantity", getObjectLabel(item, context) }),
             new Object[] { item }));
      }
      return false;
    }
    return true;
  }

  /**
   * Validates the ValidShipDate constraint of '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean validateItem_ValidShipDate(Item item, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (item.eContainer() instanceof PurchaseOrder)
    {
      Date orderDate = ((PurchaseOrder)item.eContainer()).getOrderDate();
      Date shipDate = item.getShipDate();
      
      if (orderDate != null && shipDate != null && orderDate.compareTo(shipDate) > 0)
      {
        if (diagnostics != null)
        {
          diagnostics.add
            (new BasicDiagnostic
              (Diagnostic.ERROR,
               DIAGNOSTIC_SOURCE,
               0,
               EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ValidShipDate", getObjectLabel(item, context) }),
               new Object[] { item }));
        }
        return false;
      }
    }
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUSAddress(USAddress usAddress, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validate_EveryMultiplicityConforms(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryDataValueConforms(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryProxyResolves(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validate_UniqueID(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryKeyUnique(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(usAddress, diagnostics, context);
    if (result || diagnostics != null) result &= validateUSAddress_hasUSState(usAddress, diagnostics, context);
    return result;
  }

  /**
   * Validates the hasUSState constraint of '<em>US Address</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUSAddress_hasUSState(USAddress usAddress, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return usAddress.hasUSState(diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePurchaseOrder(PurchaseOrder purchaseOrder, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(purchaseOrder, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateSKU(String sku, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDate(Date date, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

} //PPOValidator
