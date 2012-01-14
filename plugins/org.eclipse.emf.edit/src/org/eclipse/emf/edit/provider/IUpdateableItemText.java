/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * This is the interface implemented by an item provider if it supports an updateable item text.
 * This is be used to support edit-in-place tree items.
 */
public interface IUpdateableItemText
{
  /**
   * This returns the text that will be displayed when editing begins.
   */
  public String getUpdateableText(Object object);

  /**
   * This sets the given object's label text to the given text. 
   */
  public void setText(Object object, String text);
}
