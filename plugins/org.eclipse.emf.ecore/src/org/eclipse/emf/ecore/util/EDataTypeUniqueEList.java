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
 * $Id: EDataTypeUniqueEList.java,v 1.1.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.util;


import org.eclipse.emf.ecore.InternalEObject;


public class EDataTypeUniqueEList extends EDataTypeEList
{
  public static class Unsettable extends EDataTypeEList.Unsettable
  {
    public Unsettable(Class dataClass, InternalEObject owner, int featureID)
    {
      super(dataClass, owner, featureID);
    }

    protected boolean isUnique()
    {
      return true;
    }
  }

  public EDataTypeUniqueEList(Class dataClass, InternalEObject owner, int featureID)
  {
    super(dataClass, owner, featureID);
  }

  protected boolean isUnique()
  {
    return true;
  }
}
