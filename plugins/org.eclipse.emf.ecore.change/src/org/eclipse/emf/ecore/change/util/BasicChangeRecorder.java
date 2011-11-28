/**
 * <copyright>
 *
 * Copyright (c) 2007-2011 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: BasicChangeRecorder.java,v 1.6 2011/11/28 09:14:06 emerks Exp $
 */
package org.eclipse.emf.ecore.change.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * This class provides the basic methods required to implement a change recorder.
 * @since 2.3
 */
public class BasicChangeRecorder extends ListDifferenceAnalyzer
{
  protected boolean recording;
  protected boolean recordingTransientFeatures = true;

  protected ChangeDescription changeDescription;

  /**
   * @return true if this change recorder is recording or false otherwise.
   */
  public boolean isRecording()
  {
    return recording;
  }

  protected void setRecording(boolean recording)
  {
    this.recording = recording;
  }

  /**
   * @return true if this change recorder is recording transient features or false otherwise.
   * @since 2.7
   */
  public boolean isRecordingTransientFeatures()
  {
    return recordingTransientFeatures;
  }

  /**
   * @since 2.7
   */
  public void setRecordingTransientFeatures(boolean recordingTransientFeatures)
  {
    this.recordingTransientFeatures = recordingTransientFeatures;
  }

  protected ChangeDescription getChangeDescription()
  {
    return changeDescription;
  }

  protected void setChangeDescription(ChangeDescription changeDescription)
  {
    this.changeDescription = changeDescription;
  }

  /**
   * Disposes this change recorder.  This method ends a recording without
   * consolidating the changes.
   */
  public void dispose()
  {
    changeDescription = null;
  }

  /**
   * <p>Summarizes the changes made to the analyzed objects on the {@link ChangeDescription change description}
   * returned by the {@link #endRecording()} without ending the recording.</p>
   *
   * <p>This method doesn't do anything if this ChangeRecorder is not recording.</p>
   *
   * @return the {@link ChangeDescription} or <tt class="code">null</tt> if there is nothing being recorded.
   */
  public ChangeDescription summarize()
  {
    if (isRecording())
    {
      consolidateChanges();
      return getChangeDescription();
    }
    return null;
  }

  /**
   * Ends the recording and consolidates the changes on the {@link ChangeDescription change description}.
   * @return the {@link ChangeDescription} or <tt class="code">null</tt> if there is nothing being recorded.
   */
  public ChangeDescription endRecording()
  {
    if (isRecording())
    {
      setRecording(false);
      consolidateChanges();
      return getChangeDescription();
    }
    return null;
  }

  /**
   * Consolidates the changes that have happen since the last consolidation.
   */
  protected void consolidateChanges()
  {
    ChangeDescription changeDescription = getChangeDescription();
    for (Map.Entry<EObject, EList<FeatureChange>> entry : changeDescription.getObjectChanges())
    {
      EObject eObject = entry.getKey();
      for (FeatureChange featureChange : entry.getValue())
      {
        finalizeChange(featureChange, eObject);
      }
    }

    for (ResourceChange resourceChange : changeDescription.getResourceChanges())
    {
      finalizeChange(resourceChange);
    }

    eliminateEmptyChanges();
  }

  /**
   * Eliminates changes that result in a state that's equal to the current state.
   */
  protected void eliminateEmptyChanges()
  {
    ChangeDescription changeDescription = getChangeDescription();
    for (Iterator<Map.Entry<EObject, EList<FeatureChange>>> i = changeDescription.getObjectChanges().iterator(); i.hasNext();)
    {
      Map.Entry<EObject, EList<FeatureChange>> entry = i.next();
      EObject eObject = entry.getKey();
      EList<FeatureChange> featureChanges = entry.getValue();
      for (Iterator<FeatureChange> j = featureChanges.iterator(); j.hasNext(); )
      {
        FeatureChange featureChange  = j.next();
        EStructuralFeature feature = featureChange.getFeature();
        if (featureChange.isSet() == eObject.eIsSet(feature))
        {
          Object value = featureChange.getValue();
          Object eObjectValue = eObject.eGet(feature);
          if (value == null ? eObject.eGet(feature) == null : value.equals(eObjectValue))
          {
            j.remove();
          }
        }
      }
      if (featureChanges.isEmpty())
      {
        i.remove();
      }
    }

    for (Iterator<ResourceChange> i = changeDescription.getResourceChanges().iterator(); i.hasNext(); )
    {
      ResourceChange resourceChange  = i.next();
      if (resourceChange.getResource().getContents().equals(resourceChange.getValue()))
      {
        i.remove();
      }
    }
  }

