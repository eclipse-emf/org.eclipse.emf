/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.resource;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreGenModelBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipse.xtext.xbase.jvmmodel.ILogicalContainerProvider;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class XcoreModelAssociator implements IJvmModelAssociations, ILogicalContainerProvider, IDerivedStateComputer
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
      resource.getContents().addAll(jvmInferrer.inferElements(genModel));
      if (!preLinkingPhase)
      {
        xcoreEcoreBuilder.linkInstanceTypes();
        jvmInferrer.inferDeepStructure(genModel);
      }
      resource.getCache().clear(resource);
    }
  }

  public void discardDerivedState(DerivedStateAwareResource resource)
  {
    EList<EObject> contents = resource.getContents();
    int size = contents.size();
    if (size > 1)
    {
      List<EObject> toBeRemoved = Lists.newArrayList();
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

  public XExpression getAssociatedExpression(JvmIdentifiableElement element)
  {
    // TODO
    return null;
  }

  public Set<EObject> getJvmElements(EObject eObject)
  {
    final Set<EObject> result = Sets.newLinkedHashSet();
    if (eObject instanceof XNamedElement)
    {
      GenBase genBase = mapper.getGen((XNamedElement)eObject);
      result.addAll(XcoreJvmInferrer.getInferredElements(genBase));
    }
    return result;
  }

  public Set<EObject> getSourceElements(EObject eObject)
  {
    EObject xcoreElement = eObject.eClass().getEPackage() == TypesPackage.eINSTANCE ? mapper.getXcoreElement(eObject) : null;
    return xcoreElement == null ? Collections.<EObject>emptySet() : Collections.singleton(eObject);
  }

  public EObject getPrimarySourceElement(EObject eObject)
  {
    EObject xcoreElement = mapper.getXcoreElement(eObject);
    return xcoreElement;
  }

  public JvmIdentifiableElement getLogicalContainer(EObject eObject)
  {
    if (eObject instanceof XBlockExpression)
    {
      EObject eContainer = eObject.eContainer();
      EReference eContainmentFeature = eObject.eContainmentFeature();
      if (eContainmentFeature == XcorePackage.Literals.XOPERATION__BODY)
      {
        return mapper.getMapping((XOperation)eContainer).getJvmOperation();
      }
      else if (eContainmentFeature == XcorePackage.Literals.XDATA_TYPE__CREATE_BODY)
      {
        return mapper.getMapping((XDataType)eContainer).getCreator();
      }
      else if (eContainmentFeature == XcorePackage.Literals.XDATA_TYPE__CONVERT_BODY)
      {
        return mapper.getMapping((XDataType)eContainer).getConverter();
      }
      else if (eContainmentFeature == XcorePackage.Literals.XSTRUCTURAL_FEATURE__GET_BODY)
      {
        return mapper.getMapping((XStructuralFeature)eContainer).getGetter();
      }
      else if (eContainmentFeature == XcorePackage.Literals.XSTRUCTURAL_FEATURE__SET_BODY)
      {
        return mapper.getMapping((XStructuralFeature)eContainer).getSetter();
      }
      else if (eContainmentFeature == XcorePackage.Literals.XSTRUCTURAL_FEATURE__IS_SET_BODY)
      {
        return mapper.getMapping((XStructuralFeature)eContainer).getIsSetter();
      }
      else if (eContainmentFeature == XcorePackage.Literals.XSTRUCTURAL_FEATURE__UNSET_BODY)
      {
        return mapper.getMapping((XStructuralFeature)eContainer).getUnsetter();
      }
      else
      {
        return null;
      }
    }
    return null;
  }

  public JvmIdentifiableElement getNearestLogicalContainer(EObject eObject)
  {
    for (EObject eContainer = eObject; eContainer != null; eContainer = eContainer.eContainer())
    {
      if (eContainer instanceof XBlockExpression)
      {
        return getLogicalContainer(eContainer);
      }
    }
    return null;
  }
  
}
