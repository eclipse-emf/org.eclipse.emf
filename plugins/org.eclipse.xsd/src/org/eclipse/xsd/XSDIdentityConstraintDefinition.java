/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cIdentity-constraint_Definitions"><em><b>Identity Constraint Definition</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getIdentityConstraintCategory <em>Identity Constraint Category</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getReferencedKey <em>Referenced Key</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getSelector <em>Selector</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintDefinition()
 * @model
 * @generated
 */
public interface XSDIdentityConstraintDefinition extends XSDNamedComponent
{
  /**
   * Returns the value of the '<em><b>Identity Constraint Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDIdentityConstraintCategory}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#identity-constraint_name">identity constraint category</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identity Constraint Category</em>' attribute.
   * @see org.eclipse.xsd.XSDIdentityConstraintCategory
   * @see #setIdentityConstraintCategory(XSDIdentityConstraintCategory)
   * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintDefinition_IdentityConstraintCategory()
   * @model
   * @generated
   */
  XSDIdentityConstraintCategory getIdentityConstraintCategory();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getIdentityConstraintCategory <em>Identity Constraint Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identity Constraint Category</em>' attribute.
   * @see org.eclipse.xsd.XSDIdentityConstraintCategory
   * @see #getIdentityConstraintCategory()
   * @generated
   */
  void setIdentityConstraintCategory(XSDIdentityConstraintCategory value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#rc-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintDefinition_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Referenced Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#referenced_key">referenced key</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Key</em>' reference.
   * @see #setReferencedKey(XSDIdentityConstraintDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintDefinition_ReferencedKey()
   * @model resolveProxies="false"
   * @generated
   */
  XSDIdentityConstraintDefinition getReferencedKey();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getReferencedKey <em>Referenced Key</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Referenced Key</em>' reference.
   * @see #getReferencedKey()
   * @generated
   */
  void setReferencedKey(XSDIdentityConstraintDefinition value);

  /**
   * Returns the value of the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#selector">selector</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selector</em>' containment reference.
   * @see #setSelector(XSDXPathDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintDefinition_Selector()
   * @model containment="true" required="true"
   * @generated
   */
  XSDXPathDefinition getSelector();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getSelector <em>Selector</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Selector</em>' containment reference.
   * @see #getSelector()
   * @generated
   */
  void setSelector(XSDXPathDefinition value);

  /**
   * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDXPathDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#fields">fields</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fields</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintDefinition_Fields()
   * @model containment="true" required="true"
   * @generated
   */
  EList<XSDXPathDefinition> getFields();

} 
