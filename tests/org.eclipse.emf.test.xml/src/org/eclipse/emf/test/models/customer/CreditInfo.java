/**
 * <copyright>
 * </copyright>
 *
 * $Id: CreditInfo.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Credit Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.CreditInfo#getHolder <em>Holder</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CreditInfo#getCcNumber <em>Cc Number</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CreditInfo#getExpireDate <em>Expire Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo()
 * @model 
 * @generated
 */
public interface CreditInfo extends EObject
{
  /**
   * Returns the value of the '<em><b>Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Holder</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Holder</em>' attribute.
   * @see #setHolder(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo_Holder()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getHolder();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CreditInfo#getHolder <em>Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Holder</em>' attribute.
   * @see #getHolder()
   * @generated
   */
  void setHolder(String value);

  /**
   * Returns the value of the '<em><b>Cc Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cc Number</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cc Number</em>' attribute.
   * @see #setCcNumber(BigInteger)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo_CcNumber()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
   * @generated
   */
  BigInteger getCcNumber();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CreditInfo#getCcNumber <em>Cc Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cc Number</em>' attribute.
   * @see #getCcNumber()
   * @generated
   */
  void setCcNumber(BigInteger value);

  /**
   * Returns the value of the '<em><b>Expire Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expire Date</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expire Date</em>' attribute.
   * @see #setExpireDate(Object)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCreditInfo_ExpireDate()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.GYearMonth" required="true"
   * @generated
   */
  Object getExpireDate();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CreditInfo#getExpireDate <em>Expire Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expire Date</em>' attribute.
   * @see #getExpireDate()
   * @generated
   */
  void setExpireDate(Object value);

} // CreditInfo
