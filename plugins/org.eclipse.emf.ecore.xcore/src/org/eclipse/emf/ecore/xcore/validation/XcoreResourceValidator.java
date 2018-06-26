/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.validation;


import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.service.OperationCanceledManager;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.AbstractInjectableValidator;
import org.eclipse.xtext.validation.CancelableDiagnostician;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.validation.ResourceValidatorImpl;
import org.eclipse.xtext.validation.impl.ConcreteSyntaxEValidator;
import org.eclipse.xtext.xbase.annotations.validation.DerivedStateAwareResourceValidator;

import com.google.common.collect.Maps;
import com.google.inject.Inject;


public class XcoreResourceValidator extends DerivedStateAwareResourceValidator
{
  public static abstract class ValidationAdapter extends AdapterImpl
  {
    public abstract void update(Diagnostic diagnostic);
  }

  private static final Logger log = Logger.getLogger(ResourceValidatorImpl.class);

  @Inject
  private Diagnostician diagnostician;

  @Inject
  private OperationCanceledManager operationCanceledManager;

  @Override
  protected void validate(Resource resource, CheckMode mode, CancelIndicator monitor, IAcceptor<Issue> acceptor)
  {
    // Gather all the diagnostics so we can update any diagnostic decorators.
    ResourceSet resourceSet = resource.getResourceSet();
    BasicDiagnostic resourceSetDiagnostic = new BasicDiagnostic(EObjectValidator.DIAGNOSTIC_SOURCE, 0, null, new Object [] { resourceSet });
    BasicDiagnostic resourceDiagnostic = new BasicDiagnostic(EObjectValidator.DIAGNOSTIC_SOURCE, 0, null, new Object [] { resource });
    resourceSetDiagnostic.add(resourceDiagnostic);

    for (EObject eObject : resource.getContents())
    {
      // Validate all the contents except the inferred Jvm model itself.
      //
      if (monitor.isCanceled())
      {
        return;
      }
      else if (eObject instanceof JvmIdentifiableElement)
      {
        break;
      }
      resourceDiagnostic.add(validateElement(resource, eObject, mode, monitor, acceptor));
    }

    for (Adapter adapter : resourceSet.eAdapters())
    {
      if (adapter instanceof ValidationAdapter)
      {
        ValidationAdapter validationAdapter = (ValidationAdapter)adapter;
        validationAdapter.update(resourceSetDiagnostic);
      }
    }
  }

  protected Diagnostic validateElement(Resource resource, EObject element, final CheckMode mode, final CancelIndicator monitor, IAcceptor<Issue> acceptor)
  {
    try
    {
      Map<Object, Object> options = Maps.newHashMap();
      options.put(CheckMode.KEY, mode);
      options.put(CancelableDiagnostician.CANCEL_INDICATOR, monitor);
      // disable concrete syntax validation, since a semantic model that has been parsed 
      // from the concrete syntax always complies with it - otherwise there are parse errors.
      options.put(ConcreteSyntaxEValidator.DISABLE_CONCRETE_SYNTAX_EVALIDATOR, Boolean.TRUE);
      // see EObjectValidator.getRootEValidator(Map<Object, Object>)
      options.put(EValidator.class, diagnostician);
      if (resource instanceof XtextResource)
      {
        options.put(AbstractInjectableValidator.CURRENT_LANGUAGE_NAME, ((XtextResource)resource).getLanguageName());
      }
      Diagnostic diagnostic = diagnostician.validate(element, options);
      if (!diagnostic.getChildren().isEmpty())
      {
        for (Diagnostic childDiagnostic : diagnostic.getChildren())
        {
          issueFromEValidatorDiagnostic(childDiagnostic, acceptor);
        }
      }
      else
      {
        issueFromEValidatorDiagnostic(diagnostic, acceptor);
      }
      return diagnostic;
    }
    catch (RuntimeException e)
    {
      operationCanceledManager.propagateAsErrorIfCancelException(e);
      log.error(e.getMessage(), e);
      return Diagnostic.OK_INSTANCE;
    }
  }
}
