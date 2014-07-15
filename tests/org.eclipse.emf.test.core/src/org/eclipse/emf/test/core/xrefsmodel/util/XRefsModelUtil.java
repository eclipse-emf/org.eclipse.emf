/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 *
 */
package org.eclipse.emf.test.core.xrefsmodel.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DelegatingEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.test.core.xrefsmodel.A;
import org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage;
import org.junit.Assert;


public class XRefsModelUtil
{
  private static boolean wrapCrossReferenceIterators;

  private static boolean assertNoAllOthersCalls;

  private static int allOthersCallCount;

  public static EList<A> getAllOthers(A self)
  {
    Assert.assertFalse("A::allOthers() was called", assertNoAllOthersCalls);
    allOthersCallCount++;

    Set<A> result = new LinkedHashSet<A>();
    Queue<A> queue = new LinkedList<A>();
    queue.add(self);

    for (A next = queue.poll(); next != null; next = queue.poll())
    {
      for (A other : next.getOthers())
      {
        if (result.add(other))
        {
          queue.offer(other);
        }
      }
    }

    return new EcoreEList.UnmodifiableEList.FastCompare<A>((InternalEObject)self, XRefsModelPackage.Literals.A__ALL_OTHERS, result.size(), result.toArray());
  }

  public static void assertNoAllOthersCalls(boolean assertNoAllOthersCalls)
  {
    XRefsModelUtil.assertNoAllOthersCalls = assertNoAllOthersCalls;
    XRefsModelUtil.allOthersCallCount = 0;
  }

  public static int getAllOthersCallCount()
  {
    return allOthersCallCount;
  }

  /**
   * Sets whether {@link EObject#eCrossReferences()} iterators will be wrapped so that they do not implement the
   * {@link EContentsEList.Filterable} interface.
   */
  public static void setWrapCrossReferenceIterators(boolean wrap)
  {
    wrapCrossReferenceIterators = wrap;

    if (wrap)
    {
      // The wrapper will cause A::getAllOthers() to be called
      assertNoAllOthersCalls(false);
    }
  }

  public static boolean isWrapCrossReferenceIterators()
  {
    return wrapCrossReferenceIterators;
  }

  /**
   * Obtains a list for {@link EObject#eCrossReferences()} which may provide iterators that do not implement
   * the {@link EContentsEList.Filterable} interface.
   *
   * @see #setWrapCrossReferenceIterators(boolean)
   */
  public static EList<EObject> getCrossReferences(EList<EObject> crossReferences)
  {
    return isWrapCrossReferenceIterators() ? wrapCrossReferences(crossReferences) : crossReferences;
  }

  private static EList<EObject> wrapCrossReferences(final EList<EObject> crossReferences)
  {
    class Wrapper extends DelegatingEList.UnmodifiableEList<EObject> implements InternalEList<EObject>
    {
      private static final long serialVersionUID = 1L;

      Wrapper(EList<EObject> delegate)
      {
        super(delegate);
      }

      @Override
      protected InternalEList<EObject> delegateBasicList()
      {
        return (InternalEList<EObject>)super.delegateBasicList();
      }

      protected EContentsEList.FeatureIterator<EObject> wrap(Iterator<EObject> iterator)
      {
        final EContentsEList.FeatureIterator<EObject> delegate = (EContentsEList.FeatureIterator<EObject>)iterator;
        return new EContentsEList.FeatureIterator<EObject>()
          {
            public boolean hasNext()
            {
              return delegate.hasNext();
            }

            public EObject next()
            {
              return delegate.next();
            }

            public void remove()
            {
              delegate.remove();
            }

            public EStructuralFeature feature()
            {
              return delegate.feature();
            }

          };
      }

      protected EContentsEList.FeatureListIterator<EObject> wrap(ListIterator<EObject> iterator)
      {
        final EContentsEList.FeatureListIterator<EObject> delegate = (EContentsEList.FeatureListIterator<EObject>)iterator;
        return new EContentsEList.FeatureListIterator<EObject>()
          {

            public EStructuralFeature feature()
            {
              return delegate.feature();
            }

            public boolean hasNext()
            {
              return delegate.hasNext();
            }

            public EObject next()
            {
              return delegate.next();
            }

            public void remove()
            {
              delegate.remove();
            }

            public void add(EObject e)
            {
              delegate.add(e);
            }

            public boolean hasPrevious()
            {
              return delegate.hasPrevious();
            }

            public int nextIndex()
            {
              return delegate.nextIndex();
            }

            public EObject previous()
            {
              return delegate.previous();
            }

            public int previousIndex()
            {
              return delegate.previousIndex();
            }

            public void set(EObject e)
            {
              delegate.set(e);
            }

          };
      }

      @Override
      public Iterator<EObject> iterator()
      {
        return wrap(delegateList().iterator());
      }

      @Override
      public Iterator<EObject> basicIterator()
      {
        return wrap(delegateBasicList().basicIterator());
      }

      @Override
      public ListIterator<EObject> basicListIterator(final int index)
      {
        return wrap(delegateBasicList().basicListIterator(index));
      }

      @Override
      public ListIterator<EObject> basicListIterator()
      {
        return basicListIterator(0);
      }

      @Override
      public List<EObject> basicList()
      {
        return super.basicList();
      }

      @Override
      public EObject basicGet(int index)
      {
        return super.basicGet(index);
      }

      public Object[] basicToArray()
      {
        return delegateBasicList().basicToArray();
      }

      public <T> T[] basicToArray(T[] array)
      {
        return delegateBasicList().basicToArray(array);
      }

      public int basicIndexOf(Object object)
      {
        return delegateBasicList().basicIndexOf(object);
      }

      public int basicLastIndexOf(Object object)
      {
        return delegateBasicList().basicLastIndexOf(object);
      }

      public boolean basicContains(Object object)
      {
        return delegateBasicList().basicContains(object);
      }

      public boolean basicContainsAll(Collection<?> collection)
      {
        return delegateBasicList().basicContainsAll(collection);
      }

      public NotificationChain basicRemove(Object object, NotificationChain notifications)
      {
        return delegateBasicList().basicRemove(object, notifications);
      }

      public NotificationChain basicAdd(EObject object, NotificationChain notifications)
      {
        return delegateBasicList().basicAdd(object, notifications);
      }
    }

    return new Wrapper(crossReferences);
  }
}
