/**
 * <copyright> 
 *
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl <tom.schindl@bestsolution.at> - Initial API and implementation (bug 262160)
 * </copyright>
 *
 * $Id: EMFEditValuePropertyDecorator.java,v 1.2 2009/05/28 10:02:04 emerks Exp $
 */
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.databinding.property.value.ValueProperty;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFMapProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.databinding.edit.IEMFEditMapProperty;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditValuePropertyDecorator extends ValueProperty implements IEMFEditValueProperty
{
  private final IValueProperty delegate;
  private final EStructuralFeature eStructuralFeature;
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFEditValuePropertyDecorator(EditingDomain editingDomain, IValueProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
    this.editingDomain = editingDomain;
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  public Object getValueType()
  {
    return delegate.getValueType();
  }

  public IEMFEditValueProperty value(EStructuralFeature feature)
  {
    return value(FeaturePath.fromList(feature));
  }

  public IEMFEditValueProperty value(FeaturePath featurePath)
  {
    return value(EMFEditProperties.value(editingDomain, featurePath));
  }

  public IEMFEditValueProperty value(IEMFEditValueProperty property)
  {
    return new EMFEditValuePropertyDecorator(editingDomain, super.value(property), property.getStructuralFeature());
  }

  public IEMFEditListProperty list(EStructuralFeature feature)
  {
    return list(EMFEditProperties.list(editingDomain, feature));
  }

  public IEMFEditListProperty list(IEMFEditListProperty property)
  {
    return new EMFEditListPropertyDecorator(editingDomain, super.list(property), property.getStructuralFeature());
  }

  public IEMFEditMapProperty map(EStructuralFeature feature)
  {
    return EMFEditProperties.map(editingDomain, feature);
  }

  public IEMFEditMapProperty map(IEMFEditMapProperty property)
  {
    return new EMFEditMapPropertyDecorator(editingDomain, super.map(property), property.getStructuralFeature());
  }

  @Override
  public IObservableValue observe(Object source)
  {
    return new EMFEditObservableValueDecorator(editingDomain, delegate.observe(source), eStructuralFeature);
  }

  public IObservableValue observe(Realm realm, Object source)
  {
    return new EMFEditObservableValueDecorator(editingDomain, delegate.observe(realm, source), eStructuralFeature);
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
    return new EMFEditObservableValueDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
  }

  public IObservableList observeDetail(IObservableList master)
  {
    return new EMFEditObservableListDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
  }

  public IObservableMap observeDetail(IObservableSet master)
  {
    return new EMFEditObservableMapDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
  }

  public IObservableMap observeDetail(IObservableMap master)
  {
    return new EMFEditObservableMapDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
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

  public IEMFEditListProperty list(IEMFListProperty property)
  {
    return new EMFEditListPropertyDecorator(editingDomain, super.list(property), property.getStructuralFeature());
  }

  public IEMFEditMapProperty map(IEMFMapProperty property)
  {
    return new EMFEditMapPropertyDecorator(editingDomain, super.map(property), property.getStructuralFeature());
  }

  public IEMFEditValueProperty value(IEMFValueProperty property)
  {
    return new EMFEditValuePropertyDecorator(editingDomain, super.value(property), property.getStructuralFeature());
  }
}
