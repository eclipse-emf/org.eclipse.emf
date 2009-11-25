/**
 * <copyright>
 *
 * Copyright (c) 2008 Tom Schindl and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 295683
 * </copyright>
 *
 * $Id: EMFSetProperty.java,v 1.1 2009/11/25 09:15:05 tschindl Exp $
 */
package org.eclipse.emf.databinding.internal;

import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.set.SimpleSetProperty;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.6
 */
public class EMFSetProperty extends SimpleSetProperty
{
  private EStructuralFeature eStructuralFeature;

  /**
   * @param eStructuralFeature
   */
  public EMFSetProperty(EStructuralFeature eStructuralFeature)
  {
    this.eStructuralFeature = eStructuralFeature;
  }

  /**
   * @return the feature
   */
  protected EStructuralFeature getFeature()
  {
    return eStructuralFeature;
  }

  public Object getElementType()
  {
    return eStructuralFeature;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected Set doGetSet(Object source)
  {
    EObject eObj = (EObject)source;
    return new DelegatingSet((List<?>)eObj.eGet(eStructuralFeature));
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetSet(Object source, Set set, SetDiff diff)
  {
    Set<?> currentSet = doGetSet(source);
    diff.applyTo(currentSet);
  }

  @Override
  public INativePropertyListener adaptListener(final ISimplePropertyListener listener)
  {
    return new EMFPropertyListener.EMFSetPropertyListener()
      {

        @Override
        protected EStructuralFeature getFeature()
        {
          return eStructuralFeature;
        }

        @Override
        protected ISimplePropertyListener getListener()
        {
          return listener;
        }

        @Override
        protected IProperty getOwner()
        {
          return EMFSetProperty.this;
        }
      };
  }

  @Override
  public String toString()
  {
    String s = EMFPropertyHelper.propertyName(eStructuralFeature) + "[]"; //$NON-NLS-1$
    s += "<" + EMFPropertyHelper.shortClassName(eStructuralFeature) + ">"; //$NON-NLS-1$//$NON-NLS-2$
    return s;
  }



}
