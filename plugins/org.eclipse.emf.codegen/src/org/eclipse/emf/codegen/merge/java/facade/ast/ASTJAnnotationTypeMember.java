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
 * $Id: ASTJAnnotationTypeMember.java,v 1.1 2006/12/06 03:48:44 marcelop Exp $
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

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return ASTFacadeHelper.toString(getASTNode().getName());
  }
  
  /**
   * In this implementation, new name will not be returned by {@link #getName()}.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getQualifiedName()
   */    
  public void setName(String name)
  {
    setNodeProperty(getASTNode(), name, AnnotationTypeMemberDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }
}
