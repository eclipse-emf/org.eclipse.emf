/**
 * <copyright> 
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMFDataBindingContext.java,v 1.2 2009/05/23 11:11:33 tschindl Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 */
public class EMFDataBindingContext extends DataBindingContext
{
  /**
   * Create a new databinding context which uses the default realm for
   * validation
   */
  public EMFDataBindingContext()
  {
    this(Realm.getDefault());
  }

  /**
   * Create a databinding context which uses an explicit realm for
   * validation
   * 
   * @param validationRealm
   *            the realm to use
   */
  public EMFDataBindingContext(Realm validationRealm)
  {
    super(validationRealm);
  }

  @Override
  protected UpdateValueStrategy createModelToTargetUpdateValueStrategy(IObservableValue fromValue, IObservableValue toValue)
  {
    return new EMFUpdateValueStrategy();
  }

  @Override
  protected UpdateValueStrategy createTargetToModelUpdateValueStrategy(IObservableValue fromValue, IObservableValue toValue)
  {
    return new EMFUpdateValueStrategy();
  }
}
