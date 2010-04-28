/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: IDisposable.java,v 1.2 2010/04/28 20:38:36 khussey Exp $
 */
package org.eclipse.emf.edit.provider;



/**
 * This is implemented by objects that need to be disposed after they are no longer needed.
 */
public interface IDisposable
{
  /**
   * This is called to dispose the object.
   */
  public void dispose();
}
