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
 * $Id: XSDGDayType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.DayDV;
import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;



public class XSDGDayType extends XSDAnySimpleType
{
  public static class AccessibleDayDV extends DayDV
  {
    public String dateToString(int[] day)
    {
      return super.dateToString(day);
    }

    public short compareDates(int[] day1, int[] day2, boolean strict) 
    {
      return super.compareDates(day1, day2, strict);
    }
  }

  protected static final AccessibleDayDV dayDV = new AccessibleDayDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      dayDV.getActualValue(normalizedLiteral, null);
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
      return new IntSequence(this, (int [])dayDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : dayDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return dayDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
