/**
 * <copyright>
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: ECollections.java,v 1.2 2010/04/28 20:37:41 khussey Exp $
 */
package org.eclipse.emf.common.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * Support for {@link #EMPTY_ELIST empty} and {@link #unmodifiableEList unmodifiable} <code>EList</code>s.
 */
public class ECollections
{
  // Suppress default constructor for noninstantiability.
  private ECollections()
  {
    super();
  }
  
  /**
   * Moves the object to the new position, if is in the list.
   * @param list
   * @param newPosition the position of the object after the move.
   * @param object the object to move.
   */
  public static <T> void move(List<T> list, int newPosition, T object)
  {
    if (list instanceof EList<?>)
    {
      ((EList<T>)list).move(newPosition, object);
    }
    else
    {
      list.remove(object);
      list.add(newPosition, object);
    }
  }

  /**
   * Moves the object from the old position to the new position.
   * @param list
   * @param targetIndex the position of the object after the move.
   * @param sourceIndex the position of the object before the move.
   * @return the moved object
   */
  public static <T> T move(List<T> list, int targetIndex, int sourceIndex)
  {
    if (list instanceof EList<?>)
    {
      return ((EList<T>)list).move(targetIndex, sourceIndex);
    }
    else
    {
      T object = list.remove(sourceIndex);
      list.add(targetIndex, object);
      return object;
    }    
  }

  /**
   * Reverses the order of the elements in the specified EList.
   */
  public static void reverse(EList<?> list)
  {
    int last = list.size() - 1;
    for (int i = 0; i < last; i++)
    {
      list.move(i, last);
    }
  }
  
  /**
   * Searches for the first occurrence of the given argument in list starting from
   * a specified index.  The equality is tested using the operator <tt>==<tt> and
   * the <tt>equals</tt> method. 
   * @param list
   * @param o an object (can be null)
   * @param fromIndex 
   * @return the index of the first occurrence of the argument in this
   *         list (where index>=fromIndex); returns <tt>-1</tt> if the 
   *         object is not found.
   * @since 2.1.0
   */
  public static int indexOf(List<?> list, Object o, int fromIndex)
  {
    if (fromIndex < 0)
    {
      fromIndex = 0;
    }

    int size = list.size();
    for (int i = fromIndex; i < size; i++)
    {
      Object element = list.get(i);
      if (o == null)
      {
        if (element == null)
        {
          return i;
        }
      }
      else if (o == element || o.equals(element))
      {
        return i;
      }
    }
    return -1;
  }

  /**
   * Sorts the specified list.  Use this method instead of 
   * {@link Collections#sort(java.util.List)} to 
   * avoid errors when sorting unique lists.
   * @since 2.1.0
  */
  public static void sort(EList<?> list)
  {
    Object[] listAsArray = list.toArray();
    Arrays.sort(listAsArray);   
    for (int i=0; i < listAsArray.length; i++)
    {
      int oldIndex = indexOf(list, listAsArray[i], i);
      if (i != oldIndex)
      {
        list.move(i, oldIndex);
      }
    }    
  }
  
  /**
   * Sorts the specified list based on the order defined by the
   * specified comparator.  Use this method instead of 
   * {@link Collections#sort(java.util.List, java.util.Comparator)} to 
   * avoid errors when sorting unique lists.
   * @since 2.1.0
   */
  public static <T> void sort(EList<T> list, Comparator<? super T> comparator)
  {
    Object[] listAsArray = list.toArray();
    @SuppressWarnings("unchecked") Comparator<Object> objectComparator = (Comparator<Object>)comparator;
    Arrays.sort(listAsArray, objectComparator);
    for (int i=0; i < listAsArray.length; i++)
    {
      int oldIndex = indexOf(list, listAsArray[i], i);
      if (i != oldIndex)
      {
        list.move(i, oldIndex);
      }
    }    
  }
  
  /** 
   * Sets the <code>eList</code>'s contents and order to be exactly that of the <code>prototype</code> list.
   * This implementation minimizes the number of notifications the operation will produce.
   * Objects already in the list will be moved, missing objects will be added, and extra objects will be removed.
   * If <code>eList</code>'s contents and order are already exactly that of the <code>prototype</code> list,
   * no change will be made.
   * @param eList the list to set.
   * @param prototypeList the list representing the desired content and order.
   */
  public static <T> void setEList(EList<T> eList, List<? extends T> prototypeList)
  {
    int index = 0;
    for (T prototypeObject : prototypeList)
    {
      if (eList.size() <= index)
      {
        eList.add(prototypeObject);
      }
      else
      {
        boolean done;
        do
        {
          done = true;
          Object targetObject = eList.get(index);
          if (targetObject == null ? prototypeObject != null : !targetObject.equals(prototypeObject))
          {
            int position = indexOf(eList, prototypeObject, index);
            if (position != -1)
            {
              int targetIndex = indexOf(prototypeList, targetObject, index);
              if (targetIndex == -1)
              {
                eList.remove(index);
                done = false;
              }
              else if (targetIndex > position)
              {
                if (eList.size() <= targetIndex)
                {
                  targetIndex = eList.size() - 1;
                }
                eList.move(targetIndex, index);

                done = false;
              }
              else
              {
                eList.move(index, position);
              }
            }
            else
            {
              eList.add(index, prototypeObject);
            }
          }
        }
        while (!done);
      }
      ++index;
    }
    for (int i = eList.size(); i > index;)
    {
      eList.remove(--i);
    }
  }
  
  /**
   * Returns an unmodifiable view of the list.
   * @return an unmodifiable view of the list.
   */
  public static <T> EList<T> unmodifiableEList(EList<? extends T> list)
  {
    return new UnmodifiableEList<T>(list);
  }

  /**
   * Returns an unmodifiable view of the map.
   * @return an unmodifiable view of the map.
   */
  public static <K, V> EMap<K, V> unmodifiableEMap(EMap<? extends K, ? extends V> map)
  {
    return new UnmodifiableEMap<K, V>(map);
  }

  /**
   * An unmodifiable empty list with an efficient reusable iterator.
   */
  public static final EList<?> EMPTY_ELIST = new EmptyUnmodifiableEList();
  
  @SuppressWarnings("unchecked")
  public static <T> EList<T> emptyEList()
  {
    return (EList<T>)EMPTY_ELIST;
  }

  /**
   * An unmodifiable empty map with an efficient reusable iterator.
   */
  public static final EMap<?, ?> EMPTY_EMAP = new EmptyUnmodifiableEMap();
  
  @SuppressWarnings("unchecked")
  public static <K, V> EMap<K, V> emptyEMap()
  {
    return (EMap<K, V>)EMPTY_EMAP;
  }

  private static class UnmodifiableEList<E> implements EList<E>
  {
    protected EList<? extends E> list;

    public UnmodifiableEList(EList<? extends E> list)
    {
      this.list = list;
    }

    public int size()
    {
      return list.size();
    }

    public boolean isEmpty()
    {
      return list.isEmpty();
    }

    public boolean contains(Object o)
    {
      return list.contains(o);
    }

    public Object[] toArray()
    {
      return list.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
      return list.toArray(a);
    }

    @Override
    public String toString()
    {
      return list.toString();
    }

    public Iterator<E> iterator()
    {
      return 
        new Iterator<E>()
        {
          Iterator<? extends E> i = list.iterator();

          public boolean hasNext()
          {
            return i.hasNext();
          }
          public E next()
          {
            return i.next();
          }
          public void remove()
          {
            throw new UnsupportedOperationException();
          }
        };
    }

    public boolean add(E o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean remove(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> coll)
    {
      return list.containsAll(coll);
    }

    public boolean addAll(Collection<? extends E> coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> coll)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o)
    {
      return list.equals(o);
    }

    @Override
    public int hashCode()
    {
      return list.hashCode();
    }

    public E get(int index)
    {
      return list.get(index);
    }

    public E set(int index, E element)
    {
      throw new UnsupportedOperationException();
    }

    public void add(int index, Object element)
    {
      throw new UnsupportedOperationException();
    }

    public E remove(int index)
    {
      throw new UnsupportedOperationException();
    }

    public int indexOf(Object o)
    {
      return list.indexOf(o);
    }

    public int lastIndexOf(Object o)
    {
      return list.lastIndexOf(o);
    }

    public boolean addAll(int index, Collection<? extends E> collection)
    {
      throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator()
    {
      return listIterator(0);
    }

    public ListIterator<E> listIterator(final int index)
    {
      return 
        new ListIterator<E>()
        {
          ListIterator<? extends E> i = list.listIterator(index);

          public boolean hasNext()
          {
            return i.hasNext();
          }

          public E next()
          {
            return i.next();
          }

          public boolean hasPrevious()
          {
            return i.hasPrevious();
          }

          public E previous()
          {
            return i.previous();
          }

          public int nextIndex()
          {
            return i.nextIndex();
          }

          public int previousIndex()
          {
            return i.previousIndex();
          }

          public void remove()
          {
            throw new UnsupportedOperationException();
          }

          public void set(E o)
          {
            throw new UnsupportedOperationException();
          }

          public void add(E o)
          {
            throw new UnsupportedOperationException();
          }
        };
    }

    public List<E> subList(int fromIndex, int toIndex)
    {
      return new UnmodifiableEList<E>(new BasicEList<E>(list.subList(fromIndex, toIndex)));
    }

    public void move(int newPosition, E o)
    {
      throw new UnsupportedOperationException();
    }

    public E move(int newPosition, int oldPosition)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class UnmodifiableEMap<K, V> extends UnmodifiableEList<Map.Entry<K, V>> implements EMap<K, V>
  {
    protected EMap<? extends K, ? extends V> eMap;
    
    @SuppressWarnings("unchecked")
    public UnmodifiableEMap(EMap<? extends K, ? extends V> eMap)
    {
      super((EMap<K, V>)eMap);
      this.eMap = eMap;
    }
    
    public boolean containsKey(Object key)
    {
      return eMap.containsKey(key);
    }

    public boolean containsValue(Object value)
    {
      return eMap.containsValue(value);
    }

    @SuppressWarnings("unchecked")
    public Set<Map.Entry<K, V>> entrySet()
    {
      return Collections.unmodifiableSet((Set<Map.Entry<K, V>>)(Set<?>)eMap.entrySet());
    }

    public V get(Object key)
    {
      return eMap.get(key);
    }

    public int indexOfKey(Object key)
    {
      return eMap.indexOf(key);
    }

    public Set<K> keySet()
    {
      return Collections.unmodifiableSet(eMap.keySet());
    }

    public Map<K, V> map()
    {
      return Collections.unmodifiableMap(eMap.map());
    }
    
    public Collection<V> values()
    {
      return Collections.unmodifiableCollection(eMap.values());
    }
    
    public V put(K key, V value)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(EMap<? extends K, ? extends V> map)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> map)
    {
      throw new UnsupportedOperationException();
    }

    public V removeKey(Object key)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class BasicEmptyUnmodifiableEList<E>
  {
    public int size()
    {
      return 0;
    }

    public boolean isEmpty()
    {
      return true;
    }

    @Override
    public boolean equals(Object o)
    {
      return Collections.EMPTY_LIST.equals(o);
    }

    @Override
    public int hashCode()
    {
      return Collections.EMPTY_LIST.hashCode();
    }

    public E get(int index)
    {
      Collections.EMPTY_LIST.get(index);
      return null;
    }

    public boolean contains(Object o)
    {
      return false;
    }

    public int indexOf(Object o)
    {
      return -1;
    }

    public int lastIndexOf(Object o)
    {
      return -1;
    }

    ListIterator<E> listIterator = 
      new ListIterator<E>()
      {
        public boolean hasNext()
        {
          return false;
        }
        public E next()
        {
          throw new NoSuchElementException();
        }
        public boolean hasPrevious()
        {
          return false;
        }
        public E previous()
        {
          throw new NoSuchElementException();
        }
        public int nextIndex()
        {
          return 0;
        }
        public int previousIndex()
        {
          return -1;
        }

        public void remove()
        {
          throw new UnsupportedOperationException();
        }
        public void set(E o)
        {
          throw new UnsupportedOperationException();
        }
        public void add(E o)
        {
          throw new UnsupportedOperationException();
        }
     };

    public Iterator<E> iterator()
    {
      return listIterator;
    }

    public ListIterator<E> listIterator()
    {
      return listIterator;
    }

    public ListIterator<E> listIterator(int index)
    {
      return listIterator;
    }

    public List<E> subList(int fromIndex, int toIndex)
    {
      return Collections.<E>emptyList().subList(fromIndex, toIndex);
    }

    public Object[] toArray()
    {
      return Collections.EMPTY_LIST.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
      return Collections.<T>emptyList().toArray(a);
    }

    @Override
    public String toString()
    {
      return Collections.EMPTY_LIST.toString();
    }

    public boolean add(E o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean remove(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> coll)
    {
      return false;
    }

    public boolean addAll(Collection<? extends E> coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> coll)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    public E set(int index, E element)
    {
      throw new UnsupportedOperationException();
    }

    public void add(int index, E element)
    {
      throw new UnsupportedOperationException();
    }

    public E remove(int index)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends E> collection)
    {
      throw new UnsupportedOperationException();
    }

    public void move(int newPosition, E o)
    {
      throw new UnsupportedOperationException();
    }

    public E move(int newPosition, int oldPosition)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class EmptyUnmodifiableEList extends BasicEmptyUnmodifiableEList<Object> implements EList<Object>
  {
    private EmptyUnmodifiableEList()
    {
      super();
    }
  }

  private static class EmptyUnmodifiableEMap extends BasicEmptyUnmodifiableEList<Map.Entry<Object, Object>> implements EMap<Object, Object>
  {
    public boolean containsKey(Object key)
    {
      return false;
    }

    public boolean containsValue(Object value)
    {
      return false;
    }

    public Set<Map.Entry<Object, Object>> entrySet()
    {
      return Collections.emptySet();
    }

    public Object get(Object key)
    {
      return null;
    }

    public int indexOfKey(Object key)
    {
      return -1;
    }

    public Set<Object> keySet()
    {
      return Collections.emptySet();
    }

    public Map<Object, Object> map()
    {
      return Collections.emptyMap();
    }
    
    public Collection<Object> values()
    {
      return Collections.emptyList();
    }
    
    public Object put(Object key, Object value)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(EMap<? extends Object, ? extends Object> map)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends Object, ? extends Object> map)
    {
      throw new UnsupportedOperationException();
    }

    public Object removeKey(Object key)
    {
      throw new UnsupportedOperationException();
    }    
  }
}
