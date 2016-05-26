/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.findrefs;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.findReferences.ReferenceFinder;
import org.eclipse.xtext.findReferences.TargetURIs;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

public class XcoreReferenceFinder extends ReferenceFinder
{
  /**
  * @inheritDoc
  * 
  * Xtext 2.10.x
  */
  protected void findLocalReferencesFromElement(Predicate<URI> targetURIs, EObject sourceCandidate, Resource localResource,
      Acceptor acceptor)
  {
    super.findLocalReferencesFromElement(targetURIs, sourceCandidate, localResource, new DuplicateFilter(acceptor));
  }

  @Override
  protected void findReferencesInDescription(TargetURIs targetURIs, IResourceDescription resourceDescription,
      IResourceAccess resourceAccess, Acceptor acceptor, IProgressMonitor monitor)
  {
    super.findReferencesInDescription(targetURIs, resourceDescription, resourceAccess, new DuplicateFilter(acceptor), monitor);
  }

  private static class DuplicateFilter implements Acceptor
  {
    private Acceptor delegate;

    final Set<Pair<URI, EReference>> sourceURIEReferencePairs = Sets.newHashSet();

    public DuplicateFilter(Acceptor delegate)
    {
      this.delegate = delegate;
    }

    public void accept(EObject source, URI sourceURI, EReference eReference, int index, EObject targetOrProxy,
        URI targetURI)
    {
      if (sourceURIEReferencePairs.add(Tuples.pair(sourceURI, eReference)))
      {
        delegate.accept(source, sourceURI, eReference, index, targetOrProxy, targetURI);
      }
    }

    public void accept(IReferenceDescription description)
    {
      if (sourceURIEReferencePairs.add(Tuples.pair(description.getSourceEObjectUri(), description.getEReference())))
      {
        delegate.accept(description);
      }
    }

  }
}
