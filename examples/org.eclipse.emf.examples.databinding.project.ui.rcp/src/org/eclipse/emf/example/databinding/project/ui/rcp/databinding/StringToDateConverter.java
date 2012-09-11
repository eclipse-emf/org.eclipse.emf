/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.databinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;


/**
 * Convert a String to a date
 */
public class StringToDateConverter extends Converter implements IValidator
{
  private List<DateFormat> formats = new ArrayList<DateFormat>();
  private String message;

  /**
   * New converter
   * @param message message when conversion fails
   */
  public StringToDateConverter(String message)
  {
    super(String.class, Date.class);
    this.message = message;
    formats.add(DateFormat.getDateInstance(DateFormat.SHORT));
    formats.add(new SimpleDateFormat("yyyy-MM-dd"));
  }

  public Object convert(Object fromObject)
  {
    if (fromObject == null || fromObject.toString().trim().length() == 0)
    {
      return null;
    }

    for (DateFormat f : formats)
    {
      try
      {
        return f.parse(fromObject.toString());
      }
      catch (ParseException e)
      {
        // Ignore
      }
    }

    throw new RuntimeException(message);
  }

  public IStatus validate(Object value)
  {
    try
    {
      convert(value);
    }
    catch (Exception e)
    {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, message);
    }
    return Status.OK_STATUS;
  }
}