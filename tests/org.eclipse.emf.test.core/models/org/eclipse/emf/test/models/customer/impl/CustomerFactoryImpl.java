/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerFactoryImpl.java,v 1.1 2005/02/08 20:54:12 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

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
   * Creates and instance of the factory.
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
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case CustomerPackage.CANADA_ADDR: return createCanadaAddr();
      case CustomerPackage.CREDIT_INFO: return createCreditInfo();
      case CustomerPackage.CUSTOMER_STYPE: return createCustomersType();
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
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case CustomerPackage.US_STATE:
      {
        USState result = USState.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
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
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case CustomerPackage.US_STATE:
        return instanceValue == null ? null : instanceValue.toString();
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
  public USState createUSStateObjectFromString(EDataType eDataType, String initialValue)
  {
    return (USState)CustomerFactory.eINSTANCE.createFromString(CustomerPackage.eINSTANCE.getUSState(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUSStateObjectToString(EDataType eDataType, Object instanceValue)
  {
    return CustomerFactory.eINSTANCE.convertToString(CustomerPackage.eINSTANCE.getUSState(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createZipCodesFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertZipCodesToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createZipUnionFromString(EDataType eDataType, String initialValue)
  {
    try
    {
      Object result = (Object)CustomerFactory.eINSTANCE.createFromString(CustomerPackage.eINSTANCE.getUSState(), initialValue);
      if (result != null)
      {
        return result;
      }
    }
    catch (RuntimeException exception)
    {
    }
    return (Object)CustomerFactory.eINSTANCE.createFromString(CustomerPackage.eINSTANCE.getZipCodes(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertZipUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (CustomerPackage.eINSTANCE.getUSState().isInstance(instanceValue))
    {
      return CustomerFactory.eINSTANCE.convertToString(CustomerPackage.eINSTANCE.getUSState(), instanceValue);
    }
    if (CustomerPackage.eINSTANCE.getZipCodes().isInstance(instanceValue))
    {
      return CustomerFactory.eINSTANCE.convertToString(CustomerPackage.eINSTANCE.getZipCodes(), instanceValue);
    }
    return null;
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
  public static CustomerPackage getPackage()
  {
    return CustomerPackage.eINSTANCE;
  }

} //CustomerFactoryImpl
