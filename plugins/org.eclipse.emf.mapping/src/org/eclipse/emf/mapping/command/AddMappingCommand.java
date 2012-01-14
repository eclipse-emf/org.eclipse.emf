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
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * The create mapping command creates a new mapping in a {@link MappingDomain} 
 * from a set of the domain's input and output objects.
 */
public class AddMappingCommand extends AbstractCommand 
{
  /**
   * This creates a command that adds the new mappings in the collection into the appropriate place in the mapping root's.
   */
  public static Command create(MappingDomain domain, Collection<?> collection)
  {
    return 
      domain.createCommand
        (AddMappingCommand.class, 
         new CommandParameter(domain.getMappingRoot(), null, collection));
  }

  /**
   * This creates a command that adds the new mappings in the collection into the appropriate place in the mapping root's.
   */
  public static Command create(MappingDomain domain, Mapping mapping)
  {
    return create(domain, Collections.singleton(mapping));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = MappingPlugin.getPlugin().getString("_UI_AddMappingCommand_label");

  /**
   * This cachaes the description.
   */
  protected static final String DESCRIPTION = MappingPlugin.getPlugin().getString("_UI_AddMappingCommand_description");

  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain domain;

  /**
   * This keeps track of the input and output objects that are to be mapped.
   */
  protected Collection<?> collection;

  /**
   * This keeps track of all the subcommand(s) use to implement this command.
   */
  Command subcommand;

  /**
   * This creates a command instance that adds the new mappings in the collection into the appropriate place in the mapping root's.
   */
  public AddMappingCommand(MappingDomain domain, Collection<?> collection)
  {
    super(LABEL, DESCRIPTION);

    this.domain = domain;
    this.collection = collection;
  }

  @Override
  protected boolean prepare() 
  {
    boolean result = domain != null;
    for (Object object : collection)
    {
      if (!(object instanceof Mapping))
      {
        result = false;
        break;
      }
    }

    return result;
  }

  public void execute()
  {
    // This will deal with all the subcommands to modifying the root mapping tree.
    //
    CompoundCommand subcommands = new CompoundCommand();

    // For each mapping being added...
    //
    for (Object object : collection)
    {
      Mapping mapping = (Mapping)object;

      // Find the appropriate parent mapping, which at the very least, should be the mapping root itself.
      //
      Mapping parentMapping = domain.getMappingRoot().getParentMapping(mapping.getMappedObjects());

      // Make sure the back pointers to this mapping from the mapped objects is set.
      //
      domain.getMappingRoot().register(mapping);

      // Create a command to do parentMapping.getNested().add(mapping).
      //
      //if (subcommands.appendAndExecute(new AddCommand(domain, parentMapping, parentMapping.ePackageMapping().getMapping_Nested(), mapping)))
      if (subcommands.appendAndExecute(new AddCommand(domain, parentMapping, MappingPackage.eINSTANCE.getMapping_Nested(), mapping)))
      {
        // Check all the siblings to see which if any should now be nested under this new mapping.
        // The are accumulated into a list so that they can be removed as a single command with a single notification.
        //
        Collection<Mapping> siblingsToReparent = new ArrayList<Mapping>();
        for (Mapping siblingMapping : parentMapping.getNested())
        {
          if (siblingMapping != mapping)
          {
            if (domain.getMappingRoot().getParentMapping(siblingMapping.getMappedObjects()) == mapping)
            {
              siblingsToReparent.add(siblingMapping);
            }
          }
        }

        // If there are siblings that need to be reparented.
        //
        if (!siblingsToReparent.isEmpty())
        {
          // Create commands to do parentMapping.getNested().removeAll(siblingsToReparent).
          //
          subcommands.appendAndExecute
            //(new RemoveCommand(domain, parentMapping, parentMapping.ePackageMapping().getMapping_Nested(), siblingsToReparent));
            (new RemoveCommand(domain, parentMapping, MappingPackage.eINSTANCE.getMapping_Nested(), siblingsToReparent));

          // Create commands to do mapping.getNested().addAll(siblingsToReparent).
          //
          subcommands.appendAndExecute
            //(new AddCommand(domain, mapping, mapping.ePackageMapping().getMapping_Nested(), siblingsToReparent));
            (new AddCommand(domain, mapping, MappingPackage.eINSTANCE.getMapping_Nested(), siblingsToReparent));
        }
      }
    }

    subcommand = subcommands.unwrap();
  }

  @Override
  public void undo() 
  {
    for (Object object : collection)
    {
      Mapping mapping = (Mapping)object;
      domain.getMappingRoot().deregister(mapping);
    }

    subcommand.undo();
  }

  public void redo()
  {
    for (Object object : collection)
    {
      Mapping mapping = (Mapping)object;
      domain.getMappingRoot().register(mapping);
    }

    subcommand.redo();
  }

  @Override
  public Collection<?> getResult() 
  {
    return collection;
  }

  @Override
  public Collection<?> getAffectedObjects() 
  {
    return collection;
  }

  @Override
  public void dispose()
  {
    if (subcommand != null)
    {
      subcommand.dispose();
    }
    super.dispose();
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (collection: " + collection + ")");
    result.append(" (subcommand: " + subcommand + ")");

    return result.toString();
  }
}
