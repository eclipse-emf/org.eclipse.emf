/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomersType.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.models.customer;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>sType</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomersType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomersType#getCustomer <em>Customer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomersType()
 * @model 
 * @generated
 */
public interface CustomersType extends EObject
{
  /**
   * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mixed</em>' attribute list.
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomersType_Mixed()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   * @generated
   */
  FeatureMap getMixed();

  /**
   * Returns the value of the '<em><b>Customer</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.customer.CustomerType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Customer</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Customer</em>' containment reference list.
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomersType_Customer()
   * @model type="org.eclipse.emf.test.models.customer.CustomerType" containment="true" resolveProxies="false" required="true" transient="true" volatile="true" derived="true"
   * @generated
   */
  EList getCustomer();

} // CustomersType
