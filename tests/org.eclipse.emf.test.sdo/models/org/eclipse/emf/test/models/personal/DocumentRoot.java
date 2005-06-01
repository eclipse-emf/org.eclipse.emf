/**
 * <copyright>
 * </copyright>
 *
 * $Id: DocumentRoot.java,v 1.1 2005/06/01 22:28:12 elena Exp $
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
 * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot
{
  /**
   * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mixed</em>' attribute list.
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Mixed()
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_XMLNSPrefixMap()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
   *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
   * @generated
   */
  Map getXMLNSPrefixMap();

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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_XSISchemaLocation()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
   *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
   * @generated
   */
  Map getXSISchemaLocation();

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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Email()
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Family()
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Given()
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Link()
   * @model containment="true" resolveProxies="false" upper="-2" transient="true" volatile="true" derived="true"
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Name()
   * @model containment="true" resolveProxies="false" upper="-2" transient="true" volatile="true" derived="true"
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Person()
   * @model containment="true" resolveProxies="false" upper="-2" transient="true" volatile="true" derived="true"
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Personnel()
   * @model containment="true" resolveProxies="false" upper="-2" transient="true" volatile="true" derived="true"
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
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#getDocumentRoot_Url()
   * @model containment="true" resolveProxies="false" upper="-2" transient="true" volatile="true" derived="true"
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
