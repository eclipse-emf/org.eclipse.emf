/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ChangeRecorder.java,v 1.11 2004/06/22 22:42:59 marcelop Exp $
 */
package org.eclipse.emf.ecore.change.util;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * A change recorder for the tree contents of a collection of EObjects. It monitors the specified objects and
 * then produces a {@link ChangeDescription change model} representing the changes needed to reverse (undo) all
 * the model changes made while recording.
 */
public class ChangeRecorder implements Adapter
{
  protected boolean recording;

  protected ChangeDescription changeDescription;

  protected List targetObjects = new FastEList();

  protected List initialTargetObjects = new FastEList();

  protected boolean loadingTargets;

  public ChangeRecorder()
  {
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

  public ChangeRecorder(Collection rootObjects)
  {
    beginRecording(rootObjects);
  }

  public void beginRecording(Collection rootObjects)
  {
    changeDescription = createChangeDescription();

    initialTargetObjects.clear();
    initialTargetObjects.addAll(targetObjects);

    loadingTargets = true;
    for (Iterator iter = rootObjects.iterator(); iter.hasNext();)
    {
      Notifier notifier = (Notifier)iter.next();
      addAdapter(notifier);
    }
    loadingTargets = false;

    recording = true;
  }

  public ChangeDescription endRecording()
  {
    if (recording)
    {
      recording = false;

      List orphanedObjects = changeDescription.getObjectsToAttach();
      for (Iterator iter = targetObjects.iterator(); iter.hasNext();)
      {
        Object target = iter.next();
        if (target instanceof EObject)
        {
          EObject eObject = (EObject)target;
          if (eObject.eContainer() == null && eObject.eResource() == null)
          {
            orphanedObjects.add(eObject);
          }
        }
      }

      targetObjects.removeAll(initialTargetObjects);
      changeDescription.getObjectsToDetach().addAll(targetObjects);
      targetObjects.clear();

      for (Iterator iter = changeDescription.getObjectChanges().iterator(); iter.hasNext();)
      {
        Map.Entry mapEntry = (Map.Entry)iter.next();
        EObject eObject = (EObject)mapEntry.getKey();
        for (Iterator featureChangeIter = ((List)mapEntry.getValue()).iterator(); featureChangeIter.hasNext();)
        {
          finalizeChange((FeatureChange)featureChangeIter.next(), eObject);
        }
      }

      for (Iterator iter = changeDescription.getResourceChanges().iterator(); iter.hasNext();)
      {
        ResourceChange resourceChange = (ResourceChange)iter.next();
        finalizeChange(resourceChange);
      }

      return changeDescription;
    }

    return null;
  }

  public void notifyChanged(Notification notification)
  {
    if (!recording)
      return;

    Object notifier = notification.getNotifier();
    if (notifier instanceof EObject)
    {
      Object feature = notification.getFeature();
      if (feature instanceof EReference)
      {
        EReference eReference = (EReference)feature;
        handleFeature(eReference, eReference.isContainment() ? eReference : null, notification);
      }
      else
      {
        handleFeature((EStructuralFeature)feature, null, notification);
      }
    }
    else if (notifier instanceof Resource)
    {
      int featureID = notification.getFeatureID(Resource.class);
      if (loadingTargets)
      {
        if (featureID == Resource.RESOURCE__IS_LOADED)
        {
          Resource resource = (Resource)notification.getNotifier();
          for (Iterator i = resource.getContents().iterator(); i.hasNext();)
          {
            addAdapter((Notifier)i.next());
          }
          loadingTargets = false;
        }
      }
      else if (featureID == Resource.RESOURCE__CONTENTS)
      {
        handleResource(notification);
      }
    }
    else if (notifier instanceof ResourceSet)
    {
      if (notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES)
      {
        if (notification.getEventType() == Notification.ADD)
        {
          Notifier newValue = (Notifier)notification.getNewValue();
          loadingTargets = true;
          addAdapter(newValue);
        }
      }
    }
  }

  protected void handleFeature(EStructuralFeature feature, EReference containment, Notification notification)
  {
    if (!feature.isChangeable())
      return;

    EObject eObject = (EObject)notification.getNotifier();
    List changes = getFeatureChanges(eObject);
    FeatureChange change = getFeatureChange(changes, feature);

    int event = notification.getEventType();
    switch (event)
    {
      case Notification.SET:
      case Notification.UNSET: {
        if (change == null)
        {
          if (feature.isMany())
          {
            List oldValue = new BasicEList((Collection)eObject.eGet(feature));
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
          changes.add(change);
        }
        if (containment != null)
        {
          Notifier newValue = (Notifier)notification.getNewValue();
          if (newValue != null)
          {
            addAdapter(newValue);
          }
        }
        break;
      }
      case Notification.ADD: {
        if (change == null)
        {
          List oldValue = new BasicEList((Collection)eObject.eGet(feature));
          oldValue.remove(notification.getPosition());
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          changes.add(change);
        }
        if (containment != null)
        {
          Notifier newValue = (Notifier)notification.getNewValue();
          addAdapter(newValue);
        }
        break;
      }
      case Notification.ADD_MANY: {
        if (change == null)
        {
          List oldValue = new BasicEList((Collection)eObject.eGet(feature));
          int position = notification.getPosition();
          for (int i = ((Collection)notification.getNewValue()).size(); --i >= 0;)
          {
            oldValue.remove(position);
          }
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          changes.add(change);
        }
        if (containment != null)
        {
          Collection newValues = (Collection)notification.getNewValue();
          for (Iterator i = newValues.iterator(); i.hasNext();)
          {
            Notifier newValue = (Notifier)i.next();
            addAdapter(newValue);
          }
        }
        break;
      }
      case Notification.REMOVE: {
        if (change == null)
        {
          List oldValue = new BasicEList((Collection)eObject.eGet(feature));

          // If there's no position, the list is being cleared.
          //
          int position = notification.getPosition();
          if (position == Notification.NO_INDEX)
          {
            position = 0;
          }
          oldValue.add(position, notification.getOldValue());
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          changes.add(change);
        }
        break;
      }
      case Notification.REMOVE_MANY: {
        if (change == null)
        {
          List removedValues = (List)notification.getOldValue();
          List oldValue = new BasicEList((Collection)eObject.eGet(feature));
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
          changes.add(change);
        }
        break;
      }
      case Notification.MOVE: {
        if (change == null)
        {
          EList oldValue = new BasicEList((Collection)eObject.eGet(feature));
          int position = notification.getPosition();
          int oldPosition = ((Integer)notification.getOldValue()).intValue();
          oldValue.move(oldPosition, position);
          change = createFeatureChange(eObject, feature, oldValue, notification.wasSet());
          changes.add(change);
        }
        break;
      }
    }
  }

  protected void handleResource(Notification notification)
  {
    Resource resource = (Resource)notification.getNotifier();
    ResourceChange change = getResourceChange(resource);

    int eventType = notification.getEventType();
    switch (eventType)
    {
      case Notification.ADD: {
        if (change == null)
        {
          EList oldValue = new BasicEList(resource.getContents());
          oldValue.remove(notification.getPosition());
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        Notifier newValue = (Notifier)notification.getNewValue();
        addAdapter(newValue);
        break;
      }
      case Notification.ADD_MANY: {
        if (change == null)
        {
          EList oldValue = new BasicEList(resource.getContents());
          int position = notification.getPosition();
          for (int i = ((Collection)notification.getNewValue()).size(); --i >= 0;)
          {
            oldValue.remove(position);
          }
          change = createResourceChange(resource, oldValue);
          getResourceChanges().add(change);
        }
        Collection newValues = (Collection)notification.getNewValue();
        for (Iterator i = newValues.iterator(); i.hasNext();)
        {
          Notifier newValue = (Notifier)i.next();
          addAdapter(newValue);
        }
        break;
      }
      case Notification.REMOVE: {
        if (change == null)
        {
          EList oldValue = new BasicEList(resource.getContents());

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
      case Notification.REMOVE_MANY: {
        if (change == null)
        {
          List removedValues = (List)notification.getOldValue();
          EList oldValue = new BasicEList(resource.getContents());
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
      case Notification.MOVE: {
        if (change == null)
        {
          EList oldValue = new BasicEList(resource.getContents());
          int position = notification.getPosition();
          int oldPosition = ((Integer)notification.getOldValue()).intValue();
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
      initialTargetObjects.add(target);
    }

    Collection contents = target instanceof EObject ? ((EObject)target).eContents() : target instanceof ResourceSet
      ? ((ResourceSet)target).getResources() : target instanceof Resource ? ((Resource)target).getContents() : null;

    if (contents != null)
    {
      for (Iterator i = contents.iterator(); i.hasNext();)
      {
        Notifier notifier = (Notifier)i.next();
        addAdapter(notifier);
      }
    }
  }

  protected void addAdapter(Notifier notifier)
  {
    EList eAdapters = notifier.eAdapters();
    if (!eAdapters.contains(this))
      eAdapters.add(this);
  }

  public Notifier getTarget()
  {
    return null;
  }

  public boolean isAdapterForType(Object type)
  {
    return false;
  }

  protected EList getResourceChanges()
  {
    return changeDescription.getResourceChanges();
  }

  protected ResourceChange getResourceChange(Resource resource)
  {
    List resourceChanges = getResourceChanges();
    for (int i = 0, size = resourceChanges.size(); i < size;)
    {
      ResourceChange resourceChange = (ResourceChange)resourceChanges.get(i++);
      if (resourceChange.getResource() == resource)
      {
        return resourceChange;
      }
    }
    return null;
  }

  protected List getFeatureChanges(EObject eObject)
  {
    List featureChanges = (List)changeDescription.getObjectChanges().get(eObject);
    if (featureChanges == null)
    {
      Map.Entry entry = ChangeFactory.eINSTANCE.createEObjectToChangesMapEntry(eObject);
      changeDescription.getObjectChanges().add(entry);
      featureChanges = (EList)entry.getValue();
    }
    return featureChanges;
  }

  protected FeatureChange getFeatureChange(List featureChanges, EStructuralFeature eStructuralFeature)
  {
    for (int i = 0, size = featureChanges.size(); i < size;)
    {
      FeatureChange featureChange = (FeatureChange)featureChanges.get(i++);
      if (featureChange.getFeature() == eStructuralFeature)
      {
        return featureChange;
      }
    }
    return null;
  }

  protected void finalizeChange(ResourceChange change)
  {
    EList oldList = new BasicEList(change.getResource().getContents());
    EList newList = change.getValue();
    createListChanges(oldList, newList, change.getListChanges());
  }

  protected void finalizeChange(FeatureChange change, EObject eObject)
  {
    if (change.isSet())
    {
      EStructuralFeature feature = change.getFeature();
      if (feature.isMany())
      {
        EList oldList = new BasicEList((EList)eObject.eGet(feature));
        EList newList = (EList)change.getValue();
        createListChanges(oldList, newList, change.getListChanges());
      }
    }
  }

  protected void createListChanges(EList oldList, EList newList, EList changesList)
  {
    int index = 0;
    for (Iterator objects = newList.iterator(); objects.hasNext(); ++index)
    {
      Object newObject = objects.next();
      if (oldList.size() <= index)
      {
        createAddListChange(oldList, changesList, newObject, index);
      }
      else
      {
        boolean done;
        do
        {
          done = true;
          Object targetObject = oldList.get(index);
          if (targetObject == null ? newObject != null : !targetObject.equals(newObject))
          {
            int position = EcoreUtil.indexOf(oldList, newObject, index);
            if (position != -1)
            {
              int targetIndex = EcoreUtil.indexOf(newList, targetObject, index);
              if (targetIndex == -1)
              {
                createRemoveListChange(oldList, changesList, newObject, index);
                done = false;
              }
              else if (targetIndex > position)
              {
                if (oldList.size() <= targetIndex)
                {
                  targetIndex = oldList.size() - 1;
                }
                createMoveListChange(oldList, changesList, newObject, index, targetIndex);
                done = false;
              }
              else
              {
                createMoveListChange(oldList, changesList, newObject, index, targetIndex);
              }
            }
            else
            {
              createAddListChange(oldList, changesList, newObject, index);
            }
          }
        }
        while (!done);
      }
    }
    for (int i = oldList.size(); i > index;)
    {
      createRemoveListChange(oldList, changesList, null, --i);
    }
  }
  
  /**
   * Convenience method added to allow subclasses to modify the default implementation 
   * for the scenario in which an element was added to the monitored list.
   * @see #createListChanges(EList, EList, EList) 
   */
  protected void createAddListChange(EList oldList, EList changesList, Object newObject, int index)
  {
    ListChange listChange = createListChange(changesList, ChangeKind.ADD_LITERAL, index);
    listChange.getValues().add(newObject);
    oldList.add(index, newObject);    
  }

  /**
   * Convenience method added to allow subclasses to modify the default implementation 
   * for the scenario in which an element was removed from the monitored list.
   * @see #createListChanges(EList, EList, EList) 
   */
  protected void createRemoveListChange(EList oldList, EList changesList, Object newObject, int index)
  {
    ListChange listChange = createListChange(changesList, ChangeKind.REMOVE_LITERAL, index);
    oldList.remove(index);
  }

  /**
   * Convenience method added to allow subclasses to modify the default implementation 
   * for the scenario in which an element was moved in the monitored list.
   * @see #createListChanges(EList, EList, EList) 
   */
  protected void createMoveListChange(EList oldList, EList changesList, Object newObject, int index, int targetIndex)
  {
    ListChange listChange = createListChange(changesList, ChangeKind.MOVE_LITERAL, index);
    listChange.setMoveToIndex(targetIndex);
    oldList.move(targetIndex, index);
   }  

  protected ListChange createListChange(EList changesList, ChangeKind kind, int index)
  {
    ListChange listChange = ChangeFactory.eINSTANCE.createListChange();
    listChange.setKind(kind);
    listChange.setIndex(index);
    changesList.add(listChange);
    return listChange;
  }

  protected FeatureChange createFeatureChange(EObject eObject, EStructuralFeature eStructuralFeature, Object value, boolean isSet)
  {
    return ChangeFactory.eINSTANCE.createFeatureChange(eStructuralFeature, value, isSet);
  }

  protected ResourceChange createResourceChange(Resource resource, EList value)
  {
    return ChangeFactory.eINSTANCE.createResourceChange(resource, value);
  }

  protected ChangeDescription createChangeDescription()
  {
    return ChangeFactory.eINSTANCE.createChangeDescription();
  }

  protected static class FastEList extends BasicEList
  {
    public FastEList()
    {
      super();
    }

    public FastEList(Collection collection)
    {
      super(collection);
    }

    protected boolean useEquals()
    {
      return false;
    }
  }

}