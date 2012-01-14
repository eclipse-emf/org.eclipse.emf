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
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.list.ListProperty;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFListPropertyDecorator extends ListProperty implements IEMFListProperty
{
  private final IListProperty delegate;
  private final EStructuralFeature eStructuralFeature;

  /**
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFListPropertyDecorator(IListProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
  }

  public Object getElementType()
  {
    return delegate.getElementType();
  }

  public IEMFListProperty values(EStructuralFeature feature)
  {
    return values(FeaturePath.fromList(feature));
  }

  public IEMFListProperty values(FeaturePath featurePath)
  {
    return values(EMFProperties.value(featurePath));
  }

  public IEMFListProperty values(IEMFValueProperty property)
  {
    return new EMFListPropertyDecorator(super.values(property), property.getStructuralFeature());
  }

  public IEMFValueProperty value(ListElementAccess<?> elementAccess)
  {
    return new EMFValuePropertyDecorator(new EMFListValueProperty(eStructuralFeature, elementAccess), eStructuralFeature);
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  @Override
  public IObservableList observe(Object source)
  {
    return new EMFObservableListDecorator(delegate.observe(source), eStructuralFeature);
  }

  public IObservableList observe(Realm realm, Object source)
  {
    return new EMFObservableListDecorator(delegate.observe(realm, source), eStructuralFeature);
  }

  @Override
  public IObservableFactory listFactory()
  {
    return delegate.listFactory();
  }

  @Override
  public IObservableFactory listFactory(Realm realm)
  {
    return delegate.listFactory(realm);
  }

  @Override
  public IObservableList observeDetail(IObservableValue master)
  {
    return new EMFObservableListDecorator(delegate.observeDetail(master), eStructuralFeature);
  }

  @Override
  public String toString()
  {
    return delegate.toString();
  }
}
