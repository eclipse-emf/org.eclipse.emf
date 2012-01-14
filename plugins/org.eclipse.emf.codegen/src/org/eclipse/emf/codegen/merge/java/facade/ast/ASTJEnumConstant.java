/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JEnumConstant;


public class ASTJEnumConstant extends ASTJMember<EnumConstantDeclaration> implements JEnumConstant
{
  /**
   * Cached array of arguments
   * @see #getArguments()
   * @see #setArguments(String[])
   */
  protected String[] arguments = EMPTY_STRING_ARRAY;
  
  /**
   * Cached body of the enum constant.
   * @see #getBody()
   * @see #setBody(String)
   */
  protected String body = UNITIALIZED_STRING;
  
  /**
   * @param enumConstantDeclaration
   */
  public ASTJEnumConstant(EnumConstantDeclaration enumConstantDeclaration)
  {
    super(enumConstantDeclaration);
  }
  
  @Override
  public void dispose()
  {
    arguments = null;
    body = null;
    super.dispose();
  }

  @SuppressWarnings("unchecked")
  public String[] getArguments()
  {
    if (arguments == EMPTY_STRING_ARRAY)
    {
      arguments = convertASTNodeListToStringArray(getASTNode().arguments());
    }
    return arguments;
  }

  public void setArguments(String[] arguments)
  {
    this.arguments = arguments;
    setListNodeProperty(getASTNode(), arguments, EnumConstantDeclaration.ARGUMENTS_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  public String getBody()
  {
    if (body == UNITIALIZED_STRING)
    {
      body = getFacadeHelper().toString(getASTNode().getAnonymousClassDeclaration());
    }
    return body;
  }

  public void setBody(String body)
  {
    this.body = body;
    setTrackedNodeProperty(
      getASTNode(),
      body,
      EnumConstantDeclaration.ANONYMOUS_CLASS_DECLARATION_PROPERTY,
      ASTNode.ANONYMOUS_CLASS_DECLARATION);
  }

  public String getName()
  {
    if (name == UNITIALIZED_STRING)
    {
      name = ASTFacadeHelper.toString(getASTNode().getName());
    }
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
    setNodeProperty(getASTNode(), name, EnumConstantDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }
}
