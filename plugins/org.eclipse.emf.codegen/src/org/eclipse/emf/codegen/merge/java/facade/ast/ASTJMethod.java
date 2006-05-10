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
 * $Id: ASTJMethod.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JMethod;

/**
 * @since 2.2.0
 */
public class ASTJMethod extends ASTJMember implements JMethod
{
  public ASTJMethod(MethodDeclaration methodDeclaration)
  {
    super(methodDeclaration);
  }
  
  protected MethodDeclaration getASTMethodDeclaration()
  {
    return (MethodDeclaration)getASTBodyDeclaration();
  }

  public boolean isConstructor()
  {
    return getASTMethodDeclaration().isConstructor();
  }
  
  public String getName()
  {
    return isConstructor() ? null : ASTFacadeHelper.toString(getASTMethodDeclaration().getName());
  }
  
  public String getReturnType()
  {
    return ASTFacadeHelper.toString(getASTMethodDeclaration().getReturnType2());
  }

  public void setReturnType(String type)
  {
  }

  public String[] getTypeParameters()
  {
    return EMPTY_STRING_ARRAY;
  }

  public String[] getParameterNames()
  {
    List parameters = getASTMethodDeclaration().parameters();
    if (parameters.isEmpty())
    {
      return EMPTY_STRING_ARRAY; 
    }
    else
    {
      String[] ret = new String[parameters.size()];
      int j=0;
      for (Iterator i = parameters.iterator(); i.hasNext();)
      {
        SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration)i.next();
        ret[j++] = ASTFacadeHelper.toString(singleVariableDeclaration.getName());
      }
      return ret;
    }
  }

  public String[] getParameterTypes()
  {
    List parameters = getASTMethodDeclaration().parameters();
    if (parameters.isEmpty())
    {
      return EMPTY_STRING_ARRAY; 
    }
    else
    {
      String[] ret = new String[parameters.size()];
      int j=0;
      for (Iterator i = parameters.iterator(); i.hasNext();)
      {
        SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration)i.next();
        ret[j++] = ASTFacadeHelper.toString(singleVariableDeclaration.getType());
      }
      return ret;
    }
  }

  public void setParameters(String[] types, String[] names) throws IllegalArgumentException
  {
  }

  public String[] getExceptions()
  {
    List exceptions = getASTMethodDeclaration().thrownExceptions();
    if (exceptions.isEmpty())
    {
      return EMPTY_STRING_ARRAY; 
    }
    else
    {
      String[] ret = new String[exceptions.size()];
      int j=0;
      for (Iterator i = exceptions.iterator(); i.hasNext();)
      {
        ret[j++] = ASTFacadeHelper.toString((Name)i.next());
      }
      return ret;
    }
  }

  public void setExceptions(String[] exceptionTypes)
  {
  }

  public void addException(String exceptionType)
  {
  }

  public String getBody()
  {
    return null;
  }

  public void setBody(String body)
  {
  }
  
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }  
}
