/**
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: IWrapperItemProvider.java,v 1.1 2004/03/31 19:49:32 davidms Exp $
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
   * org.eclipse.emf.edit.command.CommandParameter.NO_INDEX} if the index isn't known to the wrapper.
   */
  int getIndex();

  /**
   * Sets the index. Has no effect if the index isn't known to the wrapper.
   */
  void setIndex(int index);
}
