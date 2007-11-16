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
 * $Id: EMFDataBindingContext.java,v 1.1 2007/11/16 21:25:21 emerks Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * PROVISIONAL
 * This API is subject to arbitrary change, including renaming or removal.
 */
public class EMFDataBindingContext extends DataBindingContext
{
  public EMFDataBindingContext()
  {
    this(Realm.getDefault());
  }

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
