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
 * $Id: Disposable.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


/**
 * This implements {@link IDisposable} as a set IDisposables that can in turn be disposed.
 */
public class Disposable extends HashSet implements IDisposable
{
  /**
   * This creates an empty instance.
   */
  public Disposable()
  {
  }

  /**
   * This creates an instance with containing all the given disposables.
   */
  public Disposable(Collection disposables)
  {
    super(disposables);
  }

  /**
   * This is called to dispose the disposables.
   */
  public void dispose()
  {
    for (Iterator disposables = iterator(); disposables.hasNext(); )
    {
      IDisposable disposable = (IDisposable)disposables.next();
      disposable.dispose(); 
    }
    clear();
  }

  public boolean add(Object object)
  {
    if (object instanceof IDisposable)
    {
      return super.add(object);
    }
    else
    {
      return false;
    }
  }

  public boolean addAll(Collection collection)
  {
    boolean result = false;
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      Object object = objects.next();
      if (add(object))
      {
        result = true;
      }
    }
    return result;
  }
}
