/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import java.util.ListIterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;


public class XcoreMapper
{
  public XDataType getXDataType(EObject eObject)
  {
    return (XDataType)getXcoreElement(eObject);
  }

  public XEnum getXEnum(EObject eObject)
  {
    return (XEnum)getXcoreElement(eObject);
  }

  public XEnumLiteral getXEnumLiteral(EObject eObject)
  {
    return (XEnumLiteral)getXcoreElement(eObject);
  }

  public XClass getXClass(EObject eObject)
  {
    return (XClass)getXcoreElement(eObject);
  }

  public XOperation getXOperation(EObject eObject)
  {
    return (XOperation)getXcoreElement(eObject);
  }

  public XParameter getXParameter(EObject eObject)
  {
    return (XParameter)getXcoreElement(eObject);
  }

  public XStructuralFeature getXFeature(EObject eObject)
  {
    return (XStructuralFeature)getXcoreElement(eObject);
  }

  public XPackageMapping getMapping(XPackage xPackage)
  {
    return lazyCreateMapping(xPackage, XPackageMapping.class);
  }

  public void unsetMapping(XPackage xPackage)
  {
    remove(xPackage.eAdapters(), XPackageMapping.class);
    for (XClassifier xClassifier : xPackage.getClassifiers())
    {
      if (xClassifier instanceof XClass)
      {
        XClass xClass = (XClass)xClassifier;
        remove(xClass.eAdapters(), XClassMapping.class);
        for (XMember xMember : xClass.getMembers())
        {
          if (xMember instanceof XStructuralFeature)
          {
            XStructuralFeature xStructuralFeature = (XStructuralFeature)xMember;
            remove(xStructuralFeature.eAdapters(), XFeatureMapping.class);
          }
          else if (xMember instanceof XOperation)
          {
            XOperation xOperation = (XOperation)xMember;
            remove(xOperation.eAdapters(), XOperationMapping.class);
          }
        }
      }
      else if (xClassifier instanceof XDataType)
      {
        XDataType xDataType = (XDataType)xClassifier;
        remove(xDataType.eAdapters(), XDataTypeMapping.class);
      }
    }
  }

  private void remove(EList<Adapter> adapters, Class<?> type)
  {
    for (ListIterator<Adapter> i = adapters.listIterator(); i.hasNext();)
    {
      Adapter adapter = i.next();
      if (adapter.isAdapterForType(type))
      {
        i.remove();
        break;
      }
    }
  }

  public XClassMapping getMapping(XClass xClass)
  {
    return lazyCreateMapping(xClass, XClassMapping.class);
  }

  public XFeatureMapping getMapping(XStructuralFeature xStructuralFeature)
  {
    return lazyCreateMapping(xStructuralFeature, XFeatureMapping.class);
  }

  public XOperationMapping getMapping(XOperation xOperation)
  {
    return lazyCreateMapping(xOperation, XOperationMapping.class);
  }

  public XParameterMapping getMapping(XParameter xParameter)
  {
    return lazyCreateMapping(xParameter, XParameterMapping.class);
  }

  public XDataTypeMapping getMapping(XDataType xDataType)
  {
    return lazyCreateMapping(xDataType, XDataTypeMapping.class);
  }

  public XEnumLiteralMapping getMapping(XEnumLiteral xEnumLiteral)
  {
    return lazyCreateMapping(xEnumLiteral, XEnumLiteralMapping.class);
  }

  public ToXcoreMapping getToXcoreMapping(EObject eObject)
  {
    return lazyCreateMapping(eObject, ToXcoreMapping.class);
  }

  public ENamedElement getEcore(XNamedElement xNamedElement)
  {
    if (xNamedElement instanceof XPackage)
    {
      return getMapping((XPackage)xNamedElement).getEPackage();
    }
    else if (xNamedElement instanceof XClass)
    {
      return getMapping((XClass)xNamedElement).getEClass();
    }
    else if (xNamedElement instanceof XDataType)
    {
      return getMapping((XDataType)xNamedElement).getEDataType();
    }
    else if (xNamedElement instanceof XStructuralFeature)
    {
      return getMapping((XStructuralFeature)xNamedElement).getEStructuralFeature();
    }
    else if (xNamedElement instanceof XOperation)
    {
      return getMapping((XOperation)xNamedElement).getEOperation();
    }
    else if (xNamedElement instanceof XParameter)
    {
      return getMapping((XParameter)xNamedElement).getEParameter();
    }
    else if (xNamedElement instanceof XEnumLiteral)
    {
      return getMapping((XEnumLiteral)xNamedElement).getEEnumLiteral();
    }
    return null;
  }

  public GenBase getGen(XNamedElement xNamedElement)
  {
    if (xNamedElement instanceof XPackage)
    {
      return getMapping((XPackage)xNamedElement).getGenPackage();
    }
    else if (xNamedElement instanceof XClass)
    {
      return getMapping((XClass)xNamedElement).getGenClass();
    }
    else if (xNamedElement instanceof XDataType)
    {
      return getMapping((XDataType)xNamedElement).getGenDataType();
    }
    else if (xNamedElement instanceof XStructuralFeature)
    {
      return getMapping((XStructuralFeature)xNamedElement).getGenFeature();
    }
    else if (xNamedElement instanceof XOperation)
    {
      return getMapping((XOperation)xNamedElement).getGenOperation();
    }
    else if (xNamedElement instanceof XOperation)
    {
      return getMapping((XOperation)xNamedElement).getGenOperation();
    }
    else if (xNamedElement instanceof XEnumLiteral)
    {
      return getMapping((XEnumLiteral)xNamedElement).getGenEnumLiteral();
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  protected <T extends Adapter> T lazyCreateMapping(EObject eObject, Class<T> mapperType)
  {
    EList<Adapter> eAdapters = eObject.eAdapters();
    T adapter = (T)EcoreUtil.getAdapter(eAdapters, mapperType);
    if (adapter == null)
    {
      try
      {
        adapter = mapperType.newInstance();
        eAdapters.add(adapter);
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }
    return adapter;
  }

  protected EObject getXcoreElement(EObject eObject)
  {
    ToXcoreMapping adapter = (ToXcoreMapping)EcoreUtil.getAdapter(eObject.eAdapters(), ToXcoreMapping.class);
    return adapter == null ? null : adapter.getXcoreElement();
  }
}
