/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.databinding.edit;

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
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 */
public class EMFEditObservables
{
  /**
   * Returns an observable value for the given feature of the object.
   * @param domain the editing domain used for applying changes.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable value for the given feature of the object.
   */
  public static IObservableValue observeValue(EditingDomain domain, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EditingDomainEObjectObservableValue(domain, eObject, eStructuralFeature);
  }

  /**
   * Returns an observable value for the given feature of the object.
   * @param realm the realm in which to observe.
   * @param domain the editing domain used for applying changes.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable value for the given feature of the object.
   */
  public static IObservableValue observeValue(Realm realm, EditingDomain domain, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EditingDomainEObjectObservableValue(realm, domain, eObject, eStructuralFeature);
  }

  /**
   * Returns an observable list for the given multi-valued feature of the object.
   * @param domain the editing domain used for applying changes.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable list for the given multi-valued feature of the object.
   */
  public static IObservableList observeList(EditingDomain domain, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EditingDomainEObjectObservableList(domain, eObject, eStructuralFeature);
  }

  /**
   * Returns an observable list for the given multi-valued feature of the object.
   * @param realm the realm in which to observe.
   * @param domain the editing domain used for applying changes.
   * @param eObject the object to observe.
   * @param eStructuralFeature the feature of the object to observe.
   * @return an observable list for the given multi-valued feature of the object.
   */
  public static IObservableList observeList(Realm realm, EditingDomain domain, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return new EditingDomainEObjectObservableList(realm, domain, eObject, eStructuralFeature);
  }

  /**
   * Returns an observable map in the default realm 
   * tracking the current value of the given feature for each object in the given set.
   * @param domain the editing domain used for applying changes.
   * @param objects the objects to track.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable map tracking the current value of the given feature for each object in the given set.
   */
  public static IObservableMap observeMap(EditingDomain domain, IObservableSet objects, EStructuralFeature eStructuralFeature)
  {
    return new EditingDomainEObjectObservableMap(domain, objects, eStructuralFeature);
  }

  /**
   * Returns an array of observable maps in the default realm tracking the current
   * value of the given features for each object in the given set.
   * @param domain the editing domain used for applying changes.
   * @param objects the objects to track.
   * @param eStructuralFeatures the features for which to track the value.
   * @return an array of observable maps tracking the current value of the given features for each object in the given set.
   */
  public static IObservableMap[] observeMaps(EditingDomain domain, IObservableSet objects, EStructuralFeature[] eStructuralFeatures)
  {
    IObservableMap[] result = new IObservableMap [eStructuralFeatures.length];
    for (int i = 0; i < eStructuralFeatures.length; i++)
    {
      result[i] = observeMap(domain, objects, eStructuralFeatures[i]);
    }
    return result;
  }

  /**
   * Returns an observable value that tracks the current value of the feature of the current value of the master observable value.
   * @param realm the realm in which to observe.
   * @param domain the editing domain used for applying changes.
   * @param value the master observable value.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable value that tracks the current value of the named property for the current value of the master observable value
   * @see MasterDetailObservables#detailValue(IObservableValue, IObservableFactory, Object)
   */
  public static IObservableValue observeDetailValue(
    Realm realm,
    EditingDomain domain,
    IObservableValue value,
    EStructuralFeature eStructuralFeature)
  {
    return MasterDetailObservables.detailValue(value, valueFactory(realm, domain, eStructuralFeature), eStructuralFeature);
  }

  /**
   * Returns a factory for creating observable values
   * tracking the value of the given feature of a particular {@link EObject object}.
   * @param realm the realm in which to observe.
   * @param domain the editing domain used for applying changes.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable factory.
   */
  public static IObservableFactory valueFactory(final Realm realm, final EditingDomain domain, final EStructuralFeature eStructuralFeature)
  {
    return new IObservableFactory()
      {
        public IObservable createObservable(Object target)
        {
          return observeValue(realm, domain, (EObject)target, eStructuralFeature);
        }
      };
  }

  /**
   * Returns an observable list that tracks the current value of the feature of the current value of the master observable value.
   * @param realm the realm in which to observe.
   * @param domain the editing domain used for applying changes.
   * @param value the master observable value.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable value that tracks the current value of the named property for the current value of the master observable value
   * @see MasterDetailObservables#detailList(IObservableValue, IObservableFactory, Object)
   */
  public static IObservableList observeDetailList(
    Realm realm,
    EditingDomain domain,
    IObservableValue value,
    EStructuralFeature eStructuralFeature)
  {
    return MasterDetailObservables.detailList(value, listFactory(realm, domain, eStructuralFeature), eStructuralFeature);
  }

  /**
   * Returns a factory for creating observable lists
   * tracking the value of the given feature of a particular {@link EObject object}.
   * @param realm the realm in which to observe.
   * @param domain the editing domain used for applying changes.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable factory.
   */
  public static IObservableFactory listFactory(final Realm realm, final EditingDomain domain, final EStructuralFeature eStructuralFeature)
  {
    return new IObservableFactory()
      {
        public IObservable createObservable(Object target)
        {
          return observeList(realm, domain, (EObject)target, eStructuralFeature);
        }
      };
  }

  /**
   * Returns a factory for creating observable maps
   * tracking the value of the given feature of a particular {@link EObject object}.
   * @param domain the editing domain used for applying changes.
   * @param eStructuralFeature the feature for which to track the value.
   * @return an observable factory.
   */
  public static IObservableFactory mapFactory(final EditingDomain domain, final EStructuralFeature eStructuralFeature)
  {
    return new IObservableFactory()
      {
        public IObservable createObservable(Object target)
        {
          return observeMap(domain, (IObservableSet)target, eStructuralFeature);
        }
      };
  }
}
