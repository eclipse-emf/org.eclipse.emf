/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jmerge;


/**
 * This implements the method {@link #run(Object)}, 
 * which is called just like main during headless workbench invocation.
 * @deprecated in 2.2.0. Use {@link org.eclipse.emf.codegen.merge.properties.PropertyMerger} instead.
 */
@Deprecated
public class PropertyMerger extends org.eclipse.emf.codegen.merge.properties.PropertyMerger
{
  public static class PlatformRunnable extends PropertyMerger
  {
    // Empty
  }
}
