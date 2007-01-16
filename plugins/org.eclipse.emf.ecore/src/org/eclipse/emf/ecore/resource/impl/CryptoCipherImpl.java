/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: CryptoCipherImpl.java,v 1.3 2007/01/16 22:05:22 marcelop Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


/**
 * @deprecated Deprecated in 2.3.0.  Use {@link DESCipherImpl} or
 * {@link AESCipherImpl} instead.  This class will be removed in future versions of EMF.
 */
@Deprecated
public class CryptoCipherImpl extends DESCipherImpl
{
  public CryptoCipherImpl()
  {
    super(null);
  }
  
  public CryptoCipherImpl(String key)
  {
    super(key);
  }
}
