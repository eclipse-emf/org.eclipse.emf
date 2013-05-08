/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.scoping.XcoreScopeProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsBatchScopeProvider;

import com.google.inject.Inject;


public class XcoreBatchScopeProvider extends XbaseWithAnnotationsBatchScopeProvider
{
  @Inject
  private XcoreMapper mapper;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  
  @Override
  public IScope getScope(final EObject context, EReference reference)
  {
    if (reference == XcorePackage.Literals.XREFERENCE__OPPOSITE)
    {
      return new XcoreScopeProvider.OppositeScope(qualifiedNameConverter, IScope.NULLSCOPE, false, context);
    }
    else if (reference == XcorePackage.Literals.XREFERENCE__KEYS)
    {
      return new XcoreScopeProvider.KeyScope(mapper, qualifiedNameConverter, IScope.NULLSCOPE, false, context);
    }
    else
    {
      IScope scope = super.getScope(context, reference);
      return
        reference == XcorePackage.Literals.XGENERIC_TYPE__TYPE ?
          new XcoreScopeProvider.TypeParameterScope(mapper, qualifiedNameConverter, scope, false, context) :
          scope;
    }
  }
}
