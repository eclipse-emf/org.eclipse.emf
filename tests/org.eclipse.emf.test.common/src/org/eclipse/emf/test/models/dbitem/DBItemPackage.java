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
package org.eclipse.emf.test.models.dbitem;

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
 * @see org.eclipse.emf.test.models.dbitem.DBItemFactory
 * @model kind="package"
 * @generated
 */
public interface DBItemPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "dbitem";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/dbitem";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "dbitem";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DBItemPackage eINSTANCE = org.eclipse.emf.test.models.dbitem.impl.DBItemPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dbitem.impl.DbTypeImpl <em>Db Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dbitem.impl.DbTypeImpl
   * @see org.eclipse.emf.test.models.dbitem.impl.DBItemPackageImpl#getDbType()
   * @generated
   */
  int DB_TYPE = 0;

  /**
   * The feature id for the '<em><b>Red Pen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_TYPE__RED_PEN = 0;

  /**
   * The feature id for the '<em><b>Blue Pen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_TYPE__BLUE_PEN = 1;

  /**
   * The feature id for the '<em><b>Red Pencil</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_TYPE__RED_PENCIL = 2;

  /**
   * The feature id for the '<em><b>Blue Pencil</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_TYPE__BLUE_PENCIL = 3;

  /**
   * The number of structural features of the '<em>Db Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DB_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dbitem.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dbitem.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.dbitem.impl.DBItemPackageImpl#getDocumentRoot()
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
   * The feature id for the '<em><b>Dbitem</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__DBITEM = 3;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 4;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dbitem.DbType <em>Db Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Db Type</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DbType
   * @generated
   */
  EClass getDbType();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.dbitem.DbType#getRedPen <em>Red Pen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Red Pen</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DbType#getRedPen()
   * @see #getDbType()
   * @generated
   */
  EReference getDbType_RedPen();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.dbitem.DbType#getBluePen <em>Blue Pen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Blue Pen</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DbType#getBluePen()
   * @see #getDbType()
   * @generated
   */
  EReference getDbType_BluePen();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.dbitem.DbType#getRedPencil <em>Red Pencil</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Red Pencil</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DbType#getRedPencil()
   * @see #getDbType()
   * @generated
   */
  EReference getDbType_RedPencil();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.dbitem.DbType#getBluePencil <em>Blue Pencil</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Blue Pencil</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DbType#getBluePencil()
   * @see #getDbType()
   * @generated
   */
  EReference getDbType_BluePencil();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dbitem.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.dbitem.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.dbitem.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.dbitem.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.dbitem.DocumentRoot#getDbitem <em>Dbitem</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dbitem</em>'.
   * @see org.eclipse.emf.test.models.dbitem.DocumentRoot#getDbitem()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Dbitem();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DBItemFactory getDBItemFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dbitem.impl.DbTypeImpl <em>Db Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dbitem.impl.DbTypeImpl
     * @see org.eclipse.emf.test.models.dbitem.impl.DBItemPackageImpl#getDbType()
     * @generated
     */
    EClass DB_TYPE = eINSTANCE.getDbType();

    /**
     * The meta object literal for the '<em><b>Red Pen</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DB_TYPE__RED_PEN = eINSTANCE.getDbType_RedPen();

    /**
     * The meta object literal for the '<em><b>Blue Pen</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DB_TYPE__BLUE_PEN = eINSTANCE.getDbType_BluePen();

    /**
     * The meta object literal for the '<em><b>Red Pencil</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DB_TYPE__RED_PENCIL = eINSTANCE.getDbType_RedPencil();

    /**
     * The meta object literal for the '<em><b>Blue Pencil</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DB_TYPE__BLUE_PENCIL = eINSTANCE.getDbType_BluePencil();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.dbitem.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.dbitem.impl.DocumentRootImpl
     * @see org.eclipse.emf.test.models.dbitem.impl.DBItemPackageImpl#getDocumentRoot()
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
     * The meta object literal for the '<em><b>Dbitem</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__DBITEM = eINSTANCE.getDocumentRoot_Dbitem();

  }

} //DBItemPackage
