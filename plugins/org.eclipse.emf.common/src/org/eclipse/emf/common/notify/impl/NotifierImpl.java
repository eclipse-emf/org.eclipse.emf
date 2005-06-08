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
 * $Id: NotifierImpl.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.common.notify.impl;


import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

/**
 * An extensible notifier implementation.
 */
public class NotifierImpl extends BasicNotifierImpl
{
  /**
   * The bit of {@link #eFlags} that is used to represent {@link #eDeliver}.
   */
  protected static final int EDELIVER = 0x0001;

  /**
   * The last bit used by this class; derived classes may use bit values higher than this.
   */
  protected static final int ELAST_NOTIFIER_FLAG = EDELIVER;

  /**
   * An extensible set of bit flags;
   * the first bit is used for {@link #EDELIVER} to implement {@link #eDeliver}.
   */
  protected int eFlags = EDELIVER;

  /**
   * The list of {@link org.eclipse.emf.common.notify.Adapter}s associated with the notifier.
   */
  protected BasicEList eAdapters;

  /**
   * Creates a blank new instance.
   */
  public NotifierImpl()
  {
  }

  /*
   * Javadoc copied from interface.
   */
  public EList eAdapters()
  {
    if (eAdapters == null)
    {
      eAdapters =  new EAdapterList(this);
    }
    return eAdapters;
  }

  protected BasicEList eBasicAdapters()
  {
    return eAdapters;
  }

  /*
   * Javadoc copied from interface.
   */
  public boolean eDeliver()
  {
    return (eFlags & EDELIVER) != 0;
  }

  /*
   * Javadoc copied from interface.
   */
  public void eSetDeliver(boolean deliver)
  {
    if (deliver)
    {
      this.eFlags |= EDELIVER;
    }
    else
    {
      this.eFlags &= ~EDELIVER;
    }
  }
}
