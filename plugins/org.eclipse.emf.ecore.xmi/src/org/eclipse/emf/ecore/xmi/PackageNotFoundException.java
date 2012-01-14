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
package org.eclipse.emf.ecore.xmi;

public class PackageNotFoundException extends XMIException 
{
  private static final long serialVersionUID = 1L;

  protected String uri;

  public PackageNotFoundException(String uri, String location, int line, int column) 
  {
    super("Package with uri '" + uri + "' not found.", location, line, column);
    this.uri = uri;
  }
  
  public String uri() 
  {
    return uri;
  }  
}
