/**
 * Copyright (c) 2003-2011 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.change.util;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * A change recorder for the tree contents of a collection of EObjects. It monitors the specified objects and
 * then produces a {@link ChangeDescription change model} representing the changes needed to reverse (undo) all
 * the model changes made while recording.
 */
public class ChangeRecorder extends BasicChangeRecorder implements Adapter.Internal
{
  protected List<Notifier> targetObjects = new BasicEList.FastCompare<Notifier>();

  protected List<Notifier> originalTargetObjects = new BasicEList.FastCompare<Notifier>();

  protected boolean loadingTargets;

  protected boolean resolveProxies;

  protected Map<EObject, URI> eObjectToProxyURIMap;

  public ChangeRecorder()
  {
    super();
  }

  public ChangeRecorder(EObject rootObject)
  {
    beginRecording(Collections.singleton(rootObject));
  }

  public ChangeRecorder(Resource resource)
  {
    beginRecording(Collections.singleton(resource));
  }

  public ChangeRecorder(ResourceSet resourceSet)
  {
    beginRecording(Collections.singleton(resourceSet));
  }

  public ChangeRecorder(Collection<?> rootObjects)
  {
    beginRecording(rootObjects);
  }

  public boolean isResolveProxies()
  {
    return resolveProxies;
  }

  public void setResolveProxies(boolean resolveProxies)
  {
    this.resolveProxies = resolveProxies;
  }

  /**
   * @since 2.7
   */
  public Map<EObject, URI> getEObjectToProxyURIMap()
  {
    return eObjectToProxyURIMap;
  }

  /**
   * When this is set to a non-null value,
   * the original proxy URI of each object will be recorded
   * as the change recorder is attached to each object.
   * This is important for calling {@link ChangeDescription#copyAndReverse(Map)}.
   * @since 2.7
   */
  public void setEObjectToProxyURIMap(Map<EObject, URI> eObjectToProxyURIMap)
  {
    this.eObjectToProxyURIMap = eObjectToProxyURIMap;
  }

  @Override
  public void dispose()
  {
    setRecording(false);

    Notifier[] notifiers = targetObjects.toArray(new Notifier [targetObjects.size()]);
    targetObjects.clear();
    for (int i = 0, length = notifiers.length; i < length; i++)
    {
      removeAdapter(notifiers[i]);
    }
    originalTargetObjects.clear();

    super.dispose();
  }

  protected void removeAdapter(Notifier notifier)
  {
    notifier.eAdapters().remove(this);
  }

  /**
   * Begins recording any changes made to the elements of the specified collection.
   * @param rootObjects A collection of instances of {@link Notifier}
   */
  public void beginRecording(Collection<?> rootObjects)
  {
    beginRecording(null, rootObjects);
  }

  /**
   * Begins recording any changes made to the elements of the specified collection,
   * adding the changes to an existing {@link ChangeDescription}.
   * This allows clients to resume a previous recording.
   * <p>
   * Unpredictable (and probably bad) results may happen if the change description is
   * inconsistent with the current state of the application.
   * </p>
   * @param changeDescription A change description with changes made during a previous
   * recording or <tt>null</tt> if a new change description should be instantiated.
   * @param rootObjects A collection of instances of {@link Notifier}
   * @since 2.1.0
   */
  public void beginRecording(ChangeDescription changeDescription, Collection<?> rootObjects)
  {
    List<EObject> insertedObjects = changeDescription == null ?
      null
      : changeDescription.getObjectsToDetach();

    if (changeDescription == null)
    {
      changeDescription = createChangeDescription();
    }
    setChangeDescription(changeDescription);

    loadingTargets = true;
    for (Object rootObject : rootObjects)
    {
      Notifier notifier = (Notifier)rootObject;
      addAdapter(notifier);
    }
    loadingTargets = false;

    if (changeDescription != null)
    {
      prepareChangeDescriptionForResume();
    }

    if (insertedObjects != null)
    {
      originalTargetObjects.removeAll(insertedObjects);
    }

    setRecording(true);
  }

