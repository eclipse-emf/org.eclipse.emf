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

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.edit.IEMFEditObservable;
import org.eclipse.emf.databinding.internal.EMFObservableMapDecorator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * {@link IEMFEditObservable} decorator for an {@link IObservableMap}.
 * 
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditObservableMapDecorator extends EMFObservableMapDecorator implements IEMFEditObservable
{
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param decorated
   * @param eStructuralFeature
   */
  public EMFEditObservableMapDecorator(EditingDomain editingDomain, IObservableMap decorated, EStructuralFeature eStructuralFeature)
  {
    super(decorated, eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }
}
