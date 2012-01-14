/**
 * Copyright (c) 2009 Tom Schindl and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 295683
 */
package org.eclipse.emf.databinding.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.core.databinding.property.set.SetProperty;

import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFMapProperty;
import org.eclipse.emf.databinding.IEMFSetProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.6
 */
public class EMFSetPropertyDecorator extends SetProperty implements IEMFSetProperty
{
  private final ISetProperty delegate;
  private final EStructuralFeature eStructuralFeature;

  /**
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFSetPropertyDecorator(ISetProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
  }

  public Object getElementType()
  {
    return delegate.getElementType();
  }

  public IEMFMapProperty values(EStructuralFeature feature)
  {
    return values(FeaturePath.fromList(feature));
  }

  public IEMFMapProperty values(FeaturePath featurePath)
  {
    return values(EMFProperties.value(featurePath));
  }

  public IEMFMapProperty values(IEMFValueProperty property)
  {
    return new EMFMapPropertyDecorator(super.values(property), property.getStructuralFeature());
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  @Override
  public IObservableSet observe(Object source)
  {
    return new EMFObservableSetDecorator(delegate.observe(source), eStructuralFeature);
  }

  public IObservableSet observe(Realm realm, Object source)
  {
    return new EMFObservableSetDecorator(delegate.observe(realm, source), eStructuralFeature);
  }

  @Override
  public IObservableFactory setFactory()
  {
    return delegate.setFactory();
  }

  @Override
  public IObservableFactory setFactory(Realm realm)
  {
    return delegate.setFactory(realm);
  }

  @Override
  public IObservableSet observeDetail(IObservableValue master)
  {
    return new EMFObservableSetDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  @Override
  public String toString()
  {
    return delegate.toString();
  }
}
