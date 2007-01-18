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
 * $Id: IPOFactoryImpl.java,v 1.2 2007/01/18 22:06:39 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.ipo.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IPOFactoryImpl extends EFactoryImpl implements IPOFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final IPOFactoryImpl eINSTANCE = init();

  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static IPOFactoryImpl init()
  {
    try
    {
      IPOFactoryImpl theIPOFactory = (IPOFactoryImpl)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/IPO"); 
      if (theIPOFactory != null)
      {
        return theIPOFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new IPOFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IPOFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case IPOPackageImpl.ADDRESS: return (EObject)createAddress();
      case IPOPackageImpl.DOCUMENT_ROOT: return (EObject)createDocumentRoot();
      case IPOPackageImpl.ITEMS: return (EObject)createItems();
      case IPOPackageImpl.ITEM_TYPE: return (EObject)createItemType();
      case IPOPackageImpl.PURCHASE_ORDER_TYPE: return (EObject)createPurchaseOrderType();
      case IPOPackageImpl.UK_ADDRESS: return (EObject)createUKAddress();
      case IPOPackageImpl.US_ADDRESS: return (EObject)createUSAddress();
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
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case IPOPackageImpl.US_STATE:
        return createUSStateFromString(eDataType, initialValue);
      case IPOPackageImpl.POSTCODE:
        return createPostcodeFromString(eDataType, initialValue);
      case IPOPackageImpl.QUANTITY_TYPE:
        return createQuantityTypeFromString(eDataType, initialValue);
      case IPOPackageImpl.SKU:
        return createSKUFromString(eDataType, initialValue);
      case IPOPackageImpl.UK_POSTCODE:
        return createUKPostcodeFromString(eDataType, initialValue);
      case IPOPackageImpl.US_STATE_OBJECT:
        return createUSStateObjectFromString(eDataType, initialValue);
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
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case IPOPackageImpl.US_STATE:
        return convertUSStateToString(eDataType, instanceValue);
      case IPOPackageImpl.POSTCODE:
        return convertPostcodeToString(eDataType, instanceValue);
      case IPOPackageImpl.QUANTITY_TYPE:
        return convertQuantityTypeToString(eDataType, instanceValue);
      case IPOPackageImpl.SKU:
        return convertSKUToString(eDataType, instanceValue);
      case IPOPackageImpl.UK_POSTCODE:
        return convertUKPostcodeToString(eDataType, instanceValue);
      case IPOPackageImpl.US_STATE_OBJECT:
        return convertUSStateObjectToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Address createAddress()
  {
    AddressImpl address = new AddressImpl();
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DocumentRoot createDocumentRoot()
  {
    DocumentRootImpl documentRoot = new DocumentRootImpl();
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Items createItems()
  {
    ItemsImpl items = new ItemsImpl();
    return items;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ItemType createItemType()
  {
    ItemTypeImpl itemType = new ItemTypeImpl();
    return itemType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PurchaseOrderType createPurchaseOrderType()
  {
    PurchaseOrderTypeImpl purchaseOrderType = new PurchaseOrderTypeImpl();
    return purchaseOrderType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UKAddress createUKAddress()
  {
    UKAddressImpl ukAddress = new UKAddressImpl();
    return ukAddress;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public USAddress createUSAddress()
  {
    USAddressImpl usAddress = new USAddressImpl();
    return usAddress;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public USState createUSStateFromString(EDataType eDataType, String initialValue)
  {
    USState result = USState.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUSStateToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createPostcodeFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertPostcodeToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createQuantityTypeFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.POSITIVE_INTEGER, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertQuantityTypeToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.POSITIVE_INTEGER, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createSKUFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSKUToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createUKPostcodeFromString(EDataType eDataType, String initialValue)
  {
    return createPostcodeFromString(IPOPackageImpl.Literals.POSTCODE, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUKPostcodeToString(EDataType eDataType, Object instanceValue)
  {
    return convertPostcodeToString(IPOPackageImpl.Literals.POSTCODE, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public USState createUSStateObjectFromString(EDataType eDataType, String initialValue)
  {
    return createUSStateFromString(IPOPackageImpl.Literals.US_STATE, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUSStateObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertUSStateToString(IPOPackageImpl.Literals.US_STATE, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IPOPackageImpl getIPOPackageImpl()
  {
    return (IPOPackageImpl)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static IPOPackageImpl getPackage()
  {
    return IPOPackageImpl.eINSTANCE;
  }

} //IPOFactoryImpl
