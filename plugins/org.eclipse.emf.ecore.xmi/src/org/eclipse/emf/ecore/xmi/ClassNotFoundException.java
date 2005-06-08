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
 * $Id: ClassNotFoundException.java,v 1.2.2.1 2005/06/08 18:27:44 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EFactory;

public class ClassNotFoundException extends XMIException 
{
  protected String className;
  protected EFactory factory;

  public ClassNotFoundException(String name, EFactory factory, String location, int line, int column) 
  {
    super("Class '" + name + "' not found.", location, line, column);
    className = name;
    this.factory = factory;
  }
  
  public String getName() 
  {
    return className;
  }  
  
  public EFactory getFactory() 
  {
    return factory;
  }  
}
