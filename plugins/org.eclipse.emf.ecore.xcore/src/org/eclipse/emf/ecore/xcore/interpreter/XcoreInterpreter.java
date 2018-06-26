/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.interpreter;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.interpreter.IEvaluationContext;
import org.eclipse.xtext.xbase.interpreter.impl.EvaluationException;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;

import com.google.inject.Inject;


public class XcoreInterpreter extends XbaseInterpreter
{
  @Inject
  private XcoreMapper mapper;

  @Override
  protected Object invokeOperation(JvmOperation operation, Object receiver, List<Object> argumentValues)
  {
    if (receiver instanceof EObject)
    {
      ToXcoreMapping mapping = mapper.getToXcoreMapping(operation);
      if (mapping != null)
      {
        XNamedElement element = mapping.getXcoreElement();
        if (element instanceof XOperation)
        {
          EOperation op = mapper.getMapping((XOperation)element).getEOperation();
          try
          {
            return ((EObject)receiver).eInvoke(op, new BasicEList<Object>(argumentValues));
          }
          catch (InvocationTargetException e)
          {
            throw new EvaluationException(e);
          }
        }
        else if (element instanceof XStructuralFeature)
        {
          EStructuralFeature feature = mapper.getMapping((XStructuralFeature)element).getEStructuralFeature();
          String accessorName = operation.getSimpleName();
          if (accessorName.startsWith("get") || accessorName.startsWith("is"))
          {
            try
            {
              return ((EObject)receiver).eGet(feature);
            }
            catch (Throwable e)
            {
              throw new EvaluationException(e);
            }
          }
          else if (accessorName.startsWith("set"))
          {
            try
            {
              ((EObject)receiver).eSet(feature, argumentValues.get(0));
              return null;
            }
            catch (Throwable e)
            {
              throw new EvaluationException(e);
            }
          }
        }
      }
    }
    return super.invokeOperation(operation, receiver, argumentValues);
  }

  @Override
  protected Object featureCallField(JvmField jvmField, Object receiver)
  {
    ToXcoreMapping mapping = mapper.getToXcoreMapping(jvmField);
    XNamedElement xcoreElement = mapping.getXcoreElement();
    if (xcoreElement instanceof XEnumLiteral)
    {
      EEnumLiteral literal = mapper.getMapping((XEnumLiteral)xcoreElement).getEEnumLiteral();
      if (literal != null)
      {
        return literal;
      }
    }
    return super.featureCallField(jvmField, receiver);
  }

  @Override
  protected Object _doEvaluate(XInstanceOfExpression instanceOf, IEvaluationContext context, CancelIndicator indicator)
  {
    ToXcoreMapping mapping = mapper.getToXcoreMapping(instanceOf.getType().getType());
    XClass xClass = (XClass)mapping.getXcoreElement();
    if (xClass != null)
    {
      Object instance = internalEvaluate(instanceOf.getExpression(), context, indicator);
      EClass eClass = mapper.getMapping(xClass).getEClass();
      return eClass.isInstance(instance);
    }
    else
    {
      return super._doEvaluate(instanceOf, context, indicator);
    }
  }

  @Override
  protected Object _doEvaluate(XCastedExpression castedExpression, IEvaluationContext context, CancelIndicator indicator)
  {
    JvmType castType = castedExpression.getType().getType();
    ToXcoreMapping mapping = mapper.getToXcoreMapping(castType);
    XClass xClass = (XClass)mapping.getXcoreElement();
    if (xClass != null)
    {
      Object result = internalEvaluate(castedExpression.getTarget(), context, indicator);
      EClass eClass = mapper.getMapping(xClass).getEClass();
      if (eClass.isInstance(result))
      {
        return result;
      }
      else
      {
        throw new EvaluationException(new ClassCastException(castType.getQualifiedName()));
      }
    }
    else
    {
      return super._doEvaluate(castedExpression, context, indicator);
    }
  }
}
