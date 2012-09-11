/**
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl <tom.schindl@bestsolution.at> - Initial API and implementation (bug 262160)
 */
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.list.ListProperty;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditListPropertyDecorator extends ListProperty implements IEMFEditListProperty
{
  private final IListProperty delegate;
  private final EStructuralFeature eStructuralFeature;
  private final EditingDomain editingDomain;

  
  /**
   * @param editingDomain
   * @param delegate
   * @param eStructuralFeature
   */
  public EMFEditListPropertyDecorator(EditingDomain editingDomain, IListProperty delegate, EStructuralFeature eStructuralFeature)
  {
    this.delegate = delegate;
    this.eStructuralFeature = eStructuralFeature;
    this.editingDomain = editingDomain;
  }

  public Object getElementType()
  {
    return delegate.getElementType();
  }

  public IEMFEditListProperty values(EStructuralFeature feature)
  {
    return values(FeaturePath.fromList(feature));
  }

  public IEMFEditListProperty values(FeaturePath featurePath)
  {
    return values(EMFEditProperties.value(editingDomain, featurePath));
  }

  public IEMFEditListProperty values(IEMFEditValueProperty property)
  {
    return new EMFEditListPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }

  @Override
  public IObservableList observe(Object source)
  {
    return new EMFEditObservableListDecorator(editingDomain, delegate.observe(source), eStructuralFeature);
  }

  public IObservableList observe(Realm realm, Object source)
  {
    return new EMFEditObservableListDecorator(editingDomain, delegate.observe(realm, source), eStructuralFeature);
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
    return new EMFEditObservableListDecorator(editingDomain, delegate.observeDetail(master), eStructuralFeature);
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

  public IEMFEditListProperty values(IEMFValueProperty property)
  {
    return new EMFEditListPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }

  public IEMFEditValueProperty value(ListElementAccess<?> elementAccess) 
  {
    return new EMFEditValuePropertyDecorator(editingDomain,new EMFEditListValueProperty(editingDomain,delegate,eStructuralFeature,elementAccess), eStructuralFeature);
  }
}
