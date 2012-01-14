/**
 * Copyright (c) 2006-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMethod;

import org.eclipse.emf.codegen.merge.java.facade.JMethod;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class JDOMJMethod extends JDOMJMember implements JMethod
{
  /**
   * @param method
   */
  public JDOMJMethod(IDOMMethod method)
  {
    super(method);
  }

  @Override
  protected IDOMMethod getWrappedObject()
  {
    return (IDOMMethod)super.getWrappedObject();
  }

  public boolean isConstructor()
  {
    return getWrappedObject().isConstructor();
  }

  public String getBody()
  {
    String body = getWrappedObject().getBody();
    return getWrappedObject().getNextNode() instanceof IDOMInitializer ?
      splitLastComment(body)[0] :
      body;    
  }
  
  public void setBody(String body)
  {
    getWrappedObject().setBody(body);
  }

  public String getReturnType()
  {
    return getWrappedObject().getReturnType();
  }
  
  public void setReturnType(String type)
  {
    getWrappedObject().setReturnType(type);
  }

  public String[] getParameterNames()
  {
    String[] ret = getWrappedObject().getParameterNames(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }

  public String[] getParameterTypes()
  {
    String[] ret = getWrappedObject().getParameterTypes(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }

  public String[] getFullParameterTypes()
  {
    return getParameterTypes();
  }

  public String[] getTypeParameters()
  {
    String[] ret = getWrappedObject().getTypeParameters(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }
  
  public void setParameterNames(String[] names) throws IllegalArgumentException
  {
    getWrappedObject().setParameters(getParameterTypes(), names);
  }

  public String[] getExceptions()
  {
    String[] ret = getWrappedObject().getExceptions(); 
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }
  
  public void setExceptions(String[] exceptionTypes)
  {
    getWrappedObject().setExceptions(exceptionTypes);
  }
  
  public void addException(String exceptionType)
  {
    getWrappedObject().addException(exceptionType);
  }
  
  @Override
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }

  public void setTypeParameters(String[] typeParameters)
  {
    // not supported in JDOM
  }
  
  public String[] getParameters()
  {
    String[] types = getParameterTypes().clone();
    String[] names = getParameterNames();
    for (int i = 0; i < types.length && i < names.length; i++)
    {
      String name = names[i].replaceAll(" ", "");
      if (name != null)
      {
        name = name.replaceAll(" ", "");
        if (!types[i].matches(name + ",?\\s?\\["))
        {
          types[i] += " " + name;
        }
      }
    }
    return types;
  }
  
  public void setParameters(String[] parameters)
  {
    String[] types = new String[parameters.length];
    String[] names = new String[parameters.length];
    for (int i = 0; i < parameters.length; i++)
    {
      if (parameters[i] != null)
      {
        int splitPosition = parameters[i].lastIndexOf(" ");
        types[i] = parameters[i].substring(0, splitPosition);
        names[i] = parameters[i].substring(splitPosition + 1);
      }
    }
    getWrappedObject().setParameters(types, names);
  }
}
