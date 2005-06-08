/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: DynamicEStoreEObjectImpl.java,v 1.1.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * An implementation of '<em><b>EObject</b></em>' that delegates to a {@link org.eclipse.emf.ecore.InternalEObject.EStore store}.
 */
public class DynamicEStoreEObjectImpl extends DynamicEObjectImpl
{
  protected static final InternalEObject EUNINITIALIZED_CONTAINER = new EObjectImpl();

  protected InternalEObject.EStore eStore;

  /**
   * Creates a store-based EObject.
   */
  public DynamicEStoreEObjectImpl()
  {
    super();
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  /**
   * Creates a store-based EObject.
   */
  public DynamicEStoreEObjectImpl(InternalEObject.EStore eStore)
  {
    super();
    eSetStore(eStore);
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  /**
   * Creates a store-based EObject.
   */
  public DynamicEStoreEObjectImpl(EClass eClass)
  {
    super(eClass);
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  /**
   * Creates a store-based EObject.
   */
  public DynamicEStoreEObjectImpl(EClass eClass, InternalEObject.EStore eStore)
  {
    super(eClass);
    eSetStore(eStore);
    eContainer = EUNINITIALIZED_CONTAINER;
  }

  protected boolean eIsCaching()
  {
    return true;
  }

  public Object dynamicGet(int dynamicFeatureID)
  {
    Object result = eSettings[dynamicFeatureID];
    if (result == null)
    {
      EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
      if (!eStructuralFeature.isTransient())
      {
        if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
        {
          eSettings[dynamicFeatureID] = result = createFeatureMap(eStructuralFeature);
        }
        else if (eStructuralFeature.isMany())
        {
          eSettings[dynamicFeatureID] = result = createList(eStructuralFeature);
        }
        else
        {
          result = eStore().get(this, eStructuralFeature, InternalEObject.EStore.NO_INDEX);
          if (eIsCaching())
          {
            eSettings[dynamicFeatureID] = result;
          }
        }
      }
    }
    return result;
  }

  public void dynamicSet(int dynamicFeatureID, Object value)
  {
    EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
    if (eStructuralFeature.isTransient())
    {
      eSettings[dynamicFeatureID] = value;
    }
    else
    {
      eStore().set(this, eStructuralFeature, InternalEObject.EStore.NO_INDEX, value == NIL ? null : value);
      if (eIsCaching())
      {
        eSettings[dynamicFeatureID] = value;
      }
    }
  }

  public void dynamicUnset(int dynamicFeatureID)
  {
    eStore().unset(this, eDynamicFeature(dynamicFeatureID));
    eSettings[dynamicFeatureID] = null;
  }

  public boolean eDynamicIsSet(EStructuralFeature eStructuralFeature)
  {
    return 
      eStructuralFeature.isTransient() ?
        super.eDynamicIsSet(eStructuralFeature) :
        eStore().isSet(this, eStructuralFeature);
  }

  protected EList createList(EStructuralFeature eStructuralFeature)
  {
    return new EStoreEObjectImpl.EStoreEList(this, eStructuralFeature, eStore());
  }

  protected FeatureMap createFeatureMap(EStructuralFeature eStructuralFeature)
  {
    return new EStoreEObjectImpl.EStoreFeatureMap(this, eStructuralFeature, eStore());
  }

  public EObject eContainer()
  {
    if (eContainer == EUNINITIALIZED_CONTAINER)
    {
      eInitializeContainer();
    }

    return eContainer;
  }

  public int eContainerFeatureID()
  {
    if (eContainer == EUNINITIALIZED_CONTAINER)
    {
      eInitializeContainer();
    }

    return eContainerFeatureID;
  }

  protected void eInitializeContainer()
  {
    eContainer = (InternalEObject)eStore().getContainer(this);
    if (eContainer != null)
    {
      EStructuralFeature eContainingFeature = eStore().getContainingFeature(this);
      if (eContainingFeature instanceof EReference)
      {
        EReference eContainingReference = (EReference)eContainingFeature;
        EReference eOpposite = eContainingReference.getEOpposite();
        if (eOpposite != null)
        {
          eContainerFeatureID = eClass().getEAllStructuralFeatures().indexOf(eOpposite);
          return;
        }
      }

      eContainerFeatureID = EOPPOSITE_FEATURE_BASE - eContainer.eClass().getEAllStructuralFeatures().indexOf(eContainingFeature);
    }
  }

  public InternalEObject.EStore eStore()
  {
    return eStore;
  }

  public void eSetStore(InternalEObject.EStore store)
  {
    this.eStore = store;
  }

/*
  public String toString()
  {
    String result = super.toString();
    int index = result.indexOf("DynamicEStoreEObjectImpl");
    return index == -1 ? result : result.substring(0, index) + result.substring(index + 13);
  }
*/
}
