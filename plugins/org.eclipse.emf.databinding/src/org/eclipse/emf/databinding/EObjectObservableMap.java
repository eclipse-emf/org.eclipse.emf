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
 * $Id: EObjectObservableMap.java,v 1.3 2008/01/26 20:56:46 emerks Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.map.ComputedObservableMap;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * PROVISIONAL
 * This API is subject to arbitrary change, including renaming or removal.
 */
public class EObjectObservableMap extends ComputedObservableMap
{
  protected EStructuralFeature eStructuralFeature;

  private Adapter elementListener = 
    new AdapterImpl()
    {
      @Override
      public void notifyChanged(Notification notification)
      {
        if (eStructuralFeature == notification.getFeature() && !notification.isTouch())
        {
          // TODO
          // This assumes we only get a SET notification, which isn't a good assumption.
          //
          final MapDiff diff = Diffs.createMapDiffSingleChange(notification.getNotifier(), notification.getOldValue(), notification.getNewValue());
          getRealm().exec
            (new Runnable()
             {
               public void run()
               {
                 fireMapChange(diff);
               }
             });
        }
      }
    };

  public EObjectObservableMap(IObservableSet objects, EStructuralFeature feature)
  {
    super(objects);
    this.eStructuralFeature = feature;
    init();
  }

  @Override
  protected void hookListener(Object domainElement)
  {
    ((EObject)domainElement).eAdapters().add(elementListener);
  }

  @Override
  protected void unhookListener(Object domainElement)
  {
    ((EObject)domainElement).eAdapters().remove(elementListener);
  }

  @Override
  protected Object doGet(Object key)
  {
    return ((EObject)key).eGet(eStructuralFeature);
  }

  @Override
  protected Object doPut(Object key, Object value)
  {
    EObject eObject = (EObject)key;
    Object result = eObject.eGet(eStructuralFeature);
    eObject.eSet(eStructuralFeature, value);
    return result;
  }
}
