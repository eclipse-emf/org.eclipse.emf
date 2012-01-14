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
package org.eclipse.xsd.impl.type;

import org.eclipse.emf.ecore.xml.type.internal.DataValue.URI;

public class XSDAnyURIType extends XSDAnySimpleType
{
  @Override
  public Object getValue(String normalizedLiteral)
  {
    try
    {
      if (normalizedLiteral.length() >0)
      {
        //encode special characters using XLink 5.4 algorithm
        normalizedLiteral = URI.encode(normalizedLiteral);
        // Support for relative URLs
        // According to Java 1.1: URLs may also be specified with a
        // String and the URL object that it is related to.
        new URI(URI.BASE_URI, normalizedLiteral);
      }
      return normalizedLiteral;
    }
    catch (URI.MalformedURIException exception)
    {
      // Ignore
    }
    return null;
  }
}
