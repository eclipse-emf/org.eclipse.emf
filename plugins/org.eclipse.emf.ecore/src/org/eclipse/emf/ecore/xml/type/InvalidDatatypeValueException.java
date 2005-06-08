/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: InvalidDatatypeValueException.java,v 1.2 2005/06/08 06:20:10 nickb Exp $
 */
package org.eclipse.emf.ecore.xml.type;

/**
 * Datatype exception for invalid values.
 */
public class InvalidDatatypeValueException extends RuntimeException
{
  public InvalidDatatypeValueException(String reason)
  {
    super(reason);
  }
}