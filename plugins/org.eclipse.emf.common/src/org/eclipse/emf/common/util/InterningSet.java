/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.common.util;

import java.util.Set;

/**
 * A set that supports {@link #intern(Object) intern} and {@link #get(Object) get}.
 * It's useful for maintaining a cache of instances.
 * @since 2.9
 */
public interface InterningSet<E> extends Set<E>
{
  /**
   * Returns either the instance already contained in the set that's equal to the given object, or adds the object to the set and returns it.
   * @param object the object to intern.
   * @return the existing instance already contained in the set equal to the given object, or the object itself (or perhaps in some implementations, an object equal to the object itself).
   */
  E intern(E object);

  /**
   * Returns either the instance already contained in the set that's equal to the given object, or <code>null</code> if the object is not in the set.
   * @param object the object to intern.
   * @return the existing instance already contained in the set equal to the given object, or <code>null</code> if the object is not in the set.
   */
  E get(E object);
}
