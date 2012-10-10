/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.lib;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.Functions.Function1;

import com.google.common.collect.Lists;


/**
 * An extension library for {@link EList ELists}.
 */
public class XcoreEListExtensions
{
  /**
   * Returns an unmodifiable view of the specified list.
   * @param list the list for which an unmodifiable view is to be returned. May not be <code>null</code>.
   * @return an unmodifiable view of the specified list.
   */
  @Inline(value="$2.$3unmodifiableEList($1)", imported=ECollections.class)
  public static <T> EList<T> unmodifiableView(EList<? extends T> list)
  {
    return ECollections.unmodifiableEList(list);
  }

  /**
   * Returns an immutable copy of the specified list.
   *
   * @param list the list for which an immutable copy should be created. May not be <code>null</code>.
   * @return an immutable copy of the specified list.
   */
  public static <T> EList<T> immutableCopy(EList<? extends T> list)
  {
    final Object[] elements = list.toArray();
    return
       new UnmodiableArrayView<T>(elements);
  }

  /**
   * Sorts the specified list itself into ascending order according to the natural ordering of its elements.
   *
   * @param list the list to be sorted. May not be <code>null</code>.
   * @return the sorted list itself.
   */
  public static <T extends Comparable<? super T>> EList<T> sortInplace(EList<T> list)
  {
    ECollections.sort(list);
    return list;
  }

  /**
   * Sorts the specified list itself into ascending according to the order induced by the specified comparator.
   *
   * @param list the list to be sorted. May not be <code>null</code>.
   * @param comparator the comparator to be used; it may be <code>null</code> to indicate that the natural ordering of the elements should be used.
   * @return the sorted list itself.
   */
  public static <T> EList<T> sortInplace(EList<T> list, Comparator<? super T> comparator)
  {
    Collections.sort(list, comparator);
    return list;
  }

  /**
   * Sorts the specified list in ascending order according to the nature order of the corresponding element induced by the key function.
   *
   * @param list the list to be sorted. May not be <code>null</code>.
   * @param key the key function to induce comparison keys. May not be <code>null</code>.
   * @return the sorted list itself.
   */
  public static <T, C extends Comparable<? super C>> EList<T> sortInplaceBy(EList<T> list, final Functions.Function1<? super T, C> key)
  {
    Comparator<T> comparator =
      new Comparator<T>()
      {
        public int compare(T o1, T o2)
        {
          C left = key.apply(o1);
          C right = key.apply(o2);
          return left.compareTo(right);
        }
      };
    ECollections.sort(list, comparator);
    return list;
  }

  /**
   * Provides a reverse view of the given list which is especially useful for traversing a list backwards in a for-each loop.
   *
   * @param list the list whose elements should be viewed in reverse. May not be <code>null</code>.
   * @return a list with the same elements as the given list, in reverse order.
   */
  @Pure
  public static <T> EList<T> reverseView(EList<T> list)
  {
    return ECollections.asEList(Lists.reverse(list));
  }

  /**
   * Reverses the order of the elements in the specified list.
   * The list itself will be modified.
   *
   * @param list the list whose elements are to be reversed.
   * @return the list itself.
   * @throws UnsupportedOperationException if the specified list does not support {@link EList#move(int, int) move}.
   */
  public static <T> EList<T> reverse(EList<T> list)
  {
    ECollections.reverse(list);
    return list;
  }

  /**
   * Returns a transformed view of the given list.
   * Additions are not supported but all other changes to view are reflected in the underlying list and all changes to the underlying list are reflected in the view.
   * The mapping is done lazily, i.e., each list access applies the transformation.
   *
   * @param list the list to map. May not be <code>null</code>.
   * @param transformation the transformation to induce the mapping. May not be <code>null</code>.
   * @return a list that effectively contains the result of the transformation.
   */
  @Pure
  public static <T, R> EList<R> map(EList<T> list, Function1<? super T, ? extends R> transformation)
  {
    return ECollections.asEList(ListExtensions.map(list, transformation));
  }

  static class UnmodiableArrayView<T> extends BasicEList<T>
  {
    private static final long serialVersionUID = 1L;

    UnmodiableArrayView(Object[] data)
    {
      super(data.length, data);
    }

    @Override
     public void setData(int size, Object[] data)
     {
       throw new UnsupportedOperationException();
     }

    @Override
     public void grow(int minimumCapacity)
     {
       throw new UnsupportedOperationException();
     }

    @Override
     public void shrink()
     {
       throw new UnsupportedOperationException();
     }

    @Override
     public T setUnique(int index, T object)
     {
        throw new UnsupportedOperationException();
     }

    @Override
     public T remove(int index)
     {
       throw new UnsupportedOperationException();
     }

    @Override
     public boolean remove(Object object)
     {
       throw new UnsupportedOperationException();
     }

    @Override
     public boolean removeAll(Collection<?> collection)
     {
       throw new UnsupportedOperationException();
     }

    @Override
     public void clear()
     {
       throw new UnsupportedOperationException();
     }
  }
}
