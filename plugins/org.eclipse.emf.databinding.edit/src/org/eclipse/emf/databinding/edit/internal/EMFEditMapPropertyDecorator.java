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
 * $Id: EMFEditMapPropertyDecorator.java,v 1.1 2009/05/23 11:11:30 tschindl Exp $
 */
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditMapProperty;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditMapPropertyDecorator extends MapProperty implements IEMFEditMapProperty
{
  private final IMapProperty delegate;
  private final EStructuralFeature eStructuralFeature;
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFEditMapPropertyDecorator(EditingDomain editingDomain, IMapProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
    this.editingDomain = editingDomain;
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

  public IObservableMap observe(Object source)
  {
    return new EMFEditObservableMapDecorator(editingDomain, delegate.observe(source), eStructuralFeature);
  }

  public IObservableMap observe(Realm realm, Object source)
  {
    return new EMFEditObservableMapDecorator(editingDomain, delegate.observe(realm, source), eStructuralFeature);
  }

  public IObservableFactory mapFactory()
  {
    return delegate.mapFactory();
  }

  public IObservableFactory mapFactory(Realm realm)
  {
    return delegate.mapFactory(realm);
  }

  public IObservableMap observeDetail(IObservableValue master)
  {
    return new EMFEditObservableMapDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
  }

  public String toString()
  {
    return delegate.toString();
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }

  public IEMFEditMapProperty values(IEMFValueProperty property)
  {
    return new EMFEditMapPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }
}
