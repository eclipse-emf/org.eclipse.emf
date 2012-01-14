/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.exporter.util;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;


/**
 * @since 2.2.0
 */
public class ExporterUIUtil
{
  public static abstract class CompositeEditorHelper implements Listener
  {
    protected Composite composite;

    protected ControlEditor compositeEditor;

    protected CompositeEditorHelper(Composite composite)
    {
      this.composite = composite;
      composite.addListener(SWT.Selection, this);
      composite.addListener(SWT.DefaultSelection, this);
      composite.addListener(SWT.Dispose, this);

      compositeEditor = createControlEditor(composite);
      compositeEditor.horizontalAlignment = SWT.LEFT;
      compositeEditor.grabHorizontal = true;
    }

    protected ControlEditor createControlEditor(Composite composite)
    {
      return new ControlEditor(composite);
    }

    public void handleEvent(Event event)
    {
      if (event.widget == composite)
      {
        switch (event.type)
        {
          case SWT.Selection:
          case SWT.DefaultSelection:
          compositeSelected(event);
            break;

          case SWT.Dispose:
          compositeDisposed();
            break;
        }
      }
    }

    protected void compositeSelected(Event event)
    {
      disposeOldEditorControl(compositeEditor.getEditor());

      Widget item = event.item;
      Control editorControl = createEditorControl(item);
      if (editorControl != null)
      {
        setEditor(editorControl, item);
      }
    }

    protected void compositeDisposed()
    {
      composite.removeListener(SWT.Selection, this);
      composite.removeListener(SWT.DefaultSelection, this);
      composite.removeListener(SWT.Dispose, this);
      composite = null;
    }

    protected void disposeOldEditorControl(Control control)
    {
      if (control != null && !control.isDisposed())
      {
        control.removeListener(SWT.Selection, this);
        control.dispose();
      }
    }

    protected void setEditor(Control editorControl, Widget item)
    {
      compositeEditor.setEditor(editorControl);
    }

    protected abstract Control createEditorControl(Widget item);
  }

  public static abstract class TreeEditorHelper extends CompositeEditorHelper
  {
    public TreeEditorHelper(Tree tree)
    {
      super(tree);
    }

    @Override
    protected ControlEditor createControlEditor(Composite composite)
    {
      return new TreeEditor((Tree)composite);
    }

    public void setColumn(int index)
    {
      TreeColumn treeColumn = ((Tree)composite).getColumn(index);

      TreeEditor treeEditor = (TreeEditor)compositeEditor;
      treeEditor.minimumWidth = treeColumn.getWidth();
      treeEditor.setColumn(index);
    }

    public int getColumn()
    {
      return ((TreeEditor)compositeEditor).getColumn();
    }

    @Override
    protected void setEditor(Control editorControl, Widget item)
    {
      ((TreeEditor)compositeEditor).setEditor(editorControl, (TreeItem)item);
    }
  }
}
