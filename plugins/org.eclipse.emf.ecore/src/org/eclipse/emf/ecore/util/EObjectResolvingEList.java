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
 * $Id: EObjectResolvingEList.java,v 1.3.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;


public class EObjectResolvingEList extends EObjectEList
{
  public static class Unsettable extends EObjectEList.Unsettable
  {
    public Unsettable(Class dataClass, InternalEObject owner, int featureID)
    {
      super(dataClass, owner, featureID);
    }

    protected boolean hasProxies()
    {
      return true;
    }
    
    protected Object resolve(int index, Object object)
    {
      // jdk 1.3 requires this explicity cast
      return ((EcoreEList)this).resolve(index, (EObject)object);
    }
  }

  public EObjectResolvingEList(Class dataClass, InternalEObject owner, int featureID)
  {
    super(dataClass, owner, featureID);
  }

  protected boolean hasProxies()
  {
    return true;
  }
  
  protected Object resolve(int index, Object object)
  {
    // jdk 1.3 requires this explicity cast
    return ((EcoreEList)this).resolve(index, (EObject)object);
  }
}
