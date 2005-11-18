/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: EObjectContainmentEList.java,v 1.3 2005/11/18 19:07:37 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;


public class EObjectContainmentEList extends EObjectEList
{
  public static class Unsettable extends EObjectContainmentEList
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
    
    public static class Resolving extends EObjectContainmentEList.Unsettable
    {
      public Resolving(Class dataClass, InternalEObject owner, int featureID)
      {
        super(dataClass, owner, featureID);
      }
      
      protected boolean hasProxies()
      {
        return true; 
      }
      
      protected Object resolve(int index, Object object)
      {
        return resolve(index, (EObject)object);
      }
    }
  }
  
  public static class Resolving extends EObjectContainmentEList
  {
    public Resolving(Class dataClass, InternalEObject owner, int featureID)
    {
      super(dataClass, owner, featureID);
    }
    
    protected boolean hasProxies()
    {
      return true; 
    }
    
    protected Object resolve(int index, Object object)
    {
      return resolve(index, (EObject)object);
    }
  }

  public EObjectContainmentEList(Class dataClass, InternalEObject owner, int featureID)
  {
    super(dataClass, owner, featureID);
  }

  protected boolean hasInverse()
  {
    return true;
  }

  protected boolean hasNavigableInverse()
  {
    return false;
  }

  protected boolean isContainment()
  {
    return true;
  }
}
