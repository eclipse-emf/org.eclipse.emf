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
import java.util.HashSet;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CommandAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.command.CreateMappingCommand;
import org.eclipse.emf.mapping.command.TypeMatchMappingCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;
import org.eclipse.emf.mapping.presentation.IComposedSelection;
import org.eclipse.emf.mapping.presentation.MappingUIPlugin;


/**
 */
public class TypeMatchMappingAction extends CommandAction implements CommandStackListener
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

  @Override
  public void selectionChanged(IAction action, ISelection selection)
  {
    if (selection instanceof IComposedSelection)
    {
      super.selectionChanged(action,((IComposedSelection)selection).getCombinedSelection());
    }
    else
    {
      super.selectionChanged(action, selection);
    }
  }

  /**
   * This returns the image that is used if the command does not provide an override.
   */
  protected Object getDefaultImage()
  {
    return MappingUIPlugin.getPlugin().getImage("full/etool16/MatchByType");
  }

  public static class DelegateCommand extends CompoundCommand implements CommandActionDelegate
  {
    protected MappingDomain mappingDomain;
    protected Collection<?> collection;
    protected Command createMappingCommand;

    public DelegateCommand(EditingDomain editingDomain, CommandParameter commandParameter)
    {
      super
        (MappingUIPlugin.getPlugin().getString("_UI_TypeMatchMappingAction_label"),
         MappingUIPlugin.getPlugin().getString("_UI_TypeMatchMappingAction_description"));
      collection = commandParameter.getCollection();
      mappingDomain = (MappingDomain)editingDomain;
    }

    @Override
    protected boolean prepare()
    {
      boolean result = false;

      if (collection != null)
      {
        Collection<Object> mappedObjects = new HashSet<Object>();
        Collection<Object> mappingObjects = new HashSet<Object>();
        MappingRoot mappingRoot = mappingDomain.getMappingRoot();
    
        for (Object object : collection)
        {
          if (object instanceof Mapping)
          {
            appendIfCanExecute(TypeMatchMappingCommand.create(mappingDomain, (Mapping)object));
            mappingObjects.add(object);
          }
          else if (mappingRoot.isInputObject(object) || mappingRoot.isOutputObject(object))
          {
            mappedObjects.add(object);
          }
        }

        if (!mappedObjects.isEmpty())
        {
          Collection<? extends Mapping> mappings = mappingRoot.getAllMappings(mappedObjects);
          switch (mappings.size())
          {
            case 0:
            {
              createMappingCommand = CreateMappingCommand.create(mappingDomain, mappedObjects);
              result = appendIfCanExecute(createMappingCommand);
              break;
            }
            case 1:
            {
              result = appendIfCanExecute(TypeMatchMappingCommand.create(mappingDomain, mappings.iterator().next()));
              break;
            }
            default:
            {
              break;
            }
          }
        }
      }

      result = result || !isEmpty();
      return result;
    }

    @Override
    public void execute()
    {
      super.execute();
      if (createMappingCommand != null)
      {
        appendAndExecute(TypeMatchMappingCommand.create(mappingDomain, (Mapping)createMappingCommand.getResult().iterator().next()));
      }
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

    @Override
    public void dispose()
    {
      if (createMappingCommand != null)
      {
        createMappingCommand.dispose();
      }
      super.dispose();
    }
  } 

  @Override
  protected ImageDescriptor objectToImageDescriptor(Object object)
  {
    ((Action)action).setHoverImageDescriptor
      (ExtendedImageRegistry.getInstance().getImageDescriptor(MappingPlugin.getPlugin().getImage("full/ctool16/MatchByType")));

    ((Action)action).setDisabledImageDescriptor
      (ExtendedImageRegistry.getInstance().getImageDescriptor(MappingPlugin.getPlugin().getImage("full/dtool16/MatchByType")));

    return
      ExtendedImageRegistry.getInstance().getImageDescriptor(MappingPlugin.getPlugin().getImage("full/etool16/MatchByType"));
  }

  /**
   * Match the command for this action
   */
  @Override
  protected Command createActionCommand(EditingDomain editingDomain, final Collection<?> collection)
  {
    return editingDomain.createCommand(DelegateCommand.class, new CommandParameter(null, null, collection));
  }
}
