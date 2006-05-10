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
 * $Id: ASTJField.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import org.eclipse.emf.codegen.merge.java.facade.JField;

/**
 * @since 2.2.0
 */
public class ASTJField extends ASTJMember implements JField
{
  protected static final Pattern CLEAR_INITIALIZER_MULTI_LINE = 
    Pattern.compile("\\s*;(\\s*//.*)?$", Pattern.MULTILINE);
  
  public ASTJField(FieldDeclaration fieldDeclaration)
  {
    super(fieldDeclaration);
  }

  protected FieldDeclaration getASTFieldDeclaration()
  {
    return (FieldDeclaration)getASTBodyDeclaration();
  }
  
  public String getName()
  {
    List fragments = getASTFieldDeclaration().fragments();
    if (fragments.size() == 1)
    {
      VariableDeclarationFragment fragment = (VariableDeclarationFragment)fragments.get(0);
      return ASTFacadeHelper.toString(fragment.getName());
    }
    return null;
  }  

  public String getInitializer()
  {
    String contents = getContents();
    if (contents != null)
    {
      int index = contents.indexOf('=')+1;
      if (index > 0 && index < contents.length())
      {
        String initializer = contents.substring(index);
        String[] ret = CLEAR_INITIALIZER_MULTI_LINE.split(initializer);
        return ret[0];
      }
      else
      {
        return null;
      }
    }
    return getInitializerFromAST();
  }
  
  protected String getInitializerFromAST()
  {
    List fragments = getASTFieldDeclaration().fragments();
    if (fragments.size() == 1)
    {
      VariableDeclarationFragment fragment = (VariableDeclarationFragment)fragments.get(0);
      Expression expression = fragment.getInitializer();
      return ASTFacadeHelper.toString(expression);
    }
    return null;
  }

  public void setInitializer(String initializer)
  {
  }

  public String getType()
  {
    return ASTFacadeHelper.toString(getASTFieldDeclaration().getType());
  }

  public void setType(String typeName)
  {
  }
}
