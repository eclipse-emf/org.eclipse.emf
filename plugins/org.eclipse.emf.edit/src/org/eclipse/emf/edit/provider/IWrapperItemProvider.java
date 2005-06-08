/**
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: IWrapperItemProvider.java,v 1.2.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.provider;


/**
 * The base interface for a value wrapper that can implement item provider interfaces.
 */
public interface IWrapperItemProvider extends IDisposable
{
  /**
   * Returns the wrapped value.
   */
  Object getValue();

  /**
   * Returns the index within a feature at which the value is located, or {@link
   * org.eclipse.emf.edit.command.CommandParameter#NO_INDEX} if the index isn't known to the wrapper.
   */
  int getIndex();

  /**
   * Sets the index. Has no effect if the index isn't known to the wrapper.
   */
  void setIndex(int index);
}
