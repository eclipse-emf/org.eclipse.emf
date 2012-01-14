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
 * A representation of the model object '<em><b>Type Definition</b></em>'.
 * It is used to represent aspects common to
 * '{@link org.eclipse.xsd.XSDSimpleTypeDefinition <em>Simple Type Definitions</em>}' and
 * '{@link org.eclipse.xsd.XSDComplexTypeDefinition <em>Complex Type Definitions</em>}'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getDerivationAnnotation <em>Derivation Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getRootType <em>Root Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getBaseType <em>Base Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getSimpleType <em>Simple Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDTypeDefinition#getComplexType <em>Complex Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition()
 * @model abstract="true"
 * @generated
 */
public interface XSDTypeDefinition extends XSDRedefinableComponent, XSDRedefineContent
{
  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the direct annotation content of a
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-complexType">complexType</a> element or a
   * <a href="http://www.w3.org/TR/xmlschema-2/#element-simpleType">simpleType</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDTypeDefinition#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Derivation Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the direct annotation content of a
   * complex content <a href="http://www.w3.org/TR/xmlschema-1/#element-complexContent::extension">extension</a>,
   * complex content <a href="http://www.w3.org/TR/xmlschema-1/#element-complexContent::restriction">restriction</a>,
   * simple content <a href="http://www.w3.org/TR/xmlschema-1/#element-simpleContent::extension">extension</a>,
   * simple content <a href="http://www.w3.org/TR/xmlschema-1/#element-simpleContent::restriction">restriction</a>,
   * simple type <a href="http://www.w3.org/TR/xmlschema-2/#element-restriction">restriction</a>,
   * simple type <a href="http://www.w3.org/TR/xmlschema-2/#element-list">list</a>, or
   * simple type <a href="http://www.w3.org/TR/xmlschema-2/#element-union">union</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Derivation Annotation</em>' containment reference.
   * @see #setDerivationAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_DerivationAnnotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getDerivationAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDTypeDefinition#getDerivationAnnotation <em>Derivation Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Derivation Annotation</em>' containment reference.
   * @see #getDerivationAnnotation()
   * @generated
   */
  void setDerivationAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAnnotation}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the 
   * complex type definition <a href="http://www.w3.org/TR/xmlschema-1/#ct-annotations">annotation</a> or
   * simple type definition <a href="http://www.w3.org/TR/xmlschema-2/#defn-annotation">annotation</a>
   * infoset property.
   * It is computed from the 
   * {@link #getAnnotation() annotation}, 
   * {@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentAnnotation content annotation}, 
   * {@link #getAnnotation() derivationAnnotation}
   * and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_Annotations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAnnotation> getAnnotations();

  /**
   * Returns the value of the '<em><b>Root Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This walks the {@link #getBaseType() base types}
   * until it hits that one that has the ur-type definition as it's base type.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Type</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_RootType()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDTypeDefinition getRootType();

  /**
   * Returns the value of the '<em><b>Base Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the same result as either 
   * the simple '{@link org.eclipse.xsd.XSDSimpleTypeDefinition#getBaseTypeDefinition() <em>Base Type Definition</em>}' reference or
   * the complex '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBaseTypeDefinition() <em>Base Type Definition</em>}' reference.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Type</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_BaseType()
   * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDTypeDefinition getBaseType();

  /**
   * Returns the value of the '<em><b>Simple Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents either 
   * the '{@link org.eclipse.xsd.XSDSimpleTypeDefinition <em>Simple Type Definition</em>}' itself or
   * the complex '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBaseTypeDefinition() <em>Content Type</em>}' reference,
   * if it is {@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentTypeCategory() simple}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simple Type</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_SimpleType()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDSimpleTypeDefinition getSimpleType();

  /**
   * Returns the value of the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents 
   * the complex '{@link org.eclipse.xsd.XSDComplexTypeDefinition#getBaseTypeDefinition() <em>Content Type</em>}' reference,
   * if it is {@link org.eclipse.xsd.XSDComplexTypeDefinition#getContentTypeCategory() complex},
   * i.e., if it is a '{@link org.eclipse.xsd.XSDParticle <em>Particle</em>}'.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Complex Type</em>' reference.
   * @see org.eclipse.xsd.XSDPackage#getXSDTypeDefinition_ComplexType()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  XSDParticle getComplexType();

  /**
   * Returns the first type between this and the given one, that derives from it's base in a way not permitted
   * as constrained by Type Derivation OK 
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-ct-derived-ok">Complex</a> and
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-st-derived-ok">Simple</a>.
   * The result is <code>null</code> if the derivation is valid;
   * the result is the {@link org.eclipse.xsd.util.XSDConstants#isURType UR-type}, 
   * if this type is not related to the given type;
   * otherwise, the result is the base with the blocked derivation method.
   * @return the first type between this and the given one, that derives from it's base in a way not permitted.
   */
  XSDTypeDefinition getBadTypeDerivation(XSDTypeDefinition xsdTypeDefinition, boolean extension, boolean restriction);
}
