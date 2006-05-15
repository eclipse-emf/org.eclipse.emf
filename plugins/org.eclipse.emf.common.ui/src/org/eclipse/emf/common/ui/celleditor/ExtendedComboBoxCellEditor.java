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
 * $Id: ExtendedComboBoxCellEditor.java,v 1.3 2006/05/15 19:35:35 davidms Exp $
 */
package org.eclipse.emf.common.ui.celleditor;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;


/**
 * This uses a list of objects and a label provider to build a combo box based on model objects rather than on strings.
 * If sort is true, the list will be modified to match the order of the sorted labels.
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
      // Create a new array.
      //
      result = new String[list.size()];
      
      if (sorted)
      {
        List unsortedList = new ArrayList(list);
        list.clear();

        // Create an array of label/original position pairs and sort it by label.
        //
        class StringPositionPair implements Comparable
        {
          Collator collator = Collator.getInstance();
          public String key;
          public int position;
          
          StringPositionPair(String key, int position)
          {
            this.key = key;
            this.position = position;
          }
  
          public int compareTo(Object object)
          {
            if (object == this)
            {
              return 0;
            }
            else
            {
              StringPositionPair that = (StringPositionPair)object;
              return collator.compare(key, that.key);
            }
          }
        }

        StringPositionPair[] pairs = new StringPositionPair[unsortedList.size()];
  
        for (int i = 0, size = unsortedList.size(); i < size; ++i)
        {
          Object object = unsortedList.get(i);
          pairs[i] = new StringPositionPair(labelProvider.getText(object), i);
        }
  
        Arrays.sort(pairs); 

        // Fill in the result array with labels and repopulate the origina list in order.
        //
        for (int i = 0, size = unsortedList.size(); i < size; ++i)
        {
          result[i] = pairs[i].key;
          list.add(unsortedList.get(pairs[i].position));
        }
      }
      else
      {
        // Fill in the array with labels.
        //
        for (int i = 0, size = list.size(); i < size; ++i)
        {
          Object object = list.get(i);
          result[i] = labelProvider.getText(object);
        }
      }
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
    super(composite, createItems(sorted ? list = new ArrayList(list) : list, labelProvider, sorted), style); 

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
