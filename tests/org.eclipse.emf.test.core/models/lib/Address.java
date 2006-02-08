/**
 * <copyright>
 * </copyright>
 *
 * $Id: Address.java,v 1.1 2006/02/08 21:30:38 marcelop Exp $
 */
package lib;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lib.Address#getPostalCode <em>Postal Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see lib.LibPackage#getAddress()
 * @model
 * @generated
 */
public interface Address extends EObject
{
  /**
   * Returns the value of the '<em><b>Postal Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Postal Code</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Postal Code</em>' attribute.
   * @see #setPostalCode(String)
   * @see lib.LibPackage#getAddress_PostalCode()
   * @model
   * @generated
   */
  String getPostalCode();

  /**
   * Sets the value of the '{@link lib.Address#getPostalCode <em>Postal Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Postal Code</em>' attribute.
   * @see #getPostalCode()
   * @generated
   */
  void setPostalCode(String value);

} // Address
