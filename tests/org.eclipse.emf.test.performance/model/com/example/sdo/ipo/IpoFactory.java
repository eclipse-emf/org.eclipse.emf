/**
 * <copyright>
 * </copyright>
 *
 * $Id: IpoFactory.java,v 1.1 2005/02/15 20:21:05 bportier Exp $
 */
package com.example.sdo.ipo;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.example.sdo.ipo.IpoPackage
 * @generated
 */
public interface IpoFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  IpoFactory eINSTANCE = new com.example.sdo.ipo.impl.IpoFactoryImpl();

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

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  IpoPackage getIpoPackage();

} //IpoFactory
