/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeEditor;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.emf.common.ui.viewer.ExtendedTableTreeViewer;


/**
 * This base class for implementing a {@link TableTreeEditor} calls {@link #editItem}
 * when the cell editor potentially needs to be activated.
 * This API is under construction; please do not use it for anything more than experimentation.
 * @deprecated
 * @see ExtendedTreeEditor
 */
@Deprecated
public abstract class ExtendedTableTreeEditor 
  extends TableTreeEditor
  implements KeyListener, MouseListener, SelectionListener
{
  protected Table table;
  protected TableItem selectedTableItem;
  protected TableTree tableTree;
  protected TableTreeItem editTableTreeItem;
  protected int editTableTreeItemColumn;
  boolean isActivating;
  boolean isDeactivating;
  boolean isActive;

  public ExtendedTableTreeEditor(TableTree tableTree)
  {
    super(tableTree);

    this.tableTree = tableTree;
    this.table = tableTree.getTable();

    table.addKeyListener(this);
    table.addMouseListener(this);
    table.addSelectionListener(this);
  }

  public void mouseDoubleClick(MouseEvent event)
  {
    // System.out.println("*double*");
    editTableTreeItem = null;
  }

  public void mouseDown(MouseEvent event)
  {
    editTableTreeItem = null;
    editTableTreeItemColumn =  -1;

    // System.out.println("*down*");
    if (event.button == 1)
    {
      boolean wasActive = getEditor() != null && !getEditor().isDisposed();

      Point point = new Point(event.x, event.y);
      TableItem [] tableItems = tableTree.getTable().getItems();
      int columnCount = tableTree.getTable().getColumnCount();
      LOOP: for (int i = tableTree.getTable().getTopIndex(); i < tableItems.length; ++i)
      {
        for (int j = 0; j < columnCount; ++j)
        {
          Rectangle bounds = tableItems[i].getBounds(j);
          if (bounds.y > event.y)
          {
            break LOOP;
          }
          else if (bounds.contains(event.x, event.y))
          {
            if (j != 0 || !tableItems[i].getImageBounds(0).contains(event.x, event.y))
            {
              TableItem tableItem = tableItems[i];
              if (tableItem == selectedTableItem || wasActive)
              {
                if (tableItem != selectedTableItem)
                {
                  table.setSelection(i);
                  Event selectionEvent = new Event();
                  selectionEvent.widget = table;
                  selectionEvent.item = tableItem;
                  table.notifyListeners(SWT.Selection, selectionEvent);
                  selectedTableItem = tableItem;
                }
                editTableTreeItem = (TableTreeItem)tableItems[i].getData(ExtendedTableTreeViewer.ITEM_ID);
                editTableTreeItemColumn = j;
              }
              else
              {
                TableItem mouseBasedTableItem = table.getItem(point);
                if (mouseBasedTableItem == null)
                {
                  table.setSelection(i);
                  Event selectionEvent = new Event();
                  selectionEvent.widget = table;
                  selectionEvent.item = tableItem;
                  table.notifyListeners(SWT.Selection, selectionEvent);
                  selectedTableItem = tableItem;
                }
              }
            }

            break LOOP;
          }
        }
      }

      if (editTableTreeItem == null && wasActive)
      {
        dismiss();
      }
    }
  }

  public void dismiss()
  {
    setEditor(null, null, -1);
  }

  public void mouseUp(MouseEvent event)
  {
    // System.out.println("*up*");
    if (event.button == 1)
    {
      TableItem [] tableItems = tableTree.getTable().getItems();
      int columnCount = tableTree.getTable().getColumnCount();
      LOOP: for (int i = tableTree.getTable().getTopIndex(); i < tableItems.length; ++i)
      {
        for (int j = 0; j < columnCount; ++j)
        {
          Rectangle bounds = tableItems[i].getBounds(j);
          if (bounds.y > event.y)
          {
            break LOOP;
          }
          else if (bounds.contains(event.x, event.y))
          {
            if (j != 0 || !tableItems[i].getImageBounds(0).contains(event.x, event.y))
            {
              TableItem tableItem = tableItems[i];
              TableTreeItem tableTreeItem = (TableTreeItem)tableItems[i].getData(ExtendedTableTreeViewer.ITEM_ID);
              if (tableTreeItem == editTableTreeItem)
              {
                selectedTableItem = null;
                table.showSelection();
                editItem(tableItem, editTableTreeItem, editTableTreeItemColumn);
              }
            }

            break LOOP;
          }
        }
      }
    }
  }

  public void widgetDefaultSelected(SelectionEvent event)
  {
    widgetSelected(event);
  }

  public void widgetSelected(SelectionEvent event)
  {
    TableItem [] selection = table.getSelection();
    selectedTableItem = selection.length == 1 ? selection[0] : null;
  }

  public void keyPressed(KeyEvent event)
  {
    // Do nothing
  }

  public void keyReleased(KeyEvent event)
  {
    TableItem [] selection = table.getSelection();
    selectedTableItem = selection.length == 1 ? selection[0] : null;
    if (event.character == ' ' && selectedTableItem != null)
    {
      editItem(selectedTableItem, (TableTreeItem)selectedTableItem.getData(ExtendedTableTreeViewer.ITEM_ID), 0);
      selectedTableItem = null;
    }
  }

  protected abstract void editItem(TableItem tableItem, TableTreeItem tableTreeItem, int column);

  @Override
  public void setEditor(Control canvas, TableTreeItem tableTreeItem, int column)
  {
    super.setEditor(canvas, tableTreeItem, column);
  }
}
