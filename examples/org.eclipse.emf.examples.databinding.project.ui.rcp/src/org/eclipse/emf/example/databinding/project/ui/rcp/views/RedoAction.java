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


public class RedoAction extends Action
{
  private final IModelResource resource;
  private final IModelResource.Listener listener;

  public RedoAction(IModelResource resource)
  {
    this.resource = resource;
    this.listener = new IModelResource.Listener()
      {

        public void dirtyStateChanged()
        {
          // Ignore
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
    if (resource.canRedo().isOK())
    {
      resource.redo();
    }
  }

  private void update()
  {
    if (resource.canRedo().isOK())
    {
      setText("Redo " + resource.getEditingDomain().getCommandStack().getRedoCommand().getLabel());
      setEnabled(true);
    }
    else
    {
      setText("Redo");
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