  /**
   * Prepares this ChangeRecorder's {@link #changeDescription} for the scenarios where the user
   * is resuming a previous recording.
   * @see #beginRecording(ChangeDescription, Collection)
   * @since 2.1.0
   */
  protected void prepareChangeDescriptionForResume()
  {
    loadingTargets = true;
    ChangeDescription changeDescription = getChangeDescription();
    for (Notifier notifier : changeDescription.getObjectsToAttach())
    {
      addAdapter(notifier);
    }
    loadingTargets = false;

    changeDescription.getObjectsToAttach().clear();

    // Make sure that all the old values are cached.
    for (List<FeatureChange> featureChanges : changeDescription.getObjectChanges().values())
    {
      for (FeatureChange featureChange : featureChanges)
      {
        featureChange.getValue();
      }
    }

    for (ResourceChange resourceChange : changeDescription.getResourceChanges())
    {
      resourceChange.getValue();
    }
  }

  @Override
  protected void consolidateChanges()
  {
    ChangeDescription changeDescription = getChangeDescription();
    List<EObject> orphanedObjects = changeDescription.getObjectsToAttach();
    Set<Notifier> originalTargetObjects = new HashSet<Notifier>(this.originalTargetObjects);
    for (Notifier target : targetObjects)
    {
      if (target instanceof EObject)
      {
        EObject eObject = (EObject)target;
        if (isOrphan(eObject))
        {
          if (originalTargetObjects.contains(eObject))
          {
            orphanedObjects.add(eObject);
          }
          else
          {
            changeDescription.getObjectChanges().removeKey(eObject);
          }
        }
      }
    }

    super.consolidateChanges();
  }

  protected boolean isOrphan(EObject eObject)
  {
    return ((InternalEObject)eObject).eInternalContainer() == null && eObject.eResource() == null;
  }

  public void notifyChanged(Notification notification)
  {
    Object notifier = notification.getNotifier();
    if (notifier instanceof EObject)
    {
      Object feature = notification.getFeature();
      if (feature instanceof EReference)
      {
        EReference eReference = (EReference)feature;
        handleFeature(eReference, eReference.isContainment() ? eReference : null, notification, (EObject)notifier);
      }
      else if (feature != null)
      {
        handleFeature((EStructuralFeature)feature, null, notification, (EObject) notifier);
      }
    }
    else if (notifier instanceof Resource)
    {
      int featureID = notification.getFeatureID(Resource.class);
      switch (featureID)
      {
        case Resource.RESOURCE__CONTENTS:
        {
          if (!((Resource.Internal)notification.getNotifier()).isLoading())
          {
            handleResource(notification);
          }
          break;
        }
        case Resource.RESOURCE__IS_LOADED:
        {
          loadingTargets = true;
          @SuppressWarnings("unchecked")
          EList<InternalEObject> contents = (EList<InternalEObject>)(EList<?>)((Resource)notification.getNotifier()).getContents();
          for (InternalEObject content : contents)
          {
            // Don't process it as a load if the bidirectional inverse has not been set.
            // This situation happens when objects are added to an empty not-yet-loaded resource.
            //
            if (content.eDirectResource() != null)
            {
              addAdapter(content);
            }
          }
          loadingTargets = false;
          break;
        }
      }
    }
    else if (notifier instanceof ResourceSet)
    {
      if (notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES)
      {
        switch (notification.getEventType())
        {
          case Notification.ADD:
          case Notification.SET:
          //case Notification.REMOVE:
          {
            Resource resource = (Resource)notification.getNewValue();
            loadingTargets = true;
            addAdapter(resource);
            loadingTargets = false;
            break;
          }

          case Notification.ADD_MANY:
          //case Notification.REMOVE_MANY:
          {
            @SuppressWarnings("unchecked") Collection<Resource> resources = (Collection<Resource>)notification.getNewValue();
            loadingTargets = true;
            for (Resource resource : resources)
            {
              addAdapter(resource);
            }
            loadingTargets = false;
          }
        }
      }
    }
  }

  protected boolean shouldRecord(EStructuralFeature feature, EReference containment, Notification notification, EObject eObject)
  {
    return shouldRecord(feature, eObject) &&
      notification.getEventType() != Notification.RESOLVE;
  }

