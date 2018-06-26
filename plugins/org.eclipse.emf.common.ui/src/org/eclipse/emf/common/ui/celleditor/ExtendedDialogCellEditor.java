/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.ui.celleditor;


import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * This uses a label provider to display a dialog cell editor.
 */
public abstract class ExtendedDialogCellEditor extends DialogCellEditor
{
  protected ILabelProvider labelProvider;

  private Button button;

  public ExtendedDialogCellEditor(Composite composite, ILabelProvider labelProvider)
  {
    super(composite);
    this.labelProvider = labelProvider;
  }

  @Override
  protected Control createContents(Composite cell)
  {
    final Control control = super.createContents(cell);
    control.addMouseListener(new MouseAdapter()
      {
        @Override
        public void mouseUp(MouseEvent event)
        {
          if (button != null)
          {
            button.notifyListeners(SWT.Selection, null);
          }
        }
      });
    return control;
  }

  @Override
  protected Button createButton(Composite parent)
  {
    button = super.createButton(parent);
    return button;
  }

  @Override
  protected void updateContents(Object object)
  {
    if (getDefaultLabel() != null && labelProvider != null)
    {
      getDefaultLabel().setText(labelProvider.getText(object));
    }
  }
}
