/**
 * <copyright>
 * </copyright>
 *
 * $Id: QnamePackage.java,v 1.1.2.1 2005/01/14 22:56:19 nickb Exp $
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
 * @see org.eclipse.emf.test.models.qname.QnameFactory
 * @generated
 */
public interface QnamePackage extends EPackage
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
  String eNS_URI = "http://org/eclipse/emf/test/models/qname";

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
  QnamePackage eINSTANCE = org.eclipse.emf.test.models.qname.impl.QnamePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.qname.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.qname.impl.QnamePackageImpl#getDocumentRoot()
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
   * The number of structural features of the the '<em>Document Root</em>' class.
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
   * @see org.eclipse.emf.test.models.qname.impl.QnamePackageImpl#getResourceType()
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
   * The number of structural features of the the '<em>Resource Type</em>' class.
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
   * @see org.eclipse.emf.test.models.qname.impl.QnamePackageImpl#getIntQNameUnion()
   * @generated
   */
  int INT_QNAME_UNION = 2;

  /**
   * The meta object id for the '<em>List Union</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.test.models.qname.impl.QnamePackageImpl#getListUnion()
   * @generated
   */
  int LIST_UNION = 3;

  /**
   * The meta object id for the '<em>List</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.test.models.qname.impl.QnamePackageImpl#getQnameList()
   * @generated
   */
  int QNAME_LIST = 4;

  /**
   * The meta object id for the '<em>Union</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.test.models.qname.impl.QnamePackageImpl#getUnion()
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
   * @generated
   */
  EDataType getListUnion();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>List</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
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
  QnameFactory getQnameFactory();

} //QnamePackage
