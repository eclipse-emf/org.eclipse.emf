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
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.core.databinding.property.set.SetProperty;

import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFMapProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditMapProperty;
import org.eclipse.emf.databinding.edit.IEMFEditSetProperty;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.6
 */
public class EMFEditSetPropertyDecorator extends SetProperty implements IEMFEditSetProperty
{
  private final EditingDomain editingDomain;
  private final ISetProperty delegate;
  private final EStructuralFeature eStructuralFeature;

  /**
   * @param editingDomain
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFEditSetPropertyDecorator(EditingDomain editingDomain, ISetProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.editingDomain = editingDomain;
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
  }

  public Object getElementType()
  {
    return delegate.getElementType();
  }

  public IEMFEditMapProperty values(EStructuralFeature feature)
  {
    return values(FeaturePath.fromList(feature));
  }

  public IEMFEditMapProperty values(FeaturePath featurePath)
  {
    return values(EMFEditProperties.value(editingDomain, featurePath));
  }

  public IEMFEditMapProperty values(IEMFEditValueProperty property)
  {
    return new EMFEditMapPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }

  public IEMFMapProperty values(IEMFValueProperty property)
  {
    return new EMFEditMapPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  @Override
  public IObservableSet observe(Object source)
  {
    return new EMFEditObservableSetDecorator(editingDomain, delegate.observe(source), eStructuralFeature);
  }

  public IObservableSet observe(Realm realm, Object source)
  {
    return new EMFEditObservableSetDecorator(editingDomain, delegate.observe(realm, source), eStructuralFeature);
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
    return new EMFEditObservableSetDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
  }

  @Override
  public String toString()
  {
    return delegate.toString();
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }


}
