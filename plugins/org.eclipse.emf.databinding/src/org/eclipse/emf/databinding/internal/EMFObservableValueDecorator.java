/**
 * <copyright> 
 *
 * Copyright (c) 2007 Brad Reynolds and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Brad Reynolds - initial API and implementation
 *   Matthew Hall - bug 246625
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 * </copyright>
 *
 * $Id: EMFObservableValueDecorator.java,v 1.1 2009/05/23 11:11:33 tschindl Exp $
 */
package org.eclipse.emf.databinding.internal;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * {@link IEMFObservable} decorator for an {@link IObservableValue}.
 * 
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFObservableValueDecorator extends DecoratingObservableValue implements IEMFObservable
{
  private EStructuralFeature eStructuralFeature;

  /**
   * @param decorated
   * @param eStructuralFeature
   */
  public EMFObservableValueDecorator(IObservableValue decorated, EStructuralFeature eStructuralFeature)
  {
    super(decorated, true);
    this.eStructuralFeature = eStructuralFeature;
  }

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
