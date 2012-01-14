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
 * '<a href="http://www.w3.org/TR/xmlschema-1/#Wildcard"><em><b>Wildcards</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDWildcard#getNamespaceConstraintCategory <em>Namespace Constraint Category</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDWildcard#getNamespaceConstraint <em>Namespace Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDWildcard#getProcessContents <em>Process Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDWildcard#getLexicalNamespaceConstraint <em>Lexical Namespace Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDWildcard#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDWildcard#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDWildcard()
 * @model
 * @generated
 */
public interface XSDWildcard extends XSDTerm
{
  /**
   * Returns the value of the '<em><b>Namespace Constraint Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDNamespaceConstraintCategory}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the category of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#namespace_constraint">namespace constraint</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Namespace Constraint Category</em>' attribute.
   * @see org.eclipse.xsd.XSDNamespaceConstraintCategory
   * @see #setNamespaceConstraintCategory(XSDNamespaceConstraintCategory)
   * @see org.eclipse.xsd.XSDPackage#getXSDWildcard_NamespaceConstraintCategory()
   * @model
   * @generated
   */
  XSDNamespaceConstraintCategory getNamespaceConstraintCategory();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDWildcard#getNamespaceConstraintCategory <em>Namespace Constraint Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Namespace Constraint Category</em>' attribute.
   * @see org.eclipse.xsd.XSDNamespaceConstraintCategory
   * @see #getNamespaceConstraintCategory()
   * @generated
   */
  void setNamespaceConstraintCategory(XSDNamespaceConstraintCategory value);

  /**
   * Returns the value of the '<em><b>Namespace Constraint</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the value of the
   * <a href="http://www.w3.org/TR/xmlschema-1/#namespace_constraint">namespace constraint</a>
   * infoset property.
   * It is computed from the {@link #getLexicalNamespaceConstraint() lexical namespace constraint}
   * and should typically not be modified directly.
   * </p>
   * @see #getStringNamespaceConstraint()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Namespace Constraint</em>' attribute list.
   * @see org.eclipse.xsd.XSDPackage#getXSDWildcard_NamespaceConstraint()
   * @model
   * @generated
   */
  EList<String> getNamespaceConstraint();

  /**
   * Returns the String value of the '{@link org.eclipse.xsd.XSDWildcard#getNamespaceConstraint <em>Namespace Constraint</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Namespace Constraint</em>' attribute list.
   * @see #getNamespaceConstraint()
   */
  public String getStringNamespaceConstraint();

