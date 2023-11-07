/**
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   Tom Schindl <tom.schindl@bestsolution.at> - Initial API and implementation (bug 262160)
 */
package org.eclipse.emf.databinding.edit.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.internal.EMFListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditListProperty extends EMFListProperty
{
  private final EditingDomain editingDomain;

  public EMFEditListProperty(EditingDomain editingDomain, EStructuralFeature eStructuralFeature)
  {
    super(eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  @Override
  protected void doSetList(Object source, List list, ListDiff diff)
  {
    final ListVisitorImpl visitor = new ListVisitorImpl((EObject)source, getFeature());
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
    private EObject eObj;
    private EStructuralFeature feature;
    protected final List<Command> commands = new ArrayList<Command>();

    private ListVisitorImpl(EObject eObj, EStructuralFeature feature)
    {
      this.eObj = eObj;
      this.feature = feature;
    }

    @Override
    public void handleAdd(int index, Object element)
    {
      commands.add(AddCommand.create(editingDomain, eObj, feature, element, index));
    }

    @Override
    public void handleMove(int oldIndex, int newIndex, Object element)
    {
      if (feature.isUnique())
      {
        commands.add(MoveCommand.create(editingDomain, eObj, feature, element, newIndex));
      }
      else
      {
        commands.add(MoveCommand.create(editingDomain, eObj, feature, oldIndex, newIndex));
      }
    }

    @Override
    public void handleReplace(int index, Object oldElement, Object newElement)
    {
      if (feature.isUnique())
      {
        commands.add(ReplaceCommand.create(editingDomain, eObj, feature, oldElement, Collections.singleton(newElement)));
      }
      else
      {
        commands.add(SetCommand.create(editingDomain, eObj, feature, newElement, index));
      }
    }

    @Override
    public void handleRemove(int index, Object element)
    {
      if (feature.isUnique())
      {
        commands.add(RemoveCommand.create(editingDomain, eObj, feature, element));
      }
      else
      {
        commands.add(RemoveCommand.create(editingDomain, eObj, feature, index));
      }
    }
  }
}
