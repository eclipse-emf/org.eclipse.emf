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
 * $Id: XSDDateTimeType.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;


import org.eclipse.xsd.impl.type.DataValue.DateTimeDV;
import org.eclipse.xsd.impl.type.DataValue.InvalidDatatypeValueException;



public class XSDDateTimeType extends XSDAnySimpleType
{
  public static class AccessibleDateTimeDV extends DateTimeDV
  {
    public String dateToString(int[] dateTime)
    {
      return super.dateToString(dateTime);
    }

    public short compareDates(int[] dateTime1, int[] dateTime2, boolean strict) 
    {
      return super.compareDates(dateTime1, dateTime2, strict);
    }
  }

  protected static final AccessibleDateTimeDV dateTimeDV = new AccessibleDateTimeDV();

  public boolean isValidLiteral(String normalizedLiteral)
  {
    try
    {
      dateTimeDV.getActualValue(normalizedLiteral, null);
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
      return new IntSequence(this, (int [])dateTimeDV.getActualValue(normalizedLiteral, null));
    }
    catch (InvalidDatatypeValueException exception)
    {
      return null;
    }
  }

  public String getCanonicalLiteral(Object value)
  {
    return value == null ? null : dateTimeDV.dateToString(((IntSequence)value).getInts());
  }

  public int compareValues(Object value1, Object value2)
  {
    return dateTimeDV.compareDates(((IntSequence)value1).getInts(), ((IntSequence)value2).getInts(), true);
  }
}
