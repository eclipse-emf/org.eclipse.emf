/**
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Matthew Hall - initial API and implementation (bug 195222)
 *   Matthew Hall - bug 264307
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 */
package org.eclipse.emf.databinding.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFMapProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFMapPropertyDecorator extends MapProperty implements IEMFMapProperty
{
  private final IMapProperty delegate;
  private final EStructuralFeature eStructuralFeature;

  /**
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFMapPropertyDecorator(IMapProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  public Object getKeyType()
  {
    return delegate.getKeyType();
  }

  public Object getValueType()
  {
    return delegate.getValueType();
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

  @Override
  public IObservableMap observe(Object source)
  {
    return new EMFObservableMapDecorator(delegate.observe(source), eStructuralFeature);
  }

  public IObservableMap observe(Realm realm, Object source)
  {
    return new EMFObservableMapDecorator(delegate.observe(realm, source), eStructuralFeature);
  }

  @Override
  public IObservableFactory mapFactory()
  {
    return delegate.mapFactory();
  }

  @Override
  public IObservableFactory mapFactory(Realm realm)
  {
    return delegate.mapFactory(realm);
  }

  @Override
  public IObservableMap observeDetail(IObservableValue master)
  {
    return new EMFObservableMapDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  @Override
  public String toString()
  {
    return delegate.toString();
  }
}