  /**
   * Returns the value of the '<em><b>Process Contents</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDProcessContents}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#process_contents">process contents</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Process Contents</em>' attribute.
   * @see org.eclipse.xsd.XSDProcessContents
   * @see #isSetProcessContents()
   * @see #unsetProcessContents()
   * @see #setProcessContents(XSDProcessContents)
   * @see org.eclipse.xsd.XSDPackage#getXSDWildcard_ProcessContents()
   * @model unsettable="true"
   * @generated
   */
  XSDProcessContents getProcessContents();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDWildcard#getProcessContents <em>Process Contents</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Process Contents</em>' attribute.
   * @see org.eclipse.xsd.XSDProcessContents
   * @see #isSetProcessContents()
   * @see #unsetProcessContents()
   * @see #getProcessContents()
   * @generated
   */
  void setProcessContents(XSDProcessContents value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDWildcard#getProcessContents <em>Process Contents</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetProcessContents()
   * @see #getProcessContents()
   * @see #setProcessContents(XSDProcessContents)
   * @generated
   */
  void unsetProcessContents();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDWildcard#getProcessContents <em>Process Contents</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Process Contents</em>' attribute is set.
   * @see #unsetProcessContents()
   * @see #getProcessContents()
   * @see #setProcessContents(XSDProcessContents)
   * @generated
   */
  boolean isSetProcessContents();

  /**
   * Returns the value of the '<em><b>Lexical Namespace Constraint</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * any <a href="http://www.w3.org/TR/xmlschema-1/#element-any">namespace</a> or
   * anyAttribute <a href="http://www.w3.org/TR/xmlschema-1/#element-anyAttribute">namespace</a> attribute.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lexical Namespace Constraint</em>' attribute list.
   * @see #isSetLexicalNamespaceConstraint()
   * @see #unsetLexicalNamespaceConstraint()
   * @see org.eclipse.xsd.XSDPackage#getXSDWildcard_LexicalNamespaceConstraint()
   * @model unsettable="true"
   * @generated
   */
  EList<String> getLexicalNamespaceConstraint();

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDWildcard#getLexicalNamespaceConstraint <em>Lexical Namespace Constraint</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetLexicalNamespaceConstraint()
   * @see #getLexicalNamespaceConstraint()
   * @generated
   */
  void unsetLexicalNamespaceConstraint();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDWildcard#getLexicalNamespaceConstraint <em>Lexical Namespace Constraint</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Lexical Namespace Constraint</em>' attribute list is set.
   * @see #unsetLexicalNamespaceConstraint()
   * @see #getLexicalNamespaceConstraint()
   * @generated
   */
  boolean isSetLexicalNamespaceConstraint();

  /**
   * Returns the String value of the '{@link org.eclipse.xsd.XSDWildcard#getLexicalNamespaceConstraint <em>Lexical Namespace Constraint</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the String value of the '<em>Lexical Namespace Constraint</em>' attribute list.
   * @see #getLexicalNamespaceConstraint()
   * @see #setStringLexicalNamespaceConstraint(java.lang.String)
   */
  public String getStringLexicalNamespaceConstraint();

  /**
   * Sets the String value of the '{@link org.eclipse.xsd.XSDWildcard#getLexicalNamespaceConstraint <em>Lexical Namespace Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param lexicalNamespaceConstraint the new value of the '<em>Lexical Namespace Constraint</em>' attribute.
   * @see #getLexicalNamespaceConstraint()
   * @see #getStringLexicalNamespaceConstraint()
   */
  public void setStringLexicalNamespaceConstraint(String lexicalNamespaceConstraint);


  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the annotation content of an
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-any">any</a> or
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-anyAttribute">anyAttribute</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDWildcard_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDWildcard#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAnnotation}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#w-annotation">annotation</a>
   * infoset property.
   * It is computed from the {@link #getAnnotation annotation} and should typically not modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDWildcard_Annotations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAnnotation> getAnnotations();

  /**
   * Returns whether this wildcard is a <a href="http://www.w3.org/TR/xmlschema-1/#cos-ns-subset">subset</a> of the given wildcard.
   * @param superSetWildcard the other wildcard.
   * @return whether this wildcard is a subset.
   * @see #attributeWildcardUnion
   * @see #attributeWildcardIntersection
   */
  boolean isWildcardSubset(XSDWildcard superSetWildcard);

  /**
   * Returns a wildcard that is the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-aw-union">attribute wildcard union</a> 
   * of this wildcard and the given wildcard.
   * @param otherWildcard another wildcard.
   * @return a wildcard that is the  attribute wildcard union of this wildcard and the given wildcard.
   * @see #isWildcardSubset
   * @see #attributeWildcardIntersection
   */
  XSDWildcard attributeWildcardUnion(XSDWildcard otherWildcard);

  /**
   * Returns a wildcard that is the 
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-aw-intersect">attribute wildcard interection</a> 
   * of this wildcard and the given wildcard.
   * @param otherWildcard another wildcard.
   * @return a wildcard that is the attribute wildcard interection of this wildcard and the given wildcard.
   * @see #isWildcardSubset
   * @see #attributeWildcardUnion
   */
  XSDWildcard attributeWildcardIntersection(XSDWildcard otherWildcard);


  /**
   * Returns whether the namespace is 
   * <a href="http://www.w3.org/TR/xmlschema-1/#cvc-wildcard-namespace">allowed</a>
   * by the wildcard.
   * @return whether the namespace is allowed by the wildcard.
   */
  boolean allows(String namespace);
}
