/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerType.java,v 1.1 2007/01/18 15:50:16 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomerType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomerType#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomerType#getCreditCard <em>Credit Card</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomerType#getPaymentDay <em>Payment Day</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CustomerType#getID <em>ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomerType()
 * @model extendedMetaData="name='dbcustomer' kind='elementOnly'"
 * @generated
 */
public interface CustomerType extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomerType_Name()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='name'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Address</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Address</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Address</em>' containment reference.
   * @see #setAddress(AddressType)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomerType_Address()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='address'"
   * @generated
   */
  AddressType getAddress();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getAddress <em>Address</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Address</em>' containment reference.
   * @see #getAddress()
   * @generated
   */
  void setAddress(AddressType value);

  /**
   * Returns the value of the '<em><b>Credit Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Credit Card</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Credit Card</em>' containment reference.
   * @see #setCreditCard(CreditInfo)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomerType_CreditCard()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='creditCard'"
   * @generated
   */
  CreditInfo getCreditCard();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getCreditCard <em>Credit Card</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Credit Card</em>' containment reference.
   * @see #getCreditCard()
   * @generated
   */
  void setCreditCard(CreditInfo value);

  /**
   * Returns the value of the '<em><b>Payment Day</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Payment Day</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Payment Day</em>' attribute.
   * @see #isSetPaymentDay()
   * @see #unsetPaymentDay()
   * @see #setPaymentDay(Object)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomerType_PaymentDay()
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.GDay" required="true"
   *        extendedMetaData="kind='element' name='paymentDay'"
   * @generated
   */
  Object getPaymentDay();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getPaymentDay <em>Payment Day</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Payment Day</em>' attribute.
   * @see #isSetPaymentDay()
   * @see #unsetPaymentDay()
   * @see #getPaymentDay()
   * @generated
   */
  void setPaymentDay(Object value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getPaymentDay <em>Payment Day</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetPaymentDay()
   * @see #getPaymentDay()
   * @see #setPaymentDay(Object)
   * @generated
   */
  void unsetPaymentDay();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getPaymentDay <em>Payment Day</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Payment Day</em>' attribute is set.
   * @see #unsetPaymentDay()
   * @see #getPaymentDay()
   * @see #setPaymentDay(Object)
   * @generated
   */
  boolean isSetPaymentDay();

  /**
   * Returns the value of the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ID</em>' attribute.
   * @see #setID(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCustomerType_ID()
   * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
   *        extendedMetaData="kind='attribute' name='ID' namespace='##targetNamespace'"
   * @generated
   */
  String getID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CustomerType#getID <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ID</em>' attribute.
   * @see #getID()
   * @generated
   */
  void setID(String value);

} // CustomerType
