/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: FeatureMapUtil.java,v 1.15 2005/02/08 13:51:04 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


public final class FeatureMapUtil
{
  protected static final Class VALIDATOR_CLASS = Validator.class;

  private FeatureMapUtil()
  {
  }

  public static void addText(FeatureMap featureMap, String text)
  {
    featureMap.add(XMLTypeFeatures.TEXT, text);
  }

  public static void addText(FeatureMap featureMap, int index, String text)
  {
    featureMap.add(index, XMLTypeFeatures.TEXT, text);
  }

  public static boolean isText(FeatureMap.Entry entry)
  {
    return entry.getEStructuralFeature() == XMLTypeFeatures.TEXT;
  }

  public static boolean isText(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature == XMLTypeFeatures.TEXT;
  }

  public static void addCDATA(FeatureMap featureMap, String cdata)
  {
    featureMap.add(XMLTypeFeatures.CDATA, cdata);
  }

  public static void addCDATA(FeatureMap featureMap, int index, String cdata)
  {
    featureMap.add(index, XMLTypeFeatures.CDATA, cdata);
  }

  public static boolean isCDATA(FeatureMap.Entry entry)
  {
    return entry.getEStructuralFeature() == XMLTypeFeatures.CDATA;
  }

  public static boolean isCDATA(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature == XMLTypeFeatures.CDATA;
  }

  public static void addComment(FeatureMap featureMap, String comment)
  {
    featureMap.add(XMLTypeFeatures.COMMENT, comment);
  }

  public static void addComment(FeatureMap featureMap, int index, String comment)
  {
    featureMap.add(index, XMLTypeFeatures.COMMENT, comment);
  }

  public static boolean isComment(FeatureMap.Entry entry)
  {
    return entry.getEStructuralFeature() == XMLTypeFeatures.COMMENT;
  }

  public static boolean isComment(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature == XMLTypeFeatures.COMMENT;
  }

  public static boolean isFeatureMap(EStructuralFeature eStructuralFeature)
  {
    return ((EStructuralFeature.Internal)eStructuralFeature).isFeatureMap();
  }

  public static boolean isFeatureMapEntry(EClassifier eClassifier)
  {
    return eClassifier.getInstanceClassName() == "org.eclipse.emf.ecore.util.FeatureMap$Entry";
  }

