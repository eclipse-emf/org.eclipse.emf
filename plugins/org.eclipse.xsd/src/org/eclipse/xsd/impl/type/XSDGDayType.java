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

import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;

public class XSDGDayType extends XSDAnySimpleType
{
  @Override
  public Object getValue(String normalizedLiteral)
  {
    try
    {
      XMLCalendar calendar = new XMLCalendar(normalizedLiteral, XMLCalendar.GDAY);
      return calendar.isValid() && "gDay".equals(calendar.getXMLSchemaType().getLocalPart()) ? calendar : null;
    }
    catch (RuntimeException exception)
    {
      return null;
    }
  }

  @Override
  public int compareValues(Object value1, Object value2)
  {
    return XMLCalendar.compare((XMLCalendar)value1, (XMLCalendar)value2);
  }
}
