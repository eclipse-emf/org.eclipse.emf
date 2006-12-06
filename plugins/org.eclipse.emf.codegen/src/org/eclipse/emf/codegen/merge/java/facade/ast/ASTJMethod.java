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
 * $Id: ASTJMethod.java,v 1.3 2006/12/06 03:48:44 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JMethod;


/**
 * Wraps {@link MethodDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJMethod extends ASTJMember<MethodDeclaration> implements JMethod
{
  /**
   * @param methodDeclaration
   */
  public ASTJMethod(MethodDeclaration methodDeclaration)
  {
    super(methodDeclaration);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#isConstructor()
   */
  public boolean isConstructor()
  {
    return getASTNode().isConstructor();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return isConstructor() ? null : ASTFacadeHelper.toString(getASTNode().getName());
  }

  /**
   * In this implementation, new name will not be returned by {@link #getName()}.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getQualifiedName()
   */    
  public void setName(String name)
  {
    setNodeProperty(getASTNode(), name, MethodDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }    
  
  /**
   * Returns original return type of the method.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getReturnType()
   */
  public String getReturnType()
  {
    String type = getFacadeHelper().toString(getASTNode().getReturnType2());
    if (type != null)
    {
      // append extra dimensions since they are not stored in Type object
      for (int i = 0; i < getASTNode().getExtraDimensions(); i++)
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
    setNodeProperty(getASTNode(), 0, MethodDeclaration.EXTRA_DIMENSIONS_PROPERTY);
    setTrackedNodeProperty(getASTNode(), type, MethodDeclaration.RETURN_TYPE2_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getTypeParameters()
   */
  @SuppressWarnings("unchecked")
  public String[] getTypeParameters()
  {
    return convertASTNodeListToStringArray(getASTNode().typeParameters());
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#setTypeParameters(java.lang.String[])
   */
  public void setTypeParameters(String[] typeParameters)
  {
    setListNodeProperty(getASTNode(), typeParameters, MethodDeclaration.TYPE_PARAMETERS_PROPERTY, ASTNode.TYPE_PARAMETER);
  }

  /**
   * Returns the array representation of an original list of parameter names.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getParameterNames()
   */
  public String[] getParameterNames()
  {
    @SuppressWarnings("unchecked")
    List<SingleVariableDeclaration> parameters = getASTNode().parameters();
    
    String[] ret = new String[parameters.size()];
    int j = 0;
    for (SingleVariableDeclaration parameter : parameters)
    {
      ret[j++] = ASTFacadeHelper.toString(parameter.getName());
    }
    return ret;
  }

  /**
   * Returns type erasure of all the types of formal parameters.
   * <p>
   * This method is used to create a method signature, and match methods by signature.
   * <p>
   * Note that this implementation does not expand types into fully qualified names, nor
   * does it replace type parameters defined for a class or a method.
   * 
   * @see ASTFacadeHelper#getTypeErasure(org.eclipse.jdt.core.dom.Type)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getParameterTypes()
   */
  public String[] getParameterTypes()
  {
    @SuppressWarnings("unchecked")
    List<SingleVariableDeclaration> parameters = getASTNode().parameters();
    
    String[] ret = new String[parameters.size()];
    int j = 0;
    for (SingleVariableDeclaration parameter : parameters)
    {
      String type = ASTFacadeHelper.getTypeErasure(parameter.getType());
      // append extra dimensions if any
      for (int i = 0; i < parameter.getExtraDimensions(); i++)
      {
        type += "[]";
      }
      
      // append [] if it is variable arity parameter (@see JLS3 8.4.1, http://java.sun.com/docs/books/jls/third_edition/html/classes.html#300870)
      if (parameter.isVarargs())
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
    @SuppressWarnings("unchecked")
    List<SingleVariableDeclaration> parameters = getASTNode().parameters();

    if (parameters.size() != names.length)
    {
      throw new IllegalArgumentException("Length of names must match number of existing parameters.");
    }

    int i = 0;
    for (SingleVariableDeclaration parameter : parameters)
    {
      setNodeProperty(parameter, names[i++], SingleVariableDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
    }
  }

  /**
   * Returns the array of original exceptions in the <code>throws</code> clause.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getExceptions()
   */
  public String[] getExceptions()
  {
    @SuppressWarnings("unchecked")
    List<Name> exceptions = getASTNode().thrownExceptions();
    
    String[] ret = new String [exceptions.size()];
    int j = 0;
    for (Name exception : exceptions)
    {
      ret[j++] = ASTFacadeHelper.toString(exception);
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
    setListNodeProperty(getASTNode(), exceptionTypes, MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY, ASTNode.SIMPLE_NAME);
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
    addValueToListProperty(getASTNode(), exceptionType, MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY, ASTNode.SIMPLE_NAME);
  }

  /**
   * Returns the original body of the method.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getBody()
   */
  public String getBody()
  {
    return getFacadeHelper().toString(getASTNode().getBody());
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
    setTrackedNodeProperty(getASTNode(), body, MethodDeclaration.BODY_PROPERTY, ASTNode.BLOCK);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#computeQualifiedName()
   */
  @Override
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#getParameters()
   */
  @SuppressWarnings("unchecked")
  public String[] getParameters()
  {
    return convertASTNodeListToStringArray(getASTNode().parameters());
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JMethod#setParameters(java.lang.String[])
   */
  public void setParameters(String[] parameters)
  {
    setListNodeProperty(getASTNode(), parameters, MethodDeclaration.PARAMETERS_PROPERTY, ASTNode.SINGLE_VARIABLE_DECLARATION);
  }  
}
