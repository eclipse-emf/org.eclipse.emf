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

import org.eclipse.emf.ecore.xml.type.internal.DataValue.HexBin;

public class XSDHexBinaryType extends XSDAnySimpleType
{
  @Override
  public Object getValue(String literal)
  {
    byte[] bytes = HexBin.decode(literal);
    if (bytes != null)
    {
      return new HexSequence(this, bytes);
    }
    return null;
  }

}
