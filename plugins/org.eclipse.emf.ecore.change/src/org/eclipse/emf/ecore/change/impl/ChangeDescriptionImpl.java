/**
 * <copyright>
 *
 * Copyright (c) 2003-2011 IBM Corporation and others.
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
 * $Id: ChangeDescriptionImpl.java,v 1.24 2011/08/29 20:11:50 khussey Exp $
 */
package org.eclipse.emf.ecore.change.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl#getObjectChanges <em>Object Changes</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl#getObjectsToDetach <em>Objects To Detach</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl#getObjectsToAttach <em>Objects To Attach</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl#getResourceChanges <em>Resource Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChangeDescriptionImpl extends EObjectImpl implements ChangeDescription
{
  /**
   * The cached value of the '{@link #getObjectChanges() <em>Object Changes</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObjectChanges()
   * @generated
   * @ordered
   */
  protected EMap<EObject, EList<FeatureChange>> objectChanges;

  /**
   * The cached value of the '{@link #getObjectsToDetach() <em>Objects To Detach</em>}' reference list.
   * <!-- begin-user-doc -->
   * The Objects to Detach list is first calculated when the {@link #getObjectsToDetach()}
   * method is invoked and reset when new changes are described.
   * <!-- end-user-doc -->
   * @see #getObjectsToDetach()
   * @generated
   * @ordered
   */
  protected EList<EObject> objectsToDetach;

  /**
   * The cached value of the '{@link #getObjectsToAttach() <em>Objects To Attach</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObjectsToAttach()
   * @generated
   * @ordered
   */
  protected EList<EObject> objectsToAttach;

  /**
   * The cached value of the '{@link #getResourceChanges() <em>Resource Changes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceChanges()
   * @generated
   * @ordered
   */
  protected EList<ResourceChange> resourceChanges;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ChangeDescriptionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ChangePackage.Literals.CHANGE_DESCRIPTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<EObject, EList<FeatureChange>> getObjectChanges()
  {
    if (objectChanges == null)
    {
      objectChanges = new EcoreEMap<EObject,EList<FeatureChange>>(ChangePackage.Literals.EOBJECT_TO_CHANGES_MAP_ENTRY, EObjectToChangesMapEntryImpl.class, this, ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES);
    }
    return objectChanges;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getObjectsToDetachGen()
  {
    if (objectsToDetach == null)
    {
      objectsToDetach = new EObjectEList<EObject>(EObject.class, this, ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH);
    }
    return objectsToDetach;
  }

  protected static List<EObject> getContainedEObjects(List<FeatureMap.Entry> featureMapEntries)
  {
    List<EObject> result = new ArrayList<EObject>();
    for (FeatureMap.Entry entry : featureMapEntries)
    {
      EStructuralFeature feature = entry.getEStructuralFeature();
      if (feature instanceof EReference && ((EReference)feature).isContainment())
      {
        result.add((EObject)entry.getValue());
      }
    }
    return result;
  }

  public EList<EObject> getObjectsToDetach()
  {
    List<EObject> objectsBeforeChange = new UniqueEList.FastCompare<EObject>();
    List<EObject> objectsAfterChange = new UniqueEList.FastCompare<EObject>();

    if (!getObjectChanges().isEmpty())
    {
      preApply(false);

      for (Map.Entry<EObject,EList<FeatureChange>> entry : getObjectChanges())
      {
        EObject objectToChange = entry.getKey();
        for (FeatureChange featureChange : entry.getValue())
        {
          EStructuralFeature feature = featureChange.getFeature();
          if (feature != null && FeatureMapUtil.isFeatureMap(feature))
          {
            @SuppressWarnings("unchecked") List<FeatureMap.Entry> beforeValue = (List<FeatureMap.Entry>)featureChange.getValue();
            objectsBeforeChange.addAll(getContainedEObjects(beforeValue));
            @SuppressWarnings("unchecked")
            List<FeatureMap.Entry> afterValue = (List<FeatureMap.Entry>)objectToChange.eGet(feature);
            objectsAfterChange.addAll(getContainedEObjects(afterValue));
          }
          else if (feature instanceof EReference && ((EReference)feature).isContainment())
          {
            if (feature.isMany())
            {
              @SuppressWarnings("unchecked") List<EObject> beforeValue = (List<EObject>)featureChange.getValue();
              objectsBeforeChange.addAll(beforeValue);
              @SuppressWarnings("unchecked") List<EObject> afterValue = (List<EObject>)objectToChange.eGet(feature);
              objectsAfterChange.addAll(afterValue);
            }
            else
            {
              Object value = featureChange.getValue();
              if (value != null) objectsBeforeChange.add((EObject)value);
              value = objectToChange.eGet(feature);
              if (value != null) objectsAfterChange.add((EObject)value);
            }
          }
        }
      }
    }

    if (!getResourceChanges().isEmpty())
    {
      for (ResourceChange resourceChange : getResourceChanges())
      {
        Resource resource = resourceChange.getResource();
        if (resource == null)
        {
          resource = eResource();
        }

        if (resource != null)
        {
          EList<EObject> currentContentCopy = new BasicEList<EObject>(resource.getContents());
          for (ListChange listChange : resourceChange.getListChanges())
          {
            @SuppressWarnings("unchecked") EList<Object> list = (EList<Object>)(EList<?>)currentContentCopy;
            listChange.apply(list);
          }

          objectsBeforeChange.addAll(currentContentCopy);
          objectsAfterChange.addAll(resource.getContents());
        }
      }
    }

    // Isolating the new objects
    objectsAfterChange.removeAll(objectsBeforeChange);

    // getObjectsToDetachGen() should be changed only if required
    getObjectsToDetachGen().retainAll(objectsAfterChange);
    getObjectsToDetachGen().addAll(objectsAfterChange);

    return getObjectsToDetachGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getObjectsToAttach()
  {
    if (objectsToAttach == null)
    {
      objectsToAttach = new EObjectContainmentEList<EObject>(EObject.class, this, ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH);
    }
    return objectsToAttach;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ResourceChange> getResourceChanges()
  {
    if (resourceChanges == null)
    {
      resourceChanges = new EObjectContainmentEList<ResourceChange>(ResourceChange.class, this, ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES);
    }
    return resourceChanges;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void apply()
  {
    preApply(false);

    // Apply the change.
    //
    for (Map.Entry<EObject,EList<FeatureChange>> entry : getObjectChanges())
    {
      EObject objectToChange = entry.getKey();
      for (FeatureChange featureChange : entry.getValue())
      {
        featureChange.apply(objectToChange);
      }
    }

    for (ResourceChange resourceChange : getResourceChanges())
    {
      resourceChange.apply();
    }

    // Delete the change information because it is invalid now that the objects have been changed.
    //
    getObjectsToAttach().clear();
    getObjectChanges().clear();
    getResourceChanges().clear();
    oldContainmentInformation = null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void applyAndReverse()
  {
    preApply(true);

    List<EObject> objectsBeforeApply = new UniqueEList.FastCompare<EObject>();
    List<EObject> objectsAfterApply = new UniqueEList.FastCompare<EObject>();

    // Apply the change and reverse the change information.
    //
    for (Map.Entry<EObject, EList<FeatureChange>> entry : getObjectChanges())
    {
      EObject objectToChange = entry.getKey();
      for (FeatureChange featureChange : entry.getValue())
      {
        EStructuralFeature feature  = featureChange.getFeature();

        int featureKind =
          feature != null && FeatureMapUtil.isFeatureMap(feature) ?
            3 :
            feature instanceof EReference && ((EReference)feature).isContainment() ?
              feature.isMany() ?
                1 :
                2 :
            0;
        switch (featureKind)
        {
          case 1:
          {
            @SuppressWarnings("unchecked") List<EObject> beforeValue = (List<EObject>)objectToChange.eGet(feature);
            objectsBeforeApply.addAll(beforeValue);
            break;
          }
          case 2:
          {
            Object value = objectToChange.eGet(feature);
            if (value != null)
            {
              objectsBeforeApply.add((EObject)objectToChange.eGet(feature));
            }
            break;
          }
          case 3:
          {
            @SuppressWarnings("unchecked") List<FeatureMap.Entry> beforeValue = (List<FeatureMap.Entry>)objectToChange.eGet(feature);
            objectsBeforeApply.addAll(getContainedEObjects(beforeValue));
            break;
          }
        }

        featureChange.applyAndReverse(objectToChange);

        switch (featureKind)
        {
          case 1:
          {
            @SuppressWarnings("unchecked") List<EObject> afterValue = (List<EObject>)objectToChange.eGet(feature);
            objectsAfterApply.addAll(afterValue);
            break;
          }
          case 2:
          {
            Object value = objectToChange.eGet(feature);
            if (value != null)
            {
              objectsAfterApply.add((EObject)objectToChange.eGet(feature));
            }
            break;
          }
          case 3:
          {
            @SuppressWarnings("unchecked") List<FeatureMap.Entry> afterValue = (List<FeatureMap.Entry>)objectToChange.eGet(feature);
            objectsAfterApply.addAll(getContainedEObjects(afterValue));
            break;
          }
        }
      }
    }

    for (ResourceChange resourceChange : getResourceChanges())
    {
      Resource resource = resourceChange.getResource();
      if (resource != null)
      {
        objectsBeforeApply.addAll(resource.getContents());
      }
      resourceChange.applyAndReverse();
      if (resource != null)
      {
        objectsAfterApply.addAll(resource.getContents());
      }
    }

    // The next line leaves objectsBeforeApply with all the objects that were
    // added during the last recording.
    objectsBeforeApply.removeAll(objectsAfterApply);

    // Reverse the objects to attach and detach lists.
    //
    getObjectsToAttach().clear();
    for (EObject eObject : objectsBeforeApply)
    {
      if (eObject.eContainer() == null && eObject.eResource() == null)
      {
        getObjectsToAttach().add(eObject);
      }
    }
    oldContainmentInformation = null;
  }

  public void copyAndReverse(final Map<EObject, URI> eObjectToProxyURIMap)
  {
    // Cache this list early, because it's result depends on calling preApply(false).
    //
    EList<EObject> objectToDetach = getObjectsToDetach();

    // Get ready to apply the changes, even though we really won't apply them to the originals.
    //
    preApply(true);

    EcoreUtil.Copier copier =
      new EcoreUtil.Copier()
      {
        private static final long serialVersionUID = 1L;

        @Override
        public EObject copy(EObject eObject)
        {
          // Ensure that objects that appear both as a contained child and directly in the list, will not be copied twice.
          //
          EObject result = get(eObject);
          if (result != null)
          {
            return result;
          }
          else
          {
            // Create proxies copies for objects in the proxy map.
            //
            URI proxyURI = eObjectToProxyURIMap.get(eObject);
            if (proxyURI != null)
            {
              InternalEObject copyEObject = (InternalEObject)createCopy(eObject);
              copyEObject.eSetProxyURI(proxyURI);
              put(eObject, copyEObject);
              return copyEObject;
            }
            else
            {
              // Otherwise create a normal copy.
              //
              return super.copy(eObject);
            }
          }
        }
      };
    Collection<EObject> newObjectsToAttach = copier.copyAll(objectToDetach);

    // The children of the objects to attach might become orphans;
    // we'll need to check for that later.
    //
    Set<EObject> potentialOrphans = new HashSet<EObject>();
    for (EObject eObject : newObjectsToAttach)
    {
      for (Iterator<EObject> i = eObject.eAllContents(); i.hasNext(); )
      {
        potentialOrphans.add(i.next());
      }
    }

    // Apply the change to the proxified copy and reverse the change information.
    //
    EMap<EObject, EList<FeatureChange>> oldObjectChanges =  new BasicEMap<EObject, EList<FeatureChange>>();
    oldObjectChanges.putAll(getObjectChanges());
    for (Map.Entry<EObject, EList<FeatureChange>> entry : oldObjectChanges)
    {
      // Get the copied version of the object to be changed.
      //
      EObject objectToChange = entry.getKey();
      EObject copiedObjectToChange = copier.get(objectToChange);

      for (FeatureChange featureChange : entry.getValue())
      {
        // If there is a real, non-proxy copy...
        //
        if (copiedObjectToChange != null && !copiedObjectToChange.eIsProxy())
        {
          // Apply the change to that copy.
          //
          featureChange.applyAndReverse(copiedObjectToChange);
        }
        else
        {
          // Otherwise use copy and reverse for the original object.
          //
          featureChange.reverse(objectToChange);
        }

        EObject referenceValue = featureChange.getReferenceValue();
        if (referenceValue != null)
        {
          // Try to get the copy corresponding to the reference value.
          //
          EObject copiedReferenceValue = copier.get(referenceValue);
          if (copiedReferenceValue == null)
          {
            // If there isn't one, create a proxified copy of it.
            //
            copiedReferenceValue = copier.copy(referenceValue);
          }
          // Switch to the copy.
          //
          featureChange.setReferenceValue(copiedReferenceValue);
        }
        else
        {
          // Otherwise deal with the list changes.
          //
          for (ListChange listChange : featureChange.getListChanges())
          {
            for (ListIterator<EObject> referenceValues = listChange.getReferenceValues().listIterator(); referenceValues.hasNext();)
            {
              // Deal with each reference value in exactly the same way as the single value case.
              //
              referenceValue = referenceValues.next();
              EObject copiedReferenceValue = copier.get(referenceValue);
              if (copiedReferenceValue == null)
              {
                copiedReferenceValue = copier.copy(referenceValue);
              }
              referenceValues.set(copiedReferenceValue);
            }
          }
        }
      }

      // Remove the entry for the original object.
      //
      objectChanges.remove(objectToChange);

      // Ensure that we have a copy of the original object, even if it's just a proxified one.
      //
      if (copiedObjectToChange == null)
      {
        copiedObjectToChange = copier.copy(objectToChange);
      }

      // Update the value of the new entry to the value of the old entry, which we updated above.
      //
      objectChanges.put(copiedObjectToChange, entry.getValue());
    }

    for (ResourceChange resourceChange : getResourceChanges())
    {
      // Copy and reverse the resource change itself.
      //
      resourceChange.reverse();

      // Deal with all the list changes.
      //
      for (ListChange listChange : resourceChange.getListChanges())
      {
        for (ListIterator<EObject> referenceValues = listChange.getReferenceValues().listIterator(); referenceValues.hasNext();)
        {
          // Deal with each reference value exactly like we did multi-valued feature changes.
          //
          EObject referenceValue = referenceValues.next();
          EObject copiedReferenceValue = copier.get(referenceValue);
          if (copiedReferenceValue == null)
          {
            copiedReferenceValue = copier.copy(referenceValue);
          }
          referenceValues.set(copiedReferenceValue);
        }
      }
    }

    // Ensure that references between the copied objects are properly populated as in the originals.
    //
    copier.copyReferences();

    // Check for objects that became orphans as a result of applying the changes.
    //
    for (EObject eObject : potentialOrphans)
    {
      if (eObject.eContainer() == null && !eObject.eIsProxy())
      {
        newObjectsToAttach.add(eObject);
      }
      else
      {
        newObjectsToAttach.remove(eObject);
      }
    }

    // Reverse the objects to attach and detach lists.
    //
    getObjectsToAttach().clear();

    getObjectsToAttach().addAll(newObjectsToAttach);

    // Clear this cached information.
    //
    oldContainmentInformation = null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        return ((InternalEList<?>)getObjectChanges()).basicRemove(otherEnd, msgs);
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        return ((InternalEList<?>)getObjectsToAttach()).basicRemove(otherEnd, msgs);
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        return ((InternalEList<?>)getResourceChanges()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  protected void preApply(boolean reverse)
  {
    // Make sure the changed values of bidirectional reference lists are cached before we
    //  start to apply the change.
    //
    for (Map.Entry<EObject, EList<FeatureChange>> entry : getObjectChanges())
    {
      EObject objectToChange = entry.getKey();
      @SuppressWarnings("unchecked") EList<FeatureChangeImpl> value = (EList<FeatureChangeImpl>)(EList<?>)entry.getValue();
      for (FeatureChangeImpl featureChange : value)
      {
        featureChange.preApply(objectToChange, reverse);
        if (reverse || featureChange.isSet())
        {
          EStructuralFeature.Internal internalFeature = (EStructuralFeature.Internal)featureChange.getFeature();
          if (internalFeature != null &&
                (internalFeature.isFeatureMap() ||
                  (internalFeature.isMany() && (internalFeature.getEOpposite() != null || internalFeature.isContainment()))))
          {
            if (reverse)
            {
              // This case will be handled special during apply
              //
              EList<Object> applyToList = new BasicEList<Object>((EList<?>)objectToChange.eGet(internalFeature));
              for (ListChange listChange : featureChange.getListChanges())
              {
                listChange.applyAndReverse(applyToList);
              }
              featureChange.setValue(applyToList); // caches the list value.
            }
            else
            {
              featureChange.getValue(); // caches the list value.
            }
          }
        }
      }
    }

    if (resourceChanges != null)
    {
      @SuppressWarnings({"unchecked", "rawtypes"}) EList<ResourceChangeImpl> resourceChanges = (EList)getResourceChanges();
      for (ResourceChangeImpl resourceChange : resourceChanges)
      {
        Resource resource = resourceChange.getResource();
        if (resource != null)
        {
          resourceChange.preApply(reverse);
          if (reverse)
          {
            EList<Object> applyToList = new BasicEList<Object>(resourceChange.getResource().getContents());
            for (ListChange listChange : resourceChange.getListChanges())
            {
              listChange.applyAndReverse(applyToList);
            }
            resourceChange.setValue(applyToList); // caches the list value.
          }
          else
          {
            resourceChange.getValue(); // caches the list value.
          }
        }
      }
    }
  }

  protected Map<EObject, OldContainmentInformation> oldContainmentInformation;

  protected static class OldContainmentInformation
  {
    public EObject container;
    public EReference containmentFeature;

    public OldContainmentInformation(EObject container, EReference containmentFeature)
    {
      this.container = container;
      this.containmentFeature = containmentFeature;
    }
  }

  protected Map<EObject, OldContainmentInformation> getOldContainmentInformation()
  {
    if (oldContainmentInformation == null)
    {
      HashMap<EObject, OldContainmentInformation> containmentInformation = new HashMap<EObject, OldContainmentInformation>();
      for (Map.Entry<EObject, EList<FeatureChange>> entry : getObjectChanges())
      {
        List<FeatureChange> featureChanges = entry.getValue();
        for (FeatureChange featureChange : featureChanges)
        {
          EStructuralFeature feature = featureChange.getFeature();
          if (FeatureMapUtil.isFeatureMap(feature))
          {
            EObject container = entry.getKey();
            @SuppressWarnings("unchecked") List<FeatureMap.Entry> featureMap = (List<FeatureMap.Entry>)featureChange.getValue();
            for (FeatureMap.Entry featureMapEntry : featureMap)
            {
              EStructuralFeature featureMapEntryFeature = featureMapEntry.getEStructuralFeature();
              if (featureMapEntryFeature instanceof EReference && ((EReference)featureMapEntryFeature).isContainment())
              {
                EObject eObject = (EObject)featureMapEntry.getValue();
                if (eObject != null && (eObject.eContainer() != container || eObject.eContainmentFeature() != featureMapEntryFeature))
                {
                  containmentInformation.put(eObject, new OldContainmentInformation(container, (EReference)featureMapEntryFeature));
                }
              }
            }
          }
          else if (feature instanceof EReference && ((EReference)feature).isContainment())
          {
            EObject container = entry.getKey();
            if (feature.isMany())
            {
              @SuppressWarnings("unchecked") List<EObject> list = ((List<EObject>)featureChange.getValue());
              for (EObject eObject : list)
              {
                if (eObject.eContainer() != container || eObject.eContainmentFeature() != feature)
                {
                  containmentInformation.put(eObject, new OldContainmentInformation(container, (EReference)feature));
                }
              }
            }
            else
            {
              EObject eObject = (EObject)featureChange.getValue();
              if (eObject != null && (eObject.eContainer() != container || eObject.eContainmentFeature() != feature))
              {
                containmentInformation.put(eObject, new OldContainmentInformation(container, (EReference)feature));
              }
            }
          }
        }
      }

      for (EObject eObject : getObjectsToDetach())
      {
        containmentInformation.put(eObject, new OldContainmentInformation(null, null));
      }
      oldContainmentInformation = containmentInformation;
    }

    return oldContainmentInformation;
  }

  public EObject getOldContainer(EObject eObject)
  {
    OldContainmentInformation oldContainmentInformation = getOldContainmentInformation().get(eObject);
    if (oldContainmentInformation == null)
    {
      return eObject.eContainer();
    }
    else
    {
      return oldContainmentInformation.container;
    }
  }

  public EReference getOldContainmentFeature(EObject eObject)
  {
    OldContainmentInformation oldContainmentInformation = getOldContainmentInformation().get(eObject);
    if (oldContainmentInformation == null)
    {
      return eObject.eContainmentFeature();
    }
    else
    {
      return oldContainmentInformation.containmentFeature;
    }
  }
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        if (coreType) return getObjectChanges();
        else return getObjectChanges().map();
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH:
        return getObjectsToDetach();
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        return getObjectsToAttach();
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        return getResourceChanges();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        ((EStructuralFeature.Setting)getObjectChanges()).set(newValue);
        return;
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH:
        getObjectsToDetach().clear();
        getObjectsToDetach().addAll((Collection<? extends EObject>)newValue);
        return;
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        getObjectsToAttach().clear();
        getObjectsToAttach().addAll((Collection<? extends EObject>)newValue);
        return;
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        getResourceChanges().clear();
        getResourceChanges().addAll((Collection<? extends ResourceChange>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        getObjectChanges().clear();
        return;
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH:
        getObjectsToDetach().clear();
        return;
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        getObjectsToAttach().clear();
        return;
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        getResourceChanges().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        return objectChanges != null && !objectChanges.isEmpty();
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH:
        return objectsToDetach != null && !objectsToDetach.isEmpty();
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        return objectsToAttach != null && !objectsToAttach.isEmpty();
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        return resourceChanges != null && !resourceChanges.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ChangeDescriptionImpl
