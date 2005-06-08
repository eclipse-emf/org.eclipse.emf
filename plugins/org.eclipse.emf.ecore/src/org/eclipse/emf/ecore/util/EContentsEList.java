/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EContentsEList.java,v 1.4.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;


public class EContentsEList extends AbstractSequentialList implements EList, InternalEList
{
  protected final EObject eObject; 
  protected final EStructuralFeature [] eStructuralFeatures;

  public EContentsEList(EObject eObject)
  {
    this.eObject = eObject;
    this.eStructuralFeatures = 
      ((EClassImpl.FeatureSubsetSupplier)eObject.eClass().getEAllStructuralFeatures()).containments();
  }

  public EContentsEList(EObject eObject, List eStructuralFeatures)
  {
    this.eObject = eObject;
    this.eStructuralFeatures = new EStructuralFeature [eStructuralFeatures.size()];
    eStructuralFeatures.toArray(this.eStructuralFeatures);
  }

  public EContentsEList(EObject eObject, EStructuralFeature [] eStructuralFeatures)
  {
    this.eObject = eObject;
    this.eStructuralFeatures = eStructuralFeatures;
  }

  protected ListIterator newListIterator()
  {
    return
      resolve() ?
        new ResolvingFeatureIteratorImpl(eObject, eStructuralFeatures) :
        new FeatureIteratorImpl(eObject, eStructuralFeatures);
  }

  protected Iterator newIterator()
  {
    return newListIterator();
  }

  protected boolean useIsSet()
  {
    return true;
  }

  protected boolean resolve()
  {
    return true;
  }

  protected boolean isIncluded(EStructuralFeature eStructuralFeature)
  {
    return true;
  }

  protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isContainment();
  }

  public ListIterator listIterator(int index)
  {
    if (eStructuralFeatures == null)
    {
      if (index != 0)
      {
        throw new IndexOutOfBoundsException("index=" + index + ", size=0");
      }

      return FeatureIteratorImpl.EMPTY_ITERATOR;
    }

    ListIterator result = newListIterator();
    for (int i = 0; i < index; ++i)
    {
      result.next();
    }
    return result;
  }

  public Iterator iterator()
  {
    if (eStructuralFeatures == null)
    {
      return FeatureIteratorImpl.EMPTY_ITERATOR;
    }

    Iterator result = newIterator();
    return result;
  }

  public int size()
  {
    int result = 0;
    if (eStructuralFeatures != null)
    {
      for (int i = 0; i < eStructuralFeatures.length; ++i)
      {
        EStructuralFeature feature = eStructuralFeatures[i];
        if (isIncluded(feature) && (!useIsSet() || eObject.eIsSet(feature)))
        {
          Object value = eObject.eGet(feature, false);
          if (FeatureMapUtil.isFeatureMap(feature))
          {
            FeatureMap featureMap = (FeatureMap)value;
            for (int j = 0, size = featureMap.size(); j < size; ++j)
            {
              if (isIncludedEntry(featureMap.getEStructuralFeature(j)) && featureMap.getValue(j) != null)
              {
                ++result;
              }
            }
          }
          else if (feature.isMany())
          {
            result += ((Collection)value).size();
          } 
          else if (value != null)
          {
            ++result;
          }
        }
      }
    }
    return result;
  }

  public boolean isEmpty()
  {
    if (eStructuralFeatures != null)
    {
      for (int i = 0; i < eStructuralFeatures.length; ++i)
      {
        EStructuralFeature feature = eStructuralFeatures[i];
        if (isIncluded(feature) && (!useIsSet() || eObject.eIsSet(feature)))
        {
          Object value = eObject.eGet(feature, false);
          if (FeatureMapUtil.isFeatureMap(feature))
          {
            FeatureMap featureMap = (FeatureMap)value;
            for (int j = 0, size = featureMap.size(); j < size; ++j)
            {
              if (isIncludedEntry(featureMap.getEStructuralFeature(j)) && featureMap.getValue(j) != null)
              {
                return false;
              }
            }
          }
          else if (feature.isMany())
          {
            if (!((Collection)value).isEmpty())
            {
              return false;
            }
          } 
          else if (value != null)
          {
            return false;
          }
        }
      }
    }
    return true;
  }

  public void move(int newPosition, Object o)
  {
    throw new UnsupportedOperationException();
  }

  public Object move(int newPosition, int oldPosition)
  {
    throw new UnsupportedOperationException();
  }

  public Object basicGet(int index)
  {
    return basicList().get(index);
  }

  public List basicList()
  {
    return
      new EContentsEList(eObject, eStructuralFeatures)
      {
        protected boolean resolve()
        {
          return false;
        }
      };
  }

  public Iterator basicIterator()
  {
    if (eStructuralFeatures == null)
    {
      return FeatureIteratorImpl.EMPTY_ITERATOR;
    }

    return new FeatureIteratorImpl(eObject, eStructuralFeatures);
  }

  public ListIterator basicListIterator()
  {
    if (eStructuralFeatures == null)
    {
      return FeatureIteratorImpl.EMPTY_ITERATOR;
    }

    return new FeatureIteratorImpl(eObject, eStructuralFeatures);
  }

  public ListIterator basicListIterator(int index)
  {
    if (eStructuralFeatures == null)
    {
      if (index < 0 || index > 1)
      {
        throw new IndexOutOfBoundsException("index=" + index + ", size=0");
      }

      return FeatureIteratorImpl.EMPTY_ITERATOR;
    }

    ListIterator result = new FeatureIteratorImpl(eObject, eStructuralFeatures);
    for (int i = 0; i < index; ++i)
    {
      result.next();
    }
    return result;
  }

  public NotificationChain basicRemove(Object object, NotificationChain notifications)
  {
    throw new UnsupportedOperationException();
  }

  public NotificationChain basicAdd(Object object, NotificationChain notifications)
  {
    throw new UnsupportedOperationException();
  }

  public void addUnique(Object object)
  {
    throw new UnsupportedOperationException();
  }

  public void addUnique(int index, Object object)
  {
    throw new UnsupportedOperationException();
  }

  public Object setUnique(int index, Object object)
  {
    throw new UnsupportedOperationException();
  }

  public interface FeatureIterator extends Iterator
  {
    EStructuralFeature feature();
  }

  public interface FeatureListIterator extends FeatureIterator, ListIterator
  {
  }

  public static class FeatureIteratorImpl implements FeatureListIterator
  {
    protected final EObject eObject; 
    protected final EStructuralFeature [] eStructuralFeatures;
    protected int featureCursor;
    protected int cursor;
    protected int prepared;
    protected Object preparedResult;
    protected EStructuralFeature preparedFeature;
    protected EStructuralFeature feature;
    protected boolean isHandlingFeatureMap;
    protected ListIterator values;

    public FeatureIteratorImpl(EObject eObject, List eStructuralFeatures)
    {
      this.eObject = eObject;
      this.eStructuralFeatures = new EStructuralFeature [eStructuralFeatures.size()];
      eStructuralFeatures.toArray(this.eStructuralFeatures);
    }

    public FeatureIteratorImpl(EObject eObject, EStructuralFeature [] eStructuralFeatures)
    {
      this.eObject = eObject;
      this.eStructuralFeatures = eStructuralFeatures;
    }

    protected boolean resolve()
    {
      return false;
    }

    protected boolean useIsSet()
    {
      return true;
    }

    protected boolean isIncluded(EStructuralFeature eStructuralFeature)
    {
      return true;
    }

    protected boolean isIncludedEntry(EStructuralFeature eStructuralFeature)
    {
      return eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isContainment();
    }

    public EStructuralFeature feature()
    {
      return feature;
    }

    public boolean hasNext()
    {
      switch (prepared)
      {
        case 3:
        case 2:
        {
          return true;
        }
        case 1:
        {
          return false;
        }
        case -3:
        {
          // Undo the preparation for previous and continue.
          values.next();
        }
        default:
        {
          if (values == null || !scanNext(values))
          {
            while (featureCursor < eStructuralFeatures.length)
            {
              EStructuralFeature feature = eStructuralFeatures[featureCursor++];
              if (isIncluded(feature) && (!useIsSet() || eObject.eIsSet(feature)))
              {
                Object value = eObject.eGet(feature, resolve());
                isHandlingFeatureMap = FeatureMapUtil.isFeatureMap(feature);
                if (isHandlingFeatureMap || feature.isMany())
                {
                  values = resolve() ? ((List)value).listIterator() : ((InternalEList)value).basicListIterator();
                  if (scanNext(values))
                  {
                    preparedResult = values.next();
                    if (isHandlingFeatureMap)
                    {
                      preparedResult = ((FeatureMap.Entry)preparedResult).getValue();
                    }
                    preparedFeature = feature;
                    prepared = 3;
                    return true;
                  }
                }
                else if (value != null)
                {
                  values = null;
                  preparedResult = value;
                  preparedFeature = feature;
                  prepared = 2;
                  return true;
                }
              }
            }
            values = null;
            isHandlingFeatureMap = false;
            prepared = 1;
            return false;
          }
          else
          {
            preparedResult = values.next();
            if (isHandlingFeatureMap)
            {
              preparedResult = ((FeatureMap.Entry)preparedResult).getValue();
            }
            prepared = 3;
            return true;
          }
        }
      }
    }

    protected boolean scanNext(ListIterator values)
    {
      if (isHandlingFeatureMap)
      {
        while (values.hasNext())
        {
          FeatureMap.Entry entry = (FeatureMap.Entry)values.next();
          EStructuralFeature entryFeature = entry.getEStructuralFeature();
          if (isIncludedEntry(entryFeature) && entry.getValue() != null)
          {
            values.previous();
            return true;
          }
        }
        return false;
      }
      else
      {
        return values.hasNext();
      }
    }

    public Object next()
    {
      if (hasNext())
      {
        ++cursor;
        prepared = 0;
        feature = preparedFeature;
        return preparedResult;
      }
      else
      {
        throw new NoSuchElementException();
      }
    }

    public int nextIndex()
    {
      return cursor;
    }

    public boolean hasPrevious()
    {
      switch (prepared)
      {
        case -3:
        case -2:
        {
          return true;
        }
        case -1:
        {
          return false;
        }
        case 3:
        {
          // Undo the preparation for next and continue.
          values.previous();
        }
        default:
        {
          if (values == null || !scanPrevious(values))
          {
            while (featureCursor > 0)
            {
              EStructuralFeature feature = eStructuralFeatures[--featureCursor];
              if (isIncluded(feature) && (!useIsSet() || eObject.eIsSet(feature)))
              {
                Object value = eObject.eGet(feature, resolve());
                isHandlingFeatureMap = FeatureMapUtil.isFeatureMap(feature);
                if (isHandlingFeatureMap || feature.isMany())
                {
                  List list = (List)value;
                  values = resolve() ? list.listIterator(list.size()) : ((InternalEList)list).basicListIterator(list.size());
                  if (scanPrevious(values))
                  {
                    preparedResult = values.previous();
                    if (isHandlingFeatureMap)
                    {
                      preparedResult = ((FeatureMap.Entry)preparedResult).getValue();
                    }
                    preparedFeature = feature;
                    prepared = -3;
                    return true;
                  }
                }
                else if (value != null)
                {
                  values = null;
                  preparedResult = value;
                  preparedFeature = feature;
                  prepared = -2;
                  return true;
                }
              }
            }
            values = null;
            prepared = -1;
            return false;
          }
          else
          {
            preparedResult = values.previous();
            if (isHandlingFeatureMap)
            {
              preparedResult = ((FeatureMap.Entry)preparedResult).getValue();
            }
            prepared = -3;
            return true;
          }
        }
      }
    }

    protected boolean scanPrevious(ListIterator values)
    {
      if (isHandlingFeatureMap)
      {
        while (values.hasPrevious())
        {
          FeatureMap.Entry entry = (FeatureMap.Entry)values.previous();
          EStructuralFeature entryFeature = entry.getEStructuralFeature();
          if (isIncludedEntry(entryFeature) && entry.getValue() != null)
          {
            values.next();
            return true;
          }
        }
        return false;
      }
      else
      {
        return values.hasPrevious();
      }
    }

    public Object previous()
    {
      if (hasPrevious())
      {
        --cursor;
        prepared = 0;
        feature = preparedFeature;
        return preparedResult;
      }
      else
      {
        throw new NoSuchElementException();
      }
    }

    public int previousIndex()
    {
      return cursor - 1;
    }

    public void add(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }

    public void set(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public static final ListIterator EMPTY_ITERATOR = 
      new FeatureIteratorImpl(null, (EStructuralFeature [] )null)
      {
        public boolean hasNext()
        {
          return false;
        }

        public boolean hasPrevious()
        {
          return false;
        }
      };
  }

  public static class ResolvingFeatureIteratorImpl extends FeatureIteratorImpl
  {
    public ResolvingFeatureIteratorImpl(EObject eObject, List eStructuralFeatures)
    {
      super(eObject, eStructuralFeatures);
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
}
