/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.generator;


import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.google.inject.Inject;


public class XcoreCompiler extends XbaseCompiler
{
  private final static String REASSIGNED_THIS_IN_LAMBDA = "!reassigned_this_for_lambda!";

  @Inject
  XcoreMapper mappings;

  @Override
  protected void reassignThisInClosure(final ITreeAppendable b, JvmType rawClosureType)
  {
    boolean registerClosureAsThis = rawClosureType instanceof JvmGenericType;
    boolean isAlreadyInALambda = b.hasObject(REASSIGNED_THIS_IN_LAMBDA);
    if (b.hasObject("this") && !isAlreadyInALambda)
    {
      Object element = b.getObject("this");
      if (element instanceof JvmType)
      {
        final String proposedName = "<%this%>";
        if (!b.hasObject(proposedName))
        {
          b.declareSyntheticVariable(element, proposedName);
          if (b.hasObject("super"))
          {
            Object superElement = b.getObject("super");
            if (superElement instanceof JvmType)
            {
              b.declareSyntheticVariable(superElement, "<%super%>");
            }
          }
        }
      }
      else
      {
        registerClosureAsThis = false;
      }
    }
    if (!isAlreadyInALambda)
    {
      // add a synthetic marker so we don't reassign this and super more than once.
      b.declareSyntheticVariable(REASSIGNED_THIS_IN_LAMBDA, REASSIGNED_THIS_IN_LAMBDA);
    }
    if (registerClosureAsThis)
    {
      b.declareVariable(rawClosureType, "this");
    }
  }
  
  @Override
  protected void serialize(JvmTypeReference type, EObject context, ITreeAppendable appendable, boolean withoutConstraints, boolean paramsToWildcard, boolean paramsToObject, boolean allowPrimitives)
  {
    if (type instanceof JvmParameterizedTypeReference)
    {
      JvmParameterizedTypeReference jvmParameterizedTypeReference = (JvmParameterizedTypeReference)type;
      if (jvmParameterizedTypeReference.getArguments().isEmpty())
      {
        JvmType referencedType = jvmParameterizedTypeReference.getType();
        EObject xcoreElement = mappings.getXcoreElement(referencedType);
        if (xcoreElement instanceof XClass)
        {
          GenClass genClass = (GenClass)mappings.getGen((XClass)xcoreElement);
          if (genClass != null && genClass.isExternalInterface())
          {
            JvmTypeReference superType = IterableExtensions.head(((JvmGenericType)referencedType).getSuperTypes());
            if (superType != null)
            {
              serialize(superType, context, appendable, withoutConstraints, paramsToWildcard, paramsToObject, allowPrimitives);
              return;
            }
          }
        }
      }
    }

    super.serialize(type, context, appendable, withoutConstraints, paramsToWildcard, paramsToObject, allowPrimitives);
  }
}
