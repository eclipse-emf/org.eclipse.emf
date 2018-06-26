/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.ui.celleditor;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


/**
 * This uses a list of objects and a label provider to build a combo box based on model objects rather than on strings.
 * If sort is true, the list will be modified to match the order of the sorted labels.
 */
public class ExtendedComboBoxCellEditor extends ComboBoxCellEditor
{
  /**
   * A handler for providing validation feedback and for converting from literal values to instances values.
   *
   * @since 2.14
   */
  public interface ValueHandler
  {
    String isValid(String text);
    Object toValue(String text);
  }

  private static class StringPositionPair implements Comparable<StringPositionPair>
  {
    Comparator<String> comparator = CommonPlugin.INSTANCE.getComparator();

    public String key;

    public int position;

    StringPositionPair(String key, int position)
    {
      this.key = key;
      this.position = position;
    }

    public int compareTo(StringPositionPair object)
    {
      if (object == this)
      {
        return 0;
      }
      else
      {
        StringPositionPair that = object;
        return comparator.compare(key, that.key);
      }
    }
  }

  public static boolean select(String filter, String labelValue)
  {
    if (filter != null && filter.length() > 0)
    {
      if (filter.length() > labelValue.length())
      {
        return false;
      }
      for (int i = 0; i < filter.length(); i++)
      {
        if (Character.toLowerCase(filter.charAt(i)) != Character.toLowerCase(labelValue.charAt(i)))
        {
          return false;
        }
      }
    }
    return true;
  }

  public static <T> String[] createItems(List<T> list, ILabelProvider labelProvider, boolean sorted)
  {
    return createItems(list, labelProvider, null, sorted);
  }

