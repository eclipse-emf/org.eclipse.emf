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
 * $Id: ChangeDescriptionImpl.java,v 1.7 2005/02/08 21:07:46 marcelop Exp $
 */
package org.eclipse.emf.ecore.change.impl;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
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
  protected EMap objectChanges = null;

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
  protected EList objectsToDetach = null;
  
  /**
   * The cached value of the '{@link #getObjectsToAttach() <em>Objects To Attach</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObjectsToAttach()
   * @generated
   * @ordered
   */
  protected EList objectsToAttach = null;

  /**
   * The cached value of the '{@link #getResourceChanges() <em>Resource Changes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceChanges()
   * @generated
   * @ordered
   */
  protected EList resourceChanges = null;

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
  protected EClass eStaticClass()
  {
    return ChangePackage.eINSTANCE.getChangeDescription();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getObjectChanges()
  {
    if (objectChanges == null)
    {
      objectChanges = new EcoreEMap(ChangePackage.eINSTANCE.getEObjectToChangesMapEntry(), EObjectToChangesMapEntryImpl.class, this, ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES);
    }
    return objectChanges;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getObjectsToDetachGen()
  {
    if (objectsToDetach == null)
    {
      objectsToDetach = new EObjectEList(EObject.class, this, ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH);
    }
    return objectsToDetach;
  }

  public EList getObjectsToDetach()
  {
    List objectsBeforeChange = new UniqueEList.FastCompare();
    List objectsAfterChange = new UniqueEList.FastCompare();

    if (!getObjectChanges().isEmpty())
    {
      preApply(false);

      for (Iterator i = getObjectChanges().iterator(); i.hasNext();)
      {
        EObjectToChangesMapEntryImpl entry = (EObjectToChangesMapEntryImpl)i.next();
        EObject objectToChange = entry.getTypedKey(); 
        for (Iterator j = entry.getTypedValue().iterator(); j.hasNext();)
        {
          FeatureChange featureChange = (FeatureChange)j.next();
          EStructuralFeature feature = featureChange.getFeature();
          if (feature instanceof EReference && ((EReference)feature).isContainment())
          {
            if (feature.isMany())
            {
              objectsBeforeChange.addAll((List)featureChange.getValue());
              objectsAfterChange.addAll((List)objectToChange.eGet(feature));
            }
            else
            {
              Object value = featureChange.getValue();
              if (value != null) objectsBeforeChange.add(value);
              value = objectToChange.eGet(feature);
              if (value != null) objectsAfterChange.add(value);
            }
          }
        }
      }
    }
    
    if (!getResourceChanges().isEmpty())
    {
      for (Iterator i = getResourceChanges().iterator(); i.hasNext();)
      {
        ResourceChange resourceChange = (ResourceChange)i.next();
        Resource resource = resourceChange.getResource();
        if (resource == null)
        {
          resource = eResource();
        }
        
        if (resource != null)
        {
          EList currentContentCopy = new BasicEList(resource.getContents());
          for (Iterator j = resourceChange.getListChanges().iterator(); j.hasNext();)
          {
            ListChange listChange = (ListChange)j.next();
            ((ListChangeImpl)listChange).apply(currentContentCopy);
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
  public EList getObjectsToAttach()
  {
    if (objectsToAttach == null)
    {
      objectsToAttach = new EObjectContainmentEList(EObject.class, this, ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH);
    }
    return objectsToAttach;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getResourceChanges()
  {
    if (resourceChanges == null)
    {
      resourceChanges = new EObjectContainmentEList(ResourceChange.class, this, ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES);
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
    for (Iterator iter = getObjectChanges().iterator(); iter.hasNext(); )
    {
      EObjectToChangesMapEntryImpl entry = (EObjectToChangesMapEntryImpl)iter.next();
      EObject objectToChange = entry.getTypedKey();
      for (Iterator fIter = entry.getTypedValue().iterator(); fIter.hasNext(); )
      {
        FeatureChange featureChange = (FeatureChange)fIter.next();
        featureChange.apply(objectToChange);
      }
    }

    for (Iterator iter = getResourceChanges().iterator(); iter.hasNext(); )
    {
      ResourceChange resourceChange = (ResourceChange)iter.next();
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
    
    List objectsBeforeApply = new UniqueEList.FastCompare();
    List objectsAfterApply = new UniqueEList.FastCompare();
    
    // Apply the change and reverse the change information.
    //
    for (Iterator iter = getObjectChanges().iterator(); iter.hasNext(); )
    {
      EObjectToChangesMapEntryImpl entry = (EObjectToChangesMapEntryImpl)iter.next();
      EObject objectToChange = entry.getTypedKey();
      for (Iterator fIter = entry.getTypedValue().iterator(); fIter.hasNext(); )
      {
        FeatureChange featureChange = (FeatureChange)fIter.next();
        EStructuralFeature feature  = featureChange.getFeature();

        int featureKind = feature instanceof EReference && ((EReference)feature).isContainment() ? feature.isMany() ? 1 : 2 : 0;
        switch (featureKind)
        {
          case 1:
          {
            objectsBeforeApply.addAll((List)objectToChange.eGet(feature));
            break;
          }
          case 2:
          {
            Object value = objectToChange.eGet(feature);
            if (value != null)
            {
              objectsBeforeApply.add(objectToChange.eGet(feature));
            }
            break;
          }            
        }
               
        featureChange.applyAndReverse(objectToChange);
        
        switch (featureKind)
        {
          case 1:
          {
            objectsAfterApply.addAll((List)objectToChange.eGet(feature));
            break;
          }
          case 2:
          {
            Object value = objectToChange.eGet(feature);
            if (value != null)
            {
              objectsAfterApply.add(objectToChange.eGet(feature));
            }
            break;
          }            
        }
      }
    }

    for (Iterator iter = getResourceChanges().iterator(); iter.hasNext(); )
    {
      ResourceChange resourceChange = (ResourceChange)iter.next();
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
    for (Iterator iter = objectsBeforeApply.iterator(); iter.hasNext(); )
    {
      EObject eObject = (EObject)iter.next();
      if (eObject.eContainer() == null && eObject.eResource() == null)
      {
        getObjectsToAttach().add(eObject);
      }
    }
    oldContainmentInformation = null;
  }

  protected void preApply(boolean reverse)
  {
    // Make sure the changed values of bi-directional reference lists are cached before we
    //  start to apply the change.
    //
    for (Iterator iter = getObjectChanges().iterator(); iter.hasNext(); )
    {
      EObjectToChangesMapEntryImpl entry = (EObjectToChangesMapEntryImpl)iter.next();
      EObject objectToChange = entry.getTypedKey();
      for (Iterator fIter = entry.getTypedValue().iterator(); fIter.hasNext(); )
      {
        FeatureChangeImpl featureChange = (FeatureChangeImpl)fIter.next();
        featureChange.preApply(objectToChange, reverse);
        if (reverse || featureChange.isSet())
        {
          EStructuralFeature feature = featureChange.getFeature();
          if (feature != null && feature.isMany() &&
              feature instanceof EReference &&
              (((EReference)feature).getEOpposite() != null || ((EReference)feature).isContainment()))
          {
            if (reverse)
            {
              // This case will be handled special during apply
              //
              EList applyToList = new BasicEList((EList)objectToChange.eGet(feature));
              for (Iterator k = featureChange.getListChanges().iterator(); k.hasNext(); )
              {
                ListChange listChange = (ListChange)k.next();
                listChange.applyAndReverse(applyToList);
              }
              ((FeatureChangeImpl)featureChange).setValue(applyToList); // cache the list value.
            }
            else
            {
              featureChange.getValue(); // cache the list value.
            }
          }
        }
      }
    }
  }

  protected Map oldContainmentInformation;

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

  protected Map getOldContainmentInformation()
  {
    if (oldContainmentInformation == null)
    {
      oldContainmentInformation = new HashMap();
      for (Iterator i = getObjectChanges().iterator(); i.hasNext(); )
      {
        Map.Entry entry = (Map.Entry)i.next();
        List featureChanges = (List)entry.getValue();
        for (Iterator j = featureChanges.iterator(); j.hasNext(); )
        {
          FeatureChange featureChange = (FeatureChange)j.next();
          EStructuralFeature feature = featureChange.getFeature();
          if (feature instanceof EReference && ((EReference)feature).isContainment())
          {
            EObject container = (EObject)entry.getKey();
            if (feature.isMany())
            {
              for (Iterator k = ((List)featureChange.getValue()).iterator(); k.hasNext(); )
              {
                EObject eObject = (EObject)k.next();
                if (eObject.eContainer() != container || eObject.eContainmentFeature() != feature)
                {
                  oldContainmentInformation.put(eObject, new OldContainmentInformation(container, (EReference)feature));
                }
              }
            }
            else
            {
              EObject eObject = (EObject)featureChange.getValue();
              if (eObject.eContainer() != container || eObject.eContainmentFeature() != feature)
              {
                oldContainmentInformation.put(eObject, new OldContainmentInformation(container, (EReference)feature));
              }
            }
          }
        }
      }
    }

    return oldContainmentInformation;
  }

  public EObject getOldContainer(EObject eObject)
  {
    OldContainmentInformation oldContainmentInformation = (OldContainmentInformation)getOldContainmentInformation().get(eObject);
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
    OldContainmentInformation oldContainmentInformation = (OldContainmentInformation)getOldContainmentInformation().get(eObject);
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
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
          return ((InternalEList)getObjectChanges()).basicRemove(otherEnd, msgs);
        case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
          return ((InternalEList)getObjectsToAttach()).basicRemove(otherEnd, msgs);
        case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
          return ((InternalEList)getResourceChanges()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        return getObjectChanges();
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH:
        return getObjectsToDetach();
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        return getObjectsToAttach();
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        return getResourceChanges();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.CHANGE_DESCRIPTION__OBJECT_CHANGES:
        getObjectChanges().clear();
        getObjectChanges().addAll((Collection)newValue);
        return;
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_DETACH:
        getObjectsToDetach().clear();
        getObjectsToDetach().addAll((Collection)newValue);
        return;
      case ChangePackage.CHANGE_DESCRIPTION__OBJECTS_TO_ATTACH:
        getObjectsToAttach().clear();
        getObjectsToAttach().addAll((Collection)newValue);
        return;
      case ChangePackage.CHANGE_DESCRIPTION__RESOURCE_CHANGES:
        getResourceChanges().clear();
        getResourceChanges().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    return eDynamicIsSet(eFeature);
  }

} //ChangeDescriptionImpl
