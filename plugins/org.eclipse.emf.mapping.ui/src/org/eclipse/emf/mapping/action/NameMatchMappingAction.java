/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: NameMatchMappingAction.java,v 1.1 2004/03/06 17:31:33 marcelop Exp $
 */
package org.eclipse.emf.mapping.action;


import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;

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
import org.eclipse.emf.mapping.command.NameMatchMappingCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;
import org.eclipse.emf.mapping.presentation.IComposedSelection;
import org.eclipse.emf.mapping.presentation.MappingUIPlugin;


/**
 */
public class NameMatchMappingAction extends CommandAction implements CommandStackListener
{
  public void setActiveEditor(IAction action, IEditorPart editorPart)
  {
    if (editingDomain != null)
    {
      editingDomain.getCommandStack().removeCommandStackListener(this);
    }

    super.setActiveEditor(action, editorPart);

    if (editingDomain != null)
    {
      editingDomain.getCommandStack().addCommandStackListener(this);
    }
  }

  public void commandStackChanged(EventObject event)
  {
    selectionChanged(action, ((ISelectionProvider)editorPart).getSelection());
  }

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
    return MappingPlugin.getPlugin().getImage("full/etool16/MatchByName");
  }

  public static class DelegateCommand extends CompoundCommand implements CommandActionDelegate
  {
    protected MappingDomain mappingDomain;
    protected Collection collection;
    protected Command createMappingCommand;

    public DelegateCommand(EditingDomain editingDomain, CommandParameter commandParameter)
    {
      super
        (MappingUIPlugin.getPlugin().getString("_UI_NameMatchMappingAction_label"),
         MappingUIPlugin.getPlugin().getString("_UI_NameMatchMappingAction_description"));

      collection = commandParameter.getCollection();
      mappingDomain = (MappingDomain)editingDomain;
    }

    protected boolean prepare()
    {
      boolean result = false;

      if (collection != null)
      {
        Collection mappedObjects = new HashSet();
        Collection mappingObjects = new HashSet();
        MappingRoot mappingRoot = mappingDomain.getMappingRoot();
    
        for (Iterator objects = collection.iterator(); objects.hasNext(); )
        {
          Object object = objects.next();
          if (object instanceof Mapping)
          {
            appendIfCanExecute(NameMatchMappingCommand.create(mappingDomain, (Mapping)object));
            mappingObjects.add(object);
          }
          else if (mappingRoot.isInputObject(object) || mappingRoot.isOutputObject(object))
          {
            mappedObjects.add(object);
          }
        }

        if (!mappedObjects.isEmpty())
        {
          Collection mappings = mappingRoot.getAllMappings(mappedObjects);
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
              result = appendIfCanExecute(NameMatchMappingCommand.create(mappingDomain, (Mapping)mappings.iterator().next()));
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

    public void execute()
    {
      super.execute();
      if (createMappingCommand != null)
      {
        appendAndExecute(NameMatchMappingCommand.create(mappingDomain, (Mapping)createMappingCommand.getResult().iterator().next()));
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
  } 

  protected ImageDescriptor objectToImageDescriptor(Object object)
  {
    MappingDomain mappingDomain = (MappingDomain)editingDomain;

    ((Action)action).setHoverImageDescriptor
      (ExtendedImageRegistry.getInstance().getImageDescriptor(MappingPlugin.getPlugin().getImage("full/ctool16/MatchByName")));

    ((Action)action).setDisabledImageDescriptor
      (ExtendedImageRegistry.getInstance().getImageDescriptor(MappingPlugin.getPlugin().getImage("full/dtool16/MatchByName")));

    return
      ExtendedImageRegistry.getInstance().getImageDescriptor(MappingPlugin.getPlugin().getImage("full/etool16/MatchByName"));
  }

  /**
   * Match the command for this action
   */
  protected Command createActionCommand(EditingDomain editingDomain, final Collection collection)
  {
    return editingDomain.createCommand(DelegateCommand.class, new CommandParameter(null, null, collection));
  }
}
