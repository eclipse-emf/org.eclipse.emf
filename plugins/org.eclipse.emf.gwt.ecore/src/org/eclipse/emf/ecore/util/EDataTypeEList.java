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
 * $Id: EDataTypeEList.java,v 1.1 2010/04/28 14:45:54 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;


public class EDataTypeEList<E> extends EcoreEList<E>
{
  private static final long serialVersionUID = 1L;

  public static class Unsettable<E> extends EDataTypeEList<E>
  {
    private static final long serialVersionUID = 1L;

    protected boolean isSet;

    public Unsettable(Class<?> dataClass, InternalEObject owner, int featureID)
    {
      super(dataClass, owner, featureID);
    }

    @Override
    protected void didChange()
    {
      isSet = true;
    }

    @Override
    public boolean isSet()
    {
      return isSet;
    }

    @Override
    public void unset()
    {
      super.unset();
      if (isNotificationRequired())
      {
        boolean oldIsSet = isSet;
        isSet = false;
        owner.eNotify(createNotification(Notification.UNSET, oldIsSet, false));
      }
      else
      {
        isSet = false;
      }
    }
  }

  protected final int featureID;

  public EDataTypeEList(Class<?> dataClass, InternalEObject owner, int featureID)
  {
    super(dataClass, owner);
    this.featureID = featureID;
  }

  @Override
  public int getFeatureID()
  {
    return featureID;
  }

  @Override
  protected boolean isEObject()
  {
    return false;
  }
    
  @Override
  protected E resolve(int index, E object)
  {
    return object;
  }
}
