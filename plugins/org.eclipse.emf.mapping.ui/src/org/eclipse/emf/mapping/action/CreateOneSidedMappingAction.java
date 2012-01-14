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
package org.eclipse.emf.mapping.action;


import java.util.Collection;
import java.util.EventObject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CommandAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.command.CreateMappingCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;
import org.eclipse.emf.mapping.provider.MappingItemProvider;


/**
 */
public class CreateOneSidedMappingAction extends CommandAction implements CommandStackListener
{
  @Override
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (editingDomain != null)
    {
      editingDomain.getCommandStack().removeCommandStackListener(this);
    }

    super.setActiveWorkbenchPart(workbenchPart);

    if (editingDomain != null)
    {
      editingDomain.getCommandStack().addCommandStackListener(this);
    }
  }

  public void commandStackChanged(EventObject event)
  {
    selectionChanged(action, ((ISelectionProvider)workbenchPart).getSelection());
  }

  /**
   * This returns the image that is used if the command does not provide an override.
   */
  protected Object getDefaultImage()
  {
    return MappingPlugin.getPlugin().getImage("full/etool16/CreateOneToOneMapping");
  }

  public static class DelegateCommand extends CommandWrapper implements CommandActionDelegate
  {
    protected MappingDomain mappingDomain;
    protected Collection<?> collection;

    public DelegateCommand(EditingDomain editingDomain, CommandParameter commandParameter)
    {
      super(CreateMappingCommand.create((MappingDomain)editingDomain, commandParameter.getCollection()));
      mappingDomain = (MappingDomain)editingDomain;
      collection = commandParameter.getCollection();
    }

    /**
     * This returns the icon, if any, of the action.
     */
    public Object getImage()
    {
      return "Placeholder";
    }

    public String getText()
    {
      return getLabel();
    }

    /**
     * This returns the tool tip text, if any, of the action.
     */
    public String getToolTipText()
    {
      return getDescription();
    }
  }


  @Override
  protected ImageDescriptor objectToImageDescriptor(Object object)
  {
    MappingDomain mappingDomain = (MappingDomain)editingDomain;

    ((Action)action).setHoverImageDescriptor
      (ExtendedImageRegistry.getInstance().getImageDescriptor
        (MappingItemProvider.getImage(mappingDomain.getMappingRoot(), "full/ctool16/Create", collection, true)));

    ((Action)action).setDisabledImageDescriptor
      (ExtendedImageRegistry.getInstance().getImageDescriptor
        (MappingItemProvider.getImage(mappingDomain.getMappingRoot(), "full/dtool16/Create", collection, true)));

    ((Action)action).setEnabled(!action.isEnabled());
    ((Action)action).setEnabled(!action.isEnabled());

    ImageDescriptor result=
      ExtendedImageRegistry.getInstance().getImageDescriptor
        (MappingItemProvider.getImage(mappingDomain.getMappingRoot(), "full/etool16/Create", collection, true));

    ((Action)action).setEnabled(!action.isEnabled());
    ((Action)action).setImageDescriptor(result);
    ((Action)action).setEnabled(!action.isEnabled());

    return result;
  }

  /**
   * Create the command for this action
   */
  @Override
  protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection)
  {
    return editingDomain.createCommand(DelegateCommand.class, new CommandParameter(null, null, collection));
  }
}
