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
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.util.XcoreSwitch;
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
        result.addAll(doGetDependentElementURIs(mapping.getGenPackage(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEPackage(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXClass(XClass xClass)
      {
        XClassMapping mapping = mapper.getMapping(xClass);
        result.addAll(doGetDependentElementURIs(mapping.getGenClass(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEClass(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getInterfaceType(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getClassType(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXDataType(XDataType xDataType)
      {
        XDataTypeMapping mapping = mapper.getMapping(xDataType);
        result.addAll(doGetDependentElementURIs(mapping.getGenDataType(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEDataType(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getConverter(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getCreator(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXEnumLiteral(XEnumLiteral xEnumLiteral)
      {
        XEnumLiteralMapping mapping = mapper.getMapping(xEnumLiteral);
        result.addAll(doGetDependentElementURIs(mapping.getGenEnumLiteral(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEEnumLiteral(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getField(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXStructuralFeature(XStructuralFeature xStructuralFeature)
      {
        XFeatureMapping mapping = mapper.getMapping(xStructuralFeature);
        result.addAll(doGetDependentElementURIs(mapping.getGenFeature(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEStructuralFeature(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getField(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getGetter(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getSetter(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXOperation(XOperation xOperation)
      {
        XOperationMapping mapping = mapper.getMapping(xOperation);
        result.addAll(doGetDependentElementURIs(mapping.getGenOperation(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEOperation(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getJvmOperation(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXParameter(XParameter xParameter)
      {
        XParameterMapping mapping = mapper.getMapping(xParameter);
        result.addAll(doGetDependentElementURIs(mapping.getGenParameter(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getEParameter(), monitor));
        result.addAll(doGetDependentElementURIs(mapping.getJvmFormalParameter(), monitor));
        return Boolean.FALSE;
      }

      @Override
      public Boolean caseXTypeParameter(XTypeParameter object)
      {
        // TODO
        return super.caseXTypeParameter(object);
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
