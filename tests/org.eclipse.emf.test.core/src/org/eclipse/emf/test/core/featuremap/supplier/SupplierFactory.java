/**
 * <copyright>
 * </copyright>
 *
 * $Id: SupplierFactory.java,v 1.2 2006/12/29 21:49:52 marcelop Exp $
 */
package org.eclipse.emf.test.core.featuremap.supplier;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage
 * @generated
 */
public interface SupplierFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SupplierFactory eINSTANCE = org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Purchase Order</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Purchase Order</em>'.
   * @generated
   */
  PurchaseOrder createPurchaseOrder();

  /**
   * Returns a new object of class '<em>Supplier</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Supplier</em>'.
   * @generated
   */
  Supplier createSupplier();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SupplierPackage getSupplierPackage();

} //SupplierFactory
