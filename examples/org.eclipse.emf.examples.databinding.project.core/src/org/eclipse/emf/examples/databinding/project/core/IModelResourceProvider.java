/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: IModelResourceProvider.java,v 1.1 2009/06/01 17:03:03 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core;

public interface IModelResourceProvider
{
  public boolean canHandleUri(String uri);
  public IModelResource loadResource(String uri);
}
