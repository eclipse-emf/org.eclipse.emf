/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: StaticSelectionCommandAction.java,v 1.1 2010/03/11 02:30:12 khussey Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * This class is used as a basis for implementing an {@link org.eclipse.jface.action.IAction} 
 * on the menubar, the toolbar, or a pop-up menu by delegating all required
 * behaviour to a {@link Command}, only when it is guaranteed that the
 * selection will not change during the life of the action.  In other words,
 * the action itself would be created based on the selection, and destroyed
 * when the selection changed.  All possible aspects of the action are
 * delegated to the command, namely the enablement state and, if it
 * implements {@link CommandActionDelegate}, the text, the toolbar icon, and
 * the tool tip text; however, this need only be done once, at the time the
 * action is created.
 *
 * <p>Subclasses must provide an implementation for {@link #createActionCommand} 
 * that creates the command to perform this action.
 * They may also override {@link #getDefaultImageDescriptor} to provide a
 * default icon and {@link #disable} to set the action's state when a command
 * cannot be created.
 */
public abstract class StaticSelectionCommandAction extends Action
{
  /**
   * This records the editing domain of the current editor or viewer.  For global
   * popups, we try to determine the editing domain from the selected
   * objects themselves.
   */
  protected EditingDomain editingDomain;

  /**
   * This records the command.
   */
  protected Command command;

  /**
   * This constructs an instance for a command to be executed via
   * workbenchPart's editing domain.
   * @since 2.1.0
   */
  public StaticSelectionCommandAction(IWorkbenchPart workbenchPart)
  {
    super();

    // try to get editing domain from workbench part
    if (workbenchPart instanceof IEditingDomainProvider)
    {
      editingDomain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
    }
  }

  /**
   * This constructor is simply retained for binary compatibility. It just
   * calls the {@link #StaticSelectionCommandAction(IWorkbenchPart) new form}.
   */
  public StaticSelectionCommandAction(IEditorPart editorPart)
  {
    this((IWorkbenchPart)editorPart);
  }

  /**
   * This constructs an instance for a command to be executed via the given editing domain.
   * @since 2.4.0
   */
  public StaticSelectionCommandAction(EditingDomain editingDomain)
  {
    this.editingDomain = editingDomain;
  }

  /**
   * This constructs an instance without a specified workbenchPart.
   */
  public StaticSelectionCommandAction()
  {
    super();
  }

  /**
   * This should be implemented to create a command that performs the action.
   */
  protected abstract Command createActionCommand(EditingDomain editingDomain, Collection<?> collection);

  /**
   * This extracts the objects from selection, invokes createActionCommand
   * to create the command, and then configures the action based on the
   * result.
   */
  public void configureAction(ISelection selection)
  {
    // only handle structured selections
    if (!(selection instanceof IStructuredSelection))
    {
      disable();
    }
    else
    {
      // convert the selection to a collection of the selected objects
      IStructuredSelection sselection = (IStructuredSelection) selection;
      List<?> list = sselection.toList();
      Collection<Object> collection = new ArrayList<Object>(list);
      
      // if the editing domain wasn't given by the workbench part, try to get
      // it from the selection
      if (editingDomain == null)
      {
        for (Object o : collection)
        {
          editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(o);
          if (editingDomain != null)
          {
            break;
          }
        }
      }

      // if we found an editing domain, create command
      if (editingDomain != null)
      {
        command = createActionCommand(editingDomain, collection);
        setEnabled(command.canExecute());
      }

      // give up if we couldn't create the command; otherwise, use a
      // CommandActionDelegate to set the action's text, tool-tip, icon,
      // etc. or just use the default icon
      if (command == null || command == UnexecutableCommand.INSTANCE)
      {
        disable();
      }
      else if (!(command instanceof CommandActionDelegate))
      {
        if (getDefaultImageDescriptor() != null)
        {
          setImageDescriptor(getDefaultImageDescriptor());
        }
      }
      else
      {
        CommandActionDelegate commandActionDelegate =
          (CommandActionDelegate) command;

        ImageDescriptor imageDescriptor =
          objectToImageDescriptor(commandActionDelegate.getImage());
        if (imageDescriptor != null)
        {
          setImageDescriptor(imageDescriptor);
        }
        else if (getDefaultImageDescriptor() != null)
        {
          setImageDescriptor(getDefaultImageDescriptor());
        }

        if (commandActionDelegate.getText() != null)
        {
          setText(commandActionDelegate.getText());
        }
        
        if (commandActionDelegate.getDescription() != null)
        {
          setDescription(commandActionDelegate.getDescription());
        }

        if (commandActionDelegate.getToolTipText() != null)
        {
          setToolTipText(commandActionDelegate.getToolTipText());
        }
      }
    }
  }

  /**
   * This can be overridden to provide the image descriptor used when the
   * command does not provide one.  This implementation simply returns null.
   */
  protected ImageDescriptor getDefaultImageDescriptor()
  {
    return null;
  }

  /**
   * This gets invoked when the selection is inappropriate or the command
   * cannot be created.  It puts the action in the correct state for such
   * error conditions.  This implementation disables the action and sets its
   * icon to the default.
   */
  protected void disable()
  {
    setEnabled(false);
    if (getDefaultImageDescriptor() != null)
    {
      setImageDescriptor(getDefaultImageDescriptor());
    }
  }

  /**
   * This executes the command.
   */
  @Override
  public void run()
  {
    // this guard is for extra security, but should not be necessary
    if (editingDomain != null && command != null)
    {
      // use up the command
      editingDomain.getCommandStack().execute(command);
    }
  }

  /**
   * If necessary, this converts any image representation into an image
   * descriptor.
   */
  protected ImageDescriptor objectToImageDescriptor(Object object)
  {
    return ExtendedImageRegistry.getInstance().getImageDescriptor(object);
  }
}
