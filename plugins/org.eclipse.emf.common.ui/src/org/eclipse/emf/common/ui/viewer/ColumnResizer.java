/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.common.ui.viewer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;


/**
 * This utility class provides {@link #addColumnResizer(Tree)} and {@link #addColumnResizer(Table)} to create a {@link ColumnResizer.Handler} that will pack all columns to their minimal size
 * and will respond to {@link ControlListener#controlResized(ControlEvent) control resized} events to update the column sizes automatically.
 * If needed, the last column will be expanded beyond its minimal packed size to make it large enough such that all Tree or Table columns exactly fit the {@link Composite#getClientArea() client area}.
 * When the contents of the {@link Tree} or {@link Table} changes, the Handler can be instructed to {@link ColumnResizer.Handler#resizeColumns() resize} the columns.
 * 
 * @since 2.14
 */
public final class ColumnResizer
{
  private ColumnResizer()
  {
    throw new RuntimeException("No instances");
  }

  /**
   * A handler created by {@link ColumnResizer#addColumnResizer(Tree)} or {@link ColumnResizer#addColumnResizer(Table)}.
   * that provides the ability to manually {@link #resizeColumns() resize} the columns to their minimal packed size when the contents of the {@link Tree} or {@link Table} changes.
   * If needed, the last column will be expanded beyond its minimal packed size to make it large enough such that all Tree or Table columns exactly fit the {@link Composite#getClientArea() client area}.
   * The handler itself will respond to {@link ControlListener#controlResized(ControlEvent) control resized} events to update the column sizes automatically.
   */
  public static abstract class Handler
  {
    private Handler()
    {
    }

    /**
     * Resize all columns to their minimal packed size.
     * This should be called when the contents of the {@link Tree} or {@link Table} changes.
     */
    public abstract void resizeColumns();

    /**
     * This removes this handler from the {@link Tree} or {@link Table} to which it was added.
     */
    public abstract void dispose();
  }

  /**
   * Creates a handler for resizing all columns to their minimal packed size.
   */
  public static Handler addColumnResizer(Tree tree)
  {
    return new TreeColumnResizeHandler(tree);
  }

  /**
   * Creates a handler for resizing all columns to their minimal packed size.
   */
  public static Handler addColumnResizer(Table table)
  {
    return new TableColumnResizeHandler(table);
  }

  private static class ParentHandler extends ControlAdapter implements DisposeListener, Runnable
  {
    private final Composite control;
    
    private final Composite parent;

    private boolean dispatched;

    public ParentHandler(Composite control)
    {
      this.control = control;
      this.parent = control.getParent();
      control.addDisposeListener(this);
      parent.addControlListener(this);
    }

    public void run()
    {
      if (!parent.isDisposed() && dispatched)
      {
        dispatched = false;
        parent.setRedraw(true);
      }
    }

    @Override
    public void controlResized(ControlEvent e)
    {
      if (!dispatched)
      {
        parent.setRedraw(false);
        dispatched = true;
        parent.getDisplay().asyncExec(this);
      }
    }

    public void widgetDisposed(DisposeEvent e)
    {
      parent.removeControlListener(this);
    }
    
    public void dispose()
    {
      run();
      dispatched = false;
      control.removeDisposeListener(this);
      parent.removeControlListener(this);
    }
  }

  private static abstract class ColumnResizerHandler<T extends Composite, C extends Item> extends Handler implements ControlListener
  {
    private final T control;

    private final ParentHandler parentHandler;

    private int clientWidth = -1;

    private List<Integer> columnWidths = Collections.emptyList();

    private boolean resizing;

    public ColumnResizerHandler(T control)
    {
      this.control = control;
      for (C column : getColumns())
      {
        disableResizeable(column);
      }

      parentHandler = new ParentHandler(control);
      control.addControlListener(this);
    }

    public T getControl()
    {
      return control;
    }

    protected abstract boolean isPackable();

    protected abstract List<? extends C> getColumns();

    protected abstract void disableResizeable(C column);

    protected abstract int getWidth(C column);

    protected abstract void setWidth(C column, int width);

    protected abstract void pack(C column);

