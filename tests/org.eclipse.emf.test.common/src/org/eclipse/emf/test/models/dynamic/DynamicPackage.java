/**
 * Copyright (c) 2022 Eclipse Contributor and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.models.dynamic;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.dynamic.DynamicFactory
 * @model kind="package"
 * @generated
 */
public interface DynamicPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "dynamic";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/dynamic";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "dynamic";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DynamicPackage eINSTANCE = org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dynamic.impl.ProviderImpl <em>Provider</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dynamic.impl.ProviderImpl
   * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getProvider()
   * @generated
   */
  int PROVIDER = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDER__ID = 0;

  /**
   * The feature id for the '<em><b>Admin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDER__ADMIN = 1;

  /**
   * The number of structural features of the '<em>Provider</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDER_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Provider</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDER_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dynamic.impl.ServiceImpl <em>Service</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dynamic.impl.ServiceImpl
   * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getService()
   * @generated
   */
  int SERVICE = 2;

  /**
   * The feature id for the '<em><b>Details</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE__DETAILS = 0;

  /**
   * The number of structural features of the '<em>Service</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Service</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dynamic.impl.AdminImpl <em>Admin</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dynamic.impl.AdminImpl
   * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getAdmin()
   * @generated
   */
  int ADMIN = 1;

  /**
   * The feature id for the '<em><b>Details</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADMIN__DETAILS = SERVICE__DETAILS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADMIN__NAME = SERVICE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADMIN__LOCATION = SERVICE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Admin</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADMIN_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Admin</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADMIN_OPERATION_COUNT = SERVICE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dynamic.impl.DetailImpl <em>Detail</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dynamic.impl.DetailImpl
   * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getDetail()
   * @generated
   */
  int DETAIL = 3;

  /**
   * The number of structural features of the '<em>Detail</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DETAIL_FEATURE_COUNT = 0;

  /**
   * The number of operations of the '<em>Detail</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DETAIL_OPERATION_COUNT = 0;


  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dynamic.impl.BaseImpl <em>Base</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dynamic.impl.BaseImpl
   * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getBase()
   * @generated
   */
  int BASE = 4;

  /**
   * The number of structural features of the '<em>Base</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BASE_FEATURE_COUNT = 0;

  /**
   * The number of operations of the '<em>Base</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BASE_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dynamic.Provider <em>Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Provider</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Provider
   * @generated
   */
  EClass getProvider();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.dynamic.Provider#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Provider#getId()
   * @see #getProvider()
   * @generated
   */
  EAttribute getProvider_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.dynamic.Provider#getAdmin <em>Admin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Admin</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Provider#getAdmin()
   * @see #getProvider()
   * @generated
   */
  EReference getProvider_Admin();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dynamic.Admin <em>Admin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Admin</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Admin
   * @generated
   */
  EClass getAdmin();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.dynamic.Admin#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Admin#getName()
   * @see #getAdmin()
   * @generated
   */
  EAttribute getAdmin_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.dynamic.Admin#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Admin#getLocation()
   * @see #getAdmin()
   * @generated
   */
  EAttribute getAdmin_Location();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dynamic.Service <em>Service</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Service</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Service
   * @generated
   */
  EClass getService();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.dynamic.Service#getDetails <em>Details</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Details</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Service#getDetails()
   * @see #getService()
   * @generated
   */
  EReference getService_Details();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dynamic.Detail <em>Detail</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Detail</em>'.
   * @see org.eclipse.emf.test.models.dynamic.Detail
   * @generated
   */
  EClass getDetail();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.EObject <em>Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Base</em>'.
   * @see org.eclipse.emf.ecore.EObject
   * @model instanceClass="org.eclipse.emf.ecore.EObject"
   * @generated
   */
  EClass getBase();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DynamicFactory getDynamicFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dynamic.impl.ProviderImpl <em>Provider</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dynamic.impl.ProviderImpl
     * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getProvider()
     * @generated
     */
    EClass PROVIDER = eINSTANCE.getProvider();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROVIDER__ID = eINSTANCE.getProvider_Id();

    /**
     * The meta object literal for the '<em><b>Admin</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROVIDER__ADMIN = eINSTANCE.getProvider_Admin();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dynamic.impl.AdminImpl <em>Admin</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dynamic.impl.AdminImpl
     * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getAdmin()
     * @generated
     */
    EClass ADMIN = eINSTANCE.getAdmin();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADMIN__NAME = eINSTANCE.getAdmin_Name();

    /**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADMIN__LOCATION = eINSTANCE.getAdmin_Location();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dynamic.impl.ServiceImpl <em>Service</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dynamic.impl.ServiceImpl
     * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getService()
     * @generated
     */
    EClass SERVICE = eINSTANCE.getService();

    /**
     * The meta object literal for the '<em><b>Details</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SERVICE__DETAILS = eINSTANCE.getService_Details();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dynamic.impl.DetailImpl <em>Detail</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dynamic.impl.DetailImpl
     * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getDetail()
     * @generated
     */
    EClass DETAIL = eINSTANCE.getDetail();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dynamic.impl.BaseImpl <em>Base</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dynamic.impl.BaseImpl
     * @see org.eclipse.emf.test.models.dynamic.impl.DynamicPackageImpl#getBase()
     * @generated
     */
    EClass BASE = eINSTANCE.getBase();

  }

} //DynamicPackage
