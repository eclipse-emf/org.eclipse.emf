/**
 * <copyright>
 * </copyright>
 *
 * $Id: PersonalPackage.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal;

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
 * @see org.eclipse.emf.test.models.personal.PersonalFactory
 * @generated
 */
public interface PersonalPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "personal";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://org/eclipse/emf/test/models/personal";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "personal";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PersonalPackage eINSTANCE = org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getDocumentRoot()
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
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.impl.LinkTypeImpl <em>Link Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.impl.LinkTypeImpl
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getLinkType()
   * @generated
   */
  int LINK_TYPE = 1;

  /**
   * The feature id for the '<em><b>Manager</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE__MANAGER = 0;

  /**
   * The feature id for the '<em><b>Subordinates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE__SUBORDINATES = 1;

  /**
   * The number of structural features of the the '<em>Link Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.impl.NameTypeImpl <em>Name Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.impl.NameTypeImpl
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getNameType()
   * @generated
   */
  int NAME_TYPE = 2;

  /**
   * The feature id for the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE__FAMILY = 0;

  /**
   * The feature id for the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE__GIVEN = 1;

  /**
   * The number of structural features of the the '<em>Name Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.impl.PersonnelTypeImpl <em>Personnel Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.impl.PersonnelTypeImpl
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getPersonnelType()
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
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl <em>Person Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.impl.PersonTypeImpl
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getPersonType()
   * @generated
   */
  int PERSON_TYPE = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__NAME = 0;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__EMAIL = 1;

  /**
   * The feature id for the '<em><b>Url</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__URL = 2;

  /**
   * The feature id for the '<em><b>Link</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__LINK = 3;

  /**
   * The feature id for the '<em><b>Contr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__CONTR = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__ID = 5;

  /**
   * The feature id for the '<em><b>Salary</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE__SALARY = 6;

  /**
   * The number of structural features of the the '<em>Person Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.impl.UrlTypeImpl <em>Url Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.impl.UrlTypeImpl
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getUrlType()
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
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.ContrType <em>Contr Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.ContrType
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getContrType()
   * @generated
   */
  int CONTR_TYPE = 6;

  /**
   * The meta object id for the '<em>Contr Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.ContrType
   * @see org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl#getContrTypeObject()
   * @generated
   */
  int CONTR_TYPE_OBJECT = 7;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getEmail()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Email();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getFamily <em>Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Family</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getFamily()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Family();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getGiven <em>Given</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getGiven()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Given();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Link</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getLink()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Link();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getName()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Person</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getPerson()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Person();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getPersonnel <em>Personnel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Personnel</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getPersonnel()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Personnel();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Url</em>'.
   * @see org.eclipse.emf.test.models.personal.DocumentRoot#getUrl()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Url();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.LinkType <em>Link Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link Type</em>'.
   * @see org.eclipse.emf.test.models.personal.LinkType
   * @generated
   */
  EClass getLinkType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.LinkType#getManager <em>Manager</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Manager</em>'.
   * @see org.eclipse.emf.test.models.personal.LinkType#getManager()
   * @see #getLinkType()
   * @generated
   */
  EAttribute getLinkType_Manager();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.LinkType#getSubordinates <em>Subordinates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Subordinates</em>'.
   * @see org.eclipse.emf.test.models.personal.LinkType#getSubordinates()
   * @see #getLinkType()
   * @generated
   */
  EAttribute getLinkType_Subordinates();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.NameType <em>Name Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name Type</em>'.
   * @see org.eclipse.emf.test.models.personal.NameType
   * @generated
   */
  EClass getNameType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.NameType#getFamily <em>Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Family</em>'.
   * @see org.eclipse.emf.test.models.personal.NameType#getFamily()
   * @see #getNameType()
   * @generated
   */
  EAttribute getNameType_Family();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.NameType#getGiven <em>Given</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given</em>'.
   * @see org.eclipse.emf.test.models.personal.NameType#getGiven()
   * @see #getNameType()
   * @generated
   */
  EAttribute getNameType_Given();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.PersonnelType <em>Personnel Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Personnel Type</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonnelType
   * @generated
   */
  EClass getPersonnelType();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.personal.PersonnelType#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Person</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonnelType#getPerson()
   * @see #getPersonnelType()
   * @generated
   */
  EReference getPersonnelType_Person();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.PersonType <em>Person Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Person Type</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType
   * @generated
   */
  EClass getPersonType();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.PersonType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getName()
   * @see #getPersonType()
   * @generated
   */
  EReference getPersonType_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.PersonType#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Email</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getEmail()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Email();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.personal.PersonType#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Url</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getUrl()
   * @see #getPersonType()
   * @generated
   */
  EReference getPersonType_Url();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.PersonType#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Link</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getLink()
   * @see #getPersonType()
   * @generated
   */
  EReference getPersonType_Link();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.PersonType#getContr <em>Contr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Contr</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getContr()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Contr();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.PersonType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getId()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.PersonType#getSalary <em>Salary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Salary</em>'.
   * @see org.eclipse.emf.test.models.personal.PersonType#getSalary()
   * @see #getPersonType()
   * @generated
   */
  EAttribute getPersonType_Salary();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.UrlType <em>Url Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Url Type</em>'.
   * @see org.eclipse.emf.test.models.personal.UrlType
   * @generated
   */
  EClass getUrlType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.UrlType#getHref <em>Href</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Href</em>'.
   * @see org.eclipse.emf.test.models.personal.UrlType#getHref()
   * @see #getUrlType()
   * @generated
   */
  EAttribute getUrlType_Href();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.test.models.personal.ContrType <em>Contr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Contr Type</em>'.
   * @see org.eclipse.emf.test.models.personal.ContrType
   * @generated
   */
  EEnum getContrType();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.test.models.personal.ContrType <em>Contr Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Contr Type Object</em>'.
   * @see org.eclipse.emf.test.models.personal.ContrType
   * @model instanceClass="org.eclipse.emf.test.models.personal.ContrType"
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
  PersonalFactory getPersonalFactory();

} //PersonalPackage
