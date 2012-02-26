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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;


public class XcoreReferableElementsUnloader implements IReferableElementsUnloader
{
  public void unloadRoot(EObject root)
  {
    List<Pair<EObject, URI>> elementsToUnload = newArrayList();
    for (Iterator<EObject> i = EcoreUtil.getAllProperContents(Collections.singleton(root), false); i.hasNext();)
    {
      EObject element = i.next();
      elementsToUnload.add(Tuples.create(element, EcoreUtil.getURI(element)));
      element.eAdapters().clear();
    }
    for (Pair<EObject, URI> elementToUnload : elementsToUnload)
    {
      ((InternalEObject)elementToUnload.getFirst()).eSetProxyURI(elementToUnload.getSecond());
    }
  }
}
