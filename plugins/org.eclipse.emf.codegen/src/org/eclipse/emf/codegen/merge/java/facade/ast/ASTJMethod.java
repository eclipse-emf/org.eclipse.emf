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
 * $Id: ASTJMethod.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;


/**
 * Wraps {@link MethodDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJMethod extends ASTJMember implements JMethod
{
  /**
   * @param methodDeclaration
   */
  public ASTJMethod(MethodDeclaration methodDeclaration)
  {
    super(methodDeclaration);
  }

  /**
   * @return method declaration wrapped by this node
   */
  protected MethodDeclaration getASTMethodDeclaration()
  {
    return (MethodDeclaration)getASTBodyDeclaration();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#isConstructor()
   */
  public boolean isConstructor()
  {
    return getASTMethodDeclaration().isConstructor();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return isConstructor() ? null : ASTFacadeHelper.toString(getASTMethodDeclaration().getName());
  }

  /**
   * Returns original return type of the method.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getReturnType()
   */
  public String getReturnType()
  {
    String type = facadeHelper.toString(getASTMethodDeclaration().getReturnType2());
    if (type != null)
    {
      // append extra dimensions since they are not stored in Type object
      for (int i = 0; i < getASTMethodDeclaration().getExtraDimensions(); i++)
      {
        type += "[]";
      }
    }
    return type;
  }

  /**
   * Sets the return type.
   * <p>
   * Note that <code>getReturnType()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#setReturnType(java.lang.Object)
   */
  public void setReturnType(String type)
  {
    setNodeProperty(getASTMethodDeclaration(), 0, MethodDeclaration.EXTRA_DIMENSIONS_PROPERTY);
    setTrackedNodeProperty(getASTMethodDeclaration(), type, MethodDeclaration.RETURN_TYPE2_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getTypeParameters()
   */
  public String[] getTypeParameters()
  {
    return EMPTY_STRING_ARRAY;
  }

  /**
   * Returns the array representation of an original list of parameter names.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getParameterNames()
   */
  public String[] getParameterNames()
  {
    List<?> parameters = getASTMethodDeclaration().parameters();
    String[] ret = new String[parameters.size()];
    int j = 0;
    for (Iterator<?> i = parameters.iterator(); i.hasNext();)
    {
      SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration)i.next();
      ret[j++] = ASTFacadeHelper.toString(singleVariableDeclaration.getName());
    }
    return ret;
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getParameterTypes()
   */
  public String[] getParameterTypes()
  {
    List<?> parameters = getASTMethodDeclaration().parameters();
    String[] ret = new String[parameters.size()];
    int j = 0;
    for (Iterator<?> iter = parameters.iterator(); iter.hasNext();)
    {
      SingleVariableDeclaration declaration = (SingleVariableDeclaration)iter.next();
      String type = ASTFacadeHelper.toString(declaration.getType());
      // append extra dimensions if any
      for (int i = 0; i < declaration.getExtraDimensions(); i++)
      {
        type += "[]";
      }
      ret[j++] = type;
    }
    return ret;
  }

  /**
   * Sets the names of parameters.
   * <p>
   * Note that <code>getParameterNames()</code> will not return the new value.
   *  
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#setParameterNames(java.lang.Object[])
   */
  public void setParameterNames(String[] names)
  {
    List<?> parameters = getASTMethodDeclaration().parameters();

    if (parameters.size() != names.length)
    {
      throw new IllegalArgumentException("Length of names must match number of existing parameters.");
    }

    int i = 0;
    for (Iterator<?> iter = parameters.iterator(); iter.hasNext(); i++)
    {
      setNodeProperty((ASTNode)iter.next(), names[i], SingleVariableDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
    }
  }

  /**
   * Returns the array of original exceptions in the <code>throws</code> clause.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getExceptions()
   */
  public String[] getExceptions()
  {
    List<?> exceptions = getASTMethodDeclaration().thrownExceptions();
    String[] ret = new String [exceptions.size()];
    int j = 0;
    for (Iterator<?> i = exceptions.iterator(); i.hasNext();)
    {
      Name name = (Name)i.next();
      ret[j++] = ASTFacadeHelper.toString(name);
    }
    return ret;
  }

  /**
   * Sets exceptions in the <code>throws</code> clause.
   * <p>
   * Note that <code>getExceptions()</code> will not return the new value. 
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#setExceptions(java.lang.String[])
   */
  public void setExceptions(String[] exceptionTypes)
  {
    setListNodeProperty(getASTMethodDeclaration(), exceptionTypes, MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  /**
   * Adds exception to the <code>throws</code> clause.
   * <p>
   * Note that <code>getExceptions()</code> will not include the new value. 
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#addException(java.lang.String)
   */
  public void addException(String exceptionType)
  {
    addValueToListProperty(getASTMethodDeclaration(), exceptionType, MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  /**
   * Returns the original body of the method.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getBody()
   */
  public String getBody()
  {
    return facadeHelper.toString(getASTMethodDeclaration().getBody());
  }

  /**
   * Sets the body of the method.
   * <p>
   * Note that <code>getBody()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#setBody(java.lang.String)
   */
  public void setBody(String body)
  {
    setTrackedNodeProperty(getASTMethodDeclaration(), body, MethodDeclaration.BODY_PROPERTY, ASTNode.BLOCK);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#computeQualifiedName()
   */
  @Override
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }  
}