  protected void handleFeature(EStructuralFeature feature, EReference containment, Notification notification, EObject eObject)
  {
    boolean shouldRecord = shouldRecord(feature, containment, notification, eObject);

    List<FeatureChange> changes = null;
    FeatureChange change = null;
    if (shouldRecord)
    {
      changes = getFeatureChanges(eObject);
      change = getFeatureChange(changes, feature);
    }

    switch (notification.getEventType())
    {
      case Notification.RESOLVE:
      case Notification.SET:
      case Notification.UNSET:
      {
        if (change == null && changes != null)
        {
          if (feature.isMany())
          {
            List<Object> oldValue = new BasicEList<Object>((Collection<?>)eObject.eGet(feature));
            int index = notification.getPosition();
            if (index != Notification.NO_INDEX)
            {
              oldValue.set(index, notification.getOldValue());
            }
            change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          }
          else
          {
            Object oldValue = notification.getOldValue();
            change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          }
          ((InternalEList<FeatureChange>)changes).addUnique(change);
        }
        if (containment != null)
        {
          Object newValue = notification.getNewValue();
          if (newValue != null && newValue != Boolean.TRUE && newValue != Boolean.FALSE)
          {
            addAdapter((Notifier)newValue);
          }
        }
        break;
      }
      case Notification.ADD:
      {
        if (change == null && changes != null)
        {
          List<Object> oldValue = new BasicEList<Object>((Collection<?>)eObject.eGet(feature));
          oldValue.remove(notification.getPosition());
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          ((InternalEList<FeatureChange>)changes).addUnique(change);
        }
        if (containment != null)
        {
          Notifier newValue = (Notifier)notification.getNewValue();
          addAdapter(newValue);
        }
        break;
      }
      case Notification.ADD_MANY:
      {
        if (change == null && changes != null)
        {
          List<Object> oldValue = new BasicEList<Object>((Collection<?>)eObject.eGet(feature));
          int position = notification.getPosition();
          for (int i = ((Collection<?>)notification.getNewValue()).size(); --i >= 0;)
          {
            oldValue.remove(position);
          }
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          ((InternalEList<FeatureChange>)changes).addUnique(change);
        }
        if (containment != null)
        {
          @SuppressWarnings("unchecked") Collection<Notifier> newValues = (Collection<Notifier>)notification.getNewValue();
          for (Notifier newValue : newValues)
          {
            addAdapter(newValue);
          }
        }
        break;
      }
      case Notification.REMOVE:
      {
        if (change == null && changes != null)
        {
          List<Object> oldValue = new BasicEList<Object>((Collection<?>)eObject.eGet(feature));

          // If there's no position, the list is being cleared.
          //
          int position = notification.getPosition();
          if (position == Notification.NO_INDEX)
          {
            position = 0;
          }
          oldValue.add(position, notification.getOldValue());
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          ((InternalEList<FeatureChange>)changes).addUnique(change);
        }
        break;
      }
      case Notification.REMOVE_MANY:
      {
        if (change == null && changes != null)
        {
          @SuppressWarnings("unchecked") List<Object> removedValues = (List<Object>)notification.getOldValue();
          List<Object> oldValue = new BasicEList<Object>((Collection<?>)eObject.eGet(feature));
          int[] positions = (int[])notification.getNewValue();
          if (positions == null)
          {
            oldValue.addAll(removedValues);
          }
          else
          {
            for (int i = 0; i < positions.length; ++i)
            {
              oldValue.add(positions[i], removedValues.get(i));
            }
          }
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          ((InternalEList<FeatureChange>)changes).addUnique(change);
        }
        break;
      }
      case Notification.MOVE:
      {
        if (change == null && changes != null)
        {
          EList<Object> oldValue = new BasicEList<Object>((Collection<?>)eObject.eGet(feature));
          int position = notification.getPosition();
          int oldPosition = (Integer)notification.getOldValue();
          oldValue.move(oldPosition, position);
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          ((InternalEList<FeatureChange>)changes).addUnique(change);
        }
        break;
      }
    }
  }

