/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: UniqueEList.java,v 1.2 2004/08/06 20:16:37 emerks Exp $
 */
package org.eclipse.emf.common.util;


import java.util.Collection;


/**
 * A <code>BasicEList</code> that allows only {@link #isUnique unique} elements.
 */
public class UniqueEList extends BasicEList
{
  /**
   * Creates an empty instance with no initial capacity.
   */
  public UniqueEList()
  {
    super();
  }

  /**
   * Creates an empty instance with the given capacity.
   * @param initialCapacity the initial capacity of the list before it must grow.
   * @exception IllegalArgumentException if the <code>initialCapacity</code> is negative.
   */
  public UniqueEList(int initialCapacity)
  {
    super(initialCapacity);
  }

  /**
   * Creates an instance that is a copy of the collection, with duplicates removed.
   * @param collection the initial contents of the list.
   */
  public UniqueEList(Collection collection)
  {
    super(collection.size());
    addAll(collection);   
  }

  /**
   * Returns <code>true</code> because this list requires uniqueness.
   * @return <code>true</code>.
   */
  protected boolean isUnique()
  {
    return true;
  }

  /**
   * A <code>UniqueEList</code> that {@link #useEquals uses} <code>==</code> instead of <code>equals</code> to compare members.
   */
  public static class FastCompare extends UniqueEList
  {
    /**
     * Creates an empty instance with no initial capacity.
     */
    public FastCompare()
    {
      super();
    }

    /**
     * Creates an empty instance with the given capacity.
     * @param initialCapacity the initial capacity of the list before it must grow.
     * @exception IllegalArgumentException if the <code>initialCapacity</code> is negative.
     */
    public FastCompare(int initialCapacity)
    {
      super(initialCapacity);
    }

    /**
     * Creates an instance that is a copy of the collection, with duplicates removed.
     * @param collection the initial contents of the list.
     */
    public FastCompare(Collection collection)
    {
      super(collection.size());
      addAll(collection);
    }

    /**
     * Returns <code>false</code> because this list uses <code>==</code>.
     * @return <code>false</code>.
     */
    protected boolean useEquals()
    {
      return false;
    }
  }
}
