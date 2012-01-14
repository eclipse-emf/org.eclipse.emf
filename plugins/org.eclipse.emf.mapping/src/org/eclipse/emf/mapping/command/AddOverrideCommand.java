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

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the AddCommand to additionally map the added object(s) to corresponding inputs.
 */
public class AddOverrideCommand extends AbstractCommand
{
  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain mappingDomain;

  /**
   * This keeps track of the AddCommand we're overriding
   */
  protected AddCommand addCommand;

  /**
   * This keeps track of the create mapping command(s) used to implement this command.
   */
  protected Command mapCommand;

  /**
   * This creates a command instance that adds and then maps the added object(s).
   */
  public AddOverrideCommand(MappingDomain domain, AddCommand addCommand)
  {
    super(addCommand.doGetLabel(), addCommand.doGetDescription());

    this.mappingDomain = domain;
    this.addCommand = addCommand;
  }

  @Override
  protected boolean prepare()
  {
    return addCommand.doCanExecute();
  }

  public void execute()
  {
    addCommand.doExecute();

    MappingRoot mappingRoot = mappingDomain.getMappingRoot();
    CompoundCommand subcommands = new CompoundCommand();
    for (Object addition : addCommand.getCollection())
    {
      for (Iterator<?> objects = mappingDomain.treeIterator(addition); objects.hasNext(); )
      {
        Object object = objects.next();
        MappedObjectState mappedObjectState = mappingRoot.getMappedObjectState(object);
        Object originatingInput = mappedObjectState.getOriginatingInput();

        // This is tricky and is done for each object here rather than once for the owner of the addCommnd.
        // We want to make sure the object is really part of the conceptual tree induced by the domain.
        //
        if (originatingInput == null)
        {
          mappedObjectState.setOutput();
        }
        else if (mappingRoot.isAttachedObject(object))
        {
          mapOutputObject(object, originatingInput, subcommands);
        }
      }
    }
    mapCommand = !subcommands.isEmpty() ? subcommands.unwrap() : null;
  }

  protected void mapOutputObject(Object outputObject, Object originatingInput, CompoundCommand subcommands)
  {
    subcommands.appendAndExecute(CreateMappingCommand.create(mappingDomain, originatingInput, outputObject));
  }

  @Override
  public void undo()
  {
    if (mapCommand != null)
    {
      mapCommand.undo();
    }
    addCommand.doUndo();          
  }

  public void redo()
  {
    addCommand.doRedo();
    if (mapCommand != null)
    {
      mapCommand.redo();
    }
  }

  @Override
  public void dispose()
  {
    if (mapCommand != null) 
    {
      mapCommand.dispose();
    }
    addCommand.doDispose();
  }

  @Override
  public Collection<?> getResult()
  {
    Collection<Object> result = new ArrayList<Object>();
    result.addAll(addCommand.doGetResult());
    if (mapCommand != null)
    {
      result.addAll(mapCommand.getResult());
    }
    return result;
  }

  @Override
  public Collection<?> getAffectedObjects()
  {
    Collection<Object> result = new ArrayList<Object>();
    result.addAll(addCommand.doGetAffectedObjects());
    if (mapCommand != null)
    {
      result.addAll(mapCommand.getAffectedObjects());
    }
    return result;
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
