/**
 * <copyright>
 * </copyright>
 *
 * $Id: AddressType.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.models.customer;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Address Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.AddressType#getStreet <em>Street</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.AddressType#getTown <em>Town</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getAddressType()
 * @model abstract="true"
 * @generated
 */
public interface AddressType extends EObject
{
  /**
   * Returns the value of the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Street</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Street</em>' attribute.
   * @see #setStreet(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getAddressType_Street()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getStreet();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.AddressType#getStreet <em>Street</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Street</em>' attribute.
   * @see #getStreet()
   * @generated
   */
  void setStreet(String value);

  /**
   * Returns the value of the '<em><b>Town</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Town</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Town</em>' attribute.
   * @see #setTown(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getAddressType_Town()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getTown();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.AddressType#getTown <em>Town</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Town</em>' attribute.
   * @see #getTown()
   * @generated
   */
  void setTown(String value);

} // AddressType
