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
 * A representation of the model object '<em><b>Repeatable Facet</b></em>'.
 *
 * <p>
 * Both {@link org.eclipse.xsd.XSDPatternFacet pattern} and {@link org.eclipse.xsd.XSDEnumerationFacet enumeration} facets 
 * may be repeated in the concrete syntax and yet they are merged into a single component in the infoset model.
 * As a result, instances of these two facets are synthesized by 
 * {@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectivePatternFacet()} and
 * {@link org.eclipse.xsd.XSDSimpleTypeDefinition#getEffectiveEnumerationFacet()}.
 * </p>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDRepeatableFacet#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDRepeatableFacet()
 * @model abstract="true"
 * @generated
 */
public interface XSDRepeatableFacet extends XSDConstrainingFacet
{
  /**
   * Returns the value of the '<em><b>Annotations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.xsd.XSDAnnotation}.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * enumeration <a href="http://www.w3.org/TR/xmlschema-2/#enumeration-annotation">annotation</a>, or
   * pattern <a href="http://www.w3.org/TR/xmlschema-2/#pattern-annotation">annotation</a>
   * infoset property.
   * It is computed from the concrete {@link #getAnnotation() annotation content} and should typically not be modified directly.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' reference list.
   * @see org.eclipse.xsd.XSDPackage#getXSDRepeatableFacet_Annotations()
   * @model resolveProxies="false"
   * @generated
   */
  EList<XSDAnnotation> getAnnotations();

}
