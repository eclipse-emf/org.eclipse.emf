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
 * A representation of the model object '<em><b>Schema Directive</b></em>'.
 * It is used to represent aspects common to
 * '{@link org.eclipse.xsd.XSDImport <em>Import</em>}',
 * '{@link org.eclipse.xsd.XSDInclude <em>Include</em>}' and
 * '{@link org.eclipse.xsd.XSDRedefine <em>Redefine</em>}'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDSchemaDirective#getSchemaLocation <em>Schema Location</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDSchemaDirective#getResolvedSchema <em>Resolved Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDSchemaDirective()
 * @model abstract="true"
 * @generated
 */
public interface XSDSchemaDirective extends XSDSchemaContent
{
  /**
   * Returns the value of the '<em><b>Schema Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete attribute represents the value of the
   * import <a href="http://www.w3.org/TR/xmlschema-1/#element-import">schemaLocation</a>,
   * include <a href="http://www.w3.org/TR/xmlschema-1/#element-include">schemaLocation</a>, or
   * redefine <a href="http://www.w3.org/TR/xmlschema-1/#element-redefine">schemaLocation</a> attribute.
   * It is used in conjunction with the {@link org.eclipse.xsd.XSDSchema#getSchemaLocation schema's location},
   * to compute the {@link #getResolvedSchema resolved schema}.
   * </p>
   * @see org.eclipse.xsd.XSDSchema#getSchemaLocation()
   * @see #getResolvedSchema()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Schema Location</em>' attribute.
   * @see #setSchemaLocation(String)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchemaDirective_SchemaLocation()
   * @model
   * @generated
   */
  String getSchemaLocation();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchemaDirective#getSchemaLocation <em>Schema Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Schema Location</em>' attribute.
   * @see #getSchemaLocation()
   * @generated
   */
  void setSchemaLocation(String value);

  /**
   * Returns the value of the '<em><b>Resolved Schema</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the actual schema resolved by the '{@link #getSchemaLocation <em>Schema Location</em>}' attribute.
   * </p>
   * @see #getSchemaLocation()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Resolved Schema</em>' reference.
   * @see #setResolvedSchema(XSDSchema)
   * @see org.eclipse.xsd.XSDPackage#getXSDSchemaDirective_ResolvedSchema()
   * @model resolveProxies="false"
   * @generated
   */
  XSDSchema getResolvedSchema();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDSchemaDirective#getResolvedSchema <em>Resolved Schema</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolved Schema</em>' reference.
   * @see #getResolvedSchema()
   * @generated
   */
  void setResolvedSchema(XSDSchema value);

}
