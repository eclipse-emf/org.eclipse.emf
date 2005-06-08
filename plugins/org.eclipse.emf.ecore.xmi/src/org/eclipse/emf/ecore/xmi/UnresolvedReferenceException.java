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
 * $Id: UnresolvedReferenceException.java,v 1.3 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi;

public class UnresolvedReferenceException extends XMIException 
{
  protected String reference;

  public UnresolvedReferenceException(String reference, String location, int line, int column) 
  {
    super("Unresolved reference '" + reference + "'.", location, line, column);
    this.reference = reference;
  }
  
  public String getReference() 
  {
    return reference;
  }
}
