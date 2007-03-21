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
 * $Id: EPOFactoryImpl.java,v 1.3 2007/03/21 18:10:17 marcelop Exp $
 */
package com.example.sdo.epo.impl;

import com.example.sdo.epo.*;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EPOFactoryImpl extends EFactoryImpl implements EPOFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EPOFactory init()
  {
    try
    {
      EPOFactory theEPOFactory = (EPOFactory)EPackage.Registry.INSTANCE.getEFactory("http:///com/example/epo.ecore"); 
      if (theEPOFactory != null)
      {
        return theEPOFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EPOFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EPOFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID())
    {
      case EPOPackage.ITEM: return (EObject)createItem();
      case EPOPackage.US_ADDRESS: return (EObject)createUSAddress();
      case EPOPackage.PURCHASE_ORDER: return (EObject)createPurchaseOrder();
      case EPOPackage.SUPPLIER: return (EObject)createSupplier();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID())
    {
      case EPOPackage.SKU:
        return createSKUFromString(eDataType, initialValue);
      case EPOPackage.DATE:
        return createDateFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID())
    {
      case EPOPackage.SKU:
        return convertSKUToString(eDataType, instanceValue);
      case EPOPackage.DATE:
        return convertDateToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Item createItem() {
    ItemImpl item = new ItemImpl();
    return item;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public USAddress createUSAddress() {
    USAddressImpl usAddress = new USAddressImpl();
    return usAddress;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PurchaseOrder createPurchaseOrder() {
    PurchaseOrderImpl purchaseOrder = new PurchaseOrderImpl();
    return purchaseOrder;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Supplier createSupplier() {
    SupplierImpl supplier = new SupplierImpl();
    return supplier;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String createSKUFromString(EDataType eDataType, String initialValue) {
    return (String)super.createFromString(eDataType, initialValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertSKUToString(EDataType eDataType, Object instanceValue) {
    return super.convertToString(eDataType, instanceValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Date createDateFromString(EDataType eDataType, String initialValue) {
    return (Date)super.createFromString(eDataType, initialValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertDateToString(EDataType eDataType, Object instanceValue) {
    return super.convertToString(eDataType, instanceValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EPOPackage getEPOPackage() {
    return (EPOPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
  public static EPOPackage getPackage() {
    return EPOPackage.eINSTANCE;
  }

} //EPOFactoryImpl
