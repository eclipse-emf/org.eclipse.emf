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
 * $Id: XSDDecimalType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import java.math.BigDecimal;


public class XSDDecimalType extends XSDAnySimpleType
{
  public boolean isValidLiteral(String literal)
  {
    try
    {
      BigDecimal value = new BigDecimal(literal);
      return literal.indexOf('e') == -1 && literal.indexOf('E') == -1;
    }
    catch (RuntimeException exception)
    {
      return false;
    }
  }

  public Object getValue(String literal)
  {
    BigDecimal value = new BigDecimal(literal);
    return value;
  }

  public String getCanonicalLiteral(Object value)
  {
    return value.toString();
  }

  public int compareValues(Object value1, Object value2)
  {
    return ((BigDecimal)value1).compareTo(value2);
  }
}
