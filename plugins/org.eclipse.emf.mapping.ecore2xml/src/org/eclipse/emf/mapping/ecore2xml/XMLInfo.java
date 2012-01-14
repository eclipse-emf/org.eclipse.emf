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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Info</b></em>'.
 * @extends XMLResource.XMLInfo
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getXMLRepresentation <em>XML Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLInfo()
 * @model
 * @generated
 */
public interface XMLInfo extends EObject, XMLResource.XMLInfo
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLInfo_Name()
   * @model volatile="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Namespace</em>' attribute.
   * @see #setTargetNamespace(String)
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLInfo_TargetNamespace()
   * @model volatile="true"
   * @generated
   */
  String getTargetNamespace();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getTargetNamespace <em>Target Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Namespace</em>' attribute.
   * @see #getTargetNamespace()
   * @generated
   */
  void setTargetNamespace(String value);

  /**
   * Returns the value of the '<em><b>XML Representation</b></em>' attribute.
   * The default value is <code>"-1"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>XML Representation</em>' attribute.
   * @see #setXMLRepresentation(int)
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#getXMLInfo_XMLRepresentation()
   * @model default="-1" volatile="true"
   * @generated
   */
  int getXMLRepresentation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo#getXMLRepresentation <em>XML Representation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>XML Representation</em>' attribute.
   * @see #getXMLRepresentation()
   * @generated
   */
  void setXMLRepresentation(int value);

} // XMLInfo
