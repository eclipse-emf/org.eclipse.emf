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
 * '<a href="http://www.w3.org/TR/xmlschema-1/#Model_Group_Definition"><em><b>Model Group Definition</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDModelGroupDefinition#isModelGroupDefinitionReference <em>Model Group Definition Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDModelGroupDefinition#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDModelGroupDefinition#getModelGroup <em>Model Group</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDModelGroupDefinition#getResolvedModelGroupDefinition <em>Resolved Model Group Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDModelGroupDefinition()
 * @model
 * @generated
 */
public interface XSDModelGroupDefinition extends XSDRedefinableComponent, XSDParticleContent, XSDRedefineContent
{
  /**
   * Returns the value of the '<em><b>Model Group Definition Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute is equivalent to
   *<pre>
   *  xsdModelGroupDefinition != xsdModelGroupDefinition.{@link #getResolvedModelGroupDefinition getResolvedModelGroupDefinition}()
   *</pre>
   * An infoset feature will never return an instance for which this is the <code>true</code>
   * since this is a concrete attribute that is used to represent a model group definition 
   * with a <a href="http://www.w3.org/TR/xmlschema-1/#element-group">ref</a> attribute.
   * </p>
   * @see #getResolvedModelGroupDefinition()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Model Group Definition Reference</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroupDefinition_ModelGroupDefinitionReference()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isModelGroupDefinitionReference();

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#mg-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroupDefinition_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDModelGroupDefinition#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Model Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#model_group">model group</a>
   * infoset property.
   * (Some browsers do a case-insenstive match on the anchor and get the above link wrong;
   * it should point to a property of a
   * <a href="http://www.w3.org/TR/xmlschema-1/#Model_Group_Definition_details">model group definition</a>.)
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Group</em>' containment reference.
   * @see #setModelGroup(XSDModelGroup)
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroupDefinition_ModelGroup()
   * @model containment="true" required="true"
   * @generated
   */
  XSDModelGroup getModelGroup();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDModelGroupDefinition#getModelGroup <em>Model Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Group</em>' containment reference.
   * @see #getModelGroup()
   * @generated
   */
  void setModelGroup(XSDModelGroup value);

  /**
   * Returns the value of the '<em><b>Resolved Model Group Definition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the model group definition 
   * resolved by the <a href="http://www.w3.org/TR/xmlschema-1/#element-group">ref</a> attribute.
   * Note that as of the writing of this documentation, 
   * the ref attribute doesn't actually appear in the XML Representation Summary,
   * but it is mentioned in the particle interpretation.
   * </p>
   * @see #isModelGroupDefinitionReference()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Resolved Model Group Definition</em>' reference.
   * @see #setResolvedModelGroupDefinition(XSDModelGroupDefinition)
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroupDefinition_ResolvedModelGroupDefinition()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDModelGroupDefinition getResolvedModelGroupDefinition();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDModelGroupDefinition#getResolvedModelGroupDefinition <em>Resolved Model Group Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolved Model Group Definition</em>' reference.
   * @see #getResolvedModelGroupDefinition()
   * @generated
   */
  void setResolvedModelGroupDefinition(XSDModelGroupDefinition value);

} 
