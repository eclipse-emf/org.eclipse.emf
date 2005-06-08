/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: BasicResourceHandler.java,v 1.2 2005/06/08 06:16:07 nickb Exp $
 */

package org.eclipse.emf.ecore.xmi.impl;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.ResourceHandler;


/**
 * This is the basic implementation of the ResourceHandler interface. 
 * Implementations need to extend this class to overwrite the ResourceHandler behaviour.
 */
public class BasicResourceHandler implements ResourceHandler
{

  public void preLoad(XMLResource resource, InputStream inputStream, Map options)
  {
  }

  public void postLoad(XMLResource resource, InputStream inputStream, Map options)
  {
  }

  public void preSave(XMLResource resource, OutputStream outputStream, Map options)
  {
  }

  public void postSave(XMLResource resource, OutputStream outputStream, Map options)
  {
  }

}
