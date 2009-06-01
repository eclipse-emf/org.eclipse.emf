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
 * $Id: XMIModelResourceProvider.java,v 1.2 2009/06/01 17:19:28 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.internal.xmi;

import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.IModelResourceProvider;

public class XMIModelResourceProvider implements IModelResourceProvider
{

  public boolean canHandleUri(String uri)
  {
    return uri.startsWith("file:");
  }

  public IModelResource loadResource(String uri)
  {
    return new XMIModelResource(uri);
  }

}
