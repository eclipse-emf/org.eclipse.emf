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


import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#Schemas"><em><b>Schema</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getDocument <em>Document</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getSchemaLocation <em>Schema Location</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getAttributeFormDefault <em>Attribute Form Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getElementFormDefault <em>Element Form Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getFinalDefault <em>Final Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getBlockDefault <em>Block Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getElementDeclarations <em>Element Declarations</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getAttributeDeclarations <em>Attribute Declarations</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getAttributeGroupDefinitions <em>Attribute Group Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getTypeDefinitions <em>Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getModelGroupDefinitions <em>Model Group Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getIdentityConstraintDefinitions <em>Identity Constraint Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getNotationDeclarations <em>Notation Declarations</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getAllDiagnostics <em>All Diagnostics</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getReferencingDirectives <em>Referencing Directives</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getRootVersion <em>Root Version</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getOriginalVersion <em>Original Version</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getIncorporatedVersions <em>Incorporated Versions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchema#getSchemaForSchema <em>Schema For Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDSchema()
 * @model
 * @generated
 */
public interface XSDSchema extends XSDScope
{
  /**
   * Returns the value of the '<em><b>Document</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This is the optional DOM document of this schema, 
   * i.e., the owner of the {@link #getElement element}.
   * A document can be created by calling {@link #updateDocument};
   * a document <b>and</b> an element can be create by calling {@link #updateElement}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Document</em>' attribute.
   * @see #setDocument(Document)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_Document()
   * @model dataType="org.eclipse.xsd.DOMDocument"
   * @generated
   */
  Document getDocument();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchema#getDocument <em>Document</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Document</em>' attribute.
   * @see #getDocument()
   * @generated
   */
  void setDocument(Document value);

  /**
   * Returns the value of the '<em><b>Schema Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the URI of the resource that contains this this schema.
   * It is used to complete any relative {@link org.eclipse.xsd.XSDSchemaDirective#getSchemaLocation schemaLocation} URI 
   * in an import, include, or redefine.
   * No schema {@link org.eclipse.xsd.XSDSchemaDirective#getResolvedSchema resolution} will be attempted until this has been set.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schema Location</em>' attribute.
   * @see #setSchemaLocation(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_SchemaLocation()
   * @model
   * @generated
   */
  String getSchemaLocation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchema#getSchemaLocation <em>Schema Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Schema Location</em>' attribute.
   * @see #getSchemaLocation()
   * @generated
   */
  void setSchemaLocation(String value);

  /**
   * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">targetNamespace</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Namespace</em>' attribute.
   * @see #setTargetNamespace(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_TargetNamespace()
   * @model
   * @generated
   */
  String getTargetNamespace();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchema#getTargetNamespace <em>Target Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Namespace</em>' attribute.
   * @see #getTargetNamespace()
   * @generated
   */
  void setTargetNamespace(String value);

