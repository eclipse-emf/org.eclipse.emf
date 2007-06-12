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
 * $Id: DocumentRoot.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.personal;

import commonj.sdo.Sequence;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getFamily <em>Family</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getGiven <em>Given</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getPerson <em>Person</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getPersonnel <em>Personnel</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.DocumentRoot#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot
{
  /**
   * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mixed</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="kind='elementWildcard' name=':mixed'"
   * @generated
   */
  Sequence getMixed();

  /**
   * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
   * The key is of type {@link java.lang.String},
   * and the value is of type {@link java.lang.String},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>XMLNS Prefix Map</em>' map.
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
   *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
   * @generated
   */
  Map<String, String> getXMLNSPrefixMap();

  /**
   * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
   * The key is of type {@link java.lang.String},
   * and the value is of type {@link java.lang.String},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>XSI Schema Location</em>' map.
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
   *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
   * @generated
   */
  Map<String, String> getXSISchemaLocation();

  /**
   * Returns the value of the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Email</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Email</em>' attribute.
   * @see #setEmail(String)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='email' namespace='##targetNamespace'"
   * @generated
   */
  String getEmail();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getEmail <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Email</em>' attribute.
   * @see #getEmail()
   * @generated
   */
  void setEmail(String value);

  /**
   * Returns the value of the '<em><b>Family</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Family</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Family</em>' attribute.
   * @see #setFamily(String)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='family' namespace='##targetNamespace'"
   * @generated
   */
  String getFamily();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getFamily <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Family</em>' attribute.
   * @see #getFamily()
   * @generated
   */
  void setFamily(String value);

  /**
   * Returns the value of the '<em><b>Given</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Given</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Given</em>' attribute.
   * @see #setGiven(String)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='given' namespace='##targetNamespace'"
   * @generated
   */
  String getGiven();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getGiven <em>Given</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Given</em>' attribute.
   * @see #getGiven()
   * @generated
   */
  void setGiven(String value);

  /**
   * Returns the value of the '<em><b>Link</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Link</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Link</em>' containment reference.
   * @see #setLink(LinkType)
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace'"
   * @generated
   */
  LinkType getLink();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getLink <em>Link</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Link</em>' containment reference.
   * @see #getLink()
   * @generated
   */
  void setLink(LinkType value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(NameType)
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
   * @generated
   */
  NameType getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(NameType value);

  /**
   * Returns the value of the '<em><b>Person</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Person</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Person</em>' containment reference.
   * @see #setPerson(PersonType)
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='person' namespace='##targetNamespace'"
   * @generated
   */
  PersonType getPerson();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getPerson <em>Person</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Person</em>' containment reference.
   * @see #getPerson()
   * @generated
   */
  void setPerson(PersonType value);

  /**
   * Returns the value of the '<em><b>Personnel</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Personnel</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Personnel</em>' containment reference.
   * @see #setPersonnel(PersonnelType)
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='personnel' namespace='##targetNamespace'"
   * @generated
   */
  PersonnelType getPersonnel();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getPersonnel <em>Personnel</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Personnel</em>' containment reference.
   * @see #getPersonnel()
   * @generated
   */
  void setPersonnel(PersonnelType value);

  /**
   * Returns the value of the '<em><b>Url</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Url</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Url</em>' containment reference.
   * @see #setUrl(UrlType)
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='url' namespace='##targetNamespace'"
   * @generated
   */
  UrlType getUrl();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.DocumentRoot#getUrl <em>Url</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Url</em>' containment reference.
   * @see #getUrl()
   * @generated
   */
  void setUrl(UrlType value);

} // DocumentRoot
