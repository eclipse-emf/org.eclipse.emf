/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.customer.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.customer.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomerFactoryImpl extends EFactoryImpl implements CustomerFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CustomerFactory init()
  {
    try
    {
      CustomerFactory theCustomerFactory = (CustomerFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/Customer"); 
      if (theCustomerFactory != null)
      {
        return theCustomerFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CustomerFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerFactoryImpl()
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
      case CustomerPackage.CANADA_ADDR: return createCanadaAddr();
      case CustomerPackage.CREDIT_INFO: return createCreditInfo();
      case CustomerPackage.CUSTOMERS_TYPE: return createCustomersType();
      case CustomerPackage.CUSTOMER_TYPE: return createCustomerType();
      case CustomerPackage.DOCUMENT_ROOT: return createDocumentRoot();
      case CustomerPackage.US_ADDR: return createUSAddr();
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
      case CustomerPackage.US_STATE:
        return createUSStateFromString(eDataType, initialValue);
      case CustomerPackage.US_STATE_OBJECT:
        return createUSStateObjectFromString(eDataType, initialValue);
      case CustomerPackage.ZIP_CODES:
        return createZipCodesFromString(eDataType, initialValue);
      case CustomerPackage.ZIP_UNION:
        return createZipUnionFromString(eDataType, initialValue);
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
      case CustomerPackage.US_STATE:
        return convertUSStateToString(eDataType, instanceValue);
      case CustomerPackage.US_STATE_OBJECT:
        return convertUSStateObjectToString(eDataType, instanceValue);
      case CustomerPackage.ZIP_CODES:
        return convertZipCodesToString(eDataType, instanceValue);
      case CustomerPackage.ZIP_UNION:
        return convertZipUnionToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CanadaAddr createCanadaAddr()
  {
    CanadaAddrImpl canadaAddr = new CanadaAddrImpl();
    return canadaAddr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CreditInfo createCreditInfo()
  {
    CreditInfoImpl creditInfo = new CreditInfoImpl();
    return creditInfo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomersType createCustomersType()
  {
    CustomersTypeImpl customersType = new CustomersTypeImpl();
    return customersType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerType createCustomerType()
  {
    CustomerTypeImpl customerType = new CustomerTypeImpl();
    return customerType;
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
  public USAddr createUSAddr()
  {
    USAddrImpl usAddr = new USAddrImpl();
    return usAddr;
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
  public USState createUSStateObjectFromString(EDataType eDataType, String initialValue)
  {
    return createUSStateFromString(CustomerPackage.Literals.US_STATE, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUSStateObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertUSStateToString(CustomerPackage.Literals.US_STATE, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createZipCodesFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INTEGER, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertZipCodesToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INTEGER, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createZipUnionFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    Object result = null;
    RuntimeException exception = null;
    try
    {
      result = createUSStateFromString(CustomerPackage.Literals.US_STATE, initialValue);
      if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
      {
        return result;
      }
    }
    catch (RuntimeException e)
    {
      exception = e;
    }
    try
    {
      result = createZipCodesFromString(CustomerPackage.Literals.ZIP_CODES, initialValue);
      if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
      {
        return result;
      }
    }
    catch (RuntimeException e)
    {
      exception = e;
    }
    if (result != null || exception == null) return result;
    
    throw exception;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertZipUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    if (CustomerPackage.Literals.US_STATE.isInstance(instanceValue))
    {
      try
      {
        String value = convertUSStateToString(CustomerPackage.Literals.US_STATE, instanceValue);
        if (value != null) return value;
      }
      catch (Exception e)
      {
        // Keep trying other member types until all have failed.
      }
    }
    if (CustomerPackage.Literals.ZIP_CODES.isInstance(instanceValue))
    {
      try
      {
        String value = convertZipCodesToString(CustomerPackage.Literals.ZIP_CODES, instanceValue);
        if (value != null) return value;
      }
      catch (Exception e)
      {
        // Keep trying other member types until all have failed.
      }
    }
    throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerPackage getCustomerPackage()
  {
    return (CustomerPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static CustomerPackage getPackage()
  {
    return CustomerPackage.eINSTANCE;
  }

} //CustomerFactoryImpl
