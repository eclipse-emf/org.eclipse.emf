/**
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.ui.celleditor;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


/**
 * An abstract base class for implementing table editing where only a single column is editable.
 * Clicking anywhere on a row or pressing enter when a row is selected edits the editable column.
 * <p><b>Provisional API.</b> Please do not use it for anything more than experimentation.
 * @since 2.5
 */
public abstract class SingleColumnTableEditor extends BasicTableEditor
  implements MouseListener, SelectionListener, TraverseListener
{
  protected int editableColumn;

  // These are used to avoid restarting editing immediately when the user ends editing
  // by clicking outside of an open editor but within the same item.
  //
  protected int editorFocusLostTime;
  protected TableItem editorFocusLostItem;

  public SingleColumnTableEditor(Table table)
  {
    this(table, table.getColumnCount() - 1);
  }

  public SingleColumnTableEditor(Table table, int editableColumn)
  {
    super(table);
    this.editableColumn = editableColumn;
    table.addMouseListener(this);
    table.addSelectionListener(this);
    table.addTraverseListener(this);
  }

  @Override
  public void dispose()
  {
    if (table != null)
    {
      table.removeMouseListener(this);
      table.removeSelectionListener(this);
      table.removeTraverseListener(this);
      table = null;
    }
    editorFocusLostItem = null;
  }

  public void widgetSelected(SelectionEvent e)
  {
    // Do nothing.
  }

  public void widgetDefaultSelected(SelectionEvent e)
  {
    TableItem item = getSelection();
    if (item != null)
    {
      edit(item, editableColumn);
    }
  }

  public void mouseDoubleClick(MouseEvent e)
  {
    // Do nothing.
  }

  public void mouseDown(MouseEvent e)
  {
    if (e.time != editorFocusLostTime)
    {
      editorFocusLostItem = null;
    }

    // If the table is not full selection style (SWT.FULL_SELECTION), the selection must be updated manually.
    //
    TableItem item = getItem(e.x, e.y);
    if (item != null && item != getSelection())
    {
      table.setSelection(item);
    }
  }

  public void mouseUp(MouseEvent e)
  {
    TableItem item = getItem(e.x, e.y);
    if (item != null && !item.equals(editorFocusLostItem))
    {
      edit(item, editableColumn);
    }
  }

  protected TableItem getItem(int x, int y)
  {
    int tableWidth = table.getClientArea().width;
    TableItem[] items = table.getItems();
    for (int row = table.getTopIndex(); row < items.length; row++)
    {
      TableItem item = items[row];
      Rectangle bounds = item.getBounds(0);
      bounds.width = tableWidth - bounds.x;
      
      if (bounds.contains(x, y))
      {
        return item;
      }
    }
    return null;
  }

  // Prevent return press from escaping to the table's container.
  // 
  public void keyTraversed(TraverseEvent e)
  {
    if (e.detail == SWT.TRAVERSE_RETURN)
    {
      e.doit = false;
    }
  }

  @Override
  protected boolean canEdit(TableItem item, int column)
  {
    return column == editableColumn;
  }

  @Override
  protected Text createTextEditor(final TableItem item, final int column)
  {
    final Text text = super.createTextEditor(item, column);
    text.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusLost(FocusEvent e)
      {
        editorFocusLostTime = e.time;
        editorFocusLostItem = item;
      }
    });

    return text;
  }
}
