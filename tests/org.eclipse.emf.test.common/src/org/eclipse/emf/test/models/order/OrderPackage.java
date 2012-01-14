/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.order;

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
 * @see org.eclipse.emf.test.models.order.OrderFactory
 * @model kind="package"
 * @generated
 */
public interface OrderPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "order";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/Order";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "order";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  OrderPackage eINSTANCE = org.eclipse.emf.test.models.order.impl.OrderPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.order.impl.CustomerOrderImpl <em>Customer Order</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.order.impl.CustomerOrderImpl
   * @see org.eclipse.emf.test.models.order.impl.OrderPackageImpl#getCustomerOrder()
   * @generated
   */
  int CUSTOMER_ORDER = 0;

  /**
   * The feature id for the '<em><b>Movies To See</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_ORDER__MOVIES_TO_SEE = 0;

  /**
   * The feature id for the '<em><b>Movies Seen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_ORDER__MOVIES_SEEN = 1;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_ORDER__ANY = 2;

  /**
   * The feature id for the '<em><b>Customer ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_ORDER__CUSTOMER_ID = 3;

  /**
   * The number of structural features of the '<em>Customer Order</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_ORDER_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.order.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.order.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.order.impl.OrderPackageImpl#getDocumentRoot()
   * @generated
   */
  int DOCUMENT_ROOT = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Order</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__ORDER = 3;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.order.impl.OrderImpl <em>Order</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.order.impl.OrderImpl
   * @see org.eclipse.emf.test.models.order.impl.OrderPackageImpl#getOrder()
   * @generated
   */
  int ORDER = 2;

  /**
   * The feature id for the '<em><b>Date Requested</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER__DATE_REQUESTED = 0;

  /**
   * The feature id for the '<em><b>Last Updated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER__LAST_UPDATED = 1;

  /**
   * The feature id for the '<em><b>Movies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER__MOVIES = 2;

  /**
   * The number of structural features of the '<em>Order</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_FEATURE_COUNT = 3;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.order.CustomerOrder <em>Customer Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Customer Order</em>'.
   * @see org.eclipse.emf.test.models.order.CustomerOrder
   * @generated
   */
  EClass getCustomerOrder();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.order.CustomerOrder#getMoviesToSee <em>Movies To See</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Movies To See</em>'.
   * @see org.eclipse.emf.test.models.order.CustomerOrder#getMoviesToSee()
   * @see #getCustomerOrder()
   * @generated
   */
  EReference getCustomerOrder_MoviesToSee();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.order.CustomerOrder#getMoviesSeen <em>Movies Seen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Movies Seen</em>'.
   * @see org.eclipse.emf.test.models.order.CustomerOrder#getMoviesSeen()
   * @see #getCustomerOrder()
   * @generated
   */
  EReference getCustomerOrder_MoviesSeen();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.order.CustomerOrder#getAny <em>Any</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any</em>'.
   * @see org.eclipse.emf.test.models.order.CustomerOrder#getAny()
   * @see #getCustomerOrder()
   * @generated
   */
  EAttribute getCustomerOrder_Any();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.order.CustomerOrder#getCustomerID <em>Customer ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Customer ID</em>'.
   * @see org.eclipse.emf.test.models.order.CustomerOrder#getCustomerID()
   * @see #getCustomerOrder()
   * @generated
   */
  EAttribute getCustomerOrder_CustomerID();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.order.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.order.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.order.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.order.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.order.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.order.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.order.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.order.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.order.DocumentRoot#getOrder <em>Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Order</em>'.
   * @see org.eclipse.emf.test.models.order.DocumentRoot#getOrder()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Order();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.order.Order <em>Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Order</em>'.
   * @see org.eclipse.emf.test.models.order.Order
   * @generated
   */
  EClass getOrder();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.order.Order#getDateRequested <em>Date Requested</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Date Requested</em>'.
   * @see org.eclipse.emf.test.models.order.Order#getDateRequested()
   * @see #getOrder()
   * @generated
   */
  EAttribute getOrder_DateRequested();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.order.Order#getLastUpdated <em>Last Updated</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Updated</em>'.
   * @see org.eclipse.emf.test.models.order.Order#getLastUpdated()
   * @see #getOrder()
   * @generated
   */
  EAttribute getOrder_LastUpdated();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.order.Order#getMovies <em>Movies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Movies</em>'.
   * @see org.eclipse.emf.test.models.order.Order#getMovies()
   * @see #getOrder()
   * @generated
   */
  EAttribute getOrder_Movies();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  OrderFactory getOrderFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.order.impl.CustomerOrderImpl <em>Customer Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.order.impl.CustomerOrderImpl
     * @see org.eclipse.emf.test.models.order.impl.OrderPackageImpl#getCustomerOrder()
     * @generated
     */
    EClass CUSTOMER_ORDER = eINSTANCE.getCustomerOrder();

    /**
     * The meta object literal for the '<em><b>Movies To See</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CUSTOMER_ORDER__MOVIES_TO_SEE = eINSTANCE.getCustomerOrder_MoviesToSee();

    /**
     * The meta object literal for the '<em><b>Movies Seen</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CUSTOMER_ORDER__MOVIES_SEEN = eINSTANCE.getCustomerOrder_MoviesSeen();

    /**
     * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CUSTOMER_ORDER__ANY = eINSTANCE.getCustomerOrder_Any();

    /**
     * The meta object literal for the '<em><b>Customer ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CUSTOMER_ORDER__CUSTOMER_ID = eINSTANCE.getCustomerOrder_CustomerID();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.order.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.order.impl.DocumentRootImpl
     * @see org.eclipse.emf.test.models.order.impl.OrderPackageImpl#getDocumentRoot()
     * @generated
     */
    EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Order</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__ORDER = eINSTANCE.getDocumentRoot_Order();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.order.impl.OrderImpl <em>Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.order.impl.OrderImpl
     * @see org.eclipse.emf.test.models.order.impl.OrderPackageImpl#getOrder()
     * @generated
     */
    EClass ORDER = eINSTANCE.getOrder();

    /**
     * The meta object literal for the '<em><b>Date Requested</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ORDER__DATE_REQUESTED = eINSTANCE.getOrder_DateRequested();

    /**
     * The meta object literal for the '<em><b>Last Updated</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ORDER__LAST_UPDATED = eINSTANCE.getOrder_LastUpdated();

    /**
     * The meta object literal for the '<em><b>Movies</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ORDER__MOVIES = eINSTANCE.getOrder_Movies();

  }

} //OrderPackage
