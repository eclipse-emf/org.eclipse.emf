/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.databinding.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * {@link Set} which is backed by an {@link List}
 * @param <E> the element type
 */
public class DelegatingSet<E> implements Set<E>
{
  private List<E> wrappedList;

  /**
   * Create a new delegating set
   * @param wrappedList the wrapped set
   */
  public DelegatingSet(List<E> wrappedList)
  {
    this.wrappedList = wrappedList;
  }

  public boolean add(E o)
  {
    return wrappedList.add(o);
  }

  public boolean addAll(Collection< ? extends E> c)
  {
    return wrappedList.addAll(c);
  }

  public void clear()
  {
    wrappedList.clear();
  }

  public boolean contains(Object o)
  {
    return wrappedList.contains(o);
  }

  public boolean containsAll(Collection< ? > c)
  {
    return wrappedList.containsAll(c);
  }

  public boolean isEmpty()
  {
    return wrappedList.isEmpty();
  }

  public Iterator<E> iterator()
  {
    return wrappedList.iterator();
  }

  public boolean remove(Object o)
  {
    return wrappedList.remove(o);
  }

  public boolean removeAll(Collection< ? > c)
  {
    return wrappedList.removeAll(c);
  }

  public boolean retainAll(Collection< ? > c)
  {
    return wrappedList.retainAll(c);
  }

  public int size()
  {
    return wrappedList.size();
  }

  public Object[] toArray()
  {
    return wrappedList.toArray();
  }

  public <T> T[] toArray(T[] a)
  {
    return wrappedList.toArray(a);
  }

  @Override
  public boolean equals(Object obj)
  {
    boolean rv = false;

    if (obj instanceof Set< ? >)
    {
      Set< ? > tmp = (Set< ? >)obj;
      if (wrappedList.size() == tmp.size())
      {
        if (wrappedList.containsAll(tmp))
        {
          rv = true;
        }
      }
    }

    return rv;
  }

  @Override
  public int hashCode()
  {
    int rv = 0;
    for (Object o : wrappedList)
    {
      if (o != null)
      {
        rv += o.hashCode();
      }
    }
    return rv;
  }
}
