/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IModelLoadingService.java,v 1.2 2009/06/01 17:19:28 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core;

public interface IModelLoadingService
{
  public IModelResource findAndLoadResource(String uri);
}
