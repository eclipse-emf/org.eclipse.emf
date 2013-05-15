/**
 * Copyright (c) 2003-2011 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
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
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.change.util.ListDifferenceAnalyzer;
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
    // Cache this list early, because its result depends on calling preApply(false).
    //
    EList<EObject> objectsToDetach = getObjectsToDetach();

    // Get ready to apply the changes, even though we really won't apply them to the originals.
    //
    preApply(true);

    // Cache the object changes.
    //
    final EMap<EObject, EList<FeatureChange>> objectChanges = getObjectChanges();

    // Create a specialized copier that creaties proxy copies.
    //
    EcoreUtil.Copier copier =
      new EcoreUtil.Copier()
      {
        private static final long serialVersionUID = 1L;

        @Override
        public Set<Map.Entry<EObject, EObject>> entrySet()
        {
          // Copy the entry set so that copyReferences isn't broken when proxies are created during its process.
          //
          return new HashSet<Map.Entry<EObject,EObject>>(super.entrySet());
        }

        @Override
        public EObject get(Object key)
        {
          EObject eObject = super.get(key);

          // If the object isn't already in the map...
          //
          if (eObject == null)
          {
            // Any object in the original proxy map is copied as proxy as needed.
            //
            URI proxyURI = eObjectToProxyURIMap.get(key);
            if (proxyURI != null)
            {
              EObject keyEObject = (EObject)key;
              InternalEObject copyEObject = (InternalEObject)createCopy(keyEObject);
              copyEObject.eSetProxyURI(proxyURI);
              put(keyEObject, copyEObject);
              return copyEObject;
            }
          }
          return eObject;
        }

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
            // Otherwise create a normal copy.
            //
            return super.copy(eObject);
          }
        }
      };

    // Ensure that references between the copied objects are properly populated as in the originals, but using proxies as needed.
    //
    Collection<EObject> newObjectsToAttach = copier.copyAll(objectsToDetach);
    copier.copyReferences();

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

    // Reverse the change information, and redirect any original objects to their proxy copies.
    //
    EMap<EObject, EList<FeatureChange>> oldObjectChanges =  new BasicEMap<EObject, EList<FeatureChange>>();
    oldObjectChanges.putAll(objectChanges);
    for (Map.Entry<EObject, EList<FeatureChange>> entry : oldObjectChanges)
    {
      // Get the copied version of the object to be changed.
      //
      EObject objectToChange = entry.getKey();
      EObject copiedObjectToChange = copier.get(objectToChange);

      // Discard any recorded changes for non-proxy copies, i.e., for objects that will appear in their current state in the objectsToAttach list.
      //
      if (copiedObjectToChange != null && !copiedObjectToChange.eIsProxy())
      {
        objectChanges.remove(objectToChange);
      }
      else
      {
        // For each feature change...
        //
        for (FeatureChange featureChange : entry.getValue())
        {
          // Reverse the change relative to the original object.
          //
          featureChange.reverse(objectToChange);

          // If there is a reference value...
          //
          EObject referenceValue = featureChange.getReferenceValue();
          if (referenceValue != null)
          {
            // Switch it to the copy.
            //
            featureChange.setReferenceValue(copier.get(referenceValue));
          }
          else
          {
            // Otherwise deal with the list changes.
            //
            for (ListChange listChange : featureChange.getListChanges())
            {
              // Look at each referenced value in the list change...
              //
              for (ListIterator<EObject> referenceValues = listChange.getReferenceValues().listIterator(); referenceValues.hasNext();)
              {
                // Deal with each reference value in exactly the same way as the single value case, i.e., switch it to the copy.
                //
                referenceValue = referenceValues.next();
                referenceValues.set(copier.get(referenceValue));
              }
            }
          }
        }

        // Remove the entry for the original object.
        //
        objectChanges.remove(objectToChange);

        // Add an entry for the proxy copy with the value we updated above.
        //
        objectChanges.put(copiedObjectToChange, entry.getValue());
      }
    }

    for (ResourceChange resourceChange : getResourceChanges())
    {
      // Reverse the resource change.
      //
      resourceChange.reverse();

      // Deal with all the list changes.
      //
      for (ListChange listChange : resourceChange.getListChanges())
      {
        // Look at each referenced value in the list change.
        //
        for (ListIterator<EObject> referenceValues = listChange.getReferenceValues().listIterator(); referenceValues.hasNext();)
        {
          // Deal with each reference value exactly like we did for multi-valued feature changes.
          //
          EObject referenceValue = referenceValues.next();
          referenceValues.set(copier.get(referenceValue));
        }
      }
    }

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

    // Visit all the content tree and all cross references to look for references to proxies.
    //
    class Visitor
    {
      ListDifferenceAnalyzer listDifferenceAnalyzer;

      void visit(Collection<EObject> eObjects)
      {
        for (EObject eObject : eObjects)
        {
          visit(eObject);
        }
      }

      void visit(EObject eObject)
      {
        // Consider all the references of the object's class.
        //
        EClass eClass = eObject.eClass();
        for (EReference eReference : eClass.getEAllReferences())
        {
          // If the reference is a containment or a bidirectional cross reference...
          //
          if (eReference.isContainment() || !eReference.isContainer() && eReference.getEOpposite() != null)
          {
            // If it's a multi-valued feature...
            //
            if (eReference.isMany())
            {
              // Prepare to prune out any references to proxies...
              //
              @SuppressWarnings("unchecked") InternalEList<EObject> eObjects = (InternalEList<EObject>)eObject.eGet(eReference);

              // If we modify the eObjects, we'll keep a copy of the original contents in this list.
              //
              EList<EObject> originalEObjects = null;

              // Visit all the values...
              //
              for (int i = 0, size = eObjects.size(); i < size; )
              {
                // Get the value without resolving it and check if it's a proxy.
                //
                EObject value = eObjects.basicGet(i);
                if (value.eIsProxy())
                {
                  // If we don't have a cached original yet...
                  //
                  if (originalEObjects == null)
                  {
                    // Create one.
                    //
                    originalEObjects = new BasicEList<EObject>(eObjects);
                  }

                  // Prune out the value and shrink the size.
                  //
                  eObjects.remove(i);
                  --size;
                }
                else
                {
                  // Step to the next item.
                  //
                  ++i;
                }
              }

              // If the list was modified...
              //
              if (originalEObjects != null)
              {
                // Ensure that a list of feature changes exists.
                //
                EList<FeatureChange> featureChanges = objectChanges.get(eObject);
                if (featureChanges == null)
                {
                  Map.Entry<EObject, EList<FeatureChange>> entry = ChangeFactory.eINSTANCE.createEObjectToChangesMapEntry(eObject);
                  objectChanges.add(entry);
                  featureChanges = entry.getValue();
                }

                // Create a feature change for this reference.
                //
                FeatureChange featureChange = ChangeFactory.eINSTANCE.createFeatureChange();
                featureChange.setFeature(eReference);

                // If there isn't a list difference analyzer yet, create one.
                //
                if (listDifferenceAnalyzer == null)
                {
                  listDifferenceAnalyzer = new ListDifferenceAnalyzer();
                }

                // Use the analyzer to create the list changes for the things we removed from the list.
                //
                @SuppressWarnings("unchecked")
                EList<Object> modifiedEObjects = (EList<Object>)(EList<?>)eObjects;
                listDifferenceAnalyzer.analyzeLists(modifiedEObjects, originalEObjects, featureChange.getListChanges());
                featureChanges.add(featureChange);
              }
            }
            else
            {
              // Get the value, without resolving the proxy and if it's a proxy...
              //
              InternalEObject referencedEObject = (InternalEObject)eObject.eGet(eReference, false);
              if (referencedEObject != null && referencedEObject.eIsProxy())
              {
                // Remove it.
                //
                eObject.eUnset(eReference);

                // Ensure that a list of feature changes exists.
                //
                EList<FeatureChange> featureChanges = objectChanges.get(eObject);
                if (featureChanges == null)
                {
                  Map.Entry<EObject, EList<FeatureChange>> entry = ChangeFactory.eINSTANCE.createEObjectToChangesMapEntry(eObject);
                  objectChanges.add(entry);
                  featureChanges = entry.getValue();
                }

                // Create a feature change for this reference to set the referenced object.
                //
                FeatureChange featureChange = ChangeFactory.eINSTANCE.createFeatureChange();
                featureChange.setFeature(eReference);
                featureChange.setReferenceValue(referencedEObject);
              }
            }
          }
        }

        // Visit the whole content tree.
        //
        visit(eObject.eContents());
      }
    }
    new Visitor().visit(newObjectsToAttach);

    // Reverse the objects to attach and detach lists.
    //
    EList<EObject> objectsToAttach = getObjectsToAttach();
    objectsToAttach.clear();
    objectsToAttach.addAll(newObjectsToAttach);

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
