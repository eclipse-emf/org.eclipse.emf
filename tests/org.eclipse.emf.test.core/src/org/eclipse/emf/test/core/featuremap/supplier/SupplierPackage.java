/**
 * <copyright>
 * </copyright>
 *
 * $Id: SupplierPackage.java,v 1.1 2004/08/20 22:47:32 marcelop Exp $
 */
package org.eclipse.emf.test.core.featuremap.supplier;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.core.featuremap.supplier.SupplierFactory
 * @generated
 */
public interface SupplierPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "supplier";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.example.com/supplier";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "supplier";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SupplierPackage eINSTANCE = org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.featuremap.supplier.impl.PurchaseOrderImpl <em>Purchase Order</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.featuremap.supplier.impl.PurchaseOrderImpl
   * @see org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierPackageImpl#getPurchaseOrder()
   * @generated
   */
  int PURCHASE_ORDER = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER__ID = 0;

  /**
   * The number of structural features of the the '<em>Purchase Order</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierImpl <em>Supplier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierImpl
   * @see org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierPackageImpl#getSupplier()
   * @generated
   */
  int SUPPLIER = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPPLIER__NAME = 0;

  /**
   * The feature id for the '<em><b>Orders</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPPLIER__ORDERS = 1;

  /**
   * The feature id for the '<em><b>Preferred Orders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPPLIER__PREFERRED_ORDERS = 2;

  /**
   * The feature id for the '<em><b>Standard Orders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPPLIER__STANDARD_ORDERS = 3;

  /**
   * The number of structural features of the the '<em>Supplier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPPLIER_FEATURE_COUNT = 4;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder <em>Purchase Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Purchase Order</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder
   * @generated
   */
  EClass getPurchaseOrder();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder#getId()
   * @see #getPurchaseOrder()
   * @generated
   */
  EAttribute getPurchaseOrder_Id();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.featuremap.supplier.Supplier <em>Supplier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Supplier</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.Supplier
   * @generated
   */
  EClass getSupplier();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.featuremap.supplier.Supplier#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.Supplier#getName()
   * @see #getSupplier()
   * @generated
   */
  EAttribute getSupplier_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.core.featuremap.supplier.Supplier#getOrders <em>Orders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Orders</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.Supplier#getOrders()
   * @see #getSupplier()
   * @generated
   */
  EAttribute getSupplier_Orders();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.core.featuremap.supplier.Supplier#getPreferredOrders <em>Preferred Orders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Preferred Orders</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.Supplier#getPreferredOrders()
   * @see #getSupplier()
   * @generated
   */
  EReference getSupplier_PreferredOrders();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.core.featuremap.supplier.Supplier#getStandardOrders <em>Standard Orders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Standard Orders</em>'.
   * @see org.eclipse.emf.test.core.featuremap.supplier.Supplier#getStandardOrders()
   * @see #getSupplier()
   * @generated
   */
  EReference getSupplier_StandardOrders();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SupplierFactory getSupplierFactory();

} //SupplierPackage
