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
 * $Id: XSDGYearMonthType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;
import org.eclipse.xsd.impl.type.DataValue.YearMonthDV;



public class XSDGYearMonthType extends XSDAnySimpleType
{
  public static class AccessibleYearMonthDV extends YearMonthDV
  {
    public String dateToString(int[] yearMonth)
    {
      return super.dateToString(yearMonth);
    }

    public short compareDates(int[] yearMonth1, int[] yearMonth2, boolean strict) 
    {
      return super.compareDates(yearMonth1, yearMonth2, strict);
    }
  }

  protected static final AccessibleYearMonthDV yearMonthDV = new AccessibleYearMonthDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      yearMonthDV.getActualValue(normalizedLiteral, null);
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
      return new IntSequence(this, (int [])yearMonthDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : yearMonthDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return yearMonthDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