  public static FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value)
  {
    return new EntryImpl(eStructuralFeature, value);
  }

  public static class EntryImpl implements FeatureMap.Entry
  {
    protected final EStructuralFeature eStructuralFeature;
    protected final Object value;

    public EntryImpl(EStructuralFeature eStructuralFeature, Object value)
    {
      this.eStructuralFeature = eStructuralFeature;
      this.value = value;
      if (value != null && !eStructuralFeature.getEType().isInstance(value))
      {
        throw new ClassCastException();
      }
    }

    public EStructuralFeature getEStructuralFeature()
    {
      return eStructuralFeature;
    }

    public Object getValue()
    {
      return value;
    }

    public boolean equals(Object that)
    {
      if (this == that)
      {
        return true;
      }
      else if (!(that instanceof FeatureMap.Entry))
      {
        return false;
      }
      else
      {
        FeatureMap.Entry entry = (FeatureMap.Entry)that;
        return 
          entry.getEStructuralFeature() == eStructuralFeature &&
          (value == null ? entry.getValue() == null : value.equals(entry.getValue()));
      }
    }

    public int hashCode()
    {
     return eStructuralFeature.hashCode() ^ (value == null ? 0 : value.hashCode());
    }

    public String toString()
    {
      String prefix = eStructuralFeature.getEContainingClass().getEPackage().getNsPrefix();
      eStructuralFeature.getName();
      return 
         (prefix != null && prefix.length() != 0 ? 
            prefix + ":" + eStructuralFeature.getName() : 
            eStructuralFeature.getName()) + 
           "=" + value;
/*
      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (feature: ");
      result.append(eStructuralFeature.getName());
      result.append(", value: ");
      result.append(value);
      result.append(")");
      return result.toString();
*/
    }
  }

  public static abstract class BasicFeatureEIterator implements ListIterator
  {
    protected final EStructuralFeature eStructuralFeature;
    protected final FeatureMap.Internal featureMap;

    protected int entryCursor;
    protected int cursor;
    protected int prepared;
    protected Object preparedResult;
    protected int expectedModCount;
    protected int lastCursor;
    protected boolean isFeatureMap;
    protected Validator validator;

    public BasicFeatureEIterator(EStructuralFeature eStructuralFeature, FeatureMap.Internal featureMap)
    {
      this.eStructuralFeature = eStructuralFeature;
      this.featureMap = featureMap;
      expectedModCount = featureMap.getModCount();
      isFeatureMap = isFeatureMap(eStructuralFeature);
      validator = getValidator(featureMap.getEObject().eClass(), eStructuralFeature);
    }

    protected boolean resolve()
    {
      return false;
    }

    protected Object extractValue(FeatureMap.Entry entry)
    {
      return isFeatureMap ? entry : entry.getValue();
    }

    public boolean hasNext()
    {
      switch (prepared)
      {
        case 2:
        {
          return true;
        }
        case 1:
        {
          return false;
        }
        case -1:
        {
          ++entryCursor;
        }
        default:
        {
          return scanNext();
        }
      }
    }

    protected abstract boolean scanNext();
/*
    {
      FeatureMap.Entry [] entries = (FeatureMap.Entry [])featureMap.data;
      while (entryCursor < size)
      {
        FeatureMap.Entry entry = entries[entryCursor];
        if (entry.getEStructuralFeature() == eStructuralFeature)
        {
          preparedResult = entry.getValue();
          prepared = 2;
          return true;
        }
        ++entryCursor;
      }

      prepared = 1;
      lastCursor = -1;
      return false;

    }
*/

    public Object next()
    {
      if (hasNext())
      {
        checkModCount();

        if (resolve())
        {
          preparedResult = featureMap.resolveProxy(eStructuralFeature, entryCursor, cursor, preparedResult);
        }

        lastCursor = cursor;
        ++cursor;

        ++entryCursor;
        prepared = 0;
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
        case -2:
        {
          return true;
        }
        case -1:
        {
          return false;
        }
        case 1:
        {
          --entryCursor;
        }
        default:
        {
          return scanPrevious();
        }
      }
    }

    protected abstract boolean scanPrevious();
/*
    {
      FeatureMap.Entry [] entries = (FeatureMap.Entry [])featureMap.data;
      while (--entryCursor >= 0)
      {
        FeatureMap.Entry entry = entries[entryCursor];
        if (entry.getEStructuralFeature() == eStructuralFeature)
        {
          preparedResult = entry.getValue();
          prepared = -2;
          return true;
        }
      }

      prepared = -1;
      lastCursor = -1;
      return false;
    }
*/

    public Object previous()
    {
      if (hasPrevious())
      {
        checkModCount();
        lastCursor = --cursor;
        if (resolve())
        {
          preparedResult = featureMap.resolveProxy(eStructuralFeature, entryCursor, cursor, preparedResult);
        }
        // --entryCursor;
        prepared = 0;
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
      if (lastCursor == -1)
      {
        throw new IllegalStateException();
      }
      checkModCount();

      try
      {
        featureMap.add(eStructuralFeature, cursor, o);
        expectedModCount = featureMap.getModCount();
        next();
/*

        featureMap.add(eStructuralFeature, cursor++, o);
        expectedModCount = featureMap.getModCount();

        ++entryCursor;

        ++lastCursor;
        // lastCursor = -1;
        // prepared = 0;
*/
      }
      catch (IndexOutOfBoundsException exception)
      {
        throw new ConcurrentModificationException();
      }
    }

    public void remove()
    {
      if (lastCursor == -1)
      {
        throw new IllegalStateException();
      }
      checkModCount();

      try
      {
        featureMap.remove(eStructuralFeature, lastCursor);
        expectedModCount = featureMap.getModCount();
        if (lastCursor < cursor)
        {
          --cursor;
          --entryCursor;
        }

        --lastCursor;
        //lastCursor = -1;
        //prepared = 0;
      }
      catch (IndexOutOfBoundsException exception)
      {
        throw new ConcurrentModificationException();
      }
    }

    public void set(Object o)
    {
      if (lastCursor == -1)
      {
        throw new IllegalStateException();
      }
      checkModCount();

      try
      {
        featureMap.set(eStructuralFeature, lastCursor, o);
        expectedModCount = featureMap.getModCount();
      }
      catch (IndexOutOfBoundsException exception)
      {
        throw new ConcurrentModificationException();
      }
    }

    /**
     * Checks that the modification count is as expected.
     * @exception ConcurrentModificationException if the modification count is not as expected.
     */
    protected void checkModCount()
    {
      if (featureMap.getModCount() != expectedModCount)
      {
        throw new ConcurrentModificationException();
      }
    }
  }

  public static class FeatureEList extends AbstractList implements InternalEList.Unsettable, EStructuralFeature.Setting
  {
    public static class Basic extends FeatureEList
    {
      public Basic(EStructuralFeature feature, FeatureMap.Internal featureMap)
      {
        super(feature, featureMap);
      }

      public Iterator iterator()
      {
        return this.basicIterator();
      }

      public ListIterator listIterator()
      {
        return this.basicListIterator();
      }

      public ListIterator listIterator(int index)
      {
        return this.basicListIterator(index);
      }

      public List basicList()
      {
        return this;
      }
    }

    protected EStructuralFeature feature;
    protected FeatureMap.Internal featureMap;

    public FeatureEList(EStructuralFeature feature, FeatureMap.Internal featureMap)
    {
      this.feature= feature;
      this.featureMap = featureMap;
    }

    public int size()
    {
      return featureMap.size(getEStructuralFeature());
    }

    public boolean isEmpty()
    {
      return featureMap.isEmpty(getEStructuralFeature());
    }

    public boolean contains(Object object)
    {
      return featureMap.contains(getEStructuralFeature(), object);
    }

    public int indexOf(Object object)
    {
      return featureMap.indexOf(getEStructuralFeature(), object);
    }

    public int lastIndexOf(Object object)
    {
      return featureMap.lastIndexOf(getEStructuralFeature(), object);
    }

    public boolean containsAll(Collection collection)
    {
      return featureMap.containsAll(getEStructuralFeature(), collection);
    }

    public Iterator iterator()
    {
      return featureMap.iterator(getEStructuralFeature());
    }

    public ListIterator listIterator()
    {
      return featureMap.listIterator(getEStructuralFeature());
    }

    public ListIterator listIterator(int index)
    {
      return featureMap.listIterator(getEStructuralFeature(), index);
    }

/*
    public List subList(int from, int to)
    {
      return featureMap.subList(getEStructuralFeature(), from, to);
    }
*/

    public Object basicGet(int index)
    {
      return featureMap.basicGet(index);
    }

    public List basicList()
    {
      return featureMap.basicList(getEStructuralFeature());
    }

    public Iterator basicIterator()
    {
      return featureMap.basicIterator(getEStructuralFeature());
    }

    public ListIterator basicListIterator()
    {
      return featureMap.basicListIterator(getEStructuralFeature());
    }

    public ListIterator basicListIterator(int index)
    {
      return featureMap.basicListIterator(getEStructuralFeature(), index);
    }

    public Object[] toArray()
    {
      return featureMap.toArray(getEStructuralFeature());
    }

    public Object[] toArray(Object[] array)
    {
      return featureMap.toArray(getEStructuralFeature(), array);
    }

    public boolean add(Object object)
    {
      return featureMap.add(getEStructuralFeature(), object);
    }

    public void add(int index, Object object)
    {
      featureMap.add(getEStructuralFeature(), index, object);
    }

    public boolean addAll(Collection collection)
    {
      return featureMap.addAll(getEStructuralFeature(), collection);
    }

    public boolean addAll(int index, Collection collection)
    {
      return featureMap.addAll(getEStructuralFeature(), index, collection);
    }

    public void addUnique(Object object)
    {
      featureMap.addUnique(getEStructuralFeature(), object);
    }

    public void addUnique(int index, Object object)
    {
      featureMap.addUnique(getEStructuralFeature(), index, object);
    }

    public NotificationChain basicAdd(Object object, NotificationChain notifications)
    {
      return featureMap.basicAdd(getEStructuralFeature(), object, notifications);
    }

    public boolean remove(Object object)
    {
      return featureMap.remove(getEStructuralFeature(), object);
    }

    public Object remove(int index)
    {
      return featureMap.remove(getEStructuralFeature(), index);
    }

    public NotificationChain basicRemove(Object object, NotificationChain notifications)
    {
      return featureMap.basicRemove(getEStructuralFeature(), object, notifications);
    }

    public boolean removeAll(Collection collection)
    {
      return featureMap.removeAll(getEStructuralFeature(), collection);
    }

    public boolean retainAll(Collection collection)
    {
      return featureMap.retainAll(getEStructuralFeature(), collection);
    }

    public void clear()
    {
      featureMap.clear(getEStructuralFeature());
    }

    public void move(int index, Object object)
    {
      featureMap.move(getEStructuralFeature(), index, object);
    }

    public Object move(int targetIndex, int sourceIndex)
    {
      return featureMap.move(getEStructuralFeature(), targetIndex, sourceIndex);
    }

    public Object get(int index)
    {
      return featureMap.get(getEStructuralFeature(), index, true);
    }

    public Object set(int index, Object object)
    {
      return featureMap.set(getEStructuralFeature(), index, object);
    }

    public Object setUnique(int index, Object object)
    {
      return featureMap.setUnique(getEStructuralFeature(), index, object);
    }

    public Object get(boolean resolve)
    {
      return this;
    }

    public void set(Object newValue)
    {
      clear();
      addAll((List)newValue);
    }

    public boolean isSet()
    {
      return !isEmpty();
    }

    public void unset()
    {
      clear();
    }

    public Object getFeature()
    {
      return getEStructuralFeature();
    }

    public int getFeatureID()
    {
      return getEStructuralFeature().getFeatureID();
    }

    public EStructuralFeature getEStructuralFeature()
    {
      // return featureMap.getEObject().eClass().getEStructuralFeature(getFeatureID());
      return feature;
    }

    public EObject getEObject()
    {
      return featureMap.getEObject();
    }

    public String toString()
    {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("[");
      for (Iterator i = basicIterator(); i.hasNext(); )
      {
        stringBuffer.append(String.valueOf(i.next()));
        if (i.hasNext())
        {
          stringBuffer.append(", ");
        }
      }
      stringBuffer.append("]");
      return stringBuffer.toString();
    }
  }

  public static class FeatureFeatureMap extends FeatureEList implements FeatureMap.Internal
  {
    public FeatureFeatureMap(EStructuralFeature feature, FeatureMap.Internal featureMap)
    {
      super(feature, featureMap);
    }
    
    public FeatureMap.ValueListIterator valueListIterator()
    {
      return featureMap.valueListIterator();
    }
    
    public FeatureMap.ValueListIterator valueListIterator(int index)
    {
      return featureMap.valueListIterator(index);
    }

    public EList list(EStructuralFeature feature)
    {
      return featureMap.list(feature);
    }

    public EStructuralFeature getEStructuralFeature(int index)
    {
      return ((Entry)featureMap.get(getEStructuralFeature(), index, false)).getEStructuralFeature();
    }

    public Object getValue(int index)
    {
      return ((Entry)featureMap.get(getEStructuralFeature(), index, false)).getValue();
    }

    public Object setValue(int index, Object value)
    {
      Entry entry = (Entry)featureMap.get(getEStructuralFeature(), index, false);
      set(index, createEntry(entry.getEStructuralFeature(), value));
      return entry.getValue();
    }

    public boolean add(EStructuralFeature feature, Object value)
    {
      return featureMap.add(feature, value);
    }

    public void add(int index, EStructuralFeature feature, Object value)
    {
      add(index, isFeatureMap(feature) ? value : createEntry(feature, value));
    }

    public void add(EStructuralFeature feature, int index, Object value)
    {
      featureMap.add(feature, index, value);
    }

    public boolean addAll(EStructuralFeature feature, Collection values)
    {
      return featureMap.addAll(feature, values);
    }

    public boolean addAll(int index, EStructuralFeature feature, Collection values)
    {
      if (isFeatureMap(feature))
      {
        return addAll(index, values);
      }
      else
      {
        Collection entries = new ArrayList(values.size());
        for (Iterator i = values.iterator(); i.hasNext(); )
        {
          entries.add(createEntry(feature, i.next()));
        }
        return addAll(index, entries);
      }
    }

    public boolean addAll(EStructuralFeature feature, int index, Collection values)
    {
      return featureMap.addAll(feature, index, values);
    }

    public int getModCount()
    {
      return featureMap.getModCount();
    }

    public EObject getEObject()
    {
      return featureMap.getEObject();
    }

    public Object resolveProxy(EStructuralFeature feature, int entryIndex, int index, Object object)
    {
      return featureMap.resolveProxy(feature, entryIndex, index, object);
    }

    public int size(EStructuralFeature feature)
    {
      return featureMap.size(feature);
    }

    public boolean isEmpty(EStructuralFeature feature)
    {
      return featureMap.isEmpty(feature);
    }

    public boolean contains(EStructuralFeature feature, Object object)
    {
      return featureMap.contains(feature, object);
    }

    public boolean containsAll(EStructuralFeature feature, Collection collection)
    {
      return featureMap.containsAll(feature, collection);
    }

    public int indexOf(EStructuralFeature feature, Object object)
    {
      return featureMap.indexOf(feature, object);
    }

    public int lastIndexOf(EStructuralFeature feature, Object object)
    {
      return featureMap.lastIndexOf(feature, object);
    }

    public Iterator iterator(EStructuralFeature feature)
    {
      return featureMap.iterator(feature);
    }

    public ListIterator listIterator(EStructuralFeature feature)
    {
      return featureMap.listIterator(feature);
    }

    public ListIterator listIterator(EStructuralFeature feature, int index)
    {
      return featureMap.listIterator(feature, index);
    }

    // List subList(EStructuralFeature feature, int from, int to);
    // EList list(EStructuralFeature feature);
    public EStructuralFeature.Setting setting(EStructuralFeature feature)
    {
      return featureMap.setting(feature);
    }

    public List basicList(EStructuralFeature feature)
    {
      return featureMap.basicList(feature);
    }

    public Iterator basicIterator(EStructuralFeature feature)
    {
      return featureMap.basicIterator(feature);
    }

    public ListIterator basicListIterator(EStructuralFeature feature)
    {
      return featureMap.basicListIterator(feature);
    }

    public ListIterator basicListIterator(EStructuralFeature feature, int index)
    {
      return featureMap.basicListIterator(feature, index);
    }

    public Object[] toArray(EStructuralFeature feature)
    {
      return featureMap.toArray(feature);
    }

    public Object[] toArray(EStructuralFeature feature, Object [] array)
    {
      return featureMap.toArray(feature, array);
    }

/*
    public boolean add(EStructuralFeature feature, Object object)
    {
      return featureMap.add(feature, object);
    }

    public void add(EStructuralFeature feature, int index, Object object)
    {
      featureMap.add(feature, index, object);
    }

    public boolean addAll(EStructuralFeature feature, Collection collection)
    {
      return featureMap.addAll(feature, collection);
    }

    boolean addAll(EStructuralFeature feature, int index, Collection collection)
    {
      return featureMap.addAll(feature, index, collection);
    }
*/

    public void addUnique(EStructuralFeature feature, Object object)
    {
      featureMap.addUnique(feature, object);
    }

    public void addUnique(EStructuralFeature feature, int index, Object object)
    {
      featureMap.addUnique(feature, index, object);
    }

    public NotificationChain basicAdd(EStructuralFeature feature, Object object, NotificationChain notifications)
    {
      return featureMap.basicAdd(feature, object, notifications);
    }

    public boolean remove(EStructuralFeature feature, Object object)
    {
      return featureMap.remove(feature, object);
    }

    public Object remove(EStructuralFeature feature, int index)
    {
      return featureMap.remove(feature, index);
    }

    public boolean removeAll(EStructuralFeature feature, Collection collection)
    {
      return featureMap.removeAll(feature, collection);
    }

    public NotificationChain basicRemove(EStructuralFeature feature, Object object, NotificationChain notifications)
    {
      return featureMap.basicRemove(feature, object, notifications);
    }

    public boolean retainAll(EStructuralFeature feature, Collection collection)
    {
      return featureMap.retainAll(feature, collection);
    }

    public void clear(EStructuralFeature feature)
    {
      featureMap.clear(feature);
    }

    public void move(EStructuralFeature feature, int index, Object object)
    {
      featureMap.move(feature, index, object);
    }

    public Object move(EStructuralFeature feature, int targetIndex, int sourceIndex)
    {
      return featureMap.move(feature, targetIndex, sourceIndex);
    }

    public Object get(EStructuralFeature feature, boolean resolve)
    {
      return featureMap.get(feature, resolve);
    }

    public Object get(EStructuralFeature feature, int index, boolean resolve)
    {
      return featureMap.get(feature, index, resolve);
    }

    public void set(EStructuralFeature feature, Object object)
    {
      featureMap.set(feature, object);
    }

    public Object set(EStructuralFeature feature, int index, Object object)
    {
      return featureMap.set(feature, index, object);
    }

    public Object setUnique(EStructuralFeature feature, int index, Object object)
    {
      return featureMap.setUnique(feature, index, object);
    }

    public boolean isSet(EStructuralFeature feature)
    {
      return featureMap.isSet(feature);
    }

    public void unset(EStructuralFeature feature)
    {
      featureMap.unset(feature);
    }
  }

  public static class FeatureValue implements EStructuralFeature.Setting
  {
    protected EStructuralFeature feature;
    protected FeatureMap.Internal featureMap;

    public FeatureValue(EStructuralFeature feature, FeatureMap.Internal featureMap)
    {
      this.feature = feature;
      this.featureMap = featureMap;
    }

    public Object get(boolean resolve)
    {
      return featureMap.get(getEStructuralFeature(), -1, resolve);
    }

    public void set(Object newValue)
    {
      featureMap.set(getEStructuralFeature(), newValue);
    }

    public boolean isSet()
    {
      return !featureMap.isEmpty(getEStructuralFeature());
    }

    public void unset()
    {
      featureMap.clear(getEStructuralFeature());
    }

    public Object getFeature()
    {
      return getEStructuralFeature();
    }

    public int getFeatureID()
    {
      return getEStructuralFeature().getFeatureID();
    }

    public EStructuralFeature getEStructuralFeature()
    {
      return feature;
    }

    public EObject getEObject()
    {
      return featureMap.getEObject();
    }
  }

  public static class FeatureENotificationImpl extends ENotificationImpl
  {
    public FeatureENotificationImpl
      (InternalEObject owner, int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index)
    {
      super(owner, eventType, feature, oldObject, newObject, index);
    }

    public FeatureENotificationImpl
      (InternalEObject owner, int eventType, EStructuralFeature feature, Object oldObject, Object newObject, int index, boolean wasSet)
    {
      super(owner, eventType, feature, oldObject, newObject, index, wasSet);
    }

    public boolean merge(Notification notification)
    {
      switch (eventType)
      {
        case Notification.SET:
        case Notification.UNSET:
        {
          Object notificationNotifier = notification.getNotifier();
          if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null))
          {
            newValue = notification.getNewValue();
            if (notification.getEventType() == Notification.SET)
            {
              eventType = Notification.SET;
            }
            return true;
          }
          break;
        }
        case Notification.ADD:
        {
          int notificationEventType = notification.getEventType();
          switch (notificationEventType)
          {
            case Notification.ADD:
            {
              Object notificationNotifier = notification.getNotifier();
              if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null))
              {
                eventType = Notification.ADD_MANY;
                BasicEList addedValues = new BasicEList(2);
                addedValues.add(newValue);
                addedValues.add(notification.getNewValue());
                newValue = addedValues;
                return true;
              }
              break;
            }
          }
          break;
        }
        case Notification.ADD_MANY:
        {
          int notificationEventType = notification.getEventType();
          switch (notificationEventType)
          {
            case Notification.ADD:
            {
              Object notificationNotifier = notification.getNotifier();
              if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null))
              {
                ((Collection)newValue).add(notification.getNewValue());
                return true;
              }
              break;
            }
          }
          break;
        }
        case Notification.REMOVE:
        {
          int notificationEventType = notification.getEventType();
          switch (notificationEventType)
          {
            case Notification.ADD:
            {
              Object notificationNotifier = notification.getNotifier();
              if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null))
              {
                eventType = Notification.SET;
                newValue = notification.getNewValue();
                return true;
              }
              break;
            }
            case Notification.REMOVE:
            {
              Object notificationNotifier = notification.getNotifier();
              if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null))
              {
                eventType = Notification.REMOVE_MANY;
                BasicEList removedValues = new BasicEList(2);
                removedValues.add(oldValue);
                removedValues.add(notification.getOldValue());
                oldValue = removedValues;

                int [] positions = new int [] { position, notification.getPosition() };
                newValue = positions;
                return true;
              }
              break;
            }
          }
          break;
        }
        case Notification.REMOVE_MANY:
        {
          int notificationEventType = notification.getEventType();
          switch (notificationEventType)
          {
            case Notification.REMOVE:
            {
              Object notificationNotifier = notification.getNotifier();
              if (notificationNotifier == getNotifier() && getFeatureID(null) == notification.getFeatureID(null))
              {
                ((Collection)oldValue).add(notification.getOldValue());

                int [] positions = (int [])newValue;
                int [] newPositions = new int [positions.length + 1];

                System.arraycopy(positions, 0, newPositions, 0, positions.length);
                newPositions[positions.length] = notification.getPosition();
                newValue = newPositions;

                return true;
              }
              break;
            }
          }
          break;
        }
      }

      return false;
    }
  }

  public interface Validator
  {
    boolean isValid(EStructuralFeature feature);
  }

  public static class BasicValidator implements Validator
  {
    protected EClass containingClass;
    protected EStructuralFeature eStructuralFeature;
    protected List groupMembers;
    protected List wildcards;
    protected String name;
    protected boolean isElement;

    public BasicValidator(EClass containingClass, EStructuralFeature eStructuralFeature)
    {
      this.containingClass = containingClass;
      this.eStructuralFeature = eStructuralFeature;

      wildcards = ExtendedMetaData.INSTANCE.getWildcards(eStructuralFeature);
      if (!wildcards.isEmpty())
      {
        isElement = ExtendedMetaData.INSTANCE.getFeatureKind(eStructuralFeature) == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE;
      }
      else if (ExtendedMetaData.INSTANCE.getMixedFeature(containingClass) == eStructuralFeature)
      {
        isElement = true;
        groupMembers = new ArrayList();
        wildcards = new UniqueEList();
        wildcards.add(XMLTypePackage.eNS_URI);
        for (Iterator i = ExtendedMetaData.INSTANCE.getAllElements(containingClass).iterator(); i.hasNext(); )
        {
          EStructuralFeature feature = (EStructuralFeature)i.next();
          switch (ExtendedMetaData.INSTANCE.getFeatureKind(feature))
          {
            case ExtendedMetaData.ELEMENT_FEATURE:
            {
              groupMembers.add(feature);
              break;
            }
            case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
            {
              wildcards.addAll(ExtendedMetaData.INSTANCE.getWildcards(feature));
              break;
            }
          }
        }
      }
      else if (isFeatureMap(eStructuralFeature))
      {
        isElement = true;
        wildcards = null;
        groupMembers = new ArrayList();
        for (Iterator i = containingClass.getEAllStructuralFeatures().iterator(); i.hasNext(); )
        {
          EStructuralFeature feature = (EStructuralFeature)i.next();
          for (EStructuralFeature group = ExtendedMetaData.INSTANCE.getGroup(feature); 
               group != null; 
               group = ExtendedMetaData.INSTANCE.getGroup(group))
          {
            if (group == eStructuralFeature)
            {
              groupMembers.add(feature);
            }
          }
        }
      }
      else 
      {
        wildcards = null;
        isElement = true;
        groupMembers = Collections.singletonList(eStructuralFeature);
      }
    }

    public boolean isValid(EStructuralFeature feature)
    {
      if (groupMembers != null &&
            (groupMembers.contains(feature) ||
               groupMembers.contains(ExtendedMetaData.INSTANCE.getGroup(feature)) ||
               groupMembers.contains(ExtendedMetaData.INSTANCE.getAffiliation(containingClass, feature))))
      {
        return true;
      }

      if (wildcards != null)
      {
        if (ExtendedMetaData.INSTANCE.matches(wildcards, ExtendedMetaData.INSTANCE.getNamespace(feature)))
        {
          return isElement == (ExtendedMetaData.INSTANCE.getFeatureKind(feature) == ExtendedMetaData.ELEMENT_FEATURE);
        }
      }

      return false;
    }
  }

  protected static Validator NULL_VALIDATOR = 
    new Validator()
    {
      public boolean isValid(EStructuralFeature eStructuralFeature)
      {
        return true;
      }
    };

  public static Validator getValidator(EClass containingClass, EStructuralFeature eStructuralFeature)
  {
    if (eStructuralFeature == null)
    {
      return NULL_VALIDATOR;
    }
    else
    {
      BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder holder = 
        (BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder)eStructuralFeature;
      BasicExtendedMetaData.EStructuralFeatureExtendedMetaData extendedMetaData = holder.getExtendedMetaData();
      if (extendedMetaData == null)
      {
        // For the extended meta data to be created.
        //
        ExtendedMetaData.INSTANCE.getName(eStructuralFeature);
        extendedMetaData = holder.getExtendedMetaData();
      }
      Map validatorMap = extendedMetaData.getValidatorMap();
      Validator result = (Validator)validatorMap.get(containingClass);
      if (result == null)
      {
        validatorMap.put(containingClass, result = new BasicValidator(containingClass, eStructuralFeature));
      }
      return result;
    }
  }

  public static boolean isMany(EObject owner, EStructuralFeature feature)
  {
    int upperBound = feature.getUpperBound();
    if (upperBound == ETypedElement.UNSPECIFIED_MULTIPLICITY)
    {
      EClass eclass = owner.eClass();
      if (eclass.getEAllStructuralFeatures().contains(feature))
      {
        return false;
      }
      else if (feature.getEContainingClass().getEPackage() == XMLTypePackage.eINSTANCE)
      {
        return true;
      }
      else
      {
        EStructuralFeature affiliation = ExtendedMetaData.INSTANCE.getAffiliation(eclass, feature);
        return
          affiliation == null ||
            affiliation.isMany() && ExtendedMetaData.INSTANCE.getFeatureKind(affiliation) != ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE;
      }
    }
    else
    {
      return upperBound == ETypedElement.UNBOUNDED_MULTIPLICITY || upperBound > 1 || FeatureMapUtil.isFeatureMap(feature);
    }
  }
}

final class XMLTypeFeatures
{
  public static final EStructuralFeature TEXT = XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text();
  public static final EStructuralFeature CDATA = XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA();
  public static final EStructuralFeature COMMENT = XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment();
}