  public static <T> String[] createItems(List<T> list, ILabelProvider labelProvider, String filter, boolean sorted)
  {
    String[] result;

    if (filter != null && filter.length() > 0)
    {
      sorted = true;
    }

    // If there are objects to populate...
    //
    if (list != null && list.size() > 0)
    {
      if (sorted)
      {
        List<T> unsortedList = new ArrayList<T>(list.size());
        if (filter != null && filter.length() > 0)
        {
          for (int i = 0; i < list.size(); i++)
          {
            if (select(filter, labelProvider.getText(list.get(i))))
            {
              unsortedList.add(list.get(i));
            }
          }
        }
        else
        {
          unsortedList.addAll(list);
        }
        list.clear();

        StringPositionPair[] pairs = new StringPositionPair [unsortedList.size()];

        for (int i = 0, size = unsortedList.size(); i < size; ++i)
        {
          Object object = unsortedList.get(i);
          pairs[i] = new StringPositionPair(labelProvider.getText(object), i);
        }

        Arrays.sort(pairs);

        // Create a new array.
        //
        result = new String [unsortedList.size()];
        // Fill in the result array with labels and re-populate the original list in order.
        //
        for (int i = 0, size = unsortedList.size(); i < size; ++i)
        {
          result[i] = pairs[i].key;
          list.add(unsortedList.get(pairs[i].position));
        }
      }
      else
      {
        // Create a new array.
        //
        result = new String [list.size()];
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
  protected List<?> originalList;

  protected List<?> list;

  protected ILabelProvider labelProvider;

  protected boolean sorted;

  /**
   * @since 2.14
   */
  protected boolean autoShowDropDownList;

  private boolean mouseInCombo;

  public ExtendedComboBoxCellEditor(Composite composite, List<?> list, ILabelProvider labelProvider)
  {
    this(composite, list, labelProvider, false, SWT.READ_ONLY, null, false);
  }

  public ExtendedComboBoxCellEditor(Composite composite, List<?> list, ILabelProvider labelProvider, boolean sorted)
  {
    this(composite, list, labelProvider, sorted, SWT.READ_ONLY, null, false);
  }

  public ExtendedComboBoxCellEditor(Composite composite, List<?> list, ILabelProvider labelProvider, int style)
  {
    this(composite, list, labelProvider, false, style, null, false);
  }

  public ExtendedComboBoxCellEditor(Composite composite, List<?> list, ILabelProvider labelProvider, boolean sorted, int style)
  {
    this(composite, list, labelProvider, sorted, style, null, false);
  }

  /**
   * This constructor is useful for creating a cell editor that supports both choices of values as well as direct entry of a value in the text field.
   *
   * @since 2.14
   */
  public ExtendedComboBoxCellEditor(Composite composite, List<?> list, ILabelProvider labelProvider, boolean sorted, int style, final ValueHandler valueHandler, boolean autoShowDropDownList)
  {
    super(composite, createItems(sorted ? list = new ArrayList<Object>(list) : list, labelProvider, null, sorted), style);
    this.originalList = list;
    this.list = list;
    this.labelProvider = labelProvider;
    this.sorted = sorted;
    this.autoShowDropDownList = autoShowDropDownList;
    final CCombo combo = (CCombo)getControl();
    if ((style & SWT.READ_ONLY) != 0)
    {
      new FilteringAdapter(getControl());

      // Clicking in the text area while the drop down list is showing, will cause the drop down to disappear and reappear.
      // If we keep track of the very short time between the focus lost and the mouse down event, we can avoid that.
      class DropDownAvoider extends FocusAdapter implements Listener
      {
        private long focusLostTime;

        @Override
        public void focusLost(FocusEvent e)
        {
          focusLostTime = System.currentTimeMillis();
        }

        public void handleEvent(Event event)
        {
          if (System.currentTimeMillis() - focusLostTime < 100)
          {
            event.doit = false;
          }
        }
      }

      DropDownAvoider dopDownAvoider = new DropDownAvoider();
      combo.addListener(SWT.MouseDown, dopDownAvoider);
      combo.addFocusListener(dopDownAvoider);
    }
    else if (valueHandler != null)
    {
      setValidator(new ICellEditorValidator()
        {
          public String isValid(Object value)
          {
            return valueHandler.isValid((String)value);
          }
        });

      combo.addModifyListener
        (new ModifyListener()
         {
          boolean updating;

          public void modifyText(ModifyEvent e)
          {
            if (!updating)
            {
              updating = true;

              String text = combo.getText();
              ArrayList<Object> newList = new ArrayList<Object>(originalList);
              Object valueToSelect = null;

              try
              {
                valueToSelect = valueHandler.toValue(text);
                if (!newList.contains(valueToSelect))
                {
                  newList.add(0, valueToSelect);
                }
              }
              catch (RuntimeException exception)
              {
                // Ignore.
              }

              String[] items = createItems(newList, ExtendedComboBoxCellEditor.this.labelProvider, null, ExtendedComboBoxCellEditor.this.sorted);
              ExtendedComboBoxCellEditor.this.list = newList;
              combo.setItems(items);
              combo.notifyListeners(SWT.Selection, new Event());
              combo.setRedraw(false);
              Point selection = combo.getSelection();
              if (ExtendedComboBoxCellEditor.this.list.contains(valueToSelect))
              {
                setValue(valueToSelect);
              }
              else if (!ExtendedComboBoxCellEditor.this.list.isEmpty())
              {
                setValue(ExtendedComboBoxCellEditor.this.list.get(0));
              }
              combo.setText(text);
              combo.setSelection(selection);
              String oldErrorMessage = getErrorMessage();
              String newErrorMessage = valueHandler.isValid(text);
              setErrorMessage(newErrorMessage == null ? null : MessageFormat.format(newErrorMessage, new Object[0]));
              fireEditorValueChanged(oldErrorMessage == null, newErrorMessage == null);
              combo.setRedraw(true);
              updating = false;
            }
          }
         });
    }

    combo.addMouseTrackListener(new MouseTrackAdapter()
      {
        @Override
        public void mouseExit(MouseEvent e)
        {
          mouseInCombo = false;
        }

        @Override
        public void mouseEnter(MouseEvent e)
        {
          mouseInCombo = true;
        }
      });
  }

  protected void refreshItems(String filter)
  {
    CCombo combo = (CCombo)getControl();
    if (combo != null && !combo.isDisposed())
    {
      ArrayList<Object> newList = new ArrayList<Object>(originalList);
      String[] items = createItems(newList, labelProvider, filter, sorted);
      if (!newList.equals(list))
      {
        Object previousValue = getValue();
        list = newList;
        combo.setItems(items);
        if (list.contains(previousValue))
        {
          setValue(previousValue);
        }
        else if (!list.isEmpty())
        {
          setValue(list.get(0));
        }
      }
    }
  }

  /**
   * When the drop down button is clicked while the drop down list is showing,
   * the focus goes to the shell, but the cell editor will get focus again so hasn't really lost focus for long.
   * We track whether the mouse is currently in the combo and ignore focus lost in this case.
   */
  @Override
  protected void focusLost()
  {
    if (!mouseInCombo)
    {
      // Send one more event to ensure that the current text is selected at the right index in the list.
      ((CCombo)getControl()).notifyListeners(SWT.Modify, null);
      super.focusLost();
    }
  }

  @Override
  public Object doGetValue()
  {
    // Get the index into the list via this call to super.
    //
    int index = (Integer)super.doGetValue();
    return index < list.size() && index >= 0 ? list.get((Integer)super.doGetValue()) : null;
  }

  @Override
  public void doSetValue(Object value)
  {
    // Set the index of the object value in the list via this call to super.
    //
    int index = list.indexOf(value);
    if (index == -1 && value != null)
    {
      // Look for the item textually.
      String text = labelProvider.getText(value);
      index = Arrays.asList(getItems()).indexOf(text);
    }
    if (index != -1)
    {
      super.doSetValue(index);
    }
  }

  @Override
  public void setFocus()
  {
    super.setFocus();

    if (autoShowDropDownList)
    {
      ((CCombo)getControl()).setListVisible(true);
    }
  }

  public class FilteringAdapter implements KeyListener, FocusListener
  {

    public FilteringAdapter(Control control)
    {
      control.addKeyListener(this);
      control.addFocusListener(this);
    }

    private StringBuffer filter = new StringBuffer();

    private void refreshItems()
    {
      ExtendedComboBoxCellEditor.this.refreshItems(filter.toString());
    }

    public void keyPressed(KeyEvent e)
    {
      e.doit = false;

      if (e.keyCode == SWT.DEL || e.keyCode == SWT.BS)
      {
        if (filter.length() > 0)
        {
          filter = new StringBuffer(filter.substring(0, filter.length() - 1));
        }
      }
      else if (e.keyCode == SWT.ARROW_UP || e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR || e.keyCode == SWT.LF)
      {
        e.doit = true;
      }
      else if (e.keyCode == SWT.ESC)
      {
        filter = new StringBuffer();
      }
      else if (e.character != '\0')
      {
        filter.append(e.character);
      }
      if (!e.doit)
      {
        refreshItems();
      }
    }

    public void keyReleased(KeyEvent e)
    {
      // Do nothing
    }

    public void focusGained(FocusEvent e)
    {
      filter = new StringBuffer();
    }

    public void focusLost(FocusEvent e)
    {
      filter = new StringBuffer();
      refreshItems();
    }
  }
}
