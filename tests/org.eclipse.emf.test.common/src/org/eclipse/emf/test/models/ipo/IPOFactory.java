/**
 * <copyright>
 * </copyright>
 *
 * $Id: IPOFactory.java,v 1.1 2007/01/18 15:50:24 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface IPOFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  IPOFactory INSTANCE = org.eclipse.emf.test.models.ipo.impl.IPOFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>Address</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Address</em>'.
   * @generated
   */
  Address createAddress();

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns a new object of class '<em>Items</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Items</em>'.
   * @generated
   */
  Items createItems();

  /**
   * Returns a new object of class '<em>Item Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Item Type</em>'.
   * @generated
   */
  ItemType createItemType();

  /**
   * Returns a new object of class '<em>Purchase Order Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Purchase Order Type</em>'.
   * @generated
   */
  PurchaseOrderType createPurchaseOrderType();

  /**
   * Returns a new object of class '<em>UK Address</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>UK Address</em>'.
   * @generated
   */
  UKAddress createUKAddress();

  /**
   * Returns a new object of class '<em>US Address</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>US Address</em>'.
   * @generated
   */
  USAddress createUSAddress();

} //IPOFactory
