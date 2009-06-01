/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DateToStringConverter.java,v 1.2 2009/06/01 17:19:26 tschindl Exp $
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
