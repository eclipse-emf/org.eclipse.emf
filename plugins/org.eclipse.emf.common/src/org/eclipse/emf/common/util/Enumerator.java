/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: Enumerator.java,v 1.2 2005/06/08 06:19:08 nickb Exp $
 */
package org.eclipse.emf.common.util;


/**
 * An interface implemented by the enumerators of a type-safe enum.
 */
public interface Enumerator
{
  /**
   * Returns the name of the enumerator.
   * @return the name.
   */
  String getName();

  /**
   * Returns the <code>int</code>value of the enumerator.
   * @return the value.
   */
  int getValue();
}
