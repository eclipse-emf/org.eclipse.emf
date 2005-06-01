/**
 * <copyright>
 * </copyright>
 *
 * $Id: MixedPackage.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.emf.test.models.personal.mixed.MixedFactory
 * @generated
 */
public interface MixedPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "mixed";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://org/eclipse/emf/test/models/personalMixed";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "mixed";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MixedPackage eINSTANCE = org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getDocumentRoot()
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
   * The feature id for the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__EMAIL = 3;

  /**
   * The feature id for the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__FAMILY = 4;

  /**
   * The feature id for the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__GIVEN = 5;

  /**
   * The feature id for the '<em><b>Link</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__LINK = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__NAME = 7;

  /**
   * The feature id for the '<em><b>Person</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__PERSON = 8;

  /**
   * The feature id for the '<em><b>Personnel</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__PERSONNEL = 9;

  /**
   * The feature id for the '<em><b>Url</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__URL = 10;

  /**
   * The number of structural features of the the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl <em>Link Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getLinkType()
   * @generated
   */
  int LINK_TYPE = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Manager</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE__MANAGER = 1;

  /**
   * The feature id for the '<em><b>Subordinates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE__SUBORDINATES = 2;

  /**
   * The number of structural features of the the '<em>Link Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl <em>Name Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getNameType()
   * @generated
   */
  int NAME_TYPE = 2;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE__FAMILY = 1;

  /**
   * The feature id for the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE__GIVEN = 2;

  /**
   * The number of structural features of the the '<em>Name Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonnelTypeImpl <em>Personnel Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.PersonnelTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getPersonnelType()
   * @generated
   */
  int PERSONNEL_TYPE = 3;

  /**
   * The feature id for the '<em><b>Person</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSONNEL_TYPE__PERSON = 0;

  /**
   * The number of structural features of the the '<em>Personnel Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSONNEL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl <em>Person Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getPersonType()
   * @generated
   */
  int PERSON_TYPE = 4;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__EMAIL = 2;

  /**
   * The feature id for the '<em><b>Url</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__URL = 3;

  /**
   * The feature id for the '<em><b>Link</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__LINK = 4;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__ANY = 5;

  /**
   * The feature id for the '<em><b>Contr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__CONTR = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__ID = 7;

  /**
   * The feature id for the '<em><b>Salary</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__SALARY = 8;

  /**
   * The number of structural features of the the '<em>Person Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl <em>Url Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getUrlType()
   * @generated
   */
  int URL_TYPE = 5;

  /**
   * The feature id for the '<em><b>Href</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int URL_TYPE__HREF = 0;

  /**
   * The number of structural features of the the '<em>Url Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int URL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.ContrType <em>Contr Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getContrType()
   * @generated
   */
  int CONTR_TYPE = 6;

  /**
   * The meta object id for the '<em>Contr Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getContrTypeObject()
   * @generated
   */
  int CONTR_TYPE_OBJECT = 7;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getEmail()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Email();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getFamily <em>Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Family</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getFamily()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Family();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getGiven <em>Given</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getGiven()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Given();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Link</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getLink()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Link();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getName()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Person</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPerson()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Person();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPersonnel <em>Personnel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Personnel</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPersonnel()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Personnel();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Url</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getUrl()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Url();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.LinkType <em>Link Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType
   * @generated
   */
  EClass getLinkType();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.LinkType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType#getMixed()
   * @see #getLinkType()
   * @generated
   */
  EAttribute getLinkType_Mixed();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.LinkType#getManager <em>Manager</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Manager</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType#getManager()
   * @see #getLinkType()
   * @generated
   */
  EAttribute getLinkType_Manager();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.LinkType#getSubordinates <em>Subordinates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Subordinates</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType#getSubordinates()
   * @see #getLinkType()
   * @generated
   */
  EAttribute getLinkType_Subordinates();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.NameType <em>Name Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType
   * @generated
   */
  EClass getNameType();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.NameType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType#getMixed()
   * @see #getNameType()
   * @generated
   */
  EAttribute getNameType_Mixed();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.NameType#getFamily <em>Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Family</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType#getFamily()
   * @see #getNameType()
   * @generated
   */
  EAttribute getNameType_Family();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.NameType#getGiven <em>Given</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType#getGiven()
   * @see #getNameType()
   * @generated
   */
  EAttribute getNameType_Given();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.PersonnelType <em>Personnel Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Personnel Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonnelType
   * @generated
   */
  EClass getPersonnelType();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.personal.mixed.PersonnelType#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Person</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonnelType#getPerson()
   * @see #getPersonnelType()
   * @generated
   */
  EReference getPersonnelType_Person();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.PersonType <em>Person Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Person Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType
   * @generated
   */
  EClass getPersonType();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getMixed()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Mixed();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getName()
   * @see #getPersonType()
   * @generated
   */
  EReference getPersonType_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Email</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getEmail()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Email();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Url</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getUrl()
   * @see #getPersonType()
   * @generated
   */
  EReference getPersonType_Url();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Link</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getLink()
   * @see #getPersonType()
   * @generated
   */
  EReference getPersonType_Link();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getAny <em>Any</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getAny()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Any();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getContr <em>Contr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Contr</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getContr()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Contr();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getId()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getSalary <em>Salary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Salary</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getSalary()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Salary();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.UrlType <em>Url Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Url Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.UrlType
   * @generated
   */
  EClass getUrlType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.UrlType#getHref <em>Href</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Href</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.UrlType#getHref()
   * @see #getUrlType()
   * @generated
   */
  EAttribute getUrlType_Href();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.test.models.personal.mixed.ContrType <em>Contr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Contr Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @generated
   */
  EEnum getContrType();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.test.models.personal.mixed.ContrType <em>Contr Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Contr Type Object</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @model instanceClass="org.eclipse.emf.test.models.personal.mixed.ContrType"
   *        extendedMetaData="name='contr_._type:Object' baseType='contr_._type'" 
   * @generated
   */
  EDataType getContrTypeObject();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MixedFactory getMixedFactory();

} //MixedPackage
