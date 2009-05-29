/**
 * <copyright>
 *
 * Copyright (c) 2009 Tom Schindl and others.
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
 * $Id: DateToStringConverter.java,v 1.1 2009/05/29 15:06:30 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.databinding;

import java.text.DateFormat;
import java.util.Date;

import org.eclipse.core.databinding.conversion.Converter;


/**
 * Convert a date to a string
 */
public class DateToStringConverter extends Converter
{
  private DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

  /**
   * New converter
   */
  public DateToStringConverter()
  {
    super(Date.class, String.class);
  }

  public Object convert(Object fromObject)
  {
    if (fromObject == null)
    {
      return "";
    }
    return format.format(fromObject);
  }
}
