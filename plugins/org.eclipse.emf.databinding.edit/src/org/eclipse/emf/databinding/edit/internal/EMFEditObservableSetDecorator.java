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

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;

import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.databinding.edit.IEMFEditObservable;
import org.eclipse.emf.databinding.internal.EMFObservableSetDecorator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * {@link IEMFObservable} decorator for an {@link IObservableList}.
 *
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.6
 */
public class EMFEditObservableSetDecorator extends EMFObservableSetDecorator implements IEMFEditObservable
{
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param decorated
   * @param eStructuralFeature
   */
  public EMFEditObservableSetDecorator(EditingDomain editingDomain, IObservableSet decorated, EStructuralFeature eStructuralFeature)
  {
    super(decorated, eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }
}