  /**
   * Returns the value of the '<em><b>Attribute Form Default</b></em>' attribute.
   * The default value is <code>"unqualified"</code>.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDForm}.
   * <!-- begin-user-doc -->
   * <p>
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">attributeFormDefault</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Form Default</em>' attribute.
   * @see org.eclipse.xsd.XSDForm
   * @see #isSetAttributeFormDefault()
   * @see #unsetAttributeFormDefault()
   * @see #setAttributeFormDefault(XSDForm)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_AttributeFormDefault()
   * @model default="unqualified" unsettable="true"
   * @generated
   */
  XSDForm getAttributeFormDefault();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchema#getAttributeFormDefault <em>Attribute Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute Form Default</em>' attribute.
   * @see org.eclipse.xsd.XSDForm
   * @see #isSetAttributeFormDefault()
   * @see #unsetAttributeFormDefault()
   * @see #getAttributeFormDefault()
   * @generated
   */
  void setAttributeFormDefault(XSDForm value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDSchema#getAttributeFormDefault <em>Attribute Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAttributeFormDefault()
   * @see #getAttributeFormDefault()
   * @see #setAttributeFormDefault(XSDForm)
   * @generated
   */
  void unsetAttributeFormDefault();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDSchema#getAttributeFormDefault <em>Attribute Form Default</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Attribute Form Default</em>' attribute is set.
   * @see #unsetAttributeFormDefault()
   * @see #getAttributeFormDefault()
   * @see #setAttributeFormDefault(XSDForm)
   * @generated
   */
  boolean isSetAttributeFormDefault();

  /**
   * Returns the value of the '<em><b>Element Form Default</b></em>' attribute.
   * The default value is <code>"unqualified"</code>.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDForm}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">elementFormDefault</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Form Default</em>' attribute.
   * @see org.eclipse.xsd.XSDForm
   * @see #isSetElementFormDefault()
   * @see #unsetElementFormDefault()
   * @see #setElementFormDefault(XSDForm)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_ElementFormDefault()
   * @model default="unqualified" unsettable="true"
   * @generated
   */
  XSDForm getElementFormDefault();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchema#getElementFormDefault <em>Element Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element Form Default</em>' attribute.
   * @see org.eclipse.xsd.XSDForm
   * @see #isSetElementFormDefault()
   * @see #unsetElementFormDefault()
   * @see #getElementFormDefault()
   * @generated
   */
  void setElementFormDefault(XSDForm value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDSchema#getElementFormDefault <em>Element Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetElementFormDefault()
   * @see #getElementFormDefault()
   * @see #setElementFormDefault(XSDForm)
   * @generated
   */
  void unsetElementFormDefault();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDSchema#getElementFormDefault <em>Element Form Default</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Element Form Default</em>' attribute is set.
   * @see #unsetElementFormDefault()
   * @see #getElementFormDefault()
   * @see #setElementFormDefault(XSDForm)
   * @generated
   */
  boolean isSetElementFormDefault();

  /**
   * Returns the value of the '<em><b>Final Default</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">finalDefault</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final Default</em>' attribute list.
   * @see org.eclipse.xsd.XSDProhibitedSubstitutions
   * @see #isSetFinalDefault()
   * @see #unsetFinalDefault()
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_FinalDefault()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDProhibitedSubstitutions> getFinalDefault();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDSchema#getFinalDefault <em>Final Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetFinalDefault()
   * @see #getFinalDefault()
   * @generated
   */
  void unsetFinalDefault();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDSchema#getFinalDefault <em>Final Default</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Final Default</em>' attribute list is set.
   * @see #unsetFinalDefault()
   * @see #getFinalDefault()
   * @generated
   */
  boolean isSetFinalDefault();

  /**
   * Returns the String value of the '{@link #getFinalDefault() <em>Final Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Final Default</em>' attribute list.
   * @see #getFinalDefault()
   * @see #setStringFinalDefault(java.lang.String)
   */
  public String getStringFinalDefault();

  /**
   * Set the String value of the '{@link #getFinalDefault() <em>Final Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param finalDefault the new value of the '<em>Final Default</em>' attribute list.
   * @see #getFinalDefault()
   * @see #getStringFinalDefault()
   */
  public void setStringFinalDefault(String finalDefault);

  /**
   * Returns the value of the '<em><b>Block Default</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDDisallowedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDDisallowedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">blockDefault</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block Default</em>' attribute list.
   * @see org.eclipse.xsd.XSDDisallowedSubstitutions
   * @see #isSetBlockDefault()
   * @see #unsetBlockDefault()
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_BlockDefault()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDDisallowedSubstitutions> getBlockDefault();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDSchema#getBlockDefault <em>Block Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetBlockDefault()
   * @see #getBlockDefault()
   * @generated
   */
  void unsetBlockDefault();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDSchema#getBlockDefault <em>Block Default</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Block Default</em>' attribute list is set.
   * @see #unsetBlockDefault()
   * @see #getBlockDefault()
   * @generated
   */
  boolean isSetBlockDefault();

  /**
   * Returns the value of the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">version</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' attribute.
   * @see #setVersion(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_Version()
   * @model
   * @generated
   */
  String getVersion();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchema#getVersion <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' attribute.
   * @see #getVersion()
   * @generated
   */
  void setVersion(String value);

  /**
   * Returns the String value of the '{@link #getBlockDefault() <em>Block Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Block Default</em>' attribute list.
   * @see #getBlockDefault()
   * @see #setStringBlockDefault(java.lang.String)
   */
  public String getStringBlockDefault();

  /**
   * Returns the String value of the '{@link #getBlockDefault() <em>Block Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param blockDefault the new value of the '<em>Block Default</em>' attribute list.
   * @see #getBlockDefault()
   * @see #getStringBlockDefault()
   */
  public void setStringBlockDefault(String blockDefault);

  /**
   * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSchemaContent}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the contents defined within the body of a
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-schema">schema</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_Contents()
   * @model containment="true"
   * @generated
   */
  EList<XSDSchemaContent> getContents();

  /**
   * Returns the value of the '<em><b>Element Declarations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDElementDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element_declarations">element declarations</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Declarations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_ElementDeclarations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDElementDeclaration> getElementDeclarations();

  /**
   * Returns the value of the '<em><b>Attribute Declarations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAttributeDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#attribute_declarations">attribute declarations</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Declarations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_AttributeDeclarations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAttributeDeclaration> getAttributeDeclarations();

  /**
   * Returns the value of the '<em><b>Attribute Group Definitions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAttributeGroupDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#attribute_group_definitions">attribute group definitions</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Group Definitions</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_AttributeGroupDefinitions()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAttributeGroupDefinition> getAttributeGroupDefinitions();

  /**
   * Returns the value of the '<em><b>Type Definitions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDTypeDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#type_definitions">type definitions</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Definitions</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_TypeDefinitions()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDTypeDefinition> getTypeDefinitions();

  /**
   * Returns the value of the '<em><b>Model Group Definitions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDModelGroupDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#model_group_definitions">model group definitions</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Group Definitions</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_ModelGroupDefinitions()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDModelGroupDefinition> getModelGroupDefinitions();

  /**
   * Returns the value of the '<em><b>Identity Constraint Definitions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDIdentityConstraintDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#Schema_details">identity constraint definitions</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * Actually, there is no such property defined 
   * but this seems to be an oversight;
   * it is needed to resolve a {@link org.eclipse.xsd.XSDIdentityConstraintDefinition#getReferencedKey referenced key}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identity Constraint Definitions</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_IdentityConstraintDefinitions()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDIdentityConstraintDefinition> getIdentityConstraintDefinitions();

  /**
   * Returns the value of the '<em><b>Notation Declarations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDNotationDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#notation_declarations">notation declarations</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Notation Declarations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_NotationDeclarations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDNotationDeclaration> getNotationDeclarations();

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAnnotation}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#annotations">annotations</a>
   * infoset property.
   * It is computed from the {@link #getContents() contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_Annotations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAnnotation> getAnnotations();

  /**
   * Returns the value of the '<em><b>All Diagnostics</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDDiagnostic}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents all the diagnostics that occur within the components of this schema.
   * It is computed from the '{@link #getDiagnostics <em>Diagnostics</em>}' reference list 
   * and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Diagnostics</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_AllDiagnostics()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDDiagnostic> getAllDiagnostics();

  /**
   * Returns the value of the '<em><b>Referencing Directives</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSchemaDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the directives that have this schema as their 
   * '{@link org.eclipse.xsd.XSDSchemaDirective#getResolvedSchema <em>Resolved Schema</em>}' reference or
   * '{@link org.eclipse.xsd.XSDSchemaCompositor#getIncorporatedSchema <em>Incorporated Schema</em>}' reference.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Referencing Directives</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_ReferencingDirectives()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDSchemaDirective> getReferencingDirectives();

  /**
   * Returns the value of the '<em><b>Root Version</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This walks the {@link #getOriginalVersion() original versions}
   * until it hits one that has no original version.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Version</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_RootVersion()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDSchema getRootVersion();

  /**
   * Returns the value of the '<em><b>Original Version</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the schema from which an {@link #getIncorporatedVersions incorporated version} originates.
   * The {@link #getRootVersion root version} has itself as its original version.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Original Version</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_OriginalVersion()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDSchema getOriginalVersion();

  /**
   * Returns the value of the '<em><b>Incorporated Versions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSchema}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents those versions of this schema 
   * that have been {@link org.eclipse.xsd.XSDInclude included} into a schema with a different namespace 
   * or have been otherwise {@link org.eclipse.xsd.XSDRedefine redefined}.
   * </p>
   * @see org.eclipse.xsd.XSDSchemaCompositor#getIncorporatedSchema()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Incorporated Versions</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_IncorporatedVersions()
   * @model containment="true" transient="true"
   * @generated
   */
  EList<XSDSchema> getIncorporatedVersions();

  /**
   * Returns the value of the '<em><b>Schema For Schema</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * '<a href="http://www.w3.org/TR/xmlschema-1/#normative-schemaSchema">schema for schemas</a>'.
   * It is computed from the {@link #getSchemaForSchemaNamespace schema for schema namespace}
   * and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schema For Schema</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDSchema_SchemaForSchema()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDSchema getSchemaForSchema();

  /**
   * Returns the best concrete component that corresponds to the given node.
   * Since each concrete component has an associated element not a node, a best element is determined from the node first.
   * Furthermore, since not every element corresponds to a concrete component, parent elements are considered when finding a match.
   * The best match will be the deepest choice.
   * @param node the node to find.
   * @return the best concrete component that corresponds to the given node.
   * @see #getElement()
   */
  public XSDConcreteComponent getCorrespondingComponent(Node node);

  /**
   * Returns a map from String ID to {@link XSDSimpleTypeDefinition}
   * based on the IDs in the DOM representation.
   * For the {@link #getSchemaForSchema() schema for schema},
   * this represents the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#built-in-datatypes">built-in datatypes</a>.
   * @return a map from String ID to {@link XSDSimpleTypeDefinition}
   * @see #getSchemaForSchema()
   */
  public Map<String, XSDSimpleTypeDefinition> getSimpleTypeIdMap();

  /**
   * Returns the map defined by the xmlns attributes of the underlying XML representation.
   * When a schema instance is constructed without an underlying DOM,
   * it is necessary to populate this map with at least a mapping for the 
   * {@link #getSchemaForSchemaQNamePrefix schema for schema namespace prefix}.
   * Note that the default prefix is represented as <code>null</code> not as a zero length string.
   * @return the map defined by the xmlns attributes of the underlying XML representation.
   * @see #getSchemaForSchemaQNamePrefix()
   * @see #getSchemaForSchemaNamespace()
   */
  public Map<String, String> getQNamePrefixToNamespaceMap();

  /**
   * Returns the QName prefix used to reference the {@link #getSchemaForSchemaNamespace schema for schema namespace}.
   * It must be a key in {@link #getQNamePrefixToNamespaceMap}.
   * It is recommended that the prefix '<code>xsd</code>' be used.
   * @return the QName prefix used to reference the schema for schema namespace.
   * @see #setSchemaForSchemaQNamePrefix
   * @see #getQNamePrefixToNamespaceMap
   */
  public String getSchemaForSchemaQNamePrefix();

  /**
   * Sets the QName prefix used to reference the {@link #getSchemaForSchemaNamespace schema for schema namespace}.
   * It must be a key in {@link #getQNamePrefixToNamespaceMap} 
   * that maps to a {@link org.eclipse.xsd.util.XSDConstants#isSchemaForSchemaNamespace valid} schema for schema namespace.
   * @param qNamePrefix the new schema for schema QName prefix.
   * @see #getSchemaForSchemaQNamePrefix
   * @see #getQNamePrefixToNamespaceMap
   */
  public void setSchemaForSchemaQNamePrefix(String qNamePrefix);

  /**
   * Returns the schema for schema namespace of this schema.
   * It is equivalent to
   *<pre>
   *  (String){@link #getQNamePrefixToNamespaceMap()}.get({@link #getSchemaForSchemaQNamePrefix()})
   *</pre>
   * so you can't set this directly.
   * @return the schema for schema namespace.
   * @see #getQNamePrefixToNamespaceMap()
   * @see #getSchemaForSchemaQNamePrefix()
   */
  public String getSchemaForSchemaNamespace();

  /**
   * Ensures that the {@link #getDocument document} will be set to an instance that can be used to create a DOM serialization.
   * @see #updateElement
   */
  Document updateDocument();

  /**
   * Returns whether the schema will incrementally resolve QNames and perform analysis after each edit change.
   * Since a single incremental update can be very expensive, 
   * when a significant number of edit changes are expected to be made,
   * performance can be improved by turning off incremental updates.
   * In that case, QNames will remain unresolved and analysis results will not be updated;
   * analysis involves things like propagating information from a base type to its derived types.
   * @return whether the schema will incrementally resolve QNames and perform analysis.
   * @see #setIncrementalUpdate
   * @see #update
   */
  boolean isIncrementalUpdate();

  /**
   * Sets whether the schema will incrementally resolve QNames and perform analysis after each edit change.
   * Setting this to <code>true</code> will also result in an update.
   * @param isIncrementalUpdate the new value.
   * @see #isIncrementalUpdate
   * @see #update
   */
  void setIncrementalUpdate(boolean isIncrementalUpdate);

  /**
   * Resolves QNames and performs analysis.
   * @see #isIncrementalUpdate
   * @see #update
   */
  void update();

  /**
   * Resolves QNames and performs analysis.
   * If <code>force</code> is <code>true</code> even previously resolved QNames are resolved again.
   * @param force whether previously resolved names should be resolved again.
   * @see #isIncrementalUpdate
   * @see #update
   */
  void update(boolean force);

  /**
   * Resets the effects of imports, includes, and redefines so that they will be reprocessed.
   * @see #update
   */
  void reset();
}
