/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: CommandAction.java,v 1.1 2010/03/11 02:30:12 khussey Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

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
 * in that case the method {@link #selectionChanged selectionChanged} should be overridden to do nothing.
 */
public class CommandAction implements IEditorActionDelegate, IViewActionDelegate, IActionDelegate2
{
  /**
   * This records the editor or view with which the action is currently associated.
   * @since 2.1.0
   */
  protected IWorkbenchPart workbenchPart;

  /**
   * If this action delegate is associated with an editor, it is also recorded here.
   * This field was retained for backwards compatibility.
   * @deprecated As of EMF 2.1.0, replaced by {@link #workbenchPart}.
   */
  @Deprecated
  protected IEditorPart editorPart;

  /**
   * This records the proxy action created by the platform.
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
  protected Collection<Object> collection;

  /**
   * This records the command that is created each time the selection changes.
   */
  protected Command command;

  /**
   * This constructs an instance.
   */
  public CommandAction()
  {
    super();
  }

  /**
   * This method must be implemented to create the command for this action, 
   * given the editing domain and the collection of selected objects.
   */
  protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection)
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
   * This is called immediately after this action delegate is created.
   * We use this as an opportunity to record the proxy action for later use.
   * @since 2.1.0
   */
  public void init(IAction action)
  {
    this.action = action;
  }

  /**
   * This is called when this action delegate is no longer needed. This implementation does nothing.
   * @since 2.1.0
   */
  public void dispose()
  {
    // Do nothing
  }

  /**
   * For editor actions, the framework calls this when the active editor changes, so that we can connect with it.
   * We call {@link #setActiveWorkbenchPart} to record it and its editing domain, if it can provide one.
   */
  public void setActiveEditor(IAction action, IEditorPart editorPart)
  {
    setActiveWorkbenchPart(editorPart);
    this.editorPart = editorPart;
    this.action = action;
  }

  /**
   * For view actions, the framework calls this when the view is shown, so that we can connect with it.
   * We call {@link #setActiveWorkbenchPart} to record it and its editing domain, if it can provide one.
   * @since 2.1.0
   */
  public void init(IViewPart view)
  {
    setActiveWorkbenchPart(view);
  }

  /**
   * This records the specified workbench part, and if it is an editing domain provider, its editing domain.
   * @since 2.1.0
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    // If the workbench part changes...
    //
    if (this.workbenchPart != workbenchPart)
    {
      // Discard the old editing domain.
      //
      editingDomain = null;

      // If there is a new one...
      //
      if (workbenchPart != null)
      {
        // Does this part provide an editing domain?
        //
        if (workbenchPart instanceof IEditingDomainProvider)
        {
          editingDomain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
        }
      }

      // Record the part.
      //
      this.workbenchPart = workbenchPart;
    }    
  }

  /**
   * Because we implement {@link IActionDelegate2}, this is called instead of the old {@link #run(IAction) run}.
   * This simply calls that method, which must be invoked since a subclass may have overridden it.
   * @since 2.1.0
   */
  public void runWithEvent(IAction action, Event event)
  {
    run(action);
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
      List<?> list = ((IStructuredSelection)selection).toList();
      collection = new ArrayList<Object>(list);

      // If we aren't getting the domain from the workbench part...
      // This happens when this action is used for a global popup action.
      // We try to get the editing domain from one of the objects in the selection.
      //
      if (workbenchPart == null && editorPart == null) //DMS editingDomain == null) ?
      {
        for (Object object : collection)
        {
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
