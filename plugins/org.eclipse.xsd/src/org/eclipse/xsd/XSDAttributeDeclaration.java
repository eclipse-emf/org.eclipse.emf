/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd;




/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cAttribute_Declarations"><em><b>Attribute Declaration</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDAttributeDeclaration#isAttributeDeclarationReference <em>Attribute Declaration Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeDeclaration#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeDeclaration#getAnonymousTypeDefinition <em>Anonymous Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeDeclaration#getTypeDefinition <em>Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDAttributeDeclaration#getResolvedAttributeDeclaration <em>Resolved Attribute Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDAttributeDeclaration()
 * @model
 * @generated
 */
public interface XSDAttributeDeclaration extends XSDFeature, XSDSchemaContent
{
  /**
   * Returns the value of the '<em><b>Attribute Declaration Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute is equivalent to
   *<pre>
   *  xsdAttributeDeclaration != xsdAttributeDeclaration.{@link #getResolvedAttributeDeclaration getResolvedAttributeDeclaration}()
   *</pre>
   * An infoset feature will never return an instance for which this is the <code>true</code> 
   * since this is a concrete attribute that is used to represent an attribute declaration 
   * with a <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">ref</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Declaration Reference</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeDeclaration_AttributeDeclarationReference()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isAttributeDeclarationReference();

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#a-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeDeclaration_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeDeclaration#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Anonymous Type Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents a simple type definition defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">attribute</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Anonymous Type Definition</em>' containment reference.
   * @see #setAnonymousTypeDefinition(XSDSimpleTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeDeclaration_AnonymousTypeDefinition()
   * @model containment="true"
   * @generated
   */
  XSDSimpleTypeDefinition getAnonymousTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeDeclaration#getAnonymousTypeDefinition <em>Anonymous Type Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Anonymous Type Definition</em>' containment reference.
   * @see #getAnonymousTypeDefinition()
   * @generated
   */
  void setAnonymousTypeDefinition(XSDSimpleTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#a-simple_type_definition">type definition</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Definition</em>' reference.
   * @see #setTypeDefinition(XSDSimpleTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeDeclaration_TypeDefinition()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDSimpleTypeDefinition getTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeDeclaration#getTypeDefinition <em>Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Definition</em>' reference.
   * @see #getTypeDefinition()
   * @generated
   */
  void setTypeDefinition(XSDSimpleTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Resolved Attribute Declaration</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the attribute declaration 
   * resolved by the <a href="http://www.w3.org/TR/xmlschema-1/#element-attribute">ref</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resolved Attribute Declaration</em>' reference.
   * @see #setResolvedAttributeDeclaration(XSDAttributeDeclaration)
   * @see org.eclipse.xsd.XSDPackage#getXSDAttributeDeclaration_ResolvedAttributeDeclaration()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDAttributeDeclaration getResolvedAttributeDeclaration();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDAttributeDeclaration#getResolvedAttributeDeclaration <em>Resolved Attribute Declaration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolved Attribute Declaration</em>' reference.
   * @see #getResolvedAttributeDeclaration()
   * @generated
   */
  void setResolvedAttributeDeclaration(XSDAttributeDeclaration value);

}
