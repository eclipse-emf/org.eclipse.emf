/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: UndoAction.java,v 1.2 2009/06/01 17:19:27 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import org.eclipse.jface.action.Action;

import org.eclipse.emf.examples.databinding.project.core.IModelResource;


public class UndoAction extends Action
{
  private final IModelResource resource;

  public UndoAction(IModelResource resource)
  {
    this.resource = resource;
    resource.addListener(new IModelResource.Listener()
      {

        public void dirtyStateChanged()
        {
        }

        public void commandStackChanged()
        {
          update();
        }
      });
    update();
  }

  @Override
  public void run()
  {
    if (resource.canUndo().isOK())
    {
      resource.undo();
    }
  }

  private void update()
  {
    if (resource.canUndo().isOK())
    {
      setText("Undo " + resource.getEditingDomain().getCommandStack().getUndoCommand().getLabel());
      setEnabled(true);
    }
    else
    {
      setText("Undo");
      setEnabled(false);
    }
  }
}