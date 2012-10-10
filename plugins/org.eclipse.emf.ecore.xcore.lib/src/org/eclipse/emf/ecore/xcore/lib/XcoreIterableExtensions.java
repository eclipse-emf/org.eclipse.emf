/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.lib;

import java.util.Comparator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.lib.ListExtensions;


/**
 * An extension library for {@link Iterable iterables}.
 */
public class XcoreIterableExtensions
{
  /**
   * Creates a sorted list containing the elements of the given iterable in ascending order according to the natural ordering of the elements.
   *
   * @param iterable the items to be sorted. May not be <code>null</code>.
   * @return a sorted list of the iterable's elements.
   */
  public static <T extends Comparable<? super T>> EList<T> sortEList(Iterable<T> iterable)
  {
    BasicEList<T> result = ECollections.newBasicEList(iterable);
    ECollections.sort(result);
    return result;
  }

  /**
   * Creates a sorted list containing the element of the given iterable in ascending order according to the order induced by the comparator.
   *
   * @param iterable the items to be sorted. May not be <code>null</code>.
   * @param comparator the comparator to be used; it may be <code>null</code> to indicate that the natural ordering of the elements should be used.
   * @return a sorted list of the iterable's elements.
   */
  public static <T> EList<T> sortEList(Iterable<T> iterable, Comparator<? super T> comparator)
  {
    BasicEList<T> result = ECollections.newBasicEList(iterable);
    ECollections.sort(result, comparator);
    return result;
  }

  /**
   * Creates a sorted list that contains the elements of the given iterable in ascending order according to the nature order of the corresponding element induced by the key function.
   *
   * @param iterable the elements to be sorted. May not be <code>null</code>.
   * @param key the key function to be applied to each element. May not be <code>null</code>.
   * @return a sorted list of the iterable's elements.
   */
  public static <T, C extends Comparable<? super C>> EList<T> sortEListBy(Iterable<T> iterable, final Functions.Function1<? super T, C> key)
  {
    BasicEList<T> result = ECollections.newBasicEList(iterable);
    ListExtensions.sortInplaceBy(result, key);
    return  result;
  }
}
