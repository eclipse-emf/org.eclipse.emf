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
 * $Id: XMIModelResource.java,v 1.2 2009/06/01 17:19:28 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.internal.xmi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.OverrideableCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.internal.Activator;
import org.eclipse.emf.examples.databinding.project.core.model.project.Foundation;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectFactory;


public class XMIModelResource implements IModelResource
{
  private Resource resource;
  private EditingDomain wrappedEditingDomain;
  private int changes = -1;
  private boolean skipEvent;

  private EditingDomain editingDomain = new EditingDomain()
    {

      public TreeIterator< ? > treeIterator(Object object)
      {
        return wrappedEditingDomain.treeIterator(object);
      }

      public void setClipboard(Collection<Object> clipboard)
      {
        wrappedEditingDomain.setClipboard(clipboard);
      }

      public Resource loadResource(String fileNameURI)
      {
        return wrappedEditingDomain.createResource(fileNameURI);
      }

      public boolean isReadOnly(Resource resource)
      {
        return wrappedEditingDomain.isReadOnly(resource);
      }

      public boolean isControllable(Object object)
      {
        return wrappedEditingDomain.isControllable(object);
      }

      public List< ? > getTreePath(Object object)
      {
        return wrappedEditingDomain.getTreePath(object);
      }

      public Object getRoot(Object object)
      {
        return wrappedEditingDomain.getRoot(object);
      }

      public ResourceSet getResourceSet()
      {
        return wrappedEditingDomain.getResourceSet();
      }

      public Object getParent(Object object)
      {
        return wrappedEditingDomain.getParent(object);
      }

      public boolean getOptimizeCopy()
      {
        return wrappedEditingDomain.getOptimizeCopy();
      }

      public Collection< ? > getNewChildDescriptors(Object object, Object sibling)
      {
        return wrappedEditingDomain.getNewChildDescriptors(object, sibling);
      }

      public CommandStack getCommandStack()
      {
        return new CommandStack()
          {

            public void undo()
            {
              if (canUndo())
              {
                try
                {
                  skipEvent = true;
                  changes--;
                  wrappedEditingDomain.getCommandStack().undo();

                }
                finally
                {
                  skipEvent = false;
                }
              }

            }

            public void removeCommandStackListener(CommandStackListener listener)
            {
              wrappedEditingDomain.getCommandStack().removeCommandStackListener(listener);
            }

            public void redo()
            {
              if (canRedo())
              {
                try
                {
                  skipEvent = true;
                  changes++;
                  wrappedEditingDomain.getCommandStack().redo();
                }
                finally
                {
                  skipEvent = false;
                }
              }

            }

            public Command getUndoCommand()
            {
              return wrappedEditingDomain.getCommandStack().getUndoCommand();
            }

            public Command getRedoCommand()
            {
              return wrappedEditingDomain.getCommandStack().getRedoCommand();
            }

            public Command getMostRecentCommand()
            {
              return wrappedEditingDomain.getCommandStack().getMostRecentCommand();
            }

            public void flush()
            {
              wrappedEditingDomain.getCommandStack().flush();
            }

            public void execute(Command command)
            {
              wrappedEditingDomain.getCommandStack().execute(command);
            }

            public boolean canUndo()
            {
              return wrappedEditingDomain.getCommandStack().canUndo();
            }

            public boolean canRedo()
            {
              return wrappedEditingDomain.getCommandStack().canRedo();
            }

            public void addCommandStackListener(CommandStackListener listener)
            {
              wrappedEditingDomain.getCommandStack().addCommandStackListener(listener);
            }
          };
      }

      public Collection<Object> getClipboard()
      {
        return wrappedEditingDomain.getClipboard();
      }

      public Collection< ? > getChildren(Object object)
      {
        return wrappedEditingDomain.getChildren(object);
      }

      public Resource createResource(String fileNameURI)
      {
        return wrappedEditingDomain.createResource(fileNameURI);
      }

      public Command createOverrideCommand(OverrideableCommand command)
      {
        return wrappedEditingDomain.createOverrideCommand(command);
      }

      public Command createCommand(Class< ? extends Command> commandClass, CommandParameter commandParameter)
      {
        return wrappedEditingDomain.createCommand(commandClass, commandParameter);
      }
    };

