/**
 * Copyright (c) 2016 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.serializer;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.tokens.CrossReferenceSerializer;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class XcoreCrossReferenceSerializer extends CrossReferenceSerializer
{
  @Inject
  private LinkingHelper linkingHelper;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  private IValueConverterService valueConverter;

  @Inject
  private IQualifiedNameProvider qualifiedNameProvider;

  @Override
  protected String getCrossReferenceNameFromScope(EObject semanticObject, CrossReference crossref, EObject target, IScope scope, Acceptor errors)
  {
    String ruleName = linkingHelper.getRuleNameFrom(crossref);
    List<ISerializationDiagnostic> recordedErrros = null;

    Iterator<IEObjectDescription> elements = scope.getElements(target).iterator();
    QualifiedName qualifiedName = elements.hasNext() ? elements.next().getName() : qualifiedNameProvider.getFullyQualifiedName(target);
    if (qualifiedName != null)
    {
      String unconverted = qualifiedNameConverter.toString(qualifiedName);
      try
      {
        return valueConverter.toString(unconverted, ruleName);
      }
      catch (ValueConverterException e)
      {
        if (errors != null)
        {
          if (recordedErrros == null)
          {
            recordedErrros = Lists.newArrayList();
          }

          recordedErrros.add(diagnostics.getValueConversionExceptionDiagnostic(semanticObject, crossref, unconverted, e));
        }
      }
    }

    if (errors != null)
    {
      if (recordedErrros != null)
      {
        for (ISerializationDiagnostic diag : recordedErrros)
        {
          errors.accept(diag);
        }
      }

      if (qualifiedName == null)
      {
        errors.accept(diagnostics.getNoEObjectDescriptionFoundDiagnostic(semanticObject, crossref, target, scope));
      }
    }

    return null;
  }
}
