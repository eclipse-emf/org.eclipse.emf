/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.lib;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.xtext.xbase.lib.Pure;

/**
  * An extension library for {@link java.util.Collection collections}.
 */
public class XcoreCollectionLiterals
{
  /**
   * Returns an empty unmodifiable list.
   * @return an empty unmodifiable list.
   */
  @Pure
  public static <T> EList<T> emptyEList()
  {
    return ECollections.emptyEList();
  }

  /**
   * Returns an empty unmodifiable map.
   * @return an empty unmodifiable map.
   */
  @Pure
  public static <K, V> EMap<K, V> emptyEMap()
  {
    return ECollections.emptyEMap();
  }

  /**
   * Returns an immutable list containing the given elements.
   *
   * @param elements the elements that should be contained in the list. May not be <code>null</code>.
   * @return an immutable list containing the given elements.
   */
  @Pure
  public static <T> EList<T> newImmutableEList(T... elements)
  {
    return new XcoreEListExtensions.UnmodiableArrayView<T>(elements.clone());
  }

  /**
   * Creates a mutable {@link BasicEList} containing the given elements.
   *
   * @param initial the initial elements that the list should contain, in order. May not be <code>null</code>.
   * @return a new {@link Basic} containing the given elements
   */
  @Pure
  public static <T> BasicEList<T> newBasicEList(T... elements)
  {
    return ECollections.newBasicEList(elements);
  }
}
