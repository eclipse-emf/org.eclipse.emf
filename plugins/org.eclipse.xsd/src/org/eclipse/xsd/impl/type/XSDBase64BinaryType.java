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
 * $Id: XSDBase64BinaryType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.Base64;


public class XSDBase64BinaryType extends XSDAnySimpleType
{
  public boolean isValidLiteral(String literal)
  {
    try
    {
      return Base64.decode(literal) != null;
    }
    catch (RuntimeException exception)
    {
      return false;
    }
  }

  public Object getValue(String literal)
  {
    return new ByteSequence(this, Base64.decode(literal.getBytes()));
  }

  public String getCanonicalLiteral(Object value)
  {
    return new String(Base64.encode(((ByteSequence)value).getBytes()));
  }
}
