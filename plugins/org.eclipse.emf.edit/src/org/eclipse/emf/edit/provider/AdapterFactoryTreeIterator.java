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
 * $Id: AdapterFactoryTreeIterator.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.AbstractTreeIterator;


/**
 * This implements a tree iterator that iterates over an object, it's children, their children, and so on,
 * use the {@link ITreeItemContentProvider}s produced by an adapter factory.
 */
public class AdapterFactoryTreeIterator extends AbstractTreeIterator
{
  /**
   * This keeps track of the factory used to generate the ITreeItemContentProvider adapters.
   */
  protected AdapterFactory adapterFactory;

  /**
   * This constructs tree iterator that iterates over an object, it's children, their children, and so on.
   */
  public AdapterFactoryTreeIterator(AdapterFactory adapterFactory, Object object)
  {
    super(object);
    this.adapterFactory = adapterFactory;
  }

  /**
   * This constructs tree iterator that iterates over an object (but only if includeRoot is true), 
   * it's children, their children, and so on.
   */
  public AdapterFactoryTreeIterator(AdapterFactory adapterFactory, Object object, boolean includeRoot)
  {
    super(object, includeRoot);
    this.adapterFactory = adapterFactory;
  }

  protected Iterator getChildren(Object o)
  {
    ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider)adapterFactory.adapt(o, ITreeItemContentProvider.class);
    return
      treeItemContentProvider != null ?
        treeItemContentProvider.getChildren(o).iterator() :
        Collections.EMPTY_LIST.iterator();
  }
}
