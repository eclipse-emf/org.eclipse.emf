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
 * $Id: XSDDateType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.DateDV;
import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;


public class XSDDateType extends XSDAnySimpleType
{
  public static class AccessibleDateDV extends DateDV
  {
    public String dateToString(int[] date)
    {
      return super.dateToString(date);
    }

    public short compareDates(int[] date1, int[] date2, boolean strict) 
    {
      return super.compareDates(date1, date2, strict);
    }
  }

  protected static final AccessibleDateDV dateDV = new AccessibleDateDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      dateDV.getActualValue(normalizedLiteral, null);
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
      return new IntSequence(this, (int [])dateDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : dateDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return dateDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
