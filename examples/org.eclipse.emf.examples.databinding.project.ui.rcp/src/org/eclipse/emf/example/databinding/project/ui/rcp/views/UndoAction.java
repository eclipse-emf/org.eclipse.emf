/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import org.eclipse.jface.action.Action;

import org.eclipse.emf.examples.databinding.project.core.IModelResource;


public class UndoAction extends Action
{
  private final IModelResource resource;
  private final IModelResource.Listener listener;

  public UndoAction(IModelResource resource)
  {
    this.resource = resource;
    this.listener = new IModelResource.Listener()
      {

        public void dirtyStateChanged()
        {
        }

        public void commandStackChanged()
        {
          update();
        }
      };
    resource.addListener(listener);
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

  /**
   * Clean up
   */
  public void dispose()
  {
    resource.removeListener(listener);
  }
}