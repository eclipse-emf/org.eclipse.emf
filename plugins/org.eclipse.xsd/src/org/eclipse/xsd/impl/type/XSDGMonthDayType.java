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
 * $Id: XSDGMonthDayType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;
import org.eclipse.xsd.impl.type.DataValue.MonthDayDV;



public class XSDGMonthDayType extends XSDAnySimpleType
{
  public static class AccessibleMonthDayDV extends MonthDayDV
  {
    public String dateToString(int[] monthDay)
    {
      return super.dateToString(monthDay);
    }

    public short compareDates(int[] monthDay1, int[] monthDay2, boolean strict) 
    {
      return super.compareDates(monthDay1, monthDay2, strict);
    }
  }

  protected static final AccessibleMonthDayDV monthDayDV = new AccessibleMonthDayDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      monthDayDV.getActualValue(normalizedLiteral, null);
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
      return new IntSequence(this, (int [])monthDayDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : monthDayDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return monthDayDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
