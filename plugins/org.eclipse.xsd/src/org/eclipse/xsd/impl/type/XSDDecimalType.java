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

import java.math.BigDecimal;

public class XSDDecimalType extends XSDAnySimpleType
{
  @Override
  public Object getValue(String literal)
  {
    try
    {
      BigDecimal value = new BigDecimal(literal);

      if (literal.indexOf('e') == -1 && literal.indexOf('E') == -1)
      {
        return value;
      }
    }
    catch (RuntimeException e)
    {
      // Ignore
    }
    return null;
  }
  
  @Override
  public int compareValues(Object value1, Object value2)
  {
    return ((BigDecimal)value1).compareTo((BigDecimal)value2);
  }
}
