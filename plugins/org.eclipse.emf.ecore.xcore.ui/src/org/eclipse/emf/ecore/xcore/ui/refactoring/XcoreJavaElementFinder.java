/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.ui.XcoreJavaProjectProvider;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeParameterDeclarator;
import org.eclipse.xtext.common.types.util.jdt.JavaElementFinder;

import com.google.inject.Inject;

public class XcoreJavaElementFinder extends JavaElementFinder
{
  @Inject
  private XcoreJavaProjectProvider projectProvider;

  @Override
  protected IJavaElement internalFindElementFor(JvmIdentifiableElement element, boolean isExactMatchOnly)
  {
    if (element != null)
    {
      Resource resource = element.eResource();
      if (resource != null)
      {
        for (IJavaProject javaProject : projectProvider.getJavaProjects(resource))
        {
          Implementation implementation = new Finder(javaProject, isExactMatchOnly);
          IJavaElement result = implementation.doSwitch(element);
          if (result != null)
          {
            return result;
          }
        }
      }
    }
    return null;
  }

  public static class Finder extends Implementation
  {
    public Finder(IJavaProject javaProject, boolean isExactMatchOnly)
    {
      super(javaProject, isExactMatchOnly);
    }

    @Override
    public IJavaElement caseJvmTypeParameter(JvmTypeParameter jvmTypeParameter)
    {
      JvmTypeParameterDeclarator declarator = jvmTypeParameter.getDeclarator();
      IJavaElement javaElement  = this.doSwitch(declarator);
      return
        javaElement instanceof IType ?
          ((IType)javaElement).getTypeParameter(jvmTypeParameter.getName()) :
          javaElement instanceof IMethod ?
            ((IMethod)javaElement).getTypeParameter(jvmTypeParameter.getName()) :
            null;
    }

    @Override
    public IJavaElement caseJvmFormalParameter(JvmFormalParameter jvmFormalParameter)
    {
      IJavaElement javaElement  = this.doSwitch(jvmFormalParameter.eContainer());
      if (javaElement instanceof IMethod)
      {
        String name = jvmFormalParameter.getName();
        for (ILocalVariable parameter : getParameters((IMethod)javaElement))
        {
          if (name.equals(parameter.getElementName()))
          {
            return parameter;
          }
        }
      }
      return null;
    }

    private static final Method GET_PARAMETERS_METHOD;
    static
    {
      Method getParametersMethod = null;
      try
      {
        getParametersMethod = IMethod.class.getMethod("getParameters");
      }
      catch (Throwable exception)
      {
        // Ignore.
      }
      GET_PARAMETERS_METHOD = getParametersMethod;
    }

    private ILocalVariable[] getParameters(IMethod method)
    {
      try
      {
        return (ILocalVariable[])GET_PARAMETERS_METHOD.invoke(method);
      }
      catch (Throwable exception)
      {
        return new ILocalVariable[0];
      }
    }
  }
}
