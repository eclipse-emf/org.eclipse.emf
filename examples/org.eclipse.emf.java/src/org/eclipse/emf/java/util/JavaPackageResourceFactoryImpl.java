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
 * $Id: JavaPackageResourceFactoryImpl.java,v 1.2 2005/06/08 06:21:06 nickb Exp $
 */
package org.eclipse.emf.java.util;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;


/**
 */
public class JavaPackageResourceFactoryImpl extends ResourceFactoryImpl 
{
  public Resource createResource(URI uri)
  {
    return new JavaPackageResourceImpl(uri);
  }
}
