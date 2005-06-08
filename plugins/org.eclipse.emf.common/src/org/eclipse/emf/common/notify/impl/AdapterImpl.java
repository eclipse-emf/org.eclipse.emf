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
 * $Id: AdapterImpl.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.common.notify.impl;


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;



/**
 * An extensible adapter implementation.
 */
public class AdapterImpl implements Adapter
{
  /**
   * The last notifier set to this adapter.
   */
  protected Notifier target = null;

  /**
   * Creates an instance.
   */
  public AdapterImpl()
  {
  }

  /**
   * Returns <code>false</code>
   * @param type the type.
   * @return <code>false</code>
   */
  public boolean isAdapterForType(Object type)
  {
    return false;
  }

  /**
   * Does nothing; clients may override so that it does something.
   */
  public void notifyChanged(Notification msg)
  {
  }

  /*
   * Javadoc copied from interface.
   */
  public Notifier getTarget()
  {
    return target;
  }

  /*
   * Javadoc copied from interface.
   */
  public void setTarget(Notifier newTarget)
  {
    target = newTarget;
  }
}
