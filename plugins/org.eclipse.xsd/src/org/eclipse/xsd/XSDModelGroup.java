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
 * $Id: XSDModelGroup.java,v 1.6 2007/06/12 15:06:42 emerks Exp $
 */
package org.eclipse.xsd;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#Model_Group"><em><b>Model Group</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDModelGroup#getCompositor <em>Compositor</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDModelGroup#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDModelGroup#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDModelGroup#getParticles <em>Particles</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDModelGroup()
 * @model
 * @generated
 */
public interface XSDModelGroup extends XSDTerm
{
  /**
   * Returns the value of the '<em><b>Compositor</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.xsd.XSDCompositor}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#compositor">compositor</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Compositor</em>' attribute.
   * @see org.eclipse.xsd.XSDCompositor
   * @see #setCompositor(XSDCompositor)
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroup_Compositor()
   * @model
   * @generated
   */
  XSDCompositor getCompositor();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDModelGroup#getCompositor <em>Compositor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Compositor</em>' attribute.
   * @see org.eclipse.xsd.XSDCompositor
   * @see #getCompositor()
   * @generated
   */
  void setCompositor(XSDCompositor value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#amg-annotation">annotation</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XSDAnnotation)
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroup_Annotation()
   * @model containment="true"
   * @generated
   */
  XSDAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDModelGroup#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XSDAnnotation value);

  /**
   * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDParticle}.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the {@link org.eclipse.xsd.XSDParticleContent particle contents} defined within the body of a
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-sequence">sequence</a>, 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-choice">choice</a>,  or
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-all">all</a> element.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' containment reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroup_Contents()
   * @model containment="true"
   * @generated
   */
  EList<XSDParticle> getContents();

  /**
   * Returns the value of the '<em><b>Particles</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDParticle}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#particles">particles</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Particles</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDModelGroup_Particles()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  EList<XSDParticle> getParticles();

}
