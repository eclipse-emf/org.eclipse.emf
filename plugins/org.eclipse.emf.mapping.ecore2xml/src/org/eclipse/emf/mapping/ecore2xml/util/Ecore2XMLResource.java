/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2xml.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

/**
 * The interface for Ecore2XML resources.
 */
public interface Ecore2XMLResource extends XMIResource
{
  
  /**
   * The interface for Ecore2XML resource factories.
   */
  public interface Factory extends Resource.Factory
  {
    
    public static final Factory INSTANCE = new Ecore2XMLResourceFactoryImpl();
    
  }
  
  public static final String FILE_EXTENSION = "ecore2xml"; //$NON-NLS-1$
  
  public static final String DEFAULT_ENCODING = "UTF-8"; //$NON-NLS-1$
  
}
