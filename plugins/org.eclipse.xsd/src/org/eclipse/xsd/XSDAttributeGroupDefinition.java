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
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cAttribute_Group_Definitions"><em><b>Attribute Group Definition</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#isAttributeGroupDefinitionReference <em>Attribute Group Definition Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAttributeUses <em>Attribute Uses</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAttributeWildcardContent <em>Attribute Wildcard Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAttributeWildcard <em>Attribute Wildcard</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getResolvedAttributeGroupDefinition <em>Resolved Attribute Group Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getSyntheticWildcard <em>Synthetic Wildcard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition()
 * @model
 * @generated
 */
public interface XSDAttributeGroupDefinition extends XSDRedefinableComponent, XSDAttributeGroupContent, XSDRedefineContent
{
  /**
   * Returns the value of the '<em><b>Attribute Group Definition Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute is equivalent to
   *<pre>
   *  xsdAttributeGroupDefinition != xsdAttributeGroupDefinition.{@link #getResolvedAttributeGroupDefinition getResolvedAttributeGroupDefinition}()
   *</pre>
   * An infoset feature will never return an instance for which this is the <code>true</code>
   * since this is a concrete attribute that is used to represent an attribute group definition
   * with a <a href="http://www.w3.org/TR/xmlschema-1/#element-attributeGroup">ref</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Group Definition Reference</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_AttributeGroupDefinitionReference()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isAttributeGroupDefinitionReference();

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ag-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAttributeGroupContent}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the contents defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attributeGroup">attributeGroup</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_Contents()
   * @model containment="true"
   * @generated
   */
  EList<XSDAttributeGroupContent> getContents();

  /**
   * Returns the value of the '<em><b>Attribute Uses</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAttributeUse}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ag-attribute_uses">attribute uses</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Uses</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_AttributeUses()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAttributeUse> getAttributeUses();

  /**
   * Returns the value of the '<em><b>Attribute Wildcard Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the attribute wildcard defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attributeGroup">attributeGroup</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Wildcard Content</em>' containment reference.
   * @see #setAttributeWildcardContent(XSDWildcard)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_AttributeWildcardContent()
   * @model containment="true"
   * @generated
   */
  XSDWildcard getAttributeWildcardContent();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAttributeWildcardContent <em>Attribute Wildcard Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute Wildcard Content</em>' containment reference.
   * @see #getAttributeWildcardContent()
   * @generated
   */
  void setAttributeWildcardContent(XSDWildcard value);

  /**
   * Returns the value of the '<em><b>Attribute Wildcard</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ag-attribute_wildcard">attribute wildcard</a>
   * infoset property.
   * It is computed from the {@link #getAttributeWildcardContent() attribute wildcard content} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Wildcard</em>' reference.
   * @see #setAttributeWildcard(XSDWildcard)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_AttributeWildcard()
   * @model resolveProxies="false"
   * @generated
   */
  XSDWildcard getAttributeWildcard();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getAttributeWildcard <em>Attribute Wildcard</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute Wildcard</em>' reference.
   * @see #getAttributeWildcard()
   * @generated
   */
  void setAttributeWildcard(XSDWildcard value);

  /**
   * Returns the value of the '<em><b>Resolved Attribute Group Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the attribute group definition 
   * resolved by the <a href="http://www.w3.org/TR/xmlschema-1/#element-attributeGroup">ref</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resolved Attribute Group Definition</em>' reference.
   * @see #setResolvedAttributeGroupDefinition(XSDAttributeGroupDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_ResolvedAttributeGroupDefinition()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDAttributeGroupDefinition getResolvedAttributeGroupDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getResolvedAttributeGroupDefinition <em>Resolved Attribute Group Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolved Attribute Group Definition</em>' reference.
   * @see #getResolvedAttributeGroupDefinition()
   * @generated
   */
  void setResolvedAttributeGroupDefinition(XSDAttributeGroupDefinition value);

  /**
   * Returns the value of the '<em><b>Synthetic Wildcard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This contains the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ag-attribute_wildcard">attribute wildcard</a>
   * infoset property, if the rules require a synthesized component.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synthetic Wildcard</em>' containment reference.
   * @see #setSyntheticWildcard(XSDWildcard)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeGroupDefinition_SyntheticWildcard()
   * @model containment="true" transient="true"
   * @generated
   */
  XSDWildcard getSyntheticWildcard();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeGroupDefinition#getSyntheticWildcard <em>Synthetic Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Synthetic Wildcard</em>' containment reference.
   * @see #getSyntheticWildcard()
   * @generated
   */
  void setSyntheticWildcard(XSDWildcard value);

}
