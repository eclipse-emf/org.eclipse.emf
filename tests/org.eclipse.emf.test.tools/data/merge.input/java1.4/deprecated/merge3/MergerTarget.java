/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 * 
 * Target Header
 */
package org.eclipse.emf.test.tools.merger;

public class EchoSoapBindingImpl implements echo.Echo
{
  public java.lang.String hello(java.lang.String name) throws java.rmi.RemoteException
  {
    return name;
  }
}
