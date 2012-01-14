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
package org.eclipse.emf.mapping.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the RemoveCommand to additionally unmap the removed object(s).
 */
public class RemoveOverrideCommand extends AbstractCommand
{
  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain mappingDomain;

  /**
   * This keeps track of the RemoveCommand we're overriding
   */
  protected RemoveCommand removeCommand;

  /**
   * This keeps track of the remove mapping command(s) used to implement this command.
   */
  protected Command mapCommand;

  /**
   * This creates a command instance that removes and then unmaps the removed object(s).
   */
  public RemoveOverrideCommand(MappingDomain domain, RemoveCommand removeCommand)
  {
    super(removeCommand.doGetLabel(), removeCommand.doGetDescription());

    this.mappingDomain = domain;
    this.removeCommand = removeCommand;
  }

  @Override
  protected boolean prepare()
  {
    return removeCommand.doCanExecute();
  }

  public void execute()
  {
    MappingRoot mappingRoot = mappingDomain.getMappingRoot();
    CompoundCommand subcommands = new CompoundCommand();
    for (Object removal : removeCommand.getCollection())
    {
      ArrayList<Command> commandList = new ArrayList<Command>();
      for (Iterator<?> objects = mappingDomain.treeIterator(removal); objects.hasNext(); )
      {
        Object object = objects.next();
        for (Mapping mapping : mappingRoot.getMappings(object))
        {
          Collection<?> outputs = mapping.getOutputs();
          if (outputs.size() == 1 && outputs.iterator().next() == object)
          {
            commandList.add(RemoveMappingCommand.create(mappingDomain, mapping));
          }
          else
          {
            //commandList.add(RemoveCommand.create(mappingDomain, mapping, mapping.ePackageMapping().getMapping_Outputs(), object));
            commandList.add(RemoveCommand.create(mappingDomain, mapping, MappingPackage.eINSTANCE.getMapping_Outputs(), object));
          }
        }
      }
      for (ListIterator<Command> commands = commandList.listIterator(commandList.size()); commands.hasPrevious(); )
      {
        subcommands.appendAndExecute(commands.previous());
      }
    }
    mapCommand = !subcommands.isEmpty() ? subcommands.unwrap() : null;
    removeCommand.doExecute();
  }

  @Override
  public void undo()
  {
    removeCommand.doUndo();          
    if (mapCommand != null)
    {
      mapCommand.undo();
    }
  }

  public void redo()
  {
    if (mapCommand != null)
    {
      mapCommand.redo();
    }
    removeCommand.doRedo();
  }

  @Override
  public void dispose()
  {
    if (mapCommand != null) 
    {
      mapCommand.dispose();
    }
    removeCommand.doDispose();
  }

  @Override
  public Collection<?> getResult()
  {
    return removeCommand.doGetResult();
  }

  @Override
  public Collection<?> getAffectedObjects()
  {
    return removeCommand.doGetAffectedObjects();
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (mappingDomain: " + mappingDomain + ")");
    result.append(" (mapCommand: " + mapCommand + ")");

    return result.toString();
  }
}