    protected List<Integer> getColumnWidths()
    {
      List<? extends C> columns = getColumns();
      List<Integer> result = new ArrayList<Integer>(columns.size());
      for (C column : columns)
      {
        result.add(getWidth(column));
      }
      return result;
    }

    public void controlResized(ControlEvent controlEvent)
    {
      if (!resizing && isPackable())
      {
        T control = getControl();
        Rectangle clientArea = control.getClientArea();
        int clientWidth = clientArea.width - clientArea.x;
        List<Integer> columnWidths = getColumnWidths();

        boolean inputChanged = controlEvent == null;
        if (inputChanged || clientWidth != this.clientWidth || this.columnWidths.equals(columnWidths))
        {
          try
          {
            resizing = true;
            control.setRedraw(false);

            List<? extends C> columns = getColumns();
            for (C column : columns)
            {
              pack(column);
            }

            List<Integer> packedColumnWidths = getColumnWidths();
            int total = 0;
            int limit = columns.size() - 1;
            for (int i = 0; i < limit; ++i)
            {
              int width = packedColumnWidths.get(i) + 10;
              total += width;
              setWidth(columns.get(i), width);
            }

            int width = packedColumnWidths.get(limit);
            if (total + width < clientWidth)
            {
              width = clientWidth - total;
            }
            setWidth(columns.get(limit), width);
          }
          finally
          {
            this.clientWidth = clientWidth;
            this.columnWidths = getColumnWidths();
            control.setRedraw(true);
            parentHandler.run();
            resizing = false;
          }
        }
      }
    }

    public void controlMoved(ControlEvent e)
    {
    }

    @Override
    public void resizeColumns()
    {
      controlResized(null);
    }

    @Override
    public void dispose()
    {
      parentHandler.dispose();
      control.removeControlListener(this);
    }
  }

  private static class TreeColumnResizeHandler extends ColumnResizerHandler<Tree, TreeColumn>
  {
    public TreeColumnResizeHandler(Tree tree)
    {
      super(tree);

      class TreeStateListener implements TreeListener, Runnable
      {
        private boolean dispatched;

        public void run()
        {
          dispatched = false;
          if (!getControl().isDisposed())
          {
            resizeColumns();
          }
        }

        private void dispatch()
        {
          if (!dispatched)
          {
            dispatched = true;
            getControl().getDisplay().asyncExec(this);
          }
        }

        public void treeCollapsed(TreeEvent e)
        {
          dispatch();
        }

        public void treeExpanded(TreeEvent e)
        {
          dispatch();
        }
      }

      tree.addTreeListener(new TreeStateListener());
    }

    @Override
    protected List<? extends TreeColumn> getColumns()
    {
      return Arrays.asList(getControl().getColumns());
    }

    @Override
    protected void disableResizeable(TreeColumn column)
    {
      column.setResizable(false);
    }

    @Override
    protected int getWidth(TreeColumn column)
    {
      return column.getWidth();
    }

    @Override
    protected void setWidth(TreeColumn column, int width)
    {
      column.setWidth(width);
    }

    @Override
    protected boolean isPackable()
    {
      TreeItem[] items = getControl().getItems();
      return items.length == 0 || !items[0].isDisposed();
    }

    @Override
    protected void pack(TreeColumn column)
    {
      column.pack();
    }
  }

  private static class TableColumnResizeHandler extends ColumnResizerHandler<Table, TableColumn>
  {
    public TableColumnResizeHandler(Table table)
    {
      super(table);
    }

    @Override
    protected List<? extends TableColumn> getColumns()
    {
      return Arrays.asList(getControl().getColumns());
    }

    @Override
    protected void disableResizeable(TableColumn column)
    {
      column.setResizable(false);
    }

    @Override
    protected int getWidth(TableColumn column)
    {
      return column.getWidth();
    }

    @Override
    protected void setWidth(TableColumn column, int width)
    {
      column.setWidth(width);
    }

    @Override
    protected boolean isPackable()
    {
      TableItem[] items = getControl().getItems();
      return items.length == 0 || !items[0].isDisposed();
    }

    @Override
    protected void pack(TableColumn column)
    {
      column.pack();
    }
  }
}
