/**
 * <copyright>
 * </copyright>
 *
 * $Id: CanadaAddr.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Canada Addr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.CanadaAddr#getZip <em>Zip</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.CanadaAddr#getProvince <em>Province</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCanadaAddr()
 * @model 
 * @generated
 */
public interface CanadaAddr extends AddressType
{
  /**
   * Returns the value of the '<em><b>Zip</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Zip</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Zip</em>' attribute.
   * @see #setZip(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCanadaAddr_Zip()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getZip();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CanadaAddr#getZip <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Zip</em>' attribute.
   * @see #getZip()
   * @generated
   */
  void setZip(String value);

  /**
   * Returns the value of the '<em><b>Province</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Province</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Province</em>' attribute.
   * @see #setProvince(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getCanadaAddr_Province()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getProvince();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.CanadaAddr#getProvince <em>Province</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Province</em>' attribute.
   * @see #getProvince()
   * @generated
   */
  void setProvince(String value);

} // CanadaAddr
