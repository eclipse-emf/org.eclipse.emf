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
 * $Id: CommandAction.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * This class is used to implement a selection-based {@link IAction} on the menubar, the toolbar, or a popup menu
 * by delegating all required behaviour to a {@link Command}.
 * All aspects of the action are delegated, 
 * namely the enablement state, the menu text, the toolbar icon, and the help tip text.
 * A derived class implements {@link #createActionCommand createActionCommand} 
 * to return a command based on the {@link EditingDomain} and the collection of selected objects.
 *
 * <p>
 * This class can also be used to implement actions not based on a selection, 
 * in that case the method {@link #selectionChanged selectionChanged} should be overriden to do nothing.
 */
public class CommandAction implements IEditorActionDelegate 
{
  /**
   * When the action is used as an editor part (for the menubar and toolbar), this records the current editor.
   */
  protected IEditorPart editorPart;

  /**
   * This returns the action to which we are delegating our action properties.
   */
  protected IAction action;

  /**
   * This records the editing domain of the current editor.
   * For global popups, we try to determine the editing domain from the selected objects themselves.
   */
  protected EditingDomain editingDomain;

  /** 
   * This records the collection of selected objects so that a new command can be easily constructed 
   * after the execution of the command previously constructed from this selection.
   */
  protected Collection collection;

  /**
   * This records the command that is created each time the selection changes.
   */
  protected Command command;

  /**
   * This constructs an instance.
   */
  public CommandAction()
  {
  }

  /**
   * This method must be implemented to create the command for this action, 
   * given the editing domain and the collection of selected objects.
   */
  protected Command createActionCommand(EditingDomain editingDomain, Collection collection)
  {
    return UnexecutableCommand.INSTANCE;
  }

  /**
   * This returns the image descriptor if the command does not provide an override.
   */
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return null;
  }

  /**
   * The framework calls this so that we can register against this editor.
   * We use this as an opportunity to record the action and the editor part for later use.
   */
  public void setActiveEditor(IAction action, IEditorPart editorPart) 
  {
    // If the editor changes...
    //
    if (this.editorPart != editorPart)
    {
      // Discard the old editing domain.
      //
      editingDomain = null;

      // If there is a new one...
      //
      if (editorPart != null)
      {
        // Does this editor provide an editing domain?
        //
        if (editorPart instanceof IEditingDomainProvider)
        {
          editingDomain = ((IEditingDomainProvider)editorPart).getEditingDomain();
        }
      }

      // Record the part.
      //
      this.editorPart = editorPart;
    }

    // Save the action.
    //
    this.action = action;
  }

  /**
   * The action must have been enabled for this to have been called,
   * so we must have stored the selection already by this point.
   */
  public void run(IAction action)
  {
    // This guard is for extra security, but should not be necessary.
    //
    if (editingDomain != null && command != null)
    {
      // Use up the command.
      // Note that notification will cause a new command to be created.
      //
      editingDomain.getCommandStack().execute(command);
    }
  }

  /**
   * This is invoked by the framework so that the action state can be updated. 
   */
  public void selectionChanged(IAction action, ISelection selection) 
  {
    // We will only deal with structured selections.
    //
    if (selection instanceof IStructuredSelection)
    {
      // Convert the selection to a collection of the selected objects.
      //
      collection = new ArrayList();
      for (Iterator elements = ((IStructuredSelection)selection).iterator(); elements.hasNext(); )
      {
        collection.add(elements.next());
      }

      // If we aren't getting the domain from the editor part...
      // This happens when this action is used for a global popup action.
      // We try to get the editing domain from one of the objects in the selection.
      //
      if (editorPart == null)
      {
        for (Iterator objects = collection.iterator(); objects.hasNext(); )
        {
          Object object = objects.next();
          editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(object);
          if (editingDomain != null)
          {
            break;
          }
        }
      }

      // If we have a good editing domain...
      //
      if (editingDomain != null)
      {
        // Delegate the action for this object to the editing domain.
        //
        command = createActionCommand(editingDomain, collection);

        // We can enable the action as indicated by the command,
        // and we can set all the other values from the command.
        //
        ((Action)action).setEnabled(command.canExecute());

        if (command instanceof CommandActionDelegate)
        {
          CommandActionDelegate commandActionDelegate = (CommandActionDelegate)command;
          Object object = commandActionDelegate.getImage();
          ImageDescriptor imageDescriptor = objectToImageDescriptor(object);
          if (imageDescriptor != null)
          {
            ((Action)action).setImageDescriptor(imageDescriptor);
          }
          else if (getDefaultImageDescriptor() != null)
          {
            ((Action)action).setImageDescriptor(getDefaultImageDescriptor());
          }

          if (commandActionDelegate.getText() != null)
          {
            ((Action)action).setText(commandActionDelegate.getText());
          }

          if (commandActionDelegate.getDescription() != null)
          {
            ((Action)action).setDescription(commandActionDelegate.getDescription());
          }

          if (commandActionDelegate.getToolTipText() != null)
          {
            ((Action)action).setToolTipText(commandActionDelegate.getToolTipText());
          }
        }

        // Nothing more to do and we don't want to do the default stuff below.
        //
        return;
      }
    }

    // We just can't do it.
    //
    ((Action)action).setEnabled(false);

    // No point in keeping garbage.
    //
    command = null;
    collection = null;

    // Show the colourless image.
    //
    if (getDefaultImageDescriptor() != null)
    {
      ((Action)action).setImageDescriptor(getDefaultImageDescriptor());
    }
  }

  protected ImageDescriptor objectToImageDescriptor(Object object)
  {
    return ExtendedImageRegistry.getInstance().getImageDescriptor(object);
  }
}
