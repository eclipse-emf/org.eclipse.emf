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
 *   Trevor S. Kaufman - Bug 215131 - added mapFactory
 *
 * </copyright>
 *
 * $Id: EMFObservables.java,v 1.2 2008/01/20 16:33:48 emerks Exp $
 */
package org.eclipse.emf.databinding;


import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.masterdetail.MasterDetailObservables;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * PROVISIONAL
 * This API is subject to arbitrary change, including renaming or removal.
 */
public class EMFObservables
{
  /**
   * Returns an observable value for the given feature of the object.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable value for the given feature of the object.
   */
  public static IObservableValue observeValue(EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EObjectObservableValue(eObject, eStructuralFeature);
  }

  /**
   * Returns an observable value for the given feature of the object.
   * @param realm the realm in which to observe.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable value for the given feature of the object.
   */
  public static IObservableValue observeValue(Realm realm, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EObjectObservableValue(realm, eObject, eStructuralFeature);
  }

  /**
   * Returns an observable list for the given multi-valued feature of the object.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable list for the given multi-valued feature of the object.
   */
  public static IObservableList observeList(EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EObjectObservableList(eObject, eStructuralFeature);
  }

  /**
   * Returns an observable list for the given multi-valued feature of the object.
   * @param realm the realm in which to observe.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable list for the given multi-valued feature of the object.
   */
  public static IObservableList observeList(Realm realm, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EObjectObservableList(realm, eObject, eStructuralFeature);
  }

  /**
   * Returns an observable map in the default realm 
   * tracking the current value of the given feature for each object in the given set.
   * @param objects the objects to track.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable map tracking the current value of the given feature for each object in the given set.
   */
  public static IObservableMap observeMap(IObservableSet objects, EStructuralFeature eStructuralFeature)
  {
    return new EObjectObservableMap(objects, eStructuralFeature);
  }

  /**
   * Returns an array of observable maps in the default realm 
   * tracking the current value of the given features for each object in the given set.
   * @param objects the objects to track.
   * @param eStructuralFeatures the features for which to track the value.
   * @return an array of observable maps tracking the current value of the given features for each object in the given set.
   */
  public static IObservableMap[] observeMaps(IObservableSet objects, EStructuralFeature[] eStructuralFeatures)
  {
    IObservableMap[] result = new IObservableMap [eStructuralFeatures.length];
    for (int i = 0; i < eStructuralFeatures.length; i++)
    {
      result[i] = observeMap(objects, eStructuralFeatures[i]);
    }
    return result;
  }

  /**
   * Returns an observable value that tracks the current value of the feature of the current value of the master observable value.
   * @param realm the realm in which to observe.
   * @param value the master observable value.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable value that tracks the current value of the named property for the current value of the master observable value
   * @see MasterDetailObservables#detailValue(IObservableValue, IObservableFactory, Object)
   */
  public static IObservableValue observeDetailValue(Realm realm, IObservableValue value, EStructuralFeature eStructuralFeature)
  {
    return MasterDetailObservables.detailValue(value, valueFactory(realm, eStructuralFeature), eStructuralFeature);
  }

  /**
   * Returns a factory for creating observable values
   * tracking the value of the given feature of a particular {@link EObject object}.
   * @param realm the realm in which to observe.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable factory.
   */
  public static IObservableFactory valueFactory(final Realm realm, final EStructuralFeature eStructuralFeature)
  {
    return 
      new IObservableFactory()
      {
        public IObservable createObservable(Object target)
        {
          return observeValue(realm, (EObject)target, eStructuralFeature);
        }
      };
  }

  /**
   * Returns an observable list that tracks the current value of the feature of the current value of the master observable value.
   * @param realm the realm in which to observe.
   * @param value the master observable value.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable value that tracks the current value of the named property for the current value of the master observable value
   * @see MasterDetailObservables#detailList(IObservableValue, IObservableFactory, Object)
   */
  public static IObservableList observeDetailList(Realm realm, IObservableValue master, EStructuralFeature eStructuralFeature)
  {
    return MasterDetailObservables.detailList(master, listFactory(realm, eStructuralFeature), eStructuralFeature);
  }

  /**
   * Returns a factory for creating observable lists
   * tracking the value of the given feature of a particular {@link EObject object}.
   * @param realm the realm in which to observe.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable factory.
   */
  public static IObservableFactory listFactory(final Realm realm, final EStructuralFeature eStructuralFeature)
  {
    return 
      new IObservableFactory()
      {
        public IObservable createObservable(Object target)
        {
          return observeList(realm, (EObject)target, eStructuralFeature);
        }
      };
  }
  
  /**
   * Returns a factory for creating observable maps
   * tracking the value of the given feature of a particular {@link EObject object}.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable factory.
   */
  public static IObservableFactory mapFactory(final EStructuralFeature eStructuralFeature)
  {
    return
      new IObservableFactory()
      {
        public IObservable createObservable(Object target)
        {
          return observeMap((IObservableSet)target, eStructuralFeature);
        }
      };
   }
}
