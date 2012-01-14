/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider;



/**
 * This is the interface implemented to provide a label text and even a label icon for an item;
 * it receives delegated calls from ILabelProvider.
 */
public interface IItemLabelProvider
{
  /**
   * This does the same thing as ILabelProvider.getlText, 
   * it fetches the label text specific to this object instance.
   */
  public String getText(Object object);

  /**
   * This does the same thing as ILabelProvider.getImage, 
   * it fetches the label image specific to this object instance.
   */
  public Object getImage(Object object);
}
