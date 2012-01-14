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
 * '<a href="http://www.w3.org/TR/xmlschema-2/#rf-facets"><em><b>Constraining Facet</b></em></a>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDConstrainingFacet()
 * @model abstract="true"
 * @generated
 */
public interface XSDConstrainingFacet extends XSDFacet
{
  /**
   * Returns whether the given value satisfies the constraint of this facet.
   * @param value an arbitrary value.
   * @return whether the value satisfies the constraint of this facet.
   */
  boolean isConstraintSatisfied(Object value);
}