  private boolean modified = false;
  private boolean beforeSavePointModified = false;
  private List<IModelResource.Listener> listeners = new ArrayList<Listener>();

  public XMIModelResource(String uri)
  {
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    ResourceSet resourceSet = new ResourceSetImpl();

    BasicCommandStack commandStack = new BasicCommandStack();
    commandStack.addCommandStackListener(new CommandStackListener()
      {

        public void commandStackChanged(EventObject event)
        {
          for (Listener l : listeners)
          {
            l.commandStackChanged();
          }
          setModified(true);
          if (changes > -1 && !skipEvent)
            changes++;
        }
      });
    wrappedEditingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, resourceSet);

    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new XMIResourceFactoryImpl());

    //FIXME We need to load the URI given!!!
    resource = resourceSet.getResource(URI.createURI(uri), true);
  }

  public Foundation getFoundation()
  {
    if (resource.getContents().size() == 0)
    {
      resource.getContents().add(ProjectFactory.eINSTANCE.createFoundation());
    }

    return (Foundation)resource.getContents().get(0);
  }

  public IStatus save()
  {
    try
    {
      resource.save(null);
      setModified(false);
    }
    catch (Exception e)
    {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
    }

    return Status.OK_STATUS;
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }

  public IStatus redo()
  {
    if (getEditingDomain().getCommandStack().canRedo())
    {
      getEditingDomain().getCommandStack().redo();
      return Status.OK_STATUS;
    }
    return Status.CANCEL_STATUS;
  }

  public IStatus undo()
  {
    if (getEditingDomain().getCommandStack().canUndo())
    {
      getEditingDomain().getCommandStack().undo();
      return Status.OK_STATUS;
    }
    return Status.CANCEL_STATUS;
  }

  public IStatus commit()
  {
    if (changes != -1)
    {
      changes = -1;
      return Status.OK_STATUS;
    }

    return Status.CANCEL_STATUS;
  }

  public IStatus rollback()
  {
    int steps = changes;
    if (changes != -1)
    {
      for (int i = 0; i < steps; i++)
      {
        if (getEditingDomain().getCommandStack().canUndo())
        {
          undo();
        }
        else
        {
          return Status.CANCEL_STATUS;
        }
      }
      changes = -1;
      setModified(beforeSavePointModified);
      return Status.OK_STATUS;
    }

    return Status.CANCEL_STATUS;
  }

  public IStatus setSavePoint()
  {
    if (changes == -1)
    {
      changes = 0;
      beforeSavePointModified = modified;
      return Status.OK_STATUS;
    }
    return Status.CANCEL_STATUS;
  }

  public boolean isDirty()
  {
    return modified;
  }

  private void setModified(boolean modified)
  {
    if (this.modified != modified)
    {
      this.modified = modified;
      for (IModelResource.Listener listener : listeners)
      {
        listener.dirtyStateChanged();
      }
    }
  }

  public void addListener(Listener listener)
  {
    listeners.add(listener);
  }

  public void removeListener(Listener listener)
  {
    listeners.remove(listener);
  }

  public void executeCmd(Command cmd)
  {
    getEditingDomain().getCommandStack().execute(cmd);
  }

  public IStatus canRedo()
  {
    return getEditingDomain().getCommandStack().canRedo() ? Status.OK_STATUS : Status.CANCEL_STATUS;
  }

  public IStatus canUndo()
  {
    return getEditingDomain().getCommandStack().canUndo() ? Status.OK_STATUS : Status.CANCEL_STATUS;
  }
}
