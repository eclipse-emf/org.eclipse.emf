/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ExtendedDialogCellEditor.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.ui.celleditor;


import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;


/**
 * This uses a label provider to display a dialog cell editor.
 */
public abstract class ExtendedDialogCellEditor extends DialogCellEditor
{
  protected ILabelProvider labelProvider;

  public ExtendedDialogCellEditor(Composite composite, ILabelProvider labelProvider)
  {
    super(composite);
    this.labelProvider = labelProvider;
  }

  protected void updateContents(Object object)
  {
    if (getDefaultLabel() != null && labelProvider != null)
    {
      getDefaultLabel().setText(labelProvider.getText(object));
    }
  }
}
