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
 * $Id: XSDGMonthDayType.java,v 1.2 2004/05/22 19:05:58 marcelop Exp $
 */
package org.eclipse.xsd.impl.type;

import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;

public class XSDGMonthDayType extends XSDAnySimpleType
{

  public Object getValue(String normalizedLiteral)
  {
    try
    {
      return new XMLCalendar(normalizedLiteral, XMLCalendar.GMONTHDAY);
    }
    catch (RuntimeException exception)
    {
      return null;
    }
  }

  public int compareValues(Object value1, Object value2)
  {
    return XMLCalendar.compare((XMLCalendar)value1, (XMLCalendar)value2); 
  }
}
