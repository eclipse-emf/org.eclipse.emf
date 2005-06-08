/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: CopyAction.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.Collection;

import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * A copy action is implemented by creating a {@link CopyToClipboardCommand}.
 */
public class CopyAction extends CommandActionHandler
{
  public CopyAction(EditingDomain domain)
  {
    super(domain, EMFEditUIPlugin.INSTANCE.getString("_UI_Copy_menu_item"));
  }

  public CopyAction()
  {
    super(null, EMFEditUIPlugin.INSTANCE.getString("_UI_Copy_menu_item"));
  }

  public Command createCommand(Collection selection)
  {
    return CopyToClipboardCommand.create(domain, selection);
  }

  public void setActiveEditor(IEditorPart editorPart)
  {
    if (editorPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)editorPart).getEditingDomain();
    }
  }
}
