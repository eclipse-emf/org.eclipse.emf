/**
 * <copyright>
 * </copyright>
 *
 * $Id: PurchaseOrder.java,v 1.3 2007/06/15 21:22:36 emerks Exp $
 */
package org.eclipse.emf.test.core.featuremap.supplier;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Purchase Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage#getPurchaseOrder()
 * @model extendedMetaData="name='PurchaseOrder' kind='elementOnly'"
 * @generated
 */
public interface PurchaseOrder extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage#getPurchaseOrder_Id()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='id'"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

} // PurchaseOrder
