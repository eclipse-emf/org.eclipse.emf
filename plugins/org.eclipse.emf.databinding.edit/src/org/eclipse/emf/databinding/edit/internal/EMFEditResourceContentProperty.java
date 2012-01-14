/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.databinding.edit.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.internal.EMFResourceContentProperty;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @since 2.6
 */
public class EMFEditResourceContentProperty extends EMFResourceContentProperty
{
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   */
  public EMFEditResourceContentProperty(EditingDomain editingDomain)
  {
    this.editingDomain = editingDomain;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetList(Object source, List list, ListDiff diff)
  {
    final ListVisitorImpl visitor = new ListVisitorImpl((Resource)source);
    diff.accept(visitor);
    editingDomain.getCommandStack().execute
      (new CompoundCommand()
       {
         protected int index = 0;

         @Override
         protected boolean prepare()
         {
           for (int size = visitor.commands.size(); index < size; ++index)
           {
             if (visitor.commands.get(index).canExecute())
             {
               return true;
             }
           }
           return false;
         }

         @Override
         public void execute()
         {
           for (int size = visitor.commands.size(); index < size; ++index)
           {
             appendAndExecute(visitor.commands.get(index));
           }
         }
       });
  }

  private class ListVisitorImpl extends ListDiffVisitor
  {
    private Resource resource;
    protected final List<Command> commands = new ArrayList<Command>();

    private ListVisitorImpl(Resource resource)
    {
      this.resource = resource;
    }

    @Override
    public void handleAdd(int index, Object element)
    {
      commands.add(new AddCommand(editingDomain, resource.getContents(), element, index));
    }

    @Override
    public void handleMove(int oldIndex, int newIndex, Object element)
    {
      commands.add(new MoveCommand(editingDomain, resource.getContents(), element, newIndex));
    }

    @Override
    public void handleReplace(int index, Object oldElement, Object newElement)
    {
      commands.add(new ReplaceCommand(editingDomain, resource.getContents(), oldElement, Collections.singleton(newElement)));
    }

    @Override
    public void handleRemove(int index, Object element)
    {
      commands.add(new RemoveCommand(editingDomain, resource.getContents(), element));
    }
  }
}