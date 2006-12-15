/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: ASTJAnnotationTypeMember.java,v 1.2 2006/12/15 20:26:12 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember;

public class ASTJAnnotationTypeMember extends ASTJMember<AnnotationTypeMemberDeclaration> implements JAnnotationTypeMember
{
  /**
   * @param annotationTypeMemberDeclaration
   */
  public ASTJAnnotationTypeMember(AnnotationTypeMemberDeclaration annotationTypeMemberDeclaration)
  {
    super(annotationTypeMemberDeclaration);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember#getDefaultValue()
   */
  public String getDefaultValue()
  {
    String defaultValue = getFacadeHelper().toString(getASTNode().getDefault());
    if (defaultValue == null)
    {
      return "";
    }
    return defaultValue;
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember#getType()
   */
  public String getType()
  {
    return getFacadeHelper().toString(getASTNode().getType());
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember#setDefaultValue(java.lang.String)
   */
  public void setDefaultValue(String defaultValue)
  {
    setTrackedNodeProperty(getASTNode(), defaultValue, AnnotationTypeMemberDeclaration.DEFAULT_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember#setType(java.lang.String)
   */
  public void setType(String type)
  {
    setTrackedNodeProperty(getASTNode(), type, AnnotationTypeMemberDeclaration.TYPE_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  public String getName()
  {
    return name == UNITIALIZED_STRING ? name = ASTFacadeHelper.toString(getASTNode().getName()) : name;
  }
  
  public void setName(String name)
  {
    this.name = name;
    setNodeProperty(getASTNode(), name, AnnotationTypeMemberDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }
}
