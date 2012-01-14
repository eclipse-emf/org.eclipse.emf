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

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.ui.forms.widgets.Form;


/**
 * Property to deal with {@link Form#setText(String)} and {@link Form#getText()}
 */
public class FormTextProperty extends WidgetValueProperty
{
  private FormTextProperty()
  {
    super();
  }

  @Override
  protected Object doGetValue(Object source)
  {
    return ((Form)source).getText();
  }

  @Override
  protected void doSetValue(Object source, Object value)
  {
    ((Form)source).setText(value != null ? value.toString() : "");
  }

  public Object getValueType()
  {
    return String.class;
  }

  /**
   * @return a property instance
   */
  public static IValueProperty create()
  {
    return new FormTextProperty();
  }
}
