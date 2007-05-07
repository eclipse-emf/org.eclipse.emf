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
 * $Id: ResourceType.java,v 1.4 2007/05/07 17:26:29 marcelop Exp $
 */
package org.eclipse.emf.test.models.qname;

import java.util.List;

import javax.xml.namespace.QName;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.qname.ResourceType#getUnionvalue <em>Unionvalue</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.ResourceType#getQnamelist <em>Qnamelist</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.ResourceType#getIntvalue <em>Intvalue</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.ResourceType#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.ResourceType#getMyQname <em>My Qname</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.ResourceType#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType()
 * @model extendedMetaData="name='resourceType' kind='elementOnly'"
 * @generated
 */
public interface ResourceType extends EObject
{
  /**
   * Returns the value of the '<em><b>Unionvalue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unionvalue</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unionvalue</em>' attribute.
   * @see #setUnionvalue(Object)
   * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType_Unionvalue()
   * @model unique="false" dataType="org.eclipse.emf.test.models.qname.IntQNameUnion" required="true"
   *        extendedMetaData="kind='element' name='unionvalue' namespace='##targetNamespace'"
   * @generated
   */
  Object getUnionvalue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.ResourceType#getUnionvalue <em>Unionvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unionvalue</em>' attribute.
   * @see #getUnionvalue()
   * @generated
   */
  void setUnionvalue(Object value);

  /**
   * Returns the value of the '<em><b>Qnamelist</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Qnamelist</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Qnamelist</em>' attribute.
   * @see #setQnamelist(List)
   * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType_Qnamelist()
   * @model unique="false" dataType="org.eclipse.emf.test.models.qname.QnameList" required="true" many="false"
   *        extendedMetaData="kind='element' name='qnamelist' namespace='##targetNamespace'"
   * @generated
   */
  List<QName> getQnamelist();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.ResourceType#getQnamelist <em>Qnamelist</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Qnamelist</em>' attribute.
   * @see #getQnamelist()
   * @generated
   */
  void setQnamelist(List<QName> value);

  /**
   * Returns the value of the '<em><b>Intvalue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Intvalue</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Intvalue</em>' attribute.
   * @see #isSetIntvalue()
   * @see #unsetIntvalue()
   * @see #setIntvalue(int)
   * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType_Intvalue()
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
   *        extendedMetaData="kind='element' name='intvalue' namespace='##targetNamespace'"
   * @generated
   */
  int getIntvalue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.ResourceType#getIntvalue <em>Intvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Intvalue</em>' attribute.
   * @see #isSetIntvalue()
   * @see #unsetIntvalue()
   * @see #getIntvalue()
   * @generated
   */
  void setIntvalue(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.qname.ResourceType#getIntvalue <em>Intvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetIntvalue()
   * @see #getIntvalue()
   * @see #setIntvalue(int)
   * @generated
   */
  void unsetIntvalue();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.qname.ResourceType#getIntvalue <em>Intvalue</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Intvalue</em>' attribute is set.
   * @see #unsetIntvalue()
   * @see #getIntvalue()
   * @see #setIntvalue(int)
   * @generated
   */
  boolean isSetIntvalue();

  /**
   * Returns the value of the '<em><b>Any</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any</em>' attribute list.
   * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType_Any()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="false"
   *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':3' processing='strict'"
   * @generated
   */
  FeatureMap getAny();

  /**
   * Returns the value of the '<em><b>My Qname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>My Qname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>My Qname</em>' attribute.
   * @see #setMyQname(List)
   * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType_MyQname()
   * @model unique="false" dataType="org.eclipse.emf.test.models.qname.QnameList" many="false"
   *        extendedMetaData="kind='attribute' name='myQname'"
   * @generated
   */
  List<QName> getMyQname();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.qname.ResourceType#getMyQname <em>My Qname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>My Qname</em>' attribute.
   * @see #getMyQname()
   * @generated
   */
  void setMyQname(List<QName> value);

  /**
   * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any Attribute</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any Attribute</em>' attribute list.
   * @see org.eclipse.emf.test.models.qname.QNamePackage#getResourceType_AnyAttribute()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="kind='attributeWildcard' wildcards='##any' name=':5' processing='strict'"
   * @generated
   */
  FeatureMap getAnyAttribute();

} // ResourceType
