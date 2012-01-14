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
package org.eclipse.emf.edit.ui.action;


import java.util.Collection;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * A paste action is implemented by creating a {@link PasteFromClipboardCommand}.
 */
public class PasteAction extends CommandActionHandler
{
  public PasteAction(EditingDomain domain)
  {
    super(domain, EMFEditUIPlugin.INSTANCE.getString("_UI_Paste_menu_item"));
  }

  public PasteAction()
  {
    super(null, EMFEditUIPlugin.INSTANCE.getString("_UI_Paste_menu_item"));
  }

  @Override
  public Command createCommand(Collection<?> selection)
  {
    if (selection.size() == 1)
    {
      return PasteFromClipboardCommand.create(domain, selection.iterator().next(), null);
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
   */
  @Deprecated
  public void setActiveEditor(IEditorPart editorPart)
  {
    setActiveWorkbenchPart(editorPart);
  }

  /**
   * @since 2.1.0
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
    }
  }
}