  protected void handleResource(Notification notification)
  {
    Resource resource = null;
    ResourceChange change = null;
    if (isRecording())
    {
      resource = (Resource)notification.getNotifier();
      change = getResourceChange(resource);
    }

    int eventType = notification.getEventType();
    switch (eventType)
    {
      case Notification.SET:
      case Notification.UNSET:
      {
        if (change == null && resource != null)
        {
          EList<Object> oldValue = new BasicEList<Object>(resource.getContents());
          int index = notification.getPosition();
          if (index != Notification.NO_INDEX)
          {
            oldValue.set(index, notification.getOldValue());
          }
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);

          Notifier newValue = (Notifier)notification.getNewValue();
          if (newValue != null)
          {
            addAdapter(newValue);
          }
        }
        break;
      }
      case Notification.ADD:
      {
        if (change == null && resource != null)
        {
          EList<Object> oldValue = new BasicEList<Object>(resource.getContents());
          oldValue.remove(notification.getPosition());
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        Notifier newValue = (Notifier)notification.getNewValue();
        addAdapter(newValue);
        break;
      }
      case Notification.ADD_MANY:
      {
        if (change == null && resource != null)
        {
          EList<Object> oldValue = new BasicEList<Object>(resource.getContents());
          int position = notification.getPosition();
          for (int i = ((Collection<?>)notification.getNewValue()).size(); --i >= 0;)
          {
            oldValue.remove(position);
          }
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        @SuppressWarnings("unchecked") Collection<Notifier> newValues = (Collection<Notifier>)notification.getNewValue();
        for (Notifier newValue : newValues)
        {
          addAdapter(newValue);
        }
        break;
      }
      case Notification.REMOVE:
      {
        if (change == null && resource != null)
        {
          EList<Object> oldValue = new BasicEList<Object>(resource.getContents());

          // If there's no position, the list is being cleared.
          //
          int position = notification.getPosition();
          if (position == Notification.NO_INDEX)
          {
            position = 0;
          }
          oldValue.add(position, notification.getOldValue());
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        break;
      }
      case Notification.REMOVE_MANY:
      {
        if (change == null && resource != null)
        {
          @SuppressWarnings("unchecked") List<Object> removedValues = (List<Object>)notification.getOldValue();
          EList<Object> oldValue = new BasicEList<Object>(resource.getContents());
          int[] positions = (int[])notification.getNewValue();
          if (positions == null)
          {
            oldValue.addAll(removedValues);
          }
          else
          {
            for (int i = 0; i < positions.length; ++i)
            {
              oldValue.add(positions[i], removedValues.get(i));
            }
          }
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        break;
      }
      case Notification.MOVE:
      {
        if (change == null && resource != null)
        {
          EList<Object> oldValue = new BasicEList<Object>(resource.getContents());
          int position = notification.getPosition();
          int oldPosition = (Integer)notification.getOldValue();
          oldValue.move(oldPosition, position);
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        break;
      }
    }
  }

  /**
   * Handles installation of the adapter
   * by adding the adapter to each of the directly contained objects.
   */
  public void setTarget(Notifier target)
  {
    if (!targetObjects.add(target))
    {
      throw new IllegalStateException("The target should not be set more than once");
    }

    if (loadingTargets)
    {
      originalTargetObjects.add(target);
    }

    if (target instanceof EObject)
    {
      EObject targetEObject = (EObject)target;
      if (resolveProxies)
      {
        for (EObject eObject : targetEObject.eContents())
        {
          addAdapter(eObject);
        }
      }
      else
      {
        Iterator<EObject> contents = ((InternalEList<EObject>)targetEObject.eContents()).basicIterator();
        while (contents.hasNext())
        {
          // Avoid adding the adapter to unresolved proxies
          // so that the proxies don't look like objects that have become orphans.
          //
          EObject eObject = contents.next();
          if (!eObject.eIsProxy())
          {
            addAdapter(eObject);
          }
        }
      }

      handleTarget(targetEObject);
    }
    else
    {
      Iterator<?> contents =
        target instanceof ResourceSet ?
          ((ResourceSet)target).getResources().iterator() :
            target instanceof Resource ?
              ((Resource)target).getContents().iterator() :
              null;

      if (contents != null)
      {
        while (contents.hasNext())
        {
          Notifier notifier = (Notifier)contents.next();
          addAdapter(notifier);
        }
      }
    }
  }

  protected void handleTarget(EObject targetEObject)
  {
    if (loadingTargets && eObjectToProxyURIMap != null)
    {
      eObjectToProxyURIMap.put(targetEObject, EcoreUtil.getURI(targetEObject));
    }
  }

  public void unsetTarget(Notifier oldTarget)
  {
    targetObjects.remove(oldTarget);
  }

  protected void addAdapter(Notifier notifier)
  {
    if (notifier != getChangeDescription())
    {
      EList<Adapter> eAdapters = notifier.eAdapters();
      if (!eAdapters.contains(this))
      {
        eAdapters.add(this);
      }
    }
  }

  public Notifier getTarget()
  {
    return null;
  }

  public boolean isAdapterForType(Object type)
  {
    return false;
  }
}