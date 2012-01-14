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
 * A representation of the model object '<em><b>Redefinable Component</b></em>'.
 * As expected,
 * a redefinable components is one that can be 
 * <a href="http://www.w3.org/TR/xmlschema-1/#modify-schema">redefined</a>.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDRedefinableComponent#isCircular <em>Circular</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDRedefinableComponent()
 * @model abstract="true"
 * @generated
 */
public interface XSDRedefinableComponent extends XSDNamedComponent, XSDRedefineContent {
  /**
   * Returns the value of the '<em><b>Circular</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This indicates whether the definition is circular, 
   * e.g., circular base, item, or member types reference, 
   * circular attribute group definition reference,
   * or circular model group definition reference.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Circular</em>' attribute.
   * @see org.eclipse.xsd.XSDPackage#getXSDRedefinableComponent_Circular()
   * @model changeable="false" volatile="true"
   * @generated
   */
  boolean isCircular();

} 
