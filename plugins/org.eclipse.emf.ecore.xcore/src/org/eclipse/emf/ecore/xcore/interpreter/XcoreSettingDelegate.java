/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.interpreter;


import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.interpreter.IEvaluationResult;
import org.eclipse.xtext.xbase.interpreter.impl.DefaultEvaluationContext;


public class XcoreSettingDelegate implements EStructuralFeature.Internal.SettingDelegate
{
  private XcoreInterpreter interpreter;

  private XBlockExpression getBody;

  private XBlockExpression setBody;

  private XBlockExpression isSetBody;

  private XBlockExpression unsetBody;

  private EStructuralFeature eStructuralFeature;

  public void initialize
    (XBlockExpression getBody,
     XBlockExpression setBody,
     XBlockExpression isSetBody,
     XBlockExpression unsetBody,
     EStructuralFeature eStructuralFeature,
     XcoreInterpreter interpreter)
  {
    this.getBody = getBody;
    this.setBody = setBody;
    this.isSetBody = isSetBody;
    this.unsetBody = unsetBody;
    this.eStructuralFeature = eStructuralFeature;
    this.interpreter = interpreter;
  }

  public EStructuralFeature.Setting dynamicSetting
    (final InternalEObject owner,
     final EStructuralFeature.Internal.DynamicValueHolder settings,
     final int dynamicFeatureID)
  {
    return
      new EStructuralFeature.Setting()
      {
        public void unset()
        {
          dynamicUnset(owner, settings, dynamicFeatureID);
        }

        public void set(Object newValue)
        {
          dynamicSet(owner, settings, dynamicFeatureID, newValue);
        }

        public boolean isSet()
        {
          return dynamicIsSet(owner, settings, dynamicFeatureID);
        }

        public EStructuralFeature getEStructuralFeature()
        {
          return eStructuralFeature;
        }

        public EObject getEObject()
        {
          return owner;
        }

        public Object get(boolean resolve)
        {
          return dynamicGet(owner, settings, dynamicFeatureID, resolve, false);
        }
      };
  }

  public Object dynamicGet
    (InternalEObject owner,
     EStructuralFeature.Internal.DynamicValueHolder settings,
     int dynamicFeatureID,
     boolean resolve,
     boolean coreType)
  {
    if (getBody == null)
    {
      throw new IllegalStateException("coudn't find exeutable Xbase get body");
    }
    DefaultEvaluationContext context = new DefaultEvaluationContext();
    context.newValue(QualifiedName.create("this"), owner);
    IEvaluationResult result = interpreter.evaluate(getBody, context, CancelIndicator.NullImpl);
    if (result.getException() != null)
    {
      throw new RuntimeException(result.getException());
    }
    else
    {
      return result.getResult();
    }
  }

  public void dynamicSet
    (InternalEObject owner,
     EStructuralFeature.Internal.DynamicValueHolder settings,
     int dynamicFeatureID,
     Object newValue)
  {
    if (setBody == null)
    {
      throw new IllegalStateException("coudn't find exeutable Xbase set body");
    }
    DefaultEvaluationContext context = new DefaultEvaluationContext();
    context.newValue(QualifiedName.create("this"), owner);
    context.newValue(QualifiedName.create("newValue"), newValue);
    IEvaluationResult result = interpreter.evaluate(setBody, context, CancelIndicator.NullImpl);
    if (result.getException() != null)
    {
      throw new RuntimeException(result.getException());
    }
    else
    {
      settings.dynamicSet(dynamicFeatureID, result.getResult());
    }
  }

  public boolean dynamicIsSet(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID)
  {
    if (isSetBody == null)
    {
      return settings.dynamicGet(dynamicFeatureID) != null;
    }
    else
    {
      DefaultEvaluationContext context = new DefaultEvaluationContext();
      context.newValue(QualifiedName.create("this"), owner);
      IEvaluationResult result = interpreter.evaluate(isSetBody, context, CancelIndicator.NullImpl);
      if (result.getException() != null)
      {
        throw new RuntimeException(result.getException());
      }
      return (Boolean)result.getResult();
    }
  }

  public void dynamicUnset(InternalEObject owner, EStructuralFeature.Internal.DynamicValueHolder settings, int dynamicFeatureID)
  {
    if (unsetBody == null)
    {
      settings.dynamicUnset(dynamicFeatureID);
    }
    else
    {
      DefaultEvaluationContext context = new DefaultEvaluationContext();
      context.newValue(QualifiedName.create("this"), owner);
      IEvaluationResult result = interpreter.evaluate(unsetBody, context, CancelIndicator.NullImpl);
      if (result.getException() != null)
      {
        throw new RuntimeException(result.getException());
      }
    }
  }

  public NotificationChain dynamicInverseAdd
    (InternalEObject owner,
     EStructuralFeature.Internal.DynamicValueHolder settings,
     int dynamicFeatureID,
     InternalEObject otherEnd,
     NotificationChain notifications)
  {
    throw new UnsupportedOperationException("updates aren't supported");
  }

  public NotificationChain dynamicInverseRemove
    (InternalEObject owner,
     EStructuralFeature.Internal.DynamicValueHolder settings,
     int dynamicFeatureID,
     InternalEObject otherEnd,
     NotificationChain notifications)
  {
    throw new UnsupportedOperationException("updates aren't supported");
  }
}
