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
 * $Id: ECollections.java,v 1.4 2005/12/14 07:33:25 marcelop Exp $
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
  }

  /**
   * Reverses the order of the elements in the specified EList.
   */
  public static void reverse(EList list)
  {
    int last = list.size() - 1;
    for (int i = 0; i < last; i++)
    {
      list.move(i, last);
    }
  }
  
  /**
   * Searches for the first occurence of the given argument in list starting from
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
  public static int indexOf(List list, Object o, int fromIndex)
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
  public static void sort(EList list)
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
  public static void sort(EList list, Comparator comparator)
  {
    Object[] listAsArray = list.toArray();
    Arrays.sort(listAsArray, comparator);
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
   * This implementation mimimizes the number of notifications the operation will produce.
   * Objects already in the list will be moved, missing objects will be added, and extra objects will be removed.
   * If <code>eList</code>'s contents and order are already exactly that of the <code>prototype</code> list,
   * no change will be made.
   * @param eList the list to set.
   * @param prototypeList the list representing the desired content and order.
   */
  public static void setEList(EList eList, List prototypeList)
  {
    int index = 0;
    for (Iterator objects = prototypeList.iterator(); objects.hasNext(); ++index)
    {
      Object prototypeObject = objects.next();
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
  public static EList unmodifiableEList(EList list)
  {
    return new UnmodifiableEList(list);
  }

  /**
   * Returns an unmodifiable view of the map.
   * @return an unmodifiable view of the map.
   */
  public static EMap unmodifiableEMap(EMap map)
  {
    return new UnmodifiableEMap(map);
  }

  /**
   * An unmodifiable empty list with an efficient reusable iterator.
   */
  public static final EList EMPTY_ELIST = new EmptyUnmodifiableEList();

  /**
   * An unmodifiable empty map with an efficient reusable iterator.
   */
  public static final EMap EMPTY_EMAP = new EmptyUnmodifiableEMap();
  

  private static class UnmodifiableEList implements EList
  {
    protected EList list;

    public UnmodifiableEList(EList list)
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

    public Object[] toArray(Object[] a)
    {
      return list.toArray(a);
    }

    public String toString()
    {
      return list.toString();
    }

    public Iterator iterator()
    {
      return 
        new Iterator()
        {
          Iterator i = list.iterator();

          public boolean hasNext()
          {
            return i.hasNext();
          }
          public Object next()
          {
            return i.next();
          }
          public void remove()
          {
            throw new UnsupportedOperationException();
          }
        };
    }

    public boolean add(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean remove(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection coll)
    {
      return list.containsAll(coll);
    }

    public boolean addAll(Collection coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection coll)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    public boolean equals(Object o)
    {
      return list.equals(o);
    }

    public int hashCode()
    {
      return list.hashCode();
    }

    public Object get(int index)
    {
      return list.get(index);
    }

    public Object set(int index, Object element)
    {
      throw new UnsupportedOperationException();
    }

    public void add(int index, Object element)
    {
      throw new UnsupportedOperationException();
    }

    public Object remove(int index)
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

    public boolean addAll(int index, Collection collection)
    {
      throw new UnsupportedOperationException();
    }

    public ListIterator listIterator()
    {
      return listIterator(0);
    }

    public ListIterator listIterator(final int index)
    {
      return 
        new ListIterator()
        {
          ListIterator i = list.listIterator(index);

          public boolean hasNext()
          {
            return i.hasNext();
          }

          public Object next()
          {
            return i.next();
          }

          public boolean hasPrevious()
          {
            return i.hasPrevious();
          }

          public Object previous()
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

          public void set(Object o)
          {
            throw new UnsupportedOperationException();
          }

          public void add(Object o)
          {
            throw new UnsupportedOperationException();
          }
        };
    }

    public List subList(int fromIndex, int toIndex)
    {
      return new UnmodifiableEList(new BasicEList(list.subList(fromIndex, toIndex)));
    }

    public void move(int newPosition, Object o)
    {
      throw new UnsupportedOperationException();
    }

    public Object move(int newPosition, int oldPosition)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class UnmodifiableEMap extends UnmodifiableEList implements EMap
  {
    protected EMap eMap;
    
    public UnmodifiableEMap(EMap eMap)
    {
      super(eMap);
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

    public Set entrySet()
    {
      return Collections.unmodifiableSet(eMap.entrySet());
    }

    public Object get(Object key)
    {
      return eMap.get(key);
    }

    public int indexOfKey(Object key)
    {
      return eMap.indexOf(key);
    }

    public Set keySet()
    {
      return Collections.unmodifiableSet(eMap.keySet());
    }

    public Map map()
    {
      return Collections.unmodifiableMap(eMap.map());
    }
    
    public Collection values()
    {
      return Collections.unmodifiableCollection(eMap.values());
    }
    
    public Object put(Object key, Object value)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(EMap map)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(Map map)
    {
      throw new UnsupportedOperationException();
    }

    public Object removeKey(Object key)
    {
      throw new UnsupportedOperationException();
    }
  }

  private static class EmptyUnmodifiableEList implements EList
  {
    public int size()
    {
      return 0;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public boolean equals(Object o)
    {
      return Collections.EMPTY_LIST.equals(o);
    }

    public int hashCode()
    {
      return Collections.EMPTY_LIST.hashCode();
    }

    public Object get(int index)
    {
      return Collections.EMPTY_LIST.get(index);
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

    ListIterator listIterator = 
      new ListIterator()
      {
        public boolean hasNext()
        {
          return false;
        }
        public Object next()
        {
          throw new NoSuchElementException();
        }
        public boolean hasPrevious()
        {
          return false;
        }
        public Object previous()
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
        public void set(Object o)
        {
          throw new UnsupportedOperationException();
        }
        public void add(Object o)
        {
          throw new UnsupportedOperationException();
        }
     };

    public Iterator iterator()
    {
      return listIterator;
    }

    public ListIterator listIterator()
    {
      return listIterator;
    }

    public ListIterator listIterator(int index)
    {
      return listIterator;
    }

    public List subList(int fromIndex, int toIndex)
    {
      return Collections.EMPTY_LIST.subList(fromIndex, toIndex);
    }


    public Object[] toArray()
    {
      return Collections.EMPTY_LIST.toArray();
    }

    public Object[] toArray(Object[] a)
    {
      return Collections.EMPTY_LIST.toArray(a);
    }

    public String toString()
    {
      return Collections.EMPTY_LIST.toString();
    }

    public boolean add(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean remove(Object o)
    {
      throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection coll)
    {
      return false;
    }

    public boolean addAll(Collection coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection coll)
    {
      throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection coll)
    {
      throw new UnsupportedOperationException();
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    public Object set(int index, Object element)
    {
      throw new UnsupportedOperationException();
    }

    public void add(int index, Object element)
    {
      throw new UnsupportedOperationException();
    }

    public Object remove(int index)
    {
      throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection collection)
    {
      throw new UnsupportedOperationException();
    }

    public void move(int newPosition, Object o)
    {
      throw new UnsupportedOperationException();
    }

    public Object move(int newPosition, int oldPosition)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static class EmptyUnmodifiableEMap extends EmptyUnmodifiableEList implements EMap
  {
    public boolean containsKey(Object key)
    {
      return false;
    }

    public boolean containsValue(Object value)
    {
      return false;
    }

    public Set entrySet()
    {
      return Collections.EMPTY_SET;
    }

    public Object get(Object key)
    {
      return null;
    }

    public int indexOfKey(Object key)
    {
      return -1;
    }

    public Set keySet()
    {
      return Collections.EMPTY_SET;
    }

    public Map map()
    {
      return Collections.EMPTY_MAP;
    }
    
    public Collection values()
    {
      return Collections.EMPTY_LIST;
    }
    
    public Object put(Object key, Object value)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(EMap map)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(Map map)
    {
      throw new UnsupportedOperationException();
    }

    public Object removeKey(Object key)
    {
      throw new UnsupportedOperationException();
    }
  }
}
