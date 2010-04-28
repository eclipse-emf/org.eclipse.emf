/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: Disposable.java,v 1.1 2010/04/28 14:48:40 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;
import java.util.HashSet;


/**
 * This implements {@link IDisposable} as a set IDisposables that can in turn be disposed.
 */
public class Disposable extends HashSet<Object> implements IDisposable
{
  private static final long serialVersionUID = 1L;

  /**
   * This creates an empty instance.
   */
  public Disposable()
  {
    super();
  }

  /**
   * This creates an instance with containing all the given disposables.
   */
  public Disposable(Collection<?> disposables)
  {
    super(disposables);
  }

  /**
   * This is called to dispose the disposables.
   */
  public void dispose()
  {
    for (Object object : this)
    {
      IDisposable disposable = (IDisposable)object;
      disposable.dispose(); 
    }
    clear();
  }

  @Override
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

  @Override
  public boolean addAll(Collection<?> collection)
  {
    boolean result = false;
    for (Object object : collection)
    {
      if (add(object))
      {
        result = true;
      }
    }
    return result;
  }
}
