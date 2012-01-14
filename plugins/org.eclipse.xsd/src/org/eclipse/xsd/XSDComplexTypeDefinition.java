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
 * '<a href="http://www.w3.org/TR/xmlschema-1/#Complex_Type_Definitions"><em><b>Complex Type Definition</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getDerivationMethod <em>Derivation Method</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentTypeCategory <em>Content Type Category</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getProhibitedSubstitutions <em>Prohibited Substitutions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getLexicalFinal <em>Lexical Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBlock <em>Block</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#isMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentAnnotation <em>Content Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBaseTypeDefinition <em>Base Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentType <em>Content Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getAttributeUses <em>Attribute Uses</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getAttributeContents <em>Attribute Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getAttributeWildcard <em>Attribute Wildcard</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getAttributeWildcardContent <em>Attribute Wildcard Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getRootTypeDefinition <em>Root Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getSyntheticParticle <em>Synthetic Particle</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDComplexTypeDefinition#getSyntheticWildcard <em>Synthetic Wildcard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition()
 * @model
 * @generated
 */
public interface XSDComplexTypeDefinition extends XSDTypeDefinition, XSDScope
{
  /**
   * Returns the value of the '<em><b>Derivation Method</b></em>' attribute.
   * The default value is <code>"restriction"</code>.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDDerivationMethod}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#derivation_method">derivation method</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Derivation Method</em>' attribute.
   * @see org.eclipse.xsd.XSDDerivationMethod
   * @see #isSetDerivationMethod()
   * @see #unsetDerivationMethod()
   * @see #setDerivationMethod(XSDDerivationMethod)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_DerivationMethod()
   * @model default="restriction" unsettable="true"
   * @generated
   */
  XSDDerivationMethod getDerivationMethod();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getDerivationMethod <em>Derivation Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Derivation Method</em>' attribute.
   * @see org.eclipse.xsd.XSDDerivationMethod
   * @see #isSetDerivationMethod()
   * @see #unsetDerivationMethod()
   * @see #getDerivationMethod()
   * @generated
   */
  void setDerivationMethod(XSDDerivationMethod value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getDerivationMethod <em>Derivation Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetDerivationMethod()
   * @see #getDerivationMethod()
   * @see #setDerivationMethod(XSDDerivationMethod)
   * @generated
   */
  void unsetDerivationMethod();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getDerivationMethod <em>Derivation Method</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Derivation Method</em>' attribute is set.
   * @see #unsetDerivationMethod()
   * @see #getDerivationMethod()
   * @see #setDerivationMethod(XSDDerivationMethod)
   * @generated
   */
  boolean isSetDerivationMethod();

  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDComplexFinal}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDComplexFinal}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-final">final</a>
   * infoset property.
   * It is computed from the {@link #getLexicalFinal() lexical final} and should typically not be modified directly.
   * </p>
   * @see #getStringFinal()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute list.
   * @see org.eclipse.xsd.XSDComplexFinal
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_Final()
   * @model
   * @generated
   */
  EList<XSDComplexFinal> getFinal();

  /**
   * Returns the String value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getFinal <em>Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Final</em>' attribute list.
   * @see #getFinal()
   */
  String getStringFinal();

  /**
   * Returns the value of the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-abstract">abstract</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract</em>' attribute.
   * @see #isSetAbstract()
   * @see #unsetAbstract()
   * @see #setAbstract(boolean)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_Abstract()
   * @model unsettable="true"
   * @generated
   */
  boolean isAbstract();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#isAbstract <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstract</em>' attribute.
   * @see #isSetAbstract()
   * @see #unsetAbstract()
   * @see #isAbstract()
   * @generated
   */
  void setAbstract(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#isAbstract <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAbstract()
   * @see #isAbstract()
   * @see #setAbstract(boolean)
   * @generated
   */
  void unsetAbstract();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#isAbstract <em>Abstract</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Abstract</em>' attribute is set.
   * @see #unsetAbstract()
   * @see #isAbstract()
   * @see #setAbstract(boolean)
   * @generated
   */
  boolean isSetAbstract();

  /**
   * Returns the value of the '<em><b>Content Type Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDContentTypeCategory}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the category of the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#content_type">content type</a>
   * infoset property.
   * It is computed from the type of the {@link #getContent() content} 
   * and from the setting of {@link org.eclipse.xsd.XSDComplexTypeDefinition#isMixed mixed}
   * and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content Type Category</em>' attribute.
   * @see org.eclipse.xsd.XSDContentTypeCategory
   * @see #setContentTypeCategory(XSDContentTypeCategory)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_ContentTypeCategory()
   * @model
   * @generated
   */
  XSDContentTypeCategory getContentTypeCategory();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentTypeCategory <em>Content Type Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content Type Category</em>' attribute.
   * @see org.eclipse.xsd.XSDContentTypeCategory
   * @see #getContentTypeCategory()
   * @generated
   */
  void setContentTypeCategory(XSDContentTypeCategory value);

  /**
   * Returns the value of the '<em><b>Prohibited Substitutions</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-exact">prohibited substitutions</a>
   * infoset property.
   * It is computed from the {@link #getBlock() block} and should typically not be modified directly.
   * </p>
   * @see #getStringProhibitedSubstitutions()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Prohibited Substitutions</em>' attribute list.
   * @see org.eclipse.xsd.XSDProhibitedSubstitutions
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_ProhibitedSubstitutions()
   * @model
   * @generated
   */
  EList<XSDProhibitedSubstitutions> getProhibitedSubstitutions();

  /**
   * Returns the String value of the 
   * '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getProhibitedSubstitutions <em>Prohibited Substitutions</em></a>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Prohibited Substitutions</em>' attribute list.
   * @see XSDComplexTypeDefinition#getProhibitedSubstitutions()
   */
  String getStringProhibitedSubstitutions();

  /**
   * Returns the value of the '<em><b>Lexical Final</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDComplexFinal}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDComplexFinal}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">final</a> attribute.
   * </p>
   * @see #getStringLexicalFinal()
   * @see #setStringLexicalFinal(java.lang.String)
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Lexical Final</em>' attribute list.
   * @see org.eclipse.xsd.XSDComplexFinal
   * @see #isSetLexicalFinal()
   * @see #unsetLexicalFinal()
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_LexicalFinal()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDComplexFinal> getLexicalFinal();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetLexicalFinal()
   * @see #getLexicalFinal()
   * @generated
   */
  void unsetLexicalFinal();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Lexical Final</em>' attribute list is set.
   * @see #unsetLexicalFinal()
   * @see #getLexicalFinal()
   * @generated
   */
  boolean isSetLexicalFinal();

  /**
   * Returns the String value of the 
   * '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Lexical Final</em>' attribute list.
   * @see #getLexicalFinal()
   * @see #setStringLexicalFinal(java.lang.String)
   */
  String getStringLexicalFinal();

  /**
   * Sets the String value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getLexicalFinal <em>Lexical Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param lexicalFinal the new value of the '<em>Lexical Final</em>' attribute.
   * @see #getLexicalFinal()
   * @see #getStringLexicalFinal()
   */
  void setStringLexicalFinal(String lexicalFinal);

  /**
   * Returns the value of the '<em><b>Block</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">block</a> attribute.
   * </p>
   * @see #getStringBlock()
   * @see #setStringBlock(java.lang.String)
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' attribute list.
   * @see org.eclipse.xsd.XSDProhibitedSubstitutions
   * @see #isSetBlock()
   * @see #unsetBlock()
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_Block()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDProhibitedSubstitutions> getBlock();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBlock <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetBlock()
   * @see #getBlock()
   * @generated
   */
  void unsetBlock();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBlock <em>Block</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Block</em>' attribute list is set.
   * @see #unsetBlock()
   * @see #getBlock()
   * @generated
   */
  boolean isSetBlock();

  /**
   * Returns the String value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBlock <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Block</em>' attribute list.
   * @see #getBlock()
   * @see #setStringBlock(java.lang.String)
   */
  String getStringBlock();

  /**
   * Sets the String value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBlock <em>Block</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param block the new value of the '<em>Block</em>' attribute.
   * @see #getBlock()
   * @see #getStringBlock()
   */
  void setStringBlock(String block);

  /**
   * Returns the value of the '<em><b>Mixed</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">mixed</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mixed</em>' attribute.
   * @see #isSetMixed()
   * @see #unsetMixed()
   * @see #setMixed(boolean)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_Mixed()
   * @model unsettable="true"
   * @generated
   */
  boolean isMixed();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#isMixed <em>Mixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mixed</em>' attribute.
   * @see #isSetMixed()
   * @see #unsetMixed()
   * @see #isMixed()
   * @generated
   */
  void setMixed(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#isMixed <em>Mixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetMixed()
   * @see #isMixed()
   * @see #setMixed(boolean)
   * @generated
   */
  void unsetMixed();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#isMixed <em>Mixed</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Mixed</em>' attribute is set.
   * @see #unsetMixed()
   * @see #isMixed()
   * @see #setMixed(boolean)
   * @generated
   */
  boolean isSetMixed();

  /**
   * Returns the value of the '<em><b>Content Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the annotation content of a 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexContent">complexContent</a> element or a
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-simpleContent">simpleContent</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content Annotation</em>' containment reference.
   * @see #setContentAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_ContentAnnotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getContentAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentAnnotation <em>Content Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content Annotation</em>' containment reference.
   * @see #getContentAnnotation()
   * @generated
   */
  void setContentAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Base Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-base_type_definition">base type definition</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Type Definition</em>' reference.
   * @see #setBaseTypeDefinition(XSDTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_BaseTypeDefinition()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDTypeDefinition getBaseTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBaseTypeDefinition <em>Base Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base Type Definition</em>' reference.
   * @see #getBaseTypeDefinition()
   * @generated
   */
  void setBaseTypeDefinition(XSDTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the simple type content or particle content of a 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">complexType</a> element.
   * It will be <code>null</code>, an {@link XSDSimpleTypeDefinition}, or an {@link XSDParticle}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference.
   * @see #setContent(XSDComplexTypeContent)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_Content()
   * @model containment="true"
   * @generated
   */
  XSDComplexTypeContent getContent();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContent <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content</em>' containment reference.
   * @see #getContent()
   * @generated
   */
  void setContent(XSDComplexTypeContent value);

  /**
   * Returns the value of the '<em><b>Content Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#content_type">content type</a>
   * infoset property.
   * It is computed from the {@link #getContent() content} and should typically not be modified directly.
   * It will be <code>null</code>, an {@link XSDSimpleTypeDefinition}, or an {@link XSDParticle}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content Type</em>' reference.
   * @see #setContentType(XSDComplexTypeContent)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_ContentType()
   * @model resolveProxies="false"
   * @generated
   */
  XSDComplexTypeContent getContentType();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentType <em>Content Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content Type</em>' reference.
   * @see #getContentType()
   * @generated
   */
  void setContentType(XSDComplexTypeContent value);

  /**
   * Returns the value of the '<em><b>Attribute Uses</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAttributeUse}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-attribute_declarations">attribute uses</a>
   * infoset property.
   * It is computed from the {@link #getAttributeContents() attribute contents} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Uses</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_AttributeUses()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAttributeUse> getAttributeUses();

  /**
   * Returns the value of the '<em><b>Attribute Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAttributeGroupContent}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the attribute contents defined within the body of a
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">complexType</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_AttributeContents()
   * @model containment="true"
   * @generated
   */
  EList<XSDAttributeGroupContent> getAttributeContents();

  /**
   * Returns the value of the '<em><b>Attribute Wildcard</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-attribute_wildcard">attribute wildcard</a>
   * infoset property.
   * It is computed from the {@link #getAttributeWildcardContent() attribute wildcard content} and should typically not be set directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Wildcard</em>' reference.
   * @see #setAttributeWildcard(XSDWildcard)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_AttributeWildcard()
   * @model resolveProxies="false"
   * @generated
   */
  XSDWildcard getAttributeWildcard();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getAttributeWildcard <em>Attribute Wildcard</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute Wildcard</em>' reference.
   * @see #getAttributeWildcard()
   * @generated
   */
  void setAttributeWildcard(XSDWildcard value);

  /**
   * Returns the value of the '<em><b>Attribute Wildcard Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the attribute wildcard defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">complexType</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute Wildcard Content</em>' containment reference.
   * @see #setAttributeWildcardContent(XSDWildcard)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_AttributeWildcardContent()
   * @model containment="true"
   * @generated
   */
  XSDWildcard getAttributeWildcardContent();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getAttributeWildcardContent <em>Attribute Wildcard Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute Wildcard Content</em>' containment reference.
   * @see #getAttributeWildcardContent()
   * @generated
   */
  void setAttributeWildcardContent(XSDWildcard value);

  /**
   * Returns the value of the '<em><b>Root Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This walks the {@link #getBaseTypeDefinition() base type definitions}
   * until it hits that one that has the ur-type definition as it's base type definition.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Type Definition</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_RootTypeDefinition()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDTypeDefinition getRootTypeDefinition();

  /**
   * Returns the value of the '<em><b>Synthetic Particle</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#content_type">content type</a>
   * infoset property, if the rules require a synthesized particle,
   * i.e., if the complex type is a non-empty extension of a non-empty complex base.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synthetic Particle</em>' containment reference.
   * @see #setSyntheticParticle(XSDParticle)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_SyntheticParticle()
   * @model containment="true" transient="true"
   * @generated
   */
  XSDParticle getSyntheticParticle();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getSyntheticParticle <em>Synthetic Particle</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Synthetic Particle</em>' containment reference.
   * @see #getSyntheticParticle()
   * @generated
   */
  void setSyntheticParticle(XSDParticle value);

  /**
   * Returns the value of the '<em><b>Synthetic Wildcard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#ct-attribute_wildcard">attribute wildcard</a>
   * infoset property, if the rules require a synthesized wildcard.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synthetic Wildcard</em>' containment reference.
   * @see #setSyntheticWildcard(XSDWildcard)
   * @see org.eclipse.xsd.XSDPackage#getXSDComplexTypeDefinition_SyntheticWildcard()
   * @model containment="true" transient="true"
   * @generated
   */
  XSDWildcard getSyntheticWildcard();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getSyntheticWildcard <em>Synthetic Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Synthetic Wildcard</em>' containment reference.
   * @see #getSyntheticWildcard()
   * @generated
   */
  void setSyntheticWildcard(XSDWildcard value);

}
