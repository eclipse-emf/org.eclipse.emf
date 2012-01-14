/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2xml;

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
 * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLFactory
 * @model kind="package"
 * @generated
 */
public interface Ecore2XMLPackage extends EPackage{

  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "ecore2xml"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2005/Ecore2XML"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "ecore2xml"; //$NON-NLS-1$

  /**
   * The package content type ID.
   * <!-- begin-user-doc -->
   * @since 2.4
   * <!-- end-user-doc -->
   * @generated
   */
  String eCONTENT_TYPE = "org.eclipse.emf.mapping.ecore2xml"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Ecore2XMLPackage eINSTANCE = org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl <em>XML Info</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl
   * @see org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl#getXMLInfo()
   * @generated
   */
  int XML_INFO = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_INFO__NAME = 0;

  /**
   * The feature id for the '<em><b>Target Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_INFO__TARGET_NAMESPACE = 1;

  /**
   * The feature id for the '<em><b>XML Representation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_INFO__XML_REPRESENTATION = 2;

  /**
   * The number of structural features of the '<em>XML Info</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_INFO_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl <em>XML Map</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl
   * @see org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl#getXMLMap()
   * @generated
   */
  int XML_MAP = 1;

  /**
   * The feature id for the '<em><b>ID Attribute Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_MAP__ID_ATTRIBUTE_NAME = 0;

  /**
   * The feature id for the '<em><b>Ecore To XML Info</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_MAP__ECORE_TO_XML_INFO = 1;

  /**
   * The feature id for the '<em><b>No Namespace Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_MAP__NO_NAMESPACE_PACKAGE = 2;

  /**
   * The number of structural features of the '<em>XML Map</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_MAP_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.ecore2xml.impl.ENamedElementToXMLInfoMapEntryImpl <em>ENamed Element To XML Info Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.ecore2xml.impl.ENamedElementToXMLInfoMapEntryImpl
   * @see org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl#getENamedElementToXMLInfoMapEntry()
   * @generated
   */
  int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY = 2;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>ENamed Element To XML Info Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY_FEATURE_COUNT = 2;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo <em>XML Info</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XML Info</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLInfo
   * @generated
   */
  EClass getXMLInfo();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLInfo#getName()
   * @see #getXMLInfo()
   * @generated
   */
  EAttribute getXMLInfo_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getTargetNamespace <em>Target Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Namespace</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLInfo#getTargetNamespace()
   * @see #getXMLInfo()
   * @generated
   */
  EAttribute getXMLInfo_TargetNamespace();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getXMLRepresentation <em>XML Representation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>XML Representation</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLInfo#getXMLRepresentation()
   * @see #getXMLInfo()
   * @generated
   */
  EAttribute getXMLInfo_XMLRepresentation();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap <em>XML Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XML Map</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLMap
   * @generated
   */
  EClass getXMLMap();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getIDAttributeName <em>ID Attribute Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ID Attribute Name</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLMap#getIDAttributeName()
   * @see #getXMLMap()
   * @generated
   */
  EAttribute getXMLMap_IDAttributeName();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getEcoreToXMLInfo <em>Ecore To XML Info</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Ecore To XML Info</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLMap#getEcoreToXMLInfo()
   * @see #getXMLMap()
   * @generated
   */
  EReference getXMLMap_EcoreToXMLInfo();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getNoNamespacePackage <em>No Namespace Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>No Namespace Package</em>'.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLMap#getNoNamespacePackage()
   * @see #getXMLMap()
   * @generated
   */
  EReference getXMLMap_NoNamespacePackage();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>ENamed Element To XML Info Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ENamed Element To XML Info Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.ENamedElement" keyRequired="true"
   *        valueType="org.eclipse.emf.mapping.ecore2xml.XMLInfo" valueContainment="true" valueRequired="true"
   * @generated
   */
  EClass getENamedElementToXMLInfoMapEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getENamedElementToXMLInfoMapEntry()
   * @generated
   */
  EReference getENamedElementToXMLInfoMapEntry_Key();

  /**
   * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getENamedElementToXMLInfoMapEntry()
   * @generated
   */
  EReference getENamedElementToXMLInfoMapEntry_Value();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Ecore2XMLFactory getEcore2XMLFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl <em>XML Info</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl
     * @see org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl#getXMLInfo()
     * @generated
     */
    EClass XML_INFO = eINSTANCE.getXMLInfo();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XML_INFO__NAME = eINSTANCE.getXMLInfo_Name();

    /**
     * The meta object literal for the '<em><b>Target Namespace</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XML_INFO__TARGET_NAMESPACE = eINSTANCE.getXMLInfo_TargetNamespace();

    /**
     * The meta object literal for the '<em><b>XML Representation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XML_INFO__XML_REPRESENTATION = eINSTANCE.getXMLInfo_XMLRepresentation();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl <em>XML Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl
     * @see org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl#getXMLMap()
     * @generated
     */
    EClass XML_MAP = eINSTANCE.getXMLMap();

    /**
     * The meta object literal for the '<em><b>ID Attribute Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XML_MAP__ID_ATTRIBUTE_NAME = eINSTANCE.getXMLMap_IDAttributeName();

    /**
     * The meta object literal for the '<em><b>Ecore To XML Info</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XML_MAP__ECORE_TO_XML_INFO = eINSTANCE.getXMLMap_EcoreToXMLInfo();

    /**
     * The meta object literal for the '<em><b>No Namespace Package</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XML_MAP__NO_NAMESPACE_PACKAGE = eINSTANCE.getXMLMap_NoNamespacePackage();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.ecore2xml.impl.ENamedElementToXMLInfoMapEntryImpl <em>ENamed Element To XML Info Map Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.ecore2xml.impl.ENamedElementToXMLInfoMapEntryImpl
     * @see org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLPackageImpl#getENamedElementToXMLInfoMapEntry()
     * @generated
     */
    EClass ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY = eINSTANCE.getENamedElementToXMLInfoMapEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY = eINSTANCE.getENamedElementToXMLInfoMapEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE = eINSTANCE.getENamedElementToXMLInfoMapEntry_Value();

  }

} //Ecore2XMLPackage
