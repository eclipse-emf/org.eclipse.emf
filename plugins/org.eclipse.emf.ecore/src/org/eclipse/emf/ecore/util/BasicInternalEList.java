/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: BasicInternalEList.java,v 1.1 2005/12/10 13:34:27 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;


public class BasicInternalEList extends BasicEList implements InternalEList
{
  protected final Class dataClass;
  
  public BasicInternalEList(Class dataClass)
  {
    super();
    this.dataClass = dataClass;
  }

  public BasicInternalEList(Class dataClass, int initialCapacity)
  {
    super(initialCapacity);
    this.dataClass = dataClass;
  }

  public BasicInternalEList(Class dataClass, Collection collection)
  {
    super(collection);
    this.dataClass = dataClass;
  }

  public BasicInternalEList(Class dataClass, int size, Object[] data)
  {
    super(size, data);
    this.dataClass = dataClass;
  }

  protected Object [] newData(int capacity)
  {
    return (Object [])Array.newInstance(dataClass, capacity);
  }

  public NotificationChain basicRemove(Object object, NotificationChain notifications)
  {
    return null;
  }

  public NotificationChain basicAdd(Object object, NotificationChain notifications)
  {
    return null;
  }

  public Iterator basicIterator()
  {
    return super.basicIterator();
  }

  public List basicList()
  {
    return super.basicList();
  }

  public ListIterator basicListIterator()
  {
    return super.basicListIterator();
  }

  public ListIterator basicListIterator(int index)
  {
    return super.basicListIterator(index);
  }
}
