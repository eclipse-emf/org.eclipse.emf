/**
 * <copyright> 
 *
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Matthew Hall - initial API and implementation (bug 194734)
 *   Matthew Hall - bugs 195222, 264307, 265561
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 * </copyright>
 *
 * $Id: EMFListProperty.java,v 1.3 2010/02/04 20:56:28 emerks Exp $
 */
package org.eclipse.emf.databinding.internal;

import java.util.List;

import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.list.SimpleListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFListProperty extends SimpleListProperty
{
  private EStructuralFeature eStructuralFeature;

  /**
   * @param eStructuralFeature
   */
  public EMFListProperty(EStructuralFeature eStructuralFeature)
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

  @Override
  protected List< ? > doGetList(Object source)
  {
    EObject eObj = (EObject)source;
    return (List< ? >)eObj.eGet(eStructuralFeature);
  }

  @Override
  @SuppressWarnings("rawtypes")
  protected void doSetList(Object source, List list, ListDiff diff)
  {
    List< ? > currentList = doGetList(source);
    diff.applyTo(currentList);
  }

  @Override
  public INativePropertyListener adaptListener(final ISimplePropertyListener listener)
  {
    return new EMFPropertyListener.EMFListPropertyListener()
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
          return EMFListProperty.this;
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
