/**
 * <copyright>
 * </copyright>
 *
 * $Id: USAddr.java,v 1.1 2004/06/16 18:01:17 elena Exp $
 */
package org.eclipse.emf.test.models.customer;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>US Addr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.USAddr#getZip <em>Zip</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.USAddr#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getUSAddr()
 * @model 
 * @generated
 */
public interface USAddr extends AddressType
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
   * @see #setZip(Object)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getUSAddr_Zip()
   * @model unique="false" dataType="org.eclipse.emf.test.models.customer.ZipUnion" required="true"
   * @generated
   */
  Object getZip();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.USAddr#getZip <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Zip</em>' attribute.
   * @see #getZip()
   * @generated
   */
  void setZip(Object value);

  /**
   * Returns the value of the '<em><b>State</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State</em>' attribute.
   * @see #setState(String)
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#getUSAddr_State()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getState();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.customer.USAddr#getState <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>State</em>' attribute.
   * @see #getState()
   * @generated
   */
  void setState(String value);

} // USAddr
