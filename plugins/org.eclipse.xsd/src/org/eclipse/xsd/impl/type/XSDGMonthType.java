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
 * $Id: XSDGMonthType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;
import org.eclipse.xsd.impl.type.DataValue.MonthDV;



public class XSDGMonthType extends XSDAnySimpleType
{
  public static class AccessibleMonthDV extends MonthDV
  {
    public String dateToString(int[] month)
    {
      return super.dateToString(month);
    }

    public short compareDates(int[] month1, int[] month2, boolean strict) 
    {
      return super.compareDates(month1, month2, strict);
    }
  }

  protected static final AccessibleMonthDV monthDV = new AccessibleMonthDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      monthDV.getActualValue(normalizedLiteral, null);
      return true;
    }
    catch (InvalidDatatypeValueException exception)
    {
      return false;
    }
  }

  public Object getValue(String normalizedLiteral)
  {
    try
    {
      return new IntSequence(this, (int [])monthDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : monthDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return monthDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
