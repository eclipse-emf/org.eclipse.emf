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
 * A representation of the model object '<em><b>Named Component</b></em>'.
 * It is used to represent aspects common to
 * {@link org.eclipse.xsd.XSDAttributeDeclaration attribute declarations}, 
 * {@link org.eclipse.xsd.XSDAttributeGroupDefinition attribute group definitions}, 
 * {@link org.eclipse.xsd.XSDComplexTypeDefinition complex type definitions}, 
 * {@link org.eclipse.xsd.XSDElementDeclaration element declarations}, 
 * {@link org.eclipse.xsd.XSDIdentityConstraintDefinition identity constraint definitions}, 
 * {@link org.eclipse.xsd.XSDModelGroupDefinition model groups definitions}, 
 * {@link org.eclipse.xsd.XSDNotationDeclaration notation declarations}, and
 * {@link org.eclipse.xsd.XSDSimpleTypeDefinition simple type definitions}.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDNamedComponent#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNamedComponent#getAliasName <em>Alias Name</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNamedComponent#getURI <em>URI</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNamedComponent#getAliasURI <em>Alias URI</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDNamedComponent#getQName <em>QName</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent()
 * @model abstract="true"
 * @generated
 */
public interface XSDNamedComponent extends XSDComponent
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the value of the
   * attribute declaration <a href="http://www.w3.org/TR/xmlschema-1/#a-name">name</a>,
   * attribute group definition <a href="http://www.w3.org/TR/xmlschema-1/#ag-name">name</a>,
   * complex type definition <a href="http://www.w3.org/TR/xmlschema-1/#ct-name">name</a>,
   * element declaration <a href="http://www.w3.org/TR/xmlschema-1/#e-name">name</a>,
   * identity constraint definition <a href="http://www.w3.org/TR/xmlschema-1/#c-name">name</a>,
   * model group definition <a href="http://www.w3.org/TR/xmlschema-1/#mg-name">name</a>,
   * notation declaration <a href="http://www.w3.org/TR/xmlschema-1/#n-name">name</a>, or
   * simple type definition <a href="http://www.w3.org/TR/xmlschema-2/#defn-name">name</a> 
   * (<a href="http://www.w3.org/TR/xmlschema-1/#st-name">*</a>)
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDNamedComponent#getName <em>Name</em>}' attribute.
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
   * <p>
   * This represents the value of the
   * attribute declaration <a href="http://www.w3.org/TR/xmlschema-1/#a-target_namespace">target namespace</a>,
   * attribute group definition <a href="http://www.w3.org/TR/xmlschema-1/#ag-target_namespace">target namespace</a>,
   * complex type definition <a href="http://www.w3.org/TR/xmlschema-1/#ct-target_namespace">target namespace</a>,
   * element declaration <a href="http://www.w3.org/TR/xmlschema-1/#e-target_namespace">target namespace</a>,
   * identity constraint definition <a href="http://www.w3.org/TR/xmlschema-1/#c-target_namespace">target namespace</a>,
   * model group definition <a href="http://www.w3.org/TR/xmlschema-1/#mg-target_namespace">target namespace</a>,
   * notation declaration <a href="http://www.w3.org/TR/xmlschema-1/#n-target_namespace">target namespace</a>, or
   * simple type definition <a href="http://www.w3.org/TR/xmlschema-2/#defn-target-namespace">target namespace</a> 
   * (<a href="http://www.w3.org/TR/xmlschema-1/#st-target_namespace">*</a>)
   * infoset property.
   * It is computed from the {@link org.eclipse.xsd.XSDSchema#getTargetNamespace() target namespace} of the schema 
   * and should typically not be set directly;
   * in the case of locally scoped {@link org.eclipse.xsd.XSDFeature features}, 
   * the value is also affected by the {@link org.eclipse.xsd.XSDFeature#getForm form}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Namespace</em>' attribute.
   * @see #setTargetNamespace(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent_TargetNamespace()
   * @model
   * @generated
   */
  String getTargetNamespace();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDNamedComponent#getTargetNamespace <em>Target Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Namespace</em>' attribute.
   * @see #getTargetNamespace()
   * @generated
   */
  void setTargetNamespace(String value);

  /**
   * Returns the value of the '<em><b>Alias Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This attempts to construct a relatively meaningful name for an anonymous component 
   * by using the name of the containing component and an indication of the relation to that component,
   * For example, 
   * "<code>E_._type</code>" would be the alias name of the anonymous type definition of the element "<code>E</code>" and
   * "<code>LT_._item</code>" would be the alias name of the anonymous item type definition of the list type defintion "<code>LT</code>".
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Alias Name</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent_AliasName()
   * @model changeable="false" volatile="true"
   * @generated
   */
  String getAliasName();

  /**
   * Returns the value of the '<em><b>URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This is equivalent to the string
   *<pre>
   *  &lt;{@link #getTargetNamespace() target namespace}>#&lt;{@link #getName() name}>
   *</pre>
   * where a <code>null</code> target namespace is taken to mean an empty string.
   * </p>
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveAttributeDeclarationURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveAttributeGroupDefinitionURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveComplexTypeDefinitionURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveElementDeclarationURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveIdentityConstraintDefinitionURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveModelGroupDefinitionURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveNotationDeclarationURI
   * @see org.eclipse.xsd.XSDConcreteComponent#resolveSimpleTypeDefinitionURI
   * @<!-- end-user-doc -->
   * @return the value of the '<em>URI</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent_URI()
   * @model changeable="false" volatile="true"
   * @generated
   */
  String getURI();

  /**
   * Returns the value of the '<em><b>Alias URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This is equivalent to the string
   *<pre>
   *  &lt;{@link #getTargetNamespace() target namespace}>#&lt;{@link #getAliasName() alias name}>
   *</pre>
   * where a <code>null</code> target namespace is taken to mean an empty string.
   * There is no support yet for resolving this URI;
   * only globally scoped names can be resolved.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Alias URI</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent_AliasURI()
   * @model changeable="false" volatile="true"
   * @generated
   */
  String getAliasURI();

  /**
   * Returns the value of the '<em><b>QName</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute is equivalent to
   *<pre>
   *  xsdNamedComponent.{@link #getQName(org.eclipse.xsd.XSDConcreteComponent) getQName}(xsdNamedComponent)
   *</pre>
   * </p>
   * @see #getQName(org.eclipse.xsd.XSDConcreteComponent)
   * @<!-- end-user-doc -->
   * @return the value of the '<em>QName</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDNamedComponent_QName()
   * @model changeable="false" volatile="true"
   * @generated
   */
  String getQName();

  /**
   * Returns this named component's
   * '{@link org.eclipse.xsd.XSDNamedComponent#getQName <em>QName</em>}' 
   * in the context of the given component.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param relativeToComponent the context at which to express the QName.
   * @return the '{@link org.eclipse.xsd.XSDNamedComponent#getQName <em>QName</em>}'.
   * @see #getQName()
   */
  String getQName(XSDConcreteComponent relativeToComponent);

  /**
   * Returns whether this named component and the given named component are named the same.
   * <!-- begin-user-doc -->
   * <p>
   * This is equivalent to 
   *<pre>
   *  xsdNamedComponent.{@link #hasNameAndTargetNamespace hasNameAndTargetNamespace}
   *    (xsdOtherNamedComponent.{@link #getName getName}(), 
   *     xsdOtherNamedComponent.{@link #getTargetNamespace getTargetNamespace}())
   *</pre>
   * </p>
   * <!-- end-user-doc -->
   * @param xsdNamedComponent another named component.
   * @return whether this named component and the given named component are named the same.
   * @see #hasNameAndTargetNamespace
   * @see #getName()
   * @see #getTargetNamespace()
   */
  boolean hasSameNameAndTargetNamespace(XSDNamedComponent xsdNamedComponent);

  /**
   * Returns whether this named component has the given name and target namespace.
   * <!-- begin-user-doc -->
   * <p>
   * This is equivalent to 
   *<pre>
   *  (targetNamespace == null ?
   *     getTargetNamespace() == null :
   *     targetNamespace.equals(getTargetNamespace())) &&
   *  (name == null ?
   *     getName() == null :
   *     name.equals(getName()))
   *</pre>
   * </p>
   * <!-- end-user-doc -->
   * @param name the name.
   * @param targetNamespace namespace the target namespace.
   * @return whether this named component has the given name and target namespace.
   * @see #hasSameNameAndTargetNamespace
   * @see #hasURI
   * @see #getName()
   * @see #getTargetNamespace()
   */
  boolean hasNameAndTargetNamespace(String name,  String targetNamespace);

  /**
   * Returns whether this named component has the given URI.
   * <!-- begin-user-doc -->
   * <p>
   * This is equivalent to 
   *<pre>
   *  String theTargetNamespace = getTargetNamespace();
   *  if (theTargetNamespace == null)
   *  {
   *    theTargetNamespace = "";
   *  }
   *  String theName = getName();
   *  if (theName == null)
   *  {
   *    theName = "";
   *  }
   *  return
   *    qName.startsWith(theTargetNamespace) &&
   *      qName.endsWith(theName) &&
   *      qName.length() == theName.length() + theTargetNamespace.length() + 1;
   *</pre>
   * </p>
   * <!-- end-user-doc -->
   * @param uri the URI.
   * @return whether this named component has the given URI.
   * @see #hasNameAndTargetNamespace
   * @see #hasSameNameAndTargetNamespace
   * @see #getURI()
   */
  boolean hasURI(String uri);
}
