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
 * $Id: PersonType.java,v 1.4 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.personal.mixed;

import commonj.sdo.Sequence;

import java.math.BigInteger;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getUrl <em>Url</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getContr <em>Contr</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getSalary <em>Salary</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='personType' kind='mixed'"
 * @generated
 */
public interface PersonType
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
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(NameType)
   * @model containment="true" required="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
   * @generated
   */
  NameType getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(NameType value);

  /**
   * Returns the value of the '<em><b>Email</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Email</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Email</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='email' namespace='##targetNamespace'"
   * @generated
   */
  List<String> getEmail();

  /**
   * Returns the value of the '<em><b>Url</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.personal.mixed.UrlType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Url</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Url</em>' containment reference list.
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='url' namespace='##targetNamespace'"
   * @generated
   */
  List<UrlType> getUrl();

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
   * @model containment="true" required="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace'"
   * @generated
   */
  LinkType getLink();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getLink <em>Link</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Link</em>' containment reference.
   * @see #getLink()
   * @generated
   */
  void setLink(LinkType value);

  /**
   * Returns the value of the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any</em>' attribute list.
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="false" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':5' processing='lax'"
   * @generated
   */
  Sequence getAny();

  /**
   * Returns the value of the '<em><b>Contr</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * The literals are from the enumeration {@link org.eclipse.emf.test.models.personal.mixed.ContrType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contr</em>' attribute.
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @see #isSetContr()
   * @see #unsetContr()
   * @see #setContr(ContrType)
   * @model default="false" unique="false" unsettable="true"
   *        extendedMetaData="kind='attribute' name='contr'"
   * @generated
   */
  ContrType getContr();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getContr <em>Contr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Contr</em>' attribute.
   * @see org.eclipse.emf.test.models.personal.mixed.ContrType
   * @see #isSetContr()
   * @see #unsetContr()
   * @see #getContr()
   * @generated
   */
  void setContr(ContrType value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getContr <em>Contr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetContr()
   * @see #getContr()
   * @see #setContr(ContrType)
   * @generated
   */
  void unsetContr();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getContr <em>Contr</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Contr</em>' attribute is set.
   * @see #unsetContr()
   * @see #getContr()
   * @see #setContr(ContrType)
   * @generated
   */
  boolean isSetContr();

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
   *        extendedMetaData="kind='attribute' name='id'"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Salary</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Salary</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Salary</em>' attribute.
   * @see #setSalary(BigInteger)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
   *        extendedMetaData="kind='attribute' name='salary'"
   * @generated
   */
  BigInteger getSalary();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.PersonType#getSalary <em>Salary</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Salary</em>' attribute.
   * @see #getSalary()
   * @generated
   */
  void setSalary(BigInteger value);

} // PersonType
