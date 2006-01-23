/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: ECrossReferenceEList.java,v 1.6 2006/01/23 16:33:15 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;


/**
 * A virtual list of all the cross references of an EObject.
 */
public class ECrossReferenceEList extends EContentsEList
{
  public ECrossReferenceEList(EObject eObject)
  {
    super
      (eObject, 
       ((EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).crossReferences());
  }

  protected ECrossReferenceEList(EObject eObject, EStructuralFeature [] eStructuralFeatures)
  {
    super(eObject, eStructuralFeatures);
  }

  public static class FeatureIteratorImpl extends EContentsEList.FeatureIteratorImpl
  {
    protected static final EStructuralFeature[] NO_FEATURES = new EStructuralFeature [0];
    
    public FeatureIteratorImpl(EObject eObject)
    {
      this(eObject, ((EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).crossReferences());
    }

    public FeatureIteratorImpl(EObject eObject, EStructuralFeature [] eStructuralFeatures)
    {
      super(eObject, eStructuralFeatures == null ? NO_FEATURES : eStructuralFeatures);
    }

    protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature)
    {
      if (eStructuralFeature instanceof EReference)
      {
        EReference eReference = (EReference)eStructuralFeature;
        return !eReference.isContainment() && !eReference.isContainer();
      }
      else
      {
        return false;
      }
    }
  }

  public static class ResolvingFeatureIteratorImpl extends FeatureIteratorImpl
  {
    public ResolvingFeatureIteratorImpl(EObject eObject)
    {
      super(eObject);
    }

    public ResolvingFeatureIteratorImpl(EObject eObject, EStructuralFeature [] eStructuralFeatures)
    {
      super(eObject, eStructuralFeatures);
    }

    protected boolean resolve()
    {
      return true;
    }
  }

  protected boolean isIncluded(EStructuralFeature eStructuralFeature)
  {
    if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
    {
      return true;
    }
    else
    {
      EReference eReference = (EReference)eStructuralFeature;
      return !eReference.isContainment() && !eReference.isContainer();
    }
  }

  protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature)
  {
    if (eStructuralFeature instanceof EReference)
    {
      EReference eReference = (EReference)eStructuralFeature;
      return !eReference.isContainment() && !eReference.isContainer();
    }
    else
    {
      return false;
    }
  }

  protected ListIterator newResolvingListIterator()
  {
    return new ResolvingFeatureIteratorImpl(eObject, eStructuralFeatures);
  }
  
  protected ListIterator newNonResolvingListIterator()
  {
    return new FeatureIteratorImpl(eObject, eStructuralFeatures);
  }

  public List basicList()
  {
    return
      new ECrossReferenceEList(eObject, eStructuralFeatures)
      {
        protected boolean resolve()
        {
          return false;
        }
      };
  }
}
