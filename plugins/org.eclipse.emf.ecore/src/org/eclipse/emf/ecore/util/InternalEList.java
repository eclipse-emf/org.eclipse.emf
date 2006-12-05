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
 * $Id: InternalEList.java,v 1.4 2006/12/05 20:22:26 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;


public interface InternalEList<E> extends EList<E>
{
  /**
   * Returns the unresolved value.
   */
  E basicGet(int index);

  /**
   * Returns an unmodifiable list that yields unresolved values.
   */
  List<E> basicList();

  /**
   * Returns an iterator that yields unresolved values.
   */
  Iterator<E> basicIterator();

  /**
   * Returns a list iterator that yields unresolved values.
   */
  ListIterator<E> basicListIterator();

  /**
   * Returns a list iterator that yields unresolved values.
   */
  ListIterator<E> basicListIterator(int index);

  /**
   * Removes the object with without updating the inverse.
   */
  NotificationChain basicRemove(Object object, NotificationChain notifications);

  /**
   * Adds the object without updating the inverse.
   */
  NotificationChain basicAdd(E object, NotificationChain notifications);

  /**
   * Adds the object without verifying uniqueness.
   */
  void addUnique(E object);

  /**
   * Adds the object without verifying uniqueness.
   */
  void addUnique(int index, E object);

  /**
   * Sets the object without verifying uniqueness.
   */
  E setUnique(int index, E object);

  /**
   * Additional API for unsettable lists.
   */
  interface Unsettable<E> extends InternalEList<E>
  {
    /**
     * Returns whether a value is held by the feature of the object.
     * @return whether a value is held by the feature of the object.
     * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    boolean isSet();
  
    /**
     * Unsets the value held by the feature of the object.
     * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)
     */
    void unset();
  }
}
