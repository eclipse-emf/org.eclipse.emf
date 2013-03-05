/**
 * Copyright (c) 2002-2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.impl;


import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A minimal abstract implementation of '<em><b>EObject</b></em>' that delegates to a {@link org.eclipse.emf.ecore.InternalEObject.EStore store}.
 * It's extends {@link MinimalEObjectImpl} and does <b>not</b> introduce any additional fields.
 * Clients extending this class must specialize {@link #eStore()}.
 * @since 2.9
 */
public abstract class MinimalEStoreEObjectImpl extends MinimalEObjectImpl
{
  /**
   * Creates a store-based EObject.
   */
  public MinimalEStoreEObjectImpl()
  {
    super();
  }

  /**
   * Creates a store-based EObject.
   */
  public MinimalEStoreEObjectImpl(EClass eClass)
  {
    super();
    eSetClass(eClass);
  }

  @Override
  public abstract InternalEObject.EStore eStore();

  protected boolean eIsCaching()
  {
    return true;
  }

  @Override
  public Object dynamicGet(int dynamicFeatureID)
  {
    Object[] eSettings = eDynamicSettings();
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

  @Override
  public void dynamicSet(int dynamicFeatureID, Object value)
  {
    Object[] eSettings = eDynamicSettings();
    EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
    if (eStructuralFeature.isTransient())
    {
      eSettings[dynamicFeatureID] = value;
    }
    else
    {
      eStore().set(this, eStructuralFeature, InternalEObject.EStore.NO_INDEX, value);
      if (eIsCaching())
      {
        eSettings[dynamicFeatureID] = value;
      }
    }
  }

  @Override
  public void dynamicUnset(int dynamicFeatureID)
  {
    Object[] eSettings = eDynamicSettings();
    EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
    if (eStructuralFeature.isTransient())
    {
      eSettings[dynamicFeatureID] = null;
    }
    else
    {
      eStore().unset(this, eStructuralFeature);
      eSettings[dynamicFeatureID] = null;
    }
  }

  @Override
  protected boolean eDynamicIsSet(int dynamicFeatureID, EStructuralFeature eFeature)
  {
    return
      dynamicFeatureID < 0 ?
        eOpenIsSet(eFeature) :
        eFeature.isTransient() ?
          eSettingDelegate(eFeature).dynamicIsSet(this, eSettings(), dynamicFeatureID) :
          eStore().isSet(this, eFeature);
  }

  protected EList<?> createList(final EStructuralFeature eStructuralFeature)
  {
    final EClassifier eType = eStructuralFeature.getEType();
    if (eType.getInstanceClassName() == "java.util.Map$Entry")
    {
      class EStoreEcoreEMap extends EcoreEMap<Object, Object>
      {
        private static final long serialVersionUID = 1L;

        public EStoreEcoreEMap()
        {
          super
            ((EClass)eType,
             BasicEMap.Entry.class,
             null);
          delegateEList =
             new EStoreEObjectImpl.BasicEStoreEList<BasicEMap.Entry<Object, Object>>(MinimalEStoreEObjectImpl.this, eStructuralFeature)
             {
                private static final long serialVersionUID = 1L;

                @Override
                protected void didAdd(int index, BasicEMap.Entry<Object, Object> newObject)
                {
                  EStoreEcoreEMap.this.doPut(newObject);
                }

                @Override
                protected void didSet(int index, BasicEMap.Entry<Object, Object> newObject, BasicEMap.Entry<Object, Object> oldObject)
                {
                  didRemove(index, oldObject);
                  didAdd(index, newObject);
                }

                @Override
                protected void didRemove(int index, BasicEMap.Entry<Object, Object> oldObject)
                {
                  EStoreEcoreEMap.this.doRemove(oldObject);
                }

                @Override
                protected void didClear(int size, Object [] oldObjects)
                {
                  EStoreEcoreEMap.this.doClear();
                }

                @Override
                protected void didMove(int index, BasicEMap.Entry<Object, Object> movedObject, int oldIndex)
                {
                  EStoreEcoreEMap.this.doMove(movedObject);
                }
             };
          size = delegateEList.size();
        }
      }
      return new EStoreEcoreEMap();
    }
    else
    {
      return new EStoreEObjectImpl.BasicEStoreEList<Object>(this, eStructuralFeature);
    }
  }

  protected FeatureMap createFeatureMap(EStructuralFeature eStructuralFeature)
  {
    return new EStoreEObjectImpl.EStoreFeatureMap(this, eStructuralFeature, eStore());
  }

  /**
   * Returns the container as cached by {@link MinimalEObjectImpl#eInternalContainer()}.
   */
  protected InternalEObject eBasicInternalContainer()
  {
    return super.eInternalContainer();
  }

  /**
   * Returns the container as {@link InternalEObject.EStore#getContainer(InternalEObject) provided} by the store.
   */
  @Override
  public InternalEObject eInternalContainer()
  {
    return eStore().getContainer(this);
  }

  /**
   * Returns the container feature ID as cached by {@link MinimalEObjectImpl#eContainerFeatureID()}.
   */
  protected int eBasicContainerFeatureID()
  {
    return super.eContainerFeatureID();
  }

  /**
   * Returns the container feature ID as computed from the container feature {@link InternalEObject.EStore#getContainingFeature(InternalEObject) provided} by the store.
   */
  @Override
  public int eContainerFeatureID()
  {
    EObject eContainer = eInternalContainer();
    if (eContainer != null)
    {
      EStructuralFeature eContainingFeature = eStore().getContainingFeature(this);
      if (eContainingFeature instanceof EReference)
      {
        EReference eContainingReference = (EReference)eContainingFeature;
        EReference eOpposite = eContainingReference.getEOpposite();
        if (eOpposite != null)
        {
          return eClass().getFeatureID(eOpposite);
        }
      }

      return EOPPOSITE_FEATURE_BASE - eContainer.eClass().getFeatureID(eContainingFeature);
    }

    return 0;
  }

  @Override
  protected int eStaticFeatureCount()
  {
    return 0;
  }

  @Override
  public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature)
  {
    return eClass().getFeatureID(eStructuralFeature);
  }
}
