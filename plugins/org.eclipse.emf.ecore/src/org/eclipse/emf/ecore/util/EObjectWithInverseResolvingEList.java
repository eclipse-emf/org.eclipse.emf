/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EObjectWithInverseResolvingEList.java,v 1.3 2004/08/27 14:06:30 marcelop Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;


public class EObjectWithInverseResolvingEList extends EObjectWithInverseEList
{
  public static class Unsettable extends EObjectWithInverseEList.Unsettable
  {
    public static class ManyInverse extends EObjectWithInverseResolvingEList.Unsettable
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

    public Unsettable(Class dataClass, InternalEObject owner, int featureID, int inverseFeatureID)
    {
      super(dataClass, owner, featureID, inverseFeatureID);
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

  public static class ManyInverse extends EObjectWithInverseResolvingEList
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

  public EObjectWithInverseResolvingEList
    (Class dataClass, InternalEObject owner, int featureID, int inverseFeatureID)
  {
    super(dataClass, owner, featureID, inverseFeatureID);
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
