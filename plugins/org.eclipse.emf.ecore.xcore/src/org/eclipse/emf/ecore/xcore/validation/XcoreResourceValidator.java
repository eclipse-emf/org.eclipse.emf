/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.annotations.validation.DerivedStateAwareResourceValidator;

public class XcoreResourceValidator extends DerivedStateAwareResourceValidator
{
  @Override
  protected void validate(Resource resource, CheckMode mode, CancelIndicator monitor, IAcceptor<Issue> acceptor) 
  {
    for (EObject eObject : resource.getContents()) 
    {
      // Validate all the contents except the inferred Jvm model itself.
      //
      if (monitor.isCanceled() || eObject instanceof JvmIdentifiableElement)
      {
        return;
      }
      validate(resource, eObject, mode, monitor, acceptor);
    }
  }
}
