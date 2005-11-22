/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: FeatureMap.java,v 1.4 2005/11/22 22:35:37 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A list of entries where each entry consists of a feature and a single value of that feature's type.
 */
public interface FeatureMap extends EList
{
  /**
   * A pair consisting of a feature and a single value of that feature's type.
   */
  public interface Entry
  {
    /**
     * Returns the feature.
     * @return the feature.
     */
    EStructuralFeature getEStructuralFeature();

    /**
     * Returns the value.
     * @return the value.
     */
    Object getValue();
  }
  
  public interface ValueListIterator extends EContentsEList.FeatureListIterator
  {
    void add(EStructuralFeature eStructuralFeature, Object value);
  }
  
  ValueListIterator valueListIterator();
  ValueListIterator valueListIterator(int index);

  EList list(EStructuralFeature feature);

  EStructuralFeature getEStructuralFeature(int index);
  Object getValue(int index);
  Object setValue(int index, Object value);

  Object get(EStructuralFeature feature, boolean resolve);
  void set(EStructuralFeature feature, Object object);
  boolean isSet(EStructuralFeature feature);
  void unset(EStructuralFeature feature);

  boolean add(EStructuralFeature feature, Object value);
  void add(int index, EStructuralFeature feature, Object value);

  boolean addAll(EStructuralFeature feature, Collection values);
  boolean addAll(int index, EStructuralFeature feature, Collection values);

  interface Internal extends FeatureMap, InternalEList, EStructuralFeature.Setting
  {
    int getModCount();
    EObject getEObject();

    Object resolveProxy(EStructuralFeature feature, int entryIndex, int index, Object object);

    int size(EStructuralFeature feature);
    boolean isEmpty(EStructuralFeature feature);
    boolean contains(EStructuralFeature feature, Object object);
    boolean containsAll(EStructuralFeature feature, Collection collection);
    int indexOf(EStructuralFeature feature, Object object);
    int lastIndexOf(EStructuralFeature feature, Object object);
    Iterator iterator(EStructuralFeature feature);
    ListIterator listIterator(EStructuralFeature feature);
    ListIterator listIterator(EStructuralFeature feature, int index);
    // List subList(EStructuralFeature feature, int from, int to);
    // EList list(EStructuralFeature feature);
    EStructuralFeature.Setting setting(EStructuralFeature feature);
    List basicList(EStructuralFeature feature);
    Iterator basicIterator(EStructuralFeature feature);
    ListIterator basicListIterator(EStructuralFeature feature);
    ListIterator basicListIterator(EStructuralFeature feature, int index);
    Object[] toArray(EStructuralFeature feature);
    Object[] toArray(EStructuralFeature feature, Object [] array);
    void add(EStructuralFeature feature, int index, Object object);
    boolean addAll(EStructuralFeature feature, int index, Collection collection);
    void addUnique(EStructuralFeature feature, Object object);
    void addUnique(EStructuralFeature feature, int index, Object object);
    NotificationChain basicAdd(EStructuralFeature feature, Object object, NotificationChain notifications);
    boolean remove(EStructuralFeature feature, Object object);
    Object remove(EStructuralFeature feature, int index);
    boolean removeAll(EStructuralFeature feature, Collection collection);
    NotificationChain basicRemove(EStructuralFeature feature, Object object, NotificationChain notifications);
    boolean retainAll(EStructuralFeature feature, Collection collection);
    void clear(EStructuralFeature feature);
    void move(EStructuralFeature feature, int index, Object object);
    Object move(EStructuralFeature feature, int targetIndex, int sourceIndex);
    Object get(EStructuralFeature feature, int index, boolean resolve);
    Object set(EStructuralFeature feature, int index, Object object);
    Object setUnique(EStructuralFeature feature, int index, Object object);

    interface Wrapper
    {
      FeatureMap featureMap();
    }

    Wrapper getWrapper();
    void setWrapper(Wrapper wrapper);
  }
}
