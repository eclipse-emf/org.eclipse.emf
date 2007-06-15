/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: MixedPackageImpl.java,v 1.4 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.personal.mixed.ContrType;
import org.eclipse.emf.test.models.personal.mixed.DocumentRoot;
import org.eclipse.emf.test.models.personal.mixed.LinkType;
import org.eclipse.emf.test.models.personal.mixed.MixedFactory;
import org.eclipse.emf.test.models.personal.mixed.NameType;
import org.eclipse.emf.test.models.personal.mixed.PersonType;
import org.eclipse.emf.test.models.personal.mixed.PersonnelType;
import org.eclipse.emf.test.models.personal.mixed.UrlType;

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
 * @model kind="package"
 * @generated
 */
public class MixedPackageImpl extends EPackageImpl
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "mixed";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "http:///org.eclipse.emf.test.models/personalMixed";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "mixed";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final MixedPackageImpl eINSTANCE = org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getDocumentRoot()
   * @generated
   */
  public static final int DOCUMENT_ROOT = 0;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__EMAIL = 3;

  /**
   * The feature id for the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__FAMILY = 4;

  /**
   * The feature id for the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__GIVEN = 5;

  /**
   * The feature id for the '<em><b>Link</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__LINK = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__NAME = 7;

  /**
   * The feature id for the '<em><b>Person</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__PERSON = 8;

  /**
   * The feature id for the '<em><b>Personnel</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__PERSONNEL = 9;

  /**
   * The feature id for the '<em><b>Url</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__URL = 10;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl <em>Link Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getLinkType()
   * @generated
   */
  public static final int LINK_TYPE = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int LINK_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Manager</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int LINK_TYPE__MANAGER = 1;

  /**
   * The feature id for the '<em><b>Subordinates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int LINK_TYPE__SUBORDINATES = 2;

  /**
   * The number of structural features of the '<em>Link Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int LINK_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl <em>Name Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getNameType()
   * @generated
   */
  public static final int NAME_TYPE = 2;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAME_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAME_TYPE__FAMILY = 1;

  /**
   * The feature id for the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAME_TYPE__GIVEN = 2;

  /**
   * The number of structural features of the '<em>Name Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAME_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonnelTypeImpl <em>Personnel Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.PersonnelTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getPersonnelType()
   * @generated
   */
  public static final int PERSONNEL_TYPE = 3;

  /**
   * The feature id for the '<em><b>Person</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSONNEL_TYPE__PERSON = 0;

  /**
   * The number of structural features of the '<em>Personnel Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSONNEL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl <em>Person Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getPersonType()
   * @generated
   */
  public static final int PERSON_TYPE = 4;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__EMAIL = 2;

  /**
   * The feature id for the '<em><b>Url</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__URL = 3;

  /**
   * The feature id for the '<em><b>Link</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__LINK = 4;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__ANY = 5;

  /**
   * The feature id for the '<em><b>Contr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__CONTR = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__ID = 7;

  /**
   * The feature id for the '<em><b>Salary</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE__SALARY = 8;

  /**
   * The number of structural features of the '<em>Person Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PERSON_TYPE_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl <em>Url Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getUrlType()
   * @generated
   */
  public static final int URL_TYPE = 5;

  /**
   * The feature id for the '<em><b>Href</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int URL_TYPE__HREF = 0;

  /**
   * The number of structural features of the '<em>Url Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int URL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.personal.mixed.ContrType <em>Contr Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getContrType()
   * @generated
   */
  public static final int CONTR_TYPE = 6;

  /**
   * The meta object id for the '<em>Contr Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getContrTypeObject()
   * @generated
   */
  public static final int CONTR_TYPE_OBJECT = 7;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass documentRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass linkTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nameTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass personnelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass personTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass urlTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum contrTypeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType contrTypeObjectEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private MixedPackageImpl()
  {
    super(eNS_URI, ((EFactory)MixedFactory.INSTANCE));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static MixedPackageImpl init()
  {
    if (isInited) return (MixedPackageImpl)EPackage.Registry.INSTANCE.getEPackage(MixedPackageImpl.eNS_URI);

    // Obtain or create and register package
    MixedPackageImpl theMixedPackageImpl = (MixedPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof MixedPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new MixedPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theMixedPackageImpl.createPackageContents();

    // Initialize created meta-data
    theMixedPackageImpl.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theMixedPackageImpl.freeze();

    return theMixedPackageImpl;
  }


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot
   * @generated
   */
  public EClass getDocumentRoot()
  {
    return documentRootEClass;
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Mixed()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getEmail()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Email()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getFamily <em>Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Family</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getFamily()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Family()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getGiven <em>Given</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getGiven()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Given()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Link</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getLink()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_Link()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getName()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_Name()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Person</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPerson()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_Person()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPersonnel <em>Personnel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Personnel</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getPersonnel()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_Personnel()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Url</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.DocumentRoot#getUrl()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_Url()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.LinkType <em>Link Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType
   * @generated
   */
  public EClass getLinkType()
  {
    return linkTypeEClass;
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.LinkType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType#getMixed()
   * @see #getLinkType()
   * @generated
   */
  public EAttribute getLinkType_Mixed()
  {
    return (EAttribute)linkTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.LinkType#getManager <em>Manager</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Manager</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType#getManager()
   * @see #getLinkType()
   * @generated
   */
  public EAttribute getLinkType_Manager()
  {
    return (EAttribute)linkTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.LinkType#getSubordinates <em>Subordinates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Subordinates</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.LinkType#getSubordinates()
   * @see #getLinkType()
   * @generated
   */
  public EAttribute getLinkType_Subordinates()
  {
    return (EAttribute)linkTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.NameType <em>Name Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType
   * @generated
   */
  public EClass getNameType()
  {
    return nameTypeEClass;
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.NameType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType#getMixed()
   * @see #getNameType()
   * @generated
   */
  public EAttribute getNameType_Mixed()
  {
    return (EAttribute)nameTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.NameType#getFamily <em>Family</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Family</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType#getFamily()
   * @see #getNameType()
   * @generated
   */
  public EAttribute getNameType_Family()
  {
    return (EAttribute)nameTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.NameType#getGiven <em>Given</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.NameType#getGiven()
   * @see #getNameType()
   * @generated
   */
  public EAttribute getNameType_Given()
  {
    return (EAttribute)nameTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.PersonnelType <em>Personnel Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Personnel Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonnelType
   * @generated
   */
  public EClass getPersonnelType()
  {
    return personnelTypeEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.personal.mixed.PersonnelType#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Person</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonnelType#getPerson()
   * @see #getPersonnelType()
   * @generated
   */
  public EReference getPersonnelType_Person()
  {
    return (EReference)personnelTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.PersonType <em>Person Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Person Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType
   * @generated
   */
  public EClass getPersonType()
  {
    return personTypeEClass;
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getMixed()
   * @see #getPersonType()
   * @generated
   */
  public EAttribute getPersonType_Mixed()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getName()
   * @see #getPersonType()
   * @generated
   */
  public EReference getPersonType_Name()
  {
    return (EReference)personTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Email</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getEmail()
   * @see #getPersonType()
   * @generated
   */
  public EAttribute getPersonType_Email()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Url</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getUrl()
   * @see #getPersonType()
   * @generated
   */
  public EReference getPersonType_Url()
  {
    return (EReference)personTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Link</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getLink()
   * @see #getPersonType()
   * @generated
   */
  public EReference getPersonType_Link()
  {
    return (EReference)personTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getAny <em>Any</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getAny()
   * @see #getPersonType()
   * @generated
   */
  public EAttribute getPersonType_Any()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getContr <em>Contr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Contr</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getContr()
   * @see #getPersonType()
   * @generated
   */
  public EAttribute getPersonType_Contr()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(6);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getId()
   * @see #getPersonType()
   * @generated
   */
  public EAttribute getPersonType_Id()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(7);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getSalary <em>Salary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Salary</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.PersonType#getSalary()
   * @see #getPersonType()
   * @generated
   */
  public EAttribute getPersonType_Salary()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(8);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.personal.mixed.UrlType <em>Url Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Url Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.UrlType
   * @generated
   */
  public EClass getUrlType()
  {
    return urlTypeEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.personal.mixed.UrlType#getHref <em>Href</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Href</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.UrlType#getHref()
   * @see #getUrlType()
   * @generated
   */
  public EAttribute getUrlType_Href()
  {
    return (EAttribute)urlTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.test.models.personal.mixed.ContrType <em>Contr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Contr Type</em>'.
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @generated
   */
  public EEnum getContrType()
  {
    return contrTypeEEnum;
  }

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
  public EDataType getContrTypeObject()
  {
    return contrTypeObjectEDataType;
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  public MixedFactory getMixedFactory()
  {
    return (MixedFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__EMAIL);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__FAMILY);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__GIVEN);
    createEReference(documentRootEClass, DOCUMENT_ROOT__LINK);
    createEReference(documentRootEClass, DOCUMENT_ROOT__NAME);
    createEReference(documentRootEClass, DOCUMENT_ROOT__PERSON);
    createEReference(documentRootEClass, DOCUMENT_ROOT__PERSONNEL);
    createEReference(documentRootEClass, DOCUMENT_ROOT__URL);

    linkTypeEClass = createEClass(LINK_TYPE);
    createEAttribute(linkTypeEClass, LINK_TYPE__MIXED);
    createEAttribute(linkTypeEClass, LINK_TYPE__MANAGER);
    createEAttribute(linkTypeEClass, LINK_TYPE__SUBORDINATES);

    nameTypeEClass = createEClass(NAME_TYPE);
    createEAttribute(nameTypeEClass, NAME_TYPE__MIXED);
    createEAttribute(nameTypeEClass, NAME_TYPE__FAMILY);
    createEAttribute(nameTypeEClass, NAME_TYPE__GIVEN);

    personnelTypeEClass = createEClass(PERSONNEL_TYPE);
    createEReference(personnelTypeEClass, PERSONNEL_TYPE__PERSON);

    personTypeEClass = createEClass(PERSON_TYPE);
    createEAttribute(personTypeEClass, PERSON_TYPE__MIXED);
    createEReference(personTypeEClass, PERSON_TYPE__NAME);
    createEAttribute(personTypeEClass, PERSON_TYPE__EMAIL);
    createEReference(personTypeEClass, PERSON_TYPE__URL);
    createEReference(personTypeEClass, PERSON_TYPE__LINK);
    createEAttribute(personTypeEClass, PERSON_TYPE__ANY);
    createEAttribute(personTypeEClass, PERSON_TYPE__CONTR);
    createEAttribute(personTypeEClass, PERSON_TYPE__ID);
    createEAttribute(personTypeEClass, PERSON_TYPE__SALARY);

    urlTypeEClass = createEClass(URL_TYPE);
    createEAttribute(urlTypeEClass, URL_TYPE__HREF);

    // Create enums
    contrTypeEEnum = createEEnum(CONTR_TYPE);

    // Create data types
    contrTypeObjectEDataType = createEDataType(CONTR_TYPE_OBJECT);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Email(), theXMLTypePackage.getString(), "email", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Family(), theXMLTypePackage.getString(), "family", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Given(), theXMLTypePackage.getString(), "given", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Link(), this.getLinkType(), null, "link", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Name(), this.getNameType(), null, "name", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Person(), this.getPersonType(), null, "person", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Personnel(), this.getPersonnelType(), null, "personnel", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Url(), this.getUrlType(), null, "url", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(linkTypeEClass, LinkType.class, "LinkType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLinkType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, LinkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLinkType_Manager(), theXMLTypePackage.getIDREF(), "manager", null, 0, 1, LinkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLinkType_Subordinates(), theXMLTypePackage.getIDREFS(), "subordinates", null, 0, 1, LinkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nameTypeEClass, NameType.class, "NameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNameType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, NameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNameType_Family(), theXMLTypePackage.getString(), "family", null, 1, 1, NameType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getNameType_Given(), theXMLTypePackage.getString(), "given", null, 1, 1, NameType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(personnelTypeEClass, PersonnelType.class, "PersonnelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPersonnelType_Person(), this.getPersonType(), null, "person", null, 1, -1, PersonnelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(personTypeEClass, PersonType.class, "PersonType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPersonType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersonType_Name(), this.getNameType(), null, "name", null, 1, 1, PersonType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Email(), theXMLTypePackage.getString(), "email", null, 0, -1, PersonType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getPersonType_Url(), this.getUrlType(), null, "url", null, 0, -1, PersonType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getPersonType_Link(), this.getLinkType(), null, "link", null, 1, 1, PersonType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, 1, PersonType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Contr(), this.getContrType(), "contr", "false", 0, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Salary(), theXMLTypePackage.getInteger(), "salary", null, 0, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(urlTypeEClass, UrlType.class, "UrlType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUrlType_Href(), theXMLTypePackage.getString(), "href", "http://", 0, 1, UrlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(contrTypeEEnum, ContrType.class, "ContrType");
    addEEnumLiteral(contrTypeEEnum, ContrType.TRUE);
    addEEnumLiteral(contrTypeEEnum, ContrType.FALSE);

    // Initialize data types
    initEDataType(contrTypeObjectEDataType, ContrType.class, "ContrTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (contrTypeEEnum, 
       source, 
       new String[] 
       {
       "name", "contr_._type"
       });		
    addAnnotation
      (contrTypeObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "contr_._type:Object",
       "baseType", "contr_._type"
       });		
    addAnnotation
      (documentRootEClass, 
       source, 
       new String[] 
       {
       "name", "",
       "kind", "mixed"
       });		
    addAnnotation
      (getDocumentRoot_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getDocumentRoot_XMLNSPrefixMap(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xmlns:prefix"
       });		
    addAnnotation
      (getDocumentRoot_XSISchemaLocation(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xsi:schemaLocation"
       });		
    addAnnotation
      (getDocumentRoot_Email(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "email",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Family(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "family",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Given(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "given",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Link(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "link",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Name(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "name",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Person(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "person",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Personnel(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "personnel",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Url(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "url",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (linkTypeEClass, 
       source, 
       new String[] 
       {
       "name", "linkType",
       "kind", "mixed"
       });		
    addAnnotation
      (getLinkType_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getLinkType_Manager(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "manager"
       });		
    addAnnotation
      (getLinkType_Subordinates(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "subordinates"
       });		
    addAnnotation
      (nameTypeEClass, 
       source, 
       new String[] 
       {
       "name", "nameType",
       "kind", "mixed"
       });		
    addAnnotation
      (getNameType_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getNameType_Family(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "family",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getNameType_Given(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "given",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (personnelTypeEClass, 
       source, 
       new String[] 
       {
       "name", "personnel_._type",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getPersonnelType_Person(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "person",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (personTypeEClass, 
       source, 
       new String[] 
       {
       "name", "personType",
       "kind", "mixed"
       });		
    addAnnotation
      (getPersonType_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getPersonType_Name(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "name",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Email(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "email",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Url(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "url",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Link(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "link",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Any(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "wildcards", "##any",
       "name", ":5",
       "processing", "lax"
       });		
    addAnnotation
      (getPersonType_Contr(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "contr"
       });		
    addAnnotation
      (getPersonType_Id(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "id"
       });		
    addAnnotation
      (getPersonType_Salary(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "salary"
       });		
    addAnnotation
      (urlTypeEClass, 
       source, 
       new String[] 
       {
       "name", "url_._type",
       "kind", "empty"
       });		
    addAnnotation
      (getUrlType_Href(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "href"
       });
  }

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
  public interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getDocumentRoot()
     * @generated
     */
    public static final EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__EMAIL = eINSTANCE.getDocumentRoot_Email();

    /**
     * The meta object literal for the '<em><b>Family</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__FAMILY = eINSTANCE.getDocumentRoot_Family();

    /**
     * The meta object literal for the '<em><b>Given</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__GIVEN = eINSTANCE.getDocumentRoot_Given();

    /**
     * The meta object literal for the '<em><b>Link</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__LINK = eINSTANCE.getDocumentRoot_Link();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__NAME = eINSTANCE.getDocumentRoot_Name();

    /**
     * The meta object literal for the '<em><b>Person</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__PERSON = eINSTANCE.getDocumentRoot_Person();

    /**
     * The meta object literal for the '<em><b>Personnel</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__PERSONNEL = eINSTANCE.getDocumentRoot_Personnel();

    /**
     * The meta object literal for the '<em><b>Url</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__URL = eINSTANCE.getDocumentRoot_Url();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl <em>Link Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getLinkType()
     * @generated
     */
    public static final EClass LINK_TYPE = eINSTANCE.getLinkType();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute LINK_TYPE__MIXED = eINSTANCE.getLinkType_Mixed();

    /**
     * The meta object literal for the '<em><b>Manager</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute LINK_TYPE__MANAGER = eINSTANCE.getLinkType_Manager();

    /**
     * The meta object literal for the '<em><b>Subordinates</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute LINK_TYPE__SUBORDINATES = eINSTANCE.getLinkType_Subordinates();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl <em>Name Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getNameType()
     * @generated
     */
    public static final EClass NAME_TYPE = eINSTANCE.getNameType();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute NAME_TYPE__MIXED = eINSTANCE.getNameType_Mixed();

    /**
     * The meta object literal for the '<em><b>Family</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute NAME_TYPE__FAMILY = eINSTANCE.getNameType_Family();

    /**
     * The meta object literal for the '<em><b>Given</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute NAME_TYPE__GIVEN = eINSTANCE.getNameType_Given();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonnelTypeImpl <em>Personnel Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.impl.PersonnelTypeImpl
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getPersonnelType()
     * @generated
     */
    public static final EClass PERSONNEL_TYPE = eINSTANCE.getPersonnelType();

    /**
     * The meta object literal for the '<em><b>Person</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PERSONNEL_TYPE__PERSON = eINSTANCE.getPersonnelType_Person();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl <em>Person Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getPersonType()
     * @generated
     */
    public static final EClass PERSON_TYPE = eINSTANCE.getPersonType();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PERSON_TYPE__MIXED = eINSTANCE.getPersonType_Mixed();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PERSON_TYPE__NAME = eINSTANCE.getPersonType_Name();

    /**
     * The meta object literal for the '<em><b>Email</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PERSON_TYPE__EMAIL = eINSTANCE.getPersonType_Email();

    /**
     * The meta object literal for the '<em><b>Url</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PERSON_TYPE__URL = eINSTANCE.getPersonType_Url();

    /**
     * The meta object literal for the '<em><b>Link</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PERSON_TYPE__LINK = eINSTANCE.getPersonType_Link();

    /**
     * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PERSON_TYPE__ANY = eINSTANCE.getPersonType_Any();

    /**
     * The meta object literal for the '<em><b>Contr</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PERSON_TYPE__CONTR = eINSTANCE.getPersonType_Contr();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PERSON_TYPE__ID = eINSTANCE.getPersonType_Id();

    /**
     * The meta object literal for the '<em><b>Salary</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PERSON_TYPE__SALARY = eINSTANCE.getPersonType_Salary();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl <em>Url Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getUrlType()
     * @generated
     */
    public static final EClass URL_TYPE = eINSTANCE.getUrlType();

    /**
     * The meta object literal for the '<em><b>Href</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute URL_TYPE__HREF = eINSTANCE.getUrlType_Href();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.personal.mixed.ContrType <em>Contr Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.ContrType
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getContrType()
     * @generated
     */
    public static final EEnum CONTR_TYPE = eINSTANCE.getContrType();

    /**
     * The meta object literal for the '<em>Contr Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.personal.mixed.ContrType
     * @see org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl#getContrTypeObject()
     * @generated
     */
    public static final EDataType CONTR_TYPE_OBJECT = eINSTANCE.getContrTypeObject();

  }

} //MixedPackageImpl
