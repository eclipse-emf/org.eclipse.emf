/**
 * <copyright>
 * </copyright>
 *
 * $Id: SupplierFactoryImpl.java,v 1.2 2006/12/29 21:49:52 marcelop Exp $
 */
package org.eclipse.emf.test.core.featuremap.supplier.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.test.core.featuremap.supplier.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SupplierFactoryImpl extends EFactoryImpl implements SupplierFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SupplierFactory init()
  {
    try
    {
      SupplierFactory theSupplierFactory = (SupplierFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.example.com/supplier"); 
      if (theSupplierFactory != null)
      {
        return theSupplierFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SupplierFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SupplierFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SupplierPackage.PURCHASE_ORDER: return createPurchaseOrder();
      case SupplierPackage.SUPPLIER: return createSupplier();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PurchaseOrder createPurchaseOrder()
  {
    PurchaseOrderImpl purchaseOrder = new PurchaseOrderImpl();
    return purchaseOrder;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Supplier createSupplier()
  {
    SupplierImpl supplier = new SupplierImpl();
    return supplier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SupplierPackage getSupplierPackage()
  {
    return (SupplierPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SupplierPackage getPackage()
  {
    return SupplierPackage.eINSTANCE;
  }

} //SupplierFactoryImpl
