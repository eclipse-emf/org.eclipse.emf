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

import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDRedefineContent;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Redefine Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class XSDRedefineContentImpl 
  extends XSDSchemaContentImpl 
  implements XSDRedefineContent
{
  public static XSDRedefineContent createRedefineContent(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.ANNOTATION_ELEMENT:
      {
        return XSDAnnotationImpl.createAnnotation(node);
      }
      case XSDConstants.SIMPLETYPE_ELEMENT:
      {
        return XSDSimpleTypeDefinitionImpl.createSimpleTypeDefinition(node);
      }
      case XSDConstants.COMPLEXTYPE_ELEMENT:
      {
        return XSDComplexTypeDefinitionImpl.createComplexTypeDefinition(node);
      }
      case XSDConstants.ATTRIBUTEGROUP_ELEMENT:
      {
        return XSDAttributeGroupDefinitionImpl.createAttributeGroupDefinition(node);
      }
      case XSDConstants.GROUP_ELEMENT:
      {
        return XSDModelGroupDefinitionImpl.createModelGroupDefinition(node);
      }
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDRedefineContentImpl()
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
    return XSDPackage.Literals.XSD_REDEFINE_CONTENT;
  }

} 
