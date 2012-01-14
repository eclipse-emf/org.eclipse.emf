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

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.set.DecoratingObservableSet;
import org.eclipse.core.databinding.observable.set.IObservableSet;

import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * {@link IEMFObservable} decorator for an {@link IObservableList}.
 *
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.6
 */
public class EMFObservableSetDecorator extends DecoratingObservableSet implements IEMFObservable
{
  private EStructuralFeature eStructuralFeature;

  /**
   * @param decorated
   * @param eStructuralFeature
   */
  public EMFObservableSetDecorator(IObservableSet decorated, EStructuralFeature eStructuralFeature)
  {
    super(decorated, true);
    this.eStructuralFeature = eStructuralFeature;
  }

  @Override
  public synchronized void dispose()
  {
    this.eStructuralFeature = null;
    super.dispose();
  }

  public Object getObserved()
  {
    IObservable decorated = getDecorated();
    if (decorated instanceof IObserving)
      return ((IObserving)decorated).getObserved();
    return null;
  }

  public EStructuralFeature getStructuralFeature()
  {
    return eStructuralFeature;
  }
}
