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
 * $Id: EObjectObservableValue.java,v 1.3 2009/05/23 11:11:33 tschindl Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 */
public class EObjectObservableValue extends AbstractObservableValue implements IObserving
{
  /**
   * The object instance
   */
  protected EObject eObject;
  /**
   * The feature
   */
  protected EStructuralFeature eStructuralFeature;
  /**
   * The listener
   */
  protected Adapter listener;

  /**
   * Observe a feature of the instance using the default realm
   * 
   * @param eObject
   *            the object
   * @param eStructuralFeature
   *            the feature
   */
  public EObjectObservableValue(EObject eObject, EStructuralFeature eStructuralFeature)
  {
    this(Realm.getDefault(), eObject, eStructuralFeature);
  }

  /**
   * Observe a feature of the instance using the realm
   * 
   * @param realm
   * 
   * @param eObject
   *            the object
   * @param eStructuralFeature
   *            the feature
   */
  public EObjectObservableValue(Realm realm, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    super(realm);
    this.eObject = eObject;
    this.eStructuralFeature = eStructuralFeature;
  }

  @Override
  public synchronized void dispose()
  {
    if (listener != null)
    {
      eObject.eAdapters().remove(listener);
      listener = null;
    }
    eObject = null;
    eStructuralFeature = null;
    super.dispose();
  }

  public Object getObserved()
  {
    return eObject;
  }

  @Override
  protected void firstListenerAdded()
  {
    listener = new AdapterImpl()
      {
        @Override
        public void notifyChanged(Notification notification)
        {
          if (eStructuralFeature == notification.getFeature() && !notification.isTouch())
          {
            final ValueDiff diff = Diffs.createValueDiff(notification.getOldValue(), notification.getNewValue());
            getRealm().exec(new Runnable()
              {
                public void run()
                {
                  fireValueChange(diff);
                }
              });
          }
        }
      };
    eObject.eAdapters().add(listener);
  }

  @Override
  protected void lastListenerRemoved()
  {
    eObject.eAdapters().remove(listener);
    listener = null;
  }

  @Override
  protected Object doGetValue()
  {
    return ExtendedMetaData.INSTANCE.getAffiliation(eObject.eClass(), eStructuralFeature) == null ? null : eObject.eGet(eStructuralFeature);
  }

  @Override
  protected void doSetValue(Object value)
  {
    eObject.eSet(eStructuralFeature, value);
  }

  public Object getValueType()
  {
    return eStructuralFeature;
  }

  @Override
  public String toString()
  {
    StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (eObject:");
    result.append(eObject);
    result.append(")");

    result.append(" (eStructuralFeature: ");
    result.append(eStructuralFeature);
    result.append(")");

    try
    {
      Object value = eObject.eGet(eStructuralFeature, false);
      result.append(" (value: ");
      result.append(value);
      result.append(")");
    }
    catch (Exception exception)
    {
      // Ignore.
    }

    return result.toString();
  }
}