  protected boolean shouldRecord(EStructuralFeature feature, EObject eObject)
  {
    return isRecording() &&
      !feature.isDerived() &&
      (isRecordingTransientFeatures() || !feature.isTransient()) &&
      feature != EcorePackage.Literals.ECLASS__ESUPER_TYPES &&
      feature != EcorePackage.Literals.ETYPED_ELEMENT__ETYPE &&
      feature != EcorePackage.Literals.EOPERATION__EEXCEPTIONS &&
      feature != EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME;
  }

  protected void finalizeChange(ResourceChange change)
  {
    EList<Object> oldList = new BasicEList.FastCompare<Object>(change.getResource().getContents());
    EList<?> newList = change.getValue();
    change.getListChanges().clear();
    createListChanges(oldList, newList, change.getListChanges());
  }

  protected void finalizeChange(FeatureChange change, EObject eObject)
  {
    if (change.isSet())
    {
      EStructuralFeature feature = change.getFeature();
      if (feature.isMany())
      {
        EList<Object> oldList = new BasicEList<Object>((EList<?>)eObject.eGet(feature));
        EList<?> newList = (EList<?>)change.getValue();
        EList<ListChange> listChanges = change.getListChanges();
        listChanges.clear();
        createListChanges(oldList, newList, listChanges);
      }
    }
  }

  protected EList<ResourceChange> getResourceChanges()
  {
    return getChangeDescription().getResourceChanges();
  }

  protected ResourceChange getResourceChange(Resource resource)
  {
    List<ResourceChange> resourceChanges = getResourceChanges();
    for (int i = 0, size = resourceChanges.size(); i < size;)
    {
      ResourceChange resourceChange = resourceChanges.get(i++);
      if (resourceChange.getResource() == resource)
      {
        return resourceChange;
      }
    }
    return null;
  }

  protected List<FeatureChange> getFeatureChanges(EObject eObject)
  {
    ChangeDescription changeDescription = getChangeDescription();
    List<FeatureChange> featureChanges = changeDescription.getObjectChanges().get(eObject);
    if (featureChanges == null)
    {
      Map.Entry<EObject, EList<FeatureChange>> entry = ChangeFactory.eINSTANCE.createEObjectToChangesMapEntry(eObject);
      changeDescription.getObjectChanges().add(entry);
      featureChanges = entry.getValue();
    }
    return featureChanges;
  }

  protected FeatureChange getFeatureChange(List<FeatureChange> featureChanges, EStructuralFeature eStructuralFeature)
  {
    for (int i = 0, size = featureChanges.size(); i < size;)
    {
      FeatureChange featureChange = featureChanges.get(i++);
      if (featureChange.getFeature() == eStructuralFeature)
      {
        return featureChange;
      }
    }
    return null;
  }

  protected FeatureChange createFeatureChange(EObject eObject, EStructuralFeature eStructuralFeature, Object value, boolean isSet)
  {
    return ChangeFactory.eINSTANCE.createFeatureChange(eStructuralFeature, value, isSet);
  }

  protected ResourceChange createResourceChange(Resource resource, EList<Object> value)
  {
    return ChangeFactory.eINSTANCE.createResourceChange(resource, value);
  }

  protected ChangeDescription createChangeDescription()
  {
    return ChangeFactory.eINSTANCE.createChangeDescription();
  }
}
