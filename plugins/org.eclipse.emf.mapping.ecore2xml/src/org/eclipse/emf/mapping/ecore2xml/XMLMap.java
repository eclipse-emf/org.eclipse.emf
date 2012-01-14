/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2xml;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Map</b></em>'.
 * @extends XMLResource.XMLMap
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getIDAttributeName <em>ID Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getEcoreToXMLInfo <em>Ecore To XML Info</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getNoNamespacePackage <em>No Namespace Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLMap()
 * @model
 * @generated
 */
public interface XMLMap extends EObject, XMLResource.XMLMap{
  
  /**
   * Returns the value of the '<em><b>ID Attribute Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>ID Attribute Name</em>' attribute.
   * @see #setIDAttributeName(String)
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLMap_IDAttributeName()
   * @model volatile="true"
   * @generated
   */
  String getIDAttributeName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getIDAttributeName <em>ID Attribute Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ID Attribute Name</em>' attribute.
   * @see #getIDAttributeName()
   * @generated
   */
  void setIDAttributeName(String value);

  /**
   * Returns the value of the '<em><b>Ecore To XML Info</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.ENamedElement},
   * and the value is of type {@link org.eclipse.emf.mapping.ecore2xml.XMLInfo},
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore To XML Info</em>' map.
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLMap_EcoreToXMLInfo()
   * @model mapType="org.eclipse.emf.mapping.ecore2xml.ENamedElementToXMLInfoMapEntry<org.eclipse.emf.ecore.ENamedElement, org.eclipse.emf.mapping.ecore2xml.XMLInfo>"
   * @generated
   */
  EMap<ENamedElement, XMLInfo> getEcoreToXMLInfo();

  /**
   * Returns the value of the '<em><b>No Namespace Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>No Namespace Package</em>' reference.
   * @see #setNoNamespacePackage(EPackage)
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLMap_NoNamespacePackage()
   * @model volatile="true"
   * @generated
   */
  EPackage getNoNamespacePackage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap#getNoNamespacePackage <em>No Namespace Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>No Namespace Package</em>' reference.
   * @see #getNoNamespacePackage()
   * @generated
   */
  void setNoNamespacePackage(EPackage value);

} // XMLMap
