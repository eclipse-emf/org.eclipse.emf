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
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


/**
 * An abstract base class for implementing table editing where multiple columns (all by default) are editable.
 * A table cursor is used to provide keyboard navigation over the table.
 * <p><b>Provisional API.</b> Please do not use it for anything more than experimentation.
 * @since 2.5
 */
public abstract class MultiColumnTableEditor extends BasicTableEditor
  implements KeyListener, MouseListener, SelectionListener
{
  protected TableCursor cursor;

  // Bit field: each bit indicates if the corresponding column is editable.
  //
  protected int editableColumns;

  public MultiColumnTableEditor(Table table)
  {
    this(table, 0xFFFFFFFF);
  }

  public MultiColumnTableEditor(Table table, int editableColumns)
  {
    super(table);
    this.editableColumns = editableColumns;
    table.addSelectionListener(this);

    cursor = new TableCursor(table, SWT.NONE);  
    cursor.addSelectionListener(this);
    cursor.addMouseListener(this);
    if ((table.getStyle() & SWT.CHECK) != 0)
    {
      cursor.addKeyListener(this);
    }
  }

  @Override
  public void dispose()
  {
    if (table != null)
    {
      table.removeSelectionListener(this);
      table = null;
    }
    if (cursor != null)
    {
      cursor.removeKeyListener(this);
      cursor.removeMouseListener(this);
      cursor.removeSelectionListener(this);
      cursor = null;
    }
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.character == ' ')
    {
      TableItem item = getSelection();
      if (item != null)
      {
        item.setChecked(!item.getChecked());
      }
    }
  }

  public void keyReleased(KeyEvent e)
  {
    // Do nothing.
  }

  public void mouseDoubleClick(MouseEvent e)
  {
    // Do nothing.
  }

  public void mouseDown(MouseEvent e)
  {
    // Do nothing.
  }

  public void mouseUp(MouseEvent e)
  {
    Point size = cursor.getSize();
    if (e.x >= 0 && e.x < size.x && e.y >= 0 && e.y < size.y)
    {
      edit(cursor.getRow(), cursor.getColumn());
    }
  }

  public void widgetSelected(SelectionEvent e)
  {
    // Move the table selection with the cursor.
    //
    if (e.widget == cursor)
    {
      table.setSelection(cursor.getRow());
    }

    // Initially set the cursor selection to show it when an item is selected in the table.
    //
    if (e.widget == table)
    {
      if (cursor.getRow() == null)
      {
        TableItem item = getSelection();
        if (item != null)
        {
          cursor.setSelection(item, 0);
        }
        cursor.setFocus();
      }
    }
  }

  public void widgetDefaultSelected(SelectionEvent e)
  {
    if (e.widget == cursor)
    {
      TableItem item = getSelection();
      if (item != null)
      {
        edit(item, cursor.getColumn());
      }
    }
  }

  @Override
  protected void edit(TableItem item, int column)
  {
    if (canEdit(item, column))
    {
      cursor.setVisible(false);
      super.edit(item, column);
    }
  }

  @Override
  protected boolean canEdit(TableItem item, int column)
  {
    return (1 << column & editableColumns) != 0;
  }

  @Override
  protected void endEditing(TableItem item, int column, Control editor, boolean accept)
  {
    super.endEditing(item, column, editor, accept);
    cursor.setVisible(true);
    cursor.setFocus();
  }
}
