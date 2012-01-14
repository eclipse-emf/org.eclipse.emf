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

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;


/**
 * Factory to create update strategies
 */
public class UpdateStrategyFactory
{
  /**
   * Create an update strategy which converts a string to a date
   * @param message the message when the conversion fails
   * @return the update strategy
   */
  public static EMFUpdateValueStrategy stringToDate(String message)
  {
    EMFUpdateValueStrategy strat = new EMFUpdateValueStrategy();
    StringToDateConverter c = new StringToDateConverter(message);
    strat.setConverter(c);
    return strat;
  }

  /**
   * Create an update strategy which converts a string to a date and ensures that 
   * the date value on the destinations is not set to null
   * @param convertMessage the message shown when the conversion fails
   * @param validationMessage the message when the value is set to null
   * @return the update strategy
   */
  public static EMFUpdateValueStrategy stringToDateNotNull(String convertMessage, final String validationMessage)
  {
    EMFUpdateValueStrategy strat = stringToDate(convertMessage);
    strat.setBeforeSetValidator(new IValidator()
      {

        public IStatus validate(Object value)
        {
          if (value == null)
          {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, validationMessage);
          }

          return Status.OK_STATUS;
        }
      });
    return strat;
  }

  /**
   * Create an update strategy which converts a date to a string
   * @return the update strategy
   */
  public static EMFUpdateValueStrategy dateToString()
  {
    EMFUpdateValueStrategy strat = new EMFUpdateValueStrategy();
    DateToStringConverter c = new DateToStringConverter();
    strat.setConverter(c);
    return strat;
  }
}
