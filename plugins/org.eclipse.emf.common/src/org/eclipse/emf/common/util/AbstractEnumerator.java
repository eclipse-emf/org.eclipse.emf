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
 * $Id: AbstractEnumerator.java,v 1.2 2005/06/08 06:19:08 nickb Exp $
 */
package org.eclipse.emf.common.util;


/**
 * An extensible enumerator implementation.
 */
public abstract class AbstractEnumerator implements Enumerator
{
  /**
   * The name of the enumerator.
   */
  private final String name;

  /**
   * The <code>int</code> value of the enumerator.
   */
  private final int value;

  /**
   * Creates an initialized instance.
   * @param value the <code>int</code> value of the enumerator.
   * @param name the name of the enumerator.
   */
  protected AbstractEnumerator(int value, String name)
  {
    this.name = name;
    this.value = value;
  }

  /**
   * Returns the name of the enumerator.
   * @return the name.
   */
  public final String getName()
  {
    return name;
  }

  /**
   * Returns the <code>int</code> value of the enumerator.
   * @return the value.
   */
  public final int getValue()
  {
    return value;
  }

  /**
   * Returns the name of the enumerator.
   * @return the name.
   */
  public final String toString()
  {
    return name;
  }
}
