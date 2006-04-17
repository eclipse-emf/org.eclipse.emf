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
 * $Id: JDOMJMethod.java,v 1.2 2006/04/17 21:41:32 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMethod;

import org.eclipse.emf.codegen.merge.java.facade.JMethod;


/**
 * @since 2.2.0
 */
public class JDOMJMethod extends JDOMJMember implements JMethod
{
  /**
   * @param method
   */
  public JDOMJMethod(IDOMMethod method)
  {
    super(method);
  }

  protected IDOMMethod getIDOMMethod()
  {
    return (IDOMMethod)getIDOMNode();
  }

  public boolean isConstructor()
  {
    return getIDOMMethod().isConstructor();
  }

  public String getBody()
  {
    String body = getIDOMMethod().getBody();
    return getIDOMNode().getNextNode() instanceof IDOMInitializer ?
      splitLastComment(body)[0] :
      body;    
  }
  
  public void setBody(String body)
  {
    getIDOMMethod().setBody(body);
  }

  public String getReturnType()
  {
    return getIDOMMethod().getReturnType();
  }
  
  public void setReturnType(String type)
  {
    getIDOMMethod().setReturnType(type);
  }

  public String[] getParameterNames()
  {
    String[] ret = getIDOMMethod().getParameterNames(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }

  public String[] getParameterTypes()
  {
    String[] ret = getIDOMMethod().getParameterTypes(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }

  public String[] getTypeParameters()
  {
    String[] ret = getIDOMMethod().getTypeParameters(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }
  
  public void setParameters(String[] types, String[] names) throws IllegalArgumentException
  {
    getIDOMMethod().setParameters(types, names);
  }

  public String[] getExceptions()
  {
    String[] ret = getIDOMMethod().getExceptions(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }
  
  public void setExceptions(String[] exceptionTypes)
  {
    getIDOMMethod().setExceptions(exceptionTypes);
  }
  
  public void addException(String exceptionType)
  {
    getIDOMMethod().addException(exceptionType);
  }
  
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
}
