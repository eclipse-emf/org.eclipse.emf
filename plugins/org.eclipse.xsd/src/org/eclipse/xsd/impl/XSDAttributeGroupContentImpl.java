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

import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Group Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class XSDAttributeGroupContentImpl 
  extends XSDConcreteComponentImpl 
  implements XSDAttributeGroupContent
{

  public static XSDAttributeGroupContent createAttributeGroupContent(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.ATTRIBUTE_ELEMENT:
      {
        return XSDAttributeUseImpl.createAttributeUse(node);
      }
      case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
      {
        return XSDAttributeGroupDefinitionImpl.createAttributeGroupDefinition(node);
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDAttributeGroupContentImpl()
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
    return XSDPackage.Literals.XSD_ATTRIBUTE_GROUP_CONTENT;
  }

} 
