/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: FormBuilder.java,v 1.1 2009/06/06 16:04:12 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.databinding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.databinding.EMFUpdateValueStrategy;


/**
 * Helper class which builds a two column form with labels and text-fields
 * @param <P> the value property type
 */
public class FormBuilder<P extends IValueProperty>
{
  private class Entry
  {
    private String label;
    private P property;
    private String nullMessage;

    private Entry(String label, P property, String nullMessage)
    {
      this.label = label;
      this.property = property;
      this.nullMessage = nullMessage;
    }
  }

  private List<Entry> entries = new ArrayList<Entry>();

  /**
   * Add a text entry
   * @param label the label to display
   * @param property the property to bind
   * @param nullMessage the message shown when the property gets set to null
   */
  public void addTextEntry(String label, P property, String nullMessage)
  {
    entries.add(new Entry(label, property, nullMessage));
  }

  /**
   * Build a two column form with the elements added
   * 
   * @param dbc the databinding context
   * @param parent the parent the form is created on
   * @param object the object to bind
   * @return the form container
   */
  public Composite build(DataBindingContext dbc, Composite parent, Object object)
  {
    Composite container = new Composite(parent, SWT.NONE);
    container.setLayout(new GridLayout(2, false));

    IWidgetValueProperty textProp = WidgetProperties.text(SWT.Modify);
    for (Entry e : entries)
    {

      Label l = new Label(container, SWT.NONE);
      l.setText(e.label);

      Text t = new Text(container, SWT.BORDER);
      t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

      IObservableValue uiObs = textProp.observeDelayed(400, t);
      IObservableValue mObs;

      if (object instanceof IObservableValue)
      {
        mObs = e.property.observeDetail((IObservableValue)object);
      }
      else
      {
        mObs = e.property.observe(object);
      }

      dbc.bindValue(uiObs, mObs, new EMFUpdateValueStrategy().setBeforeSetValidator(new EmptyStringValidator(e.nullMessage)), null);
    }

    return container;
  }
}