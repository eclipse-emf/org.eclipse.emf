/**
 * <copyright>
 * </copyright>
 *
 * $Id: PPOFactory.java,v 1.2 2006/12/29 21:49:52 marcelop Exp $
 */
package com.example.ppo;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.example.ppo.PPOPackage
 * @generated
 */
public interface PPOFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PPOFactory eINSTANCE = com.example.ppo.impl.PPOFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Item</em>'.
   * @generated
   */
  Item createItem();

  /**
   * Returns a new object of class '<em>US Address</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>US Address</em>'.
   * @generated
   */
  USAddress createUSAddress();

  /**
   * Returns a new object of class '<em>Purchase Order</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Purchase Order</em>'.
   * @generated
   */
  PurchaseOrder createPurchaseOrder();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  PPOPackage getPPOPackage();

} //PPOFactory
