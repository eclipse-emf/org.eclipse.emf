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
 * $Id: EObjectWithInverseEList.java,v 1.2 2005/06/08 06:20:10 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;


public class EObjectWithInverseEList extends EObjectEList 
{
  public static class Unsettable extends EObjectWithInverseEList
  {
    public static class ManyInverse extends Unsettable
    {
      public ManyInverse(Class dataClass, InternalEObject owner, int featureID, int inverseFeatureID)
      {
        super(dataClass, owner, featureID, inverseFeatureID);
      }

      protected boolean hasManyInverse()
      {
        return true;
      }
    }

    protected boolean isSet;

    public Unsettable(Class dataClass, InternalEObject owner, int featureID, int inverseFeatureID)
    {
      super(dataClass, owner, featureID, inverseFeatureID);
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

  public static class ManyInverse extends EObjectWithInverseEList
  {
    public ManyInverse(Class dataClass, InternalEObject owner, int featureID, int inverseFeatureID)
    {
      super(dataClass, owner, featureID, inverseFeatureID);
    }

    protected boolean hasManyInverse()
    {
      return true;
    }
  }

  protected final int inverseFeatureID;

  public EObjectWithInverseEList
    (Class dataClass, InternalEObject owner, int featureID, int inverseFeatureID)
  {
    super(dataClass, owner, featureID);
    this.inverseFeatureID = inverseFeatureID;
  }

  protected boolean hasInverse()
  {
    return true;
  }

  protected boolean hasNavigableInverse()
  {
    return true;
  }

  public int getInverseFeatureID()
  {
    return inverseFeatureID;
  }
  
  public Class getInverseFeatureClass()
  {
    return dataClass;
  }
}
