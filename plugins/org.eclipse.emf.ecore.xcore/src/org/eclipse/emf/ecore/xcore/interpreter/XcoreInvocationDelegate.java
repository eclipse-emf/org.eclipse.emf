/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.interpreter;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.interpreter.IEvaluationResult;
import org.eclipse.xtext.xbase.interpreter.impl.DefaultEvaluationContext;


public class XcoreInvocationDelegate implements EOperation.Internal.InvocationDelegate
{
  private XcoreInterpreter interpreter;

  private XBlockExpression body;

  private EOperation eOperation;

  public void initialize(XBlockExpression body, EOperation operation, XcoreInterpreter interpreter)
  {
    this.body = body;
    this.eOperation = operation;
    if (interpreter != null)
    {
      this.interpreter = interpreter;
    }
  }

  public Object dynamicInvoke(InternalEObject target, EList<?> arguments) throws InvocationTargetException
  {
    if (body == null)
    {
      throw new IllegalStateException("coudn't find exeutable Xbase body");
    }

    DefaultEvaluationContext context = new DefaultEvaluationContext();
    context.newValue(QualifiedName.create("this"), target);
    if (arguments != null)
    {
      for (int i = 0; i < arguments.size(); i++)
      {
        Object arg = arguments.get(i);
        EParameter parameter = eOperation.getEParameters().get(i);
        context.newValue(QualifiedName.create(parameter.getName()), arg);
      }
    }
    IEvaluationResult result = interpreter.evaluate(body, context, CancelIndicator.NullImpl);
    if (result.getException() != null)
    {
      throw new InvocationTargetException(result.getException());
    }
    else
    {
      return result.getResult();
    }
  }
}
