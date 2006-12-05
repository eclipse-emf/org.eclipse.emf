/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: BasicInternalEList.java,v 1.2 2006/12/05 20:22:26 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;


public class BasicInternalEList<E> extends BasicEList<E> implements InternalEList<E>
{
  private static final long serialVersionUID = 1L;

  protected final Class<? extends E> dataClass;
  
  public BasicInternalEList(Class<? extends E> dataClass)
  {
    super();
    this.dataClass = dataClass;
  }

  public BasicInternalEList(Class<? extends E> dataClass, int initialCapacity)
  {
    super(initialCapacity);
    this.dataClass = dataClass;
  }

  public BasicInternalEList(Class<? extends E> dataClass, Collection<? extends E> collection)
  {
    super(collection);
    this.dataClass = dataClass;
  }

  public BasicInternalEList(Class<? extends E> dataClass, int size, Object[] data)
  {
    super(size, data);
    this.dataClass = dataClass;
  }

  @Override
  protected Object [] newData(int capacity)
  {
    return (Object [])Array.newInstance(dataClass, capacity);
  }

  public NotificationChain basicRemove(Object object, NotificationChain notifications)
  {
    return null;
  }

  public NotificationChain basicAdd(E object, NotificationChain notifications)
  {
    return null;
  }

  @Override
  public Iterator<E> basicIterator()
  {
    return super.basicIterator();
  }

  @Override
  public List<E> basicList()
  {
    return super.basicList();
  }

  @Override
  public ListIterator<E> basicListIterator()
  {
    return super.basicListIterator();
  }

  @Override
  public ListIterator<E> basicListIterator(int index)
  {
    return super.basicListIterator(index);
  }
}
