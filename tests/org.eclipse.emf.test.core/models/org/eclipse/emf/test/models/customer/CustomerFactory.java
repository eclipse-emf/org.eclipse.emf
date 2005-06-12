/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerFactory.java,v 1.2 2005/06/12 13:57:39 emerks Exp $
 */
package org.eclipse.emf.test.models.customer;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.customer.CustomerPackage
 * @generated
 */
public interface CustomerFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CustomerFactory eINSTANCE = new org.eclipse.emf.test.models.customer.impl.CustomerFactoryImpl();

  /**
   * Returns a new object of class '<em>Canada Addr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Canada Addr</em>'.
   * @generated
   */
  CanadaAddr createCanadaAddr();

  /**
   * Returns a new object of class '<em>Credit Info</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Credit Info</em>'.
   * @generated
   */
  CreditInfo createCreditInfo();

  /**
   * Returns a new object of class '<em>Customers Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Customers Type</em>'.
   * @generated
   */
  CustomersType createCustomersType();

  /**
   * Returns a new object of class '<em>Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type</em>'.
   * @generated
   */
  CustomerType createCustomerType();

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns a new object of class '<em>US Addr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>US Addr</em>'.
   * @generated
   */
  USAddr createUSAddr();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  CustomerPackage getCustomerPackage();

} //CustomerFactory
