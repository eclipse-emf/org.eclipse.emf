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
package org.eclipse.emf.test.models.qname;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.eclipse.emf.test.models.qname.QNameFactory
 * @model kind="package"
 * @generated
 */
public interface QNamePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "qname";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/qname";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "qname";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  QNamePackage eINSTANCE = org.eclipse.emf.test.models.qname.impl.QNamePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.qname.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getDocumentRoot()
   * @generated
   */
  int DOCUMENT_ROOT = 0;

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
   * The feature id for the '<em><b>Any E</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__ANY_E = 3;

  /**
   * The feature id for the '<em><b>Any EU</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__ANY_EU = 4;

  /**
   * The feature id for the '<em><b>Resource</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__RESOURCE = 5;

  /**
   * The feature id for the '<em><b>AInt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__AINT = 6;

  /**
   * The feature id for the '<em><b>AQname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__AQNAME = 7;

  /**
   * The feature id for the '<em><b>AUnion</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__AUNION = 8;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl <em>Resource Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl
   * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getResourceType()
   * @generated
   */
  int RESOURCE_TYPE = 1;

  /**
   * The feature id for the '<em><b>Unionvalue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE__UNIONVALUE = 0;

  /**
   * The feature id for the '<em><b>Qnamelist</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE__QNAMELIST = 1;

  /**
   * The feature id for the '<em><b>Intvalue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE__INTVALUE = 2;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE__ANY = 3;

  /**
   * The feature id for the '<em><b>My Qname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE__MY_QNAME = 4;

  /**
   * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE__ANY_ATTRIBUTE = 5;

  /**
   * The number of structural features of the '<em>Resource Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOURCE_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '<em>Int QName Union</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getIntQNameUnion()
   * @generated
   */
  int INT_QNAME_UNION = 2;

  /**
   * The meta object id for the '<em>List Union</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getListUnion()
   * @generated
   */
  int LIST_UNION = 3;

  /**
   * The meta object id for the '<em>Qname List</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getQnameList()
   * @generated
   */
  int QNAME_LIST = 4;

  /**
   * The meta object id for the '<em>Union</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getUnion()
   * @generated
   */
  int UNION = 5;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.qname.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAnyE <em>Any E</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Any E</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getAnyE()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_AnyE();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAnyEU <em>Any EU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Any EU</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getAnyEU()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_AnyEU();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getResource <em>Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Resource</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getResource()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Resource();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAInt <em>AInt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AInt</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getAInt()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_AInt();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAQname <em>AQname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AQname</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getAQname()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_AQname();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAUnion <em>AUnion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AUnion</em>'.
   * @see org.eclipse.emf.test.models.qname.DocumentRoot#getAUnion()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_AUnion();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.qname.ResourceType <em>Resource Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Resource Type</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType
   * @generated
   */
  EClass getResourceType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.ResourceType#getUnionvalue <em>Unionvalue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unionvalue</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType#getUnionvalue()
   * @see #getResourceType()
   * @generated
   */
  EAttribute getResourceType_Unionvalue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.ResourceType#getQnamelist <em>Qnamelist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Qnamelist</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType#getQnamelist()
   * @see #getResourceType()
   * @generated
   */
  EAttribute getResourceType_Qnamelist();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.ResourceType#getIntvalue <em>Intvalue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Intvalue</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType#getIntvalue()
   * @see #getResourceType()
   * @generated
   */
  EAttribute getResourceType_Intvalue();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.qname.ResourceType#getAny <em>Any</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType#getAny()
   * @see #getResourceType()
   * @generated
   */
  EAttribute getResourceType_Any();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.qname.ResourceType#getMyQname <em>My Qname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>My Qname</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType#getMyQname()
   * @see #getResourceType()
   * @generated
   */
  EAttribute getResourceType_MyQname();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.qname.ResourceType#getAnyAttribute <em>Any Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any Attribute</em>'.
   * @see org.eclipse.emf.test.models.qname.ResourceType#getAnyAttribute()
   * @see #getResourceType()
   * @generated
   */
  EAttribute getResourceType_AnyAttribute();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Int QName Union</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Int QName Union</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='intQNameUnion' memberTypes='http://www.eclipse.org/emf/2003/XMLType#int http://www.eclipse.org/emf/2003/XMLType#QName'"
   * @generated
   */
  EDataType getIntQNameUnion();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>List Union</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>List Union</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='listUnion' itemType='Union'"
   * @generated
   */
  EDataType getListUnion();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>Qname List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Qname List</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='qnameList' itemType='http://www.eclipse.org/emf/2003/XMLType#QName'"
   * @generated
   */
  EDataType getQnameList();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Union</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Union</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='Union' memberTypes='http://www.eclipse.org/emf/2003/XMLType#boolean intQNameUnion'"
   * @generated
   */
  EDataType getUnion();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  QNameFactory getQNameFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.qname.impl.DocumentRootImpl
     * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getDocumentRoot()
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
     * The meta object literal for the '<em><b>Any E</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__ANY_E = eINSTANCE.getDocumentRoot_AnyE();

    /**
     * The meta object literal for the '<em><b>Any EU</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__ANY_EU = eINSTANCE.getDocumentRoot_AnyEU();

    /**
     * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__RESOURCE = eINSTANCE.getDocumentRoot_Resource();

    /**
     * The meta object literal for the '<em><b>AInt</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__AINT = eINSTANCE.getDocumentRoot_AInt();

    /**
     * The meta object literal for the '<em><b>AQname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__AQNAME = eINSTANCE.getDocumentRoot_AQname();

    /**
     * The meta object literal for the '<em><b>AUnion</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__AUNION = eINSTANCE.getDocumentRoot_AUnion();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl <em>Resource Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl
     * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getResourceType()
     * @generated
     */
    EClass RESOURCE_TYPE = eINSTANCE.getResourceType();

    /**
     * The meta object literal for the '<em><b>Unionvalue</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_TYPE__UNIONVALUE = eINSTANCE.getResourceType_Unionvalue();

    /**
     * The meta object literal for the '<em><b>Qnamelist</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_TYPE__QNAMELIST = eINSTANCE.getResourceType_Qnamelist();

    /**
     * The meta object literal for the '<em><b>Intvalue</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_TYPE__INTVALUE = eINSTANCE.getResourceType_Intvalue();

    /**
     * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_TYPE__ANY = eINSTANCE.getResourceType_Any();

    /**
     * The meta object literal for the '<em><b>My Qname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_TYPE__MY_QNAME = eINSTANCE.getResourceType_MyQname();

    /**
     * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOURCE_TYPE__ANY_ATTRIBUTE = eINSTANCE.getResourceType_AnyAttribute();

    /**
     * The meta object literal for the '<em>Int QName Union</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Object
     * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getIntQNameUnion()
     * @generated
     */
    EDataType INT_QNAME_UNION = eINSTANCE.getIntQNameUnion();

    /**
     * The meta object literal for the '<em>List Union</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getListUnion()
     * @generated
     */
    EDataType LIST_UNION = eINSTANCE.getListUnion();

    /**
     * The meta object literal for the '<em>Qname List</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getQnameList()
     * @generated
     */
    EDataType QNAME_LIST = eINSTANCE.getQnameList();

    /**
     * The meta object literal for the '<em>Union</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Object
     * @see org.eclipse.emf.test.models.qname.impl.QNamePackageImpl#getUnion()
     * @generated
     */
    EDataType UNION = eINSTANCE.getUnion();

  }

} //QNamePackage
