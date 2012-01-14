/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;


public class XMIResourceFactoryImpl extends ResourceFactoryImpl
{
  /**
   * Constructor for XMIResourceFactoryImpl.
   */
  public XMIResourceFactoryImpl()
  {
    super();
  }

  @Override
  public Resource createResource(URI uri)
  {
    return new XMIResourceImpl(uri);
  }
}
