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
 * $Id: EObjectEList.java,v 1.2.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;


public class EObjectEList extends EcoreEList
{
  public static class Unsettable extends EObjectEList
  {
    protected boolean isSet;

    public Unsettable(Class dataClass, InternalEObject owner, int featureID)
    {
      super(dataClass, owner, featureID);
    }

    protected void didChange()
    {
      isSet = true;
    }

    public boolean isSet()
    {
      return isSet;
    }

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

  public EObjectEList(Class dataClass, InternalEObject owner, int featureID)
  {
    super(dataClass, owner);
    this.featureID = featureID;
  }

  public int getFeatureID()
  {
    return featureID;
  }

  protected boolean useEquals()
  {
    return false;
  }

  protected boolean isUnique()
  {
    return true;
  }

  protected boolean hasInverse()
  {
    return false;
  }

  protected boolean isEObject()
  {
    return true;
  }

  protected boolean canContainNull()
  {
    return false;
  }

  protected Object resolve(int index, Object object)
  {
    return object;
  }
}
