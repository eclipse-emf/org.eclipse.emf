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
 * $Id: XSDHexBinaryType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.HexBin;


public class XSDHexBinaryType extends XSDAnySimpleType
{
  public boolean isValidLiteral(String literal)
  {
    try
    {
      return HexBin.decode(literal) != null;
    }
    catch (RuntimeException exception)
    {
      return false;
    }
  }

  public Object getValue(String literal)
  {
    return new ByteSequence(this, HexBin.decode(literal.getBytes()));
  }

  public String getCanonicalLiteral(Object value)
  {
    return new String(HexBin.encode(((ByteSequence)value).getBytes()));
  }
}
