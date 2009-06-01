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
 * $Id: IModelResource.java,v 1.2 2009/06/01 17:19:28 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.databinding.project.core.model.project.Foundation;


public interface IModelResource
{
  public interface Listener
  {
    public void dirtyStateChanged();
    public void commandStackChanged();
  }

  public Foundation getFoundation();

  public EditingDomain getEditingDomain();

  public IStatus setSavePoint();

  public IStatus commit();

  public IStatus rollback();

  public IStatus canUndo();
  
  public IStatus undo();

  public IStatus canRedo();
  
  public IStatus redo();

  public IStatus save();

  public boolean isDirty();

  public void addListener(Listener listener);

  public void removeListener(Listener listener);

  public void executeCmd(Command cmd);
}
