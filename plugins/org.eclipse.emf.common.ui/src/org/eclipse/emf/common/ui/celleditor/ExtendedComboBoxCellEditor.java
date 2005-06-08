/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ExtendedComboBoxCellEditor.java,v 1.1.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.common.ui.celleditor;


import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;


/**
 * This uses a list of objects and a label provider to build a combo box based on model objects rather than on strings.
 */
public class ExtendedComboBoxCellEditor extends ComboBoxCellEditor
{
  public static String [] createItems(List list, ILabelProvider labelProvider, boolean sorted)
  {
    String [] result;

    // If there are objects to populate...
    //
    if (list != null && list.size() > 0)
    {
      // Create an new array..
      //
      result = new String [list.size()];

      // Fill in the array with label/value pair items.
      //
      int i = 0;
      for (Iterator objects = list.iterator(); objects.hasNext(); ++i)
      {
        Object object = objects.next();
        result[i] = labelProvider.getText(object);
      }

      // We could collate the array, but we'd have to keep the list in synch.
      //
      // Arrays.sort(result, java.text.Collator.getInstance());
    }
    else
    {
      result = new String [] { "" };
    }

    return result;
  }

  /**
   * This keeps track of the list of model objects.
   */
  protected List list;

  public ExtendedComboBoxCellEditor(Composite composite, List list, ILabelProvider labelProvider)
  {
    this(composite, list, labelProvider, false, SWT.READ_ONLY);
  }

  public ExtendedComboBoxCellEditor(Composite composite, List list, ILabelProvider labelProvider, boolean sorted)
  {
    this(composite, list, labelProvider, sorted, SWT.READ_ONLY);
  }

  public ExtendedComboBoxCellEditor(Composite composite, List list, ILabelProvider labelProvider, int style)
  {
    this(composite, list, labelProvider, false, style);
  }

  public ExtendedComboBoxCellEditor(Composite composite, List list, ILabelProvider labelProvider, boolean sorted, int style)
  {
    super(composite, createItems(list, labelProvider, sorted), style); 

    this.list = list;
  }
     
  public Object doGetValue()
  {
    // Get the index into the list via this call to super.
    //
    int index = ((Integer)super.doGetValue()).intValue();
    return index < list.size() && index >= 0 ? list.get(((Integer)super.doGetValue()).intValue()) : null;
  }

  public void doSetValue(Object value)
  {
    // Set the index of the object value in the list via this call to super.
    //
    int index = list.indexOf(value);
    if (index != -1)
    {
      super.doSetValue(new Integer(list.indexOf(value)));
    }
  }
}
