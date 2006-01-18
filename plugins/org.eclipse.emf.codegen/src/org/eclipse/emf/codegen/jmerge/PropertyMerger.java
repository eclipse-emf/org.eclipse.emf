/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: PropertyMerger.java,v 1.7 2006/01/18 20:41:29 marcelop Exp $
 */
package org.eclipse.emf.codegen.jmerge;


import org.eclipse.core.runtime.IPlatformRunnable;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 * @deprecated in 2.2.0. Use {@link org.eclipse.emf.codegen.merge.properties.PropertyMerger} instead.
 */
public class PropertyMerger extends org.eclipse.emf.codegen.merge.properties.PropertyMerger
{
  public static class PlatformRunnable extends PropertyMerger implements IPlatformRunnable 
  {
  }
}
