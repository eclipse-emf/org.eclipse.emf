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
 * $Id: InternalEList.java,v 1.3 2005/06/08 06:20:10 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;


public interface InternalEList extends EList
{
  /**
   * Returns the unresolved value.
   */
  Object basicGet(int index);

  /**
   * Returns an unmodifiable list that yields unresolved values.
   */
  List basicList();

  /**
   * Returns an iterator that yields unresolved values.
   */
  Iterator basicIterator();

  /**
   * Returns a list iterator that yields unresolved values.
   */
  ListIterator basicListIterator();

  /**
   * Returns a list iterator that yields unresolved values.
   */
  ListIterator basicListIterator(int index);

  /**
   * Removes the object with without updating the inverse.
   */
  NotificationChain basicRemove(Object object, NotificationChain notifications);

  /**
   * Adds the object without updating the inverse.
   */
  NotificationChain basicAdd(Object object, NotificationChain notifications);

  /**
   * Adds the object without verifying uniqueness.
   */
  void addUnique(Object object);

  /**
   * Adds the object without verifying uniqueness.
   */
  void addUnique(int index, Object object);

  /**
   * Sets the object without verifying uniqueness.
   */
  Object setUnique(int index, Object object);

  /**
   * Additional API for unsettable lists.
   */
  interface Unsettable extends InternalEList
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
