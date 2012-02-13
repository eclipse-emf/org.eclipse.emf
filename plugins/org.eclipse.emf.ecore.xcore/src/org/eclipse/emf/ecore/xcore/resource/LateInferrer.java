/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.resource;


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreGenModelBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;

import com.google.inject.Inject;
import com.google.inject.Provider;

import static com.google.common.collect.Lists.*;


public class LateInferrer implements IDerivedStateComputer
{
  @Inject
  private XcoreJvmInferrer jvmInferrer;

  @Inject
  private XcoreGenModelBuilder genModelBuilder;

  @Inject
  private Provider<XcoreEcoreBuilder> xcoreEcoreBuilderProvider;

  @Inject
  private XcoreMapper mapper;

  @Inject
  private IReferableElementsUnloader unloader;

  public void installDerivedState(DerivedStateAwareResource resource, boolean preLinkingPhase)
  {
    if (resource.getParseResult() != null && resource.getParseResult().getRootASTElement() instanceof XPackage)
    {
      XPackage model = (XPackage)resource.getParseResult().getRootASTElement();
      XcoreEcoreBuilder xcoreEcoreBuilder = xcoreEcoreBuilderProvider.get();
      EPackage ePackage = xcoreEcoreBuilder.getEPackage(model);
      resource.getContents().add(ePackage);
      GenModel genModel = genModelBuilder.getGenModel(model);
      genModel.setCanGenerate(true);
      if (!preLinkingPhase)
      {
        xcoreEcoreBuilder.link();
        genModelBuilder.initializeUsedGenPackages(genModel);
      }
      resource.getContents().addAll(jvmInferrer.getDeclaredTypes(model));
      resource.getCache().clear(resource);
    }
  }

  public void discardDerivedState(DerivedStateAwareResource resource)
  {
    EList<EObject> contents = resource.getContents();
    int size = contents.size();
    if (size > 1)
    {
      List<EObject> toBeRemoved = newArrayList();
      for (Iterator<EObject> i = contents.iterator(); i.hasNext();)
      {
        EObject eObject = i.next();
        if (eObject instanceof XPackage)
        {
          mapper.unsetMapping((XPackage)eObject);
        }
        else
        {
          unloader.unloadRoot(eObject);
          toBeRemoved.add(eObject);
        }
      }
      contents.removeAll(toBeRemoved);
    }
  }

}
