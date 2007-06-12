/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDElementDeclaration.java,v 1.6 2007/06/12 15:06:42 emerks Exp $
 */
package org.eclipse.xsd;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cElement_Declarations"><em><b>Element Declaration</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#isNillable <em>Nillable</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getDisallowedSubstitutions <em>Disallowed Substitutions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getSubstitutionGroupExclusions <em>Substitution Group Exclusions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getLexicalFinal <em>Lexical Final</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getBlock <em>Block</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#isElementDeclarationReference <em>Element Declaration Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#isCircular <em>Circular</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getAnonymousTypeDefinition <em>Anonymous Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getTypeDefinition <em>Type Definition</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getIdentityConstraintDefinitions <em>Identity Constraint Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getResolvedElementDeclaration <em>Resolved Element Declaration</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getSubstitutionGroupAffiliation <em>Substitution Group Affiliation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDElementDeclaration#getSubstitutionGroup <em>Substitution Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration()
 * @model
 * @generated
 */
public interface XSDElementDeclaration extends XSDFeature, XSDSchemaContent, XSDTerm
{
  /**
   * Returns the value of the '<em><b>Nillable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#nillable">nillable</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nillable</em>' attribute.
   * @see #isSetNillable()
   * @see #unsetNillable()
   * @see #setNillable(boolean)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_Nillable()
   * @model unsettable="true"
   * @generated
   */
  boolean isNillable();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#isNillable <em>Nillable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nillable</em>' attribute.
   * @see #isSetNillable()
   * @see #unsetNillable()
   * @see #isNillable()
   * @generated
   */
  void setNillable(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#isNillable <em>Nillable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetNillable()
   * @see #isNillable()
   * @see #setNillable(boolean)
   * @generated
   */
  void unsetNillable();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#isNillable <em>Nillable</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Nillable</em>' attribute is set.
   * @see #unsetNillable()
   * @see #isNillable()
   * @see #setNillable(boolean)
   * @generated
   */
  boolean isSetNillable();

  /**
   * Returns the value of the '<em><b>Disallowed Substitutions</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDDisallowedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDDisallowedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#e-exact">dissallowed substitutions</a>
   * infoset property.
   * It is computed from the {@link #getBlock() block} and should typically not be modified directly.
   * </p>
   * @see #getStringDisallowedSubstitutions()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Disallowed Substitutions</em>' attribute list.
   * @see org.eclipse.xsd.XSDDisallowedSubstitutions
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_DisallowedSubstitutions()
   * @model
   * @generated
   */
  EList<XSDDisallowedSubstitutions> getDisallowedSubstitutions();

  /**
   * Returns the String value of the '{@link #getDisallowedSubstitutions() <em>Disallowed Substitutions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <p>
   * </p>
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Disallowed Substitutions</em>' attribute list.
   * @see #getDisallowedSubstitutions()
   */
  String getStringDisallowedSubstitutions();

  /**
   * Returns the value of the '<em><b>Substitution Group Exclusions</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDSubstitutionGroupExclusions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDSubstitutionGroupExclusions}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#e-final">substitution group exclusions</a>
   * infoset property.
   * It is computed from the {@link #getLexicalFinal() final} and should typically not be modified directly.
   * </p>
   * @see #getStringSubstitutionGroupExclusions()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Substitution Group Exclusions</em>' attribute list.
   * @see org.eclipse.xsd.XSDSubstitutionGroupExclusions
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_SubstitutionGroupExclusions()
   * @model
   * @generated
   */
  EList<XSDSubstitutionGroupExclusions> getSubstitutionGroupExclusions();

  /**
   * Returns the value of the '<em><b>Substitution Group Exclusions</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * @see #getSubstitutionGroupExclusions()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Substitution Group Exclusions</em>' attribute list.
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_SubstitutionGroupExclusions()
   */
  String getStringSubstitutionGroupExclusions();

  /**
   * Returns the value of the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#e-abstract">abstract</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract</em>' attribute.
   * @see #isSetAbstract()
   * @see #unsetAbstract()
   * @see #setAbstract(boolean)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_Abstract()
   * @model unsettable="true"
   * @generated
   */
  boolean isAbstract();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#isAbstract <em>Abstract</em>}' attribute.
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
   * Unsets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#isAbstract <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAbstract()
   * @see #isAbstract()
   * @see #setAbstract(boolean)
   * @generated
   */
  void unsetAbstract();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#isAbstract <em>Abstract</em>}' attribute is set.
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
   * Returns the value of the '<em><b>Lexical Final</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDProhibitedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-element">final</a> attribute.
   * </p>
   * @see #getStringLexicalFinal()
   * @see #setStringLexicalFinal(java.lang.String)
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Lexical Final</em>' attribute list.
   * @see org.eclipse.xsd.XSDProhibitedSubstitutions
   * @see #isSetLexicalFinal()
   * @see #unsetLexicalFinal()
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_LexicalFinal()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDProhibitedSubstitutions> getLexicalFinal();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getLexicalFinal <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetLexicalFinal()
   * @see #getLexicalFinal()
   * @generated
   */
  void unsetLexicalFinal();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getLexicalFinal <em>Lexical Final</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Lexical Final</em>' attribute list is set.
   * @see #unsetLexicalFinal()
   * @see #getLexicalFinal()
   * @generated
   */
  boolean isSetLexicalFinal();

  /**
   * Returns the String value of the '{@link #getLexicalFinal() <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStringLexicalFinal()
   * @see #setStringLexicalFinal(java.lang.String)
   * @return the String value of the '<em>Lexical Final</em>' attribute list.
   */
  String getStringLexicalFinal();

  /**
   * Sets the String value of the '{@link #getLexicalFinal() <em>Lexical Final</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param lexicalFinal the new value of the '<em>Lexical Final</em>' attribute list.
   * @see #getLexicalFinal()
   * @see #getStringLexicalFinal()
   */
  void setStringLexicalFinal(String lexicalFinal);

  /**
   * Returns the value of the '<em><b>Block</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.xsd.XSDDisallowedSubstitutions}.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDDisallowedSubstitutions}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-element">block</a> attribute.
   * </p>
   * @see #getStringBlock()
   * @see #setStringBlock(java.lang.String)
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' attribute list.
   * @see org.eclipse.xsd.XSDDisallowedSubstitutions
   * @see #isSetBlock()
   * @see #unsetBlock()
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_Block()
   * @model unsettable="true"
   * @generated
   */
  EList<XSDDisallowedSubstitutions> getBlock();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getBlock <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetBlock()
   * @see #getBlock()
   * @generated
   */
  void unsetBlock();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getBlock <em>Block</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Block</em>' attribute list is set.
   * @see #unsetBlock()
   * @see #getBlock()
   * @generated
   */
  boolean isSetBlock();

  /**
   * Returns the String value of the '{@link #getBlock() <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' attribute list.
   * @see #getBlock()
   * @see #setStringBlock(java.lang.String)
   */
  String getStringBlock();

  /**
   * Set the String value of the '{@link #getBlock() <em>Block</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param block the new value of the '<em>Block</em>' attribute list.
   * @see #getBlock()
   * @see #getStringBlock()
   */
  void setStringBlock(String block);

  /**
   * Returns the value of the '<em><b>Element Declaration Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute is equivalent to
   *<pre>
   *  xsdElementDeclaration != xsdElementDeclaration.{@link #getResolvedElementDeclaration getResolvedElementDeclaration}()
   *</pre>
   * An infoset feature will never return an instance for which this is the <code>true</code>
   * since this is a concrete attribute that is used to represent an element declaration
   * with a <a href="http://www.w3.org/TR/xmlschema-1/#element-element">ref</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Declaration Reference</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_ElementDeclarationReference()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isElementDeclarationReference();

  /**
   * Returns the value of the '<em><b>Circular</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates that the element declaration is part of an invalid circular substitution group.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Circular</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_Circular()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isCircular();

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#e-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getAnnotation <em>Annotation</em>}' containment reference.
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
   * This concrete reference represents a type definition defined within the body of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-element">element</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Anonymous Type Definition</em>' containment reference.
   * @see #setAnonymousTypeDefinition(XSDTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_AnonymousTypeDefinition()
   * @model containment="true"
   * @generated
   */
  XSDTypeDefinition getAnonymousTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getAnonymousTypeDefinition <em>Anonymous Type Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Anonymous Type Definition</em>' containment reference.
   * @see #getAnonymousTypeDefinition()
   * @generated
   */
  void setAnonymousTypeDefinition(XSDTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Type Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#type_definition">type definition</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Definition</em>' reference.
   * @see #setTypeDefinition(XSDTypeDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_TypeDefinition()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDTypeDefinition getTypeDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getTypeDefinition <em>Type Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Definition</em>' reference.
   * @see #getTypeDefinition()
   * @generated
   */
  void setTypeDefinition(XSDTypeDefinition value);

  /**
   * Returns the value of the '<em><b>Identity Constraint Definitions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDIdentityConstraintDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#identity-constraint_definitions">identity constraint definitions</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identity Constraint Definitions</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_IdentityConstraintDefinitions()
   * @model containment="true"
   * @generated
   */
  EList<XSDIdentityConstraintDefinition> getIdentityConstraintDefinitions();

  /**
   * Returns the value of the '<em><b>Resolved Element Declaration</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the element declaration
   * resolved by the <a href="http://www.w3.org/TR/xmlschema-1/#element-element">ref</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resolved Element Declaration</em>' reference.
   * @see #setResolvedElementDeclaration(XSDElementDeclaration)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_ResolvedElementDeclaration()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDElementDeclaration getResolvedElementDeclaration();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getResolvedElementDeclaration <em>Resolved Element Declaration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolved Element Declaration</em>' reference.
   * @see #getResolvedElementDeclaration()
   * @generated
   */
  void setResolvedElementDeclaration(XSDElementDeclaration value);

  /**
   * Returns the value of the '<em><b>Substitution Group Affiliation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#class_exemplar">substitution group affiliation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Substitution Group Affiliation</em>' reference.
   * @see #setSubstitutionGroupAffiliation(XSDElementDeclaration)
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_SubstitutionGroupAffiliation()
   * @model resolveProxies="false"
   * @generated
   */
  XSDElementDeclaration getSubstitutionGroupAffiliation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDElementDeclaration#getSubstitutionGroupAffiliation <em>Substitution Group Affiliation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Substitution Group Affiliation</em>' reference.
   * @see #getSubstitutionGroupAffiliation()
   * @generated
   */
  void setSubstitutionGroupAffiliation(XSDElementDeclaration value);

  /**
   * Returns the value of the '<em><b>Substitution Group</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDElementDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-equiv-class">substitution group</a>
   * infoset property;
   * actually it isn't really an infoset property, 
   * but it's certainly needed during validation.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Substitution Group</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDElementDeclaration_SubstitutionGroup()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDElementDeclaration> getSubstitutionGroup();

}
