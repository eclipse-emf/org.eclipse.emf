/**
 * <copyright>
 * </copyright>
 *
 * $Id: QnamePackageImpl.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;

import org.eclipse.emf.test.models.qname.DocumentRoot;
import org.eclipse.emf.test.models.qname.QnameFactory;
import org.eclipse.emf.test.models.qname.QnamePackage;
import org.eclipse.emf.test.models.qname.ResourceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QnamePackageImpl extends EPackageImpl implements QnamePackage
{
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
  private EClass resourceTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType intQNameUnionEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType listUnionEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType qnameListEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unionEDataType = null;

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
   * @see org.eclipse.emf.test.models.qname.QnamePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private QnamePackageImpl()
  {
    super(eNS_URI, QnameFactory.eINSTANCE);
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
  public static QnamePackage init()
  {
    if (isInited) return (QnamePackage)EPackage.Registry.INSTANCE.get(QnamePackage.eNS_URI);

    // Obtain or create and register package
    QnamePackageImpl theQnamePackage = (QnamePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof QnamePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new QnamePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackageImpl.init();

    // Create package meta-data objects
    theQnamePackage.createPackageContents();

    // Initialize created meta-data
    theQnamePackage.initializePackageContents();

    return theQnamePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDocumentRoot()
  {
    return documentRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Mixed()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_AnyE()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_AnyEU()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Resource()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_AInt()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_AQname()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_AUnion()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getResourceType()
  {
    return resourceTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getResourceType_Unionvalue()
  {
    return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getResourceType_Qnamelist()
  {
    return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getResourceType_Intvalue()
  {
    return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getResourceType_Any()
  {
    return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getResourceType_MyQname()
  {
    return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getResourceType_AnyAttribute()
  {
    return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIntQNameUnion()
  {
    return intQNameUnionEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getListUnion()
  {
    return listUnionEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getQnameList()
  {
    return qnameListEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnion()
  {
    return unionEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QnameFactory getQnameFactory()
  {
    return (QnameFactory)getEFactoryInstance();
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
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__ANY_E);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__ANY_EU);
    createEReference(documentRootEClass, DOCUMENT_ROOT__RESOURCE);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__AINT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__AQNAME);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__AUNION);

    resourceTypeEClass = createEClass(RESOURCE_TYPE);
    createEAttribute(resourceTypeEClass, RESOURCE_TYPE__UNIONVALUE);
    createEAttribute(resourceTypeEClass, RESOURCE_TYPE__QNAMELIST);
    createEAttribute(resourceTypeEClass, RESOURCE_TYPE__INTVALUE);
    createEAttribute(resourceTypeEClass, RESOURCE_TYPE__ANY);
    createEAttribute(resourceTypeEClass, RESOURCE_TYPE__MY_QNAME);
    createEAttribute(resourceTypeEClass, RESOURCE_TYPE__ANY_ATTRIBUTE);

    // Create data types
    intQNameUnionEDataType = createEDataType(INT_QNAME_UNION);
    listUnionEDataType = createEDataType(LIST_UNION);
    qnameListEDataType = createEDataType(QNAME_LIST);
    unionEDataType = createEDataType(UNION);
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
    XMLTypePackageImpl theXMLTypePackage = (XMLTypePackageImpl)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_AnyE(), theXMLTypePackage.getQName(), "anyE", null, 0, -2, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_AnyEU(), this.getListUnion(), "anyEU", null, 0, -2, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Resource(), this.getResourceType(), null, "resource", null, 0, -2, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_AInt(), theXMLTypePackage.getInt(), "aInt", null, 0, -2, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_AQname(), theXMLTypePackage.getQName(), "aQname", null, 0, -2, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_AUnion(), this.getListUnion(), "aUnion", null, 0, -2, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(resourceTypeEClass, ResourceType.class, "ResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getResourceType_Unionvalue(), this.getIntQNameUnion(), "unionvalue", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResourceType_Qnamelist(), this.getQnameList(), "qnamelist", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResourceType_Intvalue(), theXMLTypePackage.getInt(), "intvalue", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResourceType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResourceType_MyQname(), this.getQnameList(), "myQname", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getResourceType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize data types
    initEDataType(intQNameUnionEDataType, Object.class, "IntQNameUnion", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(listUnionEDataType, List.class, "ListUnion", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(qnameListEDataType, List.class, "QnameList", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(unionEDataType, Object.class, "Union", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
      (getDocumentRoot_AnyE(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "anyE",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_AnyEU(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "anyEU",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Resource(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "resource",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_AInt(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "aInt",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_AQname(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "aQname",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_AUnion(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "aUnion",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (intQNameUnionEDataType, 
       source, 
       new String[] 
       {
       "name", "intQNameUnion",
       "memberTypes", "http://www.eclipse.org/emf/2003/XMLType#int http://www.eclipse.org/emf/2003/XMLType#QName"
       });		
    addAnnotation
      (listUnionEDataType, 
       source, 
       new String[] 
       {
       "name", "listUnion",
       "itemType", "Union"
       });		
    addAnnotation
      (qnameListEDataType, 
       source, 
       new String[] 
       {
       "name", "qnameList",
       "itemType", "http://www.eclipse.org/emf/2003/XMLType#QName"
       });		
    addAnnotation
      (resourceTypeEClass, 
       source, 
       new String[] 
       {
       "name", "resourceType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getResourceType_Unionvalue(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "unionvalue",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getResourceType_Qnamelist(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "qnamelist",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getResourceType_Intvalue(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "intvalue",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getResourceType_Any(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "wildcards", "##any",
       "name", ":3",
       "processing", "strict"
       });		
    addAnnotation
      (getResourceType_MyQname(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "myQname"
       });		
    addAnnotation
      (getResourceType_AnyAttribute(), 
       source, 
       new String[] 
       {
       "kind", "attributeWildcard",
       "wildcards", "##any",
       "name", ":5",
       "processing", "strict"
       });		
    addAnnotation
      (unionEDataType, 
       source, 
       new String[] 
       {
       "name", "Union",
       "memberTypes", "http://www.eclipse.org/emf/2003/XMLType#boolean intQNameUnion"
       });
  }

} //QnamePackageImpl
