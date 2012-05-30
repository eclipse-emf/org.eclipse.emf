/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;
import org.eclipse.xtext.ui.editor.findrefs.DefaultReferenceFinder;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

/**
 * This is specialized to filter out duplicates.
 */
public class XcoreReferenceFinder extends DefaultReferenceFinder
{
  @Inject
  public XcoreReferenceFinder(IResourceDescriptions indexData, Registry serviceProviderRegistry)
  {
    super(indexData, serviceProviderRegistry);
  }

  @Override
  public void findReferences
    (Iterable<URI> targetURIs,
     Iterable<URI> sourceResourceURIs,
     ILocalResourceAccess localResourceAccess,
     IAcceptor<IReferenceDescription> referenceAcceptor,
     IProgressMonitor monitor)
  {
    super.findReferences(targetURIs, sourceResourceURIs, localResourceAccess, createFilteringReferenceAcceptor(referenceAcceptor), monitor);
  }

  @Override
  public void findAllReferences(Iterable<URI> targetURIs, ILocalResourceAccess localResourceAccess, IAcceptor<IReferenceDescription> referenceAcceptor, IProgressMonitor monitor)
  {
    super.findAllReferences(targetURIs, localResourceAccess, createFilteringReferenceAcceptor(referenceAcceptor), monitor);
  }

  protected IAcceptor<IReferenceDescription> createFilteringReferenceAcceptor(final IAcceptor<IReferenceDescription> referenceAcceptor)
  {
    final Set<Pair<URI, EReference>> sourceURIEReferencePairs = Sets.newHashSet();
    return
      new IAcceptor<IReferenceDescription>()
      {
        public void accept(IReferenceDescription referenceDescription)
        {
          if (sourceURIEReferencePairs.add(Tuples.pair(referenceDescription.getSourceEObjectUri(), referenceDescription.getEReference())))
          {
            referenceAcceptor.accept(referenceDescription);
          }
        }
      };
  }
}
