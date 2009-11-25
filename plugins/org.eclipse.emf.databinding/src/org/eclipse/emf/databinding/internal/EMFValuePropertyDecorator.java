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
 * $Id: EMFValuePropertyDecorator.java,v 1.3 2009/11/25 09:15:05 tschindl Exp $
 */
package org.eclipse.emf.databinding.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.databinding.property.value.ValueProperty;

import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFMapProperty;
import org.eclipse.emf.databinding.IEMFSetProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.5
 */
public class EMFValuePropertyDecorator extends ValueProperty implements IEMFValueProperty
{
  private final IValueProperty delegate;
  private final EStructuralFeature eStructuralFeature;

  /**
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFValuePropertyDecorator(IValueProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  public Object getValueType()
  {
    return delegate.getValueType();
  }

  public IEMFValueProperty value(EStructuralFeature feature)
  {
    return value(FeaturePath.fromList(feature));
  }

  public IEMFValueProperty value(FeaturePath featurePath)
  {
    return value(EMFProperties.value(featurePath));
  }

  public IEMFValueProperty value(IEMFValueProperty property)
  {
    return new EMFValuePropertyDecorator(super.value(property), property.getStructuralFeature());
  }

  public IEMFListProperty list(EStructuralFeature feature)
  {
    return list(EMFProperties.list(feature));
  }

  public IEMFListProperty list(IEMFListProperty property)
  {
    return new EMFListPropertyDecorator(super.list(property), property.getStructuralFeature());
  }

  public IEMFSetProperty set(EStructuralFeature feature) {
    return set(EMFProperties.set(feature));
  }

  public IEMFSetProperty set(IEMFSetProperty property) {
    return new EMFSetPropertyDecorator(super.set(property), property.getStructuralFeature());
  }

  public IEMFMapProperty map(EStructuralFeature feature)
  {
    return map(EMFProperties.map(feature));
  }

  public IEMFMapProperty map(IEMFMapProperty property)
  {
    return new EMFMapPropertyDecorator(super.map(property), property.getStructuralFeature());
  }

  @Override
  public IObservableValue observe(Object source)
  {
    return new EMFObservableValueDecorator(delegate.observe(source), eStructuralFeature);
  }

  public IObservableValue observe(Realm realm, Object source)
  {
    return new EMFObservableValueDecorator(delegate.observe(realm, source), eStructuralFeature);
  }

  @Override
  public IObservableFactory valueFactory()
  {
    return delegate.valueFactory();
  }

  @Override
  public IObservableFactory valueFactory(Realm realm)
  {
    return delegate.valueFactory(realm);
  }

  @Override
  public IObservableValue observeDetail(IObservableValue master)
  {
    return new EMFObservableValueDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  public IObservableList observeDetail(IObservableList master)
  {
    return new EMFObservableListDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  public IObservableMap observeDetail(IObservableSet master)
  {
    return new EMFObservableMapDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  public IObservableMap observeDetail(IObservableMap master)
  {
    return new EMFObservableMapDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  @Override
  public String toString()
  {
    return delegate.toString();
  }
}
