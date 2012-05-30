/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.ui.XcoreJavaProjectProvider;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
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
        try
        {
          for (ILocalVariable parameter : ((IMethod)javaElement).getParameters())
          {
            if (name.equals(parameter.getElementName()))
            {
              return parameter;
            }
          }
        }
        catch (JavaModelException exception)
        {
          // Ignore
        }
      }
      return null;
    }
  }
}
