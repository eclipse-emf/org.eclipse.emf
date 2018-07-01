/**
 * Copyright (c) 2013-2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.scoping.batch.IFeatureScopeSession;
import org.eclipse.xtext.xbase.typesystem.internal.LogicalContainerAwareReentrantTypeResolver;
import org.eclipse.xtext.xbase.typesystem.internal.ResolvedTypes;

import com.google.inject.Inject;

public class XcoreLogicalContainerAwareReentrantTypeResolver extends LogicalContainerAwareReentrantTypeResolver
{
  @Inject
  protected XcoreMapper mapper;

  protected Set<EObject> handledExpressions;

  @Override
  public JvmTypeReference getExtendedClass(JvmDeclaredType type)
  {
    JvmTypeReference result = null;
    String identifier = type.getIdentifier();
    boolean ignoreSupperInterfaces = identifier != null && identifier.startsWith("$");
    for (JvmTypeReference candidate: type.getSuperTypes())
    {
      JvmType jvmType = candidate.getType();
      if (jvmType instanceof JvmGenericType)
      {
        if (!((JvmGenericType) jvmType).isInterface())
        {
          return candidate;
        }
        else if (result == null && !ignoreSupperInterfaces)
        {
          result = candidate;
        }
      }
    }
    return result;
  }

  @Override
  protected boolean isHandled(XExpression expression)
  {
    return handledExpressions != null && handledExpressions.contains(expression);
  }

  @Override
  protected Map<JvmIdentifiableElement, ResolvedTypes> prepare(ResolvedTypes resolvedTypes, IFeatureScopeSession featureScopeSession)
  {
    handledExpressions = null;
    Map<JvmIdentifiableElement, ResolvedTypes> result = super.prepare(resolvedTypes, featureScopeSession);
    for (EObject eObject : rootedInstances)
    {
      if (eObject instanceof XBlockExpression)
      {
        if (handledExpressions == null)
        {
          handledExpressions = new HashSet<EObject>();
        }
        handledExpressions.add(eObject);
        for (Iterator<EObject> i = eObject.eAllContents(); i.hasNext(); )
        {
          handledExpressions.add(i.next());
        }
      }
    }
    return result;
  }

  @Override
  protected void clear()
  {
    super.clear();
    handledExpressions = null;
  }
}
