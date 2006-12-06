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
 * $Id: ASTJEnumConstant.java,v 1.1 2006/12/06 03:48:44 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JEnumConstant;


public class ASTJEnumConstant extends ASTJMember<EnumConstantDeclaration> implements JEnumConstant
{
  /**
   * @param enumConstantDeclaration
   */
  public ASTJEnumConstant(EnumConstantDeclaration enumConstantDeclaration)
  {
    super(enumConstantDeclaration);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnumConstant#getArguments()
   */
  @SuppressWarnings("unchecked")
  public String[] getArguments()
  {
    return convertASTNodeListToStringArray(getASTNode().arguments());
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnumConstant#setArguments(java.lang.String[])
   */
  public void setArguments(String[] arguments)
  {
    setListNodeProperty(getASTNode(), arguments, EnumConstantDeclaration.ARGUMENTS_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnumConstant#getBody()
   */
  public String getBody()
  {
    return getFacadeHelper().toString(getASTNode().getAnonymousClassDeclaration());
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnumConstant#setBody(java.lang.String)
   */
  public void setBody(String body)
  {
    setTrackedNodeProperty(
      getASTNode(),
      body,
      EnumConstantDeclaration.ANONYMOUS_CLASS_DECLARATION_PROPERTY,
      ASTNode.ANONYMOUS_CLASS_DECLARATION);
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
    setNodeProperty(getASTNode(), name, EnumConstantDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }
}
