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
 *   Hasan Ceylan  - patch in bug 262160
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 * </copyright>
 *
 * $Id: EMFMapProperty.java,v 1.3 2010/01/20 20:36:26 emerks Exp $
 */
package org.eclipse.emf.databinding.internal;

import java.util.Map;

import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.map.SimpleMapProperty;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFMapProperty extends SimpleMapProperty
{
  private EStructuralFeature eStructuralFeature;

  /**
   * @param eStructuralFeature
   */
  public EMFMapProperty(EStructuralFeature eStructuralFeature)
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

  public Object getKeyType()
  {
    final EClass eType = (EClass)this.eStructuralFeature.getEType();

    for (final EStructuralFeature feature : eType.getEAllStructuralFeatures())
    {
      if (feature.getName().equals("key"))
      {
        return feature;
      }
    }

    return null;
  }

  public Object getValueType()
  {
    final EClass eType = (EClass)this.eStructuralFeature.getEType();

    for (final EStructuralFeature feature : eType.getEAllStructuralFeatures())
    {
      if (feature.getName().equals("value"))
      {
        return feature;
      }
    }

    return null;
  }

  @Override
  protected Map< ? , ? > doGetMap(Object source)
  {
    EObject eObj = (EObject)source;
    return ((EMap< ? , ? >)eObj.eGet(eStructuralFeature)).map();
  }

  @Override
  @SuppressWarnings("unchecked")
  protected void doSetMap(Object source, Map map, MapDiff diff)
  {
    EObject eObject = (EObject)source;
    eObject.eSet(eStructuralFeature, map);
  }

  @Override
  public INativePropertyListener adaptListener(final ISimplePropertyListener listener)
  {
    return new EMFPropertyListener.EMFMapPropertyListener()
      {

        @Override
        protected IProperty getOwner()
        {
          return EMFMapProperty.this;
        }

        @Override
        protected ISimplePropertyListener getListener()
        {
          return listener;
        }

        @Override
        protected EStructuralFeature getFeature()
        {
          return eStructuralFeature;
        }
      };
  }

  @Override
  public String toString()
  {
    String s = EMFPropertyHelper.propertyName(eStructuralFeature) + "{:}"; //$NON-NLS-1$

    s += "<" + EMFPropertyHelper.shortClassName((EStructuralFeature)getKeyType()) + ", " //$NON-NLS-1$ //$NON-NLS-2$
      + EMFPropertyHelper.shortClassName((EStructuralFeature)getValueType()) + ">"; //$NON-NLS-1$
    return s;
  }
}
