/**
 * <copyright>
 * </copyright>
 *
 * $Id: DocumentRoot.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.qname;

import java.util.List;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAnyE <em>Any E</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAnyEU <em>Any EU</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAInt <em>AInt</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAQname <em>AQname</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAUnion <em>AUnion</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot()
 * @model 
 * @generated
 */
public interface DocumentRoot extends EObject
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
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_Mixed()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   * @generated
   */
  FeatureMap getMixed();

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
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_XMLNSPrefixMap()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
   * @generated
   */
  EMap getXMLNSPrefixMap();

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
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_XSISchemaLocation()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
   * @generated
   */
  EMap getXSISchemaLocation();

  /**
   * Returns the value of the '<em><b>Any E</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any E</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any E</em>' attribute.
   * @see #setAnyE(Object)
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_AnyE()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName" transient="true" volatile="true" derived="true"
   * @generated
   */
  Object getAnyE();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAnyE <em>Any E</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Any E</em>' attribute.
   * @see #getAnyE()
   * @generated
   */
  void setAnyE(Object value);

  /**
   * Returns the value of the '<em><b>Any EU</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any EU</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any EU</em>' attribute.
   * @see #setAnyEU(List)
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_AnyEU()
   * @model unique="false" dataType="org.eclipse.emf.test.models.qname.ListUnion" transient="true" volatile="true" derived="true"
   * @generated
   */
  List getAnyEU();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAnyEU <em>Any EU</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Any EU</em>' attribute.
   * @see #getAnyEU()
   * @generated
   */
  void setAnyEU(List value);

  /**
   * Returns the value of the '<em><b>Resource</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource</em>' containment reference.
   * @see #setResource(ResourceType)
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_Resource()
   * @model containment="true" resolveProxies="false" transient="true" volatile="true" derived="true"
   * @generated
   */
  ResourceType getResource();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getResource <em>Resource</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource</em>' containment reference.
   * @see #getResource()
   * @generated
   */
  void setResource(ResourceType value);

  /**
   * Returns the value of the '<em><b>AInt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AInt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AInt</em>' attribute.
   * @see #isSetAInt()
   * @see #unsetAInt()
   * @see #setAInt(int)
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_AInt()
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
   * @generated
   */
  int getAInt();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAInt <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AInt</em>' attribute.
   * @see #isSetAInt()
   * @see #unsetAInt()
   * @see #getAInt()
   * @generated
   */
  void setAInt(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAInt <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAInt()
   * @see #getAInt()
   * @see #setAInt(int)
   * @generated
   */
  void unsetAInt();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAInt <em>AInt</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>AInt</em>' attribute is set.
   * @see #unsetAInt()
   * @see #getAInt()
   * @see #setAInt(int)
   * @generated
   */
  boolean isSetAInt();

  /**
   * Returns the value of the '<em><b>AQname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AQname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AQname</em>' attribute.
   * @see #setAQname(Object)
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_AQname()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
   * @generated
   */
  Object getAQname();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAQname <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AQname</em>' attribute.
   * @see #getAQname()
   * @generated
   */
  void setAQname(Object value);

  /**
   * Returns the value of the '<em><b>AUnion</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>AUnion</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>AUnion</em>' attribute.
   * @see #setAUnion(List)
   * @see org.eclipse.emf.test.models.qname.QnamePackage#getDocumentRoot_AUnion()
   * @model unique="false" dataType="org.eclipse.emf.test.models.qname.ListUnion"
   * @generated
   */
  List getAUnion();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.DocumentRoot#getAUnion <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>AUnion</em>' attribute.
   * @see #getAUnion()
   * @generated
   */
  void setAUnion(List value);

} // DocumentRoot
