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
 * $Id: XSDGYearType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;
import org.eclipse.xsd.impl.type.DataValue.YearDV;



public class XSDGYearType extends XSDAnySimpleType
{
  public static class AccessibleYearDV extends YearDV
  {
    public String dateToString(int[] year)
    {
      return super.dateToString(year);
    }

    public short compareDates(int[] year1, int[] year2, boolean strict) 
    {
      return super.compareDates(year1, year2, strict);
    }
  }

  protected static final AccessibleYearDV yearDV = new AccessibleYearDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      yearDV.getActualValue(normalizedLiteral, null);
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
      return new IntSequence(this, (int [])yearDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : yearDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return yearDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
