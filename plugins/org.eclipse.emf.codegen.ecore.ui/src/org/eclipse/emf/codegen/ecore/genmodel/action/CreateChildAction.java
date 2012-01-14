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
package org.eclipse.emf.codegen.ecore.genmodel.action;


import java.util.Collection;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * This performs child creation by delegating to a {@link CreateChildCommand}.
 */
public class CreateChildAction extends StaticSelectionCommandAction
{
  /**
   * This is the descriptor for the child to be created.
   */
  protected CommandParameter newChildDescriptor;

  /**
   * This constructs an instance of an action that creates a child described by newChildDescriptor.
   */
  public CreateChildAction(IEditorPart editorPart, ISelection selection, CommandParameter newChildDescriptor)
  {
    super(editorPart);
    this.newChildDescriptor = newChildDescriptor;
    configureAction(selection);
  }

  /**
   * This returns the image that is used if the command does not provide an
   * override.
   */
  @Override
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return ExtendedImageRegistry.INSTANCE.getImageDescriptor(GenModelEditPlugin.INSTANCE.getImage("CreateChild"));
  }

  /**
   * This creates the command that creates the child and adds it under the
   * single selected object, specified in collection.
   */
  @Override
  protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection)
  {
    if (collection.size() == 1)
    {
      return CreateChildCommand.create(editingDomain, collection.iterator().next(), newChildDescriptor, collection);
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }
}
