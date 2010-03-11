/**
 * <copyright> 
 *
 * Copyright (c) 2009 IBM Corporation and others.
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
 * $Id: BasicTableEditor.java,v 1.1 2010/03/11 02:30:05 khussey Exp $
 */
package org.eclipse.emf.common.ui.celleditor;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


/**
 * The base class for implementing single-column and multi-column table editing.
 * <p><b>Provisional API.</b> Please do not use it for anything more than experimentation.
 * @since 2.5
 */
public abstract class BasicTableEditor extends TableEditor
{
  protected Table table;

  public BasicTableEditor(Table table)
  {
    super(table);
    this.table = table;

    horizontalAlignment = SWT.LEAD;
    grabHorizontal = true;
    minimumWidth = 50;
  }

  protected TableItem getSelection()
  {
    TableItem[] selection = table.getSelection();
    return selection.length == 1 ? selection[0] : null;
  }
  
  protected void edit(TableItem item, int column)
  {
    if (canEdit(item, column))
    {
      setEditor(createEditor(item, column), item, column);
    }
  }

  protected boolean canEdit(TableItem item, int column)
  {
    return true;
  }

  protected abstract Control createEditor(TableItem item, int column);

  protected Text createTextEditor(final TableItem item, final int column)
  {
    final Text text = new Text(table, SWT.NONE);
    text.setText(item.getText(column));
    text.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusLost(FocusEvent e)
      {
        endEditing(item, column, text, true);
      }
    });

    text.addTraverseListener(new TraverseListener()
    {
      public void keyTraversed(TraverseEvent e)
      {
        if (e.detail == SWT.TRAVERSE_ESCAPE)
        {
          e.doit = false;
          endEditing(item, column, text, false);
        }
        else if (e.detail == SWT.TRAVERSE_RETURN)
        {
          e.doit = false;
          endEditing(item, column, text, true);
        }
      }
    });

    text.selectAll();
    text.setFocus();
    return text;
  }

  protected void endEditing(TableItem item, int column, Control editor, boolean accept)
  {
    if (getEditor() != null)
    {
      if (accept)
      {
        update(item, column, editor);
      }
      setEditor(null);
      editor.dispose();
    }
  }

  protected abstract void update(TableItem item, int column, Control editor);
}
