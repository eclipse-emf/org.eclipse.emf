/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDAnyURIType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.URI;


public class XSDAnyURIType extends XSDAnySimpleType
{
  static protected URI DUMMY_URI;

  {
    try
    {
      DUMMY_URI = new URI("http://www.dummy.com");
    }
    catch (URI.MalformedURIException exception)
    {
    }
  }

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      URI absoluteURI = new URI(normalizedLiteral);
      return true;
    }
    catch (URI.MalformedURIException exception)
    {
      try
      {
        URI relativeURI = new URI(DUMMY_URI, normalizedLiteral);
        return true;
      }
      catch (URI.MalformedURIException anotherException)
      {
      }
    }
    return false;
  }
}
