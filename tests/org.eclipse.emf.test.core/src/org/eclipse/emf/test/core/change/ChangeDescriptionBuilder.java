/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.change;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.change.util.BasicChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * Implementation of the {@link BasicChangeRecorder} that provides methods
 * to describe changes on objects.   
 * @since 2.3
 */
public class ChangeDescriptionBuilder extends BasicChangeRecorder
{
  public ChangeDescriptionBuilder()
  {
    super();
    setChangeDescription(createChangeDescription());
    setRecording(true);
  }
  
  public void recordUnsetFeature(EObject eObject, EStructuralFeature feature)
  {
    recordFeature(eObject, feature, null, false);
  }
  
  public void recordSetFeature(EObject eObject, EStructuralFeature feature, Object value)
  {
    recordFeature(eObject, feature, value, true);
  }
  
  protected void recordFeature(EObject eObject, EStructuralFeature feature, Object value, boolean isSet)
  {
    if (shouldRecord(feature, eObject))
    {
      List<FeatureChange> changes = getFeatureChanges(eObject);
      FeatureChange change = getFeatureChange(changes, feature);
      if (change == null)
      {
        if (feature.isMany() && !(value instanceof EList<?>))
        {
          if (value instanceof Collection<?>)
          {
            value = new BasicEList<Object>((Collection<?>)value);
          }
          else
          {
            EList<Object> list = new BasicEList<Object>();
            list.add(value);
            value = list;
          }
        }
        change = createFeatureChange(eObject, feature, value, isSet);
        ((InternalEList<FeatureChange>)getFeatureChanges(eObject)).addUnique(change);
      }
    }
  }
  
  public void recordResourceContents(Resource resource, List<? extends EObject> contents)
  {
    if (isRecording())
    {
      ResourceChange change = getResourceChange(resource);
      if (change == null)
      {
        
        @SuppressWarnings("unchecked") EList<Object> oldValue = contents == null ?
          new BasicEList<Object>(0) :
          contents instanceof EList<?> ?
            (EList<Object>)(EList<?>)contents :
            new BasicEList<Object>(contents);
        change = createResourceChange(resource, oldValue);
        getResourceChanges().add(change);
      }
    }
  }  
}
