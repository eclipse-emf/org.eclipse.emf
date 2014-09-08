/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.resource;


import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

public class XcoreReferableElementsUnloader implements IReferableElementsUnloader
{
  public void unloadRoot(EObject root)
  {
    List<Pair<EObject, URI>> elementsToUnload = newArrayList();
    Resource resource = root.eResource();
    URI uri = resource.getURI();
    for (TreeIterator<EObject> i = EcoreUtil.getAllProperContents(Collections.singleton(root), false); i.hasNext();)
    {
      EObject element = i.next();
      URI proxyURI = uri.appendFragment(resource.getURIFragment(element));
      elementsToUnload.add(Tuples.create(element, proxyURI));
      element.eAdapters().clear();

      if (element instanceof GenClassifier
          || element instanceof GenAnnotation
          || element instanceof XClassifier
          || element instanceof XAnnotation
          || element instanceof JvmDeclaredType
          || element instanceof JvmTypeReference)
      {
        i.prune();
        clearChildAdapters(element);
      }
    }

    for (Pair<EObject, URI> elementToUnload : elementsToUnload)
    {
      ((InternalEObject) elementToUnload.getFirst()).eSetProxyURI(elementToUnload.getSecond());
    }
  }

  private void clearChildAdapters(EObject eObject)
  {
    for (Iterator<EObject> i = eObject.eAllContents(); i.hasNext(); )
    {
      i.next().eAdapters().clear();
    }
  }
}
