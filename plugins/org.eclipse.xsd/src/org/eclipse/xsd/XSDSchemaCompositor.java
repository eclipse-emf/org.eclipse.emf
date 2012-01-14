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
 * A representation of the model object '<em><b>Schema Compositor</b></em>'.
 * It is used to represent aspects common to
 * '{@link org.eclipse.xsd.XSDInclude <em>Include</em>}' and
 * '{@link org.eclipse.xsd.XSDRedefine <em>Redefine</em>}'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDSchemaCompositor#getIncorporatedSchema <em>Incorporated Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDSchemaCompositor()
 * @model abstract="true"
 * @generated
 */
public interface XSDSchemaCompositor extends XSDSchemaDirective {
  /**
   * Returns the value of the '<em><b>Incorporated Schema</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This reference is {@link org.eclipse.xsd.XSDSchema#getIncorporatedVersions() computed} 
   * from the {@link #getResolvedSchema() resolved schema}
   * and is typically not set directly.
   * </p>
   * @see org.eclipse.xsd.XSDSchema#getIncorporatedVersions
   * @see #getResolvedSchema
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Incorporated Schema</em>' reference.
   * @see #setIncorporatedSchema(XSDSchema)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchemaCompositor_IncorporatedSchema()
   * @model resolveProxies="false"
   * @generated
   */
  XSDSchema getIncorporatedSchema();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchemaCompositor#getIncorporatedSchema <em>Incorporated Schema</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Incorporated Schema</em>' reference.
   * @see #getIncorporatedSchema()
   * @generated
   */
  void setIncorporatedSchema(XSDSchema value);

}
