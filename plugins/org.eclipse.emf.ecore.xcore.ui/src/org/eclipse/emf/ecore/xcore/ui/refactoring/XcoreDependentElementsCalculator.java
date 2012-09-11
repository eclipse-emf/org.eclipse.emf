/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XEnumLiteralMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XTypeParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.emf.ecore.xcore.util.XcoreSwitch;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.ui.refactoring.impl.DefaultDependentElementsCalculator;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class XcoreDependentElementsCalculator extends DefaultDependentElementsCalculator
{
  @Inject
  private XcoreMapper mapper;

  @Override
  public List<URI> getDependentElementURIs(EObject eObject, final IProgressMonitor monitor)
  {
    final List<URI> result = super.getDependentElementURIs(eObject, monitor);
    new XcoreSwitch<Boolean>()
    {
      @Override
      public Boolean caseXPackage(XPackage xPackage)
      {
        XPackageMapping mapping = mapper.getMapping(xPackage);
        GenPackage genPackage = mapping.getGenPackage();
        result.addAll(doGetDependentElementURIs(genPackage, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEPackage(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genPackage))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXClass(XClass xClass)
      {
        XClassMapping mapping = mapper.getMapping(xClass);
        GenClass genClass = mapping.getGenClass();
        result.addAll(doGetDependentElementURIs(genClass, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEClass(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genClass))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXDataType(XDataType xDataType)
      {
        XDataTypeMapping mapping = mapper.getMapping(xDataType);
        GenDataType genDataType = mapping.getGenDataType();
        result.addAll(doGetDependentElementURIs(genDataType, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEDataType(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genDataType))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXEnumLiteral(XEnumLiteral xEnumLiteral)
      {
        XEnumLiteralMapping mapping = mapper.getMapping(xEnumLiteral);
        GenEnumLiteral genEnumLiteral = mapping.getGenEnumLiteral();
        result.addAll(doGetDependentElementURIs(genEnumLiteral, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEEnumLiteral(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genEnumLiteral))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXStructuralFeature(XStructuralFeature xStructuralFeature)
      {
        XFeatureMapping mapping = mapper.getMapping(xStructuralFeature);
        GenFeature genFeature = mapping.getGenFeature();
        result.addAll(doGetDependentElementURIs(genFeature, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEStructuralFeature(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genFeature))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXOperation(XOperation xOperation)
      {
        XOperationMapping mapping = mapper.getMapping(xOperation);
        GenOperation genOperation = mapping.getGenOperation();
        result.addAll(doGetDependentElementURIs(genOperation, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEOperation(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genOperation))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXParameter(XParameter xParameter)
      {
        XParameterMapping mapping = mapper.getMapping(xParameter);
        GenParameter genParameter = mapping.getGenParameter();
        result.addAll(doGetDependentElementURIs(genParameter, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEParameter(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genParameter))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXTypeParameter(XTypeParameter xTypeParameter)
      {
        XTypeParameterMapping mapping = mapper.getMapping(xTypeParameter);
        GenTypeParameter genTypeParameter = mapping.getGenTypeParameter();
        result.addAll(doGetDependentElementURIs(genTypeParameter, monitor));
        result.addAll(doGetDependentElementURIs(mapping.getETypeParameter(), monitor));
        for (JvmIdentifiableElement jvmIdentifiableElement : XcoreJvmInferrer.getInferredElements(genTypeParameter))
        {
          result.addAll(doGetDependentElementURIs(jvmIdentifiableElement, monitor));
        }
        return Boolean.FALSE;
      }
    }.doSwitch(eObject);
    return result;
  }

  protected  List<URI> doGetDependentElementURIs(EObject eObject, final IProgressMonitor monitor)
  {
    List<URI> result = Lists.newArrayList();
    if (eObject != null)
    {
      SubMonitor progress = SubMonitor.convert(monitor, 10);
      for (Iterator<EObject> i = EcoreUtil.getAllProperContents(Collections.singleton(eObject), false); !progress.isCanceled() && i.hasNext(); )
      {
        EObject childElement = i.next();
        URI childURI = EcoreUtil.getURI(childElement);
        if (childURI != null)
        {
          result.add(childURI);
        }
      }
      progress.worked(1);
      progress.setWorkRemaining(10);
    }
    return result;
  }
}
