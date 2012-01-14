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
package org.eclipse.xsd.impl;


import org.w3c.dom.Node;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraining Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class XSDConstrainingFacetImpl 
  extends XSDFacetImpl 
  implements XSDConstrainingFacet
{
  public static XSDConstrainingFacet createConstrainingFacet(Node node)
  {
    XSDConstrainingFacet result = XSDFixedFacetImpl.createFixedFacet(node);
    if (result == null)
    {
      result = XSDRepeatableFacetImpl.createRepeatableFacet(node);
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDConstrainingFacetImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_CONSTRAINING_FACET;
  }

  @Override
  public void validate()
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    if (!xsdSimpleTypeDefinition.getValidFacets().contains(getFacetName()) && !xsdSimpleTypeDefinition.isCircular())
    {
      XSDSimpleTypeDefinition rootTypeDefinition = xsdSimpleTypeDefinition.getRootTypeDefinition();
      reportConstraintViolation
        (XSDConstants.PART2,
         "cos-applicable-facets",
         getElement(),
         null,
         new Object [] { getFacetName(), rootTypeDefinition.getURI() });
    }
    else
    {
      super.validate();
    }
  }

  public boolean isConstraintSatisfied(Object value)
  {
    return false;
  }
}
